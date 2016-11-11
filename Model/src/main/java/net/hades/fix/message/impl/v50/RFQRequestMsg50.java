/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RFQRequestMsg50.java
 *
 * $Id: RFQRequestMsg50.java,v 1.10 2011-04-14 23:44:40 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.group.impl.v50.RFQRequestGroup50;
import net.hades.fix.message.struct.Tag;
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

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.Header;
import net.hades.fix.message.RFQRequestMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.RFQRequestGroup;
import net.hades.fix.message.type.BeginString;

/**
 * FIX version 5.0 RFQRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.10 $
 * @created 01/04/2009, 8:41:14 AM
 */
@XmlRootElement(name="RFQReq")
@XmlType(propOrder = {"header", "RFQRequestGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class RFQRequestMsg50 extends RFQRequestMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -359798029421778019L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> RFQ_REQUEST_GROUP_TAGS = new RFQRequestGroup50().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(RFQ_REQUEST_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(RFQ_REQUEST_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RFQRequestMsg50() {
        super();
    }
    
    public RFQRequestMsg50(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public RFQRequestMsg50(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }
    
    public RFQRequestMsg50(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        RFQRequestMsg50 fixml = (RFQRequestMsg50) fragment;
        if (fixml.getRfqReqID() != null) {
            rfqReqID = fixml.getRfqReqID();
        }
        if (fixml.getRFQRequestGroups() != null && fixml.getRFQRequestGroups().length > 0) {
            setRFQRequestGroups(fixml.getRFQRequestGroups());
        }
        if (fixml.getSubscriptionRequestType() != null) {
            subscriptionRequestType = fixml.getSubscriptionRequestType();
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
    public Integer getNoRelatedSyms() {
        return noRelatedSyms;
    }

    @Override
    public void setNoRelatedSyms(Integer noRelatedSyms) {
        this.noRelatedSyms = noRelatedSyms;
        if (noRelatedSyms != null) {
            rfqRequestGroups = new RFQRequestGroup[noRelatedSyms.intValue()];
            for (int i = 0; i < rfqRequestGroups.length; i++) {
                rfqRequestGroups[i] = new RFQRequestGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
        RFQRequestGroup group = new RFQRequestGroup50(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (RFQ_REQUEST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRelatedSyms != null && noRelatedSyms.intValue() > 0) {
                message.reset();
                rfqRequestGroups = new RFQRequestGroup[noRelatedSyms.intValue()];
                for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                    RFQRequestGroup component = new RFQRequestGroup50(context);
                    component.decode(message);
                    rfqRequestGroups[i] = component;
                }
            }
        }
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [RFQRequestMsg] message version [5.0].";
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
