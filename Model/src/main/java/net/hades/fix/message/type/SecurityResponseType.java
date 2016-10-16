/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SecurityResponseType.java
 *
 * $Id: SecurityResponseType.java,v 1.3 2010-01-14 09:06:49 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of Security Definition message response.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 02/09/2009, 9:21:53 PM
 */
@XmlType
@XmlEnum(Integer.class)
public enum SecurityResponseType {

    @XmlEnumValue("1")      AcceptSecProposalAsIs               (1),
    @XmlEnumValue("2")      AcceptSecProposalWithRev            (2),
    @XmlEnumValue("3")      ListOfSecTypesReturned              (3),
    @XmlEnumValue("4")      ListOfSecReturned                   (4),
    @XmlEnumValue("5")      RejectSecurityProposal              (5),
    @XmlEnumValue("6")      CannotMatchSelectionCriteria        (6);

    private static final long serialVersionUID = -3872865246358186993L;

    private int value;

    private static final Map<String, SecurityResponseType> stringToEnum = new HashMap<String, SecurityResponseType>();

    static {
        for (SecurityResponseType tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of SecurityResponseType */
    SecurityResponseType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SecurityResponseType valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
