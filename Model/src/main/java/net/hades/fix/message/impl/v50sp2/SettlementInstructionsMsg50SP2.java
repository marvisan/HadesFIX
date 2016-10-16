/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlementInstructionsMsg50SP2.java
 *
 * $Id: SettlementInstructionsMsg50SP2.java,v 1.3 2011-04-14 23:44:27 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.message.FIXFragment;
import net.hades.fix.message.FragmentContext;
import net.hades.fix.message.Header;
import net.hades.fix.message.SettlementInstructionsMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.SettlInstGroup;
import net.hades.fix.message.group.impl.v50sp2.SettlInstGroup50SP2;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SettlInstMode;
import net.hades.fix.message.type.SettlInstReqRejCode;
import net.hades.fix.message.xml.codec.jaxb.adapter.FixDateTimeAdapter;

/**
 * FIX version 5.0SP2 SettlementInstructionsMsg implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 9:32:41 AM
 */
@XmlRootElement(name="SettlInstrctns")
@XmlType(propOrder = {"header", "settlInstGroups"})
@XmlAccessorType(XmlAccessType.NONE)
public class SettlementInstructionsMsg50SP2 extends SettlementInstructionsMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> SETTL_INST_GROUP_TAGS = new SettlInstGroup50SP2().getFragmentAllTags();

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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SettlementInstructionsMsg50SP2() {
        super();
    }

    public SettlementInstructionsMsg50SP2(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public SettlementInstructionsMsg50SP2(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public SettlementInstructionsMsg50SP2(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
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
        SettlementInstructionsMsg50SP2 fixml = (SettlementInstructionsMsg50SP2) fragment;
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
                settlInstGroups[i] = new SettlInstGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
        SettlInstGroup group = new SettlInstGroup50SP2(new FragmentContext(sessionCharset, messageEncoding, validateRequired));
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
    protected void setFragmentCompTagValue(Tag tag, ByteBuffer message)
    throws BadFormatMsgException, InvalidMsgException, TagNotPresentException {
        FragmentContext context = new FragmentContext(sessionCharset, messageEncoding, validateRequired);
        if (SETTL_INST_GROUP_TAGS.contains(tag.tagNum)) {
            if (noSettlInst != null && noSettlInst.intValue() > 0) {
                message.reset();
                settlInstGroups = new SettlInstGroup[noSettlInst.intValue()];
                for (int i = 0; i < noSettlInst.intValue(); i++) {
                    SettlInstGroup group = new SettlInstGroup50SP2(context);
                    group.decode(message);
                    settlInstGroups[i] = group;
                }
            }
        }
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [SettlementInstructionsMsg] message version [5.0SP2].";
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
