/*
 * Copyright (c) 2015 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package monolog.handler;

import android.util.Log;
import monolog.LogLevel;
import monolog.Record;

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
        switch (record.getLevel()) {
            case TRACE:
                Log.v(this.tag, record.getMessage().toString(), record.getCause());
                break;
            case DEBUG:
                Log.d(this.tag, record.getMessage().toString(), record.getCause());
                break;
            case INFO:
                Log.i(this.tag, record.getMessage().toString(), record.getCause());
                break;
            case WARN:
                Log.w(this.tag, record.getMessage().toString(), record.getCause());
                break;
            case ERROR:
                Log.e(this.tag, record.getMessage().toString(), record.getCause());
                break;
            case FATAL:
                Log.e(this.tag, record.getMessage().toString(), record.getCause());
                break;
        }
    }
}
