/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.48 2011-10-29 02:54:35 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all the message Test Cases.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.48 $
 * @created 01/11/2008, 6:28:48 PM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AdvertisementMsg43Test.class,
    AllocationInstructionAckMsg43Test.class,
    AllocationInstructionMsg43Test.class,
    AllocationReportAckMsg43Test.class,
    AllocationReportMsg43Test.class,
    AssignmentReportMsg43Test.class,
    BidRequestMsg43Test.class,
    BidResponseMsg43Test.class,
    CollateralAssignmentMsg43Test.class,
    CollateralInquiryAckMsg43Test.class,
    CollateralInquiryMsg43Test.class,
    CollateralReportMsg43Test.class,
    CollateralRequestMsg43Test.class,
    CollateralResponseMsg43Test.class,
    ConfirmationAckMsg43Test.class,
    ConfirmationMsg43Test.class,
    ConfirmationRequestMsg43Test.class,
    CrossOrderCancelRequestMsg43Test.class,
    CrossOrderModificationRequestMsg43Test.class,
    DerivativeSecurityListMsg43Test.class,
    DerivativeSecurityListRequestMsg43Test.class,
    DontKnowTradeMsg43Test.class,
    EmailMsg43Test.class,
    ExecutionReportMsg43Test.class,
    IOIMsg43Test.class,
    ListCancelRequestMsg43Test.class,
    ListExecuteMsg43Test.class,
    ListStatusMsg43Test.class,
    ListStatusRequestMsg43Test.class,
    ListStrikePriceMsg43Test.class,
    MarketDataIncrRefreshMsg43Test.class,
    MarketDataRequestMsg43Test.class,
    MarketDataRequestRejectMsg43Test.class,
    MarketDataSnapshotMsg43Test.class,
    MassQuoteMsg43Test.class,
    MultilegModificationRequestMsg43Test.class,
    NewOrderCrossMsg43Test.class,
    NewOrderListMsg43Test.class,
    NewOrderMultilegMsg43Test.class,
    NewOrderSingleMsg43Test.class,
    NewsMsg43Test.class,
    OrderCancelRejectMsg43Test.class,
    OrderCancelRequestMsg43Test.class,
    OrderMassCancelReportMsg43Test.class,
    OrderMassCancelRequestMsg43Test.class,
    OrderMassStatusRequestMsg43Test.class,
    OrderModificationRequestMsg43Test.class,
    OrderStatusRequestMsg43Test.class,
    PositionMaintenanceReportMsg43Test.class,
    PositionMaintenanceRequestMsg43Test.class,
    PositionReportMsg43Test.class,
    QuoteCancelMsg43Test.class,
    QuoteMsg43Test.class,
    QuoteRequestMsg43Test.class,
    QuoteRequestRejectMsg43Test.class,
    QuoteResponseMsg43Test.class,
    QuoteStatusReportMsg43Test.class,
    QuoteStatusRequestMsg43Test.class,
    RFQRequestMsg43Test.class,
    RegistrationInstructionsMsg43Test.class,
    RegistrationInstructionsResponseMsg43Test.class,
    RequestForPositionsAckMsg43Test.class,
    RequestForPositionsMsg43Test.class,
    SecurityDefinitionMsg43Test.class,
    SecurityDefinitionRequestMsg43Test.class,
    SecurityListMsg43Test.class,
    SecurityListRequestMsg43Test.class,
    SecurityStatusMsg43Test.class,
    SecurityStatusRequestMsg43Test.class,
    SecurityTypeRequestMsg43Test.class,
    SecurityTypesMsg43Test.class,
    SettlementInstructionRequestMsg43Test.class,
    SettlementInstructionsMsg43Test.class,
    TradeCaptureReportAckMsg43Test.class,
    TradeCaptureReportMsg43Test.class,
    TradeCaptureReportRequestAckMsg43Test.class,
    TradeCaptureReportRequestMsg43Test.class,
    TradingSessionStatusMsg43Test.class,
    TradingSessionStatusRequestMsg43Test.class
})
public class TestAll {
}
