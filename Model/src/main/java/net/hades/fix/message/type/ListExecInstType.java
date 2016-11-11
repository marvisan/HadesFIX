/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListExecInstType.java
 *
 * $Id: ListExecInstType.java,v 1.4 2010-02-25 08:37:29 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies the type of ListExecInst.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 06/09/2009, 10:19:59 AM
 */
@XmlType
@XmlEnum(String.class)
public enum ListExecInstType {

    @XmlEnumValue("1") Immediate                            ('1'),
    @XmlEnumValue("2") WaitForExecutInstruction             ('2'),
    @XmlEnumValue("3") ExchangeCIVOrderSell                 ('3'),
    @XmlEnumValue("4") ExchangeCIVOrderBuyTopUp             ('4'),
    @XmlEnumValue("5") ExchangeCIVOrderBuyWithdraw          ('5');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, ListExecInstType> stringToEnum = new HashMap<String, ListExecInstType>();

    static {
        for (ListExecInstType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ListExecInstType */
    ListExecInstType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static ListExecInstType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
