/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OutcomeData.java
 *
 * $Id: OutcomeData.java,v 1.2 2011-03-17 07:36:01 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt.data;

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

/**
 * Outcome of a non result management method.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 13/05/2010
 */
public class OutcomeData implements CompositeDataView, Serializable {

    private static final long serialVersionUID = 1L;

    private static final String[] OUTCOME_DATA_ITEMS;
    private static final String[] OUTCOME_DATA_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] OUTCOME_DATA_OPEN_TYPES;
    
    public static final CompositeType DataType;

    static {
	try {
            OUTCOME_DATA_ITEMS = new String[] {"outcome", "errMsg"};
            OUTCOME_DATA_ITEMS_DESCRIPTION = new String[] {"Success", "Error Message"};
            OUTCOME_DATA_OPEN_TYPES = new OpenType<?>[] {SimpleType.BOOLEAN, SimpleType.STRING};

            DataType = new CompositeType("OutcomeDataType", "Outcome of an operation execution", OUTCOME_DATA_ITEMS,
                    OUTCOME_DATA_ITEMS_DESCRIPTION, OUTCOME_DATA_OPEN_TYPES);
        } catch (OpenDataException e) {
	    ByteArrayOutputStream bout = new ByteArrayOutputStream();
	    PrintWriter pout = new PrintWriter(bout);
	    e.printStackTrace(pout);
	    pout.flush();
	    throw new RuntimeException(bout.toString());
	}
    }

    private boolean outcome;

    private String errMsg;

    public OutcomeData() {
    }

    public OutcomeData(boolean outcome) {
        this.outcome = outcome;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public boolean isOutcome() {
        return outcome;
    }

    public void setOutcome(boolean outcome) {
        this.outcome = outcome;
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
            CompositeData cd = new CompositeDataSupport(xct, OUTCOME_DATA_ITEMS, new Object[] {outcome, errMsg});
            assert ct.isValue(cd);

            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
