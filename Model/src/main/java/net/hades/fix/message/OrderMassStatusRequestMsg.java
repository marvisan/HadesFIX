/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderMassStatusRequestMsg.java
 *
 * $Id: OrderMassStatusRequestMsg.java,v 1.1 2011-05-09 08:21:12 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.TargetParties;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.TagEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MassStatusReqType;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.TagNum;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The order mass status request message requests the status for orders matching criteria specified within the
 * request.<br/>
 * A mass status request is assigned a ClOrdID and is treated as a separate entity.<br/>
 * ExecutionReports with ExecType="Order Status" are returned for all orders matching the criteria provided on
 * the request.<br/>
 * Specifying order selection criteria is specified using the MassStatusReqType field.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 09/05/2011, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class OrderMassStatusRequestMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.MassStatusReqID.getValue(),
        TagNum.MassStatusReqType.getValue(),
        TagNum.Account.getValue(),
        TagNum.AcctIDSource.getValue(),
        TagNum.TradingSessionID.getValue(),
        TagNum.TradingSessionSubID.getValue(),
        TagNum.Side.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.MassStatusReqID.getValue(),
        TagNum.MassStatusReqType.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 584 REQUIRED. Starting with 4.3 version.
     */
    protected String massStatusReqID;

    /**
     * TagNum = 585 REQUIRED. Starting with 4.3 version.
     */
    protected MassStatusReqType massStatusReqType;

    /**
     * Starting with 4.3 version.
     */
    protected Parties parties;

    /**
     * Starting with 5.0SP2 version.
     */
    protected TargetParties targetParties;

    /**
     * TagNum = 1. Starting with 4.3 version.
     */
    protected String account;

    /**
     * TagNum = 660. Starting with 4.4 version.
     */
    protected AcctIDSource acctIDSource;

    /**
     * TagNum = 336. Starting with 4.3 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.3 version.
     */
    protected String tradingSessionSubID;

    /**
     * Starting with 4.3 version.
     */
    protected Instrument instrument;

    /**
     * Starting with 4.3 version.
     */
    protected UnderlyingInstrument underlyingInstrument;

    /**
     * TagNum = 54. Starting with 4.3 version.
     */
    protected Side side;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public OrderMassStatusRequestMsg() {
        super();
    }

    public OrderMassStatusRequestMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public OrderMassStatusRequestMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.OrderMassStatusRequest.getValue(), beginString);
    }

    public OrderMassStatusRequestMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.OrderMassStatusRequest.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MassStatusReqID, required = true)
    public String getMassStatusReqID() {
        return massStatusReqID;
    }

    /**
     * Message field setter.
     * @param massStatusReqID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MassStatusReqID, required = true)
    public void setMassStatusReqID(String massStatusReqID) {
        this.massStatusReqID = massStatusReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MassStatusReqType, required = true)
    public MassStatusReqType getMassStatusReqType() {
        return massStatusReqType;
    }

    /**
     * Message field setter.
     * @param massStatusReqType field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.MassStatusReqType, required = true)
    public void setMassStatusReqType(MassStatusReqType massStatusReqType) {
        this.massStatusReqType = massStatusReqType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to the proper component implementation.
     */
    @FIXVersion(introduced="4.3")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the field to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP2")
    public TargetParties getTargetParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the TargetParties component if used in this message to the proper implementation class.
     */
    @FIXVersion(introduced="5.0SP2")
    public void setTargetParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the TargetParties component to null.
     */
    @FIXVersion(introduced="5.0SP2")
    public void clearTargetParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Account)
    public String getAccount() {
        return account;
    }

    /**
     * Message field setter.
     * @param account field value
     */
    @FIXVersion(introduced = "4.3")
    @TagNumRef(tagNum = TagNum.Account)
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AcctIDSource)
    public AcctIDSource getAcctIDSource() {
        return acctIDSource;
    }

    /**
     * Message field setter.
     * @param acctIDSource field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.AcctIDSource)
    public void setAcctIDSource(AcctIDSource acctIDSource) {
        this.acctIDSource = acctIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionID, required=true)
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionID, required=true)
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    /**
     * Message field setter.
     * @param tradingSessionSubID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    public Instrument getInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Instrument component if used in this message to the proper implementation class.
     */
    @FIXVersion(introduced="4.3")
    public void setInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Instrument component to null.
     */
    @FIXVersion(introduced="4.3")
    public void clearInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.3")
    public UnderlyingInstrument getUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Sets the UnderlyingInstrument component if used in this message to the proper implementation class.
     */
    @FIXVersion(introduced = "4.3")
    public void setUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }
    
    /**
     * Sets the UnderlyingInstrument component to null.
     */
    @FIXVersion(introduced = "4.3")
    public void clearUnderlyingInstrument() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Side, required = true)
    public Side getSide() {
        return side;
    }

    /**
     * Message field setter.
     * @param side field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.Side, required = true)
    public void setSide(Side side) {
        this.side = side;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (massStatusReqID == null || massStatusReqID.trim().isEmpty()) {
            errorMsg.append(" [MassStatusReqID]");
            hasMissingTag = true;
        }
        if (massStatusReqType == null) {
            errorMsg.append(" [MassStatusReqType]");
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
            TagEncoder.encode(bao, TagNum.MassStatusReqID, massStatusReqID);
            if (massStatusReqType != null) {
                TagEncoder.encode(bao, TagNum.MassStatusReqType, massStatusReqType.getValue());
            }
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            if (targetParties != null) {
                bao.write(targetParties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.Account, account);
            if (acctIDSource != null) {
                TagEncoder.encode(bao, TagNum.AcctIDSource, acctIDSource.getValue());
            }
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            if (instrument != null) {
                bao.write(instrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (underlyingInstrument != null) {
                bao.write(underlyingInstrument.encode(MsgSecureType.ALL_FIELDS));
            }
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
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
            case MassStatusReqID:
                massStatusReqID = new String(tag.value, sessionCharset);
                break;

            case MassStatusReqType:
                massStatusReqType = MassStatusReqType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case Account:
                account = new String(tag.value, sessionCharset);
                break;

            case AcctIDSource:
                acctIDSource = AcctIDSource.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case TradingSessionID:
                tradingSessionID = new String(tag.value, sessionCharset);
                break;

            case TradingSessionSubID:
                tradingSessionSubID = new String(tag.value, sessionCharset);
                break;

            case Side:
                side = Side.valueFor((new String(tag.value, sessionCharset).charAt(0)));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [OrderMassStatusRequestMsg] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
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
        StringBuilder b = new StringBuilder("{OrderMassStatusRequestMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.MassStatusReqID, massStatusReqID);
        printTagValue(b, TagNum.MassStatusReqType, massStatusReqType);
        printTagValue(b, parties);
        printTagValue(b, targetParties);
        printTagValue(b, TagNum.Account, account);
        printTagValue(b, TagNum.AcctIDSource, acctIDSource);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, instrument);
        printTagValue(b, underlyingInstrument);
        printTagValue(b, TagNum.Side, side);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
