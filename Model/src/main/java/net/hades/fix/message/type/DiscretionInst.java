/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DiscretionInst.java
 *
 * $Id: DiscretionInst.java,v 1.4 2010-12-12 09:13:08 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Price a DiscretionOffset is related to.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 7/07/2008, 20:05:44
 */
@XmlType
@XmlEnum(String.class)
public enum DiscretionInst {

    @XmlEnumValue("0") RelatedToDisplayPrice                   ("0"),
    @XmlEnumValue("1") RelatedToMarketPrice                    ("1"),
    @XmlEnumValue("2") RelatedToPrimaryPrice                   ("2"),
    @XmlEnumValue("3") RelatedToLocalPrimaryPrice              ("3"),
    @XmlEnumValue("4") RelatedToMidpointPrice                  ("4"),
    @XmlEnumValue("5") RelatedToLastTradePrice                 ("5");

    private static final long serialVersionUID = -6878664034357044064L;

    private String value;

    private static final Map<String, DiscretionInst> stringToEnum = new HashMap<String, DiscretionInst>();

    static {
        for (DiscretionInst tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }
    
    /** Creates a new instance of DiscretionInst */
    DiscretionInst(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    public static DiscretionInst valueFor(String value) {
        return stringToEnum.get(value);
    }
}
