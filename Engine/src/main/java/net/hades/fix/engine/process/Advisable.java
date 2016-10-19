/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process;

import net.hades.fix.engine.process.listener.AlertListener;
import net.hades.fix.engine.process.listener.LifeCycleListener;
import net.hades.fix.engine.process.listener.MessageListener;

/**
 * Interface that define the contract to receive events in the system.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public interface Advisable extends AlertListener, LifeCycleListener, MessageListener {
}
