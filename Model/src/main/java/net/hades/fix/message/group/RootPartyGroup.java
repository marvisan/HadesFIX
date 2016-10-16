/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RootPartyGroup.java
 *
 * $Id: RootPartyGroup.java,v 1.7 2011-02-13 06:52:37 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.RootSubParties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.util.TagEncoder;

/**
 * Root party group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 08/04/2009, 2:29:38 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class RootPartyGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -5080991394016068715L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1117. Starting with 5.0 version.
     */
    protected String rootPartyID;

    /**
     * TagNum = 1118. Starting with 5.0 version.
     */
    protected PartyIDSource rootPartyIDSource;

    /**
     * TagNum = 1119. Starting with 5.0 version.
     */
    protected PartyRole rootPartyRole;

    /**
     * TagNum = 1120. Starting with 5.0 version.
     */
    protected Integer noRootPartySubIDs;

    /**
     * Starting with 5.0 version.
     */
    protected RootPartySubGroup[] rootPartySubGroups;

    /**
     * Starting with 5.0SP2 version.
     */
    protected RootSubParties rootSubParties;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public RootPartyGroup() {
    }

    public RootPartyGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.RootPartyID)
    public String getRootPartyID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param rootPartyID field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.RootPartyID)
    public void setRootPartyID(String rootPartyID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.RootPartyIDSource)
    public PartyIDSource getRootPartyIDSource() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param rootPartyIDSource field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.RootPartyIDSource)
    public void setRootPartyIDSource(PartyIDSource rootPartyIDSource) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.RootPartyRole)
    public PartyRole getRootPartyRole() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param rootPartyRole field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.RootPartyRole)
    public void setRootPartyRole(PartyRole rootPartyRole) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.NoRootPartySubIDs)
    public Integer getNoRootPartySubIDs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link RootPartySubGroup} groups. It will also create an array
     * of {@link RootPartySubGroup} objects and set the <code>rootPartySubGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>rootPartySubGroups</code> array they will be discarded.<br/>
     * @param noRootPartySubIDs field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.NoRootPartySubIDs)
    public void setNoRootPartySubIDs(Integer noRootPartySubIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link RootPartySubGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "5.0")
    public RootPartySubGroup[] getRootPartySubGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link RootPartySubGroup} object to the existing array of <code>rootPartySubGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noRootPartySubIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoRootPartySubIDs</code> method has been called there will already be a number of objects in the
     * <code>rootPartySubGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0")
    public RootPartySubGroup addRootPartySubGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link RootPartySubGroup} object from the existing array of <code>rootPartySubGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noRootPartySubIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0")
    public RootPartySubGroup deleteRootPartySubGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link RootPartySubGroup} objects from the <code>rootPartySubGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noRootPartySubIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0")
    public int clearRootPartySubGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    public RootSubParties getRootSubParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to the proper implementation class.
     */
    @FIXVersion(introduced = "5.0SP2")
    public void setRootSubParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to null.
     */
    @FIXVersion(introduced = "5.0SP2")
    public void clearRootSubParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.RootPartyID.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (rootPartyID == null || rootPartyID.trim().isEmpty()) {
            errorMsg.append(" [RootPartyID]");
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
            TagEncoder.encode(bao, TagNum.RootPartyID, rootPartyID);
            if (rootPartyIDSource != null) {
                TagEncoder.encode(bao, TagNum.RootPartyIDSource, rootPartyIDSource.getValue());
            }
            if (rootPartyRole != null) {
                TagEncoder.encode(bao, TagNum.RootPartyRole, rootPartyRole.getValue());
            }
            if (noRootPartySubIDs != null) {
                TagEncoder.encode(bao, TagNum.NoRootPartySubIDs, noRootPartySubIDs);
                if (rootPartySubGroups != null && rootPartySubGroups.length == noRootPartySubIDs.intValue()) {
                    for (int i = 0; i < noRootPartySubIDs.intValue(); i++) {
                        if (rootPartySubGroups[i] != null) {
                            bao.write(rootPartySubGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "RootPartySubGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoRootPartySubIDs.getValue(), error);
                }
            }
            if (rootSubParties != null) {
                bao.write(rootSubParties.encode(MsgSecureType.ALL_FIELDS));
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
            case RootPartyID:
                rootPartyID = new String(tag.value, sessionCharset);
                break;

            case RootPartyIDSource:
                rootPartyIDSource = PartyIDSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case RootPartyRole:
                rootPartyRole = PartyRole.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)).intValue());
                break;

            case NoRootPartySubIDs:
                noRootPartySubIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [RootPartyGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
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
        b.append("{RootPartyGroup=");
        printTagValue(b, TagNum.RootPartyID, rootPartyID);
        printTagValue(b, TagNum.RootPartyIDSource, rootPartyIDSource);
        printTagValue(b, TagNum.RootPartyRole, rootPartyRole);
        printTagValue(b, TagNum.NoRootPartySubIDs, noRootPartySubIDs);
        printTagValue(b, rootPartySubGroups);
        printTagValue(b, rootSubParties);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
