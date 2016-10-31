/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Nested4PartyGroup.java
 *
 * $Id: Nested4PartyGroup.java,v 1.2 2011-02-13 06:52:37 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
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
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.util.TagEncoder;

/**
 * Nested 4 party group data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 06/04/2009, 3:20:21 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Nested4PartyGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Nested4PartyID.getValue(),
        TagNum.Nested4PartyIDSource.getValue(),
        TagNum.Nested4PartyRole.getValue(),
        TagNum.NoNested4PartySubIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1415. Starting with 5.0SP1 version.
     */
    protected String nested4PartyID;

    /**
     * TagNum = 1416. Starting with 5.0SP1 version.
     */
    protected PartyIDSource nested4PartyIDSource;

    /**
     * TagNum = 1417. Starting with 5.0SP1 version.
     */
    protected PartyRole nested4PartyRole;

    /**
     * TagNum = 1413. Starting with 5.0SP1 version.
     */
    protected Integer noNested4PartySubIDs;

    /**
     * Starting with 5.0SP1 version.
     */
    protected Nested4PartySubGroup[] nstd4PtysSubGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Nested4PartyGroup() {
    }

    public Nested4PartyGroup(FragmentContext context) {
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
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.Nested4PartyID)
    public String getNested4PartyID() {
        return nested4PartyID;
    }

    /**
     * Message field setter.
     * @param nested4PartyID field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.Nested4PartyID)
    public void setNested4PartyID(String nested4PartyID) {
        this.nested4PartyID = nested4PartyID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.Nested4PartyIDSource)
    public PartyIDSource getNested4PartyIDSource() {
        return nested4PartyIDSource;
    }

    /**
     * Message field setter.
     * @param nested4PartyIDSource field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.Nested4PartyIDSource)
    public void setNested4PartyIDSource(PartyIDSource nested4PartyIDSource) {
        this.nested4PartyIDSource = nested4PartyIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.Nested4PartyRole)
    public PartyRole getNested4PartyRole() {
        return nested4PartyRole;
    }

    /**
     * Message field setter.
     * @param nested4PartyRole field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.Nested4PartyRole)
    public void setNested4PartyRole(PartyRole nested4PartyRole) {
        this.nested4PartyRole = nested4PartyRole;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoNested4PartySubIDs)
    public Integer getNoNested4PartySubIDs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link Nested4PartySubGroup} groups. It will also create an array
     * of {@link Nested4PartySubGroup} objects and set the <code>nstd4PtysSubGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>nstd4PtysSubGroups</code> array they will be discarded.<br/>
     * @param noNested4PartySubIDs field value
     */
    @FIXVersion(introduced = "5.0SP1")
    @TagNumRef(tagNum = TagNum.NoNested4PartySubIDs)
    public void setNoNested4PartySubIDs(Integer noNested4PartySubIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link Nested4PartySubGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "5.0SP1")
    public Nested4PartySubGroup[] getNstd4PtysSubGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link Nested4PartySubGroup} object to the existing array of <code>nstd4PtysSubGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noNested4PartySubIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoNestedPartySubIDs</code> method has been called there will already be a number of objects in the
     * <code>nstd4PtysSubGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0SP1")
    public Nested4PartySubGroup addNstd4PtysSubGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link Nested4PartySubGroup} object from the existing array of <code>nstd4PtysSubGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noNested4PartySubIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0SP1")
    public Nested4PartySubGroup deleteNstd4PtysSubGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link Nested4PartySubGroup} objects from the <code>nstd4PtysSubGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noNested4PartySubIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0SP1")
    public int clearNstd4PtysSubGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.Nested4PartyID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (nested4PartyID == null || nested4PartyID.trim().isEmpty()) {
            errorMsg.append(" [Nested4PartyID]");
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
            TagEncoder.encode(bao, TagNum.Nested4PartyID, nested4PartyID);
            if (nested4PartyIDSource != null) {
                TagEncoder.encode(bao, TagNum.Nested4PartyIDSource, nested4PartyIDSource.getValue());
            }
            if (nested4PartyRole != null) {
                TagEncoder.encode(bao, TagNum.Nested4PartyRole, nested4PartyRole.getValue());
            }
            if (noNested4PartySubIDs != null) {
                TagEncoder.encode(bao, TagNum.NoNested4PartySubIDs, noNested4PartySubIDs);
                if (nstd4PtysSubGroups != null && nstd4PtysSubGroups.length == noNested4PartySubIDs.intValue()) {
                    for (int i = 0; i < noNested4PartySubIDs.intValue(); i++) {
                        if (nstd4PtysSubGroups[i] != null) {
                            bao.write(nstd4PtysSubGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "Nested4PartySubGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoNested4PartySubIDs.getValue(), error);
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
            case Nested4PartyID:
                nested4PartyID = new String(tag.value, sessionCharset);
                break;

            case Nested4PartyIDSource:
                nested4PartyIDSource = PartyIDSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case Nested4PartyRole:
                nested4PartyRole = PartyRole.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)).intValue());
                break;

            case NoNested4PartySubIDs:
                noNested4PartySubIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [Nested4PartyGroup] fields.";
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
        b.append("{Nested4PartyGroup=");
        printTagValue(b, TagNum.Nested4PartyID, nested4PartyID);
        printTagValue(b, TagNum.Nested4PartyIDSource, nested4PartyIDSource);
        printTagValue(b, TagNum.Nested4PartyRole, nested4PartyRole);
        printTagValue(b, TagNum.NoNested4PartySubIDs, noNested4PartySubIDs);
        printTagValue(b, nstd4PtysSubGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
