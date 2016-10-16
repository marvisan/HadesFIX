/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIMsg43.java
 *
 * $Id: IOIMsg43.java,v 1.12 2011-04-14 23:44:34 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.impl.v43.SpreadOrBenchmarkCurveData43;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.IOIMsg;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.IOIQualifierGroup;
import net.hades.fix.message.group.RoutingIDGroup;
import net.hades.fix.message.group.impl.v40.IOIQualifierGroup40;
import net.hades.fix.message.group.impl.v43.RoutingIDGroup43;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.Benchmark;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IOIQltyInd;
import net.hades.fix.message.type.IOIQty;
import net.hades.fix.message.type.IOITransType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QuantityType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;
import java.util.logging.Level;

/**
 * FIX version 4.3 IOIMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.12 $
 * @created 09/02/2009, 7:06:44 PM
 */
public class IOIMsg43 extends IOIMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> IOI_QUALIFIER_GROUP_TAGS = new IOIQualifierGroup40().getFragmentAllTags();
    protected static final Set<Integer> ROUTING_ID_GROUP_TAGS = new RoutingIDGroup43().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument43().getFragmentAllTags();
    protected static final Set<Integer> SPREAD_COMP_TAGS = new SpreadOrBenchmarkCurveData43().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(IOI_QUALIFIER_GROUP_TAGS);
        ALL_TAGS.addAll(ROUTING_ID_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(SPREAD_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(IOI_QUALIFIER_GROUP_TAGS);
        START_COMP_TAGS.addAll(ROUTING_ID_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(SPREAD_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public IOIMsg43() {
        super();
    }

    public IOIMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        if (messageEncoding == null) {
            messageEncoding = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
        }
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }

    public IOIMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
        if (messageEncoding == null) {
            messageEncoding = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
        }
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        instrument = new Instrument43(context);
    }

    public IOIMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public String getIoiID() {
        return ioiID;
    }

    @Override
    public void setIoiID(String ioiID) {
        this.ioiID = ioiID;
    }

    @Override
    public IOITransType getIoiTransType() {
        return ioiTransType;
    }

    @Override
    public void setIoiTransType(IOITransType ioiTransType) {
        this.ioiTransType = ioiTransType;
    }

    @Override
    public String getIoiRefID() {
        return ioiRefID;
    }

    @Override
    public void setIoiRefID(String ioiRefID) {
        this.ioiRefID = ioiRefID;
    }

    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        this.instrument = new Instrument43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
    }

    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public void setSide(Side side) {
        this.side = side;
    }

    @Override
    public QuantityType getQuantityType() {
        return quantityType;
    }

    @Override
    public void setQuantityType(QuantityType quantityType) {
        this.quantityType = quantityType;
    }

    @Override
    public IOIQty getIoiQty() {
        return ioiQty;
    }

    @Override
    public void setIoiQty(IOIQty ioiQty) {
        this.ioiQty = ioiQty;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public PriceType getPriceType() {
        return priceType;
    }

    @Override
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public Date getValidUntilTime() {
        return validUntilTime;
    }

    @Override
    public void setValidUntilTime(Date validUntilTime) {
        this.validUntilTime = validUntilTime;
    }

    @Override
    public IOIQltyInd getIoiQltyInd() {
        return ioiQltyInd;
    }

    @Override
    public void setIoiQltyInd(IOIQltyInd ioiQltyInd) {
        this.ioiQltyInd = ioiQltyInd;
    }

    @Override
    public Boolean getIoiNaturalFlag() {
        return ioiNaturalFlag;
    }

    @Override
    public void setIoiNaturalFlag(Boolean ioiNaturalFlag) {
        this.ioiNaturalFlag = ioiNaturalFlag;
    }

    @Override
    public Integer getNoIOIQualifiers() {
        return noIOIQualifiers;
    }

    @Override
    public void setNoIOIQualifiers(Integer noIOIQualifiers) {
        this.noIOIQualifiers = noIOIQualifiers;
        if (noIOIQualifiers != null) {
            ioiQualifiers = new IOIQualifierGroup[noIOIQualifiers.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < ioiQualifiers.length; i++) {
                ioiQualifiers[i] = new IOIQualifierGroup40(context);
            }
        }
    }

    @Override
    public IOIQualifierGroup[] getIoiQualifiers() {
        return ioiQualifiers;
    }

    @Override
    public IOIQualifierGroup addIoiQualifier() {

        IOIQualifierGroup group = new IOIQualifierGroup40(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<IOIQualifierGroup> groups = new ArrayList<IOIQualifierGroup>();
        if (ioiQualifiers != null && ioiQualifiers.length > 0) {
            groups = new ArrayList<IOIQualifierGroup>(Arrays.asList(ioiQualifiers));
        }
        groups.add(group);
        ioiQualifiers = groups.toArray(new IOIQualifierGroup[groups.size()]);
        noIOIQualifiers = new Integer(ioiQualifiers.length);

        return group;
    }

    @Override
    public IOIQualifierGroup deleteIoiQualifier(int index) {
        IOIQualifierGroup result = null;
        if (ioiQualifiers != null && ioiQualifiers.length > 0 && ioiQualifiers.length > index) {
            List<IOIQualifierGroup> groups = new ArrayList<IOIQualifierGroup>(Arrays.asList(ioiQualifiers));
            result = groups.remove(index);
            ioiQualifiers = groups.toArray(new IOIQualifierGroup[groups.size()]);
            if (ioiQualifiers.length > 0) {
                noIOIQualifiers = new Integer(ioiQualifiers.length);
            } else {
                ioiQualifiers = null;
                noIOIQualifiers = null;
            }
        }

        return result;
    }

    @Override
    public int clearIoiQualifiers() {
        int result = 0;
        if (ioiQualifiers != null && ioiQualifiers.length > 0) {
            result = ioiQualifiers.length;
            ioiQualifiers = null;
            noIOIQualifiers = null;
        }

        return result;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
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
    }

    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    @Override
    public String getUrlLink() {
        return urlLink;
    }

    @Override
    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    @Override
    public Integer getNoRoutingIDs() {
        return noRoutingIDs;
    }

    @Override
    public void setNoRoutingIDs(Integer noRoutingIDs) {
        this.noRoutingIDs = noRoutingIDs;
        if (noRoutingIDs != null) {
            routingIDGroups = new RoutingIDGroup[noRoutingIDs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < routingIDGroups.length; i++) {
                routingIDGroups[i] = new RoutingIDGroup43(context);
            }
        }
    }

    @Override
    public RoutingIDGroup[] getRoutingIDGroups() {
        return routingIDGroups;
    }

    @Override
    public RoutingIDGroup addRoutingIDGroup() {

        RoutingIDGroup group = new RoutingIDGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<RoutingIDGroup> groups = new ArrayList<RoutingIDGroup>();
        if (routingIDGroups != null && routingIDGroups.length > 0) {
            groups = new ArrayList<RoutingIDGroup>(Arrays.asList(routingIDGroups));
        }
        groups.add(group);
        routingIDGroups = groups.toArray(new RoutingIDGroup[groups.size()]);
        noRoutingIDs = new Integer(routingIDGroups.length);

        return group;

    }

    @Override
    public RoutingIDGroup deleteRoutingIDGroup(int index) {
       RoutingIDGroup result = null;

        if (routingIDGroups != null && routingIDGroups.length > 0 && routingIDGroups.length > index) {
            List<RoutingIDGroup> groups = new ArrayList<RoutingIDGroup>(Arrays.asList(routingIDGroups));
            result = groups.remove(index);
            routingIDGroups = groups.toArray(new RoutingIDGroup[groups.size()]);
            if (routingIDGroups.length > 0) {
                noRoutingIDs = new Integer(routingIDGroups.length);
            } else {
                routingIDGroups = null;
                noRoutingIDs = null;
            }
        }

        return result;

    }

    @Override
    public int clearRoutingIDGroups() {
        int result = 0;
        if (routingIDGroups != null && routingIDGroups.length > 0) {
            result = routingIDGroups.length;
            routingIDGroups = null;
            noRoutingIDs = null;
        }

        return result;

    }

    @Override
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        return spreadOrBenchmarkCurveData;
    }

    @Override
    public void setSpreadOrBenchmarkCurveData() {
        this.spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
    }

    @Override
    public void clearSpreadOrBenchmarkCurveData() {
        this.spreadOrBenchmarkCurveData = null;
    }

    @Override
    public Benchmark getBenchmark() {
        return benchmark;
    }

    @Override
    public void setBenchmark(Benchmark benchmark) {
        this.benchmark = benchmark;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {

        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.IOIID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.IOIID, ioiID);
            }
            if (ioiTransType != null && MsgUtil.isTagInList(TagNum.IOITransType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.IOITransType, ioiTransType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.IOIRefID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.IOIRefID, ioiRefID);
            }
            bao.write(instrument.encode(getMsgSecureTypeForFlag(secured)));
            if (side != null && MsgUtil.isTagInList(TagNum.Side, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (quantityType != null && MsgUtil.isTagInList(TagNum.QuantityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.QuantityType, quantityType.getValue());
            }
            if (ioiQty != null && MsgUtil.isTagInList(TagNum.IOIQty, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.IOIQty, ioiQty.getValue());
            }
            if (currency != null && MsgUtil.isTagInList(TagNum.Currency, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Currency, currency.getValue());
            }
            if (priceType != null && MsgUtil.isTagInList(TagNum.PriceType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PriceType, priceType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.Price, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Price, price);
            }
            if (MsgUtil.isTagInList(TagNum.ValidUntilTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.ValidUntilTime, validUntilTime);
            }
            if (ioiQltyInd != null && MsgUtil.isTagInList(TagNum.IOIQltyInd, SECURED_TAGS, secured)) {
                 TagEncoder.encode(bao, TagNum.IOIQltyInd, ioiQltyInd.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.IOINaturalFlag, SECURED_TAGS, secured)) {
               TagEncoder.encode(bao, TagNum.IOINaturalFlag, ioiNaturalFlag);
            }
            if (noIOIQualifiers != null && MsgUtil.isTagInList(TagNum.NoIOIQualifiers, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoIOIQualifiers, noIOIQualifiers);
                if (ioiQualifiers != null && ioiQualifiers.length == noIOIQualifiers.intValue()) {
                    for (int i = 0; i < noIOIQualifiers.intValue(); i++) {
                        if (ioiQualifiers[i] != null) {
                            bao.write(ioiQualifiers[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "IOIQualifierGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoIOIQualifiers.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
            }
            if (encodedTextLen != null && encodedTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedTextLen, SECURED_TAGS, secured)) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (MsgUtil.isTagInList(TagNum.URLLink, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.URLLink, urlLink);
            }
            if (noRoutingIDs != null && MsgUtil.isTagInList(TagNum.NoRoutingIDs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoRoutingIDs, noRoutingIDs);
                if (routingIDGroups != null && routingIDGroups.length == noRoutingIDs.intValue()) {
                    for (int i = 0; i < noRoutingIDs.intValue(); i++) {
                        if (routingIDGroups[i] != null) {
                            bao.write(routingIDGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "RoutingIDGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoRoutingIDs.getValue(), error);
                }
            }
            if (spreadOrBenchmarkCurveData != null) {
                bao.write(spreadOrBenchmarkCurveData.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (benchmark != null && MsgUtil.isTagInList(TagNum.Benchmark, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Benchmark, benchmark.getValue());
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            instrument.decode(tag, message);
        }
        if (IOI_QUALIFIER_GROUP_TAGS.contains(tag.tagNum)) {
            if (noIOIQualifiers != null && noIOIQualifiers.intValue() > 0) {
                message.reset();
                if (ioiQualifiers == null) {
                    ioiQualifiers = new IOIQualifierGroup[noIOIQualifiers.intValue()];
                }
                for (int i = 0; i < ioiQualifiers.length; i++) {
                    IOIQualifierGroup group = new IOIQualifierGroup40(context);
                    group.decode(message);
                    ioiQualifiers[i] = group;
                }
            }
        }
        if (ROUTING_ID_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRoutingIDs != null && noRoutingIDs.intValue() > 0) {
                message.reset();
                routingIDGroups = new RoutingIDGroup[noRoutingIDs.intValue()];
                for (int i = 0; i < noRoutingIDs.intValue(); i++) {
                    RoutingIDGroup group = new RoutingIDGroup43(context);
                    group.decode(message);
                    routingIDGroups[i] = group;
                }
            }
        }
        if (SPREAD_COMP_TAGS.contains(tag.tagNum)) {
            if (spreadOrBenchmarkCurveData == null) {
                spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData43(context);
            }
            spreadOrBenchmarkCurveData.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [IOIMsg] message version [4.3].";
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
}
