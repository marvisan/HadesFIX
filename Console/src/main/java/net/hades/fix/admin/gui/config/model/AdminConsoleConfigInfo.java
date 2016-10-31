/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdminConsoleConfigInfo.java
 *
 * $Id$
 */
package net.hades.fix.admin.gui.config.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * HadesFIX administration console configuration info.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
@XmlRootElement(name="adminConsoleConfig")
@XmlType(name = "AdminConsoleConfigInfo", propOrder = {"internalInfo", "engineConnections", "alertFilter"})
@XmlAccessorType(XmlAccessType.NONE)
public class AdminConsoleConfigInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
        
    @XmlAttribute(name="configError")
    private String configError;
    
    @XmlElementRef(required = false)
    private InternalInfo internalInfo;

    @XmlElementWrapper(name = "engineConnections")
    @XmlElementRef()
    private EngineConnectionInfo[] engineConnections;
    
    @XmlElementRef()
    private AlertFilterInfo alertFilter;

    public AdminConsoleConfigInfo() {
    }

    public AdminConsoleConfigInfo(String configError) {
        this.configError = configError;
    }

    public Integer getNextConnectionId() {
        return internalInfo.getNextConnInfoId();
    }

    public String getConfigError() {
        return configError;
    }

    public void setConfigError(String configError) {
        this.configError = configError;
    }

    public InternalInfo getInternalInfo() {
        return internalInfo;
    }

    public void setInternalInfo(InternalInfo internalInfo) {
        this.internalInfo = internalInfo;
    }

    public EngineConnectionInfo[] getEngineConnections() {
        return engineConnections;
    }

    public void setEngineConnections(EngineConnectionInfo[] engineConnections) {
        this.engineConnections = engineConnections;
    }

    public AlertFilterInfo getAlertFilter() {
        return alertFilter;
    }

    public void setAlertFilter(AlertFilterInfo alertFilter) {
        this.alertFilter = alertFilter;
    }

    public EngineConnectionInfo getEngineConnectionInfo(Integer id) {
        if (engineConnections == null || engineConnections.length == 0) {
            return null;
        }
        
        for (EngineConnectionInfo conn : engineConnections) {
            if (conn.getId().equals(id)) {
                return conn;
            }
        }
        
        return null;
    }
    
    public void removeEngineConnectionInfo(Integer id) {
        if (engineConnections == null || engineConnections.length == 0) {
            return;
        }
        List<EngineConnectionInfo> engineConnectionsList = new ArrayList<EngineConnectionInfo>(Arrays.asList(engineConnections));
        for (Iterator<EngineConnectionInfo> it = engineConnectionsList.iterator(); it.hasNext();) {
            if (it.next().getId().equals(id)) {
                it.remove();
                break;
            }
        }
        setEngineConnections(engineConnectionsList.toArray(new EngineConnectionInfo[engineConnectionsList.size()]));
    }
}
