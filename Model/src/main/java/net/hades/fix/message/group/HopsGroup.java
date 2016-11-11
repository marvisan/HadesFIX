/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HopsGroup.java
 *
 * $Id: HopsGroup.java,v 1.11 2010-11-23 10:20:17 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * Number of repeating groups of historical “hop” information. Only
 * applicable if OnBehalfOfCompID is used, however, its use is optional. 
 * Note that some market regulations or counterparties may require 
 * tracking of message hops.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 13/08/2008, 20:29:49
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class HopsGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.HopCompID.getValue(),
        TagNum.HopSendingTime.getValue(),
        TagNum.HopRefID.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;
    
    protected static final Set<Integer> START_COMP_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /** 
     * TagNum = 628. Starting with 4.3 version.
     */
    protected String hopCompID;
    
    /** 
     * TagNum = 629. Starting with 4.3 version.
     */
    protected Date hopSendingTime;
    
    /** 
     * TagNum = 630. Starting with 4.3 version.
     */
    protected Integer hopRefID;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public HopsGroup() {
    }
    
    public HopsGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return TAGS;
    }

    public void copyFrom(HopsGroup hopsGroup) {
        this.hopCompID = hopsGroup.getHopCompID();
        this.hopSendingTime = hopsGroup.getHopSendingTime();
        this.hopRefID = hopsGroup.getHopRefID();
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.HopCompID, required=true)
    public String getHopCompID() {
        return hopCompID;
    }

    /**
     * Message field setter.
     * @param hopCompID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.HopCompID, required=true)
    public void setHopCompID(String hopCompID) {
        this.hopCompID = hopCompID;
    }
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.HopRefID)
    public Integer getHopRefID() {
        return hopRefID;
    }

    /**
     * Message field setter.
     * @param hopRefID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.HopRefID)
    public void setHopRefID(Integer hopRefID) {
        this.hopRefID = hopRefID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.HopSendingTime)
    public Date getHopSendingTime() {
        return hopSendingTime;
    }

    /**
     * Message field setter.
     * @param hopSendingTime field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.HopSendingTime)
    public void setHopSendingTime(Date hopSendingTime) {
        this.hopSendingTime = hopSendingTime;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (hopCompID == null || hopCompID.trim().isEmpty()) {
            errorMsg.append(" [HopCompID]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }
  
    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {             validateRequiredTags();         }
        
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            TagEncoder.encode(bao, TagNum.HopCompID, hopCompID);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.HopSendingTime, hopSendingTime);
            TagEncoder.encode(bao, TagNum.HopRefID, hopRefID);
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
            case HopCompID:
                hopCompID = new String(tag.value, sessionCharset);
                break;

            case HopSendingTime:
                hopSendingTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case HopRefID:
                hopRefID = new Integer(new String(tag.value, sessionCharset));
                break;
                
            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [HopdGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber,tag.tagNum, error);
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

    @Override
    protected int getFirstTag() {
        return TagNum.HopCompID.getValue();
    }

    // </editor-fold>

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
        b.append("{HopsGroup=");
        printTagValue(b, TagNum.HopCompID, hopCompID);
        printUTCDateTimeTagValue(b, TagNum.HopSendingTime, hopSendingTime);
        printTagValue(b, TagNum.HopRefID, hopRefID);
        b.append("}");

        return b.toString();
    }

     // </editor-fold>
}
