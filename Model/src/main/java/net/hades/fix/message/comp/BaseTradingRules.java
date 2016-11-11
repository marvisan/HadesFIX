/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BaseTradingRules.java
 *
 * $Id: BaseTradingRules.java,v 1.1 2011-04-19 12:13:39 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.group.TickRuleGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ImpliedMarketIndicator;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.SessionRejectReason;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.LotTypeRuleGroup;
import net.hades.fix.message.type.ExpirationCycle;
import net.hades.fix.message.type.MultilegModel;
import net.hades.fix.message.type.MultilegPriceMethod;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Base trading rules component.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 07/12/2008, 12:48:37 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class BaseTradingRules extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoTickRules.getValue(),
        TagNum.NoLotTypeRules.getValue(),
        TagNum.ExpirationCycle.getValue(),
        TagNum.MinTradeVol.getValue(),
        TagNum.MaxTradeVol.getValue(),
        TagNum.MaxPriceVariation.getValue(),
        TagNum.ImpliedMarketIndicator.getValue(),
        TagNum.TradingCurrency.getValue(),
        TagNum.RoundLot.getValue(),
        TagNum.MultilegModel.getValue(),
        TagNum.MultilegPriceMethod.getValue(),
        TagNum.PriceType.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NoTickRules.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1205. Starting with 5.0SP1 version.
     */
    protected Integer noTickRules;

    /**
     * Starting with 5.0SP1 version.
     */
    protected TickRuleGroup[] tickRuleGroups;

    /**
     * TagNum = 1234. Starting with 5.0SP1 version.
     */
    protected Integer noLotTypeRules;

    /**
     * Starting with 5.0SP1 version.
     */
    protected LotTypeRuleGroup[] lotTypeRuleGroups;

    /**
     * Starting with 5.0SP1 version.
     */
    protected PriceLimits priceLimits;

    /**
     * TagNum = 827. Starting with 5.0SP1 version.
     */
    protected ExpirationCycle expirationCycle;

    /**
     * TagNum = 562. Starting with 5.0SP1 version.
     */
    protected Double minTradeVol;

    /**
     * TagNum = 1140. Starting with 5.0SP1 version.
     */
    protected Double maxTradeVol;

    /**
     * TagNum = 1143. Starting with 5.0SP1 version.
     */
    protected Double maxPriceVariation;

    /**
     * TagNum = 1144. Starting with 5.0SP1 version.
     */
    protected ImpliedMarketIndicator impliedMarketIndicator;

    /**
     * TagNum = 1245. Starting with 5.0SP1 version.
     */
    protected Currency tradingCurrency;

    /**
     * TagNum = 561. Starting with 5.0SP1 version.
     */
    protected Double roundLot;

    /**
     * TagNum = 1377. Starting with 5.0SP1 version.
     */
    protected MultilegModel multilegModel;

    /**
     * TagNum = 1378. Starting with 5.0SP1 version.
     */
    protected MultilegPriceMethod multilegPriceMethod;

    /**
     * TagNum = 423. Starting with 5.0SP1 version.
     */
    protected PriceType priceType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public BaseTradingRules() {
        super();
    }

    public BaseTradingRules(FragmentContext context) {
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
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoTickRules)
    public Integer getNoTickRules() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link TickRuleGroup} groups. It will also create an array
     * of {@link TickRuleGroup} objects and set the <code>tickRuleGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>tickRuleGroups</code> array they will be discarded.<br/>
     * @param noTickRules field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoTickRules)
    public void setNoTickRules(Integer noTickRules) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link TickRuleGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public TickRuleGroup[] getTickRuleGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link TickRuleGroup} object to the existing array of <code>tickRuleGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noTickRules</code> field to the proper value.<br/>
     * Note: If the <code>setNoTickRules</code> method has been called there will already be a number of objects in the
     * <code>noTickRules</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public TickRuleGroup addTickRuleGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link TickRuleGroup} object from the existing array of <code>tickRuleGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noTickRules</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public TickRuleGroup deleteTickRuleGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link TickRuleGroup} objects from the <code>tickRuleGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noTickRules</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearTickRuleGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoLotTypeRules)
    public Integer getNoLotTypeRules() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link LotTypeRuleGroup} groups. It will also create an array
     * of {@link LotTypeRuleGroup} objects and set the <code>lotTypeRuleGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>lotTypeRuleGroups</code> array they will be discarded.<br/>
     * @param noLotTypeRules field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoLotTypeRules)
    public void setNoLotTypeRules(Integer noLotTypeRules) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link LotTypeRuleGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0SP1")
    public LotTypeRuleGroup[] getLotTypeRuleGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link LotTypeRuleGroup} object to the existing array of <code>lotTypeRuleGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noLotTypeRules</code> field to the proper value.<br/>
     * Note: If the <code>setNoTickRules</code> method has been called there will already be a number of objects in the
     * <code>noLotTypeRules</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public LotTypeRuleGroup addLotTypeRuleGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link LotTypeRuleGroup} object from the existing array of <code>lotTypeRuleGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noLotTypeRules</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public LotTypeRuleGroup deleteLotTypeRuleGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link LotTypeRuleGroup} objects from the <code>lotTypeRuleGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noLotTypeRules</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearLotTypeRuleGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    public PriceLimits getPriceLimits() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the component to the proper implementation class.
     */
    @FIXVersion(introduced="5.0SP1")
    public void setPriceLimits() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the <code>PriceLimits</code> field to null.
     */
    @FIXVersion(introduced="5.0SP1")
    public void clearPriceLimits() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ExpirationCycle)
    public ExpirationCycle getExpirationCycle() {
        return expirationCycle;
    }

    /**
     * Message field setter.
     * @param expirationCycle field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ExpirationCycle)
    public void setExpirationCycle(ExpirationCycle expirationCycle) {
        this.expirationCycle = expirationCycle;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MinTradeVol)
    public Double getMinTradeVol() {
        return minTradeVol;
    }

    /**
     * Message field setter.
     * @param minTradeVol field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MinTradeVol)
    public void setMinTradeVol(Double minTradeVol) {
        this.minTradeVol = minTradeVol;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MaxTradeVol)
    public Double getMaxTradeVol() {
        return maxTradeVol;
    }

    /**
     * Message field setter.
     * @param maxTradeVol field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MaxTradeVol)
    public void setMaxTradeVol(Double maxTradeVol) {
        this.maxTradeVol = maxTradeVol;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MaxPriceVariation)
    public Double getMaxPriceVariation() {
        return maxPriceVariation;
    }

    /**
     * Message field setter.
     * @param maxPriceVariation field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MaxPriceVariation)
    public void setMaxPriceVariation(Double maxPriceVariation) {
        this.maxPriceVariation = maxPriceVariation;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ImpliedMarketIndicator)
    public ImpliedMarketIndicator getImpliedMarketIndicator() {
        return impliedMarketIndicator;
    }

    /**
     * Message field setter.
     * @param impliedMarketIndicator field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.ImpliedMarketIndicator)
    public void setImpliedMarketIndicator(ImpliedMarketIndicator impliedMarketIndicator) {
        this.impliedMarketIndicator = impliedMarketIndicator;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.TradingCurrency)
    public Currency getTradingCurrency() {
        return tradingCurrency;
    }

    /**
     * Message field setter.
     * @param tradingCurrency field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.TradingCurrency)
    public void setTradingCurrency(Currency tradingCurrency) {
        this.tradingCurrency = tradingCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.RoundLot)
    public Double getRoundLot() {
        return roundLot;
    }

    /**
     * Message field setter.
     * @param roundLot field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.RoundLot)
    public void setRoundLot(Double roundLot) {
        this.roundLot = roundLot;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MultilegModel)
    public MultilegModel getMultilegModel() {
        return multilegModel;
    }

    /**
     * Message field setter.
     * @param multilegModel field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MultilegModel)
    public void setMultilegModel(MultilegModel multilegModel) {
        this.multilegModel = multilegModel;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MultilegPriceMethod)
    public MultilegPriceMethod getMultilegPriceMethod() {
        return multilegPriceMethod;
    }

    /**
     * Message field setter.
     * @param multilegPriceMethod field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.MultilegPriceMethod)
    public void setMultilegPriceMethod(MultilegPriceMethod multilegPriceMethod) {
        this.multilegPriceMethod = multilegPriceMethod;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.PriceType)
    public PriceType getPriceType() {
        return priceType;
    }

    /**
     * Message field setter.
     * @param priceType field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.PriceType)
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.NoTickRules.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (noTickRules == null) {
            errorMsg.append(" [NoTickRules]");
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
            if (noTickRules != null && tickRuleGroups != null && tickRuleGroups.length == noTickRules.intValue()) {
                TagEncoder.encode(bao, TagNum.NoTickRules, noTickRules);
                for (int i = 0; i < noTickRules.intValue(); i++) {
                    if (tickRuleGroups[i] != null) {
                        bao.write(tickRuleGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "TickRuleGroups field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoTickRules.getValue(), error);
            }
            if (noLotTypeRules != null && lotTypeRuleGroups != null && lotTypeRuleGroups.length == noLotTypeRules.intValue()) {
                TagEncoder.encode(bao, TagNum.NoLotTypeRules, noLotTypeRules);
                for (int i = 0; i < noLotTypeRules.intValue(); i++) {
                    if (lotTypeRuleGroups[i] != null) {
                        bao.write(lotTypeRuleGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "LotTypeRuleGroups field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoLotTypeRules.getValue(), error);
            }
            if (priceLimits != null) {
                bao.write(priceLimits.encode(MsgSecureType.ALL_FIELDS));
            }
            if (expirationCycle != null) {
                TagEncoder.encode(bao, TagNum.ExpirationCycle, expirationCycle.getValue());
            }
            TagEncoder.encode(bao, TagNum.MinTradeVol, minTradeVol);
            TagEncoder.encode(bao, TagNum.MaxTradeVol, maxTradeVol);
            TagEncoder.encode(bao, TagNum.MaxPriceVariation, maxPriceVariation);
            if (impliedMarketIndicator != null) {
                TagEncoder.encode(bao, TagNum.ImpliedMarketIndicator, impliedMarketIndicator.getValue());
            }
            if (tradingCurrency != null) {
                TagEncoder.encode(bao, TagNum.TradingCurrency, tradingCurrency.getValue());
            }
            TagEncoder.encode(bao, TagNum.RoundLot, roundLot);
            if (multilegModel != null) {
                TagEncoder.encode(bao, TagNum.MultilegModel, multilegModel.getValue());
            }
            if (multilegPriceMethod != null) {
                TagEncoder.encode(bao, TagNum.MultilegPriceMethod, multilegPriceMethod.getValue());
            }
            if (priceType != null) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
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
            case NoTickRules:
                noTickRules = new Integer(new String(tag.value, sessionCharset));
                break;

            case NoLotTypeRules:
                noLotTypeRules = new Integer(new String(tag.value, sessionCharset));
                break;

            case ExpirationCycle:
                expirationCycle = ExpirationCycle.valueFor(new Integer(new String(tag.value, sessionCharset)));
                break;

            case MinTradeVol:
                minTradeVol = new Double(new String(tag.value, sessionCharset));
                break;

            case MaxTradeVol:
                maxTradeVol = new Double(new String(tag.value, sessionCharset));
                break;

            case MaxPriceVariation:
                maxPriceVariation = new Double(new String(tag.value, sessionCharset));
                break;

            case ImpliedMarketIndicator:
                impliedMarketIndicator = ImpliedMarketIndicator.valueFor(new Integer(new String(tag.value, sessionCharset)));
                break;

            case TradingCurrency:
                tradingCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case RoundLot:
                roundLot = new Double(new String(tag.value, sessionCharset));
                break;

            case MultilegModel:
                multilegModel = MultilegModel.valueFor(new Integer(new String(tag.value, sessionCharset)));
                break;

            case MultilegPriceMethod:
                multilegPriceMethod = MultilegPriceMethod.valueFor(new Integer(new String(tag.value, sessionCharset)));
                break;

            case PriceType:
                priceType = PriceType.valueFor(new Integer(new String(tag.value, sessionCharset)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [BaseTradingRules] fields.";
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
        b.append("{BaseTradingRules=");
        printTagValue(b, TagNum.NoTickRules, noTickRules);
        printTagValue(b, tickRuleGroups);
        printTagValue(b, TagNum.NoLotTypeRules, noLotTypeRules);
        printTagValue(b, lotTypeRuleGroups);
        printTagValue(b, priceLimits);
        printTagValue(b, TagNum.ExpirationCycle, expirationCycle);
        printTagValue(b, TagNum.MinTradeVol, minTradeVol);
        printTagValue(b, TagNum.MaxTradeVol, maxTradeVol);
        printTagValue(b, TagNum.MaxPriceVariation, maxPriceVariation);
        printTagValue(b, TagNum.ImpliedMarketIndicator, impliedMarketIndicator);
        printTagValue(b, TagNum.TradingCurrency, tradingCurrency);
        printTagValue(b, TagNum.RoundLot, roundLot);
        printTagValue(b, TagNum.MultilegModel, multilegModel);
        printTagValue(b, TagNum.MultilegPriceMethod, multilegPriceMethod);
        printTagValue(b, TagNum.PriceType, priceType);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
