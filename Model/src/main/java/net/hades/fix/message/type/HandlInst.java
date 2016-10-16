/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HandlInst.java
 *
 * $Id: HandlInst.java,v 1.2 2010-12-12 09:13:08 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Instruction to handle a deal.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 29/06/2008, 18:34:57
 */
@XmlType
@XmlEnum(String.class)
public enum HandlInst {

    @XmlEnumValue("1") PrivateNoBroker     ("1"),
    @XmlEnumValue("2") PublicBrokerOK      ("2"),
    @XmlEnumValue("3") ManualOrder         ("3");

    private static final long serialVersionUID = 1L;
    
    private String value;

    private static final Map<String, HandlInst> stringToEnum = new HashMap<String, HandlInst>();

    static {
        for (HandlInst tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of HandlInst */
    HandlInst(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static HandlInst valueFor(String value) {
        return stringToEnum.get(value);
    }
}
