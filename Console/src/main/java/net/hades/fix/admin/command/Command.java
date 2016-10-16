/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Command.java
 *
 * $Id: Command.java,v 1.11 2011-04-01 05:04:23 vrotaru Exp $
 */
package net.hades.fix.admin.command;

import java.io.IOException;
import java.util.Formatter;
import java.util.logging.Logger;

import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import net.hades.fix.admin.cmdline.CommandName;
import net.hades.fix.admin.console.data.CmdLineResult;
import net.hades.fix.admin.console.data.Result;
import net.hades.fix.admin.console.data.SessionAddress;
import net.hades.fix.admin.console.data.SessionAddressHolder;
import net.hades.fix.admin.console.exception.InputValidationException;
import net.hades.fix.admin.session.HadesEngineConnectionManager;
import net.hades.fix.admin.session.HadesEngineSession;
import net.hades.fix.admin.session.SessionException;
import net.hades.fix.commons.exception.ExceptionUtil;

/**
 * Super class of all the management commands.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.11 $
 */
public abstract class Command {

    protected static final Logger LOGGER = Logger.getLogger(Command.class.getName());

    protected static final String HELP_OPTION = "help";

    protected String[] args;

    protected MBeanInfo mBeanInfo;
    
    protected SessionAddress sessionAddress;
    protected MBeanServerConnection conn;

    protected Command(String[] args) throws SessionException {
        this.args = args;
        if (mBeanInfo == null) {
            setMBeanInfo();
        }
    }
    
    protected Command(String[] args, MBeanServerConnection conn) throws SessionException {
        this.args = args;
        this.conn = conn;
        setMBeanInfo(conn);
    }

    public abstract Result execute();
    
    public abstract CmdLineResult executeCmdline();

    public abstract String getHelpText();
    
    // </editor-fold>

    public static Command getCommand(String commandString) {
        Command command = null;
        if (commandString != null && !commandString.trim().isEmpty()) {
            String[] tokens = commandString.split(" ");
            command = buildCommand(tokens);
        }

        return command;
    }
    
    public static Command getCommand(String commandString, MBeanServerConnection conn) {
        Command command = null;
        if (commandString != null && !commandString.trim().isEmpty()) {
            String[] tokens = commandString.split(" ");
            command = buildCommand(tokens, conn);
        }

        return command;
    }

    public void setSessionAddress(SessionAddress sessionAddress) {
        this.sessionAddress = sessionAddress;
    }

    protected MBeanOperationInfo getMBeanOperation(String operationName) {
        MBeanOperationInfo operation = null;
        for (MBeanOperationInfo op : mBeanInfo.getOperations()) {
            if (operationName.equals(op.getName())) {
                operation = op;
                break;
            }
        }

        return operation;
    }

    protected void handleException(Result result, Exception exception) {
        if (exception instanceof SessionException) {
            result.setOutcome(Boolean.FALSE);
            result.setErrMsg("Error connecting to the MBean server. Error was : " + ExceptionUtil.getStackTrace(exception));
        } else if (exception instanceof InstanceNotFoundException) {
            result.setOutcome(Boolean.FALSE);
            result.setErrMsg("MBean instance not found on the server. Error was : " + ExceptionUtil.getStackTrace(exception));
        } else if (exception instanceof MBeanException) {
            result.setOutcome(Boolean.FALSE);
            result.setErrMsg("MBean server error. Error was : " + ExceptionUtil.getStackTrace(exception));
        } else if (exception instanceof ReflectionException) {
            result.setOutcome(Boolean.FALSE);
            result.setErrMsg("Error reflecting the MBean. Error was : " + ExceptionUtil.getStackTrace(exception));
        } else if (exception instanceof IOException) {
            result.setOutcome(Boolean.FALSE);
            result.setErrMsg("Connection error. Error was : " + ExceptionUtil.getStackTrace(exception));
        } else if (exception instanceof MalformedObjectNameException) {
            result.setOutcome(Boolean.FALSE);
            result.setErrMsg("Bad format for the MBean Object. Error was : " + ExceptionUtil.getStackTrace(exception));
        } else if (exception instanceof InputValidationException) {
            result.setOutcome(Boolean.FALSE);
            result.setErrMsg(exception.getMessage());
        } else if (exception instanceof Exception) {
            result.setOutcome(Boolean.FALSE);
            result.setErrMsg("Unexpected error received. Error was : " + ExceptionUtil.getStackTrace(exception));
        }
    }

    protected SessionAddress findSessionAddress(String sessionId) throws InputValidationException {
        SessionAddress result = null;
        try {
            Long id = new Long(sessionId);
            result = SessionAddressHolder.getInstance().getSessionAddress(id);
        } catch (NumberFormatException ex) {
            if (sessionId.indexOf(":") <= 0) {
                throw new InputValidationException("Session identifier must be in format <CptyAddr>:<SessAddr>.");
            }
            String cptyAddr = sessionId.substring(0, sessionId.indexOf(":"));
            String sessAddr = sessionId.substring(sessionId.indexOf(":") + 1);
            result = new SessionAddress(cptyAddr, sessAddr);
            if (!SessionAddressHolder.getInstance().checkAddressExists(result)) {
                throw new InputValidationException("Session [" + sessionId + "] does not exists on the server.");
            }
        }

        return result;
    }

    protected String formatData(String data) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%1$s %n", data);

        return sb.toString();
    }

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    
    private static Command buildCommand(String[] tokens, MBeanServerConnection conn) {
        Command result = null;
        CommandName commandName = CommandName.valueFor(tokens[0].toLowerCase());
        if (commandName != null) {
            try {
                switch (commandName) {
                    case LIST_SESS:
                        result = new ListSessionCommand(getCommandArgs(tokens), conn);
                        break;

                    case SET_RX_SEQ:
                        result = new SetRxSeqCommand(getCommandArgs(tokens), conn);
                        break;

                    case SET_TX_SEQ:
                        result = new SetTxSeqCommand(getCommandArgs(tokens), conn);
                        break;

                    case SESS_FREEZE:
                        result = new SessFreezeCommand(getCommandArgs(tokens), conn);
                        break;

                    case SESS_THAW:
                        result = new SessThawCommand(getCommandArgs(tokens), conn);
                        break;

                    case SESS_START:
                        result = new SessStartCommand(getCommandArgs(tokens), conn);
                        break;

                    case SESS_STOP:
                        result = new SessStopCommand(getCommandArgs(tokens), conn);
                        break;

                    case SESS_CONN:
                        result = new SessConnectCommand(getCommandArgs(tokens), conn);
                        break;

                    case SESS_DISC:
                        result = new SessDisconnectCommand(getCommandArgs(tokens), conn);
                        break;

                    case SESS_RESET:
                        result = new SessResetCommand(getCommandArgs(tokens), conn);
                        break;

                    case SESS_STATS:
                        result = new SessStatsCommand(getCommandArgs(tokens), conn);
                        break;

                    case SEQ_RESET:
                        result = new ResetSeqCommand(getCommandArgs(tokens), conn);
                        break;

                    case SERV_SHUTDOWN:
                        result = new ShutdownCommand(getCommandArgs(tokens), conn);
                        break;
                        
                    case SERV_SHUTDOWN_NOW:
                        result = new ShutdownImmediateCommand(getCommandArgs(tokens), conn);
                        break;
                }
            } catch (SessionException ex) {
                System.console().printf("Unable to open a connection with MBean server. Error was : %1$s %n", ExceptionUtil.getStackTrace(ex));
            }
        }

        return result;
    }

    private static Command buildCommand(String[] tokens) {
        Command result = null;
        CommandName commandName = CommandName.valueFor(tokens[0].toLowerCase());
        if (commandName != null) {
            try {
                switch (commandName) {
                    case LIST_SESS:
                        result = new ListSessionCommand(getCommandArgs(tokens));
                        break;

                    case SET_RX_SEQ:
                        result = new SetRxSeqCommand(getCommandArgs(tokens));
                        break;

                    case SET_TX_SEQ:
                        result = new SetTxSeqCommand(getCommandArgs(tokens));
                        break;

                    case SESS_FREEZE:
                        result = new SessFreezeCommand(getCommandArgs(tokens));
                        break;

                    case SESS_THAW:
                        result = new SessThawCommand(getCommandArgs(tokens));
                        break;

                    case SESS_START:
                        result = new SessStartCommand(getCommandArgs(tokens));
                        break;

                    case SESS_STOP:
                        result = new SessStopCommand(getCommandArgs(tokens));
                        break;

                    case SESS_CONN:
                        result = new SessConnectCommand(getCommandArgs(tokens));
                        break;

                    case SESS_DISC:
                        result = new SessDisconnectCommand(getCommandArgs(tokens));
                        break;

                    case SESS_RESET:
                        result = new SessResetCommand(getCommandArgs(tokens));
                        break;

                    case SESS_STATS:
                        result = new SessStatsCommand(getCommandArgs(tokens));
                        break;

                    case SEQ_RESET:
                        result = new ResetSeqCommand(getCommandArgs(tokens));
                        break;

                    case SERV_SHUTDOWN:
                        result = new ShutdownCommand(getCommandArgs(tokens));
                        break;
                        
                    case SERV_SHUTDOWN_NOW:
                        result = new ShutdownImmediateCommand(getCommandArgs(tokens));
                        break;
                }
            } catch (SessionException ex) {
                System.console().printf("Unable to open a connection with MBean server. Error was : %1$s %n", ExceptionUtil.getStackTrace(ex));
            }
        }

        return result;
    }

    private static String[] getCommandArgs(String[] tokens) {
        String[] result = new String[tokens.length - 1];
        if (tokens.length > 1) {
            for (int i = 1; i < tokens.length; i++) {
                result[i - 1] = tokens[i];
            }
        }

        return result;
    }
    
    private void setMBeanInfo(MBeanServerConnection conn) throws SessionException {
        try {
            mBeanInfo = conn.getMBeanInfo(new ObjectName(HadesEngineSession.HADES_ENGINE_MBEAN_NAME));
        } catch (MalformedObjectNameException ex) {
            throw new SessionException("Bad MBean object name [" + HadesEngineSession.HADES_ENGINE_MBEAN_NAME + "].", ex);
        } catch (InstanceNotFoundException ex) {
            throw new SessionException("MBean with object name [" + HadesEngineSession.HADES_ENGINE_MBEAN_NAME + "] could not be found on the server.", ex);
        } catch (IntrospectionException ex) {
            throw new SessionException("Error introspecting MBean object name [" + HadesEngineSession.HADES_ENGINE_MBEAN_NAME + "].", ex);
        } catch (ReflectionException ex) {
            throw new SessionException("Error reflecting MBean object name [" + HadesEngineSession.HADES_ENGINE_MBEAN_NAME + "].", ex);
        } catch (IOException ex) {
            throw new SessionException("Error reading MBean object name [" + HadesEngineSession.HADES_ENGINE_MBEAN_NAME + "].", ex);
        }
    }

    private void setMBeanInfo() throws SessionException {
        MBeanServerConnection conn = HadesEngineConnectionManager.getEngineSession().getMBeanServerConnection();
        try {
            mBeanInfo = conn.getMBeanInfo(new ObjectName(HadesEngineSession.HADES_ENGINE_MBEAN_NAME));
        } catch (MalformedObjectNameException ex) {
            throw new SessionException("Bad MBean object name [" + HadesEngineSession.HADES_ENGINE_MBEAN_NAME + "].", ex);
        } catch (InstanceNotFoundException ex) {
            throw new SessionException("MBean with object name [" + HadesEngineSession.HADES_ENGINE_MBEAN_NAME + "] could not be found on the server.", ex);
        } catch (IntrospectionException ex) {
            throw new SessionException("Error introspecting MBean object name [" + HadesEngineSession.HADES_ENGINE_MBEAN_NAME + "].", ex);
        } catch (ReflectionException ex) {
            throw new SessionException("Error reflecting MBean object name [" + HadesEngineSession.HADES_ENGINE_MBEAN_NAME + "].", ex);
        } catch (IOException ex) {
            throw new SessionException("Error reading MBean object name [" + HadesEngineSession.HADES_ENGINE_MBEAN_NAME + "].", ex);
        }
    }

}
