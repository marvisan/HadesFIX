/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol.timer;

import net.hades.fix.engine.process.protocol.Protocol;

/**
 * Super class for all timer classes.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public abstract class EngineTimerTask implements Runnable {

    protected Protocol protocol;

	public EngineTimerTask(Protocol protocol) {
		this.protocol = protocol;
	}
}

