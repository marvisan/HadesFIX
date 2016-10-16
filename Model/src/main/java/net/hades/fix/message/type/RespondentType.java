/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RespondentType.java
 *
 * $Id: RespondentType.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Specifies the type of respondents requested.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 03/04/2009, 9:32:09 AM
 */
@XmlType
@XmlEnum(Integer.class)
public enum RespondentType {

    @XmlEnumValue("1") AllMarketParticipants                   (1),
    @XmlEnumValue("2") SpecifiedMarketParticipants             (2),
    @XmlEnumValue("3") AllMarketMakers                         (3),
    @XmlEnumValue("4") PrimaryMarketMaker                      (4);

    private static final long serialVersionUID = 1L;

    private int value;

    private static final Map<String, RespondentType> stringToEnum = new HashMap<String, RespondentType>();

    static {
        for (RespondentType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of RespondentType */
    RespondentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RespondentType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
