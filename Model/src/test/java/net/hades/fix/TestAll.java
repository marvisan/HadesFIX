/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 */
package net.hades.fix;

import net.hades.fix.message.crypt.DesCrypterTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all FIX engine Test Cases.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    net.hades.fix.message.TestAll.class,
    DesCrypterTest.class,
    net.hades.fix.message.util.TestAll.class,
    net.hades.fix.message.group.TestAll.class,
    net.hades.fix.message.comp.TestAll.class,
    net.hades.fix.message.impl.v40.TestAll.class,
    net.hades.fix.message.impl.v41.TestAll.class,
    net.hades.fix.message.impl.v41.fixml.TestAll.class,
    net.hades.fix.message.impl.v42.TestAll.class,
    net.hades.fix.message.impl.v42.fixml.TestAll.class,
    net.hades.fix.message.impl.v43.TestAll.class,
    net.hades.fix.message.impl.v43.fixml.TestAll.class,
    net.hades.fix.message.impl.v44.TestAll.class,
    net.hades.fix.message.impl.v44.fixml.TestAll.class,
    net.hades.fix.message.impl.v50.TestAll.class,
    net.hades.fix.message.impl.v50.fixml.TestAll.class,
    net.hades.fix.message.impl.v50sp1.TestAll.class,
    net.hades.fix.message.impl.v50sp1.fixml.TestAll.class
})
public class TestAll {
}