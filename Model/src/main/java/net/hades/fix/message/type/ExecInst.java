/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExecInst.java
 *
 * $Id: ExecInst.java,v 1.4 2011-01-03 07:28:48 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Instruction for executing a trade.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 29/06/2008, 18:05:52
 */
public enum ExecInst {

    NotHeld             ("1"),
    Work                ("2"),
    GoAlong             ("3"),
    OverTheDay          ("4"),
    Held                ("5"),
    Participate         ("6"),
    StrictScale         ("7"),
    TryToScale          ("8"),
    StayOnBidside       ("9"),
    StayOnOfferside     ("0"),
    NoCross             ("A"),
    OKToCross           ("B"),
    CallFirst           ("C"),
    VolumePct           ("D"),
    DNI                 ("E"),
    DNR                 ("F"),
    AON                 ("G"),
    InstitutionsOnly    ("I"),
    LastPeg             ("L"),
    MidPricePeg         ("M"),
    NonNegotiable       ("N"),
    OpeningPeg          ("O"),
    MarketPeg           ("P"),
    PrimaryPeg          ("R"),
    Suspend             ("S"),
    FixedPeg            ("T"),
    CustDisplayInstr    ("U"),
    Netting             ("V"),
    PegToVWAP           ("W");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, ExecInst> stringToEnum = new HashMap<String, ExecInst>();

    static {
        for (ExecInst tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    
    /** Creates a new instance of ExecInst */
    ExecInst(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static ExecInst valueFor(String value) {
        return stringToEnum.get(value);
    }
}
