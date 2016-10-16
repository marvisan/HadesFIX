/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Parties43Test.java
 *
 * $Id: Parties43Test.java,v 1.1 2009-07-06 03:19:16 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.impl.v43.Parties43;
import net.hades.fix.message.group.PartyGroup;

/**
 * Test utility for Parties43 component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/05/2009, 8:17:25 PM
 */
public class Parties43Test {

    public Parties43Test() {
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
    public void testSetNoPartyIDs() {
        Parties comp = new Parties43();
        assertNull(comp.getPartyIDGroups());
        comp.setNoPartyIDs(new Integer(3));
        for (int i = 0; i < comp.getPartyIDGroups().length; i++) {
            PartyGroup group = comp.getPartyIDGroups()[i];
            group.setPartyID("ID " + i);
            group.setPartySubID("SUB ID " + i);
        }
        assertEquals(3, comp.getPartyIDGroups().length);
        int i = 0;
        for (PartyGroup group : comp.getPartyIDGroups()) {
            assertEquals("ID " + i, group.getPartyID());
            assertEquals("SUB ID " + i, group.getPartySubID());
            i++;
        }
    }

    /**
     * Test of addStipulationsGroup method, of class Stipulations43.
     */
    @Test
    public void testAddPartyIDGroup() {
        Parties comp = new Parties43();
        assertNull(comp.getPartyIDGroups());
        comp.setNoPartyIDs(new Integer(2));
        assertEquals(2, comp.getPartyIDGroups().length);
        for (int i = 0; i < comp.getPartyIDGroups().length; i++) {
            PartyGroup group = comp.getPartyIDGroups()[i];
            group.setPartyID("ID " + i);
            group.setPartySubID("SUB ID " + i);
        }
        comp.addPartyIDGroup();
        assertEquals(3, comp.getPartyIDGroups().length);
        comp.getPartyIDGroups()[2].setPartyID("ID 2");
        comp.getPartyIDGroups()[2].setPartySubID("SUB ID 2");
        int i = 0;
        for (PartyGroup group : comp.getPartyIDGroups()) {
            assertEquals("ID " + i, group.getPartyID());
            assertEquals("SUB ID " + i, group.getPartySubID());
            i++;
        }
        assertEquals(3, comp.getNoPartyIDs().intValue());
    }

    /**
     * Test of deleteStipulationsGroup method, of class Stipulations43.
     */
    @Test
    public void testDeletePartyIDGroup() {
        Parties comp = new Parties43();
        assertNull(comp.getPartyIDGroups());
        comp.setNoPartyIDs(new Integer(3));
        for (int i = 0; i < comp.getPartyIDGroups().length; i++) {
            PartyGroup group = comp.getPartyIDGroups()[i];
            group.setPartyID("ID " + i);
            group.setPartySubID("SUB ID " + i);
        }
        assertEquals(3, comp.getPartyIDGroups().length);
        comp.deletePartyIDGroup(1);
        assertEquals(2, comp.getPartyIDGroups().length);
        assertEquals(2, comp.getNoPartyIDs().intValue());
        assertEquals("ID 2", comp.getPartyIDGroups()[1].getPartyID());
        assertEquals("SUB ID 2", comp.getPartyIDGroups()[1].getPartySubID());
    }

    /**
     * Test of clearStipulationsGroup method, of class Stipulations43.
     */
    @Test
    public void testClearPartyIDGroup() {
        Parties comp = new Parties43();
        assertNull(comp.getPartyIDGroups());
        comp.setNoPartyIDs(new Integer(3));
        for (int i = 0; i < comp.getPartyIDGroups().length; i++) {
            PartyGroup group = comp.getPartyIDGroups()[i];
            group.setPartyID("ID " + i);
            group.setPartySubID("SUB ID " + i);
        }
        assertEquals(3, comp.getPartyIDGroups().length);
        assertEquals(3, comp.getNoPartyIDs().intValue());
        int i = 0;
        for (PartyGroup group : comp.getPartyIDGroups()) {
            assertEquals("ID " + i, group.getPartyID());
            assertEquals("SUB ID " + i, group.getPartySubID());
            i++;
        }
        comp.clearPartyIDGroup();
        assertNull(comp.getPartyIDGroups());
        assertNull(comp.getNoPartyIDs());
    }

}
