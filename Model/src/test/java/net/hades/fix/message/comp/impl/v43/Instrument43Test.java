/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Instrument43Test.java
 *
 * $Id: Instrument43Test.java,v 1.2 2009-11-21 09:57:28 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.impl.v43.Instrument43;
import net.hades.fix.message.group.SecurityAltIDGroup;

/**
 * Test suite for Instrument43 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 08/01/2009, 20:57:03
 */
public class Instrument43Test {

    public Instrument43Test() {
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
     * Test of setNoSecurityAltID method, of class Instrument43.
     */
    @Test
    public void testSetNoSecurityAltID() {
        Instrument comp = new Instrument43();
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
     * Test of addSecurityAltIDGroup method, of class Instrument43.
     */
    @Test
    public void testAddSecurityAltIDGroup() {
        Instrument comp = new Instrument43();
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
     * Test of deleteSecurityAltIDGroup method, of class Instrument43.
     */
    @Test
    public void testDeleteSecurityAltIDGroup() {
        Instrument comp = new Instrument43();
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
     * Test of clearSecurityAltIDGroups method, of class Instrument43.
     */
    @Test
    public void testClearSecurityAltIDGroups() {
        Instrument comp = new Instrument43();
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
     * Test of encode getter method, of class Instrument 4.3 with unsupported tag.
     */
    @Test
    public void testGetUnsupportedInstrumentTag() {
        System.out.println("-->testGetUnsupportedInstrumentTag");
        Instrument instrument = new Instrument43();
        try {
            instrument.getSecurityXML();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedInstrumentException(ex);
        }
        try {
            instrument.getNoEvents();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedInstrumentException(ex);
        }
        try {
            instrument.getEvents();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedInstrumentException(ex);
        }
        try {
            instrument.getInstrumentParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedInstrumentException(ex);
        }
        try {
            instrument.getComplexEvents();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedInstrumentException(ex);
        }
    }

    /**
     * Test of encode setter method, of class Instrument 4.3 with unsupported tag.
     */
    @Test
    public void testSetUnsupportedInstrumentTag() {
        System.out.println("-->testSetUnsupportedInstrumentTag");
        Instrument instrument = new Instrument43();
        try {
            instrument.setSecurityXML();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedInstrumentException(ex);
        }
        try {
            instrument.setNoEvents(new Integer(2));
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedInstrumentException(ex);
        }
        try {
            instrument.addEvent();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedInstrumentException(ex);
        }
        try {
            instrument.deleteEvent(0);
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedInstrumentException(ex);
        }
        try {
            instrument.clearEvents();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedInstrumentException(ex);
        }
        try {
            instrument.setInstrumentParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedInstrumentException(ex);
        }
        try {
            instrument.setComplexEvents();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedInstrumentException(ex);
        }
    }

    private void checkUnsupportedInstrumentException(Exception ex) {
        assertEquals("This tag is not supported in [Instrument] component version [4.3].", ex.getMessage());
    }

}