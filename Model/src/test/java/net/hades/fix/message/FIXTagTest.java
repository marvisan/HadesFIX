/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXTagTest.java
 *
 * $Id: FIXTagTest.java,v 1.2 2011-04-04 09:37:11 vrotaru Exp $
 */
package net.hades.fix.message;

import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import net.hades.fix.message.type.BeginString;

/**
 * Test suite for TagNum enum.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 25/06/2008, 20:54:24
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FIXTagTest {

    public FIXTagTest() {
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
     * Test of values method, of class TagNum.
     */
    @Test
    public void testValues() {
        BeginString[] result = BeginString.values();
        System.out.println("values no=" + result.length);
        assertEquals(9, result.length);
    }
    
    /**
     * Test of toString method, of class TagNum.
     */
    @Test
    public void testToString() {
        String result = BeginString.FIX_4_3.toString();
        System.out.println("toString=" + result);
        assertEquals("FIX_4_3", result);
    }

    /**
     * Test of valueOf method, of class TagNum.
     */
    @Test
    public void testValueOf() {
        BeginString result = BeginString.valueFor("FIX.4.3");
        System.out.println("valueOf=" + result.getValue());
        assertEquals(BeginString.FIX_4_3, result);
    }

    /**
     * Test of getValue method, of class TagNum.
     */
    @Test
    public void testGetValue() {
        String result = BeginString.FIX_4_3.getValue();
        System.out.println("getValue=" + result);
        assertEquals("FIX.4.3", result);
    }
}