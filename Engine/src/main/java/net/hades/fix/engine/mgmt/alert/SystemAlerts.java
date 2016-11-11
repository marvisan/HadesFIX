/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SystemAlerts.java
 *
 * $Id: SystemAlerts.java,v 1.1 2009-12-27 04:36:50 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt.alert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Holder class for all system alerts. This class is thread safe.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 07/07/2009, 3:39:49 PM
 */
public class SystemAlerts {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private final List<Alert> alerts;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SystemAlerts() {
        alerts = Collections.synchronizedList(new ArrayList<Alert>());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public void addAlert(Alert alert) {
        if (alert != null) {
            alerts.add(alert);
        }
    }

    /**
     * Gets all the alerts and drain all the list.
     * @return all alerts recorded.
     */
    public List<Alert> getAlerts() {
        List<Alert> copy = new ArrayList<Alert>(alerts);
        synchronized (alerts) {
            alerts.clear();
        }
        return copy;
    }

    /**
     * Gets all the alerts of the given severity and drain all the list for those returned.
     * @param severityType filter for the severity type
     * @return all alerts recorded.
     */
    public synchronized List<Alert> getAlerts(BaseSeverityType severityType) {
        if (severityType == null) {
            throw new IllegalArgumentException("Severity type parameter cannot be null.");
        }
        List<Alert> selected = new ArrayList<Alert>();
        synchronized (alerts) {
            for (Iterator<Alert> it = alerts.iterator(); it.hasNext();) {
                Alert alert = it.next();
                if (severityType.equals(alert.getSeverity())) {
                    selected.add(alert);
                    it.remove();
                }
            }
        }
        
        return selected;
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
