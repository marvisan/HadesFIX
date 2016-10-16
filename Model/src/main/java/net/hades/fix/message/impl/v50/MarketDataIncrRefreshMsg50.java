/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataIncrRefreshMsg50.java
 *
 * $Id: MarketDataIncrRefreshMsg50.java,v 1.8 2011-04-14 23:44:41 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.MarketDataIncrRefreshMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MDIncGroup;
import net.hades.fix.message.group.RoutingIDGroup;
import net.hades.fix.message.group.impl.v50.MDIncGroup50;
import net.hades.fix.message.group.impl.v50.RoutingIDGroup50;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplQueueResolution;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MDBookType;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;

/**
 * FIX version 5.0 MarketDataIncrRefreshMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 01/04/2009, 8:41:14 AM
 */
@XmlRootElement(name="MktDataInc")
@XmlType(propOrder = {"header", "MDIncGroups", "routingIDGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class MarketDataIncrRefreshMsg50 extends MarketDataIncrRefreshMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -4581778295821417998L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> MD_INC_GROUP_TAGS = new MDIncGroup50().getFragmentAllTags();
    protected static final Set<Integer> ROUTING_ID_GROUP_TAGS = new RoutingIDGroup50().getFragmentAllTags();

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(MD_INC_GROUP_TAGS);
        ALL_TAGS.addAll(ROUTING_ID_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(MD_INC_GROUP_TAGS);
        START_COMP_TAGS.addAll(ROUTING_ID_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MarketDataIncrRefreshMsg50() {
        super();
    }
    
    public MarketDataIncrRefreshMsg50(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public MarketDataIncrRefreshMsg50(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }
    
    public MarketDataIncrRefreshMsg50(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        MarketDataIncrRefreshMsg50 fixml = (MarketDataIncrRefreshMsg50) fragment;
        if (fixml.getMdBookType() != null) {
            mdBookType = fixml.getMdBookType();
        }
        if (fixml.getMdReqID() != null) {
            mdReqID = fixml.getMdReqID();
        }
        if (fixml.getMdFeedType() != null) {
            mdFeedType = fixml.getMdFeedType();
        }
        if (fixml.getTradeDate() != null) {
            tradeDate = fixml.getTradeDate();
        }
        if (fixml.getMdReqID() != null) {
            mdReqID = fixml.getMdReqID();
        }
        if (fixml.getMDIncGroups() != null && fixml.getMDIncGroups().length > 0) {
            setMDIncGroups(fixml.getMDIncGroups());
        }
        if (fixml.getApplQueueDepth() != null) {
            applQueueDepth = fixml.getApplQueueDepth();
        }
        if (fixml.getApplQueueResolution() != null) {
            applQueueResolution = fixml.getApplQueueResolution();
        }
        if (fixml.getApplQueueAction() != null) {
            applQueueAction = fixml.getApplQueueAction();
        }
        if (fixml.getApplQueueMax() != null) {
            applQueueMax = fixml.getApplQueueMax();
        }
        if (fixml.getRoutingIDGroups() != null && fixml.getRoutingIDGroups().length > 0) {
            setRoutingIDGroups(fixml.getRoutingIDGroups());
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

    @XmlAttribute(name = "MDBkTyp")
    @Override
    public MDBookType getMdBookType() {
        return mdBookType;
    }

    @Override
    public void setMdBookType(MDBookType mdBookType) {
        this.mdBookType = mdBookType;
    }

    @XmlAttribute(name = "MDFeedTyp")
    @Override
    public String getMdFeedType() {
        return mdFeedType;
    }

    @Override
    public void setMdFeedType(String mdFeedType) {
        this.mdFeedType = mdFeedType;
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

    @XmlAttribute(name = "ReqID")
    @Override
    public String getMdReqID() {
        return mdReqID;
    }

    @Override
    public void setMdReqID(String mdReqID) {
        this.mdReqID = mdReqID;
    }

    @Override
    public Integer getNoMDEntries() {
        return noMDEntries;
    }

    @Override
    public void setNoMDEntries(Integer noMDEntries) {
        this.noMDEntries = noMDEntries;
        if (noMDEntries != null) {
            mdIncGroups = new MDIncGroup[noMDEntries.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
            for (int i = 0; i < mdIncGroups.length; i++) {
                mdIncGroups[i] = new MDIncGroup50(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public MDIncGroup[] getMDIncGroups() {
        return mdIncGroups;
    }

    public void setMDIncGroups(MDIncGroup[] mdIncGroups) {
        this.mdIncGroups = mdIncGroups;
        if (mdIncGroups != null) {
            noMDEntries = mdIncGroups.length;
        }
    }

    @Override
    public MDIncGroup addMDIncGroup() {
        MDIncGroup group = new MDIncGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<MDIncGroup> groups = new ArrayList<MDIncGroup>();
        if (mdIncGroups != null && mdIncGroups.length > 0) {
            groups = new ArrayList<MDIncGroup>(Arrays.asList(mdIncGroups));
        }
        groups.add(group);
        mdIncGroups = groups.toArray(new MDIncGroup[groups.size()]);
        noMDEntries = new Integer(mdIncGroups.length);

        return group;
    }

    @Override
    public MDIncGroup deleteMDIncGroup(int index) {
        MDIncGroup result = null;
        if (mdIncGroups != null && mdIncGroups.length > 0 && mdIncGroups.length > index) {
            List<MDIncGroup> groups = new ArrayList<MDIncGroup>(Arrays.asList(mdIncGroups));
            result = groups.remove(index);
            mdIncGroups = groups.toArray(new MDIncGroup[groups.size()]);
            if (mdIncGroups.length > 0) {
                noMDEntries = new Integer(mdIncGroups.length);
            } else {
                mdIncGroups = null;
                noMDEntries = null;
            }
        }

        return result;
    }

    @Override
    public int clearMDIncGroups() {
        int result = 0;
        if (mdIncGroups != null && mdIncGroups.length > 0) {
            result = mdIncGroups.length;
            mdIncGroups = null;
            noMDEntries = null;
        }

        return result;
    }

    @XmlAttribute(name = "ApplQuDepth")
    @Override
    public Integer getApplQueueDepth() {
        return applQueueDepth;
    }

    @Override
    public void setApplQueueDepth(Integer applQueueDepth) {
        this.applQueueDepth = applQueueDepth;
    }

    @XmlAttribute(name = "ApplQuResolution")
    @Override
    public ApplQueueResolution getApplQueueResolution() {
        return applQueueResolution;
    }

    @Override
    public void setApplQueueResolution(ApplQueueResolution applQueueResolution) {
        this.applQueueResolution = applQueueResolution;
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
            noRoutingIDs = routingIDGroups.length;
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

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (MD_INC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noMDEntries != null && noMDEntries.intValue() > 0) {
                message.reset();
                mdIncGroups = new MDIncGroup[noMDEntries.intValue()];
                for (int i = 0; i < noMDEntries.intValue(); i++) {
                    MDIncGroup component = new MDIncGroup50(context);
                    component.decode(message);
                    mdIncGroups[i] = component;
                }
            }
        }
        if (ROUTING_ID_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRoutingIDs != null && noRoutingIDs.intValue() > 0) {
                message.reset();
                routingIDGroups = new RoutingIDGroup[noRoutingIDs.intValue()];
                for (int i = 0; i < noRoutingIDs.intValue(); i++) {
                    RoutingIDGroup component = new RoutingIDGroup50(context);
                    component.decode(message);
                    routingIDGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MarketDataIncrRefreshMsg] message version [5.0].";
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
