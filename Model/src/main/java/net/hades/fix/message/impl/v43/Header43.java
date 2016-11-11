/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Header43.java
 *
 * $Id: Header43.java,v 1.10 2011-04-14 23:44:33 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.HopsGroup;
import net.hades.fix.message.group.impl.v43.HopsGroup43;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;
import net.hades.fix.message.xml.codec.jaxb.type.v43.HopListType43;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.xml.codec.jaxb.type.DeliverToType;
import net.hades.fix.message.xml.codec.jaxb.type.OnBehalfOfType;
import net.hades.fix.message.xml.codec.jaxb.type.SenderType;
import net.hades.fix.message.xml.codec.jaxb.type.SingleBooleanAttrSimpleType;
import net.hades.fix.message.xml.codec.jaxb.type.SingleStringAttrSimpleType;
import net.hades.fix.message.xml.codec.jaxb.type.TargetType;

/**
 * Standard header for FIX version 4.3.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 12/08/2008, 20:25:51
 */
@XmlRootElement(name="Header")
@XmlType(propOrder={"sender",
    "onBehalfOf",
    "target",
    "deliverTo",
    "sendingTime",
    "possDupFlagType",
    "possResendType", 
    "messageEncodingType",
    "hopListType"})
@XmlAccessorType(XmlAccessType.NONE)
public class Header43 extends Header {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final long serialVersionUID = -8256808950790693028L;
    
    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.BeginString.getValue(),
        TagNum.BodyLength.getValue(),
        TagNum.MsgType.getValue(),
        TagNum.SenderCompID.getValue(),
        TagNum.TargetCompID.getValue(),
        TagNum.OnBehalfOfCompID.getValue(),
        TagNum.DeliverToCompID.getValue(),
        TagNum.MsgSeqNum.getValue(),
        TagNum.SenderSubID.getValue(),
        TagNum.SenderLocationID.getValue(),
        TagNum.TargetSubID.getValue(),
        TagNum.TargetLocationID.getValue(),
        TagNum.OnBehalfOfSubID.getValue(),
        TagNum.OnBehalfOfLocationID.getValue(),
        TagNum.DeliverToSubID.getValue(),
        TagNum.DeliverToLocationID.getValue(),
        TagNum.PossDupFlag.getValue(),
        TagNum.PossResend.getValue(),
        TagNum.SendingTime.getValue(),
        TagNum.OrigSendingTime.getValue(),
        TagNum.MessageEncoding.getValue(),
        TagNum.LastMsgSeqNumProcessed.getValue(),
        TagNum.OnBehalfOfSendingTime.getValue(),
        TagNum.NoHops.getValue()
    }));
    
    protected static final Set<Integer> START_COMP_TAGS;
        
    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecureDataLen.getValue(),
        TagNum.XmlDataLen.getValue()
    }));

    protected static final Set<Integer> STANDARD_SECURED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.MsgSeqNum.getValue(),
        TagNum.SenderSubID.getValue(),
        TagNum.OnBehalfOfCompID.getValue(),
        TagNum.DeliverToCompID.getValue(),
        TagNum.SenderSubID.getValue(),
        TagNum.SenderLocationID.getValue(),
        TagNum.TargetSubID.getValue(),
        TagNum.TargetLocationID.getValue(),
        TagNum.OnBehalfOfSubID.getValue(),
        TagNum.OnBehalfOfLocationID.getValue(),
        TagNum.DeliverToSubID.getValue(),
        TagNum.DeliverToLocationID.getValue(),
        TagNum.PossDupFlag.getValue(),
        TagNum.PossResend.getValue(),
        TagNum.SendingTime.getValue(),
        TagNum.OrigSendingTime.getValue(),
        TagNum.XmlDataLen.getValue(),
        TagNum.XmlData.getValue(),
        TagNum.MessageEncoding.getValue(),
        TagNum.LastMsgSeqNumProcessed.getValue(),
        TagNum.OnBehalfOfSendingTime.getValue(),
        TagNum.NoHops.getValue()
    }));

    protected static final Set<Integer> HOP_GROUP_TAGS = new HopsGroup43().getFragmentAllTags();

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(HOP_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(HOP_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    @XmlElement(name="Sender", required=true)
    private SenderType sender = new SenderType();

    @XmlElement(name="Target", required=true)
    private TargetType target = new TargetType();

    @XmlElement(name="OnBehalfOf")
    private OnBehalfOfType onBehalfOf;

    @XmlElement(name="DeliverTo")
    private DeliverToType deliverTo;

    @XmlElement(name="PossDupFlag")
    private PossDupFlagType possDupFlagType;

    @XmlElement(name="PossResend")
    private PossResendType possResendType;

    @XmlElement(name="MessageEncoding")
    private MessageEncodingType messageEncodingType;

    @XmlElement(name="HopList")
    private HopListType43 hopListType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Header43() {
        super();
    }

    public Header43(FragmentContext context) {
        super(context);
        beginString = BeginString.FIX_4_3;
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        messageEncoding = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
    }
    
    public Header43(String msgType, FragmentContext context) {
        super(msgType, context);
        beginString = BeginString.FIX_4_3;
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        messageEncoding = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        Header43 fixmlHeader = (Header43) fragment;
        if (fixmlHeader.getDeliverTo() != null && fixmlHeader.getDeliverTo().getCompID() != null) {
            this.deliverToCompID = fixmlHeader.getDeliverTo().getCompID();
        }
        if (fixmlHeader.getDeliverTo() != null && fixmlHeader.getDeliverTo().getLocationID() != null) {
            this.deliverToLocationID = fixmlHeader.getDeliverTo().getLocationID();
        }
        if (fixmlHeader.getDeliverTo() != null && fixmlHeader.getDeliverTo().getSubID() != null) {
            this.deliverToSubID = fixmlHeader.getDeliverTo().getSubID();
        }
        if (fixmlHeader.getOnBehalfOf() != null && fixmlHeader.getOnBehalfOf().getCompID() != null) {
            this.onBehalfOfCompID = fixmlHeader.getOnBehalfOf().getCompID();
        }
        if (fixmlHeader.getOnBehalfOf() != null && fixmlHeader.getOnBehalfOf().getLocationID() != null) {
            this.onBehalfOfLocationID = fixmlHeader.getOnBehalfOf().getLocationID();
        }
        if (fixmlHeader.getOnBehalfOf() != null && fixmlHeader.getOnBehalfOf().getSubID() != null) {
            this.onBehalfOfSubID = fixmlHeader.getOnBehalfOf().getSubID();
        }
        if (fixmlHeader.getSender().getCompID() != null) {
            this.senderCompID = fixmlHeader.getSender().getCompID();
        }
        if (fixmlHeader.getSender().getLocationID() != null) {
            this.senderLocationID = fixmlHeader.getSender().getLocationID();
        }
        if (fixmlHeader.getSender().getSubID() != null) {
            this.senderSubID = fixmlHeader.getSender().getSubID();
        }
        if (fixmlHeader.getTarget().getCompID() != null) {
            this.targetCompID = fixmlHeader.getTarget().getCompID();
        }
        if (fixmlHeader.getTarget().getLocationID() != null) {
            this.targetLocationID = fixmlHeader.getTarget().getLocationID();
        }
        if (fixmlHeader.getTarget().getSubID() != null) {
            this.targetSubID = fixmlHeader.getTarget().getSubID();
        }
        if (fixmlHeader.getSendingTime() != null) {
            this.sendingTime = fixmlHeader.getSendingTime();
        }
        if (fixmlHeader.getPossDupFlagType() != null && fixmlHeader.getPossDupFlagType().getValue() != null) {
            this.possDupFlag = fixmlHeader.getPossDupFlagType().getValue();
        }
        if (fixmlHeader.getPossResendType() != null && fixmlHeader.getPossResendType().getValue() != null) {
            this.possResend = fixmlHeader.getPossResendType().getValue();
        }
        if (fixmlHeader.getMessageEncodingType() != null && fixmlHeader.getMessageEncodingType().getValue() != null) {
            this.messageEncoding = Charset.forName(fixmlHeader.getMessageEncodingType().getValue());
        }
        if (fixmlHeader.getHopListType() != null && fixmlHeader.getHopListType().getNoHops() != null) {
            this.noHops = fixmlHeader.getHopListType().getNoHops();
            this.hopsGroups = fixmlHeader.getHopsGroups();
        }
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////
    
    @Override
    public BeginString getBeginString() {
        return beginString;
    }

    @Override
    public void setBeginString(BeginString beginString) {
        this.beginString = beginString;
    }

    @Override
    public int getBodyLength() {
        return bodyLength;
    }

    @Override
    public void setBodyLength(int bodyLength) {
        this.bodyLength = bodyLength;
    }

    @Override
    public Integer getMsgSeqNum() {
        return msgSeqNum;
    }

    @Override
    public void setMsgSeqNum(Integer msgSeqNum) {
        this.msgSeqNum = msgSeqNum;
    }

    @Override
    public String getMsgType() {
        return msgType;
    }

    @Override
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public Date getOrigSendingTime() {
        return origSendingTime;
    }

    @Override
    public void setOrigSendingTime(Date origSendingTime) {
        this.origSendingTime = origSendingTime;
    }

    @Override
    public String getTargetSubID() {
        return targetSubID;
    }

    @Override
    public void setTargetSubID(String targetSubID) {
        this.targetSubID = targetSubID;
        target.setSubID(targetSubID);
    }

    @Override
    public Boolean getPossDupFlag() {
        return possDupFlag;
    }

    @Override
    public void setPossDupFlag(Boolean possDupFlag) {
        this.possDupFlag = possDupFlag;
        if (possDupFlagType == null) {
            possDupFlagType = new PossDupFlagType();
        }
        possDupFlagType.setValue(possDupFlag);
    }

    @Override
    public Boolean getPossResend() {
        return possResend;
    }

    @Override
    public void setPossResend(Boolean possResend) {
        this.possResend = possResend;
        if (possResendType == null) {
            possResendType = new PossResendType();
        }
        possResendType.setValue(possResend);
    }

    @Override
    public String getSenderCompID() {
        return senderCompID;
    }

    @Override
    public void setSenderCompID(String senderCompID) {
        this.senderCompID = senderCompID;
        sender.setCompID(senderCompID);
    }

    @XmlElement(name="SendingTime")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getSendingTime() {
        return sendingTime;
    }

    @Override
    public void setSendingTime(Date sendingTime) {
        this.sendingTime = sendingTime;
    }

    @Override
    public String getTargetCompID() {
        return targetCompID;
    }

    @Override
    public void setTargetCompID(String targetCompID) {
        this.targetCompID = targetCompID;
        target.setCompID(targetCompID);
    }
    
    @Override
    public String getDeliverToCompID() {
        return deliverToCompID;
    }

    @Override
    public void setDeliverToCompID(String deliverToCompID) {
        this.deliverToCompID = deliverToCompID;
        if (deliverTo == null) {
            deliverTo = new DeliverToType();
        }
        deliverTo.setCompID(deliverToCompID);
    }

    @Override
    public String getDeliverToSubID() {
        return deliverToSubID;
    }

    @Override
    public void setDeliverToSubID(String deliverToSubID) {
        this.deliverToSubID = deliverToSubID;
        if (deliverTo == null) {
            deliverTo = new DeliverToType();
        }
        deliverTo.setSubID(deliverToSubID);
    }

    @Override
    public String getOnBehalfOfCompID() {
        return onBehalfOfCompID;
    }

    @Override
    public void setOnBehalfOfCompID(String onBehalfOfCompID) {
        this.onBehalfOfCompID = onBehalfOfCompID;
        if (onBehalfOf == null) {
            onBehalfOf = new OnBehalfOfType();
        }
        onBehalfOf.setCompID(onBehalfOfCompID);
    }

    @Override
    public String getOnBehalfOfSubID() {
        return onBehalfOfSubID;
    }

    @Override
    public void setOnBehalfOfSubID(String onBehalfOfSubID) {
        this.onBehalfOfSubID = onBehalfOfSubID;
        if (onBehalfOf == null) {
            onBehalfOf = new OnBehalfOfType();
        }
        onBehalfOf.setSubID(onBehalfOfSubID);
    }

    @Override
    public Integer getSecureDataLen() {
        return secureDataLen;
    }

    @Override
    public void setSecureDataLen(Integer secureDataLen) {
        this.secureDataLen = secureDataLen;
    }

    @Override
    public byte[] getSecureData() {
        return secureData;
    }

    @Override
    public void setSecureData(byte[] secureData) {
        this.secureData = secureData;
        if (secureDataLen == null) {
            secureDataLen = new Integer(secureData.length);
        }
    }

    @Override
    public String getSenderSubID() {
        return senderSubID;
    }

    @Override
    public void setSenderSubID(String senderSubID) {
        this.senderSubID = senderSubID;
        sender.setSubID(senderSubID);
    }
    
    @Override
    public String getSenderLocationID() {
        return senderLocationID;
    }

    @Override
    public void setSenderLocationID(String senderLocationID) {
        this.senderLocationID = senderLocationID;
        sender.setLocationID(senderLocationID);
    }

    @Override
    public String getOnBehalfOfLocationID() {
        return onBehalfOfLocationID;
    }

    @Override
    public void setOnBehalfOfLocationID(String onBehalfOfLocationID) {
        this.onBehalfOfLocationID = onBehalfOfLocationID;
        if (onBehalfOf == null) {
            onBehalfOf = new OnBehalfOfType();
        }
        onBehalfOf.setLocationID(onBehalfOfLocationID);
    }

    @Override
    public String getDeliverToLocationID() {
        return deliverToLocationID;
    }

    @Override
    public void setDeliverToLocationID(String deliverToLocationID) {
        this.deliverToLocationID = deliverToLocationID;
        if (deliverTo == null) {
            deliverTo = new DeliverToType();
        }
        deliverTo.setLocationID(deliverToLocationID);
    }

    @Override
    public Integer getLastMsgSeqNumProcessed() {
        return lastMsgSeqNumProcessed;
    }

    @Override
    public void setLastMsgSeqNumProcessed(Integer lastMsgSeqNumProcessed) {
        this.lastMsgSeqNumProcessed = lastMsgSeqNumProcessed;
    }

    @Override
    public Charset getMessageEncoding() {
        return messageEncoding;
    }

    @Override
    public void setMessageEncoding(Charset messageEncoding) {
        this.messageEncoding = messageEncoding;
    }

    @Override
    public Date getOnBehalfOfSendingTime() {
        return onBehalfOfSendingTime;
    }

    @Override
    public void setOnBehalfOfSendingTime(Date onBehalfOfSendingTime) {
        this.onBehalfOfSendingTime = onBehalfOfSendingTime;
    }

    @Override
    public String getTargetLocationID() {
        return targetLocationID;
    }

    @Override
    public void setTargetLocationID(String targetLocationID) {
        this.targetLocationID = targetLocationID;
        target.setLocationID(targetLocationID);
    }

    @Override
    public byte[] getXmlData() {
        return xmlData;
    }

    @Override
    public void setXmlData(byte[] xmlData) {
        this.xmlData = xmlData;
        if (xmlDataLen == null) {
            xmlDataLen = new Integer(xmlData.length);
        }
    }

    @Override
    public Integer getXmlDataLen() {
        return xmlDataLen;
    }

    @Override
    public void setXmlDataLen(Integer xmlDataLen) {
        this.xmlDataLen = xmlDataLen;
    }
    
    @Override
    public HopsGroup[] getHopsGroups() {
        return hopsGroups;
    }

    @Override
    public HopsGroup addHopsGroup() {
        
        HopsGroup group = new HopsGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<HopsGroup> groups = new ArrayList<HopsGroup>();
        if (hopsGroups != null && hopsGroups.length > 0) {
            groups = new ArrayList<HopsGroup>(Arrays.asList(hopsGroups));
        }
        groups.add(group);
        hopsGroups = groups.toArray(new HopsGroup[groups.size()]);
        noHops = new Integer(hopsGroups.length);
        populateHopsListType(noHops, hopsGroups);
        
        return group;
    }
    
    @Override
    public HopsGroup deleteHopsGroup(int index) {
        
        HopsGroup result = null;
        if (hopsGroups != null && hopsGroups.length > 0 && hopsGroups.length > index) {
            List<HopsGroup> groups = new ArrayList<HopsGroup>(Arrays.asList(hopsGroups));
            result = groups.remove(index);
            hopsGroups = groups.toArray(new HopsGroup[groups.size()]);
            if (hopsGroups.length > 0) {
                noHops = new Integer(hopsGroups.length);
            } else {
                hopsGroups = null;
                noHops = null;
            }
            populateHopsListType(noHops, hopsGroups);
        }
        
        return result;
    }

    @Override
    public int clearHopsGroups() {
        
        int result = 0;
        if (hopsGroups != null && hopsGroups.length > 0) {
            result = hopsGroups.length;
            hopsGroups = null;
            noHops = null;
            populateHopsListType(noHops, hopsGroups);
        }
        
        return result;
    }

    @Override
    public Integer getNoHops() {
        return noHops;
    }

    @Override
    public void setNoHops(Integer noHops) {
        this.noHops = noHops;
        hopsGroups = new HopsGroup[noHops.intValue()];
        for (int i = 0; i < hopsGroups.length; i++) {
            hopsGroups[i] = new HopsGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        }
        populateHopsListType(noHops, hopsGroups);
    }

    public SenderType getSender() {
        return sender;
    }

    public void setSender(SenderType sender) {
        this.sender = sender;
    }

    public TargetType getTarget() {
        return target;
    }

    public void setTarget(TargetType target) {
        this.target = target;
    }

    public OnBehalfOfType getOnBehalfOf() {
        return onBehalfOf;
    }

    public void setOnBehalfOf(OnBehalfOfType onBehalfOf) {
        this.onBehalfOf = onBehalfOf;
    }

    public DeliverToType getDeliverTo() {
        return deliverTo;
    }

    public void setDeliverTo(DeliverToType deliverTo) {
        this.deliverTo = deliverTo;
    }

    public PossDupFlagType getPossDupFlagType() {
        return possDupFlagType;
    }

    public void setPossDupFlagType(PossDupFlagType possDupFlagType) {
        this.possDupFlagType = possDupFlagType;
    }

    public PossResendType getPossResendType() {
        return possResendType;
    }

    public void setPossResendType(PossResendType possResendType) {
        this.possResendType = possResendType;
    }

    public MessageEncodingType getMessageEncodingType() {
        return messageEncodingType;
    }

    public void setMessageEncodingType(MessageEncodingType messageEncodingType) {
        this.messageEncodingType = messageEncodingType;
    }

    public HopListType43 getHopListType() {
        return hopListType;
    }

    public void setHopListType(HopListType43 hopListType) {
        this.hopListType = hopListType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (HOP_GROUP_TAGS.contains(tag.tagNum)) {
            if (noHops != null && noHops.intValue() > 0) {
                message.reset();
                if (hopsGroups == null) {
                    hopsGroups = new HopsGroup[noHops.intValue()];
                }
                for (int i = 0; i < hopsGroups.length; i++) {
                    HopsGroup group = new HopsGroup43(context);
                    group.decode(message);
                    hopsGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">

    private void populateHopsListType(Integer noHops, HopsGroup[] hopsGroups) {
        if (noHops != null) {
            if (hopListType == null) {
                hopListType = new HopListType43();
            }
            hopListType.setNoHops(noHops);
            hopListType.setHopGroups(hopsGroups);
        } else {
            hopListType = null;
        }
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">

    @XmlRootElement(name = "PossDupFlag")
    @XmlType(name = "PossDupFlagV43")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class PossDupFlagType extends SingleBooleanAttrSimpleType {
    }

    @XmlRootElement(name = "PossResend")
    @XmlType(name = "PossResendTypeV43")
    @XmlAccessorType(XmlAccessType.NONE)
    public static class PossResendType extends SingleBooleanAttrSimpleType {
    }

    @XmlRootElement(name = "MessageEncoding")
    @XmlType
    @XmlAccessorType(XmlAccessType.NONE)
    public static class MessageEncodingType extends SingleStringAttrSimpleType {
    }
    
    // </editor-fold>
}
