/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties43.java
 *
 * $Id: NestedParties43.java,v 1.11 2011-04-14 23:45:01 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.impl.v43.NestedPartyGroup43;
import net.hades.fix.message.struct.Tag;
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

import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.3 implementation of NestedParties component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 07/04/2009, 8:40:36 AM
 */
public class NestedParties43 extends NestedParties {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> NESTED_PARTY_GROUP_TAGS = new NestedPartyGroup43().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NESTED_PARTY_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NESTED_PARTY_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NestedParties43() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public NestedParties43(FragmentContext context) {
        super(context);
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
    public NestedPartyGroup[] getNestedPartyIDGroups() {
        return nestedPartyGroups;
    }

    @Override
    public void setNoNestedPartyIDs(Integer noNestedPartyIDs) {
        this.noNestedPartyIDs = noNestedPartyIDs;
        if (noNestedPartyIDs != null) {
            nestedPartyGroups = new NestedPartyGroup[noNestedPartyIDs.intValue()];
            for (int i = 0; i < nestedPartyGroups.length; i++) {
                nestedPartyGroups[i] = new NestedPartyGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @Override
    public NestedPartyGroup addNestedPartyGroup() {
        NestedPartyGroup group = new NestedPartyGroup43(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<NestedPartyGroup> groups = new ArrayList<NestedPartyGroup>();
        if (nestedPartyGroups != null && nestedPartyGroups.length > 0) {
            groups = new ArrayList<NestedPartyGroup>(Arrays.asList(nestedPartyGroups));
        }
        groups.add(group);
        nestedPartyGroups = groups.toArray(new NestedPartyGroup[groups.size()]);
        noNestedPartyIDs = new Integer(nestedPartyGroups.length);

        return group;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.NoNestedPartyIDs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoNestedPartyIDs, noNestedPartyIDs);
                if (nestedPartyGroups != null && nestedPartyGroups.length == noNestedPartyIDs.intValue()) {
                    for (int i = 0; i < noNestedPartyIDs.intValue(); i++) {
                        if (nestedPartyGroups[i] != null) {
                            bao.write(nestedPartyGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "NestedPartyGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoNestedPartyIDs.getValue(), error);
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
        if (NESTED_PARTY_GROUP_TAGS.contains(tag.tagNum)) {
            if (noNestedPartyIDs != null && noNestedPartyIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
                message.reset();
                if (nestedPartyGroups == null) {
                    nestedPartyGroups = new NestedPartyGroup[noNestedPartyIDs.intValue()];
                }
                for (int i = 0; i < nestedPartyGroups.length; i++) {
                    NestedPartyGroup group = new NestedPartyGroup43(context);
                    group.decode(message);
                    nestedPartyGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NestedParties] component version [4.3].";
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
