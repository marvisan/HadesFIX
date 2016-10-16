/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.42 2011-10-29 02:54:35 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50.fixml;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all the message Test Cases.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.42 $
 * @created 01/11/2008, 6:28:48 PM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AdvertisementMsgFIXML50Test.class,
    AllocationInstructionAckMsgFIXML50Test.class,
    AllocationInstructionMsgFIXML50Test.class,
    AllocationReportAckMsgFIXML50Test.class,
    AllocationReportMsgFIXML50Test.class,
    AssignmentReportMsgFIXML50Test.class,
    BatchSetMsgFIXML50Test.class,
    BidRequestMsgFIXML50Test.class,
    BidResponseMsgFIXML50Test.class,
    CollateralAssignmentMsgFIXML50Test.class,
    CollateralInquiryAckMsgFIXML50Test.class,
    CollateralInquiryMsgFIXML50Test.class,
    CollateralReportMsgFIXML50Test.class,
    CollateralRequestMsgFIXML50Test.class,
    CollateralResponseMsgFIXML50Test.class,
    ConfirmationAckMsgFIXML50Test.class,
    ConfirmationMsgFIXML50Test.class,
    ConfirmationRequestMsgFIXML50Test.class,
    CrossOrderCancelRequestMsgFIXML50Test.class,
    CrossOrderModificationRequestMsgFIXML50Test.class,
    DerivativeSecurityListMsgFIXML50Test.class,
    DerivativeSecurityListRequestMsgFIXML50Test.class,
    DontKnowTradeMsgFIXML50Test.class,
    EmailMsgFIXML50Test.class,
    ExecutionReportFIXML50Test.class,
    IOIMsgFIXML50Test.class,
    ListCancelRequestMsgFIXML50Test.class,
    ListExecuteMsgFIXML50Test.class,
    ListStatusMsgFIXML50Test.class,
    ListStatusRequestMsgFIXML50Test.class,
    ListStrikePriceMsgFIXML50Test.class,
    MarketDataIncrRefreshMsgFIXML50Test.class,
    MarketDataRequestRejectMsgFIXML50Test.class,
    MassQuoteAckMsgFIXML50Test.class,
    MassQuoteMsgFIXML50Test.class,
    MultilegModificationRequestMsgFIXML50Test.class,
    NewOrderCrossMsgFIXML50Test.class,
    NewOrderListMsgFIXML50Test.class,
    NewOrderMultilegMsgFIXML50Test.class,
    NewOrderSingleMsgFIXML50Test.class,
    NewsMsgFIXML50Test.class,
    OrderCancelRejectMsgFIXML50Test.class,
    OrderCancelRequestMsgFIXML50Test.class,
    OrderMassCancelReportMsgFIXML50Test.class,
    OrderMassCancelRequestMsgFIXML50Test.class,
    OrderMassStatusRequestMsgFIXML50Test.class,
    OrderModificationRequestMsgFIXML50Test.class,
    OrderStatusRequestMsgFIXML50Test.class,
    PositionMaintenanceReportMsgFIXML50Test.class,
    PositionMaintenanceRequestMsgFIXML50Test.class,
    PositionReportMsgFIXML50Test.class,
    QuoteCancelMsgFIXML50Test.class,
    QuoteMsgFIXML50Test.class,
    QuoteRequestMsgFIXML50Test.class,
    QuoteRequestRejectMsgFIXML50Test.class,
    QuoteResponseMsgFIXML50Test.class,
    QuoteStatusReportMsgFIXML50Test.class,
    QuoteStatusRequestMsgFIXML50Test.class,
    RFQRequestMsgFIXML50Test.class,
    RegistrationInstructionsMsgFIXML50Test.class,
    RegistrationInstructionsResponseMsgFIXML50Test.class,
    RequestForPositionsAckMsgFIXML50Test.class,
    RequestForPositionsMsgFIXML50Test.class,
    SecurityDefinitionMsgFIXML50Test.class,
    SecurityDefinitionRequestMsgFIXML50Test.class,
    SecurityListRequestMsgFIXML50Test.class,
    SecurityStatusMsgFIXML50Test.class,
    SecurityStatusRequestMsgFIXML50Test.class,
    SecurityTypeRequestMsgFIXML50Test.class,
    SecurityTypesMsgFIXML50Test.class,
    SettlementInstructionRequestMsgFIXML50Test.class,
    SettlementInstructionsMsgFIXML50Test.class,
    TradeCaptureReportAckMsgFIXML50Test.class,
    TradeCaptureReportMsgFIXML50Test.class,
    TradeCaptureReportRequestAckMsgFIXML50Test.class,
    TradeCaptureReportRequestMsgFIXML50Test.class,
    TradingSessionStatusMsgFIXML50Test.class,
    TradingSessionStatusRequestMsgFIXML50Test.class
})
public class TestAll {
}
