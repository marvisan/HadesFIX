/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PartyGroup43.java
 *
 * $Id: PartyGroup43.java,v 1.9 2011-02-16 11:24:35 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.3 implementation of PartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 12/02/2009, 7:22:35 PM
 */
public class PartyGroup43 extends PartyGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS_V43 = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.PartyID.getValue(),
        TagNum.PartyIDSource.getValue(),
        TagNum.PartyRole.getValue(),
        TagNum.PartySubID.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS_V43);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS_V43;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PartyGroup43() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public PartyGroup43(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS_V43;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public String getPartyID() {
        return partyID;
    }

    @Override
    public void setPartyID(String partyID) {
        this.partyID = partyID;
    }

    @Override
    public PartyIDSource getPartyIDSource() {
        return partyIDSource;
    }

    @Override
    public void setPartyIDSource(PartyIDSource partyIDSource) {
        this.partyIDSource = partyIDSource;
    }

    @Override
    public PartyRole getPartyRole() {
        return partyRole;
    }

    @Override
    public void setPartyRole(PartyRole partyRole) {
        this.partyRole = partyRole;
    }

    @Override
    public String getPartySubID() {
        return partySubID;
    }

    @Override
    public void setPartySubID(String partySubID) {
        this.partySubID = partySubID;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.PartyID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PartyID, partyID);
            }
            if (partyIDSource != null && MsgUtil.isTagInList(TagNum.PartyIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PartyIDSource, partyIDSource.getValue());
            }
            if (partyRole != null && MsgUtil.isTagInList(TagNum.PartyRole, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PartyRole, partyRole.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.PartySubID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.PartySubID, partySubID);
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
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [PartyGroup] group version [4.3].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
