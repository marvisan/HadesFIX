/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedPartyGroup.java
 *
 * $Id: NestedPartyGroup.java,v 1.11 2011-02-13 06:52:37 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Nested party group data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 * @created 06/04/2009, 3:20:21 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class NestedPartyGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.NestedPartyID.getValue(),
        TagNum.NestedPartyIDSource.getValue(),
        TagNum.NestedPartyRole.getValue(),
        TagNum.NoNestedPartySubIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 524. Starting with 4.3 version.
     */
    protected String nestedPartyID;

    /**
     * TagNum = 525. Starting with 4.3 version.
     */
    protected PartyIDSource nestedPartyIDSource;

    /**
     * TagNum = 538. Starting with 4.3 version.
     */
    protected PartyRole nestedPartyRole;

    /**
     * TagNum = 545. Starting with 4.3 version.
     */
    protected String nestedPartySubID;

    /**
     * TagNum = 804. Starting with 4.4 version.
     */
    protected Integer noNestedPartySubIDs;

    /**
     * Starting with 4.4 version.
     */
    protected NestedPartySubGroup[] nstdPtysSubGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public NestedPartyGroup() {
    }

    public NestedPartyGroup(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.NestedPartyID)
    public String getNestedPartyID() {
        return nestedPartyID;
    }

    /**
     * Message field setter.
     * @param nestedPartyID field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.NestedPartyID)
    public void setNestedPartyID(String nestedPartyID) {
        this.nestedPartyID = nestedPartyID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.NestedPartyIDSource)
    public PartyIDSource getNestedPartyIDSource() {
        return nestedPartyIDSource;
    }

    /**
     * Message field setter.
     * @param nestedPartyIDSource field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.NestedPartyIDSource)
    public void setNestedPartyIDSource(PartyIDSource nestedPartyIDSource) {
        this.nestedPartyIDSource = nestedPartyIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.NestedPartyRole)
    public PartyRole getNestedPartyRole() {
        return nestedPartyRole;
    }

    /**
     * Message field setter.
     * @param nestedPartyRole field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.NestedPartyRole)
    public void setNestedPartyRole(PartyRole nestedPartyRole) {
        this.nestedPartyRole = nestedPartyRole;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3", retired = "4.4")
    @TagNumRef(tagNum = TagNum.NestedPartySubID)
    public String getNestedPartySubID() {
        return nestedPartySubID;
    }

    /**
     * Message field setter.
     * @param nestedPartySubID field value
     */
    @FIXVersion(introduced = "4.3", retired = "4.4")
    @TagNumRef(tagNum = TagNum.NestedPartySubID)
    public void setNestedPartySubID(String nestedPartySubID) {
        this.nestedPartySubID = nestedPartySubID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    @TagNumRef(tagNum = TagNum.NoNestedPartySubIDs)
    public Integer getNoNestedPartySubIDs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link NestedPartySubGroup} groups. It will also create an array
     * of {@link NestedPartySubGroup} objects and set the <code>nstdPtysSubGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>nstdPtysSubGroups</code> array they will be discarded.<br/>
     * @param noNestedPartySubIDs field value
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    @TagNumRef(tagNum = TagNum.NoNestedPartySubIDs)
    public void setNoNestedPartySubIDs(Integer noNestedPartySubIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link NestedPartySubGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4", retired = "5.0SP2")
    public NestedPartySubGroup[] getNstdPtysSubGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link NestedPartySubGroup} object to the existing array of <code>nstdPtysSubGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noNestedPartySubIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoNestedPartySubIDs</code> method has been called there will already be a number of objects in the
     * <code>nstdPtysSubGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    public NestedPartySubGroup addNstdPtysSubGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link NestedPartySubGroup} object from the existing array of <code>nstdPtysSubGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noNestedPartySubIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    public NestedPartySubGroup deleteNstdPtysSubGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link NestedPartySubGroup} objects from the <code>nstdPtysSubGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noNestedPartySubIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4", retired = "5.0SP2")
    public int clearNstdPtysSubGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.NestedPartyID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (nestedPartyID == null || nestedPartyID.trim().isEmpty()) {
            errorMsg.append(" [NestedPartyID]");
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
            TagEncoder.encode(bao, TagNum.NestedPartyID, nestedPartyID);
            if (nestedPartyIDSource != null) {
                TagEncoder.encode(bao, TagNum.NestedPartyIDSource, nestedPartyIDSource.getValue());
            }
            if (nestedPartyRole != null) {
                TagEncoder.encode(bao, TagNum.NestedPartyRole, nestedPartyRole.getValue());
            }
            TagEncoder.encode(bao, TagNum.NestedPartySubID, nestedPartySubID);
            if (noNestedPartySubIDs != null) {
                TagEncoder.encode(bao, TagNum.NoNestedPartySubIDs, noNestedPartySubIDs);
                if (nstdPtysSubGroups != null && nstdPtysSubGroups.length == noNestedPartySubIDs.intValue()) {
                    for (int i = 0; i < noNestedPartySubIDs.intValue(); i++) {
                        if (nstdPtysSubGroups[i] != null) {
                            bao.write(nstdPtysSubGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "NestedPartySubGroup field has been set but there is no data or the number of groups does not match.";
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
            case NestedPartyID:
                nestedPartyID = new String(tag.value, sessionCharset);
                break;

            case NestedPartyIDSource:
                nestedPartyIDSource = PartyIDSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case NestedPartyRole:
                nestedPartyRole = PartyRole.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)).intValue());
                break;

            case NestedPartySubID:
                nestedPartySubID = new String(tag.value, sessionCharset);
                break;

            case NoNestedPartySubIDs:
                noNestedPartySubIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [NestedPartyGroup] fields.";
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
        b.append("{NestedPartyGroup=");
        printTagValue(b, TagNum.NestedPartyID, nestedPartyID);
        printTagValue(b, TagNum.NestedPartyIDSource, nestedPartyIDSource);
        printTagValue(b, TagNum.NestedPartyRole, nestedPartyRole);
        printTagValue(b, TagNum.NestedPartySubID, nestedPartySubID);
        printTagValue(b, TagNum.NoNestedPartySubIDs, noNestedPartySubIDs);
        printTagValue(b, nstdPtysSubGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
