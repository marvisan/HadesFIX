/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PaymentMethod.java
 *
 * $Id: PaymentMethod.java,v 1.5 2011-03-26 03:24:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * A code identifying the Settlement payment method.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 22/09/2009, 9:14:49 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum PaymentMethod {

    @XmlEnumValue("1")  CREST                   (1),
    @XmlEnumValue("2")  NSCC                    (2),
    @XmlEnumValue("3")  Euroclear               (3),
    @XmlEnumValue("4")  Clearstream             (4),
    @XmlEnumValue("5")  Cheque                  (5),
    @XmlEnumValue("6")  TelegraphicTransfer     (6),
    @XmlEnumValue("7")  FedWire                 (7),
    @XmlEnumValue("8")  DebitCard               (8),
    @XmlEnumValue("9")  DirectDebit             (9),
    @XmlEnumValue("10") DirectCredit            (10),
    @XmlEnumValue("11") CreditCard              (11),
    @XmlEnumValue("12") ACHDebit                (12),
    @XmlEnumValue("13") ACHCredit               (13),
    @XmlEnumValue("14") BPAY                    (14),
    @XmlEnumValue("15") HVACS                   (15);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, PaymentMethod> stringToEnum = new HashMap<String, PaymentMethod>();

    static {
        for (PaymentMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PaymentMethod */
    PaymentMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PaymentMethod valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
