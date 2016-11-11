/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.1 2009-07-06 03:18:57 vrotaru Exp $
 */
package net.hades.fix.message.group;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all the group Test Cases.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 31/03/2009, 9:02:53 AM
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        net.hades.fix.message.group.impl.v42.TestAll.class,
        net.hades.fix.message.group.impl.v43.TestAll.class,
        net.hades.fix.message.group.impl.v44.TestAll.class,
        net.hades.fix.message.group.impl.v50.TestAll.class,
        net.hades.fix.message.group.impl.v50sp1.TestAll.class,
        net.hades.fix.message.group.impl.v50sp2.TestAll.class
})
public class TestAll {
}