/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CollInqQualGroup.java
 *
 * $Id: ClrInstGroup.java,v 1.2 2011-02-16 11:24:33 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.CollInquiryQualifier;
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

import net.hades.fix.message.util.TagEncoder;

/**
 * Clearing instruction group.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 05/04/2009, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class CollInqQualGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
                TagNum.CollInquiryQualifier.getValue()
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
     * TagNum = 896. Starting with 4.4 version.
     */
    protected CollInquiryQualifier collInquiryQualifier;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public CollInqQualGroup() {
    }

    public CollInqQualGroup(FragmentContext context) {
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
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.CollInquiryQualifier)
    public CollInquiryQualifier getCollInquiryQualifier() {
        return collInquiryQualifier;
    }

    /**
     * Message field setter.
     * @param collInquiryQualifier field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.CollInquiryQualifier)
    public void setCollInquiryQualifier(CollInquiryQualifier collInquiryQualifier) {
        this.collInquiryQualifier = collInquiryQualifier;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    @Override
    protected int getFirstTag() {
        return TagNum.CollInquiryQualifier.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (collInquiryQualifier == null) {
            errorMsg.append(" [CollInquiryQualifier]");
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
            if (collInquiryQualifier != null) {
                TagEncoder.encode(bao, TagNum.CollInquiryQualifier, collInquiryQualifier.getValue());
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
            case CollInquiryQualifier:
                collInquiryQualifier = CollInquiryQualifier.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [CollInqQualGroup] fields.";
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
        b.append("{CollInqQualGroup=");
        printTagValue(b, TagNum.CollInquiryQualifier, collInquiryQualifier);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
