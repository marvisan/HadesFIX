/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.2 2009-11-21 09:57:29 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v44;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all the message Test Cases.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 01/11/2008, 6:28:48 PM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    Instrument44Test.class,
    InstrumentLeg44Test.class,
    UnderlyingInstrument44Test.class,
    UnderlyingStipulations44Test.class,
    Stipulations44Test.class,
    LegStipulations44Test.class
})
public class TestAll {
}
