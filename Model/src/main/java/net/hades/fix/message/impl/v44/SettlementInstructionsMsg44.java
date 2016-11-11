/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlementInstructionsMsg44.java
 *
 * $Id: SettlementInstructionsMsg44.java,v 1.3 2011-04-14 23:44:38 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.SettlementInstructionsMsg;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.SettlInstGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.SessionRejectReason;
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

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.impl.v44.SettlInstGroup44;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SettlInstMode;
import net.hades.fix.message.type.SettlInstReqRejCode;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.TagEncoder;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 4.4 SettlementInstructionsMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="SettlInstrctns")
@XmlType(propOrder = {"header", "settlInstGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class SettlementInstructionsMsg44 extends SettlementInstructionsMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> SETTL_INST_GROUP_TAGS = new SettlInstGroup44().getFragmentAllTags();

    protected static final Set<Integer> START_COMP_TAGS;

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
        ALL_TAGS.addAll(SETTL_INST_GROUP_TAGS);
        START_COMP_TAGS = new HashSet<Integer>(SETTL_INST_GROUP_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SettlementInstructionsMsg44() {
        super();
    }

    public SettlementInstructionsMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SettlementInstructionsMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public SettlementInstructionsMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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
        SettlementInstructionsMsg44 fixml = (SettlementInstructionsMsg44) fragment;
        if (fixml.getSettlInstMsgID() != null) {
            settlInstMsgID = fixml.getSettlInstMsgID();
        }
        if (fixml.getSettlInstReqID() != null) {
            settlInstReqID = fixml.getSettlInstReqID();
        }
        if (fixml.getSettlInstMode() != null) {
            settlInstMode = fixml.getSettlInstMode();
        }
        if (fixml.getSettlInstReqRejCode() != null) {
            settlInstReqRejCode = fixml.getSettlInstReqRejCode();
        }
        if (fixml.getText() != null) {
            text = fixml.getText();
        }
        if (fixml.getEncodedTextLen() != null) {
            encodedTextLen = fixml.getEncodedTextLen();
            encodedText = fixml.getEncodedText();
        }
        if (fixml.getClOrdID() != null) {
            clOrdID = fixml.getClOrdID();
        }
        if (fixml.getTransactTime() != null) {
            transactTime = fixml.getTransactTime();
        }
        if (fixml.getSettlInstGroups() != null && fixml.getSettlInstGroups().length > 0) {
            setSettlInstGroups(fixml.getSettlInstGroups());
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

    @XmlAttribute(name = "SettlInstMsgID")
    @Override
    public String getSettlInstMsgID() {
        return settlInstMsgID;
    }

    @Override
    public void setSettlInstMsgID(String settlInstMsgID) {
        this.settlInstMsgID = settlInstMsgID;
    }

    @XmlAttribute(name = "SettlInstReqID")
    @Override
    public String getSettlInstReqID() {
        return settlInstReqID;
    }

    @Override
    public void setSettlInstReqID(String settlInstReqID) {
        this.settlInstReqID = settlInstReqID;
    }

    @XmlAttribute(name = "SettlInstMode")
    @Override
    public SettlInstMode getSettlInstMode() {
        return settlInstMode;
    }

    @Override
    public void setSettlInstMode(SettlInstMode settlInstMode) {
        this.settlInstMode = settlInstMode;
    }

    @XmlAttribute(name = "SettlInstReqRejCode")
    @Override
    public SettlInstReqRejCode getSettlInstReqRejCode() {
        return settlInstReqRejCode;
    }

    @Override
    public void setSettlInstReqRejCode(SettlInstReqRejCode settlInstReqRejCode) {
        this.settlInstReqRejCode = settlInstReqRejCode;
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

    @XmlAttribute(name = "ClOrdID")
    @Override
    public String getClOrdID() {
        return clOrdID;
    }

    @Override
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
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

    @Override
    public Integer getNoSettlInst() {
        return noSettlInst;
    }

    @Override
    public void setNoSettlInst(Integer noSettlInst) {
        this.noSettlInst = noSettlInst;
        if (noSettlInst != null) {
            settlInstGroups = new SettlInstGroup[noSettlInst.intValue()];
            for (int i = 0; i < settlInstGroups.length; i++) {
                settlInstGroups[i] = new SettlInstGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
            }
        }
    }

    @XmlElementRef
    @Override
    public SettlInstGroup[] getSettlInstGroups() {
        return settlInstGroups;
    }

    public void setSettlInstGroups(SettlInstGroup[] settlInstGroups) {
        this.settlInstGroups = settlInstGroups;
        if (settlInstGroups != null) {
            noSettlInst = new Integer(settlInstGroups.length);
        }
    }

    @Override
    public SettlInstGroup addSettlInstGroup() {
        SettlInstGroup group = new SettlInstGroup44(new FragmentContext(sessionCharset, messageEncoding, encryptionRequired, crypter, validateRequired));
        List<SettlInstGroup> groups = new ArrayList<SettlInstGroup>();
        if (settlInstGroups != null && settlInstGroups.length > 0) {
            groups = new ArrayList<SettlInstGroup>(Arrays.asList(settlInstGroups));
        }
        groups.add(group);
        settlInstGroups = groups.toArray(new SettlInstGroup[groups.size()]);
        noSettlInst = new Integer(settlInstGroups.length);

        return group;
    }

    @Override
    public SettlInstGroup deleteSettlInstGroup(int index) {
        SettlInstGroup result = null;
        if (settlInstGroups != null && settlInstGroups.length > 0 && settlInstGroups.length > index) {
            List<SettlInstGroup> groups = new ArrayList<SettlInstGroup>(Arrays.asList(settlInstGroups));
            result = groups.remove(index);
            settlInstGroups = groups.toArray(new SettlInstGroup[groups.size()]);
            if (settlInstGroups.length > 0) {
                noSettlInst = new Integer(settlInstGroups.length);
            } else {
                settlInstGroups = null;
                noSettlInst = null;
            }
        }

        return result;
    }

    @Override
    public int clearSettlInstGroups() {
        int result = 0;
        if (settlInstGroups != null && settlInstGroups.length > 0) {
            result = settlInstGroups.length;
            settlInstGroups = null;
            noSettlInst = null;
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
            if (MsgUtil.isTagInList(TagNum.SettlInstMsgID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstMsgID, settlInstMsgID);
            }
            if (MsgUtil.isTagInList(TagNum.SettlInstReqID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstReqID, settlInstReqID);
            }
            if (settlInstMode != null && MsgUtil.isTagInList(TagNum.SettlInstMode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstMode, settlInstMode.getValue());
            }
            if (settlInstReqRejCode != null && MsgUtil.isTagInList(TagNum.SettlInstReqRejCode, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.SettlInstReqRejCode, settlInstReqRejCode.getValue());
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
            if (MsgUtil.isTagInList(TagNum.ClOrdID, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            }
            if (MsgUtil.isTagInList(TagNum.TransactTime, SECURED_TAGS, secured)) {
                TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            }
            if (noSettlInst != null && MsgUtil.isTagInList(TagNum.NoSettlInst, SECURED_TAGS, secured)) {
                TagEncoder.encode(bao, TagNum.NoSettlInst, noSettlInst);
                if (settlInstGroups != null && settlInstGroups.length == noSettlInst.intValue()) {
                    for (int i = 0; i < noSettlInst.intValue(); i++) {
                        if (settlInstGroups[i] != null) {
                            bao.write(settlInstGroups[i].encode(getMsgSecureTypeForFlag(secured)));
                        }
                    }
                } else {
                    String error = "SettlInstGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoSettlInst.getValue(), error);
                }
            }
            if (parties != null) {
                bao.write(parties.encode(getMsgSecureTypeForFlag(secured)));
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
        if (SETTL_INST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noSettlInst != null && noSettlInst.intValue() > 0) {
                message.reset();
                settlInstGroups = new SettlInstGroup[noSettlInst.intValue()];
                for (int i = 0; i < noSettlInst.intValue(); i++) {
                    SettlInstGroup group = new SettlInstGroup44(context);
                    group.decode(message);
                    settlInstGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SettlementInstructionsMsg] message version [4.4].";
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
