/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeSecurityListMsg43.java
 *
 * $Id: DerivativeSecurityListMsg43.java,v 1.1 2011-09-27 08:57:27 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.DerivativeSecurityListMsg;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v43.UnderlyingInstrument43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.DerivSecListGroup;
import net.hades.fix.message.group.impl.v43.DerivSecListGroup43;
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
 * FIX version 4.3 DerivativeSecurityListMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 28/04/2011, 9:32:41 AM
 */
public class DerivativeSecurityListMsg43 extends DerivativeSecurityListMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> UNDERLYING_INSTRUMENT_COMP_TAGS = new UnderlyingInstrument43().getFragmentAllTags();
    protected static final Set<Integer> DERIV_SEC_LIST_GROUP_TAGS = new DerivSecListGroup43().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(UNDERLYING_INSTRUMENT_COMP_TAGS);
        ALL_TAGS.addAll(DERIV_SEC_LIST_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(UNDERLYING_INSTRUMENT_COMP_TAGS);
        START_COMP_TAGS.addAll(DERIV_SEC_LIST_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeSecurityListMsg43() {
        super();
    }

    public DerivativeSecurityListMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public DerivativeSecurityListMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public DerivativeSecurityListMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
    public UnderlyingInstrument getUnderlyingInstrument() {
        return underlyingInstrument;
    }

    @Override
    public void setUnderlyingInstrument() {
        underlyingInstrument = new UnderlyingInstrument43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
    }
    
    @Override
    public void clearUnderlyingInstrument() {
        underlyingInstrument = null;
    }
    
    public void setUnderlyingInstrument(UnderlyingInstrument underlyingInstrument) {
        this.underlyingInstrument = underlyingInstrument;
    }

    @Override
    public Integer getNoRelatedSyms() {
        return noRelatedSyms;
    }

    @Override
    public void setNoRelatedSyms(Integer noRelatedSyms) {
        this.noRelatedSyms = noRelatedSyms;
        if (noRelatedSyms != null) {
            derivSecListGroups = new DerivSecListGroup[noRelatedSyms.intValue()];
            FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
            for (int i = 0; i < derivSecListGroups.length; i++) {
                derivSecListGroups[i] = new DerivSecListGroup43(context);
            }
        }
    }

    @Override
    public DerivSecListGroup[] getDerivSecListGroups() {
        return derivSecListGroups;
    }

    public void setDerivSecListGroups(DerivSecListGroup[] derivSecListGroups) {
        this.derivSecListGroups = derivSecListGroups;
        if (derivSecListGroups != null) {
            noRelatedSyms = new Integer(derivSecListGroups.length);
        }
    }

    @Override
    public DerivSecListGroup addDerivSecListGroup() {
        DerivSecListGroup group = new DerivSecListGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<DerivSecListGroup> groups = new ArrayList<DerivSecListGroup>();
        if (derivSecListGroups != null && derivSecListGroups.length > 0) {
            groups = new ArrayList<DerivSecListGroup>(Arrays.asList(derivSecListGroups));
        }
        groups.add(group);
        derivSecListGroups = groups.toArray(new DerivSecListGroup[groups.size()]);
        noRelatedSyms = new Integer(derivSecListGroups.length);

        return group;
    }

    @Override
    public DerivSecListGroup deleteDerivSecListGroup(int index) {
        DerivSecListGroup result = null;
        if (derivSecListGroups != null && derivSecListGroups.length > 0 && derivSecListGroups.length > index) {
            List<DerivSecListGroup> groups = new ArrayList<DerivSecListGroup>(Arrays.asList(derivSecListGroups));
            result = groups.remove(index);
            derivSecListGroups = groups.toArray(new DerivSecListGroup[groups.size()]);
            if (derivSecListGroups.length > 0) {
                noRelatedSyms = new Integer(derivSecListGroups.length);
            } else {
                derivSecListGroups = null;
                noRelatedSyms = null;
            }
        }

        return result;
    }

    @Override
    public int clearDerivSecListGroup() {
        int result = 0;
        if (derivSecListGroups != null && derivSecListGroups.length > 0) {
            result = derivSecListGroups.length;
            derivSecListGroups = null;
            noRelatedSyms = null;
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
            if (securityRequestResult != null && MsgUtil.isTagInList(TagNum.SecurityRequestResult, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityRequestResult, securityRequestResult.getValue());
            }
            if (underlyingInstrument != null) {
                bao.write(underlyingInstrument.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.TotNoRelatedSym, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.TotNoRelatedSym, totNoRelatedSym);
            }
            if (noRelatedSyms != null && MsgUtil.isTagInList(TagNum.NoRelatedSym, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoRelatedSym, noRelatedSyms);
                if (derivSecListGroups != null && derivSecListGroups.length == noRelatedSyms.intValue()) {
                    for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                        if (derivSecListGroups[i] != null) {
                            bao.write(derivSecListGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "DerivSecListGroups field has been set but there is no data or the number of groups does not match.";
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
        if (UNDERLYING_INSTRUMENT_COMP_TAGS.contains(tag.tagNum)) {
            if (underlyingInstrument == null) {
                underlyingInstrument = new UnderlyingInstrument43(context);
            }
            underlyingInstrument.decode(tag, message);
        }
        if (DERIV_SEC_LIST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noRelatedSyms != null && noRelatedSyms.intValue() > 0) {
                message.reset();
                derivSecListGroups = new DerivSecListGroup[noRelatedSyms.intValue()];
                for (int i = 0; i < noRelatedSyms.intValue(); i++) {
                    DerivSecListGroup group = new DerivSecListGroup43(context);
                    group.decode(message);
                    derivSecListGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [DerivativeSecurityListMsg] message version [4.3].";
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
