/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EncryptionType.java
 *
 * $Id: EncryptionType.java,v 1.1 2009-07-09 23:24:56 vrotaru Exp $
 */
package net.hades.fix.message.config.model.type;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Type of encryption strings.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 07/07/2009, 1:41:07 PM
 */
@XmlType
@XmlEnum(String.class)
public enum EncryptionType {

    @XmlEnumValue("PKCS")           PKCS                ("PKCS"),
    @XmlEnumValue("DES")            DES                 ("DES"),
    @XmlEnumValue("PKCS-DES")       PKCS_DES            ("PKCS-DES"),
    @XmlEnumValue("PGP-DES")        PGP_DES             ("PGP-DES"),
    @XmlEnumValue("PGP-DES-MD5")    PGP_DES_MD5         ("PGP-DES-MD5"),
    @XmlEnumValue("PEM-DES-MD5")    PEM_DES_MD5         ("PEM-DES-MD5");

    private String value;

    /** Creates a new instance of EncryptionType */
    EncryptionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EncryptionType valueFor(String value) {
        EncryptionType result = null;
        for (EncryptionType val : values()) {
            if (val.getValue().equals(value)) {
                result = val;
                break;
            }
        }
        return result;
    }
}
