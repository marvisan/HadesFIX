/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IdleStatus.java
 *
 * $Id: IdleStatus.java,v 1.6 2011-04-03 08:00:05 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.state;

import net.hades.fix.engine.process.protocol.ProtocolState;
import net.hades.fix.engine.exception.UnrecoverableException;

/**
 * Idle state for which the processing thread just "spins" waiting for
 * an external command to replace the state.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 */
public class IdleStatus extends Status {

    private static final ProtocolState STATE = ProtocolState.IDLE;

    public IdleStatus(StateProcessor stateProcessor) {
        super(stateProcessor);
    }

    @Override
    public Status process() throws UnrecoverableException, InterruptedException {
        Thread.sleep(1);
        
        return this ;
    }

    @Override
    public String getName() {
        return ProtocolState.IDLE.name();
    }

    @Override
    public void recycle() {
        message = null;
    }

    @Override
    public ProtocolState getProtocolState() {
        return STATE;
    }
}
