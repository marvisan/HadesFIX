/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DesCrypterTest.java
 *
 * $Id: DesCrypterTest.java,v 1.1 2009-07-06 03:19:19 vrotaru Exp $
 */
package net.hades.fix.message.crypt;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import net.hades.fix.message.crypt.Crypter;
import net.hades.fix.message.crypt.CrypterData;
import net.hades.fix.message.crypt.CrypterSelector;
import net.hades.fix.message.type.EncryptMethod;
import net.hades.fix.message.util.CryptUtil;

/**
 * Test suite for DesCrypter class.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 28/08/2008, 20:36:46
 */
public class DesCrypterTest {
    
    private static final String MSG_TO_ENCRYPT = "This is the message to encrypt";

    public DesCrypterTest() {
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
     * Test of encrypt method, of class DesCrypter.
     * @throws Exception 
     */
    @Test
    public void testEncryptDecrypt() throws Exception {
        CrypterData crypterData = new CrypterData(EncryptMethod.DES, "SecretPassword".getBytes("UTF-8"));
        Crypter crypter = CrypterSelector.createCrypter(crypterData);
        byte[] actual = crypter.encrypt(MSG_TO_ENCRYPT.getBytes("UTF-8"));
        System.out.println("actual=" + CryptUtil.bytes2hex(actual));
        byte[] expected = crypter.decrypt(actual);
        System.out.println("message=" + new String(expected));
        assertEquals(MSG_TO_ENCRYPT, new String(expected, "UTF-8"));
    }

}