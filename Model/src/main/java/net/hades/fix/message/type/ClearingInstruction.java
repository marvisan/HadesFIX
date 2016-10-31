/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ClearingInstruction.java
 *
 * $Id: ClearingInstruction.java,v 1.4 2011-02-16 11:24:34 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Clearing and central counterparty processing type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 26/09/2009, 9:11:20 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum ClearingInstruction {

    @XmlEnumValue("0") ProcessNormally                          (0),
    @XmlEnumValue("1") ExcludeFromAllNetting                    (1),
    @XmlEnumValue("2") BilateralNettingOnly                     (2),
    @XmlEnumValue("3") ExClearing                               (3),
    @XmlEnumValue("4") SpecialTrade                             (4),
    @XmlEnumValue("5") MultilateralNetting                      (5),
    @XmlEnumValue("6") ClearAgainstCentralCpty                  (6),
    @XmlEnumValue("7") ExcludeFromCentralCpty                   (7),
    @XmlEnumValue("8") ManualMode                               (8),
    @XmlEnumValue("9") AutomaticPostingMode                     (9),
    @XmlEnumValue("10") AutomaticGiveUpMode                     (10),
    @XmlEnumValue("11") QualifiedServiceRepresentative          (11),
    @XmlEnumValue("12") CustomerTrade                           (12),
    @XmlEnumValue("13") SelfClearing                            (13);

    private static final long serialVersionUID = -1070612534374420413L;

    private int value;

    private static final Map<String, ClearingInstruction> stringToEnum = new HashMap<String, ClearingInstruction>();

    static {
        for (ClearingInstruction tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ClearingInstruction */
    ClearingInstruction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ClearingInstruction valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
