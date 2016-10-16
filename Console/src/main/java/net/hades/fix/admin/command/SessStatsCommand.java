/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SessStatsCommand.java
 *
 * $Id: SessStatsCommand.java,v 1.4 2011-04-04 07:06:36 vrotaru Exp $
 */
package net.hades.fix.admin.command;

import net.hades.fix.admin.cmdline.CommandName;
import net.hades.fix.admin.command.param.SessionStatsResultParam;
import net.hades.fix.admin.command.param.StreamStatsParam;
import net.hades.fix.admin.console.data.CmdLineResult;
import net.hades.fix.admin.console.data.FieldData;
import net.hades.fix.admin.console.data.Result;
import net.hades.fix.admin.console.data.TableResult;
import net.hades.fix.admin.session.HadesEngineSession;
import net.hades.fix.admin.session.SessionException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import javax.management.MBeanOperationInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;

import net.hades.fix.admin.command.param.ProtocolStatsParam;
import net.hades.fix.admin.command.param.SessionStatsParam;
import net.hades.fix.admin.command.param.TransportStatsParam;
import net.hades.fix.admin.console.data.MultiTableResult;
import net.hades.fix.admin.console.exception.InputValidationException;
import net.hades.fix.admin.session.HadesEngineConnectionManager;

/**
 * Retrieve the session statistics data collected by the instance.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 08/05/2010
 */
public class SessStatsCommand extends Command {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final String MBEAN_OPERATION_NAME = "getSessionStats";

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private String sessionId;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SessStatsCommand(String[] args) throws SessionException {
        super(args);
    }
    public SessStatsCommand(String[] args, MBeanServerConnection conn) throws SessionException {
        super(args, conn);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Result execute() {
        MultiTableResult result = new MultiTableResult();
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
                            new Object[] { sessionAddress.getCptyAddress(), sessionAddress.getSessAdrress() },
                            null);
                    if (response == null) {
                        result.setOutcome(Boolean.FALSE);
                        result.setErrMsg("No statistics information for session : " + sessionAddress.getCptyAddress() + ":" + sessionAddress.getSessAdrress() 
                                + " found on the HadesFIX instance.");
                    } else {
                        Date startTimestamp = (Date) response.get(SessionStatsParam.StartTimestamp.getValue());
                        List<FieldData> fields = new ArrayList<FieldData>();
                        fields.add(new FieldData(SessionStatsParam.CptyAddress.getValue(), "Cpty Address"));
                        fields.add(new FieldData(SessionStatsParam.SessAddress.getValue(), "Session Address"));
                        fields.add(new FieldData(SessionStatsParam.StartTimestamp.getValue(), "Start Timestamp"));
                        TableResult sessTR = new TableResult(fields);
                        List<String> row = new ArrayList<String>();
                        row.add(sessionAddress.getCptyAddress());
                        row.add(sessionAddress.getSessAdrress());
                        row.add(startTimestamp != null ? new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(startTimestamp) : "Unknown");
                        sessTR.addDataRow(row);
                        result.addTableResult(SessionStatsResultParam.SessionStats.toString(), sessTR);
                        CompositeData transportStats = (CompositeData) response.get(SessionStatsParam.TransportStats.getValue());
                        result.addTableResult(SessionStatsResultParam.TransportStats.toString(), readTransportStats(transportStats));
                        CompositeData protocolStats = (CompositeData) response.get(SessionStatsParam.ProtocolStats.getValue());
                        result.addTableResult(SessionStatsResultParam.ProtocolStats.toString(), readProtocolStats(protocolStats));
                        CompositeData prodStreamStats = (CompositeData) response.get(SessionStatsParam.ProducerStreamStats.getValue());
                        result.addTableResult(SessionStatsResultParam.ProducerStreamStats.toString(), readStreamStats(prodStreamStats));
                        CompositeData consStreamStats = (CompositeData) response.get(SessionStatsParam.ConsumerStreamStats.getValue());
                        result.addTableResult(SessionStatsResultParam.ConsumerStreamStats.toString(), readStreamStats(consStreamStats));
                    }
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
            result.setErrMsg("The operation [" + MBEAN_OPERATION_NAME + "] is not configured on the HadesFIX MBean server.");
        }

        return result;
    }

    @Override
    public CmdLineResult executeCmdline() {
        CmdLineResult cmdLineResult = new CmdLineResult();
        if (args.length == 1 && HELP_OPTION.equalsIgnoreCase(args[0])) {
            cmdLineResult.addRow(getHelpText());
        } else {
            if (args.length != 1) {
                cmdLineResult.addRow(getHelpText());
            } else {
                MultiTableResult cmdResult = (MultiTableResult) execute();
                if (!cmdResult.getOutcome()) {
                    cmdLineResult.addRow(cmdResult.getErrMsg());
                    return cmdLineResult;
                } else {
                    TableResult sessResult = cmdResult.getTableResult(SessionStatsResultParam.SessionStats.toString());
                    cmdLineResult.addRow(formatSessionHeader(sessResult.getMetadata()));
                    if (Boolean.FALSE.equals(sessResult.getOutcome())) {
                        cmdLineResult.addRow(sessResult.getErrMsg());
                    } else {
                        if (sessResult.getSize() > 0) {
                            cmdLineResult.addRow(formatSessionData(sessResult.getDataRow(0)));
                        }
                    }
                    TableResult transportResult = cmdResult.getTableResult(SessionStatsResultParam.TransportStats.toString());
                    cmdLineResult.addRow(formatTransportHeader(transportResult.getMetadata()));
                    if (Boolean.FALSE.equals(transportResult.getOutcome())) {
                        cmdLineResult.addRow(transportResult.getErrMsg());
                    } else {
                        if (sessResult.getSize() > 0) {
                            cmdLineResult.addRow(formatTransportData(transportResult.getDataRow(0)));
                        }
                    }
                    TableResult protocolResult = cmdResult.getTableResult(SessionStatsResultParam.ProtocolStats.toString());
                    cmdLineResult.addRow(formatProtocolHeader(protocolResult.getMetadata()));
                    if (Boolean.FALSE.equals(protocolResult.getOutcome())) {
                        cmdLineResult.addRow(protocolResult.getErrMsg());
                    } else {
                        if (sessResult.getSize() > 0) {
                            cmdLineResult.addRow(formatProtocolData(protocolResult.getDataRow(0)));
                        }
                    }
                    TableResult prodStreamResult = cmdResult.getTableResult(SessionStatsResultParam.ProducerStreamStats.toString());
                    cmdLineResult.addRow(formatStreamHeader(prodStreamResult.getMetadata(), "Producer Stream"));
                    if (Boolean.FALSE.equals(prodStreamResult.getOutcome())) {
                        cmdLineResult.addRow(prodStreamResult.getErrMsg());
                    } else {
                        if (sessResult.getSize() > 0) {
                            cmdLineResult.addRow(formatStreamData(prodStreamResult.getDataRow(0)));
                        }
                    }
                    TableResult consStreamResult = cmdResult.getTableResult(SessionStatsResultParam.ConsumerStreamStats.toString());
                    cmdLineResult.addRow(formatStreamHeader(consStreamResult.getMetadata(), "Consumer Stream"));
                    if (Boolean.FALSE.equals(consStreamResult.getOutcome())) {
                        cmdLineResult.addRow(consStreamResult.getErrMsg());
                    } else {
                        if (sessResult.getSize() > 0) {
                            cmdLineResult.addRow(formatStreamData(consStreamResult.getDataRow(0)));
                        }
                    }
                }
            }
        }

        return cmdLineResult;
    }

    @Override
    public String getHelpText() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("The format of the command is: %n");
        formatter.format("\t\t %1$s %n", CommandName.SESS_STATS.getValue() + " <session_id>");

        return sb.toString();
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">

    private String formatSessionHeader(List<FieldData> metadata) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%1$-20s %2$-20s %3$-20s %n", metadata.get(0).getDescription(),
                metadata.get(1).getDescription(),
                metadata.get(2).getDescription());
        formatter.format("-------------------- -------------------- -------------------- %n");

        return sb.toString();
    }

    private String formatTransportHeader(List<FieldData> metadata) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%1$-17s %2$-15s %3$-17s %4$-15s %n", metadata.get(0).getDescription(),
                metadata.get(1).getDescription(),
                metadata.get(2).getDescription(),
                metadata.get(3).getDescription());
        formatter.format("----------------- --------------- ----------------- --------------- %n");

        return sb.toString();
    }

    private String formatProtocolHeader(List<FieldData> metadata) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%1$-12s %2$-10s %3$-12s %4$-10s %5$-10s %n", metadata.get(0).getDescription(),
                metadata.get(1).getDescription(),
                metadata.get(2).getDescription(),
                metadata.get(3).getDescription(),
                metadata.get(4).getDescription());
        formatter.format("------------ ---------- ------------ ---------- ---------- %n");

        return sb.toString();
    }

    private String formatStreamHeader(List<FieldData> metadata, String streamType) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%1$-15s %2$-17s %3$-17s %4$-10s %5$-10s %n",
                streamType,
                metadata.get(0).getDescription(),
                metadata.get(1).getDescription(),
                metadata.get(2).getDescription(),
                metadata.get(3).getDescription());
        formatter.format("--------------- ----------------- ----------------- ---------- ---------- %n");

        return sb.toString();
    }

    private String formatSessionData(List<String> dataRow) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%1$-20s %2$-20s %3$-20s %n%n", dataRow.get(0), dataRow.get(1), dataRow.get(2));

        return sb.toString();
    }

    private String formatTransportData(List<String> dataRow) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%1$-17s %2$-15s %3$-17s %4$-15s %n%n", dataRow.get(0), dataRow.get(1), dataRow.get(2), dataRow.get(3));

        return sb.toString();
    }

    private String formatProtocolData(List<String> dataRow) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%1$-12s %2$-10s %3$-12s %4$-10s %5$-10s %n%n", dataRow.get(0), dataRow.get(1), dataRow.get(2),
                dataRow.get(3), dataRow.get(4));

        return sb.toString();
    }

    private String formatStreamData(List<String> dataRow) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%1$-15s %2$-17s %3$-17s %4$-10s %5$-10s %n%n", "", dataRow.get(0), dataRow.get(1), dataRow.get(2),
                dataRow.get(3));

        return sb.toString();
    }

    private TableResult readTransportStats(CompositeData compositeData) {
        TableResult result;
        if (compositeData == null) {
            result = new TableResult(Boolean.FALSE);
            result.setErrMsg("No transport statistics information available for this session");
            return result;
        }
        Date startTimestamp = (Date) compositeData.get(TransportStatsParam.StartTimestamp.getValue());
        long runningSecs = 0;
        if (startTimestamp != null) {
            runningSecs = (Math.abs(startTimestamp.getTime() - System.currentTimeMillis())) / 1000;
        }
        Long bytesIn = (Long) compositeData.get(TransportStatsParam.BytesIn.getValue());
        Long bytesOut = (Long) compositeData.get(TransportStatsParam.BytesOut.getValue());
        List<FieldData> fields = new ArrayList<FieldData>();
        fields.add(new FieldData(TransportStatsParam.BytesIn.getValue(), "Bytes In"));
        fields.add(new FieldData(TransportStatsParam.ThroughputIn.getValue(), "Bytes/Sec In"));
        fields.add(new FieldData(TransportStatsParam.BytesOut.getValue(), "Bytes Out"));
        fields.add(new FieldData(TransportStatsParam.ThroughputOut.getValue(), "Bytes/Sec Out"));
        result = new TableResult(fields);
        List<String> row = new ArrayList<String>();
        row.add(bytesIn != null ? new DecimalFormat("#,##0").format(bytesIn) : "0");
        if (runningSecs > 0 && bytesIn != null) {
            row.add(new DecimalFormat("#,##0").format((bytesIn / runningSecs)));
        }  else {
            row.add(String.valueOf("0"));
        }
        row.add(bytesOut != null ? new DecimalFormat("#,##0").format(bytesOut) : "0");
        if (runningSecs > 0 && bytesOut != null) {
            row.add(new DecimalFormat("#,##0").format((bytesOut / runningSecs)));
        }  else {
            row.add(String.valueOf("0"));
        }
        result.addDataRow(row);

        return result;
    }

    private TableResult readProtocolStats(CompositeData compositeData) {
        TableResult result;
        if (compositeData == null) {
            result = new TableResult(Boolean.FALSE);
            result.setErrMsg("No protocol statistics information available for this session");
            return result;
        }
        Date startTimestamp = (Date) compositeData.get(ProtocolStatsParam.StartTimestamp.getValue());
        long runningSecs = 0;
        if (startTimestamp != null) {
            runningSecs = (Math.abs(startTimestamp.getTime() - System.currentTimeMillis())) / 1000;
        }
        Integer msgInCount = (Integer) compositeData.get(ProtocolStatsParam.TotMsgInCount.getValue());
        Integer msgOutCount = (Integer) compositeData.get(ProtocolStatsParam.TotMsgOutCount.getValue());
        Integer rejMsgCount = (Integer) compositeData.get(ProtocolStatsParam.RejMsgCount.getValue());
        List<FieldData> fields = new ArrayList<FieldData>();
        fields.add(new FieldData(ProtocolStatsParam.TotMsgInCount.getValue(), "No Msg In"));
        fields.add(new FieldData(ProtocolStatsParam.ThroughputIn.getValue(), "MsgIn/Sec"));
        fields.add(new FieldData(ProtocolStatsParam.TotMsgOutCount.getValue(), "No Msg Out"));
        fields.add(new FieldData(ProtocolStatsParam.ThroughputOut.getValue(), "MsgOut/Sec"));
        fields.add(new FieldData(ProtocolStatsParam.RejMsgCount.getValue(), "No RejMsg"));
        result = new TableResult(fields);
        List<String> row = new ArrayList<String>();
        row.add(msgInCount != null ? msgInCount.toString() : "0");
        if (runningSecs > 0 && msgInCount != null) {
            row.add(new DecimalFormat("#,##0").format(msgInCount / runningSecs));
        }  else {
            row.add(String.valueOf("0"));
        }
        row.add(msgOutCount != null ? msgOutCount.toString() : "0");
        if (runningSecs > 0 && msgOutCount != null) {
            row.add(new DecimalFormat("#,##0").format(msgOutCount / runningSecs));
        }  else {
            row.add(String.valueOf("0"));
        }
        row.add(rejMsgCount != null ? rejMsgCount.toString() : "0");
        result.addDataRow(row);

        return result;
    }

    private TableResult readStreamStats(CompositeData compositeData) {
        TableResult result;
        if (compositeData == null) {
            result = new TableResult(Boolean.FALSE);
            result.setErrMsg("No stream statistics information available for this session");
            return result;
        }
        Integer msgInCount = (Integer) compositeData.get(StreamStatsParam.MsgInCount.getValue());
        Integer msgOutCount = (Integer) compositeData.get(StreamStatsParam.MsgOutCount.getValue());
        Integer rejMsgCount = (Integer) compositeData.get(StreamStatsParam.MsgRejectCount.getValue());
        Integer discMsgCount = (Integer) compositeData.get(StreamStatsParam.MsgDiscardCount.getValue());
        List<FieldData> fields = new ArrayList<FieldData>();
        fields.add(new FieldData(StreamStatsParam.MsgInCount.getValue(), "No Msg In"));
        fields.add(new FieldData(StreamStatsParam.MsgOutCount.getValue(), "No Msg Out"));
        fields.add(new FieldData(StreamStatsParam.MsgRejectCount.getValue(), "No Reject"));
        fields.add(new FieldData(StreamStatsParam.MsgDiscardCount.getValue(), "No Discard"));
        result = new TableResult(fields);
        List<String> row = new ArrayList<String>();
        row.add(msgInCount != null ? new DecimalFormat("#,##0").format(msgInCount) : "0");
        row.add(msgOutCount != null ? new DecimalFormat("#,##0").format(msgOutCount) : "0");
        row.add(rejMsgCount != null ? new DecimalFormat("#,##0").format(rejMsgCount) : "0");
        row.add(discMsgCount != null ? new DecimalFormat("#,##0").format(discMsgCount) : "0");
        result.addDataRow(row);

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
