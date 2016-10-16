/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UnderlyingStipulations50Test.java
 *
 * $Id: UnderlyingStipulations50Test.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.UnderlyingStipulations;
import net.hades.fix.message.comp.impl.v50.UnderlyingStipulations50;
import net.hades.fix.message.group.UnderlyingStipsGroup;

/**
 * Test suite for UnderlyingStipulations50 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/01/2009, 11:07:53 AM
 */
public class UnderlyingStipulations50Test {

    public UnderlyingStipulations50Test() {
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
     * Test of setNoUnderlyingStips method, of class UnderlyingStipulations44.
     */
    @Test
    public void testSetNoUnderlyingStips() {
        UnderlyingStipulations comp = new UnderlyingStipulations50();
        assertNull(comp.getUnderlyingStipsGroups());
        comp.setNoUnderlyingStips(new Integer(3));
        for (int i = 0; i < comp.getUnderlyingStipsGroups().length; i++) {
            UnderlyingStipsGroup group = comp.getUnderlyingStipsGroups()[i];
            group.setUnderlyingStipType("STIP TYPE " + i);
            group.setUnderlyingStipValue("STIP VALUE " + i);
        }
        assertEquals(3, comp.getUnderlyingStipsGroups().length);
        int i = 0;
        for (UnderlyingStipsGroup group : comp.getUnderlyingStipsGroups()) {
            assertEquals("STIP TYPE " + i, group.getUnderlyingStipType());
            assertEquals("STIP VALUE " + i, group.getUnderlyingStipValue());
            i++;
        }
    }

    /**
     * Test of addUnderlyingStipsGroup method, of class UnderlyingStipulations44.
     */
    @Test
    public void testAddUnderlyingStipsGroup() {
        UnderlyingStipulations comp = new UnderlyingStipulations50();
        assertNull(comp.getUnderlyingStipsGroups());
        comp.setNoUnderlyingStips(new Integer(2));
        assertEquals(2, comp.getUnderlyingStipsGroups().length);
        for (int i = 0; i < comp.getUnderlyingStipsGroups().length; i++) {
            UnderlyingStipsGroup group = comp.getUnderlyingStipsGroups()[i];
            group.setUnderlyingStipType("STIP TYPE " + i);
            group.setUnderlyingStipValue("STIP VALUE " + i);
        }
        comp.addUnderlyingStipsGroup();
        assertEquals(3, comp.getUnderlyingStipsGroups().length);
        comp.getUnderlyingStipsGroups()[2].setUnderlyingStipType("STIP TYPE 2");
        comp.getUnderlyingStipsGroups()[2].setUnderlyingStipValue("STIP VALUE 2");
        int i = 0;
        for (UnderlyingStipsGroup group : comp.getUnderlyingStipsGroups()) {
            assertEquals("STIP TYPE " + i, group.getUnderlyingStipType());
            assertEquals("STIP VALUE " + i, group.getUnderlyingStipValue());
            i++;
        }
        assertEquals(3, comp.getNoUnderlyingStips().intValue());
    }

    /**
     * Test of deleteUnderlyingStipsGroup method, of class UnderlyingStipulations44.
     */
    @Test
    public void testDeleteUnderlyingStipsGroup() {
        UnderlyingStipulations comp = new UnderlyingStipulations50();
        assertNull(comp.getUnderlyingStipsGroups());
        comp.setNoUnderlyingStips(new Integer(3));
        for (int i = 0; i < comp.getUnderlyingStipsGroups().length; i++) {
            UnderlyingStipsGroup group = comp.getUnderlyingStipsGroups()[i];
            group.setUnderlyingStipType("STIP TYPE " + i);
            group.setUnderlyingStipValue("STIP VALUE " + i);
        }
        assertEquals(3, comp.getUnderlyingStipsGroups().length);
        comp.deleteUnderlyingStipsGroup(1);
        assertEquals(2, comp.getUnderlyingStipsGroups().length);
        assertEquals(2, comp.getNoUnderlyingStips().intValue());
        assertEquals("STIP TYPE 2", comp.getUnderlyingStipsGroups()[1].getUnderlyingStipType());
        assertEquals("STIP VALUE 2", comp.getUnderlyingStipsGroups()[1].getUnderlyingStipValue());
    }

    /**
     * Test of clearUnderlyingStipsGroups method, of class UnderlyingStipulations44.
     */
    @Test
    public void testClearUnderlyingStipsGroups() {
        UnderlyingStipulations comp = new UnderlyingStipulations50();
        assertNull(comp.getUnderlyingStipsGroups());
        comp.setNoUnderlyingStips(new Integer(3));
        for (int i = 0; i < comp.getUnderlyingStipsGroups().length; i++) {
            UnderlyingStipsGroup group = comp.getUnderlyingStipsGroups()[i];
            group.setUnderlyingStipType("STIP TYPE " + i);
            group.setUnderlyingStipValue("STIP VALUE " + i);
        }
        assertEquals(3, comp.getUnderlyingStipsGroups().length);
        assertEquals(3, comp.getNoUnderlyingStips().intValue());
        int i = 0;
        for (UnderlyingStipsGroup group : comp.getUnderlyingStipsGroups()) {
            assertEquals("STIP TYPE " + i, group.getUnderlyingStipType());
            assertEquals("STIP VALUE " + i, group.getUnderlyingStipValue());
            i++;
        }
        comp.clearUnderlyingStipsGroups();
        assertNull(comp.getUnderlyingStipsGroups());
        assertNull(comp.getNoUnderlyingStips());
    }
}