/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestMsg50SP2.java
 *
 * $Id: MarketDataRequestMsg50SP2.java,v 1.11 2011-04-14 23:44:27 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2;
import net.hades.fix.message.group.impl.v50sp2.MDEntryTypeGroup50SP2;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplQueueAction;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SubscriptionRequestType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
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
import net.hades.fix.message.Header;
import net.hades.fix.message.MarketDataRequestMsg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MDEntryTypeGroup;
import net.hades.fix.message.group.MDReqGroup;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.TradingSessionGroup;
import net.hades.fix.message.group.impl.v50sp2.MDReqGroup50SP2;
import net.hades.fix.message.group.impl.v50sp2.TradingSessionGroup50SP2;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MDQuoteType;
import net.hades.fix.message.type.MDUpdateType;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

/**
 * FIX version 5.0SP2 MarketDataRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 01/04/2009, 8:41:14 AM
 */
@XmlRootElement(name="MktDataReq")
@XmlType(propOrder = {"header", "partyIDGroups", "mdEntryTypeGroups", "mdReqGroups", "tradingSessionGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class MarketDataRequestMsg50SP2 extends MarketDataRequestMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;
    
    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> MD_ENTRY_TYPE_GROUP_TAGS = new MDEntryTypeGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50SP2().getFragmentAllTags();
    protected static final Set<Integer> MD_REQ_GROUP_TAGS = new MDReqGroup50SP2().getFragmentAllTags();
    protected static final Set<Integer> TRADING_SESS_GROUP_TAGS = new TradingSessionGroup50SP2().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(MD_ENTRY_TYPE_GROUP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(MD_REQ_GROUP_TAGS);
        ALL_TAGS.addAll(TRADING_SESS_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(MD_ENTRY_TYPE_GROUP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(MD_REQ_GROUP_TAGS);
        START_COMP_TAGS.addAll(TRADING_SESS_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MarketDataRequestMsg50SP2() {
        super();
    }
    
    public MarketDataRequestMsg50SP2(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public MarketDataRequestMsg50SP2(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }
    
    public MarketDataRequestMsg50SP2(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        MarketDataRequestMsg50SP2 fixml = (MarketDataRequestMsg50SP2) fragment;
        if (fixml.getMdReqID() != null) {
            mdReqID = fixml.getMdReqID();
        }
        if (fixml.getSubscriptionRequestType() != null) {
            subscriptionRequestType = fixml.getSubscriptionRequestType();
        }
        if (fixml.getParties() != null) {
            parties = fixml.getParties();
        }
        if (fixml.getMarketDepth() != null) {
            marketDepth = fixml.getMarketDepth();
        }
        if (fixml.getMdUpdateType() != null) {
            mdUpdateType = fixml.getMdUpdateType();
        }
        if (fixml.getAggregatedBook() != null) {
            aggregatedBook = fixml.getAggregatedBook();
        }
        if (fixml.getOpenCloseSettlFlag() != null) {
            openCloseSettlFlag = fixml.getOpenCloseSettlFlag();
        }
        if (fixml.getScope() != null) {
            scope = fixml.getScope();
        }
        if (fixml.getMdImplicitDelete() != null) {
            mdImplicitDelete = fixml.getMdImplicitDelete();
        }
        if (fixml.getMdEntryTypeGroups() != null && fixml.getMdEntryTypeGroups().length > 0) {
            setMdEntryTypeGroups(fixml.getMdEntryTypeGroups());
        }
        if (fixml.getMdReqGroups() != null && fixml.getMdReqGroups().length > 0) {
            setMdReqGroups(fixml.getMdReqGroups());
        }
        if (fixml.getTradingSessionGroups() != null && fixml.getTradingSessionGroups().length > 0) {
            setTradingSessionGroups(fixml.getTradingSessionGroups());
        }
        if (fixml.getApplQueueAction() != null) {
            applQueueAction = fixml.getApplQueueAction();
        }
        if (fixml.getApplQueueMax() != null) {
            applQueueMax = fixml.getApplQueueMax();
        }
        if (fixml.getMdQuoteType() != null) {
            mdQuoteType = fixml.getMdQuoteType();
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
    
    @XmlAttribute(name = "ReqID")
    @Override
    public String getMdReqID() {
        return mdReqID;
    }

    @Override
    public void setMdReqID(String mdReqID) {
        this.mdReqID = mdReqID;
    }

    @XmlAttribute(name = "SubReqTyp")
    @Override
    public SubscriptionRequestType getSubscriptionRequestType() {
        return subscriptionRequestType;
    }

    @Override
    public void setSubscriptionRequestType(SubscriptionRequestType subscriptionRequestType) {
        this.subscriptionRequestType = subscriptionRequestType;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        this.parties = new Parties50SP2(context);
    }

    @Override
    public void clearParties() {
        this.parties = null;
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
            ((Parties50SP2) parties).setPartyIDGroups(partyIDGroups);
        }
    }

    @XmlAttribute(name = "MktDepth")
    @Override
    public Integer getMarketDepth() {
        return marketDepth;
    }

    @Override
    public void setMarketDepth(Integer marketDepth) {
        this.marketDepth = marketDepth;
    }

    @XmlAttribute(name = "UpdtTyp")
    @Override
    public MDUpdateType getMdUpdateType() {
        return mdUpdateType;
    }

    @Override
    public void setMdUpdateType(MDUpdateType mdUpdateType) {
        this.mdUpdateType = mdUpdateType;
    }

    @XmlAttribute(name = "AggBook")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getAggregatedBook() {
        return aggregatedBook;
    }

    @Override
    public void setAggregatedBook(Boolean aggregatedBook) {
        this.aggregatedBook = aggregatedBook;
    }

    @XmlAttribute(name = "OpenClsSettlFlag")
    @Override
    public String getOpenCloseSettlFlag() {
        return openCloseSettlFlag;
    }

    @Override
    public void setOpenCloseSettlFlag(String openCloseSettlFlag) {
        this.openCloseSettlFlag = openCloseSettlFlag;
    }

    @XmlAttribute(name = "Scope")
    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }

    @XmlAttribute(name = "ImplctDel")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getMdImplicitDelete() {
        return mdImplicitDelete;
    }

    @Override
    public void setMdImplicitDelete(Boolean mdImplicitDelete) {
        this.mdImplicitDelete = mdImplicitDelete;
    }

    @Override
    public Integer getNoMDEntryTypes() {
        return noMDEntryTypes;
    }

    @Override
    public void setNoMDEntryTypes(Integer noMDEntryTypes) {
        this.noMDEntryTypes = noMDEntryTypes;
        if (noMDEntryTypes != null) {
            mdEntryTypeGroups = new MDEntryTypeGroup[noMDEntryTypes.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < mdEntryTypeGroups.length; i++) {
                mdEntryTypeGroups[i] = new MDEntryTypeGroup50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public MDEntryTypeGroup[] getMdEntryTypeGroups() {
        return mdEntryTypeGroups;
    }

    public void setMdEntryTypeGroups(MDEntryTypeGroup[] mdEntryTypeGroups) {
        this.mdEntryTypeGroups = mdEntryTypeGroups;
        if (mdEntryTypeGroups != null) {
            noMDEntryTypes = new Integer(mdEntryTypeGroups.length);
        }
    }

    @Override
    public MDEntryTypeGroup addMdEntryTypeGroup() {
        MDEntryTypeGroup group = new MDEntryTypeGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<MDEntryTypeGroup> groups = new ArrayList<MDEntryTypeGroup>();
        if (mdEntryTypeGroups != null && mdEntryTypeGroups.length > 0) {
            groups = new ArrayList<MDEntryTypeGroup>(Arrays.asList(mdEntryTypeGroups));
        }
        groups.add(group);
        mdEntryTypeGroups = groups.toArray(new MDEntryTypeGroup[groups.size()]);
        noMDEntryTypes = new Integer(mdEntryTypeGroups.length);

        return group;
    }

    @Override
    public MDEntryTypeGroup deleteMdEntryTypeGroup(int index) {
        MDEntryTypeGroup result = null;
        if (mdEntryTypeGroups != null && mdEntryTypeGroups.length > 0 && mdEntryTypeGroups.length > index) {
            List<MDEntryTypeGroup> groups = new ArrayList<MDEntryTypeGroup>(Arrays.asList(mdEntryTypeGroups));
            result = groups.remove(index);
            mdEntryTypeGroups = groups.toArray(new MDEntryTypeGroup[groups.size()]);
            if (mdEntryTypeGroups.length > 0) {
                noMDEntryTypes = new Integer(mdEntryTypeGroups.length);
            } else {
                mdEntryTypeGroups = null;
                noMDEntryTypes = null;
            }
        }

        return result;
    }

    @Override
    public int clearMdEntryTypeGroups() {
        int result = 0;
        if (mdEntryTypeGroups != null && mdEntryTypeGroups.length > 0) {
            result = mdEntryTypeGroups.length;
            mdEntryTypeGroups = null;
            noMDEntryTypes = null;
        }

        return result;
    }

    @Override
    public Integer getNoRelatedSym() {
        return noRelatedSym;
    }

    @Override
    public void setNoRelatedSym(Integer noRelatedSym) {
        this.noRelatedSym = noRelatedSym;
        if (noRelatedSym != null) {
            mdReqGroups = new MDReqGroup[noRelatedSym.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < mdReqGroups.length; i++) {
                mdReqGroups[i] = new MDReqGroup50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public MDReqGroup[] getMdReqGroups() {
        return mdReqGroups;
    }

    public void setMdReqGroups(MDReqGroup[] mdReqGroups) {
        this.mdReqGroups = mdReqGroups;
        if (mdReqGroups != null) {
            noRelatedSym = new Integer(mdReqGroups.length);
        }
    }

    @Override
    public MDReqGroup addMdReqGroup() {
        MDReqGroup group = new MDReqGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<MDReqGroup> groups = new ArrayList<MDReqGroup>();
        if (mdReqGroups != null && mdReqGroups.length > 0) {
            groups = new ArrayList<MDReqGroup>(Arrays.asList(mdReqGroups));
        }
        groups.add(group);
        mdReqGroups = groups.toArray(new MDReqGroup[groups.size()]);
        noRelatedSym = new Integer(mdReqGroups.length);

        return group;
    }

    @Override
    public MDReqGroup deleteMdReqGroup(int index) {
        MDReqGroup result = null;
        if (mdReqGroups != null && mdReqGroups.length > 0 && mdReqGroups.length > index) {
            List<MDReqGroup> groups = new ArrayList<MDReqGroup>(Arrays.asList(mdReqGroups));
            result = groups.remove(index);
            mdReqGroups = groups.toArray(new MDReqGroup[groups.size()]);
            if (mdReqGroups.length > 0) {
                noRelatedSym = new Integer(mdReqGroups.length);
            } else {
                mdReqGroups = null;
                noRelatedSym = null;
            }
        }

        return result;
    }

    @Override
    public int clearMdReqGroups() {
        int result = 0;
        if (mdReqGroups != null && mdReqGroups.length > 0) {
            result = mdReqGroups.length;
            mdReqGroups = null;
            noRelatedSym = null;
        }

        return result;
    }

    @Override
    public Integer getNoTradingSessions() {
        return noTradingSessions;
    }

    @Override
    public void setNoTradingSessions(Integer noTradingSessions) {
        this.noTradingSessions = noTradingSessions;
        if (noTradingSessions != null) {
            tradingSessionGroups = new TradingSessionGroup[noTradingSessions.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < tradingSessionGroups.length; i++) {
                tradingSessionGroups[i] = new TradingSessionGroup50SP2(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public TradingSessionGroup[] getTradingSessionGroups() {
        return tradingSessionGroups;
    }

    public void setTradingSessionGroups(TradingSessionGroup[] tradingSessionGroups) {
        this.tradingSessionGroups = tradingSessionGroups;
        if (tradingSessionGroups != null) {
            noTradingSessions = new Integer(tradingSessionGroups.length);
        }
    }


    @Override
    public TradingSessionGroup addTradingSessionGroup() {
        TradingSessionGroup group = new TradingSessionGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<TradingSessionGroup> groups = new ArrayList<TradingSessionGroup>();
        if (tradingSessionGroups != null && tradingSessionGroups.length > 0) {
            groups = new ArrayList<TradingSessionGroup>(Arrays.asList(tradingSessionGroups));
        }
        groups.add(group);
        tradingSessionGroups = groups.toArray(new TradingSessionGroup[groups.size()]);
        noTradingSessions = new Integer(tradingSessionGroups.length);

        return group;
    }

    @Override
    public TradingSessionGroup deleteTradingSessionGroup(int index) {
        TradingSessionGroup result = null;
        if (tradingSessionGroups != null && tradingSessionGroups.length > 0 && tradingSessionGroups.length > index) {
            List<TradingSessionGroup> groups = new ArrayList<TradingSessionGroup>(Arrays.asList(tradingSessionGroups));
            result = groups.remove(index);
            tradingSessionGroups = groups.toArray(new TradingSessionGroup[groups.size()]);
            if (tradingSessionGroups.length > 0) {
                noTradingSessions = new Integer(tradingSessionGroups.length);
            } else {
                tradingSessionGroups = null;
                noTradingSessions = null;
            }
        }

        return result;
    }

    @Override
    public int clearTradingSessionGroups() {
        int result = 0;
        if (tradingSessionGroups != null && tradingSessionGroups.length > 0) {
            result = tradingSessionGroups.length;
            tradingSessionGroups = null;
            noTradingSessions = null;
        }

        return result;
    }

    @XmlAttribute(name = "ApplQuActn")
    @Override
    public ApplQueueAction getApplQueueAction() {
        return applQueueAction;
    }

    @Override
    public void setApplQueueAction(ApplQueueAction applQueueAction) {
        this.applQueueAction = applQueueAction;
    }

    @XmlAttribute(name = "ApplQuMax")
    @Override
    public Integer getApplQueueMax() {
        return applQueueMax;
    }

    @Override
    public void setApplQueueMax(Integer applQueueMax) {
        this.applQueueMax = applQueueMax;
    }

    @XmlAttribute(name = "MDQteTyp")
    @Override
    public MDQuoteType getMdQuoteType() {
        return mdQuoteType;
    }

    @Override
    public void setMdQuoteType(MDQuoteType mdQuoteType) {
        this.mdQuoteType = mdQuoteType;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties50SP2(context);
            }
            parties.decode(tag, message);
        }
        if (MD_ENTRY_TYPE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noMDEntryTypes != null && noMDEntryTypes.intValue() > 0) {
                message.reset();
                mdEntryTypeGroups = new MDEntryTypeGroup[noMDEntryTypes.intValue()];
                for (int i = 0; i < noMDEntryTypes.intValue(); i++) {
                    MDEntryTypeGroup group = new MDEntryTypeGroup50SP2(context);
                    group.decode(message);
                    mdEntryTypeGroups[i] = group;
                }
            }
        }
        if (MD_REQ_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRelatedSym != null && noRelatedSym.intValue() > 0) {
                message.reset();
                mdReqGroups = new MDReqGroup[noRelatedSym.intValue()];
                for (int i = 0; i < noRelatedSym.intValue(); i++) {
                    MDReqGroup group = new MDReqGroup50SP2(context);
                    group.decode(message);
                    mdReqGroups[i] = group;
                }
            }
        }
        if (TRADING_SESS_GROUP_TAGS.contains(tag.tagNum)) {
            if (noTradingSessions != null && noTradingSessions.intValue() > 0) {
                message.reset();
                tradingSessionGroups = new TradingSessionGroup[noTradingSessions.intValue()];
                for (int i = 0; i < noTradingSessions.intValue(); i++) {
                    TradingSessionGroup group = new TradingSessionGroup50SP2(context);
                    group.decode(message);
                    tradingSessionGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MarketDataRequestMsg] message version [5.0SP2].";
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
