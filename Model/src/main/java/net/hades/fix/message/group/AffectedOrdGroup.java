/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AffectedOrdGroup.java
 *
 * $Id: AffectedOrdGroup.java,v 1.1 2011-05-06 09:02:57 vrotaru Exp $
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
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.util.TagEncoder;

/**
 * Affected orders group.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/05/2011, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AffectedOrdGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.OrigClOrdID.getValue(),
        TagNum.AffectedOrderID.getValue(),
        TagNum.AffectedSecondaryOrderID.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    // ACCESSORS
    //////////////////////////////////////////


    /**
     * TagNum = 41. Starting with 4.3 version.
     */
    protected String origClOrdID;

    /**
     * TagNum = 535. Starting with 4.3 version.
     */
    protected String affectedOrderID;
    
    /**
     * TagNum = 536. Starting with 4.3 version.
     */
    protected String affectedSecondaryOrderID;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public AffectedOrdGroup() {
    }

    public AffectedOrdGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSORS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrigClOrdID, required = true)
    public String getOrigClOrdID() {
        return origClOrdID;
    }

    /**
     * Message field setter.
     * @param origClOrdID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.OrigClOrdID, required = true)
    public void setOrigClOrdID(String origClOrdID) {
        this.origClOrdID = origClOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.AffectedOrderID)
    public String getAffectedOrderID() {
        return affectedOrderID;
    }

    /**
     * Message field setter.
     * @param affectedOrderID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.AffectedOrderID)
    public void setAffectedOrderID(String affectedOrderID) {
        this.affectedOrderID = affectedOrderID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.AffectedSecondaryOrderID)
    public String getAffectedSecondaryOrderID() {
        return affectedSecondaryOrderID;
    }

    /**
     * Message field setter.
     * @param affectedSecondaryOrderID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.AffectedSecondaryOrderID)
    public void setAffectedSecondaryOrderID(String affectedSecondaryOrderID) {
        this.affectedSecondaryOrderID = affectedSecondaryOrderID;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.OrigClOrdID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (origClOrdID == null || origClOrdID.trim().isEmpty()) {
            errorMsg.append(" [OrigClOrdID]");
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
            TagEncoder.encode(bao, TagNum.OrigClOrdID, origClOrdID);
            TagEncoder.encode(bao, TagNum.AffectedOrderID, affectedOrderID);
            TagEncoder.encode(bao, TagNum.AffectedSecondaryOrderID, affectedSecondaryOrderID);

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
            case OrigClOrdID:
                origClOrdID = new String(tag.value, sessionCharset);
                break;
                
            case AffectedOrderID:
                affectedOrderID = new String(tag.value, sessionCharset);
                break;
                
            case AffectedSecondaryOrderID:
                affectedSecondaryOrderID = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [AffectedOrdGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
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
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{AffectedOrdGroup=");
        printTagValue(b, TagNum.OrigClOrdID, origClOrdID);
        printTagValue(b, TagNum.AffectedOrderID, affectedOrderID);
        printTagValue(b, TagNum.AffectedSecondaryOrderID, affectedSecondaryOrderID);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
