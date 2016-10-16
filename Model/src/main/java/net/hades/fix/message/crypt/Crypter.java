/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Crypter.java
 *
 * $Id: Crypter.java,v 1.1 2009-07-06 03:19:10 vrotaru Exp $
 */
package net.hades.fix.message.crypt;

/**
 * Defines the interface for a encrypter/decrypter.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 25/08/2008, 19:54:44
 */
public abstract class Crypter {
    
    protected CrypterData crypterData;

    public Crypter(CrypterData crypterData) {
        this.crypterData = crypterData;
    }
    
    /**
     * Encrypts the given message using the crypter specific algorithm.
     * @param message to be encrypted
     * @return encrypted message
     */
    public abstract byte[] encrypt(byte[] message);
    
    /**
     * Decrypts the given message using the crypter specific algorithm.
     * @param message to be decrypted
     * @return decrypted message
     */
    public abstract byte[] decrypt(byte[] message);
}
