/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.1 2009-07-06 03:18:49 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all the message Test Cases.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 01/11/2008, 6:28:48 PM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    Instrument50Test.class,
    InstrumentLeg50Test.class,
    UnderlyingInstrument50Test.class,
    UnderlyingStipulations50Test.class,
    UndlyInstrumentParties50Test.class,
    Parties50Test.class,
    Stipulations50Test.class,
    LegStipulations50Test.class
})
public class TestAll {
}
