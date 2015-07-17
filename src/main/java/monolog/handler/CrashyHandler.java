/*
 * Copyright (c) 2015 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package monolog.handler;

import monolog.LogLevel;
import monolog.Record;

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
        throw new ErrorException(record.getMessage().toString(), record.getCause());
    }
}
