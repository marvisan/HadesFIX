/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlInstSource.java
 *
 * $Id: SettlInstSource.java,v 1.2 2011-02-16 11:24:34 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Types of resulting position.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 10/02/2009, 7:44:46 PM
 */
@XmlType
@XmlEnum(String.class)
public enum SettlInstSource {

    @XmlEnumValue("1") BrokerInstructions                  ('1'),
    @XmlEnumValue("2") InstitutionInstructions             ('2'),
    @XmlEnumValue("3") Investor                            ('3');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, SettlInstSource> stringToEnum = new HashMap<String, SettlInstSource>();

    static {
        for (SettlInstSource tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SettlInstSource */
    SettlInstSource(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static SettlInstSource valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
