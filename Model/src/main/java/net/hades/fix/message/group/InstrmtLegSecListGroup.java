/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrmtLegSecListGroup.java
 *
 * $Id: InstrmtLegSecListGroup.java,v 1.3 2011-09-27 08:57:25 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.LegBenchmarkCurveData;
import net.hades.fix.message.comp.LegStipulations;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.util.TagEncoder;

/**
 * Instrument leg group for security list message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 28/04/2011, 6:46:57 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class InstrmtLegSecListGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.LegSwapType.getValue(),
        TagNum.LegSettlType.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * Starting with 4.3 version.
     */
    protected InstrumentLeg instrumentLeg;

    /**
     * TagNum = 556. Starting with 4.3 version.
     */
    protected Currency legCurrency;

    /**
     * TagNum = 690. Starting with 4.4 version.
     */
    protected LegSwapType legSwapType;

    /**
     * TagNum = 587. Starting with 4.4 version.
     */
    protected String legSettlType;

    /**
     * Starting with 4.4 version.
     */
    protected LegStipulations legStipulations;

    /**
     * Starting with 4.4 version.
     */
    protected LegBenchmarkCurveData legBenchmarkCurveData;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrmtLegSecListGroup() {
    }

    public InstrmtLegSecListGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public int getFirstTag() {
        return TagNum.LegSymbol.getValue();
    }

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter for {@link InstrumentLeg}.
     * @return field array value
     */
    @FIXVersion(introduced = "4.3")
    public InstrumentLeg getInstrumentLeg() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link InstrumentLeg} component if used in this message to the proper implementation.<br/>
     */
    @FIXVersion(introduced = "4.3")
    public void setInstrumentLeg() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link InstrumentLeg} component to null.<br/>
     */
    @FIXVersion(introduced = "4.3")
    public void clearInstrumentLeg() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.LegCurrency)
    public Currency getLegCurrency() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param legCurrency field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.LegCurrency)
    public void setLegCurrency(Currency legCurrency) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegSwapType)
    public LegSwapType getLegSwapType() {
        return legSwapType;
    }

    /**
     * Message field setter.
     * @param legSwapType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegSwapType)
    public void setLegSwapType(LegSwapType legSwapType) {
        this.legSwapType = legSwapType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegSettlType)
    public String getLegSettlType() {
        return legSettlType;
    }

    /**
     * Message field setter.
     * @param legSettlType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegSettlType)
    public void setLegSettlType(String legSettlType) {
        this.legSettlType = legSettlType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    public LegStipulations getLegStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link LegStipulations} component if used in this message to the proper implementation.<br/>
     */
    @FIXVersion(introduced = "4.4")
    public void setLegStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link LegStipulations} component to null.<br/>
     */
    @FIXVersion(introduced = "4.4")
    public void clearLegStipulations() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    public LegBenchmarkCurveData getLegBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link LegBenchmarkCurveData} component if used in this message to the proper implementation.<br/>
     */
    @FIXVersion(introduced = "4.4")
    public void setLegBenchmarkCurveData() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link LegBenchmarkCurveData} component to null.<br/>
     */
    @FIXVersion(introduced = "4.4")
    public void clearLegBenchmarkCurveData() {
        this.legBenchmarkCurveData = null;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (instrumentLeg == null || instrumentLeg.getLegSymbol() == null) {
            errorMsg.append(" [LegSymbol]");
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
            bao.write(instrumentLeg.encode(MsgSecureType.ALL_FIELDS));
            if (legCurrency != null) {
                TagEncoder.encode(bao, TagNum.LegCurrency, legCurrency.getValue());
            }
            if (legSwapType != null) {
                TagEncoder.encode(bao, TagNum.LegSwapType, legSwapType.getValue());
            }
            TagEncoder.encode(bao, TagNum.LegSettlType, legSettlType);
            if (legStipulations != null) {
                bao.write(legStipulations.encode(MsgSecureType.ALL_FIELDS));
            }
            if (legBenchmarkCurveData != null) {
                bao.write(legBenchmarkCurveData.encode(MsgSecureType.ALL_FIELDS));
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
            case LegCurrency:
                legCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case LegSwapType:
                legSwapType = LegSwapType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case LegSettlType:
                legSettlType = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [InstrmtLegSecListGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
        b.append("{InstrmtLegSecListGroup=");
        printTagValue(b, instrumentLeg);
        printTagValue(b, TagNum.LegCurrency, legCurrency);
        printTagValue(b, TagNum.LegSwapType, legSwapType);
        printTagValue(b, TagNum.LegSettlType, legSettlType);
        printTagValue(b, legStipulations);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
