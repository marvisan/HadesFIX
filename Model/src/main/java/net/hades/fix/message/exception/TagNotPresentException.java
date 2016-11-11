/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.message.exception;

/**
 * Exception thrown when the tag is not present in the message.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
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
