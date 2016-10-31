/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * ListSessionCommand.java
 *
 * $Id: ListSessionCommand.java,v 1.4 2011-03-21 04:53:03 vrotaru Exp $
 */
package net.hades.fix.admin.command;

import net.hades.fix.admin.console.data.FieldData;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import javax.management.MBeanOperationInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeType;

import net.hades.fix.admin.cmdline.CommandName;
import net.hades.fix.admin.command.param.ListSessionParam;
import net.hades.fix.admin.console.data.CmdLineResult;
import net.hades.fix.admin.console.data.Result;
import net.hades.fix.admin.console.data.SessionAddressHolder;
import net.hades.fix.admin.console.data.TableResult;
import net.hades.fix.admin.session.HadesEngineConnectionManager;
import net.hades.fix.admin.session.HadesEngineSession;
import net.hades.fix.admin.session.SessionException;

/**
 * Processing a list sessions request.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 08/05/2010
 */
public class ListSessionCommand extends Command {

    private static final String MBEAN_OPERATION_NAME = "getConfigSessions";

    public ListSessionCommand(String[] args) throws SessionException {
        super(args);
    }
    
    public ListSessionCommand(String[] args, MBeanServerConnection conn) throws SessionException {
        super(args, conn);
    }

    @Override
    public Result execute() {
        TableResult result = new TableResult();
        MBeanOperationInfo operation = getMBeanOperation(MBEAN_OPERATION_NAME);
        if (operation != null) {
            ObjectName openMBeanObjectName;
            try {
                openMBeanObjectName = new ObjectName(HadesEngineSession.HADES_ENGINE_MBEAN_NAME);
                if (conn == null) {
                    conn = HadesEngineConnectionManager.getEngineSession().getMBeanServerConnection();
                }
                CompositeData[] response = (CompositeData[]) conn.invoke(
                        openMBeanObjectName,
                        MBEAN_OPERATION_NAME,
                        null,
                        null);
                boolean hasFields = false;
                for (CompositeData session : response) {
                    if (!hasFields) {
                        CompositeType sessionType = session.getCompositeType();
                        List<FieldData> fields = new ArrayList<FieldData>();
                        fields.add(new FieldData(ListSessionParam.Id.getValue(), sessionType.getDescription(ListSessionParam.Id.getValue())));
                        fields.add(new FieldData(ListSessionParam.Counterparty.getValue(), sessionType.getDescription(ListSessionParam.Counterparty.getValue())));
                        fields.add(new FieldData(ListSessionParam.Name.getValue(), sessionType.getDescription(ListSessionParam.Name.getValue())));
                        CompositeType protocolType = (CompositeType) sessionType.getType(ListSessionParam.Protocol.getValue());
                        fields.add(new FieldData(ListSessionParam.Type.getValue(), protocolType.getDescription(ListSessionParam.Type.getValue())));
                        fields.add(new FieldData(ListSessionParam.Status.getValue(), sessionType.getDescription(ListSessionParam.Status.getValue())));
                        fields.add(new FieldData(ListSessionParam.RxSeqNo.getValue(), protocolType.getDescription(ListSessionParam.RxSeqNo.getValue())));
                        fields.add(new FieldData(ListSessionParam.TxSeqNo.getValue(), protocolType.getDescription(ListSessionParam.TxSeqNo.getValue())));
                        result = new TableResult(fields);
                        hasFields = true;
                    }
                    String id = (String) session.get(ListSessionParam.Id.getValue());
                    String counterparty = (String) session.get(ListSessionParam.Counterparty.getValue());
                    String name = (String) session.get(ListSessionParam.Name.getValue());
                    String status = (String) session.get(ListSessionParam.Status.getValue());
                    CompositeData protocol = (CompositeData) session.get(ListSessionParam.Protocol.getValue());
                    String type = (String) protocol.get(ListSessionParam.Type.getValue());
                    Integer rxSeq = (Integer) protocol.get(ListSessionParam.RxSeqNo.getValue());
                    Integer txSeq = (Integer) protocol.get(ListSessionParam.TxSeqNo.getValue());
                    List<String> row = new ArrayList<String>();
                    row.add(id);
                    row.add(counterparty);
                    row.add(name);
                    row.add(type);
                    row.add(status);
                    row.add(rxSeq.toString());
                    row.add(txSeq.toString());
                    result.addDataRow(row);
                    // updates the session cache
                    SessionAddressHolder.getInstance().addSessionAddress(new Long(id), counterparty, name);
                }
            } catch (Exception ex) {
                result.setOutcome(Boolean.FALSE);
                handleException(result, ex);
            }
        } else {
            result.setOutcome(Boolean.FALSE);
            result.setErrMsg("The operation [" + MBEAN_OPERATION_NAME + "] is not configured on the HadesFIX MBean server.");
        }

        return result;
    }

    @Override
    public CmdLineResult executeCmdline() {
        CmdLineResult cmdLineResult = new CmdLineResult();
        if (args.length == 1 && HELP_OPTION.equalsIgnoreCase(args[0])) {
            cmdLineResult.addRow(getHelpText());

            return cmdLineResult;
        } else {
            String session = null;
            boolean hasId = false;
            if (args.length == 1) {
                session = args[0];
                try {
                    Integer.parseInt(session);
                    hasId = true;
                } catch (NumberFormatException ex) {
                }
            }
            TableResult cmdResult = (TableResult) execute();
            if (!cmdResult.getOutcome()) {
                cmdLineResult.addRow(cmdResult.getErrMsg());
                return cmdLineResult;
            } else {
                cmdLineResult.addRow(formatHeader(cmdResult.getMetadata()));
                for (int i = 0; i < cmdResult.getSize(); i++) {
                    List<String> data = cmdResult.getDataRow(i);
                    if (session != null) {
                        if (hasId) {
                            if (session.equals(data.get(0))) {
                                cmdLineResult.addRow(formatData(data));
                            }
                        } else {
                            String sessionId = data.get(1) + ":" + data.get(2);
                            if (session.equals(sessionId)) {
                                cmdLineResult.addRow(formatData(data));
                            }
                        }
                    } else {
                        cmdLineResult.addRow(formatData(data));
                    }
                }

                return cmdLineResult;
            }
        }
    }

    @Override
    public String getHelpText() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("The format of the command is: %n");
        formatter.format("\t\t %1$s %n", CommandName.LIST_SESS.getValue() + " [<session_id>]");

        return sb.toString();
    }

    private String formatHeader(List<FieldData> metadata) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%1$-6s %2$-20s %3$-20s %4$-4s %5$-9s %6$9s %7$9s %n", metadata.get(0).getDescription(),
                metadata.get(1).getDescription(),
                metadata.get(2).getDescription(),
                metadata.get(3).getDescription(),
                metadata.get(4).getDescription(),
                metadata.get(5).getDescription(),
                metadata.get(6).getDescription());
        formatter.format("------ -------------------- -------------------- ---- --------- --------- --------- %n");

        return sb.toString();
    }

    private String formatData(List<String> dataRow) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%1$-6s %2$-20s %3$-20s %4$-4s %5$-9s %6$9s %7$9s %n", dataRow.get(0), dataRow.get(1), dataRow.get(2),
                dataRow.get(3), dataRow.get(4), dataRow.get(5), dataRow.get(6));

        return sb.toString();
    }

}
