/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UndlyInstrumentPartyIDGroup.java
 *
 * $Id: UndlyInstrumentPartyIDGroup.java,v 1.9 2010-11-23 10:20:17 vrotaru Exp $
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Underlying instrument party ID group.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 02/01/2009, 11:05:26 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class UndlyInstrumentPartyIDGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.UndlyInstrumentPartyID.getValue(),
        TagNum.UndlyInstrumentPartyIDSource.getValue(),
        TagNum.UndlyInstrumentPartyRole.getValue(),
        TagNum.NoUndlyInstrumentPartySubIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1059. Starting with 5.0 version.
     */
    protected String undlyInstrumentPartyID;

    /**
     * TagNum = 1060. Starting with 5.0 version.
     */
    protected PartyIDSource undlyInstrumentPartyIDSource;

    /**
     * TagNum = 1061. Starting with 5.0 version.
     */
    protected PartyRole undlyInstrumentPartyRole;

    /**
     * TagNum = 1062. Starting with 5.0 version.
     */
    protected Integer noUndlyInstrumentPartySubIDGroups;

    /**
     * Starting with 5.0 version.
     */
    protected UndlyInstrumentPartySubIDGroup[] undlyInstrumentPartySubIDGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public UndlyInstrumentPartyIDGroup() {
    }

    public UndlyInstrumentPartyIDGroup(FragmentContext context) {
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
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UndlyInstrumentPartyID)
    public String getUndlyInstrumentPartyID() {
        return undlyInstrumentPartyID;
    }

    /**
     * Message field setter.
     * @param undlyInstrumentPartyID field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UndlyInstrumentPartyID)
    public void setUndlyInstrumentPartyID(String undlyInstrumentPartyID) {
        this.undlyInstrumentPartyID = undlyInstrumentPartyID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UndlyInstrumentPartyIDSource)
    public PartyIDSource getUndlyInstrumentPartyIDSource() {
        return undlyInstrumentPartyIDSource;
    }

    /**
     * Message field setter.
     * @param undlyInstrumentPartyIDSource field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UndlyInstrumentPartyIDSource)
    public void setUndlyInstrumentPartyIDSource(PartyIDSource undlyInstrumentPartyIDSource) {
        this.undlyInstrumentPartyIDSource = undlyInstrumentPartyIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UndlyInstrumentPartyRole)
    public PartyRole getUndlyInstrumentPartyRole() {
        return undlyInstrumentPartyRole;
    }

    /**
     * Message field setter.
     * @param undlyInstrumentPartyRole field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.UndlyInstrumentPartyRole)
    public void setUndlyInstrumentPartyRole(PartyRole undlyInstrumentPartyRole) {
        this.undlyInstrumentPartyRole = undlyInstrumentPartyRole;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.NoUndlyInstrumentPartySubIDs)
    public Integer getNoUndlyInstrumentPartySubIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link UndlyInstrumentPartySubIDGroup} groups. It will also create an array
     * of {@link UndlyInstrumentPartySubIDGroup} objects and set the <code>undlyInstrumentPartySubIDGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>undlyInstrumentPartySubIDGroups</code> array they will be discarded.<br/>
     * @param noUndlyInstrumentPartySubIDGroups field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.NoUndlyInstrumentPartySubIDs)
    public void setNoUndlyInstrumentPartySubIDGroups(Integer noUndlyInstrumentPartySubIDGroups) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link UndlyInstrumentPartySubIDGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="5.0")
    public UndlyInstrumentPartySubIDGroup[] getUndlyInstrumentPartySubIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * This method adds a {@link UndlyInstrumentPartySubIDGroup} object to the existing array of <code>undlyInstrumentPartySubIDGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noUndlyInstrumentPartySubIDGroups</code> field to the proper value.<br/>
     * Note: If the <code>setNoUndlyInstrumentPartySubIDGroups</code> method has been called there will already be a number of objects in the
     * <code>undlyInstrumentPartySubIDGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0")
    public UndlyInstrumentPartySubIDGroup addUndlyInstrumentPartySubIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link UndlyInstrumentPartySubIDGroup} object from the existing array of <code>undlyInstrumentPartySubIDGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noUndlyInstrumentPartySubIDGroups</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0")
    public UndlyInstrumentPartySubIDGroup deleteUndlyInstrumentPartySubIDGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link UndlyInstrumentPartySubIDGroup} objects from the <code>undlyInstrumentPartySubIDGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noUndlyInstrumentPartySubIDGroups</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0")
    public int clearUndlyInstrumentPartySubIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }


    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.UndlyInstrumentPartyID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (undlyInstrumentPartyID == null || undlyInstrumentPartyID.trim().isEmpty()) {
            errorMsg.append(" [UndlyInstrumentPartyID]");
            hasMissingTag = true;
        }
        errorMsg.append(" is missing.");
        if (hasMissingTag) {
            throw new TagNotPresentException(errorMsg.toString());
        }
    }

    @Override
    protected byte[] encodeFragmentAll() throws TagNotPresentException, BadFormatMsgException {
        if (validateRequired) {             validateRequiredTags();         }

        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            TagEncoder.encode(bao, TagNum.UndlyInstrumentPartyID, undlyInstrumentPartyID);
            if (undlyInstrumentPartyIDSource != null) {
                TagEncoder.encode(bao, TagNum.UndlyInstrumentPartyIDSource, undlyInstrumentPartyIDSource.getValue());
            }
            if (undlyInstrumentPartyRole != null) {
                TagEncoder.encode(bao, TagNum.UndlyInstrumentPartyRole, undlyInstrumentPartyRole.getValue());
            }
            if (noUndlyInstrumentPartySubIDGroups != null) {
                TagEncoder.encode(bao, TagNum.NoUndlyInstrumentPartySubIDs, noUndlyInstrumentPartySubIDGroups);
                if (undlyInstrumentPartySubIDGroups != null && undlyInstrumentPartySubIDGroups.length == noUndlyInstrumentPartySubIDGroups.intValue()) {
                    for (int i = 0; i < noUndlyInstrumentPartySubIDGroups.intValue(); i++) {
                        if (undlyInstrumentPartySubIDGroups[i] != null) {
                            bao.write(undlyInstrumentPartySubIDGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "UndlyInstrumentPartySubIDGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoInstrumentParties.getValue(), error);
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
            case NoUndlyInstrumentPartySubIDs:
                noUndlyInstrumentPartySubIDGroups = new Integer(new String(tag.value, sessionCharset));
                break;

            case UndlyInstrumentPartyID:
                undlyInstrumentPartyID = new String(tag.value, sessionCharset);
                break;

            case UndlyInstrumentPartyIDSource:
                undlyInstrumentPartyIDSource = PartyIDSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case UndlyInstrumentPartyRole:
                undlyInstrumentPartyRole = PartyRole.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [UndlyInstrumentPartyIDGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    // </editor-fold>

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
        b.append("{UndlyInstrumentPartyIDGroup=");
        printTagValue(b, TagNum.UndlyInstrumentPartyID, undlyInstrumentPartyID);
        printTagValue(b, TagNum.UndlyInstrumentPartyIDSource, undlyInstrumentPartyIDSource);
        printTagValue(b, TagNum.UndlyInstrumentPartyRole, undlyInstrumentPartyRole);
        printTagValue(b, undlyInstrumentPartySubIDGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
