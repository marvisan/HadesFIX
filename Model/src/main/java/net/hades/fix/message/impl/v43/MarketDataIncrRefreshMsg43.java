/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MarketDataIncrRefreshMsg43.java
 *
 * $Id: MarketDataIncrRefreshMsg43.java,v 1.8 2011-04-14 23:44:33 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.MarketDataIncrRefreshMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.MDIncGroup;
import net.hades.fix.message.group.impl.v43.MDIncGroup43;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

/**
 * FIX version 4.3 MarketDataIncrRefreshMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 * @created 01/04/2009, 8:41:14 AM
 */
@XmlTransient
public class MarketDataIncrRefreshMsg43 extends MarketDataIncrRefreshMsg {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> MD_INC_GROUP_TAGS = new MDIncGroup43().getFragmentAllTags();

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(MD_INC_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(MD_INC_GROUP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public MarketDataIncrRefreshMsg43() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public MarketDataIncrRefreshMsg43(Header header, ByteBuffer rawMsg)
        throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public MarketDataIncrRefreshMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public MarketDataIncrRefreshMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < mdIncGroups.length; i++) {
                mdIncGroups[i] = new MDIncGroup43(context);
            }
        }
    }

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
        MDIncGroup group = new MDIncGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
            if (noMDEntries != null && MsgUtil.isTagInList(TagNum.NoMDEntries, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoMDEntries, noMDEntries);
                if (mdIncGroups != null && mdIncGroups.length == noMDEntries.intValue()) {
                    for (int i = 0; i < noMDEntries.intValue(); i++) {
                        if (mdIncGroups[i] != null) {
                            bao.write(mdIncGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "MDIncGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoMDEntries.getValue(), error);
                }
            }
            if (applQueueAction != null && MsgUtil.isTagInList(TagNum.ApplQueueAction, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ApplQueueAction, applQueueAction.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.ApplQueueMax, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ApplQueueMax, applQueueMax);
            }

            result = bao.toByteArray();
        } catch (IOException ex) {
            String error = "Error writing to the byte array.";
            LOGGER.severe(error + " Error was : " + ex.toString());
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
        throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (MD_INC_GROUP_TAGS.contains(tag.tagNum)) {
            if (noMDEntries != null && noMDEntries.intValue() > 0) {
                message.reset();
                mdIncGroups = new MDIncGroup[noMDEntries.intValue()];
                for (int i = 0; i < noMDEntries.intValue(); i++) {
                    MDIncGroup component = new MDIncGroup43(context);
                    component.decode(message);
                    mdIncGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [MarketDataIncrRefreshMsg] message version [4.3].";
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
