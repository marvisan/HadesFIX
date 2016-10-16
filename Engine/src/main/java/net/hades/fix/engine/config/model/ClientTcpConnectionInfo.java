/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.management.openmbean.*;
import javax.xml.bind.annotation.*;


/**
 * TCP socket connection.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name = "tcpClientConnection")
@XmlType(name = "ClientTcpConnectionInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class ClientTcpConnectionInfo extends TcpConnectionInfo implements CompositeDataView, Serializable {

    private static final long serialVersionUID = 1L;

    private static final String[] COMPOSITE_DATA_ITEMS;
    private static final String[] COMPOSITE_DATA_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] COMPOSITE_DATA_OPEN_TYPES;
    private static final TabularType TABULAR_BACKUP_CONNECTIONS_TYPE;
    private static final TabularData TABULAR_BACKUP_CONNECTIONS;
    private static final String[] BACKUP_CONNECTIONS_INDEX;

    public static CompositeType DataType;

    static {
        try {
            BACKUP_CONNECTIONS_INDEX = new String[]{"host", "port"};
            COMPOSITE_DATA_ITEMS = new String[]{"host", "port", "rxBufferSize", "txBufferSize", "soLinger", "tcpNodelay",
                    "soTimeout", "soRcvbuf", "soSndbuf", "sendKeepAlive", "numOfRetries", "retrySecondsToWait", "proxyHost",
                    "proxyPort", "backupConnections"};
            COMPOSITE_DATA_ITEMS_DESCRIPTION = new String[]{"Sell side host", "Sell side port", "Msg Rx Buffer Size",
                    "Msg Tx Buffer Size", "Socket SO_LINGER Value", "Socket TCP_NODELAY Value", "Socket SO_TIMEOUT Value",
                    "Socket Rx Buffer Size", "Socket Tx Buffer Size", "Socket SEND_ALIVE Value", "Number of connections retries",
                    "Seconds wait before each retry", "Proxy Host", "Proxy Port", "List of Backup Connections"};
            TABULAR_BACKUP_CONNECTIONS_TYPE = new TabularType("Backup Connections", "List of Backup Connections",
                    BackupConnectionInfo.DataType, BACKUP_CONNECTIONS_INDEX);
            TABULAR_BACKUP_CONNECTIONS = new TabularDataSupport(TABULAR_BACKUP_CONNECTIONS_TYPE);
            COMPOSITE_DATA_OPEN_TYPES = new OpenType<?>[]{SimpleType.STRING, SimpleType.INTEGER, SimpleType.INTEGER, SimpleType.INTEGER,
                    SimpleType.INTEGER, SimpleType.BOOLEAN, SimpleType.INTEGER, SimpleType.INTEGER, SimpleType.INTEGER, SimpleType.BOOLEAN,
                    SimpleType.INTEGER, SimpleType.INTEGER, SimpleType.STRING, SimpleType.INTEGER, TABULAR_BACKUP_CONNECTIONS_TYPE};

            DataType = new CompositeType("ClientTcpConnectionInfo", "Client TCP Connection Data", COMPOSITE_DATA_ITEMS,
                    COMPOSITE_DATA_ITEMS_DESCRIPTION, COMPOSITE_DATA_OPEN_TYPES);
        } catch (OpenDataException e) {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            PrintWriter pout = new PrintWriter(bout);
            e.printStackTrace(pout);
            pout.flush();
            throw new RuntimeException(bout.toString());
        }
    }

    @XmlAttribute(name = "numOfRetries")
    protected Integer numOfRetries;

    @XmlAttribute(name = "retrySecondsToWait")
    protected Integer retrySecondsToWait;

    @XmlAttribute(name = "proxyHost")
    protected String proxyHost;

    @XmlAttribute(name = "proxyPort")
    protected Integer proxyPort;

    @XmlElementWrapper(name = "backupConnections")
    @XmlElementRef()
    protected BackupConnectionInfo[] backupConnections;

    public ClientTcpConnectionInfo() {
    }

    public Integer getNumOfRetries() {
        return numOfRetries;
    }

    public void setNumOfRetries(Integer numOfRetries) {
        this.numOfRetries = numOfRetries;
    }

    public Integer getRetrySecondsToWait() {
        return retrySecondsToWait;
    }

    public void setRetrySecondsToWait(Integer retrySecondsToWait) {
        this.retrySecondsToWait = retrySecondsToWait;
    }

    public BackupConnectionInfo[] getBackupConnections() {
        return backupConnections;
    }

    public void setBackupConnections(BackupConnectionInfo[] backupConnections) {
        this.backupConnections = backupConnections;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    @Override
    public CompositeData toCompositeData(CompositeType ct) {
        try {
            List<String> itemNames = new ArrayList<>(ct.keySet());
            List<String> itemDescriptions = new ArrayList<>(itemNames.size());
            List<OpenType<?>> itemTypes = new ArrayList<>();
	    itemNames.stream().map((item) -> {
		itemDescriptions.add(ct.getDescription(item));
		return item;
	    }).forEach((item) -> {
		itemTypes.add(ct.getType(item));
	    });

            CompositeType xct = new CompositeType(ct.getTypeName(),
                    ct.getDescription(),
                    itemNames.toArray(new String[itemNames.size()]),
                    itemDescriptions.toArray(new String[itemDescriptions.size()]),
                    itemTypes.toArray(new OpenType<?>[itemTypes.size()]));
            for (BackupConnectionInfo backupConnection : backupConnections) {
                TABULAR_BACKUP_CONNECTIONS.put(backupConnection.toCompositeData(BackupConnectionInfo.DataType));
            }
            CompositeData cd = new CompositeDataSupport(xct, COMPOSITE_DATA_ITEMS, new Object[]{host, port, rxBufferSize, txBufferSize,
                    socketLingerTimeout, tcpNodelay, socketTimeout, socketRcvbuf, socketSndbuf, sendKeepAlive, numOfRetries, retrySecondsToWait,
                    proxyHost, proxyPort, TABULAR_BACKUP_CONNECTIONS});
            assert ct.isValue(cd);
            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ClientTcpConnectionInfo[");
        sb.append(super.toString());
        if (numOfRetries != null) {
            sb.append("numOfRetries=").append(numOfRetries);
        }
        if (retrySecondsToWait != null) {
            sb.append(",").append("retrySecondsToWait=").append(retrySecondsToWait);
        }
        if (proxyHost != null) {
            sb.append(",").append("proxyHost=").append(proxyHost);
        }
        if (proxyPort != null) {
            sb.append(",").append("proxyPort=").append(proxyPort);
        }
        if (backupConnections != null && backupConnections.length > 0) {
            for (BackupConnectionInfo backupConnection : backupConnections) {
                sb.append("backupConnection=").append(backupConnection.toString()).append(",");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}
