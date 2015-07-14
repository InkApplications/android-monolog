/*
 * Copyright (c) 2015 Ink Applications, LLC.
 * Distributed under the MIT License (http://opensource.org/licenses/MIT)
 */
package monolog.handler;

import monolog.LogLevel;
import monolog.Record;

/**
 * A log handler that does nothing.
 *
 * Simply marks every log result as 'handled' for the levels it is enabled for.
 *
 * Since this class does nothing, there shouldn't be any cause to add to it, so
 * this class has been sealed from extension.
 *
 * @author Maxwell Vandervelde (Max@MaxVandervelde.com)
 */
@SuppressWarnings("UnusedDeclaration")
final public class NullHandler extends SwitchedHandler
{
    /**
     * @param levels Log levels that this will be enabled for.
     */
    public NullHandler(LogLevel[] levels)
    {
        super(levels);
    }

    @Override
    protected HandlerResult log(Record record)
    {
        return HandlerResult.HANDLED;
    }
}
