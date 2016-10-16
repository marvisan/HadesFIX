/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidRequestMsg50SP1.java
 *
 * $Id: BidRequestMsg50SP1.java,v 1.3 2011-04-14 23:44:58 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1;

import net.hades.fix.message.BidRequestMsg;
import net.hades.fix.message.group.impl.v50sp1.BidReqComponentGroup50SP1;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.BasisPxType;
import net.hades.fix.message.type.BidRequestTransType;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.BidReqComponentGroup;
import net.hades.fix.message.group.BidReqDescriptorGroup;
import net.hades.fix.message.group.impl.v50sp1.BidReqDescriptorGroup50SP1;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.BidTradeType;
import net.hades.fix.message.type.BidType;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IncTaxInd;
import net.hades.fix.message.type.LiquidityIndType;
import net.hades.fix.message.type.ProgRptReqs;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX version 5.0SP1 BidRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="BidReq")
@XmlType(propOrder = {"header", "bidDescriptorGroups", "bidComponentGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class BidRequestMsg50SP1 extends BidRequestMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> BID_DESCR_GROUP_TAGS = new BidReqDescriptorGroup50SP1().getFragmentAllTags();
    protected static final Set<Integer> BID_COMP_GROUP_TAGS = new BidReqComponentGroup50SP1().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(BID_DESCR_GROUP_TAGS);
        ALL_TAGS.addAll(BID_COMP_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(BID_DESCR_GROUP_TAGS);
        START_COMP_TAGS.addAll(BID_COMP_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public BidRequestMsg50SP1() {
        super();
    }

    public BidRequestMsg50SP1(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public BidRequestMsg50SP1(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public BidRequestMsg50SP1(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
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
        BidRequestMsg50SP1 fixml = (BidRequestMsg50SP1) fragment;
        if (fixml.getBidID() != null) {
            bidID = fixml.getBidID();
        }
        if (fixml.getClientBidID() != null) {
            clientBidID = fixml.getClientBidID();
        }
        if (fixml.getBidRequestTransType() != null) {
            bidRequestTransType = fixml.getBidRequestTransType();
        }
        if (fixml.getListName() != null) {
            listName = fixml.getListName();
        }
        if (fixml.getTotNoRelatedSym() != null) {
            totNoRelatedSym = fixml.getTotNoRelatedSym();
        }
        if (fixml.getBidType() != null) {
            bidType = fixml.getBidType();
        }
        if (fixml.getNumTickets() != null) {
            numTickets = fixml.getNumTickets();
        }
        if (fixml.getCurrency() != null) {
            currency = fixml.getCurrency();
        }
        if (fixml.getSideValue1() != null) {
            sideValue1 = fixml.getSideValue1();
        }
        if (fixml.getSideValue2() != null) {
            sideValue2 = fixml.getSideValue2();
        }
        if (fixml.getBidDescriptorGroups() != null && fixml.getBidDescriptorGroups().length > 0) {
            setBidDescriptorGroups(fixml.getBidDescriptorGroups());
        }
        if (fixml.getBidComponentGroups() != null && fixml.getBidComponentGroups().length > 0) {
            setBidComponentGroups(fixml.getBidComponentGroups());
        }
        if (fixml.getLiquidityIndType() != null) {
            liquidityIndType = fixml.getLiquidityIndType();
        }
        if (fixml.getWtAverageLiquidity() != null) {
            wtAverageLiquidity = fixml.getWtAverageLiquidity();
        }
        if (fixml.getExchangeForPhysical() != null) {
            exchangeForPhysical = fixml.getExchangeForPhysical();
        }
        if (fixml.getOutMainCntryUIndex() != null) {
            outMainCntryUIndex = fixml.getOutMainCntryUIndex();
        }
        if (fixml.getCrossPercent() != null) {
            crossPercent = fixml.getCrossPercent();
        }
        if (fixml.getProgRptReqs() != null) {
            progRptReqs = fixml.getProgRptReqs();
        }
        if (fixml.getProgPeriodInterval() != null) {
            progPeriodInterval = fixml.getProgPeriodInterval();
        }
        if (fixml.getIncTaxInd() != null) {
            incTaxInd = fixml.getIncTaxInd();
        }
        if (fixml.getForexReq() != null) {
            forexReq = fixml.getForexReq();
        }
        if (fixml.getNumBidders() != null) {
            numBidders = fixml.getNumBidders();
        }
        if (fixml.getTradeDate() != null) {
            tradeDate = fixml.getTradeDate();
        }
        if (fixml.getBidTradeType() != null) {
            bidTradeType = fixml.getBidTradeType();
        }
        if (fixml.getBasisPxType() != null) {
            basisPxType = fixml.getBasisPxType();
        }
        if (fixml.getStrikeTime() != null) {
            strikeTime = fixml.getStrikeTime();
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
        }
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlElementRef
    @Override
    public Header getHeader() {
        return header;
    }

    @Override
    public void setHeader(Header header) {
        this.header = header;
    }

    @XmlAttribute(name = "BidID")
    @Override
    public String getBidID() {
        return bidID;
    }

    @Override
    public void setBidID(String bidID) {
        this.bidID = bidID;
    }

    @XmlAttribute(name = "ClBidID")
    @Override
    public String getClientBidID() {
        return clientBidID;
    }

    @Override
    public void setClientBidID(String clientBidID) {
        this.clientBidID = clientBidID;
    }

    @XmlAttribute(name = "BidReqTransTyp")
    @Override
    public BidRequestTransType getBidRequestTransType() {
        return bidRequestTransType;
    }

    @Override
    public void setBidRequestTransType(BidRequestTransType bidRequestTransType) {
        this.bidRequestTransType = bidRequestTransType;
    }

    @XmlAttribute(name = "ListName")
    @Override
    public String getListName() {
        return listName;
    }

    @Override
    public void setListName(String listName) {
        this.listName = listName;
    }

    @XmlAttribute(name = "TotNoReltdSym")
    @Override
    public Integer getTotNoRelatedSym() {
        return totNoRelatedSym;
    }

    @Override
    public void setTotNoRelatedSym(Integer totNoRelatedSym) {
        this.totNoRelatedSym = totNoRelatedSym;
    }

    @XmlAttribute(name = "BidTyp")
    @Override
    public BidType getBidType() {
        return bidType;
    }

    @Override
    public void setBidType(BidType bidType) {
        this.bidType = bidType;
    }

    @XmlAttribute(name = "NumTkts")
    @Override
    public Integer getNumTickets() {
        return numTickets;
    }

    @Override
    public void setNumTickets(Integer numTickets) {
        this.numTickets = numTickets;
    }

    @XmlAttribute(name = "Ccy")
    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @XmlAttribute(name = "SideValu1")
    @Override
    public Double getSideValue1() {
        return sideValue1;
    }

    @Override
    public void setSideValue1(Double sideValue1) {
        this.sideValue1 = sideValue1;
    }

    @XmlAttribute(name = "SideValu2")
    @Override
    public Double getSideValue2() {
        return sideValue2;
    }

    @Override
    public void setSideValue2(Double sideValue2) {
        this.sideValue2 = sideValue2;
    }

    @Override
    public void setNoBidDescriptors(Integer noBidDescriptors) {
        this.noBidDescriptors = noBidDescriptors;
        if (noBidDescriptors != null) {
            bidDescriptorGroups = new BidReqDescriptorGroup[noBidDescriptors.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < bidDescriptorGroups.length; i++) {
                bidDescriptorGroups[i] = new BidReqDescriptorGroup50SP1(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public BidReqDescriptorGroup[] getBidDescriptorGroups() {
        return bidDescriptorGroups;
    }

    public void setBidDescriptorGroups(BidReqDescriptorGroup[] bidDescriptorGroups) {
        this.bidDescriptorGroups = bidDescriptorGroups;
        if (bidDescriptorGroups != null) {
            noBidDescriptors = new Integer(bidDescriptorGroups.length);
        }
    }

    @Override
    public BidReqDescriptorGroup addBidDescriptorGroup() {
        BidReqDescriptorGroup group = new BidReqDescriptorGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<BidReqDescriptorGroup> groups = new ArrayList<BidReqDescriptorGroup>();
        if (bidDescriptorGroups != null && bidDescriptorGroups.length > 0) {
            groups = new ArrayList<BidReqDescriptorGroup>(Arrays.asList(bidDescriptorGroups));
        }
        groups.add(group);
        bidDescriptorGroups = groups.toArray(new BidReqDescriptorGroup[groups.size()]);
        noBidDescriptors = new Integer(bidDescriptorGroups.length);

        return group;
    }

    @Override
    public void setNoBidComponents(Integer noBidComponents) {
        this.noBidComponents = noBidComponents;
        if (noBidComponents != null) {
            bidComponentGroups = new BidReqComponentGroup[noBidComponents.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < bidComponentGroups.length; i++) {
                bidComponentGroups[i] = new BidReqComponentGroup50SP1(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public BidReqComponentGroup[] getBidComponentGroups() {
        return bidComponentGroups;
    }

    public void setBidComponentGroups(BidReqComponentGroup[] bidComponentGroups) {
        this.bidComponentGroups = bidComponentGroups;
        if (bidComponentGroups != null) {
            noBidComponents = new Integer(bidComponentGroups.length);
        }
    }

    @Override
    public BidReqComponentGroup addBidComponentGroup() {
        BidReqComponentGroup group = new BidReqComponentGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<BidReqComponentGroup> groups = new ArrayList<BidReqComponentGroup>();
        if (bidComponentGroups != null && bidComponentGroups.length > 0) {
            groups = new ArrayList<BidReqComponentGroup>(Arrays.asList(bidComponentGroups));
        }
        groups.add(group);
        bidComponentGroups = groups.toArray(new BidReqComponentGroup[groups.size()]);
        noBidComponents = new Integer(bidComponentGroups.length);

        return group;
    }

    @XmlAttribute(name = "LqdtyIndTyp")
    @Override
    public LiquidityIndType getLiquidityIndType() {
        return liquidityIndType;
    }

    @Override
    public void setLiquidityIndType(LiquidityIndType liquidityIndType) {
        this.liquidityIndType = liquidityIndType;
    }

    @XmlAttribute(name = "WtAvgLqdty")
    @Override
    public Double getWtAverageLiquidity() {
        return wtAverageLiquidity;
    }

    @Override
    public void setWtAverageLiquidity(Double wtAverageLiquidity) {
        this.wtAverageLiquidity = wtAverageLiquidity;
    }

    @XmlAttribute(name = "EFP")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getExchangeForPhysical() {
        return exchangeForPhysical;
    }

    @Override
    public void setExchangeForPhysical(Boolean exchangeForPhysical) {
        this.exchangeForPhysical = exchangeForPhysical;
    }

    @XmlAttribute(name = "OutMainCntryUNdx")
    @Override
    public Double getOutMainCntryUIndex() {
        return outMainCntryUIndex;
    }

    @Override
    public void setOutMainCntryUIndex(Double outMainCntryUIndex) {
        this.outMainCntryUIndex = outMainCntryUIndex;
    }

    @XmlAttribute(name = "CrssPct")
    @Override
    public Double getCrossPercent() {
        return crossPercent;
    }

    @Override
    public void setCrossPercent(Double crossPercent) {
        this.crossPercent = crossPercent;
    }

    @XmlAttribute(name = "ProgRptReqs")
    @Override
    public ProgRptReqs getProgRptReqs() {
        return progRptReqs;
    }

    @Override
    public void setProgRptReqs(ProgRptReqs progRptReqs) {
        this.progRptReqs = progRptReqs;
    }

    @XmlAttribute(name = "ProgPeriodIntvl")
    @Override
    public Integer getProgPeriodInterval() {
        return progPeriodInterval;
    }

    @Override
    public void setProgPeriodInterval(Integer progPeriodInterval) {
        this.progPeriodInterval = progPeriodInterval;
    }

    @XmlAttribute(name = "IncTaxInd")
    @Override
    public IncTaxInd getIncTaxInd() {
        return incTaxInd;
    }

    @Override
    public void setIncTaxInd(IncTaxInd incTaxInd) {
        this.incTaxInd = incTaxInd;
    }

    @XmlAttribute(name = "ForexReq")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getForexReq() {
        return forexReq;
    }

    @Override
    public void setForexReq(Boolean forexReq) {
        this.forexReq = forexReq;
    }

    @XmlAttribute(name = "NumBidders")
    @Override
    public Integer getNumBidders() {
        return numBidders;
    }

    @Override
    public void setNumBidders(Integer numBidders) {
        this.numBidders = numBidders;
    }

    @XmlAttribute(name = "TrdDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getTradeDate() {
        return tradeDate;
    }

    @Override
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    @XmlAttribute(name = "BidTrdTyp")
    @Override
    public BidTradeType getBidTradeType() {
        return bidTradeType;
    }

    @Override
    public void setBidTradeType(BidTradeType bidTradeType) {
        this.bidTradeType = bidTradeType;
    }

    @XmlAttribute(name = "BasisPxTyp")
    @Override
    public BasisPxType getBasisPxType() {
        return basisPxType;
    }

    @Override
    public void setBasisPxType(BasisPxType basisPxType) {
        this.basisPxType = basisPxType;
    }

    @XmlAttribute(name = "StrkTm")
    @Override
    public Date getStrikeTime() {
        return strikeTime;
    }

    @Override
    public void setStrikeTime(Date strikeTime) {
        this.strikeTime = strikeTime;
    }

    @XmlAttribute(name = "Txt")
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @XmlAttribute(name = "EncTxtLen")
    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    @XmlAttribute(name = "EncTxt")
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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (BID_DESCR_GROUP_TAGS.contains(tag.tagNum)) {
            if (noBidDescriptors != null && noBidDescriptors.intValue() > 0) {
                message.reset();
                bidDescriptorGroups = new BidReqDescriptorGroup[noBidDescriptors.intValue()];
                for (int i = 0; i < noBidDescriptors.intValue(); i++) {
                    BidReqDescriptorGroup component = new BidReqDescriptorGroup50SP1(context);
                    component.decode(message);
                    bidDescriptorGroups[i] = component;
                }
            }
        }
        if (BID_COMP_GROUP_TAGS.contains(tag.tagNum)) {
            if (noBidComponents != null && noBidComponents.intValue() > 0) {
                message.reset();
                bidComponentGroups = new BidReqComponentGroup[noBidComponents.intValue()];
                for (int i = 0; i < noBidComponents.intValue(); i++) {
                    BidReqComponentGroup component = new BidReqComponentGroup50SP1(context);
                    component.decode(message);
                    bidComponentGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [BidRequestMsg] message version [5.0SP1].";
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
