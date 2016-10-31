/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Types of FIX messages.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlType
@XmlEnum(String.class)
public enum MsgType {
    
    @XmlEnumValue("0")  Heartbeat                           ("0"),
    @XmlEnumValue("1")  TestRequest                         ("1"),
    @XmlEnumValue("2")  ResendRequest                       ("2"),
    @XmlEnumValue("3")  Reject                              ("3"),
    @XmlEnumValue("4")  SequenceReset                       ("4"),
    @XmlEnumValue("5")  Logout                              ("5"),
    @XmlEnumValue("6")  IndicationOfInterest                ("6"),
    @XmlEnumValue("7")  Advertisement                       ("7"),
    @XmlEnumValue("8")  ExecutionReport                     ("8"),
    @XmlEnumValue("9")  OrderCancelReject                   ("9"),
    @XmlEnumValue("A")  Logon                               ("A"),
    @XmlEnumValue("B")  News                                ("B"),
    @XmlEnumValue("C")  Email                               ("C"),
    @XmlEnumValue("D")  NewOrderSingle                      ("D"),
    @XmlEnumValue("E")  NewOrderList                        ("E"),
    @XmlEnumValue("F")  OrderCancelRequest                  ("F"),
    @XmlEnumValue("G")  OrderModificationRequest            ("G"),
    @XmlEnumValue("H")  OrderStatusRequest                  ("H"),
    @XmlEnumValue("J")  Allocation                          ("J"),
    @XmlEnumValue("K")  ListCancelRequest                   ("K"),
    @XmlEnumValue("L")  ListExecute                         ("L"),
    @XmlEnumValue("M")  ListStatusRequest                   ("M"),
    @XmlEnumValue("N")  ListStatus                          ("N"),
    @XmlEnumValue("P")  AllocationAck                       ("P"),
    @XmlEnumValue("Q")  DontKnowTrade                       ("Q"),
    @XmlEnumValue("R")  QuoteRequest                        ("R"),
    @XmlEnumValue("S")  Quote                               ("S"),
    @XmlEnumValue("T")  SettlInstructions                   ("T"),
    @XmlEnumValue("V")  MarketDataRequest                   ("V"),
    @XmlEnumValue("W")  MarketDataSnapshot                  ("W"),
    @XmlEnumValue("X")  MarketDataIncrRefresh               ("X"),
    @XmlEnumValue("Y")  MarketDataRequestReject             ("Y"),
    @XmlEnumValue("Z")  QuoteCancel                         ("Z"),
    @XmlEnumValue("a")  QuoteStatusRequest                  ("a"),
    @XmlEnumValue("b")  MassQuoteAck                        ("b"),
    @XmlEnumValue("c")  SecurityDefinitionRequest           ("c"),
    @XmlEnumValue("d")  SecurityDefinition                  ("d"),
    @XmlEnumValue("e")  SecurityStatusRequest               ("e"),
    @XmlEnumValue("f")  SecurityStatus                      ("f"),
    @XmlEnumValue("g")  TradingSessionStatusRequest         ("g"),
    @XmlEnumValue("h")  TradingSessionStatus                ("h"),
    @XmlEnumValue("i")  MassQuote                           ("i"),
    @XmlEnumValue("j")  BusinessMessageReject               ("j"),
    @XmlEnumValue("k")  BidRequest                          ("k"),
    @XmlEnumValue("l")  BidResponse                         ("l"),
    @XmlEnumValue("m")  ListStrikePrice                     ("m"),
    @XmlEnumValue("o")  RegistrationInstructions            ("o"),
    @XmlEnumValue("p")  RegistrationInstructionsResponse    ("p"),
    @XmlEnumValue("q")  OrderMassCancelRequest              ("q"),
    @XmlEnumValue("r")  OrderMassCancelReport               ("r"),
    @XmlEnumValue("s")  NewOrderCross                       ("s"),
    @XmlEnumValue("t")  CrossOrderModificationRequest       ("t"),
    @XmlEnumValue("u")  CrossOrderCancelRequest             ("u"),
    @XmlEnumValue("v")  SecurityTypeRequest                 ("v"),
    @XmlEnumValue("w")  SecurityTypes                       ("w"),
    @XmlEnumValue("x")  SecurityListRequest                 ("x"),
    @XmlEnumValue("y")  SecurityList                        ("y"),
    @XmlEnumValue("z")  DerivativeSecurityListRequest       ("z"),
    @XmlEnumValue("AA") DerivativeSecurityList              ("AA"),
    @XmlEnumValue("AB") NewOrderMultileg                    ("AB"),
    @XmlEnumValue("AC") MultilegModificationRequest         ("AC"),
    @XmlEnumValue("AD") TradeCaptureReportRequest           ("AD"),
    @XmlEnumValue("AE") TradeCaptureReport                  ("AE"),
    @XmlEnumValue("AF") OrderMassStatusRequest              ("AF"),
    @XmlEnumValue("AG") QuoteRequestReject                  ("AG"),
    @XmlEnumValue("AH") RFQRequest                          ("AH"),
    @XmlEnumValue("AI") QuoteStatusReport                   ("AI"),
    @XmlEnumValue("AJ") QuoteResponse                       ("AJ"),
    @XmlEnumValue("AK") Confirmation                        ("AK"),
    @XmlEnumValue("AL") PositionMaintenanceRequest          ("AL"),
    @XmlEnumValue("AM") PositionMaintenanceReport           ("AM"),
    @XmlEnumValue("AN") RequestForPositions                 ("AN"),
    @XmlEnumValue("AO") RequestForPositionsAck              ("AO"),
    @XmlEnumValue("AP") PositionReport                      ("AP"),
    @XmlEnumValue("AQ") TradeCaptureReportRequestAck        ("AQ"),
    @XmlEnumValue("AR") TradeCaptureReportAck               ("AR"),
    @XmlEnumValue("AS") AllocationReport                    ("AS"),
    @XmlEnumValue("AT") AllocationReportAck                 ("AT"),
    @XmlEnumValue("AU") ConfirmationAck                     ("AU"),
    @XmlEnumValue("AV") SettlementInstructionRequest        ("AV"),
    @XmlEnumValue("AW") AssignmentReport                    ("AW"),
    @XmlEnumValue("AX") CollateralRequest                   ("AX"),
    @XmlEnumValue("AY") CollateralAssignment                ("AY"),
    @XmlEnumValue("AZ") CollateralResponse                  ("AZ"),
    @XmlEnumValue("BA") CollateralReport                    ("BA"),
    @XmlEnumValue("BB") CollateralInquiry                   ("BB"),
    @XmlEnumValue("BC") NetworkCptySystemStatusRequest      ("BC"),
    @XmlEnumValue("BD") NetworkCptySystemStatusResponse     ("BD"),
    @XmlEnumValue("BE") UserRequest                         ("BE"),
    @XmlEnumValue("BF") UserResponse                        ("BF"),
    @XmlEnumValue("BG") CollateralInquiryAck                ("BG"),
    @XmlEnumValue("BH") ConfirmationRequest                 ("BH"),
    @XmlEnumValue("BI") TradingSessionListRequest           ("BI"),
    @XmlEnumValue("BJ") TradingSessionList                  ("BJ"),
    @XmlEnumValue("BK") SecurityListUpdateReport            ("BK"),
    @XmlEnumValue("BL") AdjustedPositionReport              ("BL"),
    @XmlEnumValue("BM") AllocationInstructionAlert          ("BM"),
    @XmlEnumValue("BN") ExecutionAcknowledgement            ("BN"),
    @XmlEnumValue("BO") ContraryIntentionReport             ("BO"),
    @XmlEnumValue("BP") SecurityDefinitionUpdateReport      ("BP"),
    @XmlEnumValue("BQ") SettlementObligationReport          ("BQ"),
    @XmlEnumValue("BR") DerivativeSecurityListUpdateReport  ("BR"),
    @XmlEnumValue("BS") TradingSessionListUpdateReport      ("BS"),
    @XmlEnumValue("BT") MarketDefinitionRequest             ("BT"),
    @XmlEnumValue("BU") MarketDefinition                    ("BU"),
    @XmlEnumValue("BV") MarketDefinitionUpdateReport        ("BV"),
    @XmlEnumValue("BW") ApplicationMessageRequest           ("BW"),
    @XmlEnumValue("BX") ApplicationMessageRequestAck        ("BX"),
    @XmlEnumValue("BY") ApplicationMessageReport            ("BY"),
    @XmlEnumValue("BZ") OrderMassActionReport               ("BZ"),
    @XmlEnumValue("CA") OrderMassActionRequest              ("CA"),
    @XmlEnumValue("CB") UserNotification                    ("CB"),
    @XmlEnumValue("CC") StreamAssignmentRequest             ("CC"),
    @XmlEnumValue("CD") StreamAssignmentReport              ("CD"),
    @XmlEnumValue("CE") StreamAssignmentReportACK           ("CE"),
    @XmlEnumValue("CF") PartyDetailsListRequest             ("CF"),
    @XmlEnumValue("CG") PartyDetailsListReport              ("CG"),
    @XmlEnumValue("ZY") BatchSet                            ("ZY"),
    @XmlEnumValue("ZZ") CustomMessage                       ("ZZ");

    private static final long serialVersionUID = 1L;
    
    private final String value;

    private static final Map<String, MsgType> stringToEnum = new HashMap<>();

    static {
        for (MsgType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of MsgType */
    MsgType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public static MsgType valueFor(String value) {
        MsgType result = stringToEnum.get(value);
        if (result == null) {
            result = MsgType.CustomMessage;
        }
        return result;
    }

    public static String displayName(String msgTypeStr) {
        String result = msgTypeStr;
        MsgType msgType = valueFor(msgTypeStr);
        if (msgType != null) {
            result = msgType.name();
        }
        
        return result;
    }
}
