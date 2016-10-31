/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Nested2PartyGroup.java
 *
 * $Id: Nested2PartyGroup.java,v 1.2 2011-02-13 06:52:37 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PartyRole;

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
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Nested party group data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 06/04/2009, 3:20:21 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Nested2PartyGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Nested2PartyID.getValue(),
        TagNum.Nested2PartyIDSource.getValue(),
        TagNum.Nested2PartyRole.getValue(),
        TagNum.NoNested2PartySubIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 757. Starting with 4.4 version.
     */
    protected String nested2PartyID;

    /**
     * TagNum = 758. Starting with 4.4 version.
     */
    protected PartyIDSource nested2PartyIDSource;

    /**
     * TagNum = 759. Starting with 4.4 version.
     */
    protected PartyRole nested2PartyRole;

    /**
     * TagNum = 806. Starting with 4.4 version.
     */
    protected Integer noNested2PartySubIDs;

    /**
     * Starting with 4.4 version.
     */
    protected Nested2PartySubGroup[] nstd2PtysSubGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Nested2PartyGroup() {
    }

    public Nested2PartyGroup(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.Nested2PartyID)
    public String getNested2PartyID() {
        return nested2PartyID;
    }

    /**
     * Message field setter.
     * @param nested2PartyID field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Nested2PartyID)
    public void setNested2PartyID(String nested2PartyID) {
        this.nested2PartyID = nested2PartyID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Nested2PartyIDSource)
    public PartyIDSource getNested2PartyIDSource() {
        return nested2PartyIDSource;
    }

    /**
     * Message field setter.
     * @param nested2PartyIDSource field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Nested2PartyIDSource)
    public void setNested2PartyIDSource(PartyIDSource nested2PartyIDSource) {
        this.nested2PartyIDSource = nested2PartyIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Nested2PartyRole)
    public PartyRole getNested2PartyRole() {
        return nested2PartyRole;
    }

    /**
     * Message field setter.
     * @param nested2PartyRole field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Nested2PartyRole)
    public void setNested2PartyRole(PartyRole nested2PartyRole) {
        this.nested2PartyRole = nested2PartyRole;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoNested2PartySubIDs)
    public Integer getNoNested2PartySubIDs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link Nested2PartySubGroup} groups. It will also create an array
     * of {@link Nested2PartySubGroup} objects and set the <code>nstd2PtysSubGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>nstd2PtysSubGroups</code> array they will be discarded.<br/>
     * @param noNested2PartySubIDs field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoNested2PartySubIDs)
    public void setNoNested2PartySubIDs(Integer noNested2PartySubIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link Nested2PartySubGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public Nested2PartySubGroup[] getNstd2PtysSubGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link Nested2PartySubGroup} object to the existing array of <code>nstd2PtysSubGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noNested2PartySubIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoNestedPartySubIDs</code> method has been called there will already be a number of objects in the
     * <code>nstd2PtysSubGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public Nested2PartySubGroup addNstd2PtysSubGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link Nested2PartySubGroup} object from the existing array of <code>nstd2PtysSubGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noNested2PartySubIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public Nested2PartySubGroup deleteNstd2PtysSubGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link Nested2PartySubGroup} objects from the <code>nstd2PtysSubGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noNested2PartySubIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearNstd2PtysSubGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.Nested2PartyID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (nested2PartyID == null || nested2PartyID.trim().isEmpty()) {
            errorMsg.append(" [Nested2PartyID]");
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
            TagEncoder.encode(bao, TagNum.Nested2PartyID, nested2PartyID);
            if (nested2PartyIDSource != null) {
                TagEncoder.encode(bao, TagNum.Nested2PartyIDSource, nested2PartyIDSource.getValue());
            }
            if (nested2PartyRole != null) {
                TagEncoder.encode(bao, TagNum.Nested2PartyRole, nested2PartyRole.getValue());
            }
            if (noNested2PartySubIDs != null) {
                TagEncoder.encode(bao, TagNum.NoNested2PartySubIDs, noNested2PartySubIDs);
                if (nstd2PtysSubGroups != null && nstd2PtysSubGroups.length == noNested2PartySubIDs.intValue()) {
                    for (int i = 0; i < noNested2PartySubIDs.intValue(); i++) {
                        if (nstd2PtysSubGroups[i] != null) {
                            bao.write(nstd2PtysSubGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "Nested2PartySubGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoNested2PartySubIDs.getValue(), error);
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
            case Nested2PartyID:
                nested2PartyID = new String(tag.value, sessionCharset);
                break;

            case Nested2PartyIDSource:
                nested2PartyIDSource = PartyIDSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case Nested2PartyRole:
                nested2PartyRole = PartyRole.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)).intValue());
                break;

            case NoNested2PartySubIDs:
                noNested2PartySubIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [Nested2PartyGroup] fields.";
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
        b.append("{Nested2PartyGroup=");
        printTagValue(b, TagNum.Nested2PartyID, nested2PartyID);
        printTagValue(b, TagNum.Nested2PartyIDSource, nested2PartyIDSource);
        printTagValue(b, TagNum.Nested2PartyRole, nested2PartyRole);
        printTagValue(b, TagNum.NoNested2PartySubIDs, noNested2PartySubIDs);
        printTagValue(b, nstd2PtysSubGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
