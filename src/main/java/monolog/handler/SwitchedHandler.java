/*
 * Copyright (c) 2015 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package monolog.handler;

import monolog.LogLevel;
import monolog.Record;

import java.util.Arrays;
import java.util.List;

/**
 * A log handler that can be enabled/disabled for specified log-levels.
 *
 * If the log-level is enabled, this will invoke a log method for the handler
 * to implement.
 * When the log level is disabled, this will pass on all requests to handle
 * the log statement, carrying the chain to the next available handler.
 *
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
abstract public class SwitchedHandler implements Handler
{
    final private List<LogLevel> levels;

    /**
     * @param levels Log levels that this will be enabled for.
     */
    public SwitchedHandler(LogLevel[] levels)
    {
        this.levels = Arrays.asList(levels);
    }

    /**
     * Sealed from overriding, use the `log` method to implement handling when
     * the level is enabled.
     *
     * @see #log
     */
    @Override
    final public HandlerResult handle(Record record)
    {
        if (this.levels.contains(record.getLevel())) {
            return this.log(record);
        }

        return HandlerResult.PASSED;
    }

    /**
     * Write the record to the appropriate log.
     *
     * This method is invoked when a log statement is recorded and the switch
     * is enabled for the specified log level.
     *
     * @param record The message/cause and other information that was logged.
     * @return Whether or not this logger handled the message or if the chain
     *         of logging should be stopped after invoking this handler.
     */
    abstract protected HandlerResult log(Record record);
}
