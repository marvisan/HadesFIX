/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * QuoteRequestType.java
 *
 * $Id: QuoteRequestType.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Indicates the type of Quote Request being generated.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 03/04/2009, 9:58:01 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum QuoteRequestType {

    @XmlEnumValue("1") Manual                      (1),
    @XmlEnumValue("2") Automatic                   (2);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, QuoteRequestType> stringToEnum = new HashMap<String, QuoteRequestType>();

    static {
        for (QuoteRequestType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of QuoteRequestType */
    QuoteRequestType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QuoteRequestType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
