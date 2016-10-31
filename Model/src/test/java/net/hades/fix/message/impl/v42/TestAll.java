/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.48 2011-10-29 02:54:35 vrotaru Exp $
 */
package net.hades.fix.message.impl.v42;

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
    AdvertisementMsg42Test.class,
    AllocationInstructionAckMsg42Test.class,
    AllocationInstructionMsg42Test.class,
    AllocationReportAckMsg42Test.class,
    AllocationReportMsg42Test.class,
    AssignmentReportMsg42Test.class,
    BidRequestMsg42Test.class,
    BidResponseMsg42Test.class,
    CollateralAssignmentMsg42Test.class,
    CollateralInquiryAckMsg42Test.class,
    CollateralInquiryMsg42Test.class,
    CollateralReportMsg42Test.class,
    CollateralRequestMsg42Test.class,
    CollateralResponseMsg42Test.class,
    ConfirmationAckMsg42Test.class,
    ConfirmationMsg42Test.class,
    ConfirmationRequestMsg42Test.class,
    CrossOrderCancelRequestMsg42Test.class,
    CrossOrderModificationRequestMsg42Test.class,
    DerivativeSecurityListMsg42Test.class,
    DerivativeSecurityListRequestMsg42Test.class,
    DontKnowTradeMsg42Test.class,
    EmailMsg42Test.class,
    ExecutionReportMsg42Test.class,
    IOIMsg42Test.class,
    ListCancelRequestMsg42Test.class,
    ListExecuteMsg42Test.class,
    ListStatusMsg42Test.class,
    ListStatusRequestMsg42Test.class,
    ListStrikePriceMsg42Test.class,
    MarketDataIncrRefreshMsg42Test.class,
    MarketDataRequestMsg42Test.class,
    MarketDataRequestRejectMsg42Test.class,
    MarketDataSnapshotMsg42Test.class,
    MassQuoteAckMsg42Test.class,
    MassQuoteMsg42Test.class,
    MultilegModificationRequestMsg42Test.class,
    NewOrderCrossMsg42Test.class,
    NewOrderListMsg42Test.class,
    NewOrderMultilegMsg42Test.class,
    NewOrderSingleMsg42Test.class,
    NewsMsg42Test.class,
    OrderCancelRejectMsg42Test.class,
    OrderCancelRequestMsg42Test.class,
    OrderMassCancelReportMsg42Test.class,
    OrderMassCancelRequestMsg42Test.class,
    OrderMassStatusRequestMsg42Test.class,
    OrderModificationRequestMsg42Test.class,
    OrderStatusRequestMsg42Test.class,
    PositionMaintenanceReportMsg42Test.class,
    PositionMaintenanceRequestMsg42Test.class,
    PositionReportMsg42Test.class,
    QuoteCancelMsg42Test.class,
    QuoteMsg42Test.class,
    QuoteRequestMsg42Test.class,
    QuoteRequestRejectMsg42Test.class,
    QuoteResponseMsg42Test.class,
    QuoteStatusReportMsg42Test.class,
    QuoteStatusRequestMsg42Test.class,
    RFQRequestMsg42Test.class,
    RegistrationInstructionsMsg42Test.class,
    RegistrationInstructionsResponseMsg42Test.class,
    RequestForPositionsAckMsg42Test.class,
    RequestForPositionsMsg42Test.class,
    SecurityDefinitionMsg42Test.class,
    SecurityDefinitionRequestMsg42Test.class,
    SecurityListMsg42Test.class,
    SecurityListRequestMsg42Test.class,
    SecurityStatusMsg42Test.class,
    SecurityStatusRequestMsg42Test.class,
    SecurityTypeRequestMsg42Test.class,
    SecurityTypesMsg42Test.class,
    SettlementInstructionRequestMsg42Test.class,
    SettlementInstructionsMsg42Test.class,
    TradeCaptureReportAckMsg42Test.class,
    TradeCaptureReportMsg42Test.class,
    TradeCaptureReportRequestAckMsg42Test.class,
    TradeCaptureReportRequestMsg42Test.class,
    TradingSessionStatusMsg42Test.class,
    TradingSessionStatusRequestMsg42Test.class
})
public class TestAll {
}
