/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlmntTyp.java
 *
 * $Id: SettlType.java,v 1.4 2010-02-25 08:37:29 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Type of settlement.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 5/07/2008, 19:30:49
 */
public enum SettlType {

    Regular             ("0"),
    Cash                ("1"),
    NextDay             ("2"),
    Tplus2              ("3"),
    Tplus3              ("4"),
    Tplus4              ("5"),
    Future              ("6"),
    WhenIssued          ("7"),
    SellersOption       ("8"),
    Tplus5              ("9"),
    BrokenDate          ("B"),
    FXSpotNextDay       ("C");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, SettlType> stringToEnum = new HashMap<String, SettlType>();

    static {
        for (SettlType tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of SettlType */
    SettlType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static SettlType valueFor(String value) {
        return stringToEnum.get(value);
    }
}
