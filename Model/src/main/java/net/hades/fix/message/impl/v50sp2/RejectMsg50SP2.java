/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RejectMsg50SP2.java
 *
 * $Id: RejectMsg50SP2.java,v 1.2 2010-11-14 08:53:00 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

import net.hades.fix.message.type.ApplVerID;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.fixt11.RejectMsgFIXT11;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;

/**
 * FIXT 5.0SP2 implementation of RejectMsg.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 18/12/2008, 8:26:23 PM
 */
public class RejectMsg50SP2 extends RejectMsgFIXT11 {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.RefSeqNo.getValue(),
        TagNum.RefTagID.getValue(),
        TagNum.RefMsgType.getValue(),
        TagNum.RefApplVerID.getValue(),
        TagNum.RefApplExtID.getValue(),
        TagNum.RefCstmApplVerID.getValue(),
        TagNum.SessionRejectReason.getValue(),
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.RefSeqNo.getValue()
    }));

    protected static final Set<Integer> ALL_TAGS;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        ALL_TAGS = new HashSet<Integer>(TAGS);
        ALL_TAGS.addAll(START_DATA_TAGS);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public RejectMsg50SP2(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public RejectMsg50SP2(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public RejectMsg50SP2(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
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

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public Integer getRefSeqNo() {
        return refSeqNo;
    }

    @Override
    public void setRefSeqNo(Integer refSeqNo) {
        this.refSeqNo = refSeqNo;
    }

    @Override
    public Integer getRefTagID() {
        return refTagID;
    }

    @Override
    public void setRefTagID(Integer refTagID) {
        this.refTagID = refTagID;
    }

    @Override
    public String getRefMsgType() {
        return refMsgType;
    }

    @Override
    public void setRefMsgType(String refMsgType) {
        this.refMsgType = refMsgType;
    }

    @Override
    public ApplVerID getRefApplVerID() {
        return refApplVerID;
    }

    @Override
    public void setRefApplVerID(ApplVerID refApplVerID) {
        this.refApplVerID = refApplVerID;
    }

    @Override
    public Integer getRefApplExtID() {
        return refApplExtID;
    }

    @Override
    public void setRefApplExtID(Integer refApplExtID) {
        this.refApplExtID = refApplExtID;
    }

    @Override
    public String getRefCstmApplVerID() {
        return refCstmApplVerID;
    }

    @Override
    public void setRefCstmApplVerID(String refCstmApplVerID) {
        this.refCstmApplVerID = refCstmApplVerID;
    }

    @Override
    public SessionRejectReason getSessionRejectReason() {
        return sessionRejectReason;
    }

    @Override
    public void setSessionRejectReason(SessionRejectReason sessionRejectReason) {
        this.sessionRejectReason = sessionRejectReason;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

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

    @Override
    public Integer getEncodedTextLen() {
        return encodedTextLen;
    }

    @Override
    public void setEncodedTextLen(Integer encodedTextLen) {
        this.encodedTextLen = encodedTextLen;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [RejectMsg] message version [5.0SP2].";
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
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
