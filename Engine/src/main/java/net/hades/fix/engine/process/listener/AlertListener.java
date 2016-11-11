/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AlertListener.java
 *
 * $Id: AlertListener.java,v 1.2 2010-02-28 00:38:09 vrotaru Exp $
 */
package net.hades.fix.engine.process.listener;

import net.hades.fix.engine.process.event.AlertEvent;

import java.util.EventListener;

/**
 * Interface implemented by all the processes that are interested in alert events raised
 * by another process.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 */
public interface AlertListener extends EventListener {
    void onAlertEvent(AlertEvent message);
}
