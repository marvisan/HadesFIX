/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EngineTimerTask.java
 *
 * $Id$
 */
package net.hades.fix.engine.process.protocol.timer;

import net.hades.fix.engine.process.protocol.state.StateProcessor;

/**
 * Super class for all timer classes.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public abstract class EngineTimerTask implements Runnable {

    protected StateProcessor stateProcessor;

    public EngineTimerTask(StateProcessor stateProcessor) {
        this.stateProcessor = stateProcessor;
    }

    public StateProcessor getStateProcessor() {
        return stateProcessor;
    }
}
