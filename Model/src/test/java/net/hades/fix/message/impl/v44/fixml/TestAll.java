/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.41 2011-10-29 02:54:36 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44.fixml;

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
    AdvertisementMsgFIXML44Test.class,
    AllocationInstructionAckMsgFIXML44Test.class,
    AllocationInstructionMsgFIXML44Test.class,
    AllocationReportAckMsgFIXML44Test.class,
    AllocationReportMsgFIXML44Test.class,
    AssignmentReportMsgFIXML44Test.class,
    BatchSetMsgFIXML44Test.class,
    BidRequestMsgFIXML44Test.class,
    BidResponseMsgFIXML44Test.class,
    CollateralAssignmentMsgFIXML44Test.class,
    CollateralInquiryMsgFIXML44Test.class,
    CollateralInquiryAckMsgFIXML44Test.class,
    CollateralReportMsgFIXML44Test.class,
    CollateralRequestMsgFIXML44Test.class,
    CollateralResponseMsgFIXML44Test.class,
    ConfirmationAckMsgFIXML44Test.class,
    ConfirmationMsgFIXML44Test.class,
    ConfirmationRequestMsgFIXML44Test.class,
    CrossOrderCancelRequestMsgFIXML44Test.class,
    CrossOrderModificationRequestMsgFIXML44Test.class,
    DerivativeSecurityListMsgFIXML44Test.class,
    DerivativeSecurityListRequestMsgFIXML44Test.class,
    DontKnowTradeMsgFIXML44Test.class,
    EmailMsgFIXML44Test.class,
    ExecutionReportMsgFIXML44Test.class,
    IOIMsgFIXML44Test.class,
    ListCancelRequestMsgFIXML44Test.class,
    ListExecuteMsgFIXML44Test.class,
    ListStatusMsgFIXML44Test.class,
    ListStatusRequestMsgFIXML44Test.class,
    ListStrikePriceMsgFIXML44Test.class,
    MarketDataRequestMsgFIXML44Test.class,
    MarketDataRequestRejectMsgFIXML44Test.class,
    MassQuoteAckMsgFIXML44Test.class,
    MassQuoteMsgFIXML44Test.class,
    MultilegModificationRequestMsgFIXML44Test.class,
    NewOrderCrossMsgFIXML44Test.class,
    NewOrderListMsgFIXML44Test.class,
    NewOrderMultilegMsgFIXML44Test.class,
    NewOrderSingleMsgFIXML44Test.class,
    NewsMsgFIXML44Test.class,
    OrderCancelRejectMsgFIXML44Test.class,
    OrderCancelRequestMsgFIXML44Test.class,
    OrderMassCancelReportMsgFIXML44Test.class,
    OrderMassCancelRequestMsgFIXML44Test.class,
    OrderMassStatusRequestMsgFIXML44Test.class,
    OrderModificationRequestMsgFIXML44Test.class,
    OrderStatusRequestMsgFIXML44Test.class,
    PositionMaintenanceReportMsgFIXML44Test.class,
    PositionMaintenanceRequestMsgFIXML44Test.class,
    PositionReportMsgFIXML44Test.class,
    QuoteCancelMsgFIXML44Test.class,
    QuoteRequestMsgFIXML44Test.class,
    QuoteRequestRejectMsgFIXML44Test.class,
    QuoteMsgFIXML44Test.class,
    QuoteStatusReportMsgFIXML44Test.class,
    QuoteResponseMsgFIXML44Test.class,
    QuoteStatusRequestMsgFIXML44Test.class,
    RFQRequestMsgFIXML44Test.class,
    RegistrationInstructionsMsgFIXML44Test.class,
    RegistrationInstructionsResponseMsgFIXML44Test.class,
    RequestForPositionsAckMsgFIXML44Test.class,
    RequestForPositionsMsgFIXML44Test.class,
    SecurityDefinitionMsgFIXML44Test.class,
    SecurityDefinitionRequestMsgFIXML44Test.class,
    SecurityListMsgFIXML44Test.class,
    SecurityListRequestMsgFIXML44Test.class,
    SecurityStatusMsgFIXML44Test.class,
    SecurityStatusRequestMsgFIXML44Test.class,
    SecurityTypeRequestMsgFIXML44Test.class,
    SecurityTypesMsgFIXML44Test.class,
    SettlementInstructionRequestMsgFIXML44Test.class,
    SettlementInstructionsMsgFIXML44Test.class,
    TradeCaptureReportAckMsgFIXML44Test.class,
    TradeCaptureReportMsgFIXML44Test.class,
    TradeCaptureReportRequestAckMsgFIXML44Test.class,
    TradeCaptureReportRequestMsgFIXML44Test.class,
    TradingSessionStatusMsgFIXML44Test.class,
    TradingSessionStatusRequestMsgFIXML44Test.class
})
public class TestAll {
}
