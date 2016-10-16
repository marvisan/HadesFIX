/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * PgpDesMd5Crypter.java
 *
 * $Id: PgpDesMd5Crypter.java,v 1.1 2009-07-06 03:19:10 vrotaru Exp $
 */
package net.hades.fix.message.crypt;

/**
 * Implements the PGP-DES-MD5 crypter.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 25/08/2008, 20:01:35
 */
public class PgpDesMd5Crypter extends Crypter {

    //////////////////////////////////////////
    // CONSTANTS
    //////////////////////////////////////////
    
    //////////////////////////////////////////
    // STATIC BLOCK
    //////////////////////////////////////////
    
    //////////////////////////////////////////
    // ATTRIBUTES
    //////////////////////////////////////////
    
    //////////////////////////////////////////
    // CONSTRUCTORS
    //////////////////////////////////////////
    
    public PgpDesMd5Crypter(CrypterData crypterData) {
        super(crypterData);
    }
    
    //////////////////////////////////////////
    // PUBLIC METHODS
    //////////////////////////////////////////

    @Override
    public byte[] encrypt(byte[] message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public byte[] decrypt(byte[] message) {
        throw new UnsupportedOperationException("Not supported yet.");
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
