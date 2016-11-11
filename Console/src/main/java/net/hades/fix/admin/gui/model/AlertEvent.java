/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AlertEvent.java
 *
 * $Id$
 */
package net.hades.fix.admin.gui.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * Holds GUI alert data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class AlertEvent implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String type;
    private String id;
    private String time;
    private String session;
    private String component;
    private String code;
    private String message;
    private String errorMessage;
    private String errorStack;
    private String displayColor;
    
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public AlertEvent() {
    }
    
    public AlertEvent(String displayColor) {
        this.displayColor = displayColor;
    }
   
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        final String old = this.code;
        this.code = code;
        propertyChangeSupport.firePropertyChange("code", old, code);
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        final String old = this.component;
        this.component = component;
        propertyChangeSupport.firePropertyChange("component", old, component);
    }

    public String getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(String displayColor) {
        this.displayColor = displayColor;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorStack() {
        return errorStack;
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        final String old = this.id;
        this.id = id;
        propertyChangeSupport.firePropertyChange("id", old, id);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        final String old = this.message;
        this.message = message;
        propertyChangeSupport.firePropertyChange("message", old, message);
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        final String old = this.session;
        this.session = session;
        propertyChangeSupport.firePropertyChange("session", old, session);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        final String old = this.time;
        this.time = time;
        propertyChangeSupport.firePropertyChange("time", old, time);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        final String old = this.type;
        this.type = type;
        propertyChangeSupport.firePropertyChange("type", old, type);
    }

    @Override
    public String toString() {
        return "AlertEvent{" + "type=" + type + ", id=" + id + ", time=" + time + ", session=" + session 
                + ", component=" + component + ", code=" + code + ", message=" + message + ", errorMessage=" + errorMessage
                + ", errorStack=" + errorStack + ", displayColor=" + displayColor + '}';
    }
    
}
