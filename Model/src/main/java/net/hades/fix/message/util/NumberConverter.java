/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NumberConverter.java
 *
 * $Id: NumberConverter.java,v 1.3 2011-02-04 09:58:24 vrotaru Exp $
 */
package net.hades.fix.message.util;

import net.hades.fix.message.util.format.NumberFormatter;

import java.math.BigInteger;

/**
 * Converts strings to numbers and reverse for different FIX versions.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 18/10/2008, 18:45:48
 */
public class NumberConverter {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    
    /**
     * Parses a string and creates a Double containing a double or an integer
     * depending of the format of the string.
     * @param number number in string format
     * @return number as a Double
     */
    public static Double parseString(String number) {
        
        Double result = null;
        
        if (number.indexOf(".") > 0 && isDecimalPartGTZero(number)) {
            result = new Double(number);
        } else {
            result = new Double(new Integer(getIntegerPart(number)));
        }

        return result;
    }

    /**
     * Formats a number in a string to be transmitted on the wire. If it is an 
     * integer then no decimal points will be attached. For a float 10 decimal
     * places will be set.
     * @param number value to be transmitted
     * @return string form of the number
     */
    public static String formatNumber(Double number) {
        
        String result = null;

        if (Math.abs(number.intValue() - number.doubleValue()) > 10e-9) {
            // decimal number - send it on wire as a double with 10 decimal places
            result = NumberFormatter.getDecimalPlacesFormat().format(number.doubleValue());
        } else {
            // integer
            result = NumberFormatter.getIntegerFormat().format(number.intValue());
        }

        return result;
    }

    /**
     * Formats a number in a string to be transmitted on the wire. If it is an
     * integer then no decimal points will be attached. For a float 10 decimal
     * places will be set.
     * @param number value to be transmitted
     * @return string form of the number
     */
    public static String formatNumber(Float number) {

        String result = null;

        if (Math.abs(number.intValue() - number.doubleValue()) > 10e-9) {
            // decimal number - send it on wire as a double with 10 decimal places
            result = NumberFormatter.getDecimalPlacesFormat().format(number.doubleValue());
        } else {
            // integer
            result = NumberFormatter.getIntegerFormat().format(number.intValue());
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">

    private static boolean isDecimalPartGTZero(String number) {

        boolean result = false;

        int point = number.indexOf(".");
        if (point >= 0 && number.length() >= point) {
            String dec = number.substring(point + 1);
            long decVal = new BigInteger(dec).longValue();
            if (decVal > 0) {
                result = true;
            }
        }

        return result;
    }

    private static String getIntegerPart(String number) {

        String result = "0";

        int point = number.indexOf(".");
        if (point > 0) {
            result = number.substring(0, point);
        } else {
            result = number;
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
