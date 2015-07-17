/*
 * Copyright (c) 2015 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package monolog.handler.console;

import android.util.Log;
import monolog.LogLevel;
import monolog.LogName;
import monolog.Record;
import monolog.handler.HandlerResult;
import monolog.handler.SwitchedHandler;

/**
 * Sends logged messages to the Android ADB log.
 *
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
@SuppressWarnings("UnusedDeclaration")
public class ConsoleHandler extends SwitchedHandler
{
    final private String tag;

    /**
     * @param tag Tag to use when logging in ADB's log.
     * @param levels Log levels that this will be enabled for.
     */
    public ConsoleHandler(String tag, LogLevel[] levels)
    {
        super(levels);

        this.tag = tag;
    }

    @Override
    public HandlerResult log(Record record)
    {
        this.sendToConsole(record);

        return HandlerResult.HANDLED;
    }

    /**
     * write a record to ADB's log.
     *
     * @param record The record to write to the log.
     */
    protected void sendToConsole(Record record)
    {
        String message = this.getDisplayedMessage(record);

        switch (record.getLevel()) {
            case TRACE:
                Log.v(this.tag, message, record.getCause());
                break;
            case DEBUG:
                Log.d(this.tag, message, record.getCause());
                break;
            case INFO:
                Log.i(this.tag, message, record.getCause());
                break;
            case WARN:
                Log.w(this.tag, message, record.getCause());
                break;
            case ERROR:
                Log.e(this.tag, message, record.getCause());
                break;
            case FATAL:
                Log.e(this.tag, message, record.getCause());
                break;
        }
    }

    /**
     * Get the loggable message from a logged object.
     */
    private String getDisplayedMessage(Record logged)
    {
        Object message = logged.getMessage();

        if (null == message) {
            return "null";
        }

        if (message.getClass().isAnnotationPresent(LogName.class)) {
            return message.getClass().getAnnotation(LogName.class).value();
        }

        return message.toString();
    }
}
