/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.management.openmbean.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.engine.config.xml.adapter.BooleanAdapter;

/**
 * Generic class for a session configuration between two Counterparties. A session can be specialized
 * as Server and Client sessions.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name = "clientSession")
@XmlType(name = "ClientSessionInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class ClientSessionInfo extends SessionInfo implements CompositeDataView, Serializable {

    private static final long serialVersionUID = 1L;

    private static final String[] COMPOSITE_DATA_ITEMS;
    private static final String[] COMPOSITE_DATA_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] COMPOSITE_DATA_OPEN_TYPES;
    private static final TabularType TABULAR_HANDLER_DEF_TYPE;
    private static final TabularData TABULAR_HANDLER_DEFS;
    private static final String[] TABULAR_HANDLER_DEF_INDEX;

    public static CompositeType DataType;

    static {
        try {
            TABULAR_HANDLER_DEF_INDEX = new String[]{"name"};
            COMPOSITE_DATA_ITEMS = new String[]{"compID", "subID", "locationID", "disabled",
                    "deliverToCompID", "deliverToSubID", "deliverToLocationID", "messageEncoding", "heartBtInt", "heartBtOffset",
                    "description", "testMessageIndicator", "enableRejectResponse", "fillLastMsgSeqNum",
                    "maxMsgLen", "logoutTimeout", "latencyCheck", "maxLatencyTreshold", "enableNextExpMsgSeqNum", "resetSeqAtStartup",
                    "resetSeqAtLogon", "resetSeqAtLogout", "resetSeqAtDisconnect", "disableGapDetection", "printableFIXML", "validateIncomingFIXML",
                    "validateOutgoingFIXML", "abortFIXMLValidationOnError", "enableMsgValidation", "persistence", "fixVersion", "defaultApplVerID",
                    "defaultApplExtID", "defaultCstmApplVerID", "customApplVerID", "rxBufferSize", "txBufferSize", "resendEndSeqNum",
                    "logonTimeout", "authenticationInfo", "connection", "producerStreamInfo", "consumerStreamInfo", "handlerDefs",
                    "maxNumLogonRetries", "doNotReconnWhenSeqNumTooLow", "connectOnStartup", "reconnectDelay"};
            COMPOSITE_DATA_ITEMS_DESCRIPTION = new String[]{"CompID", "SubID", "LocationID", "Is Disabled?",
                    "DeliverToCompID", "DeliverToSubID", "DeliverToLocationID", "Message Encoding", "Heartbeat Interval", "Heartbeat Offset",
                    "Description", "Test Message Indicator", "Enable Reject Response", "Fill Last Msg SeqNum",
                    "Max Msg Len", "Logout Timeout", "Latency Check", "Max Latency Treshold", "Enable Next Exp Msg SeqNum", "Reset Seq At Startup",
                    "Reset Seq At Logon", "Reset Seq At Logout", "Reset Seq At Disconnect", "Disable Gap Detection", "Printable FIXML", "Validate Incoming FIXML",
                    "Validate Outgoing FIXML", "Abort FIXML Validation On Error", "Enable Msg Validation", "Enable Persistence", "FIX Version", "Default ApplVerID",
                    "Default ApplExtID", "Default CstmApplVerID", "Custom ApplVerID", "RX Buffer Size", "TX Buffer Size", "Resend End SeqNum",
                    "Logon Timeout", "Authentication Data", "Connection Data", "Producer Stream Data", "Consumer Stream Data", "Handler Defs Data",
                    "Max Num Logon Retries", "Do Not Reconn When SeqNum Too Low", "Connect On Startup", "Reconnect Delay"};
            TABULAR_HANDLER_DEF_TYPE = new TabularType("HandlerDefInfo", "List of Handler Definitions", HandlerDefInfo.DataType, TABULAR_HANDLER_DEF_INDEX);
            TABULAR_HANDLER_DEFS = new TabularDataSupport(TABULAR_HANDLER_DEF_TYPE);
            COMPOSITE_DATA_OPEN_TYPES = new OpenType<?>[]{SimpleType.STRING, SimpleType.STRING, SimpleType.STRING, SimpleType.BOOLEAN,
                    SimpleType.STRING, SimpleType.STRING, SimpleType.STRING, SimpleType.STRING, SimpleType.INTEGER, SimpleType.INTEGER,
                    SimpleType.STRING, SimpleType.BOOLEAN, SimpleType.INTEGER, SimpleType.BOOLEAN, SimpleType.BOOLEAN,
                    SimpleType.INTEGER, SimpleType.INTEGER, SimpleType.BOOLEAN, SimpleType.INTEGER, SimpleType.BOOLEAN, SimpleType.BOOLEAN,
                    SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.BOOLEAN,
                    SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.STRING, SimpleType.STRING,
                    SimpleType.STRING, SimpleType.STRING, SimpleType.STRING, SimpleType.INTEGER, SimpleType.INTEGER, SimpleType.STRING,
                    SimpleType.INTEGER, AuthenticationInfo.DataType, ClientTcpConnectionInfo.DataType, StreamInfo.DataType, StreamInfo.DataType, TABULAR_HANDLER_DEF_TYPE,
                    SimpleType.INTEGER, SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.INTEGER};

            DataType = new CompositeType("ClientSessionInfo", "Client Session Data", COMPOSITE_DATA_ITEMS,
                    COMPOSITE_DATA_ITEMS_DESCRIPTION, COMPOSITE_DATA_OPEN_TYPES);
        } catch (OpenDataException e) {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            PrintWriter pout = new PrintWriter(bout);
            e.printStackTrace(pout);
            pout.flush();
            throw new RuntimeException(bout.toString());
        }
    }

    @XmlAttribute(name = "maxNumLogonRetries")
    protected Integer maxNumLogonRetries;

    @XmlAttribute(name = "doNotReconnWhenSeqNumTooLow")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean doNotReconnWhenSeqNumTooLow;

    @XmlAttribute(name = "connectOnStartup")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean connectOnStartup;

    @XmlAttribute(name = "reconnectDelay")
    protected Integer reconnectDelay;

    public ClientSessionInfo() {
        maxNumLogonRetries = 0;
        doNotReconnWhenSeqNumTooLow = Boolean.FALSE;
    }

    public Integer getMaxNumLogonRetries() {
        return maxNumLogonRetries;
    }

    public void setMaxNumLogonRetries(Integer maxNumLogonRetries) {
        this.maxNumLogonRetries = maxNumLogonRetries;
    }

    public Boolean getDoNotReconnWhenSeqNumTooLow() {
        return doNotReconnWhenSeqNumTooLow;
    }

    public void setDoNotReconnWhenSeqNumTooLow(Boolean doNotReconnWhenSeqNumTooLow) {
        this.doNotReconnWhenSeqNumTooLow = doNotReconnWhenSeqNumTooLow;
    }

    public Boolean getConnectOnStartup() {
        return connectOnStartup;
    }

    public void setConnectOnStartup(Boolean connectOnStartup) {
        this.connectOnStartup = connectOnStartup;
    }

    public Integer getReconnectDelay() {
        return reconnectDelay;
    }

    public void setReconnectDelay(Integer reconnectDelay) {
        this.reconnectDelay = reconnectDelay;
    }

    @Override
    public CompositeData toCompositeData(CompositeType ct) {
        try {
            List<String> itemNames = new ArrayList<>(ct.keySet());
            List<String> itemDescriptions = new ArrayList<>(itemNames.size());
            List<OpenType<?>> itemTypes = new ArrayList<>();
	    itemNames.stream().map((item) -> {
		itemDescriptions.add(ct.getDescription(item));
		return item;
	    }).forEach((item) -> {
		itemTypes.add(ct.getType(item));
	    });

            CompositeType xct = new CompositeType(ct.getTypeName(),
                    ct.getDescription(),
                    itemNames.toArray(new String[itemNames.size()]),
                    itemDescriptions.toArray(new String[itemDescriptions.size()]),
                    itemTypes.toArray(new OpenType<?>[itemTypes.size()]));
            for (HandlerDefInfo handlerDef : handlerDefs) {
                TABULAR_HANDLER_DEFS.put(handlerDef.toCompositeData(HandlerDefInfo.DataType));
            }
            CompositeData cd = new CompositeDataSupport(xct, COMPOSITE_DATA_ITEMS, new Object[]{compID, subID, locationID, disabled,
                    deliverToCompID, deliverToSubID, deliverToLocationID, messageEncoding, heartBtInt, heartBtOffset,
                    description, testMessageIndicator, resendTimeout, enableRejectResponse, fillLastMsgSeqNum,
                    maxMsgLen, logoutTimeout, latencyCheck, maxLatencyTreshold, enableNextExpMsgSeqNum, resetSeqAtStartup,
                    resetSeqAtLogon, resetSeqAtLogout, resetSeqAtDisconnect, disableGapDetection, printableFIXML, validateIncomingFIXML,
                    validateOutgoingFIXML, abortFIXMLValidationOnError, enableMsgValidation, persistence, fixVersion, defaultApplVerID,
                    defaultApplExtID, defaultCstmApplVerID, customApplVerID, rxBufferSize, txBufferSize, resendEndSeqNum,
                    connection != null ? ((ClientTcpConnectionInfo) connection).toCompositeData(ClientTcpConnectionInfo.DataType) : null,
                    producerStreamInfo != null ? producerStreamInfo.toCompositeData(StreamInfo.DataType) : null,
                    consumerStreamInfo != null ? consumerStreamInfo.toCompositeData(StreamInfo.DataType) : null,
                    TABULAR_HANDLER_DEFS,
                    maxNumLogonRetries, doNotReconnWhenSeqNumTooLow, connectOnStartup, reconnectDelay});
            assert ct.isValue(cd);

            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ClientSessionInfo[");
        sb.append(super.toString());
        if (maxNumLogonRetries != null) {
            sb.append("maxNumLogonRetries=").append(maxNumLogonRetries).append("\n");
        }
        if (doNotReconnWhenSeqNumTooLow != null) {
            sb.append("doNotReconnWhenSeqNumTooLow=").append(doNotReconnWhenSeqNumTooLow).append("\n");
        }
        if (connectOnStartup != null) {
            sb.append("connectOnStartup=").append(connectOnStartup).append("\n");
        }
        if (reconnectDelay != null) {
            sb.append("reconnectDelay=").append(reconnectDelay).append("\n");
        }
        sb.append("]}");

        return sb.toString();
    }
}
