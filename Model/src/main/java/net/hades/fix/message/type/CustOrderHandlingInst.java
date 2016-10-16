/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Seniority.java
 *
 * $Id: CustOrderHandlingInst.java,v 1.1 2010-12-05 08:13:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Codes that apply special information that the Broker / Dealer needs to report.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/06/2009, 9:17:06 AM
 */
@XmlType
@XmlEnum(String.class)
public enum CustOrderHandlingInst {

    @XmlEnumValue("ADD") AddOnOrder                 ("ADD"),
    @XmlEnumValue("AON") AllOrNone                  ("AON"),
    @XmlEnumValue("CNH") CashNotHeld                ("CNH"),
    @XmlEnumValue("DIR") DirectedOrder              ("DIR"),
    @XmlEnumValue("E.W") ExchPhysTx                 ("E.W"),
    @XmlEnumValue("FOK") FillOrKill                 ("FOK"),
    @XmlEnumValue("IO")  ImbalanceOnly              ("IO"),
    @XmlEnumValue("IOC") ImmediateOrCancel          ("IOC"),
    @XmlEnumValue("LOO") LimitOnOpen                ("LOO"),
    @XmlEnumValue("LOC") LimitOnClose               ("LOC"),
    @XmlEnumValue("MAO") MarketAtOpen               ("MAO"),
    @XmlEnumValue("MAC") MarketAtClose              ("MAC"),
    @XmlEnumValue("MOO") MarketOnOpen               ("MOO"),
    @XmlEnumValue("MOC") MarketOnClose              ("MOC"),
    @XmlEnumValue("MQT") MinimumQuantity            ("MQT"),
    @XmlEnumValue("NH")  NotHeld                    ("NH"),
    @XmlEnumValue("OVD") OverTheDay                 ("OVD"),
    @XmlEnumValue("PEG") Pegged                     ("PEG"),
    @XmlEnumValue("RSV") ReserveSizeOrder           ("RSV"),
    @XmlEnumValue("S.W") StopStockTransaction       ("S.W"),
    @XmlEnumValue("SCL") Scale                      ("SCL"),
    @XmlEnumValue("TMO") TimeOrder                  ("TMO"),
    @XmlEnumValue("WRK") Work                       ("WRK");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, CustOrderHandlingInst> stringToEnum = new HashMap<String, CustOrderHandlingInst>();

    static {
        for (CustOrderHandlingInst tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of Seniority */
    CustOrderHandlingInst(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CustOrderHandlingInst valueFor(String value) {
        return stringToEnum.get(value);
    }
}
