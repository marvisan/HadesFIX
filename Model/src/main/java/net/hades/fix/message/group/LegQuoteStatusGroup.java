/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegQuoteStatusGroup.java
 *
 * $Id: LegQuoteStatusGroup.java,v 1.10 2010-11-23 10:20:17 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.LegStipulations;
import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * LegQuoteStatus group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class LegQuoteStatusGroup extends Group {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.LegQty.getValue(),
        TagNum.LegOrderQty.getValue(),
        TagNum.LegSwapType.getValue(),
        TagNum.LegSettlType.getValue(),
        TagNum.LegSettlDate.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /**
     * Starting with 4.4 version.
     */
    protected InstrumentLeg instrumentLeg;

    /**
     * TagNum = 687. Starting with 4.4 version.
     */
    protected Double legQty;

    /**
     * TagNum = 685. Starting with 5.0 version.
     */
    protected Double legOrderQty;

    /**
     * TagNum = 690. Starting with 4.4 version.
     */
    protected LegSwapType legSwapType;

    /**
     * TagNum = 587. Starting with 4.4 version.
     */
    protected String legSettlType;

    /**
     * TagNum = 588. Starting with 4.4 version.
     */
    protected Date legSettlDate;

    /**
     * Starting with 4.4 version.
     */
    protected LegStipulations legStipulations;

    /**
     * Starting with 4.4 version.
     */
    protected NestedParties nestedParties;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public LegQuoteStatusGroup() {
    }
    
    public LegQuoteStatusGroup(FragmentContext context) {
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
     * Message field getter for {@link InstrumentLeg}.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public InstrumentLeg getInstrumentLeg() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link InstrumentLeg} component if used in this message to the proper implementation.<br/>
     */
    @FIXVersion(introduced = "4.4")
    public void setInstrumentLeg() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link InstrumentLeg} component to null.<br/>
     */
    @FIXVersion(introduced = "4.4")
    public void clearInstrumentLeg() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegQty)
    public Double getLegQty() {
        return legQty;
    }

    /**
     * Message field setter.
     * @param legQty field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegQty)
    public void setLegQty(Double legQty) {
        this.legQty = legQty;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.LegOrderQty)
    public Double getLegOrderQty() {
        return legOrderQty;
    }

    /**
     * Message field setter.
     * @param legOrderQty field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.LegOrderQty)
    public void setLegOrderQty(Double legOrderQty) {
        this.legOrderQty = legOrderQty;
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
    @TagNumRef(tagNum = TagNum.LegSettlDate)
    public Date getLegSettlDate() {
        return legSettlDate;
    }

    /**
     * Message field setter.
     * @param legSettlDate field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegSettlDate)
    public void setLegSettlDate(Date legSettlDate) {
        this.legSettlDate = legSettlDate;
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
    public NestedParties getNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties} component if used in this message to the proper implementation.<br/>
     */
    @FIXVersion(introduced = "4.4")
    public void setNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties} component to null.<br/>
     */
    @FIXVersion(introduced = "4.4")
    public void clearNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected int getFirstTag() {
        return TagNum.LegSymbol.getValue();
    }
    
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
        if (validateRequired) {             validateRequiredTags();         }
        
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            bao.write(instrumentLeg.encode(MsgSecureType.ALL_FIELDS));
            TagEncoder.encode(bao, TagNum.LegQty, legQty);
            TagEncoder.encode(bao, TagNum.LegOrderQty, legOrderQty);
            if (legSwapType != null) {
                TagEncoder.encode(bao, TagNum.LegSwapType, legSwapType.getValue());
            }
            TagEncoder.encode(bao, TagNum.LegSettlType, legSettlType);
            TagEncoder.encodeDate(bao, TagNum.LegSettlDate, legSettlDate);
            if (legStipulations != null) {
                bao.write(legStipulations.encode(MsgSecureType.ALL_FIELDS));
            }
            if (nestedParties != null) {
                bao.write(nestedParties.encode(MsgSecureType.ALL_FIELDS));
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
            case LegQty:
                legQty = new Double(new String(tag.value, sessionCharset));
                break;

            case LegOrderQty:
                legOrderQty = new Double(new String(tag.value, sessionCharset));
                break;

            case LegSwapType:
                legSwapType = LegSwapType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case LegSettlType:
                legSettlType = new String(tag.value, sessionCharset);
                break;

            case LegSettlDate:
                legSettlDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;
                
            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [LegQuoteStatusGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }
    
    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
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
        b.append("{LegQuoteStatusGroup=");
        printTagValue(b, instrumentLeg);
        printTagValue(b, TagNum.LegQty, legQty);
        printTagValue(b, TagNum.LegOrderQty, legOrderQty);
        printTagValue(b, TagNum.LegSwapType, legSwapType);
        printTagValue(b, TagNum.LegSettlType, legSettlType);
        printDateTagValue(b, TagNum.LegSettlDate, legSettlDate);
        printTagValue(b, legStipulations);
        printTagValue(b, nestedParties);
        b.append("}");
        
        return b.toString();
    }
    
    // </editor-fold>
    
}
