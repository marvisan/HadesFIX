/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlInstructionsData.java
 *
 * $Id: SettlInstructionsData.java,v 1.1 2011-02-10 10:02:15 vrotaru Exp $
 */
package net.hades.fix.message.comp;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.group.DeliveryInstructionGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlDeliveryType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.MsgSecureType;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.StandInstDbType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;

/**
 * The SettlInstructionsData component defines settlement instructions data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/02/2009, 8:29:09 PM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class SettlInstructionsData extends Component {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SettlDeliveryType.getValue(),
        TagNum.StandInstDbType.getValue(),
        TagNum.StandInstDbName.getValue(),
        TagNum.StandInstDbID.getValue(),
        TagNum.NoDlvyInst.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SettlDeliveryType.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 172. Starting with 4.4 version.
     */
    protected SettlDeliveryType settlDeliveryType;

    /**
     * TagNum = 169. Starting with 4.4 version.
     */
    protected StandInstDbType standInstDbType;

    /**
     * TagNum = 170. Starting with 4.4 version.
     */
    protected String standInstDbName;

    /**
     * TagNum = 171. Starting with 4.4 version.
     */
    protected String standInstDbID;

    /**
     * TagNum = 453. Starting with 4.4 version.
     */
    protected Integer noDlvyInst;

    /**
     * Starting with 5.0 version.
     */
    protected DeliveryInstructionGroup[] deliveryInstructionGroups;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SettlInstructionsData() {
        super();
    }

    public SettlInstructionsData(FragmentContext context) {
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
    @TagNumRef(tagNum = TagNum.SettlDeliveryType)
    public SettlDeliveryType getSettlDeliveryType() {
        return settlDeliveryType;
    }

    /**
     * Message field setter.
     * @param settlDeliveryType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.SettlDeliveryType)
    public void setSettlDeliveryType(SettlDeliveryType settlDeliveryType) {
        this.settlDeliveryType = settlDeliveryType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.StandInstDbType)
    public StandInstDbType getStandInstDbType() {
        return standInstDbType;
    }

    /**
     * Message field setter.
     * @param standInstDbType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.StandInstDbType)
    public void setStandInstDbType(StandInstDbType standInstDbType) {
        this.standInstDbType = standInstDbType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.StandInstDbName)
    public String getStandInstDbName() {
        return standInstDbName;
    }

    /**
     * Message field setter.
     * @param standInstDbName field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.StandInstDbName)
    public void setStandInstDbName(String standInstDbName) {
        this.standInstDbName = standInstDbName;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.StandInstDbID)
    public String getStandInstDbID() {
        return standInstDbID;
    }

    /**
     * Message field setter.
     * @param standInstDbID field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.StandInstDbID)
    public void setStandInstDbID(String standInstDbID) {
        this.standInstDbID = standInstDbID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoDlvyInst)
    public Integer getNoDlvyInst() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link DeliveryInstructionGroup} groups. It will also create an array
     * of {@link DeliveryInstructionGroup} objects and set the <code>noDlvyInst</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>deliveryInstructionGroups</code> array they will be discarded.<br/>
     * @param noDlvyInst field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.NoDlvyInst)
    public void setNoDlvyInst(Integer noDlvyInst) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link DeliveryInstructionGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced = "4.4")
    public DeliveryInstructionGroup[] getDeliveryInstructionGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link DeliveryInstructionGroup} object to the existing array of <code>deliveryInstructionGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noDlvyInst</code> field to the proper value.<br/>
     * Note: If the <code>setNoDlvyInst</code> method has been called there will already be a number of objects in the
     * <code>deliveryInstructionGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public DeliveryInstructionGroup addDeliveryInstructionGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link DeliveryInstructionGroup} object from the existing array of <code>deliveryInstructionGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noDlvyInst</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.4")
    public DeliveryInstructionGroup deleteDeliveryInstructionGroup(int index) {
        DeliveryInstructionGroup result = null;
        if (deliveryInstructionGroups != null && deliveryInstructionGroups.length > 0 && deliveryInstructionGroups.length > index) {
            List<DeliveryInstructionGroup> groups = new ArrayList<DeliveryInstructionGroup>(Arrays.asList(deliveryInstructionGroups));
            result = groups.remove(index);
            deliveryInstructionGroups = groups.toArray(new DeliveryInstructionGroup[groups.size()]);
            if (deliveryInstructionGroups.length > 0) {
                noDlvyInst = new Integer(deliveryInstructionGroups.length);
            } else {
                deliveryInstructionGroups = null;
                noDlvyInst = null;
            }
        }

        return result;
    }

    /**
     * Deletes all the {@link DeliveryInstructionGroup} objects from the <code>deliveryInstructionGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noDlvyInst</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="5.0")
    public int clearDeliveryInstructionGroups() {
        int result = 0;
        if (deliveryInstructionGroups != null && deliveryInstructionGroups.length > 0) {
            result = deliveryInstructionGroups.length;
            deliveryInstructionGroups = null;
            noDlvyInst = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected int getFirstTag() {
        return TagNum.SettlDeliveryType.getValue();
    }

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
         if (settlDeliveryType == null) {
            errorMsg.append(" [SettlDeliveryType]");
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
            if (settlDeliveryType != null) {
                TagEncoder.encode(bao, TagNum.SettlDeliveryType, settlDeliveryType.getValue());
            }
            if (standInstDbType != null) {
                TagEncoder.encode(bao, TagNum.StandInstDbType, standInstDbType.getValue());
            }
            TagEncoder.encode(bao, TagNum.StandInstDbName, standInstDbName);
            TagEncoder.encode(bao, TagNum.StandInstDbID, standInstDbID);
            TagEncoder.encode(bao, TagNum.NoDlvyInst, noDlvyInst);
            if (deliveryInstructionGroups != null && deliveryInstructionGroups.length == noDlvyInst.intValue()) {
                for (int i = 0; i < noDlvyInst.intValue(); i++) {
                    if (deliveryInstructionGroups[i] != null) {
                        bao.write(deliveryInstructionGroups[i].encode(MsgSecureType.ALL_FIELDS));
                    }
                }
            } else {
                String error = "DeliveryInstructionGroups field has been set but there is no data or the number of groups does not match.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoDlvyInst.getValue(), error);
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
            case SettlDeliveryType:
                settlDeliveryType = SettlDeliveryType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case StandInstDbType:
                standInstDbType = StandInstDbType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case StandInstDbName:
                standInstDbName = new String(tag.value, sessionCharset);
                break;

            case StandInstDbID:
                standInstDbID = new String(tag.value, sessionCharset);
                break;

            case NoDlvyInst:
                noDlvyInst = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [SettlInstructionsData] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
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
        b.append("{SettlInstructionsData=");
        printTagValue(b, TagNum.SettlDeliveryType, settlDeliveryType);
        printTagValue(b, TagNum.StandInstDbType, standInstDbType);
        printTagValue(b, TagNum.StandInstDbName, standInstDbName);
        printTagValue(b, TagNum.StandInstDbID, standInstDbID);
        printTagValue(b, TagNum.NoDlvyInst, noDlvyInst);
        printTagValue(b, deliveryInstructionGroups);
        b.append("}");

        return b.toString();
    }

    // </editor-fold>
}
