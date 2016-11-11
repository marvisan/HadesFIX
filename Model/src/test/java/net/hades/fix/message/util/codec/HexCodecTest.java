/*
 *   Copyright (c) 2006-2009 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HexCodecTest.java
 *
 * $Id: HexCodecTest.java,v 1.1 2009-08-03 09:41:42 vrotaru Exp $
 */
package net.hades.fix.message.util.codec;

import net.hades.fix.message.util.codec.HexCodec;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for HexCodec class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 03/08/2009, 11:46:25 AM
 */
public class HexCodecTest {

    public HexCodecTest() {
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
     * Test of encode method, of class HexCodec.
     */
    @Test
    public void testEncode() {
        System.out.println("encode");
        byte[] raw = new byte[] {(byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4,
            (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11,
            (byte) 12, (byte) 13, (byte) 14, (byte) 15};
        String expResult = "000102030405060708090A0B0C0D0E0F";
        String result = HexCodec.encode(raw);
        assertEquals(expResult, result.toUpperCase());
    }

    /**
     * Test of decode method, of class HexCodec.
     */
    @Test
    public void testDecode() {
        System.out.println("decode");
        String s = "000102030405060708090A0B0C0D0E0F";
        byte[] expResult = new byte[] {(byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4,
            (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11,
            (byte) 12, (byte) 13, (byte) 14, (byte) 15};
        byte[] result = HexCodec.decode(s);
        assertArrayEquals(expResult, result);
    }

}