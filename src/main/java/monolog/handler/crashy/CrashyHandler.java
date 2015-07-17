/*
 * Copyright (c) 2015 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package monolog.handler.crashy;

import monolog.LogLevel;
import monolog.LogName;
import monolog.Record;
import monolog.handler.HandlerResult;
import monolog.handler.SwitchedHandler;

/**
 * A log handler that crashes whenever it is used.
 *
 * This logger is intended to be used for debugging, so that errors can become
 * more prominently noticed by developers.
 *
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
public class CrashyHandler extends SwitchedHandler
{
    public CrashyHandler(LogLevel[] levels)
    {
        super(levels);
    }

    @Override
    protected HandlerResult log(Record record)
    {
        throw new ErrorException(getDisplayedMessage(record), record.getCause());
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

        return message.toString();
    }
}
