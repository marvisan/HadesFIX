/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SessionStats.java
 *
 * $Id: SessionStats.java,v 1.4 2011-03-27 00:27:44 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt.data;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeDataView;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;

/**
 * Stats collected by the session processes.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 27/04/2010
 */
public class SessionStats extends Stats implements CompositeDataView {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    private static final String[] SESS_STATS_ITEMS;
    private static final String[] SESS_STATS_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] SESS_STATS_OPEN_TYPES;

    public static CompositeType DataType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
	try {
            SESS_STATS_ITEMS = new String[] {"startTimestamp", "transportStats", "protocolStats", "consumerStreamStats",
                "producerStreamStats"};
            SESS_STATS_ITEMS_DESCRIPTION = new String[] {"Timestamp when session started", "Transport statistics", "Protocol statistics",
                "Consumer stream statistics", "Producer stream statistics"};
            SESS_STATS_OPEN_TYPES = new OpenType<?>[] {SimpleType.DATE, TransportStats.DataType, ProtocolStats.DataType,
                StreamStats.DataType, StreamStats.DataType};
            DataType = new CompositeType("SessionStatsCompositeType", "Session stats composite type", SESS_STATS_ITEMS,
                    SESS_STATS_ITEMS_DESCRIPTION, SESS_STATS_OPEN_TYPES);
        } catch (OpenDataException e) {
	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    PrintWriter pout = new PrintWriter(bout);
	    e.printStackTrace(pout);
	    pout.flush();
	    throw new RuntimeException(bout.toString());
	}
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private TransportStats transportStats;

    private ProtocolStats protocolStats;

    private StreamStats consumerStreamStats;

    private StreamStats producerStreamStats;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SessionStats() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public StreamStats getConsumerStreamStats() {
        return consumerStreamStats;
    }

    public void setConsumerStreamStats(StreamStats consumerStreamStats) {
        this.consumerStreamStats = consumerStreamStats;
    }

    public StreamStats getProducerStreamStats() {
        return producerStreamStats;
    }

    public void setProducerStreamStats(StreamStats producerStreamStats) {
        this.producerStreamStats = producerStreamStats;
    }

    public ProtocolStats getProtocolStats() {
        return protocolStats;
    }

    public void setProtocolStats(ProtocolStats protocolStats) {
        this.protocolStats = protocolStats;
    }

    public TransportStats getTransportStats() {
        return transportStats;
    }

    public void setTransportStats(TransportStats transportStats) {
        this.transportStats = transportStats;
    }

    @Override
    public CompositeData toCompositeData(CompositeType ct) {
        try {
            List<String> itemNames = new ArrayList<String>(ct.keySet());
            List<String> itemDescriptions = new ArrayList<String>(itemNames.size());
            List<OpenType<?>> itemTypes = new ArrayList<OpenType<?>>();
            for (String item : itemNames) {
                itemDescriptions.add(ct.getDescription(item));
                itemTypes.add(ct.getType(item));
            }
            if (transportStats == null) {
                transportStats = new TransportStats();
            }
            if (protocolStats == null) {
                protocolStats = new ProtocolStats();
            }
            if (consumerStreamStats == null) {
                consumerStreamStats = new StreamStats();
            }
            if (producerStreamStats == null) {
                producerStreamStats = new StreamStats();
            }

            CompositeType xct = new CompositeType(ct.getTypeName(),
                    ct.getDescription(),
                    itemNames.toArray(new String[itemNames.size()]),
                    itemDescriptions.toArray(new String[itemDescriptions.size()]),
                    itemTypes.toArray(new OpenType<?>[itemTypes.size()]));
            CompositeData cd = new CompositeDataSupport(xct, SESS_STATS_ITEMS,
                    new Object[] {startTimestamp,
                        transportStats.toCompositeData(TransportStats.DataType),
                        protocolStats.toCompositeData(ProtocolStats.DataType),
                        consumerStreamStats.toCompositeData(StreamStats.DataType),
                        producerStreamStats.toCompositeData(StreamStats.DataType)});
            assert ct.isValue(cd);

            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
