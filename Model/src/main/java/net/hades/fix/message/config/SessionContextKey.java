/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SessionContextKey.java
 *
 * $Id: SessionContextKey.java,v 1.6 2011-02-16 11:24:35 vrotaru Exp $
 */
package net.hades.fix.message.config;

/**
 * Session keys values.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 30/08/2008, 12:12:29
 */
public enum SessionContextKey {
    
    /**
     * Encryption data (CrypterData).
     */
    ENCRYPTION_DATA                 ("CrypterData"),
    /**
     * Encryption data (CrypterData).
     */
    ENCRYPTION_IMPL_CLASS           ("net.hades.fix.message.crypt.CrypterImplClass"),
    /**
     * Crypter object (Crypter).
     */
    CRYPTER                         ("Crypter"),
    /**
     * Flag indicating that created messages use non standard FIXT begin strings (e.g. FIX.5.0SP1).
     */
    USE_NON_STD_BEGIN_STRING        ("net.hades.fix.message.UseNonStdBeginString"),
    /**
     * Crypter object (java.nio.charset.Charset).
     */
    SESSION_CHARACTER_SET           ("net.hades.fix.session.Charset"),
    /**
     * For FIXT 1.1 the default version ID for a message.
     */
    DEFAULT_APPL_VER_ID             ("net.hades.fix.session.DefaultApplVerID"),
    /**
     * Flag indicating that FIX required tags are enforced.
     */
    VALIDATE_REQUIRED               ("net.hades.fix.session.ValidateRequiredTags"),
    /**
     * Flag indicating that FIXML generated messages are in printable format.
     */
    PRINTABLE_FIXML                 ("net.hades.fix.session.PrintableFIXML"),
    /**
     * Flag indicating that incoming FIXML data will be validated against the schema.
     */
    VALIDATE_INCOMING_FIXML         ("net.hades.fix.session.ValidateIncomingFIXML"),
    /**
     * Flag indicating that outgoing FIXML data will be validated against the schema.
     */
    VALIDATE_OUTGOING_FIXML         ("net.hades.fix.session.ValidateOutgoingFIXML"),
    /**
     * Flag indicating that outgoing FIXML data will be validated against the schema.
     */
    FIXML_VALIDATE_ABORT_ON_ERROR   ("net.hades.fix.session.FIXMLValidateAbortOnError"),
    /**
     * Thread local cached value of the FICML codec.
     */
    FIXML_CODEC                     ("net.hades.fix.session.FIXMLCodec");

    private static final long serialVersionUID = 1L;
    
    private String value;

    /** Creates a new instance of SessionContextKey */
    SessionContextKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public static SessionContextKey valueFor(String value) {
        SessionContextKey result = null;
        for (SessionContextKey val : values()) {
            if (val.getValue().equals(value)) {
                result = val;
                break;
            }
        }
        return result; 
    }

}
