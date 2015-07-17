/*
 * Copyright (c) 2015 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package monolog.handler.analytics;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import monolog.LogName;
import monolog.Record;
import monolog.handler.Handler;
import monolog.handler.HandlerResult;

/**
 * Sends log statements to Google Analytics.
 *
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
@SuppressWarnings("unused")
public class AnalyticsHandler implements Handler
{
    final private Tracker analyticsTracker;

    public AnalyticsHandler(Tracker analyticsTracker)
    {
        this.analyticsTracker = analyticsTracker;
    }

    @Override
    public HandlerResult handle(Record record)
    {
        switch (record.getLevel()) {
            case ERROR:
                this.handleError(record);
                return HandlerResult.HANDLED;
            case FATAL:
                this.handleFatal(record);
                return HandlerResult.HANDLED;
            case TRACE:
                this.handleTrace(record);
                return HandlerResult.HANDLED;

            default:
                return HandlerResult.PASSED;
        }
    }

    private void handleTrace(Record record)
    {
        if (null == record.getMessage()) {
            return;
        }

        if (record.getMessage().getClass().isAnnotationPresent(LogName.class)) {
            this.logScreen(record.getMessage());
            return;
        }
        if (record.getMessage() instanceof TrackedScreen) {
            this.logScreen((TrackedScreen) record.getMessage());
            return;
        }
        if (record.getMessage() instanceof TrackedEvent) {
            this.logEvent((TrackedEvent) record.getMessage());
            return;
        }
    }


    /**
     * Adds an event hit with Google Analytics.
     */
    private void logEvent(TrackedEvent event)
    {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder();
        builder.setCategory(event.getCategory());
        builder.setAction(event.getAction());
        builder.setLabel(event.getLabel());
        if (null != event.getValue()) {
            builder.setValue(event.getValue());
        }
        this.analyticsTracker.send(builder.build());
    }

    /**
     * Adds a screen hit with Google Analytics.
     */
    private void logScreen(TrackedScreen screen)
    {
        this.analyticsTracker.setScreenName(screen.getName());
        this.analyticsTracker.send(new HitBuilders.AppViewBuilder().build());
    }

    /**
     * Adds a screen hit with Google Analyics based off of an object's
     * ScreenName annotation.
     *
     * @param o The object containing a DisplayName annotation to be logged.
     * @see monolog.LogName
     */
    private void logScreen(Object o)
    {
        LogName annotation = o.getClass().getAnnotation(LogName.class);
        if (null == annotation) {
            return;
        }

        String name = annotation.value();
        this.analyticsTracker.setScreenName(name);
        this.analyticsTracker.send(new HitBuilders.AppViewBuilder().build());
    }

    private void handleFatal(Record record)
    {
        HitBuilders.ExceptionBuilder builder = new HitBuilders.ExceptionBuilder();
        builder.setDescription(this.getDescription(record));
        builder.setFatal(true);

        this.analyticsTracker.send(builder.build());
    }

    private void handleError(Record record)
    {
        HitBuilders.ExceptionBuilder builder = new HitBuilders.ExceptionBuilder();
        builder.setDescription(this.getDescription(record));
        builder.setFatal(false);

        this.analyticsTracker.send(builder.build());
    }

    private String getDescription(Record record)
    {
        if (null == record.getCause()) {
            return this.getMessageString(record.getMessage());
        } else {
            return this.getMessageString(record.getMessage()) + ":" + record.getCause().getMessage();
        }
    }

    private String getMessageString(Object message)
    {
        if (null == message) {
            return "NULL";
        }

        return message.toString();
    }
}
