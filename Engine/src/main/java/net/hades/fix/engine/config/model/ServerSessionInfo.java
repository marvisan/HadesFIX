/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import net.hades.fix.engine.config.xml.adapter.BooleanAdapter;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeDataView;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;
import javax.management.openmbean.TabularData;
import javax.management.openmbean.TabularDataSupport;
import javax.management.openmbean.TabularType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Generic class for a session configuration between two counterparties. A session can be specialized
 * as Server and Client sessions.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name = "serverSession")
@XmlType(name = "ServerSessionInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class ServerSessionInfo extends SessionInfo implements CompositeDataView, Serializable {

    private static final long serialVersionUID = 1L;

    private static final String[] COMPOSITE_DATA_ITEMS;
    private static final String[] COMPOSITE_DATA_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] COMPOSITE_DATA_OPEN_TYPES;
    private static final TabularType TABULAR_HANDLER_DEF_TYPE;
    private static final TabularType TABULAR_HANDLER_REF_TYPE;
    private static final TabularData TABULAR_HANDLER_DEFS;
    private static final TabularData TABULAR_HANDLER_REFS;
    private static final String[] TABULAR_HANDLER_DEF_INDEX;
    private static final String[] TABULAR_HANDLER_REF_INDEX;

    public static CompositeType DataType;

    static {
        try {
            TABULAR_HANDLER_DEF_INDEX = new String[]{"name"};
	    TABULAR_HANDLER_REF_INDEX = new String[]{"id"};
            COMPOSITE_DATA_ITEMS = new String[]{"compID", "subID", "locationID", "disabled",
                    "deliverToCompID", "deliverToSubID", "deliverToLocationID", "messageEncoding", "heartBtInt", "heartBtOffset",
                    "description", "testMessageIndicator", "resendTimeout", "enableRejectResponse", "fillLastMsgSeqNum",
                    "maxMsgLen", "logoutTimeout", "latencyCheck", "maxLatencyTreshold", "enableNextExpMsgSeqNum", "resetSeqAtStartup",
                    "resetSeqAtLogon", "resetSeqAtLogout", "resetSeqAtDisconnect", "disableGapDetection", "printableFIXML", "validateIncomingFIXML",
                    "validateOutgoingFIXML", "abortFIXMLValidationOnError", "enableMsgValidation", "persistence", "fixVersion", "defaultApplVerID",
                    "defaultApplExtID", "defaultCstmApplVerID", "customApplVerID", "rxBufferSize", "txBufferSize", "resendEndSeqNum",
                    "logonTimeout", "authenticationInfo", "connection", "producerStreamInfo", "consumerStreamInfo", "handlerRefs", "handlerDefs",
                    "enableLogonPassThrough"};
            COMPOSITE_DATA_ITEMS_DESCRIPTION = new String[]{"CompID", "SubID", "LocationID", "Is Disabled?",
                    "DeliverToCompID", "DeliverToSubID", "DeliverToLocationID", "Message Encoding", "Heartbeat Interval", "Heartbeat Offset",
                    "Description", "Test Message Indicator", "Resend Timeout", "Enable Reject Response", "Fill Last Msg SeqNum",
                    "Max Msg Len", "Logout Timeout", "Latency Check", "Max Latency Treshold", "Enable Next Exp Msg SeqNum", "Reset Seq At Startup",
                    "Reset Seq At Logon", "Reset Seq At Logout", "Reset Seq At Disconnect", "Disable Gap Detection", "Printable FIXML", "Validate Incoming FIXML",
                    "Validate Outgoing FIXML", "Abort FIXML Validation On Error", "Enable Msg Validation", "Enable Persistence", "FIX Version", "Default ApplVerID",
                    "Default ApplExtID", "Default CstmApplVerID", "Custom ApplVerID", "RX Buffer Size", "TX Buffer Size", "Resend End SeqNum",
                    "Logon Timeout", "Authentication Data", "Connection Data", "Producer Stream Data", "Consumer Stream Data", "Handler Refs Data", "Handler Defs Data",
                    "Enable Logon PassThrough"};
            TABULAR_HANDLER_DEF_TYPE = new TabularType("HandlerDefInfo", "List of Handler Definitions", HandlerDefInfo.DataType, TABULAR_HANDLER_DEF_INDEX);
            TABULAR_HANDLER_DEFS = new TabularDataSupport(TABULAR_HANDLER_DEF_TYPE);
	    TABULAR_HANDLER_REF_TYPE = new TabularType("HandlerRefInfo", "List of Handler References", HandlerDefInfo.DataType, TABULAR_HANDLER_REF_INDEX);
	    TABULAR_HANDLER_REFS = new TabularDataSupport(TABULAR_HANDLER_REF_TYPE);
            COMPOSITE_DATA_OPEN_TYPES = new OpenType<?>[]{SimpleType.STRING, SimpleType.STRING, SimpleType.STRING, SimpleType.BOOLEAN,
                    SimpleType.STRING, SimpleType.STRING, SimpleType.STRING, SimpleType.STRING, SimpleType.INTEGER, SimpleType.INTEGER,
                    SimpleType.STRING, SimpleType.BOOLEAN, SimpleType.INTEGER, SimpleType.BOOLEAN, SimpleType.BOOLEAN,
                    SimpleType.INTEGER, SimpleType.INTEGER, SimpleType.BOOLEAN, SimpleType.INTEGER, SimpleType.BOOLEAN, SimpleType.BOOLEAN,
                    SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.BOOLEAN,
                    SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.BOOLEAN, SimpleType.STRING, SimpleType.STRING,
                    SimpleType.STRING, SimpleType.STRING, SimpleType.STRING, SimpleType.INTEGER, SimpleType.INTEGER, SimpleType.STRING,
                    SimpleType.INTEGER, AuthenticationInfo.DataType, ClientTcpConnectionInfo.DataType, StreamInfo.DataType, StreamInfo.DataType, 
		    TABULAR_HANDLER_REF_TYPE, TABULAR_HANDLER_DEF_TYPE, SimpleType.BOOLEAN};

            DataType = new CompositeType("ServerSessionInfo", "Server Session Data", COMPOSITE_DATA_ITEMS,
                    COMPOSITE_DATA_ITEMS_DESCRIPTION, COMPOSITE_DATA_OPEN_TYPES);
        } catch (OpenDataException e) {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            PrintWriter pout = new PrintWriter(bout);
            e.printStackTrace(pout);
            pout.flush();
            throw new RuntimeException(bout.toString());
        }
    }

    @XmlAttribute(name = "enableLogonPassThrough")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean enableLogonPassThrough;

    public ServerSessionInfo() {
    }

    public Boolean getEnableLogonPassThrough() {
        return enableLogonPassThrough;
    }

    public void setEnableLogonPassThrough(Boolean enableLogonPassThrough) {
        this.enableLogonPassThrough = enableLogonPassThrough;
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
	    for (HandlerRefInfo handlerRef : nextHandlers) {
                TABULAR_HANDLER_REFS.put(handlerRef.toCompositeData(HandlerRefInfo.DataType));
            }
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
                    logonTimeout, authenticationInfo != null ? authenticationInfo.toCompositeData(AuthenticationInfo.DataType) : null,
                    connectionInfo != null ? ((ServerTcpConnectionInfo) connectionInfo).toCompositeData(ServerTcpConnectionInfo.DataType) : null,
                    producerStreamInfo != null ? producerStreamInfo.toCompositeData(StreamInfo.DataType) : null,
                    consumerStreamInfo != null ? consumerStreamInfo.toCompositeData(StreamInfo.DataType) : null, 
		    TABULAR_HANDLER_REFS, TABULAR_HANDLER_DEFS, enableLogonPassThrough});
            assert ct.isValue(cd);
            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ServerSessionInfo[");
        sb.append(super.toString());
        if (enableLogonPassThrough != null) {
            sb.append("enableLogonPassThrough=").append(enableLogonPassThrough).append("\n");
        }
        sb.append("]}");
        return sb.toString();
    }

}
