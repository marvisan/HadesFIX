/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FundRenewWaiv.java
 *
 * $Id: FundRenewWaiv.java,v 1.4 2010-02-25 08:37:28 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Code identifying whether the Fund based renewal commission is to be waived.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 22/09/2009, 9:42:13 PM
 */
@XmlType
@XmlEnum(String.class)
public enum FundRenewWaiv {

    @XmlEnumValue("Y") Yes          ('Y'),
    @XmlEnumValue("N") No           ('N');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, FundRenewWaiv> stringToEnum = new HashMap<String, FundRenewWaiv>();

    static {
        for (FundRenewWaiv tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of CancellationRights */
    FundRenewWaiv(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static FundRenewWaiv valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
