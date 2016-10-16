/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ShutdownCommand.java
 *
 * $Id: ShutdownImmediateCommand.java,v 1.3 2011-03-21 04:53:03 vrotaru Exp $
 */
package net.hades.fix.admin.command;

import net.hades.fix.admin.console.data.OutcomeResult;
import net.hades.fix.admin.console.data.Result;
import net.hades.fix.admin.session.HadesEngineSession;

import java.util.Formatter;

import javax.management.MBeanOperationInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;

import net.hades.fix.admin.cmdline.CommandName;
import net.hades.fix.admin.command.param.OutcomeParam;
import net.hades.fix.admin.console.data.CmdLineResult;
import net.hades.fix.admin.session.HadesEngineConnectionManager;
import net.hades.fix.admin.session.SessionException;

/**
 * Shutdown the server.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 08/05/2010
 */
public class ShutdownImmediateCommand extends Command {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final String MBEAN_OPERATION_NAME = "shutdownImmediate";

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ShutdownImmediateCommand(String[] args) throws SessionException {
        super(args);
    }
        
    public ShutdownImmediateCommand(String[] args, MBeanServerConnection conn) throws SessionException {
        super(args, conn);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Result execute() {
        OutcomeResult result = new OutcomeResult();
        MBeanOperationInfo operation = getMBeanOperation(MBEAN_OPERATION_NAME);
        if (operation != null) {
            ObjectName openMBeanObjectName;
            try {
                openMBeanObjectName = new ObjectName(HadesEngineSession.HADES_ENGINE_MBEAN_NAME);
                if (conn == null) {
                    conn = HadesEngineConnectionManager.getEngineSession().getMBeanServerConnection();
                }
                CompositeData response = (CompositeData) conn.invoke(
                        openMBeanObjectName,
                        MBEAN_OPERATION_NAME,
                        null,
                        null);
                result.setOutcome((Boolean) response.get(OutcomeParam.Outcome.getValue()));
                result.setErrMsg((String) response.get(OutcomeParam.ErrMsg.getValue()));
            } catch (Exception ex) {
                result.setOutcome(Boolean.FALSE);
                handleException(result, ex);
            }
        } else {
            result.setOutcome(Boolean.FALSE);
            result.setErrMsg("The operation is not configured on the HadesFIX MBean server.");
        }

        return result;
    }

    @Override
    public CmdLineResult executeCmdline() {
        CmdLineResult cmdLineResult = new CmdLineResult();
        if (args.length == 1 && HELP_OPTION.equalsIgnoreCase(args[0])) {
            cmdLineResult.addRow(getHelpText());
        } else {
            if (args.length != 0) {
                cmdLineResult.addRow(getHelpText());
            } else {
                OutcomeResult cmdResult = (OutcomeResult) execute();

                if (cmdResult.getOutcome()) {
                    cmdLineResult.addRow("Successfully shutdown immediate all the instance sessions.");
                } else {
                    cmdLineResult.addRow(formatData(cmdResult.getErrMsg()));
                }
            }
        }

        return cmdLineResult;
    }

    @Override
    public String getHelpText() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%n %nThe format of the command is: ");
        formatter.format("%1$s %n", CommandName.SERV_SHUTDOWN_NOW.getValue());
        formatter.format("\t\t%s %n", "Attempts to shutdown immediate all the session's instances.");

        return sb.toString();
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
