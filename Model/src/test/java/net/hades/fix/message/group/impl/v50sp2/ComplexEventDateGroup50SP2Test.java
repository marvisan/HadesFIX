/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComplexEventDateGroup50SP2Test.java
 *
 * $Id: ComplexEventDateGroup50SP2Test.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v50sp2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.group.ComplexEventDateGroup;
import net.hades.fix.message.group.impl.v50sp2.ComplexEventDateGroup50SP2;

/**
 * Test suite for ComplexEventDateGroup50SP2 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 11/01/2009, 11:32:14 AM
 */
public class ComplexEventDateGroup50SP2Test {

    public ComplexEventDateGroup50SP2Test() {
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
     * Test of setComplexEventDateGroup method, of class Instrument50SP2.
     */
    @Test
    public void testSetComplexEventTimes() {
        ComplexEventDateGroup comp = new ComplexEventDateGroup50SP2();
        assertNull(comp.getComplexEventTimes());
        comp.setComplexEventTimes();
        assertNotNull(comp.getComplexEventTimes());
    }

    /**
     * Test of clearSecurityXML method, of class Instrument50SP2.
     */
    @Test
    public void testClearComplexEventTimes() {
        ComplexEventDateGroup comp = new ComplexEventDateGroup50SP2();
        assertNull(comp.getComplexEventTimes());
        comp.setComplexEventTimes();
        assertNotNull(comp.getComplexEventTimes());
        comp.clearComplexEventTimes();
        assertNull(comp.getComplexEventTimes());
    }
}