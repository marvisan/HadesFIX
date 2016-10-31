/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Instrument50SP2Test.java
 *
 * $Id: Instrument50SP2Test.java,v 1.2 2009-11-21 09:57:20 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.impl.v50sp2.Instrument50SP2;

/**
 * FIX version 5.0SP2 Instrument component data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 08/01/2009, 8:22:17 PM
 */
public class Instrument50SP2Test {

    public Instrument50SP2Test() {
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
     * Test of setSecurityXML method, of class Instrument50SP2.
     */
    @Test
    public void testSetSecurityXML() {
        Instrument comp = new Instrument50SP2();
        assertNull(comp.getSecurityXML());
        comp.setSecurityXML();
        assertNotNull(comp.getSecurityXML());
    }

    /**
     * Test of clearSecurityXML method, of class Instrument50SP2.
     */
    @Test
    public void testClearSecurityXML() {
        Instrument comp = new Instrument50SP2();
        assertNull(comp.getSecurityXML());
        comp.setSecurityXML();
        assertNotNull(comp.getSecurityXML());
        comp.clearSecurityXML();
        assertNull(comp.getSecurityXML());
    }

    /**
     * Test of setInstrumentParties method, of class Instrument50SP2.
     */
    @Test
    public void testSetInstrumentParties() {
        Instrument comp = new Instrument50SP2();
        assertNull(comp.getInstrumentParties());
        comp.setInstrumentParties();
        assertNotNull(comp.getInstrumentParties());
    }

    /**
     * Test of clearInstrumentParties method, of class Instrument50SP2.
     */
    @Test
    public void testClearInstrumentParties() {
        Instrument comp = new Instrument50SP2();
        assertNull(comp.getInstrumentParties());
        comp.setInstrumentParties();
        assertNotNull(comp.getInstrumentParties());
        comp.clearInstrumentParties();
        assertNull(comp.getInstrumentParties());
    }

    /**
     * Test of ComplexEvents method, of class Instrument50SP2.
     */
    @Test
    public void testSetComplexEvents() {
        Instrument comp = new Instrument50SP2();
        assertNull(comp.getComplexEvents());
        comp.setComplexEvents();
        assertNotNull(comp.getComplexEvents());
    }

    /**
     * Test of clearSecurityXML method, of class Instrument50SP2.
     */
    @Test
    public void testClearComplexEvents() {
        Instrument comp = new Instrument50SP2();
        assertNull(comp.getComplexEvents());
        comp.setComplexEvents();
        assertNotNull(comp.getComplexEvents());
        comp.clearComplexEvents();
        assertNull(comp.getComplexEvents());
    }

    private void checkUnsupportedInstrumentException(Exception ex) {
        assertEquals("This tag is not supported in [Instrument] component version [5.0SP2].", ex.getMessage());
    }

}