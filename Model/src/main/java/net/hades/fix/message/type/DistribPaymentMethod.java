/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DistribPaymentMethod.java
 *
 * $Id: DistribPaymentMethod.java,v 1.4 2010-02-25 08:37:29 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code identifying the payment method for a (fractional) distribution.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 06/09/2009, 10:54:17 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum DistribPaymentMethod {

    @XmlEnumValue("1")      CREST                   (1),
    @XmlEnumValue("2")      NSCC                    (2),
    @XmlEnumValue("3")      Euroclear               (3),
    @XmlEnumValue("4")      Clearstream             (4),
    @XmlEnumValue("5")      Cheque                  (5),
    @XmlEnumValue("6")      TelegraphicTransfer     (6),
    @XmlEnumValue("7")      FedWire                 (7),
    @XmlEnumValue("8")      DirectCredit            (8),
    @XmlEnumValue("9")      ACHCredit               (9),
    @XmlEnumValue("10")     BPAY                    (10),
    @XmlEnumValue("11")     HVACS                   (11),
    @XmlEnumValue("12")     ReinvestInFund          (12);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, DistribPaymentMethod> stringToEnum = new HashMap<String, DistribPaymentMethod>();

    static {
        for (DistribPaymentMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of DistribPaymentMethod */
    DistribPaymentMethod(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DistribPaymentMethod valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
