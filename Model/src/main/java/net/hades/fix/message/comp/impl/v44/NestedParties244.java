/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedParties44.java
 *
 * $Id: NestedParties244.java,v 1.2 2011-04-14 23:44:59 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.NestedParties2;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.Nested2PartyGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
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

import javax.xml.bind.annotation.XmlTransient;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.impl.v44.Nested2PartyGroup44;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX version 4.4 implementation of NestedParties2 component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 07/04/2009, 8:40:36 AM
 */
@XmlTransient
public class NestedParties244 extends NestedParties2 {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> NESTED2_PARTY_GROUP_TAGS = new Nested2PartyGroup44().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NESTED2_PARTY_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NESTED2_PARTY_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NestedParties244() {
        super();
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public NestedParties244(FragmentContext context) {
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
    public Nested2PartyGroup[] getNested2PartyIDGroups() {
        return nested2PartyGroups;
    }

    public void setNested2PartyIDGroups(Nested2PartyGroup[] nested2PartyGroups) {
        this.nested2PartyGroups = nested2PartyGroups;
        if (nested2PartyGroups != null) {
            noNested2PartyIDs = new Integer(nested2PartyGroups.length);
        }
    }

    @Override
    public void setNoNested2PartyIDs(Integer noNested2PartyIDs) {
        this.noNested2PartyIDs = noNested2PartyIDs;
        if (noNested2PartyIDs != null) {
            nested2PartyGroups = new Nested2PartyGroup[noNested2PartyIDs.intValue()];
            for (int i = 0; i < nested2PartyGroups.length; i++) {
                nested2PartyGroups[i] = new Nested2PartyGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @Override
    public Nested2PartyGroup addNested2PartyGroup() {
        Nested2PartyGroup group = new Nested2PartyGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<Nested2PartyGroup> groups = new ArrayList<Nested2PartyGroup>();
        if (nested2PartyGroups != null && nested2PartyGroups.length > 0) {
            groups = new ArrayList<Nested2PartyGroup>(Arrays.asList(nested2PartyGroups));
        }
        groups.add(group);
        nested2PartyGroups = groups.toArray(new Nested2PartyGroup[groups.size()]);
        noNested2PartyIDs = new Integer(nested2PartyGroups.length);

        return group;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (MsgUtil.isTagInList(TagNum.NoNested2PartyIDs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoNested2PartyIDs, noNested2PartyIDs);
                if (nested2PartyGroups != null && nested2PartyGroups.length == noNested2PartyIDs.intValue()) {
                    for (int i = 0; i < noNested2PartyIDs.intValue(); i++) {
                        if (nested2PartyGroups[i] != null) {
                            bao.write(nested2PartyGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "Nested2PartyGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoNested2PartyIDs.getValue(), error);
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
        if (NESTED2_PARTY_GROUP_TAGS.contains(tag.tagNum)) {
            if (noNested2PartyIDs != null && noNested2PartyIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
                message.reset();
                if (nested2PartyGroups == null) {
                    nested2PartyGroups = new Nested2PartyGroup[noNested2PartyIDs.intValue()];
                }
                for (int i = 0; i < nested2PartyGroups.length; i++) {
                    Nested2PartyGroup group = new Nested2PartyGroup44(context);
                    group.decode(message);
                    nested2PartyGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [NestedParties2] component version [4.4].";
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
