/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * IOIMsg50.java
 *
 * $Id: IOIMsg50.java,v 1.12 2011-04-14 23:44:41 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.StipulationsGroup;
import net.hades.fix.message.group.impl.v50.IOIQualifierGroup50;
import net.hades.fix.message.group.impl.v50.LegIOIGroup50;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixUTCDateTimeAdapter;

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
import net.hades.fix.message.IOIMsg;
import net.hades.fix.message.comp.FinancingDetails;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.SpreadOrBenchmarkCurveData;
import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.YieldData;
import net.hades.fix.message.comp.impl.v50.FinancingDetails50;
import net.hades.fix.message.comp.impl.v50.Instrument50;
import net.hades.fix.message.comp.impl.v50.OrderQtyData50;
import net.hades.fix.message.comp.impl.v50.Parties50;
import net.hades.fix.message.comp.impl.v50.SpreadOrBenchmarkCurveData50;
import net.hades.fix.message.comp.impl.v50.Stipulations50;
import net.hades.fix.message.comp.impl.v50.UnderlyingInstrument50;
import net.hades.fix.message.comp.impl.v50.YieldData50;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.IOIQualifierGroup;
import net.hades.fix.message.group.LegIOIGroup;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.RoutingIDGroup;
import net.hades.fix.message.group.impl.v50.RoutingIDGroup50;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.IOIQltyInd;
import net.hades.fix.message.type.IOIQty;
import net.hades.fix.message.type.IOITransType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.QtyType;
import net.hades.fix.message.type.Side;

/**
 * FIX version 5.0 IOIMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.12 $
 * @created 09/02/2009, 7:06:44 PM
 */
@XmlRootElement(name="IOI")
@XmlType(propOrder = {"header", "instrument", "partyGroups", "financingDetails", "underlyingInstruments",
    "orderQtyData" ,"stipulationsGroups", "legIOIGroups", "ioiQualifiers", "routingIDGroups",
    "spreadOrBenchmarkCurveData", "yieldData"})
@XmlAccessorType(XmlAccessType.NONE)
public class IOIMsg50 extends IOIMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> IOI_QUALIFIER_GROUP_TAGS = new IOIQualifierGroup50().getFragmentAllTags();
    protected static final Set<Integer> ROUTING_ID_GROUP_TAGS = new RoutingIDGroup50().getFragmentAllTags();
    protected static final Set<Integer> INSTRUMENT_COMP_TAGS = new Instrument50().getFragmentAllTags();
    protected static final Set<Integer> SPREAD_COMP_TAGS = new SpreadOrBenchmarkCurveData50().getFragmentAllTags();
    protected static final Set<Integer> FINANCING_DETAILS_COMP_TAGS = new FinancingDetails50().getFragmentAllTags();
    protected static final Set<Integer> UNDLY_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument50().getFragmentAllTags();
    protected static final Set<Integer> ORDER_QTY_DATA_COMP_TAGS = new OrderQtyData50().getFragmentAllTags();
    protected static final Set<Integer> STIPULATIONS_COMP_TAGS = new Stipulations50().getFragmentAllTags();
    protected static final Set<Integer> LEG_IOI_GROUP_TAGS = new LegIOIGroup50().getFragmentAllTags();
    protected static final Set<Integer> YIELD_DATA_COMP_TAGS = new YieldData50().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(IOI_QUALIFIER_GROUP_TAGS);
        ALL_TAGS.addAll(ROUTING_ID_GROUP_TAGS);
        ALL_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(SPREAD_COMP_TAGS);
        ALL_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        ALL_TAGS.addAll(UNDLY_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        ALL_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        ALL_TAGS.addAll(LEG_IOI_GROUP_TAGS);
        ALL_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(IOI_QUALIFIER_GROUP_TAGS);
        START_COMP_TAGS.addAll(ROUTING_ID_GROUP_TAGS);
        START_COMP_TAGS.addAll(INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(SPREAD_COMP_TAGS);
        START_COMP_TAGS.addAll(FINANCING_DETAILS_COMP_TAGS);
        START_COMP_TAGS.addAll(UNDLY_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(ORDER_QTY_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(STIPULATIONS_COMP_TAGS);
        START_COMP_TAGS.addAll(LEG_IOI_GROUP_TAGS);
        START_COMP_TAGS.addAll(YIELD_DATA_COMP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public IOIMsg50() {
        super();
    }

    public IOIMsg50(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        instrument = new Instrument50(context);
    }

    public IOIMsg50(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        instrument = new Instrument50(context);
    }

    public IOIMsg50(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        instrument = new Instrument50(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        IOIMsg50 fixml = (IOIMsg50) fragment;
        if (fixml.getIoiID() != null) {
            ioiID = fixml.getIoiID();
        }
        if (fixml.getIoiTransType() != null) {
            ioiTransType = fixml.getIoiTransType();
        }
        if (fixml.getIoiRefID() != null) {
            ioiRefID = fixml.getIoiRefID();
        }
        if (fixml.getInstrument() != null) {
            instrument = fixml.getInstrument();
        }
        if (fixml.getParties() != null) {
            parties = fixml.getParties();
        }
        if (fixml.getFinancingDetails() != null) {
            financingDetails = fixml.getFinancingDetails();
        }
        if (fixml.getUnderlyingInstruments() != null && fixml.getUnderlyingInstruments().length > 0) {
            setUnderlyingInstruments(fixml.getUnderlyingInstruments());
        }
        if (fixml.getSide() != null) {
            side = fixml.getSide();
        }
        if (fixml.getQtyType() != null) {
            qtyType = fixml.getQtyType();
        }
        if (fixml.getOrderQtyData() != null) {
            orderQtyData = fixml.getOrderQtyData();
        }
        if (fixml.getIoiQty() != null) {
            ioiQty = fixml.getIoiQty();
        }
        if (fixml.getCurrency() != null) {
            currency = fixml.getCurrency();
        }
        if (fixml.getPrice() != null) {
            price = fixml.getPrice();
        }
        if (fixml.getStipulations() != null && fixml.getStipulations().getStipulationsGroups() != null) {
            setStipulationsGroups(fixml.getStipulations().getStipulationsGroups());
        }
        if (fixml.getLegIOIGroups() != null && fixml.getLegIOIGroups().length > 0) {
            setLegIOIGroups(fixml.getLegIOIGroups());
        }
        if (fixml.getPriceType() != null) {
            priceType = fixml.getPriceType();
        }
        if (fixml.getPrice() != null) {
            price = fixml.getPrice();
        }
        if (fixml.getValidUntilTime() != null) {
            validUntilTime = fixml.getValidUntilTime();
        }
        if (fixml.getIoiQltyInd() != null) {
            ioiQltyInd = fixml.getIoiQltyInd();
        }
        if (fixml.getIoiNaturalFlag() != null) {
            ioiNaturalFlag = fixml.getIoiNaturalFlag();
        }
        if (fixml.getIoiQualifiers() != null && fixml.getIoiQualifiers().length > 0) {
            setIoiQualifiers(fixml.getIoiQualifiers());
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getUrlLink() != null) {
            urlLink = fixml.getUrlLink();
        }
        if (fixml.getRoutingIDGroups() != null && fixml.getRoutingIDGroups().length > 0) {
            setRoutingIDGroups(fixml.getRoutingIDGroups());
        }
        if (fixml.getSpreadOrBenchmarkCurveData() != null) {
            spreadOrBenchmarkCurveData = fixml.getSpreadOrBenchmarkCurveData();
        }
        if (fixml.getYieldData() != null) {
            yieldData = fixml.getYieldData();
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

    @XmlAttribute(name = "ID")
    @Override
    public String getIoiID() {
        return ioiID;
    }

    @Override
    public void setIoiID(String ioiID) {
        this.ioiID = ioiID;
    }

    @XmlAttribute(name = "TransTyp")
    @Override
    public IOITransType getIoiTransType() {
        return ioiTransType;
    }

    @Override
    public void setIoiTransType(IOITransType ioiTransType) {
        this.ioiTransType = ioiTransType;
    }

    @XmlAttribute(name = "RefID")
    @Override
    public String getIoiRefID() {
        return ioiRefID;
    }

    @Override
    public void setIoiRefID(String ioiRefID) {
        this.ioiRefID = ioiRefID;
    }

    @XmlElementRef
    @Override
    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public void setInstrument() {
        this.instrument = new Instrument50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.parties = new Parties50(context);
    }

    @Override
    public void clearParties() {
        this.parties = null;
    }

    public void setParties(Parties parties) {
        this.parties = parties;
    }

    @XmlElementRef
    public PartyGroup[] getPartyGroups() {
        return parties == null ? null : parties.getPartyIDGroups();
    }

    public void setPartyGroups(PartyGroup[] partyGroups) {
        if (partyGroups != null) {
            if (parties == null) {
                setParties();
            }
            ((Parties50) parties).setPartyIDGroups(partyGroups);
        }
    }

    @XmlElementRef
    @Override
    public FinancingDetails getFinancingDetails() {
        return financingDetails;
    }

    @Override
    public void setFinancingDetails() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.financingDetails = new FinancingDetails50(context);
    }

    @Override
    public void clearFinancingDetails() {
        this.financingDetails = null;
    }

    public void setFinancingDetails(FinancingDetails financingDetails) {
        this.financingDetails = financingDetails;
    }

    @Override
    public Integer getNoUnderlyings() {
        return noUnderlyings;
    }

    @Override
    public void setNoUnderlyings(Integer noUnderlyings) {
        this.noUnderlyings = noUnderlyings;
        if (noUnderlyings != null) {
            underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < underlyingInstruments.length; i++) {
                underlyingInstruments[i] = new UnderlyingInstrument50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public UnderlyingInstrument[] getUnderlyingInstruments() {
        return underlyingInstruments;
    }

    public void setUnderlyingInstruments(UnderlyingInstrument[] underlyingInstruments) {
        this.underlyingInstruments = underlyingInstruments;
        if (underlyingInstruments != null) {
            noUnderlyings = new Integer(underlyingInstruments.length);
        }
    }

    @Override
    public UnderlyingInstrument addUnderlyingInstrument() {

        UnderlyingInstrument group = new UnderlyingInstrument50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<UnderlyingInstrument> groups = new ArrayList<UnderlyingInstrument>();
        if (underlyingInstruments != null && underlyingInstruments.length > 0) {
            groups = new ArrayList<UnderlyingInstrument>(Arrays.asList(underlyingInstruments));
        }
        groups.add(group);
        underlyingInstruments = groups.toArray(new UnderlyingInstrument[groups.size()]);
        noUnderlyings = new Integer(underlyingInstruments.length);

        return group;
    }

    @Override
    public UnderlyingInstrument deleteUnderlyingInstrument(int index) {

        UnderlyingInstrument result = null;
        if (underlyingInstruments != null && underlyingInstruments.length > 0 && underlyingInstruments.length > index) {
            List<UnderlyingInstrument> groups = new ArrayList<UnderlyingInstrument>(Arrays.asList(underlyingInstruments));
            result = groups.remove(index);
            underlyingInstruments = groups.toArray(new UnderlyingInstrument[groups.size()]);
            if (underlyingInstruments.length > 0) {
                noUnderlyings = new Integer(underlyingInstruments.length);
            } else {
                underlyingInstruments = null;
                noUnderlyings = null;
            }
        }

        return result;
    }

    @Override
    public int clearUnderlyingInstruments() {

        int result = 0;
        if (underlyingInstruments != null && underlyingInstruments.length > 0) {
            result = underlyingInstruments.length;
            underlyingInstruments = null;
            noUnderlyings = null;
        }

        return result;
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

    @XmlAttribute(name = "QtyTyp")
    @Override
    public QtyType getQtyType() {
        return qtyType;
    }

    @Override
    public void setQtyType(QtyType qtyType) {
        this.qtyType = qtyType;
    }

    @XmlElementRef
    @Override
    public OrderQtyData getOrderQtyData() {
        return orderQtyData;
    }

    @Override
    public void setOrderQtyData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.orderQtyData = new OrderQtyData50(context);
    }

    @Override
    public void clearOrderQtyData() {
        this.orderQtyData = null;
    }

    public void setOrderQtyData(OrderQtyData orderQtyData) {
        this.orderQtyData = orderQtyData;
    }

    @XmlAttribute(name = "Qty")
    @Override
    public IOIQty getIoiQty() {
        return ioiQty;
    }

    @Override
    public void setIoiQty(IOIQty ioiQty) {
        this.ioiQty = ioiQty;
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

    @Override
    public Stipulations getStipulations() {
        return stipulations;
    }

    @Override
    public void setStipulations() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.stipulations = new Stipulations50(context);
    }

    @XmlElementRef
    public StipulationsGroup[] getStipulationsGroups() {
        return stipulations == null ? null : stipulations.getStipulationsGroups();
    }

    public void setStipulationsGroups(StipulationsGroup[] stipulationsGroups) {
        if (stipulationsGroups != null) {
            if (stipulations == null) {
                setStipulations();
            }
            ((Stipulations50) stipulations).setStipulationsGroups(stipulationsGroups);
        }
    }

    @Override
    public Integer getNoLegs() {
        return noLegs;
    }

    @Override
    public void setNoLegs(Integer noLegs) {
        this.noLegs = noLegs;
        if (noLegs != null) {
            legIOIGroups = new LegIOIGroup[noLegs.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < legIOIGroups.length; i++) {
                legIOIGroups[i] = new LegIOIGroup50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public LegIOIGroup[] getLegIOIGroups() {
        return legIOIGroups;
    }

    public void setLegIOIGroups(LegIOIGroup[] legIOIGroups) {
        this.legIOIGroups = legIOIGroups;
        if (legIOIGroups != null) {
            noLegs = new Integer(legIOIGroups.length);
        }
    }

    @Override
    public LegIOIGroup addLegIOIGroup() {
        LegIOIGroup group = new LegIOIGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<LegIOIGroup> groups = new ArrayList<LegIOIGroup>();
        if (legIOIGroups != null && legIOIGroups.length > 0) {
            groups = new ArrayList<LegIOIGroup>(Arrays.asList(legIOIGroups));
        }
        groups.add(group);
        legIOIGroups = groups.toArray(new LegIOIGroup[groups.size()]);
        noLegs = new Integer(legIOIGroups.length);

        return group;
    }

    @Override
    public LegIOIGroup deleteLegIOIGroup(int index) {
        LegIOIGroup result = null;
        if (legIOIGroups != null && legIOIGroups.length > 0 && legIOIGroups.length > index) {
            List<LegIOIGroup> groups = new ArrayList<LegIOIGroup>(Arrays.asList(legIOIGroups));
            result = groups.remove(index);
            legIOIGroups = groups.toArray(new LegIOIGroup[groups.size()]);
            if (legIOIGroups.length > 0) {
                noLegs = new Integer(legIOIGroups.length);
            } else {
                legIOIGroups = null;
                noLegs = null;
            }
        }

        return result;
    }

    @Override
    public int clearLegIOIGroups() {
        int result = 0;
        if (legIOIGroups != null && legIOIGroups.length > 0) {
            result = legIOIGroups.length;
            legIOIGroups = null;
            noLegs = null;
        }

        return result;
    }

    @XmlAttribute(name = "PxTyp")
    @Override
    public PriceType getPriceType() {
        return priceType;
    }

    @Override
    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

    @XmlAttribute(name = "Px")
    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @XmlAttribute(name = "ValidUntilTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getValidUntilTime() {
        return validUntilTime;
    }

    @Override
    public void setValidUntilTime(Date validUntilTime) {
        this.validUntilTime = validUntilTime;
    }

    @XmlAttribute(name = "QltyInd")
    @Override
    public IOIQltyInd getIoiQltyInd() {
        return ioiQltyInd;
    }

    @Override
    public void setIoiQltyInd(IOIQltyInd ioiQltyInd) {
        this.ioiQltyInd = ioiQltyInd;
    }

    @XmlAttribute(name = "NatFlag")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
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
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < ioiQualifiers.length; i++) {
                ioiQualifiers[i] = new IOIQualifierGroup50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public IOIQualifierGroup[] getIoiQualifiers() {
        return ioiQualifiers;
    }

    public void setIoiQualifiers(IOIQualifierGroup[] ioiQualifiers) {
        this.ioiQualifiers = ioiQualifiers;
        if (ioiQualifiers != null) {
            noIOIQualifiers = new Integer(ioiQualifiers.length);
        }

    }

    @Override
    public IOIQualifierGroup addIoiQualifier() {

        IOIQualifierGroup group = new IOIQualifierGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @XmlAttribute(name = "TxnTm")
    @XmlJavaTypeAdapter(FixUTCDateTimeAdapter.class)
    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    @XmlAttribute(name = "URL")
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
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < routingIDGroups.length; i++) {
                routingIDGroups[i] = new RoutingIDGroup50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public RoutingIDGroup[] getRoutingIDGroups() {
        return routingIDGroups;
    }

    public void setRoutingIDGroups(RoutingIDGroup[] routingIDGroups) {
        this.routingIDGroups = routingIDGroups;
        if (routingIDGroups != null) {
            noRoutingIDs = new Integer(routingIDGroups.length);
        }
    }

    @Override
    public RoutingIDGroup addRoutingIDGroup() {

        RoutingIDGroup group = new RoutingIDGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @XmlElementRef
    @Override
    public SpreadOrBenchmarkCurveData getSpreadOrBenchmarkCurveData() {
        return spreadOrBenchmarkCurveData;
    }

    @Override
    public void setSpreadOrBenchmarkCurveData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData50(context);
    }

    public void setSpreadOrBenchmarkCurveData(SpreadOrBenchmarkCurveData spreadOrBenchmarkCurveData) {
        this.spreadOrBenchmarkCurveData = spreadOrBenchmarkCurveData;
    }

    @Override
    public void clearSpreadOrBenchmarkCurveData() {
        this.spreadOrBenchmarkCurveData = null;
    }

    @XmlElementRef
    @Override
    public YieldData getYieldData() {
        return yieldData;
    }

    @Override
    public void setYieldData() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.yieldData = new YieldData50(context);
    }

    public void setYieldData(YieldData yieldData) {
        this.yieldData = yieldData;
    }

    @Override
    public void clearYieldData() {
        this.yieldData = null;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (instrument == null) {
                instrument = new Instrument50(context);
            }
            instrument.decode(tag, message);
        }
        if (FINANCING_DETAILS_COMP_TAGS.contains(tag.tagNum)) {
            if (financingDetails == null) {
                financingDetails = new FinancingDetails50(context);
            }
            financingDetails.decode(tag, message);
        }
        if (UNDLY_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (noUnderlyings != null && noUnderlyings.intValue() > 0) {
                message.reset();
                underlyingInstruments = new UnderlyingInstrument[noUnderlyings.intValue()];
                for (int i = 0; i < noUnderlyings.intValue(); i++) {
                    UnderlyingInstrument group = new UnderlyingInstrument50(context);
                    group.decode(message);
                    underlyingInstruments[i] = group;
                }
            }
        }
        if (ORDER_QTY_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (orderQtyData == null) {
                orderQtyData = new OrderQtyData50(context);
            }
            orderQtyData.decode(tag, message);
        }
        if (STIPULATIONS_COMP_TAGS.contains(tag.tagNum)) {
            if (stipulations == null) {
                stipulations = new Stipulations50(context);
            }
            stipulations.decode(tag, message);
        }
        if (IOI_QUALIFIER_GROUP_TAGS.contains(tag.tagNum)) {
            if (noIOIQualifiers != null && noIOIQualifiers.intValue() > 0) {
                message.reset();
                if (ioiQualifiers == null) {
                    ioiQualifiers = new IOIQualifierGroup[noIOIQualifiers.intValue()];
                }
                for (int i = 0; i < ioiQualifiers.length; i++) {
                    IOIQualifierGroup group = new IOIQualifierGroup50(context);
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
                    RoutingIDGroup group = new RoutingIDGroup50(context);
                    group.decode(message);
                    routingIDGroups[i] = group;
                }
            }
        }
        if (SPREAD_COMP_TAGS.contains(tag.tagNum)) {
            if (spreadOrBenchmarkCurveData == null) {
                spreadOrBenchmarkCurveData = new SpreadOrBenchmarkCurveData50(context);
            }
            spreadOrBenchmarkCurveData.decode(tag, message);
        }
        if (LEG_IOI_GROUP_TAGS.contains(tag.tagNum)) {
            if (noLegs != null && noLegs.intValue() > 0) {
                message.reset();
                legIOIGroups = new LegIOIGroup[noLegs.intValue()];
                for (int i = 0; i < noLegs.intValue(); i++) {
                    LegIOIGroup group = new LegIOIGroup50(context);
                    group.decode(message);
                    legIOIGroups[i] = group;
                }
            }
        }
        if (YIELD_DATA_COMP_TAGS.contains(tag.tagNum)) {
            if (yieldData == null) {
                yieldData = new YieldData50(context);
            }
            yieldData.decode(tag, message);
        }
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50(context);
            }
            parties.decode(tag, message);
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [IOIMsg] message version [5.0].";
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
