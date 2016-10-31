/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EngineSessionStatistics.java
 *
 * $Id$
 */
package net.hades.fix.admin.gui.model;

import net.hades.fix.admin.command.param.SessionStatsResultParam;
import net.hades.fix.admin.console.data.TableResult;
import net.hades.fix.admin.session.SessionException;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;

import net.hades.fix.admin.console.data.MultiTableResult;

/**
 * Holds model stats for a session.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class EngineSessionStatistics implements Serializable {
    private static final long serialVersionUID = 1L;

    private String counterparty;
    private String session;
    private String sessionStartTime;
    private String transportBytesIn;
    private String transportBytesOut;
    private String transportThroughputIn;
    private String transportThroughputOut;
    private String transportReadError;
    private String protocolTotMsgInCount;
    private String protocolTotMsgOutCount;
    private String protocolThroughputIn;
    private String protocolThroughputOut;
    private String protocolRejMsgCount;
    private String protocolReadError;
    private String consStreamMsgInCount;
    private String consStreamMsgRejectCount;
    private String consStreamMsgDiscardCount;
    private String consStreamReadError;
    private String prodStreamMsgOutCount;
    private String prodStreamMsgRejectCount;
    private String prodStreamMsgDiscardCount;
    private String prodStreamReadError;
    private Integer connectionId;
    
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public EngineSessionStatistics() {
    }

    public EngineSessionStatistics(String sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }
   
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }    

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        final String old = this.counterparty;
        this.counterparty = counterparty;
        propertyChangeSupport.firePropertyChange("counterparty", old, counterparty);
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        final String old = this.session;
        this.session = session;
        propertyChangeSupport.firePropertyChange("session", old, session);
    }

    public String getConsStreamMsgDiscardCount() {
        return consStreamMsgDiscardCount;
    }

    public void setConsStreamMsgDiscardCount(String consStreamMsgDiscardCount) {
        final String old = this.consStreamMsgDiscardCount;
        this.consStreamMsgDiscardCount = consStreamMsgDiscardCount;
        propertyChangeSupport.firePropertyChange("consStreamMsgDiscardCount", old, consStreamMsgDiscardCount);
    }

    public String getConsStreamMsgRejectCount() {
        return consStreamMsgRejectCount;
    }

    public void setConsStreamMsgRejectCount(String consStreamMsgRejectCount) {
        final String old = this.consStreamMsgRejectCount;
        this.consStreamMsgRejectCount = consStreamMsgRejectCount;
        propertyChangeSupport.firePropertyChange("consStreamMsgRejectCount", old, consStreamMsgRejectCount);
    }

    public String getConsStreamReadError() {
        return consStreamReadError;
    }

    public void setConsStreamReadError(String consStreamReadError) {
        final String old = this.consStreamReadError;
        this.consStreamReadError = consStreamReadError;
        propertyChangeSupport.firePropertyChange("consStreamReadError", old, consStreamReadError);
    }

    public String getProdStreamMsgDiscardCount() {
        return prodStreamMsgDiscardCount;
    }

    public void setProdStreamMsgDiscardCount(String prodStreamMsgDiscardCount) {
        final String old = this.prodStreamMsgDiscardCount;
        this.prodStreamMsgDiscardCount = prodStreamMsgDiscardCount;
        propertyChangeSupport.firePropertyChange("prodStreamMsgDiscardCount", old, prodStreamMsgDiscardCount);
    }

    public String getProdStreamMsgRejectCount() {
        return prodStreamMsgRejectCount;
    }

    public void setProdStreamMsgRejectCount(String prodStreamMsgRejectCount) {
        final String old = this.prodStreamMsgRejectCount;
        this.prodStreamMsgRejectCount = prodStreamMsgRejectCount;
        propertyChangeSupport.firePropertyChange("prodStreamMsgRejectCount", old, prodStreamMsgRejectCount);
    }

    public String getProdStreamReadError() {
        return prodStreamReadError;
    }

    public void setProdStreamReadError(String prodStreamReadError) {
        final String old = this.prodStreamReadError;
        this.prodStreamReadError = prodStreamReadError;
        propertyChangeSupport.firePropertyChange("prodStreamReadError", old, prodStreamReadError);
    }

    public String getProtocolReadError() {
        return protocolReadError;
    }

    public void setProtocolReadError(String protocolReadError) {
        final String old = this.protocolReadError;
        this.protocolReadError = protocolReadError;
        propertyChangeSupport.firePropertyChange("protocolReadError", old, protocolReadError);
    }

    public String getTransportReadError() {
        return transportReadError;
    }

    public void setTransportReadError(String transportReadError) {
        final String old = this.transportReadError;
        this.transportReadError = transportReadError;
        propertyChangeSupport.firePropertyChange("transportReadError", old, transportReadError);
    }

    public String getConsStreamMsgInCount() {
        return consStreamMsgInCount;
    }

    public void setConsStreamMsgInCount(String consStreamMsgInCount) {
        final String old = this.consStreamMsgInCount;
        this.consStreamMsgInCount = consStreamMsgInCount;
        propertyChangeSupport.firePropertyChange("consStreamMsgInCount", old, consStreamMsgInCount);
    }

    public String getProdStreamMsgOutCount() {
        return prodStreamMsgOutCount;
    }

    public void setProdStreamMsgOutCount(String prodStreamMsgOutCount) {
        final String old = this.prodStreamMsgOutCount;
        this.prodStreamMsgOutCount = prodStreamMsgOutCount;
        propertyChangeSupport.firePropertyChange("prodStreamMsgOutCount", old, prodStreamMsgOutCount);
    }

    public String getProtocolRejMsgCount() {
        return protocolRejMsgCount;
    }

    public void setProtocolRejMsgCount(String protocolRejMsgCount) {
        final String old = this.protocolRejMsgCount;
        this.protocolRejMsgCount = protocolRejMsgCount;
        propertyChangeSupport.firePropertyChange("protocolRejMsgCount", old, protocolRejMsgCount);
    }

    public String getProtocolThroughputIn() {
        return protocolThroughputIn;
    }

    public void setProtocolThroughputIn(String protocolThroughputIn) {
        final String old = this.protocolThroughputIn;
        this.protocolThroughputIn = protocolThroughputIn;
        propertyChangeSupport.firePropertyChange("protocolThroughputIn", old, protocolThroughputIn);
    }

    public String getProtocolThroughputOut() {
        return protocolThroughputOut;
    }

    public void setProtocolThroughputOut(String protocolThroughputOut) {
        final String old = this.protocolThroughputOut;
        this.protocolThroughputOut = protocolThroughputOut;
        propertyChangeSupport.firePropertyChange("protocolThroughputOut", old, protocolThroughputOut);
    }

    public String getProtocolTotMsgInCount() {
        return protocolTotMsgInCount;
    }

    public void setProtocolTotMsgInCount(String protocolTotMsgInCount) {
        final String old = this.protocolTotMsgInCount;
        this.protocolTotMsgInCount = protocolTotMsgInCount;
        propertyChangeSupport.firePropertyChange("protocolTotMsgInCount", old, protocolTotMsgInCount);
    }

    public String getProtocolTotMsgOutCount() {
        return protocolTotMsgOutCount;
    }

    public void setProtocolTotMsgOutCount(String protocolTotMsgOutCount) {
        final String old = this.protocolTotMsgOutCount;
        this.protocolTotMsgOutCount = protocolTotMsgOutCount;
        propertyChangeSupport.firePropertyChange("protocolTotMsgOutCount", old, protocolTotMsgOutCount);
    }

    public String getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(String sessionStartTime) {
        final String old = this.sessionStartTime;
        this.sessionStartTime = sessionStartTime;
        propertyChangeSupport.firePropertyChange("sessionStartTime", old, sessionStartTime);
    }

    public String getTransportBytesIn() {
        return transportBytesIn;
    }

    public void setTransportBytesIn(String transportBytesIn) {
        final String old = this.transportBytesIn;
        this.transportBytesIn = transportBytesIn;
        propertyChangeSupport.firePropertyChange("transportBytesIn", old, transportBytesIn);
    }

    public String getTransportBytesOut() {
        return transportBytesOut;
    }

    public void setTransportBytesOut(String transportBytesOut) {
        final String old = this.transportBytesOut;
        this.transportBytesOut = transportBytesOut;
        propertyChangeSupport.firePropertyChange("transportBytesOut", old, transportBytesOut);
    }

    public String getTransportThroughputIn() {
        return transportThroughputIn;
    }

    public void setTransportThroughputIn(String transportThroughputIn) {
        final String old = this.transportThroughputIn;
        this.transportThroughputIn = transportThroughputIn;
        propertyChangeSupport.firePropertyChange("transportThroughputIn", old, transportThroughputIn);
    }

    public String getTransportThroughputOut() {
        return transportThroughputOut;
    }

    public void setTransportThroughputOut(String transportThroughputOut) {
        final String old = this.transportThroughputOut;
        this.transportThroughputOut = transportThroughputOut;
        propertyChangeSupport.firePropertyChange("transportThroughputOut", old, transportThroughputOut);
    }

    public Integer getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Integer connectionId) {
        this.connectionId = connectionId;
    }

    public void updateSessionStatistics(MultiTableResult tableResult) throws SessionException {
        if (tableResult == null || tableResult.getNoResults() == 0) {
            return;
        }
        if (!tableResult.getOutcome().booleanValue()) {
            throw new SessionException(tableResult.getErrMsg());
        }
        TableResult stats = tableResult.getTableResult(SessionStatsResultParam.SessionStats.toString());
        if (stats != null && stats.getSize() > 0) {
            List<String> row = stats.getDataRow(0);
            setCounterparty(row.get(0));
            setSession(row.get(1));
            setSessionStartTime(row.get(2));
        } else {
            setCounterparty("");
            setSession("");
            setSessionStartTime("");
        }
        TableResult transport = tableResult.getTableResult(SessionStatsResultParam.TransportStats.toString());
        if (transport != null) {
            if (transport.getOutcome().booleanValue()) {
                if (transport.getSize() > 0) {
                    List<String> row = transport.getDataRow(0);
                    setTransportBytesIn(row.get(0));
                    setTransportThroughputIn(row.get(1));
                    setTransportBytesOut(row.get(2));
                    setTransportThroughputOut(row.get(3));
                } else {
                    setTransportBytesIn("");
                    setTransportThroughputIn("");
                    setTransportBytesOut("");
                    setTransportThroughputOut("");
                }
            } else {
                setTransportReadError(transport.getErrMsg());
            }
        }
        TableResult protocol = tableResult.getTableResult(SessionStatsResultParam.ProtocolStats.toString());
        if (protocol != null) {
            if (protocol.getOutcome().booleanValue()) {
                if (protocol.getSize() > 0) {
                    List<String> row = protocol.getDataRow(0);
                    setProtocolTotMsgInCount(row.get(0));
                    setProtocolThroughputIn(row.get(1));
                    setProtocolTotMsgOutCount(row.get(2));
                    setProtocolThroughputOut(row.get(3));
                    setProtocolRejMsgCount(row.get(4));
                } else {
                    setProtocolTotMsgInCount("");
                    setProtocolThroughputIn("");
                    setProtocolTotMsgOutCount("");
                    setProtocolThroughputOut("");
                    setProtocolRejMsgCount("");
                }
            } else {
                setProtocolReadError(protocol.getErrMsg());
            }
        }
        TableResult consStream = tableResult.getTableResult(SessionStatsResultParam.ConsumerStreamStats.toString());
        if (consStream != null) {
            if (consStream.getOutcome().booleanValue()) {
                if (consStream.getSize() > 0) {
                    List<String> row = consStream.getDataRow(0);
                    setConsStreamMsgInCount(row.get(0));
                    setConsStreamMsgRejectCount(row.get(2));
                    setConsStreamMsgDiscardCount(row.get(3));
                } else {
                    setConsStreamMsgInCount("");
                    setConsStreamMsgRejectCount("");
                    setConsStreamMsgDiscardCount("");
                }
            } else {
                setConsStreamReadError(consStream.getErrMsg());
            }
        }
        TableResult prodStream = tableResult.getTableResult(SessionStatsResultParam.ProducerStreamStats.toString());
        if (prodStream != null) {
            if (prodStream.getOutcome().booleanValue()) {
                if (prodStream.getSize() > 0) {
                    List<String> row = prodStream.getDataRow(0);
                    setProdStreamMsgOutCount(row.get(1));
                    setProdStreamMsgRejectCount(row.get(2));
                    setProdStreamMsgDiscardCount(row.get(3));
                } else {
                    setProdStreamMsgOutCount("");
                    setProdStreamMsgRejectCount("");
                    setProdStreamMsgDiscardCount("");
                }
            } else {
                setProdStreamReadError(prodStream.getErrMsg());
            }
        }
    }
    
    
}
