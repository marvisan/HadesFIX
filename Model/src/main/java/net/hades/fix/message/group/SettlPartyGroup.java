/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlPartyGroup.java
 *
 * $Id: SettlPartyGroup.java,v 1.3 2011-02-16 11:24:33 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.SessionRejectReason;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Settlement PartyGroup group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class SettlPartyGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SettlPartyID.getValue(),
        TagNum.SettlPartyIDSource.getValue(),
        TagNum.SettlPartyRole.getValue(),
        TagNum.NoSettlPartySubIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 782. Starting with 4.4 version.
     */
    protected String settlPartyID;

    /**
     * TagNum = 783. Starting with 4.4 version.
     */
    protected PartyIDSource settlPartyIDSource;

    /**
     * TagNum = 784. Starting with 4.4 version.
     */
    protected PartyRole settlPartyRole;

    /**
     * TagNum = 801. Starting with 4.4 version.
     */
    protected Integer noSettlPartySubIDs;

    /**
     * Starting with 4.4 version.
     */
    protected SettlPartySubGroup[] settlPartySubIDGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SettlPartyGroup() {
    }

    public SettlPartyGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.PartyID)
    public String getSettlPartyID() {
        return settlPartyID;
    }

    /**
     * Message field setter.
     * @param settlPartyID field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.SettlPartyID)
    public void setSettlPartyID(String settlPartyID) {
        this.settlPartyID = settlPartyID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.SettlPartyIDSource)
    public PartyIDSource getSettlPartyIDSource() {
        return settlPartyIDSource;
    }

    /**
     * Message field setter.
     * @param settlPartyIDSource field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.SettlPartyIDSource)
    public void setSettlPartyIDSource(PartyIDSource settlPartyIDSource) {
        this.settlPartyIDSource = settlPartyIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.SettlPartyRole)
    public PartyRole getSettlPartyRole() {
        return settlPartyRole;
    }

    /**
     * Message field setter.
     * @param settlPartyRole field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.SettlPartyRole)
    public void setSettlPartyRole(PartyRole settlPartyRole) {
        this.settlPartyRole = settlPartyRole;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoSettlPartySubIDs)
    public Integer getNoSettlPartySubIDs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link SettlPartySubGroup} groups. It will also create an array
     * of {@link SettlPartySubGroup} objects and set the <code>settlPartySubIDGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>settlPartySubIDGroups</code> array they will be discarded.<br/>
     * @param noSettlPartySubIDs field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoSettlPartySubIDs)
    public void setNoSettlPartySubIDs(Integer noSettlPartySubIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link SettlPartySubGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public SettlPartySubGroup[] getSettlPartySubIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method adds a {@link SettlPartySubGroup} object to the existing array of <code>settlPartySubIDGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noSettlPartySubIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoSettlPartySubIDs</code> method has been called there will already be a number of objects in the
     * <code>settlPartySubIDGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public SettlPartySubGroup addSettlPartySubIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link SettlPartySubGroup} object from the existing array of <code>settlPartySubIDGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noSettlPartySubIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public SettlPartySubGroup deleteSettlPartySubIDGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link SettlPartySubGroup} objects from the <code>settlPartySubIDGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noSettlPartySubIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearSettlPartySubIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.SettlPartyID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (settlPartyID == null || settlPartyID.trim().isEmpty()) {
            errorMsg.append(" [SettlPartyID]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {
            validateRequiredTags();
        }
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            TagEncoder.encode(bao, TagNum.SettlPartyID, settlPartyID);
            if (settlPartyIDSource != null) {
                TagEncoder.encode(bao, TagNum.SettlPartyIDSource, settlPartyIDSource.getValue());
            }
            if (settlPartyRole != null) {
                TagEncoder.encode(bao, TagNum.SettlPartyRole, settlPartyRole.getValue());
            }
            if (noSettlPartySubIDs != null) {
                TagEncoder.encode(bao, TagNum.NoSettlPartySubIDs, noSettlPartySubIDs);
                if (settlPartySubIDGroups != null && settlPartySubIDGroups.length == noSettlPartySubIDs.intValue()) {
                    for (int i = 0; i < noSettlPartySubIDs.intValue(); i++) {
                        if (settlPartySubIDGroups[i] != null) {
                            bao.write(settlPartySubIDGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "SettlPartySubGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoUnderlyings.getValue(), error);
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
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        if (secured) {
            return new byte[0];
        } else {
            return encodeFragmentAll();
        }
    }

    @Override
    protected void setFragmentTagValue(Tag tag) throws BadFormatMsgException {
        TagNum tagNum = TagNum.fromString(tag.tagNum);
        switch (tagNum) {
            case SettlPartyID:
                settlPartyID = new String(tag.value, sessionCharset);
                break;

            case SettlPartyIDSource:
                settlPartyIDSource = PartyIDSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case SettlPartyRole:
                settlPartyRole = PartyRole.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)).intValue());
                break;

            case NoSettlPartySubIDs:
                noSettlPartySubIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [SettlPartyGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    /// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="toString()">

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder(System.getProperty("line.separator"));
        b.append("{SettlPartyGroup=");
        printTagValue(b, TagNum.SettlPartyID, settlPartyID);
        printTagValue(b, TagNum.SettlPartyIDSource, settlPartyIDSource);
        printTagValue(b, TagNum.SettlPartyRole, settlPartyRole);
        printTagValue(b, TagNum.NoSettlPartySubIDs, noSettlPartySubIDs);
        printTagValue(b, settlPartySubIDGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>

}
