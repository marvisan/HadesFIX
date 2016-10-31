/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegOrdGroup.java
 *
 * $Id: LegOrdGroup.java,v 1.3 2011-09-09 08:05:14 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.LegStipulations;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.DateConverter;

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

import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.type.CoveredOrUncovered;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.LegSwapType;
import net.hades.fix.message.type.PositionEffect;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Leg order group message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 29/04/2009, 6:41:55 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class LegOrdGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.LegQty.getValue(),
        TagNum.LegSwapType.getValue(),
        TagNum.LegAllocID.getValue(),
        TagNum.NoLegAllocs.getValue(),
        TagNum.LegPositionEffect.getValue(),
        TagNum.LegCoveredOrUncovered.getValue(),
        TagNum.LegRefID.getValue(),
        TagNum.LegSettlType.getValue(),
        TagNum.LegSettlDate.getValue(),
        TagNum.LegSettlCurrency.getValue(),
        TagNum.LegOrderQty.getValue(),
        TagNum.LegVolatility.getValue(),
        TagNum.LegDividendYield.getValue(),
        TagNum.LegCurrencyRatio.getValue(),
        TagNum.LegExecInst.getValue()
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
     * TagNum = 1017. Starting with 5.0 version.
     */
    protected Double legOptionRatio;

    /**
     * TagNum = 687. Starting with 4.4 version.
     */
    protected Double legQty;

    /**
     * TagNum = 690. Starting with 4.4 version.
     */
    protected LegSwapType legSwapType;

    /**
     * Starting with 4.4 version.
     */
    protected LegStipulations legStipulations;

    /**
     * TagNum = 1366. Starting with 5.0SP2 version.
     */
    protected String legAllocID;

    /**
     * TagNum = 670. Starting with 4.4 version.
     */
    protected Integer noLegAllocs;

    /**
     * Starting with 4.4 version.
     */
    protected LegAllocGroup[] legAllocGroups;

    /**
     * TagNum = 564. Starting with 4.3 version.
     */
    protected PositionEffect legPositionEffect;

    /**
     * TagNum = 565. Starting with 4.3 version.
     */
    protected CoveredOrUncovered legCoveredOrUncovered;

    /**
     * Starting with 4.3 version.
     */
    protected NestedParties nestedParties;

    /**
     * TagNum = 654. Starting with 4.3 version.
     */
    protected String legRefID;

    /**
     * TagNum = 566. Starting with 4.3 version.
     */
    protected Double legPrice;

    /**
     * TagNum = 587. Starting with 4.3 version.
     */
    protected String legSettlType;

    /**
     * TagNum = 588. Starting with 4.3 version.
     */
    protected Date legSettlDate;

    /**
     * TagNum = 675. Starting with 5.0 version.
     */
    protected Currency legSettlCurrency;

    /**
     * TagNum = 685. Starting with 5.0 version.
     */
    protected Double legOrderQty;

    /**
     * TagNum = 1379. Starting with 5.0SP1 version.
     */
    protected Double legVolatility;

    /**
     * TagNum = 1381. Starting with 5.0SP1 version.
     */
    protected Double legDividendYield;

    /**
     * TagNum = 1383. Starting with 5.0SP1 version.
     */
    protected Double legCurrencyRatio;

    /**
     * TagNum = 1384. Starting with 5.0SP1 version.
     */
    protected String legExecInst;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public LegOrdGroup() {
    }

    public LegOrdGroup(FragmentContext context) {
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
    @FIXVersion(introduced = "5.0", retired = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegOptionRatio)
    public Double getLegOptionRatio() {
        return legOptionRatio;
    }

    /**
     * Message field setter.
     * @param legOptionRatio field value
     */
    @FIXVersion(introduced = "5.0", retired = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegOptionRatio)
    public void setLegOptionRatio(Double legOptionRatio) {
        this.legOptionRatio = legOptionRatio;
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
    @TagNumRef(tagNum = TagNum.LegAllocID)
    public String getLegAllocID() {
        return legAllocID;
    }

    /**
     * Message field setter.
     * @param legAllocID field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.LegAllocID)
    public void setLegAllocID(String legAllocID) {
        this.legAllocID = legAllocID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoLegAllocs)
    public Integer getNoLegAllocs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link LegAllocGroup} components. It will also create an array
     * of {@link LegAllocGroup} objects and set the <code>legAllocGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>legAllocGroups</code> array they will be discarded.<br/>
     * @param noLegAllocs field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoLegAllocs)
    public void setNoLegAllocs(Integer noLegAllocs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link LegAllocGroup} array of groups.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public LegAllocGroup[] getLegAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link LegAllocGroup} object to the existing array of <code>legAllocGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLegAllocs</code> field to the proper value.<br/>
     * Note: If the <code>setNoLegAllocs</code> method has been called there will already be a number of objects in the
     * <code>legAllocGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public LegAllocGroup addLegAllocGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link LegAllocGroup} object from the existing array of <code>legAllocGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLegAllocs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public LegAllocGroup deleteLegAllocGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link LegAllocGroup} objects from the <code>LegAllocGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLegAllocs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearLegAllocGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.LegPositionEffect)
    public PositionEffect getLegPositionEffect() {
        return legPositionEffect;
    }

    /**
     * Message field setter.
     * @param legPositionEffect field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.LegPositionEffect)
    public void setLegPositionEffect(PositionEffect legPositionEffect) {
        this.legPositionEffect = legPositionEffect;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.LegCoveredOrUncovered)
    public CoveredOrUncovered getLegCoveredOrUncovered() {
        return legCoveredOrUncovered;
    }

    /**
     * Message field setter.
     * @param legCoveredOrUncovered field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.LegCoveredOrUncovered)
    public void setLegCoveredOrUncovered(CoveredOrUncovered legCoveredOrUncovered) {
        this.legCoveredOrUncovered = legCoveredOrUncovered;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    public NestedParties getNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties} component if used in this message to the proper implementation.<br/>
     */
    @FIXVersion(introduced = "4.3")
    public void setNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the {@link NestedParties} component to null.<br/>
     */
    @FIXVersion(introduced = "4.3")
    public void clearNestedParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.LegRefID)
    public String getLegRefID() {
        return legRefID;
    }

    /**
     * Message field setter.
     * @param legRefID field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.LegRefID)
    public void setLegRefID(String legRefID) {
        this.legRefID = legRefID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3", retired = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegPrice)
    public Double getLegPrice() {
        return legPrice;
    }

    /**
     * Message field setter.
     * @param legPrice field value
     */
    @FIXVersion(introduced = "4.3", retired = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegPrice)
    public void setLegPrice(Double legPrice) {
        this.legPrice = legPrice;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.LegSettlType)
    public String getLegSettlType() {
        return legSettlType;
    }

    /**
     * Message field setter.
     * @param legSettlType field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.LegSettlType)
    public void setLegSettlType(String legSettlType) {
        this.legSettlType = legSettlType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.LegSettlDate)
    public Date getLegSettlDate() {
        return legSettlDate;
    }

    /**
     * Message field setter.
     * @param legSettlDate field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.LegSettlDate)
    public void setLegSettlDate(Date legSettlDate) {
        this.legSettlDate = legSettlDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.LegSettlCurrency)
    public Currency getLegSettlCurrency() {
        return legSettlCurrency;
    }

    /**
     * Message field setter.
     * @param legSettlCurrency field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.LegSettlCurrency)
    public void setLegSettlCurrency(Currency legSettlCurrency) {
        this.legSettlCurrency = legSettlCurrency;
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
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegVolatility)
    public Double getLegVolatility() {
        return legVolatility;
    }

    /**
     * Message field setter.
     * @param legVolatility field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegVolatility)
    public void setLegVolatility(Double legVolatility) {
        this.legVolatility = legVolatility;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegDividendYield)
    public Double getLegDividendYield() {
        return legDividendYield;
    }

    /**
     * Message field setter.
     * @param legDividendYield field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegDividendYield)
    public void setLegDividendYield(Double legDividendYield) {
        this.legDividendYield = legDividendYield;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegCurrencyRatio)
    public Double getLegCurrencyRatio() {
        return legCurrencyRatio;
    }

    /**
     * Message field setter.
     * @param legCurrencyRatio field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegCurrencyRatio)
    public void setLegCurrencyRatio(Double legCurrencyRatio) {
        this.legCurrencyRatio = legCurrencyRatio;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegExecInst)
    public String getLegExecInst() {
        return legExecInst;
    }

    /**
     * Message field setter.
     * @param legExecInst field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.LegExecInst)
    public void setLegExecInst(String legExecInst) {
        this.legExecInst = legExecInst;
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
            TagEncoder.encode(bao, TagNum.LegOptionRatio, legOptionRatio);
            TagEncoder.encode(bao, TagNum.LegQty, legQty);
            if (legSwapType != null) {
                TagEncoder.encode(bao, TagNum.LegSwapType, legSwapType.getValue());
            }
            if (legStipulations != null) {
                bao.write(legStipulations.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.LegAllocID, legAllocID);
            if (noLegAllocs != null) {
                TagEncoder.encode(bao, TagNum.NoLegAllocs, noLegAllocs);
                if (legAllocGroups != null && legAllocGroups.length == noLegAllocs.intValue()) {
                    for (int i = 0; i < noLegAllocs.intValue(); i++) {
                        if (legAllocGroups[i] != null) {
                            bao.write(legAllocGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "LegAllocGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoLegAllocs.getValue(), error);
                }
            }
            if (legPositionEffect != null) {
                TagEncoder.encode(bao, TagNum.LegPositionEffect, legPositionEffect.getValue());
            }
            if (legCoveredOrUncovered != null) {
                TagEncoder.encode(bao, TagNum.LegCoveredOrUncovered, legCoveredOrUncovered.getValue());
            }
            if (nestedParties != null) {
                bao.write(nestedParties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.LegRefID, legRefID);
            TagEncoder.encode(bao, TagNum.LegPrice, legPrice);
            TagEncoder.encode(bao, TagNum.LegSettlType, legSettlType);
            TagEncoder.encodeDate(bao, TagNum.LegSettlDate, legSettlDate);
            if (legSettlCurrency != null) {
                TagEncoder.encode(bao, TagNum.LegSettlCurrency, legSettlCurrency.getValue());
            }
            TagEncoder.encode(bao, TagNum.LegOrderQty, legOrderQty);
            TagEncoder.encode(bao, TagNum.LegVolatility, legVolatility);
            TagEncoder.encode(bao, TagNum.LegDividendYield, legDividendYield);
            TagEncoder.encode(bao, TagNum.LegCurrencyRatio, legCurrencyRatio);
            TagEncoder.encode(bao, TagNum.LegExecInst, legExecInst);
            
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
            case LegOptionRatio:
                legOptionRatio = new Double(new String(tag.value, sessionCharset));
                break;
                
            case LegQty:
                legQty = new Double(new String(tag.value, sessionCharset));
                break;

            case LegSwapType:
                legSwapType = LegSwapType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case LegAllocID:
                legAllocID = new String(tag.value, sessionCharset);
                break;

            case NoLegAllocs:
                noLegAllocs = Integer.valueOf(new String(tag.value, sessionCharset));
                break;
                
            case LegPositionEffect:
                legPositionEffect = PositionEffect.valueFor(new String(tag.value, sessionCharset));
                break;

            case LegCoveredOrUncovered:
                legCoveredOrUncovered = CoveredOrUncovered.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case LegRefID:
                legRefID = new String(tag.value, sessionCharset);
                break;

            case LegPrice:
                legPrice = new Double(new String(tag.value, sessionCharset));
                break;

            case LegSettlType:
                legSettlType = new String(tag.value, sessionCharset);
                break;

            case LegSettlDate:
                legSettlDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case LegSettlCurrency:
                legSettlCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case LegOrderQty:
                legOrderQty = new Double(new String(tag.value, sessionCharset));
                break;

            case LegVolatility:
                legVolatility = new Double(new String(tag.value, sessionCharset));
                break;

            case LegDividendYield:
                legDividendYield = new Double(new String(tag.value, sessionCharset));
                break;

            case LegCurrencyRatio:
                legCurrencyRatio = new Double(new String(tag.value, sessionCharset));
                break;

            case LegExecInst:
                legExecInst = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [LegOrdGroup] fields.";
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
        b.append("{LegOrdGroup=");
        printTagValue(b, instrumentLeg);
        printTagValue(b, TagNum.LegOptionRatio, legOptionRatio);
        printTagValue(b, TagNum.LegQty, legQty);
        printTagValue(b, TagNum.LegSwapType, legSwapType);
        printTagValue(b, legStipulations);
        printTagValue(b, TagNum.LegAllocID, legAllocID);
        printTagValue(b, TagNum.NoLegAllocs, noLegAllocs);
        printTagValue(b, legAllocGroups);
        printTagValue(b, TagNum.LegPositionEffect, legPositionEffect);
        printTagValue(b, TagNum.LegCoveredOrUncovered, legCoveredOrUncovered);
        printTagValue(b, nestedParties);
        printTagValue(b, TagNum.LegRefID, legRefID);
        printTagValue(b, TagNum.LegSettlType, legSettlType);
        printDateTagValue(b, TagNum.LegSettlDate, legSettlDate);
        printTagValue(b, TagNum.LegSettlCurrency, legSettlCurrency);
        printTagValue(b, TagNum.LegOrderQty, legOrderQty);
        printTagValue(b, TagNum.LegVolatility, legVolatility);
        printTagValue(b, TagNum.LegDividendYield, legDividendYield);
        printTagValue(b, TagNum.LegCurrencyRatio, legCurrencyRatio);
        printTagValue(b, TagNum.LegExecInst, legExecInst);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
