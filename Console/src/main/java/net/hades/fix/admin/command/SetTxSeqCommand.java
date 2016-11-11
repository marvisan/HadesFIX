/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SetTxSeqCommand.java
 *
 * $Id: SetTxSeqCommand.java,v 1.6 2011-04-01 05:04:23 vrotaru Exp $
 */
package net.hades.fix.admin.command;

import net.hades.fix.admin.console.data.OutcomeResult;
import net.hades.fix.admin.console.data.Result;
import net.hades.fix.admin.session.HadesEngineSession;

import java.util.Formatter;
import java.util.Iterator;

import javax.management.MBeanOperationInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;

import net.hades.fix.admin.cmdline.CommandName;
import net.hades.fix.admin.command.param.OutcomeParam;
import net.hades.fix.admin.console.data.CmdLineResult;
import net.hades.fix.admin.console.exception.InputValidationException;
import net.hades.fix.admin.session.HadesEngineConnectionManager;
import net.hades.fix.admin.session.SessionException;

/**
 * Sets the TX sequence on the server.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 08/05/2010
 */
public class SetTxSeqCommand extends Command {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final String MBEAN_OPERATION_NAME = "setTxSeq";

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private String sessionId;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SetTxSeqCommand(String[] args) throws SessionException {
        super(args);
    }
      
    public SetTxSeqCommand(String[] args, MBeanServerConnection conn) throws SessionException {
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
            sessionId = args[0];
            try {
                if (sessionAddress == null) {
                    sessionAddress = findSessionAddress(sessionId);
                }
                if (sessionAddress != null) {
                    openMBeanObjectName = new ObjectName(HadesEngineSession.HADES_ENGINE_MBEAN_NAME);
                    if (conn == null) {
                        conn = HadesEngineConnectionManager.getEngineSession().getMBeanServerConnection();
                    }
                    CompositeData response = (CompositeData) conn.invoke(
                            openMBeanObjectName,
                            MBEAN_OPERATION_NAME,
                            new Object[] { sessionAddress.getCptyAddress(), sessionAddress.getSessAdrress(), new Integer(args[1]) },
                            null);
                    result.setOutcome((Boolean) response.get(OutcomeParam.Outcome.getValue()));
                    result.setErrMsg((String) response.get(OutcomeParam.ErrMsg.getValue()));
                } else {
                    result.setOutcome(Boolean.FALSE);
                    result.setErrMsg("Session with ID [" + sessionId + "} does not exists.");
                }
            } catch (InputValidationException ex) {
                result.setOutcome(Boolean.FALSE);
                result.setErrMsg(ex.getMessage());
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
            if (args.length != 2) {
                cmdLineResult.addRow(getHelpText());
            } else {
                OutcomeResult cmdResult = (OutcomeResult) execute();

                if (cmdResult.getOutcome()) {
                    cmdLineResult.addRow("Sequence number [" + args[1] + "] successfully set for outgoing messages on session ["
                            + sessionAddress.getCptyAddress() + ":" + sessionAddress.getSessAdrress() + "].");
                    Command listCmd = Command.getCommand(CommandName.LIST_SESS.getValue() + " " + sessionId);
                    cmdLineResult.addRow("\n");
                    CmdLineResult listCmdResult = listCmd.executeCmdline();
                    for (Iterator<String> it = listCmdResult.iterator(); it.hasNext();) {
                        cmdLineResult.addRow(it.next());
                    }
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
        formatter.format("%1$s <session_id> <new_seq_num> %n", CommandName.SET_TX_SEQ.getValue());
        formatter.format("\t Where the options are: %n");
        formatter.format("\t\t%1$10s - %2$s %n", "<session_id>", "Session identifier - can be either the ID or the string " +
                "<Cpty Address>:<Session Address>");
        formatter.format("\t\t%1$10s - %2$s %n", "<new_seq_num>", "New expected outgoing sequence number.");


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
