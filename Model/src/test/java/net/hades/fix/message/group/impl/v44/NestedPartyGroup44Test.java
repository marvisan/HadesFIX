/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NestedPartyGroup44Test.java
 *
 * $Id: NestedPartyGroup44Test.java,v 1.2 2009-11-21 09:57:24 vrotaru Exp $
 */
package net.hades.fix.message.group.impl.v44;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.group.NestedPartyGroup;
import net.hades.fix.message.group.NestedPartySubGroup;
import net.hades.fix.message.group.impl.v44.NestedPartyGroup44;

/**
 * Test suite for NestedPartyGroup44 class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 13/04/2009, 3:28:17 PM
 */
public class NestedPartyGroup44Test {

    public NestedPartyGroup44Test() {
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
     * Test of setNoNestedPartySubIDs method, of class NestedPartyGroup44.
     */
    @Test
    public void testSetNoNestedPartySubIDs() {
        NestedPartyGroup comp = new NestedPartyGroup44();
        assertNull(comp.getNstdPtysSubGroups());
        comp.setNoNestedPartySubIDs(new Integer(3));
        for (int i = 0; i < comp.getNstdPtysSubGroups().length; i++) {
            NestedPartySubGroup group = comp.getNstdPtysSubGroups()[i];
            group.setNestedPartySubID("NSTD SUB " + i);
        }
        assertEquals(3, comp.getNstdPtysSubGroups().length);
        int i = 0;
        for (NestedPartySubGroup group : comp.getNstdPtysSubGroups()) {
            assertEquals("NSTD SUB " + i, group.getNestedPartySubID());
            i++;
        }
    }

    /**
     * Test of addNstdPtysSubGroup method, of class NestedPartyGroup44.
     */
    @Test
    public void testAddNstdPtysSubGroup() {
        NestedPartyGroup comp = new NestedPartyGroup44();
        assertNull(comp.getNstdPtysSubGroups());
        comp.setNoNestedPartySubIDs(new Integer(2));
        assertEquals(2, comp.getNstdPtysSubGroups().length);
        for (int i = 0; i < comp.getNstdPtysSubGroups().length; i++) {
            NestedPartySubGroup group = comp.getNstdPtysSubGroups()[i];
            group.setNestedPartySubID("NSTD SUB " + i);
        }
        comp.addNstdPtysSubGroup();
        assertEquals(3, comp.getNstdPtysSubGroups().length);
        comp.getNstdPtysSubGroups()[2].setNestedPartySubID("NSTD SUB 2");
        int i = 0;
        for (NestedPartySubGroup group : comp.getNstdPtysSubGroups()) {
            assertEquals("NSTD SUB " + i, group.getNestedPartySubID());
            i++;
        }
        assertEquals(3, comp.getNoNestedPartySubIDs().intValue());
    }

    /**
     * Test of deleteNstdPtysSubGroup method, of class NestedPartyGroup44.
     */
    @Test
    public void testDeleteNstdPtysSubGroup() {
        NestedPartyGroup comp = new NestedPartyGroup44();
        assertNull(comp.getNstdPtysSubGroups());
        comp.setNoNestedPartySubIDs(new Integer(3));
        for (int i = 0; i < comp.getNstdPtysSubGroups().length; i++) {
            NestedPartySubGroup group = comp.getNstdPtysSubGroups()[i];
            group.setNestedPartySubID("NSTD SUB " + i);
        }
        assertEquals(3, comp.getNstdPtysSubGroups().length);
        comp.deleteNstdPtysSubGroup(1);
        assertEquals(2, comp.getNstdPtysSubGroups().length);
        assertEquals(2, comp.getNoNestedPartySubIDs().intValue());
        assertEquals("NSTD SUB 2", comp.getNstdPtysSubGroups()[1].getNestedPartySubID());
    }

    /**
     * Test of clearNstdPtysSubGroups method, of class NestedPartyGroup44.
     */
    @Test
    public void testClearNstdPtysSubGroups() {
        NestedPartyGroup comp = new NestedPartyGroup44();
        assertNull(comp.getNstdPtysSubGroups());
        comp.setNoNestedPartySubIDs(new Integer(3));
        for (int i = 0; i < comp.getNstdPtysSubGroups().length; i++) {
            NestedPartySubGroup group = comp.getNstdPtysSubGroups()[i];
            group.setNestedPartySubID("NSTD SUB " + i);
        }
        assertEquals(3, comp.getNstdPtysSubGroups().length);
        assertEquals(3, comp.getNoNestedPartySubIDs().intValue());
        int i = 0;
        for (NestedPartySubGroup group : comp.getNstdPtysSubGroups()) {
            assertEquals("NSTD SUB " + i, group.getNestedPartySubID());
            i++;
        }
        comp.clearNstdPtysSubGroups();
        assertNull(comp.getNstdPtysSubGroups());
        assertNull(comp.getNoNestedPartySubIDs());
    }

    private void checkUnsupportedUnderlyingInstrumentException(Exception ex) {
        assertEquals("This tag is not supported in [NestedPartyGroup] group version [4.4].", ex.getMessage());
    }

}
