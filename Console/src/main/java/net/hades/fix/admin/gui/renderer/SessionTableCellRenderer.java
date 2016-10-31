/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SessionTableCellRenderer.java
 *
 * $Id$
 */
package net.hades.fix.admin.gui.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import net.hades.fix.admin.gui.model.EngineSessionTableModel;

/**
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class SessionTableCellRenderer extends DefaultTableCellRenderer {
    
    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String colorCode = ((EngineSessionTableModel) table.getModel()).getDisplayColor();
        Color displayColor = new Color(0,0,0);
        if (colorCode != null && !colorCode.isEmpty()) {
            String[] color = colorCode.split(",");
            if (color.length == 3) {
                displayColor = new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]), Integer.parseInt(color[2]));
            }
        }
        setForeground(displayColor);

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
