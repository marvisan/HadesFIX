/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PriceQuoteMethod.java
 *
 * $Id: PriceQuoteMethod.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Method for price quotation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/12/2008, 7:20:55 PM
 */
@XmlType
@XmlEnum(String.class)
public enum PriceQuoteMethod {

    @XmlEnumValue("STD") Standard       ("STD"),
    @XmlEnumValue("INX") Index          ("INX"),
    @XmlEnumValue("INT") Interest       ("INT");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, PriceQuoteMethod> stringToEnum = new HashMap<String, PriceQuoteMethod>();

    static {
        for (PriceQuoteMethod tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of PriceQuoteMethod. */
    PriceQuoteMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PriceQuoteMethod valueFor(String value) {
        return stringToEnum.get(value);
    }
}
