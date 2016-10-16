/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NumberConverterTest.java
 *
 * $Id: NumberConverterTest.java,v 1.1 2009-07-06 03:19:13 vrotaru Exp $
 */
package net.hades.fix.message.util;

import net.hades.fix.message.util.NumberConverter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for NumberConverter class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 19/10/2008, 10:21:31
 */
public class NumberConverterTest {

    public NumberConverterTest() {
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
     * Test of parseString method, of class NumberConverter.
     */
    @Test
    public void testParseString_float() {
        System.out.println("parseString_float");
        String number = "1.25";
        Double expResult = new Double(1.25);
        Double result = NumberConverter.parseString(number);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of parseString method, of class NumberConverter.
     */
    @Test
    public void testParseString_float_digit0() {
        System.out.println("parseString_float_digit0");
        String number = "16.0";
        Double expResult = new Double(16);
        Double result = NumberConverter.parseString(number);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of parseString method, of class NumberConverter.
     */
    @Test
    public void testParseString_integer() {
        System.out.println("parseString_integer");
        String number = "25";
        Double expResult = new Double(25);
        Double result = NumberConverter.parseString(number);
        assertEquals(expResult, result);
    }

    /**
     * Test of formatNumber method, of class NumberConverter.
     */
    @Test
    public void testFormatNumber_float() {
        System.out.println("formatNumber_float");
        Float number = new Float(12.567);
        String expResult = "12.567";
        String result = NumberConverter.formatNumber(number);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of formatNumber method, of class NumberConverter.
     */
    @Test
    public void testFormatNumber_float_digit0() {
        System.out.println("formatNumber_float_digit0");
        Double number = new Double(12.0);
        String expResult = "12";
        String result = NumberConverter.formatNumber(number);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of formatNumber method, of class NumberConverter.
     */
    @Test
    public void testFormatNumber_integer() {
        System.out.println("formatNumber_integer");
        Double number = new Double(567);
        String expResult = "567";
        String result = NumberConverter.formatNumber(number);
        assertEquals(expResult, result);
    }
}