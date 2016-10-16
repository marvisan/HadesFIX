/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CmdLineResult.java
 *
 * $Id: CmdLineResult.java,v 1.1 2011-03-17 07:36:17 vrotaru Exp $
 */
package net.hades.fix.admin.console.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Result suitable for a command line utility.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 17/03/2011
 */
public class CmdLineResult {

    private List<String> textRows;

    public CmdLineResult() {
        textRows = new ArrayList<String>();
    }
    
    public void addRow(String textRow) {
        textRows.add(textRow);
    }

    public void insertRow(int index, String textRow) {
        textRows.add(index, textRow);
    }

    public Iterator<String> iterator() {
        return textRows.iterator();
    }

    public void addAll(List<String> rowsOfText) {
        textRows.addAll(rowsOfText);
    }
}
