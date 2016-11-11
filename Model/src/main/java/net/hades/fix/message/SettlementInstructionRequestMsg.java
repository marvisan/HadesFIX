/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlementInstructionRequestMsg.java
 *
 * $Id: SettlementInstructionsMsg.java,v 1.3 2011-04-28 10:07:43 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.MsgType;

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
import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.AcctIDSource;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.Product;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.Side;
import net.hades.fix.message.type.StandInstDbType;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The Settlement Instruction Request message is used to request standing settlement instructions from another
 * party. This could be:
 * <ul>
 *      <li>A buyside firm requesting standing instructions from a sellside firm.</li>
 *      <li>A sellside firm requesting standing instructions from a buyside firm.</li>
 *      <li>A sellside or buyside firm requesting standing instructions from a third party central static data database.</li>
 *      <li>A third party central static data database requesting standing instructions from a sellside or buyside firm.</li>
 * </ul>
 * Settlement instructions can be requested for any combination of the following parameters (in addition to the
 * party whose instructions are being requested):
 * <ul>
 *      <li>AllocAccount</li>
 *      <li>Country (of settlement)</li>
 *      <li>Side</li>
 *      <li>SecurityType (and/or CFI code)</li>
 *      <li>SettlDeliveryType (i.e. DVP vs. FOP)</li>
 *      <li>EffectiveTime (i.e. all instructions valid at any time from this date/time)</li>
 *      <li>Expiry Time (i.e. all instructions valid until this date/time)</li>
 *      <li>Last update time (i.e. all instructions created or updated since this date/time).</li>
 * </ul>
 * Alternatively, settlement instructions can be queried by reference to a database of standing instructions using 
 * the identifiers of that database as follows:
 * <ul>
 *      <li>Database id</li>
 *      <li>Database name</li>
 *      <li>Id of the settlement instructions on this database</li>
 * </ul>
 * The response to such a request should be a Settlement Instruction message with SettlInstTransType "New"
 * containing all SSIs meeting the criteria specified in the Settlement Instruction request. If the request cannot be
 * processed, the request should be rejected with a Settlement Instruction message with SettlInstTransType
 * "Request rejected". Similarly, if the request returns no data, the request should be rejected with a Settlement
 * Instruction message with SettlInstTransType "No matching data found".
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 29/01/2011, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class SettlementInstructionRequestMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SettlInstReqID.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.AllocAccount.getValue(),
        TagNum.AllocAcctIDSource.getValue(),
        TagNum.Side.getValue(),
        TagNum.Product.getValue(),
        TagNum.SecurityType.getValue(),
        TagNum.CFICode.getValue(),
        TagNum.SettlCurrency.getValue(),
        TagNum.EffectiveTime.getValue(),
        TagNum.ExpireTime.getValue(),
        TagNum.LastUpdateTime.getValue(),
        TagNum.StandInstDbType.getValue(),
        TagNum.StandInstDbName.getValue(),
        TagNum.StandInstDbID.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.SettlInstReqID.getValue(),
        TagNum.TransactTime.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    /**
     * TagNum = 791 REQUIRED. Starting with 4.4 version.
     */
    protected String settlInstReqID;

    /**
     * TagNum = 60 REQUIRED REQUIRED. Starting with 4.4 version.
     */
    protected Date transactTime;

    /**
     * Starting with 4.4 version.
     */
    protected Parties parties;

    /**
     * TagNum = 79. Starting with 4.4 version.
     */
    protected String allocAccount;

    /**
     * TagNum = 661. Starting with 4.4 version.
     */
    protected AcctIDSource allocAcctIDSource;

    /**
     * TagNum = 54. Starting with 4.4 version.
     */
    protected Side side;

    /**
     * TagNum = 460. Starting with 4.4 version.
     */
    protected Product product;

    /**
     * TagNum = 167. Starting with 4.4 version.
     */
    protected String securityType;

    /** 
     * TagNum = 461. Starting with 4.4 version.
     */
    protected String cfiCode;

    /**
     * TagNum = 120. Starting with 5.0 version.
     */
    protected Currency settlCurrency;

    /**
     * TagNum = 168. Starting with 4.4 version.
     */
    protected Date effectiveTime;

    /**
     * TagNum = 126. Starting with 4.4 version.
     */
    protected Date expireTime;

    /**
     * TagNum = 779. Starting with 4.4 version.
     */
    protected Date lastUpdateTime;

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

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SettlementInstructionRequestMsg() {
        super();
    }

    public SettlementInstructionRequestMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public SettlementInstructionRequestMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.SettlementInstructionRequest.getValue(), beginString);
    }

    public SettlementInstructionRequestMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.SettlementInstructionRequest.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstReqID, required=true)
    public String getSettlInstReqID() {
        return settlInstReqID;
    }

    /**
     * Message field setter.
     * @param settlInstReqID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.SettlInstReqID, required=true)
    public void setSettlInstReqID(String settlInstReqID) {
        this.settlInstReqID = settlInstReqID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TransactTime, required=true)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.TransactTime, required=true)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    public Parties getParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component if used in this message to the proper implementation
     * class.
     */
    @FIXVersion(introduced="4.4")
    public void setParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Sets the Parties component to null.
     */
    @FIXVersion(introduced="4.4")
    public void clearParties() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.AllocAccount)
    public String getAllocAccount() {
        return allocAccount;
    }

    /**
     * Message field setter.
     * @param allocAccount field value
     */
    @FIXVersion(introduced="4.1", retired="4.4")
    @TagNumRef(tagNum=TagNum.AllocAccount)
    public void setAllocAccount(String allocAccount) {
        this.allocAccount = allocAccount;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocAcctIDSource)
    public AcctIDSource getAllocAcctIDSource() {
        return allocAcctIDSource;
    }

    /**
     * Message field setter.
     * @param allocAcctIDSource field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.AllocAcctIDSource)
    public void setAllocAcctIDSource(AcctIDSource allocAcctIDSource) {
        this.allocAcctIDSource = allocAcctIDSource;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Side)
    public Side getSide() {
        return side;
    }

    /**
     * Message field setter.
     * @param side field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Side)
    public void setSide(Side side) {
        this.side = side;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Product)
    public Product getProduct() {
        return product;
    }

    /**
     * Message field setter.
     * @param product field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.Product)
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public String getSecurityType() {
        return securityType;
    }

    /**
     * Message field setter.
     * @param securityType field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.SecurityType)
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }
  
    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.CFICode)
    public String getCfiCode() {
        return cfiCode;
    }

    /**
     * Message field setter.
     * @param cfiCode field value
     */
    @FIXVersion(introduced = "4.4")
    @TagNumRef(tagNum = TagNum.CFICode)
    public void setCfiCode(String cfiCode) {
        this.cfiCode = cfiCode;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public Currency getSettlCurrency() {
        return settlCurrency;
    }

    /**
     * Message field setter.
     * @param settlCurrency field value
     */
    @FIXVersion(introduced="5.0")
    @TagNumRef(tagNum=TagNum.SettlCurrency)
    public void setSettlCurrency(Currency settlCurrency) {
        this.settlCurrency = settlCurrency;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EffectiveTime)
    public Date getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * Message field setter.
     * @param effectiveTime field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.EffectiveTime)
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * Message field setter.
     * @param expireTime field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.ExpireTime)
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastUpdateTime)
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * Message field setter.
     * @param lastUpdateTime field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastUpdateTime)
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.StandInstDbType)
    public StandInstDbType getStandInstDbType() {
        return standInstDbType;
    }

    /**
     * Message field setter.
     * @param standInstDbType field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.StandInstDbType)
    public void setStandInstDbType(StandInstDbType standInstDbType) {
        this.standInstDbType = standInstDbType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.StandInstDbName)
    public String getStandInstDbName() {
        return standInstDbName;
    }

    /**
     * Message field setter.
     * @param standInstDbName field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.StandInstDbName)
    public void setStandInstDbName(String standInstDbName) {
        this.standInstDbName = standInstDbName;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.StandInstDbID)
    public String getStandInstDbID() {
        return standInstDbID;
    }

    /**
     * Message field setter.
     * @param standInstDbID field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.StandInstDbID)
    public void setStandInstDbID(String standInstDbID) {
        this.standInstDbID = standInstDbID;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (settlInstReqID == null || settlInstReqID.trim().isEmpty()) {
            errorMsg.append(" [SettlInstReqID]");
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
            TagEncoder.encode(bao, TagNum.SettlInstReqID, settlInstReqID);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            if (parties != null) {
                bao.write(parties.encode(MsgSecureType.ALL_FIELDS));
            }
            TagEncoder.encode(bao, TagNum.AllocAccount, allocAccount);
            if (allocAcctIDSource != null) {
                TagEncoder.encode(bao, TagNum.AllocAcctIDSource, allocAcctIDSource.getValue());
            }
            if (side != null) {
                TagEncoder.encode(bao, TagNum.Side, side.getValue());
            }
            if (product != null) {
                TagEncoder.encode(bao, TagNum.Product, product.getValue());
            }
            TagEncoder.encode(bao, TagNum.SecurityType, securityType);
            TagEncoder.encode(bao, TagNum.CFICode, cfiCode, sessionCharset);
            if (settlCurrency != null) {
                TagEncoder.encode(bao, TagNum.SettlCurrency, settlCurrency.getValue());
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.EffectiveTime, effectiveTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.ExpireTime, expireTime);
            TagEncoder.encodeUtcTimestamp(bao, TagNum.LastUpdateTime, lastUpdateTime);
            if (standInstDbType != null) {
                TagEncoder.encode(bao, TagNum.StandInstDbType, standInstDbType.getValue());
            }
            TagEncoder.encode(bao, TagNum.StandInstDbName, standInstDbName);
            TagEncoder.encode(bao, TagNum.StandInstDbID, standInstDbID);

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
            case SettlInstReqID:
                settlInstReqID = new String(tag.value, sessionCharset);
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case AllocAccount:
                allocAccount = new String(tag.value, sessionCharset);
                break;

            case AllocAcctIDSource:
                allocAcctIDSource = AcctIDSource.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case Side:
                side = Side.valueFor((new String(tag.value, sessionCharset).charAt(0)));
                break;

            case Product:
                product = Product.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case SecurityType:
                securityType = new String(tag.value, sessionCharset);
                break;

            case CFICode:
                cfiCode = new String(tag.value, sessionCharset);
                break;

            case SettlCurrency:
                settlCurrency = Currency.valueFor(new String(tag.value, sessionCharset));
                break;

            case EffectiveTime:
                effectiveTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case ExpireTime:
                expireTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case LastUpdateTime:
                lastUpdateTime = DateConverter.parseString(new String(tag.value, sessionCharset));
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

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [SettlementInstructionRequestMsg] fields.";
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
        StringBuilder b = new StringBuilder("{SettlementInstructionRequestMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.SettlInstReqID, settlInstReqID);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, parties);
        printTagValue(b, TagNum.AllocAccount, allocAccount);
        printTagValue(b, TagNum.AllocAcctIDSource, allocAcctIDSource);
        printTagValue(b, TagNum.Side, side);
        printTagValue(b, TagNum.Product, product);
        printTagValue(b, TagNum.SecurityType, securityType);
        printTagValue(b, TagNum.CFICode, cfiCode);
        printTagValue(b, TagNum.SettlCurrency, settlCurrency);
        printUTCDateTimeTagValue(b, TagNum.EffectiveTime, effectiveTime);
        printUTCDateTimeTagValue(b, TagNum.ExpireTime, expireTime);
        printUTCDateTimeTagValue(b, TagNum.LastUpdateTime, lastUpdateTime);
        printTagValue(b, TagNum.StandInstDbType, standInstDbType);
        printTagValue(b, TagNum.StandInstDbName, standInstDbName);
        printTagValue(b, TagNum.StandInstDbID, standInstDbID);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
