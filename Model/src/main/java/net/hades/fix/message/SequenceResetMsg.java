/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SequenceResetMsg.java
 *
 * $Id: SequenceResetMsg.java,v 1.13 2011-04-28 10:07:42 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.util.BooleanConverter;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The sequence reset message is used by the sending application to reset the incoming
 * sequence number on the opposing side.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.13 $
 * @created 10/08/2008, 13:41:56
 */
public abstract class SequenceResetMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -7783647084125287421L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.GapFillFlag.getValue(),
        TagNum.NewSeqNo.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NewSeqNo.getValue()
    }));
     
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;
    
    /** 
     * TagNum = 123.
     */
    private Boolean gapFillFlag;
    
    /** 
     * TagNum = 36 REQUIRED.
     */
    private Integer newSeqNo;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public SequenceResetMsg(Header header, ByteBuffer rawMsg) 
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public SequenceResetMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.SequenceReset.getValue(), beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SequenceResetMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.SequenceReset.getValue(), beginString, applVerID);
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
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////
    
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.GapFillFlag)
    public Boolean getGapFillFlag() {
        return gapFillFlag;
    }

    /**
     * Message field setter.
     * @param gapFillFlag field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.GapFillFlag)
    public void setGapFillFlag(Boolean gapFillFlag) {
        this.gapFillFlag = gapFillFlag;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.NewSeqNo, required=true)
    public Integer getNewSeqNo() {
        return newSeqNo;
    }

    /**
     * Message field setter.
     * @param newSeqNo field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.NewSeqNo, required=true)
    public void setNewSeqNo(Integer newSeqNo) {
        this.newSeqNo = newSeqNo;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (newSeqNo == null) {
            errorMsg.append(" [NewSeqNo]");
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
            TagEncoder.encode(bao, TagNum.GapFillFlag, gapFillFlag);
            TagEncoder.encode(bao, TagNum.NewSeqNo, newSeqNo);
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
            if (MsgUtil.isTagInList(TagNum.GapFillFlag, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.GapFillFlag, gapFillFlag);
            }
            if (MsgUtil.isTagInList(TagNum.NewSeqNo, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NewSeqNo, newSeqNo);
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
            case GapFillFlag:
                gapFillFlag = BooleanConverter.parse(new String(tag.value, getSessionCharset()));
                break;

            case NewSeqNo:
                newSeqNo = new Integer(new String(tag.value, getSessionCharset()));
                break;

            default:
                String error = getNotPresentTagMessage(tag);
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
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

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder("{SequenceResetMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.GapFillFlag, gapFillFlag);
        printTagValue(b, TagNum.NewSeqNo, newSeqNo);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
