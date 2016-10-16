/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RFQRequestMsg50SP2.java
 *
 * $Id: RFQRequestMsg50SP2.java,v 1.11 2011-04-14 23:44:27 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

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
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.RFQRequestMsg;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.impl.v50sp2.Parties50SP2;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.RFQRequestGroup;
import net.hades.fix.message.group.impl.v50sp2.RFQRequestGroup50SP2;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

/**
 * FIX version 5.0SP2 RFQRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 01/04/2009, 8:41:14 AM
 */
@XmlRootElement(name="RFQReq")
@XmlType(propOrder = {"header", "partyIDGroups", "RFQRequestGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class RFQRequestMsg50SP2 extends RFQRequestMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;
    
    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties50SP2().getFragmentAllTags();
    protected static final Set<Integer> RFQ_REQUEST_GROUP_TAGS = new RFQRequestGroup50SP2().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(RFQ_REQUEST_GROUP_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(RFQ_REQUEST_GROUP_TAGS);
        START_COMP_TAGS.addAll(PARTIES_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RFQRequestMsg50SP2() {
        super();
    }
    
    public RFQRequestMsg50SP2(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public RFQRequestMsg50SP2(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }
    
    public RFQRequestMsg50SP2(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        RFQRequestMsg50SP2 fixml = (RFQRequestMsg50SP2) fragment;
        if (fixml.getRfqReqID() != null) {
            rfqReqID = fixml.getRfqReqID();
        }
        if (fixml.getParties() != null) {
            parties = fixml.getParties();
        }
        if (fixml.getRFQRequestGroups() != null && fixml.getRFQRequestGroups().length > 0) {
            setRFQRequestGroups(fixml.getRFQRequestGroups());
        }
        if (fixml.getSubscriptionRequestType() != null) {
            subscriptionRequestType = fixml.getSubscriptionRequestType();
        }
        if (fixml.getPrivateQuote() != null) {
            privateQuote = fixml.getPrivateQuote();
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
    
    @XmlAttribute(name = "RFQReqID")
    @Override
    public String getRfqReqID() {
        return rfqReqID;
    }

    @Override
    public void setRfqReqID(String rfqReqID) {
        this.rfqReqID = rfqReqID;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        this.parties = new Parties50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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

    @Override
    public Integer getNoRelatedSyms() {
        return noRelatedSyms;
    }

    @Override
    public void setNoRelatedSyms(Integer noRelatedSyms) {
        this.noRelatedSyms = noRelatedSyms;
        if (noRelatedSyms != null) {
            rfqRequestGroups = new RFQRequestGroup[noRelatedSyms.intValue()];
            for (int i = 0; i < rfqRequestGroups.length; i++) {
                rfqRequestGroups[i] = new RFQRequestGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public RFQRequestGroup[] getRFQRequestGroups() {
        return rfqRequestGroups;
    }

    public void setRFQRequestGroups(RFQRequestGroup[] rfqRequestGroups) {
        this.rfqRequestGroups = rfqRequestGroups;
        if (rfqRequestGroups != null) {
            noRelatedSyms = new Integer(rfqRequestGroups.length);
        }
    }

    @Override
    public RFQRequestGroup addRFQRequestGroup() {
        RFQRequestGroup group = new RFQRequestGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
        List<RFQRequestGroup> groups = new ArrayList<RFQRequestGroup>();
        if (rfqRequestGroups != null && rfqRequestGroups.length > 0) {
            groups = new ArrayList<RFQRequestGroup>(Arrays.asList(rfqRequestGroups));
        }
        groups.add(group);
        rfqRequestGroups = groups.toArray(new RFQRequestGroup[groups.size()]);
        noRelatedSyms = new Integer(rfqRequestGroups.length);

        return group;
    }

    @Override
    public RFQRequestGroup deleteRFQRequestGroup(int index) {
        RFQRequestGroup result = null;
        if (rfqRequestGroups != null && rfqRequestGroups.length > 0 && rfqRequestGroups.length > index) {
            List<RFQRequestGroup> groups = new ArrayList<RFQRequestGroup>(Arrays.asList(rfqRequestGroups));
            result = groups.remove(index);
            rfqRequestGroups = groups.toArray(new RFQRequestGroup[groups.size()]);
            if (rfqRequestGroups.length > 0) {
                noRelatedSyms = new Integer(rfqRequestGroups.length);
            } else {
                rfqRequestGroups = null;
                noRelatedSyms = null;
            }
        }

        return result;
    }

    @Override
    public int clearRFQRequestGroups() {
        int result = 0;
        if (rfqRequestGroups != null && rfqRequestGroups.length > 0) {
            result = rfqRequestGroups.length;
            rfqRequestGroups = null;
            noRelatedSyms = null;
        }

        return result;
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

    @XmlAttribute(name = "PrvtQt")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getPrivateQuote() {
        return privateQuote;
    }

    @Override
    public void setPrivateQuote(Boolean privateQuote) {
        this.privateQuote = privateQuote;
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
        if (RFQ_REQUEST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRelatedSyms != null && noRelatedSyms.intValue() > 0) {
                message.reset();
                rfqRequestGroups = new RFQRequestGroup[noRelatedSyms.intValue()];
                for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                    RFQRequestGroup component = new RFQRequestGroup50SP2(context);
                    component.decode(message);
                    rfqRequestGroups[i] = component;
                }
            }
        }
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [RFQRequestMsg] message version [5.0SP2].";
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
