/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TagNum.java
 *
 * $Id: TagNum.java,v 1.49 2011-10-21 10:30:59 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.IOIMsg;
import net.hades.fix.message.MarketDataRequestMsg;
import net.hades.fix.message.MarketDataSnapshotMsg;
import net.hades.fix.message.MassQuoteAckMsg;
import net.hades.fix.message.QuoteMsg;
import net.hades.fix.message.QuoteStatusReportMsg;
import net.hades.fix.message.RFQRequestMsg;
import net.hades.fix.message.RejectMsg;
import net.hades.fix.message.ResendRequestMsg;
import net.hades.fix.message.TestRequestMsg;
import net.hades.fix.message.comp.ApplicationSequenceControl;
import net.hades.fix.message.comp.LegBenchmarkCurveData;
import net.hades.fix.message.comp.LegStipulations;
import net.hades.fix.message.comp.NestedParties;
import net.hades.fix.message.comp.OrderQtyData;
import net.hades.fix.message.comp.SecurityXML;
import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.group.AltMDSourceGroup;
import net.hades.fix.message.group.IOIQualifierGroup;
import net.hades.fix.message.group.LegIOIGroup;
import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.group.RootPartyGroup;
import net.hades.fix.message.group.RootPartySubGroup;
import net.hades.fix.message.group.RoutingIDGroup;

/**
 * <p>Extracted from FIX protocol documentation <a href="http://www.fixprotocol.org/">FIX Protocol</a></p>
 * 
 * FIX tags.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.49 $
 * @created 25/06/2008, 20:29:16
 */
public enum TagNum {
    
    /**
     * Account mnemonic as agreed between buy and sell sides, e.g. broker and
     * institution or investor / intermediary and fund manager.<br/>
     * IB - Needed for sessions which route orders to multiple IB accounts.
     * The intended IB account number is used by default, although mapping
     * can be implemented to replace the IB account designation with a
     * unique user-specified value.<br/>
     * Type: String<br/>
     * Used: {@link QuoteMsg}
     */
    Account                                             (1),
    /**
     * Unique identifier of advertisement message.<br/>
     * (Prior to FIX 4.1 this field was of type int)<br/>
     * Type: String<br/>
     * Used: Advertisment
     */
    AdvID                                               (2),
    /**
     * Reference identifier used with CANCEL and REPLACE transaction types.<br/>
     * (Prior to FIX 4.1 this field was of type int)<br/>
     * Type: String<br/>
     * Used: Advertisment
     */
    AdvRefID                                            (3),
    /**
     * Broker's side of advertised trade Valid values:<br/>
     * B - Buy<br/>
     * S - Sell<br/>
     * T - Trade<br/>
     * X - Cross<br/>
     * Type: String(AdvSide)<br/>
     * Used: Advertisment
     */
    AdvSide                                             (4),
    /**
     * Identifies advertisement message transaction type.<br/>
     * Valid values: N - New C - Cancel R - Replace.<br/>
     * Type: String(AdvTransType)<br/>
     * Used: Advertisment
     */
    AdvTransType                                        (5),
    /**
     * Calculated average price of all fills on this order.<br/>
     * Type: Float<br/>
     * Used: Execution Report
     */
    AvgPx                                               (6),
    /**
     * Message sequence number of first message in range to be resent.<br/>
     * Type: Int<br/>
     * Used: Resend Request
     */
    BeginSeqNo                                          (7),
    /**
     * Identifies beginning of new message and protocol version. ALWAYS FIRST FIELD
     * IN MESSAGE. (Always unencrypted) Valid values: FIX 4.2.<br/>
     * Type: String<br/>
     * Used: Standard Header
     */
    BeginString                                         (8),
    /**
     * Message length, in bytes, forward to the CheckSum field. ALWAYS SECOND
     * FIELD IN MESSAGE. (Always unencrypted).<br/>
     * IB - Message length must be 1 to 4 characters long<br/>
     * Type: Int<br/>
     * Used: Standard Header
     */
    BodyLength                                          (9),
    /**
     * Three byte, simple checksum (See Appendix B of 4.2 manual for description).
     * ALWAYS LAST FIELD IN MESSAGE; i.e. serves, with the trailing <SOH>, as the
     * end-of-message delimiter. Always defined as three characters. (Always unencrypted)<br/>
     * Type: String<br/>
     * Used: Standard Trailer
     */
    CheckSum                                            (10),
    /**
     * Unique identifier for Order as assigned by institution. Uniqueness must be
     * guaranteed within a single trading day. Firms which electronically submit multiday
     * orders should consider embedding a date within the ClOrdID field to assure
     * uniqueness across days.<br/>
     * Type: String<br/>
     * Used: New Order-Single, Execution Report, Cancel, Cancel Replace, Cancel Reject
     */
    ClOrdID                                             (11),
    /**
     * Commission. Note if {@link TagNum#CommType} (13) is percentage, Commission of 5% should
     * be represented as 0.05.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    Commission                                          (12),
    /**
     * Commission type.<br/>
     * Type: Char (see {@link CommType})<br/>
     * Used: {@link QuoteMsg}
     */
    CommType                                            (13),
    /**
     * Total number of shares filled. (Prior to FIX 4.2 this field was of type int).<br/>
     * Type: Int<br/>
     * Used: Execution Report
     */
    CumQty                                              (14),
    /**
     * Identifies currency used for price. Absence of this field is interpreted as the default for
     * the security. It is recommended that systems provide the currency value
     * whenever possible. See Fix 4.2 Manual Appendix A: Valid Currency Codes for
     * information on obtaining valid values.<br/>
     * Type: Currency (see {@link Currency})<br/>
     * Used: {@link IOIMsg},{@link QuoteMsg}
     */
    Currency                                            (15),
    /**
     * Message sequence number of last message in range to be resent. If request
     * is for a single message BeginSeqNo = EndSeqNo. If request is for all messages
     * subsequent to a particular message, EndSeqNo = “0” (representing infinity).<br/>
     * Type: Int<br/>
     * Used: {@link ResendRequestMsg}
     */
    EndSeqNo                                            (16),
    /**
     * Unique identifier of execution message as assigned by broker (will be 0 (zero) for
     * ExecTransType=3 (Status)). Uniqueness must be guaranteed within a single trading
     * day or the life of a multi-day order. Firms which accept multi-day orders should
     * consider embedding a date within the ExecID field to assure uniqueness across
     * days. (Prior to FIX 4.1 this field was of type int)<br/>
     * Type: String<br/>
     * Used: Execution Report
     */
    ExecID                                              (17),
    /**
     * Instructions for order handling on exchange trading floor. If more than one
     * instruction is applicable to an order, this field can contain multiple instructions
     * separated by space.<br/>
     * Type: String (ExecInst)<br/>
     * Used: New Order-Single
     */
    ExecInst                                            (18),
    /**
     * Reference identifier used with Cancel and Correct transaction types. (Prior to FIX 4.1
     * this field was of type int)<br/>
     * Type: String<br/>
     * Used: Execution Report
     */
    ExecRefID                                           (19),
    /**
     * Identifies transaction type.<br/>
     * Type: Char (ExecTransType)<br/>
     * Used: Execution Report
     */
    ExecTransType                                       (20),
    /**
     * Instructions for order handling on Broker trading floor.<br/>
     * Type: Char (HandlInst)<br/>
     * Used: Order, Cancel, Replace
     */
    HandlInst                                           (21),
    /**
     * Identifies class or source of the SecurityID (48) value. Required if SecurityID is specified.
     * 100+ are reserved for private security identifications. Valid values:<br/>
     * 1 - CUSIP<br/>
     * 2 - SEDOL<br/>
     * 3 - QUIK<br/>
     * 4 - ISIN number<br/>
     * 5 - RIC code<br/>
     * 6 - ISO Currency Code<br/>
     * 7 - ISO Country Code<br/>
     * 8 - Exchange Symbol<br/>
     * 9 - Consolidated Tape Association (CTA) Symbol (SIAC CTS/CQS line format)<br/>
     * A - Bloomberg Symbol<br/>
     * C - Dutch<br/>
     * D - Valoren<br/>
     * E - Sicovam<br/>
     * F - Belgian<br/>
     * G - "Common" (Clearstream and Euroclear)<br/>
     * H - Clearing House / Clearing Organization<br/>
     * I - ISDA/FpML Product Specification (XML in EncodedSecurityDesc)<br/>
     * J - Option Price Reporting Authority<br/>
     * K - ISDA/FpML Product URL (URL in SecurityID)<br/>
     * L - Letter of Credit<br/>
     * M - Marketplace-assigned Identifier<br/>
     * Type: String (see {@link SecurityIDSource})<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link QuoteMsg}
     */
    SecurityIDSource                                    (22),
    /**
     * Unique identifier of IOI message.<br/>
     * Type: String<br/>
     * Used: {@link IOIMsg}
     */
    IOIID                                               (23),
    /**
     * Indicates if, and on which other services, the indication has been advertised.
     * Each character represents an additional service (e.g. if on Bridge and Autex,
     * field = BA, if only on Autex, field = A)<br/>
     * Type: Character<br/>
     * Used: {@link IOIMsg}
     */
    IOIOthSvc                                           (24),
    /**
     * Relative quality of indication.<br/>
     * Type: Character (see {@link IOIQltyInd})<br/>
     * Used: {@link IOIMsg}
     */
    IOIQltyInd                                          (25),
    /**
     * IReference identifier used with CANCEL and REPLACE, transaction types.<br/>
     * (Prior to FIX 4.1 this field was of type int)
     * Type: String<br/>
     * Used: {@link IOIMsg}
     */
    IOIRefID                                            (26),
    /**
     * Quantity (e.g. number of shares) in numeric form or relative size.<br/>
     * (Prior to FIX 4.1 this field was of type int)
     * Type: String (see {@link IOIQty})<br/>
     * Used: {@link IOIMsg}
     */
    IOIQty                                              (27),
    /**
     * Identifies IOI message transaction type.<br/>
     * Type: Character (see {@link IOITransType)}<br/>
     * Used: {@link IOIMsg}
     */
    IOITransType                                        (28),
    /**
     * Broker capacity in order execution.<br/>
     * Type: Character (see {@link LastCapacity)}<br/>
     * Used: {@link ExecutionReportMsg}
     */
    LastCapacity                                        (29),
    /**
     * Market of execution for last fill, or an indication of the market where an order was routed.<br/>
     * Type: String, Exchange<br/>
     * Used: {@link AdvertismentMsg}
     */
    LastMkt                                             (30),
    /**
     * Price of this (last) fill. Field not required for ExecTransType = 3 (Status).<br/>
     * Type: Float<br/>
     * Used: Execution Report
     */
    LastPx                                              (31),
    /**
     * Quantity of shares bought/sold on this (last) fill. Field not required for
     * ExecTransType = 3 (Status).<br/>
     * Type: Qty<br/>
     * Used: Execution Report,{@link net.hades.fix.message.NewsMsg}
     */
    LastQty                                             (32),
    /**
     * Identifies number of lines of text body.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.NewsMsg}
     */
    NoLinesOfText                                       (33),
    /**
     * Integer message sequence number.<br/>
     * Type: Int<br/>
     * Used: Standard Header
     */
    MsgSeqNum                                           (34),
    /**
     * Defines message type. ALWAYS THIRD FIELD IN MESSAGE. (Always unencrypted)
     * Note: A "U" as the first character in the MsgType field (i.e. U1, U2, etc) indicates
     * that the message format is privately defined between the sender and receiver.<br/>
     * IB - The only supported value for IB is: 0,1,2,3,4,5,8,9,A,D,F,G,H,B,AB,AC<br/>
     * Type: Int (MsgType)<br/>
     * Used: Standard Header
     */
    MsgType                                             (35),
    /**
     * New sequence number.<br/>
     * Type: Int<br/>
     * Used: Sequence Reset
     */
    NewSeqNo                                            (36),
    /**
     * Unique identifier for Order as assigned bybroker. Uniqueness must be guaranteed
     * within a single trading day. Firms which accept multi-day orders should consider
     * embedding a date within the OrderID field to assure uniqueness across days.<br/>
     * IB - For combo orders, an additional value will be added to the end of
     * the order ID to identify the leg of the execution in the event of a
     * partial execution on a BEST routed combo order<br/>
     * Type: String<br/>
     * Used: Execution Report, Cancel/Replace Request, Order Cancel Reject
     */
    OrderID                                             (37),
    /**
     * Number of shares ordered.<br/>
     * Type: Qty<br/>
     * Used: {@link OrderQtyData}
     */
    OrderQty                                            (38),
    /**
     * Identifies current status of order.<br/>
     * IB Supports the following values: 0,1,2,4,5,6,8,A,C,E<br/>
     * Type: Char<br/>
     * Used: Execution Report
     */
    OrdStatus                                           (39),
    /**
     * Order type.<br/>
     * Type: Char<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    OrdType                                             (40),
    /**
     * ClOrdID of the previous order (NOT the initial order of the day) as assigned by the
     * institution, used to identify the previous order in cancel and cancel/replace
     * requests.<br/>
     * Type: String<br/>
     * Used: Cancel/replace Request, Order Cancel Request, Order Cancel Reject
     */
    OrigClOrdID                                          (41),
    /**
     * Time of message origination (always expressed in UTC (Universal Time Coordinated, also known as "GMT)).<br/>
     * Type: UTC Timestamp<br/>
     * Used: {@link net.hades.fix.message.NewsMsg}
     */
    OrigTime                                            (42),
    /**
     * Indicates possible retransmission of message with this sequence number.<br/>
     * Type: Boolean<br/>
     * Used: Standard Header
     */
    PossDupFlag                                         (43),
    /**
     * Price per share.<br/>
     * Type: Price<br/>
     * Used: {@link AdvertismentMsg}
     */
    Price                                               (44),
    /**
     * Reference message sequence number. MsgSeqNum of rejected message.<br/>
     * Type: Int<br/>
     * Used: {@link RejectMsg}
     */
    RefSeqNo                                            (45),
    /**
     * Related symbol.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.NewsMsg}
     */
    RelatdSym                                           (46),
    /**
     * Note that the name of this field is changing to “OrderCapacity” as Rule80A
     * is a very US market-specific term. Other world markets need to convey similar
     * information, however, often a subset of the US values..<br/>
     * IB Supports the following values: A,J,K,I,P,U,Y,M,N,W<br/>
     * Type: Char (Rule80A)<br/>
     * Used: New Order Single, Execution Reports, Cancel/Replace Request
     */
    Rule80A                                             (47),
    /**
     * Security identifier value of SecurityIDSource (22) type (e.g. CUSIP, SEDOL, ISIN, etc). 
     * Requires SecurityIDSource.<br/>
     * IB: Only CUSIP or ISIN are supported.<br/>
     * Type: String<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link QuoteMsg}
     */
    SecurityID                                          (48),
    /**
     * Assigned value used to identify firm sending message.<br/>
     * Type: String<br/>
     * Used: Standard Header
     */
    SenderCompID                                        (49),
    /**
     * Assigned value used to identify specific message originator (desk, trader, etc.)<br/>
     * Type: String<br/>
     * Used: Standard Header
     */
    SenderSubID                                         (50),
    /**
     * Seding date<br/>
     * Type: LocalMktDate<br/>
     * Used: Standard Header
     */
    SendingDate                                         (51),
    /**
     * Time of message transmission (always expressed in UTC (Universal Time Coordinated, also known as “GMT”).<br/>
     * Type: UTC time stamp<br/>
     * Used: Standard Header
     */
    SendingTime                                         (52),
    /**
     * Overall/total quantity (e.g. number of shares).<br/>
     * (Prior to FIX 4.2 this field was of type int)<br/>
     * Type: Qty<br/>
     * Used: Advertisment
     */
    Quantity                                            (53),
    /**
     * Side of order.<br/>
     * Type: Char<br/>
     * Used: {@link IOIMsg}
     */
    Side                                                (54),
    /**
     * Ticker Symbol.<br/>
     * Type: String<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link QuoteMsg}
     */
    Symbol                                              (55),
    /**
     * Assigned value used to identify receiving firm.<br/>
     * IB: Default is “IB” If required the value can be determined by the client, please
     * notify FIX Intergration Group.<br/>
     * Type: String<br/>
     * Used: Standard Header
     */
    TargetCompID                                        (56),
    /**
     * Assigned value used to identify specific individual or unit intended to receive
     * message. “ADMIN” reserved for administrative messages not intended for
     * a specific user.<br/>
     * Type: String<br/>
     * Used: Standard Header
     */
    TargetSubID                                         (57),
    /**
     * Free format text string (Note: this field does not have a specified maximum length).<br/>
     * Type: String<br/>
     * Used: {@link RejectMsg},{@link net.hades.fix.message.SequenceResetMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link QuoteMsg}
     */
    Text                                                (58),
    /**
     * Specifies how long the order remains in effect. Absence of this field is interpreted as DAY.<br/>
     * IB accepted values: 0,1,2,3,4,5,6,7.<br/>
     * Type: Char<br/>
     * Used: New Order-Single, Execution Report, Cancel/Replace Request
     */
    TimeInForce                                         (59),
    /**
     * Time of execution/order creation (expressed in UTC (Universal Time Coordinated, also known as “GMT”).<br/>
     * Type: UTC time stamp<br/>
     * Used: {@link IOIMsg}
     */
    TransactTime                                        (60),
    /**
     * Urgency of message.<br/>
     * IB : Used in bulletin messages.<br/>
     * Type: Char<br/>
     * Used: {@link net.hades.fix.message.NewsMsg},{@link QuoteMsg}
     */
    Urgency                                             (61),
    /**
     * Indicates expiration time of indication message (always expressed in UTC
     * (Universal Time Coordinated, also known as "GMT).<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link IOIMsg}
     */
    ValidUntilTime                                      (62),
    /**
     * Indicates order settlement period. If present, {@link TagNum#SettlDate} (64) overrides
     * this field. If both {@link TagNum#SettlType} (63) and {@link TagNum#SettDate} (64) are
     * omitted, the default for {@link TagNum#SettlType} (63) is 0 (Regular) Regular is defined
     * as the default settlement period for the particular security on the exchange of execution.<br/>
     * In Fixed Income the contents of this field may influence the instrument definition if the
     * {@link TagNum#SecurityID} (48) is ambiguous. In the US an active Treasury offering may be
     * re-opened, and for a time one <code>CUSIP</code> will apply to both the current and "when-issued"
     * securities. Supplying a value of "7" clarifies the instrument description; any other value
     * or the absence of this field should cause the respondent to default to the active issue.<br/>
     * Additionally the following patterns may be uses as well as enum values Dx = FX tenor expression
     * for "days", e.g. "D5", where "x" is any integer > 0 Mx = FX tenor expression for "months",
     * e.g. "M3", where "x" is any integer > 0 Wx = FX tenor expression for "weeks", e.g. "W13",
     * where "x" is any integer > 0 Yx = FX tenor expression for "years", e.g. "Y1",
     * where "x" is any integer > 0 Noted that for FX the tenors expressed using Dx, Mx,
     * Wx, and Yx values do not denote business days, but calendar days.<br/>
     * Valid values: <br/>
     * 0 - Regular / FX Spot settlement (T+1 or T+2 depending on currency) <br/>
     * 1 - Cash (TOD / T+0) <br/>
     * 2 - Next Day (TOM / T+1) <br/>
     * 3 - T+2 <br/>
     * 4 - T+3 <br/>
     * 5 - T+4 <br/>
     * 6 - Future <br/>
     * 7 - When And If Issued <br/>
     * 8 - Sellers Option <br/>
     * 9 - T+5 <br/>
     * B - Broken date - for FX expressing non-standard tenor, {@link TagNum#SettlDate} (64) must be specified <br/>
     * C - FX Spot Next settlement (Spot+1, aka next day) <br/>
     * or any value conforming to the data type Tenor<br/>
     * Type: String<br/>
     * Used: {@link QuoteRelatedSymbolGroup},{@link QuoteMsg}
     */
    SettlType                                           (63),
    /**
     * Specific date of trade settlement (SettlementDate) in YYYYMMDD format.
     * If present, this field overrides {@link TagNum#SettlType} (63). This field is required if the value of
     * {@link TagNum#SettlType} (63) is 6 (Future) or 8 (Sellers Option).<br/>
     * This field must be omitted if the value of {@link TagNum#SettlType} (63) is 7 (When and If Issued)<br/>
     * (expressed in local time at place of settlement).<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    SettlDate                                           (64),
    /**
     * Additional information about the security (e.g. preferred, warrants, etc.).<br/>
     * Note also see {@link TagNum#SecurityType} (167). As defined in the NYSE Stock and bond Symbol Directory
     * and in the AMEX Fitch Directory. Valid values:<br/>
     * For Fixed Income<br/>
     * CD - EUCP with lump-sum interest rather than discount price<br/>
     * WI - "When Issued" for a security to be reissued under an old CUSIP or ISIN
     * Type: String<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link QuoteMsg}
     */
    SymbolSfx                                           (65),
    /**
     * Unique identifier for list as assigned by institution, used to associate multiple individual orders.
     * Uniqueness must be guaranteed within a single trading day. Firms which generate multi-day orders
     * should consider embedding a date within the ListID field to assure uniqueness across days.<br/>
     * Type: String<br/>
     * Used: {@link OrderCancelRejectMsg},{@link NewOrderListMsg},{@link OrderCancelRequestMsg},
     * {@link OrderCancelReplaceRequestMsg}
     */
    ListID                                              (66),
    /**
     * Sequence of individual order within list (i.e. ListSeqNo of {@link TagNum#TotNoOrders} (68),
     * 2 of 25, 3 of 25, . . . )<br/>
     * Type: Integer<br/>
     * Used: {@link ListOrdGrp}
     */
    ListSeqNo                                           (67),
    /**
     * Total number of list order entries across all messages. Should be the sum of all
     * {@link TagNum#NoOrders} (73) in each message that has repeating list order entries related to the
     * same {@link TagNum#ListID} (66). Used to support fragmentation.
     * (Prior to FIX 4.2 this field was named "ListNoOrds).<br/>
     * Type: Integer<br/>
     * Used: {@link NewOrderListMsg}, {@link ListStatusMsg}
     */
    TotNoOrders                                         (68),
    /**
     * Free format text message containing list handling and execution instructions.<br/>
     * Type: String<br/>
     * Used: {@link NewOrderList}
     */
    ListExecInst                                        (69),
    /**
     * Unique identifier for allocation message. (Prior to FIX 4.1 this field was of type int)<br/>
     * Type: String<br/>
     * Used: {@link NewOrderSingleMsg}, {@link OrderCancelReplaceRequestMsg}, {@link AllocationInstruction Msg}, {@link ListOrdGrpMsg},
     * {@link SideCrossOrdModGrp}, {@link TrdCapRptSideGrpMsg}, {@link TrdCapRptAckSideGrpMsg}, {@link AllocationInstructionAckMsg},
     * {@link NewOrderMultilegMsg}, {@link MultilegOrderCancelReplaceMsg}, {@link ConfirmationMsg},
     * {@link AllocationReportMsg}, {@link AllocationReportAckMsg}, {@link ExecutionReportMsg},  {@link ConfirmationRequestMsg},
     * {@link AllocationInstructionAlertMsg}
     */
    AllocID                                             (70),
    /**
     * Identifies allocation transaction type.<br/>
     * Type: Character (see {@link AllocTransType})<br/>
     * Used: {@link AllocationInstructionMsg}, {@link AllocationReportMsg}, {@link AllocationReportAckMsg},
     * {@link AllocationInstructionAlertMsg}
     */
    AllocTransType                                      (71),
    /**
     * Reference identifier to be used with AllocTransType (71) = Replace or Cancel.
     * (Prior to FIX 4.1 this field was of type int).<br/>
     * Type: String<br/>
     * Used: {@link AllocationInstructionMsg}, {@link AllocationReportMsg}, {@link AllocationInstructionAlertMsg}
     */
    RefAllocID                                          (72),
    /**
     * Indicates number of orders to be combined for average pricing and allocation..
     * (Prior to FIX 4.1 this field was of type int).<br/>
     * Type: Integer<br/>
     * Used: {@link ListOrdGrp}, {@link OrdAllocGrp}, {@link OrdListStatGrp}
     */
    NoOrders                                            (73),
    /**
     * Indicates number of decimal places to be used for average pricing.
     * Absence of this field indicates that default precision arranged by the broker/institution is to be used.<br/>
     * Type: Integer<br/>
     * Used: {@link AllocationInstructionMsg}, {@link ConfirmationMsg}, {@link AllocationReportMsg},
     * {@link AllocationInstructionAlertMsg}
     */
    AvgPxPrecision                                      (74),
    /**
     * Indicates date of trade referenced in this message in YYYYMMDD format. 
     * Absence of this field indicates current day (expressed in local time at place of trade).
     * Type: LocalMktDate<br/>
     * Used: {@link AdvertismentMsg}
     */
    TradeDate                                           (75),
    /**
     * Identifies executing / give-up broker. Standard NASD market-maker mnemonic is preferred.<br/>
     * Type: String<br/>
     * Used:
     */
    ExecBroker                                          (76),
    /**
     * Indicates whether the resulting position after a trade should be an opening
     * position or closing position. Used for omnibus accounting - where accounts are
     * held on a gross basis instead of being netted together.<br/>
     * IB supports the following value: O,C.<br/>
     * Type: Char<br/>
     * Used: {@link NewOrderSingleMsg}
     */
    PositionEffect                                      (77),
    /**
     * Number of repeating {@link TagNum#AllocAccount} (79)/ {@link TagNum#AllocPrice} (366) entries.<br/>
     * Type: Integer<br/>
     * Used: {@link AllocAckGrp}, {@link AllocGrp}, {@link PreAllocGrp}, {@link PreAllocMlegGrp}, {@link TrdAllocGrp}
     */
    NoAllocs                                            (78),
    /**
     * Sub-account mnemonic.<br/>
     * Type: String<br/>
     * Used: {@link AllocAckGrp}, {@link AllocGrp}, {@link PreAllocGrp}, {@link PreAllocMlegGrp}, {@link TrdAllocGrp},
     * {@link ConfirmationMsg}, {@link SettlementInstructionRequestMsg}, {@link ConfirmationRequestMsg}
     */
    AllocAccount                                        (79),
    /**
     * Quantity to be allocated to specific sub-account. (Prior to FIX 4.2 this field was of type int)<br/>
     * Type: Double<br/>
     * Used: {@link AllocAckGrp}, {@link AllocGrp}, {@link PreAllocGrp}, {@link PreAllocMlegGrp}, {@link TrdAllocGrp},
     * {@link ConfirmationMsg}
     */
    AllocQty                                            (80),
    /**
     * Processing code for sub-account. Absence of this field in {@link TagNum#AllocAccount} (79) /
     * {@link TagNum#AllocPrice} (366) / {@link TagNum#AllocQty} (80) / {@link TagNum#ProcessCode}
     * instance indicates regular trade.<br/>
     * Type: Character (see {@link ProcessCode})<br/>
     * Used: {@link NewOrderSingleMsg}, {@link AllocGrp}, {@link ListOrdGrp}, {@link TrdCapRptSideGrp}, {@link TrdCapRptAckSideGrp},
     * {@link NewOrderCrossMsg}, {@link CrossOrderCancelReplaceRequestMsg}, {@link NewOrderMultilegMsg},
     * {@link MultilegOrderCancelReplaceMsg}, {@link ConfirmationMsg}
     */
    ProcessCode                                         (81),
    /**
     * Total number of reports within series.<br/>
     * Type: Integer<br/>
     * Used: {@link ListStatusMsg}
     */
    NoRpts                                              (82),
    /**
     * Sequence number of message within report series. Used to carry reporting sequence number of the fill
     * as represented on the Trade Report Side.<br/>
     * Type: Integer<br/>
     * Used: {@link MDFullGrp}, {@link MDIncGrp}, {@link TrdCapRptSideGrp}, {@link TrdCapRptAckSideGrp},
     * {@link ListStatusMsg}
     */
    RptSeq                                              (83),
    /**
     * Total quantity canceled for this order. (Prior to FIX 4.2 this field was of type int).<br/>
     * Type: Double<br/>
     * Used: {@link OrdListStatGrp}
     */
    CxlQty                                              (84),
    /**
     * Number of delivery instruction fields in repeating group.<br/>
     * Type: Integer<br/>
     * Used: {@link DlvyInstGrp}
     */
    NoDlvyInst                                          (85),
    /**
     * Free format text field to indicate delivery instructions.<br/>
     * Type: String<br/>
     * Used: 
     */
    DlvyInst                                            (86),
    /**
     * Identifies status of allocation.<br/>
     * Type: Integer (see {@link AllocStatus})<br/>
     * Used: {@link AllocationInstructionAckMsg}, {@link AllocationReportMsg}, {@link AllocationReportAckMsg},
     */
    AllocStatus                                         (87),
    /**
     * Identifies reason for rejection.<br/>
     * Type: Integer (see {@link AllocRejCode})<br/>
     * Used: {@link AllocationInstructionAckMsg}, {@link AllocationReportMsg}, {@link AllocationReportAckMsg},
     */
     AllocRejCode                                       (88),
    /**
     * Trailer final field.<br/>
     * Type: Integer<br/>
     * Used: Standard Header FIX 4.0
     */
    Signature                                           (89),
    /**
     * Required to identify length of encrypted section of message.<br/>
     * Type: Integer<br/>
     * Used: Standard Header FIX 4.0
     */
    SecureDataLen                                       (90),
    /**
     * Required when message body is encrypted. Always immediately follows SecureDataLen field.<br/>
     * Type: String<br/>
     * Used: Standard Header FIX 4.0
     */
    SecureData                                          (91),
    /**
     * Broker to receive trade credit.<br/>
     * Type: String<br/>
     * Used:
     */
    BrokerOfCredit                                      (92),
    /**
     * Required when trailer contains signature.<br/>
     * Type: String<br/>
     * Used: Standard Header FIX 4.0
     */
    SignatureLength                                     (93),
    /**
     * Email message type.<br/>
     * Type: char (see {@link EmailType})<br/>
     * Used: {@link net.hades.fix.message.EmailMsg}
     */
    EmailType                                           (94),
    /**
     * Number of bytes in raw data field.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.LogonMsg},{@link net.hades.fix.message.NewsMsg}
     */
    RawDataLength                                       (95),
    /**
     * Unformatted raw data, can include bitmaps, word processor documents, etc.<br/>
     * Type: Data<br/>
     * Used: {@link net.hades.fix.message.LogonMsg},{@link net.hades.fix.message.NewsMsg}
     */
    RawData                                             (96),
    /**
     * Indicates that message may contain information that has been sent under
     * another sequence number.<br/>
     * Type: Boolean<br/>
     * Used: Standard Header
     */
    PossResend                                          (97),
    /**
     * Method of encryption.<br/>
     * Type: Int<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    EncryptMethod                                       (98),
    /**
     * Price per share.<br/>
     * Type: Price<br/>
     * Used: {@link NewOrderSingleMsg}
     */
    StopPx                                              (99),
    /**
     * Execution destination as defined by institution when order is entered.<br/>
     * IB: If “BEST” is used, either tag 207 or tag 15 must be used as well.
     * For options, IB uses underlying symbol.<br/>
     * Type: Exchange<br/>
     * Used: New Order-Single, Execution Report
     */
    ExDestination                                       (100),
    /**
     * Code to identify reason for cancel rejection.<br/>
     * Type: Int<br/>
     * Used: Order Cancel Reject
     */
    CxlRejReason                                        (102),
    /**
     * Code to identify reason for order rejection.<br/>
     * IB Supported Values: 0,3<br/>
     * Type: Int<br/>
     * Used: Execution Reports
     */
    OrdRejReason                                        (103),
    /**
     * Code to qualify IOI use.<br/>
     * Type: Character (see {@link IOIQualifier})<br/>
     * Used: {@link IOIMsg}
     */
    IOIQualifier                                        (104),
    /**
     * Wave number.<br/>
     * Type: String<br/>
     * Used:
     */
    WaveNo                                              (105),
    /**
     * Name of security issuer (e.g. International Business Machines, GNMA).<br/>
     * Type: String<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    Issuer                                              (106),
    /**
     * Can be used to provide an optional textual description for a financial instrument.<br/>
     * Type: String<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    SecurityDesc                                        (107),
    /**
     * Heartbeat interval (seconds).<br/>
     * Type: Int<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    HeartBtInt                                          (108),
    /**
     * Firm identifier used in third party-transactions (should not be a substitute for
     * OnBehalfOfCompID/DeliverToCompID)..<br/>
     * Type: Int<br/>
     * Used:
     */
    ClientID                                            (109),
    /**
     * Minimum quantity of an order to be executed.<br/>
     * Type: Qty<br/>
     * Used: New Order Single
     */
    MinQty                                              (110),
    /**
     * Maximum number of shares within an order to be shown on the exchange floor
     * at any given time. (Prior to FIX 4.2 this field was of type int).<br/>
     * Type: Qty<br/>
     * Used: New Order Single
     */
    MaxFloor                                            (111),
    /**
     * Identifier included in Test Request message to be returned in resulting Heartbeat.<br/>
     * A timestamp string is suggested for TestReqID.<br/>
     * Type: String<br/>
     * Used: {@link TestRequestMsg}
     */
    TestReqID                                           (112),
    /**
     * Identifies party of trade responsible for exchange reporting. Valid Values:
     * N - Indicates the party sending message will report trade
     * Y - Indicates the party receiving message must report trade<br/>
     * Type: Boolean<br/>
     * Used: {@link ExecutionReport}
     */
    ReportToExch                                        (113),
    /**
     * Indicates whether the broker is to locate the stock in conjunction with a short sale
     * order.<br/>
     * IB: Required for short sale orders involving U.S. equity securities (“stocks”).
     * If customer uses IB as its executing broker but uses a clearing broker other than IB (a
     * “Non-Cleared Customer”) and Tag 6086 contains the value “1” or “2”, this Tag 114 must contain the
     * value “N”. IB will not accept a short sale order if a Non-Cleared Customer enters the value Y in
     * Tag 114. If customer uses IB as its executing broker and clearing broker (a “Cleared Customer”),
     * then such Cleared Customer is not required to populate this field.<br/>
     * Type: Boolean<br/>
     * Used: New Order - Single
     */
    LocateReqd                                          (114),
    /**
     * Trading partner company ID used when sending messages via a third party.<br/>
     * Type: String<br/>
     * Used: Standard Header FIX 4.0
     */
    OnBehalfOfCompID                                    (115),
    /**
     * Trading partner SubID used when delivering messages via a third party.<br/>
     * Type: String<br/>
     * Used: Standard Header FIX 4.0
     */
    OnBehalfOfSubID                                     (116),
    /**
     * Unique identifier for quote.<br/>
     * Type: String<br/>
     * Used: {@link QuoteMsg}
     */
    QuoteID                                             (117),
    /**
     * Total amount due as the result of the transaction (e.g. for Buy order - principal + commission + fees)
     * reported in currency of execution.<br/>
     * Type: Double<br/>
     * Used: {@link AllocationInstructionMsg}, {@link TrdCapRptSideGrp}, {@link TrdCapRptAckSideGrp},
     * {@link ConfirmationMsg}, {@link AllocationReportMsg}, {@link ExecutionReportMsg}, {@link AllocationInstructionAlertMsg}
     */
    NetMoney                                            (118),
    /**
     * Total amount due expressed in settlement currency (includes the effect of the forex transaction).<br/>
     * Type: Double<br/>
     * Used: {@link AllocGrp}, {@link TrdCapRptSideGrp}, {@link TrdCapRptAckSideGrp},
     * {@link SettlObligationInstructionsMsg}, {@link ConfirmationMsg}, {@link ExecutionReportMsg}
     */
    SettlCurrAmt                                        (119),
    /**
     * Currency code of settlement denomination.<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: {@link QuoteMsg}, {@link net.hades.fix.message.QuoteRequestMsg}
     */
    SettlCurrency                                       (120),
    /**
     * Indicates request for forex accommodation trade to be executed along with security transaction. Valid Values:
     * <ul>
     *      <li>N - Do Not Execute Forex After Security Trade
     *      <li>Y - Execute Forex After Security Trade<br/>
     * </ul>
     * Type: Boolean<br/>
     * Used: {@link NewOrderSingleMsg}, {@link OrderCancelReplaceRequestMsg}, @link ListOrdGrp},
     * @link SideCrossOrdModGrp}, @link BidRequest}, @link NewOrderMultileg}, @link MultilegOrderCancelReplace}
     */
    ForexReq                                            (121),
    /**
     * Original time of message transmission (always expressed in UTC (Universal
     * Time Coordinated, also known as “GMT”) when transmitting orders as the result of a
     * resend request.<br/>
     * Type: UTC time stamp<br/>
     * Used: Message Header
     */
    OrigSendingTime                                     (122),
    /**
     * Indicates that the Sequence Reset message is replacing administrative or application 
     * messages which will not be resent.<br/>
     * Valid values:<br/>
     * N - Sequence Reset, Ignore Msg Seq Num (N/A For FIXML - Not Used)<br/>
     * Y - Gap Fill Message, Msg Seq Num Field Valid<br/>
     * Type: Boolean (see {@link BoolYesNo})<br/>
     * Used: {@link net.hades.fix.message.SequenceResetMsg}
     */
    GapFillFlag                                         (123),
    /**
     * No of execution repeating group entries to follow.<br/>
     * Type: Integer<br/>
     * Used: {@link ExecAllocGrp}, {@link ExecCollGrp}
     */
    NoExecs                                             (124),
    /**
     * Defines if cancel is for part or all of the remaining quantity of an order.
     * Valid values:
     * P = partial cancel (reduce quantity)
     * F = full remaining quantity<br/>
     * Type: Character<br/>
     * Used:
     */
    CxlType                                             (125),
    /**
     * Time/Date of order expiration (always expressed in UTC (Universal Time
     * Coordinated, also known as “GMT”).<br/>
     * Type: UTC time stamp<br/>
     * Used: {@link NewOrderSingleMsg}
     */
    ExpireTime                                          (126),
    /**
     * Reason for execution rejection.<br/>
     * Type: Character (see {@link DKReason})<br/>
     * Used: {@link DontKnowTradeMsg}, {@link ExecutionAcknowledgementMsg}
     */
     DKReason                                           (127),
    /**
     * Trading partner company ID used when sending messages via a third party.<br/>
     * Type: Boolean (BoolYesNo)<br/>
     * Used: Logon
     */
    DeliverToCompID                                     (128),
    /**
     * Trading partner SubID used when delivering messages via a third party.<br/>
     * Type: String<br/>
     * Used: Standard Header FIX 4.0
     */
    DeliverToSubID                                      (129),
    /**
     * Indicates that IOI is the result of an existing agency order or a facilitation position
     * resulting from an agency order, not from principal trading or order solicitation activity.<br/>
     * Type: Boolean (see {@link BoolYesNo})<br/>
     * Used: {@link IOIMsg}
     */
    IOINaturalFlag                                      (130),
    /**
     * Unique identifier for quote request.<br/>
     * Type: String (see {@link BoolYesNo})<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    QuoteReqID                                          (131),
    /**
     * Bid price/rate.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    BidPx                                               (132),
    /**
     * Offer price/rate.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    OfferPx                                             (133),
    /**
     * Quantity of bid. (Prior to FIX 4.2 this field was of type int)<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    BidSize                                             (134),
    /**
     * Quantity of offer. (Prior to FIX 4.2 this field was of type int)<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    OfferSize                                           (135),
    /**
     * Number of repeating groups of miscellaneous fees.<br/>
     * Type: Integer<br/>
     * Used: {@link MiscFeesGrp}
     */
    NoMiscFees                                          (136),
    /**
     * Miscellaneous fee value.<br/>
     * Type: Double<br/>
     * Used: {@link MiscFeesGrp}
     */
    MiscFeeAmt                                          (137),
    /**
     * Currency of miscellaneous fee.<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: {@link MiscFeesGrp}
     */
    MiscFeeCurr                                         (138),
    /**
     * Indicates type of miscellaneous fee.<br/>
     * Type: String (see {@link MiscFeeType})<br/>
     * Used: {@link MiscFeesGrp}
     */
    MiscFeeType                                         (139),
    /**
     * Previous closing price of security.<br/>
     * Type: Decimal<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    PrevClosePx                                         (140),
    /**
     * Indicates that the both sides of the FIX session should reset sequence numbers.<br/>
     * Type: String<br/>
     * Used: Standard Header FIX 4.0
     */
    ResetSeqNumFlag                                     (141),
    /**
     * Trading partner LocationID (i.e. geographic location and/or desk).<br/>
     * Type: String<br/>
     * Used: Standard Header FIX 4.1 up
     */
    SenderLocationID                                    (142),
    /**
     * Trading partner LocationID (i.e. geographic location and/or desk).<br/>
     * Type: String<br/>
     * Used: Standard Header FIX 4.2 up
     */
    TargetLocationID                                    (143),
    /**
     * Trading partner LocationID (i.e. geographic location and/or desk) used 
     * when delivering messages via a third party.<br/>
     * Type: String<br/>
     * Used: Standard Header FIX 4.1 up
     */
    OnBehalfOfLocationID                                (144),
    /**
     * Trading partner LocationID (i.e. geographic location and/or desk) used 
     * when delivering messages via a third party.<br/>
     * Type: String<br/>
     * Used: Standard Header FIX 4.0
     */
    DeliverToLocationID                                 (145),
    /**
     * Specifies the number of repeating symbols specified.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.NewsMsg}
     */
    NoRelatedSym                                        (146),
    /**
     * The subject of an Email message.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.EmailMsg}
     */
    Subject                                             (147),
    /**
     * The headline of a news message.<br/>
     * IB: Used in bulletin messages, 35=B.<br/>
     * Type: Text<br/>
     * Used: {@link net.hades.fix.message.NewsMsg}
     */
    Headline                                            (148),
    /**
     * A URI (Uniform Resource Identifier) or URL (Uniform Resource Locator)
     * link to additional information (i.e. http://www.XYZ.com/research.html).<br/>
     * Type: String<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg}
     */
    URLLink                                             (149),
    /**
     * Describes the specific ExecutionRpt (i.e. Pending Cancel) while OrdStatus will
     * always identify the current order status (i.e. Partially Filled).<br/>
     * IB supports the following values: 0,1,2,4,5,6,8,C,D,E<br/>
     * Type: Char (ExecType)<br/>
     * Used: Execution Reports
     */
    ExecType                                            (150),
    /**
     * Amount of shares open for further execution. If the OrdStatus is Canceled,
     * DoneForTheDay, Expired, Calculated, or Rejected (in which case the order is no
     * longer active) then LeavesQty could be 0, otherwise LeavesQty = OrderQty - CumQty. 
     * (Prior to FIX 4.2 this field was of type int).<br/>
     * Type: Qty<br/>
     * Used: Execution Reports
     */
    LeavesQty                                           (151),
    /**
     * Specifies the approximate order quantity desired in total monetary units vs. as tradeable
     * units (e.g. number of shares). The broker or fund manager (for CIV orders) would be responsible
     * for converting and calculating a tradeable unit (e.g. share) quantity (OrderQty (38))
     * based upon this amount to be used for the actual order and subsequent messages.<br/>
     * Type: Qty<br/>
     * Used: {@link OrderQtyData}
     */
    CashOrderQty                                        (152),
    /**
     * {@link TagNum#AvgPx} (6) for a specific {@link TagNum#AllocAccount} (79)
     * For Fixed Income this is always expressed as "percent of par" price type.<br/>
     * Type: Double<br/>
     * Used: {@link AllocGrp}
     */
    AllocAvgPx                                          (153),
    /**
     * {@link TagNum#NetMoney} (8) for a specific {@link TagNum#AllocAccount} (79).<br/>
     * Type: Double<br/>
     * Used: {@link AllocGrp}
     */
    AllocNetMoney                                       (154),
    /**
     * Foreign exchange rate used to compute {@link TagNum#SettlCurrAmt} (9) from {@link TagNum#Currency} (5)
     * to SettlCurrency (20).<br/>
     * Type: Double<br/>
     * Used: {@link AllocGrp}, {@link TrdCapRptSideGrp}, {@link TrdCapRptAckSideGrp}, {@link SettlObligationInstructions},
     * {@link Confirmation}, {@link ExecutionReport}
     */
    SettlCurrFxRate                                     (155),
    /**
     * Specifies whether or not SettlCurrFxRate (55) should be multiplied or divided.<br/>
     * Type: Char (see {@link SettlCurrFxRateCalc})<br/>
     * Used: {@link QuoteMsg}
     */
    SettlCurrFxRateCalc                                 (156),
    /**
     * Number of Days of Interest for convertible bonds and fixed income. Note value may be negative.<br/>
     * Type: Integer<br/>
     * Used: {@link AllocationInstructionMsg}, {@link TrdCapRptSideGrp}, {@link TrdCapRptAckSideGrp}, {@link ConfirmationMsg},
     * {@link AllocationReportMsg}, {@link ExecutionReportMsg}, {@link AllocationInstructionAlertMsg}
     */
    NumDaysInterest                                     (157),
    /**
     * The amount the buyer compensates the seller for the portion of the next coupon interest payment
     * the seller has earned but will not receive from the issuer because the issuer will send the
     * next coupon payment to the buyer.
     * Accrued Interest Rate is the annualized Accrued Interest amount divided by the purchase price of the bond.<br/>
     * Type: Double<br/>
     * Used: {@link AllocationInstructionMsg}, {@link TrdCapRptSideGrp}, {@link TrdCapRptAckSideGrp}, {@link ConfirmationMsg},
     * {@link AllocationReportMsg}, {@link ExecutionReportMsg}, {@link AllocationInstructionAlertMsg}
     */
    AccruedInterestRate                                 (158),
    /**
     * Amount of Accrued Interest for convertible bonds and fixed income.<br/>
     * Type: Double<br/>
     * Used: {@link AllocationInstructionMsg}, {@link TrdCapRptSideGrp}, {@link TrdCapRptAckSideGrp}, {@link ConfirmationMsg},
     * {@link AllocationReportMsg}, {@link CollateralRequestMsg}, {@link CollateralAssignmentMsg}, {@link CollateralResponseMsg},
     * {@link CollateralReportMsg}, {@link CollateralInquiryMsg}, {@link ExecutionReportMsg}, {@link AllocationInstructionAlertMsg}
     */
    AccruedInterestAmt                                  (159),
    /**
     * Indicates mode used for Settlement Instructions message.<br/>
     * Type: Character (see {@link SettlInstMode})<br/>
     * Used: {@link ListOrdGrp}, {@link SettlementInstructionsMsg}
     */
    SettlInstMode                                       (160),
    /**
     * Free format text related to a specific {@link TagNum#AllocAccount} (79).<br/>
     * Type: String<br/>
     * Used: {@link AllocAckGroup}, {@link AllocGroup}
     */
    AllocText                                           (161),
    /**
     * Unique identifier for Settlement Instruction.<br/>
     * Type: String<br/>
     * Used: {@link AllocAckGroup}, {@link AllocGroup}
     */
    SettlInstID                                         (162),
    /**
     * Settlement Instructions message transaction type.<br/>
     * Type: Character(see {@link SettlInstTransType}) <br/>
     * Used: {@link SettlInstGroup}
     */
    SettlInstTransType                                  (163),
    /**
     * Unique identifier for an email thread (new and chain of replies).<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.EmailMsg}
     */
    EmailThreadID                                       (164),
    /**
     * Indicates source of Settlement Instructions.<br/>
     * Type: Character (see {@link SettlInstSource})<br/>
     * Used: {@link DlvyInstGroup}
     */
    SettlInstSource                                     (165),
    /**
     * Identifies Settlement Depository or Country Code, ISITC spec.<br/>
     * Type: String<br/>
     * Used:
     */
    SettlLocation                                       (166),
    /**
     * Indicates type of security. Security type enumerations are grouped by {@link TagNum#Product}(460) field value.
     * NOTE: Additional values may be used by mutual agreement of the counterparties.<br/>
     * IB supports the following values: CS,FUT,OPT,WAR,MULTILEG,MLEG,CASH<br/>
     * Type: String<br/>
     * Used: {@link AdvertismentMsg}, {@link IOIMsg}, {@link net.hades.fix.message.NewsMsg}, {@link QuoteMsg}
     */
    SecurityType                                        (167),
    /**
     * Time the details within the message should take effect (always expressed in
     * UTC (Universal Time Coordinated, also known as “GMT”).<br/>
     * Type: UTC time stamp<br/>
     * Used: {@link NewOrderSingleMsg}, {@link OrderCancelReplaceRequestMsg}, {@link ListOrdGrp}, {@link SettlInstGrp},
     * {@link SettlObligationInstructionsMsg}, {@link NewOrderCrossMsg}, {@link CrossOrderCancelReplaceRequestMsg},
     * {@link NewOrderMultilegMsg}, {@link MultilegOrderCancelReplaceMsg}, {@link SettlementInstructionRequestMsg},
     * {@link ExecutionReportMsg}
     */
    EffectiveTime                                       (168),
    /**
     * Identifies the Standing Instruction database used.<br/>
     * Type: Integer (see {@link StandInstDbType})<br/>
     * Used: {@link SettlInstructionsDataMsg}, {@link SettlementInstructionRequestMsg}
     */
    StandInstDbType                                     (169),
    /**
     * Name of the Standing Instruction database represented with {@link TagNum#StandInstDbType} (169)
     * (i.e. the Global Custodian's name).<br/>
     * Type: String<br/>
     * Used: {@link SettlInstructionsDataMsg}, {@link SettlementInstructionRequestMsg}
     */
    StandInstDbName                                     (170),
    /**
     * Unique identifier used on the Standing Instructions database for the Standing Instructions to be referenced.<br/>
     * Type: String<br/>
     * Used: {@link SettlInstructionsDataMsg}, {@link SettlementInstructionRequestMsg}
     */
    StandInstDbID                                       (171),
    /**
     * Identifies type of settlement.<br/>
     * Type: Integer (see {@link SettlDeliveryType})<br/>
     * Used: {@link SettlInstructionsDataMsg}
     */
    SettlDeliveryType                                   (172),
    /**
     * Brokers account code at the depository (i.e. CEDEL ID for CEDEL, FINS for DTC, or Euroclear ID for Euroclear)
     * if Settlement Location is a depository.<br/>
     * Type: String<br/>
     * Used: 
     */
    SettlDepositoryCode                                 (173),
    /**
     * BIC (Bank Identification Code - Swift managed) code of the broker involved (i.e. for multi-company
     * brokerage firms).<br/>
     * Type: String<br/>
     * Used:
     */
    SettlBrkrCode                                       (174),
    /**
     * BIC (Bank Identification Code - Swift managed) code of the institution involved
     * (i.e. for multi-company institution firms).<br/>
     * Type: String<br/>
     * Used:
     */
    SettlInstCode                                       (175),
    /**
     * Name of SettlInstSource's local agent bank if SettlLocation is not a depository.<br/>
     * Type: String<br/>
     * Used:
     */
    SecuritySettlAgentName                              (176),
    /**
     * BIC (Bank Identification Code--Swift managed) code of the SettlInstSource's local agent bank if
     * SettlLocation is not a depository.<br/>
     * Type: String<br/>
     * Used:
     */
    SecuritySettlAgentCode                              (177),
    /**
     * SettlInstSource's account number at local agent bank if SettlLocation is not a depository.<br/>
     * Type: String<br/>
     * Used:
     */
    SecuritySettlAgentAcctNum                           (178),
    /**
     * Name of SettlInstSource's account at local agent bank if SettlLocation is not a depository.<br/>
     * Type: String<br/>
     * Used:
     */
    SecuritySettlAgentAcctName                          (179),
    /**
     * Name of contact at local agent bank for SettlInstSource's account if SettlLocation is not a depository.<br/>
     * Type: String<br/>
     * Used:
     */
    SecuritySettlAgentContactName                       (180),
    /**
     * Phone number for contact at local agent bank if SettlLocation is not a depository.<br/>
     * Type: String<br/>
     * Used:
     */
    SecuritySettlAgentContactPhone                      (181),
    /**
     * Name of SettlInstSource's local agent bank if SettlDeliveryType=Free.<br/>
     * Type: String<br/>
     * Used:
     */
    CashSettlAgentName                                  (182),
    /**
     * BIC (Bank Identification Code--Swift managed) code of the SettlInstSource's local agent bank
     * if SettlDeliveryType=Free.<br/>
     * Type: String<br/>
     * Used:
     */
    CashSettlAgentCode                                  (183),
    /**
     * SettlInstSource's account number at local agent bank if SettlDeliveryType=Free.<br/>
     * Type: String<br/>
     * Used:
     */
    CashSettlAgentAcctNum                               (184),
    /**
     * Name of SettlInstSource's account at local agent bank if SettlDeliveryType=Free.<br/>
     * Type: String<br/>
     * Used:
     */
    CashSettlAgentAcctName                              (185),
    /**
     * Name of contact at local agent bank for SettlInstSource's account if SettlDeliveryType=Free.<br/>
     * Type: String<br/>
     * Used:
     */
    CashSettlAgentContactName                           (186),
    /**
     * Phone number for contact at local agent bank for SettlInstSource's account if SettlDeliveryType=Free.<br/>
     * Type: String<br/>
     * Used:
     */
    CashSettlAgentContactPhone                          (187),
    /**
     * Bid F/X spot rate.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    BidSpotRate                                         (188),
    /**
     * Bid F/X forward points added to spot rate. May be a negative value.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    BidForwardPoints                                    (189),
    /**
     * Offer F/X spot rate.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    OfferSpotRate                                       (190),
    /**
     * Offer F/X forward points added to spot rate. May be a negative value.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    OfferForwardPoints                                  (191),
    /**
     * Deprecated in FIX.5.0 {@link TagNum#OrderQty} (38) of the future part of a F/X swap order..<br/>
     * Type: Decimal<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    OrderQty2                                           (192),
    /**
     * Deprecated in FIX.5.0 {@link TagNum#SettDate} (64) of the future part of a F/X swap order.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    SettlDate2                                          (193),
    /**
     * F/X spot rate.<br/>
     * Type: Double<br/>
     * Used: {@link TradeCaptureReportMsg}, {@link TradeCaptureReportAckMsg}, {@link ExecutionReportMsg}
     */
    LastSpotRate                                        (194),
    /**
     * F/X forward points added to LastSpotRate (94). May be a negative value. Expressed in decimal form.
     * For example, 61.99 points is expressed and sent as 0.006199.<br/>
     * Type: Double<br/>
     * Used: {@link TradeCaptureReportMsg}, {@link TradeCaptureReportAckMsg}, {@link ExecutionReportMsg}
     */
    LastForwardPoints                                   (195),
    /**
     * Can be used to link two different Allocation messages (each with unique AllocID (70)) together,
     * i.e. for F/X "Netting" or "Swaps". Should be unique.<br/>
     * Type: String<br/>
     * Used: {@link AllocationInstructionMsg}, {@link AllocationReportMsg}, {@link AllocationInstructionAlertMsg}
     */
    AllocLinkID                                         (196),
    /**
     * Identifies the type of Allocation linkage when {@link TagNum#AllocLinkID} (96) is used.<br/>
     * Type: Integer (see {@link AllocLinkType})<br/>
     * Used: {@link AllocationInstructionMsg}, {@link AllocationReportMsg}, {@link AllocationInstructionAlertMsg}
     */
    AllocLinkType                                       (197),
    /**
     * Assigned by the party which accepts the order. Can be used to provide the OrderID (37) used by an
     * exchange or executing system.<br/>
     * Type: String<br/>
     * Used: {@link OrderCancelRejectMsg}, {@link MDFullGrp}, {@link MDIncGrp}, {@link OrdAllocGrp}, {@link TradeReportOrderDetailMsg},
     * {@link DontKnowTradeDKMsg}, {@link OrderMassCancelReportMsg}, {@link CollateralRequestMsg}, {@link CollateralAssignmentMsg},
     * {@link CollateralResponseMsg}, {@link CollateralReportMsg}, {@link CollateralInquiryMsg}, {@link ExecutionReportMsg},
     * {@link CollateralInquiryAckMsg}, {@link ExecutionAcknowledgementMsg}
     */
    SecondaryOrderID                                    (198),
    /**
     * Number of repeating groups of {@link IOIQualifierGroup} (04).<br/>
     * Type: Integer<br/>
     * Used: {@link IOIMsg}
     */
    NoIOIQualifiers                                     (199),
    /**
     * Can be used with standardized derivatives vs. the MaturityDate (54) field. 
     * Month and Year of the maturity (used for standardized futures and options).<br/>
     * Format:<br/>
     * YYYYMM (i.e. 99903)<br/>
     * YYYYMMDD (20030323)<br/>
     * YYYYMMwN (200303w) for week<br/>
     * A specific date or can be appended to the MaturityMonthYear. For instance, if multiple 
     * standard products exist that mature in the same Year and Month, but actually mature at 
     * a different time, a value can be appended, such as "w" or "w2" to indicate week as opposed 
     * to week 2 expiration. Likewise, the date (0-3) can be appended to indicate a specific 
     * expiration (maturity date).<br/>
     * Type: string,month-year<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    MaturityMonthYear                                   (200),
    /**
     * Indicates whether an Option is for a put or call. Valid values: 0 - Put 1 - Call<br/>
     * Type: Integer (see {@link PutOrCall})<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    PutOrCall                                           (201),
    /**
     * The strike price for an option.<br/>
     * Type: Price<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    StrikePrice                                         (202),
    /**
     * Used for derivative products, such as options.<br/>
     * Type: Integer (see {@link CoveredOrUncovered})<br/>
     * Used: {@link NewOrderSingleMsg},{@link OrderCancelReplaceRequestMsg},{@link ListOrdGrp},{@link SideCrossOrdModGrp},
     * {@link NewOrderMultilegMsg},{@link MultilegOrderCancelReplaceMsg}
     */
    CoveredOrUncovered                                  (203),
    /**
     * Used for options when delivering the order to an execution system/exchange to
     * specify if the order is for a customer or the firm placing the order itself.<br/>
     * IB: Required for Options Orders<br/>
     * Type: Int (CustomerOrFirm)<br/>
     * Used: New Order-Single, Execution Report
     */
    CustomerOrFirm                                      (204),
    /**
     * Day of month used in conjunction with MaturityMonthYear to specify the maturity 
     * date for SecurityType=FUT or SecurityType=OPT.<br/>
     * IB: Required for Options Orders<br/>
     * Type: Int, DayOfMonth<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    MaturityDay                                         (205),
    /**
     * Provided to support versioning of option contracts as a result of corporate actions or events. 
     * Use of this field is defined by counterparty agreement or market conventions.<br/>
     * Type: Character<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    OptAttribute                                        (206),
    /**
     * Market used to help identify a security.<br/>
     * Type: String, Exchange at application level<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    SecurityExchange                                    (207),
    /**
     * Indicates whether or not details should be communicated to BrokerOfCredit (i.e. step-in broker).<br/>
     * Valid Values:
     * <ul>
     *      <li>N - Details shoult not be communicated
     *      <li>Y - Details should be communicated<br/>
     * </ul>
     * Type: Boolean<br/>
     * Used: {@link AllocGrp}
     */
    NotifyBrokerOfCredit                                (208),
    /**
     * Indicates how the receiver (i.e. third party) of Allocation message should handle/process the account details.<br/>
     * Type: Int (AllocHandlInst)<br/>
     * Used: {@link AllocGrp}
     */
    AllocHandlInst                                      (209),
    /**
     * Maximum quantity (e.g. number of shares) within an order to be shown to other customers (i.e. sent via an IOI).
     * (Prior to FIX 4.2 this field was of type int).<br/>
     * Type: Double<br/>
     * Used: {@link NewOrderSingleMsg},{@link OrderCancelReplaceRequestMsg},{@link ListOrdGrp},{@link NewOrderCrossMsg},
     * {@link MultilegOrderCancelReplaceMsg},{@link ExecutionReportMsg},
     */
    MaxShow                                             (210),
    /**
     * Amount (signed) added to the price of the peg for a pegged order.<br/>
     * Type: PriceOffset<br/>
     * Used:
     */
    PegOffsetValue                                       (211),
    /**
     * Required when specifying XmlData to identify the length of a XmlData 
     * message block. (Can be embedded within encrypted data section.)<br/>
     * Type: Integer<br/>
     * Used: Standard Header 4.2 up
     */
    XmlDataLen                                          (212),
    /**
     * Can contain a XML formatted message block (e.g. FIXML). Always immediately 
     * follows XmlDataLen field. (Can be embedded within encrypted data section.)<br/>
     * Type: String<br/>
     * Used: Standard Header 4.2 up
     */
    XmlData                                             (213),
    /**
     * Reference identifier for the {@link TagNum#SettlInstID} (162) with Cancel and Replace
     * {@link TagNum#SettlInstTransType} (163) transaction types.<br/>
     * Type: String<br/>
     * Used: {@link SettlInstGrp}
     */
    SettlInstRefID                                      (214),
    /**
     * Number of repeating groups of {@link TagNum#RoutingID} (217) and {@link TagNum#RoutingType} (216) values.<br/>
     * Type: Integer<br/>
     * Used: {@link IOIMsg},{@link net.hades.fix.message.NewsMsg}
     */
    NoRoutingIDs                                        (215),
    /**
     * Indicates the type of {@link TagNum#RoutingID} (217) specified.<br/>
     * Type: Integer (see {@link RoutingType})<br/>
     * Used: {@link RoutingIDGroup},{@link net.hades.fix.message.NewsMsg}
     */
    RoutingType                                         (216),
    /**
     * Assigned value used to identify a specific routing destination.<br/>
     * Type:String<br/>
     * Used: {@link RoutingIDGroup},{@link net.hades.fix.message.NewsMsg}
     */
    RoutingID                                           (217),
    /**
     * For Fixed Income. Either Swap Spread or Spread to Benchmark depending upon the order type.
     * Spread to Benchmark: Basis points relative to a benchmark. To be expressed as
     * "count of basis points" (vs. an absolute value). E.g. High Grade Corporate Bonds may express
     * price as basis points relative to benchmark (the BenchmarkCurveName (22) field).
     * Note: Basis points can be negative.<br/>
     * Swap Spread: Target spread for a swap.<br/>
     * Type:Float<br/>
     * Used: {@link SpreadOfBenchmarkCurveData},{@link net.hades.fix.message.NewsMsg}
     */
    Spread                                              (218),
    /**
     * Deprecated in FIX.4.2 For Fixed Income. Identifies the benchmark (e.g. used in conjunction
     * with the Spread field).<br/>
     * Type: Character (see {@link Benchmark})<br/>
     * Used: {@link IOIMsg}
     */
    Benchmark                                           (219),
    /**
     * Identifies currency used for benchmark curve.<br/>
     * Type: Currency (see {@link Currency})<br/>
     * Used: {@link SpreadOfBenchmarkCurveData}
     */
    BenchmarkCurveCurrency                              (220),
    /**
     * Name of benchmark curve.<br/>
     * Type: String (see {@link BenchmarkCurveName})<br/>
     * Used: {@link SpreadOfBenchmarkCurveData}
     */
    BenchmarkCurveName                                  (221),
    /**
     * Point on benchmark curve. Free form values: e.g. "Y", "7Y", "INTERPOLATED".<br/>
     * Type: String<br/>
     * Used: {@link SpreadOfBenchmarkCurveData}
     */
    BenchmarkCurvePoint                                 (222),
    /**
     * The rate of interest that, when multiplied by the principal, par value, or face value 
     * of a bond, provides the currency amount of the periodic interest payment. The coupon 
     * is always cited, along with maturity, in any quotation of a bond's price.<br/>
     * Type: Percentage<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    CouponRate                                          (223),
    /**
     * Date interest is to be paid. Used in identifying Corporate Bond issues. 
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3)<br/>
     * (prior to FIX 4.4 field was of type UTCDate).<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    CouponPaymentDate                                   (224),
    /**
     * The date on which a bond or stock offering is issued. It may or may not be the same 
     * as the effective date (Dated Date) or the date on which interest begins to accrue
     * (Interest Accrual Date) (Note tag # was reserved in FIX 4.1, added in FIX 4.3)<br/>
     * (prior to FIX 4.4 field was of type UTCDate).<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    IssueDate                                           (225),
    /**
     * Deprecated in FIX.4.4 Number of business days before repurchase of a repo. 
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3).<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    RepurchaseTerm                                      (226),
    /**
     * Deprecated in FIX.4.4 Percent of par at which a Repo will be repaid. Represented 
     * as a percent, e.g. .9525 represents 95-/4 percent of par. (Note tag # was reserved 
     * in FIX 4.1, added in FIX 4.3).<br/>
     * Type: Percentage<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    RepurchaseRate                                      (227),
    /**
     * For Fixed Income: Amorization Factor for deriving Current face from Original face 
     * for ABS or MBS securities, note the fraction may be greater than, equal to or 
     * less than. In TIPS securities this is the Inflation index.<br/>
     * Qty * Factor * Price = Gross Trade Amount<br/>
     * For Derivatives: Contract Value Factor by which price must be adjusted to determine 
     * the true nominal value of one futures/options contract.<br/>
     * (Qty * Price) * Factor = Nominal Value<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3).<br/>
     * Type: Float<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    Factor                                              (228),
    /**
     * Used with Fixed Income for Muncipal New Issue Market.
     * Agreement in principal between counter-parties prior to actual trade date.<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3)<br/>
     * (prior to FIX 4.4 field was of type UTCDate).<br/>
     * Type: LocalMkyDate<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    TradeOriginationDate                                (229),
    /**
     * The date when a distribution of interest is deducted from a securities assets or set aside for
     * payment to bondholders. On the ex-date, the securities price drops by the amount of the
     * distribution (plus or minus any market activity).<br/>
     * Type: LocalMkyDate<br/>
     * Used: {@link TrdCapRptSideGrp},{@link TrdCapRptAckSideGrp},{@link ConfirmationMsg},{@link ExecutionReportMsg}
     */
    ExDate                                              (230),
    /**
     * Specifies the ratio or multiply factor to convert from "nominal" units (e.g. contracts) 
     * to total units (e.g.shares) (e.g. 1.0, 100, 1000, etc). Applicable For Fixed Income, 
     * Convertible Bonds, Derivatives, etc.<br/>
     * In general quantities for all calsses should be expressed in the basic unit of the instrument, 
     * e.g. shares for equities, norminal or par amount for bonds, currency for foreign exchange. 
     * When quantity is expressed in contracts, e.g. financing transactions and bond trade reporting, 
     * ContractMutliplier should contain the number of units in one contract and can be omitted if the 
     * multiplier is the default amount for the instrument, i.e. 1,000 par of bonds, 1,000,000 par 
     * for financing transactions.<br/>
     * IB: This tag should be used to identify option contracts if the underlying
     * symbol is used in tag 55.<br/>
     * Type: Float<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    ContractMultiplier                                  (231),
    /**
     * Number of stipulation entries.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.Stipulations}
     */
    NoStipulations                                      (232),
    /**
     * For Fixed Income. Type of Stipulation. Other types may be used by mutual agreement of the
     * counterparties.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Stipulations}
     */
    StipulationType                                     (233),
    /**
     * For Fixed Income. Value of stipulation. The expression can be an absolute single value or a
     * combination of values and logical operators.
     * Bargain conditions recognized by the London Stock Exchange – to be used when StipulationType is "BGNCON".
     * <br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Stipulations}
     */
    StipulationValue                                    (234),
    /**
     * Type of yield. (Note tag # was reserved in FIX 4.1, added in FIX 4.3).<br/>
     * Type: String (see {@link YieldType})<br/>
     * Used: {@link net.hades.fix.message.comp.YieldData}
     */
    YieldType                                           (235),
    /**
     * Yield percentage.<br/>
     * Type: Percentage<br/>
     * Used: {@link net.hades.fix.message.comp.YieldData}
     */
    Yield                                               (236),
    /**
     * The price at which the securities are distributed to the different members of an
     * underwriting group for the primary market in Municipals, total gross underwriter's spread.<br/>
     * Type: Double<br/>
     * Used: {@link AllocationInstructionMsg},{@link TrdCapRptSideGrp},{@link TrdCapRptAckSideGrp},{@link ConfirmationMsg},
     * {@link AllocationReportMsg},{@link ExecutionReportMsg},{@link AllocationInstructionAlertMsg}
     */
    TotalTakedown                                       (237),
    /**
     * Provides the reduction in price for the secondary market in Muncipals.<br/>
     * Type: Double<br/>
     * Used: {@link AllocationInstructionMsg},{@link TrdCapRptSideGrp},{@link TrdCapRptAckSideGrp},{@link ConfirmationMsg},
     * {@link AllocationReportMsg},{@link ExecutionReportMsg},{@link AllocationInstructionAlertMsg}
     */
    Concession                                          (238),
    /**
     * Identifies the collateral used in the transaction.
     * Valid values: see {@link TagNum#SecurityType} (167) field (Note tag # was reserved in FIX 4.1, added in FIX 4.3)<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    RepoCollateralSecurityType                          (239),
    /**
     * Deprecated in FIX.4.4 Return of investor's principal in a security. Bond redemption can occur 
     * before maturity date.<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3) (prior to FIX 4.4 field was of type UTCDate)<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    RedemptionDate                                      (240),
    /**
     * Underlying security’s CouponPaymentDate. See {@link TagNum#CouponPaymentDate} (224) field for description.
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3)<br/>
     * (prior to FIX 4.4 field was of type UTCDate)<br/>
     * Time of security's maturity expressed in local time with offset to UTC specified.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingCouponPaymentDate                         (241),
    /**
     * Underlying security’s IssueDate. See {@link TagNum#IssueDate} (225) field for description.
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3)<br/>
     * (prior to FIX 4.4 field was of type UTCDate)<br/>
     * Time of security's maturity expressed in local time with offset to UTC specified.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingIssueDate                                 (242),
    /**
     * Deprecated in FIX.4.4 Underlying security's RepoCollateralSecurityType. See
     * {@link TagNum#RepoCollateralSecurityType} (239) field for description.<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3).<br/>
     * Type: Integer<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingRepoCollateralSecurityType                (243),
    /**
     * Deprecated in FIX.4.4 Underlying security's RepurchaseTerm. See
     * {@link TagNum#RepurchaseTerm} (226) field for description.<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3).<br/>
     * Type: Integer<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingRepurchaseTerm                            (244),
    /**
     * Deprecated in FIX.4.4 Underlying security's RepurchaseRate. See
     * {@link TagNum#RepurchaseRate} (227) field for description.<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3).<br/>
     * Type: Percentage<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingRepurchaseRate                            (245),
    /**
     * Underlying security’s Factor. See {@link TagNum#Factor} (228) field for description.<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3).<br/>
     * Type: Float<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingFactor                                    (246),
    /**
     * Deprecated in FIX.4.4 Underlying security's RedemptionDate. See {@link TagNum#RedemptionDate} (240)
     * field for description.<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3) <br/>
     * (prior to FIX 4.4 field was of type UTCDate)<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingRedemptionDate                            (247),
    /**
     * Multileg instrument's individual leg security’s CouponPaymentDate. See {@link TagNum#CouponPaymentDate} (224)
     * field for description (Note tag # was reserved in FIX 4.1, added in FIX 4.3)<br/>
     * (prior to FIX 4.4 field was of type UTCDate)<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegCouponPaymentDate                                (248),
    /**
     * Multileg instrument's individual leg security’s IssueDate. See {@link TagNum#IssueDate} (225)
     * field for description.<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3)<br/>
     * (prior to FIX 4.4 field was of type UTCDate)<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegIssueDate                                        (249),
    /**
     * Deprecated in FIX.4.4 Multileg instrument's individual leg security's RepoCollateralSecurityType.<br/>
     * See See {@link TagNum#RepoCollateralSecurityType} (239) field for description.<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3)<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegRepoCollateralSecurityType                       (250),
    /**
     * Deprecated in FIX.4.4 Multileg instrument's individual leg securitys RepurchaseTerm.<br/>
     * See {@link TagNum#RepurchaseTerm} (226) field for description.<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3)<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegRepurchaseTerm                                   (251),
    /**
     * Deprecated in FIX.4.4 Multileg instrument's individual leg security's RepurchaseRate.<br/>
     * See {@link TagNum#RepurchaseRate} (227) field for description.<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3)<br/>
     * Type: Percentage<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegRepurchaseRate                                   (252),
    /**
     * Multileg instrument's individual leg security’s Factor.<br/>
     * See {@link TagNum#Factor} (228) field for description.<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3)<br/>
     * Type: Float<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegFactor                                           (253),
    /**
     * Deprecated in FIX.4.4 Multileg instrument's individual leg security's RedemptionDate.
     * See {@link TagNum#RedemptionDate} (240) field for description<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3)<br/>
     * (prior to FIX 4.4 field was of type UTCDate)<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegRedemptionDate                                   (254),
    /**
     * An evaluation of a company's ability to repay obligations or its likelihood of not defaulting. 
     * These evaluation are provided by Credit Rating Agencies, i.e. S&P, Moody's.<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3)<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    CreditRating                                        (255),
    /**
     * Underlying security’s CreditRating. See {@link TagNum#CreditRating} (255) field for description.<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3).<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingCreditRating                              (256),
    /**
     * Multileg instrument's individual leg security’s CreditRating.<br/>
     * See {@link TagNum#CreditRating} (255) field for description.<br/>
     * (Note tag # was reserved in FIX 4.1, added in FIX 4.3)<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegCreditRating                                     (257),
    /**
     * Driver and part of trade in the event that the Security Master file was wrong at the point of entry.<br/>
     * Valid Values:
     * <ul>
     *      <li>N - Not Traded Flat
     *      <li>Y - Traded Flat
     * </ul>
     * Type: Boolean<br/>
     * Used: {@link ExecutionReportMsg}
     */
    TradedFlatSwitch                                    (258),
    /**
     * BasisFeatureDate allows requesting firms within fixed income the ability to request an
     * alternative yield-to-worst, -maturity, -extended or other call. This flows through the confirm process.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link ExecutionReportMsg}
     */
    BasisFeatureDate                                    (259),
    /**
     * Price for BasisFeatureDate.<br/>
     * Type: Double<br/>
     * Used: {@link ExecutionReportMsg}
     */
    BasisFeaturePrice                                   (260),
    /**
     * Unique identifier for Market Data Request.<br/>
     * Type: String<br/>
     * Used: {@link MarketDataRequest}
     */
    MDReqID                                             (262),
    /**
     * Subscription Request Type.<br/>
     * Type: Character (see {@link SubscriptionRequestType})<br/>
     * Used: {@link RFQRequestMsg}
     */
    SubscriptionRequestType                             (263),
    /**
     * Depth of market for Book Snapshot / Incremental updates.<br/>
     * Type: Integer<br/>
     * Used: {@link MarketDataRequest}
     */
    MarketDepth                                         (264),
    /**
     * Specifies the type of Market Data update.<br/>
     * Type: Integer (see {@link MDUpdateType})<br/>
     * Used: {@link MarketDataRequest}
     */
    MDUpdateType                                        (265),
    /**
     * Specifies whether or not book entries should be aggregated. (Not specified) = broker option.<br/>
     * Type: Boolean<br/>
     * Used: {@link MarketDataRequest}
     */
    AggregatedBook                                      (266),
    /**
     * Number of {@link TagNum#MDEntryType} (269) fields requested.<br/>
     * Type: Integer<br/>
     * Used: {@link MarketDataRequest}
     */
    NoMDEntryTypes                                      (267),
    /**
     * Number of entries in Market Data message.<br/>
     * Type: Integer<br/>
     * Used: {@link MarketDataSnapshot}
     */
     NoMDEntries                                        (268),
    /**
     * Type Market Data entry.<br/>
     * Type: Character (see {@link MDEntryType})<br/>
     * Used: {@link MarketDataRequest}
     */
    MDEntryType                                         (269),
    /**
     * Price of the Market Data Entry.<br/>
     * Type: Double<br/>
     * Used: {@link MarketDataSnapshot}
     */
     MDEntryPx                                          (270),
    /**
     * Quantity or volume represented by the Market Data Entry.<br/>
     * Type: Double<br/>
     * Used: {@link MarketDataRequest}
     */
    MDEntrySize                                         (271),
    /**
     * Date of Market Data Entry.<br/>
     * Type: UTCDateOnly<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    MDEntryDate                                         (272),
    /**
     * Time of Market Data Entry.<br/>
     * Type: UTCTimeOnly<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    MDEntryTime                                         (273),
    /**
     * Direction of the "tick".<br/>
     * Type: Character (see {@link TickDirection})<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    TickDirection                                       (274),
    /**
     * Market posting quote/trade.<br/>
     * Type: String (see {@link Exchange} for standard values)<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    MDMkt                                               (275),
    /**
     * Space-delimited list of conditions describing a quote.<br/>
     * Type: MultipleStringValue (see {@link QuoteCondition} for standard values)<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    QuoteCondition                                      (276),
    /**
     * Space-delimited list of conditions describing a trade.<br/>
     * Type: MultipleStringValue (see {@link TradeCondition} for standard values)<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    TradeCondition                                      (277),
    /**
     * Unique Market Data Entry identifier.<br/>
     * Type: String<br/>
     * Used: {@link MarketDataSnapshot}
     */
    MDEntryID                                          (278),
    /**
     * Type of Market Data update action.<br/>
     * Type: Character (see {@link MDUpdateAction})<br/>
     * Used: {@link MDIncGrp}
     */
    MDUpdateAction                                     (279),
    /**
     * Refers to a previous {@link TagNum#MDEntryID} (278).<br/>
     * Type: String<br/>
     * Used: {@link MDIncGrp}
     */
    MDEntryRefID                                        (280),
    /**
     * Reason for the rejection of a Market Data request.<br/>
     * Type: Character (see {@link MDReqRejReason})<br/>
     * Used: {@link net.hades.fix.message.MarketDataRequestRejectMsg}
     */
    MDReqRejReason                                      (281),
    /**
     * Originator of a Market Data Entry.<br/>
     * Type: String<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    MDEntryOriginator                                   (282),
    /**
     * Identification of a Market Maker's location.<br/>
     * Type: String<br/>
     * Used: {@link CompIDReqGrp},{@link CompIDStatGrp},{@link MDFullGrp},{@link MDIncGrp}
     */
    LocationID                                          (283),
    /**
     * Identification of a Market Maker's desk.<br/>
     * Type: String<br/>
     * Used: {@link CompIDReqGrp},{@link CompIDStatGrp},{@link MDFullGrp},{@link MDIncGrp}
     */
    DeskID                                              (284),
    /**
     * Reason for deletion.<br/>
     * Type: Character (see {@link DeleteReason})<br/>
     * Used: {@link MDIncGrp}
     */
    DeleteReason                                        (285),
    /**
     * Flag that identifies a market data entry. (Prior to FIX 4.3 this field was of type char).<br/>
     * Type: String<br/>
     * Used: {@link MarketDataRequest}
     */
    OpenCloseSettlFlag                                  (286),
    /**
     * Specifies the number of days that may elapse before delivery of the security.<br/>
     * Type: Integer<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    SellerDays                                          (287),
    /**
     * Buying party in a trade.<br/>
     * Type: String<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    MDEntryBuyer                                        (288),
    /**
     * Selling party in a trade.<br/>
     * Type: String<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    MDEntrySeller                                       (289),
    /**
     * Display position of a bid or offer, numbered from most competitive to least competitive,
     * per market side, beginning with.<br/>
     * Type: Integer<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    MDEntryPositionNo                                   (290),
    /**
     * Identifies a firm's or a security's financial status.<br/>
     * Type: String (MultipleCharValue)<br/>
     * Used: {@link MarketDataSnapshot}
     */
    FinancialStatus                                     (291),
    /**
     * Identifies the type of Corporate Action.<br/>
     * Type: String (MultipleCharValue)<br/>
     * Used: {@link MarketDataSnapshot}
     */
    CorporateAction                                     (292),
    /**
     * Default Bid Size.<br/>
     * Type: Decimal<br/>
     * Used: {@link net.hades.fix.message.MassQuoteMsg}
     */
    DefBidSize                                          (293),
    /**
     * Default Offer Size.<br/>
     * Type: Decimal<br/>
     * Used: {@link net.hades.fix.message.MassQuoteMsg}
     */
    DefOfferSize                                        (294),
    /**
     * The number of quote entries for a QuoteSet.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.QuoteCancelMsg}
     */
    NoQuoteEntries                                      (295),
    /**
     * The number of sets of quotes in the message.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.MassQuoteMsg}
     */
    NoQuoteSets                                         (296),
    /**
     * Identifies the status of the quote acknowledgement.<br/>
     * Type: Integer (see {@link QuoteStatus})<br/>
     * Used: {@link QuoteStatusReportMsg}
     */
    QuoteStatus                                         (297),
    /**
     * Identifies the type of quote cancel.<br/>
     * Type: Integer (see {@link QuoteCancelType} for standard values)<br/>
     * Used: {@link net.hades.fix.message.QuoteCancelMsg}
     */
    QuoteCancelType                                     (298),
    /**
     * Unique identifier for a quote. The QuoteEntryID stays with the quote as a static
     * identifier even if the quote is updated.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.group.QuoteEntryGroup}
     */
    QuoteEntryID                                        (299),
    /**
     * Reason Quote was rejected.<br/>
     * Type: Integer (see {@link QuoteRejectReason} for standard values)<br/>
     * Used: {@link QuoteStatusReportMsg}
     */
    QuoteRejectReason                                   (300),
    /**
     * Level of Response requested from receiver of quote messages. A default value should be bilaterally agreed.<br/>
     * Type: Integer (see {@link QuoteResponseLevel})<br/>
     * Used: {@link QuoteMsg}
     */
    QuoteResponseLevel                                  (301),
    /**
     * Unique id for the Quote Set.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.MassQuoteMsg}
     */
    QuoteSetID                                          (302),
    /**
     * Indicates the type of Quote Request being generated.<br/>
     * Type: Integer ({@link QuoteRequestType})<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    QuoteRequestType                                    (303),
    /**
     * Total number of quotes for the quote set.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.group.QuoteSetGroup}
     */
    TotNoQuoteEntries                                   (304),
    /**
     * Underlying security’s SecurityIDSource. Valid values: see See {@link TagNum#SecurityIDSource} (22) field.<br/>
     * Type: String ({@link SecurityIDSource})<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingSecurityIDSource                          (305),
    /**
     * Underlying security’s Issuer. See {@link TagNum#Issuer} (06) field for description.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingIssuer                                    (306),
    /**
     * Underlying security’s SecurityDesc. See {@link TagNum#SecurityDesc} (07) field for description.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingSecurityDesc                              (307),
    /**
     * Underlying security’s SecurityExchange. Can be used to identify the underlying security.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingSecurityExchange                          (308),
    /**
     * Underlying security’s SecurityID. See {@link TagNum#SecurityID} (48) field for description.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingSecurityID                                (309),
    /**
     * Underlying security’s SecurityType. Valid values: see SecurityType (167) field.
     * (see below for details concerning this fields use in conjunction with SecurityType=REPO)<br/>
     * The following applies when used in conjunction with SecurityType=REPO.
     * Represents the general or specific type of security that underlies a financing agreement.<br/>
     * If bonds of a particular issuer or country are wanted in an Order or are in the basket of an
     * Execution and the SecurityType is not granular enough, include the {@link TagNum#UnderlyingIssuer} (306),
     * {@link TagNum#UnderlyingCountryOfIssue} (592), {@link TagNum#UnderlyingProgram},
     * {@link TagNum#UnderlyingRegType} and/or {@link TagNum#UnderlyingStipulations} block e.g.<br/>
     * Type: String (See {@link SecurityType})<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingSecurityType                              (310),
    /**
     * Underlying security’s Symbol. See {@link TagNum#Symbol} (55) field for description.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingSymbol                                    (311),
    /**
     * Underlying security’s SymbolSfx. See {@link TagNum#SymbolSfx} (65) field for description.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingSymbolSfx                                 (312),
    /**
     * Underlying security’s MaturityMonthYear. Can be used with standardized derivatives vs. the
     * {@link UnderlyingMaturityDate} (542) field. {@link TagNum#See MaturityMonthYear} (200) field
     * for description.<br/>
     * Type: MonthYear<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingMaturityMonthYear                         (313),
    /**
     * Underlying security's MaturityDay. See MaturityDay field for description. Retired in version 4.3<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.group.QuoteSetGroup}
     */
    UnderlyingMaturityDay                               (314),
    /**
     * Put or call indicator the underlying security. See {@link TagNum#PutOrCall} field for description.<br/>
     * Type: Integer<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingPutOrCall                                 (315),
    /**
     * Underlying security’s StrikePrice. See {@link TagNum#StrikePrice} (202) field for description.<br/>
     * Type: Price<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingStrikePrice                               (316),
    /**
     * Underlying security’s OptAttribute. See {@link TagNum#OptAttribute} (206) field for description.<br/>
     * Type: Char<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingOptAttribute                              (317),
    /**
     * Underlying security’s Currency. See {@link TagNum#Currency} (5) field for description and valid values.<br/>
     * Type: Currency<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingCurrency                                  (318),
    /**
     * Quantity of a particular leg in the security.<br/>
     * Type: Double<br/>
     * Used:
     */
    RatioQty                                            (319),
    /**
     * Unique ID of a Security Definition Request.<br/>
     * Type: String<br/>
     * Used: {@link DerivativeSecurityListUpdateReportMsg},{@link SecurityDefinitionRequestMsg},{@link SecurityDefinitionMsg},
     * {@link SecurityTypeRequestMsg},{@link SecurityTypesMsg},{@link SecurityListRequestMsg},{@link SecurityListMsg},
     * {@link DerivativeSecurityListRequestMsg},{@link DerivativeSecurityListMsg},{@link SecurityDefinitionUpdateReportMsg},
     * {@link SecurityListUpdateReportMsg}
     */
    SecurityReqID                                       (320),
    /**
     * Type of Security Definition Request.<br/>
     * Type: Integer (see {@link SecurityRequestType})<br/>
     * Used: {@link SecurityDefinitionRequestMsg}
     */
    SecurityRequestType                                 (321),
    /**
     * Unique ID of a Security Definition message.<br/>
     * Type: String<br/>
     * Used: {@link DerivativeSecurityListUpdateReportMsg},{@link SecurityDefinitionMsg},{@link SecurityTypesMsg},
     * {@link SecurityListMsg},{@link DerivativeSecurityListMsg},{@link SecurityDefinitionUpdateReportMsg},
     * {@link SecurityListUpdateReportMsg}
     */
    SecurityResponseID                                  (322),
    /**
     * Type of Security Definition message response.<br/>
     * Type: Integer (see {@link SecurityResponseType})<br/>
     * Used: {@link DerivativeSecurityListUpdateReportMsg},{@link SecurityDefinitionMsg},{@link SecurityTypesMsg},
     * {@link SecurityListMsg},{@link DerivativeSecurityListMsg},{@link SecurityDefinitionUpdateReportMsg},
     * {@link SecurityListUpdateReportMsg}
     */
    SecurityResponseType                                (323),
    /**
     * Unique ID of a Security Status Request message.<br/>
     * Type: String<br/>
     * Used: {@link SecurityStatusRequestMsg},{@link SecurityStatusMsg}
     */
    SecurityStatusReqID                                 (324),
    /**
     * Indicates whether or not message is being sent as a result of a subscription request or not.<br/>
     * Valid Values:
     * <ul>
     *      <li>N - Message is being sent as a result of a prior request
     *      <li>Y - Message is being sent unsolicited
     * </ul>
     * Type: Boolean<br/>
     * Used: {@link TrdSessLstGrp},{@link SecurityStatusMsg},{@link TradingSessionStatusMsg},{@link TradeCaptureReportMsg},
     * {@link RequestForPositionsAckMsg},{@link PositionReportMsg}
     */
    UnsolicitedIndicator                                (325),
    /**
     * Identifies the trading status applicable to the transaction.<br/>
     * Type: Integer (see {@link SecurityTradingStatus} for standard values)<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp},{@link SecurityStatus}
     */
    SecurityTradingStatus                               (326),
    /**
     * Denotes the reason for the Opening Delay or Trading Halt.<br/>
     * Values "100" and above are reserved for bilaterally agreed upon user defined enumerations.<br/>
     * Type: Integer (see {@link HaltReason} for standard values)<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp},{@link SecurityStatus}
     */
    HaltReason                                          (327),
    /**
     * Indicates whether or not the halt was due to Common Stock trading being halted.
     * Valid Values:
     * <ul>
     *      <li>N - Halt was not related to a halt of the common stock
     *      <li>Y - Halt was due to common stock being halted<br/>
     * </ul>
     * Type: Boolean<br/>
     * Used: {@link SecurityStatus}
     */
    InViewOfCommon                                      (328),
    /**
     * Indicates whether or not the halt was due to the Related Security being halted.
     * Valid Values:
     * <ul>
     *      <li>N - Halt was not related to a halt of the related security
     *      <li>Y - Halt was due to related security being halted
     * </ul>
     * Type: Boolean<br/>
     * Used: {@link SecurityStatus}
     */
    DueToRelated                                        (329),
    /**
     * Quantity bought.<br/>
     * Type: Double<br/>
     * Used: {@link SecurityStatus}
     */
    BuyVolume                                           (330),
    /**
     * Quantity sold.<br/>
     * Type: Double<br/>
     * Used: {@link SecurityStatus}
     */
    SellVolume                                          (331),
    /**
     * Represents an indication of the high end of the price range for a security prior to the open or reopen.<br/>
     * Type: Double<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp},{@link SecurityStatus}
     */
    HighPx                                              (332),
    /**
     * Represents an indication of the low end of the price range for a security prior to the open or reopen.<br/>
     * Type: Double<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp},{@link SecurityStatus}
     */
    LowPx                                               (333),
    /**
     * Identifies the type of adjustment.<br/>
     * Type: Integer (see {@link Adjustment})<br/>
     * Used: {@link SecurityStatus}
     */
    Adjustment                                          (334),
    /**
     * Unique ID of a Trading Session Status message.<br/>
     * Type: String<br/>
     * Used: {@link TradingSessionListMsg},{@link TradingSessionListRequestMsg},{@link TradingSessionListUpdateReportMsg},
     * {@link TradingSessionStatusRequestMsg},{@link TradingSessionStatusMsg}
     */
    TradSesReqID                                        (335),
    /**
     * Identifier for Trading Session. A trading session spans an extended period of time 
     * that can also be expressed informally in terms of the trading day. 
     * Usage is determined by market or counterparties.<br/>
     * To specify good for session where session spans more than one calendar day, use 
     * TimeInForce = Day in conjunction with TradingSessionID.<br/>
     * Bilaterally agreed values of data type "String" that start with a character can 
     * be used for backward compatibility.<br/>
     * Type: String<br/>
     * Used: {@link AdvertismentMsg},{@link QuoteMsg}
     */
    TradingSessionID                                    (336),
    /**
     * Identifies the trader (e.g. "badge number) of the ContraBroker.<br/>
     * Type: String<br/>
     * Used: {@link ContraGrp}
     */
    ContraTrader                                        (337),
    /**
     * Method of trading.<br/>
     * Type: Integer (see {@link TradSesMethod})<br/>
     * Used: {@link TradingSessionListRequestMsg},{@link TrdSessLstGrp},{@link TradingSessionStatusRequestMsg},
     * {@link TradingSessionStatusMsg}
     */
    TradSesMethod                                       (338),
    /**
     * Trading Session Mode.<br/>
     * Type: Integer (see {@link TradSesMode})<br/>
     * Used: {@link TradingSessionListRequestMsg},{@link TrdSessLstGrp},{@link TradingSessionStatusRequestMsg},
     * {@link TradingSessionStatusMsg}
     */
    TradSesMode                                         (339),
    /**
     * State of the trading session.<br/>
     * Type: Integer (see {@link TradSesStatus})<br/>
     * Used: {@link TrdSessLstGrp},{@link TradingSessionStatusMsg}
     */
    TradSesStatus                                       (340),
    /**
     * Starting time of the trading session.<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link TrdSessLstGrp},{@link TradingSessionStatusMsg}
     */
    TradSesStartTime                                    (341),
    /**
     * Time of the opening of the trading session.<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link TrdSessLstGrp},{@link TradingSessionStatusMsg}
     */
    TradSesOpenTime                                     (342),
    /**
     * Time of the pre-closed of the trading session.<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link TrdSessLstGrp},{@link TradingSessionStatusMsg}
     */
    TradSesPreCloseTime                                 (343),
    /**
     * Closing time of the trading session.<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link TrdSessLstGrp},{@link TradingSessionStatusMsg}
     */
    TradSesCloseTime                                    (344),
    /**
     * End time of the trading session.<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link TrdSessLstGrp},{@link TradingSessionStatusMsg}
     */
    TradSesEndTime                                      (345),
    /**
     * Number of orders in the market.<br/>
     * Type: Integer<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    NumberOfOrders                                      (346),
    /**
     * Type of message encoding (non-ASCII characters) used in a message’s “Encoded” fields. 
     * Required if any “Encoding” fields are used.<br/>
     * Type: String<br/>
     * Used: Standard Header 4.2 up
     */
    MessageEncoding                                     (347),
    /**
     * Byte length of encoded (non-ASCII characters) EncodedIssuer (349) field. 
     * Type: Integer<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    EncodedIssuerLen                                    (348),
    /**
     * Encoded (non-ASCII characters) representation of the Issuer field in the encoded 
     * format specified via the MessageEncoding (347) field. If used, the ASCII (English) 
     * representation should also be specified in the Issuer field.<br/>
     * Type: data<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    EncodedIssuer                                       (349),
    /**
     * Byte length of encoded (non-ASCII characters) EncodedSecurityDesc (351) field.<br/>
     * Type: Integer<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    EncodedSecurityDescLen                              (350),
    /**
     * Encoded (non-ASCII characters) representation of the SecurityDesc (107) field in 
     * the encoded format specified via the MessageEncoding (347) field. 
     * If used, the ASCII (English) representation should also be specified in the SecurityDesc field.<br/>
     * Type: Data<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    EncodedSecurityDesc                                 (351),
    /**
     * Byte length of encoded (non-ASCII characters) EncodedListExecInst (353) field.<br/>
     * Type: Integer<br/>
     * Used: {@link NewOrderListMsg}
     */
    EncodedListExecInstLen                              (352),
    /**
     * Encoded (non-ASCII characters) representation of the {@link TagNum#ListExecInst} (69) field in the encoded format
     * specified via the {@link TagNum#MessageEncoding} (347) field. If used, the ASCII (English) representation should
     * also be specified in the ListExecInst field.<br/>
     * Type: Data<br/>
     * Used: {@link NewOrderListMsg}
     */
    EncodedListExecInst                                 (353),
    /**
     * Byte length of encoded (non-ASCII characters) EncodedText field.<br/>
     * Type: Integer<br/>
     * Used: {@link RejectMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link QuoteMsg}
     */
    EncodedTextLen                                      (354),
    /**
     * Encoded (non-ASCII characters) representation of the Text field in the encoded 
     * format specified via the MessageEncoding field. If used, the ASCII (English)
     * representation should also be specified in the Text field.<br/>
     * Type: Data<br/>
     * Used: {@link RejectMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg},{@link QuoteMsg}
     */
    EncodedText                                         (355),
    /**
     * Byte length of encoded (non-ASCII characters) {@link TagNum#EncodedSubject} (357) field.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.EmailMsg}
     */
    EncodedSubjectLen                                   (356),
    /**
     * Encoded (non-ASCII characters) representation of the {@link TagNum#Subject} (147) field in the encoded
     * format specified via the {@link TagNum#MessageEncoding} (347) field. If used, the ASCII (English)
     * representation should also be specified in the Subject field.<br/>
     * Type: Data<br/>
     * Used: {@link net.hades.fix.message.EmailMsg}
     */
    EncodedSubject                                      (357),
    /**
     * Byte length of encoded (non-ASCII characters) {@link TagNum#EncodedHeadline} (359) field.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.NewsMsg}
     */
    EncodedHeadlineLen                                  (358),
    /**
     * Encoded (non-ASCII characters) representation of the {@link TagNum#Headline} (148) field in the encoded format
     * specified via the {@link TagNum#MessageEncoding} (347) field. If used, the ASCII (English) representation
     * should also be specified in the Headline field.<br/>
     * Type: Data<br/>
     * Used: {@link net.hades.fix.message.NewsMsg}
     */
    EncodedHeadline                                     (359),
    /**
     * Byte length of encoded (non-ASCII characters) {@link TagNum#EncodedAllocText} (361) field.<br/>
     * Type: Integer<br/>
     * Used: {@link AllocAckGrp},{@link AllocGrp}
     */
    EncodedAllocTextLen                                 (360),
    /**
     * Encoded (non-ASCII characters) representation of the {@link TagNum#AllocText} (161) field in the encoded
     * format specified via the {@link TagNum#MessageEncoding} (347) field. If used, the ASCII (English)
     * representation should also be specified in the AllocText field.<br/>
     * Type: Data<br/>
     * Used: {@link AllocAckGrp},{@link AllocGrp}
     */
    EncodedAllocText                                    (361),
    /**
     * Byte length of encoded (non-ASCII characters). See {@link TagNum#EncodedUnderlyingIssuer} (363)
     * field for description.<br/>
     * Type: Integer<br/>
     * Used: {@link UnderlyingInstrument}
     */
    EncodedUnderlyingIssuerLen                          (362),
    /**
     * Encoded (non-ASCII characters) representation of the {@link TagNum#UnderlyingIssuer} (306) field
     * in the encoded format specified via the {@link TagNum#MessageEncoding} (347) field.
     * If used, the ASCII (English) representation should also be specified in the UnderlyingIssuer field.<br/>
     * Type: Data<br/>
     * Used: {@link UnderlyingInstrument}
     */
    EncodedUnderlyingIssuer                             (363),
    /**
     * Byte length of encoded (non-ASCII characters) {@link TagNum#EncodedUnderlyingSecurityDesc} (365) field.<br/>
     * Type: Length<br/>
     * Used: {@link UnderlyingInstrument}
     */
    EncodedUnderlyingSecurityDescLen                    (364),
    /**
     * Encoded (non-ASCII characters) representation of the {@link TagNum#UnderlyingSecurityDesc} (307) field
     * in the encoded format specified via the {@link TagNum#MessageEncoding} (347) field.
     * If used, the ASCII (English) representation should also be specified in the UnderlyingSecurityeDesc field.<br/>
     * Type: Data<br/>
     * Used: {@link UnderlyingInstrument}
     */
    EncodedUnderlyingSecurityDesc                       (365),
    /**
     * Executed price for an {@link TagNum#AllocAccount} (79) entry used when using "executed price" vs.
     * "average price" allocations (e.g. Japan).<br/>
     * Type: Double<br/>
     * Used: {@link AllocAckGrp},{@link AllocGrp}
     */
    AllocPrice                                          (366),
    /**
     * Indicates expiration time of this particular QuoteSet (always expressed in UTC
     * (Universal Time Coordinated, also known as "GMT).<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link net.hades.fix.message.group.QuoteSetGroup}
     */
    QuoteSetValidUntilTime                              (367),
    /**
     * Reason Quote Entry was rejected.<br/>
     * Type: Integer (see {@link QuoteEntryRejectReason} for standard values)<br/>
     * Used: {@link MassQuoteAckGroup}
     */
    QuoteEntryRejectReason                              (368),
    /**
     * The last MsgSeqNum value received and processed. Can be specified on every 
     * message sent. Useful for detecting a backlog with a counterparty.<br/>
     * Type: Integer<br/>
     * Used: Standard Header 4.2 up
     */
    LastMsgSeqNumProcessed                              (369),
    /**
     * Used when a message is sent via a “hub” or “service bureau”. If A
     * sends to Q (the hub) who then sends to B via a separate FIX session,
     * then when Q sends to B the value of this field should represent the
     * SendingTime on the message A sent to Q. (always expressed in UTC
     * (Universal Time Coordinated, also known as “GMT”).<br/>
     * Type: Timestamp<br/>
     * Used: Standard Header 4.2 and 4.3
     */
    OnBehalfOfSendingTime                               (370),
    /**
     * The tag number of the FIX field being referenced.<br/>
     * Type: Integer<br/>
     * Used: {@link RejectMsg}
     */
    RefTagID                                            (371),
    /**
     * Specifies a specific, supported MsgType. Required if NoMsgTypes is greater than 0.<br/>
     * Type: String(see {@link MsgType })<br/>
     * Used: {@link net.hades.fix.message.LogonMsg},{@link RejectMsg}
     */
    RefMsgType                                          (372),
    /**
     * Code to identify reason for a session-level Rejectmessage.<br/>
     * Type: Integer(see {@link SessionRejectReason})<br/>
     * Used: {@link RejectMsg}
     */
    SessionRejectReason                                 (373),
    /**
     * Identifies the Bid Request message type.<br/>
     * Type: Characetr (see {@link BidRequestTransType})<br/>
     * Used: {@link BidRequestMsg}
     */
    BidRequestTransType                                 (374),
    /**
     * Identifies contra broker. Standard NASD market-maker mnemonic is preferred.<br/>
     * Type: String<br/>
     * Used: {@link ContraGrp}
     */
    ContraBroker                                        (375),
    /**
     * ID used to represent this transaction for compliance purposes (e.g. OATS reporting).<br/>
     * Type: String<br/>
     * Used: {@link NewOrderSingleMsg},{@link OrderCancelRequestMsg},{@link OrderCancelReplaceRequestMsg},{@link ListOrdGrp},
     * {@link SideCrossOrdCxlGrp},{@link TrdCapRptSideGrp},{@link TrdCapRptAckSideGrp},{@link NewOrderCrossMsg},
     * {@link CrossOrderCancelReplaceRequestMsg},{@link NewOrderMultilegMsg},{@link MultilegOrderCancelReplaceMsg},{
     * @link ExecutionReportMsg},
     */
    ComplianceID                                        (376),
    /**
     * Indicates whether or not the order was solicited. Valid Values:
     * <ul>
     *      <li>N - Was not solicited
     *      <li>Y - Was solicited
     * </ul>
     * Type: Boolean<br/>
     * Used: {@link NewOrderSingleMsg},{@link OrderCancelRequestMsg},{@link ListOrdGrp},
     * {@link SideCrossOrdModGrp},{@link TrdCapRptSideGrp},{@link TrdCapRptAckSideGrp},
     * {@link NewOrderMultilegMsg},{@link MultilegOrderCancelReplaceMsg},{
     * @link ExecutionReportMsg},
     */
    SolicitedFlag                                       (377),
    /**
     * Code to identify reason for an ExecutionRpt message sent with ExecType=Restated or
     * used when communicating an unsolicited cancel.<br/>
     * Values "100" and above are reserved for bilaterally agreed upon user defined enumerations. <br/>
     * Type: Integer (see {@link ExecRestatementReason} for standard values)<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg},{@link ExecutionReportMsg}
     */
    ExecRestatementReason                               (378),
    /**
     * The value of the business-level "ID" field on the message being referenced.<br/>
     * Type: String<br/>
     * Used: {@link BusinessMessageRejectMsg}
     */
    BusinessRejectRefID                                 (379),
    /**
     * Code to identify reason for a Business Message Reject message.<br/>
     * Type: Integer (see {@link BusinessRejectReason})<br/>
     * Used: {@link BusinessMessageRejectMsg}
     */
    BusinessRejectReason                                (380),
    /**
     * Total amount traded (i.e. quantity * price) expressed in units of currency.
     * For FX Futures this is used to express the notional value of a fill when quantity
     * fields are expressed in terms of contract size (i.e. quantity * price * contract size).<br/>
     * Type: Double<br/>
     * Used: {@link AllocationInstructionMsg},{@link TradeCaptureReportMsg},{@link ConfirmationMsg},
     * {@link TradeCaptureReportAckMsg},{@link AllocationReportMsg},{@link ExecutionReportMsg},
     * {@link AAllocationInstructionAlertMsg}
     */
    GrossTradeAmt                                       (381),
    /**
     * The number of {@link TagNum#ContraBroker} (375) entries..<br/>
     * Type: Integer<br/>
     * Used: {@link ContraGrp}
     */
    NoContraBrokers                                     (382),
    /**
     * Can be used to specify the maximum number of bytes supported for messages received.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    MaxMessageSize                                      (383),
    /**
     * Specifies the number of repeating MsgTypes specified.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    NoMsgTypes                                          (384),
    /**
     * Indicates direction (send vs. receive) of a supported MsgType. Required if NoMsgTypes is > 0.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    MsgDirection                                        (385),
    /**
     * Number of TradingSessionIDs (336) in repeating group.<br/>
     * Type: Integer<br/>
     * Used: {@link TrdgSesGrp}
     */
    NoTradingSessions                                   (386),
    /**
     * Total volume (quantity) traded.<br/>
     * Type: Double<br/>
     * Used: {@link MarketDataSnapshotMsg}
     */
    TotalVolumeTraded                                   (387),
    /**
     * Code to identify the price a DiscretionOffsetValue is related to and should
     * be mathematically added to.<br/>
     * Type: Character (see {@link DiscretionInst})<br/>
     * Used:
     */
    DiscretionInst                                      (388),
    /**
     * Amount (signed) added to the <quote>related to</quote> price specified via DiscretionInst.<br/>
     * Type: PriceOffset<br/>
     * Used:
     */
    DiscretionOffsetValue                               (389),
    /**
     * Unique identifier for Bid Response as assigned by sell-side (broker, exchange, ECN).
     * Uniqueness must be guaranteed within a single trading day.<br/>
     * Type: String<br/>
     * Used: {@link NewOrderListMsg},{@link ListExecuteMsg},{@link BidRequestMsg},{@link BidResponseMsg}
     */
    BidID                                               (390),
    /**
     * Unique identifier for a Bid Request as assigned by institution.
     * Uniqueness must be guaranteed within a single trading day.<br/>
     * Type: String<br/>
     * Used: {@link NewOrderListMsg},{@link ListExecuteMsg},{@link BidRequestMsg},{@link BidResponseMsg}
     */
    ClientBidID                                         (391),
    /**
     * Descriptive name for list order.<br/>
     * Type: String<br/>
     * Used: {@link BidRequestMsg}
     */
    ListName                                            (392),
    /**
     * Total number of securities.<br/>
     * Type: Integer<br/>
     * Used: {@link DerivativeSecurityListUpdateReportMsg},{@link BidRequestMsg},{@link SecurityListMsg},
     * {@link DerivativeSecurityListMsg},{@link SecurityListUpdateReportMsg}
     */
    TotNoRelatedSym                                     (393),
    /**
     * Code to identify the type of Bid Request.<br/>
     * Type: Integer (see {@link BidType})<br/>
     * Used: {@link NewOrderListMsg},{@link BidRequestMsg}
     */
    BidType                                             (394),
    /**
     * Total number of tickets.<br/>
     * Type: Integer<br/>
     * Used: {@link BidRequestMsg}
     */
    NumTickets                                          (395),
    /**
     * Amounts in currency.<br/>
     * Type: Double<br/>
     * Used: {@link BidRequestMsg}
     */
    SideValue1                                          (396),
    /**
     * Amounts in currency.<br/>
     * Type: Double<br/>
     * Used: {@link BidRequestMsg}
     */
    SideValue2                                          (397),
    /**
     * Number of {@link TagNum#BidDescriptor} (400) entries.<br/>
     * Type: Integer<br/>
     * Used: {@link BidDescReqGrp}
     */
    NoBidDescriptors                                    (398),
    /**
     * Code to identify the type of {@link TagNum#BidDescriptor} (400).<br/>
     * Type: Integer (see {@link BidDescriptorType})<br/>
     * Used: {@link BidDescReqGrp}
     */
    BidDescriptorType                                   (399),
    /**
     * BidDescriptor value. Usage depends upon {@link TagNum#BidDescriptorType} (399).<br/>
     * If BidDescriptorType = 1 Industrials etc - Free text<br/>
     * If BidDescriptorType = 2 "FR" etc - ISO Country Codes<br/>
     * If BidDescriptorType = 3 FT00, FT250, STOX - Free text.<br/>
     * Type: String<br/>
     * Used: {@link BidDescReqGrp}
     */
    BidDescriptor                                       (400),
    /**
     * Code to identify which "SideValue" the value refers to.
     * SideValue1 and SideValue2 are used as opposed to Buy or Sell so that the basket
     * can be quoted either way as Buy or Sell.<br/>
     * Type: Integer (see {@link SideValueInd})<br/>
     * Used: {@link BidDescReqGrp},@link ListOrdGrpGrp}
     */
    SideValueInd                                        (401),
    /**
     * Liquidity indicator or lower limit if {@link TagNum#TotalNumSecurities} (393) > 1. Represented as a percentage.<br/>
     * Type: Double<br/>
     * Used: {@link BidDescReqGrp}
     */
    LiquidityPctLow                                     (402),
    /**
     * Upper liquidity indicator if {@link TagNum#TotalNumSecurities} (393) > 1. Represented as a percentage.<br/>
     * Type: Double<br/>
     * Used: {@link BidDescReqGrp}
     */
    LiquidityPctHigh                                    (403),
    /**
     * Value between {@link TagNum#LiquidityPctLow} (402) and {@link TagNum#LiquidityPctHigh} (403) in Currency.<br/>
     * Type: Double<br/>
     * Used: {@link BidDescReqGrp}
     */
    LiquidityValue                                      (404),
    /**
     * Eg Used in EFP trades 2% (EFP - Exchange for Physical ). Represented as a percentage.<br/>
     * Type: Double<br/>
     * Used: {@link BidDescReqGrp}
     */
    EFPTrackingError                                    (405),
    /**
     * Used in EFP trades.<br/>
     * Type: Double<br/>
     * Used: {@link BidCompRspGrp},{@link BidDescReqGrp}
     */
    FairValue                                           (406),
    /**
     * Used in EFP trades. Represented as a percentage.<br/>
     * Type: Double<br/>
     * Used: {@link BidDescReqGrp}
     */
    OutsideIndexPct                                     (407),
    /**
     * Used in EFP trades.<br/>
     * Type: Double<br/>
     * Used: {@link BidDescReqGrp}
     */
    ValueOfFutures                                      (408),
    /**
     * Code to identify the type of liquidity indicator.<br/>
     * Type: Integer (see {@link LiquidityIndType})<br/>
     * Used: {@link BidRequestMsg}
     */
    LiquidityIndType                                    (409),
    /**
     * Overall weighted average liquidity expressed as a % of average daily volume. Represented as a percentage.<br/>
     * Type: Double<br/>
     * Used: {@link BidRequestMsg}
     */
    WtAverageLiquidity                                  (410),
    /**
     * Indicates whether or not to exchange for phsyical.<br/>
     * Type: Boolean<br/>
     * Used: {@link BidRequestMsg}
     */
    ExchangeForPhysical                                 (411),
    /**
     * Value of stocks in Currency.<br/>
     * Type: Double<br/>
     * Used: {@link BidRequestMsg}
     */
    OutMainCntryUIndex                                  (412),
    /**
     * Percentage of program that crosses in Currency. Represented as a percentage.<br/>
     * Type: Double<br/>
     * Used: {@link BidRequestMsg}
     */
    CrossPercent                                        (413),
    /**
     * Code to identify the desired frequency of progress reports.<br/>
     * Type: Integer (see {@link ProgRptReqs})<br/>
     * Used: {@link NewOrderListMsg},{@link BidRequestMsg}
     */
    ProgRptReqs                                         (414),
    /**
     * Time in minutes between each ListStatus report sent by SellSide. Zero means don't send status.<br/>
     * Type: Integer<br/>
     * Used: {@link NewOrderListMsg},{@link BidRequestMsg}
     */
    ProgPeriodInterval                                  (415),
    /**
     * Code to represent whether value is net (inclusive of tax) or gross.<br/>
     * Type: Integer (see {@link IncTaxInd})<br/>
     * Used: {@link BidRequestMsg}
     */
    IncTaxInd                                           (416),
    /**
     * Indicates the total number of bidders on the list.<br/>
     * Type: Integer <br/>
     * Used: {@link BidRequestMsg}
     */
    NumBidders                                          (417),
    /**
     * Code to represent the type of trade. (Prior to FIX 4.4 this field was named "TradeType)<br/>
     * Type: Character (see {@link BidTradeType})<br/>
     * Used: {@link BidRequestMsg}
     */
    BidTradeType                                        (418),
    /**
     * Code to represent the basis price type.<br/>
     * Type: Character (see {@link BasisPxType})<br/>
     * Used: {@link BidRequestMsg}
     */
    BasisPxType                                         (419),
    /**
     * Indicates the number of list entries.<br/>
     * Type: Integer<br/>
     * Used: {@link BidCompReqGrp},{@link BidCompRspGrp}
     */
    NoBidComponents                                     (420),
    /**
     * ISO Country Code in field.<br/>
     * Type: String ((see {@link Country})<br/>
     * Used: {@link BidCompRspGrp}
     */
    Country                                             (421),
    /**
     *Total number of strike price entries across all messages. Should be the sum of all
     * {@link TagNum#NoStrikes} (428) in each message that has repeating strike price entries related
     * to the same {@link TagNum#ListID} (66). Used to support fragmentation.<br/>
     * Type: Integer<br/>
     * Used: {@link ListStrikePriceMsg}
     */
    TotNoStrikes                                        (422),
    /**
     * Code to represent the price type.
     * (For Financing transactions PriceType implies the "repo type" – Fixed or Floating – 9 (Yield)
     * or 6 (Spread) respectively - and Price (44) gives the corresponding "repo rate".<br/>
     * Type: Integer (see {@link PriceType})<br/>
     * Used: {@link IOIMsg},{@link QuoteMsg}
     */
    PriceType                                           (423),
    /**
     * For GT orders, the OrderQty (38) less all quantity (adjusted for stock splits) that
     * traded on previous days. DayOrderQty (424) = OrderQty - (CumQty (14) - DayCumQty (425)).<br/>
     * Type: Double<br/>
     * Used: {@link ExecutionReportMsg}
     */
    DayOrderQty                                         (424),
    /**
     * Quantity on a GT order that has traded today.<br/>
     * Type: Double<br/>
     * Used: {@link ExecutionReportMsg}
     */
    DayCumQty                                           (425),
    /**
     * The average price for quantity on a GT order that has traded today.<br/>
     * Type: Double<br/>
     * Used: {@link ExecutionReportMsg}
     */
    DayAvgPx                                            (426),
    /**
     * Code to identify whether to book out executions on a part-filled GT order on the day of
     * execution or to accumulate.<br/>
     * Type: Integer (see {@link GTBookingInst})<br/>
     * Used: {@link NewOrderSingleMsg},{@link OrderCancelReplaceRequestMsg},{@link ListOrdGrp},{@link NewOrderCrossMsg},
     * {@link CrossOrderCancelReplaceRequestMsg},{@link NewOrderMultilegMsg},{@link MultilegOrderCancelReplaceMsg},
     * {@link ExecutionReportMsg}
     */
    GTBookingInst                                       (427),
    /**
     * Number of list strike price entries.<br/>
     * Type: Integer<br/>
     * Used: {@link InstrmtStrkPxGrp}
     */
    NoStrikes                                           (428),
    /**
     * Code to represent the status type.<br/>
     * Type: Integer (see {@link ListStatusType})<br/>
     * Used: {@link ListStatusMsg}
     */
    ListStatusType                                      (429),
    /**
     * Code to represent whether value is net (inclusive of tax) or gross.<br/>
     * Type: Integer (see {@link NetGrossInd})<br/>
     * Used: {@link BidCompReqGrp},{@link BidCompRspGrp},{@link TrdCapRptSideGrp},{@link TrdCapRptAckSideGrp},
     * {@link SettlObligationInstructionsMsg}
     */
    NetGrossInd                                         (430),
    /**
     * Code to represent the status of a list order.<br/>
     * Type: Integer (see {@link ListOrderStatus})<br/>
     * Used: {@link ListStatusMsg}
     */
    ListOrderStatus                                     (431),
    /**
     * Date of order expiration (last day the order can trade), always expressed in terms of
     * the local market date. The time at which the order expires is determined by the
     * local market’s business practices.<br/>
     * Type: LocalMkt Date<br/>
     * Used:
     */
    ExpireDate                                          (432),
    /**
     * Identifies the type of {@link TagNum#ListExecInst} (69).<br/>
     * Type: Character (see {@link ListExecInstType})<br/>
     * Used: {@link NewOrderListMsg}
     */
    ListExecInstType                                    (433),
    /**
     * Identifies the type of request that a cancel replace request is in response to.<br/>
     * Type: Int (CxlRejResponseTo)<br/>
     * Used: Cancel Reject
     */
    CxlRejResponseTo                                    (434),
    /**
     * Underlying security’s CouponRate. See {@link TagNum#CouponRate} (223) field for description.<br/>
     * Type: Percentage<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingCouponRate                                (435),
    /**
     * Underlying security’s ContractMultiplier.. See {@link TagNum#ContractMultiplier} (231) field
     * for description.<br/>
     * Type: Float<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingContractMultiplier                        (436),
    /**
     * Quantity traded with the {@link TagNum#ContraBroker} (375).<br/>
     * Type: Double<br/>
     * Used: {@link ContraGrp}
     */
    ContraTradeQty                                      (437),
    /**
     * Identifes the time of the trade with the ContraBroker (375).
     * (always expressed in UTC (Universal Time Coordinated, also known as "GMT).<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link ContraGrp}
     */
    ContraTradeTime                                     (438),
    /**
     * Firm that will clear the trade. Used if different than the executing firm.<br/>
     * Type: String<br/>
     * Used: {@link NewOrderMsg},{@link ExecutionReportMsg}
     */
    ClearingFirm                                        (439),
    /**
     * Supplemental accounting information forwarded to clearing house/firm.<br/>
     * IB: Extra user-defined required field for Non-IB Clearing CTCI
     * Accounts when placing futures and futures options orders.<br/>
     * Type: String<br/>
     * Used: {@link NewOrderMsg},{@link ExecutionReportMsg}
     */
    ClearingAccount                                     (440),
    /**
     * Number of Securites between {@link TagNum#LiquidityPctLow} (402) and {@link TagNum#LiquidityPctHigh}
     * (403) in Currency.<br/>
     * Type: Integer<br/>
     * Used: {@link BidDescReqGrp},
     */
    LiquidityNumSecurities                              (441),
    /**
     * Used to indicate what an Execution Report represents (e.g. used with multi-leg
     * securities, such as option strategies, spreads, etc.).<br/>
     * Type: Char (see {@link MultiLegReportingType})<br/>
     * Used: {@link ExecutionReportMsg}
     */
    MultiLegReportingType                               (442),
    /**
     * The time at which current market prices are used to determine the value of a basket.<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link BidRequestMsg}
     */
    StrikeTime                                          (443),
    /**
     * Free format text string related to List Status.<br/>
     * Type: String<br/>
     * Used: {@link ListStatusMsg}
     */
    ListStatusText                                      (444),
    /**
     * Byte length of encoded (non-ASCII characters) {@link TagNum#EncodedListStatusText} (446) field.<br/>
     * Type: Integer<br/>
     * Used: {@link ListStatusMsg}
     */
    EncodedListStatusTextLen                            (445),
    /**
     * Encoded (non-ASCII characters) representation of the {@link TagNum#ListStatusText} (444) field in the
     * encoded format specified via the {@link TagNum#MessageEncoding} (347) field. If used, the ASCII (English)
     * representation should also be specified in the ListStatusText field.<br/>
     * Type: Data<br/>
     * Used: {@link ListStatusMsg}
     */
    EncodedListStatusText                               (446),
    /**
     * Identifies class or source of the PartyID (448) value. Required if PartyID is specified.
     * Note: applicable values depend upon {@link TagNum#PartyRole} (452) specified.
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Parties}
     */
    PartyIDSource                                       (447),
    /**
     * Party identifier/code. See {@link TagNum#PartyIDSource} (447) and {@link TagNum#PartyRole} (452).
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Parties}
     */
    PartyID                                             (448),
    /**
     * Date of TotalVolumeTraded (387). (prior to FIX 4.4 field was of type UTCDate).<br/>
     * Type: UTCDateOnly<br/>
     * Used: {@link MarketDataSnapshot}
     */
    TotalVolumeTradedDate                               (449),
    /**
     * Time of TotalVolumeTraded (387).<br/>
     * Type: UTCTimeOnly<br/>
     * Used: {@link MarketDataSnapshot}
     */
    TotalVolumeTradedTime                               (450),
    /**
     * Net change from previous day's closing price vs. last traded price.<br/>
     * Type: Double<br/>
     * Used: {@link MarketDataSnapshot}
     */
     NetChgPrevDay                                      (451),
    /**
     * Number of {@link TagNum#PartyID} (448), {@link TagNum#PartyIDSource} (447), and
     * {@link TagNum#PartyRole} (452) entries.
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.Parties}
     */
    PartyRole                                           (452),
    /**
     * Identifies the type or role of the {@link TagNum#PartyID} (448) specified.
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.Parties}
     */
    NoPartyIDs                                          (453),
    /**
     * Number of SecurityAltID (455) entries.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    NoSecurityAltID                                     (454),
    /**
     * Alternate Security identifier value for this security of SecurityAltIDSource (456) type 
     * (e.g. CUSIP, SEDOL, ISIN, etc). Requires SecurityAltIDSource.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    SecurityAltID                                       (455),
    /**
     * Identifies class or source of the SecurityAltID (455) value. Required if SecurityAltID is specified.<br/>
     * Valid values:<br/>
     * Same valid values as the SecurityIDSource (22) field<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    SecurityAltIDSource                                 (456),
    /**
     * Number of {@link TagNum#UnderlyingSecurityAltID} (458) entries.<br/>
     * Type: Integer<br/>
     * Used: {@link UnderlyingInstrument}
     */
    NoUnderlyingSecurityAltID                           (457),
    /**
     * Alternate Security identifier value for this underlying security of
     * {@link TagNum#UnderlyingSecurityAltIDSource} (459) type (e.g. CUSIP, SEDOL, ISIN, etc).
     * Requires {@link TagNum#UnderlyingSecurityAltIDSource}.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingSecurityAltID                             (458),
    /**
     * Identifies class or source of the {@link TagNum#UnderlyingSecurityAltID} (458) value.
     * Required if UnderlyingSecurityAltID is specified.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingSecurityAltIDSource                       (459),
    /**
     * Indicates the type of product the security is associated with. 
     * See also the CFICode (461) and SecurityType (167) fields.<br/>
     * Type: Integer (ProductType)<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    Product                                             (460),
    /**
     * Indicates the type of security using ISO 10962 standard, Classification of Financial
     * Instruments (CFI code) values. ISO 10962 is maintained by ANNA (Association 
     * of National Numbering Agencies) acting as Registration Authority.<br/>
     * Type: String<br/>
     * Used: 
     */
    CFICode                                             (461),
    /**
     * Underlying security’s Product. Valid values: see {@link TagNum#Product}(460) field.<br/>
     * Type: Integer<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingProduct                                   (462),
    /**
     * Underlying security’s CFICode. Valid values: see {@link TagNum#CFICode} (461) field.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingCFICode                                   (463),
    /**
     * Can be used to specify that this FIX session will be sending and receiving “test” vs. 
     * “production” messages.<br/>
     * Type: Boolean<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    TestMessageIndicator                                (464),
    /**
     * Deprecated in FIX.4.4 Designates the type of quantities (e.g. OrderQty) specified.
     * Used for MBS and TIPS Fixed Income security types.<br/>
     * Type: Integer (see {@link QuantityType})<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}, {@link IOIMsg}
     */
    QuantityType                                        (465),
    /**
     * Common reference passed to a post-trade booking process (e.g. industry matching utility).<br/>
     * Type: String<br/>
     * Used: {@link AllocationInstructionMsg},{@link AllocationReportMsg},{@link AllocationInstructionAlertMsg}
     */
    BookingRefID                                        (466),
    /**
     * Unique identifier for a specific {@link TagNum#NoAllocs} (78) repeating group instance
     * (e.g. for an AllocAccount).<br/>
     * Type: String<br/>
     * Used: {@link AllocationInstructionMsg},{@link AllocationReportMsg},{@link AllocationInstructionAlertMsg}
     */
    IndividualAllocID                                   (467),
    /**
     * Specifies which direction to round. For CIV – indicates whether or not the quantity of
     * shares/units is to be rounded and in which direction where {@link TagNum#CashOrdQty} (152) or
     * (for CIV only) {@link TagNum#OrderPercent} (516) are specified on an order.
     * The default is for rounding to be at the discretion of the executing broker or fund manager.<br/>
     * e.g. for an order specifying CashOrdQty or OrderPercent if the calculated number of
     * shares/units was 325.76 and RoundingModulus (469) was 0 - "round down" would give 320 units,
     * 1 - "round up" would give 330 units and "round to nearest" would give 320 units.<br/>
     * Type: Character (see {@link RoundingDirection})<br/>
     * Used: {@link OrderQtyData}
     */
    RoundingDirection                                   (468),
    /**
     * For CIV - a float value indicating the value to which rounding is required.<br/>
     * i.e. 0 means round to a multiple of 0 units/shares; 0.5 means round to a multiple of
     * 0.5 units/shares.<br/>
     * The default, if RoundingDirection (468) is specified without RoundingModulus, is to
     * round to a whole unit/share.<br/>
     * Type: Float<br/>
     * Used: {@link OrderQtyData}
     */
    RoundingModulus                                     (469),
    /**
     * ISO Country code of instrument issue (e.g. the country portion typically used in ISIN). 
     * Can be used in conjunction with non-ISIN SecurityID (48) (e.g. CUSIP for Municipal Bonds 
     * without ISIN) to provide uniqueness.<br/>
     * Type: String (see {@link Country})<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    CountryOfIssue                                      (470),
    /**
     * A two-character state or province abbreviation.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    StateOrProvinceOfIssue                              (471),
    /**
     * Identifies the locale. For Municipal Security Issuers other than state or province. Refer to
     * http://www.atmos.albany.edu/cgi/stagrep-cgi<br/>
     * Reference the IATA city codes for values.<br/>
     * Note IATA (International Air Transport Association) maintains the codes at www.iata.org.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    LocaleOfIssue                                       (472),
    /**
     * The number of registration details on a Registration Instructions message.<br/>
     * Type: Integer<br/>
     * Used: {@link RgstDtlsGrp}
     */
    NoRegistDtls                                        (473),
    /**
     * Set of Correspondence address details, possibly including phone, fax, etc.<br/>
     * Type: String<br/>
     * Used: {@link RgstDtlsGrp}
     */
    MailingDtls                                         (474),
    /**
     * The ISO 366 Country code (2 character) identifying which country the beneficial investor
     * is resident for tax purposes.<br/>
     * Type: String (see {@link Country})<br/>
     * Used: {@link RgstDtlsGrp}
     */
    InvestorCountryOfResidence                          (475),
    /**
     * "Settlement Payment Reference" - A free format Payment reference to assist with
     * reconciliation, e.g. a Client and/or Order ID number.<br/>
     * Type: String<br/>
     * Used: {@link SettlInstGrp}
     */
    PaymentRef                                          (476),
    /**
     * A code identifying the payment method for a (fractional) distribution.
     * 13 through 998 are reserved for future use
     * Values above 1000 are available for use by private agreement among counterparties<br/>
     * Type: Integer (see {@link DistribPaymentMethod} for reserved values)<br/>
     * Used: {@link RgstDistInstGrp}
     */
    DistribPaymentMethod                                (477),
    /**
     * Specifies currency to be used for Cash Distributions.<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: {@link RgstDistInstGrp}
     */
    CashDistribCurr                                     (478),
    /**
     * Specifies currency to be use for Commission (12) if the Commission currency is different
     * from the Deal Currency.<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: {@link CommissionData}
     */
    CommCurrency                                        (479),
    /**
     * For CIV - A one character code identifying whether Cancellation rights/Cooling off period applies.<br/>
     * Type: Character (see {@link CancellationRights})<br/>
     * Used: {@link NewOrderSingleMsg}, {@link NewOrderListMsg}, {@link OrderCancelReplaceRequestMsg},
     * {@link NewOrderCrossMsg},{@link CrossOrderCancelReplaceRequestMsg}, {@link NewOrderMultilegMsg},
     * {@link MultilegOrderCancelReplaceMsg},{@link ExecutionReportMsg}
     */
    CancellationRights                                  (480),
    /**
     * A one character code identifying Money laundering status.<br/>
     * Type: Character (see {@link MoneyLaunderingStatus})<br/>
     * Used: {@link NewOrderSingleMsg}, {@link NewOrderListMsg}, {@link OrderCancelReplaceRequestMsg},
     * {@link NewOrderCrossMsg},{@link CrossOrderCancelReplaceRequestMsg}, {@link NewOrderMultilegMsg},
     * {@link MultilegOrderCancelReplaceMsg},{@link ExecutionReportMsg}
     */
    MoneyLaunderingStatus                               (481),
    /**
     * Free format text to specify mailing instruction requirements, e.g. "no third party mailings".<br/>
     * Type: String<br/>
     * Used: {@link RgstDtlsGrp}
     */
    MailingInst                                         (482),
    /**
     * For CIV A date and time stamp to indicate the time a CIV order was booked by the fund manager.
     * For derivatives a date and time stamp to indicate when this order was booked with the agent prior to
     * submission to the VMU. Indicates the time at which the order was finalized between the
     * buyer and seller prior to submission.<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link MDIncGrp},{@link TradeReportOrderDetailMsg},{@link NewOrderCrossMsg},
     * {@link CrossOrderCancelReplaceRequestMsg},{@link ExecutionReportMsg}
     */
    TransBkdTime                                        (483),
    /**
     * For CIV - Identifies how the execution price {@link TagNum#LastPx} (31) was calculated from the fund
     * unit/share price(s) calculated at the fund valuation point.<br/>
     * Type: Character (see {@link ExecPriceType})<br/>
     * Used: {@link ExecutionReportMsg}
     */
    ExecPriceType                                       (484),
    /**
     * For CIV the amount or percentage by which the fund unit/share price was adjusted,
     * as indicated by {@link TagNum#ExecPriceType} (484).<br/>
     * Type: Double<br/>
     * Used: {@link ExecutionReportMsg}
     */
    ExecPriceAdjustment                                 (485),
    /**
     * The date of birth applicable to the individual, e.g. required to open some types of tax-exempt account.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link RgstDtlsGrp}
     */
    DateOfBirth                                         (486),
    /**
     * Identifies Trade Report message transaction type.<br/>
     * Type: Integer (see {@link TradeReportTransType})<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    TradeReportTransType                                (487),
    /**
     * The name of the payment card holder as specified on the card being used for payment.<br/>
     * Type: String<br/>
     * Used: {@link SettlInstGrp}
     */
    CardHolderName                                      (488),
    /**
     * The number of the payment card as specified on the card being used for payment.<br/>
     * Type: String<br/>
     * Used: {@link SettlInstGrp}
     */
    CardNumber                                          (489),
    /**
     * The expiry date of the payment card as specified on the card being used for payment.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link SettlInstGrp}
     */
    CardExpDate                                         (490),
    /**
     * The issue number of the payment card as specified on the card being used for payment.
     * This is only applicable to certain types of card.<br/>
     * Type: String<br/>
     * Used: {@link SettlInstGrp}
     */
    CardIssNum                                          (491),
    /**
     * A code identifying the Settlement payment method. 16 through 998 are reserved for future use
     * Values above 1000 are available for use by private agreement among counterparties.<br/>
     * Type: Integer (see {@link PaymentMethod} for standard values)<br/>
     * Used: {@link SettlInstGrp}
     */
    PaymentMethod                                       (492),
    /**
     * For CIV - a fund manager-defined code identifying which of the fund manager's account types is required.<br/>
     * Type: String<br/>
     * Used: {@link RegistrationInstructions}
     */
    RegistAcctType                                      (493),
    /**
     * Free format text defining the designation to be associated with a holding on the register.
     * Used to identify assets of a specific underlying investor using a common registration,
     * e.g. a broker's nominee or street name.<br/>
     * Type: String<br/>
     * Used: {@link NewOrderSingleMsg}, {@link ListOrdGrp}, {@link OrderCancelReplaceRequestMsg},
     * {@link NewOrderCrossMsg},{@link CrossOrderCancelReplaceRequestMsg}, {@link NewOrderMultilegMsg},
     * {@link MultilegOrderCancelReplaceMsg},{@link ExecutionReportMsg}
     */
    Designation                                         (494),
    /**
     * For CIV - a code identifying the type of tax exempt account in which purchased shares/units are to be held.
     * 30 - 998 are reserved for future use by recognized taxation authorities
     * 999 = Other
     * values above 1000 are available for use by private agreement among counterparties.<br/>
     * Type:  Integer (see {@link TaxAdvantageType} for standard values)<br/>
     * Used: {@link RegistrationInstructions}
     */
    TaxAdvantageType                                    (495),
    /**
     * Text indicating reason(s) why a Registration Instruction has been rejected.<br/>
     * Type: String<br/>
     * Used: {@link RegistrationInstructionsResponse}
     */
    RegistRejReasonText                                 (496),
    /**
     * A one character code identifying whether the Fund based renewal commission is to be waived.<br/>
     * Type: Character  (see {@link FundRenewWaiv})<br/>
     * Used: {@link CommissionData}
     */
    FundRenewWaiv                                       (497),
    /**
     * Name of local agent bank if for cash distributions.<br/>
     * Type: String<br/>
     * Used: {@link RgstDistInstGrp}
     */
    CashDistribAgentName                                (498),
    /**
     * BIC (Bank Identification Code--Swift managed) code of agent bank for cash distributions.<br/>
     * Type: String<br/>
     * Used: {@link RgstDistInstGrp}
     */
    CashDistribAgentCode                                (499),
    /**
     * Account number at agent bank for distributions.<br/>
     * Type: String<br/>
     * Used: {@link RgstDistInstGrp}
     */
    CashDistribAgentAcctNumber                          (500),
    /**
     * Free format Payment reference to assist with reconciliation of distributions.<br/>
     * Type: String<br/>
     * Used: {@link RgstDistInstGrp}
     */
    CashDistribPayRef                                   (501),
    /**
     * Name of account at agent bank for distributions.<br/>
     * Type: String<br/>
     * Used: {@link RgstDistInstGrp}
     */
    CashDistribAgentAcctName                            (502),
    /**
     * The start date of the card as specified on the card being used for payment.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link SettlInstGrp}
     */
    CardStartDate                                       (503),
    /**
     * The date written on a cheque or date payment should be submitted to the relevant clearing system.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link SettlInstGrp}
     */
    PaymentDate                                         (504),
    /**
     * Identifies sender of a payment, e.g. the payment remitter or a customer reference number.<br/>
     * Type: String<br/>
     * Used: {@link SettlInstGrp}
     */
    PaymentRemitterID                                   (505),
    /**
     * Registration status as returned by the broker or (for CIV) the fund manager.<br/>
     * Type: Character  (see {@link RegistStatus})<br/>
     * Used: {@link RegistrationInstructionsResponseMsg},{@link PositionReportMsg}
     */
    RegistStatus                                        (506),
    /**
     * Reason(s) why Registration Instructions has been rejected. The reason may be further amplified
     * in the RegistRejReasonCode field.<br/>
     * Type: Integer (see {@link RegistRejReasonCode} for standard values)<br/>
     * Used: {@link RegistrationInstructionsResponseMsg}
     */
    RegistRejReasonCode                                 (507),
    /**
     * Reference identifier for the {@link TagNum#RegistID} (53) with Cancel and Replace {@link TagNum#RegistTransType}
     * (54) transaction types.<br/>
     * Type: String<br/>
     * Used: {@link RegistrationInstructionsMsg},{@link RegistrationInstructionsResponseMsg}
     */
    RegistRefID                                         (508),
    /**
     * Set of Registration name and address details, possibly including phone, fax etc.<br/>
     * Type: String<br/>
     * Used: {@link RgstDtlsGrp}
     */
    RegistDtls                                          (509),
    /**
     * The number of Distribution Instructions on a Registration Instructions message.<br/>
     * Type: Integer<br/>
     * Used: {@link RgstDistInstGrp}
     */
    NoDistribInsts                                      (510),
    /**
     * Email address relating to Registration name and address details.<br/>
     * Type: String<br/>
     * Used: {@link RgstDtlsGrp}
     */
    RegistEmail                                         (511),
    /**
     * The amount of each distribution to go to this beneficiary, expressed as a percentage.<br/>
     * Type: Double<br/>
     * Used: {@link RgstDistInstGrp}
     */
    DistribPercentage                                   (512),
    /**
     * Unique identifier of the registration details as assigned by institution or intermediary.<br/>
     * Type: String<br/>
     * Used: {@link NewOrderSingleMsg},{@link NewOrderListMsg},{@link OrderCancelReplaceRequestMsg},
     * {@link RegistrationInstructionsMsg},{@link RegistrationInstructionsResponseMsg},{@link NewOrderCrossMsg},
     * {@link CrossOrderCancelReplaceRequestMsg},{@link NewOrderMultilegMsg},{@link MultilegOrderCancelReplaceMsg},
     * {@link ExecutionReportMsg},
     */
    RegistID                                            (513),
    /**
     * Identifies Registration Instructions transaction type.<br/>
     * Type: Character  (see {@link RegistTransType})<br/>
     * Used: {@link RegistrationInstructionsResponseMsg},{@link RegistrationInstructionsMsg}
     */
    RegistTransType                                     (514),
    /**
     * For CIV - a date and time stamp to indicate the fund valuation point with respect to
     * which a order was priced by the fund manager.<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link ExecutionReportMsg}
     */
    ExecValuationPoint                                  (515),
    /**
     * For CIV specifies the approximate order quantity desired. For a CIV Sale it specifies percentage
     * of investor’s total holding to be sold. For a CIV switch/exchange it specifies percentage of
     * investor’s cash realised from sales to be re-invested. The executing broker, intermediary or
     * fund manager is responsible for converting and calculating  {@link TagNum#OrderQty} (38) in
     * shares/units for subsequent messages.<br/>
     * Type: Percentage<br/>
     * Used: {@link OrderQtyData}
     */
    OrderPercent                                        (516),
    /**
     * The relationship between Registration parties.<br/>
     * Type: Character  (see {@link OwnershipType})<br/>
     * Used: {@link RegistrationInstructionsMsg}
     */
    OwnershipType                                       (517),
    /**
     * The number of Contract Amount details on an Execution Report message.<br/>
     * Type: Integer<br/>
     * Used: {@link ContAmtGrp}
     */
    NoContAmts                                          (518),
    /**
     * Type of {@link TagNum#ContAmtValue} (520).
     * NOTE That Commission Amount / % in Contract Amounts is the commission actually charged,
     * rather than the commission instructions given in Fields 2/3.<br/>
     * Type: Integer (see {@link ContAmtType} for standard value)<br/>
     * Used: {@link ContAmtGrp}
     */
    ContAmtType                                         (519),
    /**
     * Value of Contract Amount, e.g. a financial amount or percentage as indicated by
     * {@link TagNum#ContAmtType} (519).<br/>
     * Type: Double<br/>
     * Used: {@link ContAmtGrp}
     */
    ContAmtValue                                        (520),
    /**
     * Specifies currency for the Contract amount if different from the Deal Currency.<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: {@link ContAmtGrp}
     */
    ContAmtCurr                                         (521),
    /**
     * Identifies the type of owner.<br/>
     * Type: Integer (see {@link OwnerType})<br/>
     * Used: {@link RgstDtlsGrp}
     */
    OwnerType                                           (522),
    /**
     * Sub-identifier (e.g. Clearing Account for {@link TagNum#PartyRole} (452)=Clearing Firm,
     * Locate ID # for {@link TagNum#PartyRole}=Locate/Lending Firm, etc).<br/>
     * Not required when using {@link TagNum#PartyID} (448), {@link TagNum#PartyIDSource} (447), and PartyRole.
     * Type: String<br/>
     * Used: {@link PartySubID}
     */
    PartySubID                                          (523),
    /**
     * PartyID value within a nested repeating group. Same values as {@link TagNum#PartyID} (448).<br/>
     * Type: String<br/>
     * Used: {@link NestedPartyGroup}
     */
    NestedPartyID                                       (524),
    /**
     * PartyIDSource value within a nested repeating group. Same values as {@link TagNum#PartyIDSource} (447).<br/>
     * Type: String (see {@link PartyIDSource})<br/>
     * Used: {@link NestedPartyGroup}
     */
    NestedPartyIDSource                                 (525),
    /**
     * Assigned by the party which originates the order. Can be used to provide the {@link TagNum#ClOrdID} (11)
     * used by an exchange or executing system..<br/>
     * Type: String<br/>
     * Used: {@link OrderCancelRejectMsg},{@link OrderMassActionReportMsg},{@link OrderMassActionRequestMsg},
     * {@link NewOrderSingleMsg},{@link OrderCancelRequestMsg},{@link OrderCancelReplaceRequestMsg},
     * {@link OrderStatusRequestMsg},{@link InstrmtStrkPxGrp},{@link ListOrdGrp},{@link OrdAllocGrp},
     * {@link OrdListStatGrp},{@link SideCrossOrdCxlGrp},{@link SideCrossOrdModGrp},{@link TradeReportOrderDetailMsg},
     * {@link OrderMassCancelRequestMsg},{@link OrderMassCancelReportMsg},{@link NewOrderMultilegMsg},
     * {@link MultilegOrderCancelReplaceMsg},{@link CollateralRequestMsg},{@link CollateralAssignmentMsg},
     * {@link CollateralResponseMsg},{@link CollateralReportMsg},{@link CollateralInquiryMsg},{@link ExecutionReportMsg},
     * {@link CollateralInquiryAckMsg}
     */
    SecondaryClOrdID                                    (526),
    /**
     * Assigned by the party which accepts the order. Can be used to provide the {@link TagNum#ExecID} (17)
     * used by an exchange or executing system.<br/>
     * Type: String<br/>
     * Used: {@link ExecAllocGrp},{@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg},
     * {@link ExecutionReportMsg}
     */
    SecondaryExecID                                     (527),
    /**
     * Designates the capacity of the Firm placing the order. (as of FIX 4.3, 
     * this field replaced Rule80A (tag 47) --used in conjunction with
     * OrderRestrictions (529) field).<br/>
     * IB valid values: A,P.<br/>
     * Type: Char (see {@link OrderCapacity})<br/>
     * Used: {@link NewOrderSingleMsg}
     */
    OrderCapacity                                       (528),
    /**
     * Restrictions associated with an order. If more than one restriction is applicable 
     * to an order, this field can contain multiple instructions separated by space.<br/>
     * Type: MultipleValueString (seee {@link OrderRestrictions})<br/>
     * Used: {@link NewOrderSingleMsg}
     */
    OrderRestrictions                                   (529),
    /**
     * Specifies scope of Order Mass Cancel Request.<br/>
     * Type: Character (see {@link MassCancelRequestType})<br/>
     * Used: {@link OrderMassCancelRequestMsg},{@link OrderMassCancelReportMsg}
     */
    MassCancelRequestType                               (530),
    /**
     * Specifies the action taken by counterparty order handling system as a result of the
     * Order Mass Cancel Request.<br/>
     * Type: Character (see {@link MassCancelResponse})<br/>
     * Used: {@link OrderMassCancelReportMsg}
     */
    MassCancelResponse                                  (531),
    /**
     * Reason Order Mass Cancel Request was rejected.<br/>
     * Type: Integer (see {@link MassCancelRejectReason} for standard values)<br/>
     * Used: {@link OrderMassCancelReportMsg}
     */
    MassCancelRejectReason                              (532),
    /**
     * Total number of orders affected by either the OrderMassActionRequest(MsgType=CA)
     * or OrderMassCancelRequest(MsgType=Q).<br/>
     * Type: Integer<br/>
     * Used: {@link OrderMassActionReportMsg},{@link OrderMassCancelReportMsg}
     */
    TotalAffectedOrders                                 (533),
    /**
     * Number of affected orders in the repeating group of order ids.<br/>
     * Type: Integer<br/>
     * Used: {@link AffectedOrdGrp}
     */
    NoAffectedOrders                                    (534),
    /**
     * {@link TagNum#OrderID} (37) of an order affected by a mass cancel request.<br/>
     * Type: String<br/>
     * Used: {@link AffectedOrdGrp}
     */
    AffectedOrderID                                     (535),
    /**
     * {@link TagNum#SecondaryOrderID} (198) of an order affected by a mass cancel request.<br/>
     * Type: String<br/>
     * Used: {@link AffectedOrdGrp}
     */
    AffectedSecondaryOrderID                            (536),
    /**
     * Identifies the type of quote. An indicative quote is used to inform a counterparty
     * of a market. An indicative quote does not result directly in a trade. A tradeable quote
     * is submitted to a market and will result directly in a trade against other orders and
     * quotes in a market.
     * A restricted tradeable quote is submitted to a market and within a certain restriction
     * (possibly based upon price or quantity) will automatically trade against orders.
     * Order that do not comply with restrictions are sent to the quote issuer who can choose
     * to accept or decline the order.
     * A counter quote is used in the negotiation model.<br/>
     * Type: Integer (see {@link QuoteType})<br/>
     * Used: {@link QuoteMsg}
     */
    QuoteType                                           (537),
    /**
     * PartyRole value within a nested repeating group. Same values as {@link TagNum#PartyRole} (452).<br/>
     * Type: String (see {@link PartyRole})<br/>
     * Used: {@link NestedPartyGroup}
     */
    NestedPartyRole                                     (538),
    /**
     * Number of {@link TagNum#NestedPartyID} (524), {@link TagNum#NestedPartyIDSource} (525),
     * and {@link TagNum#NestedPartyRole} (538) entries.<br/>
     * Type: Integer<br/>
     * Used: {@link NestedParties}
     */
    NoNestedPartyIDs                                    (539),
    /**
     * Total Amount of Accrued Interest for convertible bonds and fixed income.<br/>
     * Type: Double<br/>
     * Used: {@link AllocationInstructionMsg},{@link AllocationReportMsg},{@link AllocationInstructionAlertMsg}
     */
    TotalAccruedInterestAmt                             (540),
    /**
     * Used in place of tag 200 to identify option contracts that may be ambiguous due to
     * listing of weekly and quarterly options within that same month and strike.<br/>
     * IB: Format: yyyymmdd ** Date should reflect the last trading day of the contract.
     * (typically the Friday before expiration Saturday)<br/>
     * Type: LocalMktDate<br/>
     * Used: New Order (opt/fut/war)
     */
    MaturityDate                                        (541),
    /**
     * Underlying security’s maturity date. See {@link  TagNum#MaturityDate} (541) field for description.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingMaturityDate                              (542),
    /**
     * Values may include BIC for the depository or custodian who maintain ownership records, 
     * the ISO country code for the location of the record, or the value "ZZ" to specify physical
     * ownership of the security (e.g. stock certificate).<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    InstrRegistry                                       (543),
    /**
     * Identifies whether an order is a margin order or a non-margin order. This is primarily used when
     * sending orders to Japanese exchanges to indicate sell margin or buy to cover.
     * The same tag could be assigned also by buy-side to indicate the intent to sell or buy margin
     * and the sell-side to accept or reject (base on some validation criteria) the margin request.<br/>
     * Type: Character (see {@link CashMargin})<br/>
     * Used: {@link NewOrderSingleMsg},{@link OrderCancelReplaceRequestMsg},{@link ListOrdGrp},
     * {@link SideCrossOrdModGrp},{@link NewOrderMultilegMsg},{@link MultilegOrderCancelReplace},
     * {@link ExecutionReportMsg}
     */
    CashMargin                                          (544),
    /**
     * PartySubID value within a nested repeating group. Same values as {@link  TagNum#PartySubID} (523).<br/>
     * Type: Character<br/>
     * Used: {@link NestedPartyGroup}, {@link NstdPtysSubGroup}
     */
    NestedPartySubID                                    (545),
    /**
     * Specifies the market scope of the market data.<br/>
     * Type: String<br/>
     * Used: {@link MarketDataRequestMsg}
     */
    Scope                                               (546),
    /**
     * Defines how a server handles distribution of a truncated book. Defaults to broker option.<br/>
     * Type: Boolean<br/>
     * Used: {@link MarketDataRequestMsg}
     */
    MDImplicitDelete                                    (547),
    /**
     * Identifier for a cross order. Must be unique during a given trading day.
     * Recommend that firms use the order date as part of the CrossID for Good Till Cancel (GT) orders.<br/>
     * Type: String<br/>
     * Used: {@link NewOrderCrossMsg},{@link CrossOrderCancelReplaceRequestMsg},{@link CrossOrderCancelRequestMsg},
     * 
     */
    CrossID                                             (548),
    /**
     * Type of cross being submitted to a market.<br/>
     * Type: Integer (see {@link CrossType})<br/>
     * Used: {@link NewOrderCrossMsg},{@link CrossOrderCancelReplaceRequestMsg},{@link CrossOrderCancelRequestMsg},
     * {@link ExecutionReportMsg}
     */
    CrossType                                           (549),
    /**
     * Indicates if one side or the other of a cross order should be prioritized.
     * The definition of prioritization is left to the market. In some markets prioritization means
     * which side of the cross order is applied to the market first. In other markets - prioritization
     * may mean that the prioritized side is fully executed (sometimes referred to as the side being protected).<br/>
     * Type: Integer (see {@link CrossPrioritization})<br/>
     * Used: {@link NewOrderCrossMsg},{@link CrossOrderCancelReplaceRequestMsg},{@link CrossOrderCancelRequestMsg}
     */
    CrossPrioritization                                 (550),
    /**
     * CrossID of the previous cross order (NOT the initial cross order of the day) as
     * assigned by the institution, used to identify the previous cross order in Cross
     * Cancel and Cross Cancel/Replace Requests.<br/>
     * Type: String<br/>
     * Used: {@link CrossOrderCancelReplaceRequestMsg},{@link CrossOrderCancelRequestMsg},{@link ExecutionReportMsg}
     */
    OrigCrossID                                         (551),
    /**
     * Number of Side repeating group instances.<br/>
     * Type: Integer (see {@link NoSides})<br/>
     * Used: {@link SideCrossOrdCxlGrp},{@link SideCrossOrdModGrp},{@link TrdCapRptSideGrp},{@link TrdCapRptAckSideGrp}
     */
    NoSides                                             (552),
    /**
     * User name used for authentication.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    Username                                            (553),
    /**
     * User password used for authentication.<br/>
     * Type: String<br/>
     * Used: Logon
     */
    Password                                            (554),
    /**
     * Number of InstrumentLeg repeating group instances.<br/>
     * Type: NumInGroup<br/>
     * Used: {@link net.hades.fix.message.NewsMsg}
     */
    NoLegs                                              (555),
    /**
     * Currency associated with a particular Leg's quantity.<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegCurrency                                         (556),
    /**
     * Used to support fragmentation. Indicates total number of security types when multiple
     * Security Type messages are used to return results.<br/>
     * Type: Integer<br/>
     * Used: {@link SecTypesGrp}
     */
    TotNoSecurityTypes                                  (557),
    /**
     * Number of Security Type repeating group instances.<br/>
     * Type: Integer<br/>
     * Used: {@link SecTypesGrp}
     */
    NoSecurityTypes                                     (558),
    /**
     * Identifies the type/criteria of Security List Request.<br/>
     * Type: Integer (see {@link SecurityListRequestType})<br/>
     * Used: {@link SecurityListRequestMsg},{@link DerivativeSecurityListRequestMsg}
     */
    SecurityListRequestType                             (559),
    /**
     * The results returned to a Security Request message.<br/>
     * Type: Integer (see {@link SecurityRequestResult})<br/>
     * Used: {@link DerivativeSecurityListUpdateReportMsg},{@link SecurityListMsg},{@link DerivativeSecurityListMsg},
     * {@link SecurityListUpdateReportMsg}
     */
    SecurityRequestResult                               (560),
    /**
     * The trading lot size of a security.<br/>
     * Type: Double<br/>
     * Used: {@link BaseTradingRulesMsg}
     */
    RoundLot                                            (561),
    /**
     * The minimum trading volume for a security.<br/>
     * Type: Double<br/>
     * Used: {@link BaseTradingRulesMsg}
     */
    MinTradeVol                                         (562),
    /**
     * Indicates the method of execution reporting requested by issuer of the order.<br/>
     * Type: Integer (see {@link MultiLegRptTypeReq})<br/>
     * Used: {@link NewOrderMultilegMsg},{@link MultilegOrderCancelReplace}
     */
    MultiLegRptTypeReq                                  (563),
    /**
     * PositionEffect for leg of a multileg. See {@link TagNum#PositionEffect} (77) field for description.<br/>
     * Type: Character<br/>
     * Used: {@link NewOrderMultilegMsg}
     */
    LegPositionEffect                                   (564),
    /**
     * CoveredOrUncovered for leg of a multileg. See {@link TagNum#CoveredOrUncovered} (203) field for description.<br/>
     * Type: Integer (see {@link LegCoveredOrUncovered})<br/>
     * Used: {@link InstrmtLegExecGrp},{@link LegOrdGrp},{@link TrdInstrmtLegGrp}
     */
    LegCoveredOrUncovered                               (565),
    /**
     * Price for leg of a multileg. See {@link TagNum#Price} (44) field for description.<br/>
     * Type: Price<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegPrice                                            (566),
    /**
     * Indicates the reason a Trading Session Status Request was rejected.<br/>
     * Type: Integer (see {@link TradSesStatusRejReason} for standard values)<br/>
     * Used: {@link TrdSessLstGrp},{@link TradingSessionStatus}
     */
    TradSesStatusRejReason                              (567),
    /**
     * Trade Capture Report Request ID.<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportRequestMsg},{@link TradeCaptureReportMsg},{@link TradeCaptureReportRequestAckMsg}
     */
    TradeRequestID                                      (568),
    /**
     * Type of Trade Capture Report.<br/>
     * Type: Integer (see {@link TradeRequestType})<br/>
     * Used: {@link TradeCaptureReportRequestMsg},{@link TradeCaptureReportRequestAckMsg}
     */
    TradeRequestType                                    (569),
    /**
     * Indicates if the trade capture report was previously reported to the counterparty.<br/>
     * Type: Boolean<br/>
     * Used: {@link AllocationInstructionMsg},{@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg},
     * {@link AllocationReportMsg},{@link AllocationInstructionAlertMsg}
     */
    PreviouslyReported                                  (570),
    /**
     * Unique identifier of trade capture report.<br/>
     * Type: String<br/>
     * Used: {@link TrdCollGrp},{@link TradeCaptureReportRequestMsg},{@link TradeCaptureReportMsg},
     * {@link TradeCaptureReportAckMsg}
     */
    TradeReportID                                       (571),
    /**
     * Reference identifier used with CANCEL and REPLACE transaction types.<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    TradeReportRefID                                    (572),
    /**
     * The status of this trade with respect to matching or comparison.<br/>
     * Type: Character (see {@link MatchStatus})<br/>
     * Used: {@link AllocGrp},{@link AllocationInstructionAckMsg},{@link TradeCaptureReportRequestMsg},
     * {@link TradeCaptureReportMsg},{@link RequestForPositionsMsg},{@link RequestForPositionsAckMsg},
     * {@link PositionReportMsg},{@link TradeCaptureReportAckMsg},{@link AllocationReportAckMsg},
     * {@link ConfirmationAckMsg}
     */
    MatchStatus                                         (573),
    /**
     * The point in the matching process at which this trade was matched.<br/>
     * Type: String (see {@link MatchType})<br/>
     * Used: {@link AllocationInstructionMsg},{@link MDIncGrp},{@link MatchRules},
     * {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg},{@link AllocationReportMsg},
     * {@link AllocationReportAckMsg},{@link ExecutionReportMsg},{@link AllocationInstructionAlertMsg}
     */
    MatchType                                           (574),
    /**
     * This trade is to be treated as an odd lot. If this field is not specified, the default will be "N".<br/>
     * Type: Boolean<br/>
     * Used: {@link TrdCapRptSideGrp},{@link TrdCapRptAckSideGrp}
     */
    OddLot                                              (575),
    /**
     * Number of clearing instructions.<br/>
     * Type: Integer<br/>
     * Used: {@link ClrInstGrp}
     */
    NoClearingInstructions                              (576),
    /**
     * Eligibility of this trade for clearing and central counterparty processing values above
     * 4000 are reserved for agreement between parties.<br/>
     * Type: Integer (see {@link ClearingInstruction})<br/>
     * Used: {@link ClrInstGrp}
     */
    ClearingInstruction                                 (577),
    /**
     * Type of input device or system from which the trade was entered.<br/>
     * Type: String<br/>
     * Used: {@link AllocationInstructionMsg},{@link TrdCapRptSideGrp},{@link TrdCapRptAckSideGrp},
     * {@link TradeCaptureReportRequestMsg},{@link AllocationReportMsg},{@link AllocationInstructionAlertMsg}
     */
    TradeInputSource                                    (578),
    /**
     * Specific device number, terminal number or station where trade was entered.<br/>
     * Type: String<br/>
     * Used: {@link TrdCapRptSideGrp},{@link TrdCapRptAckSideGrp},{@link TradeCaptureReportRequestMsg},
     * {@link AllocationReportMsg}
     */
    TradeInputDevice                                    (579),
    /**
     * Number of Date fields provided in date range.<br/>
     * Type: Integer<br/>
     * Used: {@link TrdCapDtGrp}
     */
    NoDates                                             (580),
    /**
     * Type of account associated with an order.<br/>
     * Type: Integer (see {@link AccountType})<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    AccountType                                         (581),
    /**
     * Capacity of customer placing the order. Primarily used by futures exchanges to indicate
     * the CTICode (customer type indicator) as required by the US CFTC (Commodity Futures Trading Commission).<br/>
     * Type: Integer (see {@link CustOrderCapacity})<br/>
     * Used: {@link QuoteMsg}
     */
    CustOrderCapacity                                   (582),
    /**
     * Permits order originators to tie together groups of orders in which trades resulting
     * from orders are associated for a specific purpose, for example the calculation of
     * average execution price for a customer or to associate lists submitted to a broker as
     * waves of a larger program trade.<br/>
     * Type: String<br/>
     * Used: {@link NewOrderSingleMsg}
     */
    ClOrdLinkID                                         (583),
    /**
     * Value assigned by issuer of Mass Status Request to uniquely identify the request.<br/>
     * Type: String<br/>
     * Used: {@link OrderMassStatusRequestMsg},{@link ExecutionReportMsg}
     */
    MassStatusReqID                                     (584),
    /**
     * Mass Status Request Type. Values "100" and above are reserved for bilaterally agreed upon
     * user defined enumerations.<br/>
     * Type: Integer (see {@link MassStatusReqType} for standard values)<br/>
     * Used: {@link OrderMassStatusRequestMsg}
     */
    MassStatusReqType                                   (585),
    /**
     * The most recent (or current) modification {@link TagNum#TransactTime} (tag 60) reported on an
     * Execution Report for the order. The OrigOrdModTime is provided as an optional field
     * on Order Cancel Request and Order Cancel Replace Requests to identify that the state
     * of the order has not changed since the request was issued. The use of this approach
     * is not recommended.<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link OrderCancelRejectMsg},{@link OrderCancelRequestMsg},{@link OrderCancelReplaceRequestMsg},
     * {@link SideCrossOrdCxlGrp},{@link TradeReportOrderDetailMsg},{@link MultilegOrderCancelReplaceMsg},
     */
    OrigOrdModTime                                      (586),
    /**
     * Refer to values for {@link TagNum#SettlType} (63).<br/>
     * Type: Character (see {@link SettlmntType})<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    LegSettlType                                        (587),
    /**
     * Refer to description for {@link TagNum#SettlDate} (64).<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    LegSettlDate                                        (588),
    /**
     * Indicates whether or not automatic booking can occur.<br/>
     * Type: Character (see {@link DayBookingInst})<br/>
     * Used: {@link NewOrderSingleMsg},{@link OrderCancelReplaceRequestMsg},{@link ListOrdGrp},{@link SideCrossOrdModGrp},
     * {@link NewOrderMultilegMsg},{@link MultilegOrderCancelReplaceMsg},{@link ExecutionReportMsg}
     */
    DayBookingInst                                      (589),
    /**
     * Indicates what constitutes a bookable unit.<br/>
     * Type: Character (see {@link BookingUnit})<br/>
     * Used: {@link NewOrderSingleMsg},{@link OrderCancelReplaceRequestMsg},{@link ListOrdGrp},{@link SideCrossOrdModGrp},
     * {@link NewOrderMultilegMsg},{@link MultilegOrderCancelReplaceMsg},{@link ExecutionReportMsg}
     */
    BookingUnit                                         (590),
    /**
     * Indicates the method of preallocation.<br/>
     * Type: Character (see {@link PreallocMethod})<br/>
     * Used: {@link NewOrderSingleMsg},{@link OrderCancelReplaceRequestMsg},{@link ListOrdGrp},{@link SideCrossOrdModGrp},
     * {@link NewOrderMultilegMsg},{@link MultilegOrderCancelReplaceMsg},{@link ExecutionReportMsg}
     */
    PreallocMethod                                      (591),
    /**
     * Underlying security’s CountryOfIssue. See {@link TagNum#CountryOfIssue} (470) field for description.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingCountryOfIssue                            (592),
    /**
     * Underlying security’s StateOrProvinceOfIssue. See {@link TagNum#StateOrProvinceOfIssue} (471)
     * field for description.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingStateOrProvinceOfIssue                    (593),
    /**
     * Underlying security’s LocaleOfIssue.. See {@link TagNum#LocaleOfIssue} (472)
     * field for description.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingLocaleOfIssue                             (594),
    /**
     * Underlying security’s InstrRegistry. See {@link TagNum#InstrRegistry} (543) field for description.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingInstrRegistry                             (595),
    /**
     * Multileg instrument's individual leg security’s CountryOfIssue.<br/>
     * See {@link TagNum#CountryOfIssue} (470) field for description.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegCountryOfIssue                                   (596),
    /**
     * Multileg instrument's individual leg security’s StateOrProvinceOfIssue.<br/>
     * See {@link TagNum#StateOrProvinceOfIssue} (471) field for description.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegStateOrProvinceOfIssue                           (597),
    /**
     * Multileg instrument's individual leg security’s LocaleOfIssue.<br/>
     * See {@link TagNum#LocaleOfIssue} (472) field for description.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegLocaleOfIssue                                    (598),
    /**
     * Multileg instrument's individual leg security’s InstrRegistry.<br/>
     * See {@link TagNum#InstrRegistry} (543) field for description.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegInstrRegistry                                    (599),
    /**
     * Multileg instrument's individual security’s Symbol. See Symbol (55) field for description.<br/>
     * Type: String<br/>
     * Used: New Order-Multileg, {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegSymbol                                           (600),
    /**
     * Multileg instrument's individual security’s SymbolSfx. See SymbolSfx (65) field for description.<br/>
     * Valid values:<br/>
     * For Fixed Income CD - EUCP with lump-sum interest rather than discount price<br/>
     * WI - "When Issued" for a security to be reissued under an old CUSIP or ISIN.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegSymbolSfx                                        (601),
    /**
     * Multileg instrument's individual security’s SecurityID. See {@link TagNum#SecurityID} (48)
     * field for description.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegSecurityID                                       (602),
    /**
     * Multileg instrument's individual security’s SecurityIDSource. See {@link TagNum#SecurityIDSource} (22)
     * field for description.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegSecurityIDSource                                 (603),
    /**
     * Multileg instrument's individual security’s NoSecurityAltID. See {@link TagNum#NoSecurityAltID} (454)
     * field for description.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    NoLegSecurityAltID                                  (604),
    /**
     * Multileg instrument's individual security’s SecurityAltID. See {@link TagNum#SecurityAltID} (455)
     * field for description.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegSecurityAltID                                    (605),
    /**
     * Multileg instrument's individual security’s SecurityAltIDSource. See {@link TagNum#SecurityAltIDSource} (456)
     * field for description.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegSecurityAltIDSource                              (606),
    /**
     * Multileg instrument's individual  security’s Product. See {@link TagNum#Product} (460)
     * field for description.<br/>
     * Type: Integer (see {@link Product})<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegProduct                                          (607),
    /**
     * Multileg instrument's individual security’s CFICode.See {@link TagNum#CFICode} (461) field for description.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegCFICode                                          (608),
    /**
     * Multileg instrument's individual security’s SecurityType. See {@link TagNum#SecurityType} (167) field for
     * description.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegSecurityType                                     (609),
    /**
     * Multileg instrument's individual security’s MaturityMonthYear. See {@link TagNum#MaturityMonthYear} (200)
     * field for description.<br/>
     * Type: month-year<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegMaturityMonthYear                                (610),
    /**
     * Multileg instrument's individual security’s MaturityDate. See {@link TagNum#MaturityDate} (54)
     * field for description.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegMaturityDate                                     (611),
    /**
     * Multileg instrument's individual security’s StrikePrice. See {@link TagNum#StrikePrice} (202) field
     * for description.<br/>
     * Type: Price<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegStrikePrice                                      (612),
    /**
     * Multileg instrument's individual security’s OptAttribute. See {@link TagNum#OptAttribute} (206) field
     * for description.<br/>
     * Type: Char<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegOptAttribute                                     (613),
    /**
     * Multileg instrument's individual security’s ContractMultiplier. See {@link TagNum#ContractMultiplier}
     * (231) field for description.<br/>
     * Type: Float<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegContractMultiplier                               (614),
    /**
     * Multileg instrument's individual security’s CouponRate. See {@link TagNum#CouponRate}
     * (223) field for description.<br/>
     * Type: Float<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegCouponRate                                       (615),
    /**
     * Multileg instrument's individual security’s SecurityExchange. See {@link TagNum#SecurityExchange}
     * (207) field for description<br/>
     * Type: Exchange<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegSecurityExchange                                 (616),
    /**
     * Multileg instrument's individual security’s Issuer. See {@link TagNum#Issuer}
     * (106) field for description<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegIssuer                                           (617),
    /**
     * Multileg instrument's individual security’s EncodedIssuerLen. See {@link TagNum#EncodedIssuerLen}
     * (348) field for description<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    EncodedLegIssuerLen                                 (618),
    /**
     * Multileg instrument's individual security’s EncodedIssuer. See {@link TagNum#EncodedIssuer}
     * (349) field for description<br/>
     * Type: Data<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    EncodedLegIssuer                                    (619),
    /**
     * Multileg instrument's individual security’s SecurityDesc. See {@link TagNum#SecurityDesc}
     * (7) field for description<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegSecurityDesc                                     (620),
    /**
     * Multileg instrument's individual security’s EncodedSecurityDescLen. See {@link TagNum#EncodedSecurityDescLen}
     * (350) field for description<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    EncodedLegSecurityDescLen                           (621),
    /**
     * Multileg instrument's individual security’s EncodedSecurityDesc. See {@link TagNum#EncodedSecurityDesc}
     * (35) field for description<br/>
     * Type: Data<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    EncodedLegSecurityDesc                              (622),
    /**
     * The ratio of quantity for this individual leg relative to the entire multileg security.<br/>
     * Type: Float<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegRatioQty                                         (623),
    /**
     * The side of this individual leg (multileg security). See {@link TagNum#Side} (54)
     *  field for desciption.<br/>
     * Type: Char (Side)<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegSide                                             (624),
    /**
     * Optional market assigned sub identifier for a trading phase within a trading session. 
     * Usage is determined by market or counterparties. Used by US based futures markets to 
     * identify exchange specific execution time bracket codes as required by US market regulations. 
     * Bilaterally agreed values of data type "String" that start with a character can be used for 
     * backward compatibility.<br/>
     * Valid values:<br/>
     * 1 - Pre-Trading<br/>
     * 2 - Opening or opening auction<br/>
     * 3 - (Continuous) Trading<br/>
     * 4 - Closing or closing auction<br/>
     * 5 - Post-Trading<br/>
     * 6 - Intraday Auction<br/>
     * 7 - Quiescent<br/>
     * or any value conforming to the data type Reserved100Plus
     * Type: String<br/>
     * Used: {@link AdvertismentMsg},{@link QuoteMsg}
     */
    TradingSessionSubID                                 (625),
    /**
     * Describes the specific type or purpose of an Allocation message (i.e. "Buyside Calculated).<br/>
     * Type: Integer (see {@link AllocType})<br/>
     * Used: {@link AllocationInstructionMsg},{@link AllocationInstructionAckMsg},{@link AllocationInstructionAlertMsg}
     */
    AllocType                                           (626),
    /**
     * Number of repeating groups of historical “hop” information. Only applicable if 
     * OnBehalfOfCompID is used, however, its use iso ptional. Note that some market 
     * regulations or counterparties may require tracking of message hops.<br/>
     * Type: Integer<br/>
     * Used: Standard Header 4.3 up
     */
    NoHops                                              (627),
    /**
     * Third party firm which delivered a specific message either from the firm which 
     * originated the message or from another third party (if multiple “hops” are performed). 
     * It is recommended that this value be the SenderCompID (49) of the third party.<br/>
     * Part of NoHops group.<br/>
     * Type: String<br/>
     * Used: Standard Header 4.3 up
     */
    HopCompID                                           (628),
    /**
     * Time that {@link TagNum#HopCompID} (628) sent the message. It is recommended that this value be the
     * {@link TagNum#SendingTime} (52) of the message sent by the third party.<br/>
     * Part of NoHops group.<br/>
     * Type: Timestamp<br/>
     * Used: Standard Header 4.3 up
     */
    HopSendingTime                                      (629),
    /**
     * Reference identifier assigned by HopCompID (628) associated with the message sent. 
     * It is recommended that this value be the MsgSeqNum (34) of the message sent by 
     * the third party.<br/>
     * Part of NoHops group.<br/>
     * Type: String<br/>
     * Used: Standard Header 4.3 up
     */
    HopRefID                                            (630),
    /**
     * Mid price/rate.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    MidPx                                               (631),
    /**
     * Bid yield.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    BidYield                                            (632),
    /**
     * Mid yield.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    MidYield                                            (633),
    /**
     * Offer yield.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    OfferYield                                          (634),
    /**
     * Indicates type of fee being assessed of the customer for trade executions at an exchange.
     * Applicable for futures markets only at this time.<br/>
     * Type: String (see {@link ClearingFeeIndicator)}<br/>
     * Used: {@link NewOrderSingleMsg},{@link OrderCancelReplaceRequestMsg},{@link AllocGrp},
     * {@link ListOrdGrp},{@link SideCrossOrdModGrp},{@link NewOrderMultilegMsg},{@link MultilegOrderCancelReplaceMsg},
     * {@link TradeCaptureReportAckMsg},{@link ExecutionReportMsg}
     */
    ClearingFeeIndicator                                (635),
    /**
     * Indicates if the order is currently being worked. Applicable only for OrdStatus =“New”. 
     * For open outcry markets this indicates that the order is being worked in the crowd. 
     * For electronic markets it indicates that the order has transitioned from a contingent 
     * order to a market order.<br/>
     * Type: Boolean (see {@link BoolYesNo)}<br/>
     * Used: {@link ExecutionReportMsg}
     */
    WorkingIndicator                                    (636),
    /**
     * Execution price assigned to a leg of a multileg instrument. See LastPx (31) field for description and values.<br/>
     * Type: Double<br/>
     * Used: {@link InstrmtLegExecGrp},{@link TrdInstrmtLegGrp}
     */
    LegLastPx                                           (637),
    /**
     * Indicates if a Cancel/Replace has caused an order to lose book priority.<br/>
     * Type: Integer (see {@link PriorityIndicator)}<br/>
     * Used: {@link ExecutionReportMsg}
     */
    PriorityIndicator                                   (638),
    /**
     * Amount of price improvement.<br/>
     * Type: Double<br/>
     * Used: {@link ExecutionReportMsg}
     */
    PriceImprovement                                    (639),
    /**
     * Deprecated in FIX.5.0 Price of the future part of a F/X swap order.
     * See {@link TagNum#Price} (44) for description.<br/>
     * Type: Decimal<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    Price2                                              (640),
    /**
     * Deprecated in FIX.5.0 F/X forward points of the future part of a F/X swap order added to
     * {@link TagNum#LastSpotRate} (94). May be a negative value. .
     * Type: Decimal<br/>
     * Used: {@link ExecutionReportMsg}
     */
    LastForwardPoints2                                  (641),
    /**
     * Deprecated in FIX.5.0 Bid F/X forward points of the future portion
     * of a F/X swap quote added to spot rate. May be a negative value.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    BidForwardPoints2                                   (642),
    /**
     * Deprecated in FIX.5.0 BOffer F/X forward points of the future portion
     * of a F/X swap quote added to spot rate. May be a negative value.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    OfferForwardPoints2                                 (643),
    /**
     * RFQ Request ID – used to identify an RFQ Request.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    RFQReqID                                            (644),
    /**
     * Used to indicate the best bid in a market.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    MktBidPx                                            (645),
    /**
     * Used to indicate the best offer in a market.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    MktOfferPx                                          (646),
    /**
     * Used to indicate a minimum quantity for a bid. If this field is used
     * the {@link TagNum#BidSize} (134) field is interpreted as the maximum bid size.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    MinBidSize                                          (647),
    /**
     * Used to indicate a minimum quantity for an offer. If this field is used the
     * {@link TagNum#OfferSize} (135) field is interpreted as the maximum offer size.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    MinOfferSize                                        (648),
    /**
     * Unique identifier for Quote Status Request.<br/>
     * Type: String<br/>
     * Used: {@link QuoteMsg},{@link net.hades.fix.message.QuoteStatusRequestMsg}
     */
    QuoteStatusReqID                                    (649),
    /**
     * Indicates that this message is to serve as the final and legal confirmation.
     * Valid Values:
     * N - Does not consitute a Legal Confirm
     * Y - Legal Confirm<br/>
     * Type: Boolean<br/>
     * Used: {@link AllocationInstructionMsg},{@link ConfirmationMsg},{@link AllocationReportMsg},
     * {@link AllocationInstructionAlertMsg}
     */
    LegalConfirm                                        (650),
    /**
     * The calculated or traded price for the underlying instrument that corresponds
     * to a derivative. Used for transactions that include the cash instrument and the derivative.<br/>
     * Type: Double<br/>
     * Used: {@link ExecutionReportMsg}
     */
    UnderlyingLastPx                                    (651),
    /**
     * The calculated or traded quantity for the underlying instrument that corresponds to a derivative.
     * Used for transactions that include the cash instrument and the derivative.<br/>
     * Type: Double<br/>
     * Used: {@link ExecutionReportMsg}
     */
    UnderlyingLastQty                                   (652),
    /**
     * State of a security definition request made to a market. Useful for markets,
     * such as derivatives markets, where market participants are permitted to define
     * instruments for subsequent trading.<br/>
     * Type: Integer ({@link SecDefStatus})<br/>
     * Used: {@link ExecutionReportMsg}
     */
    SecDefStatus                                        (653),
    /**
     * Unique identifier for a specific leg. (combo orders).<br/>
     * Type: String<br/>
     * Used: {@link ExecutionReportMsg}
     */
    LegRefID                                            (654),
    /**
     * Unique indicator for a specific leg for the {@link TagNum#ContraBroker} (375).<br/>
     * Type: String<br/>
     * Used: {@link ContraGrp}
     */
    ContraLegRefID                                      (655),
    /**
     * Foreign exchange rate used to compute the bid {@link TagNum#SettlCurrAmt} (119)
     * from {@link TagNum#Currency} (15) to {@link TagNum#SettlCurrency} (120).<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    SettlCurrBidFxRate                                  (656),
    /**
     * Foreign exchange rate used to compute the offer {@link TagNum#SettlCurrAmt} (119)
     * from {@link TagNum#Currency} (15) to {@link TagNum#SettlCurrency} (120).<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    SettlCurrOfferFxRate                                (657),
    /**
     * Reason Quote was rejected.<br/>
     * Type: Integer (see {@link QuoteRequestRejectReason} for standard values)<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestRejectMsg}
     */
    QuoteRequestRejectReason                            (658),
    /**
     * ID within repeating group of sides which is used to represent this transaction for
     * compliance purposes (e.g. OATS reporting).<br/>
     * Type: String<br/>
     * Used: {@link SideCrossOrdModGrp}
     */
    SideComplianceID                                    (659),
    /**
     * Used to identify the source of the {@link TagNum#Account} (1) code. This is especially useful
     * if the account is a new account that the Respondent may not have setup yet
     * in their system.<br/>
     * Type: Integer (see {@link AcctIDSource}, {@link Reserved100Plus})<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    AcctIDSource                                        (660),
    /**
     * Used to identify the source of the {@link TagNum#AllocAccount} (79) code.
     * See {@link TagNum#AcctIDSource} (660) for valid values.<br/>
     * Type: Integer (see {@link AcctIDSource})<br/>
     * Used: {@link AllocAckGrp},{@link AllocGrp},{@link PreAllocGrp},{@link PreAllocMlegGrp},{@link TrdAllocGrp},
     * {@link ConfirmationMsg},{@link SettlementInstructionRequestMsg},{@link ConfirmationRequestMsg}
     */
    AllocAcctIDSource                                   (661),
    /**
     * Specifies the price of the benchmark.<br/>
     * Type: Price<br/>
     * Used: {@link SpreadOfBenchmarkCurveData}
     */
    BenchmarkPrice                                      (662),
    /**
     * Identifies type of {@link TagNum#BenchmarkPrice} (662).<br/>
     * Type: Integer (see {@link PriceType})<br/>
     * Used: {@link SpreadOfBenchmarkCurveData}
     */
    BenchmarkPriceType                                  (663),
    /**
     * Message reference for Confirmation.<br/>
     * Type: String<br/>
     * Used: {@link Confirmation}, {@link ConfirmationAck}
     */
    ConfirmID                                           (664),
    /**
     * Identifies the status of the Confirmation.<br/>
     * Type: Integer (see {@link ConfirmStatus})<br/>
     * Used: {@link Confirmation}, {@link ConfirmationAck}
     */
    ConfirmStatus                                       (665),
    /**
     * Identifies the Confirmation transaction type.<br/>
     * Type: Integer (see {@link ConfirmTransType})<br/>
     * Used: {@link Confirmation}
     */
    ConfirmTransType                                    (666),
    /**
     * Specifies when the contract (i.e. MBS/TBA) will settle.<br/>
     * Type: MonthYear<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    ContractSettlMonth                                  (667),
    /**
     * Identifies the form of delivery.<br/>
     * Type: Integer (see {@link DeliveryForm})<br/>
     * Used: {@link InstrumentExtension}
     */
    DeliveryForm                                        (668),
    /**
     * Last price expressed in percent-of-par. Conditionally required for Fixed Income trades
     * when {@link TagNum#LastPx} (31) is expressed in Yield, Spread, Discount or any other type.
     * Usage: Execution Report and Allocation Report repeating executions block (from sellside).<br/>
     * Type: Double<br/>
     * Used: {@link ExecAllocGrp},{@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg},
     * {@link ExecutionReportMsg},{@link ExecutionAcknowledgementMsg}
     */
    LastParPx                                           (669),
    /**
     * Number of Allocations for the leg.<br/>
     * Type: Integer<br/>
     * Used: {@link LegPreAllocGr}
     */
    NoLegAllocs                                         (670),
    /**
     * Allocation Account for the leg. See {@link TagNum#AllocAccount} (79) for description and valid values.<br/>
     * Type: String<br/>
     * Used: {@link LegPreAllocGr}
     */
    LegAllocAccount                                     (671),
    /**
     * Reference for the individual allocation ticket. See {@link TagNum#IndividualAllocID} (467)
     * for description and valid values.<br/>
     * Type: String<br/>
     * Used: {@link LegPreAllocGr}
     */
    LegIndividualAllocID                                (672),
    /**
     * Leg allocation quantity.See {@link TagNum#AllocQty} (80) for description and valid values.<br/>
     * Type: Double<br/>
     * Used: {@link LegPreAllocGr}
     */
    LegAllocQty                                         (673),
    /**
     * The source of the {@link TagNum#LegAllocAccount} (671). See {@link TagNum#AllocAcctIDSource} (661)
     * for description and valid values.<br/>
     * Type: String<br/>
     * Used: {@link LegPreAllocGr}
     */
    LegAllocAcctIDSource                                (674),
    /**
     * Identifies settlement currency for the Leg. See {@link TagNum#SettlCurrency} (20)
     * for description and valid values<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: {@link LegPreAllocGr}
     */
    LegSettlCurrency                                    (675),
    /**
     * {@link TagNum#LegBenchmarkPrice} (679) currency. See {@link TagNum#BenchmarkCurveCurrency} (220)
     * for description and valid values.<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: {@link LegBenchmarkCurveData}
     */
    LegBenchmarkCurveCurrency                           (676),
    /**
     * Name of the Leg Benchmark Curve. See {@link TagNum#BenchmarkCurveName} (22) for description
     * and valid values.<br/>
     * Type: String (see {@link BenchmarkCurveName})<br/>
     * Used: {@link LegBenchmarkCurveData}
     */
    LegBenchmarkCurveName                               (677),
    /**
     * Identifies the point on the Leg Benchmark Curve. See {@link TagNum#BenchmarkCurvePoint} (222)
     * for description and valid values.<br/>
     * Type: String<br/>
     * Used: {@link LegBenchmarkCurveData}
     */
    LegBenchmarkCurvePoint                              (678),
    /**
     * Used to identify the price of the benchmark security. See {@link TagNum#BenchmarkPrice} (662)
     * for description and valid values.<br/>
     * Type: Decimal<br/>
     * Used: {@link LegBenchmarkCurveData}
     */
    LegBenchmarkPrice                                   (679),
    /**
     * The price type of the LegBenchmarkPrice. See {@link TagNum#BenchmarkPriceType} (663)
     * for description and valid values.<br/>
     * Type: Integer (see {@link PriceType})<br/>
     * Used: {@link LegBenchmarkCurveData}
     */
    LegBenchmarkPriceType                               (680),
    /**
     * Bid price of this leg. See {@link TagNum#BidPx} (32) for description and valid values.<br/>
     * Type: Decimal<br/>
     * Used: {@link LegQuoteSymbolGroup}
     */
    LegBidPx                                            (681),
    /**
     * Leg-specific IOI quantity..<br/>
     * Type: String (see {@link IOIQty})<br/>
     * Used: {@link LegIOIGroup}
     */
    LegIOIQty                                           (682),
    /**
     * Number of leg stipulation entries.<br/>
     * Type: Integer<br/>
     * Used: {@link LegStipulations}
     */
    NoLegStipulations                                   (683),
    /**
     * Offer price of this leg. See {@link TagNum#OfferPx} (133) for description and valid values.<br/>
     * Type: Decimal<br/>
     * Used: {@link LegQuoteSymbolGroup}
     */
    LegOfferPx                                          (684),
    /**
     * Quantity ordered of this leg.
     * See {@link TagNum#OrderQty} (38) for description and valid values.<br/>
     * Type: Decimal<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    LegOrderQty                                         (685),
    /**
     * The price type of the {@link TagNum#LegBidPx} (681) and/or {@link TagNum#LegOfferPx} (684).
     * See {@link TagNum#PriceType} (423) for description and valid values.<br/>
     * Type: Integer (see {@link PriceType})<br/>
     * Used: {@link LegQuoteSymbolGroup}
     */
    LegPriceType                                        (686),
    /**
     * Deprecated in 5.0SP1 Quantity of this leg, e.g. in Quote dialog.
     * See {@link TagNum#Quantity} (53) for description and valid values.<br/>
     * Type: Decimal<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    LegQty                                              (687),
    /**
     * Number of leg stipulation entries.<br/>
     * Type: String (see {@link StipulationType})<br/>
     * Used: {@link LegStipulations}
     */
    LegStipulationType                                  (688),
    /**
     * For Fixed Income, value of stipulation. See {@link TagNum#StipulationValue} (234) for
     * description and valid values.<br/>
     * Type: String<br/>
     * Used: {@link LegStipulations}
     */
    LegStipulationValue                                 (689),
    /**
     * For Fixed Income, used instead of {@link TagNum#LegQty} (687) or {@link TagNum#LegOrderQty} (685) to
     * requests the respondent to calculate the quantity based on the quantity on the
     * opposite side of the swap.<br/>
     * Type: Integer (see {@link LegSwapType})<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    LegSwapType                                         (690),
    /**
     * For Fixed Income, identifies MBS / ABS pool.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}, {@link IOIMsg}
     */
    Pool                                                (691),
    /**
     * Code to represent price type requested in Quote.
     * If the Quote Request is for a Swap values 1-8 apply to all legs.<br/>
     * Type: Integer  (see {@link QuotePriceType})<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    QuotePriceType                                      (692),
    /**
     * Message reference for Quote Response.<br/>
     * Type: String<br/>
     * Used: {@link QuoteMsg}
     */
    QuoteRespID                                         (693),
    /**
     * Identifies the type of Quote Response.<br/>
     * Type: Integer (see {@link QuoteRespType})<br/>
     * Used: {@link QuoteMsg}
     */
    QuoteRespType                                       (694),
    /**
     * Code to qualify Quote use. See {@link TagNum#IOIQualifier} (104) for description and valid values.<br/>
     * Type: Character  (see {@link IOIQualifier})<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    QuoteQualifier                                      (695),
    /**
     * Date to which the yield has been calculated (i.e. maturity, par call or current call,
     * pre-refunded date).<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.YieldData}
     */
    YieldRedemptionDate                                 (696),
    /**
     * Price to which the yield has been calculated.<br/>
     * Type: Price<br/>
     * Used: {@link net.hades.fix.message.comp.YieldData}
     */
    YieldRedemptionPrice                                (697),
    /**
     * The price type of the {@link TagNum#YieldRedemptionPrice} (697). See {@link TagNum#PriceType} (423) for description
     * and valid values.<br/>
     * Type: Integer (see {@link PriceType})<br/>
     * Used: {@link net.hades.fix.message.comp.YieldData}
     */
    YieldRedemptionPriceType                            (698),
    /**
     * The identifier of the benchmark security, e.g. Treasury against Corporate bond.
     * See {@link TagNum#SecurityID} (tag 48) for description and valid values.<br/>
     * Type: String<br/>
     * Used: {@link SpreadOfBenchmarkCurveData}
     */
    BenchmarkSecurityID                                 (699),
    /**
     * Indicates a trade that reverses a previous trade.<br/>
     * Type: Boolean<br/>
     * Used: {@link AllocationInstructionMsg},{@link AllocationReportMsg},{@link AllocationInstructionAlertMsg}
     */
    ReversalIndicator                                   (700),
    /**
     * Include as needed to clarify yield irregularities associated with date, e.g.
     * when it falls on a non-business day.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.YieldData}
     */
    YieldCalcDate                                       (701),
    /**
     * Number of position entries.<br/>
     * Type: Integer<br/>
     * Used: {@link PositionQty}
     */
    NoPositions                                         (702),
    /**
     * Used to identify the type of quantity that is being returned.<br/>
     * Type: String (see {@link PosType})<br/>
     * Used: {@link PositionQty}
     */
    PosType                                             (703),
    /**
     * Long Quantity.<br/>
     * Type: Double<br/>
     * Used: {@link PositionQty}
     */
    LongQty                                             (704),
    /**
     * Short Quantity.<br/>
     * Type: Double<br/>
     * Used: {@link PositionQty}
     */
    ShortQty                                            (705),
    /**
     * Status of this position.<br/>
     * Type: Integer (see {@link PosQtyStatus})<br/>
     * Used: {@link PositionQty}
     */
    PosQtyStatus                                        (706),
    /**
     * Type of Position amount.<br/>
     * Type: String (see {@link PosAmtType})<br/>
     * Used: {@link PositionAmountData}
     */
    PosAmtType                                          (707),
    /**
     * Position amount.<br/>
     * Type: Double<br/>
     * Used: {@link PositionAmountData}
     */
    PosAmt                                              (708),
    /**
     * Identifies the type of position transaction.<br/>
     * Type: Integer (see {@link PosAmtType})<br/>
     * Used: {@link PositionMaintenanceRequestMsg},{@link PositionMaintenanceReportMsg}
     */
    PosTransType                                        (709),
    /**
     * Unique identifier for the position maintenance request as assigned by the submitter.<br/>
     * Type: String<br/>
     * Used: {@link PositionMaintenanceRequestMsg},{@link PositionMaintenanceReportMsg},{@link RequestForPositionsMsg},
     * {@link RequestForPositionsAckMsg},{@link PositionReportMsg},{@link AssignmentReportMsg}
     */
    PosReqID                                            (710),
    /**
     * Number of underlying legs that make up the security.<br/>
     * Type: Integer<br/>
     * Used: {@link AdvertismentMsg},{@link IOIMsg},{@link net.hades.fix.message.NewsMsg}
     */
    NoUnderlyings                                       (711),
    /**
     * Maintenance Action to be performed.<br/>
     * Type: Integer (see {@link PosMaintAction})<br/>
     * Used: {@link PositionMaintenanceRequestMsg},{@link PositionMaintenanceReportMsg}
     */
    PosMaintAction                                      (712),
    /**
     * Reference to the {@link TagNum#PosReqID} (710) of a previous maintenance request that is being replaced or canceled.<br/>
     * Type: String<br/>
     * Used: {@link PositionMaintenanceRequestMsg},{@link PositionMaintenanceReportMsg}
     */
    OrigPosReqRefID                                     (713),
    /**
     * Reference to a {@link TagNum#PosMaintRptID} (721) from a previous Position Maintenance Report that is being replaced or canceled.<br/>
     * Type: String<br/>
     * Used: {@link PositionMaintenanceRequestMsg},{@link PositionMaintenanceReportMsg},{@link AdjustedPositionReportMsg}
     */
    PosMaintRptRefID                                    (714),
    /**
     * The "Clearing Business Date" referred to by this maintenance request.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link MarketDataSnapshot}
     */
    ClearingBusinessDate                                (715),
    /**
     * Identifies a specific settlement session.<br/>
     * Type: String (see {@link SettlSessID})<br/>
     * Used: {@link AdjustedPositionReportMsg}, {@link AssignmentReportMsg}, {@link CollateralAssignmentMsg}, {@link CollateralInquiryMsg},
     *  {@link CollateralInquiryAckMsg}, {@link CollateralReportMsg}, {@link CollateralRequestMsg}, {@link PositionMaintenanceReportMsg},
     *  {@link PositionMaintenanceRequestMsg}, {@link PositionReportMsg}, {@link RequestForPositionsMsg}, {@link RequestForPositionsAckMsg},
     *  {@link TradeCaptureReportMsg}, {@link TradeCaptureReportAckMsg}
     */
    SettlSessID                                         (716),
    /**
     * SubID value associated with {@link TagNum#SettlSessID} (716).<br/>
     * Type: String<br/>
     * Used: {@link AdjustedPositionReportMsg}, {@link AssignmentReportMsg}, {@link CollateralAssignmentMsg}, {@link CollateralInquiryMsg},
     *  {@link CollateralInquiryAckMsg}, {@link CollateralReportMsg}, {@link CollateralRequestMsg}, {@link PositionMaintenanceReportMsg},
     *  {@link PositionMaintenanceRequestMsg}, {@link PositionReportMsg}, {@link RequestForPositionsMsg}, {@link RequestForPositionsAckMsg},
     *  {@link TradeCaptureReportMsg}, {@link TradeCaptureReportAckMsg}
     */
    SettlSessSubID                                      (717),
    /**
     * Type of adjustment to be applied, used for PCS and PAJ.<br/>
     * Type: Integer (see {@link AdjustmentType})<br/>
     * Used: {@link PositionMaintenanceReportMsg},{@link PositionMaintenanceRequestMsg}
     */
    AdjustmentType                                      (718),
    /**
     * Used to indicate when a contrary instruction for exercise or abandonment is being submitted.<br/>
     * Type: Boolean<br/>
     * Used: {@link PositionMaintenanceReportMsg},{@link PositionMaintenanceRequestMsg}
     */
    ContraryInstructionIndicator                        (719),
    /**
     * Indicates if requesting a rollover of prior day's spread submissions.<br/>
     * Type: Boolean<br/>
     * Used: {@link PositionMaintenanceReportMsg},{@link PositionMaintenanceRequestMsg}
     */
    PriorSpreadIndicator                                (720),
    /**
     * Unique identifier for this position report.<br/>
     * Type: String<br/>
     * Used: {@link AdjustedPositionReportMsg}, {@link PositionMaintenanceRequestMsg}, {@link PositionReportMsg},
     * {@link RequestForPositionsAckMsg}
     */
    PosMaintRptID                                       (721),
    /**
     * Status of Position Maintenance Request.<br/>
     * Type: Integer (see {@link PosMaintStatus})<br/>
     * Used: {@link PositionMaintenanceReportMsg}
     */
    PosMaintStatus                                      (722),
    /**
     * Result of Position Maintenance Request.
     * 4000+ Reserved and available for bi-laterally agreed upon user-defined values.<br/>
     * Type: Integer (see {@link PosMaintResult})<br/>
     * Used: {@link PositionMaintenanceReportMsg}
     */
    PosMaintResult                                      (723),
    /**
     * Used to specify the type of position request being made.<br/>
     * Type: Integer (see {@link PosReqType})<br/>
     * Used: {@link AdjustedPositionReportMsg},{@link PositionReportMsg},{@link RequestForPositionsMsg},
     * {@link RequestForPositionsAckMsg},
     */
    PosReqType                                          (724),
    /**
     * Identifies how the response to the request should be transmitted.
     * Details specified via {@link TagNum#ResponseDestination} (726).<br/>
     * Type: Integer (see {@link ResponseTransportType})<br/>
     * Used: {@link CollateralInquiryMsg},{@link CollateralInquiryAckMsg},{@link RequestForPositionsMsg},
     * {@link RequestForPositionsAckMsg},{@link TradeCaptureReportAckMsg},{@link TradeCaptureReportRequestMsg},
     * {@link TradeCaptureReportRequestAckMsg}
     */
    ResponseTransportType                               (725),
    /**
     * URI (Uniform Resource Identifier) for details) or other pre-arranged value.
     * Used in conjunction with ResponseTransportType (725) value of Out-of-Band to identify the out-of-band destination.<br/>
     * Type: String<br/>
     * Used: {@link CollateralInquiryMsg},{@link CollateralInquiryAckMsg},{@link RequestForPositionsMsg},
     * {@link RequestForPositionsAckMsg},{@link TradeCaptureReportAckMsg},{@link TradeCaptureReportRequestMsg},
     * {@link TradeCaptureReportRequestAckMsg}
     */
    ResponseDestination                                 (726),
    /**
     * Total number of Position Reports being returned.<br/>
     * Type: Integer<br/>
     * Used: {@link PositionReportMsg},{@link RequestForPositionsAckMsg}
     */
    TotalNumPosReports                                  (727),
    /**
     * Result of Request for Position.
     * 4000+ Reserved and available for bi-laterally agreed upon user-defined values<br/>
     * Type: Integer (see {@link PosReqResult} for standard values)<br/>
     * Used: {@link PositionReportMsg},{@link RequestForPositionsAckMsg}
     */
    PosReqResult                                        (728),
    /**
     * Status of Request for Positions.<br/>
     * Type: Integer (see {@link PosReqStatus})<br/>
     * Used: {@link RequestForPositionsAckMsg}
     */
    PosReqStatus                                        (729),
    /**
     * Settlement price.<br/>
     * Type: Double<br/>
     * Used: {@link AdjustedPositionReportMsg}, {@link AssignmentReportMsg}, {@link PositionReportMsg}
     */
    SettlPrice                                          (730),
    /**
     * Type of settlement price.<br/>
     * Type: Integer (see {@link SettlPriceType})<br/>
     * Used: {@link AssignmentReportMsg}, {@link PositionReportMsg}
     */
    SettlPriceType                                      (731),
    /**
     * Underlying security's SettlPrice. See {@link TagNum#SettlPrice} (730) field for description.<br/>
     * Type: Double<br/>
     * Used: {@link AssignmentReportMsg}, {@link PositionReportMsg}
     */
    UnderlyingSettlPrice                                (732),
    /**
     * Underlying security's SettlPriceType. See {@link TagNum#SettlPriceType} (731) field for description.<br/>
     * Type: Integer (see {@link SettlPriceType})<br/>
     * Used: {@link PosUndInstrmtGroup}
     */
    UnderlyingSettlPriceType                            (733),
    /**
     * Previous settlement price.<br/>
     * Type: Double<br/>
     * Used: {@link AdjustedPositionReportMsg}, {@link AssignmentReportMsg}, {@link PositionReportMsg}
     */
    PriorSettlPrice                                     (734),
    /**
     * Number of repeating groups of {@link TagNum#QuoteQualifiers} (695).<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    NoQuoteQualifiers                                   (735),
    /**
     * Currency code of settlement denomination for a specific {@link TagNum#AllocAccount} (79).<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: 
     */
    AllocSettlCurrency                                  (736),
    /**
     * Total amount due expressed in settlement currency (includes the effect of the
     * forex transaction) for a specific {@link TagNum#AllocAccount}(79).<br/>
     * Type: Decimal<br/>
     * Used: {@link AllocGroup}
     */
    AllocSettlCurrAmt                                   (737),
    /**
     * Amount of interest (i.e. lump-sum) at maturity.<br/>
     * Type: Decimal<br/>
     * Used: {@link AllocationInstructionMsg},{@link AllocationInstructionAlertMsg},{@link AllocationReportMsg},{@link ConfirmationMsg},
     * {@link ExecutionReportMsg}
     */
    InterestAtMaturity                                  (738),
    /**
     * The effective date of a new securities issue determined by its underwriters.
     * Often but not always the same as the Issue Date and the Interest Accrual Date.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegDatedDate                                        (739),
    /**
     * NFor Fixed Income, identifies MBS / ABS pool for a specific leg of a multi-leg instrument.<br/>
     * See {@link TagNum#Pool} (691) for description and valid values.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegPool                                             (740),
    /**
     * Amount of interest (i.e. lump-sum) at maturity at the account-level.<br/>
     * Type: Double<br/>
     * Used: {@link AllocGroup}
     */
    AllocInterestAtMaturity                             (741),
    /**
     * Amount of Accrued Interest for convertible bonds and fixed income at the allocation-level.<br/>
     * Type: Double<br/>
     * Used: {@link AllocGroup}
     */
    AllocAccruedInterestAmt                             (742),
    /**
     * Date of delivery.<br/>
     * Type: Local market date<br/>
     * Used: {@link PositionReportMsg}
     */
    DeliveryDate                                        (743),
    /**
     * Method by which short positions are assigned to an exercise notice during exercise and assignment processing.<br/>
     * Type: Character (See {@link AssignmentMethod})<br/>
     * Used: {@link AssignmentReportMsg}
     */
    AssignmentMethod                                    (744),
    /**
     * Quantity Increment used in performing assignment.<br/>
     * Type: Double<br/>
     * Used: {@link AssignmentReportMsg}
     */
    AssignmentUnit                                      (745),
    /**
     * Open interest that was eligible for assignment.<br/>
     * Type: Double<br/>
     * Used: {@link AssignmentReportMsg}
     */
    OpenInterest                                        (746),
    /**
     * Exercise Method used to in performing assignment.<br/>
     * Type: Character (See {@link ExerciseMethod})<br/>
     * Used: {@link AssignmentReportMsg}
     */
    ExerciseMethod                                      (747),
    /**
     * Total number of trade reports returned.<br/>
     * Type: Integer<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    TotNumTradeReports                                  (748),
    /**
     * Result of Trade Request.<br/>
     * Type: Integer (See {@link TradeRequestResult})<br/>
     * Used: {@link TradeCaptureReportRequestAckMsg}
     */
    TradeRequestResult                                  (749),
    /**
     * Status of Trade Request.<br/>
     * Type: Integer (See {@link TradeRequestStatus})<br/>
     * Used: {@link TradeCaptureReportRequestAckMsg}
     */
    TradeRequestStatus                                  (750),
    /**
     * Reason Trade Capture Request was rejected.
     * 4000+ Reserved and available for bi-laterally agreed upon user-defined values<br/>
     * Type: Integer (for standard values see {@link TradeReportRejectReason})<br/>
     * Used: {@link TradeCaptureReportRequestAckMsg}
     */
    TradeReportRejectReason                             (751),
    /**
     * Number of position amount entries.<br/>
     * Type: Integer<br/>
     * Used: {@link PositionAmountData}
     */
    NoPosAmt                                            (752),
    /**
     * Used to indicate if the side being reported on Trade Capture Report represents a leg of a 
     * multi-leg instrument or a single security.<br/>
     * Type: Integer (see {@link MultiLegReportingType})<br/>
     * Used: 
     */
    SideMultiLegReportingType                           (753),
    /**
     * Identifies whether or not an allocation has been automatically accepted on behalf of the Carry Firm by the Clearing House.<br/>
     * Type: Boolean<br/>
     * Used: {@link AllocationInstructionMsg},{@link AllocationInstructionAlertMsg},{@link AllocationReportMsg}
     */
    AutoAcceptIndicator                                 (754),
    /**
     * Unique identifier for Allocation Report message.<br/>
     * Type: String<br/>
     * Used: {@link AllocationReportMsg},{@link AllocationReportAckMsg}
     */
    AllocReportID                                       (755),
    /**
     * Refer to definition of {@link TagNum#NoPartyIDs} (453)<br/>
     * Type: Integer<br/>
     * Used:
     */
    NoNested2PartyIDs                                   (756),
    /**
     * Refer to definition of {@link TagNum#PartyID} (448)<br/>
     * Type: String<br/>
     * Used:
     */
    Nested2PartyID                                      (757),
    /**
     * Refer to definition of {@link TagNum#PartyIDSource} (447)<br/>
     * Type: Character (see {@link PartyIDSource})<br/>
     * Used:
     */
    Nested2PartyIDSource                                (758),
    /**
     * Refer to definition of {@link TagNum#PartyRole} (452)<br/>
     * Type: Character (see {@link PartyRole})<br/>
     * Used:
     */
    Nested2PartyRole                                    (759),
    /**
     * Refer to definition of {@link TagNum#PartySubID} (523)<br/>
     * Type: String<br/>
     * Used:
     */
    Nested2PartySubID                                   (760),
    /**
     * Identifies class or source of the BenchmarkSecurityID (699) value.
     * Required if BenchmarkSecurityID is specified. Same values as the {@link TagNum#SecurityIDSource} (22) field.
     * Type: String<br/>
     * Used: {@link SpreadOfBenchmarkCurveData}
     */
    BenchmarkSecurityIDSource                           (761),
    /**
     * Sub-type qualification/identification of the SecurityType (e.g. for SecurityType="REPO), or
     * the CFICode if SecurityType is not specified. If specified, SecuirtyType or CFICode is required.<br/>
     * Example Values:<br/>
     * General = General Collateral (for SecurityType=REPO)<br/>
     * For SecurityType="MLEG" markets can provide the name of the option or futures strategy, 
     * such as Calendar, Vertical, Butterfly, etc.<br/>
     * NOTE: Additional values may be used by mutual agreement of the counterparties.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    SecuritySubType                                     (762),
    /**
     * Underlying security’s SecuritySubType. See See {@link TagNum#SecuritySubType} (762) field for description.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingSecuritySubType                           (763),
    /**
     * SecuritySubType of the leg instrument. See {@link TagNum#SecuritySubType} (762)
     * field for description.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegSecuritySubType                                  (764),
    /**
     * The maximum percentage that execution of one side of a program trade can exceed execution of the other.<br/>
     * Type: Double<br/>
     * Used: {@link NewOrderListMsg}
     */
    AllowableOneSidednessPct                            (765),
    /**
     * The maximum amount that execution of one side of a program trade can exceed execution of the other.<br/>
     * Type: Double<br/>
     * Used: {@link NewOrderListMsg}
     */
    AllowableOneSidednessValue                          (766),
    /**
     * The currency that AllowableOneSidednessValue (766) is expressed in if AllowableOneSidednessValue is used.<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: {@link NewOrderListMsg}
     */
    AllowableOneSidednessCurr                           (767),
    /**
     * Number of TrdRegTimestamp (769) entries.<br/>
     * Type: Integer<br/>
     * Used: {@link TrdRegTimestamps}
     */
    NoTrdRegTimestamps                                  (768),
    /**
     * Traded / Regulatory timestamp value. Use to store time information required by
     * government regulators or self regulatory organizations (such as an exchange or clearing house).<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link TrdRegTimestamps}
     */
    TrdRegTimestamp                                     (769),
    /**
     * Traded / Regulatory timestamp type.<br/>
     * Type: Integer (see {@link TrdRegTimestampType}))<br/>
     * Used: {@link TrdRegTimestamps}
     */
    TrdRegTimestampType                                 (770),
    /**
     * Text which identifies the "origin" (i.e. system which was used to generate the time stamp) for the
     * Traded / Regulatory timestamp value.<br/>
     * Type: String<br/>
     * Used: {@link TrdRegTimestamps}
     */
    TrdRegTimestampOrigin                               (771),
    /**
     * Reference identifier to be used with ConfirmTransType (666) = Replace or Cancel.<br/>
     * Type: String<br/>
     * Used: {@link ConfirmationMsg}
     */
    ConfirmRefID                                        (772),
    /**
     * Identifies the type of Confirmation message being sent.<br/>
     * Type: Integer (see {@link ConfirmType}))<br/>
     * Used: {@link ConfirmationMsg}, {@link ConfirmationRequestMsg}
     */
    ConfirmType                                         (773),
    /**
     * Identifies the reason for rejecting a Confirmation.<br/>
     * Type: Integer (see {@link ConfirmRejReason}))<br/>
     * Used: {@link ConfirmationAckMsg}
     */
    ConfirmRejReason                                    (774),
    /**
     * Method for booking out this order. Used when notifying a broker that an order to be
     * settled by that broker is to be booked out as an OTC derivative (e.g. CFD or similar).<br/>
     * Type: Integer (see {@link BookingType}))<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    BookingType                                         (775),
    /**
     * Identified reason for rejecting an individual {@link TagNum#AllocAccount} (79) detail. 
     * Same values as {@link TagNum#AllocRejCode} (88).<br/>
     * Type: Integer (see {@link AllocRejCode})<br/>
     * Used: {@link AllocAckGroup}
     */
    IndividualAllocRejCode                              (776),
    /**
     * Unique identifier for Settlement Instruction message.<br/>
     * Type: String<br/>
     * Used: {@link SettlementInstructionsMsg}
     */
    SettlInstMsgID                                      (777),
    /**
     * Number of settlement instructions within repeating group.<br/>
     * Type: Integer<br/>
     * Used: {@link SettlementInstructionsMsg}
     */
    NoSettlInst                                         (778),
    /**
     * Timestamp of last update to data item (or creation if no updates made since creation).<br/>
     * Type: UTC Timestamp<br/>
     * Used: {@link SettlementInstructionRequestMsg},{@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    LastUpdateTime                                      (779),
    /**
     * Used to indicate whether settlement instructions are provided on an allocation instruction message,
     * and if not, how they are to be derived.<br/>
     * Type: Integer (see {@link AllocSettlInstType})<br/>
     * Used: {@link AllocGroup}
     */
    AllocSettlInstType                                  (780),
    /**
     * Number of  {@link TagNum#SettlPartyID} (782),  {@link TagNum#SettlPartyIDSource} (783), and
     * {@link TagNum#SettlPartyRole} (784) entries.<br/>
     * Type: Integer<br/>
     * Used: {@link SettlPartiesGroup}
     */
    NoSettlPartyIDs                                     (781),
    /**
     * PartyID value within a settlement parties component. Nested repeating group. Same values as {@link TagNum#PartyID} (448).<br/>
     * Type: String<br/>
     * Used: {@link SettlPartiesGroup}
     */
    SettlPartyID                                        (782),
    /**
     * PartyIDSource value within a settlement parties component. Same values as {@link TagNum#PartyIDSource} (447).<br/>
     * Type: Character (see {@link PartyIDSource})<br/>
     * Used: {@link SettlPartiesGroup}
     */
    SettlPartyIDSource                                  (783),
    /**
     * PartyRole value within a settlement parties component. Same values as {@link TagNum#PartyRole} (452).<br/>
     * Type: Integer (see {@link PartyRole})<br/>
     * Used: {@link SettlPartiesGroup}
     */
    SettlPartyRole                                      (784),
    /**
     * PartySubID value within a settlement parties component. Same values as {@link TagNum#PartySubID} (523).<br/>
     * Type: String (see {@link PartySubID})<br/>
     * Used: {@link SettlPtysSubGroup}
     */
    SettlPartySubID                                     (785),
    /**
     * Type of {@link TagNum#SettlPartySubID} (785) value. Same values as {@link TagNum#PartySubIDType} (803).<br/>
     * Type: Integer (see {@link PartySubIDType})<br/>
     * Used: {@link SettlPtysSubGroup}
     */
    SettlPartySubIDType                                 (786),
    /**
     * Used to indicate whether a delivery instruction is used for securities or cash settlement.<br/>
     * Type: Character (see {@link DlvyInstType})<br/>
     * Used: {@link SettlPtysSubGroup}
     */
    DlvyInstType                                        (787),
    /**
     * Type of financing termination.<br/>
     * Type: Integer (see {@link TerminationType})<br/>
     * Used: {@link net.hades.fix.message.comp.FinancingDetails}
     */
    TerminationType                                     (788),
    /**
     * Optional, alternative via counterparty bi-lateral agreement message gap 
     * detection and recovery approach.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    NextExpectedMsgSeqNum                               (789),
    /**
     * Can be used to uniquely identify a specific Order Status Request message.<br/>
     * Type: String<br/>
     * Used: {@link ExecutionReportMsg},{@link OrderStatusRequestMsg}
     */
    OrdStatusReqID                                      (790),
    /**
     * Unique ID of settlement instruction request message.<br/>
     * Type: String<br/>
     * Used: {@link SettlementInstructionRequestMsg},{@link SettlementInstructionsMsg}
     */
    SettlInstReqID                                      (791),
    /**
     * Identifies reason for rejection (of a settlement instruction request message).<br/>
     * Type: Integer (see {@link SettlInstReqRejCode})<br/>
     * Used: {@link SettlementInstructionRequestMsg},{@link SettlementInstructionsMsg}
     */
    SettlInstReqRejCode                                 (792),
    /**
     * Secondary allocation identifier. Unlike the AllocID (70), this can be shared across a number
     * of allocation instruction or allocation report messages, thereby making it possible to pass
     * an identifier for an original allocation message on multiple messages (e.g. from one party
     * to a second to a third, across cancel and replace messages etc.)<br/>
     * Type: String<br/>
     * Used: {@link AllocationInstructionMsg},{@link AllocationInstructionAckMsg},{@link AllocationInstructionAlertMsg},
     * {@link AllocationReportMsg},{@link AllocationReportAckMsg},{@linkConfirmationMsg},{@link ConfirmationRequestMsg},
     */
    SecondaryAllocID                                    (793),
    /**
     * Describes the specific type or purpose of an Allocation Report message.<br/>
     * Type: Integer (see {@link AllocReportType})<br/>
     * Used: {@link AllocationReportMsg},{@link AllocationReportAckMsg}
     */
    AllocReportType                                     (794),
    /**
     * Reference identifier to be used with {@link TagNum#AllocTransType} (7) = Replace or Cancel.<br/>
     * Type: String<br/>
     * Used: {@link AllocationReportMsg}
     */
    AllocReportRefID                                    (795),
    /**
     * Reason for canceling or replacing an Allocation Instruction or Allocation Report message.<br/>
     * Type: Integer (see {@link AllocCancReplaceReason})<br/>
     * Used: {@link AllocationReportMsg},{@link AllocationInstructionMsg},{@link AllocationInstructionAlertMsg}
     */
    AllocCancReplaceReason                              (796),
    /**
     * Indicates whether or not this message is a drop copy of another message.<br/>
     * Type: Boolean<br/>
     * Used: {@link ExecutionReportMsg},{@link ConfirmationMsg},{@linkTradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    CopyMsgIndicator                                    (797),
    /**
     * Type of account associated with a confirmation or other trade-level message.<br/>
     * Type: Integer (see {@link AllocAccountType})<br/>
     * Used: {@link ConfirmationMsg},{@link ConfirmationRequestMsg}
     */
    AllocAccountType                                    (798),
    /**
     * Average price for a specific order.<br/>
     * Type: Double<br/>
     * Used: {@link OrderAllocGroup}
     */
    OrderAvgPx                                          (799),
    /**
     * Quantity of the order that is being booked out as part of an Allocation Instruction or Allocation Report message.<br/>
     * Type: Double<br/>
     * Used: {@link OrderAllocGroup}
     */
    OrderBookingQty                                     (800),
    /**
     * Number of {@link TagNum#SettlPartySubID} (785) and {@link TagNum#SettlPartySubIDType} (786) entries.<br/>
     * Type: Integer<br/>
     * Used: {@link OrderAllocGroup}
     */
    NoSettlPartySubIDs                                  (801),
    /**
     * Number of {@link TagNum#PartySubID} (523)and {@link TagNum#PartySubIDType} (803) entries.
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.Parties}
     */
    NoPartySubIDs                                       (802),
    /**
     * Type of {@link TagNum#PartySubID} (523) value<br/>
     * 4000+ = Reserved and available for bi-laterally agreed upon user defined values
     * Type: Integer (see {@link PartySubIDType})<br/>
     * Used: {@link PartySubID}
     */
    PartySubIDType                                      (803),
    /**
     * Number of {@link TagNum#NestedPartySubID} (545) and {@link TagNum#NestedPartySubIDType} (805) entries.<br/>
     * Type: Integer<br/>
     * Used: {@link NestedPartyGroup}
     */
    NoNestedPartySubIDs                                 (804),
    /**
     * Type of NestedPartySubID (545) value. Same values as {@link TagNum#PartySubIDType} (803).<br/>
     * Type: Integer (see {@link PartySubIDType})<br/>
     * Used: {@link NestedPartyGroup}, {@link NstdPtysSubGroup}
     */
    NestedPartySubIDType                                (805),
    /**
     * Refer to definition of {@link TagNum#NoPartySubIDs} (802)<br/>
     * Type: Integer<br/>
     * Used:
     */
    NoNested2PartySubIDs                                (806),
    /**
     * Refer to definition of {@link TagNum#PartySubIDType} (803)<br/>
     * Type: Integer (see {@link PartySubIDType})<br/>
     * Used:
     */
    Nested2PartySubIDType                               (807),
    /**
     * Response to allocation to be communicated to a counterparty through an intermediary, i.e.
     * clearing house. Used in conjunction with AllocType = "Request to Intermediary" and
     * AllocReportType = "Request to Intermediary"<br/>
     * Type: Integer (see {@link AllocIntermedReqType})<br/>
     * Used:  {@link AllocationInstructionMsg},{@link AllocationInstructionAckMsg},{@link AllocationInstructionAlertMsg},
     * {@link AllocationReportMsg},{@link AllocationReportAckMsg}
     */
    AllocIntermedReqType                                (808),
    /**
     * Number of Usernames to which this this response is directed.<br/>
     * Type: Integer<br/>
     * Used:  {@link UsernameGroup}
     */
    NoUsernames                                         (809),
    /**
     * Underlying price associate with a derivative instrument.<br/>
     * Type: Price<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingPx                                        (810),
    /**
     * The rate of change in the price of a derivative with respect to the movement in the price of the underlying
     * instrument(s) upon which the derivative instrument price is based.<br/>
     * This value is normally between -1.0 and 1.0.<br/>
     * Type: Double<br/>
     * Used: {@link ExecutionReportMsg},{@link PositionReportMsg}
     */
    PriceDelta                                          (811),
    /**
     * Used to specify the maximum number of application messages that can be
     * queued bedore a corrective action needs to take place to resolve the
     * queuing issue.<br/>
     * Type: Integer<br/>
     * Used: {@link MarketDataRequestMsg}
     */
    ApplQueueMax                                        (812),
    /**
     * Current number of application messages that were queued at the time that the message was created
     * by the counterparty.<br/>
     * Type: Integer<br/>
     * Used: {@link MarketDataIncrementalRefreshMsg},{@link MarketDataSnapshotFullRefreshMsg}
     */
    ApplQueueDepth                                      (813),
    /**
     * Resolution taken when {@link TagNum#ApplQueueDepth} (813) exceeds {@link TagNum#ApplQueueMax} (812)
     * or system specified maximum queue size.<br/>
     * Type: Integer (see {@link ApplQueueResolution})<br/>
     * Used: {@link MarketDataIncrementalRefreshMsg},{@link MarketDataSnapshotFullRefreshMsg}
     */
    ApplQueueResolution                                 (814),
    /**
     * Action to take to resolve an application message queue (backlog).<br/>
     * Type: Integer (see {@link ApplQueueAction})<br/>
     * Used: {@link MarketDataRequestMsg}
     */
    ApplQueueAction                                     (815),
    /**
     * Number of alternative market data sources.<br/>
     * Type: Integer<br/>
     * Used: {@link AltMDSourceGroup}
     */
    NoAltMDSource                                       (816),
    /**
     * Session layer source for market data.<br/>
     * Type: String<br/>
     * Used: {@link AltMDSourceGroup}
     */
    AltMDSourceID                                       (817),
    /**
     * Secondary trade report identifier - can be used to associate an additional identifier with a trade.<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg},{@link TradeCaptureReportRequestMsg}
     */
    SecondaryTradeReportID                              (818),
    /**
     * Average Pricing Indicator.<br/>
     * Type: Integer (see {@link ApplQueueResolution})<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg},{@link TradeCaptureReportRequestMsg}
     */
    AvgPxIndicator                                      (819),
    /**
     * Used to link a group of trades together. Useful for linking a group of trades together for average
     * price calculations.<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg},{@link TradeCaptureReportRequestMsg}
     */
    TradeLinkID                                         (820),
    /**
     * Specific device number, terminal number or station where order was entered.<br/>
     * Type: String<br/>
     * Used: {@link TradeReportOrderDetailMsg}
     */
    OrderInputDevice                                    (821),
    /**
     * Trading Session in which the underlying instrument trades.<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    UnderlyingTradingSessionID                          (822),
    /**
     * Trading Session sub identifier in which the underlying instrument trades.<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    UnderlyingTradingSessionSubID                       (823),
    /**
     * Reference to the leg of a multileg instrument to which this trade refers.<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    TradeLegRefID                                       (824),
    /**
     * Used to report any exchange rules that apply to this trade.
     * Primarily intended for US futures markets. Certain trading practices are permitted by the CFTC,
     * such as large lot trading, block trading, all or none trades. If the rules are used, the exchanges
     * are required to indicate these rules on the trade.<br/>
     * Type: String<br/>
     * Used: {@link TrdCapRptAckSideGroup},{@link TrdCapRptSideGroup}
     */
    ExchangeRule                                        (825),
    /**
     * Identifies how the trade is to be allocated.<br/>
     * Type: Integer (see {@link TradeAllocIndicator})<br/>
     * Used: {@link TrdCapRptAckSideGroup},{@link TrdCapRptSideGroup}
     */
    TradeAllocIndicator                                 (826),
    /**
     * Part of trading cycle when an instrument expires. Field is applicable for derivatives.<br/>
     * Type: Integer (see {@link ExpirationCycle})<br/>
     * Used: {@link SecurityDefinitionRequestMsg}
     */
    ExpirationCycle                                     (827),
    /**
     * Type of Trade.<br/>
     * Type: Integer (see {@link TrdType})<br/>
     * Used: {@link MarketDataRequest},{@link AllocationInstruction} ,{@link AllocationInstructionAlert} ,
     * {@link AllocationReport} ,{@link TradeCaptureReport} ,{@link TradeCaptureReportAck} ,
     * {@link TradeCaptureReportRequest}
     */
    TrdType                                             (828),
    /**
     * Further qualification to the trade type.<br/>
     * Type: Integer (see {@link TrdSubType})<br/>
     * Used: {@link AllocationInstructionMsg},{@link AllocationInstructionAlertMsg},{@link AllocationReportMsg},{@link TradeCaptureReportMsg}
     * ,{@link TradeCaptureReportAckMsg},{@link TradeCaptureReportRequestMsg}
     */
    TrdSubType                                          (829),
    /**
     * Reason trade is being transferred.<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg},{@link TradeCaptureReportRequestMsg}
     */
    TransferReason                                      (830),
    /**
     * Unique identifier for the Assignment Report Request.<br/>
     * Type: String<br/>
     * Used:
     */
    AsgnReqID                                           (831),
    /**
     * Total Number of Assignment Reports being returned to a firm.<br/>
     * Type: Integer<br/>
     * Used: {@link AssignmentReportMsg}
     */
    TotNumAssignmentReports                             (832),
    /**
     * Unique identifier for the Assignment Report.<br/>
     * Type: String<br/>
     * Used: {@link AssignmentReportMsg}
     */
    AsgnRptID                                           (833),
    /**
     * Amount that a position has to be in the money before it is exercised..<br/>
     * Type: Double<br/>
     * Used: {@link AssignmentReportMsg},{@link PositionMaintenanceReportMsg},{@link PositionMaintenanceRequestMsg}
     */
    ThresholdAmount                                     (834),
    /**
     * Describes whether peg is static or floats.<br/>
     * Type: Integer (see {@link PegMoveType})<br/>
     * Used: {@link PegInstructions}
     */
    PegMoveType                                         (835),
    /**
     * Type of Peg Offset value.<br/>
     * Type: Integer (see {@link PegOffsetType})<br/>
     * Used: {@link PegInstructions}
     */
    PegOffsetType                                       (836),
    /**
     * Type of Peg Limit.<br/>
     * Type: Integer (see {@link PegLimitType})<br/>
     * Used: {@link PegInstructions}
     */
    PegLimitType                                        (837),
    /**
     * If the calculated peg price is not a valid tick price, specifies whether to round the price to be more or less aggressive.<br/>
     * Type: Integer (see {@link PegRoundDirection})<br/>
     * Used: {@link PegInstructions}
     */
    PegRoundDirection                                   (838),
    /**
     * The price the order is currently pegged at.<br/>
     * Type: Double<br/>
     * Used: {@link ExecutionReportMsg}
     */
    PeggedPrice                                         (839),
    /**
     * The scope of the peg.<br/>
     * Type: Integer (see {@link PegScope})<br/>
     * Used: {@link PegInstructions}
     */
    PegScope                                            (840),
    /**
     * Describes whether discretionary price is static or floats.<br/>
     * Type: Integer (see {@link DiscretionMoveType})<br/>
     * Used: {@link DiscretionInstructions}
     */
    DiscretionMoveType                                  (841),
    /**
     * Type of Discretion Offset value.<br/>
     * Type: Integer (see {@link DiscretionOffsetType})<br/>
     * Used: {@link DiscretionInstructions}
     */
    DiscretionOffsetType                                (842),
    /**
     * Type of Discretion Limit.<br/>
     * Type: Integer (see {@link DiscretionLimitType})<br/>
     * Used: {@link DiscretionInstructions}
     */
    DiscretionLimitType                                 (843),
    /**
     * If the calculated discretionary price is not a valid tick price, specifies whether
     * to round the price to be more or less aggressive.<br/>
     * Type: Integer (see {@link DiscretionRoundDirection})<br/>
     * Used: {@link DiscretionInstructions}
     */
    DiscretionRoundDirection                            (844),
    /**
     * The current discretionary price of the order.<br/>
     * Type: Double<br/>
     * Used: {@link [ExecutionReportMag}
     */
    DiscretionPrice                                     (845),
    /**
     * The scope of the discretion.<br/>
     * Type: Integer (see {@link DiscretionScope})<br/>
     * Used: {@link DiscretionInstructions}
     */
    DiscretionScope                                     (846),
    /**
     * The target strategy of the order.<br/>
     * 1000+ = Reserved and available for bi-laterally agreed upon user defined values<br/>
     * Type: Integer (see {@link TargetStrategy} for standard value)<br/>
     * Used: {@link CrossOrderCancelReplaceRequestMsg},{@link ExecutionReportMsg} ,{@link MultilegOrderCancelReplaceMsg} ,
     * {@link AllocationReport} ,{@link TradeCaptureReport} ,{@link TradeCaptureReportAck} ,
     * {@link NewOrderCrossMsg},{@link NewOrderMultileg},{@link NewOrderSingle},{@link OrderCancelReplaceRequest}
     */
    TargetStrategy                                      (847),
    /**
     * Field to allow further specification of the TargetStrategy - usage to be agreed between counterparties.<br/>
     * Type: String<br/>
     * Used: {@link CrossOrderCancelReplaceRequestMsg},{@link ExecutionReportMsg} ,{@link MultilegOrderCancelReplaceMsg} ,
     * {@link NewOrderCrossMsg},{@link NewOrderMultileg},{@link NewOrderSingle},{@link OrderCancelReplaceRequest}
     */
    TargetStrategyParameters                            (848),
    /**
     * For a TargetStrategy=Participate order specifies the target particpation rate.
     * For other order types this is a volume limit (i.e. do not be more than this percent of the market volume).<br/>
     * Type: Double<br/>
     * Used: {@link CrossOrderCancelReplaceRequestMsg},{@link ExecutionReportMsg} ,{@link MultilegOrderCancelReplaceMsg} ,
     * {@link NewOrderCrossMsg},{@link NewOrderMultileg},{@link NewOrderSingle},{@link OrderCancelReplaceRequest}
     */
    ParticipationRate                                   (849),
    /**
     * For communication of the performance of the order versus the target strategy.<br/>
     * Type: Double<br/>
     * Used: {@link ExecutionReportMsg}
     */
    TargetStrategyPerformance                           (850),
    /**
     * Indicator to identify whether this fill was a result of a liquidity provider providing
     * or liquidity taker taking the liquidity. Applicable only for OrdStatus of Partial or Filled.<br/>
     * Type: Integer (see {@link LastLiquidityInd)}<br/>
     * Used: {@link ExecutionReportMsg}
     */
    LastLiquidityInd                                    (851),
    /**
     * Indicates if a trade should be reported via a market reporting service.<br/>
     * Type: Boolean<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    PublishTrdIndicator                                 (852),
    /**
     * Reason for short sale.<br/>
     * Type: Integer (see {@link ShortSaleReason)}<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    ShortSaleReason                                     (853),
    /**
     * Type of quantity specified in a quantity field. Valid values:
     * <ul>
     *      <li>0 = Units (shares, par, currency)
     *      <li>1 = Contracts (if used - should specify ContractMultiplier (tag 231)).
     * </ul>
     * Type: Integer (see {@link QtyType)}<br/>
     * Used: {@link AdvertismentMsg}, {@link IOIMsg}
     */
    QtyType                                             (854),
    /**
     * Additional {@link TagNum#TrdType} (828) assigned to a trade by trade match system.<br/>
     * Type: Integer (see {@link SecondaryTrdType)}<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg},{@link TradeCaptureReportAckMsg}
     */ 
    SecondaryTrdType                                    (855),
    /**
     * Type of Trade Report.<br/>
     * Type: Integer (see {@link TradeReportType)}<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    TradeReportType                                     (856),
    /**
     * Indicates how the orders being booked and allocated by an Allocation Instruction or
     * Allocation Report message are identified, i.e. by explicit definition in the NoOrders group or not.<br/>
     * Type: Integer (see {@link AllocNoOrdersType)}<br/>
     * Used: {@link AllocationInstructionMsg},{@link AllocationInstructionAlertMsg},{@link AllocationReportMsg}
     */
    AllocNoOrdersType                                   (857),
    /**
     * Commission to be shared with a third party, e.g. as part of a directed brokerage commission sharing arrangement.<br/>
     * Type: Double<br/>
     * Used: {@link ConfirmationMsg}
     */
    SharedCommission                                    (858),
    /**
     * Unique identifier for a Confirmation Request message.<br/>
     * Type: String<br/>
     * Used: {@link ConfirmationMsg},{@link ConfirmationRequestMsg}
     */
    ConfirmReqID                                        (859),
    /**
     * Used to express average price as percent of par (used where AvgPx field is expressed in some other way).<br/>
     * Type: Double<br/>
     * Used: {@link AllocationInstructionMsg},{@link AllocationInstructionAlertMsg},{@link AllocationInstructionAlertMsg},{@link ConfirmationMsg}
     */
    AvgParPx                                            (860),
    /**
     * Reported price (used to differentiate from AvgPx on a confirmation of a marked-up or marked-down principal trade).<br/>
     * Type: Double<br/>
     * Used: {@link ConfirmationMsg}
     */
    ReportedPx                                          (861),
    /**
     * Number of repeating OrderCapacity entries.<br/>
     * Type: Integer<br/>
     * Used: {@link CpctyConfGroup}
     */
    NoCapacities                                        (862),
    /**
     * Quantity executed under a specific OrderCapacity (e.g. quantity executed as agent, quantity executed as principal).<br/>
     * Type: Double<br/>
     * Used: {@link CpctyConfGroup}
     */
    OrderCapacityQty                                    (863),
    /**
     * Number of repeating EventType entries.<br/>
     * Type: Integer (see {@link QtyType)}<br/>
     * Used: {@link EvntGrp}
     */
    NoEvents                                            (864),
    /**
     * Code to represent the type of event.<br/>
     * Type: Integer (see {@link EventType)}<br/>
     * Used: {@link EvntGrp}
     */
    EventType                                           (865),
    /**
     * Date of event.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link EvntGrp}
     */
    EventDate                                           (866),
    /**
     * Predetermined price of issue at event, if applicable.<br/>
     * Type: Price<br/>
     * Used: {@link EvntGrp}
     */
    EventPx                                             (867),
    /**
     * Comments related to the event.<br/>
     * Type: String<br/>
     * Used: {@link EvntGrp}
     */
    EventText                                           (868),
    /**
     * Percent at risk due to lowest possible call.<br/>
     * Type: Double<br/>
     * Used: {@link InstrumentExtension}
     */
    PctAtRisk                                           (869),
    /**
     * Number of repeating InstrAttribType entries.<br/>
     * Type: Integer<br/>
     * Used: {@link InstrmtAttribGroup}
     */
    NoInstrAttrib                                       (870),
    /**
     * Code to represent the type of instrument attribute.<br/>
     * Type: Integer (see {@link InstrAttribType)}<br/>
     * Used: {@link InstrmtAttribGroup}
     */
    InstrAttribType                                     (871),
    /**
     * Attribute value appropriate to the {@link TagNum#InstrAttribType} (87) field.<br/>
     * Type: String<br/>
     * Used: {@link InstrmtAttribGroup}
     */
    InstrAttribValue                                    (872),
    /**
     * The effective date of a new securities issue determined by its underwriters.
     * Often but not always the same as the Issue Date and the Interest Accrual Date.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    DatedDate                                           (873),
    /**
     * The start date used for calculating accrued interest on debt instruments which are being
     * sold between interest payment dates. Often but not always the same as the Issue Date
     * and the Dated Date.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    InterestAccrualDate                                 (874),
    /**
     * The program under which a commercial paper is issued<br/>
     * Valid values: 1 - 3(a)(3) 2 - 4(2) 99 - Other or any value conforming to the data type Reserved100Plus<br/>
     * Type: Integer (QtyType)<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    CPProgram                                           (875),
    /**
     * The registration type of a commercial paper issuance.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    CPRegType                                           (876),
    /**
     * The program under which the underlying commercial paper is issued.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingCPProgram                                 (877),
    /**
     * The registration type of the underlying commercial paper issuance.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingCPRegType                                 (878),
    /**
     * Unit amount of the underlying security (par, shares, currency, etc.).<br/>
     * Type: Qty<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingQty                                       (879),
    /**
     * Identifier assigned to a trade by a matching system.<br/>
     * Type: String<br/>
     * Used: {@link ExecutionReportMsg},{@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg},{@link TradeCaptureReportRequestMsg}
     */
    TrdMatchID                                          (880),
    /**
     * Used to refer to a previous SecondaryTradeReportRefID when amending the transaction (cancel, replace, release, or reversal).<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    SecondaryTradeReportRefID                           (881),
    /**
     * Price (percent-of-par or per unit) of the underlying security or basket.<br/>
     * "Dirty" means it includes accrued interest.<br/>
     * Type: Price<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingDirtyPrice                                (882),
    /**
     * Price (percent-of-par or per unit) of the underlying security or basket at the end of the agreement.<br/>
     * Type: Price<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingEndPrice                                  (883),
    /**
     * Currency value attributed to this collateral at the start of the agreement.<br/>
     * Type: Amt<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingStartValue                                (884),
    /**
     * Currency value currently attributed to this collateral.<br/>
     * Type: Amt<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingCurrentValue                              (885),
    /**
     * Currency value attributed to this collateral at the end of the agreement.<br/>
     * Type: Amt<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingEndValue                                  (886),
    /**
     * Number of underlying stipulation entries.<br/>
     * Type: Integer<br/>
     * Used: {@link UnderlyingInstrument}
     */
    NoUnderlyingStips                                   (887),
    /**
     * Type of stipulation. Same values as {@link TagNum#StipulationType} (233).<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingStipType                                  (888),
    /**
     * Value of stipulation. Same values as {@link TagNum#StipulationValue} (234).<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingStipValue                                 (889),
    /**
     * Net Money at maturity if Zero Coupon and maturity value is different from par value.<br/>
     * Type: Double<br/>
     * Used: {@link Confirmation}
     */
    MaturityNetMoney                                    (890),
    /**
     * Defines the unit for a miscellaneous fee.<br/>
     * Type: Integer (see {@link MiscFeeBasis})<br/>
     * Used: {@link MiscFeesGroup}
     */
    MiscFeeBasis                                        (891),
    /**
     * Total number of NoAlloc entries across all messages. Should be the sum of all NoAllocs
     * in each message that has repeating NoAlloc entries related to the same AllocID or AllocReportID.
     * Used to support fragmentation.<br/>
     * Type: Integer<br/>
     * Used: {@link AllocationInstructionMsg},{@link AllocationInstructionAlertMsg},{@link AllocationReportMsg}
     */
    TotNoAllocs                                         (892),
    /**
     * Indicates whether this message is the last in a sequence of messages for those messages
     * that support fragmentation, such as Allocation Instruction, Mass Quote, Security List,
     * Derivative Security List.<br/>
     * Type: Boolean<br/>
     * Used: {@link net.hades.fix.message.group.QuoteSetGroup}
     */
    LastFragment                                        (893),
    /**
     * Collateral Request Identifier.<br/>
     * Type: String<br/>
     * Used: {@link CollateralAssignmentMsg},{@link CollateralRequestMsg},{@link CollateralResponseMsg}
     */
    CollReqID                                           (894),
    /**
     * Reason for Collateral Assignment.<br/>
     * Type: Integer (see {@link CollAsgnReason})<br/>
     * Used: {@link CollateralAssignmentMsg},{@link CollateralRequestMsg},{@link CollateralResponseMsg}
     */
    CollAsgnReason                                      (895),
    /**
     * Collateral inquiry qualifiers.<br/>
     * Type: Integer (see {@link CollInquiryQualifier})<br/>
     * Used: {@link CollInqQualGroup}
     */
    CollInquiryQualifier                                (896),
    /**
     * Number of trades in repeating group.<br/>
     * Type: Integer<br/>
     * Used: {@link TrdCollGroup}
     */
    NoTrades                                            (897),
    /**
     * The fraction of the cash consideration that must be collateralize, expressed as a
     * percent. A MarginRatio of 02% indicates that the value of the collateral (after
     * deducting for "haircut) must exceed the cash consideration by 2%.<br/>
     * Type: Double<br/>
     * Used: {@link net.hades.fix.message.comp.FinancingDetails}
     */
    MarginRatio                                         (898),
    /**
     * Excess margin amount (deficit if value is negative).<br/>
     * Type: Double<br/>
     * Used: {@link CollateralAssignmentMsg},{@link CollateralRequestMsg},{@link CollateralResponseMsg},{@link CollateralInquiryMsg},{@link CollateralReportMsg}
     */
    MarginExcess                                        (899),
    /**
     * TotalNetValue is determined as follows:<br/>
     * At the initial collateral assignment TotalNetValue is the sum of (UnderlyingStartValue * (1-haircut)).
     * In a collateral substitution TotalNetValue is the sum of (UnderlyingCurrentValue * (1-haircut)).
     * For listed derivatives clearing margin management, this is the collateral value which equals (Market value x haircut)<br/>
     * Type: Double<br/>
     * Used: {@link CollateralAssignmentMsg},{@link CollateralRequestMsg},{@link CollateralResponseMsg},{@link CollateralInquiryMsg},{@link CollateralReportMsg}
     */
    TotalNetValue                                       (900),
    /**
     * Starting consideration less repayments.<br/>
     * Type: Double<br/>
     * Used: {@link CollateralAssignmentMsg},{@link CollateralRequestMsg},{@link CollateralResponseMsg},{@link CollateralInquiryMsg},{@link CollateralReportMsg}
     */
    CashOutstanding                                     (901),
    /**
     * Collateral Assignment Identifier.<br/>
     * Type: String<br/>
     * Used: {@link CollateralAssignmentMsg},{@link CollateralResponseMsg}
     */
    CollAsgnID                                          (902),
    /**
     * Collateral Assignment Transaction Type.<br/>
     * Type: Integer (see {@link CollAsgnTransType})<br/>
     * Used: {@link CollateralAssignmentMsg},{@link CollateralResponseMsg}
     */
    CollAsgnTransType                                   (903),
    /**
     * Collateral Response Identifier.<br/>
     * Type: String<br/>
     * Used: {@link CollateralResponseMsg}
     */
    CollRespID                                          (904),
    /**
     * Collateral Assignment Response Type.<br/>
     * Type: Integer (see {@link CollAsgnRespType})<br/>
     * Used: {@link CollateralResponseMsg}
     */
    CollAsgnRespType                                    (905),
    /**
     * Collateral Assignment Reject Reason.<br/>
     * Type: Integer (see {@link CollAsgnRejectReason})<br/>
     * Used: {@link CollateralResponseMsg}
     */
    CollAsgnRejectReason                                (906),
    /**
     * Collateral Assignment Identifier to which a transaction refers.<br/>
     * Type: String<br/>
     * Used: {@link CollateralAssignmentMsg}
     */
    CollAsgnRefID                                       (907),
    /**
     * Collateral Report Identifier.<br/>
     * Type: String<br/>
     * Used: {@link CollateralReportMsg}
     */
    CollRptID                                           (908),
    /**
     * Collateral Inquiry Identifier.<br/>
     * Type: String<br/>
     * Used: {@link CollateralReportMsg}
     */
    CollInquiryID                                       (909),
    /**
     * Collateral Assignment Reject Reason.<br/>
     * Type: Integer (see {@link CollStatus})<br/>
     * Used: {@link CollateralReportMsg}
     */
    CollStatus                                          (910),
    /**
     * Total number of reports returned in response to a request.<br/>
     * Type: Integer<br/>
     * Used: {@link MarketDataSnapshot}
     */
    TotNumReports                                       (911),
    /**
     * Indicates whether this message is that last report message in response to a request, such as Order Mass Status Request.<br/>
     * Type: Boolean<br/>
     * Used: {@link MarketDataSnapshot}
     */
    LastRptRequested                                    (912),
    /**
     * The full name of the base standard agreement, annexes and amendments in place
     * between the principals applicable to a financing transaction.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.FinancingDetails}
     */
    AgreementDesc                                       (913),
    /**
     * A common reference to the applicable standing agreement between the counterparties
     * to a financing transaction.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.FinancingDetails}
     */
    AgreementID                                         (914),
    /**
     * A reference to the date the underlying agreement specified by AgreementID and
     * AgreementDesc was executed.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.FinancingDetails}
     */
    AgreementDate                                       (915),
    /**
     * Start date of a financing deal, i.e. the date the buyer pays the seller cash
     * and takes control of the collateral.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.FinancingDetails}
     */
    StartDate                                           (916),
    /**
     * SEnd date of a financing deal, i.e. the date the seller reimburses the buyer
     * and takes back control of the collateral.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.FinancingDetails}
     */
    EndDate                                             (917),
    /**
     * A reference to the date the underlying agreement specified by AgreementID and
     * AgreementDesc was executed.<br/>
     * Type: Currency (see {@link Currency}))<br/>
     * Used: {@link net.hades.fix.message.comp.FinancingDetails}
     */
    AgreementCurrency                                   (918),
    /**
     * Identifies type of settlement.<br/>
     * Type: Integer (see {@link DeliveryType}))<br/>
     * Used: {@link net.hades.fix.message.comp.FinancingDetails}
     */
    DeliveryType                                        (919),
    /**
     * Accrued Interest Amount applicable to a financing transaction on the End Date.<br/>
     * Type: Decimal<br/>
     * Used: {@link AllocationInstructionMsg},{@link AllocationInstructionAlertMsg},{@link AllocationReportMsg},
     * {@link CollateralAssignmentMsg},{@link CollateralInquiryMsg},{@link CollateralReportMsg},{@link CollateralRequestMsg},
     * {@link CollateralResponseMsg},{@link ConfirmationMsg},{@link ExecutionReportMsg}
     */
    EndAccruedInterestAmt                               (920),
    /**
     * Starting dirty cash consideration of a financing deal, i.e. paid to the seller on the Start Date.<br/>
     * Type: Decimal<br/>
     * Used: {@link AllocationInstructionMsg},{@link AllocationInstructionAlertMsg},{@link AllocationReportMsg},
     * {@link CollateralAssignmentMsg},{@link CollateralInquiryMsg},{@link CollateralReportMsg},{@link CollateralRequestMsg},
     * {@link CollateralResponseMsg},{@link ConfirmationMsg},{@link ExecutionReportMsg}
     */
    StartCash                                           (921),
    /**
     * Ending dirty cash consideration of a financing deal. i.e. reimbursed to the buyer on the End Date.<br/>
     * Type: Decimal<br/>
     * Used: {@link AllocationInstructionMsg},{@link AllocationInstructionAlertMsg},{@link AllocationReportMsg},
     * {@link CollateralAssignmentMsg},{@link CollateralInquiryMsg},{@link CollateralReportMsg},{@link CollateralRequestMsg},
     * {@link CollateralResponseMsg},{@link ConfirmationMsg},{@link ExecutionReportMsg}
     */
    EndCash                                             (922),
    /**
     * New Password or passphrase.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    NewPassword                                         (925),
    /**
     * Number of CollInquiryQualifier entries in a repeating group.<br/>
     * Type: Integer<br/>
     * Used: {@link [CollInqQualGroup}
     */
    NoCollInquiryQualifier                              (938),
    /**
     * Trade Report Status.<br/>
     * Type: Integer (see {@link TrdRptStatus})<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    TrdRptStatus                                        (939),
    /**
     * Identifies the status of the ConfirmationAck.<br/>
     * Type: Integer (see {@link AffirmStatus})<br/>
     * Used: {@link ConfirmationAckMsg}
     */
    AffirmStatus                                        (940),
    /**
     * Currency in which the strike price of an underlying instrument is denominated.<br/>
     * Type: Currency<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingStrikeCurrency                            (941),
    /**
     * Currency in which the strike price of a instrument leg of a multileg instrument is denominated.<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: {@link AdvertismentMsg}, {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegStrikeCurrency                                   (942),
    /**
     * A code that represents a time interval in which a fill or trade occurred. Required for US futures markets.<br/>
     * Type: String<br/>
     * Used: {@link ExecutionReportMsg},{@link TradeCaptureReportRequestMsg},{@link TrdCapRptAckSideGroup},{@link TrdCapRptSideGroup}
     */
    TimeBracket                                         (943),
    /**
     * Action proposed for an Underlying Instrument instance.<br/>
     * Type: Integer (see {@link CollAction})<br/>
     * Used: {@link ExecutionReportMsg},{@link TradeCaptureReportRequestMsg},{@link TrdCapRptAckSideGroup},{@link TrdCapRptSideGroup}
     */
    CollAction                                          (944),
    /**
     * Status of Collateral Inquiry.<br/>
     * Type: Integer (see {@link CollInquiryStatus})<br/>
     * Used: {@link CollateralInquiryAckMsg}
     */
    CollInquiryStatus                                   (945),
    /**
     * Result returned in response to Collateral Inquiry. 4000+ Reserved and available for
     * bi-laterally agreed upon user-defined values<br/>
     * Type: Integer (see {@link CollInquiryResult} for standard values)<br/>
     * Used: {@link CollateralInquiryAckMsg}
     */
    CollInquiryResult                                   (946),
    /**
     * Currency in which the StrikePrice is denominated.<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    StrikeCurrency                                      (947),
    /**
     * Refer to definition of {@link TagNum#NoPartyIDs} (453)<br/>
     * Type: Integer<br/>
     * Used:
     */
    NoNested3PartyIDs                                   (948),
    /**
     * Refer to definition of {@link TagNum#PartyID} (448)<br/>
     * Type: String<br/>
     * Used:
     */
    Nested3PartyID                                      (949),
    /**
     * Refer to definition of {@link TagNum#PartyIDSource} (447)<br/>
     * Type: Character (see {@link PartyIDSource})<br/>
     * Used:
     */
    Nested3PartyIDSource                                (950),
    /**
     * Refer to definition of {@link TagNum#PartyRole} (452)<br/>
     * Type: Character (see {@link PartyRole})<br/>
     * Used:
     */
    Nested3PartyRole                                    (951),
    /**
     * Refer to definition of {@link TagNum#NoPartySubIDs} (802)<br/>
     * Type: Integer<br/>
     * Used:
     */
    NoNested3PartySubIDs                                (952),
    /**
     * Refer to definition of {@link TagNum#PartySubID} (523)<br/>
     * Type: String<br/>
     * Used:
     */
    Nested3PartySubID                                   (953),
    /**
     * Refer to definition of {@link TagNum#PartySubIDType} (803)<br/>
     * Type: Integer (see {@link PartySubIDType})<br/>
     * Used:
     */
    Nested3PartySubIDType                               (954),
    /**
     * Specifies when the contract (i.e. MBS/TBA) will settle.<br/>
     * Type: MonthYear<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegContractSettlMonth                               (955),
    /**
     * The start date used for calculating accrued interest on debt instruments which are being sold between
     * interest payment dates. Often but not always the same as the Issue Date and the Dated Date.<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegInterestAccrualDate                              (956),
    /**
     * Indicates number of strategy parameters.<br/>
     * Type: Integer<br/>
     * Used: {@link StrategyParametersGroup}
     */
    NoStrategyParameters                                (957),
    /**
     * Name of the strategy parameter.<br/>
     * Type: String<br/>
     * Used: {@link StrategyParametersGroup}
     */
    StrategyParameterName                               (958),
    /**
     * Type of the strategy parameter.<br/>
     * Type: Integer (see {@link Currency})<br/>
     * Used: {@link StrategyParametersGroup}
     */
    StrategyParameterType                               (959),
    /**
     * Value of the strategy parameter.<br/>
     * Type: String<br/>
     * Used: {@link StrategyParametersGroup}
     */
    StrategyParameterValue                              (960),
    /**
     * Host assigned entity ID that can be used to reference all components of a cross; sides + strategy + legs.
     * Used as the primary key with which to refer to the Cross Order for cancellation and replace.
     * The HostCrossID will also be used to link together components of the Cross Order.
     * For example, each individual Execution Report associated with the order will carry HostCrossID
     * in order to tie back to the original cross order.<br/>
     * Type: String<br/>
     * Used: {@link ExecutionReportMsg},{@link CrossOrderCancelReplaceRequestMsg},{@link CrossOrderCancelRequestMsg}
     */
    HostCrossID                                         (961),
    /**
     * Indicates how long the order as specified in the side stays in effect. SideTimeInForce allows a two-sided
     * cross order to specify order behavior separately for each side. Absence of this field indicates that
     * TimeInForce should be referenced. SideTimeInForce will override TimeInForce if both are provided.<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link ExecutionReportMsg}
     */
    SideTimeInForce                                     (962),
    /**
     * Unique identifier for the Market Data Report.<br/>
     * Type: Integer<br/>
     * Used: {@link MarketDataSnapshot}
     */
    MDReportID                                          (963),
    /**
     * Identifies a Security List message.<br/>
     * Type: Integer<br/>
     * Used: {@link DerivativeSecurityListMsg}, {@link SecurityDefinitionMsg}, {@link SecurityDefinitionUpdateReportMsg},
     * {@link SecurityListMsg}, {@link SecurityListUpdateReportMsg}
     */
    SecurityReportID                                    (964),
    /**
     * Used for derivatives. Denotes the current state of the Instrument.
     * Valid values: 1 - Active 2 - Inactive<br/>
     * Type: String (see {@link SecurityStatus})<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    SecurityStatus                                      (965),
    /**
     * Indicator to determine if instrument is settle on open.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    SettleOnOpenFlag                                    (966),
    /**
     * Used for derivatives. Multiplier applied to the strike price for
     * the purpose of calculating the settlement value.<br/>
     * Type: Float<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    StrikeMultiplier                                    (967),
    /**
     * Used for derivatives. The number of shares/units for the financial instrument involved in the
     * option trade.<br/>
     * Type: Float<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    StrikeValue                                         (968),
    /**
     * Minimum price increase for a given exchange-traded Instrument.<br/>
     * Type: Float<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    MinPriceIncrement                                   (969),
    /**
     * Position Limit for a given exchange-traded product.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    PositionLimit                                       (970),
    /**
     * Position Limit in the near-term contract for a given exchange-traded product.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    NTPositionLimit                                     (971),
    /**
     * Percent of the Strike Price that this underlying represents.<br/>
     * Type: Percentage<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingAllocationPercent                         (972),
    /**
     * Cash amount associated with the underlying component.<br/>
     * Type: Amount<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingCashAmount                                (973),
    /**
     * Specific to the UnderlyingInstrument. Used for derivatives that deliver into cash underlying.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingCashType                                  (974),
    /**
     * Indicates order settlement period for the underlying instrument.<br/>
     * Type: Integer (see {@link UnderlyingSettlementType})<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingSettlementType                            (975),
    /**
     * Date associated to the quantity that is being reported for the position.<br/>
     * Type: Local market data<br/>
     * Used: {@link PositionQtyGroup}
     */
    QuantityDate                                        (976),
    /**
     * Total number of occurrences of Amount to pay in order to receive the underlying instrument.<br/>
     * Type: Integer<br/>
     * Used: {@link UnderlyingAmountGroup}
     */
    NoUnderlyingAmounts                                 (984),
    /**
     * Amount to pay in order to receive the underlying instrument.<br/>
     * Type: Double<br/>
     * Used: {@link UnderlyingAmountGroup}
     */
    UnderlyingPayAmount                                 (985),
    /**
     * Amount to collect in order to deliver the underlying instrument.<br/>
     * Type: Double<br/>
     * Used: {@link UnderlyingAmountGroup}
     */
    UnderlyingCollectAmount                             (986),
    /**
     * Date the underlying instrument will settle. Used for derivatives that deliver into more than one underlying instrument. 
     * Settlement dates can vary across underlying instruments..<br/>
     * Type: LocalMktDate<br/>
     * Used: {@link UnderlyingAmountGroup}
     */
    UnderlyingSettlementDate                            (987),
    /**
     * Settlement status of the underlying instrument. Used for derivatives that deliver into more than
     * one underlying instrument. Settlement can be delayed for an underlying instrument.<br/>
     * Type: String<br/>
     * Used:
     */
    UnderlyingSettlementStatus                          (988),
    /**
     * Will allow the intermediary to specify an allocation ID generated by their system.<br/>
     * Type: String<br/>
     * Used: {@link AllocAckGroup},{@link AllocGroup},{@link TrdAllocGroup}
     */
    SecondaryIndividualAllocID                          (989),
    /**
     * Additional attribute to store the Trade ID of the Leg.<br/>
     * Type: String<br/>
     * Used: {@link TrdInstrmtLegGroup}
     */
    LegReportID                                         (990),
    /**
     * Specifies average price rounded to quoted precision.<br/>
     * Type: Double<br/>
     * Used: {@link TrdInstrmtLegGroup}
     */
    RndPx                                               (991),
    /**
     * Identifies whether the allocation is to be sub-allocated or allocated to a third party.<br/>
     * Type: Integer (see {@link IndividualAllocType})<br/>
     * Used: {@link AllocAckGroup},{@link AllocGroup}
     */
    IndividualAllocType                                 (992),
    /**
     * Capacity of customer in the allocation block.<br/>
     * Type: String<br/>
     * Used: {@link AllocAckGroup},{@link AllocGroup},{@link TrdAllocGroup}
     */
    AllocCustomerCapacity                               (993),
    /**
     * The Tier the trade was matched by the clearing system.<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    TierCode                                            (994),
    /**
     * The unit of measure of the underlying commodity upon which the contract is based. 
     * Two groups of units of measure enumerations are supported<br/>
     * Fixed Magnitude UOMs are primarily used in energy derivatives and specify a magnitude 
     * (such as, MM, Kilo, M, etc.) and the dimension (such as, watt hours, BTU's) to 
     * produce standard fixed measures (such as MWh - Megawatt-hours, MMBtu - One million BTUs).<br/>
     * The second group, Variable Quantity UOMs, specifies the dimension as a single unit without 
     * a magnitude (or more accurately a magnitude of one) and uses the UnitOfMeasureQty(1147) 
     * field to define the quantity of units per contract. Variable Quantity UOMs are used for 
     * both commodities (such as lbs of lean cattle, bushels of corn, ounces of gold) and financial futures.<br/>
     * Examples:<br/>
     * For lean cattle futures contracts, a UnitOfMeasure of 'lbs' with a UnitOfMeasureQty(1147) of 
     * 40,000, means each lean cattle futures contract represents 40,000 lbs of lean cattle.<br/>
     * For Eurodollars futures contracts, a UnitOfMeasure of USD with a UnitOfMeasureQty(1147) 
     * of 1,000,000, means a Eurodollar futures contract represents 1,000,000 USD.<br/>
     * For gold futures contracts, a UnitOfMeasure is oz_tr (Troy ounce) with a UnitOfMeasureQty(1147) 
     * of 1,000, means each gold futures contract represents 1,000 troy ounces of gold.<br/>
     * Valid values: <br/>
     * <b>Fixed Magnitude</b><br/>
     * UOM Bcf - Billion cubic feet <br/>
     * MMbbl - Million Barrels ( Deprecated in FIX.5.0SP1 ) <br/>
     * MMBtu - One Million BTU <br/>
     * MWh - Megawatt hours <br/>
     *  <b>Variable Quantity UOM </b><br/>
     * Bbl - Barrels <br/>
     * Bu - Bushels <br/>
     * lbs - pounds <br/>
     * Gal - Gallons <br/>
     * oz_tr - Troy Ounces <br/>
     * t - Metric Tons (aka Tonne) <br/>
     * tn - Tons (US)<br/>
     * Type: String (see {@link UnitOfMeasure})<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    UnitOfMeasure                                       (996),
    /**
     * Unit of time associated with the contract.)<br/>
     * NOTE: Additional values may be used by mutual agreement of the counterparties)<br/>
     * Valid values:
     * <ul>
     *      <li>H - Hour
     *      <li>Min - Minute
     *      <li>S - Second
     *      <li>D - Day
     *      <li>Wk - Week
     *      <li>Mo - Month
     *      <li>Yr - Year
     * </ul>
     * Type: String (see {@link TimeUnit})<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    TimeUnit                                            (997),
    /**
     * Refer to definition of  {@link TagNum#UnitOfMeasure}(996).
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingUnitOfMeasure                             (998),
     /**
     * Refer to defintion of {@link TagNum#UnitOfMeasure} (996)<br/>
     * Type: String (see {@link UnitOfMeasure})<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegUnitOfMeasure                                    (999),
    /**
     * Refer to definition of {@link TagNum#UnitOfMeasure} (996).<br/>
     * Type: String (see {@link UnitOfMeasure})<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingTimeUnit                                  (1000),
    /**
     * Same as TimeUnit..<br/>
     * Type: String (see {@link TimeUnit})<br/>
     * Used: {@link UnderlyingInstrument}
     */
    LegTimeUnit                                         (1001),
    /**
     * Specifies the method under which a trade quantity was allocated.<br/>
     * Type: Integer (see {@link AllocMethod})<br/>
     * Used: {@link AllocGroup}, {@link TrdAllocGroup}
     */
    AllocMethod                                         (1002),
    /**
     * The unique ID assigned to the trade entity once it is received or matched by the exchange or
     * central counterparty.<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportMsg}, {@link TradeCaptureReportAckMsg}, {@link TradeCaptureReportRequestMsg},
     * {@link TradeCaptureReportRequestAck},{@link ExecAllocGrp}, {@link MDIncGrp}
     */
    TradeID                                             (1003),
    /**
     * Used on a multi-sided trade to designate the ReportID.<br/>
     * Type: String<br/>
     * Used:
     */
    SideTradeReportID                                   (1005),
    /**
     * Used on a multi-sided trade to convey order routing information.<br/>
     * Type: String<br/>
     * Used:
     */
    SideFillStationCd                                   (1006),
    /**
     * Used on a multi-sided trade to convey reason for execution.<br/>
     * Type: String<br/>
     * Used:
     */
    SideReasonCd                                        (1007),
    /**
     * Used on a multi-sided trade to specify the type of trade for a given side. Same values as {@ling TagNum#TrdSubType} (828).<br/>
     * Type: Integer (see {@link TrdSubType})<br/>
     * Used:
     */
    SideTrdSubTyp                                       (1008),
    /**
     * Used to indicate the quantity on one of a multi-sided Trade Capture Report.<br/>
     * Type: Integer<br/>
     * Used:
     */
    SideLastQty                                         (1009),
    /**
     * Used to identify the event or source which gave rise to a message.<br/>
     * Type: String<br/>
     * Used: {@link AllocationInstructionMsg}, {@link AllocationInstructionAlertMsg}, {@link AllocationReportMsg},
     * {@link PositionReportMsg},{@link TradeCaptureReportMsg}, {@link TradeCaptureReportAckMsg}, {@link TradeCaptureReportRequestMsg},
     * {@link TradeCaptureReportRequestAckMsg}.
     */
    MessageEventSource                                  (1011),
    /**
     * Will be used in a multi-sided message. Traded Regulatory timestamp value Use to store time information
     * required by government regulators or self regulatory organizations such as an exchange or clearing house.<br/>
     * Type: UTC Timestamp<br/>
     * Used:
     */
    SideTrdRegTimestamp                                 (1012),
    /**
     * Same as TrdRegTimeStampType.<br/>
     * Type: Integer (see {@link TrdRegTimestampType})<br/>
     * Used:
     */
    SideTrdRegTimestampType                             (1013),
    /**
     * Same as TrdRegTimestampOrigin. Text which identifies the origin i.e. system which was used to generate
     * the time stamp for the Traded Regulatory timestamp value<br/>
     * Type: String<br/>
     * Used:
     */
    SideTrdRegTimestampSrc                              (1014),
    /**
     * Used to indicate that a floor-trade was originally submitted "as of" a specific trade date which 
     * is earlier than its clearing date.<br/>
     * Type: Character (see {@link AsOfIndicator})<br/>
     * Used:
     */
    AsOfIndicator                                       (1015),
    /**
     * Indicates number of SideTimestamps contained in group<br/>
     * Type: Integer <br/>
     * Used:
     */
    NoSideTrdRegTS                                      (1016),
    /**
     * Expresses the risk of an option leg. Value must be between -1 and 1.<br/>
     * A Call Option will require a ratio value between 0 and 1.<br/>
     * A Put Option will require a ratio value between -1 and 0.<br/>
     * Type: Float<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegOptionRatio                                      (1017),
    /**
     * Identifies the number of parties identified with an instrument.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentParties}
     */
    NoInstrumentParties                                 (1018),
    /**
     * Identifies the number of parties identified with an instrument.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentParties}
     */
    InstrumentPartyID                                   (1019),
    /**
     * Used to report volume with a trade.<br/>
     * Type: Qty<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentParties}
     */
    TradeVolume                                         (1020),
    /**
     * Describes the type of book for which the feed is intended. Used when multiple feeds are
     * provided over the same connection.<br/>
     * Type: Integer (see {@link MDBookType})<br/>
     * Used: {@link MarketDataSnapshotMsg}
     */
    MDBookType                                          (1021),
    /**
     * Describes a class of service for a given data feed, ie Regular and Market Maker,
     * Bandwidth Intensive or Bandwidth Conservative.<br/>
     * Type: String<br/>
     * Used: {@link MarketDataSnapshotMsg}
     */
    MDFeedType                                          (1022),
    /**
     * Integer to convey the level of a bid or offer at a given price level.
     * This is in contrast to MDEntryPositionNo which is used to convey the position
     * of an order within a Price level<br/>
     * Type: Integer<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    MDPriceLevel                                        (1023),
    /**
     * Used to describe the origin of an entry in the book.<br/>
     * Type: Integer (see {@link MDOriginType})<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    MDOriginType                                        (1024),
    /**
     * Indicates the first trade price of the day/session.<br/>
     * Type: Double<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    FirstPx                                             (1025),
    /**
     * The spot rate for an FX entry.<br/>
     * Type: Double<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    MDEntrySpotRate                                     (1026),
    /**
     * Used for an F/X entry. The forward points to be added to or subtracted from the spot rate to
     * get the "all-in" rate in MDEntryPx. Expressed in decimal form. For example, 61.99 points is
     * expressed and sent as 0.006199<br/>
     * Type: Double<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    MDEntryForwardPoints                                (1027),
    /**
     * Indicates if the order was initially received manually (as opposed to electronically).<br/>
     * Type: Boolean<br/>
     * Used: {@link ExecutionReportMsg},{@link NewOrderSingleMsg},{@link OrderCancelReplaceRequest}
     */
    ManualOrderIndicator                                (1028),
    /**
     * Indicates if the customer directed this order to a specific execution venue "Y" or not "N".<br/>
     * A default of "N" customer did not direct this order should be used in the case where the
     * information is both missing and essential.<br/>
     * Type: Boolean<br/>
     * Used: {@link ExecutionReportMsg},{@link NewOrderSingleMsg},{@link OrderCancelReplaceRequest}
     */
    CustDirectedOrder                                   (1029),
    /**
     * Identifies the Broker / Dealer Department that first took the order.<br/>
     * Type: String<br/>
     * Used: {@link ExecutionReportMsg},{@link NewOrderSingleMsg},{@link OrderCancelReplaceRequest}
     */
    ReceivedDeptID                                      (1030),
    /**
     * Codes that apply special information that the Broker / Dealer needs to report, as specified by the customer.<br/>
     * NOTE: This field and its values have no bearing on the ExecInst and TimeInForce fields.
     * These values should not be used instead of ExecInst or TimeInForce.
     * This field and its values are intended for compliance reporting only.<br/>
     * Type: String (see {@link CustOrderHandlingInst})<br/>
     * Used: {@link ExecutionReportMsg},{@link NewOrderSingleMsg},{@link OrderCancelReplaceRequest}
     */
    CustOrderHandlingInst                               (1031),
    /**
     * Identifies the class or source of the "OrderHandlingInst" values. Scope of this will apply to
     * both CustOrderHandlingInst and DeskOrderHandlingInst fields.<br/>
     * Required if CustOrderHandlingInst and/or DeskOrderHandlingInst is specified.<br/>
     * Type: Integer<br/>
     * Used: {@link ExecutionReportMsg},{@link NewOrderSingleMsg},{@link OrderCancelReplaceRequest}
     */
    OrderHandlingInstSource                             (1032),
    /**
     * Type of trading desk.<br/>
     * Type: String (see {@link DeskType})<br/>
     * Used: {@link TrdRegTimestamps}
     */
    DeskType                                            (1033),
    /**
     * Identifies the class or source of DeskType(1033) values. Required if {@link TagNum#DeskType(1033)} is specified.<br/>
     * Type: Integer<br/>
     * Used: {@link TrdRegTimestamps}
     */
    DeskTypeSource                                      (1034),
    /**
     * Codes that apply special information that the Broker / Dealer needs to report.<br/>
     * Type: String<br/>
     * Used: {@link TrdRegTimestamps}
     */
    DeskOrderHandlingInst                               (1035),
    /**
     * The status of this execution acknowledgment message.<br/>
     * Type: Character (see {@link ExecAckStatus})<br/>
     * Used: {@link ExecutionAcknowledgementMsg}
     */
    ExecAckStatus                                       (1036),
    /**
     * Indicates the underlying position amount to be delivered.<br/>
     * Type: Double<br/>
     * Used: {@link PosUndInstrmtGroup}
     */
    UnderlyingDeliveryAmount                            (1037),
    /**
     * Maximum notional value for a capped financial instrument.<br/>
     * Type: Double<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingCapValue                                  (1038),
    /**
     * Settlement method for a contract.<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingSettlMethod                               (1039),
    /**
     * Used to carry an internal trade entity ID which may or may not be reported to the firm.<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg},{@link TradeCaptureReportRequestMsg},
     * {@link TradeCaptureReportRequestAckMsg}
     */
    SecondaryTradeID                                    (1040),
    /**
     * The ID assigned to a trade by the Firm to track a trade within the Firm system.
     * This ID can be assigned either before or after submission to the exchange or central counterpary.<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg},{@link TradeCaptureReportRequestMsg},
     * {@link TradeCaptureReportRequestAckMsg}
     */
    FirmTradeID                                         (1041),
    /**
     * Used to carry an internal firm assigned ID which may or may not be reported to the exchange or central counterpary.<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg},{@link TradeCaptureReportRequestMsg},
     * {@link TradeCaptureReportRequestAckMsg}
     */
    SecondaryFirmTradeID                                (1042),
    /**
     * Conveys how the collateral should be/has been applied.<br/>
     * Type: Integer(see {@link CollApplType}) <br/>
     * Used: {@link CollateralReportMsg},{@link CollateralResponseMsg}
     */
    CollApplType                                        (1043),
    /**
     * Unit amount of the underlying security (shares) adjusted for pending corporate action not yet allocated.<br/>
     * Type: Qty<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingAdjustedQuantity                          (1044),
    /**
     * Foreign exchange rate used to compute {@link TagNum#UnderlyingCurrentValue}(885) (or market value) from
     * {@link TagNum#UnderlyingCurrency}(318) to {@link TagNum#Currency} (15).<br/>
     * Type: Float<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingFXRate                                    (1045),
    /**
     * Specifies whether the {@link TagNum#UnderlyingFxRate} (1045) should be multiplied or divided.<br/>
     * Type: Char (see {@link FXRateCalc})<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingFXRateCalc                                (1046),
    /**
     * Indicates whether the resulting position after a trade should be an opening position or closing position.
     * Used for omnibus accounting - where accounts are held on a gross basis instead of being netted together.<br/>
     * Type: Character (see {@link AllocPositionEffect})<br/>
     * Used: {@link AllocAckGrp\oup},{@link AllocGroup}
     */
    AllocPositionEffect                                 (1047),
    /**
     * Identifies role of dealer; Agent, Principal, RisklessPrincipal.<br/>
     * Type: Char (see {@link DealingCapacity})<br/>
     * Used: {@link UnderlyingInstrument}
     */
    DealingCapacity                                     (1048),
    /**
     * Method under which assignment was conducted.<br/>
     * Valid values: R = Random, P = ProRata<br/>
     * Type: Character (see {@link InstrmtAssignmentMethod})<br/>
     * Used: {@link MDFullGrp},{@link MDIncGrp}
     */
    InstrmtAssignmentMethod                             (1049),
    /**
     * PartyIDSource value within an instrument partyrepeating group. Same values as
     * {@link TagNum#PartyIDSource} (447).<br/>
     * Type: Char (see {@link PartyIDSource})<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentParties}
     */
    InstrumentPartyIDSource                             (1050),
    /**
     * PartyRole value within an instrument partyepeating group. Same values as
     * {@link TagNum#PartyRole} (452)<br/>
     * Type: Integer (see {@link PartyIDSource})<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentParties}
     */
    InstrumentPartyRole                                 (1051),
    /**
     * Number of {@link TagNum#InstrumentPartySubID} (1053) and {@link TagNum#InstrumentPartySubIDType}
     * (1054) entries<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentParties}
     */
    NoInstrumentPartySubIDs                             (1052),
    /**
     * PartySubID value within an instrument party repeating group. Same values as
     * {@link TagNum#PartySubID} (523)<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentParties}
     */
    InstrumentPartySubID                                (1053),
    /**
     * Type of InstrumentPartySubID (1053) value. Same values as {@link TagNum#PartySubIDType} (803)<br/>
     * Type: Integer (see {@link PartyIDSource})<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentParties}
     */
    InstrumentPartySubIDType                            (1054),
    /**
     * The Currency in which the position Amount is denominated.<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: {@link PositionAmountData}
     */
    PositionCurrency                                    (1055),
    /**
     * Used for the calculated quantity of the other side of the currency trade. Can be derived from LastQty and LastPx.<br/>
     * Type: Double<br/>
     * Used: {@link ExecutionReportMsg}, {@link TradeCaptureReportMsg}, {@link TradeCaptureReportAckMsg}
     */
    CalculatedCcyLastQty                                (1056),
    /**
     * Used to identify whether the order initiator is an aggressor or not in the trade.<br/>
     * Type: Boolean<br/>
     * Used: {@link ExecutionReportMsg}
     */
    AggressorIndicator                                  (1057),
    /**
     * Identifies the number of parties identified with an underlying instrument.<br/>
     * Type: Integer<br/>
     * Used: {@link UndlyInstrumentPaties}
     */
    NoUndlyInstrumentParties                            (1058),
    /**
     * PartyID value within an underlying instrument party repeating group.
     * Same values as {@link TagNum#PartyID} (448).<br/>
     * Type: String<br/>
     * Used: {@link UndlyInstrumentPaties}
     */
    UndlyInstrumentPartyID                              (1059),
    /**
     * PartyIDSource value within an underlying instrument partyrepeating group. 
     * Same values as {@link TagNum#PartyIDSource} (447).<br/>
     * Type: Character (see {@link PartyIDSource})<br/>
     * Used: {@link UndlyInstrumentPaties}
     */
    UndlyInstrumentPartyIDSource                        (1060),
    /**
     * PartyRole value within an underlying instrument partyepeating group.
     * Same values as {@link TagNum#PartyRole} (447).<br/>
     * Type: Integer (see {@link PartyRole})<br/>
     * Used: {@link UndlyInstrumentPaties}
     */
    UndlyInstrumentPartyRole                            (1061),
    /**
     * Number of Underlying {@link TagNum#InstrumentPartySubID} (1053) and
     * {@link TagNum#InstrumentPartySubIDType} (1054) entries.<br/>
     * Type: Integer<br/>
     * Used: {@link UndlyInstrumentPaties}
     */
    NoUndlyInstrumentPartySubIDs                        (1062),
    /**
     * PartySubID value within an underlying instrument party repeating group.
     * Same values as {@link TagNum#PartySubID} (523).<br/>
     * Type: String<br/>
     * Used: {@link UndlyInstrumentPaties}
     */
    UndlyInstrumentPartySubID                           (1063),
    /**
     * Type of underlying InstrumentPartySubID (1053) value. Same values as {@link TagNum#PartySubIDType} (803).<br/>
     * Type: Integer (see {@link PartySubIDType})<br/>
     * Used: {@link UndlyInstrumentPaties}
     */
    UndlyInstrumentPartySubIDType                       (1064),
    /**
     * The bid FX Swap points for an FX Swap. It is the "far bid forward points - near offer forward point".
     * Value can be negative. Expressed in decimal form. For example, 61.99 points is expressed and
     * sent as 0.006199.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    BidSwapPoints                                       (1065),
    /**
     * The offer FX Swap points for an FX Swap. It is the "far offer forward points - near bid forward points".
     * Value can be negative. Expressed in decimal form. For example, 61.99 points is expressed and
     * sent as 0.006199.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    OfferSwapPoints                                     (1066),
    /**
     * The bid FX forward points for the leg of an FX Swap. Value can be negative. Expressed in decimal form.
     * For example, 61.99 points is expressed and sent as 0.006199.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    LegBidForwardPoints                                 (1067),
    /**
     * The offer FX forward points for the leg of an FX Swap. Value can be negative. Expressed in decimal form.
     * For example, 61.99 points is expressed and sent as 0.006199.<br/>
     * Type: Decimal<br/>
     * Used: {@link QuoteMsg}
     */
    LegOfferForwardPoints                               (1068),
    /**
     * For FX Swap, this is used to express the differential between the far leg's bid/offer and the near
     * leg's bid/offer. Value can be negative. Expressed in decimal form.
     * For example, 61.99 points is expressed and sent as 0.006199.<br/>
     * Type: Decimal<br/>
     * Used: {@link MultilegOrderCancelReplaceMsg},{@link NewOrderMultilegMsg}
     */
    SwapPoints                                          (1069),
    /**
     * Identifies market data quote type.<br/>
     * Type: Integer (see {@link MDQuoteType})<br/>
     * Used: {@link MarketDataRequestMsg}
     */
    MDQuoteType                                         (1070),
    /**
     * For FX Swap, this is used to express the last market event for the differential between the
     * far leg's bid/offer and the near leg's bid/offer in a fill or partial fill.
     * Value can be negative. Expressed in decimal form. For example, 61.99 points is expressed and sent as 0.006199.<br/>
     * Type: Decimal<br/>
     * Used: {@link ExecutionReportMsg},{@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    LastSwapPoints                                      (1071),
    /**
     * The gross trade amount for this side of the trade. See also GrossTradeAmt (381) for additional definition.<br/>
     * Type: Decimal<br/>
     * Used: {@link TrdCapRptAckSideGroup},{@link TrdCapRptSideGroup}
     */
    SideGrossTradeAmt                                   (1072),
    /**
     * The forward points for this leg's fill event. Value can be negative. Expressed in decimal form.
     * For example, 61.99 points is expressed and sent as 0.006199<br/>
     * Type: Decimal<br/>
     * Used: {@link InstrmtLegExecGroup},{@link TrdInstrmtLegGroup}
     */
    LegLastForwardPoints                                (1073),
    /**
     * Used for the calculated quantity of the other side of the currency for this leg.
     * Can be derived from LegQty and LegLastPx.<br/>
     * Type: Decimal<br/>
     * Used: {@link InstrmtLegExecGroup},{@link TrdInstrmtLegGroup}
     */
    LegCalculatedCcyLastQty                             (1074),
    /**
     * The gross trade amount of the leg. For FX Futures this is used to express the notional
     * value of a fill when LegLastQty and other quantity fields are express in terms of contract size.<br/>
     * Type: Decimal<br/>
     * Used: {@link InstrmtLegExecGroup},{@link TrdInstrmtLegGroup}
     */
    LegGrossTradeAmt                                    (1075),
    /**
     * Time of security's maturity expressed in local time with offset to UTC specified.<br/>
     * Type: TZTimeOnly<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    MaturityTime                                        (1079),
    /**
     * The ID reference to the order being hit or taken.<br/>
     * Type: String<br/>
     * Used: {@link NewOrderMultilegMsg}, {@link NewOrderSingleMsg}
     */
    RefOrderID                                          (1080),
    /**
     * Used to specify what identifier, provided in order depth market data, to use when hitting (taking) a specific order.<br/>
     * Type: Character (see {@link RefOrderIDSource})<br/>
     * Used: {@link NewOrderMultilegMsg}, {@link NewOrderSingleMsg}
     */
    RefOrderIDSource                                    (1081),
    /**
     * Used for reserve orders when DisplayQty applies to the primary execution market (e.g.an ECN) and
     * another quantity is to be shown at other markets (e.g. the exchange).
     * On orders specifies the qty to be displayed, on execution reports the currently displayed quantity.<br/>
     * Type: Quantity<br/>
     * Used: {@link DisplayInstruction}
     */
    SecondaryDisplayQty                                 (1082),
    /**
     * Instructs when to refresh {@link TagNum#DisplayQty} (1138).<br/>
     * Type: Character (see {@link DisplayWhen})<br/>
     * Used: {@link DisplayInstruction}
     */
    DisplayWhen                                         (1083),
    /**
     * Defines what value to use in {@link TagNum#DisplayQty} (1138). If not specified the default DisplayMethod is "1".<br/>
     * Type: Character (see {@link DisplayMethod})<br/>
     * Used: {@link DisplayInstruction}
     */
    DisplayMethod                                       (1084),
    /**
     * Defines the lower quantity limit to a randomized refresh of DisplayQty.<br/>
     * Type: Double<br/>
     * Used: {@link DisplayInstruction}
     */
    DisplayLowQty                                       (1085),
    /**
     * Defines the upper quantity limit to a randomized refresh of DisplayQty.<br/>
     * Type: Double<br/>
     * Used: {@link DisplayInstruction}
     */
    DisplayHighQty                                      (1086),
    /**
     * Defines the minimum increment to be used when calculating a random refresh of DisplayQty.
     * A user specifies this when he wants a larger increment than the standard provided by
     * the market (e.g. the round lot size).<br/>
     * Type: Double<br/>
     * Used: {@link DisplayInstruction}
     */
    DisplayMinIncr                                      (1087),
    /**
     * Defines the quantity used to refresh DisplayQty.<br/>
     * Type: Double<br/>
     * Used: {@link DisplayInstruction}
     */
    RefreshQty                                          (1088),
    /**
     * Allows orders to specify a minimum quantity that applies to every execution (one execution
     * could be for multiple counter-orders). <br/>
     * The order may still fill against smaller orders, but the cumulative quantity of the execution must
     * be in multiples of the MatchIncrement.<br/>
     * Type: Integer<br/>
     * Used: {@link ExecutionReportMsg}
     */
    MatchIncrement                                      (1089),
    /**
     * Allows an order to specify a maximum number of price levels to trade through. Only valid for
     * aggressive orders and during continuous (autoexecution) trading sessions.
     * Property lost when order is put on book. A partially filled order is assigned last trade price
     * as limit price. Non-filled order behaves as ordinary Market or Limit.<br/>
     * Type: Qty<br/>
     * Used: {@link ExecutionReportMsg}
     */
    MaxPriceLevels                                      (1090),
    /**
     * Allows trader to explicitly request anonymity or disclosure in pre-trade market data feeds.
     * Anonymity is relevant in markets where counterparties are regularly disclosed in order
     * depth feeds. Disclosure is relevant when counterparties are not normally visible.<br/>
     * Type: Boolean<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    PreTradeAnonymity                                   (1091),
    /**
     * Allows trader to explicitly request anonymity or disclosure in pre-trade market data feeds.
     * Anonymity is relevant in markets where counterparties are regularly disclosed in order
     * depth feeds. Disclosure is relevant when counterparties are not normally visible.<br/>
     * Type: Character (see {@link PriceProtectionScope})<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    PriceProtectionScope                                (1092),
    /**
     * Defines the lot type assigned to the order.<br/>
     * Type: Character (see {@link LotType})<br/>
     * Used: {@link ExecutionReportMsg},{@link MDFullGrp},{@link MDIncGrp},{@link TradeReportOrderDetail}
     */
    LotType                                             (1093),
    /**
     * Defines the type of peg.<br/>
     * Type: Integer (see {@link PegPriceType})<br/>
     * Used: {@link PegInstructions}
     */
    PegPriceType                                        (1094),
    /**
     * The value of the reference price that the order is pegged to.
     * PeggedRefPrice + PegOffsetValue (211) = PeggedPrice (839) unless the limit price (44, Price)
     * is breached. The values may not be exact due to rounding.<br/>
     * Type: Double<br/>
     * Used: {@link ExecutionReportMsg}
     */
    PeggedRefPrice                                      (1095),
    /**
     * Defines the identity of the security off whose prices the order will peg. Same values as SecurityIDSource (22).<br/>
     * Type: String (see {@link SecurityIDSource})<br/>
     * Used: {@link PegInstructions}
     */
    PegSecurityIDSource                                 (1096),
    /**
     * Defines the identity of the security off whose prices the order will peg.<br/>
     * Type: String<br/>
     * Used: {@link PegInstructions}
     */
    PegSecurityID                                       (1097),
    /**
     * Defines the common, human understood representation of the security off whose prices the order will Peg.<br/>
     * Type: String<br/>
     * Used: {@link PegInstructions}
     */
    PegSymbol                                           (1098),
    /**
     * Security description of the security off whose prices the order will Peg.<br/>
     * Type: String<br/>
     * Used: {@link PegInstructions}
     */
    PegSecurityDesc                                     (1099),
    /**
     * Defines when the trigger will hit, i.e. the action specified by the trigger instructions will come into effect.<br/>
     * Type: Character (see {@link TriggerType})<br/>
     * Used: {@link TriggeringInstruction}
     */
    TriggerType                                         (1100),
    /**
     * Defines the type of action to take when the trigger hits.<br/>
     * Type: Character (see {@link TriggerAction})<br/>
     * Used: {@link TriggeringInstruction}
     */
    TriggerAction                                       (1101),
    /**
     * The price at which the trigger should hit.<br/>
     * Type: Double<br/>
     * Used: {@link TriggeringInstruction}
     */
    TriggerPrice                                        (1102),
    /**
     * Defines the common, human understood representation of the security whose prices will be tracked by the trigger logic.<br/>
     * Type: String<br/>
     * Used: {@link TriggeringInstruction}
     */
    TriggerSymbol                                       (1103),
    /**
     * Defines the identity of the security whose prices will be tracked by the trigger logic.<br/>
     * Type: String<br/>
     * Used: {@link TriggeringInstruction}
     */
    TriggerSecurityID                                   (1104),
    /**
     * Defines the identity of the security whose prices will be tracked by the trigger logic.
     * Same values as {@link TagNum#SecurityIDSource} (22).<br/>
     * Type: String<br/>
     * Used: {@link TriggeringInstruction}
     */
    TriggerSecurityIDSource                             (1105),
    /**
     * Defines the security description of the security whose prices will be tracked by the trigger logic.<br/>
     * Type: String<br/>
     * Used: {@link TriggeringInstruction}
     */
    TriggerSecurityDesc                                 (1106),
    /**
     * The type of price that the trigger is compared to.<br/>
     * Type: Character (see {@link TriggerPriceType})<br/>
     * Used: {@link TriggeringInstruction}
     */
    TriggerPriceType                                    (1107),
    /**
     * Defines the type of price protection the customer requires on their order.<br/>
     * Type: Character (see {@link TriggerPriceTypeScope})<br/>
     * Used: {@link TriggeringInstruction}
     */
    TriggerPriceTypeScope                               (1108),
    /**
     * The side from which the trigger price is reached.<br/>
     * Type: Character (see {@link TriggerPriceDirection})<br/>
     * Used: {@link TriggeringInstruction}
     */
    TriggerPriceDirection                               (1109),
    /**
     * The Price that the order should have after the trigger has hit. Could be applicable
     * for any trigger type, but must be specified for Trigger Type 1.<br/>
     * Type: Double<br/>
     * Used: {@link TriggeringInstruction}
     */
    TriggerNewPrice                                     (1110),
    /**
     * The OrdType the order should have after the trigger has hit. Required to
     * express orders that change from Limit to Market.
     * Other values from {@link TagNum#OrdType} (40) may be used if appropriate and bilaterally agreed upon.<br/>
     * Type: Character (see {@link OrdType})<br/>
     * Used: {@link TriggeringInstruction}
     */
    TriggerOrderType                                    (1111),
    /**
     * The Quantity the order should have after the trigger has hit.<br/>
     * Type: Double<br/>
     * Used: {@link TriggeringInstruction}
     */
    TriggerNewQty                                       (1112),
    /**
     * Defines the trading session at which the order will be activated.<br/>
     * Type: String<br/>
     * Used: {@link TriggeringInstruction}
     */
    TriggerTradingSessionID                             (1113),
    /**
     * Defines the subordinate trading session at which the order will be activated.<br/>
     * Type: String<br/>
     * Used: {@link TriggeringInstruction}
     */
    TriggerTradingSessionSubID                          (1114),
    /**
     * Defines the type of interest behind a trade (fill or partial fill).<br/>
     * Type: Character (see {@link OrderCategory})<br/>
     * Used: {@link TriggeringInstruction}
     */
    OrderCategory                                       (1115),
    /**
     * Number of {@link TagNum#RootPartyID} (1117), {@link TagNum#RootPartyIDSource} (1118), and
     * {@link TagNum#RootPartyRole} (1119) entries.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.RootParties}
     */
    NoRootPartyIDs                                      (1116),
    /**
     * PartyID value within a root parties component. Same values as {@link TagNum#PartyID} (448).<br/>
     * Type: String<br/>
     * Used: {@link RootPartyGroup}
     */
    RootPartyID                                         (1117),
    /**
     * PartyIDSource value within a root parties component. Same values as {@link TagNum#PartyIDSource} (447).<br/>
     * Type: Character (see {@link PartyIDSource})<br/>
     * Used: {@link RootPartyGroup}
     */
    RootPartyIDSource                                   (1118),
    /**
     * PartyRole value within a root parties component. Same values as {@link TagNum#PartyRole} (452).<br/>
     * Type: Integer (see {@link PartyRole})<br/>
     * Used: {@link RootPartyGroup}
     */
    RootPartyRole                                       (1119),
    /**
     * Number of {@link TagNum#RootPartySubID} (1121) and {@link TagNum#RootPartySubIDType} (1122) entries.<br/>
     * Type: Integer<br/>
     * Used: {@link RootPartyGroup}
     */
    NoRootPartySubIDs                                   (1120),
    /**
     * PartySubID value within a root parties component. Same values as {@link TagNum#PartySubID} (523).<br/>
     * Type: String<br/>
     * Used: {@link RootPartySubGroup}
     */
    RootPartySubID                                      (1121),
    /**
     * Type of {@link TagNum#RootPartySubID} (1121) value. Same values as {@link TagNum#PartySubIDType} (803).<br/>
     * Type: Integer (see {@link PartySubIDType})<br/>
     * Used: {@link RootPartySubGroup}
     */
    RootPartySubIDType                                  (1122),
    /**
     * Specified how the Trade Capture Report should be handled by the Respondent.<br/>
     * Type: Character (see {@link TradeHandlingInstr})<br/>
     * Used: {@link [TradeCaptureReportMsg}, {@link [TradeCaptureReportAckMsg}, {@link [TradeCaptureReportRequestMsg}
     */
    TradeHandlingInstr                                  (1123),
    /**
     * Optionally used with TradeHandlingInstr = 0 to relay the trade handling instruction used when reporting the 
     * trade to the marketplace. Same values as {@link TagNum#TradeHandlingInstr} (1123).<br/>
     * Type: Character (see {@link TradeHandlingInstr})<br/>
     * Used: {@link [TradeCaptureReportMsg}, {@link [TradeCaptureReportAckMsg}
     */
    OrigTradeHandlingInstr                              (1124),
    /**
     * Used to preserve original trade date when original trade is being referenced in a subsequent trade transaction 
     * such as a transfer.<br/>
     * Type: Local market date<br/>
     * Used: {@link [TradeCaptureReportMsg}, {@link [TradeCaptureReportAckMsg}
     */
    OrigTradeDate                                       (1125),
    /**
     * Used to preserve original trade id when original trade is being referenced in a subsequent trade transaction 
     * such as a transfer.<br/>
     * Type: String<br/>
     * Used: {@link [TradeCaptureReportMsg}, {@link [TradeCaptureReportAckMsg}
     */
    OrigTradeID                                         (1126),
    /**
     * Used to preserve original trade id when original trade is being referenced in a subsequent trade transaction 
     * such as a transfer.<br/>
     * Type: String<br/>
     * Used: {@link [TradeCaptureReportMsg}, {@link [TradeCaptureReportAckMsg}
     */
    OrigSecondaryTradeID                                (1127),
    /**
     * Application version ID.<br/>
     * Type: String<br/>
     * Used: Standard Header 5.0
     */
    ApplVerID                                           (1128),
    /**
     * Customer application version ID.<br/>
     * Type: String<br/>
     * Used: Standard Header 5.0
     */
    CstmApplVerID                                       (1129),
    /**
     * Reference application version ID.<br/>
     * Type: String<br/>
     * Used: {@link Logon}
     */
    RefApplVerID                                        (1130),
    /**
     * Reference customer application version ID.<br/>
     * Type: String<br/>
     * Used: {@link Logon}
     */
    RefCstmApplVerID                                    (1131),
    /**
     * Transact time in the local date-time stamp with a TZ offset to UTC identified<br/>
     * Type: TZ Timestamp<br/>
     * Used: {@link TradeCaptureReportMsg}
     */
    TZTransactTime                                      (1132),
    /**
     * The ID source of ExDestination.<br/>
     * Type: Character (see {@link ExDestinationIDSource})<br/>
     * Used: {@link QuoteMsg}
     */
    ExDestinationIDSource                               (1133),
    /**
     * Indicates that the reported price that is different from the market price. The price difference should 
     * be stated by using field 828 TrdType and, if required, field 829 TrdSubType.<br/>
     * Type: Boolean<br/>
     * Used: {@link TradeCaptureReportMsg}
     */
    ReportedPxDiff                                      (1134),
    /**
     * Indicates the system or medium on which the report has been published.<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportAckMsg}
     */
    RptSys                                              (1135),
    /**
     * learingFeeIndicator(635) for Allocation, see {@link TagNum#ClearingFeeIndicator} (635) for permitted values.<br/>
     * Type: String<br/>
     * Used: {@link TradeAllocGroup}
     */
    AllocClearingFeeIndicator                           (1136),
    /**
     * Default application version ID.<br/>
     * Type: String<br/>
     * Used: {@link Logon}
     */
    DefaultApplVerID                                    (1137),
    /**
     * The quantity to be displayed . Required for reserve orders.
     * On orders specifies the qty to be displayed, on execution reports
     * the currently displayed quantity.<br/>
     * Type: Double<br/>
     * Used: {@link DisplayInstruction}
     */
    DisplayQty                                          (1138),
    /**
     * Free format text string related to exchange.<br/>
     * Type: String<br/>
     * Used: {@link TrdCapRptSideGroup}
     */
    ExchangeSpecialInstructions                         (1139),
    /**
     * The maximum order quantity that can be submitted for a security.<br/>
     * Type: Double<br/>
     * Used: {@link BaseTradingRuleGroup}
     */
    MaxTradeVol                                         (1140),
    /**
     * The number of feed types and corresponding book depths associated with a security.<br/>
     * Type: Integer<br/>
     * Used: {@link MarketDataFeedTypeGroup}
     */
    NoMDFeedTypes                                       (1141),
    /**
     *The types of algorithm used to match orders in a specific security. Possible value types are FIFO,
     * Allocation, Pro-rata, Lead Market Maker, Currency Calender.<br/>
     * Type: String<br/>
     * Used: {@link MatchRuleGroup}
     */
    MatchAlgorithm                                      (1142),
    /**
     * The maximum price variation of an execution from one event to the next for a given security.<br/>
     * Type: Double<br/>
     * Used: {@link BaseTradingRules}
     */
    MaxPriceVariation                                   (1143),
    /**
     * Indicates that an implied market should be created for either the legs of a multi-leg instrument
     * (Implied-in) or for the multi-leg instrument based on the existence of the legs (Implied-out).
     * Determination as to whether implied markets should be created is generally done at the level of
     * the multi-leg instrument. Commonly used in listed derivatives.<br/>
     * Type: Integer (see {@link ImpliedMarketIndicator})<br/>
     * Used: {@link BaseTradingRules}
     */
    ImpliedMarketIndicator                              (1144),
    /**
     * Specific time of event. To be used in combination with {@link TagNum#EventDate} [866].<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link BaseTradingRules}
     */
    EventTime                                           (1145),
    /**
     * Minimum price increment amount associated with the MinPriceIncrement (tag 969).
     * For listed derivatives, the value can be calculated by multiplying MinPriceIncrement
     * by ContractValueFactor(231).<br/>
     * Type: Double<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    MinPriceIncrementAmount                             (1146),
    /**
     * Used to indicate the quantity of the underlying commodity unit of measure on which
     * the contract is based, such as, 2500 lbs of lean cattle, 1000 barrels of crude oil,
     * 1000 bushels of corn, etc. <br/>
     * UnitOfMeasureQty is required for UnitOfMeasure Variable Quantity UOMs enumerations.
     * Refer to the definition of {@link TagNum#UnitOfMeasure}(996) for more information
     * on the use of UnitOfMeasureQty.<br/>
     * Type: Double<br/>
     * Used:
     */
    UnitOfMeasureQty                                    (1147),
    /**
     * Allowable low limit price for the trading day. A key parameter in validating order price.
     * Used as the lower band for validating order prices. Orders submitted with prices
     * below the lower limit will be rejected.<br/>
     * Type: Double<br/>
     * Used: {@link PriceLimits}
     */
    LowLimitPrice                                       (1148),
    /**
     * Allowable high limit price for the trading day. A key parameter in validating order price.
     * Used as the upper band for validating order prices. Orders submitted with prices above
     * the upper limit will be rejected.<br/>
     * Type: Double<br/>
     * Used: {@link PriceLimits}
     */
    HighLimitPrice                                      (1149),
    /**
     * Reference price for the current trading price range usually representing the mid price
     * between the HighLimitPrice and LowLimitPrice. The value may be the settlement price or
     * closing price of the prior trading day..<br/>
     * Type: Double<br/>
     * Used: {@link PriceLimits}
     */
    TradingReferencePrice                               (1150),
    /**
     * An exchange specific name assigned to a group of related securities which may be
     * concurrently affected by market events and actions.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    SecurityGroup                                       (1151),
    /**
     * Allow sequencing of Legs for a Strategy to be captured.<br/>
     * Type: Integer<br/>
     * Used: 
     */
    LegNumber                                           (1152),
    /**
     * Settlement cycle in which the settlement obligation was generated.<br/>
     * Type: Integer<br/>
     * Used: {@link SettlementObligationReportMsg}
     */
    SettlementCycleNo                                   (1153),
    /**
     * Used to identify the trading currency on the Trade Capture Report Side.<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: 
     */
    SideCurrency                                        (1154),
    /**
     * Used to identify the settlement currency on the Trade Capture Report Side.<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: 
     */
    SideSettlCurrency                                   (1155),
    /**
     * The extension pack number associated with an application message.<br/>
     * Type: Integer<br/>
     * Used: 
     */
    ApplExtID                                           (1156),
    /**
     * Net flow of Currency 1.<br/>
     * Type: Double<br/>
     * Used: 
     */
    CcyAmt                                              (1157),
    /**
     * Used to group Each Settlement Party.<br/>
     * Type: Integer<br/>
     * Used: 
     */
    NoSettlDetails                                      (1158),
    /**
     * Used to identify the reporting mode of the settlement obligation which is either preliminary or final.<br/>
     * Type: Integer (see {@link SettlObligMode})<br/>
     * Used: 
     */
    SettlObligMode                                      (1159),
    /**
     * Message identifier for Settlement Obligation Report.<br/>
     * Type: String<br/>
     * Used: 
     */
    SettlObligMsgID                                     (1160),
    /**
     * Unique ID for this settlement instruction.<br/>
     * Type: String<br/>
     * Used: 
     */
    SettlObligID                                        (1161),
    /**
     * Used to identify whether these delivery instructions are for the buyside or the sellside.<br/>
     * Type: Character (see {@link SettlObligSource})<br/>
     * Used:
     */
    SettlObligSource                                    (1164),
    /**
     * Number of settlement obligations.<br/>
     * Type: Integer<br/>
     * Used:
     */
    NoSettlOblig                                        (1165),
    /**
     * Unique identifier for a quote message.<br/>
     * Type: String<br/>
     * Used: {@link QuoteMsg}
     */
    QuoteMsgID                                          (1166),
    /**
     * Identifies the status of an individual quote.
     * See also {@link TagNum#QuoteStatus} (297) which is used for single Quotes.<br/>
     * Type: Integer (see {@link QuoteEntryStatus})<br/>
     * Used: {@link MassQuoteAckMsg}
     */
    QuoteEntryStatus                                    (1167),
    /**
     * Specifies the number of canceled quotes.<br/>
     * Type: Integer<br/>
     * Used: {@link MassQuoteAckMsg}
     */
    TotNoCxldQuotes                                     (1168),
    /**
     * Specifies the number of accepted quotes.<br/>
     * Type: Integer<br/>
     * Used: {@link MassQuoteAckMsg}
     */
    TotNoAccQuotes                                      (1169),
    /**
     * Specifies the number of rejected quotes.<br/>
     * Type: Integer<br/>
     * Used: {@link MassQuoteAckMsg}
     */
    TotNoRejQuotes                                      (1170),
    /**
     * Specifies whether a quote is public, i.e. available to the market, or private,
     * i.e. available to a specified counterparty only.<br/>
     * Type: Boolean (see {@link BoolYesNo})<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg},{@link QuoteMsg}
     */
    PrivateQuote                                        (1171),
    /**
     * Specifies the type of respondents requested.<br/>
     * Type: Integer (see {@link RespondentType})<br/>
     * Used: {@link net.hades.fix.message.QuoteRequestMsg}
     */
    RespondentType                                      (1172),
    /**
     * Describes a class of sub book, e.g. for the separation of various lot types.
     * The Sub Book Type indicates that the following Market Data Entries belong to a
     * non-integrated Sub Book. Whenever provided the Sub Book must be used together with
     * MDPriceLevel and MDEntryPositionNo in order to sort the order properly.<br/>
     * Values are bilaterally agreed. <br/>
     * Type: Integer<br/>
     * Used: {@link MarketDataSnapshot}
     */
    MDSubBookType                                       (1173),
    /**
     * Identifies an event related to a {@link TagNum#SecurityTradingStatus} (326). An event occurs and is gone,
     * it is not a state that applies for a period of time.<br/>
     * Type: Integer (see {@link SecurityTradingEvent})<br/>
     * Used: {@link [SecurityStatusMsg}
     */
    SecurityTradingEvent                                (1174),
    /**
     * Number of statistics indicator repeating group entries. <br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.group.MDIncGroup}
     */
    NoStatsIndicators                                   (1175),
    /**
     * Type of statistics.<br/>
     * Type: Integer (see {@link StatsType})<br/>
     * Used: {@link net.hades.fix.message.group.MDIncGroup}
     */
    StatsType                                           (1176),
    /**
     * The number of secondary sizes specifies in this entry.<br/>
     * Type: Integer<br/>
     * Used: {@link MarketDataSnapshot}
     */
    NoOfSecSizes                                        (1177),
    /**
     * Specifies the type of secondary size.<br/>
     * Type: Integer<br/>
     * Used: {@link MarketDataSnapshot}
     */
     MDSecSizeType                                      (1178),
     /**
     * A part of the {@link TagNum#MDEntrySize} (271) that represents secondary interest as specified
      * by {@link TagNum#MDSecSizeType} (1178).<br/>
     * Type: Double<br/>
     * Used: {@link MarketDataSnapshot}
     */
     MDSecSize                                          (1179),
    /**
     * Identifies the application with which a message is associated. Used only if application
     * sequencing is in effect.<br/>
     * Type: String<br/>
     * Used: {@link ApplicationSequenceControl}
     */
    ApplID                                              (1180),
    /**
     * Data sequence number to be used when FIX session is not in effect.<br/>
     * Type: SeqNum<br/>
     * Used: {@link ApplicationSequenceControl}
     */
    ApplSeqNum                                          (1181),
    /**
     * Lenght of the SecurityXML data block.<br/>
     * Type: Integer<br/>
     * Used: {@link SecurityXML}
     */
    SecurityXMLLen                                      (1184),
    /**
     * Actual XML data stream describing a security, normally FpML.<br/>
     * Type: Data<br/>
     * Used: {@link SecurityXML}
     */
    SecurityXML                                         (1185),
    /**
     * The schema used to validate the contents of SecurityXML.<br/>
     * Type: String<br/>
     * Used: {@link SecurityXML}
     */
    SecurityXMLSchema                                   (1186),
    /**
     * Set by the sender to tell the receiver to perform an immediate refresh of the book due
     * to disruptions in the accompanying real-time feed:
     * <ul>
     *      <li>'Y' - Mandatory refresh by all participants
     *      <li>'N' - Process as required.<br/>
     * </ul>
     * Type: Boolean<br/>
     * Used: {@link MarketDataSnapshot}
     */
    RefreshIndicator                                    (1187),
    /**
     * Annualized volatility for option model calculations.<br/>
     * Type: Decimal<br/>
     * Used: {@link ExecutionReportMsg},{@link TradeCaptureReportMsg}
     */
    Volatility                                          (1188),
    /**
     * Time to expiration in years calculated as the number of days remaining to expiration divided by 365 days per year.<br/>
     * Type: Decimal<br/>
     * Used: {@link ExecutionReportMsg}
     */
    TimeToExpiration                                    (1189),
    /**
     * Interest rate. Usually some form of short term rate.<br/>
     * Type: Decimal<br/>
     * Used: {@link ExecutionReportMsg}, {@link MultilegOrderCancelReplaceMsg}, {@link NewOrderMultilegMsg}, {@link TradeCaptureReportMsg}
     */
    RiskFreeRate                                        (1190),
    /**
     * Used to express the UOM of the price if different from the contract. In futures,
     * this can be different for cross-rate products in which the price is quoted in units
     * differently from the contract.<br/>
     * UnitOfMeasureQty is required for UnitOfMeasure Variable Quantity UOMs enumerations.
     * Refer to the definition of {@link TagNum#UnitOfMeasure}(996) for more information
     * on the use of UnitOfMeasureQty.<br/>
     * Type: String (see {@link PriceUnitOfMeasure})<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    PriceUnitOfMeasure                                  (1191),
    /**
     * Used to express the UOM Quantity of the price if different from the contract. In futures,
     * this can be different for physically delivered products in which price is quoted in a unit
     * size different from the contract, i.e. a Cattle Future contract has a UOMQty of 40,000
     * and a PriceUOMQty of 100.<br/>
     * Type: Quantity<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    PriceUnitOfMeasureQty                               (1192),
    /**
     * Settlement method for a contract. Can be used as an alternative to CFI Code value<br/>
     * Valid values: C - Cash settlement required P - Physical settlement required<br/>
     * Type: Character (see {@link SettlMethod})<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    SettlMethod                                         (1193),
    /**
     * Type of exercise of a derivatives security. Valid values: 0 - European 1 - American 2 - Bermuda<br/>
     * Type: Integer (see {@link ExerciseStyle})<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    ExerciseStyle                                       (1194),
    /**
     * Cash amount indicating the pay out associated with an option. For binary options this is a fixed amount.<br/>
     * Type: Amount<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    OptPayAmount                                        (1195),
    /**
     * Method for price quotation.<br/>
     * Valid values: STD - Standard, money per unit of a physical INX - Index INT - Interest rate Index<br/>
     * Type: String (see {@link PriceQuoteMethod})<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    PriceQuoteMethod                                    (1196),
    /**
     * For futures, indicates type of valuation method applied.<br/>
     * Valid values: EQTY - premium style FUT - futures style mark-to-market FUTDA - futures style with an
     * attached cash adjustment<br/>
     * Type: String (see {@link ValuationMethod})<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    ValuationMethod                                     (1197),
    /**
     * Indicates whether instruments are pre-listed only or can also be defined via user request.<br/>
     * Valid values: 0 - pre-listed only 1 - user requested<br/>
     * Type: Integer (see {@link ListMethod})<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    ListMethod                                          (1198),
    /**
     * Used to express the ceiling price of a capped call.<br/>
     * Type: Double<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    CapPrice                                            (1199),
    /**
     * Used to express the floor price of a capped put.<br/>
     * Type: Double<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    FloorPrice                                          (1200),
    /**
     * Number of strike rule entries. This block specifies the rules for determining how new strikes
     * should be listed within the stated price range of the underlying instrument.<br/>
     * Type: Integer<br/>
     * Used: {@link [StrikeRuleGroup}
     */
    NoStrikeRules                                       (1201),
    /**
     * Starting price for the range to which the StrikeIncrement applies. Price refers to the price of the underlying.<br/>
     * Type: Double<br/>
     * Used: {@link [StrikeRuleGroup}
     */
    StartStrikePxRange                                  (1202),
    /**
     * Ending price of the range to which the StrikeIncrement applies. Price refers to the price of the underlying.<br/>
     * Type: Double<br/>
     * Used: {@link [StrikeRuleGroup}
     */
    EndStrikePxRange                                    (1203),
    /**
     * Value by which strike price should be incremented within the specified price range.<br/>
     * Type: Double<br/>
     * Used: {@link [StrikeRuleGroup}
     */
    StrikeIncrement                                     (1204),
    /**
     * Number of tick rules. This block specifies the rules for determining how a security ticks, i.e. the
     * price increments at which it can be quoted and traded, depending on the current price of the security.<br/>
     * Type: Integer<br/>
     * Used: {@link [TickRuleGroup}
     */
    NoTickRules                                         (1205),
    /**
     * Starting price range for specified tick increment.<br/>
     * Type: Double<br/>
     * Used: {@link [TickRuleGroup}
     */
    StartTickPriceRange                                 (1206),
    /**
     * Ending price range for the specified tick increment.<br/>
     * Type: Double<br/>
     * Used: {@link [TickRuleGroup}
     */
    EndTickPriceRange                                   (1207),
    /**
     * Tick increment for stated price range. Specifies the valid price increments at which a security can be quoted and traded.<br/>
     * Type: Double<br/>
     * Used: {@link [TickRuleGroup}
     */
    TickIncrement                                       (1208),
    /**
     * Tick increment for stated price range. Specifies the valid price increments at which a security can be quoted and traded.<br/>
     * Type: Integer (see {@link TickRuleType})<br/>
     * Used: {@link [TickRuleGroup}
     */
    TickRuleType                                        (1209),
    /**
     * Code to represent the type of instrument attribute.<br/>
     * Type: Integer (see {@link NestedInstrAttribType})<br/>
     * Used: {@link [NestedInstrumentAttributeGroup}
     */
    NestedInstrAttribType                               (1210),
    /**
     * Attribute value appropriate to the NestedInstrAttribType field.<br/>
     * Type: String<br/>
     * Used: {@link [NestedInstrumentAttributeGroup}
     */
    NestedInstrAttribValue                              (1211),
    /**
     * Time of security's maturity expressed in local time with offset to UTC specified.<br/>
     * Type: TZTimeOnly<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegMaturityTime                                     (1212),
    /**
     * Time of security's maturity expressed in local time with offset to UTC specified.<br/>
     * Type: TZTimeOnly<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingMaturityTime                              (1213),
    /**
     * Refer to {@link TagNum#Symbol} definition.<br/>
     * Type: String<br/>
     * Used: {@link DerivativeInstrument}
     */
    DerivativeSymbol                                    (1214),
    /**
     * Refer to {@link TagNum#SymbolSfx} definition.<br/>
     * Type: String<br/>
     * Used: {@link DerivativeInstrument}
     */
    DerivativeSymbolSfx                                 (1215),
    /**
     * Refer to {@link TagNum#SecurityID} definition.<br/>
     * Type: String<br/>
     * Used: {@link DerivativeInstrument}
     */
    DerivativeSecurityID                                (1216),
    /**
     * Refer to {@link TagNum#IDSource} definition.<br/>
     * Type: String<br/>
     * Used: {@link DerivativeInstrument}
     */
    DerivativeSecurityIDSource                          (1217),
    /**
     * Refer to {@link TagNum#NoSecurityAltID} definition.<br/>
     * Type: Integer<br/>
     * Used: {@link [DerivativeSecurityAltIDGroup}
     */
    NoDerivativeSecurityAltID                           (1218),
    /**
     * Refer to {@link TagNum#SecurityAltID} definition.<br/>
     * Type: String<br/>
     * Used: {@link DerivativeSecurityAltIDGroup}
     */
    DerivativeSecurityAltID                             (1219),
    /**
     * Refer to {@link TagNum#SecurityAltIDSource} definition.<br/>
     * Type: String<br/>
     * Used: {@link DerivativeSecurityAltIDGroup}
     */
    DerivativeSecurityAltIDSource                       (1220),
    /**
     * Refer to {@link TagNum#LowLimitPrice} definition.<br/>
     * Type: String<br/>
     * Used: {@link SecondaryPriceLimits}
     */
    SecondaryLowLimitPrice                              (1221),
    /**
     * Allows maturity rule to be referenced via an identifier so that rules do not need to be explicitly enumerated.<br/>
     * Type: String<br/>
     * Used: {@link [MaturityRuleGroup}
     */
    MaturityRuleID                                      (1222),
    /**
     * Allows strike rule to be referenced via an identifier so that rules do not need to be explicitly enumerated.<br/>
     * Type: String<br/>
     * Used: {@link [MaturityRuleGroup}
     */
    StrikeRuleID                                        (1223),
    /**
     * Refer to definition of {@link TagNum#UnitOfMeasureQty} (1147).<br/>
     * Type: Double<br/>
     * Used: {@link [StrikeRuleGroup}
     */
    LegUnitOfMeasureQty                                 (1224),
    /**
     * Cash amount indicating the pay out associated with an option. For binary options this is a fixed amount.<br/>
     * Type: Double<br/>
     * Used: {@link [DerivativeInstrument}
     */
    DerivativeOptPayAmount                              (1225),
    /**
     * Ending maturity month year for an option class.<br/>
     * Type: String<br/>
     * Used: {@link [MaturityRuleGroup}
     */
    EndMaturityMonthYear                                (1226),
    /**
     * Identifies an entire suite of products for a given market. In Futures this may be
     * "interest rates", "agricultural", "equity indexes", etc.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    ProductComplex                                      (1227),
    /**
     * Refer to {@link TagNum#ProductComplex}(1227)<br/>
     * Type: String<br/>
     * Used: {@link DerivativeInstrument}
     */
    DerivativeProductComplex                            (1228),
    /**
     * Increment between successive maturities for an option class.<br/>
     * Type: Integer<br/>
     * Used: {@link MaturityRuleGroup}
     */
    MaturityMonthYearIncrement                          (1229),
    /**
     * Refer to definition of {@link TagNum#HighLimitPrice} (1149)<br/>
     * Type: Double<br/>
     * Used: {@link SecondaryPriceLimitGroup}
     */
    SecondaryHighLimitPrice                             (1230),
    /**
     * Minimum lot size allowed based on lot type specified in {@link TagNum#LotType} (1093)<br/>
     * Type: Double<br/>
     * Used: {@link LotTypeRuleGroup}
     */
    MinLotSize                                          (1231),
    /**
     * Number of execution instructions.<br/>
     * Type: Integer<br/>
     * Used: {@link LotTypeRuleGroup}
     */
    NoExecInstRules                                     (1232),
    /**
     * Number of Lot Type Rules.<br/>
     * Type: Integer<br/>
     * Used: {@link LotTypeRuleGroup}
     */
    NoLotTypeRules                                      (1234),
    /**
     * Number of Match Rules.<br/>
     * Type: Integer<br/>
     * Used: {@link MaturityRuleGroup}
     */
    NoMatchRules                                        (1235),
    /**
     * Number of maturity rules in MarurityRules component block.<br/>
     * Type: Integer<br/>
     * Used: {@link MaturityRuleGroup}
     */
    NoMaturityRules                                     (1236),
    /**
     * Number of order types.<br/>
     * Type: Integer<br/>
     * Used: {@link TradingSessionRules}
     */
    NoOrdTypeRules                                      (1237),
    /**
     * Number of time in force techniques.<br/>
     * Type: Integer<br/>
     * Used: {@link TradingSessionRules}
     */
    NoTimeInForceRules                                  (1239),
    /**
     * Refer to definition for {@link TagNum#TradingReferencePrice} (1150)<br/>
     * Type: Double<br/>
     * Used: {@link SecondaryPriceLimits}
     */
    SecondaryTradingReferencePrice                      (1240),
    /**
     * Starting maturity month year for an option class.<br/>
     * Type: String<br/>
     * Used: {@link MaturityRuleGroup}
     */
    StartMaturityMonthYear                              (1241),
    /**
     * Used to indicate if a product or group of product supports the creation of flexible securities.<br/>
     * Type: Boolean<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    FlexProductEligibilityIndicator                     (1242),
    /**
     * Refer to {@link TagNum#FlexProductEligibilityIndicator} (1242).<br/>
     * Type: Boolean<br/>
     * Used: 
     */
    DerivFlexProductEligibilityIndicator                (1243),
    /**
     * Used to indicate a derivatives security that can be defined using flexible terms.
     * The terms commonly permitted to be defined by market participants are expiration
     * date and strike price. FlexibleIndicator is an alternative {@link TagNum#CFICode} (461)
     * Standard/Non-standard attribute.<br/>
     * Type: Boolean<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    FlexibleIndicator                                   (1244),
    /**
     * Used when the trading currency can differ from the price currency.<br/>
     * Type: String (see {@ Currency})<br/>
     * Used: {@link BaseTradingRules}
     */
    TradingCurrency                                     (1245),
    /**
     * Refer to definition  {@link TagNum#Product} (460).<br/>
     * Type: Integer (see {@ Product})<br/>
     * Used: 
     */
    DerivativeProduct                                   (1246),
    /**
     * Refer to definition  {@link TagNum#SecurityGroup} (1151).<br/>
     * Type: String<br/>
     * Used: 
     */
    DerivativeSecurityGroup                             (1247),
    /**
     * Refer to definition  {@link TagNum#CFICode} (461).<br/>
     * Type: String<br/>
     * Used: 
     */
    DerivativeCFICode                                   (1248),
    /**
     * Refer to definition  {@link TagNum#SecurityType} (167).<br/>
     * Type: String<br/>
     * Used: 
     */
    DerivativeSecurityType                              (1249),
    /**
     * Refer to definition  {@link TagNum#SecuritySubType} (762).<br/>
     * Type: String<br/>
     * Used: 
     */
    DerivativeSecuritySubType                           (1250),
    /**
     * Refer to definition  {@link TagNum#MaturityMonthYear} (200).<br/>
     * Type: String<br/>
     * Used: 
     */
    DerivativeMaturityMonthYear                         (1251),
    /**
     * Refer to definition  {@link TagNum#MaturityDate} (541).<br/>
     * Type: Local Market date<br/>
     * Used: 
     */
    DerivativeMaturityDate                              (1252),
    /**
     * Refer to definition  {@link TagNum#MaturityTime} (1079).<br/>
     * Type: UTC time<br/>
     * Used: 
     */
    DerivativeMaturityTime                              (1253),
    /**
     * Refer to definition  {@link TagNum#SettleOnOpenFlag} (966).<br/>
     * Type: String<br/>
     * Used: 
     */
    DerivativeSettleOnOpenFlag                          (1254),
    /**
     * Refer to definition  {@link TagNum#InstrmtAssignmentMethod} (1049).<br/>
     * Type: Character (see {@link InstrmtAssignmentMethod})<br/>
     * Used: 
     */
    DerivativeInstrmtAssignmentMethod                   (1255),
    /**
     * Refer to definition  {@link TagNum#SecurityStatus} (965).<br/>
     * Type: String (see {@link SecurityStatus})<br/>
     * Used: 
     */
    DerivativeSecurityStatus                            (1256),
    /**
     * Refer to definition  {@link TagNum#InstrRegistry} (543).<br/>
     * Type: String<br/>
     * Used: 
     */
    DerivativeInstrRegistry                             (1257),
    /**
     * Refer to definition  {@link TagNum#CountryOfIssue} (470).<br/>
     * Type: String (see {@link Country})<br/>
     * Used: 
     */
    DerivativeCountryOfIssue                            (1258),
    /**
     * Refer to definition  {@link TagNum#StateOrProvinceOfIssue} (471).<br/>
     * Type: String<br/>
     * Used: 
     */
    DerivativeStateOrProvinceOfIssue                    (1259),
    /**
     * Refer to definition  {@link TagNum#LocaleOfIssue} (472).<br/>
     * Type: String<br/>
     * Used: 
     */
    DerivativeLocaleOfIssue                             (1260),
    /**
     * Refer to definition  {@link TagNum#StrikePrice} (202).<br/>
     * Type: Double<br/>
     * Used: 
     */
    DerivativeStrikePrice                               (1261),
    /**
     * Refer to definition  {@link TagNum#StrikeCurrency} (947).<br/>
     * Type: String (see {@link Currency})<br/>
     * Used: 
     */
    DerivativeStrikeCurrency                            (1262),
    /**
     * Refer to definition  {@link TagNum#StrikeMultiplier} (967).<br/>
     * Type: Double<br/>
     * Used: 
     */
    DerivativeStrikeMultiplier                          (1263),
    /**
     * Refer to definition  {@link TagNum#StrikeValue} (968).<br/>
     * Type: Double<br/>
     * Used: 
     */
    DerivativeStrikeValue                               (1264),
    /**
     * Refer to definition  {@link TagNum#OptAttribute} (206).<br/>
     * Type: Character<br/>
     * Used: 
     */
    DerivativeOptAttribute                              (1265),
    /**
     * Refer to definition  {@link TagNum#ContractMultiplier} (231).<br/>
     * Type: Double<br/>
     * Used: 
     */
    DerivativeContractMultiplier                        (1266),
    /**
     * Refer to definition  {@link TagNum#MinPriceIncrement} (969).<br/>
     * Type: Double<br/>
     * Used: 
     */
    DerivativeMinPriceIncrement                         (1267),
    /**
     * Refer to definition  {@link TagNum#MinPriceIncrementAmount} (1146).<br/>
     * Type: Double<br/>
     * Used: 
     */
    DerivativeMinPriceIncrementAmount                   (1268),
    /**
     * Refer to definition  {@link TagNum#UnitOfMeasure} (996).<br/>
     * Type: String (see {@link UnitOfMeasure})<br/>
     * Used: 
     */
    DerivativeUnitOfMeasure                             (1269),
    /**
     * Refer to definition  {@link TagNum#UnitOfMeasureQty} (1147).<br/>
     * Type: Double<br/>
     * Used: 
     */
    DerivativeUnitOfMeasureQty                          (1270),
    /**
     * Refer to definition  {@link TagNum#TimeUnit} (997).<br/>
     * Type: String (see {@link TimeUnit})<br/>
     * Used: 
     */
    DerivativeTimeUnit                                  (1271),
    /**
     * Refer to definition  {@link TagNum#SecurityExchange} (207).<br/>
     * Type: String (see {@link Exchange})<br/>
     * Used: 
     */
    DerivativeSecurityExchange                          (1272),
    /**
     * Refer to definition  {@link TagNum#PositionLimit} (970).<br/>
     * Type: Integer<br/>
     * Used: 
     */
    DerivativePositionLimit                             (1273),
    /**
     * Refer to definition  {@link TagNum#NTPositionLimit} (971).<br/>
     * Type: Integer<br/>
     * Used: 
     */
    DerivativeNTPositionLimit                           (1274),
    /**
     * Refer to definition  {@link TagNum#Issuer} (106).<br/>
     * Type: String<br/>
     * Used: 
     */
    DerivativeIssuer                                    (1275),
    /**
     * Refer to definition  {@link TagNum#IssueDate} (225).<br/>
     * Type: Local market date<br/>
     * Used: 
     */
    DerivativeIssueDate                                 (1276),
    /**
     * Refer to definition  {@link TagNum#EncodedIssuerLen} (348).<br/>
     * Type: Integer<br/>
     * Used: 
     */
    DerivativeEncodedIssuerLen                          (1277),
    /**
     * Refer to definition  {@link TagNum#EncodedIssuer} (349).<br/>
     * Type: Data<br/>
     * Used: 
     */
    DerivativeEncodedIssuer                             (1278),
    /**
     * Refer to definition  {@link TagNum#SecurityDesc} (107).<br/>
     * Type: String<br/>
     * Used: 
     */
    DerivativeSecurityDesc                              (1279),
    /**
     * Refer to definition  {@link TagNum#EncodedSecurityDescLen} (350).<br/>
     * Type: Integer<br/>
     * Used: 
     */
    DerivativeEncodedSecurityDescLen                    (1280),
    /**
     * Refer to definition  {@link TagNum#EncodedSecurityDesc} (351).<br/>
     * Type: Data<br/>
     * Used: 
     */
    DerivativeEncodedSecurityDesc                       (1281),
    /**
     * Refer to definition  {@link TagNum#SecurityXMLLen} (1184).<br/>
     * Type: Integer<br/>
     * Used6
     */
    DerivativeSecurityXMLLen                            (1282),
    /**
     * Refer to definition  {@link TagNum#SecurityXML} (1185).<br/>
     * Type: Data<br/>
     * Used:
     */
    DerivativeSecurityXML                               (1283),
    /**
     * Refer to definition  {@link TagNum#SecurityXMLSchema} (118l).<br/>
     * Type: String<br/>
     * Used:
     */
    DerivativeSecurityXMLSchema                         (1284),
    /**
     * Derivative contract settlement month (monthyear).<br/>
     * Type: String<br/>
     * Used:
     */
    DerivativeContractSettlMonth                        (1285),
    /**
     * No of DerivativeEvent groups.<br/>
     * Type: Integer<br/>
     * Used:
     */
    NoDerivativeEvents                                  (1286),
    /**
     * Derivative event type.<br/>
     * Type: Integer (see {@ EventType})<br/>
     * Used:
     */
    DerivativeEventType                                 (1287),
    /**
     * Derivative event date.<br/>
     * Type: Local market date<br/>
     * Used:
     */
    DerivativeEventDate                                 (1288),
    /**
     * Derivative event time.<br/>
     * Type: UTC timestamp<br/>
     * Used:
     */
    DerivativeEventTime                                 (1289),
    /**
     * Derivative event price.<br/>
     * Type: Double<br/>
     * Used:
     */
    DerivativeEventPx                                   (1290),
    /**
     * Derivative event text.<br/>
     * Type: String<br/>
     * Used:
     */
    DerivativeEventText                                 (1291),
    /**
     * Refer to definition  {@link TagNum#NoParties} (453).<br/>
     * Type: Integer<br/>
     * Used:
     */
    NoDerivativeInstrumentParties                       (1292),
    /**
     * Refer to definition  {@link TagNum#PartyID} (448).<br/>
     * Type: String<br/>
     * Used:
     */
    DerivativeInstrumentPartyID                         (1293),
    /**
     * Refer to definition  {@link TagNum#PartyIDSource} (447).<br/>
     * Type: String<br/>
     * Used:
     */
    DerivativeInstrumentPartyIDSource                   (1294),
    /**
     * Refer to definition  {@link TagNum#PartyRole} (452).<br/>
     * Type: Integer<br/>
     * Used:
     */
    DerivativeInstrumentPartyRole                       (1295),
    /**
     * Refer to definition  {@link TagNum#NoPartySubIDs} (802).<br/>
     * Type: Integer<br/>
     * Used:
     */
    NoDerivativeInstrumentPartySubIDs                   (1296),
    /**
     * Refer to definition  {@link TagNum#PartySubID} (523).<br/>
     * Type: String<br/>
     * Used:
     */
    DerivativeInstrumentPartySubID                      (1297),
    /**
     * Refer to definition  {@link TagNum#PartySubIDType} (803).<br/>
     * Type: String<br/>
     * Used:
     */
    DerivativeInstrumentPartySubIDType                  (1298),
    /**
     * Type of exercise of a derivatives security.<br/>
     * Type: Character (see {@link DerivativeExerciseStyle})<br/>
     * Used:
     */
    DerivativeExerciseStyle                             (1299),
    /**
     * Identifies the market segment.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.NewsMsg}
     */
    MarketSegmentID                                     (1300),
    /**
     * Identifies the Market.<br/>
     * Type: String (see {@link Exchange})<br/>
     * Used: {@link net.hades.fix.message.NewsMsg}
     */
    MarketID                                            (1301),
    /**
     * Unit of measure for the Maturity Month Year Increment.<br/>
     * Type: Integer (see {@link MaturityMonthYearIncrementUnits})<br/>
     * Used: {@link MaturityRuleGroup}
     */
    MaturityMonthYearIncrementUnits                     (1302),
    /**
     * Format used to generate the MaturityMonthYear for each option.<br/>
     * Type: Integer (see {@link MaturityMonthYearFormat})<br/>
     * Used: {@link MaturityRuleGroup}
     */
    MaturityMonthYearFormat                             (1303),
    /**
     * Expiration Style for an option class.<br/>
     * Type: Integer (see {@link StrikeExerciseStyle})<br/>
     * Used: {@link StrikeRuleGroup}
     */
    StrikeExerciseStyle                                 (1304),
    /**
     * Describes the how the price limits are expressed.<br/>
     * Type: Integer (see {@link PriceLimitType})<br/>
     * Used: {@link SecondaryPriceLimits}
     */
    SecondaryPriceLimitType                             (1305),
    /**
     * Describes the how the price limits are expressed.<br/>
     * Type: Integer (see {@link PriceLimitType})<br/>
     * Used: {@link PriceLimits}
     */
    PriceLimitType                                      (1306),
    /**
     * Identifies the type of Security List Request.<br/>
     * Type: Integer (see {@link SecurityListRequestType})<br/>
     * Used:
     */
    DerivativeSecurityListRequestType                   (1307),
    /**
     * Indicates execution instructions that are valid for the specified market segment.<br/>
     * Type: Character (see {@link ExecInstValue})<br/>
     * Used:
     */
    ExecInstValue                                       (1308),
    /**
     * No of trading session rules.<br/>
     * Type: Integer<br/>
     * Used: {@link TradingSessionRulesGroup}
     */
    NoTradingSessionRules                               (1309),
    /**
     * Number of Market Segments on which a security may trade.<br/>
     * Type: Integer<br/>
     * Used: {@link MarketSegmentGroup}
     */
    NoMarketSegments                                    (1310),
    /**
     * Number of derivative instrument attributes.<br/>
     * Type: Integer<br/>
     * Used: {@link DerivativeInstrumentAttribute}
     */
    NoDerivativeInstrAttrib                             (1311),
    /**
     * Number of nested attributes.<br/>
     * Type: Integer<br/>
     * Used: {@link MarketSegmentGroup}
     */
    NoNestedInstrAttrib                                 (1312),
    /**
     * Refer to definition of {@link TagNum#InstrAttribType} (871)<br/>
     * Type: Integer (see {@link InstrAttribType)}<br/>
     * Used:
     */
    DerivativeInstrAttribType                           (1313),
    /**
     * Refer to definition of {@link TagNum#InstrAttribValue} (872)<br/>
     * Type: String<br/>
     * Used:
     */
    DerivativeInstrAttribValue                          (1314),
    /**
     * Refer to definition of {@link TagNum#PriceUnitOfMeasure} (1191)<br/>
     * Type: String (see {@link PriceUnitOfMeasure})<br/>
     * Used:
     */
    DerivativePriceUnitOfMeasure                        (1315),
    /**
     * Refer to definition of {@link TagNum#PriceUnitOfMeasureQty} (1192)<br/>
     * Type: Double<br/>
     * Used:
     */
    DerivativePriceUnitOfMeasureQty                     (1316),
    /**
     * Refer to definition of {@link TagNum#SettlMethod} (1193)<br/>
     * Type: Character (see {@link SettlMethod})<br/>
     * Used:
     */
    DerivativeSettlMethod                               (1317),
    /**
     * Refer to definition of {@link TagNum#PriceQuoteMethod} (1196)<br/>
     * Type: String (see {@link PriceQuoteMethod})<br/>
     * Used:
     */
    DerivativePriceQuoteMethod                          (1318),
    /**
     * Refer to definition of {@link TagNum#ValuationMethod} (1197)<br/>
     * Type: String (see {@link ValuationMethod})<br/>
     * Used:
     */
    DerivativeValuationMethod                           (1319),
    /**
     * Refer to definition of {@link TagNum#ListMethod} (1198)<br/>
     * Type: Integer (see {@link ListMethod})<br/>
     * Used:
     */
    DerivativeListMethod                                (1320),
    /**
     * Refer to definition of {@link TagNum#CapPrice} (1199)<br/>
     * Type: Double<br/>
     * Used:
     */
    DerivativeCapPrice                                  (1321),
    /**
     * Refer to definition of {@link TagNum#FloorPrice} (1200)<br/>
     * Type: Double<br/>
     * Used:
     */
    DerivativeFloorPrice                                (1322),
    /**
     * Refer to definition of {@link TagNum#PutOrCall} (201)<br/>
     * Type: Integer (see {@link PutOrCall})<br/>
     * Used:
     */
    DerivativePutOrCall                                 (1323),
    /**
     * Refer to definition of {@link TagNum#PutOrCall} (201)<br/>
     * Type: Character (see {@link ListUpdateAction})<br/>
     * Used:
     */
    ListUpdateAction                                    (1324),
    /**
     * Reference to a parent Market Segment. See {@link TagNum#MarketSegmentID} (1300).<br/>
     * Type: String<br/>
     * Used: {@link MarketDefinitionRequestMsg},{@link MarketDefinitionMsg},{@link MarketDefinitionUpdateReportMsg}
     */
    ParentMktSegmID                                     (1325),
    /**
     * Trading Session description.<br/>
     * Type: String<br/>
     * Used:
     */
    TradingSessionDesc                                  (1326),
    /**
     * Specifies the action taken for the specified trading sessions.<br/>
     * Type: Character (see {@link ListUpdateAction})<br/>
     * Used:
     */
    TradSesUpdateAction                                 (1327),
    /**
     * Those will be used by Firms to send a reason for rejecting a trade in an allocate claim model.<br/>
     * Type: String<br/>
     * Used: {@link TradeCaptureReportMsg}
     */
    RejectText                                          (1328),
    /**
     * This is a multiplier that Clearing (Fee system) will use to calculate fees and will be sent to the firms on their confirms.<br/>
     * Type: Double<br/>
     * Used: {@link TradeCaptureReportMsg}, @link TradeCaptureReportAckMsg}
     */
    FeeMultiplier                                       (1329),
    /**
     * Refer to definition for {@link TagNum#Symbol} (55)<br/>
     * Type: String<br/>
     * Used:
     */
    UnderlyingLegSymbol                                 (1330),
    /**
     * Refer to definition for {@link TagNum#SymbolSfx} (65)<br/>
     * Type: String<br/>
     * Used:
     */
    UnderlyingLegSymbolSfx                              (1331),
    /**
     * Refer to definition for {@link TagNum#SecurityID} (48)<br/>
     * Type: String<br/>
     * Used:
     */
    UnderlyingLegSecurityID                             (1332),
    /**
     * Refer to definition for {@link TagNum#SecurityIDSource} (22)<br/>
     * Type: String<br/>
     * Used:
     */
    UnderlyingLegSecurityIDSource                       (1333),
    /**
     * Refer to definition for {@link TagNum#NoSecurityAltID} (454)<br/>
     * Type: Integer<br/>
     * Used:
     */
    NoUnderlyingLegSecurityAltID                        (1334),
    /**
     * Refer to definition for {@link TagNum#SecurityAltID} (455)<br/>
     * Type: String<br/>
     * Used:
     */
    UnderlyingLegSecurityAltID                          (1335),
    /**
     * Refer to definition for {@link TagNum#SecurityAltIDSource} (456)<br/>
     * Type: String<br/>
     * Used:
     */
    UnderlyingLegSecurityAltIDSource                    (1336),
    /**
     * Refer to definition for {@link TagNum#SecurityType} (167)<br/>
     * Type: String<br/>
     * Used:
     */
    UnderlyingLegSecurityType                           (1337),
    /**
     * Refer to definition for {@link TagNum#SecuritySubType} (762)<br/>
     * Type: String<br/>
     * Used:
     */
    UnderlyingLegSecuritySubType                        (1338),
    /**
     * Refer to definition for {@link TagNum#MaturityMonthYear} (200)<br/>
     * Type: String<br/>
     * Used:
     */
    UnderlyingLegMaturityMonthYear                      (1339),
    /**
     * Refer to definition for {@link TagNum#StrikePrice} (202)<br/>
     * Type: Double<br/>
     * Used:
     */
    UnderlyingLegStrikePrice                            (1340),
    /**
     * Refer to definition for {@link TagNum#SecurityExchange} (207)<br/>
     * Type: String<br/>
     * Used:
     */
    UnderlyingLegSecurityExchange                       (1341),
    /**
     * If provided, then Instrument occurrence has explicitly changed.<br/>
     * Type: Integer<br/>
     * Used: 
     */
    NoOfLegUnderlyings                                  (1342),
    /**
     * Refer to definition for {@link TagNum#PutOrCall} (201)<br/>
     * Type: Integer<br/>
     * Used:
     */
    UnderlyingLegPutOrCall                              (1343),
    /**
     * Refer to definition for {@link TagNum#CFICode} (461)<br/>
     * Type: String<br/>
     * Used:
     */
    UnderlyingLegCFICode                                (1344),
    /**
     * Date of maturity.<br/>
     * Type: Local market date<br/>
     * Used:
     */
    UnderlyingLegMaturityDate                           (1345),
    /**
     * Unique identifier for request.<br/>
     * Type: String<br/>
     * Used: {@link ApplicationMessageReportMsg},{@link ApplicationMessageRequestMsg},{@link ApplicationMessageRequestAckMsg}
     */
    ApplReqID                                           (1346),
    /**
     * Type of Application Message Request being made.<br/>
     * Type: Integer (see {@link ApplReqType})<br/>
     * Used: {@link ApplicationMessageRequestMsg},{@link ApplicationMessageRequestAckMsg}
     */
    ApplReqType                                         (1347),
    /**
     * Used to indicate the type of acknowledgment being sent.<br/>
     * Type: Integer (see {@link ApplResponseType})<br/>
     * Used: {@link ApplicationMessageRequestAckMsg}
     */
    ApplResponseType                                    (1348),
    /**
     * Total number of messages included in transmission.<br/>
     * Type: Integer<br/>
     * Used: {@link ApplicationMessageRequestAckMsg}
     */
    ApplTotalMessageCount                               (1349),
    /**
     * Application sequence number of last message in transmission.<br/>
     * Type: SeqNum<br/>
     * Used: {@link ApplicationSequenceControl}
     */
    ApplLastSeqNum                                      (1350),
    /**
     * Used to indicate that a message is being sent in response to an Application Message Request.
     * It is possible for both ApplResendFlag and PossDupFlag to be set on the same message if
     * the Sender's cache size is greater than zero and the message is being resent due to a session
     * level resend request.<br/>
     * Type: Boolean<br/>
     * Used: {@link ApplicationSequenceControl}
     */
    ApplResendFlag                                      (1352),
    /**
     * Refer to definition of {@link TagNum#PutOrCall} (201).<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegPutOrCall                                        (1358),
    /**
     * Byte length of encoded (non-ASCII characters) {@link TagNum#EncodedSymbol} (1360) field.<br/>
     * Type: Integer<br/>
     * Used: 
     */
    EncodedSymbolLen                                    (1359),
    /**
     * Encoded (non-ASCII characters) representation of the {@link TagNum#Symbol}(55) field in the encoded format specified
     * via the {@link TagNum#MessageEncoding}(347) field. If used, the ASCII (English) representation can also
     * be specified in the Symbol field.<br/>
     * Type: Data<br/>
     * Used:
     */
    EncodedSymbol                                       (1360),
    /**
     * Total number of fill entries across all messages. Should be the sum of all {@link TagNum#NoFills} (1362) in
     * each message that has repeating list of fill entries related to the same {@link TagNum#ExecID} (17).
     * Used to support fragmentation.<br/>
     * Type: Integer<br/>
     * Used: {@link ExecutionReportMsg}
     */
    TotNoFills                                          (1361),
    /**
     * No of filled groups.<br/>
     * Type: Integer<br/>
     * Used: {@link ExecutionReportMsg}
     */
    NoFills                                             (1362),
    /**
     * Refer to {@link TagNum#ExecID} (17). Used when multiple partial fills are reported in single
     * Execution Report. ExecID and FillExecID should not overlap.<br/>
     * Type: String<br/>
     * Used: {@link FillsGroup}
     */
    FillExecID                                          (1363),
    /**
     * Price of Fill. Refer to {@link TagNum#LastPx} (31).<br/>
     * Type: Decimal<br/>
     * Used: {@link FillsGroup}
     */
    FillPx                                              (1364),
    /**
     * Quantity of Fill. Refer to {@link TagNum#LastQty} (32).<br/>
     * Type: Decimal<br/>
     * Used: {@link FillsGroup}
     */
    FillQty                                             (1365),
    /**
     * The {@link TagNum#AllocID} (70) of an individual leg of a multileg order.<br/>
     * Type: String<br/>
     * Used: {@link InstrmtLegExecGroup}, {@link LegOrdGroup}
     */
    LegAllocID                                          (1366),
    /**
     * Identifies settlement currency for the leg level allocation.<br/>
     * Type: String (see {@link Currency}<br/>
     * Used: {@link LegPreAllocGroup}
     */
    LegAllocSettlCurrency                               (1367),
    /**
     * Identifies an event related to a {@link TagNum#TradSesStatus} (340). An event occurs and is gone,
     * it is not a state that applies for a period of time.<br/>
     * Type: Integer (see {@link TradSesEvent}<br/>
     * Used: {@link TradingSessionStatusMsg}
     */
    TradSesEvent                                        (1368),
    /**
     * Unique identifier of Order Mass Cancel Report or Order Mass Action Report message as assigned
     * by sell-side (broker, exchange, ECN).<br/>
     * Type: String<br/>
     * Used: {@link OrderMassActionReportMsg},  {@link OrderMassCancelReportMsg}
     */
    MassActionReportID                                  (1369),
    /**
     * Number of not affected orders in the repeating group of order ids.<br/>
     * Type: Integer<br/>
     * Used: {@link NotAffectedOrdGroup}
     */
    NoNotAffectedOrders                                 (1370),
    /**
     * {@link TagNum#OrderID}(37) of an order not affected by a mass cancel request.<br/>
     * Type: String<br/>
     * Used: {@link NotAffectedOrdGroup}
     */
    NotAffectedOrderID                                  (1371),
    /**
     * {@link TagNum#ClOrdID}(37) of the previous order (NOT the initial order of the day) as 
     * assigned by the institution, used to identify the previous order in cancel and cancel/replace requests.<br/>
     * Type: String<br/>
     * Used: {@link NotAffectedOrdGroup}
     */
    NotAffOrigClOrdID                                   (1372),
    /**
     * Specifies the type of multi-leg order.<br/>
     * Type: Integer ({@link MultilegModel})<br/>
     * Used: {@link MultilegOrderCancelReplaceMsg},{@link NewOrderMultilegMsg}
     */
    MultilegModel                                       (1377),
    /**
     * Code to represent how the multi-leg price is to be interpreted when applied to the legs.<br/>
     * Type: Integer ({@link MultilegPriceMethod})<br/>
     * Used: {@link MultilegOrderCancelReplaceMsg},{@link NewOrderMultilegMsg}
     */
    MultilegPriceMethod                                 (1378),
    /**
     * Specifies the volatility of an instrument leg.<br/>
     * Type: Decimal<br/>
     * Used: {@link InstrmtLegExecGroup},{@link LegOrdGroup},{@link TrdInstrmtLegGroup}
     */
    LegVolatility                                       (1379),
    /**
     * The continuously-compounded annualized dividend yield of the underlying(s) of an option.
     * Used as a parameter to theoretical option pricing models.<br/>
     * Type: Decimal<br/>
     * Used: {@link ExecutionReportMsg},{@link TradeCaptureReportMsg}
     */
    DividendYield                                       (1380),
    /**
     * Refer to definition for {@link TagNum#DividendYield} (1380).<br/>
     * Type: Decimal<br/>
     * Used: {@link InstrmtLegExecGroup},{@link LegOrdGroup},{@link TrdInstrmtLegGroup}
     */
    LegDividendYield                                    (1381),
    /**
     * Specifies the currency ratio between the currency used for a multileg price and the
     * currency used by the outright book defined by the leg.
     * Example: Multileg quoted in EUR, outright leg in USD and 1 EUR = 0,7 USD then CurrencyRatio = 0.7<br/>
     * Type: Decimal<br/>
     * Used: {@link TradeCaptureReportMsg}
     */
    CurrencyRatio                                       (1382),
    /**
     * Specifies the currency ratio between the currency used for a multileg price and the
     * currency used by the outright book defined by the leg.
     * Example: Multileg quoted in EUR, outright leg in USD and 1 EUR = 0,7 USD then LegCurrencyRatio = 0.7<br/>
     * Type: Decimal<br/>
     * Used: {@link InstrmtLegExecGroup},{@link LegOrdGroup},{@link TrdInstrmtLegGroup}
     */
    LegCurrencyRatio                                    (1383),
    /**
     * Specifies the currency ratio between the currency used for a multileg price and the
     * currency used by the outright book defined by the leg.
     * Example: Multileg quoted in EUR, outright leg in USD and 1 EUR = 0,7 USD then LegCurrencyRatio = 0.7<br/>
     * Type: MultipleCharValue (see {@link ExecInst} for values)<br/>
     * Used: {@link InstrmtLegExecGroup},{@link LegOrdGroup},{@link TrdInstrmtLegGroup}
     */
    LegExecInst                                         (1384),
    /**
     * Defines the type of contingency.<br/>
     * Type: Integer (see {@link ContingencyType} for values)<br/>
     * Used: {@link ListStatusMsg},{@link NewOrderListMsg}
     */
    ContingencyType                                     (1385),
    /**
     * Identifies the reason for rejection of a New Order List message.
     * Note that OrdRejReason (103) is used if the rejection is based on properties of an individual order part of the List.<br/>
     * Type: Integer (see {@link ListRejectReason} for values)<br/>
     * Used: {@link ListStatusMsg}
     */
    ListRejectReason                                    (1386),
    /**
     * Number of trade reporting indicators.<br/>
     * Type: Integer<br/>
     * Used: 
     */
    NoTrdRepIndicators                                  (1387),
    /**
     * Identifies the type of party for trade reporting. Same values as {@link TagNum#PartyRole} (452)<br/>
     * Type: Integer (see {@link PartyRole} for values)<br/>
     * Used:
     */
    TrdRepPartyRole                                     (1388),
    /**
     * Specifies whether the trade should be reported (or not) to parties of the provided {@link TagNum#TrdRepPartyRole} (1388). 
     * Used to override standard reporting behavior by the receiver of the trade report and thereby complements 
     * the {@link TagNum#PublTrdIndicator} (1390).<br/>
     * Type: Boolean<br/>
     * Used:
     */
    TrdRepIndicator                                     (1389),
    /**
     * Indicates if a trade should be reported via a market reporting service. The indicator governs all reporting 
     * services of the recipient. Replaces {@link TagNum#PublishTrdIndicator} (852).<br/>
     * Type: Integer (see {@link TradePublishIndicator}<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    TradePublishIndicator                               (1390),
    /**
     * Refer to definition of {@link TagNum#OptAttribute} (206).<br/>
     * Type: Character <br/>
     * Used: 
     */
    UnderlyingLegOptAttribute                           (1391),
    /**
     * Refer to definition of {@link TagNum#SecurityDesc} (107).<br/>
     * Type: String <br/>
     * Used: 
     */
    UnderlyingLegSecurityDesc                           (1392),
    /**
     * Unique ID of a Market Definition Request message.<br/>
     * Type: String <br/>
     * Used: {@link MarketDefinitionMsg},{@link MarketDefinitionRequestMag},{@link MarketDefinitionUpdateReportMsg}
     */
    MktReqID                                            (1393),
    /**
     * Market Definition message identifier.<br/>
     * Type: String <br/>
     * Used: {@link MarketDefinitionMsg},{@link MarketDefinitionUpdateReportMsg}
     */
    MarketReportID                                      (1394),
    /**
     * Enumeration defining the encryption method used to encrypt password fields.
     * At this time there are no encryption methods defined by FPL.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    EncryptedPasswordMethod                             (1400),
    /**
     * Length of the {@link TagNum#EncryptedPassword} (1402) field.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    EncryptedPasswordLen                                (1401),
    /**
     * Encrypted password - encrypted via the method specified in the field {@link TagNum#EncryptedPasswordMethod} (1400).<br/>
     * Type: Data<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    EncryptedPassword                                   (1402),
    /**
     * Length of the  field {@link TagNum#EncryptedNewPassword} (1404) field.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    EncryptedNewPasswordLen                             (1403),
    /**
     * Encrypted new password - encrypted via the method specified in the field {@link TagNum#EncryptedPasswordMethod} (1400).<br/>
     * Type: Data<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    EncryptedNewPassword                                (1404),
    /**
     * Time of security maturity expressed in local time with offset to UTC specified<br/>
     * Type: UTC time<br/>
     * Used: 
     */
    UnderlyingLegMaturityTime                           (1405),
    /**
     * The extension pack number associated with an application message.<br/>
     * Type: Integer<br/>
     * Used: {@link RejectMsg},{@link BusinessMessageRejecMsg}
     */
    RefApplExtID                                        (1406),
    /**
     * The extension pack number that is the default for a FIX session.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    DefaultApplExtID                                    (1407),
    /**
     * The default custom application version ID that is the default for a session.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.LogonMsg}
     */
    DefaultCstmApplVerID                                (1408),
    /**
     * Status of a FIX session.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.LogonMsg},{@link LogoutMsg}
     */
    SessionStatus                                       (1409),
    /**
     * Default version indicator for the message.<br/>
     * Type: Boolean<br/>
     * Used: {@link net.hades.fix.message.LogonMsg},{@link RejectMsg}
     */
    DefaultVerIndicator                                 (1410),
    /**
     * Refer to definition of {@link TagNum#PartySubIDType} (803)<br/>
     * Type: Integer (see {@link PartySubIDType})<br/>
     * Used: 
     */
    Nested4PartySubIDType                               (1411),
    /**
     * Refer to definition of {@link TagNum#PartySubID} (523)<br/>
     * Type: String<br/>
     * Used:
     */
    Nested4PartySubID                                   (1412),
    /**
     * Refer to definition of {@link TagNum#NoPartySubIDs} (802)<br/>
     * Type: Integer<br/>
     * Used:
     */
    NoNested4PartySubIDs                                (1413),
    /**
     * Refer to definition of {@link TagNum#NoPartyIDs} (453)<br/>
     * Type: Integer<br/>
     * Used:
     */
    NoNested4PartyIDs                                   (1414),
    /**
     * Refer to definition of {@link TagNum#PartyID} (448)<br/>
     * Type: String<br/>
     * Used:
     */
    Nested4PartyID                                      (1415),
    /**
     * Refer to definition of {@link TagNum#PartyIDSource} (447)<br/>
     * Type: Character (see {@link PartyIDSource})<br/>
     * Used:
     */
    Nested4PartyIDSource                                (1416),
    /**
     * Refer to definition of {@link TagNum#PartyRole} (452)<br/>
     * Type: Character (see {@link PartyRole})<br/>
     * Used:
     */
    Nested4PartyRole                                    (1417),
    /**
     * Fill quantity for the leg instrument.<br/>
     * Type: Decimal<br/>
     * Used:
     */
    LegLastQty                                          (1418),
    /**
     * Type of exercise of a derivatives security. Refer to {@link ExerciseStyle}.<br/>
     * Type: Integer<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingExerciseStyle                             (1419),
    /**
     *Type of exercise of a derivatives security<br/>
     * Valid values: 0 - European 1 - American 2 - Bermuda.<br/>
     * Type: Integer (ExerciseStyle)<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegExerciseStyle                                    (1420),
    /**
     * Refer to definition for {@link TagNum#PriceUnitOfMeasure} (1191).<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegPriceUnitOfMeasure                               (1421),
    /**
     * Refer to definition of {@link TagNum#PriceUnitOfMeasureQty} (1192).<br/>
     * Type: Quantity<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegPriceUnitOfMeasureQty                            (1422),
    /**
     * Refer to definition of {@link TagNum#UnitOfMeasureQty} (1147).<br/>
     * Type: Quantity<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingUnitOfMeasureQty                          (1423),
    /**
     * Refer to definition of {@link TagNum#PriceUnitOfMeasure} (1191).<br/>
     * Type: String<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingPriceUnitOfMeasure                        (1424),
    /**
     * Refer to definition of {@link TagNum#PriceUnitOfMeasure} (1192).<br/>
     * Type: Quantity<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingPriceUnitOfMeasureQty                     (1425),
    /**
     * Type of report.<br/>
     * Type: Integer (see {@link ApplReportType})<br/>
     * Used: {@link ApplicationMessageReportMsg}
     */
    ApplReportType                                      (1426),
    /**
     * When reporting trades, used to reference the identifier of the execution (ExecID) being reported if different
     * ExecIDs were assigned to each side of the trade.<br/>
     * Type: String<br/>
     * Used:
     */
    SideExecID                                          (1427),
    /**
     * Time lapsed from order entry until match, based on the unit of time specified in OrderDelayUnit. 
     * Default is seconds if OrderDelayUnit is not specified. Value = 0, indicates the aggressor
     * (the initiating side of the trade).<br/>
     * Type: Integer<br/>
     * Used:
     */
    OrderDelay                                          (1428),
    /**
     * Time unit in which the OrderDelay(1428) is expressed.<br/>
     * Type: Integer (see {@link OrderDelayUnit})<br/>
     * Used:
     */
    OrderDelayUnit                                      (1429),
    /**
     * Identifies the type of venue where a trade was executed.<br/>
     * Type: Character (see {@link VenueType})<br/>
     * Used: {@link TradeCaptureReportMsg},{@link TradeCaptureReportAckMsg}
     */
    VenueType                                           (1430),
    /**
     * The reason for updating the RefOrdID.<br/>
     * Type: Integer (see {@link RefOrdIDReason})<br/>
     * Used:
     */
    RefOrdIDReason                                      (1431),
     /**
     * The customer capacity for this trade at the time of the order/execution. Primarily used by futures 
     * exchanges to indicate the CTICode (customer type indicator) as required by the 
     * US CFTC (Commodity Futures Trading Commission).<br/>
     * Type: Integer (see {@link OrigCustOrderCapacity})<br/>
     * Used:
     */
    OrigCustOrderCapacity                               (1432),
     /**
     * Used to reference a previously submitted {@link TagNum#ApplReqID} (1346) from within a subsequent 
     * {@link ApplicationMessageRequestMsg}.<br/>
     * Type: String<br/>
     * Used:
     */
    RefApplReqID                                        (1433),
    /**
     * Type of pricing model used.<br/>
     * Type: Type: Integer (see {@link ModelType})<br/>
     * Used: {@link PositionReportMsg}
     */
    ModelType                                           (1434),
    /**
     * Indicates the type of multiplier being applied to the contract.
     * Can be optionally used to further define what unit {@link TagNum#ContractMultiplier} (tag 231)
     * is expressed in.<br/>
     * Type: Integer (see {@link ContractMultiplierUnit})<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    ContractMultiplierUnit                              (1435),
    /**
     * Indicates the type of multiplier being applied to the contract.
     * Can be optionally used to further define what unit {@link TagNum#LegContractMultiplier}
     * (tag 614) is expressed in.<br/>
     * Type: Integer (see {@link ContractMultiplierUnit})<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    LegContractMultiplierUnit                           (1436),
    /**
     * Indicates the type of multiplier being applied to the contract.
     * Can be optionally used to further define what unit {@link TagNum#UndlyContractMultiplier}(tag 436)
     * is expressed in.<br/>
     * Type: Integer (see {@link ContractMultiplierUnit})<<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingContractMultiplierUnit                    (1437),
    /**
     * Indicates the type of multiplier being applied to the contract. 
     * Can be optionally used to further define what unit 
     * DerivativeContractMultiplier(tag 1266)is expressed in.<br/>
     * Type: Integer (see {@link ContractMultiplierUnit})<<br/>
     * Used: {@link UnderlyingInstrument}
     */
    DerivativeContractMultiplierUnit                    (1438),
    /**
     * The industry standard flow schedule by which electricity or natural gas is traded.
     * Schedules exist by regions and on-peak and off-peak status, such as "Western Peak".<br/>
     * Type: Integer (see {@link FlowScheduleType} for standard values)<<br/>
     * Used: {@link UnderlyingInstrument}
     */
    FlowScheduleType                                    (1439),
    /**
     * The industry standard flow schedule by which electricity or natural gas is traded.
     * Schedules exist by regions and on-peak and off-peak status, such as "Western Peak".<br/>
     * Type: Integer (see {@link FlowScheduleType} for standard values)<br/>
     * Used: {@link net.hades.fix.message.comp.InstrumentLeg}
     */
    LegFlowScheduleType                                 (1440),
    /**
     * The industry standard flow schedule by which electricity or natural gas is traded.
     * Schedules exist by regions and on-peak and off-peak status, such as "Western Peak".<br/>
     * Type: Integer (see {@link FlowScheduleType})<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingFlowScheduleType                          (1441),
    /**
     * The industry standard flow schedule by which electricity or natural gas is traded.
     * Schedules exist by regions and on-peak and off-peak status, such as "Western Peak".<br/>
     * Type: Integer (see {@link DerivativeFlowScheduleType})<br/>
     * Used: {@link DerivativeInstrument}
     */
    DerivativeFlowScheduleType                          (1442),
    /**
     * Indicator to identify whether this fill was a result of a liquidity provider
     * providing or liquidity taker taking the liquidity. Applicable only for OrdStatus of Partial or Filled.<br/>
     * Type: Integer (see {@link FillLiquidityInd})<br/>
     * Used: {@link FillGroup}
     */
    FillLiquidityInd                                    (1443),
    /**
     * Indicator to identify whether this fill was a result of a liquidity provider providing or liquidity
     * taker taking the liquidity. Applicable only for OrdStatus of Partial or Filled.<br/>
     * Type: Integer (see {@link FillLiquidityInd})<br/>
     * Used: 
     */
    SideLiquidityInd                                    (1444),
    /**
     * Number of rate sources being specified.<br/>
     * Type: Integer<br/>
     * Used: {@link RateSources}
     */
    NoRateSources                                       (1445),
    /**
     * Identifies the source of rate information. For FX, the reference source to be used for
     * the FX spot rate.<br/>
     * Type: Integer (see {@link RateSource})<br/>
     * Used: {@link RateSources}
     */
    RateSource                                          (1446),
    /**
     * Indicates whether the rate source specified is a primary or secondary source.<br/>
     * Type: Integer (see {@link RateSourceType})<br/>
     * Used: {@link RateSources}
     */
    RateSourceType                                      (1447),
    /**
     * Identifies the reference "page" from the rate source.
     * For FX, the reference page to the spot rate to be used for the reference FX spot rate. <br/>
     * Type: String<br/>
     * Used: {@link RateSources}
     */
    ReferencePage                                       (1448),
    /**
     * A category of CDS credit even in which the underlying bond experiences a restructuring.
     * Used to define a CDS instrument.<br/>
     * Type: String(see {@link RestructuringType})<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    RestructuringType                                   (1449),
    /**
     * Specifies which issue (underlying bond) will receive payment priority in the event of a default.
     * Used to define a CDS instrument.<br/>
     * Type: String(see {@link Seniority})<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    Seniority                                           (1450),
    /**
     * Indicates the notional percentage of the deal that is still outstanding based on the remaining
     * components of the index. Used to calculate the true value of a CDS trade or position. <br/>
     * Type: Decimal<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    NotionalPercentageOutstanding                       (1451),
    /**
     * Used to reflect the Original value prior to the application of a credit event.
     * See {@link TagNum#NotionalPercentageOutstanding} (1451).<br/>
     * Type: Decimal<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    OriginalNotionalPercentageOutstanding               (1452),
    /**
     * See {@link TagNum#RestructuringType}(1449).<br/>
     * Type: String(see {@link RestructuringType})<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingRestructuringType                         (1453),
    /**
     * See {@link TagNum#Seniority}(1450).
     * Used to define a CDS instrument.<br/>
     * Type: String (see {@link Seniority})<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingSeniority                                 (1454),
    /**
     * See {@link TagNum#NotionalPercentageOutstanding} (1451).<br/>
     * Type: Decimal<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingNotionalPercentageOutstanding             (1455),
    /**
     * See {@link TagNum#OriginalNotionalPercentageOutstanding} (1452).<br/>
     * Type: Decimal<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingOriginalNotionalPercentageOutstanding     (1456),
    /**
     * Lower bound percentage of the loss that the tranche can endure.<br/>
     * Type:Decimal<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    AttachmentPoint                                     (1457),
    /**
     * Upper bound percentage of the loss the tranche can endure.<br/>
     * Type: Decimal<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    DetachmentPoint                                     (1458),
    /**
     * See {@link TagNum#AttachmentPoint} (1457).<br/>
     * Type: Decimal<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingAttachmentPoint                           (1459),
    /**
     * See {@link TagNum#DetachmentPoint} (1458).<br/>
     * Type: Decimal<br/>
     * Used: {@link UnderlyingInstrument}
     */
    UnderlyingDetachmentPoint                           (1460),
    /**
     * Identifies the number of target parties identified in a mass action.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.TargetParties}
     */
    NoTargetPartyIDs                                    (1461),
    /**
     * PartyID value within an target party repeating group.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.comp.TargetParties}
     */
    TargetPartyID                                       (1462),
    /**
     * PartyIDSource value within an target party repeating group.
     * Same values as {@link TagNum#PartyIDSource} (447)<br/>
     * Type: Character (see {@link PartyIDSource})<br/>
     * Used: {@link net.hades.fix.message.comp.TargetParties}
     */
    TargetPartyIDSource                                 (1463),
    /**
     * PartyRole value within an target party repeating group.
     * Same values as {@link TagNum#PartyRole} (452)<br/>
     * Type: Character (see {@link PartyRole})<br/>
     * Used: {@link net.hades.fix.message.comp.TargetParties}
     */
    TargetPartyRole                                     (1464),
    /**
     * Specifies an identifier for a Security List.<br/>
     * Type: String<br/>
     * Used: {@link SecurityListMsg}, {@link SecurityListRequestMsg}, {@link SecurityListUpdateReportMsg}
     */
    SecurityListID                                      (1465),
    /**
     * Specifies a reference from one Security List to another. Used to support a hierarchy of Security Lists.<br/>
     * Type: String<br/>
     * Used: {@link SecurityListMsg}, {@link SecurityListUpdateReportMsg}
     */
    SecurityListRefID                                   (1466),
    /**
     * Specifies a description or name of a Security List.<br/>
     * Type: String<br/>
     * Used: {@link SecurityListMsg}, {@link SecurityListUpdateReportMsg}
     */
    SecurityListDesc                                    (1467),
    /**
     * Byte length of encoded (non-ASCII characters) {@link TagNum#EncodedSecurityListDesc} field.<br/>
     * Type: Integer<br/>
     * Used: {@link SecurityListMsg}, {@link SecurityListUpdateReportMsg}
     */
    EncodedSecurityListDescLen                          (1468),
    /**
     * Encoded (non-ASCII characters) representation of the {@link TagNum#SecurityListDesc} (1467) field
     * in the encoded format specified via the MessageEncoding (347) field.
     * If used, the ASCII (English) representation should also be specified in the SecurityListDesc field.<br/>
     * Type: Data<br/>
     * Used: {@link SecurityListMsg}, {@link SecurityListUpdateReportMsg}
     */
    EncodedSecurityListDesc                             (1469),
    /**
     * Specifies a type of Security List.<br/>
     * Type: Integer (see {@link SecurityListType})<br/>
     * Used: {@link SecurityListMsg}, {@link SecurityListRequestMsg}, {@link SecurityListUpdateReportMsg}
     */
    SecurityListType                                    (1470),
    /**
     * Specifies a specific source for a SecurityListType. Relevant when a certain type can be provided from various sources.<br/>
     * Type: Integer (see {@link SecurityListTypeSource})<br/>
     * Used: {@link SecurityListMsg}, {@link SecurityListRequestMsg}, {@link SecurityListUpdateReportMsg}
     */
    SecurityListTypeSource	                        (1471),
    /**
     * Unique identifier for a News message.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.NewsMsg}
     */
    NewsID                                              (1472),
    /**
     * Category of news mesage. Values "100" and above are reserved
     * for bilaterally agreed upon user defined enumerations. <br/>
     * Type: Integer (See {@link NewsCategory}) for standard values<br/>
     * Used: {@link net.hades.fix.message.NewsMsg}
     */
    NewsCategory                                        (1473),
    /**
     * The national language in which the news item is provided.<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.NewsMsg}
     */
    LanguageCode                                        (1474),
    /**
     * Number of News reference items.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.group.NewsRefGroup}
     */
    NoNewsRefIDs                                        (1475),
    /**
     * Reference to another News message identified by NewsID(1474).<br/>
     * Type: String<br/>
     * Used: {@link net.hades.fix.message.group.NewsRefGroup}
     */
    NewsRefID                                           (1476),
    /**
     * Reference to another News message identified by NewsID(1474).<br/>
     * Values "100" and above are reserved for bilaterally agreed upon user defined enumerations.<br/>
     * Type: Integer (see {@link NewsRefType} for standard values)<br/>
     * Used: {@link net.hades.fix.message.group.NewsRefGroup}
     */
    NewsRefType                                         (1477),
    /**
     * Specifies how the strike price is determined at the point of option exercise.
     * The strike may be fixed throughout the life of the option, set at expiration to the value of the
     * underlying, set to the average value of the underlying , or set to the optimal value of the underlying.<br/>
     * Conditionally, required if value is other than "fixed".<br/>
     * Type: Integer(see {@link StrikePriceDeterminationMethod} for standard values)<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    StrikePriceDeterminationMethod                      (1478),
    /**
     * Specifies the boundary condition to be used for the strike price relative to the underlying
     * price at the point of option exercise.
     * Type: Integer(see {@link StrikePriceBoundaryMethod})<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    StrikePriceBoundaryMethod                           (1479),
    /**
     * Used in combination with StrikePriceBoundaryMethod to specify the percentage of the
     * strike price in relation to the underlying price. The percentage is generally 100 or
     * greater for puts and 100 or less for calls.<br/>
     * Type: Decimal<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    StrikePriceBoundaryPrecision                        (1480),
    /**
     * Specifies how the underlying price is determined at the point of option exercise.
     * The underlying price may be set to the current settlement price, set to a special
     * reference, set to the optimal value of the underlying during the defined period
     * {Look-back) or set to the average value of the underlying during the defined
     * period (Asian option).<br/>
     * Type: Integer(see {@link UnderlyingPriceDeterminationMethod})<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    UnderlyingPriceDeterminationMethod                  (1481),
    /**
     * Indicates the type of payout that will result from an in-the-money option.<br/>
     * Type: Integer(see {@link OptPayoutType})<br/>
     * Used: {@link net.hades.fix.message.comp.Instrument}
     */
    OptPayoutType                                       (1482),
    /**
     * Identifies the type of complex event.<br/>
     * Type: Integer(see {@link ComplexEventType})<br/>
     * Used: {@link net.hades.fix.message.comp.ComplexEvents}
     */
    ComplexEventType                                    (1484),
    /**
     * Cash amount indicating the pay out associated with an event. For binary options this is a fixed amount.<br/>
     * Type: Decimal<br/>
     * Used: {@link net.hades.fix.message.comp.ComplexEvents}
     */
    ComplexOptPayoutAmount                              (1485),
    /**
     * Specifies the price at which the complex event takes effect.
     * Impact of the event price is determined by the {@link TagNum#ComplexEventType} (1484).<br/>
     * Type: Decimal<br/>
     * Used: {@link net.hades.fix.message.comp.ComplexEvents}
     */
    ComplexEventPrice                                   (1486),
    /**
     * Specifies the boundary condition to be used for the event price relative to the underlying price
     * at the point the complex event outcome takes effect as determined by the
     * ComplexEventPriceTimeType.<br/>
     * Type: Integer(see {@link ComplexEventPriceBoundaryMethod})<br/>
     * Used: {@link net.hades.fix.message.comp.ComplexEvents}
     */
    ComplexEventPriceBoundaryMethod                     (1487),
    /**
     * Used in combination with ComplexEventPriceBoundaryMethod to specify the percentage
     * of the strike price in relation to the underlying price.
     * The percentage is generally 100 or greater for puts and 100 or less for calls.<br/>
     * Type: Decimal<br/>
     * Used: {@link net.hades.fix.message.comp.ComplexEvents}
     */
    ComplexEventPriceBoundaryPrecision                  (1488),
    /**
     * Specifies when the complex event outcome takes effect. The outcome of a complex
     * event is a payout or barrier action as specified by the ComplexEventType.<br/>
     * Type: Integer(see {@link ComplexEventPriceTimeType})<br/>
     * Used: {@link net.hades.fix.message.comp.ComplexEvents}
     */
    ComplexEventPriceTimeType                           (1489),
    /**
     * Specifies the condition between complex events when more than one event is specified.
     * Multiple barrier events would use an "or" condition since only one can be effective
     * at a given time. A set of digital range events would use an "and" condition since
     * both conditions must be in effect for a payout to result.<br/>
     * Type: Integer(see {@link ComplexEventCondition})<br/>
     * Used: {@link net.hades.fix.message.comp.ComplexEvents}
     */
    ComplexEventCondition                               (1490),
    /**
     * Number of complex event date occurrences for a given complex event.<br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.ComplexEvents}
     */
    NoComplexEventDates                                 (1491),
    /**
     * Specifies the start date of the date range on which a complex event is effective.
     * The start date will be set equal to the end date for single day events such as Bermuda options
     * ComplexEventStartDate must always be less than or equal to ComplexEventEndDate.<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link net.hades.fix.message.comp.ComplexEvents}
     */
    ComplexEventStartDate                               (1492),
    /**
     * Specifies the end date of the date range on which a complex event is effective.
     * The start date will be set equal to the end date for single day events such as Bermuda options
     * ComplexEventEndDate must always be greater than or equal to ComplexEventStartDate.<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link net.hades.fix.message.comp.ComplexEvents}
     */
    ComplexEventEndDate                                 (1493),
    /**
     * Number of complex event time occurrences for a given complex event date.
     * The default in case of an absence of time fields is 00:00:00-23:59:59. <br/>
     * Type: Integer<br/>
     * Used: {@link net.hades.fix.message.comp.ComplexEvents}
     */
    NoComplexEventTimes                                 (1494),
    /**
     * Specifies the start time of the time range on which a complex event date is effective.
     * ComplexEventStartTime must always be less than or equal to ComplexEventEndTime.<br/>
     * Type: UTCTimeOnly<br/>
     * Used: {@link net.hades.fix.message.comp.ComplexEvents}
     */
    ComplexEventStartTime                               (1495),
    /**
     * Specifies the end time of the time range on which a complex event date is effective.
     * ComplexEventEndTime must always be greater than or equal to ComplexEventStartTime. <br/>
     * Type: UTCTimeOnly<br/>
     * Used: {@link net.hades.fix.message.comp.ComplexEvents}
     */
    ComplexEventEndTime                                 (1496),
    /**
     * The identifier or name of the price stream.<br/>
     * Type: String<br/>
     * Used: {@link MDRequestGroup}
     */
    MDStreamID                                          (1500),
    /**
     * Unique identifier of the stream assignment report provided by the respondent.<br/>
     * Type: String<br/>
     * Used: {@link StreamAssignmentReportMsg}, {@link StreamAssignmentReportAckMsg}
     */
    StreamAsgnRptID                                     (1501),
    /**
     * Reason code for stream assignment request reject.<br/>
     * Type: Integer (see {@link StreamAsgnRejReason})<br/>
     * Used: {@link StreamAssignmentReportAckMsg}, {@link StrmAsgnRptInstrmtGroup}
     */
    StreamAsgnRejReason                                 (1502),
    /**
     * Type of acknowledgment.<br/>
     * Type: Integer (see {@link StreamAsgnAckType})<br/>
     * Used: {@link StreamAssignmentReportAckMsg}
     */
    StreamAsgnAckType                                   (1503),
    /**
     * See {@link TagNum#TransactTime}.<br/>
     * Type: UTCTimestamp<br/>
     * Used: {@link RelSymDerivSecGroup}, {@link RelSymDerivSecUpdGroup}, {@link SecListGroup},{@link SecLstUpdRelSymGroup}
     */
    RelSymTransactTime                                  (1504);

    private static final long serialVersionUID = 1L;

    protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    
    private int value;

    private static final Map<Integer, TagNum> stringToEnum = new HashMap<Integer, TagNum>();

    static {
        for (TagNum tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of TagNum */
    TagNum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static TagNum fromString(Integer value) {
        return stringToEnum.get(value);
    }

    public byte[] asBytes() throws BadFormatMsgException {
        byte[] result =  null;
        try {
            result = String.valueOf(value).getBytes(FIXMsg.DEFAULT_CHARACTER_SET);
        } catch (UnsupportedEncodingException ex) {
            String error = "Could not encode TagNum field.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }
}
