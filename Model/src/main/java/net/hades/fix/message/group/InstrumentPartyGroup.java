/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentPartyGroup.java
 *
 * $Id: InstrumentPartyGroup.java,v 1.9 2011-04-17 09:30:49 vrotaru Exp $
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
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.comp.InstrumentPtysSubGrp;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Group for an instrument party values.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 07/12/2008, 3:32:15 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class InstrumentPartyGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1019. Starting with 5.0 version.
     */
    protected String instrumentPartyID;

    /**
     * TagNum = 1050. Starting with 5.0 version.
     */
    protected PartyIDSource instrumentPartyIDSource;

    /**
     * TagNum = 1051. Starting with 5.0 version.
     */
    protected PartyRole instrumentPartyRole;

    /**
     * TagNum = 1052. Starting with 5.0 version.
     */
    protected Integer noInstrumentPartySubIDs;

    /**
     * Starting with 5.0 version.
     */
    protected InstrumentPartySubIDGroup[] instrumentPartySubIDGroups;

    /**
     * Starting with 5.0SP2 version.
     */
    protected InstrumentPtysSubGrp instrumentPtysSubGrp;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public InstrumentPartyGroup() {
    }

    public InstrumentPartyGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public int getFirstTag() {
        return TagNum.InstrumentPartyID.getValue();
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.InstrumentPartyID)
    public String getInstrumentPartyID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param instrumentPartyID field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.InstrumentPartyID)
    public void setInstrumentPartyID(String instrumentPartyID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.InstrumentPartyIDSource)
    public PartyIDSource getInstrumentPartyIDSource() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param instrumentPartyIDSource field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.InstrumentPartyIDSource)
    public void setInstrumentPartyIDSource(PartyIDSource instrumentPartyIDSource) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.InstrumentPartyRole)
    public PartyRole getInstrumentPartyRole() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param instrumentPartyRole field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.InstrumentPartyRole)
    public void setInstrumentPartyRole(PartyRole instrumentPartyRole) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.NoInstrumentPartySubIDs)
    public Integer getNoInstrumentPartySubIDs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link InstrumentPartySubIDGroup} groups. It will also create an array
     * of {@link InstrumentPartySubIDGroup} objects and set the <code>instrumentPartySubIDGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>instrumentPartySubIDGroups</code> array they will be discarded.<br/>
     * @param noInstrumentPartySubIDs field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.NoInstrumentPartySubIDs)
    public void setNoInstrumentPartySubIDs(Integer noInstrumentPartySubIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    public InstrumentPartySubIDGroup[] getInstrumentPartySubIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link InstrumentPartySubIDGroup} object to the existing array of 
     * <code>instrumentPartySubIDGroups</code> and expands the static array with 1 place.<br/>
     * This method will also update <code>noInstrumentPartySubIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoInstrumentPartySubIDs</code> method has been called there will already be a number of objects in the
     * <code>noInstrumentPartySubIDs</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0")
    public InstrumentPartySubIDGroup addInstrumentPartySubIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link InstrumentPartySubIDGroup} object from the existing array of 
     * <code>instrumentPartySubIDGroups</code> and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noInstrumentPartySubIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0")
    public InstrumentPartySubIDGroup deleteInstrumentPartySubIDGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link InstrumentPartySubIDGroup} objects from the <code>instrumentPartySubIDGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noInstrumentPartySubIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0")
    public int clearInstrumentPartySubIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0SP2")
    public InstrumentPtysSubGrp getInstrumentPtysSubGrp() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to the proper implementation.
     */
    @FIXVersion(introduced = "5.0SP2")
    public void setInstrumentPtysSubGrp() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to null.
     */
    @FIXVersion(introduced = "5.0SP2")
    public void clearInstrumentPtysSubGrp() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (instrumentPartyID == null || instrumentPartyID.trim().isEmpty()) {
            errorMsg.append(" [InstrumentPartyID]");
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
            TagEncoder.encode(bao, TagNum.InstrumentPartyID, instrumentPartyID);
            if (instrumentPartyIDSource != null) {
                TagEncoder.encode(bao, TagNum.InstrumentPartyIDSource, instrumentPartyIDSource.getValue());
            }
            if (instrumentPartyRole != null) {
                TagEncoder.encode(bao, TagNum.InstrumentPartyRole, instrumentPartyRole.getValue());
            }
            if (noInstrumentPartySubIDs != null && noInstrumentPartySubIDs.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoInstrumentPartySubIDs, noInstrumentPartySubIDs);
                if (instrumentPartySubIDGroups != null && instrumentPartySubIDGroups.length == noInstrumentPartySubIDs.intValue()) {
                    for (int i = 0; i < noInstrumentPartySubIDs.intValue(); i++) {
                        if (instrumentPartySubIDGroups[i] != null) {
                            bao.write(instrumentPartySubIDGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "InstrumentPartySubIDGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoInstrumentPartySubIDs.getValue(), error);
                }
            }
            if (instrumentPtysSubGrp != null) {
                bao.write(instrumentPtysSubGrp.encode(MsgSecureType.ALL_FIELDS));
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
            case InstrumentPartyID:
                instrumentPartyID = new String(tag.value, sessionCharset);
                break;

            case InstrumentPartyIDSource:
                instrumentPartyIDSource = PartyIDSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case InstrumentPartyRole:
                instrumentPartyRole = PartyRole.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case NoInstrumentPartySubIDs:
                noInstrumentPartySubIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [InstrumentPartyGroup] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
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
        b.append("{InstrumentPartyGroup=");
        printTagValue(b, TagNum.InstrumentPartyID, instrumentPartyID);
        printTagValue(b, TagNum.InstrumentPartyIDSource, instrumentPartyIDSource);
        printTagValue(b, TagNum.InstrumentPartyRole, instrumentPartyRole);
        printTagValue(b, TagNum.NoInstrumentPartySubIDs, noInstrumentPartySubIDs);
        printTagValue(b, instrumentPartySubIDGroups);
        printTagValue(b, instrumentPtysSubGrp);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
