/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlementInstructionsMsg.java
 *
 * $Id: SettlementInstructionsMsg.java,v 1.3 2011-04-28 10:07:43 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.group.SettlInstGroup;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.util.MsgUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.hades.fix.message.anno.TagNumRef;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.PaymentMethod;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.SettlDeliveryType;
import net.hades.fix.message.type.SettlInstMode;
import net.hades.fix.message.type.SettlInstReqRejCode;
import net.hades.fix.message.type.SettlInstSource;
import net.hades.fix.message.type.SettlInstTransType;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.StandInstDbType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Settlement Instructions message provides the broker’s, the institution’s, or the intermediary’s instructions
 * for trade settlement. This message has been designed so that it can be sent from the broker to the institution,
 * from the institution to the broker, or from either to an independent “standing instructions” database or matching
 * system or, for CIV, from an intermediary to a fund manager.<br/>
 * The Settlement Instructions message can be used in one of three modes (SettlInstMode):
 * <ul>
 *      <li>To provide “standing instructions” for the settlement of trades occurring in the future. The message could
 * either be sent in an 'unsolicited' fashion (i.e. a 'push'-style update from one firm to that firm's
 * counterparties) or in response to a Settlement Instruction Request message. In either of these scenarios, this
 * message can provide multiple settlement instructions.</li>
 *      <li>To reject a Settlement Instruction Request message (e.g. unable to process request, no matching settlement
 * instructions found).</li>
 *      <li>To provide settlement instructions for a specific Order with a single account either as overriding or
 * standing instructions to support matching. The ClOrdID field should be used to link the settlement
 * instructions to the corresponding Order message.</li>
 * </ul>
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 29/01/2011, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class SettlementInstructionsMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SettlInstMsgID.getValue(),
        TagNum.SettlInstReqID.getValue(),
        TagNum.SettlInstMode.getValue(),
        TagNum.SettlInstReqRejCode.getValue(),
        TagNum.Text.getValue(),
        TagNum.ClOrdID.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.NoSettlInst.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SettlInstID.getValue(),
        TagNum.SettlInstMsgID.getValue(),
        TagNum.SettlInstTransType.getValue(),
        TagNum.SettlInstRefID.getValue(),
        TagNum.SettlInstMode.getValue(),
        TagNum.SettlInstSource.getValue(),
        TagNum.AllocAccount.getValue(),
        TagNum.TransactTime.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 162 REQUIRED. Starting with 4.1 version.
     */
    protected String settlInstID;

    /**
     * TagNum = 777 REQUIRED. Starting with 4.4 version.
     */
    protected String settlInstMsgID;

    /**
     * TagNum = 791. Starting with 4.4 version.
     */
    protected String settlInstReqID;

    /**
     * TagNum = 163. Starting with 4.1 version.
     */
    protected SettlInstTransType settlInstTransType;

    /**
     * TagNum = 214 REQUIRED. Starting with 4.2 version.
     */
    protected String settlInstRefID;

    /**
     * TagNum = 160 REQUIRED. Starting with 4.1 version.
     */
    protected SettlInstMode settlInstMode;

    /**
     * TagNum = 792. Starting with 4.4 version.
     */
    protected SettlInstReqRejCode settlInstReqRejCode;

    /**
     * TagNum = 58. Starting with 4.4 version.
     */
    protected String text;

    /**
     * TagNum = 354. Starting with 4.4 version.
     */
    protected Integer encodedTextLen;

    /**
     * TagNum = 355. Starting with 4.4 version.
     */
    protected byte[] encodedText;

    /**
     * TagNum = 165 REQUIRED. Starting with 4.1 version.
     */
    protected SettlInstSource settlInstSource;

    /**
     * TagNum = 79 REQUIRED. Starting with 4.1 version.
     */
    protected String allocAccount;

    /**
     * TagNum = 467. Starting with 4.3 version.
     */
    protected String individualAllocID;

    /**
     * TagNum = 11. Starting with 4.3 version.
     */
    protected String clOrdID;

    /**
     * TagNum = 166. Starting with 4.1 version.
     */
    protected String settlLocation;

    /**
     * TagNum = 75. Starting with 4.1 version.
     */
    protected Date tradeDate;

    /**
     * TagNum = 70. Starting with 4.1 version.
     */
    protected String allocID;

    /**
     * TagNum = 30. Starting with 4.1 version.
     */
    protected String lastMkt;

    /**
     * TagNum = 336. Starting with 4.2 version.
     */
    protected String tradingSessionID;

    /**
     * TagNum = 625. Starting with 4.3 version.
     */
    protected String tradingSessionSubID;

    /**
     * TagNum = 54. Starting with 4.1 version.
     */
    protected Side side;

    /**
     * TagNum = 167. Starting with 4.1 version.
     */
    protected String securityType;

    /**
     * TagNum = 168. Starting with 4.1 version.
     */
    protected Date effectiveTime;

    /**
     * TagNum = 60 REQUIRED. Starting with 4.1 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 778 REQUIRED. Starting with 4.4 version.
     */
    protected Integer noSettlInst;

    /**
     * Starting with 4.4 version.
     */
    protected SettlInstGroup[] settlInstGroups;

    /**
     * Starting with 4.3 version.
     */
    protected Parties parties;

    /**
     * TagNum = 109. Starting with 4.1 version.
     */
    protected String clientID;

    /**
     * TagNum = 76. Starting with 4.1 version.
     */
    protected String execBroker;

    /**
     * TagNum = 169. Starting with 4.1 version.
     */
    protected StandInstDbType standInstDbType;

    /**
     * TagNum = 170. Starting with 4.1 version.
     */
    protected String standInstDbName;

    /**
     * TagNum = 171. Starting with 4.1 version.
     */
    protected String standInstDbID;

    /**
     * TagNum = 172. Starting with 4.1 version.
     */
    protected SettlDeliveryType settlDeliveryType;

    /**
     * TagNum = 173. Starting with 4.1 version.
     */
    protected String settlDepositoryCode;

    /**
     * TagNum = 174. Starting with 4.1 version.
     */
    protected String settlBrkrCode;

    /**
     * TagNum = 175. Starting with 4.1 version.
     */
    protected String settlInstCode;

    /**
     * TagNum = 176. Starting with 4.1 version.
     */
    protected String securitySettlAgentName;

    /**
     * TagNum = 177. Starting with 4.1 version.
     */
    protected String securitySettlAgentCode;

    /**
     * TagNum = 178. Starting with 4.1 version.
     */
    protected String securitySettlAgentAcctNum;

    /**
     * TagNum = 179. Starting with 4.1 version.
     */
    protected String securitySettlAgentAcctName;

    /**
     * TagNum = 180. Starting with 4.1 version.
     */
    protected String securitySettlAgentContactName;

    /**
     * TagNum = 181. Starting with 4.1 version.
     */
    protected String securitySettlAgentContactPhone;

    /**
     * TagNum = 182. Starting with 4.1 version.
     */
    protected String cashSettlAgentName;

    /**
     * TagNum = 183. Starting with 4.1 version.
     */
    protected String cashSettlAgentCode;

    /**
     * TagNum = 184. Starting with 4.1 version.
     */
    protected String cashSettlAgentAcctNum;

    /**
     * TagNum = 185. Starting with 4.1 version.
     */
    protected String cashSettlAgentAcctName;

    /**
     * TagNum = 186. Starting with 4.1 version.
     */
    protected String cashSettlAgentContactName;

    /**
     * TagNum = 187. Starting with 4.1 version.
     */
    protected String cashSettlAgentContactPhone;

    /**
     * TagNum = 492. Starting with 4.3 version.
     */
    protected PaymentMethod paymentMethod;

    /**
     * TagNum = 476. Starting with 4.3 version.
     */
    protected String paymentRef;

    /**
     * TagNum = 488. Starting with 4.3 version.
     */
    protected String cardHolderName;

    /**
     * TagNum = 489. Starting with 4.3 version.
     */
    protected String cardNumber;

    /**
     * TagNum = 503. Starting with 4.3 version.
     */
    protected Date cardStartDate;

    /**
     * TagNum = 490. Starting with 4.3 version.
     */
    protected Date cardExpDate;

    /**
     * TagNum = 491. Starting with 4.3 version.
     */
    protected String cardIssNum;

    /**
     * TagNum = 504. Starting with 4.3 version.
     */
    protected Date paymentDate;

    /**
     * TagNum = 505. Starting with 4.3 version.
     */
    protected String paymentRemitterID;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SettlementInstructionsMsg() {
        super();
    }

    public SettlementInstructionsMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public SettlementInstructionsMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.SettlInstructions.getValue(), beginString);
    }

    public SettlementInstructionsMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.SettlInstructions.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstID, required=true)
    public String getSettlInstID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param settlInstID field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstID, required=true)
    public void setSettlInstID(String settlInstID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstMsgID, required=true)
    public String getSettlInstMsgID() {
        return settlInstMsgID;
    }

    /**
     * Message field setter.
     * @param settlInstMsgID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstMsgID, required=true)
    public void setSettlInstMsgID(String settlInstMsgID) {
        this.settlInstMsgID = settlInstMsgID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstReqID)
    public String getSettlInstReqID() {
        return settlInstReqID;
    }

    /**
     * Message field setter.
     * @param settlInstReqID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstReqID)
    public void setSettlInstReqID(String settlInstReqID) {
        this.settlInstReqID = settlInstReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstTransType, required=true)
    public SettlInstTransType getSettlInstTransType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param settlInstTransType field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstTransType, required=true)
    public void setSettlInstTransType(SettlInstTransType settlInstTransType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstRefID, required=true)
    public String getSettlInstRefID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param settlInstRefID field value
     */
    @FIXVersion(introduced="4.2", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstRefID, required=true)
    public void setSettlInstRefID(String settlInstRefID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlInstMode, required=true)
    public SettlInstMode getSettlInstMode() {
        return settlInstMode;
    }

    /**
     * Message field setter.
     * @param settlInstMode field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.SettlInstMode, required=true)
    public void setSettlInstMode(SettlInstMode settlInstMode) {
        this.settlInstMode = settlInstMode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstReqRejCode)
    public SettlInstReqRejCode getSettlInstReqRejCode() {
        return settlInstReqRejCode;
    }

    /**
     * Message field setter.
     * @param settlInstReqRejCode field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstReqRejCode)
    public void setSettlInstReqRejCode(SettlInstReqRejCode settlInstReqRejCode) {
        this.settlInstReqRejCode = settlInstReqRejCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Text)
    public String getText() {
        return text;
    }

    /**
     * Message field setter.
     * @param text field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Text)
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public Integer getEncodedTextLen() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param encodedTextLen field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedTextLen)
    public void setEncodedTextLen(Integer encodedTextLen) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public byte[] getEncodedText() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param encodedText field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EncodedText)
    public void setEncodedText(byte[] encodedText) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstSource, required=true)
    public SettlInstSource getSettlInstSource() {
        return settlInstSource;
    }

    /**
     * Message field setter.
     * @param settlInstSource field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstSource, required=true)
    public void setSettlInstSource(SettlInstSource settlInstSource) {
        this.settlInstSource = settlInstSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.AllocAccount, required=true)
    public String getAllocAccount() {
        return allocAccount;
    }

    /**
     * Message field setter.
     * @param allocAccount field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.AllocAccount, required=true)
    public void setAllocAccount(String allocAccount) {
        this.allocAccount = allocAccount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.IndividualAllocID)
    public String getIndividualAllocID() {
        return individualAllocID;
    }

    /**
     * Message field setter.
     * @param individualAllocID field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.IndividualAllocID)
    public void setIndividualAllocID(String individualAllocID) {
        this.individualAllocID = individualAllocID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public String getClOrdID() {
        return clOrdID;
    }

    /**
     * Message field setter.
     * @param clOrdID field value
     */
    @FIXVersion(introduced="4.3")
    @TagNumRef(tagNum=TagNum.ClOrdID)
    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.3")
    @TagNumRef(tagNum=TagNum.SettlLocation)
    public String getSettlLocation() {
        return settlLocation;
    }

    /**
     * Message field setter.
     * @param settlLocation field value
     */
    @FIXVersion(introduced="4.1", retired="4.3")
    @TagNumRef(tagNum=TagNum.SettlLocation)
    public void setSettlLocation(String settlLocation) {
        this.settlLocation = settlLocation;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.TradeDate)
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * Message field setter.
     * @param tradeDate field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.TradeDate)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.AllocID)
    public String getAllocID() {
        return allocID;
    }

    /**
     * Message field setter.
     * @param allocID field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.AllocID)
    public void setAllocID(String allocID) {
        this.allocID = allocID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.LastMkt)
    public String getLastMkt() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param lastMkt field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.LastMkt)
    public void setLastMkt(String lastMkt) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2", retired="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public String getTradingSessionID() {
        return tradingSessionID;
    }

    /**
     * Message field setter.
     * @param tradingSessionID field value
     */
    @FIXVersion(introduced="4.2", retired="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionID)
    public void setTradingSessionID(String tradingSessionID) {
        this.tradingSessionID = tradingSessionID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public String getTradingSessionSubID() {
        return tradingSessionSubID;
    }

    /**
     * Message field setter.
     * @param tradingSessionSubID field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.TradingSessionSubID)
    public void setTradingSessionSubID(String tradingSessionSubID) {
        this.tradingSessionSubID = tradingSessionSubID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.Side)
    public Side getSide() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param side field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.Side)
    public void setSide(Side side) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.4")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public String getSecurityType() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param securityType field value
     */
    @FIXVersion(introduced = "4.1", retired = "4.4")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public void setSecurityType(String securityType) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired = "4.4")
    @TagNumRef(tagNum=TagNum.EffectiveTime)
    public Date getEffectiveTime() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param effectiveTime field value
     */
    @FIXVersion(introduced="4.1", retired = "4.4")
    @TagNumRef(tagNum=TagNum.EffectiveTime)
    public void setEffectiveTime(Date effectiveTime) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.TransactTime, required=true)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="4.1")
    @TagNumRef(tagNum=TagNum.TransactTime, required=true)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoSettlInst)
    public Integer getNoSettlInst() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link SettlInstGroup} groups. It will also create an array
     * of {@link SettlInstGroup} objects and set the <code>settlInstGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>settlInstGroups</code> array they will be discarded.<br/>
     * @param noSettlInst field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.NoSettlInst)
    public void setNoSettlInst(Integer noSettlInst) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link SettlInstGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.4")
    public SettlInstGroup[] getSettlInstGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link SettlInstGroup} object to the existing array of <code>settlInstGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noSettlInst</code> field to the proper value.<br/>
     * Note: If the <code>setNoSettlInst</code> method has been called there will already be a number of objects in the
     * <code>settlInstGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.4")
    public SettlInstGroup addSettlInstGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link SettlInstGroup} object from the existing array of <code>settlInstGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noSettlInst</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted object
     */
    @FIXVersion(introduced="4.4")
    public SettlInstGroup deleteSettlInstGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link SettlInstGroup} objects from the <code>settlInstGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noSettlInst</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.4")
    public int clearSettlInstGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired = "4.4")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.3", retired = "4.4")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component to null.
     */
    @FIXVersion(introduced="4.3", retired = "4.4")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.3")
    @TagNumRef(tagNum=TagNum.ClientID)
    public String getClientID() {
        return clientID;
    }

    /**
     * Message field setter.
     * @param clientID field value
     */
    @FIXVersion(introduced="4.1", retired="4.3")
    @TagNumRef(tagNum=TagNum.ClientID)
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.3")
    @TagNumRef(tagNum=TagNum.ExecBroker)
    public String getExecBroker() {
        return execBroker;
    }

    /**
     * Message field setter.
     * @param execBroker field value
     */
    @FIXVersion(introduced="4.1", retired="4.3")
    @TagNumRef(tagNum=TagNum.ExecBroker)
    public void setExecBroker(String execBroker) {
        this.execBroker = execBroker;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.StandInstDbType)
    public StandInstDbType getStandInstDbType() {
        return standInstDbType;
    }

    /**
     * Message field setter.
     * @param standInstDbType field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.StandInstDbType)
    public void setStandInstDbType(StandInstDbType standInstDbType) {
        this.standInstDbType = standInstDbType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.StandInstDbName)
    public String getStandInstDbName() {
        return standInstDbName;
    }

    /**
     * Message field setter.
     * @param standInstDbName field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.StandInstDbName)
    public void setStandInstDbName(String standInstDbName) {
        this.standInstDbName = standInstDbName;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.StandInstDbID)
    public String getStandInstDbID() {
        return standInstDbID;
    }

    /**
     * Message field setter.
     * @param standInstDbID field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.StandInstDbID)
    public void setStandInstDbID(String standInstDbID) {
        this.standInstDbID = standInstDbID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlDeliveryType)
    public SettlDeliveryType getSettlDeliveryType() {
        return settlDeliveryType;
    }

    /**
     * Message field setter.
     * @param settlDeliveryType field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlDeliveryType)
    public void setSettlDeliveryType(SettlDeliveryType settlDeliveryType) {
        this.settlDeliveryType = settlDeliveryType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlDepositoryCode)
    public String getSettlDepositoryCode() {
        return settlDepositoryCode;
    }

    /**
     * Message field setter.
     * @param settlDepositoryCode field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlDepositoryCode)
    public void setSettlDepositoryCode(String settlDepositoryCode) {
        this.settlDepositoryCode = settlDepositoryCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlBrkrCode)
    public String getSettlBrkrCode() {
        return settlBrkrCode;
    }

    /**
     * Message field setter.
     * @param settlBrkrCode field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlBrkrCode)
    public void setSettlBrkrCode(String settlBrkrCode) {
        this.settlBrkrCode = settlBrkrCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstCode)
    public String getSettlInstCode() {
        return settlInstCode;
    }

    /**
     * Message field setter.
     * @param settlInstCode field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstCode)
    public void setSettlInstCode(String settlInstCode) {
        this.settlInstCode = settlInstCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySettlAgentName)
    public String getSecuritySettlAgentName() {
        return securitySettlAgentName;
    }

    /**
     * Message field setter.
     * @param securitySettlAgentName field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySettlAgentName)
    public void setSecuritySettlAgentName(String securitySettlAgentName) {
        this.securitySettlAgentName = securitySettlAgentName;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySettlAgentCode)
    public String getSecuritySettlAgentCode() {
        return securitySettlAgentCode;
    }

    /**
     * Message field setter.
     * @param securitySettlAgentCode field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySettlAgentCode)
    public void setSecuritySettlAgentCode(String securitySettlAgentCode) {
        this.securitySettlAgentCode = securitySettlAgentCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySettlAgentAcctNum)
    public String getSecuritySettlAgentAcctNum() {
        return securitySettlAgentAcctNum;
    }

    /**
     * Message field setter.
     * @param securitySettlAgentAcctNum field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySettlAgentAcctNum)
    public void setSecuritySettlAgentAcctNum(String securitySettlAgentAcctNum) {
        this.securitySettlAgentAcctNum = securitySettlAgentAcctNum;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySettlAgentAcctName)
    public String getSecuritySettlAgentAcctName() {
        return securitySettlAgentAcctName;
    }

    /**
     * Message field setter.
     * @param securitySettlAgentAcctName field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySettlAgentAcctName)
    public void setSecuritySettlAgentAcctName(String securitySettlAgentAcctName) {
        this.securitySettlAgentAcctName = securitySettlAgentAcctName;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySettlAgentContactName)
    public String getSecuritySettlAgentContactName() {
        return securitySettlAgentContactName;
    }

    /**
     * Message field setter.
     * @param securitySettlAgentContactName field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySettlAgentContactName)
    public void setSecuritySettlAgentContactName(String securitySettlAgentContactName) {
        this.securitySettlAgentContactName = securitySettlAgentContactName;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySettlAgentContactPhone)
    public String getSecuritySettlAgentContactPhone() {
        return securitySettlAgentContactPhone;
    }

    /**
     * Message field setter.
     * @param securitySettlAgentContactPhone field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.SecuritySettlAgentContactPhone)
    public void setSecuritySettlAgentContactPhone(String securitySettlAgentContactPhone) {
        this.securitySettlAgentContactPhone = securitySettlAgentContactPhone;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.CashSettlAgentName)
    public String getCashSettlAgentName() {
        return cashSettlAgentName;
    }

    /**
     * Message field setter.
     * @param cashSettlAgentName field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.CashSettlAgentName)
    public void setCashSettlAgentName(String cashSettlAgentName) {
        this.cashSettlAgentName = cashSettlAgentName;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.CashSettlAgentCode)
    public String getCashSettlAgentCode() {
        return cashSettlAgentCode;
    }

    /**
     * Message field setter.
     * @param cashSettlAgentCode field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.CashSettlAgentCode)
    public void setCashSettlAgentCode(String cashSettlAgentCode) {
        this.cashSettlAgentCode = cashSettlAgentCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.CashSettlAgentAcctNum)
    public String getCashSettlAgentAcctNum() {
        return cashSettlAgentAcctNum;
    }

    /**
     * Message field setter.
     * @param cashSettlAgentAcctNum field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.CashSettlAgentAcctNum)
    public void setCashSettlAgentAcctNum(String cashSettlAgentAcctNum) {
        this.cashSettlAgentAcctNum = cashSettlAgentAcctNum;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.CashSettlAgentAcctName)
    public String getCashSettlAgentAcctName() {
        return cashSettlAgentAcctName;
    }

    /**
     * Message field setter.
     * @param cashSettlAgentAcctName field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.CashSettlAgentAcctName)
    public void setCashSettlAgentAcctName(String cashSettlAgentAcctName) {
        this.cashSettlAgentAcctName = cashSettlAgentAcctName;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.CashSettlAgentContactName)
    public String getCashSettlAgentContactName() {
        return cashSettlAgentContactName;
    }

    /**
     * Message field setter.
     * @param cashSettlAgentContactName field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.CashSettlAgentContactName)
    public void setCashSettlAgentContactName(String cashSettlAgentContactName) {
        this.cashSettlAgentContactName = cashSettlAgentContactName;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.CashSettlAgentContactPhone)
    public String getCashSettlAgentContactPhone() {
        return cashSettlAgentContactPhone;
    }

    /**
     * Message field setter.
     * @param cashSettlAgentContactPhone field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.CashSettlAgentContactPhone)
    public void setCashSettlAgentContactPhone(String cashSettlAgentContactPhone) {
        this.cashSettlAgentContactPhone = cashSettlAgentContactPhone;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.PaymentMethod)
    public PaymentMethod getPaymentMethod() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param paymentMethod field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.PaymentMethod)
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.PaymentRef)
    public String getPaymentRef() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param paymentRef field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.PaymentRef)
    public void setPaymentRef(String paymentRef) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.CardHolderName)
    public String getCardHolderName() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param cardHolderName field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.CardHolderName)
    public void setCardHolderName(String cardHolderName) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.CardNumber)
    public String getCardNumber() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param cardNumber field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.CardNumber)
    public void setCardNumber(String cardNumber) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.CardStartDate)
    public Date getCardStartDate() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param cardStartDate field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.CardStartDate)
    public void setCardStartDate(Date cardStartDate) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.CardExpDate)
    public Date getCardExpDate() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param cardExpDate field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.CardExpDate)
    public void setCardExpDate(Date cardExpDate) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.CardIssNum)
    public String getCardIssNum() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param cardIssNum field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.CardIssNum)
    public void setCardIssNum(String cardIssNum) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.PaymentDate)
    public Date getPaymentDate() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param paymentDate field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.PaymentDate)
    public void setPaymentDate(Date paymentDate) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.PaymentRemitterID)
    public String getPaymentRemitterID() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field setter.
     * @param paymentRemitterID field value
     */
    @FIXVersion(introduced="4.3", retired="4.4")
    @TagNumRef(tagNum=TagNum.PaymentRemitterID)
    public void setPaymentRemitterID(String paymentRemitterID) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (settlInstMsgID == null || settlInstMsgID.trim().isEmpty()) {
            errorMsg.append(" [SettlInstMsgID]");
            hasMissingTag = true;
        }
        if (settlInstMode == null) {
            errorMsg.append(" [SettlInstMode]");
            hasMissingTag = true;
        }
        if (transactTime == null) {
            errorMsg.append(" [TransactTime]");
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
            TagEncoder.encode(bao, TagNum.SettlInstID, settlInstID);
            TagEncoder.encode(bao, TagNum.SettlInstMsgID, settlInstMsgID);
            TagEncoder.encode(bao, TagNum.SettlInstReqID, settlInstReqID);
            if (settlInstTransType != null) {
                TagEncoder.encode(bao, TagNum.SettlInstTransType, settlInstTransType.getValue());
            }
            TagEncoder.encode(bao, TagNum.SettlInstRefID, settlInstRefID);
            if (settlInstMode != null) {
                TagEncoder.encode(bao, TagNum.SettlInstMode, settlInstMode.getValue());
            }
            if (settlInstReqRejCode != null) {
                TagEncoder.encode(bao, TagNum.SettlInstReqRejCode, settlInstReqRejCode.getValue());
            }
            TagEncoder.encode(bao, TagNum.Text, text);
            if (encodedTextLen != null && encodedTextLen.intValue() > 0) {
                if (encodedText != null && encodedText.length > 0) {
                    encodedTextLen = new Integer(encodedText.length);
                    TagEncoder.encode(bao, TagNum.EncodedTextLen, encodedTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedText, encodedText);
                }
            }
            if (settlInstSource != null) {
                TagEncoder.encode(bao, TagNum.SettlInstSource, settlInstSource.getValue());
            }
            TagEncoder.encode(bao, TagNum.AllocAccount, allocAccount);
            TagEncoder.encode(bao, TagNum.IndividualAllocID, individualAllocID);
            TagEncoder.encode(bao, TagNum.ClOrdID, clOrdID);
            TagEncoder.encode(bao, TagNum.SettlLocation, settlLocation);
            TagEncoder.encodeDate(bao, TagNum.TradeDate, tradeDate);
            TagEncoder.encode(bao, TagNum.AllocID, allocID);
            TagEncoder.encode(bao, TagNum.LastMkt, lastMkt);
            TagEncoder.encode(bao, TagNum.TradingSessionID, tradingSessionID);
            TagEncoder.encode(bao, TagNum.TradingSessionSubID, tradingSessionSubID);
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.EffectiveTime, effectiveTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            if (noSettlInst != null) {
                TagEncoder.encode(bao, TagNum.NoSettlInst, noSettlInst);
                if (settlInstGroups != null && settlInstGroups.length == noSettlInst.intValue()) {
                    for (int i = 0; i < noSettlInst.intValue(); i++) {
                        if (settlInstGroups[i] != null) {
                            bao.write(settlInstGroups[i].encode(MsgSecureType.ALL_FIELDS));
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
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.ClientID, clientID);
            TagEncoder.encode(bao, TagNum.ExecBroker, execBroker);
            if (standInstDbType != null) {
                TagEncoder.encode(bao, TagNum.StandInstDbType, standInstDbType.getValue());
            }
            TagEncoder.encode(bao, TagNum.StandInstDbName, standInstDbName);
            TagEncoder.encode(bao, TagNum.StandInstDbID, standInstDbID);
            if (settlDeliveryType != null) {
                TagEncoder.encode(bao, TagNum.SettlDeliveryType, settlDeliveryType.getValue());
            }
            TagEncoder.encode(bao, TagNum.SettlDepositoryCode, settlDepositoryCode);
            TagEncoder.encode(bao, TagNum.SettlBrkrCode, settlBrkrCode);
            TagEncoder.encode(bao, TagNum.SettlInstCode, settlInstCode);
            TagEncoder.encode(bao, TagNum.SecuritySettlAgentName, securitySettlAgentName);
            TagEncoder.encode(bao, TagNum.SecuritySettlAgentCode, securitySettlAgentCode);
            TagEncoder.encode(bao, TagNum.SecuritySettlAgentAcctNum, securitySettlAgentAcctNum);
            TagEncoder.encode(bao, TagNum.SecuritySettlAgentAcctName, securitySettlAgentAcctName);
            TagEncoder.encode(bao, TagNum.SecuritySettlAgentContactName, securitySettlAgentContactName);
            TagEncoder.encode(bao, TagNum.SecuritySettlAgentContactPhone, securitySettlAgentContactPhone);
            TagEncoder.encode(bao, TagNum.CashSettlAgentName, cashSettlAgentName);
            TagEncoder.encode(bao, TagNum.CashSettlAgentCode, cashSettlAgentCode);
            TagEncoder.encode(bao, TagNum.CashSettlAgentAcctNum, cashSettlAgentAcctNum);
            TagEncoder.encode(bao, TagNum.CashSettlAgentAcctName, cashSettlAgentAcctName);
            TagEncoder.encode(bao, TagNum.CashSettlAgentContactName, cashSettlAgentContactName);
            TagEncoder.encode(bao, TagNum.CashSettlAgentContactPhone, cashSettlAgentContactPhone);
            if (paymentMethod != null) {
                TagEncoder.encode(bao, TagNum.PaymentMethod, paymentMethod.getValue());
            }
            TagEncoder.encode(bao, TagNum.PaymentRef, paymentRef);
            TagEncoder.encode(bao, TagNum.CardHolderName, cardHolderName);
            TagEncoder.encode(bao, TagNum.CardNumber, cardNumber);
            TagEncoder.encodeDate(bao, TagNum.CardStartDate, cardStartDate);
            TagEncoder.encodeDate(bao, TagNum.CardExpDate, cardExpDate);
            TagEncoder.encode(bao, TagNum.CardIssNum, cardIssNum);
            TagEncoder.encodeDate(bao, TagNum.PaymentDate, paymentDate);
            TagEncoder.encode(bao, TagNum.PaymentRemitterID, paymentRemitterID);
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
            case SettlInstID:
                settlInstID = new String(tag.value, sessionCharset);
                break;

            case SettlInstMsgID:
                settlInstMsgID = new String(tag.value, sessionCharset);
                break;

            case SettlInstReqID:
                settlInstReqID = new String(tag.value, sessionCharset);
                break;

            case SettlInstTransType:
                settlInstTransType = SettlInstTransType.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case SettlInstRefID:
                settlInstRefID = new String(tag.value, sessionCharset);
                break;

            case SettlInstMode:
                settlInstMode = SettlInstMode.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case SettlInstReqRejCode:
                settlInstReqRejCode = SettlInstReqRejCode.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case Text:
                text = new String(tag.value, sessionCharset);
                break;

            case EncodedTextLen:
                encodedTextLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case SettlInstSource:
                settlInstSource = SettlInstSource.valueFor(new String(tag.value, sessionCharset).charAt(0));
                break;

            case AllocAccount:
                allocAccount = new String(tag.value, sessionCharset);
                break;

            case IndividualAllocID:
                individualAllocID = new String(tag.value, sessionCharset);
                break;

            case ClOrdID:
                clOrdID = new String(tag.value, sessionCharset);
                break;

            case SettlLocation:
                settlLocation = new String(tag.value, sessionCharset);
                break;

            case TradeDate:
                tradeDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case AllocID:
                allocID = new String(tag.value, sessionCharset);
                break;

            case LastMkt:
                lastMkt = new String(tag.value, sessionCharset);
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

            case SecurityType:
                securityType = new String(tag.value, sessionCharset);
                break;

            case EffectiveTime:
                effectiveTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case NoSettlInst:
                noSettlInst = new Integer(new String(tag.value, sessionCharset));
                break;

            case ClientID:
                clientID = new String(tag.value, sessionCharset);
                break;

            case ExecBroker:
                execBroker = new String(tag.value, sessionCharset);
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

            case SettlDeliveryType:
                settlDeliveryType = SettlDeliveryType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case SettlDepositoryCode:
                settlDepositoryCode = new String(tag.value, sessionCharset);
                break;

            case SettlBrkrCode:
                settlBrkrCode = new String(tag.value, sessionCharset);
                break;

            case SettlInstCode:
                settlInstCode = new String(tag.value, sessionCharset);
                break;

            case SecuritySettlAgentName:
                securitySettlAgentName = new String(tag.value, sessionCharset);
                break;

            case SecuritySettlAgentCode:
                securitySettlAgentCode = new String(tag.value, sessionCharset);
                break;

            case SecuritySettlAgentAcctNum:
                securitySettlAgentAcctNum = new String(tag.value, sessionCharset);
                break;

            case SecuritySettlAgentAcctName:
                securitySettlAgentAcctName = new String(tag.value, sessionCharset);
                break;

            case SecuritySettlAgentContactName:
                securitySettlAgentContactName = new String(tag.value, sessionCharset);
                break;

            case SecuritySettlAgentContactPhone:
                securitySettlAgentContactPhone = new String(tag.value, sessionCharset);
                break;

            case CashSettlAgentName:
                cashSettlAgentName = new String(tag.value, sessionCharset);
                break;

            case CashSettlAgentCode:
                cashSettlAgentCode = new String(tag.value, sessionCharset);
                break;

            case CashSettlAgentAcctNum:
                cashSettlAgentAcctNum = new String(tag.value, sessionCharset);
                break;

            case CashSettlAgentAcctName:
                cashSettlAgentAcctName = new String(tag.value, sessionCharset);
                break;

            case CashSettlAgentContactName:
                cashSettlAgentContactName = new String(tag.value, sessionCharset);
                break;

            case CashSettlAgentContactPhone:
                cashSettlAgentContactPhone = new String(tag.value, sessionCharset);
                break;

            case PaymentMethod:
                paymentMethod = PaymentMethod.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case PaymentRef:
                paymentRef = new String(tag.value, sessionCharset);
                break;

            case CardHolderName:
                cardHolderName = new String(tag.value, sessionCharset);
                break;

            case CardNumber:
                cardNumber = new String(tag.value, sessionCharset);
                break;

            case CardStartDate:
                cardStartDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case CardExpDate:
                cardExpDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case CardIssNum:
                cardIssNum = new String(tag.value, sessionCharset);
                break;

            case PaymentDate:
                paymentDate = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case PaymentRemitterID:
                paymentRemitterID = new String(tag.value, sessionCharset);
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [SettlementInstructionsMsg] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedTextLen.getValue()) {
            try {
                encodedTextLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedTextLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(), TagNum.EncodedTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedTextLen.intValue());
            encodedText = dataTag.value;
        }

        return result;
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
        StringBuilder b = new StringBuilder("{SettlementInstructionsMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.SettlInstID, settlInstID);
        printTagValue(b, TagNum.SettlInstMsgID, settlInstMsgID);
        printTagValue(b, TagNum.SettlInstReqID, settlInstReqID);
        printTagValue(b, TagNum.SettlInstTransType, settlInstTransType);
        printTagValue(b, TagNum.SettlInstRefID, settlInstRefID);
        printTagValue(b, TagNum.SettlInstMode, settlInstMode);
        printTagValue(b, TagNum.SettlInstReqRejCode, settlInstReqRejCode);
        printTagValue(b, TagNum.Text, text);
        printTagValue(b, TagNum.EncodedTextLen, encodedTextLen);
        printTagValue(b, TagNum.EncodedText, encodedText);
        printTagValue(b, TagNum.SettlInstSource, settlInstSource);
        printTagValue(b, TagNum.AllocAccount, allocAccount);
        printTagValue(b, TagNum.IndividualAllocID, individualAllocID);
        printTagValue(b, TagNum.ClOrdID, clOrdID);
        printTagValue(b, TagNum.SettlLocation, settlLocation);
        printDateTagValue(b, TagNum.TradeDate, tradeDate);
        printTagValue(b, TagNum.AllocID, allocID);
        printTagValue(b, TagNum.LastMkt, lastMkt);
        printTagValue(b, TagNum.TradingSessionID, tradingSessionID);
        printTagValue(b, TagNum.TradingSessionSubID, tradingSessionSubID);
        printTagValue(b, TagNum.Side, side);
        printTagValue(b, TagNum.SecurityType, securityType);
        printUTCDateTimeTagValue(b, TagNum.EffectiveTime, effectiveTime);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.NoSettlInst, noSettlInst);
        printTagValue(b, settlInstGroups);
        printTagValue(b, parties);
        printTagValue(b, TagNum.ClientID, clientID);
        printTagValue(b, TagNum.ExecBroker, execBroker);
        printTagValue(b, TagNum.StandInstDbType, standInstDbType);
        printTagValue(b, TagNum.StandInstDbName, standInstDbName);
        printTagValue(b, TagNum.StandInstDbID, standInstDbID);
        printTagValue(b, TagNum.SettlDeliveryType, settlDeliveryType);
        printTagValue(b, TagNum.SettlDepositoryCode, settlDepositoryCode);
        printTagValue(b, TagNum.SettlBrkrCode, settlBrkrCode);
        printTagValue(b, TagNum.SettlInstCode, settlInstCode);
        printTagValue(b, TagNum.SecuritySettlAgentName, securitySettlAgentName);
        printTagValue(b, TagNum.SecuritySettlAgentCode, securitySettlAgentCode);
        printTagValue(b, TagNum.SecuritySettlAgentAcctNum, securitySettlAgentAcctNum);
        printTagValue(b, TagNum.SecuritySettlAgentAcctName, securitySettlAgentAcctName);
        printTagValue(b, TagNum.SecuritySettlAgentContactName, securitySettlAgentContactName);
        printTagValue(b, TagNum.SecuritySettlAgentContactPhone, securitySettlAgentContactPhone);
        printTagValue(b, TagNum.CashSettlAgentName, cashSettlAgentName);
        printTagValue(b, TagNum.CashSettlAgentCode, cashSettlAgentCode);
        printTagValue(b, TagNum.CashSettlAgentAcctNum, cashSettlAgentAcctNum);
        printTagValue(b, TagNum.CashSettlAgentAcctName, cashSettlAgentAcctName);
        printTagValue(b, TagNum.CashSettlAgentContactName, cashSettlAgentContactName);
        printTagValue(b, TagNum.CashSettlAgentContactPhone, cashSettlAgentContactPhone);
        printTagValue(b, TagNum.PaymentMethod, paymentMethod);
        printTagValue(b, TagNum.PaymentRef, paymentRef);
        printTagValue(b, TagNum.CardHolderName, cardHolderName);
        printTagValue(b, TagNum.CardNumber, cardNumber);
        printDateTagValue(b, TagNum.CardStartDate, cardStartDate);
        printDateTagValue(b, TagNum.CardExpDate, cardExpDate);
        printTagValue(b, TagNum.CardIssNum, cardIssNum);
        printDateTagValue(b, TagNum.PaymentDate, paymentDate);
        printTagValue(b, TagNum.PaymentRemitterID, paymentRemitterID);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
