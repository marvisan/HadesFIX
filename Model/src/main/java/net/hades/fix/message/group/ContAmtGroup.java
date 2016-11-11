/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ContAmtGroup.java
 *
 * $Id: ContAmtGroup.java,v 1.1 2011-01-03 07:28:47 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ContAmtType;
import net.hades.fix.message.type.Currency;
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
 * Contract Amount details on an Execution Report message.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ContAmtGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ContAmtType.getValue(),
        TagNum.ContAmtValue.getValue(),
        TagNum.ContAmtCurr.getValue()
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
     * TagNum = 519. Starting with 4.3 version.
     */
    protected ContAmtType contAmtType;

    /**
     * TagNum = 520. Starting with 4.3 version.
     */
    protected Double contAmtValue;
    
    /**
     * TagNum = 521. Starting with 4.2 version.
     */
    protected Currency contAmtCurr;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ContAmtGroup() {
    }

    public ContAmtGroup(FragmentContext context) {
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
    @TagNumRef(tagNum=TagNum.ContAmtType)
    public ContAmtType getContAmtType() {
        return contAmtType;
    }

    /**
     * Message field setter.
     * @param contAmtType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ContAmtType)
    public void setContAmtType(ContAmtType contAmtType) {
        this.contAmtType = contAmtType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ContAmtValue)
    public Double getContAmtValue() {
        return contAmtValue;
    }

    /**
     * Message field setter.
     * @param contAmtValue field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ContAmtValue)
    public void setContAmtValue(Double contAmtValue) {
        this.contAmtValue = contAmtValue;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ContAmtCurr)
    public Currency getContAmtCurr() {
        return contAmtCurr;
    }

    /**
     * Message field setter.
     * @param contAmtCurr field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ContAmtCurr)
    public void setContAmtCurr(Currency contAmtCurr) {
        this.contAmtCurr = contAmtCurr;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.ContAmtType.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (contAmtType == null) {
            errorMsg.append(" [ContAmtType]");
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
            if (contAmtType != null) {
                TagEncoder.encode(bao, TagNum.ContAmtType, contAmtType.getValue());
            }
            TagEncoder.encode(bao, TagNum.ContAmtValue, contAmtValue);
            if (contAmtCurr != null) {
                TagEncoder.encode(bao, TagNum.ContAmtCurr, contAmtCurr.getValue());
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
            case ContAmtType:
                contAmtType = ContAmtType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ContAmtValue:
                contAmtValue = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case ContAmtCurr:
                contAmtCurr = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [ContAmtGroup] fields.";
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
        b.append("{ContAmtGroup=");
        printTagValue(b, TagNum.ContAmtType, contAmtType);
        printTagValue(b, TagNum.ContAmtValue, contAmtValue);
        printTagValue(b, TagNum.ContAmtCurr, contAmtCurr);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
