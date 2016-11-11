/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CryptUtilTest.java
 *
 * $Id: CryptUtilTest.java,v 1.1 2009-07-06 03:19:13 vrotaru Exp $
 */
package net.hades.fix.message.util;

import net.hades.fix.message.util.CryptUtil;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for CryptUtil class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 28/08/2008, 20:52:10
 */
public class CryptUtilTest {

    public CryptUtilTest() {
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
     * Test of generateDESKey method, of class CryptUtil.
     */
    @Test
    public void testGenerateDESKey() {
        byte[] output = CryptUtil.generateDESKey();
        System.out.println("key=" + CryptUtil.bytes2hex(output));
    }

    /**
     * Test of bytes2hex method, of class CryptUtil.
     */
    @Test
    public void testBytes2hex() {
        byte[] input = new byte[] { (byte) 0x45, (byte) 0x46, (byte) 0x47, (byte) 0xaa, 
            (byte) 0xFF, (byte) 0x0b, (byte) 0xab, (byte) 0x34};
        String output = CryptUtil.bytes2hex(input);
        System.out.println("output=" + output);
        assertEquals("454647aaff0bab34", output);
    }

    /**
     * Test of hex2bytes method, of class CryptUtil.
     */
    @Test
    public void testHex2bytes() {
        String input = "454647aaff0bab34";
        byte[] output = CryptUtil.hex2bytes(input);
        byte[] expected = new byte[] { (byte) 0x45, (byte) 0x46, (byte) 0x47, (byte) 0xaa, 
            (byte) 0xFF, (byte) 0x0b, (byte) 0xab, (byte) 0x34};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], output[i]);
        }
    }
    
    /**
     * Test of hex2bytes method, of class CryptUtil.
     */
    @Test
    public void testHex2bytesCaps() {
        String input = "454647AAFF0BAB34";
        byte[] output = CryptUtil.hex2bytes(input);
        byte[] expected = new byte[] { (byte) 0x45, (byte) 0x46, (byte) 0x47, (byte) 0xaa, 
            (byte) 0xFF, (byte) 0x0b, (byte) 0xab, (byte) 0x34};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], output[i]);
        }
    }

}