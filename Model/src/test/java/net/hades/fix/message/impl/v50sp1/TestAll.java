/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.48 2011-10-29 02:54:35 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp1;

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
    AdvertisementMsg50SP1Test.class,
    AllocationInstructionAckMsg50SP1Test.class,
    AllocationInstructionMsg50SP1Test.class,
    AllocationReportAckMsg50SP1Test.class,
    AllocationReportMsg50SP1Test.class,
    AssignmentReportMsg50SP1Test.class,
    BidRequestMsg50SP1Test.class,
    BidResponseMsg50SP1Test.class,
    CollateralAssignmentMsg50SP1Test.class,
    CollateralInquiryAckMsg50SP1Test.class,
    CollateralInquiryMsg50SP1Test.class,
    CollateralReportMsg50SP1Test.class,
    CollateralRequestMsg50SP1Test.class,
    CollateralResponseMsg50SP1Test.class,
    ConfirmationAckMsg50SP1Test.class,
    ConfirmationMsg50SP1Test.class,
    ConfirmationRequestMsg50SP1Test.class,
    CrossOrderCancelRequestMsg50SP1Test.class,
    CrossOrderModificationRequestMsg50SP1Test.class,
    DerivativeSecurityListMsg50SP1Test.class,
    DerivativeSecurityListRequestMsg50SP1Test.class,
    DontKnowTradeMsg50SP1Test.class,
    EmailMsg50SP1Test.class,
    ExecutionReportMsg50SP1Test.class,
    IOIMsg50SP1Test.class,
    ListCancelRequestMsg50SP1Test.class,
    ListExecuteMsg50SP1Test.class,
    ListStatusMsg50SP1Test.class,
    ListStatusRequestMsg50SP1Test.class,
    ListStrikePriceMsg50SP1Test.class,
    MarketDataIncrRefreshMsg50SP1Test.class,
    MarketDataRequestMsg50SP1Test.class,
    MarketDataRequestRejectMsg50SP1Test.class,
    MarketDataSnapshotMsg50SP1Test.class,
    MassQuoteAckMsg50SP1Test.class,
    MassQuoteMsg50SP1Test.class,
    MultilegModificationRequestMsg50SP1Test.class,
    NewOrderCrossMsg50SP1Test.class,
    NewOrderListMsg50SP1Test.class,
    NewOrderMultilegMsg50SP1Test.class,
    NewOrderSingleMsg50SP1Test.class,
    NewsMsg50SP1Test.class,
    OrderCancelRejectMsg50SP1Test.class,
    OrderCancelRequestMsg50SP1Test.class,
    OrderMassCancelReportMsg50SP1Test.class,
    OrderMassCancelRequestMsg50SP1Test.class,
    OrderMassStatusRequestMsg50SP1Test.class,
    OrderModificationRequestMsg50SP1Test.class,
    OrderStatusRequestMsg50SP1Test.class,
    PositionMaintenanceReportMsg50SP1Test.class,
    PositionMaintenanceRequestMsg50SP1Test.class,
    PositionReportMsg50SP1Test.class,
    QuoteCancelMsg50SP1Test.class,
    QuoteMsg50SP1Test.class,
    QuoteRequestMsg50SP1Test.class,
    QuoteRequestRejectMsg50SP1Test.class,
    QuoteResponseMsg50SP1Test.class,
    QuoteStatusReportMsg50SP1Test.class,
    QuoteStatusRequestMsg50SP1Test.class,
    RFQRequestMsg50SP1Test.class,
    RegistrationInstructionsMsg50SP1Test.class,
    RegistrationInstructionsResponseMsg50SP1Test.class,
    RequestForPositionsAckMsg50SP1Test.class,
    RequestForPositionsMsg50SP1Test.class,
    SecurityDefinitionMsg50SP1Test.class,
    SecurityDefinitionRequestMsg50SP1Test.class,
    SecurityListMsg50SP1Test.class,
    SecurityListRequestMsg50SP1Test.class,
    SecurityStatusMsg50SP1Test.class,
    SecurityStatusRequestMsg50SP1Test.class,
    SecurityTypeRequestMsg50SP1Test.class,
    SecurityTypesMsg50SP1Test.class,
    SettlementInstructionRequestMsg50SP1Test.class,
    SettlementInstructionsMsg50SP1Test.class,
    TradeCaptureReportAckMsg50SP1Test.class,
    TradeCaptureReportMsg50SP1Test.class,
    TradeCaptureReportRequestAckMsg50SP1Test.class,
    TradeCaptureReportRequestMsg50SP1Test.class,
    TradingSessionStatusMsg50SP1Test.class,
    TradingSessionStatusRequestMsg50SP1Test.class
})
public class TestAll {
}
