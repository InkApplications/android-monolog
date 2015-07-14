/*
 * Copyright (c) 2015 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package monolog;

/**
 * Container object for all metadata for the original log statement.
 *
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
public class Record
{
    final private LogLevel level;
    final private Object message;
    final private Throwable cause;

    public Record(
        LogLevel level,
        Object message,
        Throwable cause
    ) {
        this.level = level;
        this.message = message;
        this.cause = cause;
    }

    /**
     * @return Optional root exception that caused the state being logged.
     */
    final public Throwable getCause()
    {
        return this.cause;
    }

    /**
     * @return The severity level that the message was logged with.
     */
    final public LogLevel getLevel()
    {
        return this.level;
    }

    /**
     * @return The root message that was logged.
     */
    final public Object getMessage()
    {
        return this.message;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Record record = (Record) o;

        if (cause != null ? !cause.equals(record.cause) : record.cause != null) {
            return false;
        }
        if (level != record.level) {
            return false;
        }
        if (message != null ? !message.equals(record.message) : record.message != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = level.hashCode();
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (cause != null ? cause.hashCode() : 0);
        return result;
    }
}
