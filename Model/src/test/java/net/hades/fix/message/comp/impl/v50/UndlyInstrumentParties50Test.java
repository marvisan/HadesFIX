/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UndlyInstrumentParties50Test.java
 *
 * $Id: UndlyInstrumentParties50Test.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.UndlyInstrumentParties;
import net.hades.fix.message.comp.impl.v50.UndlyInstrumentParties50;
import net.hades.fix.message.group.UndlyInstrumentPartyIDGroup;
import net.hades.fix.message.type.PartyIDSource;
import net.hades.fix.message.type.PartyRole;

/**
 * Test suite for UndlyInstrumentParties50 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/01/2009, 11:11:30 AM
 */
public class UndlyInstrumentParties50Test {

    public UndlyInstrumentParties50Test() {
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
     * Test of setNoUndlyInstrumentParties method, of class UndlyInstrumentParties50.
     */
    @Test
    public void testSetNoUndlyInstrumentParties() {
        UndlyInstrumentParties comp = new UndlyInstrumentParties50();
        assertNull(comp.getUndlyInstrumentPartyIDGroups());
        comp.setNoUndlyInstrumentParties(new Integer(3));
        for (int i = 0; i < comp.getUndlyInstrumentPartyIDGroups().length; i++) {
            UndlyInstrumentPartyIDGroup group = comp.getUndlyInstrumentPartyIDGroups()[i];
            group.setUndlyInstrumentPartyID("ID " + i);
            group.setUndlyInstrumentPartyIDSource(PartyIDSource.AustralianBusinessNumber);
            group.setUndlyInstrumentPartyRole(PartyRole.AcceptableCounterparty);
        }
        assertEquals(3, comp.getUndlyInstrumentPartyIDGroups().length);
        int i = 0;
        for (UndlyInstrumentPartyIDGroup group : comp.getUndlyInstrumentPartyIDGroups()) {
            assertEquals("ID " + i, group.getUndlyInstrumentPartyID());
            assertEquals(PartyIDSource.AustralianBusinessNumber, group.getUndlyInstrumentPartyIDSource());
            assertEquals(PartyRole.AcceptableCounterparty, group.getUndlyInstrumentPartyRole());
            i++;
        }
    }

    /**
     * Test of addUndlyInstrumentPartyIDGroup method, of class UndlyInstrumentParties50.
     */
    @Test
    public void testAddUndlyInstrumentPartyIDGroup() {
        UndlyInstrumentParties comp = new UndlyInstrumentParties50();
        assertNull(comp.getUndlyInstrumentPartyIDGroups());
        comp.setNoUndlyInstrumentParties(new Integer(2));
        assertEquals(2, comp.getUndlyInstrumentPartyIDGroups().length);
        for (int i = 0; i < comp.getUndlyInstrumentPartyIDGroups().length; i++) {
            UndlyInstrumentPartyIDGroup group = comp.getUndlyInstrumentPartyIDGroups()[i];
            group.setUndlyInstrumentPartyID("ID " + i);
            group.setUndlyInstrumentPartyIDSource(PartyIDSource.AustralianBusinessNumber);
            group.setUndlyInstrumentPartyRole(PartyRole.AllocationEntity);
        }
        comp.addUndlyInstrumentPartyIDGroup();
        assertEquals(3, comp.getUndlyInstrumentPartyIDGroups().length);
        comp.getUndlyInstrumentPartyIDGroups()[2].setUndlyInstrumentPartyID("ID 2");
        comp.getUndlyInstrumentPartyIDGroups()[2].setUndlyInstrumentPartyIDSource(PartyIDSource.AustralianBusinessNumber);
        comp.getUndlyInstrumentPartyIDGroups()[2].setUndlyInstrumentPartyRole(PartyRole.AllocationEntity);
        int i = 0;
        for (UndlyInstrumentPartyIDGroup group : comp.getUndlyInstrumentPartyIDGroups()) {
            assertEquals("ID " + i, group.getUndlyInstrumentPartyID());
            assertEquals(PartyIDSource.AustralianBusinessNumber, group.getUndlyInstrumentPartyIDSource());
            assertEquals(PartyRole.AllocationEntity, group.getUndlyInstrumentPartyRole());
            i++;
        }
        assertEquals(3, comp.getNoUndlyInstrumentParties().intValue());
    }

    /**
     * Test of deleteUndlyInstrumentPartyIDGroup method, of class UndlyInstrumentParties50.
     */
    @Test
    public void testDeleteUndlyInstrumentPartyIDGroup() {
        UndlyInstrumentParties comp = new UndlyInstrumentParties50();
        assertNull(comp.getUndlyInstrumentPartyIDGroups());
        comp.setNoUndlyInstrumentParties(new Integer(3));
        for (int i = 0; i < comp.getUndlyInstrumentPartyIDGroups().length; i++) {
            UndlyInstrumentPartyIDGroup group = comp.getUndlyInstrumentPartyIDGroups()[i];
            group.setUndlyInstrumentPartyID("ID " + i);
            group.setUndlyInstrumentPartyIDSource(PartyIDSource.AustralianBusinessNumber);
            group.setUndlyInstrumentPartyRole(PartyRole.AllocationEntity);
        }
        assertEquals(3, comp.getUndlyInstrumentPartyIDGroups().length);
        comp.deleteUndlyInstrumentPartyIDGroup(1);
        assertEquals(2, comp.getUndlyInstrumentPartyIDGroups().length);
        assertEquals(2, comp.getNoUndlyInstrumentParties().intValue());
        assertEquals("ID 2", comp.getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartyID());
        assertEquals(PartyIDSource.AustralianBusinessNumber, comp.getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartyIDSource());
        assertEquals(PartyRole.AllocationEntity, comp.getUndlyInstrumentPartyIDGroups()[1].getUndlyInstrumentPartyRole());
    }

    /**
     * Test of clearUndlyInstrumentPartyIDGroup method, of class UndlyInstrumentParties50.
     */
    @Test
    public void testClearUndlyInstrumentPartyIDGroup() {
        UndlyInstrumentParties comp = new UndlyInstrumentParties50();
        assertNull(comp.getUndlyInstrumentPartyIDGroups());
        comp.setNoUndlyInstrumentParties(new Integer(3));
        for (int i = 0; i < comp.getUndlyInstrumentPartyIDGroups().length; i++) {
            UndlyInstrumentPartyIDGroup group = comp.getUndlyInstrumentPartyIDGroups()[i];
            group.setUndlyInstrumentPartyID("ID " + i);
            group.setUndlyInstrumentPartyIDSource(PartyIDSource.AustralianBusinessNumber);
            group.setUndlyInstrumentPartyRole(PartyRole.AcceptableCounterparty);
        }
        assertEquals(3, comp.getUndlyInstrumentPartyIDGroups().length);
        assertEquals(3, comp.getNoUndlyInstrumentParties().intValue());
        int i = 0;
        for (UndlyInstrumentPartyIDGroup group : comp.getUndlyInstrumentPartyIDGroups()) {
            assertEquals("ID " + i, group.getUndlyInstrumentPartyID());
            assertEquals(PartyIDSource.AustralianBusinessNumber, group.getUndlyInstrumentPartyIDSource());
            assertEquals(PartyRole.AcceptableCounterparty, group.getUndlyInstrumentPartyRole());
            i++;
        }
        comp.clearUndlyInstrumentPartyIDGroup();
        assertNull(comp.getUndlyInstrumentPartyIDGroups());
        assertNull(comp.getNoUndlyInstrumentParties());
    }
 }