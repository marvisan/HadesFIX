/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderMassCancelReportMsg50SP1.java
 *
 * $Id: OrderMassCancelReportMsg50SP1.java,v 1.2 2011-05-07 06:58:52 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
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
import net.hades.fix.message.OrderMassCancelReportMsg;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v50sp1.Instrument50SP1;
import net.hades.fix.message.comp.impl.v50sp1.Parties50SP1;
import net.hades.fix.message.comp.impl.v50sp1.UnderlyingInstrument50SP1;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.AffectedOrdGroup;
import net.hades.fix.message.group.NotAffectedOrdGroup;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.impl.v50sp1.AffectedOrdGroup50SP1;
import net.hades.fix.message.group.impl.v50sp1.NotAffectedOrdGroup50SP1;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MassCancelRejectReason;
import net.hades.fix.message.type.MassCancelRequestType;
import net.hades.fix.message.type.MassCancelResponse;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 5.0SP1 OrderMassCancelReportMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 01/05/2011, 9:32:41 AM
 */
@XmlRootElement(name="OrdMassCxlRpt")
@XmlType(propOrder = {"header", "affectedOrdGroups", "notAffectedOrdGroups", "partyIDGroups", "instrument", "underlyingInstrument"})
@XmlAccessorType(XmlAccessType.NONE)
public class OrderMassCancelReportMsg50SP1 extends OrderMassCancelReportMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> AFFECTED_ORD_GROUP_TAGS = new AffectedOrdGroup50SP1().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50SP1().getFragmentAllTags();
    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50SP1().getFragmentAllTags();
    protected static final Set<Integer> NOT_AFFECTED_ORD_GROUP_TAGS = new NotAffectedOrdGroup50SP1().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50SP1().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(AFFECTED_ORD_GROUP_TAGS);
        ALL_TAGS.addAll(NOT_AFFECTED_ORD_GROUP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(AFFECTED_ORD_GROUP_TAGS);
        START_COMP_TAGS.addAll(NOT_AFFECTED_ORD_GROUP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public OrderMassCancelReportMsg50SP1() {
        super();
    }

    public OrderMassCancelReportMsg50SP1(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public OrderMassCancelReportMsg50SP1(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public OrderMassCancelReportMsg50SP1(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        OrderMassCancelReportMsg50SP1 fixml = (OrderMassCancelReportMsg50SP1) fragment;
        if (fixml.getClOrdID() != null) {
            clOrdID = fixml.getClOrdID();
        }
        if (fixml.getSecondaryClOrdID() != null) {
            secondaryClOrdID = fixml.getSecondaryClOrdID();
        }
        if (fixml.getOrderID() != null) {
            orderID = fixml.getOrderID();
        }
        if (fixml.getMassActionReportID() != null) {
            massActionReportID = fixml.getMassActionReportID();
        }
        if (fixml.getSecondaryOrderID() != null) {
            secondaryOrderID = fixml.getSecondaryOrderID();
        }
        if (fixml.getMassCancelRequestType() != null) {
            massCancelRequestType = fixml.getMassCancelRequestType();
        }
        if (fixml.getMassCancelResponse() != null) {
            massCancelResponse = fixml.getMassCancelResponse();
        }
        if (fixml.getMassCancelRejectReason() != null) {
            massCancelRejectReason = fixml.getMassCancelRejectReason();
        }
        if (fixml.getTotalAffectedOrders() != null) {
            totalAffectedOrders = fixml.getTotalAffectedOrders();
        }
        if (fixml.getAffectedOrdGroups() != null && fixml.getAffectedOrdGroups().length > 0) {
            setAffectedOrdGroups(fixml.getAffectedOrdGroups());
        }
        if (fixml.getNotAffectedOrdGroups() != null && fixml.getNotAffectedOrdGroups().length > 0) {
            setNotAffectedOrdGroups(fixml.getNotAffectedOrdGroups());
        }
        if (fixml.getTradingSessionID() != null) {
            tradingSessionID = fixml.getTradingSessionID();
        }
        if (fixml.getTradingSessionSubID() != null) {
            tradingSessionSubID = fixml.getTradingSessionSubID();
        }
        if (fixml.getParties() != null) {
            parties = fixml.getParties();
        }
        if (fixml.getInstrument() != null) {
            setInstrument(fixml.getInstrument());
        }
        if (fixml.getUnderlyingInstrument() != null) {
            setUnderlyingInstrument(fixml.getUnderlyingInstrument());
        }
        if (fixml.getMarketID() != null) {
            marketID = fixml.getMarketID();
        }
        if (fixml.getMarketSegmentID() != null) {
            marketSegmentID = fixml.getMarketSegmentID();
        }
        if (fixml.getSide() != null) {
            side = fixml.getSide();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
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

    @XmlAttribute(name = "ClOrdID")
    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    @XmlAttribute(name = "ClOrdID2")
    @Override
    public String getSecondaryClOrdID() {
        return secondaryClOrdID;
    }

    @Override
    public void setSecondaryClOrdID(String secondaryClOrdID) {
        this.secondaryClOrdID = secondaryClOrdID;
    }

    @XmlAttribute(name = "OrdID")
    @Override
    public String getOrderID() {
        return orderID;
    }

    @Override
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @XmlAttribute(name = "MassActionReportID")
    @Override
    public String getMassActionReportID() {
        return massActionReportID;
    }

    @Override
    public void setMassActionReportID(String massActionReportID) {
        this.massActionReportID = massActionReportID;
    }

    @XmlAttribute(name = "OrdID2")
    @Override
    public String getSecondaryOrderID() {
        return secondaryOrderID;
    }

    @Override
    public void setSecondaryOrderID(String secondaryOrderID) {
        this.secondaryOrderID = secondaryOrderID;
    }

    @XmlAttribute(name = "ReqTyp")
    @Override
    public MassCancelRequestType getMassCancelRequestType() {
        return massCancelRequestType;
    }

    @Override
    public void setMassCancelRequestType(MassCancelRequestType massCancelRequestType) {
        this.massCancelRequestType = massCancelRequestType;
    }

    @XmlAttribute(name = "Rsp")
    @Override
    public MassCancelResponse getMassCancelResponse() {
        return massCancelResponse;
    }

    @Override
    public void setMassCancelResponse(MassCancelResponse massCancelResponse) {
        this.massCancelResponse = massCancelResponse;
    }

    @XmlAttribute(name = "MassCxlRejRsn")
    @Override
    public MassCancelRejectReason getMassCancelRejectReason() {
        return massCancelRejectReason;
    }

    @Override
    public void setMassCancelRejectReason(MassCancelRejectReason massCancelRejectReason) {
        this.massCancelRejectReason = massCancelRejectReason;
    }

    @XmlAttribute(name = "TotAffctdOrds")
    @Override
    public Integer getTotalAffectedOrders() {
        return totalAffectedOrders;
    }

    @Override
    public void setTotalAffectedOrders(Integer totalAffectedOrders) {
        this.totalAffectedOrders = totalAffectedOrders;
    }

    @Override
    public Integer getNoAffectedOrders() {
        return noAffectedOrders;
    }

    @Override
    public void setNoAffectedOrders(Integer noAffectedOrders) {
        this.noAffectedOrders = noAffectedOrders;
        if (noAffectedOrders != null) {
            affectedOrdGroups = new AffectedOrdGroup[noAffectedOrders.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < affectedOrdGroups.length; i++) {
                affectedOrdGroups[i] = new AffectedOrdGroup50SP1(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public AffectedOrdGroup[] getAffectedOrdGroups() {
        return affectedOrdGroups;
    }

    public void setAffectedOrdGroups(AffectedOrdGroup[] affectedOrdGroups) {
        this.affectedOrdGroups = affectedOrdGroups;
        if (affectedOrdGroups != null) {
            noAffectedOrders = new Integer(affectedOrdGroups.length);
        }
    }

    @Override
    public AffectedOrdGroup addAffectedOrdGroup() {
        AffectedOrdGroup group = new AffectedOrdGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<AffectedOrdGroup> groups = new ArrayList<AffectedOrdGroup>();
        if (affectedOrdGroups != null && affectedOrdGroups.length > 0) {
            groups = new ArrayList<AffectedOrdGroup>(Arrays.asList(affectedOrdGroups));
        }
        groups.add(group);
        affectedOrdGroups = groups.toArray(new AffectedOrdGroup[groups.size()]);
        noAffectedOrders = new Integer(affectedOrdGroups.length);

        return group;
    }

    @Override
    public AffectedOrdGroup deleteAffectedOrdGroup(int index) {
        AffectedOrdGroup result = null;
        if (affectedOrdGroups != null && affectedOrdGroups.length > 0 && affectedOrdGroups.length > index) {
            List<AffectedOrdGroup> groups = new ArrayList<AffectedOrdGroup>(Arrays.asList(affectedOrdGroups));
            result = groups.remove(index);
            affectedOrdGroups = groups.toArray(new AffectedOrdGroup[groups.size()]);
            if (affectedOrdGroups.length > 0) {
                noAffectedOrders = new Integer(affectedOrdGroups.length);
            } else {
                affectedOrdGroups = null;
                noAffectedOrders = null;
            }
        }

        return result;
    }

    @Override
    public int clearAffectedOrdGroups() {
        int result = 0;
        if (affectedOrdGroups != null && affectedOrdGroups.length > 0) {
            result = affectedOrdGroups.length;
            affectedOrdGroups = null;
            noAffectedOrders = null;
        }

        return result;
    }

    @Override
    public Integer getNoNotAffectedOrders() {
        return noNotAffectedOrders;
    }

    @Override
    public void setNoNotAffectedOrders(Integer noNotAffectedOrders) {
        this.noNotAffectedOrders = noNotAffectedOrders;
        if (noNotAffectedOrders != null) {
            notAffectedOrdGroups = new NotAffectedOrdGroup[noNotAffectedOrders.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < notAffectedOrdGroups.length; i++) {
                notAffectedOrdGroups[i] = new NotAffectedOrdGroup50SP1(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public NotAffectedOrdGroup[] getNotAffectedOrdGroups() {
        return notAffectedOrdGroups;
    }

    public void setNotAffectedOrdGroups(NotAffectedOrdGroup[] notAffectedOrdGroups) {
        this.notAffectedOrdGroups = notAffectedOrdGroups;
        if (notAffectedOrdGroups != null) {
            noNotAffectedOrders = new Integer(notAffectedOrdGroups.length);
        }
    }

    @Override
    public NotAffectedOrdGroup addNotAffectedOrdGroup() {
        NotAffectedOrdGroup group = new NotAffectedOrdGroup50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<NotAffectedOrdGroup> groups = new ArrayList<NotAffectedOrdGroup>();
        if (notAffectedOrdGroups != null && notAffectedOrdGroups.length > 0) {
            groups = new ArrayList<NotAffectedOrdGroup>(Arrays.asList(notAffectedOrdGroups));
        }
        groups.add(group);
        notAffectedOrdGroups = groups.toArray(new NotAffectedOrdGroup[groups.size()]);
        noNotAffectedOrders = new Integer(notAffectedOrdGroups.length);

        return group;
    }

    @Override
    public NotAffectedOrdGroup deleteNotAffectedOrdGroup(int index) {
        NotAffectedOrdGroup result = null;
        if (notAffectedOrdGroups != null && notAffectedOrdGroups.length > 0 && notAffectedOrdGroups.length > index) {
            List<NotAffectedOrdGroup> groups = new ArrayList<NotAffectedOrdGroup>(Arrays.asList(notAffectedOrdGroups));
            result = groups.remove(index);
            notAffectedOrdGroups = groups.toArray(new NotAffectedOrdGroup[groups.size()]);
            if (notAffectedOrdGroups.length > 0) {
                noNotAffectedOrders = new Integer(notAffectedOrdGroups.length);
            } else {
                notAffectedOrdGroups = null;
                noNotAffectedOrders = null;
            }
        }

        return result;
    }

    @Override
    public int clearNotAffectedOrdGroups() {
        int result = 0;
        if (notAffectedOrdGroups != null && notAffectedOrdGroups.length > 0) {
            result = notAffectedOrdGroups.length;
            notAffectedOrdGroups = null;
            noNotAffectedOrders = null;
        }

        return result;
    }

    @XmlAttribute(name = "SesID")
    @Override
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    @Override
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    @XmlAttribute(name = "SesSub")
    @Override
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    @Override
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        this.parties = new Parties50SP1(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    @Override
    public void clearParties() {
        this.parties = null;
    }

    public void setParties(Parties parties) {
        this.parties = parties;
    }

    @XmlElementRef
    public PartyGroup[] getPartyIDGroups() {
        return parties == null ? null : parties.getPartyIDGroups();
    }

    public void setPartyIDGroups(PartyGroup[] partyIDGroups) {
        if (partyIDGroups != null) {
            if (parties == null) {
                setParties();
            }
            ((Parties50SP1) parties).setPartyIDGroups(partyIDGroups);
        }
    }

    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.instrument = new Instrument50SP1(context);
    }

    @Override
    public void clearInstrument() {
        this.instrument = null;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @XmlElementRef
    @Override
    public UnderlyingInstrument getUnderlyingInstrument() {
        return underlyingInstrument;
    }

    @Override
    public void setUnderlyingInstrument() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.underlyingInstrument = new UnderlyingInstrument50SP1(context);
    }

    @Override
    public void clearUnderlyingInstrument() {
        this.underlyingInstrument = null;
    }

    public void setUnderlyingInstrument(UnderlyingInstrument underlyingInstrument) {
        this.underlyingInstrument = underlyingInstrument;
    }

    @XmlAttribute(name = "MktID")
    @Override
    public String getMarketID() {
        return marketID;
    }

    @Override
    public void setMarketID(String marketID) {
        this.marketID = marketID;
    }

    @XmlAttribute(name = "MktSegID")
    @Override
    public String getMarketSegmentID() {
        return marketSegmentID;
    }

    @Override
    public void setMarketSegmentID(String marketSegmentID) {
        this.marketSegmentID = marketSegmentID;
    }

    @XmlAttribute(name = "Side")
    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public void setSide(Side side) {
        this.side = side;
    }

    @XmlAttribute(name = "TxnTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
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
        if (AFFECTED_ORD_GROUP_TAGS.contains(tag.tagNum)) {
            if (noAffectedOrders != null && noAffectedOrders.intValue() > 0) {
                message.reset();
                affectedOrdGroups = new AffectedOrdGroup[noAffectedOrders.intValue()];
                for (int i = 0; i < noAffectedOrders.intValue(); i++) {
                    AffectedOrdGroup component = new AffectedOrdGroup50SP1(context);
                    component.decode(message);
                    affectedOrdGroups[i] = component;
                }
            }
        }
        if (NOT_AFFECTED_ORD_GROUP_TAGS.contains(tag.tagNum)) {
            if (noNotAffectedOrders != null && noNotAffectedOrders.intValue() > 0) {
                message.reset();
                notAffectedOrdGroups = new NotAffectedOrdGroup[noNotAffectedOrders.intValue()];
                for (int i = 0; i < noNotAffectedOrders.intValue(); i++) {
                    NotAffectedOrdGroup component = new NotAffectedOrdGroup50SP1(context);
                    component.decode(message);
                    notAffectedOrdGroups[i] = component;
                }
            }
        }
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50SP1(context);
            }
            parties.decode(tag, message);
        }
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument50SP1(context);
            }
            instrument.decode(tag, message);
        }
        if (UNDERLYING_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (underlyingInstrument == null) {
                underlyingInstrument = new UnderlyingInstrument50SP1(context);
            }
            underlyingInstrument.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [OrderMassCancelReportMsg] message version [5.0SP1].";
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
}
