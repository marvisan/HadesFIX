/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EncryptMethod.java
 *
 * $Id: EncryptMethod.java,v 1.3 2010-01-14 09:06:49 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Method of encryption.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 5/07/2008, 19:43:53
 */
public enum EncryptMethod {

    None            (0),
    PKCS            (1),            // proprietary
    DES             (2),            // ECB mode
    PKCS_DES        (3),            // proprietary
    PGP_DES         (4),            // defunct
    PGP_DES_MD5     (5),
    PEM_DES_MD5     (6);

    private static final long serialVersionUID = -7663208375178536187L;
    
    private int value;

    private static final Map<String, EncryptMethod> stringToEnum = new HashMap<String, EncryptMethod>();

    static {
        for (EncryptMethod tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }
    
    /** Creates a new instance of EncryptMethod */
    EncryptMethod(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public static EncryptMethod valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
