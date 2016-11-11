/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RFQRequestMsg43.java
 *
 * $Id: RFQRequestMsg43.java,v 1.8 2011-04-14 23:44:32 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SubscriptionRequestType;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.hades.fix.message.Header;
import net.hades.fix.message.RFQRequestMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.RFQRequestGroup;
import net.hades.fix.message.group.impl.v43.RFQRequestGroup43;
import net.hades.fix.message.type.BeginString;

/**
 * FIX version 4.3 RFQRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 01/04/2009, 8:41:14 AM
 */
public class RFQRequestMsg43 extends RFQRequestMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -5637821094841678341L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> RFQ_REQUEST_GROUP_TAGS = new RFQRequestGroup43().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(RFQ_REQUEST_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(RFQ_REQUEST_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RFQRequestMsg43() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public RFQRequestMsg43(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public RFQRequestMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public RFQRequestMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
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
    public Header getHeader() {
        return header;
    }
    
    @Override
    public void setHeader(Header header) {
        this.header = header;
    }

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
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < rfqRequestGroups.length; i++) {
                rfqRequestGroups[i] = new RFQRequestGroup43(context);
            }
        }
    }

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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        RFQRequestGroup group = new RFQRequestGroup43(context);
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (RFQ_REQUEST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRelatedSyms != null && noRelatedSyms.intValue() > 0) {
                message.reset();
                rfqRequestGroups = new RFQRequestGroup[noRelatedSyms.intValue()];
                for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                    RFQRequestGroup component = new RFQRequestGroup43(context);
                    component.decode(message);
                    rfqRequestGroups[i] = component;
                }
            }
        }
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [RFQRequestMsg] message version [4.3].";
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
