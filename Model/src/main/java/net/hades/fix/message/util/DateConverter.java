/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DateConverter.java
 *
 * $Id: DateConverter.java,v 1.2 2010-06-27 03:00:46 vrotaru Exp $
 */
package net.hades.fix.message.util;

import net.hades.fix.message.util.format.DateFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.TimeZone;

import net.hades.fix.message.exception.BadFormatMsgException;

/**
 * Converts strings to date and reverse for different FIX versions.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 20/08/2008, 20:45:30
 */
public class DateConverter {

    private static final Logger LOGGER = Logger.getLogger(DateConverter.class .getName());

    public static Date parseString(String date) throws BadFormatMsgException {
        
        Date result = null;
        try {
            if (date.matches("[0-9]{4}(([0][1-9])|([1][0-2]))(([0][1-9])|([1-2][0-9])|([3][0-1]))-(([0-1][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9]")) {
                // yyyymmdd-HH:MM:SS * FixTSFormat
                result = DateFormatter.getFixTSFormat().parse(date);
            } else if (date.matches("[0-9]{4}-(([0][1-9])|([1][0-2]))-(([0][1-9])|([1-2][0-9])|([3][0-1]))T(([0-1][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9]")) {
                // yyyy-mm-ddTHH:MM:SS * ISOLocalDateTimeFormat
                result = DateFormatter.getISOLocalDateTimeFormat().parse(date);
            } else if (date.matches("[0-9]{4}(([0][1-9])|([1][0-2]))(([0][1-9])|([1-2][0-9])|([3][0-1]))-(([0-1][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9]\\.[0-9]{3}")) {
                // yyyymmdd-HH:MM:SS.ZZZ * FixExtdTSFormat
                result = DateFormatter.getFixExtdTSFormat().parse(date);
            } else if (date.matches("[0-9]{4}-(([0][1-9])|([1][0-2]))-(([0][1-9])|([1-2][0-9])|([3][0-1]))T(([0-1][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9](Z|(([-+])(([0-1][0-9])|([2][0-3]))([0-5][0-9])?))")) {
                // yyyy-mm-ddTHH:MM:SS[Z|hh[mm]] * ISODateTimeFormat
                result = DateFormatter.getISODateTimeFormat().parse(date);
            } else if (date.matches("[0-9]{4}(([0][1-9])|([1][0-2]))(([0][1-9])|([1-2][0-9])|([3][0-1]))-(([0-1][0-9])|(([2][0-3])):[0-5][0-9]:([0-5][0-9])?)(Z|(([-+])(([0-1][0-9])|([2][0-3]))(:[0-5][0-9])?))")) {
                // yyyymmdd-HH:MM:SS[Z|hh[:mm]] * FixTZTimestampFormat
                String rfc822Date = date;
                if (date.trim().endsWith("Z")) {
                    rfc822Date = date.substring(0, date.length() - 1) + "+0000";
                } else if (date.indexOf("+") > 0 || (date.lastIndexOf("-") > 0 && date.lastIndexOf("-") > date.indexOf(":"))) {
                    int plusMinusIndex = 100;
                    if (date.indexOf("+") > 0) {
                        plusMinusIndex = date.indexOf("+");
                    } else if (date.lastIndexOf("-") > 0) {
                        plusMinusIndex = date.lastIndexOf("-");
                    }
                    int lastColonIndex = date.lastIndexOf(":");
                    if (lastColonIndex > plusMinusIndex) {
                        // remove last colon
                        rfc822Date = date.substring(0, lastColonIndex) + date.substring(lastColonIndex + 1);
                    } else {
                        rfc822Date = date + "00";
                    }
                }
                result = DateFormatter.getFixTZTimestampFormat().parse(rfc822Date);
            } else if (date.matches("[0-9]{4}(([0][1-9])|([1][0-2]))(([0][1-9])|([1-2][0-9])|([3][0-1]))")) {
                // yyyymmdd
                result = DateFormatter.getFixDateFormat().parse(date);
            } else if (date.matches("[0-9]{4}-(([0][1-9])|([1][0-2]))-(([0][1-9])|([1-2][0-9])|([3][0-1]))")) {
                // yyyy-mm-dd
                result = DateFormatter.getISODateFormat().parse(date);
            } else if (date.matches("(([0-1][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9](Z|(([-+])(([0-1][0-9])|([2][0-3]))(:[0-5][0-9])?))")) {
                // HH:MM:SS[Z+-]hh[:mm]
                String rfc822Date = date;
                if (date.trim().endsWith("Z")) {
                    rfc822Date = date.substring(0, date.length() - 1) + "+0000";
                } else if (date.indexOf("+") > 0 || (date.lastIndexOf("-") > 0 && date.lastIndexOf("-") > date.indexOf(":"))) {
                    int plusMinusIndex = 100;
                    if (date.indexOf("+") > 0) {
                        plusMinusIndex = date.indexOf("+");
                    } else if (date.lastIndexOf("-") > 0) {
                        plusMinusIndex = date.lastIndexOf("-");
                    }
                    int lastColonIndex = date.lastIndexOf(":");
                    if (lastColonIndex > plusMinusIndex) {
                        // remove last colon
                        rfc822Date = date.substring(0, lastColonIndex) + date.substring(lastColonIndex + 1);
                    } else {
                        rfc822Date = date + "00";
                    }
                }
                result = DateFormatter.getFixTZTimeFormat().parse(rfc822Date);
            } else if (date.matches("(([0-1][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9]")) {
                // HH:MM:SS
                result = DateFormatter.getFixTimeFormat().parse(date);
            } else if (date.matches("(([0-1][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9]\\.[0-9]{3}(Z|(([-+])(([0-1][0-9])|([2][0-3]))([0-5][0-9])?))")) {
                // HH:MM:SS.SSS[Z|hh[mm]]
                result = DateFormatter.getISOExtTimeFormat().parse(date);
            } else if (date.matches("(([0-1][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9](Z|(([-+])(([0-1][0-9])|([2][0-3]))([0-5][0-9])?))")) {
                // HH:MM:SS.SSS[Z|hh[mm]]
                result = DateFormatter.getISOTimeFormat().parse(date);
            } else if (date.matches("(([0-1][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9]\\.[0-9]{3}")) {
                // HH:MM:SS.ZZZ
                result = DateFormatter.getFixExtdTimeFormat().parse(date);
            } else {
                String error = "Given date string [" + date + "] does not match any known patterns.";
                LOGGER.severe(error);
                throw new BadFormatMsgException(error);
            }
            
        } catch (ParseException ex) {
            String error = "Could not convert rcvd date string [" + date + "] using any of the formats configured.";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }
        
        return result;
    }
    
    public static String formatDate(SimpleDateFormat formatter, Date date) throws BadFormatMsgException {
        
        String result = null;
        
        try {
            result = formatter.format(date);
        } catch (Exception ex) {
            String error = "Could not convert rcvd date [" + date + "] using the format [" +
                    formatter.toPattern() + "].";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }
        
        return result;
    }

    public static String formatUTCDate(SimpleDateFormat formatter, Date date) throws BadFormatMsgException {

        String result = null;

        try {
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            result = formatter.format(date);
        } catch (Exception ex) {
            String error = "Could not convert rcvd date [" + date + "] using the format [" +
                    formatter.toPattern() + "].";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    public static String formatTZDate(Date date) throws BadFormatMsgException {

        String result = null;
        SimpleDateFormat formatter = DateFormatter.getFixTZTimestampFormat();
        try {
            String rfc822Date = formatter.format(date);
            if (rfc822Date.endsWith("0000")) {
                result = rfc822Date.substring(0, rfc822Date.length() - 4) + "Z";
            } else {
                result = rfc822Date.substring(0, rfc822Date.length() - 2) + ":" +
                    rfc822Date.substring(rfc822Date.length() - 2);
            }
        } catch (Exception ex) {
            String error = "Could not convert rcvd date [" + date + "] using the format [" +
                    formatter.toPattern() + "].";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

    public static String formatTZTime(Date date) throws BadFormatMsgException {

        String result = null;
        SimpleDateFormat formatter = DateFormatter.getFixTZTimeFormat();
        try {
            String rfc822Date = formatter.format(date);
            if (rfc822Date.endsWith("0000")) {
                result = rfc822Date.substring(0, rfc822Date.length() - 4) + "Z";
            } else {
                result = rfc822Date.substring(0, rfc822Date.length() - 2) + ":" +
                    rfc822Date.substring(rfc822Date.length() - 2);
            }
        } catch (Exception ex) {
            String error = "Could not convert rcvd date [" + date + "] using the format [" +
                    formatter.toPattern() + "].";
            LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] { error, ex.toString() });
            throw new BadFormatMsgException(error, ex);
        }

        return result;
    }

}
