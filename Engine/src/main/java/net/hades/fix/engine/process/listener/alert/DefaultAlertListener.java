/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DefaultAlertListener.java
 *
 * $Id: DefaultAlertListener.java,v 1.1 2011-03-21 04:54:25 vrotaru Exp $
 */
package net.hades.fix.engine.process.listener.alert;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.listener.AlertListener;

/**
 * Log only alert listener that logs only FATAL alerts.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/03/2011
 */
public class DefaultAlertListener implements AlertListener {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final Logger LOGGER = Logger.getLogger(DefaultAlertListener.class.getName());

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public void onAlertEvent(AlertEvent event) {
        if (BaseSeverityType.FATAL.equals(event.getAlert().getSeverity())) {
            LOGGER.log(Level.SEVERE, "FATAL alert received : {0} {1} Event raised by component : {2}",
                    new Object[] { event.getAlert().getMessage(), event.getAlert().getError() != null ? " Error message : "
                        + event.getAlert().getError().getMessage() : "", event.getAlert().getComponent() });
        } else {
            if (LOGGER.isLoggable(Level.FINEST)) {
                LOGGER.log(Level.FINEST, "ALERT EVNT : {0}", event.toString());
            }
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
