/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Instrument50SP1Test.java
 *
 * $Id: Instrument50SP1Test.java,v 1.2 2009-11-21 09:57:23 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.impl.v50sp1.Instrument50SP1;
import net.hades.fix.message.group.SecurityAltIDGroup;

/**
 * FIX version 5.0SP1 Instrument component data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 08/01/2009, 8:22:17 PM
 */
public class Instrument50SP1Test {

    public Instrument50SP1Test() {
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
     * Test of setNoSecurityAltID method, of class Instrument44.
     */
    @Test
    public void testSetNoSecurityAltID() {
        Instrument comp = new Instrument50SP1();
        assertNull(comp.getSecurityAltIDGroups());
        comp.setNoSecurityAltID(new Integer(3));
        for (int i = 0; i < comp.getSecurityAltIDGroups().length; i++) {
            SecurityAltIDGroup group = comp.getSecurityAltIDGroups()[i];
            group.setSecurityAltID("ALT ID " + i);
            group.setSecurityAltIDSource("ALT ID SOURCE " + i);
        }
        assertEquals(3, comp.getSecurityAltIDGroups().length);
        int i = 0;
        for (SecurityAltIDGroup group : comp.getSecurityAltIDGroups()) {
            assertEquals("ALT ID " + i, group.getSecurityAltID());
            assertEquals("ALT ID SOURCE " + i, group.getSecurityAltIDSource());
            i++;
        }
    }

    /**
     * Test of addSecurityAltIDGroup method, of class Instrument44.
     */
    @Test
    public void testAddSecurityAltIDGroup() {
        Instrument comp = new Instrument50SP1();
        assertNull(comp.getSecurityAltIDGroups());
        comp.setNoSecurityAltID(new Integer(2));
        assertEquals(2, comp.getSecurityAltIDGroups().length);
        for (int i = 0; i < comp.getSecurityAltIDGroups().length; i++) {
            SecurityAltIDGroup group = comp.getSecurityAltIDGroups()[i];
            group.setSecurityAltID("ALT ID " + i);
            group.setSecurityAltIDSource("ALT ID SOURCE " + i);
        }
        comp.addSecurityAltIDGroup();
        assertEquals(3, comp.getSecurityAltIDGroups().length);
        comp.getSecurityAltIDGroups()[2].setSecurityAltID("ALT ID 2");
        comp.getSecurityAltIDGroups()[2].setSecurityAltIDSource("ALT ID SOURCE 2");
        int i = 0;
        for (SecurityAltIDGroup group : comp.getSecurityAltIDGroups()) {
            assertEquals("ALT ID " + i, group.getSecurityAltID());
            assertEquals("ALT ID SOURCE " + i, group.getSecurityAltIDSource());
            i++;
        }
        assertEquals(3, comp.getNoSecurityAltID().intValue());
    }

    /**
     * Test of deleteSecurityAltIDGroup method, of class Instrument44.
     */
    @Test
    public void testDeleteSecurityAltIDGroup() {
        Instrument comp = new Instrument50SP1();
        assertNull(comp.getSecurityAltIDGroups());
        comp.setNoSecurityAltID(new Integer(3));
        for (int i = 0; i < comp.getSecurityAltIDGroups().length; i++) {
            SecurityAltIDGroup group = comp.getSecurityAltIDGroups()[i];
            group.setSecurityAltID("ALT ID " + i);
            group.setSecurityAltIDSource("ALT ID SOURCE " + i);
        }
        assertEquals(3, comp.getSecurityAltIDGroups().length);
        comp.deleteSecurityAltIDGroup(1);
        assertEquals(2, comp.getSecurityAltIDGroups().length);
        assertEquals(2, comp.getNoSecurityAltID().intValue());
        assertEquals("ALT ID 2", comp.getSecurityAltIDGroups()[1].getSecurityAltID());
        assertEquals("ALT ID SOURCE 2", comp.getSecurityAltIDGroups()[1].getSecurityAltIDSource());
    }

    /**
     * Test of clearSecurityAltIDGroups method, of class Instrument44.
     */
    @Test
    public void testClearSecurityAltIDGroups() {
        Instrument comp = new Instrument50SP1();
        assertNull(comp.getSecurityAltIDGroups());
        comp.setNoSecurityAltID(new Integer(3));
        for (int i = 0; i < comp.getSecurityAltIDGroups().length; i++) {
            SecurityAltIDGroup group = comp.getSecurityAltIDGroups()[i];
            group.setSecurityAltID("ALT ID " + i);
            group.setSecurityAltIDSource("ALT ID SOURCE " + i);
        }
        assertEquals(3, comp.getSecurityAltIDGroups().length);
        assertEquals(3, comp.getNoSecurityAltID().intValue());
        int i = 0;
        for (SecurityAltIDGroup group : comp.getSecurityAltIDGroups()) {
            assertEquals("ALT ID " + i, group.getSecurityAltID());
            assertEquals("ALT ID SOURCE " + i, group.getSecurityAltIDSource());
            i++;
        }
        comp.clearSecurityAltIDGroups();
        assertNull(comp.getNoSecurityAltID());
        assertNull(comp.getSecurityAltIDGroups());
    }

    /**
     * Test of setSecurityXML method, of class Instrument50SP1.
     */
    @Test
    public void testSetSecurityXML() {
        Instrument comp = new Instrument50SP1();
        assertNull(comp.getSecurityXML());
        comp.setSecurityXML();
        assertNotNull(comp.getSecurityXML());
    }

    /**
     * Test of clearSecurityXML method, of class Instrument50SP1.
     */
    @Test
    public void testClearSecurityXML() {
        Instrument comp = new Instrument50SP1();
        assertNull(comp.getSecurityXML());
        comp.setSecurityXML();
        assertNotNull(comp.getSecurityXML());
        comp.clearSecurityXML();
        assertNull(comp.getSecurityXML());
    }

    /**
     * Test of setInstrumentParties method, of class Instrument50SP1.
     */
    @Test
    public void testSetInstrumentParties() {
        Instrument comp = new Instrument50SP1();
        assertNull(comp.getInstrumentParties());
        comp.setInstrumentParties();
        assertNotNull(comp.getInstrumentParties());
    }

    /**
     * Test of clearInstrumentParties method, of class Instrument50SP1.
     */
    @Test
    public void testClearInstrumentParties() {
        Instrument comp = new Instrument50SP1();
        assertNull(comp.getInstrumentParties());
        comp.setInstrumentParties();
        assertNotNull(comp.getInstrumentParties());
        comp.clearInstrumentParties();
        assertNull(comp.getInstrumentParties());
    }

    /**
     * Test of encode getter method, of class Instrument 4.4 with unsupported tag.
     */
    @Test
    public void testGetUnsupportedInstrumentTag() {
        System.out.println("-->testGetUnsupportedInstrumentTag");
        Instrument instrument = new Instrument50SP1();
        try {
            instrument.getComplexEvents();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedInstrumentException(ex);
        }
    }

    /**
     * Test of encode setter method, of class Instrument 4.4 with unsupported tag.
     */
    @Test
    public void testSetUnsupportedInstrumentTag() {
        System.out.println("-->testSetUnsupportedInstrumentTag");
        Instrument instrument = new Instrument50SP1();
        try {
            instrument.setComplexEvents();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedInstrumentException(ex);
        }
    }

    private void checkUnsupportedInstrumentException(Exception ex) {
        assertEquals("This tag is not supported in [Instrument] component version [5.0SP1].", ex.getMessage());
    }

}