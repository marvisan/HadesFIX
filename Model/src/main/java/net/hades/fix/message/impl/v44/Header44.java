/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Header44.java
 *
 * $Id: Header44.java,v 1.10 2011-04-14 23:44:39 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixCharsetAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.HopsGroup;
import net.hades.fix.message.group.impl.v44.HopsGroup44;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

/**
 * Standard header for FIX version 4.4.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 12/08/2008, 20:25:51
 */
@XmlRootElement(name="Hdr")
@XmlType(propOrder={"hopsGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class Header44 extends Header {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final long serialVersionUID = -2211941741014246005L;
    
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
        TagNum.NoHops.getValue()
    }));

    protected static final Set<Integer> HOP_GROUP_TAGS = new HopsGroup44().getFragmentAllTags();

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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Header44() {
        super();
    }

    public Header44(FragmentContext context) {
        super(context);
        beginString = BeginString.FIX_4_4;
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        messageEncoding = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
    }
    
    public Header44(String msgType, FragmentContext context) {
        super(msgType, context);
        beginString = BeginString.FIX_4_4;
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

    @XmlAttribute(name = "SeqNum")
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

    @XmlAttribute(name = "OrigSnt")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getOrigSendingTime() {
        return origSendingTime;
    }

    @Override
    public void setOrigSendingTime(Date origSendingTime) {
        this.origSendingTime = origSendingTime;
    }

    @XmlAttribute(name = "TSub")
    @Override
    public String getTargetSubID() {
        return targetSubID;
    }

    @Override
    public void setTargetSubID(String targetSubID) {
        this.targetSubID = targetSubID;
    }

    @XmlAttribute(name = "PosDup")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getPossDupFlag() {
        return possDupFlag;
    }

    @Override
    public void setPossDupFlag(Boolean possDupFlag) {
        this.possDupFlag = possDupFlag;
    }

    @XmlAttribute(name = "PosRsnd")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getPossResend() {
        return possResend;
    }

    @Override
    public void setPossResend(Boolean possResend) {
        this.possResend = possResend;
    }

    @XmlAttribute(name = "SID")
    @Override
    public String getSenderCompID() {
        return senderCompID;
    }

    @Override
    public void setSenderCompID(String senderCompID) {
        this.senderCompID = senderCompID;
    }

    @XmlAttribute(name = "Snt")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getSendingTime() {
        return sendingTime;
    }

    @Override
    public void setSendingTime(Date sendingTime) {
        this.sendingTime = sendingTime;
    }

    @XmlAttribute(name = "TID")
    @Override
    public String getTargetCompID() {
        return targetCompID;
    }

    @Override
    public void setTargetCompID(String targetCompID) {
        this.targetCompID = targetCompID;
    }

    @XmlAttribute(name = "D2ID")
    @Override
    public String getDeliverToCompID() {
        return deliverToCompID;
    }

    @Override
    public void setDeliverToCompID(String deliverToCompID) {
        this.deliverToCompID = deliverToCompID;
    }

    @XmlAttribute(name = "D2Sub")
    @Override
    public String getDeliverToSubID() {
        return deliverToSubID;
    }

    @Override
    public void setDeliverToSubID(String deliverToSubID) {
        this.deliverToSubID = deliverToSubID;
    }

    @XmlAttribute(name = "OBID")
    @Override
    public String getOnBehalfOfCompID() {
        return onBehalfOfCompID;
    }

    @Override
    public void setOnBehalfOfCompID(String onBehalfOfCompID) {
        this.onBehalfOfCompID = onBehalfOfCompID;
    }

    @XmlAttribute(name = "OBSub")
    @Override
    public String getOnBehalfOfSubID() {
        return onBehalfOfSubID;
    }

    @Override
    public void setOnBehalfOfSubID(String onBehalfOfSubID) {
        this.onBehalfOfSubID = onBehalfOfSubID;
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

    @XmlAttribute(name = "SSub")
    @Override
    public String getSenderSubID() {
        return senderSubID;
    }

    @Override
    public void setSenderSubID(String senderSubID) {
        this.senderSubID = senderSubID;
    }

    @XmlAttribute(name = "SLoc")
    @Override
    public String getSenderLocationID() {
        return senderLocationID;
    }

    @Override
    public void setSenderLocationID(String senderLocationID) {
        this.senderLocationID = senderLocationID;
    }

    @XmlAttribute(name = "OBLoc")
    @Override
    public String getOnBehalfOfLocationID() {
        return onBehalfOfLocationID;
    }

    @Override
    public void setOnBehalfOfLocationID(String onBehalfOfLocationID) {
        this.onBehalfOfLocationID = onBehalfOfLocationID;
    }

    @XmlAttribute(name = "D2Loc")
    @Override
    public String getDeliverToLocationID() {
        return deliverToLocationID;
    }

    @Override
    public void setDeliverToLocationID(String deliverToLocationID) {
        this.deliverToLocationID = deliverToLocationID;
    }

    @Override
    public Integer getLastMsgSeqNumProcessed() {
        return lastMsgSeqNumProcessed;
    }

    @Override
    public void setLastMsgSeqNumProcessed(Integer lastMsgSeqNumProcessed) {
        this.lastMsgSeqNumProcessed = lastMsgSeqNumProcessed;
    }

    @XmlAttribute(name = "MsgEncd")
    @XmlJavaTypeAdapter(FixCharsetAdapter.class)
    @Override
    public Charset getMessageEncoding() {
        return messageEncoding;
    }

    @Override
    public void setMessageEncoding(Charset messageEncoding) {
        this.messageEncoding = messageEncoding;
    }

    @XmlAttribute(name = "TLoc")
    @Override
    public String getTargetLocationID() {
        return targetLocationID;
    }

    @Override
    public void setTargetLocationID(String targetLocationID) {
        this.targetLocationID = targetLocationID;
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

    @XmlElementRef
    @Override
    public HopsGroup[] getHopsGroups() {
        return hopsGroups;
    }

    public void setHopsGroups(HopsGroup[] hopsGroups) {
        this.hopsGroups = hopsGroups;
    }

    @Override
    public HopsGroup addHopsGroup() {
        
        HopsGroup group = new HopsGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<HopsGroup> groups = new ArrayList<HopsGroup>();
        if (hopsGroups != null && hopsGroups.length > 0) {
            groups = new ArrayList<HopsGroup>(Arrays.asList(hopsGroups));
        }
        groups.add(group);
        hopsGroups = groups.toArray(new HopsGroup[groups.size()]);
        noHops = new Integer(hopsGroups.length);
        
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
            hopsGroups[i] = new HopsGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        }
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
                    HopsGroup group = new HopsGroup44(context);
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
