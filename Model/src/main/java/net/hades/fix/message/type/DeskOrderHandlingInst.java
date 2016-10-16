/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DeskOrderHandlingInst.java
 *
 * $Id: DeskOrderHandlingInst.java,v 1.1 2010-12-05 08:13:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Instruction to handle a deal.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 29/06/2008, 18:34:57
 */
public enum DeskOrderHandlingInst {

    AddOnOrder                          ("ADD"),
    AllOrNone                           ("AON"),
    CashNotHeld                         ("CNH"),
    DirectedOrder                       ("DIR"),
    ExchPhysTransaction                 ("E.W"),
    FillOrKill                          ("FOK"),
    ImbalanceOnly                       ("IO"),
    ImmediateOrCancel                   ("IOC"),
    LimitOnOpen                         ("LOO"),
    LimitOnClose                        ("LOC"),
    MarketAtOpen                        ("MAO"),
    MarketAtClose                       ("MAC"),
    MarketOnOpen                        ("MOO"),
    MarketOnClose                       ("MOC"),
    MinimumQuantity                     ("MQT"),
    OverTheDay                          ("OVD"),
    Pegged                              ("PEG"),
    ReserveSizeOrder                    ("RSV"),
    StopStockTransaction                ("S.W"),
    Scale                               ("SCL"),
    TimeOrder                           ("TMO"),
    TrailingStop                        ("TS"),
    Work                                ("WRK");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, DeskOrderHandlingInst> stringToEnum = new HashMap<String, DeskOrderHandlingInst>();

    static {
        for (DeskOrderHandlingInst tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of DeskOrderHandlingInst */
    DeskOrderHandlingInst(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static DeskOrderHandlingInst valueFor(String value) {
        return stringToEnum.get(value);
    }
}
