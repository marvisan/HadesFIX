/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DeliveryInstructionGroup.java
 *
 * $Id: DeliveryInstructionGroup.java,v 1.1 2011-02-10 10:02:16 vrotaru Exp $
 */
package net.hades.fix.message.group;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;

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
import net.hades.fix.message.type.DlvyInstType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlInstSource;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * Delivery instruction group data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/02/2009, 7:08:50 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class DeliveryInstructionGroup extends Group {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SettlInstSource.getValue(),
        TagNum.DlvyInstType.getValue(),
        TagNum.NoSettlPartyIDs.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 165. Starting with 4.4 version.
     */
    protected SettlInstSource settlInstSource;

    /**
     * TagNum = 787. Starting with 4.4 version.
     */
    protected DlvyInstType dlvyInstType;

    /**
     * TagNum = 781. Starting with 4.4 version.
     */
    protected Integer noSettlPartyIDs;

    /**
     * Starting with 4.4 version.
     */
    protected SettlPartyGroup[] settlPartyGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public DeliveryInstructionGroup() {
    }

    public DeliveryInstructionGroup(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.SettlInstSource)
    public SettlInstSource getSettlInstSource() {
        return settlInstSource;
    }

    /**
     * Message field setter.
     * @param settlInstSource field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.SettlInstSource)
    public void setSettlInstSource(SettlInstSource settlInstSource) {
        this.settlInstSource = settlInstSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DlvyInstType)
    public DlvyInstType getDlvyInstType() {
        return dlvyInstType;
    }

    /**
     * Message field setter.
     * @param dlvyInstType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.DlvyInstType)
    public void setDlvyInstType(DlvyInstType dlvyInstType) {
        this.dlvyInstType = dlvyInstType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoSettlPartyIDs)
    public Integer getNoSettlPartyIDs() {
        return noSettlPartyIDs;
    }

    /**
     * This method sets the number of {@link SettlPartyGroup} groups. It will also create an array
     * of {@link SettlPartyGroup} objects and set the <code>settlPartyGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>settlPartyGroups</code> array they will be discarded.<br/>
     * @param noSettlPartyIDs field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoSettlPartyIDs)
    public void setNoSettlPartyIDs(Integer noSettlPartyIDs) {
        this.noSettlPartyIDs = noSettlPartyIDs;
    }

    /**
     * Message field getter for {@link SettlPartyGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public SettlPartyGroup[] getSettlPartyGroups() {
        return settlPartyGroups;
    }
    
    /**
     * This method adds a {@link SettlPartyGroup} object to the existing array of <code>settlPartyGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noPartySubIDs</code> field to the proper value.<br/>
     * Note: If the <code>setNoPartySubIDs</code> method has been called there will already be a number of objects in the
     * <code>settlPartyGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced = "4.4")
    public SettlPartyGroup addSettlPartyGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link SettlPartyGroup} object from the existing array of <code>settlPartyGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noPartySubIDs</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced = "4.4")
    public SettlPartyGroup deleteSettlPartyGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link SettlPartyGroup} objects from the <code>settlPartyGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noPartySubIDs</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced = "4.4")
    public int clearSettlPartyGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.SettlInstSource.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (settlInstSource == null) {
            errorMsg.append(" [SettlInstSource]");
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
            if (settlInstSource != null) {
                TagEncoder.encode(bao, TagNum.SettlInstSource, settlInstSource.getValue());
            }
            if (dlvyInstType != null) {
                TagEncoder.encode(bao, TagNum.DlvyInstType, dlvyInstType.getValue());
            }
            if (noSettlPartyIDs != null) {
                TagEncoder.encode(bao, TagNum.NoSettlPartyIDs, noSettlPartyIDs);
                if (settlPartyGroups != null && settlPartyGroups.length == noSettlPartyIDs.intValue()) {
                    for (int i = 0; i < noSettlPartyIDs.intValue(); i++) {
                        if (settlPartyGroups[i] != null) {
                            bao.write(settlPartyGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "SettlPartyGroup field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoSettlPartyIDs.getValue(), error);
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
            case SettlInstSource:
                settlInstSource = SettlInstSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case DlvyInstType:
                dlvyInstType = DlvyInstType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case NoSettlPartyIDs:
                noSettlPartyIDs = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [DeliveryInstructionGroup] fields.";
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
        b.append("{DeliveryInstructionGroup=");
        printTagValue(b, TagNum.SettlInstSource, settlInstSource);
        printTagValue(b, TagNum.DlvyInstType, dlvyInstType);
        printTagValue(b, TagNum.NoSettlPartyIDs, noSettlPartyIDs);
        printTagValue(b, settlPartyGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>

}
