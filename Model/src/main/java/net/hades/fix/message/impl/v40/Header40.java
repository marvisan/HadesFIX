/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Header40.java
 *
 * $Id: Header40.java,v 1.11 2011-04-04 09:37:11 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.TagNum;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Standard header for FIX version 4.0.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 12/08/2008, 20:25:51
 */
public class Header40 extends Header {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final long serialVersionUID = 1L;

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
        TagNum.TargetSubID.getValue(),
        TagNum.OnBehalfOfSubID.getValue(),
        TagNum.DeliverToSubID.getValue(),
        TagNum.PossDupFlag.getValue(),
        TagNum.PossResend.getValue(),
        TagNum.SendingTime.getValue(),
        TagNum.OrigSendingTime.getValue()
    }));
    
    protected static final Set<Integer> START_COMP_TAGS = null;
        
    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SecureDataLen.getValue()
    }));
    
    protected static final Set<Integer> STANDARD_SECURED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.MsgSeqNum.getValue(),
        TagNum.OnBehalfOfCompID.getValue(),
        TagNum.DeliverToCompID.getValue(),
        TagNum.SenderSubID.getValue(),
        TagNum.TargetSubID.getValue(),
        TagNum.OnBehalfOfSubID.getValue(),
        TagNum.DeliverToSubID.getValue(),
        TagNum.PossDupFlag.getValue(),
        TagNum.PossResend.getValue(),
        TagNum.SendingTime.getValue(),
        TagNum.OrigSendingTime.getValue()
    }));

    protected static final Set<Integer> ALL_TAGS;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Header40() {
        super();
        beginString = BeginString.FIX_4_0;
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public Header40(FragmentContext context) {
        super(context);
        beginString = BeginString.FIX_4_0;
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public Header40(String msgType, FragmentContext context) {
        super(msgType, context);
        beginString = BeginString.FIX_4_0;
        SECURED_TAGS = STANDARD_SECURED_TAGS;
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
    }

    @Override
    public Boolean getPossDupFlag() {
        return possDupFlag;
    }

    @Override
    public void setPossDupFlag(Boolean possDupFlag) {
        this.possDupFlag = possDupFlag;
    }

    @Override
    public Boolean getPossResend() {
        return possResend;
    }

    @Override
    public void setPossResend(Boolean possResend) {
        this.possResend = possResend;
    }

    @Override
    public String getSenderCompID() {
        return senderCompID;
    }

    @Override
    public void setSenderCompID(String senderCompID) {
        this.senderCompID = senderCompID;
    }

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
    }
    
    @Override
    public String getDeliverToCompID() {
        return deliverToCompID;
    }

    @Override
    public void setDeliverToCompID(String deliverToCompID) {
        this.deliverToCompID = deliverToCompID;
    }

    @Override
    public String getDeliverToSubID() {
        return deliverToSubID;
    }

    @Override
    public void setDeliverToSubID(String deliverToSubID) {
        this.deliverToSubID = deliverToSubID;
    }

    @Override
    public String getOnBehalfOfCompID() {
        return onBehalfOfCompID;
    }

    @Override
    public void setOnBehalfOfCompID(String onBehalfOfCompID) {
        this.onBehalfOfCompID = onBehalfOfCompID;
    }

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

    @Override
    public String getSenderSubID() {
        return senderSubID;
    }

    @Override
    public void setSenderSubID(String senderSubID) {
        this.senderSubID = senderSubID;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
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
