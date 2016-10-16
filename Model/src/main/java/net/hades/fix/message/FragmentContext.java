/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FragmentContext.java
 *
 * $Id: FragmentContext.java,v 1.4 2011-04-14 23:44:47 vrotaru Exp $
 */
package net.hades.fix.message;

import net.hades.fix.message.crypt.Crypter;

import java.nio.charset.Charset;

/**
 * Container of context data passed to fragments and used for encoding/decoding
 * functionality.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 12/11/2008, 8:53:19 PM
 */
public class FragmentContext {
    
    private Charset sessionCharset;
    
    private Charset messageEncoding;
    
    private boolean encryptionRequired;
    
    private Crypter crypter;

    private boolean validateRequired;
    
    public FragmentContext() {
        this.sessionCharset = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
        this.messageEncoding = Charset.forName(FIXMsg.DEFAULT_CHARACTER_SET);
    }

    public FragmentContext(Charset sessionCharset, Charset messageEncoding) {
        this.sessionCharset = sessionCharset;
        this.messageEncoding = messageEncoding;
    }

    public FragmentContext(Charset sessionCharset, Charset messageEncoding, boolean validateRequired) {
        this(sessionCharset, messageEncoding);
        this.validateRequired = validateRequired;
    }
    
    public FragmentContext(Charset sessionCharset, Charset messageEncoding, boolean encryptionRequired, Crypter crypter) {
        this(sessionCharset, messageEncoding);
        this.encryptionRequired = encryptionRequired;
        this.crypter = crypter;
    }

    public FragmentContext(Charset sessionCharset, Charset messageEncoding, boolean encryptionRequired, Crypter crypter, boolean validateRequired) {
        this(sessionCharset, messageEncoding, encryptionRequired, crypter);
        this.validateRequired = validateRequired;
    }

    public Charset getSessionCharset() {
        return sessionCharset;
    }

    public boolean isEncryptionRequired() {
        return encryptionRequired;
    }

    public Crypter getCrypter() {
        return crypter;
    }

    public Charset getMessageEncoding() {
        return messageEncoding;
    }

    public boolean isValidateRequired() {
        return validateRequired;
    }
}
