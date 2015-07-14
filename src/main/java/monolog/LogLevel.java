/*
 * Copyright (c) 2015 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package monolog;

/**
 * List of available log levels that can be used with the logger.
 *
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
public enum LogLevel
{
    TRACE,
    DEBUG,
    INFO,
    WARN,
    ERROR,
    FATAL;

    final public static LogLevel[] ALL = new LogLevel[] {
        TRACE,
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL
    };
}
