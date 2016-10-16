/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LoginTest.java
 *
 * $Id: LoginTest.java,v 1.3 2010-05-09 11:38:01 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol;

import net.hades.fix.engine.HadesInstance;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests a login sequenece.
 * 
 * @author vrotaru
 */
public class LoginTest {

    public LoginTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        System.out.println("Config dir=/hades/HadesEngine");
        System.setProperty("hades.engine.config.file", "/hades/HadesEngine/LoginTest.hades.xml");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void start() throws Exception {
        HadesInstance engine = new HadesInstance();
        engine.initialise();
        engine.startEngine();
        engine.waitToExit();
    }

}