/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
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
 * TCP statistics collected by a TCP processes.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class StreamStats extends Stats implements CompositeDataView {

    private static final long serialVersionUID = 1L;

    private static final String[] STREAM_STATS_ITEMS;
    private static final String[] STREAM_STATS_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] STREAM_STATS_OPEN_TYPES;

    public static CompositeType DataType;

    static {
	try {
            STREAM_STATS_ITEMS = new String[] {"startTimestamp", "msgInCount", "msgOutCount", "msgRejectCount", "msgDiscardCount"};
            STREAM_STATS_ITEMS_DESCRIPTION = new String[] {"Timestamp when transport started", "No messages received by the consumer stream",
                "No messages transmitted by the producer stream", "No of messages rejected by the stream", "No of messages discarded by the stream"};
            STREAM_STATS_OPEN_TYPES = new OpenType<?>[] {SimpleType.DATE, SimpleType.INTEGER, SimpleType.INTEGER,
                SimpleType.INTEGER, SimpleType.INTEGER};
            DataType = new CompositeType("StreamStatsCompositeType", "Stream statistics composite type", STREAM_STATS_ITEMS,
                    STREAM_STATS_ITEMS_DESCRIPTION, STREAM_STATS_OPEN_TYPES);
        } catch (OpenDataException e) {
	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    PrintWriter pout = new PrintWriter(bout);
	    e.printStackTrace(pout);
	    pout.flush();
	    throw new RuntimeException(bout.toString());
	}
    }

    private int msgInCount;
    private int msgOutCount;
    private int msgRejectCount;
    private int msgDiscardCount;

    public StreamStats() {
    }

    public int getMsgDiscardCount() {
        return msgDiscardCount;
    }

    public void setMsgDiscardCount(int msgDiscardCount) {
        this.msgDiscardCount = msgDiscardCount;
    }

    public int incrMsgDiscardCount() {
        return ++msgDiscardCount;
    }

    public int getMsgInCount() {
        return msgInCount;
    }

    public void setMsgInCount(int msgInCount) {
        this.msgInCount = msgInCount;
    }

    public int incrMsgInCount() {
        return ++msgInCount;
    }

    public int getMsgOutCount() {
        return msgOutCount;
    }

    public void setMsgOutCount(int msgOutCount) {
        this.msgOutCount = msgOutCount;
    }

    public int incrMsgOutCount() {
        return ++msgOutCount;
    }

    public int getMsgRejectCount() {
        return msgRejectCount;
    }

    public void setMsgRejectCount(int msgRejectCount) {
        this.msgRejectCount = msgRejectCount;
    }

    public int incrMsgRejectCount() {
        return ++msgRejectCount;
    }

    @Override
    public CompositeData toCompositeData(CompositeType ct) {
        try {
            List<String> itemNames = new ArrayList<>(ct.keySet());
            List<String> itemDescriptions = new ArrayList<>(itemNames.size());
            List<OpenType<?>> itemTypes = new ArrayList<OpenType<?>>();
            for (String item : itemNames) {
                itemDescriptions.add(ct.getDescription(item));
                itemTypes.add(ct.getType(item));
            }

            CompositeType xct = new CompositeType(ct.getTypeName(),
                    ct.getDescription(),
                    itemNames.toArray(new String[itemNames.size()]),
                    itemDescriptions.toArray(new String[itemDescriptions.size()]),
                    itemTypes.toArray(new OpenType<?>[itemTypes.size()]));
            CompositeData cd = new CompositeDataSupport(xct, STREAM_STATS_ITEMS,
                    new Object[] {startTimestamp, msgInCount, msgOutCount, msgRejectCount, msgDiscardCount});
            assert ct.isValue(cd);

            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
