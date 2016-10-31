/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * InstrumentLeg44Test.java
 *
 * $Id: InstrumentLeg44Test.java,v 1.2 2009-11-21 09:57:28 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.comp.InstrumentLeg;
import net.hades.fix.message.comp.impl.v44.InstrumentLeg44;
import net.hades.fix.message.group.LegSecurityAltIDGroup;

/**
 * Test suite for InstrumentLeg44 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 08/01/2009, 8:43:42 PM
 */
public class InstrumentLeg44Test {

    public InstrumentLeg44Test() {
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
     * Test of setNoLegSecurityAltID method, of class InstrumentLeg44.
     */
    @Test
    public void testSetNoLegSecurityAltID() {
        InstrumentLeg comp = new InstrumentLeg44();
        assertNull(comp.getLegSecurityAltIDGroups());
        comp.setNoLegSecurityAltID(new Integer(3));
        for (int i = 0; i < comp.getLegSecurityAltIDGroups().length; i++) {
            LegSecurityAltIDGroup group = comp.getLegSecurityAltIDGroups()[i];
            group.setLegSecurityAltID("SEC ALT ID " + i);
            group.setLegSecurityAltIDSource("SEC ALT ID SOURCE " + i);
        }
        assertEquals(3, comp.getLegSecurityAltIDGroups().length);
        int i = 0;
        for (LegSecurityAltIDGroup group : comp.getLegSecurityAltIDGroups()) {
            assertEquals("SEC ALT ID " + i, group.getLegSecurityAltID());
            assertEquals("SEC ALT ID SOURCE " + i, group.getLegSecurityAltIDSource());
            i++;
        }
    }

    /**
     * Test of addLegSecurityAltIDGroup method, of class InstrumentLeg44.
     */
    @Test
    public void testAddLegSecurityAltIDGroup() {
        InstrumentLeg comp = new InstrumentLeg44();
        assertNull(comp.getLegSecurityAltIDGroups());
        comp.setNoLegSecurityAltID(new Integer(2));
        assertEquals(2, comp.getLegSecurityAltIDGroups().length);
        for (int i = 0; i < comp.getLegSecurityAltIDGroups().length; i++) {
            LegSecurityAltIDGroup group = comp.getLegSecurityAltIDGroups()[i];
            group.setLegSecurityAltID("SEC ALT ID " + i);
            group.setLegSecurityAltIDSource("SEC ALT ID SOURCE " + i);
        }
        comp.addLegSecurityAltIDGroup();
        assertEquals(3, comp.getLegSecurityAltIDGroups().length);
        comp.getLegSecurityAltIDGroups()[2].setLegSecurityAltID("SEC ALT ID 2");
        comp.getLegSecurityAltIDGroups()[2].setLegSecurityAltIDSource("SEC ALT ID SOURCE 2");
        int i = 0;
        for (LegSecurityAltIDGroup group : comp.getLegSecurityAltIDGroups()) {
            assertEquals("SEC ALT ID " + i, group.getLegSecurityAltID());
            assertEquals("SEC ALT ID SOURCE " + i, group.getLegSecurityAltIDSource());
            i++;
        }
        assertEquals(3, comp.getNoLegSecurityAltID().intValue());
    }

    /**
     * Test of deleteLegSecurityAltIDGroup method, of class InstrumentLeg44.
     */
    @Test
    public void testDeleteLegSecurityAltIDGroup() {
        InstrumentLeg comp = new InstrumentLeg44();
        assertNull(comp.getLegSecurityAltIDGroups());
        comp.setNoLegSecurityAltID(new Integer(3));
        for (int i = 0; i < comp.getLegSecurityAltIDGroups().length; i++) {
            LegSecurityAltIDGroup group = comp.getLegSecurityAltIDGroups()[i];
            group.setLegSecurityAltID("SEC ALT ID " + i);
            group.setLegSecurityAltIDSource("SEC ALT ID SOURCE " + i);
        }
        assertEquals(3, comp.getLegSecurityAltIDGroups().length);
        comp.deleteLegSecurityAltIDGroup(1);
        assertEquals(2, comp.getLegSecurityAltIDGroups().length);
        assertEquals(2, comp.getNoLegSecurityAltID().intValue());
        assertEquals("SEC ALT ID 2", comp.getLegSecurityAltIDGroups()[1].getLegSecurityAltID());
        assertEquals("SEC ALT ID SOURCE 2", comp.getLegSecurityAltIDGroups()[1].getLegSecurityAltIDSource());
    }

    /**
     * Test of clearLegSecurityAltIDGroups method, of class InstrumentLeg44.
     */
    @Test
    public void testClearLegSecurityAltIDGroups() {
        InstrumentLeg comp = new InstrumentLeg44();
        assertNull(comp.getLegSecurityAltIDGroups());
        comp.setNoLegSecurityAltID(new Integer(3));
        for (int i = 0; i < comp.getLegSecurityAltIDGroups().length; i++) {
            LegSecurityAltIDGroup group = comp.getLegSecurityAltIDGroups()[i];
            group.setLegSecurityAltID("SEC ALT ID " + i);
            group.setLegSecurityAltIDSource("SEC ALT ID SOURCE " + i);
        }
        assertEquals(3, comp.getLegSecurityAltIDGroups().length);
        assertEquals(3, comp.getNoLegSecurityAltID().intValue());
        int i = 0;
        for (LegSecurityAltIDGroup group : comp.getLegSecurityAltIDGroups()) {
            assertEquals("SEC ALT ID " + i, group.getLegSecurityAltID());
            assertEquals("SEC ALT ID SOURCE " + i, group.getLegSecurityAltIDSource());
            i++;
        }
        comp.clearLegSecurityAltIDGroups();
        assertNull(comp.getNoLegSecurityAltID());
        assertNull(comp.getLegSecurityAltIDGroups());
    }

    private void checkUnsupportedInstrumentLegException(Exception ex) {
        assertEquals("This tag is not supported in [InstrumentLeg] component version [4.4].", ex.getMessage());
    }

}