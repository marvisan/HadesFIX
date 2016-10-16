/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityListMsg43.java
 *
 * $Id: SecurityListMsg43.java,v 1.1 2011-04-28 10:07:50 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.SecurityListMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.SecListGroup;
import net.hades.fix.message.group.impl.v43.SecListGroup43;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 SecurityListMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 28/04/2011, 9:32:41 AM
 */
public class SecurityListMsg43 extends SecurityListMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> SEC_LIST_GROUP_TAGS = new SecListGroup43().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(SEC_LIST_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(SEC_LIST_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecurityListMsg43() {
        super();
    }

    public SecurityListMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SecurityListMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SecurityListMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public Integer getNoRelatedSyms() {
        return noRelatedSyms;
    }

    @Override
    public void setNoRelatedSyms(Integer noRelatedSyms) {
        this.noRelatedSyms = noRelatedSyms;
        if (noRelatedSyms != null) {
            secListGroups = new SecListGroup[noRelatedSyms.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < secListGroups.length; i++) {
                secListGroups[i] = new SecListGroup43(context);
            }
        }
    }

    @Override
    public SecListGroup[] getSecListGroups() {
        return secListGroups;
    }

    public void setSecListGroups(SecListGroup[] secListGroups) {
        this.secListGroups = secListGroups;
        if (secListGroups != null) {
            noRelatedSyms = new Integer(secListGroups.length);
        }
    }

    @Override
    public SecListGroup addSecListGroup() {
        SecListGroup group = new SecListGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<SecListGroup> groups = new ArrayList<SecListGroup>();
        if (secListGroups != null && secListGroups.length > 0) {
            groups = new ArrayList<SecListGroup>(Arrays.asList(secListGroups));
        }
        groups.add(group);
        secListGroups = groups.toArray(new SecListGroup[groups.size()]);
        noRelatedSyms = new Integer(secListGroups.length);

        return group;
    }

    @Override
    public SecListGroup deleteSecListGroup(int index) {
        SecListGroup result = null;
        if (secListGroups != null && secListGroups.length > 0 && secListGroups.length > index) {
            List<SecListGroup> groups = new ArrayList<SecListGroup>(Arrays.asList(secListGroups));
            result = groups.remove(index);
            secListGroups = groups.toArray(new SecListGroup[groups.size()]);
            if (secListGroups.length > 0) {
                noRelatedSyms = new Integer(secListGroups.length);
            } else {
                secListGroups = null;
                noRelatedSyms = null;
            }
        }

        return result;
    }

    @Override
    public int clearSecListGroup() {
        int result = 0;
        if (secListGroups != null && secListGroups.length > 0) {
            result = secListGroups.length;
            secListGroups = null;
            noRelatedSyms = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (securityReqID == null || securityReqID.trim().isEmpty()) {
            errorMsg.append(" [SecurityReqID]");
            hasMissingTag = true;
        }
        if (securityRequestResult == null) {
            errorMsg.append(" [SecurityRequestResult]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.SecurityReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityReqID, securityReqID);
            }
            if (securityRequestResult != null && MsgUtil.isTagInList(TagNum.SecurityRequestResult, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityRequestResult, securityRequestResult.getValue());
            }
            if (noRelatedSyms != null && MsgUtil.isTagInList(TagNum.NoRelatedSym, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoRelatedSym, noRelatedSyms);
                if (secListGroups != null && secListGroups.length == noRelatedSyms.intValue()) {
                    for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                        if (secListGroups[i] != null) {
                            bao.write(secListGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "SecListGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoLegs.getValue(), error);
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
        if (SEC_LIST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRelatedSyms != null && noRelatedSyms.intValue() > 0) {
                message.reset();
                secListGroups = new SecListGroup[noRelatedSyms.intValue()];
                for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                    SecListGroup group = new SecListGroup43(context);
                    group.decode(message);
                    secListGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SecurityListMsg] message version [4.3].";
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
