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

import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import net.hades.fix.admin.gui.config.model.AlertFilterInfo;
import net.hades.fix.admin.gui.resources.Messages;

/**
 * Table model for Engine sessions.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class AlertTableModel extends AbstractTableModel {
    
    private static final long serialVersionUID = 1L;
    
    private LinkedList<AlertEvent> alerts;
    private LinkedList<AlertEvent> filteredAlerts;
    private AlertFilterInfo filter;
   
    private static final String[] ColumnNames = new String[] { "Type",
        "Id",
        Messages.getString("HadesAdminConsole.alertList.column.time"), 
        Messages.getString("HadesAdminConsole.alertList.column.session"),
        Messages.getString("HadesAdminConsole.alertList.column.component"), 
        Messages.getString("HadesAdminConsole.alertList.column.code"), 
        Messages.getString("HadesAdminConsole.alertList.column.message"), 
        "errorMessage",
        "errorStack",
        "displayColor"
    };
    
    private static final Class[] ColumnClasses = new Class[]{ String.class, 
        String.class, 
        String.class,
        String.class,
        String.class, 
        String.class, 
        String.class, 
        String.class, 
        String.class, 
        String.class
    };

    public AlertTableModel() {
        this.alerts = new LinkedList<AlertEvent>();
        this.filteredAlerts = new LinkedList<AlertEvent>();
    }

    @Override
    public int getRowCount() {
        return filteredAlerts.size();
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
        if (filteredAlerts == null || filteredAlerts.size() == 0 || rowIndex >= filteredAlerts.size()) {
            return null;
        }
        switch(columnIndex) {
            case 0: return filteredAlerts.get(rowIndex).getType();          //type
            case 1: return filteredAlerts.get(rowIndex).getId();            //id
            case 2: return filteredAlerts.get(rowIndex).getTime();          //time
            case 3: return filteredAlerts.get(rowIndex).getSession();       //session
            case 4: return filteredAlerts.get(rowIndex).getComponent();     //component
            case 5: return filteredAlerts.get(rowIndex).getCode();          //code
            case 6: return filteredAlerts.get(rowIndex).getMessage();       //message
            case 7: return filteredAlerts.get(rowIndex).getErrorMessage();  //errorMessage
            case 8: return filteredAlerts.get(rowIndex).getErrorStack();    //errorStack
            case 9: return filteredAlerts.get(rowIndex).getDisplayColor();  //displayColor
            
            default: return null;
        }
    }
   
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: 
                filteredAlerts.get(rowIndex).setType((String) value);
                break;
                                                
            case 1: 
                filteredAlerts.get(rowIndex).setId((String) value);
                break;

            case 2: 
                filteredAlerts.get(rowIndex).setTime((String) value);
                break;
                
            case 3: 
                filteredAlerts.get(rowIndex).setSession((String) value);
                break;
                
            case 4:
                filteredAlerts.get(rowIndex).setComponent((String) value);
                break;
                
            case 5:
                filteredAlerts.get(rowIndex).setCode((String) value);
                break;
                
            case 6:
                filteredAlerts.get(rowIndex).setMessage((String) value);
                break;
                
            case 7:
                filteredAlerts.get(rowIndex).setErrorMessage((String) value);
                break;
                
            case 8:
                filteredAlerts.get(rowIndex).setErrorStack((String) value);
                break;
                
            case 9: 
                filteredAlerts.get(rowIndex).setDisplayColor((String) value);
                break;

            default: 
                // do nothing
        }
        fireTableDataChanged();
    }

    public void setFilter(AlertFilterInfo filter) {
        this.filter = filter;
        this.filteredAlerts = new LinkedList<AlertEvent>();
        for (AlertEvent alert : alerts) {
            copyFilteredEvent(alert);
        }
        fireTableDataChanged();
    }

    public synchronized void addAlert(AlertEvent alertEvent) {
        alerts.add(alertEvent);
        if (copyFilteredEvent(alertEvent)) {
            fireTableDataChanged();
        }
    }
    
    public synchronized AlertEvent getAlertById(String id) {
        AlertEvent event = null;
        if (id == null) {
            return event;
        }
        for (AlertEvent alert : alerts) {
            if (id.equals(alert.getId())) {
                event = alert;
                break;
            }
        }
        
        return event;
    }

    public synchronized void removeAlert(String id) {
        for (Iterator<AlertEvent> it = alerts.iterator(); it.hasNext(); ) {
            if (id.equals(it.next().getId())) {
                it.remove();
            }
        }
        for (Iterator<AlertEvent> it = filteredAlerts.iterator(); it.hasNext(); ) {
            if (id.equals(it.next().getId())) {
                it.remove();
            }
        }
        fireTableDataChanged();
    }

    public synchronized void clearNotifications() {
        filteredAlerts.clear();
        alerts.clear();
        fireTableDataChanged();
    }

    private boolean copyFilteredEvent(AlertEvent alertEvent) {
        if (filter == null) {
            filteredAlerts.add(alertEvent);
            return true;
        } else {
            if (filter.getFilterFatal()) {
                if ("FATAL".equals(alertEvent.getType())) {
                    return false;
                }
            }
            if (filter.getFilterWarning()) {
                if ("WARNING".equals(alertEvent.getType())) {
                    return false;
                }
            } 
            if (filter.getFilterRecoverable()) {
                if ("RECOVERABLE".equals(alertEvent.getType())) {
                    return false;
                }
            }
            if (filter.getFilterInfo()) {
                if ("INFO".equals(alertEvent.getType())) {
                    return false;
                }
            } 
            if (filter.getFilterTest()) {
                if ("TEST".equals(alertEvent.getType())) {
                    return false;
                }
            }
            if (filter.getSession() != null && !filter.getSession().isEmpty()) {
                if (filter.getSession().equals(alertEvent.getSession())) {
                    return false;
                }
            }
            if (filter.getComponents() != null && filter.getComponents().size() > 0) {
                for (String component : filter.getComponents()) {
                    if (component.equals(alertEvent.getComponent())) {
                        return false;
                    }
                }
            }
            filteredAlerts.add(alertEvent);
            return true;
        }
    }
}
