/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.48 2011-10-29 02:54:36 vrotaru Exp $
 */
package net.hades.fix.message.impl.v50sp2;

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
    AdvertisementMsg50SP2Test.class,
    AllocationInstructionAckMsg50SP2Test.class,
    AllocationInstructionMsg50SP2Test.class,
    AllocationReportAckMsg50SP2Test.class,
    AllocationReportMsg50SP2Test.class,
    AssignmentReportMsg50SP2Test.class,
    BidRequestMsg50SP2Test.class,
    BidResponseMsg50SP2Test.class,
    CollateralAssignmentMsg50SP2Test.class,
    CollateralInquiryAckMsg50SP2Test.class,
    CollateralInquiryMsg50SP2Test.class,
    CollateralReportMsg50SP2Test.class,
    CollateralRequestMsg50SP2Test.class,
    CollateralResponseMsg50SP2Test.class,
    ConfirmationAckMsg50SP2Test.class,
    ConfirmationMsg50SP2Test.class,
    ConfirmationRequestMsg50SP2Test.class,
    CrossOrderCancelRequestMsg50SP2Test.class,
    CrossOrderModificationRequestMsg50SP2Test.class,
    DerivativeSecurityListMsg50SP2Test.class,
    DerivativeSecurityListRequestMsg50SP2Test.class,
    DontKnowTradeMsg50SP2Test.class,
    EmailMsg50SP2Test.class,
    ExecutionReportMsg50SP2Test.class,
    IOIMsg50SP2Test.class,
    ListCancelRequestMsg50SP2Test.class,
    ListExecuteMsg50SP2Test.class,
    ListStatusMsg50SP2Test.class,
    ListStatusRequestMsg50SP2Test.class,
    ListStrikePriceMsg50SP2Test.class,
    MarketDataIncrRefreshMsg50SP2Test.class,
    MarketDataRequestMsg50SP2Test.class,
    MarketDataRequestRejectMsg50SP2Test.class,
    MarketDataSnapshotMsg50SP2Test.class,
    MassQuoteAckMsg50SP2Test.class,
    MassQuoteMsg50SP2Test.class,
    MultilegModificationRequestMsg50SP2Test.class,
    NewOrderCrossMsg50SP2Test.class,
    NewOrderListMsg50SP2Test.class,
    NewOrderMultilegMsg50SP2Test.class,
    NewOrderSingleMsg50SP2Test.class,
    NewsMsg50SP2Test.class,
    OrderCancelRejectMsg50SP2Test.class,
    OrderCancelRequestMsg50SP2Test.class,
    OrderMassCancelReportMsg50SP2Test.class,
    OrderMassCancelRequestMsg50SP2Test.class,
    OrderMassStatusRequestMsg50SP2Test.class,
    OrderModificationRequestMsg50SP2Test.class,
    OrderStatusRequestMsg50SP2Test.class,
    PositionMaintenanceReportMsg50SP2Test.class,
    PositionMaintenanceRequestMsg50SP2Test.class,
    PositionReportMsg50SP2Test.class,
    QuoteCancelMsg50SP2Test.class,
    QuoteMsg50SP2Test.class,
    QuoteRequestMsg50SP2Test.class,
    QuoteRequestRejectMsg50SP2Test.class,
    QuoteResponseMsg50SP2Test.class,
    QuoteStatusReportMsg50SP2Test.class,
    QuoteStatusRequestMsg50SP2Test.class,
    RFQRequestMsg50SP2Test.class,
    RegistrationInstructionsMsg50SP2Test.class,
    RegistrationInstructionsResponseMsg50SP2Test.class,
    RequestForPositionsAckMsg50SP2Test.class,
    RequestForPositionsMsg50SP2Test.class,
    SecurityDefinitionMsg50SP2Test.class,
    SecurityDefinitionRequestMsg50SP2Test.class,
    SecurityListMsg50SP2Test.class,
    SecurityListRequestMsg50SP2Test.class,
    SecurityStatusMsg50SP2Test.class,
    SecurityStatusRequestMsg50SP2Test.class,
    SecurityTypeRequestMsg50SP2Test.class,
    SecurityTypesMsg50SP2Test.class,
    SettlementInstructionRequestMsg50SP2Test.class,
    SettlementInstructionsMsg50SP2Test.class,
    TradeCaptureReportAckMsg50SP2Test.class,
    TradeCaptureReportMsg50SP2Test.class,
    TradeCaptureReportRequestAckMsg50SP2Test.class,
    TradeCaptureReportRequestMsg50SP2Test.class,
    TradingSessionStatusMsg50SP2Test.class,
    TradingSessionStatusRequestMsg50SP2Test.class
})
public class TestAll {
}
