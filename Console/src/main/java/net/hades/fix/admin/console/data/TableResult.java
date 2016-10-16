/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TableResult.java
 *
 * $Id: TableResult.java,v 1.7 2011-03-21 04:53:04 vrotaru Exp $
 */
package net.hades.fix.admin.console.data;

import java.util.ArrayList;
import java.util.List;

/**
 * The result of an executed command that is an array of arrays of String objects.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 08/05/2010
 */
public class TableResult extends Result {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private List<FieldData> metadata;

    private List<List<String>> data;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public TableResult() {
        super();
        data = new ArrayList<List<String>>();
        metadata = new ArrayList<FieldData>();
    }

    public TableResult(Boolean outcome) {
        this();
        this.outcome = outcome;
    }

    public TableResult(List<FieldData> metadata) {
        this(Boolean.TRUE);
        this.metadata = new ArrayList<FieldData>();
        this.metadata.addAll(metadata);
        data = new ArrayList<List<String>>();
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public List<String> getDataRow(int rowNum) {
        return data.get(rowNum);
    }

    public void addDataRow(List<String> row) {
        data.add(row);
    }
    
    public boolean setDataRow(int rowNum, List<String> dataRow) {
        if (rowNum >= getSize()) {
            return false;
        }
        this.data.set(rowNum, dataRow);
        
        return true;
    }

    public void addTableData(TableResult tableResult) {
        for (int i = 0; i < tableResult.getSize(); i++) {
            this.data.add(tableResult.getDataRow(i));
        }
    }

    public int getSize() {
        return data.size();
    }

    public List<FieldData> getMetadata() {
        return metadata;
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
