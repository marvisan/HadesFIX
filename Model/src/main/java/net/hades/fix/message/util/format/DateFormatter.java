/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DateFormatter.java
 *
 * $Id: DateFormatter.java,v 1.2 2010-06-27 03:00:46 vrotaru Exp $
 */
package net.hades.fix.message.util.format;

import java.text.SimpleDateFormat;

/**
 * Date formatter.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 22/05/2008, 09:04:16
 */
public class DateFormatter {

    public static SimpleDateFormat getISODateTimeFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    }

    public static SimpleDateFormat getISOLocalDateTimeFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    }

    public static SimpleDateFormat getISOExtDateTimeFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }

    public static SimpleDateFormat getISODateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    public static SimpleDateFormat getISOTimeFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }

    public static SimpleDateFormat getISOExtTimeFormat() {
        return new SimpleDateFormat("HH:mm:ss.SSSZ");
    }

    public static SimpleDateFormat getFixTZTimestampFormat() {
        return new SimpleDateFormat("yyyyMMdd-HH:mm:ssZ");
    }

    public static SimpleDateFormat getFixTZTimeFormat() {
        return new SimpleDateFormat("HH:mm:ssZ");
    }
    
    public static SimpleDateFormat getFixExtdTSFormat() {
        return new SimpleDateFormat("yyyyMMdd-HH:mm:ss.SSS");
    }
    
    public static SimpleDateFormat getFixTSFormat() {
        return new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
    }
    
    public static SimpleDateFormat getFixDateFormat() {
        return new SimpleDateFormat("yyyyMMdd");
    }
    
    public static SimpleDateFormat getFixTimeFormat() {
        return new SimpleDateFormat("HH:mm:ss");
    }
    
    public static SimpleDateFormat getFixExtdTimeFormat() {
        return new SimpleDateFormat("HH:mm:ss.SSS");
    }
    
    public static SimpleDateFormat getSlashDateFormat() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }
    
    public static SimpleDateFormat getDashDateFormat() {
        return new SimpleDateFormat("dd-MM-yyyy");
    }
}
