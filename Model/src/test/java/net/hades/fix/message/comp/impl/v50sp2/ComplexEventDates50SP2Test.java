/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEventDates50SP2Test.java
 *
 * $Id: ComplexEventDates50SP2Test.java,v 1.1 2009-07-06 03:19:10 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp2;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.ComplexEventDates;
import net.hades.fix.message.comp.impl.v50sp2.ComplexEventDates50SP2;
import net.hades.fix.message.group.ComplexEventDateGroup;

/**
 * Test suite for ComplexEventDates50SP2 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/01/2009, 11:32:14 AM
 */
public class ComplexEventDates50SP2Test {

    public ComplexEventDates50SP2Test() {
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
     * Test of setNoComplexEventDateGroup method, of class ComplexEventDates50SP2.
     */
    @Test
    public void testSetNoComplexEventDateGroup() {
        ComplexEventDates comp = new ComplexEventDates50SP2();
        assertNull(comp.getComplexEventDateGroups());
        comp.setNoComplexEventDates(new Integer(3));
        for (int i = 0; i < comp.getComplexEventDateGroups().length; i++) {
            ComplexEventDateGroup group = comp.getComplexEventDateGroups()[i];
            group.setComplexEventStartDate(new Date());
        }
        assertEquals(3, comp.getComplexEventDateGroups().length);
        for (ComplexEventDateGroup group : comp.getComplexEventDateGroups()) {
            assertNotNull(group.getComplexEventStartDate());
            assertNull(group.getComplexEventEndDate());
        }
    }

    /**
     * Test of addComplexEventDateGroup method, of class ComplexEventDates50SP2.
     */
    @Test
    public void testAddComplexEventDateGroup() {
        ComplexEventDates comp = new ComplexEventDates50SP2();
        assertNull(comp.getComplexEventDateGroups());
        comp.setNoComplexEventDates(new Integer(2));
        assertEquals(2, comp.getComplexEventDateGroups().length);
        for (int i = 0; i < comp.getComplexEventDateGroups().length; i++) {
            ComplexEventDateGroup group = comp.getComplexEventDateGroups()[i];
            group.setComplexEventStartDate(new Date());
        }
        comp.addComplexEventDateGroup();
        assertEquals(3, comp.getComplexEventDateGroups().length);
        comp.getComplexEventDateGroups()[2].setComplexEventStartDate(new Date());
        for (ComplexEventDateGroup group : comp.getComplexEventDateGroups()) {
            assertNotNull(group.getComplexEventStartDate());
            assertNull(group.getComplexEventEndDate());
        }
        assertEquals(3, comp.getNoComplexEventDates().intValue());
    }

    /**
     * Test of deleteComplexEventDateGroup method, of class ComplexEventDates50SP2.
     */
    @Test
    public void testDeleteComplexEventDateGroup() {
        ComplexEventDates comp = new ComplexEventDates50SP2();
        assertNull(comp.getComplexEventDateGroups());
        comp.setNoComplexEventDates(new Integer(3));
        for (int i = 0; i < comp.getComplexEventDateGroups().length; i++) {
            ComplexEventDateGroup group = comp.getComplexEventDateGroups()[i];
            group.setComplexEventStartDate(new Date());
        }
        assertEquals(3, comp.getComplexEventDateGroups().length);
        assertEquals(3, comp.getNoComplexEventDates().intValue());
        comp.deleteComplexEventDateGroup(1);
        assertEquals(2, comp.getComplexEventDateGroups().length);
        assertEquals(2, comp.getNoComplexEventDates().intValue());
        assertNotNull(comp.getComplexEventDateGroups()[1].getComplexEventStartDate());
        assertNull(comp.getComplexEventDateGroups()[1].getComplexEventEndDate());
    }

    /**
     * Test of clearComplexEventDateGroup method, of class ComplexEventDates50SP2.
     */
    @Test
    public void testClearComplexEventDateGroup() {
        ComplexEventDates comp = new ComplexEventDates50SP2();
        assertNull(comp.getComplexEventDateGroups());
        comp.setNoComplexEventDates(new Integer(3));
        for (int i = 0; i < comp.getComplexEventDateGroups().length; i++) {
            ComplexEventDateGroup group = comp.getComplexEventDateGroups()[i];
            group.setComplexEventStartDate(new Date());
        }
        assertEquals(3, comp.getComplexEventDateGroups().length);
        assertEquals(3, comp.getNoComplexEventDates().intValue());
        for (ComplexEventDateGroup group : comp.getComplexEventDateGroups()) {
            assertNotNull(group.getComplexEventStartDate());
            assertNull(group.getComplexEventEndDate());
        }
        comp.clearComplexEventDateGroups();
        assertNull(comp.getNoComplexEventDates());
        assertNull(comp.getComplexEventDateGroups());
    }
}