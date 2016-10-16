/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TransportProcessData.java
 *
 * $Id: TransportProcessData.java,v 1.4 2011-03-27 00:27:44 vrotaru Exp $
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
 * TCP process management data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 28/04/2010
 */
public class TransportProcessData extends ProcessData implements CompositeDataView {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    private static final String[] TRANS_DATA_ITEMS;
    private static final String[] TRANS_DATA_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] TRANS_DATA_OPEN_TYPES;

    public static CompositeType DataType;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
	try {
            TRANS_DATA_ITEMS = new String[] {"id", "name", "status", "config"};
            TRANS_DATA_ITEMS_DESCRIPTION = new String[] {"Thread identifier", "Process name", "Status of the process", "Configuration data string"};
            TRANS_DATA_OPEN_TYPES = new OpenType<?>[] {SimpleType.STRING, SimpleType.STRING, SimpleType.STRING, SimpleType.STRING};
            DataType = new CompositeType("TansportCompositeType", "Transport data composite type", TRANS_DATA_ITEMS,
                    TRANS_DATA_ITEMS_DESCRIPTION, TRANS_DATA_OPEN_TYPES);
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TransportProcessData() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

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
            if (status == null) {
                status = ProcessStatus.SHUTDOWN;
            }

            CompositeType xct = new CompositeType(ct.getTypeName(),
                    ct.getDescription(),
                    itemNames.toArray(new String[itemNames.size()]),
                    itemDescriptions.toArray(new String[itemDescriptions.size()]),
                    itemTypes.toArray(new OpenType<?>[itemTypes.size()]));
            CompositeData cd = new CompositeDataSupport(xct, TRANS_DATA_ITEMS,
                    new Object[] {id, name, status.name(), config});
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
