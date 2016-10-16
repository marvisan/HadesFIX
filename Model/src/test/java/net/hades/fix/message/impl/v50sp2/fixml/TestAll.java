/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.41 2011-10-29 02:54:35 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2.fixml;

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
    AdvertisementMsgFIXML50SP2Test.class,
    AllocationInstructionAckMsgFIXML50SP2Test.class,
    AllocationInstructionMsgFIXML50SP2Test.class,
    AllocationReportAckMsgFIXML50SP2Test.class,
    AllocationReportMsgFIXML50SP2Test.class,
    AssignmentReportMsgFIXML50SP2Test.class,
    BatchSetMsgFIXML50SP2Test.class,
    BidRequestMsgFIXML50SP2Test.class,
    BidResponseMsgFIXML50SP2Test.class,
    CollateralAssignmentMsgFIXML50SP2Test.class,
    CollateralInquiryAckMsgFIXML50SP2Test.class,
    CollateralInquiryMsgFIXML50SP2Test.class,
    CollateralReportMsgFIXML50SP2Test.class,
    CollateralRequestMsgFIXML50SP2Test.class,
    CollateralResponseMsgFIXML50SP2Test.class,
    ConfirmationAckMsgFIXML50SP2Test.class,
    ConfirmationMsgFIXML50SP2Test.class,
    ConfirmationRequestMsgFIXML50SP2Test.class,
    CrossOrderCancelRequestMsgFIXML50SP2Test.class,
    CrossOrderModificationRequestMsgFIXML50SP2Test.class,
    DerivativeSecurityListMsgFIXML50SP2Test.class,
    DerivativeSecurityListRequestMsgFIXML50SP2Test.class,
    DontKnowTradeMsgFIXML50SP2Test.class,
    EmailMsgFIXML50SP2Test.class,
    ExecutionReportFIXML50SP2Test.class,
    IOIMsgFIXML50SP2Test.class,
    ListCancelRequestMsgFIXML50SP2Test.class,
    ListExecuteMsgFIXML50SP2Test.class,
    ListStatusMsgFIXML50SP2Test.class,
    ListStatusRequestMsgFIXML50SP2Test.class,
    ListStrikePriceMsgFIXML50SP2Test.class,
    MarketDataIncrRefreshMsgFIXML50SP2Test.class,
    MarketDataRequestMsgFIXML50SP2Test.class,
    MarketDataRequestRejectMsgFIXML50SP2Test.class,
    MarketDataSnapshotMsgFIXML50SP2Test.class,
    MassQuoteAckMsgFIXML50SP2Test.class,
    MassQuoteMsgFIXML50SP2Test.class,
    MultilegModificationRequestMsgFIXML50SP2Test.class,
    NewOrderCrossMsgFIXML50SP2Test.class,
    NewOrderListMsgFIXML50SP2Test.class,
    NewOrderMultilegMsgFIXML50SP2Test.class,
    NewOrderSingleMsgFIXML50SP2Test.class,
    NewsMsgFIXML50SP2Test.class,
    OrderCancelRejectMsgFIXML50SP2Test.class,
    OrderCancelRequestMsgFIXML50SP2Test.class,
    OrderMassCancelReportMsgFIXML50SP2Test.class,
    OrderMassCancelRequestMsgFIXML50SP2Test.class,
    OrderMassStatusRequestMsgFIXML50SP2Test.class,
    OrderModificationRequestMsgFIXML50SP2Test.class,
    OrderStatusRequestMsgFIXML50SP2Test.class,
    PositionMaintenanceReportMsgFIXML50SP2Test.class,
    PositionMaintenanceRequestMsgFIXML50SP2Test.class,
    PositionReportMsgFIXML50SP2Test.class,
    QuoteCancelMsgFIXML50SP2Test.class,
    QuoteMsgFIXML50SP2Test.class,
    QuoteRequestMsgFIXML50SP2Test.class,
    QuoteRequestRejectMsgFIXML50SP2Test.class,
    QuoteResponseMsgFIXML50SP2Test.class,
    QuoteStatusReportMsgFIXML50SP2Test.class,
    QuoteStatusRequestMsgFIXML50SP2Test.class,
    RFQRequestMsgFIXML50SP2Test.class,
    RegistrationInstructionsMsgFIXML50SP2Test.class,
    RegistrationInstructionsResponseMsgFIXML50SP2Test.class,
    RequestForPositionsAckMsgFIXML50SP2Test.class,
    RequestForPositionsMsgFIXML50SP2Test.class,
    SecurityDefinitionMsgFIXML50SP2Test.class,
    SecurityDefinitionRequestMsgFIXML50SP2Test.class,
    SecurityListMsgFIXML50SP2Test.class,
    SecurityListRequestMsgFIXML50SP2Test.class,
    SecurityStatusMsgFIXML50SP2Test.class,
    SecurityStatusRequestMsgFIXML50SP2Test.class,
    SecurityTypeRequestMsgFIXML50SP2Test.class,
    SecurityTypesMsgFIXML50SP2Test.class,
    SettlementInstructionRequestMsgFIXML50SP2Test.class,
    SettlementInstructionsMsgFIXML50SP2Test.class,
    TradeCaptureReportAckMsgFIXML50SP2Test.class,
    TradeCaptureReportMsgFIXML50SP2Test.class,
    TradeCaptureReportRequestAckMsgFIXML50SP2Test.class,
    TradeCaptureReportRequestMsgFIXML50SP2Test.class,
    TradingSessionStatusMsgFIXML50SP2Test.class,
    TradingSessionStatusRequestMsgFIXML50SP2Test.class
})
public class TestAll {
}
