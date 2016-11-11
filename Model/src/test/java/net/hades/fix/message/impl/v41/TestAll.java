/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.45 2011-10-29 02:54:35 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41;

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
    AdvertisementMsg41Test.class,
    AllocationInstructionAckMsg41Test.class,
    AllocationInstructionMsg41Test.class,
    AllocationReportAckMsg41Test.class,
    AllocationReportMsg41Test.class,
    AssignmentReportMsg41Test.class,
    BidRequestMsg41Test.class,
    BidResponseMsg41Test.class,
    CollateralAssignmentMsg41Test.class,
    CollateralInquiryAckMsg41Test.class,
    CollateralInquiryMsg41Test.class,
    CollateralReportMsg41Test.class,
    CollateralRequestMsg41Test.class,
    CollateralResponseMsg41Test.class,
    ConfirmationAckMsg41Test.class,
    ConfirmationMsg41Test.class,
    ConfirmationRequestMsg41Test.class,
    CrossOrderCancelRequestMsg41Test.class,
    CrossOrderModificationRequestMsg41Test.class,
    DerivativeSecurityListMsg41Test.class,
    DerivativeSecurityListRequestMsg41Test.class,
    DontKnowTradeMsg41Test.class,
    EmailMsg41Test.class,
    ExecutionReportMsg41Test.class,
    IOIMsg41Test.class,
    ListCancelRequestMsg41Test.class,
    ListExecuteMsg41Test.class,
    ListStatusMsg41Test.class,
    ListStatusRequestMsg41Test.class,
    ListStrikePriceMsg41Test.class,
    MarketDataRequestMsg41Test.class,
    MarketDataRequestRejectMsg41Test.class,
    MassQuoteAckMsg41Test.class,
    MassQuoteMsg41Test.class,
    MultilegModificationRequestMsg41Test.class,
    NewOrderCrossMsg41Test.class,
    NewOrderListMsg41Test.class,
    NewOrderMultilegMsg41Test.class,
    NewOrderSingleMsg41Test.class,
    NewsMsg41Test.class,
    OrderCancelRejectMsg41Test.class,
    OrderCancelRequestMsg41Test.class,
    OrderMassCancelReportMsg41Test.class,
    OrderMassCancelRequestMsg41Test.class,
    OrderMassStatusRequestMsg41Test.class,
    OrderModificationRequestMsg41Test.class,
    OrderStatusRequestMsg41Test.class,
    PositionMaintenanceReportMsg41Test.class,
    PositionMaintenanceRequestMsg41Test.class,
    PositionReportMsg41Test.class,
    QuoteCancelMsg41Test.class,
    QuoteMsg41Test.class,
    QuoteRequestMsg41Test.class,
    QuoteRequestRejectMsg41Test.class,
    QuoteResponseMsg41Test.class,
    QuoteStatusReportMsg41Test.class,
    QuoteStatusRequestMsg41Test.class,
    RFQRequestMsg41Test.class,
    RegistrationInstructionsMsg41Test.class,
    RegistrationInstructionsResponseMsg41Test.class,
    RequestForPositionsAckMsg41Test.class,
    RequestForPositionsMsg41Test.class,
    SecurityDefinitionMsg41Test.class,
    SecurityDefinitionRequestMsg41Test.class,
    SecurityListMsg41Test.class,
    SecurityListRequestMsg41Test.class,
    SecurityStatusMsg41Test.class,
    SecurityStatusRequestMsg41Test.class,
    SecurityTypeRequestMsg41Test.class,
    SecurityTypesMsg41Test.class,
    SettlementInstructionRequestMsg41Test.class,
    SettlementInstructionsMsg41Test.class,
    TradeCaptureReportAckMsg41Test.class,
    TradeCaptureReportMsg41Test.class,
    TradeCaptureReportRequestAckMsg41Test.class,
    TradeCaptureReportRequestMsg41Test.class,
    TradingSessionStatusMsg41Test.class,
    TradingSessionStatusRequestMsg41Test.class
})
public class TestAll {
}
