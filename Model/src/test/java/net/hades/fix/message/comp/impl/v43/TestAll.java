/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.3 2009-11-21 09:57:28 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v43;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all the message Test Cases.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 01/11/2008, 6:28:48 PM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    Instrument43Test.class,
    Stipulations43Test.class,
    Parties43Test.class,
    UnderlyingInstrument43Test.class
})
public class TestAll {
}
