/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocReportType.java
 *
 * $Id: AllocReportType.java,v 1.1 2011-02-13 04:40:42 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type or purpose of an Allocation Report message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/04/2009, 10:19:51 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum AllocReportType {

    @XmlEnumValue("2")  PrelimRequestToIntermediary                 (2),
    @XmlEnumValue("3")  SellsideUsingPreliminary                    (3),
    @XmlEnumValue("4")  SellsideWithoutPreliminary                  (4),
    @XmlEnumValue("5")  WarehouseRecap                              (5),
    @XmlEnumValue("8")  RequestToIntermediary                       (8),
    @XmlEnumValue("9")  Accept                                      (9),
    @XmlEnumValue("10") Reject                                      (10),
    @XmlEnumValue("11") AcceptPending                               (11),
    @XmlEnumValue("12") Complete                                    (12),
    @XmlEnumValue("14") ReversePending                              (14);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, AllocReportType> stringToEnum = new HashMap<String, AllocReportType>();

    static {
        for (AllocReportType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of AllocReportType */
    AllocReportType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AllocReportType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
