/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PartyGroup.java
 *
 * $Id: PartyGroup.java,v 1.12 2011-05-21 23:53:24 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

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
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.util.TagEncoder;

/**
 * PartyGroup group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.12 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class PartyGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.PartyID.getValue(),
        TagNum.PartyIDSource.getValue(),
        TagNum.PartyRole.getValue(),
        TagNum.NoPartySubIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 448. Starting with 4.3 version.
     */
    protected String partyID;

    /**
     * TagNum = 447. Starting with 4.3 version.
     */
    protected PartyIDSource partyIDSource;

    /**
     * TagNum = 452. Starting with 4.3 version.
     */
    protected PartyRole partyRole;

    /**
     * TagNum = 523. Starting with 4.3 version.
     */
    protected String partySubID;

    /**
     * TagNum = 802. Starting with 4.4 version.
     */
    protected Integer noPartySubIDs;

    /**
     * Starting with 4.4 version.
     */
    protected PartySubGroup[] partySubIDGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public PartyGroup() {
    }

    public PartyGroup(FragmentContext context) {
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
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.PartyID)
    public String getPartyID() {
        return partyID;
    }

    /**
     * Message field setter.
     * @param partyID field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.PartyID)
    public void setPartyID(String partyID) {
        this.partyID = partyID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.PartyIDSource)
    public PartyIDSource getPartyIDSource() {
        return partyIDSource;
    }

    /**
     * Message field setter.
     * @param partyIDSource field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.PartyIDSource)
    public void setPartyIDSource(PartyIDSource partyIDSource) {
        this.partyIDSource = partyIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.PartyRole)
    public PartyRole getPartyRole() {
        return partyRole;
    }

    /**
     * Message field setter.
     * @param partyRole field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.PartyRole)
    public void setPartyRole(PartyRole partyRole) {
        this.partyRole = partyRole;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    @TagNumRef(tagNum = TagNum.NoPartySubIDs)
    public Integer getNoPartySubIDs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link PartySubGroup} groups. It will also create an array
     * of {@link PartySubGroup} objects and set the <code>partySubIDGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>partySubIDGroups</code> array they will be discarded.<br/>
     * @param noPartySubIDs field value
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    @TagNumRef(tagNum = TagNum.NoPartySubIDs)
    public void setNoPartySubIDs(Integer noPartySubIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link PartySubGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    public PartySubGroup[] getPartySubIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method adds a {@link PartySubGroup} object to the existing array of <code>partySubIDGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noPartySubIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoPartySubIDs</code> method has been called there will already be a number of objects in the
     * <code>partySubIDGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    public PartySubGroup addPartySubIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link PartySubGroup} object from the existing array of <code>partySubIDGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noPartySubIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    public PartySubGroup deletePartySubIDGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link PartySubGroup} objects from the <code>partySubIDGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noPartySubIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    public int clearPartySubIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3", retired = "4.4")
    @TagNumRef(tagNum = TagNum.PartySubID)
    public String getPartySubID() {
        return partySubID;
    }

    /**
     * Message field setter.
     * @param partySubID field value
     */
    @FIXVersion(introduced = "4.3", retired = "4.4")
    @TagNumRef(tagNum = TagNum.PartySubID)
    public void setPartySubID(String partySubID) {
        this.partySubID = partySubID;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.PartyID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (partyID == null || partyID.trim().isEmpty()) {
            errorMsg.append(" [PartyID]");
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
            TagEncoder.encode(bao, TagNum.PartyID, partyID);
            if (partyIDSource != null) {
                TagEncoder.encode(bao, TagNum.PartyIDSource, partyIDSource.getValue());
            }
            if (partyRole != null) {
                TagEncoder.encode(bao, TagNum.PartyRole, partyRole.getValue());
            }
            TagEncoder.encode(bao, TagNum.PartySubID, partySubID);
            if (noPartySubIDs != null) {
                TagEncoder.encode(bao, TagNum.NoPartySubIDs, noPartySubIDs);
                if (partySubIDGroups != null && partySubIDGroups.length == noPartySubIDs.intValue()) {
                    for (int i = 0; i < noPartySubIDs.intValue(); i++) {
                        if (partySubIDGroups[i] != null) {
                            bao.write(partySubIDGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "PartySubGroup field has been set but there is no data or the number of groups does not match.";
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
            case PartyID:
                partyID = new String(tag.value, sessionCharset);
                break;

            case PartyIDSource:
                partyIDSource = PartyIDSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case PartyRole:
                partyRole = PartyRole.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)).intValue());
                break;

            case PartySubID:
                partySubID = new String(tag.value, sessionCharset);
                break;

            case NoPartySubIDs:
                noPartySubIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [PartyGroup] fields.";
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
        b.append("{PartyGroup=");
        printTagValue(b, TagNum.PartyID, partyID);
        printTagValue(b, TagNum.PartyIDSource, partyIDSource);
        printTagValue(b, TagNum.PartyRole, partyRole);
        printTagValue(b, TagNum.PartySubID, partySubID);
        printTagValue(b, TagNum.NoPartySubIDs, noPartySubIDs);
        printTagValue(b, partySubIDGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>

}
