/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CrypterSelector.java
 *
 * $Id: CrypterSelector.java,v 1.3 2010-08-25 05:30:35 vrotaru Exp $
 */
package net.hades.fix.message.crypt;

import java.util.logging.Logger;

import net.hades.fix.message.exception.UnsupportedCrypterException;

/**
 * Selects the crypter based on session information.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 25/08/2008, 19:58:03
 */
public class CrypterSelector {

    //////////////////////////////////////////
    // CONSTANTS
    //////////////////////////////////////////
    
    private static final Logger LOGGER = Logger.getLogger(CrypterSelector.class.getName());
    
    private static final CrypterSelector INSTANCE;
    
    //////////////////////////////////////////
    // STATIC BLOCK
    //////////////////////////////////////////
    
    static {
        INSTANCE = new CrypterSelector();
    }
    
    //////////////////////////////////////////
    // ATTRIBUTES
    //////////////////////////////////////////
    
    //////////////////////////////////////////
    // CONSTRUCTORS
    //////////////////////////////////////////
    
    //////////////////////////////////////////
    // PUBLIC METHODS
    //////////////////////////////////////////
    
    /**
     * Creates a specific crypter implementation.
     * @param data cryper initialization data
     * @return Crypte object
     * @throws UnsupportedCrypterException the given crypter method is not supported
     */
    public static Crypter createCrypter(CrypterData data) throws UnsupportedCrypterException {
        
        Crypter result = null;

        switch (data.getMethod()) {
            
            case PGP_DES_MD5:
                result = new PgpDesMd5Crypter(data);
                break;
                
            case DES:
                result = new DesCrypter(data);
                break;
                
            default:
                String error = "Crypter not impemented [" + data.getMethod() + "] yet.";
                LOGGER.severe(error);
                throw new UnsupportedCrypterException(error);
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
}
