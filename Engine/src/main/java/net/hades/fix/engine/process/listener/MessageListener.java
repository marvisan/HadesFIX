/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LifeCycleListener.java
 *
 * $Id: MessageListener.java,v 1.1 2010-02-28 00:38:09 vrotaru Exp $
 */
package net.hades.fix.engine.process.listener;

import net.hades.fix.engine.process.event.MessageEvent;

import java.util.EventListener;

/**
 * Interface implemented by all the processes that are interested in messages generated
 * by another process.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public interface MessageListener extends EventListener {
    void onMessageEvent(MessageEvent message);
}
