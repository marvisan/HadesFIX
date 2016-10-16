/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingInstrument44Test.java
 *
 * $Id: UnderlyingInstrument44Test.java,v 1.2 2009-11-21 09:57:29 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.UnderlyingInstrument;
import net.hades.fix.message.comp.impl.v44.UnderlyingInstrument44;
import net.hades.fix.message.group.UnderlyingSecurityAltIDGroup;

/**
 * Test suite for UnderlyingInstrument44 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 11/01/2009, 10:08:32 AM
 */
public class UnderlyingInstrument44Test {

    public UnderlyingInstrument44Test() {
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
     * Test of setNoUnderlyingSecurityAltID method, of class Instrument44.
     */
    @Test
    public void testSetNoUnderlyingSecurityAltID() {
        UnderlyingInstrument comp = new UnderlyingInstrument44();
        assertNull(comp.getUnderlyingSecurityAltIDGroups());
        comp.setNoUnderlyingSecurityAltID(new Integer(3));
        for (int i = 0; i < comp.getUnderlyingSecurityAltIDGroups().length; i++) {
            UnderlyingSecurityAltIDGroup group = comp.getUnderlyingSecurityAltIDGroups()[i];
            group.setUnderlyingSecurityAltID("ALT ID " + i);
            group.setUnderlyingSecurityAltIDSource("ALT ID SOURCE " + i);
        }
        assertEquals(3, comp.getUnderlyingSecurityAltIDGroups().length);
        int i = 0;
        for (UnderlyingSecurityAltIDGroup group : comp.getUnderlyingSecurityAltIDGroups()) {
            assertEquals("ALT ID " + i, group.getUnderlyingSecurityAltID());
            assertEquals("ALT ID SOURCE " + i, group.getUnderlyingSecurityAltIDSource());
            i++;
        }
    }

    /**
     * Test of addUnderlyingSecurityAltIDGroup method, of class Instrument44.
     */
    @Test
    public void testAddUnderlyingSecurityAltIDGroup() {
        UnderlyingInstrument comp = new UnderlyingInstrument44();
        assertNull(comp.getUnderlyingSecurityAltIDGroups());
        comp.setNoUnderlyingSecurityAltID(new Integer(2));
        assertEquals(2, comp.getUnderlyingSecurityAltIDGroups().length);
        for (int i = 0; i < comp.getUnderlyingSecurityAltIDGroups().length; i++) {
            UnderlyingSecurityAltIDGroup group = comp.getUnderlyingSecurityAltIDGroups()[i];
            group.setUnderlyingSecurityAltID("ALT ID " + i);
            group.setUnderlyingSecurityAltIDSource("ALT ID SOURCE " + i);
        }
        comp.addUnderlyingSecurityAltIDGroup();
        assertEquals(3, comp.getUnderlyingSecurityAltIDGroups().length);
        comp.getUnderlyingSecurityAltIDGroups()[2].setUnderlyingSecurityAltID("ALT ID 2");
        comp.getUnderlyingSecurityAltIDGroups()[2].setUnderlyingSecurityAltIDSource("ALT ID SOURCE 2");
        int i = 0;
        for (UnderlyingSecurityAltIDGroup group : comp.getUnderlyingSecurityAltIDGroups()) {
            assertEquals("ALT ID " + i, group.getUnderlyingSecurityAltID());
            assertEquals("ALT ID SOURCE " + i, group.getUnderlyingSecurityAltIDSource());
            i++;
        }
        assertEquals(3, comp.getNoUnderlyingSecurityAltID().intValue());
    }

    /**
     * Test of deleteUnderlyingSecurityAltIDGroup method, of class Instrument44.
     */
    @Test
    public void testDeleteUnderlyingSecurityAltIDGroup() {
        UnderlyingInstrument comp = new UnderlyingInstrument44();
        assertNull(comp.getUnderlyingSecurityAltIDGroups());
        comp.setNoUnderlyingSecurityAltID(new Integer(3));
        for (int i = 0; i < comp.getUnderlyingSecurityAltIDGroups().length; i++) {
            UnderlyingSecurityAltIDGroup group = comp.getUnderlyingSecurityAltIDGroups()[i];
            group.setUnderlyingSecurityAltID("ALT ID " + i);
            group.setUnderlyingSecurityAltIDSource("ALT ID SOURCE " + i);
        }
        assertEquals(3, comp.getUnderlyingSecurityAltIDGroups().length);
        comp.deleteUnderlyingSecurityAltIDGroup(1);
        assertEquals(2, comp.getUnderlyingSecurityAltIDGroups().length);
        assertEquals(2, comp.getNoUnderlyingSecurityAltID().intValue());
        assertEquals("ALT ID 2", comp.getUnderlyingSecurityAltIDGroups()[1].getUnderlyingSecurityAltID());
        assertEquals("ALT ID SOURCE 2", comp.getUnderlyingSecurityAltIDGroups()[1].getUnderlyingSecurityAltIDSource());
    }

    /**
     * Test of clearUnderlyingSecurityAltIDGroups method, of class Instrument44.
     */
    @Test
    public void testClearUnderlyingSecurityAltIDGroups() {
        UnderlyingInstrument comp = new UnderlyingInstrument44();
        assertNull(comp.getUnderlyingSecurityAltIDGroups());
        comp.setNoUnderlyingSecurityAltID(new Integer(3));
        for (int i = 0; i < comp.getUnderlyingSecurityAltIDGroups().length; i++) {
            UnderlyingSecurityAltIDGroup group = comp.getUnderlyingSecurityAltIDGroups()[i];
            group.setUnderlyingSecurityAltID("ALT ID " + i);
            group.setUnderlyingSecurityAltIDSource("ALT ID SOURCE " + i);
        }
        assertEquals(3, comp.getUnderlyingSecurityAltIDGroups().length);
        assertEquals(3, comp.getNoUnderlyingSecurityAltID().intValue());
        int i = 0;
        for (UnderlyingSecurityAltIDGroup group : comp.getUnderlyingSecurityAltIDGroups()) {
            assertEquals("ALT ID " + i, group.getUnderlyingSecurityAltID());
            assertEquals("ALT ID SOURCE " + i, group.getUnderlyingSecurityAltIDSource());
            i++;
        }
        comp.clearUnderlyingSecurityAltIDGroups();
        assertNull(comp.getNoUnderlyingSecurityAltID());
        assertNull(comp.getUnderlyingSecurityAltIDGroups());
    }

    /**
     * Test of setUnderlyingStipulations method, of class UnderlyingInstrument44.
     */
    @Test
    public void testSetUnderlyingStipulations() {
        UnderlyingInstrument comp = new UnderlyingInstrument44();
        assertNull(comp.getUnderlyingStipulations());
        comp.setUnderlyingStipulations();
        assertNotNull(comp.getUnderlyingStipulations());
    }

    /**
     * Test of clearUnderlyingStipulations method, of class UnderlyingInstrument44.
     */
    @Test
    public void testClearUnderlyingStipulations() {
        UnderlyingInstrument comp = new UnderlyingInstrument44();
        assertNull(comp.getUnderlyingStipulations());
        comp.setUnderlyingStipulations();
        assertNotNull(comp.getUnderlyingStipulations());
        comp.clearUnderlyingStipulations();
        assertNull(comp.getUnderlyingStipulations());
    }


    /**
     * Test of encode getter method, of class UnderlyingInstrument with unsupported tag.
     */
    @Test
    public void testGetUnsupportedTag() {
        System.out.println("-->testGetUnsupportedTag");
        UnderlyingInstrument comp = new UnderlyingInstrument44();
        try {
            comp.getUndlyInstrumentParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedUnderlyingInstrumentException(ex);
        }
    }

    /**
     * Test of encode setter method, of class UnderlyingInstrument with unsupported tag.
     */
    @Test
    public void testSetUnsupportedTag() {
        System.out.println("-->testSetUnsupportedTag");
        UnderlyingInstrument comp = new UnderlyingInstrument44();
        try {
            comp.setUndlyInstrumentParties();
            fail("Expect exception thrown.");
        } catch (Exception ex) {
            checkUnsupportedUnderlyingInstrumentException(ex);
        }
    }

    private void checkUnsupportedUnderlyingInstrumentException(Exception ex) {
        assertEquals("This tag is not supported in [UnderlyingInstrument] component version [4.4].", ex.getMessage());
    }

}