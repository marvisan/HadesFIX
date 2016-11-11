/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Advisable.java
 *
 * $Id: Reportable.java,v 1.2 2010-11-13 08:23:05 vrotaru Exp $
 */
package net.hades.fix.engine.process;

import net.hades.fix.engine.process.listener.AlertListener;
import net.hades.fix.engine.process.listener.LifeCycleListener;
import net.hades.fix.engine.process.listener.MessageListener;

/**
 * Interface implemented by a managed process that allows for Alert or LifeCycle
 * subscriber.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 */
public interface Reportable {

    /**
     * Adds an alert event listener to this component.
     * @param listener alert listener
     */
    void addAlertListener(AlertListener listener);

    /**
     * Adds an alert event listener to this component.
     * @param listener alert listener
     */
    void addLifeCycleListener(LifeCycleListener listener);

     /**
     * Adds a message event listener to this component.
     * @param listener alert listener
     */
    void addMessageListener(MessageListener listener);

}
