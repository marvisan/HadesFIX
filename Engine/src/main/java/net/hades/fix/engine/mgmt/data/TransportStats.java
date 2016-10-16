/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TransportStats.java
 *
 * $Id: TransportStats.java,v 1.3 2011-03-17 07:36:01 vrotaru Exp $
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
 * TCP statsitics collected by a TCP processes.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 27/04/2010
 */
public class TransportStats extends Stats implements CompositeDataView {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    private static final String[] TRANS_STATS_ITEMS;
    private static final String[] TRANS_STATS_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] TRANS_STATS_OPEN_TYPES;

    public static CompositeType DataType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
	try {
            TRANS_STATS_ITEMS = new String[] {"startTimestamp", "bytesIn", "bytesOut"};
            TRANS_STATS_ITEMS_DESCRIPTION = new String[] {"Timestamp when transport started", "No bytes received", "No bytes transmitted"};
            TRANS_STATS_OPEN_TYPES = new OpenType<?>[] {SimpleType.DATE, SimpleType.LONG, SimpleType.LONG};
            DataType = new CompositeType("TansportStatsCompositeType", "Transport statistics composite type", TRANS_STATS_ITEMS,
                    TRANS_STATS_ITEMS_DESCRIPTION, TRANS_STATS_OPEN_TYPES);
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

    private long bytesIn;
    
    private long bytesOut;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TransportStats() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public long getBytesIn() {
        return bytesIn;
    }

    public void setBytesIn(long bytesIn) {
        this.bytesIn = bytesIn;
    }

    public long addBytesIn(long bytesIn) {
        this.bytesIn += bytesIn;
        
        return this.bytesIn;
    }

    public long getBytesOut() {
        return bytesOut;
    }

    public void setBytesOut(long bytesOut) {
        this.bytesOut = bytesOut;
    }

    public long addBytesOut(long bytesOut) {
        this.bytesOut += bytesOut;
        
        return this.bytesOut;
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

            CompositeType xct = new CompositeType(ct.getTypeName(),
                    ct.getDescription(),
                    itemNames.toArray(new String[itemNames.size()]),
                    itemDescriptions.toArray(new String[itemDescriptions.size()]),
                    itemTypes.toArray(new OpenType<?>[itemTypes.size()]));
            CompositeData cd = new CompositeDataSupport(xct, TRANS_STATS_ITEMS,
                    new Object[] {startTimestamp, bytesIn, bytesOut});
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
