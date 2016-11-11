package net.hades.fix.commons.security;

import java.io.File;

import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 * Test cases for PasswordBank class.
 * 
 * @author vrotaru
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PasswordBankTest {

    private static String USER_DIR = System.getProperty("user.dir");

    public PasswordBankTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.setProperty("user.dir", USER_DIR + File.separator + "target" + File.separator + "tmp");
        System.out.println("user.dir=" + System.getProperty("user.dir"));
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.setProperty("user.dir", USER_DIR);
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void AA_testGetInstance() {
        System.out.println("getInstance");
        PasswordBank result = PasswordBank.getInstance();
        assertNotNull(result);
    }

    @Test
    public void AB_testCreateNewStore() {
        System.out.println("createNewStore");
        PasswordBank instance = PasswordBank.getInstance();
        String result = instance.createNewStore();
        assertTrue(result.contains("Successfully created the password container."));
    }

    @Test
    public void AC_testAddEntry() {
        System.out.println("addEntry");
        String entry = "test";
        String password = "test123";
        PasswordBank instance = PasswordBank.getInstance();
        String result = instance.addEntry(entry, password);
        assertTrue(result.contains("Successfully added a password entry to the password container."));
    }

    @Test
    public void AD_testChektEntryValue() {
        System.out.println("chektEntryValue");
        String entry = "test";
        char[] value = "test123".toCharArray();
        PasswordBank instance = PasswordBank.getInstance();
        boolean result = instance.chektEntryValue(entry, value);
        assertTrue(result);
    }

    @Test
    public void AE_testChektEntryValueNotExists() {
        System.out.println("testChektEntryValueNotExists");
        String entry = "testX";
        char[] value = "test123".toCharArray();
        PasswordBank instance = PasswordBank.getInstance();
        boolean result = instance.chektEntryValue(entry, value);
        assertFalse(result);
    }

    @Test
    public void AF_testGetEntryValue() {
        System.out.println("getEntryValue");
        String entry = "test";
        PasswordBank instance = PasswordBank.getInstance();
        char[] expResult = "test123".toCharArray();
        char[] result = instance.getEntryValue(entry);
        assertArrayEquals(expResult, result);
    }

    @Test
    public void AG_testGetEntryValueNotExists() {
        System.out.println("testGetEntryValueNotExists");
        String entry = "testX";
        PasswordBank instance = PasswordBank.getInstance();
        char[] result = instance.getEntryValue(entry);
        assertNull(result);
    }
}