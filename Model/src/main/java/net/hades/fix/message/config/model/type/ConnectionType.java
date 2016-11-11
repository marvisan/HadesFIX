/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConnectionType.java
 *
 * $Id: ConnectionType.java,v 1.1 2009-07-09 23:24:56 vrotaru Exp $
 */
package net.hades.fix.message.config.model.type;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of connection: server or client.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 06/07/2009, 8:46:54 PM
 */
@XmlType
@XmlEnum(String.class)
public enum ConnectionType {

    @XmlEnumValue("S") Server       ("S"),
    @XmlEnumValue("C") Client       ("C");

    private String value;

    /** Creates a new instance of ConnectionType */
    ConnectionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ConnectionType valueFor(String value) {
        ConnectionType result = null;
        for (ConnectionType val : values()) {
            if (val.getValue().equals(value)) {
                result = val;
                break;
            }
        }
        return result;
    }
}
