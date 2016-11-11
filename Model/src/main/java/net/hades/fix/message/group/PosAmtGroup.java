/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PosAmtGroup.java
 *
 * $Id: PosAmtGroup.java,v 1.1 2011-02-10 10:02:16 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PosAmtType;
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
 * Position amount details message.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 05/04/2009, 11:10:10 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class PosAmtGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.PosAmtType.getValue(),
        TagNum.PosAmt.getValue(),
        TagNum.PositionCurrency.getValue()
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
     * TagNum = 707. Starting with 5.0 version.
     */
    protected PosAmtType posAmtType;

    /**
     * TagNum = 708. Starting with 5.0 version.
     */
    protected Double posAmt;
    
    /**
     * TagNum = 753. Starting with 5.0 version.
     */
    protected Currency positionCurrency;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PosAmtGroup() {
    }

    public PosAmtGroup(FragmentContext context) {
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
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PosAmtType)
    public PosAmtType getPosAmtType() {
        return posAmtType;
    }

    /**
     * Message field setter.
     * @param posAmtType field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PosAmtType)
    public void setPosAmtType(PosAmtType posAmtType) {
        this.posAmtType = posAmtType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PosAmt)
    public Double getPosAmt() {
        return posAmt;
    }

    /**
     * Message field setter.
     * @param posAmt field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PosAmt)
    public void setPosAmt(Double posAmt) {
        this.posAmt = posAmt;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PositionCurrency)
    public Currency getPositionCurrency() {
        return positionCurrency;
    }

    /**
     * Message field setter.
     * @param positionCurrency field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.PositionCurrency)
    public void setPositionCurrency(Currency positionCurrency) {
        this.positionCurrency = positionCurrency;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.PosAmtType.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (posAmtType == null) {
            errorMsg.append(" [PosAmtType]");
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
            if (posAmtType != null) {
                TagEncoder.encode(bao, TagNum.PosAmtType, posAmtType.getValue());
            }
            TagEncoder.encode(bao, TagNum.PosAmt, posAmt);
            if (positionCurrency != null) {
                TagEncoder.encode(bao, TagNum.PositionCurrency, positionCurrency.getValue());
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
            case PosAmtType:
                posAmtType = PosAmtType.valueFor(new String(tag.value, sessionCharset));
                break;

            case PosAmt:
                posAmt = Double.valueOf(new String(tag.value, sessionCharset));
                break;

            case PositionCurrency:
                positionCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [PosAmtGroup] fields.";
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
        b.append("{PosAmtGroup=");
        printTagValue(b, TagNum.PosAmtType, posAmtType);
        printTagValue(b, TagNum.PosAmt, posAmt);
        printTagValue(b, TagNum.PositionCurrency, positionCurrency);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
