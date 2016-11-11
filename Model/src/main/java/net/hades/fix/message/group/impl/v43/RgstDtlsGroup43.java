/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RgstDtlsGroup43.java
 *
 * $Id: RgstDtlsGroup43.java,v 1.1 2011-10-27 09:16:59 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.comp.impl.v43.NestedParties43;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.RgstDtlsGroup;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.3 implementation of RgstDtlsGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/02/2009, 7:22:35 PM
 */
public class RgstDtlsGroup43 extends RgstDtlsGroup {
    
    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> ALL_TAGS;
    
    protected static final Set<Integer> NESTED_PARTIES_COMP_TAGS = new NestedParties43().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NESTED_PARTIES_COMP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NESTED_PARTIES_COMP_TAGS);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RgstDtlsGroup43() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public RgstDtlsGroup43(FragmentContext context) {
        super(context);
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
    public NestedParties getNestedParties() {
        return nestedParties;
    }

    @Override
    public void setNestedParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.nestedParties = new NestedParties43(context);
    }

    @Override
    public void clearNestedParties() {
        this.nestedParties = null;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        
        try {
            if (MsgUtil.isTagInList(TagNum.RegistDtls, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RegistDtls, registDtls);
            }
            if (MsgUtil.isTagInList(TagNum.RegistEmail, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.RegistEmail, registEmail);
            }
            if (MsgUtil.isTagInList(TagNum.MailingDtls, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MailingDtls, mailingDtls);
            }
            if (MsgUtil.isTagInList(TagNum.MailingInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MailingInst, mailingInst);
            }
            if (nestedParties != null) {
                bao.write(nestedParties.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (ownerType != null && MsgUtil.isTagInList(TagNum.OwnerType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.OwnerType, ownerType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.DateOfBirth, SECURED_TAGS, secured)) {
                TagEncoder.encodeDate(bao, TagNum.DateOfBirth, dateOfBirth);
            }
            if (investorCountryOfResidence != null && MsgUtil.isTagInList(TagNum.InvestorCountryOfResidence, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.InvestorCountryOfResidence, investorCountryOfResidence.getValue());
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
        if (NESTED_PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (nestedParties == null) {
                nestedParties = new NestedParties43(context);
            }
            nestedParties.decode(tag, message);
        }
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [RgstDtlsGroup] group version [4.3].";
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
