
/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * PasswordBank.java
 *
 * $Id: PasswordBank.java,v 1.4 2010-12-05 08:18:23 vrotaru Exp $
 */
package net.hades.fix.commons.security;

import java.io.BufferedInputStream;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Container of passwords utility.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 18/05/2010
 */
public class PasswordBank {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final PasswordBank INSTANCE;

    private static final char[] KEYSTORE_PASSWORD = new char[] {'K', '1', 'a', '6', 'L', 'J', 'e', 'A', 'I', 'N', 'd', 'U', 'O', 'A', 's',
        'R', 'C', 'Y', 'o', '6', 'P', '3', 'e'};

    private static final char[] KEY_PASSWORD = new char[] {'M', '1', 'a', '2', 'N', '0', 'd', '7', 'O', '1', 'l', '9', 'i', '7', 'N',
        '0', 'e'};

    private static final String KEYSTORE_NAME       = "password.bank";
    private static final String LIST_ENTRIES_CMD    = "list";
    private static final String NEW_STORE_CMD       = "new";
    private static final String ADD_ENTRY_CMD       = "add";
    private static final String CHECK_ENTRY_CMD     = "check";
    private static final String REMOVE_ENTRY_CMD    = "remove";
    private static final String HELP_CMD            = "help";
    private static final String EXIT_CMD            = "exit";
    private static final String QUIT_CMD            = "quit";
    private static final String BYE_CMD             = "bye";

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        INSTANCE = new PasswordBank();
    }

    private PasswordBank() {
    }

    public static PasswordBank getInstance() {
        return INSTANCE;
    }

    public String createNewStore() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        FileOutputStream fos = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("JCEKS");
            keyStore.load(null, KEYSTORE_PASSWORD);
            File ksFile = new File(System.getProperty("user.dir") + File.separator + KEYSTORE_NAME);
            String result;
            if ((result = backupExistingStore(ksFile)) == null) {
		// check folder exists
		if (!ksFile.getParentFile().exists()) {
		    Files.createDirectories(ksFile.getParentFile().toPath());
		}
                fos = new FileOutputStream(ksFile);
                keyStore.store(fos, KEYSTORE_PASSWORD);
                formatter.format("%1$s %n %n", "Successfully created the password container.");
            } else {
                formatter.format("%1$s %n %n", result);
            }
        } catch (Exception ex) {
            String result = "Error creating the keystore : " + ex.getMessage() ;
            formatter.format("%1$s %n %n", result);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                String result = "Could not close the output stream : " + ex.getMessage() ;
                formatter.format("%1$s %n %n", result);
            }
        }

        return sb.toString();
    }

    public boolean chektEntryValue(String entry, char[] value) {
        boolean result = false;
        try {
            KeyStore keyStore = getKeyStore();
            SecretKey key = (SecretKey) keyStore.getKey(entry, KEY_PASSWORD);
            if (key != null) {
                result = Arrays.equals(new String(key.getEncoded()).toCharArray(), value);
            }
        } catch (Exception ex) {
            System.err.println("Error=" + ex.toString());
        }

        return result;
    }

    public boolean removetEntry(String entry) {
        boolean result = false;
        try {
            KeyStore keyStore = getKeyStore();
            SecretKey key = (SecretKey) keyStore.getKey(entry, KEY_PASSWORD);
            if (key != null) {
                keyStore.deleteEntry(entry);
                saveKeyStore(keyStore);
                result = true;
            }
        } catch (Exception ex) {
            System.err.println("Error=" + ex.toString());
        }

        return result;
    }

    public String[] listEntriesValue() {
        String[] result = new String[] {"No entries."};
        try {
            KeyStore keyStore = getKeyStore();
            Enumeration<String> aliases = keyStore.aliases();
            List<String> aliasesList = new ArrayList<String>();
            while (aliases.hasMoreElements()) {
                aliasesList.add(aliases.nextElement());
            }
            if (aliasesList.size() > 0) {
                result = aliasesList.toArray(new String[aliasesList.size()]);
            }
        } catch (Exception ex) {
            System.err.println("Error=" + ex.toString());
        }

        return result;
    }

    public char[] getEntryValue(String entry) {
        char[] result = null;
        try {
            KeyStore keyStore = getKeyStore();
            SecretKey key = (SecretKey) keyStore.getKey(entry, KEY_PASSWORD);
            if (key != null) {
                result = new String(key.getEncoded()).toCharArray();
            }
        } catch (Exception ex) {
            System.err.println("Error=" + ex.toString());
        }

        return result;
    }

    public String addEntry(String entry, String password) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        try {
            KeyStore keyStore = getKeyStore();
            SecretKey key = new SecretKeySpec(password.getBytes(), "Blowfish");
            keyStore.setKeyEntry(entry, key, KEY_PASSWORD, new Certificate[0]);
            saveKeyStore(keyStore);
            formatter.format("%1$s %n %n", "Successfully added a password entry to the password container.");
        } catch (Exception ex) {
            String result = "Error adding a keystore entry : " + ex.toString() ;
            formatter.format("%1$s %n %n", result);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Console console = System.console();
        if (console == null) {
            System.err.println("No console set.");
            System.exit(1);
        }

        String prompt = "pwdbank #>";
        console.printf("%n %n %s %n %n", "!!! The file cteated by this utility must be copied in the HadesFIX engine configuration directory. ");
        boolean exit = false;
        while (!exit) {
            String command = console.readLine(prompt);
            if (command != null && !command.trim().isEmpty()) {
                String[] commandTokens = command.split(" ");
                if (EXIT_CMD.equalsIgnoreCase(commandTokens[0]) || QUIT_CMD.equalsIgnoreCase(commandTokens[0])
                        || BYE_CMD.equalsIgnoreCase(commandTokens[0])) {
                    exit = true;
                } else if (LIST_ENTRIES_CMD.equalsIgnoreCase(commandTokens[0])) {
                    if (commandTokens.length != 1 || (commandTokens.length == 2 && HELP_CMD.equalsIgnoreCase(commandTokens[1]))) {
                        printListEntriesHelp(console);
                    } else {
                        String[] result = INSTANCE.listEntriesValue();
                        console.printf("%n");
                        for (String entry : result) {
                            console.printf("%1$s %n", entry);
                        }
                        console.printf("%n %n");
                    }
                } else if (NEW_STORE_CMD.equalsIgnoreCase(commandTokens[0])) {
                    if (commandTokens.length != 1 || (commandTokens.length == 2 && HELP_CMD.equalsIgnoreCase(commandTokens[1]))) {
                        printNewStoreHelp(console);
                    } else {
                        String result = INSTANCE.createNewStore();
                        console.printf("%n %1$s %n", result);
                    }
                } else if (ADD_ENTRY_CMD.equalsIgnoreCase(commandTokens[0])) {
                    if ((commandTokens.length != 3 || commandTokens[1] == null || commandTokens[1].trim().isEmpty() ||
                            commandTokens[2] == null || commandTokens[2].trim().isEmpty())
                            || (commandTokens.length == 2 && HELP_CMD.equalsIgnoreCase(commandTokens[1]))) {
                            printAddEntryHelp(console);
                    } else {
                        String result = INSTANCE.addEntry(commandTokens[1].trim(), commandTokens[2].trim());
                        commandTokens[1] = null;
                        commandTokens[2] = null;
                        console.printf("%n %1$s %n", result);
                    }
                } else if (CHECK_ENTRY_CMD.equalsIgnoreCase(commandTokens[0])) {
                    if ((commandTokens.length != 3 || commandTokens[1] == null || commandTokens[1].trim().isEmpty() ||
                            commandTokens[2] == null || commandTokens[2].trim().isEmpty())
                            || (commandTokens.length == 2 && HELP_CMD.equalsIgnoreCase(commandTokens[1]))) {
                            printCheckEntryHelp(console);
                    } else {
                        boolean result = INSTANCE.chektEntryValue(commandTokens[1].trim(), commandTokens[2].trim().toCharArray());
                        commandTokens[1] = null;
                        commandTokens[2] = null;
                        console.printf("%n %1$s %n %n", result ? "Matches the store value" : "Does not match the store value");
                    }
                } else if (REMOVE_ENTRY_CMD.equalsIgnoreCase(commandTokens[0])) {
                    if ((commandTokens.length != 2 || commandTokens[1] == null || commandTokens[1].trim().isEmpty())
                            || (commandTokens.length == 2 && HELP_CMD.equalsIgnoreCase(commandTokens[1]))) {
                            printRemoveEntryHelp(console);
                    } else {
                        boolean result = INSTANCE.removetEntry(commandTokens[1].trim());
                        commandTokens[1] = null;
                        console.printf("%n %1$s %n %n", result ? "Entry successfully removed" : "Entry does not exist in the password bank.");
                    }
                } else {
                    printHelp(console);
                }
            }
        }
        console.printf("%n %n %s %n %n", "Exiting password bank... Have a nice day!");
    }

    private String backupExistingStore(File storeFile) throws FileNotFoundException {
        String result = null;
        if (storeFile.exists()) {
            String backupFileName = storeFile.getAbsolutePath() + new SimpleDateFormat("-yyMMddhhmmss").format(new Date());
            File backupFile = new File(backupFileName);
            if (!storeFile.renameTo(backupFile)) {
                result = "Could not backup the existing store file [" + storeFile + "] to the new name [" + backupFileName + "].";
            }
        }
        return result;
    }

    private synchronized KeyStore getKeyStore() throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException {
        File ksFile = new File(System.getProperty("user.dir") + File.separator + KEYSTORE_NAME);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(ksFile));
        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        keyStore.load(bis, KEYSTORE_PASSWORD);

        return keyStore;
    }

    private synchronized void saveKeyStore(KeyStore keyStore) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException {
        File ksFile = new File(System.getProperty("user.dir") + File.separator + KEYSTORE_NAME);
        FileOutputStream fos = new FileOutputStream(ksFile);
        keyStore.store(fos, KEYSTORE_PASSWORD);
    }

    private static void printListEntriesHelp(Console console) {
        console.printf("%n %n %s %n", "The format of list entries command is:");
        console.printf("%s %n %n", LIST_ENTRIES_CMD);
    }

    private static void printNewStoreHelp(Console console) {
        console.printf("%n %n %s %n", "The format of new store command is:");
        console.printf("%s %n %n", NEW_STORE_CMD);
    }

    private static void printAddEntryHelp(Console console) {
        console.printf("%n %n %s %n", "The format of add entry command is:");
        console.printf("%s %n %n", ADD_ENTRY_CMD + " <entry> <password>");
    }

    private static void printCheckEntryHelp(Console console) {
        console.printf("%n %n %s %n", "The format of add entry command is:");
        console.printf("%s %n %n", CHECK_ENTRY_CMD + " <entry> <password>");
    }

    private static void printRemoveEntryHelp(Console console) {
        console.printf("%n %n %s %n", "The format of remove entry command is:");
        console.printf("%s %n %n", REMOVE_ENTRY_CMD + " <entry>");
    }

    private static void printHelp(Console console) {
        console.printf("%n %n %s %n %n", "Password Store utility supported commands are:");
        console.printf("%1$10s - %2$s %n %n", LIST_ENTRIES_CMD, "List all the entries in the password bank.");
        console.printf("%1$10s - %2$s %n %n", NEW_STORE_CMD, "creates a new password in the password bank.");
        console.printf("%1$10s - %2$s %n %n", ADD_ENTRY_CMD, "<entry name> <password> add a new password entry to the password bank.");
        console.printf("%1$10s - %2$s %n %n", CHECK_ENTRY_CMD, "<entry name> <password> check an entry password in the password bank.");
        console.printf("%1$10s - %2$s %n %n", REMOVE_ENTRY_CMD, "<entry name> removes the given entry from the password bank.");
        console.printf("%1$10s - %2$s %n %n", HELP_CMD, "displays this text.");
        console.printf("%1$10s - %2$s %n %n", EXIT_CMD, "exits the utility.");
        console.printf("%1$10s - %2$s %n %n", QUIT_CMD, "exits the utility.");
        console.printf("%1$10s - %2$s %n %n", BYE_CMD, "exits the utility.");
    }
}
