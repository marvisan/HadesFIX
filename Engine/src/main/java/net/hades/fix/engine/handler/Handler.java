/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.handler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import net.hades.fix.engine.process.ExecutionResult;
import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.message.Message;

/**
 * Contract that a message handler must implement.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @param <T> Message
 * @param <U> ExecutionResult
 */
public interface Handler<T extends Message,U extends ExecutionResult> extends Callable<U> {

    static final String CONS_PREFIX = "HC";
    static final String PROD_PREFIX = "HP";
    
    TaskStatus getStatus();
    String getId();
    void write(T message);
    boolean tryWrite(T message, int waitMillis);
    void addNextHandler(String id, Handler<T,U> next);
    Map<String, String> getStatistics();
    void shutdown();
    void shutdownImmediate();
    List<Handler<T,U>> getNextHandlers();

    /**
     * Enables/Disable a handler. By default the handler is enabled.
     * @param disabled action to be taken: disable/enable
     */
    void setDisabled(boolean disabled);

}
