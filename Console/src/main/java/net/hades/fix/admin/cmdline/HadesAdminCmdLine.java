/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HadesAdminCmdLine.java
 *
 * $Id: HadesAdminCmdLine.java,v 1.9 2011-03-17 07:36:17 vrotaru Exp $
 */
package net.hades.fix.admin.cmdline;

import net.hades.fix.admin.command.Command;
import net.hades.fix.admin.console.data.CmdLineResult;
import net.hades.fix.admin.session.SessionException;

import java.io.Console;
import java.util.Formatter;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.admin.session.HadesEngineConnectionManager;

/**
 * CommandName line utility for administrating a fix engine instance.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 * @created 08/05/2010
 */
public class HadesAdminCmdLine {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final Logger LOGGER = Logger.getLogger(HadesAdminCmdLine.class.getName());

    private static final String NEW_LINE = System.getProperty("line.separator");

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private String hepText;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    private HadesAdminCmdLine() {
        setHelpText();
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public static void main (String[] args) {

        Console console = System.console();
        if (console == null) {
            System.err.println("The console is not set for this JVM.");
            System.exit(1);
        }
        String prompt = "hadesadmin #> ";
        HadesAdminCmdLine cmdLine = new HadesAdminCmdLine();
        boolean exit = false;
        String command;
        console.printf("%n %n");
        String instanceName;
        try {
            instanceName = HadesEngineConnectionManager.getEngineSession().getHadesInstanceName();
            console.printf("%1$-50s %n", "Welcome to HadesAdmin.");
            console.printf("Connected to HadesFIX engine instance : %1$s %n", instanceName);
            console.printf("Type <help> for a list of supported commands. %n");
        } catch (SessionException ex) {
            console.printf("Error connecting to HadesFIX engine instance. Error was : %1$s %n", ex.getMessage());
            System.exit(1);
        }
        
        while (!exit) {
            console.printf("%n");
            command = console.readLine("%s", prompt);
            LOGGER.log(Level.FINEST, "Command --> {0}", command);
            if (command != null && command.trim().length() > 0) {
                if (CommandName.QUIT.getValue().equalsIgnoreCase(command) || CommandName.EXIT.getValue().equalsIgnoreCase(command)
                        || CommandName.BYE.getValue().equalsIgnoreCase(command)) {
                    exit = true;
                } else if (CommandName.HELP.getValue().equalsIgnoreCase(command)) {
                    System.out.println(cmdLine.hepText);
                } else {
                    Command cmd = Command.getCommand(command);
                    if (cmd != null) {
                        CmdLineResult result = cmd.executeCmdline();
                        for (Iterator<String> it = result.iterator(); it.hasNext();) {
                            String row = it.next();
                            if (row.length() > 0) {
                                console.printf(row);
                            }
                        }
                    } else {
                        console.printf("Unknown command : %1$5s. Please type  <help> for a list of supported commands. %n", command);
                    }
                }
            }
        }
        console.printf("%n %n Quiting HadesAdmin... have a nice day. %n %n");
   }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">

    private void setHelpText() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        sb.append(NEW_LINE);
        sb.append("Hades FIX engine commands:").append(NEW_LINE).append(NEW_LINE);
        for (CommandName command : CommandName.values()) {
            formatter.format("%1$4s : %2$-73s %n", command.getValue(), command.getHelp());
        }

        hepText = sb.toString();
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
