/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedPartyGroup44.java
 *
 * $Id: NestedPartyGroup44.java,v 1.11 2011-04-14 23:44:46 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.NestedPartySubGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of NestedPartyGroup group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 06/04/2009, 3:22:58 PM
 */
@XmlRootElement(name="Pty")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class NestedPartyGroup44 extends NestedPartyGroup {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> NSTD_PARTY_SUB_GROUP_TAGS = new NestedPartySubGroup44().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(NSTD_PARTY_SUB_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(NSTD_PARTY_SUB_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NestedPartyGroup44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public NestedPartyGroup44(FragmentContext context) {
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

    @XmlAttribute(name = "ID")
    @Override
    public String getNestedPartyID() {
        return nestedPartyID;
    }

    @Override
    public void setNestedPartyID(String nestedPartyID) {
        this.nestedPartyID = nestedPartyID;
    }

    @XmlAttribute(name = "Src")
    @Override
    public PartyIDSource getNestedPartyIDSource() {
        return nestedPartyIDSource;
    }

    @Override
    public void setNestedPartyIDSource(PartyIDSource nestedPartyIDSource) {
        this.nestedPartyIDSource = nestedPartyIDSource;
    }

    @XmlAttribute(name = "R")
    @Override
    public PartyRole getNestedPartyRole() {
        return nestedPartyRole;
    }

    @Override
    public void setNestedPartyRole(PartyRole nestedPartyRole) {
        this.nestedPartyRole = nestedPartyRole;
    }

    @Override
    public Integer getNoNestedPartySubIDs() {
        return noNestedPartySubIDs;
    }

    @Override
    public void setNoNestedPartySubIDs(Integer noNestedPartySubIDs) {
        this.noNestedPartySubIDs = noNestedPartySubIDs;
        if (noNestedPartySubIDs != null) {
            nstdPtysSubGroups = new NestedPartySubGroup[noNestedPartySubIDs.intValue()];
            for (int i = 0; i < nstdPtysSubGroups.length; i++) {
                nstdPtysSubGroups[i] = new NestedPartySubGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public NestedPartySubGroup[] getNstdPtysSubGroups() {
        return nstdPtysSubGroups;
    }

    public void setNstdPtysSubGroups(NestedPartySubGroup[] nstdPtysSubGroups) {
        this.nstdPtysSubGroups = nstdPtysSubGroups;
        if (nstdPtysSubGroups != null) {
            noNestedPartySubIDs = new Integer(nstdPtysSubGroups.length);
        }
    }

    @Override
    public NestedPartySubGroup addNstdPtysSubGroup() {
        
        NestedPartySubGroup group = new NestedPartySubGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<NestedPartySubGroup> groups = new ArrayList<NestedPartySubGroup>();
        if (nstdPtysSubGroups != null && nstdPtysSubGroups.length > 0) {
            groups = new ArrayList<NestedPartySubGroup>(Arrays.asList(nstdPtysSubGroups));
        }
        groups.add(group);
        nstdPtysSubGroups = groups.toArray(new NestedPartySubGroup[groups.size()]);
        noNestedPartySubIDs = new Integer(nstdPtysSubGroups.length);

        return group;
    }

    @Override
    public NestedPartySubGroup deleteNstdPtysSubGroup(int index) {

        NestedPartySubGroup result = null;
        if (nstdPtysSubGroups != null && nstdPtysSubGroups.length > 0 && nstdPtysSubGroups.length > index) {
            List<NestedPartySubGroup> groups = new ArrayList<NestedPartySubGroup>(Arrays.asList(nstdPtysSubGroups));
            result = groups.remove(index);
            nstdPtysSubGroups = groups.toArray(new NestedPartySubGroup[groups.size()]);
            if (nstdPtysSubGroups.length > 0) {
                noNestedPartySubIDs = new Integer(nstdPtysSubGroups.length);
            } else {
                nstdPtysSubGroups = null;
                noNestedPartySubIDs = null;
            }
        }

        return result;
    }

    @Override
    public int clearNstdPtysSubGroups() {

        int result = 0;
        if (nstdPtysSubGroups != null && nstdPtysSubGroups.length > 0) {
            result = nstdPtysSubGroups.length;
            nstdPtysSubGroups = null;
            noNestedPartySubIDs = null;
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
            if (MsgUtil.isTagInList(TagNum.NestedPartyID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NestedPartyID, nestedPartyID);
            }
            if (nestedPartyIDSource != null && MsgUtil.isTagInList(TagNum.NestedPartyIDSource, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NestedPartyIDSource, nestedPartyIDSource.getValue());
            }
            if (nestedPartyRole != null && MsgUtil.isTagInList(TagNum.NestedPartyRole, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NestedPartyRole, nestedPartyRole.getValue());
            }
            if (noNestedPartySubIDs != null && MsgUtil.isTagInList(TagNum.NoNestedPartySubIDs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoNestedPartySubIDs, noNestedPartySubIDs);
                if (nstdPtysSubGroups != null && nstdPtysSubGroups.length == noNestedPartySubIDs.intValue()) {
                    for (int i = 0; i < noNestedPartySubIDs.intValue(); i++) {
                        if (nstdPtysSubGroups[i] != null) {
                            bao.write(nstdPtysSubGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "NesstedPartysSubGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoNestedPartySubIDs.getValue(), error);
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
        if (NSTD_PARTY_SUB_GROUP_TAGS.contains(tag.tagNum)) {
            if (noNestedPartySubIDs != null && noNestedPartySubIDs.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
                message.reset();
                if (nstdPtysSubGroups == null) {
                    nstdPtysSubGroups = new NestedPartySubGroup[noNestedPartySubIDs.intValue()];
                }
                for (int i = 0; i < nstdPtysSubGroups.length; i++) {
                    NestedPartySubGroup group = new NestedPartySubGroup44(context);
                    group.decode(message);
                    nstdPtysSubGroups[i] = group;
                }
            }
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
        return "This tag is not supported in [NestedPartyGroup] group version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
