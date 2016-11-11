/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecInstValue.java
 *
 * $Id: ExecInstValue.java,v 1.1 2011-04-17 09:30:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Instruction for executing a trade.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 29/06/2008, 18:05:52
 */
@XmlType
@XmlEnum(String.class)
public enum ExecInstValue {

    @XmlEnumValue("0") StayOnOfferSide                          ('0'),
    @XmlEnumValue("1") NotHeld                                  ('1'),
    @XmlEnumValue("2") Work                                     ('2'),
    @XmlEnumValue("3") GoAlong                                  ('3'),
    @XmlEnumValue("4") OverTheDay                               ('4'),
    @XmlEnumValue("5") Held                                     ('5'),
    @XmlEnumValue("6") Participate                              ('6'),
    @XmlEnumValue("7") StrictScale                              ('7'),
    @XmlEnumValue("8") TryToScale                               ('8'),
    @XmlEnumValue("9") StayOnBidside                            ('9'),
    @XmlEnumValue("0") StayOnOfferside                          ('0'),
    @XmlEnumValue("A") NoCross                                  ('A'),
    @XmlEnumValue("B") OKToCross                                ('B'),
    @XmlEnumValue("C") CallFirst                                ('C'),
    @XmlEnumValue("D") VolumePct                                ('D'),
    @XmlEnumValue("E") DNI                                      ('E'),
    @XmlEnumValue("F") DNR                                      ('F'),
    @XmlEnumValue("G") AON                                      ('G'),
    @XmlEnumValue("H") ReinstateOnSysFailure                    ('H'),
    @XmlEnumValue("I") InstitutionsOnly                         ('I'),
    @XmlEnumValue("J") ReinstateOnTradingHalt                   ('J'),
    @XmlEnumValue("K") CancelOnTradingHalt                      ('K'),
    @XmlEnumValue("L") LastPeg                                  ('L'),
    @XmlEnumValue("M") MidPricePeg                              ('M'),
    @XmlEnumValue("N") NonNegotiable                            ('N'),
    @XmlEnumValue("O") OpeningPeg                               ('O'),
    @XmlEnumValue("P") MarketPeg                                ('P'),
    @XmlEnumValue("Q") CancelOnSysFailure                       ('Q'),
    @XmlEnumValue("R") PrimaryPeg                               ('R'),
    @XmlEnumValue("S") Suspend                                  ('S'),
    @XmlEnumValue("T") FixedPeg                                 ('T'),
    @XmlEnumValue("U") CustDisplayInstr                         ('U'),
    @XmlEnumValue("V") Netting                                  ('V'),
    @XmlEnumValue("W") PegToVWAP                                ('W'),
    @XmlEnumValue("X") TradeAlong                               ('X'),
    @XmlEnumValue("Y") TryToStop                                ('Y'),
    @XmlEnumValue("Z") CancelIfNotBest                          ('Z'),
    @XmlEnumValue("a") TrailingStopPeg                          ('a'),
    @XmlEnumValue("b") StrictLimit                              ('b'),
    @XmlEnumValue("c") IgnorePxValidChecks                      ('c'),
    @XmlEnumValue("d") PegToLimitPrice                          ('d'),
    @XmlEnumValue("e") WorkToTargetStrategy                     ('e'),
    @XmlEnumValue("f") IntermarketSweep                         ('f'),
    @XmlEnumValue("g") ExternalRoutingAllowed                   ('g'),
    @XmlEnumValue("h") ExternalRoutingNotAllowed                ('h'),
    @XmlEnumValue("i") ImbalanceOnly                            ('i'),
    @XmlEnumValue("j") SingleExecReqForBlockTrade               ('j'),
    @XmlEnumValue("k") BestExecution                            ('k'),
    @XmlEnumValue("l") SuspendOnSysFailure                      ('l'),
    @XmlEnumValue("m") SuspendOnTradingHalt                     ('m'),
    @XmlEnumValue("n") ReinstateOnConnectionLoss                ('n'),
    @XmlEnumValue("o") CancelOnConnectionLoss                   ('o'),
    @XmlEnumValue("p") SuspendOnConnectionLoss                  ('p'),
    @XmlEnumValue("q") ReleaseFromSuspension                    ('q'),
    @XmlEnumValue("r") ExecAsDeltaNeutral                       ('r'),
    @XmlEnumValue("s") ExecAsDurationNeutral                    ('s'),
    @XmlEnumValue("t") ExecuteAsFXNeutral                       ('t') ;

    private static final long serialVersionUID = 1L;
    
    private char value;

    private static final Map<String, ExecInstValue> stringToEnum = new HashMap<String, ExecInstValue>();

    static {
        for (ExecInstValue tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    
    /** Creates a new instance of ExecInstValue */
    ExecInstValue(char value) {
        this.value = value;
    }
    
    public char getValue() {
        return value;
    }
    
    public static ExecInstValue valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
