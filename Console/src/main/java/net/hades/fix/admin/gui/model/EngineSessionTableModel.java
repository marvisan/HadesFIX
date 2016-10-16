/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EngineSessionTableModel.java
 *
 * $Id$
 */
package net.hades.fix.admin.gui.model;

import net.hades.fix.admin.console.data.TableResult;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import net.hades.fix.admin.gui.resources.Messages;

/**
 * Table model for Engine sessions.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class EngineSessionTableModel extends AbstractTableModel {
    
    private static final long serialVersionUID = 1L;
    
    private TableResult sessions;
    private String displayColor;
    private Integer connectionId;
   
    private static final String[] ColumnNames = new String[] { Messages.getString("HadesAdminConsole.sessionList.column.id"), 
        Messages.getString("HadesAdminConsole.sessionList.column.counterparty"), 
        Messages.getString("HadesAdminConsole.sessionList.column.party"),
        Messages.getString("HadesAdminConsole.sessionList.column.type"), 
        Messages.getString("HadesAdminConsole.sessionList.column.rxSeqNo"), 
        Messages.getString("HadesAdminConsole.sessionList.column.txSeqNo"),
        Messages.getString("HadesAdminConsole.sessionList.column.status")
    };
    
    private static final Class[] ColumnClasses = new Class[]{ String.class, 
        String.class, 
        String.class,
        String.class,
        String.class, 
        String.class, 
        String.class
    };

    public EngineSessionTableModel() {
        displayColor = "0,0,0";
    }
    
    public EngineSessionTableModel(TableResult sessions) {
        this();
        this.sessions = sessions;
    }

    @Override
    public int getRowCount() {
        return sessions != null ? sessions.getSize() : 0;
    }

    @Override
    public int getColumnCount() {
        return ColumnNames.length;
    }

    @Override
    public String getColumnName(int col) { return ColumnNames[col]; }
    
    @Override
    public Class getColumnClass(int col) { return ColumnClasses[col]; }
  
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: return sessions.getDataRow(rowIndex).get(0);    //sessionId
            case 1: return sessions.getDataRow(rowIndex).get(1);    //counterparty
            case 2: return sessions.getDataRow(rowIndex).get(2);    //party
            case 3: return sessions.getDataRow(rowIndex).get(3);    //sessionType
            case 4: return sessions.getDataRow(rowIndex).get(5);    //rxSeqNo
            case 5: return sessions.getDataRow(rowIndex).get(6);    //txSeqNo
            case 6: return sessions.getDataRow(rowIndex).get(4);    //sessionStatus
            default: return null;
        }
    }
   
    @Override
    public boolean isCellEditable(int row, int col) {
        if (col == 4) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (sessions == null) {
            return;
        }
        sessions.getDataRow(rowIndex).set(getColumnForIndex(columnIndex), (String)value);
        fireTableDataChanged();
    }

    public void setSessions(TableResult sessions) {
        this.sessions = sessions;
        fireTableDataChanged();
    }

    public String getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(String displayColor) {
        this.displayColor = displayColor;
    }

    public Integer getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Integer connectionId) {
        this.connectionId = connectionId;
    }

    public void updateSession(TableResult configSession) {
        if (sessions == null) {
            return;
        }
        List<String> currentData = configSession.getDataRow(0);
        for (int i = 0; i < sessions.getSize(); i++) {
            List<String> oldData = sessions.getDataRow(i);
            // matching cpty-party
            if (oldData.get(1).equals(currentData.get(1)) && oldData.get(2).equals(currentData.get(2))) {
                sessions.setDataRow(i, currentData);
            }
        }
        fireTableDataChanged();
    }

    private int getColumnForIndex(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return 0;
                
            case 1:
                return 1;
              
            case 2:
                return 2;
               
            case 3:
                return 3;
               
            case 4:
                return 5;
               
            case 5:
                return 6;
               
            case 6:
                return 4;
                
            default:
                return 0;
        }
    }
 }
