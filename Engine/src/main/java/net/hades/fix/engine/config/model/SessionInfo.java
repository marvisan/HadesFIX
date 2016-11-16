/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.engine.config.xml.adapter.BooleanAdapter;
import net.hades.fix.engine.util.PartyUtil;

/**
 * Generic class for a session configuration between two counter-parties. A session can be specialized
 * as Server and Client sessions.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlType(name = "SessionInfo", propOrder = {"connection", "authenticationInfo", "encryptedAuthenticationInfo", "encryption",
    "producerStreamInfo", "consumerStreamInfo", "msgTypes", "nextHandlers", "handlerDefs", "securedMessages", "schedules"})
@XmlAccessorType(XmlAccessType.NONE)
public class SessionInfo implements Serializable {

     private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "id", required = true)
    protected String id;
     
    @XmlAttribute(name = "compID", required = true)
    protected String compID;

    @XmlAttribute(name = "subID")
    protected String subID;

    @XmlAttribute(name = "locationID")
    protected String locationID;

    @XmlAttribute(name = "disabled")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean disabled;

    @XmlAttribute(name = "deliverToCompID")
    protected String deliverToCompID;

    @XmlAttribute(name = "deliverToSubID")
    protected String deliverToSubID;

    @XmlAttribute(name = "deliverToLocationID")
    protected String deliverToLocationID;

    @XmlAttribute(name = "messageEncoding")
    protected String messageEncoding;

    @XmlAttribute(name = "heartBtInt")
    protected Integer heartBtInt;

    @XmlAttribute(name = "heartBtOffset")
    protected Integer heartBtOffset;

    @XmlAttribute(name = "description")
    protected String description;

    @XmlAttribute(name = "testMessageIndicator")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean testMessageIndicator;

    @XmlAttribute(name = "resendTimeout")
    protected Integer resendTimeout;

    @XmlAttribute(name = "enableRejectResponse")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean enableRejectResponse;

    @XmlAttribute(name = "fillLastMsgSeqNum")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean fillLastMsgSeqNum;

    @XmlAttribute(name = "maxMsgLen")
    protected Integer maxMsgLen;

    @XmlAttribute(name = "logoutTimeout")
    protected Integer logoutTimeout;

    @XmlAttribute(name = "latencyCheck")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean latencyCheck;

    @XmlAttribute(name = "maxLatencyTreshold")
    protected Integer maxLatencyTreshold;

    @XmlAttribute(name = "enableNextExpMsgSeqNum")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean enableNextExpMsgSeqNum;

    @XmlAttribute(name = "resetSeqAtStartup")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean resetSeqAtStartup;

    @XmlAttribute(name = "resetSeqAtLogon")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean resetSeqAtLogon;

    @XmlAttribute(name = "resetSeqAtLogout")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean resetSeqAtLogout;

    @XmlAttribute(name = "resetSeqAtDisconnect")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean resetSeqAtDisconnect;

    @XmlAttribute(name = "disableGapDetection")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean disableGapDetection;

    @XmlAttribute(name = "printableFIXML")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean printableFIXML;

    @XmlAttribute(name = "validateIncomingFIXML")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean validateIncomingFIXML;

    @XmlAttribute(name = "validateOutgoingFIXML")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean validateOutgoingFIXML;

    @XmlAttribute(name = "abortFIXMLValidationOnError")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean abortFIXMLValidationOnError;

    @XmlAttribute(name = "enableMsgValidation")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean enableMsgValidation;

    @XmlAttribute(name = "persistence")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean persistence;

    @XmlAttribute(name = "fixVersion", required = true)
    protected String fixVersion;

    @XmlAttribute(name = "defaultApplVerID")
    protected String defaultApplVerID;

    @XmlAttribute(name = "defaultApplExtID")
    protected Integer defaultApplExtID;

    @XmlAttribute(name = "defaultCstmApplVerID")
    protected String defaultCstmApplVerID;

    @XmlAttribute(name = "customApplVerID")
    protected String customApplVerID;

    @XmlAttribute(name = "rxBufferSize")
    protected Integer rxBufferSize;

    @XmlAttribute(name = "txBufferSize")
    protected Integer txBufferSize;

    @XmlAttribute(name = "resendEndSeqNum")
    protected Integer resendEndSeqNum;

    @XmlAttribute(name = "logonTimeout")
    protected Integer logonTimeout;

    @XmlElement(name = "authInfo")
    protected AuthenticationInfo authenticationInfo;

    @XmlElement(name = "encryptAuthInfo")
    protected EncryptedAuthenticationInfo encryptedAuthenticationInfo;
    
    @XmlElementRef()
    protected ConnectionInfo connection;

    @XmlElement(name = "encryption")
    protected EncryptionInfo encryption;

    @XmlElementRef()
    protected StreamInfo producerStreamInfo;

    @XmlElementRef()
    protected StreamInfo consumerStreamInfo;

    @XmlElementWrapper(name = "msgTypes")
    @XmlElementRef()
    protected MsgTypeInfo[] msgTypes;

    @XmlElementWrapper(name = "handlerDefs")
    @XmlElementRef()
    protected HandlerDefInfo[] handlerDefs;

    @XmlElementWrapper(name = "securedMessages")
    @XmlElementRef()
    protected SecuredMessageInfo[] securedMessages;

    @XmlElementWrapper(name = "schedules")
    @XmlElementRef()
    protected ScheduleTaskInfo[] schedules;
    
    @XmlElement(name = "nextHandlers")
    @XmlElementWrapper(name = "next")
    protected HandlerRefInfo[] nextHandlers;

    @XmlTransient
    protected String remoteID;

    public SessionInfo() {
        resetSeqAtDisconnect = Boolean.FALSE;
        resetSeqAtLogon = Boolean.FALSE;
        resetSeqAtLogout = Boolean.FALSE;
        resetSeqAtStartup = Boolean.FALSE;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getCompID() {
        return compID;
    }

    public void setCompID(String compID) {
        this.compID = compID;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getSubID() {
        return subID;
    }

    public void setSubID(String subID) {
        this.subID = subID;
    }

    public String getDeliverToCompID() {
        return deliverToCompID;
    }

    public void setDeliverToCompID(String deliverToCompID) {
        this.deliverToCompID = deliverToCompID;
    }

    public String getDeliverToSubID() {
        return deliverToSubID;
    }

    public void setDeliverToSubID(String deliverToSubID) {
        this.deliverToSubID = deliverToSubID;
    }

    public String getDeliverToLocationID() {
        return deliverToLocationID;
    }

    public void setDeliverToLocationID(String deliverToLocationID) {
        this.deliverToLocationID = deliverToLocationID;
    }

    public String getMessageEncoding() {
        return messageEncoding;
    }

    public void setMessageEncoding(String messageEncoding) {
        this.messageEncoding = messageEncoding;
    }

    public Integer getHeartBtInt() {
        return heartBtInt;
    }

    public void setHeartBtInt(Integer heartBtInt) {
        this.heartBtInt = heartBtInt;
    }

    public Integer getHeartBtOffset() {
        return heartBtOffset;
    }

    public void setHeartBtOffset(Integer heartBtOffset) {
        this.heartBtOffset = heartBtOffset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getTestMessageIndicator() {
        return testMessageIndicator;
    }

    public void setTestMessageIndicator(Boolean testMessageIndicator) {
        this.testMessageIndicator = testMessageIndicator;
    }

    public Integer getResendTimeout() {
        return resendTimeout;
    }

    public void setResendTimeout(Integer resendTimeout) {
        this.resendTimeout = resendTimeout;
    }

    public Boolean getEnableRejectResponse() {
        return enableRejectResponse;
    }

    public void setEnableRejectResponse(Boolean enableRejectResponse) {
        this.enableRejectResponse = enableRejectResponse;
    }

    public Boolean getFillLastMsgSeqNum() {
        return fillLastMsgSeqNum;
    }

    public void setFillLastMsgSeqNum(Boolean fillLastMsgSeqNum) {
        this.fillLastMsgSeqNum = fillLastMsgSeqNum;
    }

    public Integer getMaxMsgLen() {
        return maxMsgLen;
    }

    public void setMaxMsgLen(Integer maxMsgLen) {
        this.maxMsgLen = maxMsgLen;
    }

    public Integer getLogoutTimeout() {
        return logoutTimeout;
    }

    public void setLogoutTimeout(Integer logoutTimeout) {
        this.logoutTimeout = logoutTimeout;
    }

    public Boolean getLatencyCheck() {
        return latencyCheck;
    }

    public void setLatencyCheck(Boolean latencyCheck) {
        this.latencyCheck = latencyCheck;
    }

    public Integer getMaxLatencyTreshold() {
        return maxLatencyTreshold;
    }

    public void setMaxLatencyTreshold(Integer maxLatencyTreshold) {
        this.maxLatencyTreshold = maxLatencyTreshold;
    }

    public Boolean getEnableNextExpMsgSeqNum() {
        return enableNextExpMsgSeqNum;
    }

    public void setEnableNextExpMsgSeqNum(Boolean enableNextExpMsgSeqNum) {
        this.enableNextExpMsgSeqNum = enableNextExpMsgSeqNum;
    }

    public Boolean getResetSeqAtLogon() {
        return resetSeqAtLogon;
    }

    public void setResetSeqAtLogon(Boolean resetSeqAtLogon) {
        this.resetSeqAtLogon = resetSeqAtLogon;
    }

    public Boolean getResetSeqAtLogout() {
        return resetSeqAtLogout;
    }

    public void setResetSeqAtLogout(Boolean resetSeqAtLogout) {
        this.resetSeqAtLogout = resetSeqAtLogout;
    }

    public Boolean getResetSeqAtDisconnect() {
        return resetSeqAtDisconnect;
    }

    public void setResetSeqAtDisconnect(Boolean resetSeqAtDisconnect) {
        this.resetSeqAtDisconnect = resetSeqAtDisconnect;
    }

    public Boolean getPrintableFIXML() {
        return printableFIXML;
    }

    public void setPrintableFIXML(Boolean printableFIXML) {
        this.printableFIXML = printableFIXML;
    }

    public Boolean getValidateIncomingFIXML() {
        return validateIncomingFIXML;
    }

    public void setValidateIncomingFIXML(Boolean validateIncomingFIXML) {
        this.validateIncomingFIXML = validateIncomingFIXML;
    }

    public Boolean getValidateOutgoingFIXML() {
        return validateOutgoingFIXML;
    }

    public void setValidateOutgoingFIXML(Boolean validateOutgoingFIXML) {
        this.validateOutgoingFIXML = validateOutgoingFIXML;
    }

    public Boolean getAbortFIXMLValidationOnError() {
        return abortFIXMLValidationOnError;
    }

    public void setAbortFIXMLValidationOnError(Boolean abortFIXMLValidationOnError) {
        this.abortFIXMLValidationOnError = abortFIXMLValidationOnError;
    }

    public Boolean getEnableMsgValidation() {
        return enableMsgValidation;
    }

    public void setEnableMsgValidation(Boolean enableMsgValidation) {
        this.enableMsgValidation = enableMsgValidation;
    }

    public String getFixVersion() {
        return fixVersion;
    }

    public void setFixVersion(String fixVersion) {
        this.fixVersion = fixVersion;
    }

    public String getDefaultApplVerID() {
        return defaultApplVerID;
    }

    public void setDefaultApplVerID(String defaultApplVerID) {
        this.defaultApplVerID = defaultApplVerID;
    }

    public Integer getDefaultApplExtID() {
        return defaultApplExtID;
    }

    public void setDefaultApplExtID(Integer defaultApplExtID) {
        this.defaultApplExtID = defaultApplExtID;
    }

    public String getDefaultCstmApplVerID() {
        return defaultCstmApplVerID;
    }

    public void setDefaultCstmApplVerID(String defaultCstmApplVerID) {
        this.defaultCstmApplVerID = defaultCstmApplVerID;
    }

    public String getCustomApplVerID() {
        return customApplVerID;
    }

    public void setCustomApplVerID(String customApplVerID) {
        this.customApplVerID = customApplVerID;
    }

    public Integer getRxBufferSize() {
        return rxBufferSize;
    }

    public void setRxBufferSize(Integer rxBufferSize) {
        this.rxBufferSize = rxBufferSize;
    }

    public Integer getTxBufferSize() {
        return txBufferSize;
    }

    public void setTxBufferSize(Integer txBufferSize) {
        this.txBufferSize = txBufferSize;
    }

    public MsgTypeInfo[] getMsgTypes() {
        return msgTypes;
    }

    public void setMsgTypes(MsgTypeInfo[] msgTypes) {
        this.msgTypes = msgTypes;
    }

    public ConnectionInfo getConnection() {
        return connection;
    }

    public void setConnection(ConnectionInfo connection) {
        this.connection = connection;
    }

    public AuthenticationInfo getAuthenticationInfo() {
        return authenticationInfo;
    }

    public void setAuthenticationInfo(AuthenticationInfo authenticationInfo) {
        this.authenticationInfo = authenticationInfo;
    }

    public EncryptedAuthenticationInfo getEncryptedAuthenticationInfo() {
        return encryptedAuthenticationInfo;
    }

    public void setEncryptedAuthenticationInfo(EncryptedAuthenticationInfo encryptedAuthenticationInfo) {
        this.encryptedAuthenticationInfo = encryptedAuthenticationInfo;
    }

    public EncryptionInfo getEncryption() {
        return encryption;
    }

    public void setEncryption(EncryptionInfo encryption) {
        this.encryption = encryption;
    }

    public StreamInfo getProducerStreamInfo() {
        return producerStreamInfo;
    }

    public void setProducerStreamInfo(StreamInfo producerStreamInfo) {
        this.producerStreamInfo = producerStreamInfo;
    }

    public StreamInfo getConsumerStreamInfo() {
        return consumerStreamInfo;
    }

    public void setConsumerStreamInfo(StreamInfo consumerStreamInfo) {
        this.consumerStreamInfo = consumerStreamInfo;
    }

    public ScheduleTaskInfo[] getSchedules() {
        return schedules;
    }

    public void setSchedules(ScheduleTaskInfo[] schedules) {
        this.schedules = schedules;
    }

    public SecuredMessageInfo[] getSecuredMessages() {
        return securedMessages;
    }

    public void setSecuredMessages(SecuredMessageInfo[] securedMessages) {
        this.securedMessages = securedMessages;
    }

    public HandlerDefInfo[] getHandlerDefs() {
        return handlerDefs;
    }

    public void setHandlerDefs(HandlerDefInfo[] handlerDefs) {
        this.handlerDefs = handlerDefs;
    }

    public String getID() {
        return PartyUtil.getID(compID, subID, locationID);
    }

    public Boolean getDisableGapDetection() {
        return disableGapDetection;
    }

    public void setDisableGapDetection(Boolean disableGapDetection) {
        this.disableGapDetection = disableGapDetection;
    }

    public Integer getResendEndSeqNum() {
        return resendEndSeqNum;
    }

    public void setResendEndSeqNum(Integer resendEndSeqNum) {
        this.resendEndSeqNum = resendEndSeqNum;
    }

    public Boolean getResetSeqAtStartup() {
        return resetSeqAtStartup;
    }

    public void setResetSeqAtStartup(Boolean resetSeqAtStartup) {
        this.resetSeqAtStartup = resetSeqAtStartup;
    }

    public Boolean getPersistence() {
        return persistence;
    }

    public void setPersistence(Boolean persistence) {
        this.persistence = persistence;
    }

    public Integer getLogonTimeout() {
        return logonTimeout;
    }

    public void setLogonTimeout(Integer logonTimeout) {
        this.logonTimeout = logonTimeout;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public HandlerRefInfo[] getNextHandlers() {
	return nextHandlers;
    }

    public void setNextHandlers(HandlerRefInfo[] nextHandlers) {
	this.nextHandlers = nextHandlers;
    }

    public String getRemoteID() {
        return remoteID;
    }

    public void setRemoteID(String remoteID) {
        this.remoteID = remoteID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{SessionInfo[");
	sb.append("id=").append(id).append(",");
        sb.append("compID=").append(compID).append(",");
        if (subID != null) {
            sb.append("subID=").append(subID).append(",");
        }
        if (locationID != null) {
            sb.append("locationID=").append(locationID).append(",");
        }
        if (disabled != null) {
            sb.append("disabled=").append(disabled).append(",");
        }
        if (deliverToCompID != null) {
            sb.append("deliverToCompID=").append(deliverToCompID).append(",");
        }
        if (deliverToSubID != null) {
            sb.append("deliverToSubID=").append(deliverToSubID).append(",");
        }
        if (deliverToLocationID != null) {
            sb.append("deliverToLocationID=").append(deliverToLocationID).append(",");
        }
        if (messageEncoding != null) {
            sb.append("messageEncoding=").append(messageEncoding).append(",");
        }
        if (heartBtInt != null) {
            sb.append("heartBtInt=").append(heartBtInt).append(",");
        }
        if (heartBtOffset != null) {
            sb.append("heartBtOffset=").append(heartBtOffset).append(",");
        }
        if (description != null) {
            sb.append("description=").append(description).append(",");
        }
        if (testMessageIndicator != null) {
            sb.append("testMessageIndicator=").append(testMessageIndicator).append(",");
        }
        if (resendTimeout != null) {
            sb.append("resendTimeout=").append(resendTimeout).append(",");
        }
        if (enableRejectResponse != null) {
            sb.append("enableRejectResponse=").append(enableRejectResponse).append(",");
        }
        if (fillLastMsgSeqNum != null) {
            sb.append("fillLastMsgSeqNum=").append(fillLastMsgSeqNum).append(",");
        }
        if (maxMsgLen != null) {
            sb.append("maxMsgLen=").append(maxMsgLen).append(",");
        }
        if (logoutTimeout != null) {
            sb.append("logoutTimeout=").append(logoutTimeout).append(",");
        }
        if (latencyCheck != null) {
            sb.append("latencyCheck=").append(latencyCheck).append(",");
        }
        if (maxLatencyTreshold != null) {
            sb.append("maxLatencyTreshold=").append(maxLatencyTreshold).append(",");
        }
        if (description != null) {
            sb.append("description=").append(description).append(",");
        }
        if (enableNextExpMsgSeqNum != null) {
            sb.append("enableNextExpMsgSeqNum=").append(enableNextExpMsgSeqNum).append(",");
        }
        if (resetSeqAtStartup != null) {
            sb.append("resetSeqAtStartup=").append(resetSeqAtStartup).append(",");
        }
        if (resetSeqAtLogon != null) {
            sb.append("resetSeqAtLogon=").append(resetSeqAtLogon).append(",");
        }
        if (resetSeqAtLogout != null) {
            sb.append("resetSeqAtLogout=").append(resetSeqAtLogout).append(",");
        }
        if (resetSeqAtDisconnect != null) {
            sb.append("resetSeqAtDisconnect=").append(resetSeqAtDisconnect).append(",");
        }
        if (disableGapDetection != null) {
            sb.append("disableGapDetection=").append(disableGapDetection).append(",");
        }
        if (printableFIXML != null) {
            sb.append("printableFIXML=").append(printableFIXML).append(",");
        }
        if (validateIncomingFIXML != null) {
            sb.append("validateIncomingFIXML=").append(validateIncomingFIXML).append(",");
        }
        if (validateOutgoingFIXML != null) {
            sb.append("validateOutgoingFIXML=").append(validateOutgoingFIXML).append(",");
        }
        if (abortFIXMLValidationOnError != null) {
            sb.append("abortFIXMLValidationOnError=").append(abortFIXMLValidationOnError).append(",");
        }
        if (enableMsgValidation != null) {
            sb.append("enableMsgValidation=").append(enableMsgValidation).append(",");
        }
        if (persistence != null) {
            sb.append("persistence=").append(persistence).append(",");
        }
        if (fixVersion != null) {
            sb.append("fixVersion=").append(fixVersion).append(",");
        }
        if (defaultApplVerID != null) {
            sb.append("defaultApplVerID=").append(defaultApplVerID).append(",");
        }
        if (defaultApplExtID != null) {
            sb.append("defaultApplExtID=").append(defaultApplExtID).append(",");
        }
        if (defaultCstmApplVerID != null) {
            sb.append("defaultCstmApplVerID=").append(defaultCstmApplVerID).append(",");
        }
        if (customApplVerID != null) {
            sb.append("customApplVerID=").append(customApplVerID).append(",");
        }
        if (rxBufferSize != null) {
            sb.append("rxBufferSize=").append(rxBufferSize).append(",");
        }
        if (txBufferSize != null) {
            sb.append("txBufferSize=").append(txBufferSize).append(",");
        }
        if (resendEndSeqNum != null) {
            sb.append("resendEndSeqNum=").append(resendEndSeqNum).append(",");
        }
        if (logonTimeout != null) {
            sb.append("logonTimeout=").append(logonTimeout).append(",");
        }
        if (authenticationInfo != null) {
            sb.append("authenticationInfo=").append(authenticationInfo.toString()).append(",");
        }
        if (encryptedAuthenticationInfo != null) {
            sb.append("encryptedAuthenticationInfo=").append(encryptedAuthenticationInfo.toString()).append(",");
        }
        if (connection != null) {
            sb.append("connection=").append(connection.toString()).append(",");
        }
        if (encryption != null) {
            sb.append("encryption=").append(encryption.toString()).append(",");
        }
        if (producerStreamInfo != null) {
            sb.append("producerStreamInfo=").append(producerStreamInfo.toString()).append(",");
        }
        if (consumerStreamInfo != null) {
            sb.append("consumerStreamInfo=").append(consumerStreamInfo.toString()).append(",");
        }
        if (msgTypes != null && msgTypes.length > 0) {
            sb.append("msgTypes=");
            for (MsgTypeInfo msgTypeInfo : msgTypes) {
                sb.append(msgTypeInfo.toString()).append("|");
            }
	    sb.append(",");
        }
        if (schedules != null && schedules.length > 0) {
            sb.append("scheduleTasks=");
            for (ScheduleTaskInfo scheduleTask : schedules) {
                sb.append(scheduleTask.toString()).append("|");
            }
	    sb.append(",");
        }
        if (handlerDefs != null && handlerDefs.length > 0) {
            sb.append("handlerDefs=");
            for (HandlerDefInfo handlerDefInfo : handlerDefs) {
                sb.append(handlerDefInfo.toString()).append("|");
            }
	    sb.append(",");
        }
        if (securedMessages != null && securedMessages.length > 0) {
            sb.append("securedMessages=");
            for (SecuredMessageInfo securedMessageInfo : securedMessages) {
                sb.append(securedMessageInfo.toString()).append("|");
            }
	    sb.append(",");
        }
	if (nextHandlers != null && nextHandlers.length > 0) {
            sb.append("nextHandlers=");
            for (HandlerRefInfo nextHandler : nextHandlers) {
                sb.append(nextHandler.toString()).append("|");
            }
        }
        return sb.toString();
    }
}
