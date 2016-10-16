/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DesCrypter.java
 *
 * $Id: DesCrypter.java,v 1.2 2010-08-25 05:30:35 vrotaru Exp $
 */
package net.hades.fix.message.crypt;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.logging.Level;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.util.logging.Logger;
import javax.crypto.Cipher;

/**
 * Implements the DES crypter.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 27/08/2008, 20:59:26
 */
public class DesCrypter extends Crypter {

    //////////////////////////////////////////
    // CONSTANTS
    //////////////////////////////////////////
    
    private static final Logger LOGGER = Logger.getLogger(DesCrypter.class.getName());
    
    //////////////////////////////////////////
    // STATIC BLOCK
    //////////////////////////////////////////
    
    //////////////////////////////////////////
    // ATTRIBUTES
    //////////////////////////////////////////

    private Cipher ecipher;
    
    private Cipher dcipher;
    
    private byte[] salt;

    private static final byte[] SALT = {
        (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
        (byte)0x56, (byte)0x35, (byte)0xE3, (byte)0x03
    };

    // Iteration count
    private int iterationCount = 16;

    
    //////////////////////////////////////////
    // CONSTRUCTORS
    //////////////////////////////////////////
    
    public DesCrypter(CrypterData crypterData) {
        super(crypterData);
        if (crypterData.getSalt() != null && crypterData.getSalt().length == 8) {
            salt = crypterData.getSalt();
        } else {
            salt = SALT;
        }
        initialiseCiphers(crypterData.getSymmetricKey());
     }
    
    //////////////////////////////////////////
    // PUBLIC METHODS
    //////////////////////////////////////////

    @Override
    public byte[] encrypt(byte[] message) {
        
        byte[] result = null;
        try {
            // Encrypt
            result = ecipher.doFinal(message);
        } catch (Exception ex) {
            String error = "Could not ecrypt DES the message.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new RuntimeException(error, ex);
        }
        
        return result;
    }

    @Override
    public byte[] decrypt(byte[] message) {
        
        byte[] result = null;
        try {
            // Decrypt
           result = dcipher.doFinal(message);
        } catch (Exception ex) {
            String error = "Could not decrypt DES the message.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new RuntimeException(error, ex);
        }

        return result;
    }

    //////////////////////////////////////////
    // PROTECTED METHODS
    //////////////////////////////////////////

    //////////////////////////////////////////
    // PACKAGE METHODS
    //////////////////////////////////////////
    
    //////////////////////////////////////////
    // PRIVATE METHODS
    //////////////////////////////////////////
    
    private void initialiseCiphers(byte[] symmetricKey) {
        try {
            KeySpec keySpec = new PBEKeySpec(new String(symmetricKey, "UTF-8").toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());
            // Prepare the parameter to the ciphers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
            // Create the ciphers
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch (Exception ex) {
            String error = "Could not initialize DES cyphers.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[] { error, ex.toString() });
            throw new RuntimeException(error, ex);
        }
    }

}
