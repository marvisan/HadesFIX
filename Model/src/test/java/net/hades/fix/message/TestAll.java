/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TestAll.java
 *
 * $Id: TestAll.java,v 1.2 2010-01-27 07:54:02 vrotaru Exp $
 */
package net.hades.fix.message;

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
    FIXTagTest.class,
    HeadTrail40Test.class,
    HeadTrail41Test.class,
    HeadTrail42Test.class,
    HeadTrail43Test.class,
    HeadTrail44Test.class,
    HeadTrailFIXT11Test.class,
    LogonMsgTest.class,
    LogoutMsgTest.class,
    HeartbeatMsgTest.class,
    RejectMsgTest.class,
    ResendRequestMsgTest.class,
    SequenceResetMsgTest.class,
    TestRequestMsgTest.class,
    net.hades.fix.message.builder.TestAll.class
})
public class TestAll {
}
