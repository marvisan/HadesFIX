/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ExDestinationIDSource.java
 *
 * $Id: ExDestinationIDSource.java,v 1.4 2010-02-25 08:37:27 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * The ID source of ExDestination.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 30/04/2009, 6:21:11 PM
 */
@XmlType
@XmlEnum(String.class)
public enum ExDestinationIDSource {

    @XmlEnumValue("B") BankIdentificationCode                      ('B'),
    @XmlEnumValue("C") GenerallyAcceptedMarketParticipant          ('C'),
    @XmlEnumValue("D") CustomCode                                  ('D'),
    @XmlEnumValue("E") ISOCountryCode                              ('E'),
    @XmlEnumValue("G") MarketIdentifierCode                        ('G');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, ExDestinationIDSource> stringToEnum = new HashMap<String, ExDestinationIDSource>();

    static {
        for (ExDestinationIDSource tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of ExDestinationIDSource */
    ExDestinationIDSource(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static ExDestinationIDSource valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
