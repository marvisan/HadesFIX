/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataRequestMsg42.java
 *
 * $Id: MarketDataRequestMsg42.java,v 1.8 2011-04-14 23:44:40 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.MDReqGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SubscriptionRequestType;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.hades.fix.message.MarketDataRequestMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MDEntryTypeGroup;
import net.hades.fix.message.group.impl.v42.MDEntryTypeGroup42;
import net.hades.fix.message.group.impl.v42.MDReqGroup42;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MDUpdateType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;
import java.util.logging.Level;

/**
 * FIX version 4.2 MarketDataRequestMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 01/04/2009, 8:41:14 AM
 */
public class MarketDataRequestMsg42 extends MarketDataRequestMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;
    
    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> MD_ENTRY_TYPE_GROUP_TAGS = new MDEntryTypeGroup42().getFragmentAllTags();
    protected static final Set<Integer> MD_REQ_GROUP_TAGS = new MDReqGroup42().getFragmentAllTags();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(MD_ENTRY_TYPE_GROUP_TAGS);
        ALL_TAGS.addAll(MD_REQ_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(MD_ENTRY_TYPE_GROUP_TAGS);
        START_COMP_TAGS.addAll(MD_REQ_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MarketDataRequestMsg42() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public MarketDataRequestMsg42(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public MarketDataRequestMsg42(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public MarketDataRequestMsg42(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
    public String getMdReqID() {
        return mdReqID;
    }

    @Override
    public void setMdReqID(String mdReqID) {
        this.mdReqID = mdReqID;
    }

    @Override
    public SubscriptionRequestType getSubscriptionRequestType() {
        return subscriptionRequestType;
    }

    @Override
    public void setSubscriptionRequestType(SubscriptionRequestType subscriptionRequestType) {
        this.subscriptionRequestType = subscriptionRequestType;
    }

    @Override
    public Integer getMarketDepth() {
        return marketDepth;
    }

    @Override
    public void setMarketDepth(Integer marketDepth) {
        this.marketDepth = marketDepth;
    }

    @Override
    public MDUpdateType getMdUpdateType() {
        return mdUpdateType;
    }

    @Override
    public void setMdUpdateType(MDUpdateType mdUpdateType) {
        this.mdUpdateType = mdUpdateType;
    }

    @Override
    public Boolean getAggregatedBook() {
        return aggregatedBook;
    }

    @Override
    public void setAggregatedBook(Boolean aggregatedBook) {
        this.aggregatedBook = aggregatedBook;
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
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < mdEntryTypeGroups.length; i++) {
                mdEntryTypeGroups[i] = new MDEntryTypeGroup42(context);
            }
        }
    }

    @Override
    public MDEntryTypeGroup[] getMdEntryTypeGroups() {
        return mdEntryTypeGroups;
    }

    @Override
    public MDEntryTypeGroup addMdEntryTypeGroup() {
        MDEntryTypeGroup group = new MDEntryTypeGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < mdReqGroups.length; i++) {
                mdReqGroups[i] = new MDReqGroup42(context);
            }
        }
    }

    @Override
    public MDReqGroup[] getMdReqGroups() {
        return mdReqGroups;
    }

    @Override
    public MDReqGroup addMdReqGroup() {
        MDReqGroup group = new MDReqGroup42(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.MDReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MDReqID, mdReqID);
            }
            if (subscriptionRequestType != null && MsgUtil.isTagInList(TagNum.SubscriptionRequestType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SubscriptionRequestType, subscriptionRequestType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.MarketDepth, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MarketDepth, marketDepth);
            }
            if (mdUpdateType != null && MsgUtil.isTagInList(TagNum.MDUpdateType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MDUpdateType, mdUpdateType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.AggregatedBook, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AggregatedBook, aggregatedBook);
            }
            if (noMDEntryTypes != null) {
                if (MsgUtil.isTagInList(TagNum.NoMDEntryTypes, SECURED_TAGS, secured)) {
                    TagEncoder.encode(bao, TagNum.NoMDEntryTypes, noMDEntryTypes);
                    if (mdEntryTypeGroups != null && mdEntryTypeGroups.length == noMDEntryTypes.intValue()) {
                        for (int i = 0; i < noMDEntryTypes.intValue(); i++) {
                            if (mdEntryTypeGroups[i] != null) {
                                bao.write(mdEntryTypeGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                            }
                        }
                    } else {
                        String error = "MDEntryTypeGroups field has been set but there is no data or the number of groups does not match.";
                        LOGGER.severe(error);
                        throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoMDEntryTypes.getValue(), error);
                    }
                }
            }
            if (noRelatedSym != null) {
                if (MsgUtil.isTagInList(TagNum.NoRelatedSym, SECURED_TAGS, secured)) {
                    TagEncoder.encode(bao, TagNum.NoRelatedSym, noRelatedSym);
                    if (mdReqGroups != null && mdReqGroups.length == noRelatedSym.intValue()) {
                        for (int i = 0; i < noRelatedSym.intValue(); i++) {
                            if (mdReqGroups[i] != null) {
                                bao.write(mdReqGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                            }
                        }
                    } else {
                        String error = "MDReqGroups field has been set but there is no data or the number of groups does not match.";
                        LOGGER.severe(error);
                        throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoRelatedSym.getValue(), error);
                    }
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
        if (MD_ENTRY_TYPE_GROUP_TAGS.contains(tag.tagNum)) {
            if (noMDEntryTypes != null && noMDEntryTypes.intValue() > 0) {
                message.reset();
                mdEntryTypeGroups = new MDEntryTypeGroup[noMDEntryTypes.intValue()];
                for (int i = 0; i < noMDEntryTypes.intValue(); i++) {
                    MDEntryTypeGroup group = new MDEntryTypeGroup42(context);
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
                    MDReqGroup group = new MDReqGroup42(context);
                    group.decode(message);
                    mdReqGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MarketDataRequestMsg] message version [4.2].";
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
