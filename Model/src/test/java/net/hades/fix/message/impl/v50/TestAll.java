/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.49 2011-10-29 02:54:35 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all the message Test Cases.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.49 $
 * @created 01/11/2008, 6:28:48 PM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    AdvertisementMsg50Test.class,
    AllocationInstructionAckMsg50Test.class,
    AllocationInstructionMsg50Test.class,
    AllocationReportAckMsg50Test.class,
    AllocationReportMsg50Test.class,
    AssignmentReportMsg50Test.class,
    BidRequestMsg50Test.class,
    BidResponseMsg50Test.class,
    CollateralAssignmentMsg50Test.class,
    CollateralInquiryAckMsg50Test.class,
    CollateralInquiryMsg50Test.class,
    CollateralReportMsg50Test.class,
    CollateralRequestMsg50Test.class,
    CollateralResponseMsg50Test.class,
    ConfirmationAckMsg50Test.class,
    ConfirmationMsg50Test.class,
    ConfirmationRequestMsg50Test.class,
    CrossOrderCancelRequestMsg50Test.class,
    CrossOrderModificationRequestMsg50Test.class,
    DerivativeSecurityListMsg50Test.class,
    DerivativeSecurityListRequestMsg50Test.class,
    DontKnowTradeMsg50Test.class,
    EmailMsg50Test.class,
    ExecutionReportMsg50Test.class,
    IOIMsg50Test.class,
    ListCancelRequestMsg50Test.class,
    ListExecuteMsg50Test.class,
    ListStatusMsg50Test.class,
    ListStatusRequestMsg50Test.class,
    ListStrikePriceMsg50Test.class,
    MarketDataIncrRefreshMsg50Test.class,
    MarketDataRequestMsg50Test.class,
    MarketDataRequestRejectMsg50Test.class,
    MarketDataSnapshotMsg50Test.class,
    MassQuoteAckMsg50Test.class,
    MassQuoteMsg50Test.class,
    MultilegModificationRequestMsg50Test.class,
    NewOrderCrossMsg50Test.class,
    NewOrderListMsg50Test.class,
    NewOrderMultilegMsg50Test.class,
    NewOrderSingleMsg50Test.class,
    NewsMsg50Test.class,
    OrderCancelRejectMsg50Test.class,
    OrderCancelRequestMsg50Test.class,
    OrderMassCancelReportMsg50Test.class,
    OrderMassCancelRequestMsg50Test.class,
    OrderMassStatusRequestMsg50Test.class,
    OrderModificationRequestMsg50Test.class,
    OrderStatusRequestMsg50Test.class,
    PositionMaintenanceReportMsg50Test.class,
    PositionMaintenanceRequestMsg50Test.class,
    PositionReportMsg50Test.class,
    QuoteCancelMsg50Test.class,
    QuoteMsg50Test.class,
    QuoteRequestMsg50Test.class,
    QuoteRequestRejectMsg50Test.class,
    QuoteResponseMsg50Test.class,
    QuoteStatusReportMsg50Test.class,
    QuoteStatusRequestMsg50Test.class,
    RFQRequestMsg50Test.class,
    RegistrationInstructionsMsg50Test.class,
    RegistrationInstructionsResponseMsg50Test.class,
    RequestForPositionsAckMsg50Test.class,
    RequestForPositionsMsg50Test.class,
    SecurityDefinitionMsg50Test.class,
    SecurityDefinitionRequestMsg50Test.class,
    SecurityListMsg50Test.class,
    SecurityListRequestMsg50Test.class,
    SecurityStatusMsg50Test.class,
    SecurityStatusRequestMsg50Test.class,
    SecurityTypeRequestMsg50Test.class,
    SecurityTypesMsg50Test.class,
    SettlementInstructionRequestMsg50Test.class,
    SettlementInstructionsMsg50Test.class,
    TradeCaptureReportAckMsg50Test.class,
    TradeCaptureReportMsg50Test.class,
    TradeCaptureReportRequestAckMsg50Test.class,
    TradeCaptureReportRequestMsg50Test.class,
    TradingSessionStatusMsg50Test.class,
    TradingSessionStatusRequestMsg50Test.class
})
public class TestAll {
}
