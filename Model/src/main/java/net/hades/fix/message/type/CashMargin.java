/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CashMargin.java
 *
 * $Id: CashMargin.java,v 1.4 2010-02-25 08:37:27 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Margin order type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/09/2009, 7:25:57 PM
 */
@XmlType
@XmlEnum(String.class)
public enum CashMargin {

    @XmlEnumValue("1") Cash                 ('1'),
    @XmlEnumValue("2") MarginOpen           ('2'),
    @XmlEnumValue("3") MarginClose          ('3');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, CashMargin> stringToEnum = new HashMap<String, CashMargin>();

    static {
        for (CashMargin tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CashMargin */
    CashMargin(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static CashMargin valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
