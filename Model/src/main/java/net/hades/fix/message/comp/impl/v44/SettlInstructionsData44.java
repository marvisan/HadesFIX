/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlInstructionsData44.java
 *
 * $Id: SettlInstructionsData44.java,v 1.3 2011-04-14 23:44:59 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import net.hades.fix.message.struct.Tag;

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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.comp.SettlInstructionsData;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.DeliveryInstructionGroup;
import net.hades.fix.message.group.impl.v44.DeliveryInstructionGroup44;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlDeliveryType;
import net.hades.fix.message.type.StandInstDbType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.MsgUtil;
import net.hades.fix.message.util.TagEncoder;

/**
 * FIX 4.4 implementation of SettlInstructionsData component.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 11/02/2009, 8:31:52 PM
 */
@XmlRootElement(name="SetInstr")
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class SettlInstructionsData44 extends SettlInstructionsData {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    protected static final Set<Integer> DLVY_INST_GROUP_TAGS = new DeliveryInstructionGroup44().getFragmentAllTags();

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(DLVY_INST_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(DLVY_INST_GROUP_TAGS);
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SettlInstructionsData44() {
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SettlInstructionsData44(FragmentContext context) {
        super(context);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlAttribute(name = "DlvryTyp")
    @Override
    public SettlDeliveryType getSettlDeliveryType() {
        return settlDeliveryType;
    }

    @Override
    public void setSettlDeliveryType(SettlDeliveryType settlDeliveryType) {
        this.settlDeliveryType = settlDeliveryType;
    }

    @XmlAttribute(name = "StandInstDbTyp")
    @Override
    public StandInstDbType getStandInstDbType() {
        return standInstDbType;
    }

    @Override
    public void setStandInstDbType(StandInstDbType standInstDbType) {
        this.standInstDbType = standInstDbType;
    }

    @XmlAttribute(name = "StandInstDbName")
    @Override
    public String getStandInstDbName() {
        return standInstDbName;
    }

    @Override
    public void setStandInstDbName(String standInstDbName) {
        this.standInstDbName = standInstDbName;
    }

    @XmlAttribute(name = "StandInstDbID")
    @Override
    public String getStandInstDbID() {
        return standInstDbID;
    }

    @Override
    public void setStandInstDbID(String standInstDbID) {
        this.standInstDbID = standInstDbID;
    }
    
    @Override
    public Integer getNoDlvyInst() {
        return noDlvyInst;
    }

    @Override
    public void setNoDlvyInst(Integer noDlvyInst) {
        this.noDlvyInst = noDlvyInst;
        if (noDlvyInst != null) {
            deliveryInstructionGroups = new DeliveryInstructionGroup[noDlvyInst.intValue()];
            for (int i = 0; i < deliveryInstructionGroups.length; i++) {
                deliveryInstructionGroups[i] = new DeliveryInstructionGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public DeliveryInstructionGroup[] getDeliveryInstructionGroups() {
        return deliveryInstructionGroups;
    }

    public void setDeliveryInstructionGroups(DeliveryInstructionGroup[] deliveryInstructionGroups) {
        this.deliveryInstructionGroups = deliveryInstructionGroups;
        if (deliveryInstructionGroups != null) {
            noDlvyInst = new Integer(deliveryInstructionGroups.length);
        }
    }

    @Override
    public DeliveryInstructionGroup addDeliveryInstructionGroup() {
        DeliveryInstructionGroup group = new DeliveryInstructionGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<DeliveryInstructionGroup> groups = new ArrayList<DeliveryInstructionGroup>();
        if (deliveryInstructionGroups != null && deliveryInstructionGroups.length > 0) {
            groups = new ArrayList<DeliveryInstructionGroup>(Arrays.asList(deliveryInstructionGroups));
        }
        groups.add(group);
        deliveryInstructionGroups = groups.toArray(new DeliveryInstructionGroup[groups.size()]);
        noDlvyInst = new Integer(deliveryInstructionGroups.length);

        return group;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();

        try {
            if (settlDeliveryType != null && MsgUtil.isTagInList(TagNum.SettlDeliveryType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlDeliveryType, settlDeliveryType.getValue());
            }
            if (standInstDbType != null && MsgUtil.isTagInList(TagNum.StandInstDbType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StandInstDbType, standInstDbType.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.StandInstDbName, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StandInstDbName, standInstDbName);
            }
            if (MsgUtil.isTagInList(TagNum.StandInstDbID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.StandInstDbID, standInstDbID);
            }
            if (MsgUtil.isTagInList(TagNum.NoDlvyInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoDlvyInst, noDlvyInst);
                if (deliveryInstructionGroups != null && deliveryInstructionGroups.length == noDlvyInst.intValue()) {
                    for (int i = 0; i < noDlvyInst.intValue(); i++) {
                        if (deliveryInstructionGroups[i] != null) {
                            bao.write(deliveryInstructionGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "DeliveryInstructionGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, TagNum.NoDlvyInst.getValue(), error);
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        if (DLVY_INST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noDlvyInst != null && noDlvyInst.intValue() > 0) {
                FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
                message.reset();
                if (deliveryInstructionGroups == null) {
                    deliveryInstructionGroups = new DeliveryInstructionGroup[noDlvyInst.intValue()];
                }
                for (int i = 0; i < deliveryInstructionGroups.length; i++) {
                    DeliveryInstructionGroup group = new DeliveryInstructionGroup44(context);
                    group.decode(message);
                    deliveryInstructionGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SettlInstructionsData] component version [4.4].";
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
