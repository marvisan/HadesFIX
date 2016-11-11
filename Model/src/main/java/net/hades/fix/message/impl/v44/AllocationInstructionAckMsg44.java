/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationInstructionAckMsg44.java
 *
 * $Id: AllocationInstructionAckMsg44.java,v 1.3 2011-04-14 23:44:36 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.impl.v44.AllocAckGroup44;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.AllocationInstructionAckMsg;
import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.comp.impl.v44.Parties44;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.AllocAckGroup;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.type.AllocIntermedReqType;
import net.hades.fix.message.type.AllocRejCode;
import net.hades.fix.message.type.AllocStatus;
import net.hades.fix.message.type.AllocType;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MatchStatus;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateAdapter;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 4.4 AllocationInstructionAckMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="AllocInstrctnAck")
@XmlType(propOrder = {"header", "partyIDGroups", "allocAckGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class AllocationInstructionAckMsg44 extends AllocationInstructionAckMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> PARTIES_COMP_TAGS = new Parties44().getFragmentAllTags();
    protected static final Set<Integer> ALLOC_ACK_GROUP_TAGS = new AllocAckGroup44().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(PARTIES_COMP_TAGS);
        ALL_TAGS.addAll(ALLOC_ACK_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(PARTIES_COMP_TAGS);
        START_COMP_TAGS.addAll(ALLOC_ACK_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public AllocationInstructionAckMsg44() {
        super();
    }

    public AllocationInstructionAckMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public AllocationInstructionAckMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public AllocationInstructionAckMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return ALL_TAGS;
    }

    @Override
    public void copyFixmlData(FIXFragment fragment) {
        AllocationInstructionAckMsg44 fixml = (AllocationInstructionAckMsg44) fragment;
        if (fixml.getAllocID() != null) {
            allocID = fixml.getAllocID();
        }
        if (fixml.getParties() != null) {
            setParties(fixml.getParties());
        }
        if (fixml.getSecondaryAllocID() != null) {
            secondaryAllocID = fixml.getSecondaryAllocID();
        }
        if (fixml.getTradeDate() != null) {
            tradeDate = fixml.getTradeDate();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getAllocStatus() != null) {
            allocStatus = fixml.getAllocStatus();
        }
        if (fixml.getAllocRejCode() != null) {
            allocRejCode = fixml.getAllocRejCode();
        }
        if (fixml.getAllocType() != null) {
            allocType = fixml.getAllocType();
        }
        if (fixml.getAllocIntermedReqType() != null) {
            allocIntermedReqType = fixml.getAllocIntermedReqType();
        }
        if (fixml.getMatchStatus() != null) {
            matchStatus = fixml.getMatchStatus();
        }
        if (fixml.getProduct() != null) {
            product = fixml.getProduct();
        }
        if (fixml.getSecurityType() != null) {
            securityType = fixml.getSecurityType();
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
        }
        if (fixml.getLegalConfirm() != null) {
            legalConfirm = fixml.getLegalConfirm();
        }
        if (fixml.getAllocAckGroups() != null) {
            setAllocAckGroups(fixml.getAllocAckGroups());
        }
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @XmlElementRef
    @Override
    public Header getHeader() {
        return header;
    }

    @Override
    public void setHeader(Header header) {
        this.header = header;
    }

    @XmlAttribute(name = "ID")
    @Override
    public String getAllocID() {
        return allocID;
    }

    @Override
    public void setAllocID(String allocID) {
        this.allocID = allocID;
    }

    @Override
    public Parties getParties() {
        return parties;
    }

    @Override
    public void setParties() {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        this.parties = new Parties44(context);
    }

    @Override
    public void clearParties() {
        this.parties = null;
    }

    public void setParties(Parties parties) {
        this.parties = parties;
    }

    @XmlElementRef
    public PartyGroup[] getPartyIDGroups() {
        return parties == null ? null : parties.getPartyIDGroups();
    }

    public void setPartyIDGroups(PartyGroup[] partyIDGroups) {
        if (partyIDGroups != null) {
            if (parties == null) {
                setParties();
            }
            ((Parties44) parties).setPartyIDGroups(partyIDGroups);
        }
    }

    @XmlAttribute(name = "ID2")
    @Override
    public String getSecondaryAllocID() {
        return secondaryAllocID;
    }

    @Override
    public void setSecondaryAllocID(String secondaryAllocID) {
        this.secondaryAllocID = secondaryAllocID;
    }

    @XmlAttribute(name = "TrdDt")
    @XmlJavaTypeAdapter(FixDateAdapter.class)
    @Override
    public Date getTradeDate() {
        return tradeDate;
    }

    @Override
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    @XmlAttribute(name = "TxnTm")
    @XmlJavaTypeAdapter(FixDateTimeAdapter.class)
    @Override
    public Date getTransactTime() {
        return transactTime;
    }

    @Override
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    @XmlAttribute(name = "Stat")
    @Override
    public AllocStatus getAllocStatus() {
        return allocStatus;
    }

    @Override
    public void setAllocStatus(AllocStatus allocStatus) {
        this.allocStatus = allocStatus;
    }

    @XmlAttribute(name = "RejCode")
    @Override
    public AllocRejCode getAllocRejCode() {
        return allocRejCode;
    }

    @Override
    public void setAllocRejCode(AllocRejCode allocRejCode) {
        this.allocRejCode = allocRejCode;
    }

    @XmlAttribute(name = "Typ")
    @Override
    public AllocType getAllocType() {
        return allocType;
    }

    @Override
    public void setAllocType(AllocType allocType) {
        this.allocType = allocType;
    }

    @XmlAttribute(name = "ImReqTyp")
    @Override
    public AllocIntermedReqType getAllocIntermedReqType() {
        return allocIntermedReqType;
    }

    @Override
    public void setAllocIntermedReqType(AllocIntermedReqType allocIntermedReqType) {
        this.allocIntermedReqType = allocIntermedReqType;
    }

    @XmlAttribute(name = "MtchStat")
    @Override
    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    @Override
    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    @XmlAttribute(name = "Prod")
    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public void setProduct(Product product) {
        this.product = product;
    }

    @XmlAttribute(name = "SecTyp")
    @Override
    public String getSecurityType() {
        return securityType;
    }

    @Override
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    @XmlAttribute(name = "Txt")
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @XmlAttribute(name = "EncTxtLen")
    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    @XmlAttribute(name = "EncTxt")
    @Override
    public byte[] getEncodedText() {
        return encodedText;
    }

    @Override
    public void setEncodedText(byte[] encodedText) {
        this.encodedText = encodedText;
        if (encodedTextLen == null) {
            encodedTextLen = new Integer(encodedText.length);
        }
    }

    @XmlAttribute(name = "LegalCnfm")
    @Override
    public Boolean getLegalConfirm() {
        return legalConfirm;
    }

    @Override
    public void setLegalConfirm(Boolean legalConfirm) {
        this.legalConfirm = legalConfirm;
    }

    @Override
    public Integer getNoAllocs() {
        return noAllocs;
    }

    @Override
    public void setNoAllocs(Integer noAllocs) {
        this.noAllocs = noAllocs;
        if (noAllocs != null) {
            allocAckGroups = new AllocAckGroup[noAllocs.intValue()];
            for (int i = 0; i < allocAckGroups.length; i++) {
                allocAckGroups[i] = new AllocAckGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public AllocAckGroup[] getAllocAckGroups() {
        return allocAckGroups;
    }

    public void setAllocAckGroups(AllocAckGroup[] allocAckGroups) {
        this.allocAckGroups = allocAckGroups;
        if (allocAckGroups != null) {
            noAllocs = new Integer(allocAckGroups.length);
        }
    }

    @Override
    public AllocAckGroup addAllocAckGroup() {
        AllocAckGroup group = new AllocAckGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<AllocAckGroup> groups = new ArrayList<AllocAckGroup>();
        if (allocAckGroups != null && allocAckGroups.length > 0) {
            groups = new ArrayList<AllocAckGroup>(Arrays.asList(allocAckGroups));
        }
        groups.add(group);
        allocAckGroups = groups.toArray(new AllocAckGroup[groups.size()]);
        noAllocs = new Integer(allocAckGroups.length);

        return group;
    }

    @Override
    public AllocAckGroup deleteAllocAckGroup(int index) {
        AllocAckGroup result = null;
        if (allocAckGroups != null && allocAckGroups.length > 0 && allocAckGroups.length > index) {
            List<AllocAckGroup> groups = new ArrayList<AllocAckGroup>(Arrays.asList(allocAckGroups));
            result = groups.remove(index);
            allocAckGroups = groups.toArray(new AllocAckGroup[groups.size()]);
            if (allocAckGroups.length > 0) {
                noAllocs = new Integer(allocAckGroups.length);
            } else {
                allocAckGroups = null;
                noAllocs = null;
            }
        }

        return result;
    }

    @Override
    public int clearAllocAckGroups() {
        int result = 0;
        if (allocAckGroups != null && allocAckGroups.length > 0) {
            result = allocAckGroups.length;
            allocAckGroups = null;
            noAllocs = null;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected byte[] encodeFragmentSecured(boolean secured) throws TagNotPresentException, BadFormatMsgException {
        byte[] result = new byte[0];
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        try {
            if (MsgUtil.isTagInList(TagNum.AllocID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocID, allocID);
            }
            if (MsgUtil.isTagInList(TagNum.ClientID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClientID, clientID);
            }
            if (MsgUtil.isTagInList(TagNum.ExecBroker, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ExecBroker, execBroker);
            }
            if (parties != null) {
                bao.write(parties.encode(getMsgSecureTypeForFlag(secured)));
            }
            if (MsgUtil.isTagInList(TagNum.SecondaryAllocID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecondaryAllocID, secondaryAllocID);
            }
            if (MsgUtil.isTagInList(TagNum.TradeDate, SECURED_TAGS, secured)) {
                TagEncoder.encodeTimestamp(bao, TagNum.TradeDate, tradeDate);
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (allocStatus != null && MsgUtil.isTagInList(TagNum.AllocStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocStatus, allocStatus.getValue());
            }
            if (allocRejCode != null && MsgUtil.isTagInList(TagNum.AllocRejCode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocRejCode, allocRejCode.getValue());
            }
            if (allocType != null && MsgUtil.isTagInList(TagNum.AllocType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocType, allocType.getValue());
            }
            if (allocIntermedReqType != null && MsgUtil.isTagInList(TagNum.AllocIntermedReqType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.AllocIntermedReqType, allocIntermedReqType.getValue());
            }
            if (matchStatus != null && MsgUtil.isTagInList(TagNum.MatchStatus, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.MatchStatus, matchStatus.getValue());
            }
            if (product != null && MsgUtil.isTagInList(TagNum.Product, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Product, product.getValue());
            }
            if (MsgUtil.isTagInList(TagNum.SecurityType, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            }
            if (MsgUtil.isTagInList(TagNum.Text, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.Text, text);
            }
            if (encodedTextLen != null && encodedTextLen.intValue() > 0 && MsgUtil.isTagInList(TagNum.EncodedTextLen, SECURED_TAGS, secured)) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (MsgUtil.isTagInList(TagNum.LegalConfirm, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.LegalConfirm, legalConfirm);
            }
            if (noAllocs != null && MsgUtil.isTagInList(TagNum.NoAllocs, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoAllocs, noAllocs);
                if (allocAckGroups != null && allocAckGroups.length == noAllocs.intValue()) {
                    for (int i = 0; i < noAllocs.intValue(); i++) {
                        if (allocAckGroups[i] != null) {
                            bao.write(allocAckGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "AllocGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoAllocs.getValue(), error);
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
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired);
        if (PARTIES_COMP_TAGS.contains(tag.tagNum)) {
            if (parties == null) {
                parties = new Parties44(context);
            }
            parties.decode(tag, message);
        }
        if (ALLOC_ACK_GROUP_TAGS.contains(tag.tagNum)) {
            if (noAllocs != null && noAllocs.intValue() > 0) {
                message.reset();
                allocAckGroups = new AllocAckGroup[noAllocs.intValue()];
                for (int i = 0; i < noAllocs.intValue(); i++) {
                    AllocAckGroup group = new AllocAckGroup44(context);
                    group.decode(message);
                    allocAckGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [AllocationInstructionAckMsg] message version [4.4].";
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
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
}
