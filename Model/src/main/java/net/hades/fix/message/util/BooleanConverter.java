/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BooleanFormatter.java
 *
 * $Id: BooleanConverter.java,v 1.2 2010-06-27 03:00:46 vrotaru Exp $
 */
package net.hades.fix.message.util;

/**
 * Utilities for boolean types.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/07/2008, 20:38:02
 */
public class BooleanConverter {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    /**
     * Parses a string and covert it in a boolean true only if the
     * string is equal ignore case with <code>y</code>, <code>yes</code>.
     * @param value string to be evaluated as a boolean
     * @return boolean value
     */
    public static boolean parse(String value) {
        
        boolean result = false;
        
        if (value != null && value.trim().length() > 0) {
            result = value.equalsIgnoreCase("y") || value.equalsIgnoreCase("yes");
        }
        
        return result;
    }
    
    public static String formatYN(boolean value) {
        
        String result = "N";
        
        if (value) {
            result = "Y";
        }
        
        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
