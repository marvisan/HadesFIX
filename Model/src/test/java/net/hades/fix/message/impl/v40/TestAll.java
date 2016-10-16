/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.45 2011-10-29 02:54:36 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all the message Test Cases.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.45 $
 * @created 01/11/2008, 6:28:48 PM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AdvertisementMsg40Test.class,
    AllocationInstructionAckMsg40Test.class,
    AllocationInstructionMsg40Test.class,
    AllocationReportAckMsg40Test.class,
    AllocationReportMsg40Test.class,
    AssignmentReportMsg40Test.class,
    BidRequestMsg40Test.class,
    BidResponseMsg40Test.class,
    CollateralAssignmentMsg40Test.class,
    CollateralInquiryAckMsg40Test.class,
    CollateralInquiryMsg40Test.class,
    CollateralReportMsg40Test.class,
    CollateralRequestMsg40Test.class,
    CollateralResponseMsg40Test.class,
    ConfirmationAckMsg40Test.class,
    ConfirmationMsg40Test.class,
    ConfirmationRequestMsg40Test.class,
    CrossOrderCancelRequestMsg40Test.class,
    CrossOrderModificationRequestMsg40Test.class,
    DerivativeSecurityListMsg40Test.class,
    DerivativeSecurityListRequestMsg40Test.class,
    DontKnowTradeMsg40Test.class,
    EmailMsg40Test.class,
    ExecutionReportMsg40Test.class,
    IOIMsg40Test.class,
    ListCancelRequestMsg40Test.class,
    ListExecuteMsg40Test.class,
    ListStatusMsg40Test.class,
    ListStatusRequestMsg40Test.class,
    ListStrikePriceMsg40Test.class,
    MarketDataRequestMsg40Test.class,
    MarketDataRequestRejectMsg40Test.class,
    MassQuoteAckMsg40Test.class,
    MassQuoteMsg40Test.class,
    MultilegModificationRequestMsg40Test.class,
    NewOrderCrossMsg40Test.class,
    NewOrderListMsg40Test.class,
    NewOrderMultilegMsg40Test.class,
    NewOrderSingleMsg40Test.class,
    NewsMsg40Test.class,
    OrderCancelRejectMsg40Test.class,
    OrderCancelRequestMsg40Test.class,
    OrderMassCancelReportMsg40Test.class,
    OrderMassCancelRequestMsg40Test.class,
    OrderMassStatusRequestMsg40Test.class,
    OrderModificationRequestMsg40Test.class,
    OrderStatusRequestMsg40Test.class,
    PositionMaintenanceReportMsg40Test.class,
    PositionMaintenanceRequestMsg40Test.class,
    PositionReportMsg40Test.class,
    QuoteCancelMsg40Test.class,
    QuoteMsg40Test.class,
    QuoteRequestMsg40Test.class,
    QuoteRequestRejectMsg40Test.class,
    QuoteResponseMsg40Test.class,
    QuoteStatusReportMsg40Test.class,
    QuoteStatusRequestMsg40Test.class,
    RFQRequestMsg40Test.class,
    RegistrationInstructionsMsg40Test.class,
    RegistrationInstructionsResponseMsg40Test.class,
    RequestForPositionsAckMsg40Test.class,
    RequestForPositionsMsg40Test.class,
    SecurityDefinitionMsg40Test.class,
    SecurityDefinitionRequestMsg40Test.class,
    SecurityListMsg40Test.class,
    SecurityListRequestMsg40Test.class,
    SecurityStatusMsg40Test.class,
    SecurityStatusRequestMsg40Test.class,
    SecurityTypeRequestMsg40Test.class,
    SecurityTypesMsg40Test.class,
    SettlementInstructionRequestMsg40Test.class,
    SettlementInstructionsMsg40Test.class,
    TradeCaptureReportAckMsg40Test.class,
    TradeCaptureReportMsg40Test.class,
    TradeCaptureReportRequestAckMsg40Test.class,
    TradeCaptureReportRequestMsg40Test.class,
    TradingSessionStatusMsg40Test.class,
    TradingSessionStatusRequestMsg40Test.class
})
public class TestAll {
}
