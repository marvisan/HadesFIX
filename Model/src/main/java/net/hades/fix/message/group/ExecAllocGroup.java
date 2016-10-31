/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecAllocGroup.java
 *
 * $Id: ExecAllocGroup.java,v 1.4 2011-02-16 11:24:33 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.LastCapacity;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Execution allocation group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ExecAllocGroup extends Group {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.LastQty.getValue(),
        TagNum.ExecID.getValue(),
        TagNum.SecondaryExecID.getValue(),
        TagNum.LastPx.getValue(),
        TagNum.LastParPx.getValue(),
        TagNum.LastCapacity.getValue(),
        TagNum.TradeID.getValue(),
        TagNum.FirmTradeID.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 32. Starting with 4.0 version.
     */
    protected Double lastQty;

    /**
     * TagNum = 17. Starting with 4.0 version.
     */
    protected String execID;

    /**
     * TagNum = 527. Starting with 4.3 version.
     */
    protected String secondaryExecID;

    /**
     * TagNum = 31. Starting with 4.0 version.
     */
    protected Double lastPx;

    /**
     * TagNum = 669. Starting with 4.4 version.
     */
    protected Double lastParPx;

    /**
     * TagNum = 29. Starting with 4.1 version.
     */
    protected LastCapacity lastCapacity;

    /**
     * TagNum = 1003. Starting with 5.0 version.
     */
    protected String tradeID;

    /**
     * TagNum = 1041. Starting with 5.0 version.
     */
    protected String firmTradeID;

    /**
     * TagNum = 30. Starting with 4.0 version.
     */
    protected String lastMkt;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public ExecAllocGroup() {
    }
    
    public ExecAllocGroup(FragmentContext context) {
        super(context);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.LastQty)
    public Double getLastQty() {
        return lastQty;
    }

    /**
     * Message field setter.
     * @param lastQty field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.LastQty)
    public void setLastQty(Double lastQty) {
        this.lastQty = lastQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ExecID)
    public String getExecID() {
        return execID;
    }

    /**
     * Message field setter.
     * @param execID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ExecID)
    public void setExecID(String execID) {
        this.execID = execID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecondaryExecID)
    public String getSecondaryExecID() {
        return secondaryExecID;
    }

    /**
     * Message field setter.
     * @param secondaryExecID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.SecondaryExecID)
    public void setSecondaryExecID(String secondaryExecID) {
        this.secondaryExecID = secondaryExecID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.LastPx)
    public Double getLastPx() {
        return lastPx;
    }

    /**
     * Message field setter.
     * @param lastPx field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.LastPx)
    public void setLastPx(Double lastPx) {
        this.lastPx = lastPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastParPx)
    public Double getLastParPx() {
        return lastParPx;
    }

    /**
     * Message field setter.
     * @param lastParPx field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastParPx)
    public void setLastParPx(Double lastParPx) {
        this.lastParPx = lastParPx;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.LastCapacity)
    public LastCapacity getLastCapacity() {
        return lastCapacity;
    }

    /**
     * Message field setter.
     * @param lastCapacity field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.LastCapacity)
    public void setLastCapacity(LastCapacity lastCapacity) {
        this.lastCapacity = lastCapacity;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeID)
    public String getTradeID() {
        return tradeID;
    }

    /**
     * Message field setter.
     * @param tradeID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.TradeID)
    public void setTradeID(String tradeID) {
        this.tradeID = tradeID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.FirmTradeID)
    public String getFirmTradeID() {
        return firmTradeID;
    }

    /**
     * Message field setter.
     * @param firmTradeID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.FirmTradeID)
    public void setFirmTradeID(String firmTradeID) {
        this.firmTradeID = firmTradeID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.LastMkt)
    public String getLastMkt() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param lastMkt field value
     */
    @FIXVersion(introduced="4.0", retired = "4.2")
    @TagNumRef(tagNum=TagNum.LastMkt)
    public void setLastMkt(String lastMkt) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.LastQty.getValue();
    }
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (lastQty == null) {
            errorMsg.append(" [LastQty]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }
    
    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            TagEncoder.encode(bao, TagNum.LastQty, lastQty);
            TagEncoder.encode(bao, TagNum.ExecID, execID);
            TagEncoder.encode(bao, TagNum.SecondaryExecID, secondaryExecID);
            TagEncoder.encode(bao, TagNum.LastPx, lastPx);
            TagEncoder.encode(bao, TagNum.LastParPx, lastParPx);
            if (lastCapacity != null) {
                TagEncoder.encode(bao, TagNum.LastCapacity, lastCapacity.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradeID, tradeID);
            TagEncoder.encode(bao, TagNum.FirmTradeID, firmTradeID);
            TagEncoder.encode(bao, TagNum.LastMkt, lastMkt);
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
        if (secured) {
            return new byte[0];
        } else {
            return encodeFragmentAll();
        }
    }
    
    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case LastQty:
                lastQty = new Double(new String(tag.value, sessionCharset));
                break;

            case ExecID:
                execID = new String(tag.value, sessionCharset);
                break;

           case SecondaryExecID:
                secondaryExecID = new String(tag.value, sessionCharset);
                break;

            case LastPx:
                lastPx = new Double(new String(tag.value, sessionCharset));
                break;

            case LastParPx:
                lastParPx = new Double(new String(tag.value, sessionCharset));
                break;

            case LastCapacity:
                lastCapacity = LastCapacity.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

           case TradeID:
                tradeID = new String(tag.value, sessionCharset);
                break;

           case FirmTradeID:
                firmTradeID = new String(tag.value, sessionCharset);
                break;

            case LastMkt:
                lastMkt = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [ExecAllocGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    /// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="toString()">
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{ExecAllocGroup=");
        printTagValue(b, TagNum.LastQty, lastQty);
        printTagValue(b, TagNum.ExecID, execID);
        printTagValue(b, TagNum.SecondaryExecID, secondaryExecID);
        printTagValue(b, TagNum.LastPx, lastPx);
        printTagValue(b, TagNum.LastParPx, lastParPx);
        printTagValue(b, TagNum.LastCapacity, lastCapacity);
        printTagValue(b, TagNum.TradeID, tradeID);
        printTagValue(b, TagNum.FirmTradeID, firmTradeID);
        printTagValue(b, TagNum.LastMkt, lastMkt);
        b.append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
    
}
