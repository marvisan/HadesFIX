/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvertisementMsg42.java
 *
 * $Id: AdvertisementMsg42.java,v 1.11 2010-08-25 05:30:24 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

import net.hades.fix.message.AdvertisementMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixNumberAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;
import net.hades.fix.message.xml.codec.jaxb.type.AdvSideType;
import net.hades.fix.message.xml.codec.jaxb.type.AdvTransTypeType;
import net.hades.fix.message.xml.codec.jaxb.type.InstrumentType;

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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v41.AdvertisementMsg41.LastMktType;
import net.hades.fix.message.type.AdvSide;
import net.hades.fix.message.type.AdvTransType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.PutOrCall;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.type.CurrencyType;

/**
 * Advertisement messages are used to announce completed transactions. The advertisement message 
 * can be transmitted in various transaction types; NEW, CANCEL and REPLACE. All message types 
 * other than NEW modify the state of a previously transmitted advertisement identified in AdvRefID.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 25/10/2008, 18:25:47
 */
@XmlRootElement(name="Advertisement")
@XmlType(propOrder = {"advID", "advTransTypeType", "instrumentType", "advSideType",
    "quantity", "price", "currencyType", "tradeDate", "transactTime", "text", "encodedTextLen", "encodedText",
    "urlLink", "lastMktType", "tradingSessionID"})
@XmlAccessorType(XmlAccessType.NONE)
public class AdvertisementMsg42 extends AdvertisementMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">//Constants

    private static final long serialVersionUID = -1264014069766369511L;

    protected static final Set<Integer> TAGS_42 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.AdvID.getValue(),
        TagNum.AdvTransType.getValue(),
        TagNum.AdvRefID.getValue(),
        TagNum.Symbol.getValue(),
        TagNum.SymbolSfx.getValue(),
        TagNum.SecurityID.getValue(),
        TagNum.SecurityIDSource.getValue(),
        TagNum.SecurityType.getValue(),
        TagNum.MaturityMonthYear.getValue(),
        TagNum.MaturityDay.getValue(),
        TagNum.PutOrCall.getValue(),
        TagNum.StrikePrice.getValue(),
        TagNum.OptAttribute.getValue(),
        TagNum.ContractMultiplier.getValue(),
        TagNum.CouponRate.getValue(),
        TagNum.SecurityExchange.getValue(),
        TagNum.Issuer.getValue(),
        TagNum.SecurityDesc.getValue(),
        TagNum.AdvSide.getValue(),
        TagNum.Quantity.getValue(),
        TagNum.QtyType.getValue(),
        TagNum.Price.getValue(),
        TagNum.Currency.getValue(),
        TagNum.TradeDate.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.Text.getValue(),
        TagNum.URLLink.getValue(),
        TagNum.LastMkt.getValue(),
        TagNum.TradingSessionID.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS_42 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedIssuerLen.getValue(),
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_42);
        ALL_TAGS.addAll(START_DATA_TAGS_42);
    }

    // </editor-fold>

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">//Attributes
    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    @XmlElement(name="AdvTransType", required=true)
    private AdvTransTypeType advTransTypeType = new AdvTransTypeType();

    @XmlElement(name="Instrument", required=true)
    private InstrumentType instrumentType = new InstrumentType();

    @XmlElement(name="AdvSide", required=true)
    private AdvSideType advSideType = new AdvSideType();

    @XmlElement(name="Currency")
    private CurrencyType currencyType;

    @XmlElement(name="LastMkt")
    private LastMktType lastMktType;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public AdvertisementMsg42() {
        super();
    }

    public AdvertisementMsg42(Header header,ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public AdvertisementMsg42(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public AdvertisementMsg42(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">//Public Methods
    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_42;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        AdvertisementMsg42 fixml = (AdvertisementMsg42) fragment;
        if (fixml.getAdvID() != null) {
            advID = fixml.getAdvID();
        }
        if (fixml.getAdvTransTypeType().getValue() != null) {
            advTransType = fixml.getAdvTransTypeType().getValue();
        }
        if (fixml.getAdvTransTypeType().getElement().getAdvRefID() != null) {
            advRefID = fixml.getAdvTransTypeType().getElement().getAdvRefID();
        }
        if (fixml.getInstrumentType().getSymbol() != null) {
            symbol = fixml.getInstrumentType().getSymbol();
        }
        if (fixml.getInstrumentType().getSymbolSfx() != null) {
            symbolSfx = fixml.getInstrumentType().getSymbolSfx();
        }
        if (fixml.getInstrumentType().getSecurityID() != null) {
            securityID = fixml.getInstrumentType().getSecurityID();
        }
        if (fixml.getInstrumentType().getIdSource() != null) {
            securityIDSource = fixml.getInstrumentType().getIdSource();
        }
        if (fixml.getInstrumentType().getSecurityType() != null) {
            securityType = fixml.getInstrumentType().getSecurityType();
        }
        if (fixml.getInstrumentType().getPutOrCall() != null) {
            putOrCall = fixml.getInstrumentType().getPutOrCall();
        }
        if (fixml.getInstrumentType().getMaturityMonthYear() != null) {
            maturityMonthYear = fixml.getInstrumentType().getMaturityMonthYear();
        }
        if (fixml.getInstrumentType().getMaturityDay() != null) {
            maturityDay = fixml.getInstrumentType().getMaturityDay();
        }
        if (fixml.getInstrumentType().getOptAttribute() != null) {
            optAttribute = fixml.getInstrumentType().getOptAttribute();
        }
        if (fixml.getInstrumentType().getContractMultiplier() != null) {
            contractMultiplier = fixml.getInstrumentType().getContractMultiplier();
        }
        if (fixml.getInstrumentType().getCouponRate() != null) {
            couponRate = fixml.getInstrumentType().getCouponRate();
        }
        if (fixml.getInstrumentType().getStrikePrice() != null) {
            strikePrice = fixml.getInstrumentType().getStrikePrice();
        }
        if (fixml.getInstrumentType().getSecurityExchange() != null) {
            securityExchange = fixml.getInstrumentType().getSecurityExchange();
        }
        if (fixml.getInstrumentType().getIssuer() != null) {
            issuer = fixml.getInstrumentType().getIssuer();
        }
        if (fixml.getInstrumentType().getEncodedIssuerLen() != null) {
            encodedIssuerLen = fixml.getInstrumentType().getEncodedIssuerLen();
        }
        if (fixml.getInstrumentType().getEncodedIssuer() != null) {
            encodedIssuer = fixml.getInstrumentType().getEncodedIssuer();
        }
        if (fixml.getInstrumentType().getSecurityDesc() != null) {
            securityDesc = fixml.getInstrumentType().getSecurityDesc();
        }
        if (fixml.getAdvSideType().getValue() != null) {
            advSide = fixml.getAdvSideType().getValue();
        }
        if (fixml.getQuantity() != null) {
            quantity = fixml.getQuantity();
        }
        if (fixml.getPrice() != null) {
            price = fixml.getPrice();
        }
        if (fixml.getCurrencyType() != null && fixml.getCurrencyType().getValue() != null) {
            currency = fixml.getCurrencyType().getValue();
        }
        if (fixml.getTradeDate() != null) {
            tradeDate = fixml.getTradeDate();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
        }
        if (fixml.getEncodedText() != null) {
            encodedText = fixml.getEncodedText();
        }
        if (fixml.getUrlLink() != null) {
            urlLink = fixml.getUrlLink();
        }
        if (fixml.getLastMktType() != null && fixml.getLastMktType().getValue() != null) {
            lastMkt = fixml.getLastMktType().getValue();
        }
        if (fixml.getTradingSessionID() != null) {
            tradingSessionID = fixml.getTradingSessionID();
        }
    }
    
    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlElement(name="AdvID", required=true)
    @Override
    public String getAdvID() {
        return advID;
    }

    @Override
    public void setAdvID(String advID) {
        this.advID = advID;
    }

    @Override
    public String getAdvRefID() {
        return advRefID;
    }

    @Override
    public void setAdvRefID(String advRefID) {
        this.advRefID = advRefID;
        advTransTypeType.setValue(advTransType, advRefID);
    }

    @Override
    public AdvSide getAdvSide() {
        return advSide;
    }

    @Override
    public void setAdvSide(AdvSide advSide) {
        this.advSide = advSide;
        advSideType.setValue(advSide);
    }

    @Override
    public AdvTransType getAdvTransType() {
        return advTransType;
    }

    @Override
    public void setAdvTransType(AdvTransType advTransType) {
        this.advTransType = advTransType;
        advTransTypeType.setValue(advTransType, advRefID);
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
        if (currencyType == null) {
            currencyType = new CurrencyType();
        }
        currencyType.setValue(currency);
    }

    @Override
    public String getIssuer() {
        return issuer;
    }

    @Override
    public void setIssuer(String issuer) {
        this.issuer = issuer;
        instrumentType.setIssuer(issuer);
    }

    @XmlElement(name="Price")
    @XmlJavaTypeAdapter(FixNumberAdapter.class)
    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @XmlElement(name="Shares", required = true)
    @XmlJavaTypeAdapter(FixNumberAdapter.class)
    @Override
    public Double getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getSecurityDesc() {
        return securityDesc;
    }

    @Override
    public void setSecurityDesc(String securityDesc) {
        this.securityDesc = securityDesc;
        instrumentType.setSecurityDesc(securityDesc);
    }

    @Override
    public String getSecurityID() {
        return securityID;
    }

    @Override
    public void setSecurityID(String securityID) {
        this.securityID = securityID;
        instrumentType.setSecurityID(securityID);
    }

    @Override
    public String getSecurityIDSource() {
        return securityIDSource;
    }

    @Override
    public void setSecurityIDSource(String securityIDSource) {
        this.securityIDSource = securityIDSource;
        instrumentType.setIdSource(securityIDSource);
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public void setSymbol(String symbol) {
        this.symbol = symbol;
        instrumentType.setSymbol(symbol);
    }

    @Override
    public String getSymbolSfx() {
        return symbolSfx;
    }

    @Override
    public void setSymbolSfx(String symbolSfx) {
        this.symbolSfx = symbolSfx;
        instrumentType.setSymbolSfx(symbolSfx);
    }

    @XmlElement(name="Text")
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @XmlElement(name="TransactTime")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }
    
    @Override
    public Character getOptAttribute() {
        return optAttribute;
    }

    @Override
    public void setOptAttribute(Character optAttribute) {
        this.optAttribute = optAttribute;
        instrumentType.setOptAttribute(optAttribute);
    }

    @Override
    public String getLastMkt() {
        return lastMkt;
    }

    @Override
    public void setLastMkt(String lastMkt) {
        this.lastMkt = lastMkt;
        if (lastMktType == null) {
            lastMktType = new LastMktType();
        }
        lastMktType.setValue(lastMkt);
    }

    @Override
    public Integer getMaturityDay() {
        return maturityDay;
    }

    @Override
    public void setMaturityDay(Integer maturityDay) {
        this.maturityDay = maturityDay;
        instrumentType.setMaturityDay(maturityDay);
    }

    @Override
    public String getMaturityMonthYear() {
        return maturityMonthYear;
    }

    @Override
    public void setMaturityMonthYear(String maturityMonthYear) {
        this.maturityMonthYear = maturityMonthYear;
        instrumentType.setMaturityMonthYear(maturityMonthYear);
    }

    @Override
    public PutOrCall getPutOrCall() {
        return putOrCall;
    }

    @Override
    public void setPutOrCall(PutOrCall putOrCall) {
        this.putOrCall = putOrCall;
        instrumentType.setPutOrCall(putOrCall);
    }

    @Override
    public String getSecurityExchange() {
        return securityExchange;
    }

    @Override
    public void setSecurityExchange(String securityExchange) {
        this.securityExchange = securityExchange;
        instrumentType.setSecurityExchange(securityExchange);
    }

    @Override
    public String getSecurityType() {
        return securityType;
    }

    @Override
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
        instrumentType.setSecurityType(securityType);
    }

    @Override
    public Double getStrikePrice() {
        return strikePrice;
    }

    @Override
    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
        instrumentType.setStrikePrice(strikePrice);
    }

    @XmlElement(name="TradeDate")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getTradeDate() {
        return tradeDate;
    }

    @Override
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    @XmlElement(name="URLLink")
    @Override
    public String getUrlLink() {
        return urlLink;
    }

    @Override
    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    @Override
    public Double getContractMultiplier() {
        return contractMultiplier;
    }

    @Override
    public void setContractMultiplier(Double contractMultiplier) {
        this.contractMultiplier = contractMultiplier;
        instrumentType.setContractMultiplier(contractMultiplier);
    }

    @Override
    public Double getCouponRate() {
        return couponRate;
    }

    @Override
    public void setCouponRate(Double couponRate) {
        this.couponRate = couponRate;
        instrumentType.setCouponRate(couponRate);
    }

    @Override
    public byte[] getEncodedIssuer() {
        return encodedIssuer;
    }

    @Override
    public void setEncodedIssuer(byte[] encodedIssuer) {
        this.encodedIssuer = encodedIssuer;
        if (encodedIssuerLen == null) {
            encodedIssuerLen = new Integer(encodedIssuer.length);
        }
        instrumentType.setEncodedIssuer(encodedIssuer);
    }

    @Override
    public Integer getEncodedIssuerLen() {
        return encodedIssuerLen;
    }

    @Override
    public void setEncodedIssuerLen(Integer encodedIssuerLen) {
        this.encodedIssuerLen = encodedIssuerLen;
        instrumentType.setEncodedIssuerLen(encodedIssuerLen);
    }

    @XmlElement(name="EncodedText")
    @Override
    public byte[] getEncodedText() {
        return encodedText;
    }

    @Override
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

    @XmlElement(name="EncodedTextLen")
    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    @XmlElement(name="TrdSesID")
    @Override
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    public AdvTransTypeType getAdvTransTypeType() {
        return advTransTypeType;
    }

    public void setAdvTransTypeType(AdvTransTypeType advTransTypeType) {
        this.advTransTypeType = advTransTypeType;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }
    
    public AdvSideType getAdvSideType() {
        return advSideType;
    }

    public void setAdvSideType(AdvSideType advSideType) {
        this.advSideType = advSideType;
    }
    
    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }
    
    public LastMktType getLastMktType() {
        return lastMktType;
    }

    public void setLastMktType(LastMktType lastMktType) {
        this.lastMktType = lastMktType;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">//Protected Methods
    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (advID == null || advID.trim().isEmpty()) {
            errorMsg.append(" [AdvID]");
            hasMissingTag = true;
        }
        if (advTransType == null) {
            errorMsg.append(" [AdvTransType]");
            hasMissingTag = true;
        }
        if (symbol == null || symbol.trim().isEmpty()) {
            errorMsg.append(" [Symbol]");
            hasMissingTag = true;
        }
        if (advSide == null) {
            errorMsg.append(" [AdvSide]");
            hasMissingTag = true;
        }
        if (quantity == null) {
            errorMsg.append(" [Quantity]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.AdvID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AdvID, advID);
            }
            if (MsgUtil.isTagInList(TagNum.AdvTransType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AdvTransType, advTransType.getValue());
            }
            if (advRefID != null && !advRefID.trim().isEmpty() && MsgUtil.isTagInList(TagNum.AdvRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AdvRefID, advRefID);
            }
            if (MsgUtil.isTagInList(TagNum.Symbol, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Symbol, symbol);
            }
            if (MsgUtil.isTagInList(TagNum.SymbolSfx, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SymbolSfx, symbolSfx);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityID, securityID);
            }
            if (securityIDSource != null && !securityIDSource.trim().isEmpty() && MsgUtil.isTagInList(TagNum.SecurityIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityIDSource, securityIDSource);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            }
            if (MsgUtil.isTagInList(TagNum.MaturityMonthYear, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MaturityMonthYear, maturityMonthYear);
            }
            if (MsgUtil.isTagInList(TagNum.MaturityDay, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MaturityDay, maturityDay);
            }
            if (putOrCall != null && !MsgUtil.isTagInList(TagNum.PutOrCall, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PutOrCall, putOrCall.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.StrikePrice, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StrikePrice, strikePrice);
            }
            if (MsgUtil.isTagInList(TagNum.OptAttribute, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OptAttribute, optAttribute);
            }
             if (MsgUtil.isTagInList(TagNum.ContractMultiplier, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ContractMultiplier, contractMultiplier);
            }
            if (MsgUtil.isTagInList(TagNum.CouponRate, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.CouponRate, couponRate);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityExchange, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityExchange, securityExchange);
            }
            if (MsgUtil.isTagInList(TagNum.Issuer, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Issuer, issuer);
            }
            if (encodedIssuerLen != null && encodedIssuerLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedIssuerLen, SECURED_TAGS, secured)) {
                if (encodedIssuer != null && encodedIssuer.length > 0) {
                    encodedIssuerLen = new Integer(encodedIssuer.length);
                    TagEncoder.encode(bao, TagNum.EncodedIssuerLen, encodedIssuerLen);
                    TagEncoder.encode(bao, TagNum.EncodedIssuer, encodedIssuer);
                }
            }
            if (MsgUtil.isTagInList(TagNum.SecurityDesc, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityDesc, securityDesc, sessionCharset);
            }
            if (MsgUtil.isTagInList(TagNum.AdvSide, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AdvSide, advSide.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Quantity, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Quantity, quantity);
            }
            if (MsgUtil.isTagInList(TagNum.Price, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Price, price);
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TradeDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.TradeDate, tradeDate);
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text, sessionCharset);
            }
            if (encodedTextLen != null && encodedTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedTextLen, SECURED_TAGS, secured)) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (MsgUtil.isTagInList(TagNum.URLLink, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.URLLink, urlLink);
            }
            if (MsgUtil.isTagInList(TagNum.LastMkt, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastMkt, lastMkt);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedIssuerLen.getValue()) {
            try {
                encodedIssuerLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedIssuerLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(), TagNum.EncodedIssuerLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedIssuerLen.intValue());
            encodedIssuer = dataTag.value;
        }
        if (tag.tagNum == TagNum.EncodedTextLen.getValue()) {
            try {
                encodedTextLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedTextLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(), TagNum.EncodedTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedTextLen.intValue());
            encodedText = dataTag.value;
        }
        
        return result;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [AdvertismentMsg] message version [4.2].";
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS_42;
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
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">//Package Methods
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">//Private Methods
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">//Inner Classes
    // </editor-fold>
}
