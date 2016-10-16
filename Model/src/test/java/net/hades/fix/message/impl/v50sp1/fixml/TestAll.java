/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.41 2011-10-29 02:54:35 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1.fixml;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all the message Test Cases.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.41 $
 * @created 01/11/2008, 6:28:48 PM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AdvertisementMsgFIXML50SP1Test.class,
    AllocationInstructionAckMsgFIXML50SP1Test.class,
    AllocationInstructionMsgFIXML50SP1Test.class,
    AllocationReportAckMsgFIXML50SP1Test.class,
    AllocationReportMsgFIXML50SP1Test.class,
    AssignmentReportMsgFIXML50SP1Test.class,
    BatchSetMsgFIXML50SP1Test.class,
    BidRequestMsgFIXML50SP1Test.class,
    BidResponseMsgFIXML50SP1Test.class,
    CollateralAssignmentMsgFIXML50SP1Test.class,
    CollateralInquiryAckMsgFIXML50SP1Test.class,
    CollateralInquiryMsgFIXML50SP1Test.class,
    CollateralReportMsgFIXML50SP1Test.class,
    CollateralRequestMsgFIXML50SP1Test.class,
    CollateralResponseMsgFIXML50SP1Test.class,
    ConfirmationAckMsgFIXML50SP1Test.class,
    ConfirmationMsgFIXML50SP1Test.class,
    ConfirmationRequestMsgFIXML50SP1Test.class,
    CrossOrderCancelRequestMsgFIXML50SP1Test.class,
    CrossOrderModificationRequestMsgFIXML50SP1Test.class,
    DerivativeSecurityListMsgFIXML50SP1Test.class,
    DerivativeSecurityListRequestMsgFIXML50SP1Test.class,
    EmailMsgFIXML50SP1Test.class,
    ExecutionReportFIXML50SP1Test.class,
    IOIMsgFIXML50SP1Test.class,
    ListCancelRequestMsgFIXML50SP1Test.class,
    ListExecuteMsgFIXML50SP1Test.class,
    ListStatusMsgFIXML50SP1Test.class,
    ListStatusRequestMsgFIXML50SP1Test.class,
    ListStrikePriceMsgFIXML50SP1Test.class,
    MarketDataIncrRefreshMsgFIXML50SP1Test.class,
    MarketDataRequestRejectMsgFIXML50SP1Test.class,
    MarketDataSnapshotMsgFIXML50SP1Test.class,
    MassQuoteAckMsgFIXML50SP1Test.class,
    MassQuoteMsgFIXML50SP1Test.class,
    MultilegModificationRequestMsgFIXML50SP1Test.class,
    NewOrderCrossMsgFIXML50SP1Test.class,
    NewOrderListMsgFIXML50SP1Test.class,
    NewOrderMultilegMsgFIXML50SP1Test.class,
    NewOrderSingleMsgFIXML50SP1Test.class,
    NewsMsgFIXML50SP1Test.class,
    OrderCancelRejectMsgFIXML50SP1Test.class,
    OrderCancelRequestMsgFIXML50SP1Test.class,
    OrderMassCancelReportMsgFIXML50SP1Test.class,
    OrderMassCancelRequestMsgFIXML50SP1Test.class,
    OrderMassStatusRequestMsgFIXML50SP1Test.class,
    OrderModificationRequestMsgFIXML50SP1Test.class,
    OrderStatusRequestMsgFIXML50SP1Test.class,
    PositionMaintenanceReportMsgFIXML50SP1Test.class,
    PositionMaintenanceRequestMsgFIXML50SP1Test.class,
    PositionReportMsgFIXML50SP1Test.class,
    QuoteCancelMsgFIXML50SP1Test.class,
    QuoteMsgFIXML50SP1Test.class,
    QuoteRequestRejectMsgFIXML50SP1Test.class,
    QuoteResponseMsgFIXML50SP1Test.class,
    QuoteStatusReportMsgFIXML50SP1Test.class,
    QuoteStatusRequestMsgFIXML50SP1Test.class,
    RFQRequestMsgFIXML50SP1Test.class,
    RegistrationInstructionsMsgFIXML50SP1Test.class,
    RegistrationInstructionsResponseMsgFIXML50SP1Test.class,
    RequestForPositionsAckMsgFIXML50SP1Test.class,
    RequestForPositionsMsgFIXML50SP1Test.class,
    SecurityDefinitionMsgFIXML50SP1Test.class,
    SecurityDefinitionRequestMsgFIXML50SP1Test.class,
    SecurityListMsgFIXML50SP1Test.class,
    SecurityListRequestMsgFIXML50SP1Test.class,
    SecurityStatusMsgFIXML50SP1Test.class,
    SecurityStatusRequestMsgFIXML50SP1Test.class,
    SecurityStatusRequestMsgFIXML50SP1Test.class,
    SecurityTypeRequestMsgFIXML50SP1Test.class,
    SecurityTypesMsgFIXML50SP1Test.class,
    SettlementInstructionRequestMsgFIXML50SP1Test.class,
    SettlementInstructionsMsgFIXML50SP1Test.class,
    TradeCaptureReportAckMsgFIXML50SP1Test.class,
    TradeCaptureReportMsgFIXML50SP1Test.class,
    TradeCaptureReportRequestAckMsgFIXML50SP1Test.class,
    TradeCaptureReportRequestMsgFIXML50SP1Test.class,
    TradingSessionStatusMsgFIXML50SP1Test.class,
    TradingSessionStatusRequestMsgFIXML50SP1Test.class
})
public class TestAll {
}
