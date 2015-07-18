/*
 * Copyright (c) 2015 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package monolog;

import monolog.handler.Handler;
import monolog.handler.HandlerResult;

/**
 * Monolog Logger.
 *
 * This logger contains an array of handlers that will be invoked in order with
 * any logged messages until either all of the handlers have been invoked or
 * until a handler returns a `FINISHED` response.
 * If no handlers are are bound and the class does not allow unhandled errors
 * it will throw an IllegalArgumentException. This feature can be enabled during
 * development so that you can be sure there is a handler for ever given error
 * type.
 *
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
@SuppressWarnings("UnusedDeclaration")
public class Monolog
{
    final private Handler[] handlers;
    final private boolean allowUnhandled;

    public Monolog(Handler[] handlers)
    {
        this(handlers, true);
    }

    /**
     * @param handlers serviced to be invoked, in order, to send messages to
     *                 various logs.
     * @param allowUnhandled whether to allow log records to be unhandled by any
     *                       of the specified handlers without error.
     * @throws IllegalArgumentException if an empty set of handlers is provided.
     */
    public Monolog(Handler[] handlers, boolean allowUnhandled)
    {
        if (0 == handlers.length) {
            throw new IllegalArgumentException("You are required to add at least one handler.");
        }

        this.allowUnhandled = allowUnhandled;
        this.handlers = handlers;
    }

    /**
     * Logs a message with the specified level.
     *
     * @param level The severity/class of the log statement.
     * @param message The message to be recorded.
     * @param cause Optional root-cause of the issue being described.
     */
    public void log(LogLevel level, Object message, Throwable cause)
    {
        Record record = new Record(level, message, cause);

        this.notifyHandlers(record);
    }

    /**
     * Sends the record through the chain of handlers until handled or finished.
     *
     * @param record The log to notify handlers of.
     */
    final protected void notifyHandlers(Record record)
    {
        boolean handled = false;
        for (Handler handler : this.handlers) {
            HandlerResult result = handler.handle(record);

            if (result == HandlerResult.HANDLED) {
                handled = true;
            }
            if (result == HandlerResult.FINISHED) {
                return;
            }
        }

        if (false == handled && false == this.allowUnhandled) {
            throw new IllegalArgumentException("No handlers available to handle logged object");
        }
    }

    /**
     * Logs a message with debug log level.
     *
     * @param message log this message
     */
    final public void debug(Object message)
    {
        this.log(LogLevel.DEBUG, message, null);
    }

    /**
     * Logs an error with debug log level.
     *
     * @param message log this message
     * @param cause log this cause
     */
    final public void debug(Object message, Throwable cause)
    {
        this.log(LogLevel.DEBUG, message, cause);
    }

    /**
     * Logs a message with error log level.
     *
     * @param message log this message
     */
    final public void error(Object message)
    {
        this.log(LogLevel.ERROR, message, null);
    }

    /**
     * Logs an error with error log level.
     *
     * @param message log this message
     * @param cause log this cause
     */
    final public void error(Object message, Throwable cause)
    {
        this.log(LogLevel.ERROR, message, cause);
    }

    /**
     * Logs a message with fatal log level.
     *
     * @param message log this message
     */
    final public void fatal(Object message)
    {
        this.log(LogLevel.FATAL, message, null);
    }

    /**
     * Logs an error with fatal log level.
     *
     * @param message log this message
     * @param cause log this cause
     */
    final public void fatal(Object message, Throwable cause)
    {
        this.log(LogLevel.FATAL, message, cause);
    }

    /**
     * Logs a message with info log level.
     *
     * @param message log this message
     */
    final public void info(Object message)
    {
        this.log(LogLevel.INFO, message, null);
    }

    /**
     * Logs an error with info log level.
     *
     * @param message log this message
     * @param cause log this cause
     */
    final public void info(Object message, Throwable cause)
    {
        this.log(LogLevel.INFO, message, cause);
    }

    /**
     * Logs a message with trace log level.
     *
     * @param message log this message
     */
    final public void trace(Object message)
    {
        this.log(LogLevel.TRACE, message, null);
    }

    /**
     * Logs an error with trace log level.
     *
     * @param message log this message
     * @param cause log this cause
     */
    final public void trace(Object message, Throwable cause)
    {
        this.log(LogLevel.TRACE, message, cause);
    }

    /**
     * Logs a message with warn log level.
     *
     * @param message log this message
     */
    final public void warn(Object message)
    {
        this.log(LogLevel.WARN, message, null);
    }

    /**
     * Logs an error with warn log level.
     *
     * @param message log this message
     * @param cause log this cause
     */
    final public void warn(Object message, Throwable cause)
    {
        this.log(LogLevel.WARN, message, cause);
    }
}
