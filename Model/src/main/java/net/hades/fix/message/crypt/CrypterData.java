/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CrypterData.java
 *
 * $Id: CrypterData.java,v 1.1 2009-07-06 03:19:10 vrotaru Exp $
 */
package net.hades.fix.message.crypt;

import net.hades.fix.message.type.EncryptMethod;

/**
 * Data needed to create a crypter.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/08/2008, 11:15:36
 */
public class CrypterData {

    //////////////////////////////////////////
    // CONSTANTS
    //////////////////////////////////////////
    
    //////////////////////////////////////////
    // STATIC BLOCK
    //////////////////////////////////////////
    
    //////////////////////////////////////////
    // ATTRIBUTES
    //////////////////////////////////////////
    
    private EncryptMethod method;
    
    private byte[] salt;
    
    private byte[] symmetricKey;
    
    private byte[] privateKey;
    
    private byte[] publicKey;
    
    private byte[] cptyPublicKey;

    //////////////////////////////////////////
    // CONSTRUCTORS
    //////////////////////////////////////////
    
    public CrypterData(EncryptMethod method, byte[] symmetricKey) {
        this.method = method;
        this.symmetricKey = symmetricKey;
    }
    
    public CrypterData(EncryptMethod method, byte[] privateKey, byte[] publicKey) {
        this.method = method;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    //////////////////////////////////////////
    // PUBLIC METHODS
    //////////////////////////////////////////

    public EncryptMethod getMethod() {
        return method;
    }

    public void setMethod(EncryptMethod method) {
        this.method = method;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public byte[] getSymmetricKey() {
        return symmetricKey;
    }

    public void setSymmetricKey(byte[] symmetricKey) {
        this.symmetricKey = symmetricKey;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getCptyPublicKey() {
        return cptyPublicKey;
    }

    public void setCptyPublicKey(byte[] cptyPublicKey) {
        this.cptyPublicKey = cptyPublicKey;
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
}
