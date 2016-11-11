/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Stipulations43Test.java
 *
 * $Id: Stipulations43Test.java,v 1.1 2009-07-06 03:19:16 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.Stipulations;
import net.hades.fix.message.comp.impl.v43.Stipulations43;
import net.hades.fix.message.group.StipulationsGroup;

/**
 * Test suite for Stipulations43 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 15/02/2009, 5:13:42 PM
 */
public class Stipulations43Test {

    public Stipulations43Test() {
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
     * Test of setNoStipulations method, of class Stipulations43.
     */
    @Test
    public void testSetNoStipulations() {
        Stipulations comp = new Stipulations43();
        assertNull(comp.getStipulationsGroups());
        comp.setNoStipulations(new Integer(3));
        for (int i = 0; i < comp.getStipulationsGroups().length; i++) {
            StipulationsGroup group = comp.getStipulationsGroups()[i];
            group.setStipulationType("STIP TYPE " + i);
            group.setStipulationValue("STIP VALUE " + i);
        }
        assertEquals(3, comp.getStipulationsGroups().length);
        int i = 0;
        for (StipulationsGroup group : comp.getStipulationsGroups()) {
            assertEquals("STIP TYPE " + i, group.getStipulationType());
            assertEquals("STIP VALUE " + i, group.getStipulationValue());
            i++;
        }
    }

    /**
     * Test of addStipulationsGroup method, of class Stipulations43.
     */
    @Test
    public void testAddStipulationsGroup() {
        Stipulations comp = new Stipulations43();
        assertNull(comp.getStipulationsGroups());
        comp.setNoStipulations(new Integer(2));
        assertEquals(2, comp.getStipulationsGroups().length);
        for (int i = 0; i < comp.getStipulationsGroups().length; i++) {
            StipulationsGroup group = comp.getStipulationsGroups()[i];
            group.setStipulationType("STIP TYPE " + i);
            group.setStipulationValue("STIP VALUE " + i);
        }
        comp.addStipulationsGroup();
        assertEquals(3, comp.getStipulationsGroups().length);
        comp.getStipulationsGroups()[2].setStipulationType("STIP TYPE 2");
        comp.getStipulationsGroups()[2].setStipulationValue("STIP VALUE 2");
        int i = 0;
        for (StipulationsGroup group : comp.getStipulationsGroups()) {
            assertEquals("STIP TYPE " + i, group.getStipulationType());
            assertEquals("STIP VALUE " + i, group.getStipulationValue());
            i++;
        }
        assertEquals(3, comp.getNoStipulations().intValue());
    }

    /**
     * Test of deleteStipulationsGroup method, of class Stipulations43.
     */
    @Test
    public void testDeleteStipulationsGroup() {
        Stipulations comp = new Stipulations43();
        assertNull(comp.getStipulationsGroups());
        comp.setNoStipulations(new Integer(3));
        for (int i = 0; i < comp.getStipulationsGroups().length; i++) {
            StipulationsGroup group = comp.getStipulationsGroups()[i];
            group.setStipulationType("STIP TYPE " + i);
            group.setStipulationValue("STIP VALUE " + i);
        }
        assertEquals(3, comp.getStipulationsGroups().length);
        comp.deleteStipulationsGroup(1);
        assertEquals(2, comp.getStipulationsGroups().length);
        assertEquals(2, comp.getNoStipulations().intValue());
        assertEquals("STIP TYPE 2", comp.getStipulationsGroups()[1].getStipulationType());
        assertEquals("STIP VALUE 2", comp.getStipulationsGroups()[1].getStipulationValue());
    }

    /**
     * Test of clearStipulationsGroup method, of class Stipulations43.
     */
    @Test
    public void testClearStipulationsGroup() {
        Stipulations comp = new Stipulations43();
        assertNull(comp.getStipulationsGroups());
        comp.setNoStipulations(new Integer(3));
        for (int i = 0; i < comp.getStipulationsGroups().length; i++) {
            StipulationsGroup group = comp.getStipulationsGroups()[i];
            group.setStipulationType("STIP TYPE " + i);
            group.setStipulationValue("STIP VALUE " + i);
        }
        assertEquals(3, comp.getStipulationsGroups().length);
        assertEquals(3, comp.getNoStipulations().intValue());
        int i = 0;
        for (StipulationsGroup group : comp.getStipulationsGroups()) {
            assertEquals("STIP TYPE " + i, group.getStipulationType());
            assertEquals("STIP VALUE " + i, group.getStipulationValue());
            i++;
        }
        comp.clearStipulationsGroups();
        assertNull(comp.getStipulationsGroups());
        assertNull(comp.getNoStipulations());
    }

}