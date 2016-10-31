/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AlertFilterInfo.java
 *
 * $Id$
 */
package net.hades.fix.admin.gui.config.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.admin.gui.config.adapter.BooleanAdapter;

/**
 * Filter data for alert events.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
@XmlRootElement(name="alertFilter")
@XmlType(name = "AlertFilterInfo", propOrder = {"components"})
@XmlAccessorType(XmlAccessType.NONE)
public class AlertFilterInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @XmlAttribute(name="filterFatal", required = true)
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean filterFatal;
       
    @XmlAttribute(name="filterWarning", required = true)
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean filterWarning;
       
    @XmlAttribute(name="filterRecoverable", required = true)
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean filterRecoverable;
       
    @XmlAttribute(name="filterInfo", required = true)
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean filterInfo;
      
    @XmlAttribute(name="filterTest", required = true)
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean filterTest;

    @XmlAttribute(name="session")
    private String session;

    @XmlElementWrapper(name = "components")
    @XmlElement(name="component")
    private List<String> components;
     
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public AlertFilterInfo() {
        filterFatal = Boolean.FALSE;
        filterWarning = Boolean.FALSE;
        filterRecoverable = Boolean.FALSE;
        filterInfo = Boolean.FALSE;
        filterTest = Boolean.FALSE;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        final List<String> old = this.components;
        this.components = components;
        propertyChangeSupport.firePropertyChange("components", old, components);
    }

    public Boolean getFilterFatal() {
        return filterFatal;
    }

    public void setFilterFatal(Boolean filterFatal) {
        final Boolean old = this.filterFatal;
        this.filterFatal = filterFatal;
        propertyChangeSupport.firePropertyChange("filterFatal", old, filterFatal);
    }

    public Boolean getFilterInfo() {
        return filterInfo;
    }

    public void setFilterInfo(Boolean filterInfo) {
        final Boolean old = this.filterInfo;
        this.filterInfo = filterInfo;
        propertyChangeSupport.firePropertyChange("filterInfo", old, filterInfo);
    }

    public Boolean getFilterRecoverable() {
        return filterRecoverable;
    }

    public void setFilterRecoverable(Boolean filterRecoverable) {
        final Boolean old = this.filterRecoverable;
        this.filterRecoverable = filterRecoverable;
        propertyChangeSupport.firePropertyChange("filterRecoverable", old, filterRecoverable);
    }

    public Boolean getFilterTest() {
        return filterTest;
    }

    public void setFilterTest(Boolean filterTest) {
        final Boolean old = this.filterTest;
        this.filterTest = filterTest;
        propertyChangeSupport.firePropertyChange("filterTest", old, filterTest);
    }

    public Boolean getFilterWarning() {
        return filterWarning;
    }

    public void setFilterWarning(Boolean filterWarning) {
        final Boolean old = this.filterWarning;
        this.filterWarning = filterWarning;
        propertyChangeSupport.firePropertyChange("filterWarning", old, filterWarning);
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        final String old = this.session;
        this.session = session;
        propertyChangeSupport.firePropertyChange("session", old, session);
    }
        
    public void addPropertyChangeListener(PropertyChangeListener l) {
	propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
	propertyChangeSupport.removePropertyChangeListener(l);
    }   
}
