/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process;

import java.util.concurrent.Callable;

/**
 * Engine managed task contract.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public interface ManagedTask extends Callable<ExecutionResult> {
    String getId();
    void shutdown();
    void shutdownImmediate();
    TaskStatus getStatus();
}
