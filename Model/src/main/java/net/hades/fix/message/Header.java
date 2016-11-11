/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Header.java
 *
 * $Id: Header.java,v 1.15 2011-04-04 09:37:11 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.config.ThreadData;
import net.hades.fix.message.crypt.Crypter;
import net.hades.fix.message.exception.UnsupportedCrypterException;
import net.hades.fix.message.group.impl.v50.HopsGroup50;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.config.SessionContext;
import net.hades.fix.message.config.SessionContextKey;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.HopsGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * Fix standard header super class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.15 $
 * @created 12/08/2008, 20:08:44
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Header extends FIXFragment {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /** TagNum = 8 REQUIRED. */
    protected BeginString beginString;

    /** TagNum = 9 REQUIRED. */
    protected Integer bodyLength;

    /** TagNum = 35 REQUIRED. */
    protected String msgType;
        
    /** TagNum = 1128. */
    protected ApplVerID applVerID;
    
    /** TagNum = 1129. */
    protected String cstmApplVerID;
    
    /** TagNum = 49 REQUIRED. */
    protected String senderCompID;
    
    /** TagNum = 56 REQUIRED. */
    protected String targetCompID;
    
    /** TagNum = 115. */
    protected String onBehalfOfCompID;
    
    /** TagNum = 128. */
    protected String deliverToCompID;
    
    /** TagNum = 90. */
    protected Integer secureDataLen;
    
    /** TagNum = 91. */
    protected byte[] secureData;
    
    /** TagNum = 34 REQUIRED. */
    protected Integer msgSeqNum;
    
    /** TagNum = 50. */
    protected String senderSubID;
    
    /** TagNum = 57. */
    protected String targetSubID;
    
    /** TagNum = 116. */
    protected String onBehalfOfSubID;
    
    /** TagNum = 129. */
    protected String deliverToSubID;
    
    /** TagNum = 43. */
    protected Boolean possDupFlag;
    
    /** TagNum = 97. */
    protected Boolean possResend;
    
    /** TagNum = 52 REQUIRED. */
    protected Date sendingTime;

    /** TagNum = 122. */
    protected Date origSendingTime;
    
    /** TagNum = 142. */
    protected String senderLocationID;
    
    /** TagNum = 144. */
    protected String onBehalfOfLocationID;
    
    /** TagNum = 145. */
    protected String deliverToLocationID;
        
    /** TagNum = 143. */
    protected String targetLocationID;
    
    /** TagNum = 212. */
    protected Integer xmlDataLen;
    
    /** TagNum = 213. */
    protected byte[] xmlData;
    
    /** TagNum = 369. */
    protected Integer lastMsgSeqNumProcessed;
    
    /** TagNum = 370. */
    protected Date onBehalfOfSendingTime;
    
    /** TagNum = 627. */
    protected Integer noHops;
    
    /** TagNum = 628,629,630. */
    protected HopsGroup[] hopsGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Header() {
        super();
    }

    public Header(FragmentContext context) {
        super(context);
    }
    
    public Header(String msgType, FragmentContext context) {
        this(context);
        this.msgType = msgType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    
    // ABSTRACT INTERFACE
    //////////////////////////////////////////

    // ACCESSOR METHODS
    //////////////////////////////////////////

    public void copyFrom(Header header) {
        this.msgSeqNum = header.getMsgSeqNum();
        try {
            this.cstmApplVerID = header.getCstmApplVerID();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.senderCompID = header.getSenderCompID();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.targetCompID = header.getTargetCompID();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.onBehalfOfCompID = header.getOnBehalfOfCompID();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.deliverToCompID = header.getDeliverToCompID();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.secureDataLen = header.getSecureDataLen();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.secureData = header.getSecureData();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.senderSubID = header.getSenderSubID();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.targetSubID = header.getTargetSubID();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.onBehalfOfSubID = header.getOnBehalfOfSubID();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.deliverToSubID = header.getDeliverToSubID();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.possDupFlag = header.getPossDupFlag();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.possResend = header.getPossResend();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.sendingTime = header.getSendingTime();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.origSendingTime = header.getOrigSendingTime();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.senderLocationID = header.getSenderLocationID();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.onBehalfOfLocationID = header.getOnBehalfOfLocationID();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.deliverToLocationID = header.getDeliverToLocationID();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.targetLocationID = header.getTargetLocationID();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.xmlDataLen = header.getXmlDataLen();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.xmlData = header.getXmlData();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.lastMsgSeqNumProcessed = header.getLastMsgSeqNumProcessed();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.onBehalfOfSendingTime = header.getOnBehalfOfSendingTime();
        } catch (UnsupportedOperationException ex) {
        }
        try {
            this.noHops = header.getNoHops();
            if (noHops != null) {
                hopsGroups = new HopsGroup[header.getHopsGroups().length];
                for (int i = 0; i < header.getHopsGroups().length; i++) {
                    HopsGroup grp = new HopsGroup50();
                    grp.copyFrom(header.getHopsGroups()[i]);
                    hopsGroups[i] = grp;
                }
            }
        } catch (UnsupportedOperationException ex) {
        }
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.BeginString, required=true)
    public BeginString getBeginString() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param beginString field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.BeginString, required=true)
    public void setBeginString(BeginString beginString) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.BodyLength, required=true)
    public int getBodyLength() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param bodyLength field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.BodyLength, required=true)
    public void setBodyLength(int bodyLength) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.MsgSeqNum, required=true)
    public Integer getMsgSeqNum() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param msgSeqNum field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.MsgSeqNum, required=true)
    public void setMsgSeqNum(Integer msgSeqNum) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.MsgType, required=true)
    public String getMsgType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param msgType field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.MsgType, required=true)
    public void setMsgType(String msgType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrigSendingTime)
    public Date getOrigSendingTime() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param origSendingTime field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OrigSendingTime)
    public void setOrigSendingTime(Date origSendingTime) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.TargetSubID)
    public String getTargetSubID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param targetSubID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.TargetSubID)
    public void setTargetSubID(String targetSubID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.PossDupFlag)
    public Boolean getPossDupFlag() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param possDupFlag field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.PossDupFlag)
    public void setPossDupFlag(Boolean possDupFlag) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.PossResend)
    public Boolean getPossResend() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param possResend field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.PossResend)
    public void setPossResend(Boolean possResend) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SenderCompID, required=true)
    public String getSenderCompID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param senderCompID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SenderCompID, required=true)
    public void setSenderCompID(String senderCompID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SendingTime, required=true)
    public Date getSendingTime() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param sendingTime field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SendingTime, required=true)
    public void setSendingTime(Date sendingTime) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.TargetCompID, required=true)
    public String getTargetCompID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param targetCompID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.TargetCompID, required=true)
    public void setTargetCompID(String targetCompID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.DeliverToCompID)
    public String getDeliverToCompID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param deliverToCompID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.DeliverToCompID)
    public void setDeliverToCompID(String deliverToCompID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.DeliverToSubID)
    public String getDeliverToSubID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param deliverToSubID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.DeliverToSubID)
    public void setDeliverToSubID(String deliverToSubID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OnBehalfOfCompID)
    public String getOnBehalfOfCompID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param onBehalfOfCompID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OnBehalfOfCompID)
    public void setOnBehalfOfCompID(String onBehalfOfCompID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OnBehalfOfSubID)
    public String getOnBehalfOfSubID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param onBehalfOfSubID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.OnBehalfOfSubID)
    public void setOnBehalfOfSubID(String onBehalfOfSubID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SecureDataLen)
    public Integer getSecureDataLen() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param secureDataLen field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SecureDataLen)
    public void setSecureDataLen(Integer secureDataLen) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SecureData)
    public byte[] getSecureData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param secureData field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SecureData)
    public void setSecureData(byte[] secureData) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SenderSubID)
    public String getSenderSubID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param senderSubID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.SenderSubID)
    public void setSenderSubID(String senderSubID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SenderLocationID)
    public String getSenderLocationID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param senderLocationID field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SenderLocationID)
    public void setSenderLocationID(String senderLocationID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.OnBehalfOfLocationID)
    public String getOnBehalfOfLocationID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param onBehalfOfLocationID field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.OnBehalfOfLocationID)
    public void setOnBehalfOfLocationID(String onBehalfOfLocationID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.DeliverToLocationID)
    public String getDeliverToLocationID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param deliverToLocationID field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.DeliverToLocationID)
    public void setDeliverToLocationID(String deliverToLocationID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.LastMsgSeqNumProcessed)
    public Integer getLastMsgSeqNumProcessed() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param lastMsgSeqNumProcessed field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.LastMsgSeqNumProcessed)
    public void setLastMsgSeqNumProcessed(Integer lastMsgSeqNumProcessed) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MessageEncoding)
    public Charset getMessageEncoding() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param messageEncoding field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.MessageEncoding)
    public void setMessageEncoding(Charset messageEncoding) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.4")
    @TagNumRef(tagNum=TagNum.OnBehalfOfSendingTime)
    public Date getOnBehalfOfSendingTime() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param onBehalfOfSendingTime field value
     */
    @FIXVersion(introduced="4.2", retired="4.4")
    @TagNumRef(tagNum=TagNum.OnBehalfOfSendingTime)
    public void setOnBehalfOfSendingTime(Date onBehalfOfSendingTime) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.TargetLocationID)
    public String getTargetLocationID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param targetLocationID field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.TargetLocationID)
    public void setTargetLocationID(String targetLocationID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.XmlData)
    public byte[] getXmlData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param xmlData field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.XmlData)
    public void setXmlData(byte[] xmlData) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.XmlDataLen)
    public Integer getXmlDataLen() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param xmlDataLen field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.XmlDataLen)
    public void setXmlDataLen(Integer xmlDataLen) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for the <code>HopsGroup</code> array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.3")
    public HopsGroup[] getHopsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method adds a  <code>HopsGroup</code> object to the existing array of <code>hopsGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noHops</code> field to the proper value.
     * Note: If the <code>setNoHops</code> method has been called there will already a number of objects in the
     * array created.
     * @return newly created and added to the array group object
     */
    @FIXVersion(introduced="4.3")
    public HopsGroup addHopsGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method deletes a  <code>HopsGroup</code> object from the existing array of <code>hopsGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noHops</code> field to the proper value.
     * @param index position in array to be delted
     * @return deleted group object
     */
    @FIXVersion(introduced="4.3")
    public HopsGroup deleteHopsGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Deletes all the <code>HopsGroup</code> objects from the <code>hopsGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noHops</code> field and set it to null.
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.3")
    public int clearHopsGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoHops)
    public Integer getNoHops() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of HopsGroup groups. It will also create an array
     * of HopsGroup objects and set the <code>hopsGroups</code> field with this array.
     * The created objects inside the array need to be populated with data.<br/>
     * If there where already objects in <code>hopsGroups</code> array they will be discarded.
     * @param noHops number of HopsGroup objects
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.NoHops)
    public void setNoHops(Integer noHops) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="T1.1")
    @TagNumRef(tagNum=TagNum.ApplVerID)
    public ApplVerID getApplVerID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param applVerID field value
     */
    @FIXVersion(introduced="T1.1")
    @TagNumRef(tagNum=TagNum.ApplVerID)
    public void setApplVerID(ApplVerID applVerID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="T1.1")
    @TagNumRef(tagNum=TagNum.CstmApplVerID)
    public String getCstmApplVerID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param cstmApplVerID field value
     */
    @FIXVersion(introduced="T1.1")
    @TagNumRef(tagNum=TagNum.CstmApplVerID)
    public void setCstmApplVerID(String cstmApplVerID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    public void setHeaderSessionData() {

        SessionContext context = ThreadData.getSessionContext();
        if (context == null) {
            LOGGER.warning("Session context data has not been set by the current FIX session.");
            sessionCharset = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
            validateRequired = true;
        } else {
            if (context.getValue(SessionContextKey.SESSION_CHARACTER_SET) == null) {
                sessionCharset = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
                LOGGER.finest("Session character set not set. Using default [" + FIXMsg.DEFAULT_CHARACTER_SET + "].");
            } else {
                sessionCharset = (Charset) context.getValue(SessionContextKey.SESSION_CHARACTER_SET);
            }
            if (context.getValue(SessionContextKey.VALIDATE_REQUIRED) == null) {
                validateRequired = true;
                LOGGER.finest("Session FIX required tag validation set to false.");
            } else {
                validateRequired = ((Boolean) context.getValue(SessionContextKey.VALIDATE_REQUIRED)).booleanValue();
            }
            Object crypterObj = context.getValue(SessionContextKey.CRYPTER);
            if (crypterObj != null) {
                encryptionRequired = true;
                crypter = (Crypter) crypterObj;
            }
        }
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (beginString == null) {
            errorMsg.append(" [BeginString]");
            hasMissingTag = true;
        }
        if (msgType == null) {
            errorMsg.append(" [MsgType]");
            hasMissingTag = true;
        }
        if (senderCompID == null || senderCompID.trim().isEmpty()) {
            errorMsg.append(" [SenderCompID]");
            hasMissingTag = true;
        }
        if (targetCompID == null || targetCompID.trim().isEmpty()) {
            errorMsg.append(" [TargetCompID]");
            hasMissingTag = true;
        }
        if (msgSeqNum == null) {
            errorMsg.append(" [MsgSeqNum]");
            hasMissingTag = true;
        }
        if (sendingTime == null) {
            errorMsg.append(" [SendingTime]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }
    
    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            TagEncoder.encode(bao, TagNum.MsgType, msgType);
            if (applVerID != null) {
                TagEncoder.encode(bao, TagNum.ApplVerID, applVerID.getValue());
            }
            TagEncoder.encode(bao, TagNum.CstmApplVerID, cstmApplVerID);
            TagEncoder.encode(bao, TagNum.SenderCompID, senderCompID);
            TagEncoder.encode(bao, TagNum.TargetCompID, targetCompID);
            TagEncoder.encode(bao, TagNum.OnBehalfOfCompID, onBehalfOfCompID);
            TagEncoder.encode(bao, TagNum.DeliverToCompID, deliverToCompID);
            if (secureDataLen != null && secureDataLen.intValue() > 0) {
                if (secureData != null && secureData.length > 0) {
                    secureDataLen = new Integer(secureData.length);
                    TagEncoder.encode(bao, TagNum.SecureDataLen, secureDataLen);
                    TagEncoder.encode(bao, TagNum.SecureData, secureData);
                }
            }
            TagEncoder.encode(bao, TagNum.MsgSeqNum, msgSeqNum);
            TagEncoder.encode(bao, TagNum.SenderSubID, senderSubID);
            TagEncoder.encode(bao, TagNum.SenderLocationID, senderLocationID);
            TagEncoder.encode(bao, TagNum.TargetSubID, targetSubID);
            TagEncoder.encode(bao, TagNum.TargetLocationID, targetLocationID);
            TagEncoder.encode(bao, TagNum.OnBehalfOfSubID, onBehalfOfSubID);
            TagEncoder.encode(bao, TagNum.OnBehalfOfLocationID, onBehalfOfLocationID);
            TagEncoder.encode(bao, TagNum.DeliverToSubID, deliverToSubID);
            TagEncoder.encode(bao, TagNum.DeliverToLocationID, deliverToLocationID);
            TagEncoder.encode(bao, TagNum.PossDupFlag, possDupFlag);
            TagEncoder.encode(bao, TagNum.PossResend, possResend);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.SendingTime, sendingTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.OrigSendingTime, origSendingTime);
            if (messageEncoding != null) {
                TagEncoder.encode(bao, TagNum.MessageEncoding, messageEncoding.name());
            }
            if (xmlDataLen != null && xmlDataLen.intValue() > 0) {
                if (xmlData != null && xmlData.length > 0) {
                    xmlDataLen = new Integer(xmlData.length);
                    TagEncoder.encode(bao, TagNum.XmlDataLen, xmlDataLen);
                    TagEncoder.encode(bao, TagNum.XmlData, xmlData);
                }
            }
            TagEncoder.encode(bao, TagNum.LastMsgSeqNumProcessed, lastMsgSeqNumProcessed);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.OnBehalfOfSendingTime, onBehalfOfSendingTime);
            if (noHops != null && noHops.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoHops, noHops);
                if (hopsGroups != null && hopsGroups.length == noHops.intValue()) {
                    for (int i = 0; i < hopsGroups.length; i++) {
                        if (hopsGroups[i] != null) {
                            bao.write(hopsGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "NoHops field has been set but there is no Hops data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, msgType, TagNum.NoHops.getValue(), error);
                }
            }
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.MsgType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MsgType, msgType);
            }
            if (applVerID != null && MsgUtil.isTagInList(TagNum.ApplVerID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ApplVerID, applVerID.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.CstmApplVerID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CstmApplVerID, cstmApplVerID);
            }
            if (MsgUtil.isTagInList(TagNum.SenderCompID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SenderCompID, senderCompID);
            }
            if (MsgUtil.isTagInList(TagNum.TargetCompID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TargetCompID, targetCompID);
            }
            if (MsgUtil.isTagInList(TagNum.OnBehalfOfCompID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OnBehalfOfCompID, onBehalfOfCompID);
            }
            if (MsgUtil.isTagInList(TagNum.DeliverToCompID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DeliverToCompID, deliverToCompID);
            }
            if (MsgUtil.isTagInList(TagNum.MsgSeqNum, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MsgSeqNum, msgSeqNum);
            }
            if (MsgUtil.isTagInList(TagNum.SenderSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SenderSubID, senderSubID);
            }
            if (MsgUtil.isTagInList(TagNum.SenderLocationID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SenderLocationID, senderLocationID);
            }
            if (MsgUtil.isTagInList(TagNum.TargetSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TargetSubID, targetSubID);
            }
            if (MsgUtil.isTagInList(TagNum.TargetLocationID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TargetLocationID, targetLocationID);
            }
            if (MsgUtil.isTagInList(TagNum.OnBehalfOfSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OnBehalfOfSubID, onBehalfOfSubID);
            }
            if (MsgUtil.isTagInList(TagNum.OnBehalfOfLocationID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OnBehalfOfLocationID, onBehalfOfLocationID);
            }
            if (MsgUtil.isTagInList(TagNum.DeliverToSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DeliverToSubID, deliverToSubID);
            }
            if (MsgUtil.isTagInList(TagNum.DeliverToLocationID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.DeliverToLocationID, deliverToLocationID);
            }
            if (MsgUtil.isTagInList(TagNum.PossDupFlag, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PossDupFlag, possDupFlag);
            }
            if (MsgUtil.isTagInList(TagNum.PossResend, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PossResend, possResend);
            }
            if (MsgUtil.isTagInList(TagNum.SendingTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.SendingTime, sendingTime);
            }
            if (MsgUtil.isTagInList(TagNum.OrigSendingTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.OrigSendingTime, origSendingTime);
            }
            if (messageEncoding != null && MsgUtil.isTagInList(TagNum.MessageEncoding, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MessageEncoding, messageEncoding.name());
            }
            if (xmlDataLen != null && xmlDataLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.XmlDataLen, SECURED_TAGS, secured)) {
                if (xmlData != null && xmlData.length > 0) {
                    xmlDataLen = new Integer(xmlData.length);
                    TagEncoder.encode(bao, TagNum.XmlDataLen, xmlDataLen);
                    TagEncoder.encode(bao, TagNum.XmlData, xmlData);
                }
            }
            if (MsgUtil.isTagInList(TagNum.LastMsgSeqNumProcessed, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastMsgSeqNumProcessed, lastMsgSeqNumProcessed);
            }
            if (noHops != null && noHops.intValue() > 0 && MsgUtil.isTagInList(TagNum.NoHops, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoHops, noHops);
                if (hopsGroups != null && hopsGroups.length == noHops.intValue()) {
                    for (int i = 0; i < hopsGroups.length; i++) {
                        if (hopsGroups[i] != null) {
                            bao.write(hopsGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "NoHops field has been set but there is no Hops data.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, msgType, TagNum.NoHops.getValue(), error);
                }
            }
            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case BeginString:
                beginString = BeginString.valueFor(new String(tag.value, sessionCharset));
                break;

            case BodyLength:
                bodyLength = new Integer(new String(tag.value, sessionCharset));
                break;

            case MsgType:
                msgType = new String(tag.value, sessionCharset);
                break;

            case ApplVerID:
                applVerID = ApplVerID.valueFor(new String(tag.value, sessionCharset));
                break;

            case CstmApplVerID:
                cstmApplVerID = new String(tag.value, sessionCharset);
                break;

            case SenderCompID:
                senderCompID = new String(tag.value, sessionCharset);
                break;

            case TargetCompID:
                targetCompID = new String(tag.value, sessionCharset);
                break;

            case MsgSeqNum:
                msgSeqNum = new Integer(new String(tag.value, sessionCharset));
                break;

            case SendingTime:
                sendingTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case OnBehalfOfCompID:
                onBehalfOfCompID = new String(tag.value, sessionCharset);
                break;

            case DeliverToCompID:
                deliverToCompID = new String(tag.value, sessionCharset);
                break;

            case SenderSubID:
                senderSubID = new String(tag.value, sessionCharset);
                break;

            case SenderLocationID:
                senderLocationID = new String(tag.value, sessionCharset);
                break;

            case TargetSubID:
                targetSubID = new String(tag.value, sessionCharset);
                break;

            case TargetLocationID:
                targetLocationID = new String(tag.value, sessionCharset);
                break;

            case OnBehalfOfSubID:
                onBehalfOfSubID = new String(tag.value, sessionCharset);
                break;

            case OnBehalfOfLocationID:
                onBehalfOfLocationID = new String(tag.value, sessionCharset);
                break;

            case DeliverToSubID:
                deliverToSubID = new String(tag.value, sessionCharset);
                break;

            case DeliverToLocationID:
                deliverToLocationID = new String(tag.value, sessionCharset);
                break;

            case PossDupFlag:
                possDupFlag = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case PossResend:
                possResend = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case OrigSendingTime:
                origSendingTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case MessageEncoding:
                messageEncoding = Charset.forName(new String(tag.value, sessionCharset));
                break;

            case XmlDataLen:
                xmlDataLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case SecureDataLen:
                secureDataLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case LastMsgSeqNumProcessed:
                lastMsgSeqNumProcessed = new Integer(new String(tag.value, sessionCharset));
                break;
                
            case OnBehalfOfSendingTime:
                onBehalfOfSendingTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case NoHops:
                noHops = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in FIX Header version [" +
                    beginString.getValue() + "].";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, msgType, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.SecureDataLen.getValue()) {
            try {
                secureDataLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [SecureDataLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, msgType,
                        TagNum.SecureDataLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, secureDataLen.intValue());
            secureData = dataTag.value;
            if (encryptionRequired) {
                try {
                    byte[] decryptedSecureData = MsgUtil.decryptSecureData(secureData, crypter);
                    result = MsgUtil.insertByteArray(decryptedSecureData, message);
                    if (LOGGER.isLoggable(Level.FINEST)) {
                        LOGGER.log(Level.FINEST, "message decrypted [{0}].", new String(result.array()));
                    }
                } catch (UnsupportedCrypterException ex) {
                    String error = "Could not decrypt secure data section.";
                    throw new BadFormatMsgException(SessionRejectReason.DecryptionProblem, msgType,
                            TagNum.SecureData.getValue(), error);
                }
            }
        }
        if (tag.tagNum == TagNum.XmlDataLen.getValue()) {
            try {
                xmlDataLen = new Integer(new String(tag.value, sessionCharset));
            } catch (NumberFormatException ex) {
                String error = "Tag [XmlDataLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, msgType, TagNum.XmlDataLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, xmlDataLen.intValue());
            xmlData = dataTag.value;
        }
        
        return result;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in release [" + beginString.getValue() + "].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{Header=");
        printTagValue(b, TagNum.BeginString, beginString);
        printTagValue(b, TagNum.BodyLength, bodyLength);
        printTagValue(b, TagNum.MsgType, msgType);
        printTagValue(b, TagNum.ApplVerID, applVerID);
        printTagValue(b, TagNum.CstmApplVerID, cstmApplVerID);
        printTagValue(b, TagNum.SenderCompID, senderCompID);
        printTagValue(b, TagNum.TargetCompID, targetCompID);
        printTagValue(b, TagNum.OnBehalfOfCompID, onBehalfOfCompID);
        printTagValue(b, TagNum.DeliverToCompID, deliverToCompID);
        printTagValue(b, TagNum.SecureDataLen, secureDataLen);
        printBase64TagValue(b, TagNum.SecureData, secureData);
        printTagValue(b, TagNum.MsgSeqNum, msgSeqNum);
        printTagValue(b, TagNum.SenderSubID, senderSubID);
        printTagValue(b, TagNum.OnBehalfOfSubID, onBehalfOfSubID);
        printTagValue(b, TagNum.DeliverToSubID, deliverToSubID);
        printTagValue(b, TagNum.PossDupFlag, possDupFlag);
        printTagValue(b, TagNum.PossResend, possResend);
        printUTCDateTimeTagValue(b, TagNum.SendingTime, sendingTime);
        printUTCDateTimeTagValue(b, TagNum.OrigSendingTime, origSendingTime);
        printTagValue(b, TagNum.SenderLocationID, senderLocationID);
        printTagValue(b, TagNum.OnBehalfOfLocationID, onBehalfOfLocationID);
        printTagValue(b, TagNum.DeliverToLocationID, deliverToLocationID);
        printTagValue(b, TagNum.TargetLocationID, targetLocationID);
        printTagValue(b, TagNum.XmlDataLen, xmlDataLen);
        printTagValue(b, TagNum.XmlData, xmlData);
        printTagValue(b, TagNum.LastMsgSeqNumProcessed, lastMsgSeqNumProcessed);
        printUTCDateTimeTagValue(b, TagNum.OnBehalfOfSendingTime, onBehalfOfSendingTime);
        printTagValue(b, TagNum.NoHops, noHops);
        printTagValue(b, hopsGroups);
        b.append("}");

        return b.toString();
    }
    // </editor-fold>
}
