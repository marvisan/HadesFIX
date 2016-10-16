/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TagNotPresentException.java
 *
 * $Id: TagNotPresentException.java,v 1.3 2010-02-25 08:37:46 vrotaru Exp $
 */
package net.hades.fix.message.exception;

/**
 * Exception thrown when the tag is not present in the message.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 21/07/2008, 19:39:27
 */
public class TagNotPresentException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public TagNotPresentException(String message) {
        super(message);
    }
    
    public TagNotPresentException(String message, Throwable cause) {
        super(message, cause);
    }
}
