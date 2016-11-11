/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TriggerPriceDirection.java
 *
 * $Id: TrdRegTimestampType.java,v 1.1 2010-12-05 08:13:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Regulatory timestamp type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 10/02/2009, 7:44:46 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum TrdRegTimestampType {

    @XmlEnumValue("1")  ExecutionTime                   (1),
    @XmlEnumValue("2")  TimeIn                          (2),
    @XmlEnumValue("3")  TimeOut                         (3),
    @XmlEnumValue("4")  BrokerReceipt                   (4),
    @XmlEnumValue("5")  BrokerExecution                 (5),
    @XmlEnumValue("6")  DeskReceipt                     (6),
    @XmlEnumValue("7")  SubmissionToClearing            (7);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, TrdRegTimestampType> stringToEnum = new HashMap<String, TrdRegTimestampType>();

    static {
        for (TrdRegTimestampType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of PegLimitType */
    TrdRegTimestampType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TrdRegTimestampType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
