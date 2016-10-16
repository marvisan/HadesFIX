/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EngineConnectionInfo.java
 *
 * $Id$
 */
package net.hades.fix.admin.gui.config.model;

import net.hades.fix.admin.gui.config.adapter.BooleanAdapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Hades engine connection info.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
@XmlRootElement(name="engineConnectionInfo")
@XmlType(name = "EngineConnectionInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class EngineConnectionInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @XmlAttribute(name="id", required = true)
    private Integer id;
    
    @XmlAttribute(name="host", required = true)
    private String host;
    
    @XmlAttribute(name="port", required = true)
    private Integer port;
    
    @XmlAttribute(name="user")
    private String user;
    
    @XmlAttribute(name="authEnabled")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean authEnabled;
    
    @XmlAttribute(name="sslUser")
    private String sslUser;
    
    @XmlAttribute(name="sslEnabled")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean sslEnabled;
    
    @XmlAttribute(name="sslCliAuthEnabled")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean sslCliAuthEnabled;
    
    @XmlAttribute(name="keystorePath")
    private String keystorePath;
    
    @XmlAttribute(name="truststorePath")
    private String truststorePath;
    
    @XmlAttribute(name="connectAtStartup")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean connectAtStartup;
    
    @XmlAttribute(name="pollingInterval")
    private Integer pollingIntervalSecs;
    
    @XmlAttribute(name="displayCcolor", required = true)
    private String displayColor;
       
    @XmlAttribute(name="engineName")
    private String engineName;
    
    @XmlAttribute(name="engineDescr")
    private String engineDescr;

    // transient values
    private boolean connected;
     
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public EngineConnectionInfo() {
        pollingIntervalSecs = new Integer(0);
        connectAtStartup = Boolean.FALSE;
        authEnabled = Boolean.FALSE;
        sslEnabled = Boolean.FALSE;
        sslCliAuthEnabled = Boolean.FALSE;
        displayColor = "0,0,0";
    }
    
    public EngineConnectionInfo(Integer id) {
        this();
        this.id = id;
    }
    
    public int[] getDisplayColorCodes() {
        if (displayColor != null && !displayColor.isEmpty() && displayColor.split(",").length == 3) {
            String[] colors = displayColor.split(",");
            return new int[] {Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2])};
        }
        
        return new int[] {255, 255, 255};
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        final Integer old = this.id;
        this.id = id;
        propertyChangeSupport.firePropertyChange("id", old, id);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        final String old = this.host;
        this.host = host;
        propertyChangeSupport.firePropertyChange("host", old, host);
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        final Integer old = this.port;
        this.port = port;
        propertyChangeSupport.firePropertyChange("port", old, port);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        final String old = this.user;
        this.user = user;
        propertyChangeSupport.firePropertyChange("user", old, user);
    }

    public Boolean getConnectAtStartup() {
        return connectAtStartup;
    }

    public void setConnectAtStartup(Boolean connectAtStartup) {
        final Boolean old = this.connectAtStartup;
        this.connectAtStartup = connectAtStartup;
        propertyChangeSupport.firePropertyChange("connectAtStartup", old, connectAtStartup);
    }

    public Boolean getAuthEnabled() {
        return authEnabled;
    }

    public void setAuthEnabled(Boolean authEnabled) {
        final Boolean old = this.authEnabled;
        this.authEnabled = authEnabled;
        propertyChangeSupport.firePropertyChange("authEnabled", old, authEnabled);
    }

    public Boolean getSslEnabled() {
        return sslEnabled;
    }

    public void setSslEnabled(Boolean sslEnabled) {
        final Boolean old = this.sslEnabled;
        this.sslEnabled = sslEnabled;
        propertyChangeSupport.firePropertyChange("sslEnabled", old, sslEnabled);
    }

    public String getSslUser() {
        return sslUser;
    }

    public void setSslUser(String sslUser) {
        final String old = this.sslUser;
        this.sslUser = sslUser;
        propertyChangeSupport.firePropertyChange("sslUser", old, sslUser);
    }

    public Boolean getSslCliAuthEnabled() {
        return sslCliAuthEnabled;
    }

    public void setSslCliAuthEnabled(Boolean sslCliAuthEnabled) {
        final Boolean old = this.sslCliAuthEnabled;
        this.sslCliAuthEnabled = sslCliAuthEnabled;
        propertyChangeSupport.firePropertyChange("sslCliAuthEnabled", old, sslCliAuthEnabled);
    }

    public String getKeystorePath() {
        return keystorePath;
    }

    public void setKeystorePath(String keystorePath) {
        final String old = this.keystorePath;
        this.keystorePath = keystorePath;
        propertyChangeSupport.firePropertyChange("keystorePath", old, keystorePath);
    }

    public String getTruststorePath() {
        return truststorePath;
    }

    public void setTruststorePath(String truststorePath) {
        final String old = this.truststorePath;
        this.truststorePath = truststorePath;
        propertyChangeSupport.firePropertyChange("truststorePath", old, truststorePath);
    }

    public Integer getPollingIntervalSecs() {
        return pollingIntervalSecs;
    }

    public void setPollingIntervalSecs(Integer pollingIntervalSecs) {
        final Integer old = this.pollingIntervalSecs;
        this.pollingIntervalSecs = pollingIntervalSecs;
        propertyChangeSupport.firePropertyChange("pollingIntervalSecs", old, pollingIntervalSecs);
    }

    public String getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(String displayCcolor) {
        final String old = this.displayColor;
        this.displayColor = displayCcolor;
        propertyChangeSupport.firePropertyChange("displayCcolor", old, displayCcolor);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l) {
	propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
	propertyChangeSupport.removePropertyChangeListener(l);
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        final boolean old = this.connected;
        this.connected = connected;
        propertyChangeSupport.firePropertyChange("connected", old, connected);
    }

    public String getEngineName() {
        return engineName;
    }

    public void setEngineName(String engineName) {
        final String old = this.engineName;
        this.engineName = engineName;
        propertyChangeSupport.firePropertyChange("engineName", old, engineName);
    }

    public String getEngineDescr() {
        return engineDescr;
    }

    public void setEngineDescr(String engineDescr) {
        final String old = this.engineDescr;
        this.engineDescr = engineDescr;
        propertyChangeSupport.firePropertyChange("engineDescr", old, engineDescr);
    }
    
}
