/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EngineConnectionTableModel.java
 *
 * $Id$
 */
package net.hades.fix.admin.gui.model;

import javax.swing.table.AbstractTableModel;

import net.hades.fix.admin.gui.config.model.EngineConnectionInfo;
import net.hades.fix.admin.gui.resources.Messages;

/**
 * Table model for Engine connections.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class EngineConnectionTableModel extends AbstractTableModel {
    
    private static final long serialVersionUID = 1L;
    
    private EngineConnectionInfo[] connections;
   
    private String[] columnNames = new String[] { Messages.getString("HadesAdminConsole.connectionList.column.id"), 
        Messages.getString("HadesAdminConsole.connectionList.column.engineName"), 
        Messages.getString("HadesAdminConsole.connectionList.column.engineDescr"),
        Messages.getString("HadesAdminConsole.connectionList.column.hostName"), 
        Messages.getString("HadesAdminConsole.connectionList.column.portNum"), 
        Messages.getString("HadesAdminConsole.connectionList.column.connected"),
        "Color"
    };
    
    private Class[] columnClasses = new Class[]{ String.class, 
        String.class, 
        String.class,
        String.class,
        String.class, 
        String.class,
        String.class
    };

    public EngineConnectionTableModel(EngineConnectionInfo[] connections) {
        this.connections = connections;
    }

    @Override
    public int getRowCount() {
        return connections != null ? connections.length : 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) { return columnNames[col]; }
    
    @Override
    public Class getColumnClass(int col) { return columnClasses[col]; }
  
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: return connections[rowIndex].getId();
            case 1: return connections[rowIndex].getEngineName();
            case 2: return connections[rowIndex].getEngineDescr();
            case 3: return connections[rowIndex].getHost();
            case 4: return connections[rowIndex].getPort();
            case 5: return connections[rowIndex].isConnected() ? Boolean.TRUE.toString() : Boolean.FALSE.toString();
            case 6: return connections[rowIndex].getDisplayColor();
            default: return null;
        }
    }
 
    public void setConnections(EngineConnectionInfo[] connections) {
        this.connections = connections;
        fireTableDataChanged();
    }

    public void updateEngineSession(EngineConnectionInfo connInfo) {
        if (connections == null) {
            return;
        }
        for (EngineConnectionInfo conn : connections) {
            if (conn.getId().equals(connInfo.getId())) {
                conn.setConnected(connInfo.isConnected());
                conn.setEngineName(connInfo.getEngineName());
                conn.setEngineDescr(connInfo.getEngineDescr());
                fireTableDataChanged();
                break;
            }
        }
    }
}
