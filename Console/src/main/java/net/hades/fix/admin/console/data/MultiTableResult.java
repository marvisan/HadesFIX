/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TableResult.java
 *
 * $Id: MultiTableResult.java,v 1.1 2011-03-21 04:53:04 vrotaru Exp $
 */
package net.hades.fix.admin.console.data;

import java.util.HashMap;
import java.util.Map;

/**
 * The result of an executed command that is collection of array of arrays of String objects.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 08/05/2010
 */
public class MultiTableResult extends Result {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private Map<String, TableResult> results;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MultiTableResult() {
        results = new HashMap<String, TableResult>();
    }

    public MultiTableResult(Boolean outcome) {
        this();
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public TableResult getTableResult(String name) {
        return results.get(name);
    }

    public void addTableResult(String name, TableResult result) {
        results.put(name, result);
    }

    public int clearResults() {
        int result = results.size();
        results.clear();

        return result;
    }

    public int getNoResults() {
        return results.size();
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
