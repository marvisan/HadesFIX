/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.mgmt.data;

import net.hades.fix.engine.process.session.SessionType;

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
 * FIX process management data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class ProtocolProcessData extends ProcessData implements CompositeDataView {

    private static final long serialVersionUID = 1L;

    private static final String[] PROTO_DATA_ITEMS;
    private static final String[] PROTO_DATA_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] PROTO_DATA_OPEN_TYPES;

    public static CompositeType DataType;

    static {
	try {
            PROTO_DATA_ITEMS = new String[] {"id", "name", "type", "status", "config", "rxSeqNo", "txSeqNo"};
            PROTO_DATA_ITEMS_DESCRIPTION = new String[] {"Thread identifier", "Process name", "Type", "Status of the process",
                "Configuration data string", "Rx Seq", "Tx Seq"};
            PROTO_DATA_OPEN_TYPES = new OpenType<?>[] {SimpleType.STRING, SimpleType.STRING, SimpleType.STRING, SimpleType.STRING,
                SimpleType.STRING, SimpleType.INTEGER, SimpleType.INTEGER};
            DataType = new CompositeType("ProtocolCompositeType", "Protocol data composite type", PROTO_DATA_ITEMS,
                    PROTO_DATA_ITEMS_DESCRIPTION, PROTO_DATA_OPEN_TYPES);
        } catch (OpenDataException e) {
	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    PrintWriter pout = new PrintWriter(bout);
	    e.printStackTrace(pout);
	    pout.flush();
	    throw new RuntimeException(bout.toString());
	}
    }

    private int rxSeqNo;

    private int txSeqNo;

    private SessionType sessionType;

    public ProtocolProcessData() {
    }

    public int getRxSeqNo() {
        return rxSeqNo;
    }

    public void setRxSeqNo(int rxSeqNo) {
        this.rxSeqNo = rxSeqNo;
    }

    public int getTxSeqNo() {
        return txSeqNo;
    }

    public void setTxSeqNo(int txSeqNo) {
        this.txSeqNo = txSeqNo;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    @Override
    public CompositeData toCompositeData(CompositeType ct) {
        try {
            List<String> itemNames = new ArrayList<>(ct.keySet());
            List<String> itemDescriptions = new ArrayList<>(itemNames.size());
            List<OpenType<?>> itemTypes = new ArrayList<>();
            for (String item : itemNames) {
                itemDescriptions.add(ct.getDescription(item));
                itemTypes.add(ct.getType(item));
            }

            CompositeType xct = new CompositeType(ct.getTypeName(),
                    ct.getDescription(),
                    itemNames.toArray(new String[itemNames.size()]),
                    itemDescriptions.toArray(new String[itemDescriptions.size()]),
                    itemTypes.toArray(new OpenType<?>[itemTypes.size()]));
            CompositeData cd = new CompositeDataSupport(xct, PROTO_DATA_ITEMS,
                    new Object[] {id, name, sessionType != null ? sessionType.name() : "UNKN",
                        status != null ? status.name() : "UNKNOWN" ,
                        config, rxSeqNo, txSeqNo});
            assert ct.isValue(cd);

            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
