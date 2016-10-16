/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Instrument50Test.java
 *
 * $Id: Instrument50Test.java,v 1.2 2009-11-21 09:57:27 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.Instrument;
import net.hades.fix.message.comp.impl.v50.Instrument50;
import net.hades.fix.message.group.EventGroup;
import net.hades.fix.message.group.SecurityAltIDGroup;

/**
 * Test suite for Instrument50 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 08/01/2009, 20:57:03
 */
public class Instrument50Test {

    public Instrument50Test() {
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
        Instrument comp = new Instrument50();
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
        Instrument comp = new Instrument50();
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
        Instrument comp = new Instrument50();
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
        Instrument comp = new Instrument50();
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
     * Test of setNoEvents method, of class Instrument44.
     */
    @Test
    public void testSetNoEvents() {
        Instrument comp = new Instrument50();
        assertNull(comp.getEvents());
        comp.setNoEvents(new Integer(3));
        for (int i = 0; i < comp.getEvents().length; i++) {
            EventGroup group = comp.getEvents()[i];
            group.setEventType(new Integer(i));
            group.setEventPx(new Double(i * 2.2));
            group.setEventDate(new Date());
            group.setEventText("EVT TXT " + i);
        }
        assertEquals(3, comp.getEvents().length);
        int i = 0;
        for (EventGroup group : comp.getEvents()) {
            assertEquals(i, group.getEventType().intValue());
            assertEquals(new Double(i * 2.2), group.getEventPx());
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(group.getEventDate());
            assertEquals(cal1.get(Calendar.MONTH), cal2.get(Calendar.MONTH));
            assertEquals("EVT TXT " + i, group.getEventText());
            i++;
        }

    }

    /**
     * Test of addEvent method, of class Instrument44.
     */
    @Test
    public void testAddEvent() {
        Instrument comp = new Instrument50();
        assertNull(comp.getEvents());
        comp.setNoEvents(new Integer(2));
        assertEquals(2, comp.getEvents().length);
        for (int i = 0; i < comp.getEvents().length; i++) {
            EventGroup group = comp.getEvents()[i];
            group.setEventType(new Integer(i));
            group.setEventPx(new Double(i * 2.2));
            group.setEventDate(new Date());
            group.setEventText("EVT TXT " + i);
        }
        comp.addEvent();
        assertEquals(3, comp.getEvents().length);
        comp.getEvents()[2].setEventType(new Integer(2));
        comp.getEvents()[2].setEventPx(new Double(2 * 2.2));
        comp.getEvents()[2].setEventDate(new Date());
        comp.getEvents()[2].setEventText("EVT TXT 2");
        int i = 0;
        for (EventGroup group : comp.getEvents()) {
            assertEquals(i, group.getEventType().intValue());
            assertEquals(new Double(i * 2.2), group.getEventPx());
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(group.getEventDate());
            assertEquals(cal1.get(Calendar.MONTH), cal2.get(Calendar.MONTH));
            assertEquals("EVT TXT " + i, group.getEventText());
            i++;
        }
        assertEquals(3, comp.getNoEvents().intValue());
    }

    /**
     * Test of deleteEvent method, of class Instrument44.
     */
    @Test
    public void testDeleteEvent() {
        Instrument comp = new Instrument50();
        assertNull(comp.getEvents());
        comp.setNoEvents(new Integer(3));
        for (int i = 0; i < comp.getEvents().length; i++) {
            EventGroup group = comp.getEvents()[i];
            group.setEventType(new Integer(i));
            group.setEventPx(new Double(i * 2.2));
            group.setEventDate(new Date());
            group.setEventText("EVT TXT " + i);
        }
        assertEquals(3, comp.getEvents().length);
        comp.deleteEvent(1);
        assertEquals(2, comp.getEvents().length);
        assertEquals(2, comp.getNoEvents().intValue());
        assertEquals(2, comp.getEvents()[1].getEventType().intValue());
        assertEquals(new Double(2 * 2.2), comp.getEvents()[1].getEventPx());
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(comp.getEvents()[1].getEventDate());
        assertEquals(cal1.get(Calendar.MONTH), cal2.get(Calendar.MONTH));
        assertEquals("EVT TXT 2", comp.getEvents()[1].getEventText());
    }

    /**
     * Test of clearEvents method, of class Instrument44.
     */
    @Test
    public void testClearEvents() {
        Instrument comp = new Instrument50();
        assertNull(comp.getEvents());
        comp.setNoEvents(new Integer(3));
        for (int i = 0; i < comp.getEvents().length; i++) {
            EventGroup group = comp.getEvents()[i];
            group.setEventType(new Integer(i));
            group.setEventPx(new Double(i * 2.2));
            group.setEventDate(new Date());
            group.setEventText("EVT TXT " + i);
        }
        assertEquals(3, comp.getEvents().length);
        assertEquals(3, comp.getNoEvents().intValue());
        int i = 0;
        for (EventGroup group : comp.getEvents()) {
            assertEquals(i, group.getEventType().intValue());
            assertEquals(new Double(i * 2.2), group.getEventPx());
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(group.getEventDate());
            assertEquals(cal1.get(Calendar.MONTH), cal2.get(Calendar.MONTH));
            assertEquals("EVT TXT " + i, group.getEventText());
            i++;
        }
        comp.clearEvents();
        assertNull(comp.getNoEvents());
        assertNull(comp.getEvents());
    }

    /**
     * Test of setInstrumentParties method, of class Instrument50.
     */
    @Test
    public void testSetInstrumentParties() {
        Instrument comp = new Instrument50();
        assertNull(comp.getInstrumentParties());
        comp.setInstrumentParties();
        assertNotNull(comp.getInstrumentParties());
    }

    /**
     * Test of clearInstrumentParties method, of class Instrument50.
     */
    @Test
    public void testClearInstrumentParties() {
        Instrument comp = new Instrument50();
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
        Instrument instrument = new Instrument50();
        try {
            instrument.getSecurityXML();
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
     * Test of encode setter method, of class Instrument 4.4 with unsupported tag.
     */
    @Test
    public void testSetUnsupportedInstrumentTag() {
        System.out.println("-->testSetUnsupportedInstrumentTag");
        Instrument instrument = new Instrument50();
        try {
            instrument.setSecurityXML();
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
        assertEquals("This tag is not supported in [Instrument] component version [5.0].", ex.getMessage());
    }

}