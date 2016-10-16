/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LotType.java
 *
 * $Id: LotType.java,v 1.4 2010-02-25 08:37:30 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Lot type assigned to the order.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 20/10/2009, 6:35:44 PM
 */
@XmlType
@XmlEnum(String.class)
public enum LotType {

    @XmlEnumValue("1") OddLot                               ('1'),
    @XmlEnumValue("2") RoundLot                             ('2'),
    @XmlEnumValue("3") BlockLot                             ('3'),
    @XmlEnumValue("4") RoundLotUponUnitOfMeasure            ('4');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, LotType> stringToEnum = new HashMap<String, LotType>();

    static {
        for (LotType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of LotType */
    LotType(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static LotType valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
