/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityTypesMsg43.java
 *
 * $Id: SecurityTypesMsg43.java,v 1.1 2011-04-27 01:09:58 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.Header;
import net.hades.fix.message.SecurityTypesMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.SecTypesGroup;
import net.hades.fix.message.group.impl.v43.SecTypesGroup43;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 SecurityTypesMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 21/04/2009, 9:32:41 AM
 */
public class SecurityTypesMsg43 extends SecurityTypesMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> SEC_TYPES_GROUP_TAGS = new SecTypesGroup43().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(SEC_TYPES_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(SEC_TYPES_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SecurityTypesMsg43() {
        super();
    }

    public SecurityTypesMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SecurityTypesMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SecurityTypesMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
    public Integer getNoSecurityTypes() {
        return noSecurityTypes;
    }

    @Override
    public void setNoSecurityTypes(Integer noSecurityTypes) {
        this.noSecurityTypes = noSecurityTypes;
        if (noSecurityTypes != null) {
            secTypesGroups = new SecTypesGroup[noSecurityTypes.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < secTypesGroups.length; i++) {
                secTypesGroups[i] = new SecTypesGroup43(context);
            }
        }
    }

    @Override
    public SecTypesGroup[] getSecTypesGroups() {
        return secTypesGroups;
    }

    @Override
    public SecTypesGroup addSecTypesGroup() {
        SecTypesGroup group = new SecTypesGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<SecTypesGroup> groups = new ArrayList<SecTypesGroup>();
        if (secTypesGroups != null && secTypesGroups.length > 0) {
            groups = new ArrayList<SecTypesGroup>(Arrays.asList(secTypesGroups));
        }
        groups.add(group);
        secTypesGroups = groups.toArray(new SecTypesGroup[groups.size()]);
        noSecurityTypes = new Integer(secTypesGroups.length);

        return group;
    }

    @Override
    public SecTypesGroup deleteSecTypesGroup(int index) {
        SecTypesGroup result = null;
        if (secTypesGroups != null && secTypesGroups.length > 0 && secTypesGroups.length > index) {
            List<SecTypesGroup> groups = new ArrayList<SecTypesGroup>(Arrays.asList(secTypesGroups));
            result = groups.remove(index);
            secTypesGroups = groups.toArray(new SecTypesGroup[groups.size()]);
            if (secTypesGroups.length > 0) {
                noSecurityTypes = new Integer(secTypesGroups.length);
            } else {
                secTypesGroups = null;
                noSecurityTypes = null;
            }
        }

        return result;
    }

    @Override
    public int clearSecTypesGroups() {
        int result = 0;
        if (secTypesGroups != null && secTypesGroups.length > 0) {
            result = secTypesGroups.length;
            secTypesGroups = null;
            noSecurityTypes = null;
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
            if (MsgUtil.isTagInList(TagNum.SecurityReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityReqID, securityReqID);
            }
            if (MsgUtil.isTagInList(TagNum.SecurityResponseID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityResponseID, securityResponseID);
            }
            if (securityResponseType != null && MsgUtil.isTagInList(TagNum.SecurityResponseType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityResponseType, securityResponseType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TotNoSecurityTypes, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotNoSecurityTypes, totNoSecurityTypes);
            }
            if (noSecurityTypes != null && MsgUtil.isTagInList(TagNum.NoSecurityTypes, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoSecurityTypes, noSecurityTypes);
                if (secTypesGroups != null && secTypesGroups.length == noSecurityTypes.intValue()) {
                    for (int i = 0; i < noSecurityTypes.intValue(); i++) {
                        if (secTypesGroups[i] != null) {
                            bao.write(secTypesGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "SecTypesGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoSecurityTypes.getValue(), error);
                }
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
            }
            if (encodedTextLen != null && encodedTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedTextLen, SECURED_TAGS, secured)) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            }
            if (MsgUtil.isTagInList(TagNum.TradingSessionSubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            }
            if (subscriptionRequestType != null && MsgUtil.isTagInList(TagNum.SubscriptionRequestType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SubscriptionRequestType, subscriptionRequestType.getValue());
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
        if (SEC_TYPES_GROUP_TAGS.contains(tag.tagNum)) {
            if (noSecurityTypes != null && noSecurityTypes.intValue() > 0) {
                message.reset();
                secTypesGroups = new SecTypesGroup[noSecurityTypes.intValue()];
                for (int i = 0; i < noSecurityTypes.intValue(); i++) {
                    SecTypesGroup component = new SecTypesGroup43(context);
                    component.decode(message);
                    secTypesGroups[i] = component;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SecurityTypesMsg] message version [4.3].";
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
