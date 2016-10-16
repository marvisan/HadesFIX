/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Advisable.java
 *
 * $Id: Advisable.java,v 1.4 2010-08-24 02:31:53 vrotaru Exp $
 */
package net.hades.fix.engine.process;

import net.hades.fix.engine.process.listener.AlertListener;
import net.hades.fix.engine.process.listener.LifeCycleListener;
import net.hades.fix.engine.process.listener.MessageListener;

/**
 * Interface that define the contract to receive events in the system.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 */
public interface Advisable extends AlertListener, LifeCycleListener, MessageListener {
}
