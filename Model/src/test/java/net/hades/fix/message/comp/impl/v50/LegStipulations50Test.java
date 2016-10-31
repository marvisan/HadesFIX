/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LegStipulations50Test.java
 *
 * $Id: LegStipulations50Test.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.LegStipulations;
import net.hades.fix.message.comp.impl.v50.LegStipulations50;
import net.hades.fix.message.group.LegStipulationsGroup;

/**
 * Test suite for LegStipulations50 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 15/02/2009, 6:59:34 PM
 */
public class LegStipulations50Test {

    public LegStipulations50Test() {
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
     * Test of setNoLegStipulations method, of class LegStipulations50.
     */
    @Test
    public void testSetNoLegStipulations() {
        LegStipulations comp = new LegStipulations50();
        assertNull(comp.getLegStipulationsGroups());
        comp.setNoLegStipulations(new Integer(3));
        for (int i = 0; i < comp.getLegStipulationsGroups().length; i++) {
            LegStipulationsGroup group = comp.getLegStipulationsGroups()[i];
            group.setLegStipulationType("STIP TYPE " + i);
            group.setLegStipulationValue("STIP VALUE " + i);
        }
        assertEquals(3, comp.getLegStipulationsGroups().length);
        int i = 0;
        for (LegStipulationsGroup group : comp.getLegStipulationsGroups()) {
            assertEquals("STIP TYPE " + i, group.getLegStipulationType());
            assertEquals("STIP VALUE " + i, group.getLegStipulationValue());
            i++;
        }
    }

    /**
     * Test of addLegStipulationsGroup method, of class LegStipulations50.
     */
    @Test
    public void testAddLegStipulationsGroup() {
        LegStipulations comp = new LegStipulations50();
        assertNull(comp.getLegStipulationsGroups());
        comp.setNoLegStipulations(new Integer(2));
        assertEquals(2, comp.getLegStipulationsGroups().length);
        for (int i = 0; i < comp.getLegStipulationsGroups().length; i++) {
            LegStipulationsGroup group = comp.getLegStipulationsGroups()[i];
            group.setLegStipulationType("STIP TYPE " + i);
            group.setLegStipulationValue("STIP VALUE " + i);
        }
        comp.addLegStipulationsGroup();
        assertEquals(3, comp.getLegStipulationsGroups().length);
        comp.getLegStipulationsGroups()[2].setLegStipulationType("STIP TYPE 2");
        comp.getLegStipulationsGroups()[2].setLegStipulationValue("STIP VALUE 2");
        int i = 0;
        for (LegStipulationsGroup group : comp.getLegStipulationsGroups()) {
            assertEquals("STIP TYPE " + i, group.getLegStipulationType());
            assertEquals("STIP VALUE " + i, group.getLegStipulationValue());
            i++;
        }
        assertEquals(3, comp.getNoLegStipulations().intValue());
    }

    /**
     * Test of deleteStipulationsGroup method, of class Stipulations44.
     */
    @Test
    public void testDeleteStipulationsGroup() {
        LegStipulations comp = new LegStipulations50();
        assertNull(comp.getLegStipulationsGroups());
        comp.setNoLegStipulations(new Integer(3));
        for (int i = 0; i < comp.getLegStipulationsGroups().length; i++) {
            LegStipulationsGroup group = comp.getLegStipulationsGroups()[i];
            group.setLegStipulationType("STIP TYPE " + i);
            group.setLegStipulationValue("STIP VALUE " + i);
        }
        assertEquals(3, comp.getLegStipulationsGroups().length);
        comp.deleteLegStipulationsGroup(1);
        assertEquals(2, comp.getLegStipulationsGroups().length);
        assertEquals(2, comp.getNoLegStipulations().intValue());
        assertEquals("STIP TYPE 2", comp.getLegStipulationsGroups()[1].getLegStipulationType());
        assertEquals("STIP VALUE 2", comp.getLegStipulationsGroups()[1].getLegStipulationValue());
    }

    /**
     * Test of clearLegStipulationsGroup method, of class LegStipulations50.
     */
    @Test
    public void testClearLegStipulationsGroup() {
        LegStipulations comp = new LegStipulations50();
        assertNull(comp.getLegStipulationsGroups());
        comp.setNoLegStipulations(new Integer(3));
        for (int i = 0; i < comp.getLegStipulationsGroups().length; i++) {
            LegStipulationsGroup group = comp.getLegStipulationsGroups()[i];
            group.setLegStipulationType("STIP TYPE " + i);
            group.setLegStipulationValue("STIP VALUE " + i);
        }
        assertEquals(3, comp.getLegStipulationsGroups().length);
        assertEquals(3, comp.getNoLegStipulations().intValue());
        int i = 0;
        for (LegStipulationsGroup group : comp.getLegStipulationsGroups()) {
            assertEquals("STIP TYPE " + i, group.getLegStipulationType());
            assertEquals("STIP VALUE " + i, group.getLegStipulationValue());
            i++;
        }
        comp.clearLegStipulationsGroups();
        assertNull(comp.getLegStipulationsGroups());
        assertNull(comp.getNoLegStipulations());
    }
}