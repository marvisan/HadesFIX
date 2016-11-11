/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TargetParties50SP2Test.java
 *
 * $Id: TargetParties50SP2Test.java,v 1.1 2009-07-06 03:19:10 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.TargetParties;
import net.hades.fix.message.comp.impl.v50sp2.TargetParties50SP2;
import net.hades.fix.message.group.TargetPartyGroup;

/**
 * Test suite for TargetParties50SP2 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 13/06/2009, 10:14:50 AM
 */
public class TargetParties50SP2Test {

    public TargetParties50SP2Test() {
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
     * Test of setNoTargetPartyIDs method, of class RateSources50SP2.
     */
    @Test
    public void testSetNoRateSources() {
        TargetParties comp = new TargetParties50SP2();
        assertNull(comp.getTargetPartyGroups());
        comp.setNoTargetPartyIDs(new Integer(3));
        for (int i = 0; i < comp.getTargetPartyGroups().length; i++) {
            assertNotNull(comp.getTargetPartyGroups()[i]);
        }
        assertEquals(3, comp.getTargetPartyGroups().length);
    }

    /**
     * Test of addRateSource method, of class TargetParties50SP2.
     */
    @Test
    public void testAddRateSource() {
        TargetParties comp = new TargetParties50SP2();
        assertNull(comp.getTargetPartyGroups());
        comp.setNoTargetPartyIDs(new Integer(2));
        assertEquals(2, comp.getTargetPartyGroups().length);
        for (int i = 0; i < comp.getTargetPartyGroups().length; i++) {
            assertNotNull(comp.getTargetPartyGroups()[i]);
        }
        comp.addTargetPartyGroup();
        assertEquals(3, comp.getTargetPartyGroups().length);
        for (TargetPartyGroup group : comp.getTargetPartyGroups()) {
            assertNotNull(group);
        }
        assertEquals(3, comp.getNoTargetPartyIDs().intValue());
    }

    /**
     * Test of deleteRateSource method, of class TargetParties50SP2.
     */
    @Test
    public void testDeleteRateSource() {
        TargetParties comp = new TargetParties50SP2();
        assertNull(comp.getTargetPartyGroups());
        comp.setNoTargetPartyIDs(new Integer(3));
        for (int i = 0; i < comp.getTargetPartyGroups().length; i++) {
            assertNotNull(comp.getTargetPartyGroups()[i]);
        }
        assertEquals(3, comp.getTargetPartyGroups().length);
        comp.deleteTargetPartyGroup(1);
        assertEquals(2, comp.getTargetPartyGroups().length);
        assertEquals(2, comp.getNoTargetPartyIDs().intValue());
    }

    /**
     * Test of clearTargetParties method, of class TargetParties50SP2.
     */
    @Test
    public void testClearTargetParties() {
        TargetParties comp = new TargetParties50SP2();
        assertNull(comp.getTargetPartyGroups());
        comp.setNoTargetPartyIDs(new Integer(3));
        for (int i = 0; i < comp.getTargetPartyGroups().length; i++) {
            assertNotNull(comp.getTargetPartyGroups()[i]);
        }
        assertEquals(3, comp.getTargetPartyGroups().length);
        assertEquals(3, comp.getNoTargetPartyIDs().intValue());
        for (TargetPartyGroup group : comp.getTargetPartyGroups()) {
            assertNotNull(group);
        }
        comp.clearTargetPartyGroups();
        assertNull(comp.getNoTargetPartyIDs());
        assertNull(comp.getTargetPartyGroups());
    }
}
