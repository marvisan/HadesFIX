/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DateConverterTest.java
 *
 * $Id: DateConverterTest.java,v 1.1 2009-07-06 03:19:13 vrotaru Exp $
 */
package net.hades.fix.message.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.util.DateConverter;
import net.hades.fix.message.util.format.DateFormatter;

/**
 * Test suite for DateConverter class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 13/09/2008, 16:34:41
 */
public class DateConverterTest {

    public DateConverterTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of parseString method, of class DateConverter.
     * @throws Exception 
     */
    @Test
    public void testParseString_yyyyMMdd() throws Exception {
        System.out.println("parseString - yyyyMMdd");
        String date = "20080712";
        Calendar cal = Calendar.getInstance();
        resetTimePart(cal);
        cal.set(Calendar.DAY_OF_MONTH, 12);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.YEAR, 2008);
        Date expResult = cal.getTime();
        Date result = DateConverter.parseString(date);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of parseString method, of class DateConverter.
     * @throws Exception 
     */
    @Test
    public void testParseString_hhmmsss() throws Exception {
        System.out.println("parseString - hh:mm:ss");
        String date = "22:17:55";
        Date result = DateConverter.parseString(date);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(result);
        assertEquals(22, cal1.get(Calendar.HOUR_OF_DAY));
        assertEquals(17, cal1.get(Calendar.MINUTE));
        assertEquals(55, cal1.get(Calendar.SECOND));
    }

    /**
     * Test of parseString method, of class DateConverter.
     * @throws Exception
     */
    @Test
    public void testParseString_tz_time_zulu() throws Exception {
        System.out.println("parseString - hh:mm:ssZ");
        String date = "22:17:55Z";
        Date result = DateConverter.parseString(date);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(result);
        assertEquals(8, cal1.get(Calendar.HOUR_OF_DAY));
        assertEquals(17, cal1.get(Calendar.MINUTE));
        assertEquals(55, cal1.get(Calendar.SECOND));
    }

    /**
     * Test of parseString method, of class DateConverter.
     * @throws Exception
     */
    @Test
    public void testParseString_tz_time_houronly() throws Exception {
        System.out.println("parseString - hh:mm:ss+03");
        String date = "22:17:55+03";
        Date result = DateConverter.parseString(date);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(result);
        assertEquals(5, cal1.get(Calendar.HOUR_OF_DAY));
        assertEquals(17, cal1.get(Calendar.MINUTE));
        assertEquals(55, cal1.get(Calendar.SECOND));
    }

    /**
     * Test of parseString method, of class DateConverter.
     * @throws Exception
     */
    @Test
    public void testParseString_tz_time_hourmin() throws Exception {
        System.out.println("parseString - hh:mm:ss");
        String date = "22:17:55-03:30";
        Date result = DateConverter.parseString(date);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(result);
        assertEquals(11, cal1.get(Calendar.HOUR_OF_DAY));
        assertEquals(47, cal1.get(Calendar.MINUTE));
        assertEquals(55, cal1.get(Calendar.SECOND));
    }
    
    /**
     * Test of parseString method, of class DateConverter.
     * @throws Exception 
     */
    @Test
    public void testParseString_hhmmsssSSS() throws Exception {
        System.out.println("parseString - hh:mm:ss.SSS");
        String date = "08:40:13.130";
        Date result = DateConverter.parseString(date);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(result);
        assertEquals(8, cal1.get(Calendar.HOUR_OF_DAY));
        assertEquals(40, cal1.get(Calendar.MINUTE));
        assertEquals(13, cal1.get(Calendar.SECOND));
        assertEquals(130, cal1.get(Calendar.MILLISECOND));
    }
    
    /**
     * Test of parseString method, of class DateConverter.
     * @throws Exception 
     */
    @Test
    public void testParseString_ts() throws Exception {
        System.out.println("parseString - yyyyMMdd-hh:mm:ss");
        String date = "20080712-22:17:55";
        Date result = DateConverter.parseString(date);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(result);
        assertEquals(12, cal1.get(Calendar.DAY_OF_MONTH));
        assertEquals(6, cal1.get(Calendar.MONTH));
        assertEquals(2008, cal1.get(Calendar.YEAR));
        assertEquals(22, cal1.get(Calendar.HOUR_OF_DAY));
        assertEquals(17, cal1.get(Calendar.MINUTE));
        assertEquals(55, cal1.get(Calendar.SECOND));
    }
    
    /**
     * Test of parseString method, of class DateConverter.
     * @throws Exception 
     */
    @Test
    public void testParseString_ts_ext() throws Exception {
        System.out.println("parseString - yyyyMMdd-hh:mm:ss.SSS");
        String date = "20080712-22:17:55.765";
        Date result = DateConverter.parseString(date);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(result);
        assertEquals(12, cal1.get(Calendar.DAY_OF_MONTH));
        assertEquals(6, cal1.get(Calendar.MONTH));
        assertEquals(2008, cal1.get(Calendar.YEAR));
        assertEquals(22, cal1.get(Calendar.HOUR_OF_DAY));
        assertEquals(17, cal1.get(Calendar.MINUTE));
        assertEquals(55, cal1.get(Calendar.SECOND));
        assertEquals(765, cal1.get(Calendar.MILLISECOND));
    }

    /**
     * Test of parseString method, of class DateConverter.
     * @throws Exception
     */
    @Test
    public void testParseString_ts_tz_zulu() throws Exception {
        System.out.println("parseString - yyyyMMdd-hh:mm:ssZ");
        String date = "20080712-22:17:55Z";
        Date result = DateConverter.parseString(date);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(result);
        assertEquals(13, cal1.get(Calendar.DAY_OF_MONTH));
        assertEquals(6, cal1.get(Calendar.MONTH));
        assertEquals(2008, cal1.get(Calendar.YEAR));
        assertEquals(8, cal1.get(Calendar.HOUR_OF_DAY));
        assertEquals(17, cal1.get(Calendar.MINUTE));
        assertEquals(55, cal1.get(Calendar.SECOND));
    }

    /**
     * Test of parseString method, of class DateConverter.
     * @throws Exception
     */
    @Test
    public void testParseString_ts_tz_houronly() throws Exception {
        System.out.println("parseString - yyyyMMdd-hh:mm:ssZ");
        String date = "20080712-22:17:55-05";
        Date result = DateConverter.parseString(date);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(result);
        assertEquals(13, cal1.get(Calendar.DAY_OF_MONTH));
        assertEquals(6, cal1.get(Calendar.MONTH));
        assertEquals(2008, cal1.get(Calendar.YEAR));
        assertEquals(13, cal1.get(Calendar.HOUR_OF_DAY));
        assertEquals(17, cal1.get(Calendar.MINUTE));
        assertEquals(55, cal1.get(Calendar.SECOND));
    }

    /**
     * Test of parseString method, of class DateConverter.
     * @throws Exception
     */
    @Test
    public void testParseString_ts_tz() throws Exception {
        System.out.println("parseString - yyyyMMdd-hh:mm:ssZ");
        String date = "20080712-22:17:55+04";
        Date result = DateConverter.parseString(date);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(result);
        assertEquals(13, cal1.get(Calendar.DAY_OF_MONTH));
        assertEquals(6, cal1.get(Calendar.MONTH));
        assertEquals(2008, cal1.get(Calendar.YEAR));
        assertEquals(4, cal1.get(Calendar.HOUR_OF_DAY));
        assertEquals(17, cal1.get(Calendar.MINUTE));
        assertEquals(55, cal1.get(Calendar.SECOND));
    }

    /**
     * Test of formatDate method, of class DateConverter.
     * @throws Exception 
     */
    @Test
    public void testFormatDate() throws Exception {
        System.out.println("formatDate");
        SimpleDateFormat formatter = DateFormatter.getFixTSFormat();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 22);
        cal.set(Calendar.MONTH, 7);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 16);
        cal.set(Calendar.SECOND, 44);
        Date date = cal.getTime();
        String expResult = "20080822-13:16:44";
        String result = DateConverter.formatDate(formatter, date);
        assertEquals(expResult, result);
    }

    /**
     * Test of formatTZDate method, of class DateConverter.
     * @throws Exception
     */
    @Test
    public void testFormatTZDate() throws Exception {
        System.out.println("formatTZDate");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 22);
        cal.set(Calendar.MONTH, 7);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 16);
        cal.set(Calendar.SECOND, 44);
        Date date = cal.getTime();
        String expResult = "20080822-13:16:44+10:00";
        String result = DateConverter.formatTZDate(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of formatTZTime method, of class DateConverter.
     * @throws Exception
     */
    @Test
    public void testFormatTZTime() throws Exception {
        System.out.println("formatTZTime");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 22);
        cal.set(Calendar.MONTH, 7);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 16);
        cal.set(Calendar.SECOND, 44);
        Date date = cal.getTime();
        String expResult = "13:16:44+10:00";
        String result = DateConverter.formatTZTime(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of formatTZTime method, of class DateConverter.
     * @throws Exception
     */
    @Test
    public void testFormatISOExtDateTime() throws Exception {
        System.out.println("formatISOExtDateTime");
        SimpleDateFormat formatter = DateFormatter.getISOExtDateTimeFormat();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 22);
        cal.set(Calendar.MONTH, 7);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 16);
        cal.set(Calendar.SECOND, 44);
        cal.set(Calendar.MILLISECOND, 345);
        Date date = cal.getTime();
        String expResult = "2008-08-22T13:16:44.345+1000";
        String result = DateConverter.formatDate(formatter, date);
        assertEquals(expResult, result);
    }

    /**
     * Test of formatISODateTime method, of class DateConverter.
     * @throws Exception
     */
    @Test
    public void testFormatISODateTime() throws Exception {
        System.out.println("formatISODateTime");
        SimpleDateFormat formatter = DateFormatter.getISODateTimeFormat();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 22);
        cal.set(Calendar.MONTH, 7);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 16);
        cal.set(Calendar.SECOND, 44);
        cal.set(Calendar.MILLISECOND, 345);
        Date date = cal.getTime();
        String expResult = "2008-08-22T13:16:44";
        String result = DateConverter.formatDate(formatter, date);
        assertEquals(expResult, result);
    }

    /**
     * Test of formatISODate method, of class DateConverter.
     * @throws Exception
     */
    @Test
    public void testFormatISODate() throws Exception {
        System.out.println("formatISODate");
        SimpleDateFormat formatter = DateFormatter.getISODateFormat();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 22);
        cal.set(Calendar.MONTH, 7);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 16);
        cal.set(Calendar.SECOND, 44);
        cal.set(Calendar.MILLISECOND, 345);
        Date date = cal.getTime();
        String expResult = "2008-08-22";
        String result = DateConverter.formatDate(formatter, date);
        assertEquals(expResult, result);
    }

    /**
     * Test of formatISOLocalTime method, of class DateConverter.
     * @throws Exception
     */
    @Test
    public void testFormatISOLocalTime() throws Exception {
        System.out.println("formatISOLocalTime");
        SimpleDateFormat formatter = DateFormatter.getISOTimeFormat();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 22);
        cal.set(Calendar.MONTH, 7);
        cal.set(Calendar.YEAR, 2008);
        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 16);
        cal.set(Calendar.SECOND, 44);
        cal.set(Calendar.MILLISECOND, 345);
        Date date = cal.getTime();
        String expResult = "13:16:44";
        String result = DateConverter.formatDate(formatter, date);
        assertEquals(expResult, result);
    }

    // UTILITY METHODS
    ////////////////////////////////////////
    
    private void resetTimePart(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}