/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InvalidMsgException.java
 *
 * $Id: InvalidMsgException.java,v 1.3 2010-02-25 08:37:46 vrotaru Exp $
 */
package net.hades.fix.message.exception;

/**
 * Wrong FIX message format or checksum failed.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 8/07/2008, 21:15:34
 */
public class InvalidMsgException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public InvalidMsgException(String message) {
        super(message);
    }
    
    public InvalidMsgException(String message, Throwable cause) {
        super(message, cause);
    }
}
