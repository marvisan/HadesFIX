/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXMsgBuilder.java
 *
 * $Id: FIXMsgBuilder.java,v 1.73 2011-10-29 02:16:41 vrotaru Exp $
 */
package net.hades.fix.message.builder;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.message.config.SessionContext;
import net.hades.fix.message.config.SessionContextKey;
import net.hades.fix.message.config.ThreadData;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v40.Header40;
import net.hades.fix.message.impl.v41.Header41;
import net.hades.fix.message.impl.v42.Header42;
import net.hades.fix.message.impl.v43.Header43;
import net.hades.fix.message.impl.v44.Header44;
import net.hades.fix.message.impl.v50.HeaderT11_50;
import net.hades.fix.message.impl.v50sp1.HeaderT11_50SP1;
import net.hades.fix.message.impl.v50sp2.HeaderT11_50SP2;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;
import net.hades.fix.message.type.SessionRejectReason;
import net.hades.fix.message.type.TagNum;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.util.MsgUtil;

/**
 * Factory class for FIX messages.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.73 $
 * @created 8/07/2008, 20:33:29
 */
public class FIXMsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    /**
     * The name of the property file containing the fix message type / builder implementation
     * class pair values. The file entries would look like:<p/>
     * AU=com.mycompany.AUMessageBuilder
     */
    public static final String CUSTOM_MSG_BUILDER_FILE_NAME = "HadesMsgBuilderClasses.properties";

    /**
     * Colon separated list of packages containing the <quote>HadesMsgBuilderClasses.properties</quote>
     * files. The load order of the message builders listed in this files is not guaranteed.
     */
    public static final String CUSTOM_MSG_BUILDER_FILE_LOCATIONS = "hades.model.build.file.location";
    
    private static final Logger LOGGER = Logger.getLogger(FIXMsgBuilder.class.getName());
    
    private static final FIXMsgBuilder INSTANCE;

    private static final String[][] MSG_BUILDER_SPEC_CLASSES = new String[][] {
        {MsgType.Advertisement.getValue(), "net.hades.fix.message.builder.impl.AdvertismentMsgBuilder"},
        {MsgType.AllocationAck.getValue(), "net.hades.fix.message.builder.impl.AllocationInstructionAckMsgBuilder"},
        {MsgType.Allocation.getValue(), "net.hades.fix.message.builder.impl.AllocationInstructionMsgBuilder"},
        {MsgType.AllocationReport.getValue(), "net.hades.fix.message.builder.impl.AllocationReportMsgBuilder"},
        {MsgType.AllocationReportAck.getValue(), "net.hades.fix.message.builder.impl.AllocationReportAckMsgBuilder"},
        {MsgType.AssignmentReport.getValue(), "net.hades.fix.message.builder.impl.AssignmentReportMsgBuilder"},
        {MsgType.BatchSet.getValue(), "net.hades.fix.message.builder.impl.BatchSetMsgBuilder"},
        {MsgType.BidRequest.getValue(), "net.hades.fix.message.builder.impl.BidRequestMsgBuilder"},
        {MsgType.BidResponse.getValue(), "net.hades.fix.message.builder.impl.BidResponseMsgBuilder"},
        {MsgType.CollateralAssignment.getValue(), "net.hades.fix.message.builder.impl.CollateralAssignmentMsgBuilder"},
        {MsgType.CollateralInquiryAck.getValue(), "net.hades.fix.message.builder.impl.CollateralInquiryAckMsgBuilder"},
        {MsgType.CollateralInquiry.getValue(), "net.hades.fix.message.builder.impl.CollateralInquiryMsgBuilder"},
        {MsgType.CollateralReport.getValue(), "net.hades.fix.message.builder.impl.CollateralReportMsgBuilder"},
        {MsgType.CollateralRequest.getValue(), "net.hades.fix.message.builder.impl.CollateralRequestMsgBuilder"},
        {MsgType.CollateralResponse.getValue(), "net.hades.fix.message.builder.impl.CollateralResponseMsgBuilder"},
        {MsgType.ConfirmationAck.getValue(), "net.hades.fix.message.builder.impl.ConfirmationAckMsgBuilder"},
        {MsgType.Confirmation.getValue(), "net.hades.fix.message.builder.impl.ConfirmationMsgBuilder"},
        {MsgType.ConfirmationRequest.getValue(), "net.hades.fix.message.builder.impl.ConfirmationRequestMsgBuilder"},
        {MsgType.CrossOrderCancelRequest.getValue(), "net.hades.fix.message.builder.impl.CrossOrderCancelRequestMsgBuilder"},
        {MsgType.CrossOrderModificationRequest.getValue(), "net.hades.fix.message.builder.impl.CrossOrderModificationRequestMsgBuilder"},
        {MsgType.DerivativeSecurityList.getValue(), "net.hades.fix.message.builder.impl.DerivativeSecurityListMsgBuilder"},
        {MsgType.DerivativeSecurityListRequest.getValue(), "net.hades.fix.message.builder.impl.DerivativeSecurityListRequestMsgBuilder"},
        {MsgType.DontKnowTrade.getValue(), "net.hades.fix.message.builder.impl.DontKnowTradeMsgBuilder"},
        {MsgType.Email.getValue(), "net.hades.fix.message.builder.impl.EmailMsgBuilder"},
        {MsgType.Heartbeat.getValue(), "net.hades.fix.message.builder.impl.HeartbeatMsgBuilder"},
        {MsgType.ExecutionReport.getValue(), "net.hades.fix.message.builder.impl.ExecutionReportMsgBuilder"},
        {MsgType.IndicationOfInterest.getValue(), "net.hades.fix.message.builder.impl.IOIMsgBuilder"},
        {MsgType.ListCancelRequest.getValue(), "net.hades.fix.message.builder.impl.ListCancelRequestMsgBuilder"},
        {MsgType.ListExecute.getValue(), "net.hades.fix.message.builder.impl.ListExecuteMsgBuilder"},
        {MsgType.ListStatus.getValue(), "net.hades.fix.message.builder.impl.ListStatusMsgBuilder"},
        {MsgType.ListStatusRequest.getValue(), "net.hades.fix.message.builder.impl.ListStatusRequestMsgBuilder"},
        {MsgType.ListStrikePrice.getValue(), "net.hades.fix.message.builder.impl.ListStrikePriceMsgBuilder"},
        {MsgType.Logon.getValue(), "net.hades.fix.message.builder.impl.LogonMsgBuilder"},
        {MsgType.Logout.getValue(), "net.hades.fix.message.builder.impl.LogoutMsgBuilder"},
        {MsgType.MarketDataIncrRefresh.getValue(), "net.hades.fix.message.builder.impl.MarketDataIncrRefreshMsgBuilder"},
        {MsgType.MarketDataRequest.getValue(), "net.hades.fix.message.builder.impl.MarketDataRequestMsgBuilder"},
        {MsgType.MarketDataRequestReject.getValue(), "net.hades.fix.message.builder.impl.MarketDataRequestRejectMsgBuilder"},
        {MsgType.MarketDataSnapshot.getValue(), "net.hades.fix.message.builder.impl.MarketDataSnapshotMsgBuilder"},
        {MsgType.MassQuoteAck.getValue(), "net.hades.fix.message.builder.impl.MassQuoteAckMsgBuilder"},
        {MsgType.MassQuote.getValue(), "net.hades.fix.message.builder.impl.MassQuoteMsgBuilder"},
        {MsgType.MultilegModificationRequest.getValue(), "net.hades.fix.message.builder.impl.MultilegModificationRequestMsgBuilder"},
        {MsgType.NewOrderCross.getValue(), "net.hades.fix.message.builder.impl.NewOrderCrossMsgBuilder"},
        {MsgType.NewOrderList.getValue(), "net.hades.fix.message.builder.impl.NewOrderListMsgBuilder"},
        {MsgType.NewOrderMultileg.getValue(), "net.hades.fix.message.builder.impl.NewOrderMultilegMsgBuilder"},
        {MsgType.NewOrderSingle.getValue(), "net.hades.fix.message.builder.impl.NewOrderSingleMsgBuilder"},
        {MsgType.News.getValue(), "net.hades.fix.message.builder.impl.NewsMsgBuilder"},
        {MsgType.OrderCancelReject.getValue(), "net.hades.fix.message.builder.impl.OrderCancelRejectMsgBuilder"},
        {MsgType.OrderCancelRequest.getValue(), "net.hades.fix.message.builder.impl.OrderCancelRequestMsgBuilder"},
        {MsgType.OrderMassCancelReport.getValue(), "net.hades.fix.message.builder.impl.OrderMassCancelReportMsgBuilder"},
        {MsgType.OrderMassCancelRequest.getValue(), "net.hades.fix.message.builder.impl.OrderMassCancelRequestMsgBuilder"},
        {MsgType.OrderMassStatusRequest.getValue(), "net.hades.fix.message.builder.impl.OrderMassStatusRequestMsgBuilder"},
        {MsgType.OrderModificationRequest.getValue(), "net.hades.fix.message.builder.impl.OrderModificationRequestMsgBuilder"},
        {MsgType.OrderStatusRequest.getValue(), "net.hades.fix.message.builder.impl.OrderStatusRequestMsgBuilder"},
        {MsgType.PositionMaintenanceReport.getValue(), "net.hades.fix.message.builder.impl.PositionMaintenanceReportMsgBuilder"},
        {MsgType.PositionMaintenanceRequest.getValue(), "net.hades.fix.message.builder.impl.PositionMaintenanceRequestMsgBuilder"},
        {MsgType.PositionReport.getValue(), "net.hades.fix.message.builder.impl.PositionReportMsgBuilder"},
        {MsgType.QuoteCancel.getValue(), "net.hades.fix.message.builder.impl.QuoteCancelMsgBuilder"},
        {MsgType.Quote.getValue(), "net.hades.fix.message.builder.impl.QuoteMsgBuilder"},
        {MsgType.QuoteRequest.getValue(), "net.hades.fix.message.builder.impl.QuoteRequestMsgBuilder"},
        {MsgType.QuoteRequestReject.getValue(), "net.hades.fix.message.builder.impl.QuoteRequestRejectMsgBuilder"},
        {MsgType.QuoteResponse.getValue(), "net.hades.fix.message.builder.impl.QuoteResponseMsgBuilder"},
        {MsgType.QuoteStatusReport.getValue(), "net.hades.fix.message.builder.impl.QuoteStatusReportMsgBuilder"},
        {MsgType.QuoteStatusRequest.getValue(), "net.hades.fix.message.builder.impl.QuoteStatusRequestMsgBuilder"},
        {MsgType.RFQRequest.getValue(), "net.hades.fix.message.builder.impl.RFQRequestMsgBuilder"},
        {MsgType.RegistrationInstructions.getValue(), "net.hades.fix.message.builder.impl.RegistrationInstructionsMsgBuilder"},
        {MsgType.RegistrationInstructionsResponse.getValue(), "net.hades.fix.message.builder.impl.RegistrationInstructionsResponseMsgBuilder"},
        {MsgType.Reject.getValue(), "net.hades.fix.message.builder.impl.RejectMsgBuilder"},
        {MsgType.RequestForPositionsAck.getValue(), "net.hades.fix.message.builder.impl.RequestForPositionsAckMsgBuilder"},
        {MsgType.RequestForPositions.getValue(), "net.hades.fix.message.builder.impl.RequestForPositionsMsgBuilder"},
        {MsgType.ResendRequest.getValue(), "net.hades.fix.message.builder.impl.ResendRequestMsgBuilder"},
        {MsgType.SecurityDefinition.getValue(), "net.hades.fix.message.builder.impl.SecurityDefinitionMsgBuilder"},
        {MsgType.SecurityDefinitionRequest.getValue(), "net.hades.fix.message.builder.impl.SecurityDefinitionRequestMsgBuilder"},
        {MsgType.SecurityList.getValue(), "net.hades.fix.message.builder.impl.SecurityListMsgBuilder"},
        {MsgType.SecurityListRequest.getValue(), "net.hades.fix.message.builder.impl.SecurityListRequestMsgBuilder"},
        {MsgType.SecurityStatus.getValue(), "net.hades.fix.message.builder.impl.SecurityStatusMsgBuilder"},
        {MsgType.SecurityStatusRequest.getValue(), "net.hades.fix.message.builder.impl.SecurityStatusRequestMsgBuilder"},
        {MsgType.SecurityTypeRequest.getValue(), "net.hades.fix.message.builder.impl.SecurityTypeRequestMsgBuilder"},
        {MsgType.SecurityTypes.getValue(), "net.hades.fix.message.builder.impl.SecurityTypesMsgBuilder"},
        {MsgType.SequenceReset.getValue(), "net.hades.fix.message.builder.impl.SequenceResetMsgBuilder"},
        {MsgType.SettlementInstructionRequest.getValue(), "net.hades.fix.message.builder.impl.SettlementInstructionRequestMsgBuilder"},
        {MsgType.SettlInstructions.getValue(), "net.hades.fix.message.builder.impl.SettlementInstructionsMsgBuilder"},
        {MsgType.TestRequest.getValue(), "net.hades.fix.message.builder.impl.TestRequestMsgBuilder"},
        {MsgType.TradeCaptureReportAck.getValue(), "net.hades.fix.message.builder.impl.TradeCaptureReportAckMsgBuilder"},
        {MsgType.TradeCaptureReport.getValue(), "net.hades.fix.message.builder.impl.TradeCaptureReportMsgBuilder"},
        {MsgType.TradeCaptureReportRequestAck.getValue(), "net.hades.fix.message.builder.impl.TradeCaptureReportRequestAckMsgBuilder"},
        {MsgType.TradeCaptureReportRequest.getValue(), "net.hades.fix.message.builder.impl.TradeCaptureReportRequestMsgBuilder"},
        {MsgType.TradingSessionStatus.getValue(), "net.hades.fix.message.builder.impl.TradingSessionStatusMsgBuilder"},
        {MsgType.TradingSessionStatusRequest.getValue(), "net.hades.fix.message.builder.impl.TradingSessionStatusRequestMsgBuilder"}
    };

    private final Map<String, String> msgBuilderClasses = new HashMap<String, String>();

    private final Map<String, MsgBuilder> msgBuilders = new HashMap<String, MsgBuilder>();
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    
    static {
        INSTANCE = new FIXMsgBuilder();
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    private FIXMsgBuilder() {
        loadMsgBuilderClasses();
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    
    /**
     * Decode a FIX message into a HadesFIX message object.
     * @param msg byte array of a FIX message
     * @return HadesFIX object
     * @throws TagNotPresentException
     * @throws InvalidMsgException
     * @throws BadFormatMsgException
     */
    public static FIXMsg build(byte[] msg)
    throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {

        ByteBuffer message = ByteBuffer.wrap(msg);
        DecodedMessage decodedMessage = INSTANCE.decodeHeader(message);
        MsgBuilder builder =  INSTANCE.msgBuilders.get(decodedMessage.header.getMsgType());
        if (builder == null) {
            builder = addMsgBuilderToCache(decodedMessage.header.getMsgType());
        }

        return builder.build(decodedMessage.header, decodedMessage.message);
    }

    /**
     * Factory method for a HadesFIX message object for a pre 5.0 version. <p/>
     * This method will invoke the {@link #build(String, BeginString, ApplVerID)} method for the
     * configured builder class with <code>applVerID</code> is set to null.
     * @param msgType type of FIX message
     * @param version thsi is the transport version (BeginString tag)
     * @return a HadesFIX message
     * @throws InvalidMsgException
     */
    public static FIXMsg build(String msgType, BeginString version)
    throws InvalidMsgException {
        return build(msgType, version, null);
    }
    
    /**
     * Factory method for a HadesFIX message object. If <code>applVerID</code> is specified than the
     * will be set to FIXT. For a pre 5.0 version the <code>applVerID</code> must be left null.<p/>
     *
     * @param msgType type of FIX message
     * @param version this is the transport version (BeginString tag)
     * @param applVerID needs to be populated for post 5.0 messages
     * @return a HadesFIX message
     * @throws InvalidMsgException
     */
    public static FIXMsg build(String msgType, BeginString version, ApplVerID applVerID)
    throws InvalidMsgException {
        if (msgType == null || msgType.trim().isEmpty()) {
            throw new IllegalArgumentException("Message type (FIX tag 35) cannot be empty.");
        }
        if (version == null) {
            throw new IllegalArgumentException("Fix version (FIX tag 8) cannot be empty.");
        }

        MsgBuilder builder =  INSTANCE.msgBuilders.get(msgType);
        if (builder == null) {
            builder = addMsgBuilderToCache(msgType);
        }
        try {
            return builder.build(version, applVerID);
        } catch (TagNotPresentException ex) {
            throw new InvalidMsgException("Tag not found error", ex);
        }
     }

    /**
     * This method can be used to programmatically add new message builders.<p/>
     * NOTE: Be aware that if the msgType is one of an existing fix message configured
     * by the HadesFIX this method will override that implementation.
     * @param msgType fix message type (tag 35)
     * @param builder message builder object (must inherit from {@link MsgBuilder} class.
     */
    public static synchronized void addMsgBuilder(String msgType, MsgBuilder builder) {
        if (msgType == null || msgType.trim().isEmpty()) {
            throw new IllegalArgumentException("Message type (FIX tag 35) cannot be empty.");
        }
        if (builder == null) {
            throw new IllegalArgumentException("Message builder object cannot be null.");
        }
        INSTANCE.msgBuilders.put(msgType, builder);
    }

    /**
     * This method can be used to programatically add a set of new message builders.<p/>
     * NOTE: Be aware that if the msgType is one of an existing fix message configured
     * by the HadesFIX this method will override that implementation.
     * @param builders map containing multiple message builders
     */
    public static synchronized void addMsgBuilders(Map<String, MsgBuilder> builders) {
        if (builders == null) {
            throw new IllegalArgumentException("Message builder map object cannot be null.");
        }
        INSTANCE.msgBuilders.putAll(builders);
    }

    /**
     * This method will clear the message builder for the given message type. This is only
     * useful if you override a default HadesFIX implementation of the message builder and
     * later in runtime you want to restore the original implementation.
     * @param msgType fix message type (tag 35)
     * @return the message builder object removed for the given message type
     */
    public static synchronized MsgBuilder clearMsgBuilder(String msgType) {
        return INSTANCE.msgBuilders.remove(msgType);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    
    /**
     * This method peeks in the first 3 message tags and returns them to the factory
     * method in order to build the proper message. A primary validation occurs at this
     * level for these 3 fields and the message checksum.
     * @param message byte array message
     * @return message version and type
     * @throws net.hades.fix.message.exception.InvalidMsgException error in case the first
     * 3 fields in the message are not set.
     */
    private DecodedMessage decodeHeader(ByteBuffer message) throws InvalidMsgException, BadFormatMsgException {
        DecodedMessage result = new DecodedMessage();
        Tag tag = MsgUtil.getNextTag(message);
        if (tag.tagNum != TagNum.BeginString.getValue()) {
            String error = "Not a valid FIX message. Does not begin with FIX version tag [" + TagNum.BeginString.getValue() + "].";
            LOGGER.severe(error);
            throw new InvalidMsgException(error);
        }
        BeginString version = null;
        try {
            version = BeginString.valueFor(new String(tag.value, FIXMsg.DEFAULT_CHARACTER_SET));
        } catch (UnsupportedEncodingException ex) {
            String error = "Could not encode given byte buffer as [ISO-8859-1].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new InvalidMsgException(error);
        }
        if (version == null) {
            String error = "This FIX message version is not supported yet [" + tag.value + "].";
            LOGGER.severe(error);
            throw new BadFormatMsgException(SessionRejectReason.Other, TagNum.BeginString.getValue(), error);
        }
        tag = MsgUtil.getNextTag(message);
        if (tag.tagNum != TagNum.BodyLength.getValue()) {
            String error = "Not a valid FIX message. Body length tag is missing [" + message.toString() + "].";
            LOGGER.severe(error);
            throw new InvalidMsgException(error);
        }
        int bodyLength = 0;
        try {
            bodyLength = Integer.parseInt(new String(tag.value, FIXMsg.DEFAULT_CHARACTER_SET));
        } catch (NumberFormatException ex) {
            String error = "Not a valid FIX message. Body length value is not a number [" + tag.value + "].";
            LOGGER.severe(error);
            throw new InvalidMsgException(error, ex);
        } catch (UnsupportedEncodingException ex) {
            String error = "Could not encode given byte buffer as [ISO-8859-1].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new InvalidMsgException(error);
        }
        int startBodyPos = message.position();
        message.mark();
        tag = MsgUtil.getNextTag(message);
        if (tag.tagNum != TagNum.MsgType.getValue()) {
            String error = "Not a valid FIX message. Does not have a FIX message type field [" + TagNum.MsgType.getValue() + "].";
            LOGGER.severe(error);
            throw new InvalidMsgException(error);
        }
        String msgType;
        try {
            msgType = new String(tag.value, FIXMsg.DEFAULT_CHARACTER_SET);
        } catch (UnsupportedEncodingException ex) {
            String error = "Could not encode given byte buffer as [ISO-8859-1].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new InvalidMsgException(error);
        }
        tag = MsgUtil.getNextTag(message);
        ApplVerID applVerID = null;
        if (tag.tagNum == TagNum.ApplVerID.getValue()) {
            try {
                applVerID = ApplVerID.valueFor(new String(tag.value, FIXMsg.DEFAULT_CHARACTER_SET));
            } catch (UnsupportedEncodingException ex) {
                String error = "Could not encode given byte buffer as [ISO-8859-1].";
                LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
                throw new InvalidMsgException(error);
            }
        }
        message.rewind();
        byte[] checksumBody = new byte[startBodyPos + bodyLength];
        message.get(checksumBody, 0, startBodyPos + bodyLength);
        tag = MsgUtil.getNextTag(message);
        if (tag.tagNum != TagNum.CheckSum.getValue()) {
            String error = "Not a valid FIX message. Message checksum tag is missing [" + message.toString() + "].";
            LOGGER.severe(error);
            throw new InvalidMsgException(error);
        }
        int rcvdChecksum = 0;
        try {
            rcvdChecksum = Integer.parseInt(new String(tag.value, FIXMsg.DEFAULT_CHARACTER_SET));
        } catch (NumberFormatException ex) {
            String error = "Not a valid FIX message. Checksum value is not a number [" + tag.value + "].";
            LOGGER.severe(error);
            throw new InvalidMsgException(error, ex);
        } catch (UnsupportedEncodingException ex) {
            String error = "Could not encode given byte buffer as [ISO-8859-1].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new InvalidMsgException(error);
        }
        int calcChecksum = MsgUtil.calculateChecksum(checksumBody);
        if (rcvdChecksum != calcChecksum) {
            String error = "Not a valid FIX message. Message received checksum [" + rcvdChecksum +
                    "] different than the calculated value [" + calcChecksum + "].";
            LOGGER.severe(error);
            throw new InvalidMsgException(error);
        }
        Header header = null;
        if (BeginString.FIXT_1_1.equals(version)) {
            if (applVerID == null) {
                applVerID = getSessionApplVerID();
            }
            header = getHeader(version, applVerID);
        } else if (BeginString.FIX_5_0.equals(version)) {
            header = getHeader(version, ApplVerID.FIX50);
        } else if (BeginString.FIX_5_0SP1.equals(version)) {
            header = getHeader(version, ApplVerID.FIX50SP1);
        } else if (BeginString.FIX_5_0SP2.equals(version)) {
            header = getHeader(version, ApplVerID.FIX50SP2);
        } else {
            header = getHeader(version);
        }
        header.setBeginString(version);
        header.setMsgType(msgType);
        header.setBodyLength(bodyLength);
        header.setHeaderSessionData();
        message.position(startBodyPos);
        message = decode(header, message);
        
        result.header = header;
        result.message = message;
        
        return result;
    }

    public ByteBuffer decode(Header header, ByteBuffer message) throws InvalidMsgException, BadFormatMsgException {
        ByteBuffer result = message;
        try {
            while (result.hasRemaining()) {
                result.mark();
                Tag tag = MsgUtil.getNextTag(result);
                if (MsgUtil.isTagInList(tag.tagNum, header.getFragmentAllTags())) {
                    result = header.decode(tag, result);
                } else {
                    result.reset();
                    break;
                }
            }
        } catch (TagNotPresentException ex) {
            throw new BadFormatMsgException(SessionRejectReason.RequiredTagMissing, header.getMsgType(),
                    TagNum.MsgType.getValue(), ex.getMessage());
        }

        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.finest("Header decoded");
        }

        return result;
    }

    private ApplVerID getSessionApplVerID() throws InvalidMsgException {
        
        ApplVerID result = null;
        SessionContext context = ThreadData.getSessionContext();
        if (context.getValue(SessionContextKey.DEFAULT_APPL_VER_ID) != null) {
            result = ApplVerID.valueFor((String) context.getValue(SessionContextKey.DEFAULT_APPL_VER_ID));
        } else {
            String error = "Default ApplVerID was not set in the session and the message has no ApplVerID set.";
            LOGGER.severe(error);
            throw new InvalidMsgException(error);
        }

        return result;
    }

    private void loadMsgBuilderClasses() {
        loadSpecMsgBuilderClasses();
        loadCustomMsgBuilderClasses();
    }

    private void loadSpecMsgBuilderClasses() {
        for (int i = 0; i < MSG_BUILDER_SPEC_CLASSES.length; i++) {
            msgBuilderClasses.put(MSG_BUILDER_SPEC_CLASSES[i][0], MSG_BUILDER_SPEC_CLASSES[i][1]);
        }
    }

    private void loadCustomMsgBuilderClasses() {
        Properties props = new Properties();
        List<String> customMsgBuilderFiles = getCustomMsgBuilderFiles();
        for (String customMsgBuilderFile : customMsgBuilderFiles) {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(customMsgBuilderFile);
            try {
                if (is != null) {
                    props.load(is);
                    LOGGER.log(Level.INFO, "Loading message builders for [{0}] configured file.", customMsgBuilderFile);
                } else {
                    LOGGER.log(Level.INFO, "No custom message builders configured for [{0}].", customMsgBuilderFile);
                }
            } catch (IOException ex) {
                LOGGER.info("No custom message builders configured on the classpath.");
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException ex) {
                        LOGGER.log(Level.WARNING, "Could not close input stream for [{0}] file.", customMsgBuilderFile);
                    }
                }
            }
        }
        Set<String> customMsgs = props.stringPropertyNames();
        for (String customMsg : customMsgs) {
            msgBuilderClasses.put(customMsg, props.getProperty(customMsg));
        }
    }

    private static MsgBuilder addMsgBuilderToCache(String msgType) throws InvalidMsgException {
        MsgBuilder builder = null;
        String builderClass = INSTANCE.msgBuilderClasses.get(msgType);
        if (builderClass == null) {
            String error = "No builder class configured for message [" + msgType.toString() + "].";
            LOGGER.severe(error);
            throw new InvalidMsgException(error);
        }
        try {
            Class<? extends MsgBuilder> mbClass = Class.forName(builderClass).asSubclass(MsgBuilder.class);
            builder = mbClass.newInstance();
        } catch (ClassNotFoundException ex) {
            String error = "Class [" + builderClass + "] not found on the classpath. Error was : " + ex.getMessage();
            LOGGER.severe(error);
            throw new InvalidMsgException(error);
        } catch (InstantiationException ex) {
            String error = "Could not create an instance of the class [" + builderClass + "]. Error was : " + ex.getMessage();
            LOGGER.severe(error);
            throw new InvalidMsgException(error);
        } catch (IllegalAccessException ex) {
            String error = "Constructor of the class [" + builderClass + "] is not accessible. Error was : " + ex.getMessage();
            LOGGER.severe(error);
            throw new InvalidMsgException(error);
        }
        INSTANCE.msgBuilders.put(msgType, builder);

        return builder;
    }

    private Header getHeader(BeginString beginString) throws InvalidMsgException {
        Header header = null;
        switch (beginString) {
            case FIX_4_0:
                header = new Header40();
                break;

            case FIX_4_1:
                header = new Header41();
                break;

            case FIX_4_2:
                header = new Header42();
                break;

            case FIX_4_3:
                header = new Header43();
                break;

            case FIX_4_4:
                header = new Header44();
                break;

            default:
                throw new InvalidMsgException("FIX messages with version [" + beginString.getValue() + "] are not yet supported.");
        }

        return header;
    }

    private Header getHeader(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {

        Header header = null;
        switch (beginString) {
           case FIX_5_0:
                header = new HeaderT11_50();
                break;

            case FIX_5_0SP1:
                header = new HeaderT11_50SP1();
                break;

            case FIX_5_0SP2:
                header = new HeaderT11_50SP2();
                break;

            case FIXT_1_1:
                header = getHeaderForApplVerID(applVerID);
                break;

            default:
                throw new InvalidMsgException("FIX messages with version [" + beginString.getValue() +
                        "] are not yet supported.");
        }

        return header;
    }

    private Header getHeaderForApplVerID(ApplVerID applVerID) throws InvalidMsgException {
        Header header = null;

        switch (applVerID) {
            case FIX40:
                header = new Header40();
                break;

            case FIX41:
                header = new Header41();
                break;

            case FIX42:
                header = new Header42();
                break;

            case FIX43:
                header = new Header43();
                break;

            case FIX44:
                header = new Header44();
                break;

            case FIX50:
                header = new HeaderT11_50();
                break;

            case FIX50SP1:
                header = new HeaderT11_50SP1();
                break;

            case FIX50SP2:
                header = new HeaderT11_50SP2();
                break;

            default:
                throw new InvalidMsgException("FIX messages with version [" + applVerID + "] are not yet supported.");
        }

        return header;
    }

    private List<String> getCustomMsgBuilderFiles() {
        List<String> result = new ArrayList<String>();
        result.add(CUSTOM_MSG_BUILDER_FILE_NAME);
        String locations = System.getProperty(CUSTOM_MSG_BUILDER_FILE_LOCATIONS);
        if (locations != null && !locations.trim().isEmpty()) {
            String[] locArry = locations.split(":");
            for (String loc : locArry) {
                result.add(packageToPath(loc));
            }
        }

        return result;
    }

    private String packageToPath(String loc) {
        String path = loc.replaceAll("\\.", "/");
        StringBuilder sb = new StringBuilder();
        sb.append(path).append("/").append(CUSTOM_MSG_BUILDER_FILE_NAME);

        return sb.toString();
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">

    private class DecodedMessage {
        private Header header;
        private ByteBuffer message;
    }
    
    // </editor-fold>
}
