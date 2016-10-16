/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConnTableCellRenderer.java
 *
 * $Id$
 */
package net.hades.fix.admin.gui.renderer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class ConnTableCellRenderer extends DefaultTableCellRenderer {
    
    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        int modelRow = table.convertRowIndexToModel(row);
        String colorCode = table.getModel().getValueAt(modelRow, table.getColumnCount()).toString();
        if (column == 5) {
            boolean connected = Boolean.valueOf((String) table.getModel().getValueAt(modelRow, table.getColumnCount() - 1));
            JLabel connectLabel;
            if (connected) {
                connectLabel = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/accept.png")));
            } else {
                connectLabel = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/cancel.png")));
            }
            table.getColumnModel().getColumn(5).setMaxWidth(150);
            table.getColumnModel().getColumn(5).setMinWidth(150);
            return connectLabel;
        }
        table.getColumnModel().getColumn(0).setMaxWidth(50);
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
