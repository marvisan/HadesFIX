/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Nested3PartyGroup.java
 *
 * $Id: Nested3PartyGroup.java,v 1.2 2011-02-13 06:52:37 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.exception.InvalidMsgException;
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
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Nested 3 party group data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 06/04/2009, 3:20:21 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class Nested3PartyGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Nested3PartyID.getValue(),
        TagNum.Nested3PartyIDSource.getValue(),
        TagNum.Nested3PartyRole.getValue(),
        TagNum.NoNested3PartySubIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 949. Starting with 4.4 version.
     */
    protected String nested3PartyID;

    /**
     * TagNum = 950. Starting with 4.4 version.
     */
    protected PartyIDSource nested3PartyIDSource;

    /**
     * TagNum = 951. Starting with 4.4 version.
     */
    protected PartyRole nested3PartyRole;

    /**
     * TagNum = 952. Starting with 4.4 version.
     */
    protected Integer noNested3PartySubIDs;

    /**
     * Starting with 4.4 version.
     */
    protected Nested3PartySubGroup[] nstd3PtysSubGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public Nested3PartyGroup() {
    }

    public Nested3PartyGroup(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.Nested3PartyID)
    public String getNested3PartyID() {
        return nested3PartyID;
    }

    /**
     * Message field setter.
     * @param nested3PartyID field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Nested3PartyID)
    public void setNested3PartyID(String nested3PartyID) {
        this.nested3PartyID = nested3PartyID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Nested3PartyIDSource)
    public PartyIDSource getNested3PartyIDSource() {
        return nested3PartyIDSource;
    }

    /**
     * Message field setter.
     * @param nested3PartyIDSource field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Nested3PartyIDSource)
    public void setNested3PartyIDSource(PartyIDSource nested3PartyIDSource) {
        this.nested3PartyIDSource = nested3PartyIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Nested3PartyRole)
    public PartyRole getNested3PartyRole() {
        return nested3PartyRole;
    }

    /**
     * Message field setter.
     * @param nested3PartyRole field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.Nested3PartyRole)
    public void setNested3PartyRole(PartyRole nested3PartyRole) {
        this.nested3PartyRole = nested3PartyRole;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoNested3PartySubIDs)
    public Integer getNoNested3PartySubIDs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link Nested3PartySubGroup} groups. It will also create an array
     * of {@link Nested3PartySubGroup} objects and set the <code>nstd3PtysSubGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>nstd3PtysSubGroups</code> array they will be discarded.<br/>
     * @param noNested3PartySubIDs field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoNested3PartySubIDs)
    public void setNoNested3PartySubIDs(Integer noNested3PartySubIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link Nested3PartySubGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public Nested3PartySubGroup[] getNstd3PtysSubGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link Nested3PartySubGroup} object to the existing array of <code>nstd3PtysSubGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noNested3PartySubIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoNestedPartySubIDs</code> method has been called there will already be a number of objects in the
     * <code>nstd3PtysSubGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public Nested3PartySubGroup addNstd3PtysSubGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link Nested3PartySubGroup} object from the existing array of <code>nstd3PtysSubGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noNested3PartySubIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public Nested3PartySubGroup deleteNstd3PtysSubGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link Nested3PartySubGroup} objects from the <code>nstd3PtysSubGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noNested3PartySubIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearNstd3PtysSubGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.Nested3PartyID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (nested3PartyID == null || nested3PartyID.trim().isEmpty()) {
            errorMsg.append(" [Nested3PartyID]");
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
            TagEncoder.encode(bao, TagNum.Nested3PartyID, nested3PartyID);
            if (nested3PartyIDSource != null) {
                TagEncoder.encode(bao, TagNum.Nested3PartyIDSource, nested3PartyIDSource.getValue());
            }
            if (nested3PartyRole != null) {
                TagEncoder.encode(bao, TagNum.Nested3PartyRole, nested3PartyRole.getValue());
            }
            if (noNested3PartySubIDs != null) {
                TagEncoder.encode(bao, TagNum.NoNested3PartySubIDs, noNested3PartySubIDs);
                if (nstd3PtysSubGroups != null && nstd3PtysSubGroups.length == noNested3PartySubIDs.intValue()) {
                    for (int i = 0; i < noNested3PartySubIDs.intValue(); i++) {
                        if (nstd3PtysSubGroups[i] != null) {
                            bao.write(nstd3PtysSubGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "Nested3PartySubGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoNested3PartySubIDs.getValue(), error);
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
            case Nested3PartyID:
                nested3PartyID = new String(tag.value, sessionCharset);
                break;

            case Nested3PartyIDSource:
                nested3PartyIDSource = PartyIDSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case Nested3PartyRole:
                nested3PartyRole = PartyRole.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)).intValue());
                break;

            case NoNested3PartySubIDs:
                noNested3PartySubIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [Nested3PartyGroup] fields.";
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
        b.append("{Nested3PartyGroup=");
        printTagValue(b, TagNum.Nested3PartyID, nested3PartyID);
        printTagValue(b, TagNum.Nested3PartyIDSource, nested3PartyIDSource);
        printTagValue(b, TagNum.Nested3PartyRole, nested3PartyRole);
        printTagValue(b, TagNum.NoNested3PartySubIDs, noNested3PartySubIDs);
        printTagValue(b, nstd3PtysSubGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
