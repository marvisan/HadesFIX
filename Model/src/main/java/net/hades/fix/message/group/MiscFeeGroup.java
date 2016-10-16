/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MiscFeeGroup.java
 *
 * $Id: MiscFeeGroup.java,v 1.2 2011-01-12 11:33:58 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;

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
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.MiscFeeBasis;
import net.hades.fix.message.type.MiscFeeType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Miscelaneous fees group.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 05/04/2009, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class MiscFeeGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.MiscFeeAmt.getValue(),
        TagNum.MiscFeeCurr.getValue(),
        TagNum.MiscFeeType.getValue(),
        TagNum.MiscFeeBasis.getValue()
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
     * TagNum = 137. Starting with 4.0 version.
     */
    protected Double miscFeeAmt;

    /**
     * TagNum = 138. Starting with 4.0 version.
     */
    protected Currency miscFeeCurr;
    
    /**
     * TagNum = 139. Starting with 4.0 version.
     */
    protected MiscFeeType miscFeeType;

    /**
     * TagNum = 891. Starting with 4.4 version.
     */
    protected MiscFeeBasis miscFeeBasis;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MiscFeeGroup() {
    }

    public MiscFeeGroup(FragmentContext context) {
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
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.MiscFeeAmt)
    public Double getMiscFeeAmt() {
        return miscFeeAmt;
    }

    /**
     * Message field setter.
     * @param miscFeeAmt field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.MiscFeeAmt)
    public void setMiscFeeAmt(Double miscFeeAmt) {
        this.miscFeeAmt = miscFeeAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.MiscFeeCurr)
    public Currency getMiscFeeCurr() {
        return miscFeeCurr;
    }

    /**
     * Message field setter.
     * @param miscFeeCurr field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.MiscFeeCurr)
    public void setMiscFeeCurr(Currency miscFeeCurr) {
        this.miscFeeCurr = miscFeeCurr;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.MiscFeeType)
    public MiscFeeType getMiscFeeType() {
        return miscFeeType;
    }

    /**
     * Message field setter.
     * @param miscFeeType field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.MiscFeeType)
    public void setMiscFeeType(MiscFeeType miscFeeType) {
        this.miscFeeType = miscFeeType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MiscFeeBasis)
    public MiscFeeBasis getMiscFeeBasis() {
        return miscFeeBasis;
    }

    /**
     * Message field setter.
     * @param miscFeeBasis field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.MiscFeeBasis)
    public void setMiscFeeBasis(MiscFeeBasis miscFeeBasis) {
        this.miscFeeBasis = miscFeeBasis;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.MiscFeeAmt.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (miscFeeAmt == null) {
            errorMsg.append(" [MiscFeeAmt]");
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
            TagEncoder.encode(bao, TagNum.MiscFeeAmt, miscFeeAmt);
            if (miscFeeCurr != null) {
                TagEncoder.encode(bao, TagNum.MiscFeeCurr, miscFeeCurr.getValue());
            }
            if (miscFeeType != null) {
                TagEncoder.encode(bao, TagNum.MiscFeeType, miscFeeType.getValue());
            }
            if (miscFeeBasis != null) {
                TagEncoder.encode(bao, TagNum.MiscFeeBasis, miscFeeBasis.getValue());
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
            case MiscFeeAmt:
                miscFeeAmt = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case MiscFeeCurr:
                miscFeeCurr = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case MiscFeeType:
                miscFeeType = MiscFeeType.valueFor(new String(tag.value, sessionCharset));
                break;

            case MiscFeeBasis:
                miscFeeBasis = MiscFeeBasis.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [MiscFeeGroup] fields.";
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
        b.append("{MiscFeeGroup=");
        printTagValue(b, TagNum.MiscFeeAmt, miscFeeAmt);
        printTagValue(b, TagNum.MiscFeeCurr, miscFeeCurr);
        printTagValue(b, TagNum.MiscFeeType, miscFeeType);
        printTagValue(b, TagNum.MiscFeeBasis, miscFeeBasis);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
