/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.47 2011-10-29 02:54:35 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all the message Test Cases.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.47 $
 * @created 01/11/2008, 6:28:48 PM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AdvertisementMsg44Test.class,
    AllocationInstructionAckMsg44Test.class,
    AllocationInstructionMsg44Test.class,
    AllocationReportAckMsg44Test.class,
    AllocationReportMsg44Test.class,
    AssignmentReportMsg44Test.class,
    BidRequestMsg44Test.class,
    BidResponseMsg44Test.class,
    CollateralAssignmentMsg44Test.class,
    CollateralInquiryMsg44Test.class,
    CollateralInquiryAckMsg44Test.class,
    CollateralReportMsg44Test.class,
    CollateralRequestMsg44Test.class,
    CollateralResponseMsg44Test.class,
    ConfirmationAckMsg44Test.class,
    ConfirmationMsg44Test.class,
    ConfirmationRequestMsg44Test.class,
    CrossOrderCancelRequestMsg44Test.class,
    CrossOrderModificationRequestMsg44Test.class,
    DerivativeSecurityListRequestMsg44Test.class,
    DontKnowTradeMsg44Test.class,
    EmailMsg44Test.class,
    ExecutionReportMsg44Test.class,
    IOIMsg44Test.class,
    ListCancelRequestMsg44Test.class,
    ListExecuteMsg44Test.class,
    ListStatusMsg44Test.class,
    ListStatusRequestMsg44Test.class,
    ListStrikePriceMsg44Test.class,
    MarketDataIncrRefreshMsg44Test.class,
    MarketDataRequestMsg44Test.class,
    MarketDataRequestMsg44Test.class,
    MarketDataRequestRejectMsg44Test.class,
    MassQuoteAckMsg44Test.class,
    MassQuoteMsg44Test.class,
    MultilegModificationRequestMsg44Test.class,
    NewOrderCrossMsg44Test.class,
    NewOrderListMsg44Test.class,
    NewOrderMultilegMsg44Test.class,
    NewOrderSingleMsg44Test.class,
    NewsMsg44Test.class,
    OrderCancelRejectMsg44Test.class,
    OrderCancelRequestMsg44Test.class,
    OrderMassCancelReportMsg44Test.class,
    OrderMassCancelRequestMsg44Test.class,
    OrderMassStatusRequestMsg44Test.class,
    OrderModificationRequestMsg44Test.class,
    OrderStatusRequestMsg44Test.class,
    PositionMaintenanceReportMsg44Test.class,
    PositionMaintenanceRequestMsg44Test.class,
    PositionReportMsg44Test.class,
    QuoteCancelMsg44Test.class,
    QuoteRequestMsg44Test.class,
    QuoteRequestRejectMsg44Test.class,
    QuoteMsg44Test.class,
    QuoteResponseMsg44Test.class,
    QuoteStatusReportMsg44Test.class,
    QuoteStatusRequestMsg44Test.class,
    RFQRequestMsg44Test.class,
    RegistrationInstructionsMsg44Test.class,
    RegistrationInstructionsResponseMsg44Test.class,
    RequestForPositionsAckMsg44Test.class,
    RequestForPositionsMsg44Test.class,
    SecurityDefinitionMsg44Test.class,
    SecurityDefinitionRequestMsg44Test.class,
    SecurityListMsg44Test.class,
    SecurityListRequestMsg44Test.class,
    SecurityStatusMsg44Test.class,
    SecurityStatusRequestMsg44Test.class,
    SecurityTypeRequestMsg44Test.class,
    SecurityTypesMsg44Test.class,
    SettlementInstructionRequestMsg44Test.class,
    SettlementInstructionsMsg44Test.class,
    TradeCaptureReportAckMsg44Test.class,
    TradeCaptureReportMsg44Test.class,
    TradeCaptureReportRequestAckMsg44Test.class,
    TradeCaptureReportRequestMsg44Test.class,
    TradingSessionStatusMsg44Test.class,
    TradingSessionStatusRequestMsg44Test.class
})
public class TestAll {
}
