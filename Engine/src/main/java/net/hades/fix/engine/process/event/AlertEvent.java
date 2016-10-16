/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AlertEvent.java
 *
 * $Id: AlertEvent.java,v 1.2 2010-08-22 09:00:00 vrotaru Exp $
 */
package net.hades.fix.engine.process.event;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import javax.management.Notification;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.util.PartyUtil;

/**
 * Alert event raised by any component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 */
public class AlertEvent extends EventObject {

    private static final long serialVersionUID = 1L;
    
    public static final String ALERT_RAISED = "hadesfix.alert";

    private Alert alert;
    
    private String session;

    /**
     * Creates a Alert event.
     * @param source source of the data event
     * @param alert alert data
     */
    public AlertEvent(Object source, Alert alert) {
        super(source);
        // we need the session decoded here just in case the thread logging the alert dies
        session = PartyUtil.getSessionId(getSource());
        if (alert == null) {
            throw new IllegalArgumentException("The alert data cannot be null or empty.");
        }
        this.alert = alert;
        populateUserData();
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public String getMessage() {
        return alert.getMessage();
    }

    public Notification buildNotification() {
        Notification result = new Notification(ALERT_RAISED, source, NotificationSequenceGenerator.getInstance().getNextSequence(), alert.getTimestamp().getTime());
        result.setSource(null);
        result.setUserData(populateUserData());
        
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AlertEvent{source=").append(source != null ? source.getClass().getName() : "null")
                .append(" alert=").append(alert != null ? alert.toString() : "null").append("}");
        
        return sb.toString();
    }

    private Object populateUserData() {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("session", session);
        userData.put("code", alert.getCode());
        userData.put("message", alert.getMessage());
        userData.put("severity", alert.getSeverity() != null ? alert.getSeverity().toString() : null);
        userData.put("component", alert.getComponent());
        if (alert.getError() != null) {
            userData.put("errorMessage", alert.getError().getMessage());
            userData.put("errorStack", ExceptionUtil.getStackTrace(alert.getError()));
        }
        
        return userData;
    }

}
