/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CryptUtil.java
 *
 * $Id: CryptUtil.java,v 1.2 2010-06-27 03:00:46 vrotaru Exp $
 */
package net.hades.fix.message.util;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Different cryptographic utilities.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 27/08/2008, 21:17:20
 */
public class CryptUtil {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final Logger LOGGER = Logger.getLogger(CryptUtil.class.getName());
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public static byte[] generateDESKey() {
        
        byte[] result = null;
        SecretKey key;
        try {
            key = KeyGenerator.getInstance("DES").generateKey();
        } catch (NoSuchAlgorithmException ex) {
            String error = "Could not generate a DES key.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new RuntimeException(error, ex);
        }
        result = key.getEncoded();
                
        return result;
    }
    
    public static String bytes2hex(byte[] byteMsg) {
        BigInteger bi = new BigInteger(byteMsg);
        return bi.toString(16);
    }
    
    public static byte[] hex2bytes(String hexMsg) {
        return new BigInteger(hexMsg, 16).toByteArray(); 
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
