/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingSecurityAltIDGroup.java
 *
 * $Id: UnderlyingSecurityAltIDGroup.java,v 1.9 2010-11-23 10:20:16 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.TagNotPresentException;
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
import net.hades.fix.message.util.TagEncoder;

/**
 * Alternate Security value for this underlying security.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 16/12/2008, 8:21:53 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class UnderlyingSecurityAltIDGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -3724947585136691810L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.UnderlyingSecurityAltID.getValue(),
        TagNum.UnderlyingSecurityAltIDSource.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 458. Starting with 4.4 version.
     */
    protected String underlyingSecurityAltID;

    /**
     * TagNum = 459. Starting with 4.4 version.
     */
    protected String underlyingSecurityAltIDSource;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UnderlyingSecurityAltIDGroup() {
    }

    public UnderlyingSecurityAltIDGroup(FragmentContext context) {
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
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.UnderlyingSecurityAltID)
    public String getUnderlyingSecurityAltID() {
        return underlyingSecurityAltID;
    }

    /**
     * Message field setter.
     * @param underlyingSecurityAltID field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.UnderlyingSecurityAltID)
    public void setUnderlyingSecurityAltID(String underlyingSecurityAltID) {
        this.underlyingSecurityAltID = underlyingSecurityAltID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.UnderlyingSecurityAltIDSource)
    public String getUnderlyingSecurityAltIDSource() {
        return underlyingSecurityAltIDSource;
    }

    /**
     * Message field setter.
     * @param underlyingSecurityAltIDSource field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.UnderlyingSecurityAltIDSource)
    public void setUnderlyingSecurityAltIDSource(String underlyingSecurityAltIDSource) {
        this.underlyingSecurityAltIDSource = underlyingSecurityAltIDSource;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.UnderlyingSecurityAltID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (underlyingSecurityAltID == null || underlyingSecurityAltID.trim().isEmpty()) {
            errorMsg.append(" [UnderlyingSecurityAltID]");
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
            TagEncoder.encode(bao, TagNum.UnderlyingSecurityAltID, underlyingSecurityAltID);
            TagEncoder.encode(bao, TagNum.UnderlyingSecurityAltIDSource, underlyingSecurityAltIDSource);
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
            case UnderlyingSecurityAltID:
                underlyingSecurityAltID = new String(tag.value, sessionCharset);
                break;

            case UnderlyingSecurityAltIDSource:
                underlyingSecurityAltIDSource = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [UnderlyingSecurityAltIDGroup] fields.";
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
    protected String getUnsupportedTagMessage() {
        throw new UnsupportedOperationException("No need for error message in group [UnderlyingSecurityAltIDGroup].");
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
        b.append("{UnderlyingSecurityAltIDGroup=");
        printTagValue(b, TagNum.UnderlyingSecurityAltID, underlyingSecurityAltID);
        printTagValue(b, TagNum.UnderlyingSecurityAltIDSource, underlyingSecurityAltIDSource);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
