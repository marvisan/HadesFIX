/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DerivativeInstrumentPartyGroup.java
 *
 * $Id: DerivativeInstrumentPartyGroup.java,v 1.1 2011-09-19 08:15:46 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.PartyRole;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Group for a derivative instrument party values.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 07/12/2008, 3:32:15 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DerivativeInstrumentPartyGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.DerivativeInstrumentPartyID.getValue(),
        TagNum.DerivativeInstrumentPartyIDSource.getValue(),
        TagNum.DerivativeInstrumentPartyRole.getValue(),
        TagNum.NoDerivativeInstrumentPartySubIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 1293. Starting with 5.0 version.
     */
    protected String derivativeInstrumentPartyID;

    /**
     * TagNum = 1294. Starting with 5.0 version.
     */
    protected PartyIDSource derivativeInstrumentPartyIDSource;

    /**
     * TagNum = 1295. Starting with 5.0 version.
     */
    protected PartyRole derivativeInstrumentPartyRole;

    /**
     * TagNum = 1296. Starting with 5.0 version.
     */
    protected Integer noDerivativeInstrumentPartySubIDs;

    /**
     * Starting with 5.0 version.
     */
    protected DerivativeInstrumentPartySubIDGroup[] derivativeInstrumentPartySubIDGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DerivativeInstrumentPartyGroup() {
    }

    public DerivativeInstrumentPartyGroup(FragmentContext context) {
        super(context);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public int getFirstTag() {
        return TagNum.DerivativeInstrumentPartyID.getValue();
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DerivativeInstrumentPartyID)
    public String getDerivativeInstrumentPartyID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param derivativeInstrumentPartyID field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DerivativeInstrumentPartyID)
    public void setDerivativeInstrumentPartyID(String derivativeInstrumentPartyID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DerivativeInstrumentPartyIDSource)
    public PartyIDSource getDerivativeInstrumentPartyIDSource() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param derivativeInstrumentPartyIDSource field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DerivativeInstrumentPartyIDSource)
    public void setDerivativeInstrumentPartyIDSource(PartyIDSource derivativeInstrumentPartyIDSource) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DerivativeInstrumentPartyRole)
    public PartyRole getDerivativeInstrumentPartyRole() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param derivativeInstrumentPartyRole field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.DerivativeInstrumentPartyRole)
    public void setDerivativeInstrumentPartyRole(PartyRole derivativeInstrumentPartyRole) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.NoDerivativeInstrumentPartySubIDs)
    public Integer getNoDerivativeInstrumentPartySubIDs() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link DerivativeInstrumentPartySubIDGroup} groups. It will also create an array
     * of {@link DerivativeInstrumentPartySubIDGroup} objects and set the <code>derivativeInstrumentPartySubIDGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>derivativeInstrumentPartySubIDGroups</code> array they will be discarded.<br/>
     * @param noDerivativeInstrumentPartySubIDs field value
     */
    @FIXVersion(introduced = "5.0")
    @TagNumRef(tagNum = TagNum.NoDerivativeInstrumentPartySubIDs)
    public void setNoDerivativeInstrumentPartySubIDs(Integer noDerivativeInstrumentPartySubIDs) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "5.0")
    public DerivativeInstrumentPartySubIDGroup[] getDerivativeInstrumentPartySubIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link DerivativeInstrumentPartySubIDGroup} object to the existing array of 
     * <code>derivativeInstrumentPartySubIDGroups</code> and expands the static array with 1 place.<br/>
     * This method will also update <code>noDerivativeInstrumentPartySubIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoDerivativeInstrumentPartySubIDs</code> method has been called there will already be a number of objects in the
     * <code>noDerivativeInstrumentPartySubIDs</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="5.0")
    public DerivativeInstrumentPartySubIDGroup addDerivativeInstrumentPartySubIDGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link DerivativeInstrumentPartySubIDGroup} object from the existing array of 
     * <code>derivativeInstrumentPartySubIDGroups</code> and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noDerivativeInstrumentPartySubIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="5.0")
    public DerivativeInstrumentPartySubIDGroup deleteDerivativeInstrumentPartySubIDGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link DerivativeInstrumentPartySubIDGroup} objects from the <code>derivativeInstrumentPartySubIDGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noDerivativeInstrumentPartySubIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0")
    public int clearDerivativeInstrumentPartySubIDGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (derivativeInstrumentPartyID == null || derivativeInstrumentPartyID.trim().isEmpty()) {
            errorMsg.append(" [DerivativeInstrumentPartyID]");
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
            TagEncoder.encode(bao, TagNum.DerivativeInstrumentPartyID, derivativeInstrumentPartyID);
            if (derivativeInstrumentPartyIDSource != null) {
                TagEncoder.encode(bao, TagNum.DerivativeInstrumentPartyIDSource, derivativeInstrumentPartyIDSource.getValue());
            }
            if (derivativeInstrumentPartyRole != null) {
                TagEncoder.encode(bao, TagNum.DerivativeInstrumentPartyRole, derivativeInstrumentPartyRole.getValue());
            }
            if (noDerivativeInstrumentPartySubIDs != null && noDerivativeInstrumentPartySubIDs.intValue() > 0) {
                TagEncoder.encode(bao, TagNum.NoDerivativeInstrumentPartySubIDs, noDerivativeInstrumentPartySubIDs);
                if (derivativeInstrumentPartySubIDGroups != null && derivativeInstrumentPartySubIDGroups.length == noDerivativeInstrumentPartySubIDs.intValue()) {
                    for (int i = 0; i < noDerivativeInstrumentPartySubIDs.intValue(); i++) {
                        if (derivativeInstrumentPartySubIDGroups[i] != null) {
                            bao.write(derivativeInstrumentPartySubIDGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "DerivativeInstrumentPartySubIDGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoDerivativeInstrumentPartySubIDs.getValue(), error);
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
            case DerivativeInstrumentPartyID:
                derivativeInstrumentPartyID = new String(tag.value, sessionCharset);
                break;

            case DerivativeInstrumentPartyIDSource:
                derivativeInstrumentPartyIDSource = PartyIDSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case DerivativeInstrumentPartyRole:
                derivativeInstrumentPartyRole = PartyRole.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case NoDerivativeInstrumentPartySubIDs:
                noDerivativeInstrumentPartySubIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [DerivativeInstrumentPartyGroup] fields.";
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
        b.append("{DerivativeInstrumentPartyGroup=");
        printTagValue(b, TagNum.DerivativeInstrumentPartyID, derivativeInstrumentPartyID);
        printTagValue(b, TagNum.DerivativeInstrumentPartyIDSource, derivativeInstrumentPartyIDSource);
        printTagValue(b, TagNum.DerivativeInstrumentPartyRole, derivativeInstrumentPartyRole);
        printTagValue(b, TagNum.NoDerivativeInstrumentPartySubIDs, noDerivativeInstrumentPartySubIDs);
        printTagValue(b, derivativeInstrumentPartySubIDGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
