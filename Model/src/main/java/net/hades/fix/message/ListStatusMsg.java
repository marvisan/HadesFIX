/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderSingleMsg.java
 *
 * $Id: ListStatusMsg.java,v 1.3 2011-04-28 10:07:42 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.anno.FIXVersion;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.util.BooleanConverter;
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
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.OrderStatusGroup;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.ContingencyType;
import net.hades.fix.message.type.ListOrderStatus;
import net.hades.fix.message.type.ListRejectReason;
import net.hades.fix.message.type.ListStatusType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.TagEncoder;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * The list status message is issued as the response to a List Status Request message sent in an unsolicited fashion
 * by the sell-side. It indicates the current state of the orders within the list as they exist at the broker's site. This
 * message may also be used to respond to the List Cancel Request.<br/>
 * Orders within the list are statused at the summary level. Individual executions are not reported, rather, the
 * current state of the order is reported.
 * The message contains repeating fields for each. The relative position of the repeating fields is important in this
 * message, i.e. each instance of ClOrdID, CumQty, LeavesQty, CxlQty and AvgPx must be in the order shown
 * below.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/04/2009, 9:25:04 AM
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ListStatusMsg extends FIXMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ListID.getValue(),
        TagNum.ListStatusType.getValue(),
        TagNum.WaveNo.getValue(),
        TagNum.NoRpts.getValue(),
        TagNum.ListOrderStatus.getValue(),
        TagNum.ContingencyType.getValue(),
        TagNum.ListRejectReason.getValue(),
        TagNum.RptSeq.getValue(),
        TagNum.ListStatusText.getValue(),
        TagNum.TransactTime.getValue(),
        TagNum.TotNoOrders.getValue(),
        TagNum.LastFragment.getValue(),
        TagNum.NoOrders.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.EncodedListStatusTextLen.getValue()
    }));

    protected static final Set<Integer> REQUIRED_TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.ListID.getValue(),
        TagNum.NoRpts.getValue(),
        TagNum.RptSeq.getValue()
    }));

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    /**
     * TagNum = 66 REQUIRED. Starting with 4.0 version.
     */
    protected String listID;

    /**
     * TagNum = 429. Starting with 4.2 version.
     */
    protected ListStatusType listStatusType;

    /**
     * TagNum = 105. Starting with 4.0 version.
     */
    protected String waveNo;

    /**
     * TagNum = 82 REQUIRED. Starting with 4.0 version.
     */
    protected Integer noRpts;

    /**
     * TagNum = 431. Starting with 4.2 version.
     */
    protected ListOrderStatus listOrderStatus;

    /**
     * TagNum = 1385. Starting with 5.0SP1 version.
     */
    protected ContingencyType contingencyType;

    /**
     * TagNum = 1386. Starting with 5.0SP1 version.
     */
    protected ListRejectReason listRejectReason;

    /**
     * TagNum = 83 REQUIRED. Starting with 4.0 version.
     */
    protected Integer rptSeq;

    /**
     * TagNum = 444. Starting with 4.2 version.
     */
    protected String listStatusText;

    /**
     * TagNum = 445. Starting with 4.2 version.
     */
    protected Integer encodedListStatusTextLen;

    /**
     * TagNum = 446. Starting with 4.2 version.
     */
    protected byte[] encodedListStatusText;

    /**
     * TagNum = 60. Starting with 4.2 version.
     */
    protected Date transactTime;

    /**
     * TagNum = 68 REQUIRED. Starting with 4.2 version.
     */
    protected Integer totNoOrders;

    /**
     * TagNum = 893. Starting with 4.4 version.
     */
    protected Boolean lastFragment;

    /**
     * TagNum = 68 REQUIRED. Starting with 4.0 version.
     */
    protected Integer noOrders;

    /**
     * Starting with 4.0 version.
     */
    protected OrderStatusGroup[] orderStatusGroups;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ListStatusMsg() {
        super();
    }

    public ListStatusMsg(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public ListStatusMsg(BeginString beginString) throws InvalidMsgException {
        super(MsgType.ListStatus.getValue(), beginString);
    }

    public ListStatusMsg(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(MsgType.ListStatus.getValue(), beginString, applVerID);
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
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ListID, required = true)
    public String getListID() {
        return listID;
    }

    /**
     * Message field setter.
     * @param listID field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.ListID, required = true)
    public void setListID(String listID) {
        this.listID = listID;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ListStatusType)
    public ListStatusType getListStatusType() {
        return listStatusType;
    }

    /**
     * Message field setter.
     * @param listStatusType field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ListStatusType)
    public void setListStatusType(ListStatusType listStatusType) {
        this.listStatusType = listStatusType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.WaveNo)
    public String getWaveNo() {
        return waveNo;
    }

    /**
     * Message field setter.
     * @param waveNo field value
     */
    @FIXVersion(introduced = "4.0", retired = "4.2")
    @TagNumRef(tagNum = TagNum.WaveNo)
    public void setWaveNo(String waveNo) {
        this.waveNo = waveNo;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.NoRpts, required = true)
    public Integer getNoRpts() {
        return noRpts;
    }

    /**
     * Message field setter.
     * @param noRpts field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.NoRpts, required = true)
    public void setNoRpts(Integer noRpts) {
        this.noRpts = noRpts;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ListOrderStatus, required = true)
    public ListOrderStatus getListOrderStatus() {
        return listOrderStatus;
    }

    /**
     * Message field setter.
     * @param listOrderStatus field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ListOrderStatus, required = true)
    public void setListOrderStatus(ListOrderStatus listOrderStatus) {
        this.listOrderStatus = listOrderStatus;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.ContingencyType)
    public ContingencyType getContingencyType() {
        return contingencyType;
    }

    /**
     * Message field setter.
     * @param contingencyType field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.ContingencyType)
    public void setContingencyType(ContingencyType contingencyType) {
        this.contingencyType = contingencyType;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.ListRejectReason)
    public ListRejectReason getListRejectReason() {
        return listRejectReason;
    }

    /**
     * Message field setter.
     * @param listRejectReason field value
     */
    @FIXVersion(introduced="5.0SP1")
    @TagNumRef(tagNum=TagNum.ListRejectReason)
    public void setListRejectReason(ListRejectReason listRejectReason) {
        this.listRejectReason = listRejectReason;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.RptSeq, required = true)
    public Integer getRptSeq() {
        return rptSeq;
    }

    /**
     * Message field setter.
     * @param rptSeq field value
     */
    @FIXVersion(introduced="4.0")
    @TagNumRef(tagNum=TagNum.RptSeq, required = true)
    public void setRptSeq(Integer rptSeq) {
        this.rptSeq = rptSeq;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ListStatusText)
    public String getListStatusText() {
        return listStatusText;
    }

    /**
     * Message field setter.
     * @param listStatusText field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.ListStatusText)
    public void setListStatusText(String listStatusText) {
        this.listStatusText = listStatusText;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedListStatusTextLen)
    public Integer getEncodedListStatusTextLen() {
        return encodedListStatusTextLen;
    }

    /**
     * Message field setter.
     * @param encodedListStatusTextLen field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedListStatusTextLen)
    public void setEncodedListStatusTextLen(Integer encodedListStatusTextLen) {
        this.encodedListStatusTextLen = encodedListStatusTextLen;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedListStatusText)
    public byte[] getEncodedListStatusText() {
        return encodedListStatusText;
    }

    /**
     * Message field setter.
     * @param encodedListStatusText field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.EncodedListStatusText)
    public void setEncodedListStatusText(byte[] encodedListStatusText) {
        this.encodedListStatusText = encodedListStatusText;
        if (encodedListStatusTextLen == null) {
            encodedListStatusTextLen = new Integer(encodedListStatusText.length);
        }
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public Date getTransactTime() {
        return transactTime;
    }

    /**
     * Message field setter.
     * @param transactTime field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TransactTime)
    public void setTransactTime(Date transactTime) {
        this.transactTime = transactTime;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TotNoOrders, required = true)
    public Integer getTotNoOrders() {
        return totNoOrders;
    }

    /**
     * Message field setter.
     * @param totNoOrders field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.TotNoOrders, required = true)
    public void setTotNoOrders(Integer totNoOrders) {
        this.totNoOrders = totNoOrders;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastFragment)
    public Boolean getLastFragment() {
        return lastFragment;
    }

    /**
     * Message field setter.
     * @param lastFragment field value
     */
    @FIXVersion(introduced="4.4")
    @TagNumRef(tagNum=TagNum.LastFragment)
    public void setLastFragment(Boolean lastFragment) {
        this.lastFragment = lastFragment;
    }

    /**
     * Message field getter.
     * @return field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoOrders)
    public Integer getNoOrders() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method sets the number of {@link OrderStatusGroup} groups. It will also create an array
     * of {@link OrderStatusGroup} objects and set the <code>orderStatusGroups</code> field with this array.
     * The created objects inside the array need to be populated with data for encoding.<br/>
     * If there where already objects in <code>orderStatusGroups</code> array they will be discarded.<br/>
     * @param noOrders field value
     */
    @FIXVersion(introduced="4.2")
    @TagNumRef(tagNum=TagNum.NoOrders)
    public void setNoOrders(Integer noOrders) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Message field getter for {@link OrderStatusGroup} array of groups.
     * @return field array value
     */
    @FIXVersion(introduced="4.2")
    public OrderStatusGroup[] getOrderStatusGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method adds a {@link OrderStatusGroup} object to the existing array of <code>orderStatusGroups</code>
     * and expands the static array with 1 place.<br/>
     * This method will also update <code>noOrders</code> field to the proper value.<br/>
     * Note: If the <code>setNoOrders</code> method has been called there will already be a number of objects in the
     * <code>OrderStatusGroups</code> array created.<br/>
     * @return newly created block and added to the array group object
     */
    @FIXVersion(introduced="4.2")
    public OrderStatusGroup addOrderStatusGroup() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * This method deletes a {@link OrderStatusGroup} object from the existing array of <code>orderStatusGroups</code>
     * and shrink the static array with 1 place.<br/>
     * If the array does not have the index position then a null object will be returned.)<br/>
     * This method will also update <code>noOrders</code> field to the proper value.<br/>
     * @param index position in array to be deleted starting at 0
     * @return deleted block object
     */
    @FIXVersion(introduced="4.2")
    public OrderStatusGroup deleteOrderStatusGroup(int index) {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    /**
     * Deletes all the {@link OrderStatusGroup} objects from the <code>orderStatusGroups</code> array
     * (sets the array to 0 length)<br/>
     * This method will also update <code>noOrders</code> field and set it to null.<br/>
     * @return number of elements in array cleared
     */
    @FIXVersion(introduced="4.2")
    public int clearOrderStatusGroups() {
        throw new UnsupportedOperationException(getUnsupportedTagMessage());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected void validateRequiredTags() throws TagNotPresentException {
        StringBuilder errorMsg = new StringBuilder("Tag value(s) for");
        boolean hasMissingTag = false;
        if (listID == null || listID.trim().isEmpty()) {
            errorMsg.append(" [ListID]");
            hasMissingTag = true;
        }
        if (listStatusType == null) {
            errorMsg.append(" [ListStatusType]");
            hasMissingTag = true;
        }
        if (noRpts == null) {
            errorMsg.append(" [NoRpts]");
            hasMissingTag = true;
        }
        if (listOrderStatus == null) {
            errorMsg.append(" [ListOrderStatus]");
            hasMissingTag = true;
        }
        if (rptSeq == null) {
            errorMsg.append(" [RptSeq]");
            hasMissingTag = true;
        }
        if (totNoOrders == null) {
            errorMsg.append(" [TotNoOrders]");
            hasMissingTag = true;
        }
        if (noOrders == null) {
            errorMsg.append(" [NoOrders]");
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
            TagEncoder.encode(bao, TagNum.ListID, listID);
            if (listStatusType != null) {
                TagEncoder.encode(bao, TagNum.ListStatusType, listStatusType.getValue());
            }
            TagEncoder.encode(bao, TagNum.WaveNo, waveNo);
            TagEncoder.encode(bao, TagNum.NoRpts, noRpts);
            if (listOrderStatus != null) {
                TagEncoder.encode(bao, TagNum.ListOrderStatus, listOrderStatus.getValue());
            }
            if (contingencyType != null) {
                TagEncoder.encode(bao, TagNum.ContingencyType, contingencyType.getValue());
            }
            if (listRejectReason != null) {
                TagEncoder.encode(bao, TagNum.ListRejectReason, listRejectReason.getValue());
            }
            TagEncoder.encode(bao, TagNum.RptSeq, rptSeq);
            TagEncoder.encode(bao, TagNum.ListStatusText, listStatusText);
            if (encodedListStatusTextLen != null && encodedListStatusTextLen.intValue() > 0) {
                if (encodedListStatusText != null && encodedListStatusText.length > 0) {
                    encodedListStatusTextLen = new Integer(encodedListStatusText.length);
                    TagEncoder.encode(bao, TagNum.EncodedListStatusTextLen, encodedListStatusTextLen);
                    TagEncoder.encode(bao, TagNum.EncodedListStatusText, encodedListStatusText);
                }
            }
            TagEncoder.encodeUtcTimestamp(bao, TagNum.TransactTime, transactTime);
            TagEncoder.encode(bao, TagNum.TotNoOrders, totNoOrders);
            TagEncoder.encode(bao, TagNum.LastFragment, lastFragment);
            if (noOrders != null) {
                TagEncoder.encode(bao, TagNum.NoOrders, noOrders);
                if (orderStatusGroups != null && orderStatusGroups.length == noOrders.intValue()) {
                    for (int i = 0; i < noOrders.intValue(); i++) {
                        if (orderStatusGroups[i] != null) {
                            bao.write(orderStatusGroups[i].encode(MsgSecureType.ALL_FIELDS));
                        }
                    }
                } else {
                    String error = "OrderStatusGroups field has been set but there is no data or the number of groups does not match.";
                    LOGGER.severe(error);
                    throw new BadFormatMsgException(SessionRejectReason.IncorrectCountForGroups, getHeader().getMsgType(),
                            TagNum.NoOrders.getValue(), error);
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
            case ListID:
                listID = new String(tag.value, sessionCharset);
                break;

            case ListStatusType:
                listStatusType = ListStatusType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case WaveNo:
                waveNo = new String(tag.value, sessionCharset);
                break;

            case NoRpts:
                noRpts = new Integer(new String(tag.value, sessionCharset));
                break;

            case ListOrderStatus:
                listOrderStatus = ListOrderStatus.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ContingencyType:
                contingencyType = ContingencyType.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case ListRejectReason:
                listRejectReason = ListRejectReason.valueFor(Integer.valueOf(new String(tag.value, sessionCharset)));
                break;

            case RptSeq:
                rptSeq = new Integer(new String(tag.value, sessionCharset));
                break;

            case ListStatusText:
                listStatusText = new String(tag.value, sessionCharset);
                break;

            case EncodedListStatusTextLen:
                encodedListStatusTextLen = new Integer(new String(tag.value, sessionCharset));
                break;

            case TransactTime:
                transactTime = DateConverter.parseString(new String(tag.value, sessionCharset));
                break;

            case TotNoOrders:
                totNoOrders = new Integer(new String(tag.value, sessionCharset));
                break;

            case LastFragment:
                lastFragment = BooleanConverter.parse(new String(tag.value, sessionCharset));
                break;

            case NoOrders:
                noOrders = new Integer(new String(tag.value, sessionCharset));
                break;

            default:
                String error = "Tag value [" + tag.tagNum + "] not present in [ListStatusMsg] fields.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(SessionRejectReason.InvalidTagNumber, getHeader().getMsgType(),
                        tag.tagNum, error);
        }
    }

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        ByteBuffer result = message;
        if (tag.tagNum == TagNum.EncodedListStatusTextLen.getValue()) {
            try {
                encodedListStatusTextLen = new Integer(new String(tag.value, getSessionCharset()));
            } catch (NumberFormatException ex) {
                String error = "Tag [EncodedListStatusTextLen] requires an integer value. The value set was [" + tag.value + "].";
                LOGGER.log(Level.SEVERE, "{0} Error was: {1}", new Object[] { error, ex.toString() });
                throw new BadFormatMsgException(SessionRejectReason.IncorrectDataFormat, getHeader().getMsgType(), TagNum.EncodedListStatusTextLen.getValue(), error);
            }
            Tag  dataTag = MsgUtil.getNextTag(message, encodedListStatusTextLen.intValue());
            encodedListStatusText = dataTag.value;
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
        StringBuilder b = new StringBuilder("{ListStatusMsg=");
        b.append(header != null ? header.toString() : "");
        b.append(System.getProperty("line.separator")).append("{Body=");
        printTagValue(b, TagNum.ListID, listID);
        printTagValue(b, TagNum.ListStatusType, listStatusType);
        printTagValue(b, TagNum.WaveNo, waveNo);
        printTagValue(b, TagNum.NoRpts, noRpts);
        printTagValue(b, TagNum.ListOrderStatus, listOrderStatus);
        printTagValue(b, TagNum.ContingencyType, contingencyType);
        printTagValue(b, TagNum.ListRejectReason, listRejectReason);
        printTagValue(b, TagNum.RptSeq, rptSeq);
        printTagValue(b, TagNum.ListStatusText, listStatusText);
        printTagValue(b, TagNum.EncodedListStatusTextLen, encodedListStatusTextLen);
        printTagValue(b, TagNum.EncodedListStatusText, encodedListStatusText);
        printUTCDateTimeTagValue(b, TagNum.TransactTime, transactTime);
        printTagValue(b, TagNum.TotNoOrders, totNoOrders);
        printTagValue(b, TagNum.LastFragment, lastFragment);
        printTagValue(b, TagNum.NoOrders, noOrders);
        printTagValue(b, orderStatusGroups);
        b.append("}");
        b.append(trailer != null ? trailer.toString() : "").append("}");

        return b.toString();
    }

    // </editor-fold>
}
