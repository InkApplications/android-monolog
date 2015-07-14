/*
 * Copyright (c) 2015 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package monolog.handler;

/**
 * Describes how a handler dealt with a record, and whether or not it was
 * handled, and whether the logger should continue.
 *
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
public enum HandlerResult
{
    /** Handler did NOT deal with the record. */
    PASSED,
    /** Handler sent the record into its logs and the logger should continue. */
    HANDLED,
    /** Handler sent the record to its logs and the logger should NOT continue. */
    FINISHED
}
