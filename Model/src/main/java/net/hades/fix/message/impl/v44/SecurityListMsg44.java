/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityListMsg44.java
 *
 * $Id: SecurityListMsg44.java,v 1.1 2011-04-28 10:07:49 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.struct.Tag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.SecurityListMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.SecListGroup;
import net.hades.fix.message.group.impl.v44.SecListGroup44;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SecurityRequestResult;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixBooleanAdapter;

/**
 * FIX version 4.4 SecurityListMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 28/04/2011, 9:32:41 AM
 */
@XmlRootElement(name="SecList")
@XmlType(propOrder = {"header", "secListGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class SecurityListMsg44 extends SecurityListMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> SEC_LIST_GROUP_TAGS = new SecListGroup44().getFragmentAllTags();

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

    public SecurityListMsg44() {
        super();
    }

    public SecurityListMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SecurityListMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SecurityListMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        SecurityListMsg44 fixml = (SecurityListMsg44) fragment;
        if (fixml.getSecurityReqID() != null) {
            securityReqID = fixml.getSecurityReqID();
        }
        if (fixml.getSecurityResponseID() != null) {
            securityResponseID = fixml.getSecurityResponseID();
        }
        if (fixml.getSecurityRequestResult() != null) {
            securityRequestResult = fixml.getSecurityRequestResult();
        }
        if (fixml.getTotNoRelatedSym() != null) {
            totNoRelatedSym = fixml.getTotNoRelatedSym();
        }
        if (fixml.getLastFragment() != null) {
            lastFragment = fixml.getLastFragment();
        }
        if (fixml.getSecListGroups() != null && fixml.getSecListGroups().length > 0) {
            setSecListGroups(fixml.getSecListGroups());
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
    public String getSecurityReqID() {
        return securityReqID;
    }

    @Override
    public void setSecurityReqID(String securityReqID) {
        this.securityReqID = securityReqID;
    }

    @XmlAttribute(name = "RspID")
    @Override
    public String getSecurityResponseID() {
        return securityResponseID;
    }

    @Override
    public void setSecurityResponseID(String securityResponseID) {
        this.securityResponseID = securityResponseID;
    }

    @XmlAttribute(name = "ReqRslt")
    @Override
    public SecurityRequestResult getSecurityRequestResult() {
        return securityRequestResult;
    }

    @Override
    public void setSecurityRequestResult(SecurityRequestResult securityRequestResult) {
        this.securityRequestResult = securityRequestResult;
    }

    @XmlAttribute(name = "TotNoReltdSym")
    @Override
    public Integer getTotNoRelatedSym() {
        return totNoRelatedSym;
    }

    @Override
    public void setTotNoRelatedSym(Integer totNoRelatedSym) {
        this.totNoRelatedSym = totNoRelatedSym;
    }

    @XmlAttribute(name = "LastFragment")
    @XmlJavaTypeAdapter(FixBooleanAdapter.class)
    @Override
    public Boolean getLastFragment() {
        return lastFragment;
    }

    @Override
    public void setLastFragment(Boolean lastFragment) {
        this.lastFragment = lastFragment;
    }

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
                secListGroups[i] = new SecListGroup44(context);
            }
        }
    }

    @XmlElementRef
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
        SecListGroup group = new SecListGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
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
        if (securityResponseID == null) {
            errorMsg.append(" [SecurityResponseID]");
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
            if (MsgUtil.isTagInList(TagNum.SecurityResponseID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityResponseID, securityResponseID);
            }
            if (securityRequestResult != null && MsgUtil.isTagInList(TagNum.SecurityRequestResult, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityRequestResult, securityRequestResult.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.TotNoRelatedSym, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotNoRelatedSym, totNoRelatedSym);
            }
            if (MsgUtil.isTagInList(TagNum.LastFragment, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LastFragment, lastFragment);
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
                    SecListGroup group = new SecListGroup44(context);
                    group.decode(message);
                    secListGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SecurityListMsg] message version [4.4].";
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
