/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RxSeqTableCellEditor.java
 *
 * $Id$
 */
package net.hades.fix.admin.gui.editor;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 * Enable editing of the RxSeq value in order to execute a sequence reset.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class RxSeqTableCellEditor extends AbstractCellEditor implements TableCellEditor {
    private static final long serialVersionUID = 1L;
    
    private JTextField textField = new JTextField();
    
    public RxSeqTableCellEditor() {
        textField.setColumns(15);
        textField.setMinimumSize(new java.awt.Dimension(200, 24));
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        textField.setText((String) value);
        return textField;
    }

    @Override
    public Object getCellEditorValue() {
        return textField.getText();
    }
}
