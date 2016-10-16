/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AlertTableCellRenderer.java
 *
 * $Id$
 */
package net.hades.fix.admin.gui.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import net.hades.fix.admin.gui.resources.Messages;

/**
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class AlertTableCellRenderer extends DefaultTableCellRenderer {
    
    private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        int modelRow = table.convertRowIndexToModel(row);
        if (table.getModel().getRowCount() == 0) {
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
        String colorCode = table.getModel().getValueAt(modelRow, table.getModel().getColumnCount() - 1).toString();
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(0);
        table.getColumnModel().getColumn(1).setMinWidth(0);
        table.getColumnModel().getColumn(1).setPreferredWidth(0);
        table.getColumnModel().getColumn(1).setWidth(0);
        table.getColumnModel().getColumn(6).setMaxWidth(100000);
        table.getColumnModel().getColumn(6).setMinWidth(500);
        table.getColumnModel().getColumn(6).setPreferredWidth(500);
        Color displayColor = new Color(0,0,0);
        if (colorCode != null && !colorCode.isEmpty()) {
            String[] color = colorCode.split(",");
            if (color.length == 3) {
                displayColor = new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]), Integer.parseInt(color[2]));
            }
        }
        table.setRowHeight(18);
        setForeground(displayColor);
        if (column == 0) {
            JLabel connectLabel;
            String alertSeverity = (String) value;
            if ("FATAL".equals(alertSeverity)) {
                connectLabel = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/error.png")));
                connectLabel.setToolTipText(Messages.getString("HadesAdminConsole.alertSeverity.fatal"));
            } else if ("WARNING".equals(alertSeverity)) {
                connectLabel = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/warning.png")));
                connectLabel.setToolTipText(Messages.getString("HadesAdminConsole.alertSeverity.warning"));
            } else if ("RECOVERABLE".equals(alertSeverity)) {
                connectLabel = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/recoverable.png")));
                connectLabel.setToolTipText(Messages.getString("HadesAdminConsole.alertSeverity.recoverable"));
            } else if ("INFO".equals(alertSeverity)) {
                connectLabel = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/information.png")));
                connectLabel.setToolTipText(Messages.getString("HadesAdminConsole.alertSeverity.info"));
            } else if ("TEST".equals(alertSeverity)) {
                connectLabel = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/test.png")));
                connectLabel.setToolTipText(Messages.getString("HadesAdminConsole.alertSeverity.test"));
            } else {
                connectLabel = new JLabel(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/undefined.png")));
                connectLabel.setToolTipText(Messages.getString("HadesAdminConsole.alertSeverity.undefined"));
            }
            connectLabel.setSize(20, 18);
            return connectLabel;
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
