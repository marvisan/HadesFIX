/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Parties50Test.java
 *
 * $Id: Parties50Test.java,v 1.4 2009-11-24 11:16:45 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.Parties;
import net.hades.fix.message.comp.impl.v50.Parties50;
import net.hades.fix.message.group.PartyGroup;
import net.hades.fix.message.group.PartySubGroup;
import net.hades.fix.message.group.impl.v44.PartyGroup44;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;
import net.hades.fix.message.type.PartySubIDType;

/**
 * Test suite for Parties50 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 14/02/2009, 1:34:24 PM
 */
public class Parties50Test {

    public Parties50Test() {
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
     * Test of setNoPartyIDs method, of class Parties50.
     */
    @Test
    public void testSetNoPartyIDs() {
        Parties comp = new Parties50();
        assertNull(comp.getPartyIDGroups());
        comp.setNoPartyIDs(new Integer(3));
        for (int i = 0; i < comp.getNoPartyIDs().intValue(); i++) {
            PartyGroup group = comp.getPartyIDGroups()[i];
            group.setPartyID("ID " + i);
            group.setPartyIDSource(PartyIDSource.AustralianTaxFileNumber);
            group.setPartyRole(PartyRole.Agent);
            assertNull(group.getNoPartySubIDs());
            assertNull(group.getPartySubIDGroups());
            group.setNoPartySubIDs(new Integer(3));
            for (int j = 0; j < group.getNoPartySubIDs().intValue(); j++) {
                PartySubGroup group1 = group.getPartySubIDGroups()[j];
                group1.setPartySubID("SUB ID " + j);
                group1.setPartySubIDType(PartySubIDType.CSDParticipantMemberCode);
            }
        }
        assertEquals(3, comp.getPartyIDGroups().length);
        int i = 0;
        for (PartyGroup group : comp.getPartyIDGroups()) {
            assertEquals("ID " + i, group.getPartyID());
            assertEquals(PartyIDSource.AustralianTaxFileNumber, group.getPartyIDSource());
            assertEquals(PartyRole.Agent, group.getPartyRole());
            assertNotNull(group.getNoPartySubIDs());
            assertEquals(3, group.getNoPartySubIDs().intValue());
            assertNotNull(group.getPartySubIDGroups());
            assertEquals(3, group.getPartySubIDGroups().length);
            int j = 0;
            for (PartySubGroup group1 : group.getPartySubIDGroups()) {
                assertEquals("SUB ID " + j, group1.getPartySubID());
                assertEquals(PartySubIDType.CSDParticipantMemberCode, group1.getPartySubIDType());
                j++;
            }
            i++;
        }
    }

    /**
     * Test of addPartyIDGroup method, of class Parties50.
     */
    @Test
    public void testAddPartyIDGroup() {
        Parties comp = new Parties50();
        assertNull(comp.getPartyIDGroups());
        comp.setNoPartyIDs(new Integer(2));
        assertEquals(2, comp.getPartyIDGroups().length);
        for (int i = 0; i < comp.getNoPartyIDs().intValue(); i++) {
            PartyGroup group = comp.getPartyIDGroups()[i];
            group.setPartyID("ID " + i);
            group.setPartyIDSource(PartyIDSource.AustralianTaxFileNumber);
            group.setPartyRole(PartyRole.Agent);
            assertNull(group.getNoPartySubIDs());
            assertNull(group.getPartySubIDGroups());
            group.setNoPartySubIDs(new Integer(2));
            for (int j = 0; j < group.getNoPartySubIDs().intValue(); j++) {
                PartySubGroup group1 = group.getPartySubIDGroups()[j];
                group1.setPartySubID("SUB ID " + j);
                group1.setPartySubIDType(PartySubIDType.CSDParticipantMemberCode);
            }
        }
        for (int i = 0; i < comp.getNoPartyIDs().intValue(); i++) {
            PartyGroup group = comp.getPartyIDGroups()[i];
            group.addPartySubIDGroup();
            assertEquals(3, group.getNoPartySubIDs().intValue());
            assertEquals(3, group.getPartySubIDGroups().length);
            PartySubGroup group1 = group.getPartySubIDGroups()[2];
            group1.setPartySubID("SUB ID 2");
            group1.setPartySubIDType(PartySubIDType.CSDParticipantMemberCode);
        }
        comp.addPartyIDGroup();
        assertEquals(3, comp.getPartyIDGroups().length);
        comp.getPartyIDGroups()[2].setPartyID("ID 2");
        comp.getPartyIDGroups()[2].setPartyIDSource(PartyIDSource.AustralianTaxFileNumber);
        comp.getPartyIDGroups()[2].setPartyRole(PartyRole.Agent);
        comp.getPartyIDGroups()[2].setNoPartySubIDs(new Integer(3));
        for (int j = 0; j < comp.getPartyIDGroups()[2].getNoPartySubIDs().intValue(); j++) {
            PartySubGroup group1 = comp.getPartyIDGroups()[2].getPartySubIDGroups()[j];
            group1.setPartySubID("SUB ID " + j);
            group1.setPartySubIDType(PartySubIDType.CSDParticipantMemberCode);
        }
        int i = 0;
        for (PartyGroup group : comp.getPartyIDGroups()) {
            assertEquals("ID " + i, group.getPartyID());
            assertEquals(PartyIDSource.AustralianTaxFileNumber, group.getPartyIDSource());
            assertEquals(PartyRole.Agent, group.getPartyRole());
            assertNotNull(group.getNoPartySubIDs());
            assertEquals(3, group.getNoPartySubIDs().intValue());
            assertNotNull(group.getPartySubIDGroups());
            assertEquals(3, group.getPartySubIDGroups().length);
            int j = 0;
            for (PartySubGroup group1 : group.getPartySubIDGroups()) {
                assertEquals("SUB ID " + j, group1.getPartySubID());
                assertEquals(PartySubIDType.CSDParticipantMemberCode, group1.getPartySubIDType());
                j++;
            }
            i++;
        }
        assertEquals(3, comp.getNoPartyIDs().intValue());
    }

    /**
     * Test of deletePartyIDGroup method, of class Parties50.
     */
    @Test
    public void testDeletePartyIDGroup() {
        Parties comp = new Parties50();
        assertNull(comp.getPartyIDGroups());
        comp.setNoPartyIDs(new Integer(3));
        for (int i = 0; i < comp.getNoPartyIDs().intValue(); i++) {
            PartyGroup group = comp.getPartyIDGroups()[i];
            group.setPartyID("ID " + i);
            group.setPartyIDSource(PartyIDSource.AustralianTaxFileNumber);
            group.setPartyRole(PartyRole.Agent);
            assertNull(group.getNoPartySubIDs());
            assertNull(group.getPartySubIDGroups());
            group.setNoPartySubIDs(new Integer(3));
            for (int j = 0; j < group.getNoPartySubIDs().intValue(); j++) {
                PartySubGroup group1 = group.getPartySubIDGroups()[j];
                group1.setPartySubID("SUB ID " + j);
                group1.setPartySubIDType(PartySubIDType.CSDParticipantMemberCode);
            }
        }
        assertEquals(3, comp.getPartyIDGroups().length);
        comp.deletePartyIDGroup(1);
        assertEquals(2, comp.getPartyIDGroups().length);
        assertEquals(2, comp.getNoPartyIDs().intValue());
        assertEquals("ID 2", comp.getPartyIDGroups()[1].getPartyID());
        assertEquals(PartyIDSource.AustralianTaxFileNumber, comp.getPartyIDGroups()[1].getPartyIDSource());
        for (int i = 0; i < comp.getNoPartyIDs().intValue(); i++) {
            PartyGroup group = comp.getPartyIDGroups()[i];
            group.deletePartySubIDGroup(1);
            assertEquals(2, group.getPartySubIDGroups().length);
            assertEquals(2, group.getNoPartySubIDs().intValue());
            assertEquals("SUB ID 2", group.getPartySubIDGroups()[1].getPartySubID());
        }
    }

    /**
     * Test of clearPartyIDGroup method, of class Parties50.
     */
    @Test
    public void testClearPartyIDGroup() {
        Parties comp = new Parties50();
        assertNull(comp.getPartyIDGroups());
        comp.setNoPartyIDs(new Integer(2));
        assertEquals(2, comp.getPartyIDGroups().length);
        for (int i = 0; i < comp.getNoPartyIDs().intValue(); i++) {
            PartyGroup group = comp.getPartyIDGroups()[i];
            group.setPartyID("ID " + i);
            group.setPartyIDSource(PartyIDSource.AustralianTaxFileNumber);
            group.setPartyRole(PartyRole.Agent);
            assertNull(group.getNoPartySubIDs());
            assertNull(group.getPartySubIDGroups());
            group.setNoPartySubIDs(new Integer(2));
            for (int j = 0; j < group.getNoPartySubIDs().intValue(); j++) {
                PartySubGroup group1 = group.getPartySubIDGroups()[j];
                group1.setPartySubID("SUB ID " + j);
                group1.setPartySubIDType(PartySubIDType.CSDParticipantMemberCode);
            }
        }
        assertEquals(2, comp.getPartyIDGroups().length);
        assertEquals(2, comp.getNoPartyIDs().intValue());
        comp.getPartyIDGroups()[1].clearPartySubIDGroup();
        assertNull(comp.getPartyIDGroups()[1].getPartySubIDGroups());
        assertNull(comp.getPartyIDGroups()[1].getNoPartySubIDs());
        comp.clearPartyIDGroup();
        assertNull(comp.getPartyIDGroups());
        assertNull(comp.getNoPartyIDs());
    }

    /**
     * Test of getFragmentTags method, of class Parties50.
     */
    @Test
    public void testGetFragmentTags() {
        PartyGroup comp = new PartyGroup44();
        assertEquals(4, comp.getFragmentTags().size());
    }

    /**
     * Test of getFragmentAllTags method, of class Parties50.
     */
    @Test
    public void testGetFragmentAllTags() {
        PartyGroup comp = new PartyGroup44();
        assertEquals(6, comp.getFragmentAllTags().size());
    }
}