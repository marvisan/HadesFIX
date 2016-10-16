/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LifeCycleEvent.java
 *
 * $Id: LifeCycleEvent.java,v 1.5 2010-08-22 09:00:00 vrotaru Exp $
 */
package net.hades.fix.engine.process.event;

import net.hades.fix.engine.util.PartyUtil;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import javax.management.Notification;

/**
 * Event fired by a managed process to indicate a certain state.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 */
public class LifeCycleEvent extends EventObject {

    private static final long serialVersionUID = 1L;
    
    public static final String LIFECYCLE_RAISED = "hadesfix.lifecycle";

    private String code;

    private String eventType;

    private String description;
   
    private String session;

    public LifeCycleEvent(Object source) {
        super(source);
        // we need the session decoded here just in case the thread logging the alert dies
        session = PartyUtil.getSessionId(getSource());
    }

    public LifeCycleEvent(Object source, String code) {
        this(source);
        this.code = code;
    }

    public LifeCycleEvent(Object source, String eventType, String code) {
        this(source);
        this.code = code;
        this.eventType = eventType;
    }

    public LifeCycleEvent(Object source, String eventType, String code, String description) {
        this(source);
        this.code = code;
        this.eventType = eventType;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String type) {
        this.eventType = type;
    }

    public Notification buildNotification() {
        Notification result = new Notification(LIFECYCLE_RAISED, source, NotificationSequenceGenerator.getInstance().getNextSequence(), System.currentTimeMillis());
        result.setSource(null);
        result.setUserData(populateUserData());
        
        return result;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{LifeCycleEvent Source=");
        b.append(getSource().getClass().getName());
        b.append(", Session=").append(session);
        if (code != null) {
            b.append(", Code=").append(getCode());
        }
        if (code != null) {
            b.append(", Type=").append(getEventType());
        }
        if (description != null) {
            b.append(", Description=").append(getDescription());
        }
        b.append("}");
        
        return b.toString();
    }

    private Object populateUserData() {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("session", session);
        userData.put("code", code);
        userData.put("eventType", eventType);
        userData.put("description", description);
        
        return userData;
    }
}
