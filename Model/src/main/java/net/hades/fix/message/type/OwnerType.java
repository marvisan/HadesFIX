/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OwnerType.java
 *
 * $Id: OwnerType.java,v 1.3 2010-01-14 09:06:46 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Identifies the type of owner.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 24/09/2009, 9:40:37 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum OwnerType {

    @XmlEnumValue("1")      IndividualInvestor              (1),
    @XmlEnumValue("2")      PublicCompany                   (2),
    @XmlEnumValue("3")      PrivateCompany                  (3),
    @XmlEnumValue("4")      IndividualTrustee               (4),
    @XmlEnumValue("5")      CompanyTrustee                  (5),
    @XmlEnumValue("6")      PensionPlan                     (6),
    @XmlEnumValue("7")      CustodianUnderGifts             (7),
    @XmlEnumValue("8")      Trusts                          (8),
    @XmlEnumValue("9")      Fiduciaries                     (9),
    @XmlEnumValue("10")     NetworkingSubAccount            (10),
    @XmlEnumValue("11")     NonProfitOrganization           (11),
    @XmlEnumValue("12")     CorporateBody                   (12),
    @XmlEnumValue("13")     Nominee                         (13);

    private static final long serialVersionUID = -8962584116116374478L;

    private int value;

    private static final Map<String, OwnerType> stringToEnum = new HashMap<String, OwnerType>();

    static {
        for (OwnerType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of OwnerType */
    OwnerType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OwnerType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
