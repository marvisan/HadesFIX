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

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeDataView;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * TCP server socket connection.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name = "tcpServerConnection")
@XmlType(name = "ServerTcpConnectionInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class ServerTcpConnectionInfo extends TcpConnectionInfo implements CompositeDataView, Serializable {

    private static final long serialVersionUID = 1L;

    private static final String[] COMPOSITE_DATA_ITEMS;
    private static final String[] COMPOSITE_DATA_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] COMPOSITE_DATA_OPEN_TYPES;

    public static CompositeType DataType;

    static {
        try {
	    COMPOSITE_DATA_ITEMS = new String[]{"id", "host", "port", "soLinger", "tcpNodelay",
		"soTimeout", "soRcvbuf", "soSndbuf", "sendKeepAlive", "restrHostsIPAddresses"};
	    COMPOSITE_DATA_ITEMS_DESCRIPTION = new String[]{"Tcp Server Id", "Sell side host", "Sell side port",
		"Socket SO_LINGER Value", "Socket TCP_NODELAY Value", "Socket SO_TIMEOUT Value", "Socket Rx Buffer Size",
		"Socket Tx Buffer Size", "Socket SEND_ALIVE Value", "Restricted Hosts List"};
	    COMPOSITE_DATA_OPEN_TYPES = new OpenType<?>[]{SimpleType.STRING, SimpleType.STRING, SimpleType.INTEGER, SimpleType.INTEGER, 
		SimpleType.BOOLEAN, SimpleType.INTEGER, SimpleType.INTEGER, SimpleType.INTEGER, SimpleType.BOOLEAN,
		SimpleType.STRING};

	    DataType = new CompositeType("ServerTcpConnectionInfo", "Server TCP Connection Data", COMPOSITE_DATA_ITEMS,
		    COMPOSITE_DATA_ITEMS_DESCRIPTION, COMPOSITE_DATA_OPEN_TYPES);
        } catch (OpenDataException e) {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            PrintWriter pout = new PrintWriter(bout);
            e.printStackTrace(pout);
            pout.flush();
            throw new RuntimeException(bout.toString());
        }
    }

    @XmlAttribute(name = "restrHostsIPAddresses")
    protected String restrHostsIPAddresses;

    public ServerTcpConnectionInfo() {
    }

    public String getRestrHostsIPAddresses() {
        return restrHostsIPAddresses;
    }

    public void setRestrHostsIPAddresses(String restrHostsIPAddresses) {
        this.restrHostsIPAddresses = restrHostsIPAddresses;
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
            CompositeData cd = new CompositeDataSupport(xct, COMPOSITE_DATA_ITEMS, new Object[]{id, host, port, socketLingerTimeout, 
		tcpNodelay, socketTimeout, socketRcvbuf, socketSndbuf, sendKeepAlive, restrHostsIPAddresses});
            assert ct.isValue(cd);
            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ServerTcpConnectionInfo[");
        sb.append(super.toString());
        if (restrHostsIPAddresses != null) {
            sb.append(",").append("restrHostsIPAddresses=").append(restrHostsIPAddresses);
        }
        sb.append("]}");
        return sb.toString();
    }
}
