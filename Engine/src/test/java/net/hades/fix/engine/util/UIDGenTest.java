/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.hades.fix.engine.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for UIDGen class.
 * 
 * @author vrotaru
 */
public class UIDGenTest {

    public UIDGenTest() {
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
     * Test of getNextUID method, of class UIDGen.
     */
    @Test
    public void testGetNextUID() {
        System.out.println("getNextUID");
        String prefix = "CH";
        String result1 = UIDGen.getInstance().getNextUID(prefix);
        String result2 = UIDGen.getInstance().getNextUID(prefix);
        System.out.println("result1=" + result1);
        System.out.println("result2=" + result2);
        assertEquals(result1.substring(0, 2), result2.substring(0, 2));
        assertFalse(result1.equals(result2));
    }

}