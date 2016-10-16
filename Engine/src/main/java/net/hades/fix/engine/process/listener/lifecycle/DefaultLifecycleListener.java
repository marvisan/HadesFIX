/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DefaultLifecycleListener.java
 *
 * $Id: DefaultLifecycleListener.java,v 1.1 2011-03-21 04:54:25 vrotaru Exp $
 */
package net.hades.fix.engine.process.listener.lifecycle;

import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.listener.LifeCycleListener;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Default implementation of the lifecycle listener that logs only in debug mode
 * the events.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/03/2011
 */
public class DefaultLifecycleListener implements LifeCycleListener {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final Logger LOGGER = Logger.getLogger(DefaultLifecycleListener.class.getName());

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public void onLifeCycleEvent(LifeCycleEvent event) {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "LIFECYCLE EVNT : {0}", event.toString());
        }
        HadesInstance.getOpenMBean().sendNotification(event.buildNotification());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
