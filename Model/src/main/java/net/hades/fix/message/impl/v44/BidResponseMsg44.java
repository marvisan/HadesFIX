/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BidResponseMsg44.java
 *
 * $Id: BidResponseMsg44.java,v 1.2 2011-04-14 23:44:37 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.BidResponseMsg;
import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.group.BidRespComponentGroup;
import net.hades.fix.message.group.impl.v44.BidRespComponentGroup44;

/**
 * FIX version 4.4 BidResponseMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="BidRsp")
@XmlType(propOrder = {"header", "bidComponentGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class BidResponseMsg44 extends BidResponseMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> BID_COMP_GROUP_TAGS = new BidRespComponentGroup44().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(BID_COMP_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(BID_COMP_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public BidResponseMsg44() {
        super();
    }

    public BidResponseMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public BidResponseMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public BidResponseMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
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
        BidResponseMsg44 fixml = (BidResponseMsg44) fragment;
        if (fixml.getBidID() != null) {
            bidID = fixml.getBidID();
        }
        if (fixml.getClientBidID() != null) {
            clientBidID = fixml.getClientBidID();
        }
        if (fixml.getBidComponentGroups() != null && fixml.getBidComponentGroups().length > 0) {
            setBidComponentGroups(fixml.getBidComponentGroups());
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

    @Override
    public void setNoBidComponents(Integer noBidComponents) {
        this.noBidComponents = noBidComponents;
        if (noBidComponents != null) {
            bidComponentGroups = new BidRespComponentGroup[noBidComponents.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < bidComponentGroups.length; i++) {
                bidComponentGroups[i] = new BidRespComponentGroup44(context);
            }
        }
    }

    @XmlElementRef
    @Override
    public BidRespComponentGroup[] getBidComponentGroups() {
        return bidComponentGroups;
    }

    public void setBidComponentGroups(BidRespComponentGroup[] bidComponentGroups) {
        this.bidComponentGroups = bidComponentGroups;
        if (bidComponentGroups != null) {
            noBidComponents = new Integer(bidComponentGroups.length);
        }
    }

    @Override
    public BidRespComponentGroup addBidComponentGroup() {
        BidRespComponentGroup group = new BidRespComponentGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<BidRespComponentGroup> groups = new ArrayList<BidRespComponentGroup>();
        if (bidComponentGroups != null && bidComponentGroups.length > 0) {
            groups = new ArrayList<BidRespComponentGroup>(Arrays.asList(bidComponentGroups));
        }
        groups.add(group);
        bidComponentGroups = groups.toArray(new BidRespComponentGroup[groups.size()]);
        noBidComponents = new Integer(bidComponentGroups.length);

        return group;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.BidID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.BidID, bidID);
            }
            if (MsgUtil.isTagInList(TagNum.ClientBidID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClientBidID, clientBidID);
            }
            if (noBidComponents != null && MsgUtil.isTagInList(TagNum.NoBidComponents, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoBidComponents, noBidComponents);
                if (bidComponentGroups != null && bidComponentGroups.length == noBidComponents.intValue()) {
                    for (int i = 0; i < noBidComponents.intValue(); i++) {
                        if (bidComponentGroups[i] != null) {
                            bao.write(bidComponentGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "BidRespComponentGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoBidComponents.getValue(), error);
                }
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
        if (BID_COMP_GROUP_TAGS.contains(tag.tagNum)) {
            if (noBidComponents != null && noBidComponents.intValue() > 0) {
                message.reset();
                bidComponentGroups = new BidRespComponentGroup[noBidComponents.intValue()];
                for (int i = 0; i < noBidComponents.intValue(); i++) {
                    BidRespComponentGroup component = new BidRespComponentGroup44(context);
                    component.decode(message);
                    bidComponentGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [BidResponseMsg] message version [4.4].";
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
