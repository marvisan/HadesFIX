/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LifeCycleListener.java
 *
 * $Id: LifeCycleListener.java,v 1.2 2010-02-28 00:38:09 vrotaru Exp $
 */
package net.hades.fix.engine.process.listener;

import java.util.EventListener;

import net.hades.fix.engine.process.event.LifeCycleEvent;

/**
 * Interface implemented by all the processes that are interested in life cycle events of another process.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 */
public interface LifeCycleListener extends EventListener {
    void onLifeCycleEvent(LifeCycleEvent message);
}
