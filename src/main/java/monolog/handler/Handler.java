/*
 * Copyright (c) 2015 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package monolog.handler;

import monolog.Record;

/**
 * Service for sending logged records to the desired output source.
 *
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
public interface Handler
{
    /**
     * Takes a logged record and sends it to a target output.
     *
     * @param record The message/cause and other information that was logged.
     * @return Whether or not this logger handled the message or if the chain
     *         of logging should be stopped after invoking this handler.
     */
    HandlerResult handle(Record record);
}
