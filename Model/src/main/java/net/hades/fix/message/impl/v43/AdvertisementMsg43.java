/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvertisementMsg43.java
 *
 * $Id: AdvertisementMsg43.java,v 1.11 2011-04-14 23:44:33 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.AdvertisementMsg;
import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.Component;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v41.AdvertisementMsg41.LastMktType;
import net.hades.fix.message.type.AdvSide;
import net.hades.fix.message.type.AdvTransType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixNumberAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;
import net.hades.fix.message.xml.codec.jaxb.type.AdvSideType;
import net.hades.fix.message.xml.codec.jaxb.type.AdvTransTypeType;
import net.hades.fix.message.xml.codec.jaxb.type.CurrencyType;

/**
 * Advertisement messages are used to announce completed transactions. The advertisement message 
 * can be transmitted in various transaction types; NEW, CANCEL and REPLACE. All message types 
 * other than NEW modify the state of a previously transmitted advertisement identified in AdvRefID.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 26/10/2008, 11:21:12
 */
@XmlRootElement(name="Advertisement")
@XmlType(propOrder = {"advID", "advTransTypeType", "instrument", "advSideType",
    "quantity", "price", "currencyType", "tradeDate", "transactTime", "text", "encodedTextGroupType",
    "urlLink", "lastMktType", "tradingSessionID", "tradingSessionSubID"})
@XmlAccessorType(XmlAccessType.NONE)
public class AdvertisementMsg43 extends AdvertisementMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(INSTRUMENT_COMP_TAGS);
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    @XmlElement(name="AdvTransType", required=true)
    private AdvTransTypeType advTransTypeType = new AdvTransTypeType();

    @XmlElement(name="AdvSide", required=true)
    private AdvSideType advSideType = new AdvSideType();

    @XmlElement(name="Currency")
    private CurrencyType currencyType;

    @XmlElement(name="LastMkt")
    private LastMktType lastMktType;

    @XmlElement(name="EncodedTextGroup")
    private EncodedTextGroupType encodedTextGroupType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public AdvertisementMsg43() {
        super();
    }

    public AdvertisementMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        if (messageEncoding == null) {
            messageEncoding = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
        }
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }
    
    public AdvertisementMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        if (messageEncoding == null) {
            messageEncoding = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
        }
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }

    public AdvertisementMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        if (messageEncoding == null) {
            messageEncoding = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
        }
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        AdvertisementMsg43 fixml = (AdvertisementMsg43) fragment;
        if (fixml.getAdvID() != null) {
            advID = fixml.getAdvID();
        }
        if (fixml.getAdvTransTypeType() != null) {
            advTransType = fixml.getAdvTransTypeType().getValue();
        }
        if (fixml.getAdvTransTypeType().getElement().getAdvRefID() != null) {
            advRefID = fixml.getAdvTransTypeType().getElement().getAdvRefID();
        }
        if (fixml.getInstrument() != null) {
            instrument = fixml.getInstrument();
        }
        if (fixml.getAdvSideType() != null) {
            advSide = fixml.getAdvSideType().getValue();
        }
        if (fixml.getQuantity() != null) {
            quantity = fixml.getQuantity();
        }
        if (fixml.getPrice() != null) {
            price = fixml.getPrice();
        }
        if (fixml.getCurrencyType() != null) {
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
        if (fixml.getEncodedTextGroupType() != null) {
            encodedTextLen = fixml.getEncodedTextGroupType().getEncodedTextLen();
            encodedText = fixml.getEncodedTextGroupType().getEncodedText();
        }
        if (fixml.getUrlLink() != null) {
            urlLink = fixml.getUrlLink();
        }
        if (fixml.getLastMktType() != null) {
            lastMkt = fixml.getLastMktType().getValue();
        }
        if (fixml.getTradingSessionID() != null) {
            tradingSessionID = fixml.getTradingSessionID();
        }
        if (fixml.getTradingSessionSubID() != null) {
            tradingSessionSubID = fixml.getTradingSessionSubID();
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

    @XmlElementRefs({
            @XmlElementRef(type = Component.class)
    })
    @Override
    public Instrument getInstrument() {
        return (Instrument43) instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public void setInstrument() {
        this.instrument = new Instrument43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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

    @XmlElement(name="Quantity", required = true)
    @XmlJavaTypeAdapter(FixNumberAdapter.class)
    @Override
    public Double getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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
    public byte[] getEncodedText() {
        return encodedText;
    }

    @Override
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
        if (encodedTextGroupType == null) {
            encodedTextGroupType = new EncodedTextGroupType();
        }
        encodedTextGroupType.setEncodedText(encodedText);
    }

    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
        if (encodedTextGroupType == null) {
            encodedTextGroupType = new EncodedTextGroupType();
        }
        encodedTextGroupType.setEncodedTextLen(encodedTextLen);
    }

    @XmlElement(name="TradingSessionID")
    @Override
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    @XmlElement(name="TradingSessionSubID")
    @Override
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    @Override
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    public AdvTransTypeType getAdvTransTypeType() {
        return advTransTypeType;
    }

    public void setAdvTransTypeType(AdvTransTypeType advTransTypeType) {
        this.advTransTypeType = advTransTypeType;
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

    public EncodedTextGroupType getEncodedTextGroupType() {
        return encodedTextGroupType;
    }

    public void setEncodedTextGroupType(EncodedTextGroupType encodedTextGroupType) {
        this.encodedTextGroupType = encodedTextGroupType;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

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
            bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
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
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
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
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            instrument.decode(tag, message);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;
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
        return "This tag is not supported in [AdvertismentMsg] message version [4.3].";
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
    
    @XmlRootElement(name = "EncodedTextGroup")
    @XmlType(propOrder = {"encodedTextLen", "encodedText"})
    @XmlAccessorType(XmlAccessType.NONE)
    public static class EncodedTextGroupType {

        @XmlElement(name = "EncodedTextLen")
        private Integer encodedTextLen;

        @XmlElement(name = "EncodedText")
        private byte[] encodedText;

        public byte[] getEncodedText() {
            return encodedText;
        }

        public void setEncodedText(byte[] encodedText) {
            this.encodedText = encodedText;
        }

        public Integer getEncodedTextLen() {
            return encodedTextLen;
        }

        public void setEncodedTextLen(Integer encodedTextLen) {
            this.encodedTextLen = encodedTextLen;
        }
    }
    
    // </editor-fold>

}
