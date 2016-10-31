/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HadesAdminConsole.java
 *
 * Created on 19/02/2012, 12:06:09 PM
 */
package net.hades.fix.admin.gui;

import net.hades.fix.admin.command.param.SessionStatsResultParam;
import net.hades.fix.admin.console.data.MultiTableResult;
import net.hades.fix.admin.console.data.OutcomeResult;
import net.hades.fix.admin.console.data.TableResult;
import net.hades.fix.admin.gui.config.ConfigurationException;
import net.hades.fix.admin.gui.config.Configurator;
import net.hades.fix.admin.gui.config.model.AdminConsoleConfigInfo;
import net.hades.fix.admin.gui.config.model.AlertFilterInfo;
import net.hades.fix.admin.gui.config.model.EngineConnectionInfo;
import net.hades.fix.admin.gui.config.model.InternalInfo;
import net.hades.fix.admin.gui.editor.RxSeqTableCellEditor;
import net.hades.fix.admin.gui.model.AlertEvent;
import net.hades.fix.admin.gui.model.EngineConnectionTableModel;
import net.hades.fix.admin.gui.model.EngineSessionStatistics;
import net.hades.fix.admin.gui.model.EngineSessionTableModel;
import net.hades.fix.admin.gui.renderer.AlertTableCellRenderer;
import net.hades.fix.admin.gui.renderer.ConnTableCellRenderer;
import net.hades.fix.admin.gui.renderer.SessionTableCellRenderer;
import net.hades.fix.admin.gui.resources.Messages;
import net.hades.fix.admin.gui.view.AboutView;
import net.hades.fix.admin.gui.view.AlertFilterView;
import net.hades.fix.admin.gui.view.ErrorDetailsView;
import net.hades.fix.admin.gui.view.MaintainConnectionView;
import net.hades.fix.admin.session.EngineNotificationProcessor;
import net.hades.fix.admin.session.EngineSessionWorker;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.commons.thread.ThreadUtil;

/**
 * Main console frame.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public class HadesAdminConsole extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    private static final Logger LOGGER = Logger.getLogger(HadesAdminConsole.class.getName());

    private AdminConsoleConfigInfo configuration;
    private EngineConnectionTableModel connectionTableModel;
    private EngineSessionTableModel sessionTableModel;
    private ConcurrentMap<Integer, EngineSessionWorker> activeConnections;
    private EngineSessionStatistics sessionStatistics;
    private EngineNotificationProcessor notificationProcessor;
    private Image frameIcon = new ImageIcon(this.getClass().getResource("/net/hades/fix/admin/gui/resources/icons/hades.jpg")).getImage();
    
    /**
     * Creates new form HadesAdminConsole
     */
    public HadesAdminConsole() {
        loadConfiguration();
        initConfiguration();
        initComponents();
    }

    public void closeConnection(EngineConnectionInfo connectionInfo) {
        try {
            connectionInfo.setConnected(false);
            activeConnections.remove(connectionInfo.getId());
            connectionTableModel.updateEngineSession(connectionInfo);
            if (sessionTableModel.getConnectionId() != null && sessionTableModel.getConnectionId().equals(connectionInfo.getId())) {
                sessionTableModel.setSessions(null);
            }
            if (sessionStatistics.getConnectionId() != null && sessionStatistics.getConnectionId().equals(connectionInfo.getId())) {
                MultiTableResult stats = new MultiTableResult(Boolean.TRUE);
                stats.addTableResult(SessionStatsResultParam.SessionStats.toString(), new TableResult(Boolean.TRUE));
                stats.addTableResult(SessionStatsResultParam.TransportStats.toString(), new TableResult(Boolean.TRUE));
                stats.addTableResult(SessionStatsResultParam.ProtocolStats.toString(), new TableResult(Boolean.TRUE));
                stats.addTableResult(SessionStatsResultParam.ConsumerStreamStats.toString(), new TableResult(Boolean.TRUE));
                stats.addTableResult(SessionStatsResultParam.ProducerStreamStats.toString(), new TableResult(Boolean.TRUE));
                sessionStatistics.updateSessionStatistics(stats);
                if (sessStatsInternalFrame.isVisible()) {
                    if (!sessStatsInternalFrame.isIcon()) {
                        sessStatsInternalFrame.setIcon(true);
                    }
                }
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " " + ex.getCause().getMessage() : "";
            String host = connectionInfo == null ? "Unknown" : connectionInfo.getHost();
            Integer port = connectionInfo == null ? new Integer(0) : connectionInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void saveConfiguration(AlertFilterInfo alertFilter) {
        try {
            configuration.setAlertFilter(alertFilter);
            Configurator.saveConfiguration(configuration);
            notificationProcessor.getAlertsTableModel().setFilter(alertFilter);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    Messages.getString("MaintainConnectionView.ErrMsg.saveConfig.text"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        connConfigPopupMenu = new javax.swing.JPopupMenu();
        connConfigEditMenuItem = new javax.swing.JMenuItem();
        connConfigDeleteMenuItem = new javax.swing.JMenuItem();
        connConfigConnectMenuItem = new javax.swing.JMenuItem();
        connConfigDisconnectMenuItem = new javax.swing.JMenuItem();
        connSeparator1 = new javax.swing.JPopupMenu.Separator();
        connInfoMenuItem = new javax.swing.JMenuItem();
        connSeparator2 = new javax.swing.JPopupMenu.Separator();
        connShutdownMenuItem = new javax.swing.JMenuItem();
        connShutdownNowMenuItem = new javax.swing.JMenuItem();
        sessInfoPopupMenu = new javax.swing.JPopupMenu();
        sessInfoRefreshMenuItem = new javax.swing.JMenuItem();
        sessInfoConnMenuItem = new javax.swing.JMenuItem();
        sessInfoDisconnMenuItem = new javax.swing.JMenuItem();
        sessInfoResetSeqMenuItem = new javax.swing.JMenuItem();
        sessInfoResetSessMenuItem = new javax.swing.JMenuItem();
        sessInfoFreezeMenuItem = new javax.swing.JMenuItem();
        sessInfoThawMenuItem = new javax.swing.JMenuItem();
        sessInfoStopMenuItem = new javax.swing.JMenuItem();
        sessInfoStartMenuItem = new javax.swing.JMenuItem();
        sessSeparator = new javax.swing.JPopupMenu.Separator();
        sessInfoStatsMenuItem = new javax.swing.JMenuItem();
        alertPopupMenu = new javax.swing.JPopupMenu();
        viewErrorMenuItem = new javax.swing.JMenuItem();
        viewStackMenuItem = new javax.swing.JMenuItem();
        alertSeparator = new javax.swing.JPopupMenu.Separator();
        deleteAlertMenuItem = new javax.swing.JMenuItem();
        desktopPane = new javax.swing.JDesktopPane();
        connInternalFrame = new javax.swing.JInternalFrame();
        configConnPane = new javax.swing.JScrollPane();
        configConnTable = new javax.swing.JTable();
        sessInfoInternalFrame = new javax.swing.JInternalFrame();
        sessInfoScrollPane = new javax.swing.JScrollPane();
        sessionInfoTable = new javax.swing.JTable();
        eventsInternalFrame = new javax.swing.JInternalFrame();
        eventsToolBar = new javax.swing.JToolBar();
        clearEventsButton = new javax.swing.JButton();
        filterEventsButton = new javax.swing.JButton();
        eventsTabbedPane = new javax.swing.JTabbedPane();
        alertsPanel = new javax.swing.JPanel();
        alertsScrollPane = new javax.swing.JScrollPane();
        alertsTable = new javax.swing.JTable();
        sessStatsInternalFrame = new javax.swing.JInternalFrame();
        sessStatsScrollPane = new javax.swing.JScrollPane();
        sessStatsPanel = new javax.swing.JPanel();
        startTimeStatsPanel = new javax.swing.JPanel();
        startSessionTimeLabel = new javax.swing.JLabel();
        startSessionTimeAppLabel = new javax.swing.JLabel();
        statsSessionLabel = new javax.swing.JLabel();
        statsSepLabel = new javax.swing.JLabel();
        statsCptyLabel = new javax.swing.JLabel();
        transpStatsPanel = new javax.swing.JPanel();
        transportBytesInLabel = new javax.swing.JLabel();
        transportBytesInAppLabel = new javax.swing.JLabel();
        transportBytesOutLabel = new javax.swing.JLabel();
        transportBytesOutAppLabel = new javax.swing.JLabel();
        transportThroughputInLabel = new javax.swing.JLabel();
        transportThroughputInAppLabel = new javax.swing.JLabel();
        transportThroughputOutLabel = new javax.swing.JLabel();
        transportThroughputOutAppLabel = new javax.swing.JLabel();
        statsFillPanel = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6), new java.awt.Dimension(32767, 6));
        protoStatsPanel = new javax.swing.JPanel();
        protoMsgInLabel = new javax.swing.JLabel();
        protoMsgInAppLabel = new javax.swing.JLabel();
        protoMsgOutLabel = new javax.swing.JLabel();
        protoMsgOutAppLabel = new javax.swing.JLabel();
        protoThroughputInLabel = new javax.swing.JLabel();
        protoThroughputInAppLabel = new javax.swing.JLabel();
        protoThroughputOutLabel = new javax.swing.JLabel();
        protoThroughputOutAppLabel = new javax.swing.JLabel();
        protoRejectedLabel = new javax.swing.JLabel();
        protoRejectedAppLabel = new javax.swing.JLabel();
        consStreamStatsPanel = new javax.swing.JPanel();
        consStreamMsgInLabel = new javax.swing.JLabel();
        consStreamMsgInAppLabel = new javax.swing.JLabel();
        consStreamRejectedLabel = new javax.swing.JLabel();
        consStreamRejectedAppLabel = new javax.swing.JLabel();
        consStreamDiscardedLabel = new javax.swing.JLabel();
        consStreamDiscardedAppLabel = new javax.swing.JLabel();
        prodStreamStatsPanel = new javax.swing.JPanel();
        prodStreamMsgOutLabel = new javax.swing.JLabel();
        prodStreamMsgOutAppLabel = new javax.swing.JLabel();
        prodStreamRejectedLabel = new javax.swing.JLabel();
        prodStreamRejectedAppLabel = new javax.swing.JLabel();
        prodStreamDiscardedLabel = new javax.swing.JLabel();
        prodStreamDiscardedAppLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        connectionMenu = new javax.swing.JMenu();
        createConnMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        windowsMenu = new javax.swing.JMenu();
        connWindowMenuItem = new javax.swing.JMenuItem();
        sessInfoMenuItem = new javax.swing.JMenuItem();
        sessStatsMenuItem = new javax.swing.JMenuItem();
        alertsMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        connConfigPopupMenu.setBackground(new java.awt.Color(204, 204, 255));

        connConfigEditMenuItem.setBackground(new java.awt.Color(255, 255, 153));
        connConfigEditMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/connection_edit.png"))); // NOI18N
        connConfigEditMenuItem.setText("Edit");
        connConfigEditMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connConfigEditMenuItemActionPerformed(evt);
            }
        });
        connConfigPopupMenu.add(connConfigEditMenuItem);

        connConfigDeleteMenuItem.setBackground(new java.awt.Color(255, 255, 153));
        connConfigDeleteMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/connection_delete.png"))); // NOI18N
        connConfigDeleteMenuItem.setText("Delete");
        connConfigDeleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connConfigDeleteMenuItemActionPerformed(evt);
            }
        });
        connConfigPopupMenu.add(connConfigDeleteMenuItem);

        connConfigConnectMenuItem.setBackground(new java.awt.Color(255, 255, 153));
        connConfigConnectMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/connect.png"))); // NOI18N
        connConfigConnectMenuItem.setText("Connect");
        connConfigConnectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connConfigConnectMenuItemActionPerformed(evt);
            }
        });
        connConfigPopupMenu.add(connConfigConnectMenuItem);

        connConfigDisconnectMenuItem.setBackground(new java.awt.Color(255, 255, 153));
        connConfigDisconnectMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/disconnect.png"))); // NOI18N
        connConfigDisconnectMenuItem.setText("Disconnect");
        connConfigDisconnectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connConfigDisconnectMenuItemActionPerformed(evt);
            }
        });
        connConfigPopupMenu.add(connConfigDisconnectMenuItem);
        connConfigPopupMenu.add(connSeparator1);

        connInfoMenuItem.setBackground(new java.awt.Color(255, 255, 153));
        connInfoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/session-info.png"))); // NOI18N
        connInfoMenuItem.setText("Session Info");
        connInfoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connInfoMenuItemActionPerformed(evt);
            }
        });
        connConfigPopupMenu.add(connInfoMenuItem);

        connSeparator2.setBackground(new java.awt.Color(255, 255, 153));
        connConfigPopupMenu.add(connSeparator2);

        connShutdownMenuItem.setBackground(new java.awt.Color(255, 255, 153));
        connShutdownMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/shutdown.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("net/hades/fix/admin/gui/resources/HadesAdminConsole"); // NOI18N
        connShutdownMenuItem.setText(bundle.getString("HadesAdminConsole.ConfigConnPopupMenu.shutdown.text")); // NOI18N
        connShutdownMenuItem.setActionCommand(bundle.getString("HadesAdminConsole.ConfigConnPopupMenu.shutdown.text")); // NOI18N
        connShutdownMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connShutdownMenuItemActionPerformed(evt);
            }
        });
        connConfigPopupMenu.add(connShutdownMenuItem);

        connShutdownNowMenuItem.setBackground(new java.awt.Color(255, 255, 153));
        connShutdownNowMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/shutdownNow.png"))); // NOI18N
        connShutdownNowMenuItem.setText(bundle.getString("HadesAdminConsole.ConfigConnPopupMenu.shutdownNow.text")); // NOI18N
        connShutdownNowMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connShutdownNowMenuItemActionPerformed(evt);
            }
        });
        connConfigPopupMenu.add(connShutdownNowMenuItem);

        sessInfoPopupMenu.setBackground(new java.awt.Color(102, 255, 204));

        sessInfoRefreshMenuItem.setBackground(new java.awt.Color(0, 255, 204));
        sessInfoRefreshMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/refresh.png"))); // NOI18N
        sessInfoRefreshMenuItem.setText(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.refresh.text")); // NOI18N
        sessInfoRefreshMenuItem.setActionCommand(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.refresh.text")); // NOI18N
        sessInfoRefreshMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessInfoRefreshMenuItemActionPerformed(evt);
            }
        });
        sessInfoPopupMenu.add(sessInfoRefreshMenuItem);

        sessInfoConnMenuItem.setBackground(new java.awt.Color(0, 255, 204));
        sessInfoConnMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/transport_connect.png"))); // NOI18N
        sessInfoConnMenuItem.setText(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.connect.text")); // NOI18N
        sessInfoConnMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessInfoConnMenuItemActionPerformed(evt);
            }
        });
        sessInfoPopupMenu.add(sessInfoConnMenuItem);

        sessInfoDisconnMenuItem.setBackground(new java.awt.Color(0, 255, 204));
        sessInfoDisconnMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/transport_disconnect.png"))); // NOI18N
        sessInfoDisconnMenuItem.setText(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.disconnect.text")); // NOI18N
        sessInfoDisconnMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessInfoDisconnMenuItemActionPerformed(evt);
            }
        });
        sessInfoPopupMenu.add(sessInfoDisconnMenuItem);

        sessInfoResetSeqMenuItem.setBackground(new java.awt.Color(0, 255, 204));
        sessInfoResetSeqMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/resetSeq.png"))); // NOI18N
        sessInfoResetSeqMenuItem.setText(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.resetSeq.text")); // NOI18N
        sessInfoResetSeqMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessInfoResetSeqMenuItemActionPerformed(evt);
            }
        });
        sessInfoPopupMenu.add(sessInfoResetSeqMenuItem);

        sessInfoResetSessMenuItem.setBackground(new java.awt.Color(0, 255, 204));
        sessInfoResetSessMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/resetSession.png"))); // NOI18N
        sessInfoResetSessMenuItem.setText(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.resetSession.text")); // NOI18N
        sessInfoResetSessMenuItem.setActionCommand(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.resetSession.text")); // NOI18N
        sessInfoResetSessMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessInfoResetSessMenuItemActionPerformed(evt);
            }
        });
        sessInfoPopupMenu.add(sessInfoResetSessMenuItem);

        sessInfoFreezeMenuItem.setBackground(new java.awt.Color(0, 255, 204));
        sessInfoFreezeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/freeze.png"))); // NOI18N
        sessInfoFreezeMenuItem.setText(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.freeze.text")); // NOI18N
        sessInfoFreezeMenuItem.setActionCommand(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.freeze.text")); // NOI18N
        sessInfoFreezeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessInfoFreezeMenuItemActionPerformed(evt);
            }
        });
        sessInfoPopupMenu.add(sessInfoFreezeMenuItem);

        sessInfoThawMenuItem.setBackground(new java.awt.Color(0, 255, 204));
        sessInfoThawMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/thaw.png"))); // NOI18N
        sessInfoThawMenuItem.setText(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.thaw.text")); // NOI18N
        sessInfoThawMenuItem.setActionCommand(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.thaw.text")); // NOI18N
        sessInfoThawMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessInfoThawMenuItemActionPerformed(evt);
            }
        });
        sessInfoPopupMenu.add(sessInfoThawMenuItem);

        sessInfoStopMenuItem.setBackground(new java.awt.Color(0, 255, 204));
        sessInfoStopMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/stop.png"))); // NOI18N
        sessInfoStopMenuItem.setText(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.stop.text")); // NOI18N
        sessInfoStopMenuItem.setActionCommand(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.stop.text")); // NOI18N
        sessInfoStopMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessInfoStopMenuItemActionPerformed(evt);
            }
        });
        sessInfoPopupMenu.add(sessInfoStopMenuItem);

        sessInfoStartMenuItem.setBackground(new java.awt.Color(0, 255, 204));
        sessInfoStartMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/start.png"))); // NOI18N
        sessInfoStartMenuItem.setText(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.start.text")); // NOI18N
        sessInfoStartMenuItem.setActionCommand(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.start.text")); // NOI18N
        sessInfoStartMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessInfoStartMenuItemActionPerformed(evt);
            }
        });
        sessInfoPopupMenu.add(sessInfoStartMenuItem);
        sessInfoPopupMenu.add(sessSeparator);

        sessInfoStatsMenuItem.setBackground(new java.awt.Color(0, 255, 204));
        sessInfoStatsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/session-stats.png"))); // NOI18N
        sessInfoStatsMenuItem.setText(bundle.getString("HadesAdminConsole.SessionInfoPopupMenu.stats.text")); // NOI18N
        sessInfoStatsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessInfoStatsMenuItemActionPerformed(evt);
            }
        });
        sessInfoPopupMenu.add(sessInfoStatsMenuItem);

        viewErrorMenuItem.setText(bundle.getString("HadesAdminConsole.AlertPopupMenu.error.text")); // NOI18N
        viewErrorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewErrorMenuItemActionPerformed(evt);
            }
        });
        alertPopupMenu.add(viewErrorMenuItem);

        viewStackMenuItem.setText(bundle.getString("HadesAdminConsole.AlertPopupMenu.stack.text")); // NOI18N
        viewStackMenuItem.setActionCommand(bundle.getString("HadesAdminConsole.AlertPopupMenu.stack.text")); // NOI18N
        viewStackMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewStackMenuItemActionPerformed(evt);
            }
        });
        alertPopupMenu.add(viewStackMenuItem);
        alertPopupMenu.add(alertSeparator);

        deleteAlertMenuItem.setText(bundle.getString("HadesAdminConsole.AlertPopupMenu.delete.text")); // NOI18N
        deleteAlertMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAlertMenuItemActionPerformed(evt);
            }
        });
        alertPopupMenu.add(deleteAlertMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HadesFIX Management Console");
        setIconImage(frameIcon);

        desktopPane.setBackground(new java.awt.Color(153, 153, 255));

        connInternalFrame.setBackground(new java.awt.Color(255, 255, 204));
        connInternalFrame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        connInternalFrame.setClosable(true);
        connInternalFrame.setIconifiable(true);
        connInternalFrame.setMaximizable(true);
        connInternalFrame.setResizable(true);
        connInternalFrame.setTitle(bundle.getString("ConnInternalFrame.title.text")); // NOI18N
        connInternalFrame.setAutoscrolls(true);
        connInternalFrame.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/connections.png"))); // NOI18N
        connInternalFrame.setOpaque(true);
        connInternalFrame.setVisible(true);
        connInternalFrame.getContentPane().setLayout(new java.awt.GridBagLayout());

        configConnPane.setBackground(new java.awt.Color(255, 255, 204));
        configConnPane.setAutoscrolls(true);

        configConnTable.setAutoCreateRowSorter(true);
        configConnTable.setBackground(new java.awt.Color(255, 255, 204));
        configConnTable.setModel(connectionTableModel);
        configConnTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        configConnTable.setSelectionBackground(new java.awt.Color(153, 153, 0));
        configConnTable.setShowHorizontalLines(true);
        configConnTable.setGridColor(new Color(170, 170, 170));
        configConnTable.setShowVerticalLines(false);
        configConnTable.setRowHeight(24);
        configConnTable.getColumnModel().removeColumn(configConnTable.getColumnModel().getColumn(configConnTable.getColumnModel().getColumnCount() - 1));
        configConnTable.setDefaultRenderer(Object.class, new ConnTableCellRenderer());

        configConnTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                configConnTableMouseReleased(evt);
            }
        });
        configConnPane.setViewportView(configConnTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        connInternalFrame.getContentPane().add(configConnPane, gridBagConstraints);

        connInternalFrame.setBounds(0, 0, 1250, 160);
        desktopPane.add(connInternalFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);

        sessInfoInternalFrame.setBackground(new java.awt.Color(204, 255, 204));
        sessInfoInternalFrame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sessInfoInternalFrame.setClosable(true);
        sessInfoInternalFrame.setIconifiable(true);
        sessInfoInternalFrame.setMaximizable(true);
        sessInfoInternalFrame.setResizable(true);
        sessInfoInternalFrame.setTitle(bundle.getString("SessionInfoInternalFrame.title.text")); // NOI18N
        sessInfoInternalFrame.setAutoscrolls(true);
        sessInfoInternalFrame.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/session-info.png"))); // NOI18N
        sessInfoInternalFrame.setOpaque(true);
        sessInfoInternalFrame.setVisible(true);
        sessInfoInternalFrame.getContentPane().setLayout(new java.awt.GridBagLayout());

        sessInfoScrollPane.setBackground(new java.awt.Color(204, 255, 204));
        sessInfoScrollPane.setMinimumSize(new java.awt.Dimension(100, 100));

        sessionInfoTable.setAutoCreateRowSorter(true);
        sessionInfoTable.setModel(sessionTableModel);
        sessionInfoTable.setSelectionBackground(new java.awt.Color(0, 153, 153));
        sessionInfoTable.setShowHorizontalLines(false);
        sessionInfoTable.setShowVerticalLines(false);
        sessionInfoTable.setRowHeight(24);
        sessionInfoTable.setDefaultRenderer(Object.class, new SessionTableCellRenderer());
        sessionInfoTable.getColumnModel().getColumn(4).setCellEditor(new RxSeqTableCellEditor());
        sessionInfoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sessionInfoTableMouseReleased(evt);
            }
        });
        sessInfoScrollPane.setViewportView(sessionInfoTable);
        sessionInfoTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        sessInfoInternalFrame.getContentPane().add(sessInfoScrollPane, gridBagConstraints);

        sessInfoInternalFrame.setBounds(0, 160, 1250, 140);
        desktopPane.add(sessInfoInternalFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);

        eventsInternalFrame.setBackground(new java.awt.Color(255, 204, 255));
        eventsInternalFrame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        eventsInternalFrame.setClosable(true);
        eventsInternalFrame.setIconifiable(true);
        eventsInternalFrame.setMaximizable(true);
        eventsInternalFrame.setResizable(true);
        eventsInternalFrame.setTitle(bundle.getString("AlertsInternalFrame.title.text")); // NOI18N
        eventsInternalFrame.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/alert.png"))); // NOI18N
        eventsInternalFrame.setOpaque(true);
        eventsInternalFrame.setVisible(true);
        eventsInternalFrame.getContentPane().setLayout(new java.awt.GridBagLayout());

        eventsToolBar.setRollover(true);
        eventsToolBar.setMargin(new java.awt.Insets(2, 2, 2, 2));

        clearEventsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/event-clear.png"))); // NOI18N
        clearEventsButton.setToolTipText(bundle.getString("HadesAdminConsole.EventToolbar.clearAll.text")); // NOI18N
        clearEventsButton.setFocusable(false);
        clearEventsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        clearEventsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        clearEventsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearEventsButtonActionPerformed(evt);
            }
        });
        eventsToolBar.add(clearEventsButton);

        filterEventsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/event-filter.png"))); // NOI18N
        filterEventsButton.setToolTipText(bundle.getString("HadesAdminConsole.EventToolbar.filter.text")); // NOI18N
        filterEventsButton.setFocusable(false);
        filterEventsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        filterEventsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        filterEventsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterEventsButtonActionPerformed(evt);
            }
        });
        eventsToolBar.add(filterEventsButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        eventsInternalFrame.getContentPane().add(eventsToolBar, gridBagConstraints);

        eventsTabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        eventsTabbedPane.setAutoscrolls(true);
        eventsTabbedPane.setOpaque(true);

        alertsPanel.setLayout(new java.awt.GridBagLayout());

        alertsTable.setAutoCreateRowSorter(true);
        alertsTable.setModel(notificationProcessor.getAlertsTableModel());
        alertsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        alertsTable.setGridColor(new java.awt.Color(255, 153, 255));
        alertsTable.setRowHeight(18);
        alertsTable.setSelectionBackground(new java.awt.Color(255, 102, 255));
        alertsTable.getColumnModel().removeColumn(alertsTable.getColumnModel().getColumn(alertsTable.getColumnModel().getColumnCount() - 1));
        alertsTable.getColumnModel().removeColumn(alertsTable.getColumnModel().getColumn(alertsTable.getColumnModel().getColumnCount() - 1));
        alertsTable.getColumnModel().removeColumn(alertsTable.getColumnModel().getColumn(alertsTable.getColumnModel().getColumnCount() - 1));
        alertsTable.setDefaultRenderer(Object.class, new AlertTableCellRenderer());
        alertsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                alertsTableMouseReleased(evt);
            }
        });
        alertsScrollPane.setViewportView(alertsTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 689;
        gridBagConstraints.ipady = 369;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        alertsPanel.add(alertsScrollPane, gridBagConstraints);

        eventsTabbedPane.addTab(bundle.getString("HadesAdminConsole.events.tabAlert"), alertsPanel); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        eventsInternalFrame.getContentPane().add(eventsTabbedPane, gridBagConstraints);

        eventsInternalFrame.setBounds(0, 300, 1250, 510);
        desktopPane.add(eventsInternalFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);

        sessStatsInternalFrame.setBackground(new java.awt.Color(204, 204, 255));
        sessStatsInternalFrame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sessStatsInternalFrame.setClosable(true);
        sessStatsInternalFrame.setIconifiable(true);
        sessStatsInternalFrame.setMaximizable(true);
        sessStatsInternalFrame.setResizable(true);
        sessStatsInternalFrame.setTitle(bundle.getString("SessionStatsInternalFrame.title.text")); // NOI18N
        sessStatsInternalFrame.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/session-stats.png"))); // NOI18N
        sessStatsInternalFrame.setOpaque(true);
        sessStatsInternalFrame.setVisible(true);
        sessStatsInternalFrame.getContentPane().setLayout(new java.awt.GridBagLayout());

        sessStatsPanel.setLayout(new java.awt.GridBagLayout());

        startTimeStatsPanel.setLayout(new java.awt.GridBagLayout());

        startSessionTimeLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.startTimeLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        startTimeStatsPanel.add(startSessionTimeLabel, gridBagConstraints);

        startSessionTimeAppLabel.setMaximumSize(new java.awt.Dimension(250, 15));
        startSessionTimeAppLabel.setMinimumSize(new java.awt.Dimension(150, 15));
        startSessionTimeAppLabel.setPreferredSize(new java.awt.Dimension(150, 15));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.sessionStartTime}"), startSessionTimeAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 5);
        startTimeStatsPanel.add(startSessionTimeAppLabel, gridBagConstraints);

        statsSessionLabel.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        statsSessionLabel.setMaximumSize(new java.awt.Dimension(150, 14));
        statsSessionLabel.setMinimumSize(new java.awt.Dimension(150, 14));
        statsSessionLabel.setPreferredSize(new java.awt.Dimension(150, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.session}"), statsSessionLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 3, 5, 5);
        startTimeStatsPanel.add(statsSessionLabel, gridBagConstraints);

        statsSepLabel.setFont(new java.awt.Font("Arial Black", 0, 11)); // NOI18N
        statsSepLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statsSepLabel.setText("-");
        statsSepLabel.setPreferredSize(new java.awt.Dimension(10, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 5, 0);
        startTimeStatsPanel.add(statsSepLabel, gridBagConstraints);

        statsCptyLabel.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        statsCptyLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.counterparty}"), statsCptyLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 3);
        startTimeStatsPanel.add(statsCptyLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        sessStatsPanel.add(startTimeStatsPanel, gridBagConstraints);

        transpStatsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("HadesAdminConsole.StatsPanel.transport.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 204))); // NOI18N
        transpStatsPanel.setLayout(new java.awt.GridBagLayout());

        transportBytesInLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.transportBytesIn.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        transpStatsPanel.add(transportBytesInLabel, gridBagConstraints);

        transportBytesInAppLabel.setMaximumSize(new java.awt.Dimension(200, 14));
        transportBytesInAppLabel.setMinimumSize(new java.awt.Dimension(100, 14));
        transportBytesInAppLabel.setPreferredSize(new java.awt.Dimension(100, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.transportBytesIn}"), transportBytesInAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 10);
        transpStatsPanel.add(transportBytesInAppLabel, gridBagConstraints);

        transportBytesOutLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.transportBytesOut.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
        transpStatsPanel.add(transportBytesOutLabel, gridBagConstraints);

        transportBytesOutAppLabel.setMaximumSize(new java.awt.Dimension(200, 14));
        transportBytesOutAppLabel.setMinimumSize(new java.awt.Dimension(100, 14));
        transportBytesOutAppLabel.setPreferredSize(new java.awt.Dimension(100, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.transportBytesOut}"), transportBytesOutAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 10);
        transpStatsPanel.add(transportBytesOutAppLabel, gridBagConstraints);

        transportThroughputInLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.transportThroughputIn.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        transpStatsPanel.add(transportThroughputInLabel, gridBagConstraints);

        transportThroughputInAppLabel.setMaximumSize(new java.awt.Dimension(150, 14));
        transportThroughputInAppLabel.setMinimumSize(new java.awt.Dimension(100, 14));
        transportThroughputInAppLabel.setPreferredSize(new java.awt.Dimension(100, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.transportThroughputIn}"), transportThroughputInAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        transpStatsPanel.add(transportThroughputInAppLabel, gridBagConstraints);

        transportThroughputOutLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.transportThroughputOut.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 5);
        transpStatsPanel.add(transportThroughputOutLabel, gridBagConstraints);

        transportThroughputOutAppLabel.setMaximumSize(new java.awt.Dimension(200, 14));
        transportThroughputOutAppLabel.setMinimumSize(new java.awt.Dimension(100, 14));
        transportThroughputOutAppLabel.setPreferredSize(new java.awt.Dimension(100, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.transportThroughputOut}"), transportThroughputOutAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        transpStatsPanel.add(transportThroughputOutAppLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        sessStatsPanel.add(transpStatsPanel, gridBagConstraints);

        statsFillPanel.setLayout(new java.awt.GridBagLayout());
        statsFillPanel.add(filler1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        sessStatsPanel.add(statsFillPanel, gridBagConstraints);

        protoStatsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("HadesAdminConsole.StatsPanel.protocol.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 204))); // NOI18N
        protoStatsPanel.setLayout(new java.awt.GridBagLayout());

        protoMsgInLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.protoTotMsgIn.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        protoStatsPanel.add(protoMsgInLabel, gridBagConstraints);

        protoMsgInAppLabel.setMaximumSize(new java.awt.Dimension(150, 14));
        protoMsgInAppLabel.setMinimumSize(new java.awt.Dimension(100, 14));
        protoMsgInAppLabel.setPreferredSize(new java.awt.Dimension(100, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.protocolTotMsgInCount}"), protoMsgInAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        protoStatsPanel.add(protoMsgInAppLabel, gridBagConstraints);

        protoMsgOutLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.protoTotMsgOut.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        protoStatsPanel.add(protoMsgOutLabel, gridBagConstraints);

        protoMsgOutAppLabel.setMaximumSize(new java.awt.Dimension(150, 14));
        protoMsgOutAppLabel.setMinimumSize(new java.awt.Dimension(100, 14));
        protoMsgOutAppLabel.setPreferredSize(new java.awt.Dimension(100, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.protocolTotMsgOutCount}"), protoMsgOutAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        protoStatsPanel.add(protoMsgOutAppLabel, gridBagConstraints);

        protoThroughputInLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.protoThroughputIn.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        protoStatsPanel.add(protoThroughputInLabel, gridBagConstraints);

        protoThroughputInAppLabel.setMaximumSize(new java.awt.Dimension(150, 14));
        protoThroughputInAppLabel.setMinimumSize(new java.awt.Dimension(100, 14));
        protoThroughputInAppLabel.setPreferredSize(new java.awt.Dimension(100, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.protocolThroughputIn}"), protoThroughputInAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        protoStatsPanel.add(protoThroughputInAppLabel, gridBagConstraints);

        protoThroughputOutLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.protoThroughputOut.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        protoStatsPanel.add(protoThroughputOutLabel, gridBagConstraints);

        protoThroughputOutAppLabel.setMaximumSize(new java.awt.Dimension(150, 14));
        protoThroughputOutAppLabel.setMinimumSize(new java.awt.Dimension(100, 14));
        protoThroughputOutAppLabel.setPreferredSize(new java.awt.Dimension(100, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.protocolThroughputOut}"), protoThroughputOutAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        protoStatsPanel.add(protoThroughputOutAppLabel, gridBagConstraints);

        protoRejectedLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.protoRejectCount.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        protoStatsPanel.add(protoRejectedLabel, gridBagConstraints);

        protoRejectedAppLabel.setMaximumSize(new java.awt.Dimension(100, 14));
        protoRejectedAppLabel.setMinimumSize(new java.awt.Dimension(80, 14));
        protoRejectedAppLabel.setPreferredSize(new java.awt.Dimension(80, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.protocolRejMsgCount}"), protoRejectedAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        protoStatsPanel.add(protoRejectedAppLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        sessStatsPanel.add(protoStatsPanel, gridBagConstraints);

        consStreamStatsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("HadesAdminConsole.StatsPanel.consStream.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 204))); // NOI18N
        consStreamStatsPanel.setLayout(new java.awt.GridBagLayout());

        consStreamMsgInLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.streamMsgIn.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        consStreamStatsPanel.add(consStreamMsgInLabel, gridBagConstraints);

        consStreamMsgInAppLabel.setMaximumSize(new java.awt.Dimension(150, 14));
        consStreamMsgInAppLabel.setMinimumSize(new java.awt.Dimension(100, 14));
        consStreamMsgInAppLabel.setPreferredSize(new java.awt.Dimension(100, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.consStreamMsgInCount}"), consStreamMsgInAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        consStreamStatsPanel.add(consStreamMsgInAppLabel, gridBagConstraints);

        consStreamRejectedLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.streamRejectCount.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        consStreamStatsPanel.add(consStreamRejectedLabel, gridBagConstraints);

        consStreamRejectedAppLabel.setMaximumSize(new java.awt.Dimension(150, 14));
        consStreamRejectedAppLabel.setMinimumSize(new java.awt.Dimension(100, 14));
        consStreamRejectedAppLabel.setPreferredSize(new java.awt.Dimension(100, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.consStreamMsgRejectCount}"), consStreamRejectedAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        consStreamStatsPanel.add(consStreamRejectedAppLabel, gridBagConstraints);

        consStreamDiscardedLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.streamDiscardCount.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        consStreamStatsPanel.add(consStreamDiscardedLabel, gridBagConstraints);

        consStreamDiscardedAppLabel.setMaximumSize(new java.awt.Dimension(150, 14));
        consStreamDiscardedAppLabel.setMinimumSize(new java.awt.Dimension(100, 14));
        consStreamDiscardedAppLabel.setPreferredSize(new java.awt.Dimension(100, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.consStreamMsgDiscardCount}"), consStreamDiscardedAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        consStreamStatsPanel.add(consStreamDiscardedAppLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        sessStatsPanel.add(consStreamStatsPanel, gridBagConstraints);

        prodStreamStatsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, bundle.getString("HadesAdminConsole.StatsPanel.prodStream.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 204))); // NOI18N
        prodStreamStatsPanel.setLayout(new java.awt.GridBagLayout());

        prodStreamMsgOutLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.streamMsgOut.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
        prodStreamStatsPanel.add(prodStreamMsgOutLabel, gridBagConstraints);

        prodStreamMsgOutAppLabel.setMaximumSize(new java.awt.Dimension(150, 14));
        prodStreamMsgOutAppLabel.setMinimumSize(new java.awt.Dimension(100, 14));
        prodStreamMsgOutAppLabel.setPreferredSize(new java.awt.Dimension(100, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.prodStreamMsgOutCount}"), prodStreamMsgOutAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        prodStreamStatsPanel.add(prodStreamMsgOutAppLabel, new java.awt.GridBagConstraints());

        prodStreamRejectedLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.streamRejectCount.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        prodStreamStatsPanel.add(prodStreamRejectedLabel, gridBagConstraints);

        prodStreamRejectedAppLabel.setMaximumSize(new java.awt.Dimension(150, 14));
        prodStreamRejectedAppLabel.setMinimumSize(new java.awt.Dimension(100, 14));
        prodStreamRejectedAppLabel.setPreferredSize(new java.awt.Dimension(100, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.prodStreamMsgRejectCount}"), prodStreamRejectedAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        prodStreamStatsPanel.add(prodStreamRejectedAppLabel, gridBagConstraints);

        prodStreamDiscardedLabel.setText(bundle.getString("HadesAdminConsole.StatsPanel.streamDiscardCount.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        prodStreamStatsPanel.add(prodStreamDiscardedLabel, gridBagConstraints);

        prodStreamDiscardedAppLabel.setMaximumSize(new java.awt.Dimension(150, 14));
        prodStreamDiscardedAppLabel.setMinimumSize(new java.awt.Dimension(100, 14));
        prodStreamDiscardedAppLabel.setPreferredSize(new java.awt.Dimension(100, 14));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${sessionStatistics.prodStreamMsgDiscardCount}"), prodStreamDiscardedAppLabel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 5);
        prodStreamStatsPanel.add(prodStreamDiscardedAppLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        sessStatsPanel.add(prodStreamStatsPanel, gridBagConstraints);

        sessStatsScrollPane.setViewportView(sessStatsPanel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        sessStatsInternalFrame.getContentPane().add(sessStatsScrollPane, gridBagConstraints);

        sessStatsInternalFrame.setBounds(800, 290, 450, 520);
        desktopPane.add(sessStatsInternalFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        try {
            sessStatsInternalFrame.setIcon(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        menuBar.setBackground(new java.awt.Color(153, 153, 255));

        connectionMenu.setMnemonic('c');
        connectionMenu.setText(bundle.getString("Application.menu.connection")); // NOI18N

        createConnMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        createConnMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/connection_add.png"))); // NOI18N
        createConnMenuItem.setMnemonic('o');
        createConnMenuItem.setText(bundle.getString("Application.menu.connection.create")); // NOI18N
        createConnMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createConnMenuItemActionPerformed(evt);
            }
        });
        connectionMenu.add(createConnMenuItem);
        connectionMenu.add(jSeparator1);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/exit.png"))); // NOI18N
        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText(bundle.getString("Application.menu.connection.exit")); // NOI18N
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        connectionMenu.add(exitMenuItem);

        menuBar.add(connectionMenu);

        windowsMenu.setText(bundle.getString("Application.menu.window")); // NOI18N

        connWindowMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/connections.png"))); // NOI18N
        connWindowMenuItem.setText(bundle.getString("Application.menu.window.conn")); // NOI18N
        connWindowMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connWindowMenuItemActionPerformed(evt);
            }
        });
        windowsMenu.add(connWindowMenuItem);

        sessInfoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/session-info.png"))); // NOI18N
        sessInfoMenuItem.setText(bundle.getString("Application.menu.window.sessInfo")); // NOI18N
        sessInfoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessInfoMenuItemActionPerformed(evt);
            }
        });
        windowsMenu.add(sessInfoMenuItem);

        sessStatsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/session-stats.png"))); // NOI18N
        sessStatsMenuItem.setText(bundle.getString("Application.menu.window.sessStats")); // NOI18N
        sessStatsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sessStatsMenuItemActionPerformed(evt);
            }
        });
        windowsMenu.add(sessStatsMenuItem);

        alertsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/hades/fix/admin/gui/resources/icons/alert.png"))); // NOI18N
        alertsMenuItem.setText(bundle.getString("Application.menu.window.alerts")); // NOI18N
        alertsMenuItem.setActionCommand("alertsMenuItem");
        alertsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alertsMenuItemActionPerformed(evt);
            }
        });
        windowsMenu.add(alertsMenuItem);

        menuBar.add(windowsMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText(bundle.getString("Application.menu.help")); // NOI18N

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText(bundle.getString("Application.menu.help.about")); // NOI18N
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1253, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        AboutView about = new AboutView(this, true);
        about.setLocationRelativeTo(this);
        about.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void createConnMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createConnMenuItemActionPerformed
        MaintainConnectionView createConnection = new MaintainConnectionView(this);
        createConnection.setLocationRelativeTo(this);
        createConnection.setVisible(true);
        connectionTableModel.fireTableDataChanged();
    }//GEN-LAST:event_createConnMenuItemActionPerformed

    private void configConnTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configConnTableMouseReleased
        if (SwingUtilities.isRightMouseButton(evt)) {
            int r = configConnTable.rowAtPoint(evt.getPoint());
            if (r >= 0 && r < configConnTable.getRowCount()) {
                configConnTable.setRowSelectionInterval(r, r);
            } else {
                configConnTable.clearSelection();
            }

            int rowindex = configConnTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            if (evt.getComponent() instanceof JTable) {
                connConfigPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
            Boolean connected = Boolean.valueOf((String) configConnTable.getValueAt(rowindex, 5));
            if (connected) {
                connConfigEditMenuItem.setEnabled(false);
                connConfigConnectMenuItem.setEnabled(false);
                connConfigDisconnectMenuItem.setEnabled(true);
                connConfigDeleteMenuItem.setEnabled(false);
                connInfoMenuItem.setEnabled(true);
                connShutdownMenuItem.setEnabled(true);
                connShutdownNowMenuItem.setEnabled(true);
            } else {
                connConfigEditMenuItem.setEnabled(true);
                connConfigConnectMenuItem.setEnabled(true);
                connConfigDisconnectMenuItem.setEnabled(false);
                connConfigDeleteMenuItem.setEnabled(true);
                connInfoMenuItem.setEnabled(false);
                connShutdownMenuItem.setEnabled(false);
                connShutdownNowMenuItem.setEnabled(false);
            }
        }
    }//GEN-LAST:event_configConnTableMouseReleased

    private void connConfigEditMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connConfigEditMenuItemActionPerformed
        int rowindex = configConnTable.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        Integer id = (Integer) configConnTable.getValueAt(rowindex, 0);
        EngineConnectionInfo connInfo = configuration.getEngineConnectionInfo(id);
        if (connInfo != null) {
            MaintainConnectionView createConnection = new MaintainConnectionView(this, connInfo);
            createConnection.setLocationRelativeTo(this);
            createConnection.setVisible(true);
        }  
    }//GEN-LAST:event_connConfigEditMenuItemActionPerformed

    private void connWindowMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connWindowMenuItemActionPerformed
        try {
            if (connInternalFrame.isVisible()) {
                if (connInternalFrame.isIcon()) {
                    connInternalFrame.setIcon(false);
                }
                return;
            }
            connInternalFrame.setVisible(true);
            desktopPane.add(connInternalFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);

            if (connInternalFrame.isIcon()) {
                connInternalFrame.setIcon(false);
            }
            connInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            LOGGER.log(Level.SEVERE, "Error setting the Connections internal frame selectd. Error was: {0}", ex);
        }
    }//GEN-LAST:event_connWindowMenuItemActionPerformed

    private void connConfigDeleteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connConfigDeleteMenuItemActionPerformed
        int result = JOptionPane.showConfirmDialog(this,
                Messages.getString("HadesAdminConsole.deleteConnection.confirm.text"),
                Messages.getString("HadesAdminConsole.deleteConnection.confirm.title"),
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.NO_OPTION) {
            return;
        }
        int rowindex = configConnTable.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        Integer id = (Integer) configConnTable.getValueAt(rowindex, 0);
        configuration.removeEngineConnectionInfo(id);
        try {
            Configurator.saveConfiguration(configuration);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    Messages.getString("MaintainConnectionView.ErrMsg.saveConfig.text"),
                    JOptionPane.ERROR_MESSAGE);
        }
        connectionTableModel.setConnections(configuration.getEngineConnections());
    }//GEN-LAST:event_connConfigDeleteMenuItemActionPerformed

    private void connConfigConnectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connConfigConnectMenuItemActionPerformed
        EngineConnectionInfo connInfo = null;
        try {
            int rowindex = configConnTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            Integer id = (Integer) configConnTable.getValueAt(rowindex, 0);
            connInfo = configuration.getEngineConnectionInfo(id);
            EngineSessionWorker worker = new EngineSessionWorker(connInfo, this);
            Thread t = new Thread(worker, String.format("SESSION_%s_%s:%s%n", new Object[]{connInfo.getId(), connInfo.getHost(), connInfo.getPort()}));
            t.start();
            ThreadUtil.blockUntilAlive(t);
            worker.connect();
            activeConnections.putIfAbsent(new Integer(connInfo.getId()), worker);
            connInfo.setConnected(true);
            connInfo.setEngineName(worker.getInstanceName());
            connInfo.setEngineDescr(worker.getInstanceDescr());
            connectionTableModel.updateEngineSession(connInfo);
            try {
                Configurator.saveConfiguration(configuration);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        Messages.getString("MaintainConnectionView.ErrMsg.saveConfig.text"),
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " " + ex.getCause().getMessage() : "";
            String host = connInfo == null ? "Unknown" : connInfo.getHost();
            Integer port = connInfo == null ? new Integer(0) : connInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_connConfigConnectMenuItemActionPerformed

    private void connConfigDisconnectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connConfigDisconnectMenuItemActionPerformed
        EngineConnectionInfo connInfo = null;
        try {
            int rowindex = configConnTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            Integer id = (Integer) configConnTable.getValueAt(rowindex, 0);
            connInfo = configuration.getEngineConnectionInfo(id);
            EngineSessionWorker worker = activeConnections.get(id);
            if (worker != null) {
                worker.executeCommand(EngineSessionWorker.CMD_EXIT);
                connInfo.setConnected(false);
                activeConnections.remove(id);
                connectionTableModel.updateEngineSession(connInfo);
                if (sessionTableModel.getConnectionId() != null && sessionTableModel.getConnectionId().equals(connInfo.getId())) {
                    sessionTableModel.setSessions(null);
                }
                if (sessionStatistics.getConnectionId() != null && sessionStatistics.getConnectionId().equals(connInfo.getId())) {
                    MultiTableResult stats = new MultiTableResult(Boolean.TRUE);
                    stats.addTableResult(SessionStatsResultParam.SessionStats.toString(), new TableResult(Boolean.TRUE));
                    stats.addTableResult(SessionStatsResultParam.TransportStats.toString(), new TableResult(Boolean.TRUE));
                    stats.addTableResult(SessionStatsResultParam.ProtocolStats.toString(), new TableResult(Boolean.TRUE));
                    stats.addTableResult(SessionStatsResultParam.ConsumerStreamStats.toString(), new TableResult(Boolean.TRUE));
                    stats.addTableResult(SessionStatsResultParam.ProducerStreamStats.toString(), new TableResult(Boolean.TRUE));
                    sessionStatistics.updateSessionStatistics(stats);
                    if (sessStatsInternalFrame.isVisible()) {
                        if (!sessStatsInternalFrame.isIcon()) {
                            sessStatsInternalFrame.setIcon(true);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        Messages.getString("HadesAdminConsole.ErrMsg.connectSession.text", connInfo.getId()),
                        Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " - Cause: " + ex.getCause().getMessage() : "";
            String host = connInfo == null ? "Unknown" : connInfo.getHost();
            Integer port = connInfo == null ? new Integer(0) : connInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_connConfigDisconnectMenuItemActionPerformed

    private void sessInfoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessInfoMenuItemActionPerformed
        try {
            if (sessInfoInternalFrame.isVisible()) {
                if (sessInfoInternalFrame.isIcon()) {
                    sessInfoInternalFrame.setIcon(false);
                }
                return;
            }

            sessInfoInternalFrame.setVisible(true);
            desktopPane.add(sessInfoInternalFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
            if (sessInfoInternalFrame.isIcon()) {
                sessInfoInternalFrame.setIcon(false);
            }
            sessInfoInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            LOGGER.log(Level.SEVERE, "Error setting the Session Info internal frame selectd. Error was: {0}", ex);
        }
    }//GEN-LAST:event_sessInfoMenuItemActionPerformed

    private void connInfoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connInfoMenuItemActionPerformed
        EngineConnectionInfo connInfo = null;
        try {
            int rowindex = configConnTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            Integer id = (Integer) configConnTable.getValueAt(rowindex, 0);
            connInfo = configuration.getEngineConnectionInfo(id);
            EngineSessionWorker worker = activeConnections.get(id);
            if (worker != null) {
                TableResult result = worker.getConfigSessions();
                if (!result.getOutcome()) {
                    JOptionPane.showMessageDialog(this,
                            Messages.getString("HadesAdminConsole.ErrMsg.executeCommand.text", "ListSessions", result.getErrMsg()),
                            Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    if (!sessInfoInternalFrame.isVisible()) {
                        sessInfoInternalFrame.setVisible(true);
                        desktopPane.add(sessInfoInternalFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
                        try {
                            sessInfoInternalFrame.setSelected(true);
                        } catch (PropertyVetoException ex) {
                            LOGGER.log(Level.SEVERE, "Error setting the Session Info internal frame selectd. Error was: {0}", ex);
                        }
                    }
                    sessionTableModel.setSessions(result);
                    sessionTableModel.setDisplayColor(connInfo.getDisplayColor());
                    sessionTableModel.setConnectionId(id);
                    if (sessStatsInternalFrame.isVisible()) {
                        if (sessionStatistics.getConnectionId() != null && !sessionStatistics.getConnectionId().equals(connInfo.getId())) {
                            MultiTableResult stats = new MultiTableResult(Boolean.TRUE);
                            stats.addTableResult(SessionStatsResultParam.SessionStats.toString(), new TableResult(Boolean.TRUE));
                            stats.addTableResult(SessionStatsResultParam.TransportStats.toString(), new TableResult(Boolean.TRUE));
                            stats.addTableResult(SessionStatsResultParam.ProtocolStats.toString(), new TableResult(Boolean.TRUE));
                            stats.addTableResult(SessionStatsResultParam.ConsumerStreamStats.toString(), new TableResult(Boolean.TRUE));
                            stats.addTableResult(SessionStatsResultParam.ProducerStreamStats.toString(), new TableResult(Boolean.TRUE));
                            sessionStatistics.updateSessionStatistics(stats);
                            if (!sessStatsInternalFrame.isIcon()) {
                                sessStatsInternalFrame.setIcon(true);
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        Messages.getString("HadesAdminConsole.ErrMsg.connectSession.text", connInfo.getId()),
                        Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " - Cause: " + ex.getCause().getMessage() : "";
            String host = connInfo == null ? "Unknown" : connInfo.getHost();
            Integer port = connInfo == null ? new Integer(0) : connInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_connInfoMenuItemActionPerformed

    private void sessionInfoTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sessionInfoTableMouseReleased
        if (SwingUtilities.isRightMouseButton(evt)) {
            int r = sessionInfoTable.rowAtPoint(evt.getPoint());
            if (r >= 0 && r < sessionInfoTable.getRowCount()) {
                sessionInfoTable.setRowSelectionInterval(r, r);
            } else {
                sessionInfoTable.clearSelection();
            }

            int rowindex = sessionInfoTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            if (evt.getComponent() instanceof JTable) {
                sessInfoPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
            String status = (String) sessionInfoTable.getValueAt(rowindex, 6);
            String type = (String) sessionInfoTable.getValueAt(rowindex, 3);
            if ("ACTIVE".equalsIgnoreCase(status)) {
                sessInfoRefreshMenuItem.setEnabled(true);
                sessInfoConnMenuItem.setEnabled(false);
                if ("SELL".equalsIgnoreCase(type)) {
                    sessInfoDisconnMenuItem.setEnabled(false);
                } else {
                    sessInfoDisconnMenuItem.setEnabled(true);
                }
                sessInfoResetSeqMenuItem.setEnabled(true);
                sessInfoResetSessMenuItem.setEnabled(true);
                sessInfoFreezeMenuItem.setEnabled(true);
                sessInfoThawMenuItem.setEnabled(false);
                sessInfoStopMenuItem.setEnabled(true);
                sessInfoStartMenuItem.setEnabled(false);
                sessInfoStatsMenuItem.setEnabled(true);
            } else if ("FROZEN".equalsIgnoreCase(status)) {
                sessInfoRefreshMenuItem.setEnabled(true);
                sessInfoConnMenuItem.setEnabled(false);
                sessInfoDisconnMenuItem.setEnabled(false);
                sessInfoResetSeqMenuItem.setEnabled(false);
                sessInfoResetSessMenuItem.setEnabled(false);
                sessInfoFreezeMenuItem.setEnabled(false);
                sessInfoThawMenuItem.setEnabled(true);
                sessInfoStopMenuItem.setEnabled(false);
                sessInfoStartMenuItem.setEnabled(false);
                sessInfoStatsMenuItem.setEnabled(false);
            } else if ("DISCONNECTED".equalsIgnoreCase(status)) {
                sessInfoRefreshMenuItem.setEnabled(true);
                if ("SELL".equalsIgnoreCase(type)) {
                    sessInfoConnMenuItem.setEnabled(false);
                } else {
                    sessInfoConnMenuItem.setEnabled(true);
                }
                sessInfoDisconnMenuItem.setEnabled(false);
                sessInfoResetSeqMenuItem.setEnabled(false);
                sessInfoResetSessMenuItem.setEnabled(false);
                sessInfoFreezeMenuItem.setEnabled(false);
                sessInfoThawMenuItem.setEnabled(false);
                sessInfoStopMenuItem.setEnabled(false);
                sessInfoStartMenuItem.setEnabled(false);
                sessInfoStatsMenuItem.setEnabled(false);
            } else if ("INACTIVE".equalsIgnoreCase(status)) {
                sessInfoRefreshMenuItem.setEnabled(true);
                sessInfoConnMenuItem.setEnabled(false);
                sessInfoDisconnMenuItem.setEnabled(false);
                sessInfoResetSeqMenuItem.setEnabled(false);
                sessInfoResetSessMenuItem.setEnabled(false);
                sessInfoFreezeMenuItem.setEnabled(false);
                sessInfoThawMenuItem.setEnabled(false);
                sessInfoStopMenuItem.setEnabled(false);
                sessInfoStartMenuItem.setEnabled(false);
                sessInfoStatsMenuItem.setEnabled(false);
            } else if ("SHUTDOWN".equalsIgnoreCase(status)) {
                sessInfoRefreshMenuItem.setEnabled(true);
                sessInfoConnMenuItem.setEnabled(false);
                sessInfoDisconnMenuItem.setEnabled(false);
                sessInfoResetSeqMenuItem.setEnabled(false);
                sessInfoResetSessMenuItem.setEnabled(false);
                sessInfoFreezeMenuItem.setEnabled(false);
                sessInfoThawMenuItem.setEnabled(false);
                sessInfoStopMenuItem.setEnabled(false);
                sessInfoStartMenuItem.setEnabled(true);
                sessInfoStatsMenuItem.setEnabled(false);
            } else {
                sessInfoRefreshMenuItem.setEnabled(true);
                sessInfoConnMenuItem.setEnabled(false);
                sessInfoDisconnMenuItem.setEnabled(false);
                sessInfoResetSeqMenuItem.setEnabled(false);
                sessInfoResetSessMenuItem.setEnabled(false);
                sessInfoFreezeMenuItem.setEnabled(false);
                sessInfoThawMenuItem.setEnabled(false);
                sessInfoStopMenuItem.setEnabled(false);
                sessInfoStartMenuItem.setEnabled(false);
                sessInfoStatsMenuItem.setEnabled(false);
            }
        }
    }//GEN-LAST:event_sessionInfoTableMouseReleased

    private void sessInfoRefreshMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessInfoRefreshMenuItemActionPerformed
        EngineConnectionInfo connInfo = null;
        try {
            int rowindex = sessionInfoTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            String sessionId = (String) sessionInfoTable.getValueAt(rowindex, 0);
            connInfo = configuration.getEngineConnectionInfo(sessionTableModel.getConnectionId());
            EngineSessionWorker worker = activeConnections.get(sessionTableModel.getConnectionId());
            if (worker != null) {
                TableResult result = worker.getConfigSession(sessionId);
                if (!result.getOutcome()) {
                    JOptionPane.showMessageDialog(this,
                            Messages.getString("HadesAdminConsole.ErrMsg.executeCommand.text", "ListSession", result.getErrMsg()),
                            Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    sessionTableModel.updateSession(result);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        Messages.getString("HadesAdminConsole.ErrMsg.connectSession.text", connInfo.getId()),
                        Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " - Cause: " + ex.getCause().getMessage() : "";
            String host = connInfo == null ? "Unknown" : connInfo.getHost();
            Integer port = connInfo == null ? new Integer(0) : connInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_sessInfoRefreshMenuItemActionPerformed

    private void connShutdownMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connShutdownMenuItemActionPerformed
        EngineConnectionInfo connInfo = null;
        try {
            int rowindex = configConnTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            int choice = JOptionPane.showConfirmDialog(this,
                    Messages.getString("HadesAdminConsole.shutdownEngine.confirm.text", configConnTable.getValueAt(rowindex, 1)),
                    Messages.getString("HadesAdminConsole.shutdownEngine.confirm.title"),
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                return;
            }
            Integer id = (Integer) configConnTable.getValueAt(rowindex, 0);
            connInfo = configuration.getEngineConnectionInfo(id);
            EngineSessionWorker worker = activeConnections.get(id);
            if (worker != null) {
                OutcomeResult result = worker.shutdown();
                if (!result.getOutcome()) {
                    JOptionPane.showMessageDialog(this,
                            Messages.getString("HadesAdminConsole.ErrMsg.executeCommand.text", "Shutdown", result.getErrMsg()),
                            Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    worker.executeCommand(EngineSessionWorker.CMD_EXIT);
                    activeConnections.remove(id);
                    connInfo.setConnected(false);
                    connectionTableModel.updateEngineSession(connInfo);
                    sessionTableModel.setSessions(null);
                    if (sessStatsInternalFrame.isVisible()) {
                        if (sessionStatistics.getConnectionId() != null && sessionStatistics.getConnectionId().equals(connInfo.getId())) {
                            MultiTableResult stats = new MultiTableResult(Boolean.TRUE);
                            stats.addTableResult(SessionStatsResultParam.SessionStats.toString(), new TableResult(Boolean.TRUE));
                            stats.addTableResult(SessionStatsResultParam.TransportStats.toString(), new TableResult(Boolean.TRUE));
                            stats.addTableResult(SessionStatsResultParam.ProtocolStats.toString(), new TableResult(Boolean.TRUE));
                            stats.addTableResult(SessionStatsResultParam.ConsumerStreamStats.toString(), new TableResult(Boolean.TRUE));
                            stats.addTableResult(SessionStatsResultParam.ProducerStreamStats.toString(), new TableResult(Boolean.TRUE));
                            sessionStatistics.updateSessionStatistics(stats);
                            if (!sessStatsInternalFrame.isIcon()) {
                                sessStatsInternalFrame.setIcon(true);
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        Messages.getString("HadesAdminConsole.ErrMsg.connectSession.text", connInfo.getId()),
                        Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " - Cause: " + ex.getCause().getMessage() : "";
            String host = connInfo == null ? "Unknown" : connInfo.getHost();
            Integer port = connInfo == null ? new Integer(0) : connInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_connShutdownMenuItemActionPerformed

    private void connShutdownNowMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connShutdownNowMenuItemActionPerformed
        EngineConnectionInfo connInfo = null;
        try {
            int rowindex = configConnTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            int choice = JOptionPane.showConfirmDialog(this,
                    Messages.getString("HadesAdminConsole.shutdownEngineNow.confirm.text", configConnTable.getValueAt(rowindex, 1)),
                    Messages.getString("HadesAdminConsole.shutdownEngineNow.confirm.title"),
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                return;
            }
            Integer id = (Integer) configConnTable.getValueAt(rowindex, 0);
            connInfo = configuration.getEngineConnectionInfo(id);
            EngineSessionWorker worker = activeConnections.get(id);
            if (worker != null) {
                OutcomeResult result = worker.shutdown();
                if (!result.getOutcome()) {
                    JOptionPane.showMessageDialog(this,
                            Messages.getString("HadesAdminConsole.ErrMsg.executeCommand.text", "ShutdownImmediate", result.getErrMsg()),
                            Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    worker.executeCommand(EngineSessionWorker.CMD_EXIT);
                    activeConnections.remove(id);
                    connInfo.setConnected(false);
                    connectionTableModel.updateEngineSession(connInfo);
                    sessionTableModel.setSessions(null);
                    if (sessStatsInternalFrame.isVisible()) {
                        if (sessionStatistics.getConnectionId() != null && sessionStatistics.getConnectionId().equals(connInfo.getId())) {
                            MultiTableResult stats = new MultiTableResult(Boolean.TRUE);
                            stats.addTableResult(SessionStatsResultParam.SessionStats.toString(), new TableResult(Boolean.TRUE));
                            stats.addTableResult(SessionStatsResultParam.TransportStats.toString(), new TableResult(Boolean.TRUE));
                            stats.addTableResult(SessionStatsResultParam.ProtocolStats.toString(), new TableResult(Boolean.TRUE));
                            stats.addTableResult(SessionStatsResultParam.ConsumerStreamStats.toString(), new TableResult(Boolean.TRUE));
                            stats.addTableResult(SessionStatsResultParam.ProducerStreamStats.toString(), new TableResult(Boolean.TRUE));
                            sessionStatistics.updateSessionStatistics(stats);
                            if (!sessStatsInternalFrame.isIcon()) {
                                sessStatsInternalFrame.setIcon(true);
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        Messages.getString("HadesAdminConsole.ErrMsg.connectSession.text", connInfo.getId()),
                        Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " - Cause: " + ex.getCause().getMessage() : "";
            String host = connInfo == null ? "Unknown" : connInfo.getHost();
            Integer port = connInfo == null ? new Integer(0) : connInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_connShutdownNowMenuItemActionPerformed

    private void sessInfoDisconnMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessInfoDisconnMenuItemActionPerformed
        EngineConnectionInfo connInfo = null;
        try {
            int rowindex = sessionInfoTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            String sessionId = (String) sessionInfoTable.getValueAt(rowindex, 0);
            connInfo = configuration.getEngineConnectionInfo(sessionTableModel.getConnectionId());
            EngineSessionWorker worker = activeConnections.get(sessionTableModel.getConnectionId());
            if (worker != null) {
                OutcomeResult result = worker.disconnectSessionTransport(sessionId);
                if (!result.getOutcome()) {
                    JOptionPane.showMessageDialog(this,
                            Messages.getString("HadesAdminConsole.ErrMsg.executeCommand.text", "DisconnectTransport", result.getErrMsg()),
                            Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    sessionTableModel.updateSession(worker.getConfigSession(sessionId));
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        Messages.getString("HadesAdminConsole.ErrMsg.connectSession.text", connInfo.getId()),
                        Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " - Cause: " + ex.getCause().getMessage() : "";
            String host = connInfo == null ? "Unknown" : connInfo.getHost();
            Integer port = connInfo == null ? new Integer(0) : connInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_sessInfoDisconnMenuItemActionPerformed

    private void sessInfoConnMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessInfoConnMenuItemActionPerformed
        EngineConnectionInfo connInfo = null;
        try {
            int rowindex = sessionInfoTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            String sessionId = (String) sessionInfoTable.getValueAt(rowindex, 0);
            connInfo = configuration.getEngineConnectionInfo(sessionTableModel.getConnectionId());
            EngineSessionWorker worker = activeConnections.get(sessionTableModel.getConnectionId());
            if (worker != null) {
                OutcomeResult result = worker.connectSessionTransport(sessionId);
                if (!result.getOutcome()) {
                    JOptionPane.showMessageDialog(this,
                            Messages.getString("HadesAdminConsole.ErrMsg.executeCommand.text", "ConnectTransport", result.getErrMsg()),
                            Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    sessionTableModel.updateSession(worker.getConfigSession(sessionId));
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        Messages.getString("HadesAdminConsole.ErrMsg.connectSession.text", connInfo.getId()),
                        Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " - Cause: " + ex.getCause().getMessage() : "";
            String host = connInfo == null ? "Unknown" : connInfo.getHost();
            Integer port = connInfo == null ? new Integer(0) : connInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_sessInfoConnMenuItemActionPerformed

    private void sessInfoResetSeqMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessInfoResetSeqMenuItemActionPerformed
        EngineConnectionInfo connInfo = null;
        try {
            int rowindex = sessionInfoTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            String sessionId = (String) sessionInfoTable.getValueAt(rowindex, 0);
            int newSeq = Integer.parseInt((String) sessionInfoTable.getValueAt(rowindex, 4));
            connInfo = configuration.getEngineConnectionInfo(sessionTableModel.getConnectionId());
            EngineSessionWorker worker = activeConnections.get(sessionTableModel.getConnectionId());
            if (worker != null) {
                OutcomeResult result = worker.resetSequence(sessionId, newSeq);
                if (!result.getOutcome()) {
                    JOptionPane.showMessageDialog(this,
                            Messages.getString("HadesAdminConsole.ErrMsg.executeCommand.text", "ResetSequence", result.getErrMsg()),
                            Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    sessionTableModel.updateSession(worker.getConfigSession(sessionId));
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        Messages.getString("HadesAdminConsole.ErrMsg.connectSession.text", connInfo.getId()),
                        Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " - Cause: " + ex.getCause().getMessage() : "";
            String host = connInfo == null ? "Unknown" : connInfo.getHost();
            Integer port = connInfo == null ? new Integer(0) : connInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_sessInfoResetSeqMenuItemActionPerformed

    private void sessInfoResetSessMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessInfoResetSessMenuItemActionPerformed
        EngineConnectionInfo connInfo = null;
        try {
            int rowindex = sessionInfoTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            String sessionId = (String) sessionInfoTable.getValueAt(rowindex, 0);
            connInfo = configuration.getEngineConnectionInfo(sessionTableModel.getConnectionId());
            EngineSessionWorker worker = activeConnections.get(sessionTableModel.getConnectionId());
            if (worker != null) {
                OutcomeResult result = worker.resetSeession(sessionId);
                if (!result.getOutcome()) {
                    JOptionPane.showMessageDialog(this,
                            Messages.getString("HadesAdminConsole.ErrMsg.executeCommand.text", "ResetSession", result.getErrMsg()),
                            Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    sessionTableModel.updateSession(worker.getConfigSession(sessionId));
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        Messages.getString("HadesAdminConsole.ErrMsg.connectSession.text", connInfo.getId()),
                        Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " - Cause: " + ex.getCause().getMessage() : "";
            String host = connInfo == null ? "Unknown" : connInfo.getHost();
            Integer port = connInfo == null ? new Integer(0) : connInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_sessInfoResetSessMenuItemActionPerformed

    private void sessInfoFreezeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessInfoFreezeMenuItemActionPerformed
        EngineConnectionInfo connInfo = null;
        try {
            int rowindex = sessionInfoTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            String sessionId = (String) sessionInfoTable.getValueAt(rowindex, 0);
            connInfo = configuration.getEngineConnectionInfo(sessionTableModel.getConnectionId());
            EngineSessionWorker worker = activeConnections.get(sessionTableModel.getConnectionId());
            if (worker != null) {
                OutcomeResult result = worker.freezeSession(sessionId);
                if (!result.getOutcome()) {
                    JOptionPane.showMessageDialog(this,
                            Messages.getString("HadesAdminConsole.ErrMsg.executeCommand.text", "FreezeSession", result.getErrMsg()),
                            Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    sessionTableModel.updateSession(worker.getConfigSession(sessionId));
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        Messages.getString("HadesAdminConsole.ErrMsg.connectSession.text", connInfo.getId()),
                        Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " - Cause: " + ex.getCause().getMessage() : "";
            String host = connInfo == null ? "Unknown" : connInfo.getHost();
            Integer port = connInfo == null ? new Integer(0) : connInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_sessInfoFreezeMenuItemActionPerformed

    private void sessInfoThawMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessInfoThawMenuItemActionPerformed
        EngineConnectionInfo connInfo = null;
        try {
            int rowindex = sessionInfoTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            String sessionId = (String) sessionInfoTable.getValueAt(rowindex, 0);
            connInfo = configuration.getEngineConnectionInfo(sessionTableModel.getConnectionId());
            EngineSessionWorker worker = activeConnections.get(sessionTableModel.getConnectionId());
            if (worker != null) {
                OutcomeResult result = worker.thawSession(sessionId);
                if (!result.getOutcome()) {
                    JOptionPane.showMessageDialog(this,
                            Messages.getString("HadesAdminConsole.ErrMsg.executeCommand.text", "ThawSession", result.getErrMsg()),
                            Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    sessionTableModel.updateSession(worker.getConfigSession(sessionId));
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        Messages.getString("HadesAdminConsole.ErrMsg.connectSession.text", connInfo.getId()),
                        Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " - Cause: " + ex.getCause().getMessage() : "";
            String host = connInfo == null ? "Unknown" : connInfo.getHost();
            Integer port = connInfo == null ? new Integer(0) : connInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_sessInfoThawMenuItemActionPerformed

    private void sessInfoStopMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessInfoStopMenuItemActionPerformed
        EngineConnectionInfo connInfo = null;
        try {
            int rowindex = sessionInfoTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            String sessionId = (String) sessionInfoTable.getValueAt(rowindex, 0);
            connInfo = configuration.getEngineConnectionInfo(sessionTableModel.getConnectionId());
            EngineSessionWorker worker = activeConnections.get(sessionTableModel.getConnectionId());
            if (worker != null) {
                OutcomeResult result = worker.stopSession(sessionId);
                if (!result.getOutcome()) {
                    JOptionPane.showMessageDialog(this,
                            Messages.getString("HadesAdminConsole.ErrMsg.executeCommand.text", "KillSession", result.getErrMsg()),
                            Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    sessionTableModel.updateSession(worker.getConfigSession(sessionId));
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        Messages.getString("HadesAdminConsole.ErrMsg.connectSession.text", connInfo.getId()),
                        Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " - Cause: " + ex.getCause().getMessage() : "";
            String host = connInfo == null ? "Unknown" : connInfo.getHost();
            Integer port = connInfo == null ? new Integer(0) : connInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_sessInfoStopMenuItemActionPerformed

    private void sessInfoStartMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessInfoStartMenuItemActionPerformed
        EngineConnectionInfo connInfo = null;
        try {
            int rowindex = sessionInfoTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            String sessionId = (String) sessionInfoTable.getValueAt(rowindex, 0);
            connInfo = configuration.getEngineConnectionInfo(sessionTableModel.getConnectionId());
            EngineSessionWorker worker = activeConnections.get(sessionTableModel.getConnectionId());
            if (worker != null) {
                OutcomeResult result = worker.startSession(sessionId);
                if (!result.getOutcome()) {
                    JOptionPane.showMessageDialog(this,
                            Messages.getString("HadesAdminConsole.ErrMsg.executeCommand.text", "StartSession", result.getErrMsg()),
                            Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    sessionTableModel.updateSession(worker.getConfigSession(sessionId));
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        Messages.getString("HadesAdminConsole.ErrMsg.connectSession.text", connInfo.getId()),
                        Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " - Cause: " + ex.getCause().getMessage() : "";
            String host = connInfo == null ? "Unknown" : connInfo.getHost();
            Integer port = connInfo == null ? new Integer(0) : connInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_sessInfoStartMenuItemActionPerformed

    private void sessInfoStatsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessInfoStatsMenuItemActionPerformed
        EngineConnectionInfo connInfo = null;
        try {
            int rowindex = sessionInfoTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            String sessionId = (String) sessionInfoTable.getValueAt(rowindex, 0);
            connInfo = configuration.getEngineConnectionInfo(sessionTableModel.getConnectionId());
            EngineSessionWorker worker = activeConnections.get(sessionTableModel.getConnectionId());
            if (worker != null) {
                MultiTableResult result = worker.getSessionStats(sessionId);
                if (!result.getOutcome()) {
                    JOptionPane.showMessageDialog(this,
                            Messages.getString("HadesAdminConsole.ErrMsg.executeCommand.text", "StartSession", result.getErrMsg()),
                            Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    if (!sessStatsInternalFrame.isVisible()) {
                        sessStatsInternalFrame.setVisible(true);
                        desktopPane.add(sessStatsInternalFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);
                        try {
                            sessStatsInternalFrame.setSelected(true);
                        } catch (PropertyVetoException ex) {
                            LOGGER.log(Level.SEVERE, "Error setting the SessionStats Info internal frame selectd. Error was: {0}", ex);
                        }
                    }
                    if (sessStatsInternalFrame.isIcon()) {
                        sessStatsInternalFrame.setIcon(false);
                    }
                    sessionStatistics.updateSessionStatistics(result);
                    sessionStatistics.setConnectionId(sessionTableModel.getConnectionId());
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        Messages.getString("HadesAdminConsole.ErrMsg.connectSession.text", connInfo.getId()),
                        Messages.getString("HadesAdminConsole.connectSession.confirm.title", connInfo.getHost(), connInfo.getPort()),
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            String cause = ex.getCause() != null ? " - Cause: " + ex.getCause().getMessage() : "";
            String host = connInfo == null ? "Unknown" : connInfo.getHost();
            Integer port = connInfo == null ? new Integer(0) : connInfo.getPort();
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + cause,
                    Messages.getString("HadesAdminConsole.connectSession.confirm.title", host, port),
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_sessInfoStatsMenuItemActionPerformed

    private void sessStatsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sessStatsMenuItemActionPerformed
        try {
            if (sessStatsInternalFrame.isVisible()) {
                if (sessStatsInternalFrame.isIcon()) {
                    sessStatsInternalFrame.setIcon(false);
                }
                return;
            }
            sessStatsInternalFrame.setVisible(true);
            desktopPane.add(sessStatsInternalFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);

            if (sessStatsInternalFrame.isIcon()) {
                sessStatsInternalFrame.setIcon(false);
            }
            sessStatsInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            LOGGER.log(Level.SEVERE, "Error setting the Statistics internal frame selectd. Error was: {0}", ex);
        }
    }//GEN-LAST:event_sessStatsMenuItemActionPerformed

    private void alertsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alertsMenuItemActionPerformed
        try {
            if (eventsInternalFrame.isVisible()) {
                if (eventsInternalFrame.isIcon()) {
                    eventsInternalFrame.setIcon(false);
                }
                return;
            }
            eventsInternalFrame.setVisible(true);
            desktopPane.add(eventsInternalFrame, javax.swing.JLayeredPane.DEFAULT_LAYER);

            if (eventsInternalFrame.isIcon()) {
                eventsInternalFrame.setIcon(false);
            }
            eventsInternalFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            LOGGER.log(Level.SEVERE, "Error setting the Alerts internal frame selected. Error was: {0}", ex);
        }
    }//GEN-LAST:event_alertsMenuItemActionPerformed

    private void alertsTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alertsTableMouseReleased
        if (SwingUtilities.isRightMouseButton(evt)) {
            int r = alertsTable.rowAtPoint(evt.getPoint());
            if (r >= 0 && r < alertsTable.getRowCount()) {
                alertsTable.setRowSelectionInterval(r, r);
            } else {
                alertsTable.clearSelection();
            }

            int rowindex = alertsTable.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            if (evt.getComponent() instanceof JTable) {
                alertPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_alertsTableMouseReleased

    private void viewStackMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewStackMenuItemActionPerformed
        int rowindex = alertsTable.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        String id = (String) alertsTable.getValueAt(rowindex, 1);
        AlertEvent alert = notificationProcessor.getAlertsTableModel().getAlertById(id);
        if (alert != null) {
            ErrorDetailsView errorView = new ErrorDetailsView(this, id, alert.getErrorMessage(), alert.getErrorStack());
            errorView.setLocationRelativeTo(this);
            errorView.setVisible(true);
        }
    }//GEN-LAST:event_viewStackMenuItemActionPerformed

    private void deleteAlertMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAlertMenuItemActionPerformed
        int rowindex = alertsTable.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        String id = (String) alertsTable.getValueAt(rowindex, 1);
        notificationProcessor.getAlertsTableModel().removeAlert(id);
    }//GEN-LAST:event_deleteAlertMenuItemActionPerformed

    private void clearEventsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearEventsButtonActionPerformed
        int result = JOptionPane.showConfirmDialog(this,
                Messages.getString("HadesAdminConsole.events.confirmClearEvents.text"),
                Messages.getString("HadesAdminConsole.events.confirmClearEvents.title"),
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.NO_OPTION) {
            return;
        }
        if (eventsTabbedPane.getSelectedIndex() == 0) {
            notificationProcessor.getAlertsTableModel().clearNotifications();
        }
    }//GEN-LAST:event_clearEventsButtonActionPerformed

    private void filterEventsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterEventsButtonActionPerformed
        if (eventsTabbedPane.getSelectedIndex() == 0) {
            AlertFilterView alertFilter = new AlertFilterView(this);
            alertFilter.setLocationRelativeTo(this);
            alertFilter.setVisible(true);
        }
    }//GEN-LAST:event_filterEventsButtonActionPerformed

    private void viewErrorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewErrorMenuItemActionPerformed
        int rowindex = alertsTable.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        String id = (String) alertsTable.getValueAt(rowindex, 1);
        AlertEvent alert = notificationProcessor.getAlertsTableModel().getAlertById(id);
        if (alert != null) {
            ErrorDetailsView errorView = new ErrorDetailsView(this, id, alert.getMessage());
            errorView.setLocationRelativeTo(this);
            errorView.setVisible(true);
        }
    }//GEN-LAST:event_viewErrorMenuItemActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            LOGGER.info(String.format("OS Type: %s", System.getProperty("os.name")));
            if (System.getProperty("os.name").startsWith("Windows")) {
                LOGGER.info(String.format("System L&F: %s", UIManager.getSystemLookAndFeelClassName()));
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } else {
                boolean found = false;
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    LOGGER.info(String.format("System L&F: %s", info.getClassName()));
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HadesAdminConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HadesAdminConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HadesAdminConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HadesAdminConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                HadesAdminConsole console = new HadesAdminConsole();
                console.setBackground(Color.blue);
                console.setVisible(true);
            }
        });
    }
    
    public AdminConsoleConfigInfo getConfiguration() {
        return configuration;
    }

    public EngineConnectionTableModel getConnectionTableModel() {
        return connectionTableModel;
    }

    public EngineSessionTableModel getSessionTableModel() {
        return sessionTableModel;
    }

    public EngineSessionStatistics getSessionStatistics() {
        return sessionStatistics;
    }

    public EngineNotificationProcessor getNotificationProcessor() {
        return notificationProcessor;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JPopupMenu alertPopupMenu;
    private javax.swing.JPopupMenu.Separator alertSeparator;
    private javax.swing.JMenuItem alertsMenuItem;
    private javax.swing.JPanel alertsPanel;
    private javax.swing.JScrollPane alertsScrollPane;
    private javax.swing.JTable alertsTable;
    private javax.swing.JButton clearEventsButton;
    private javax.swing.JScrollPane configConnPane;
    private javax.swing.JTable configConnTable;
    private javax.swing.JMenuItem connConfigConnectMenuItem;
    private javax.swing.JMenuItem connConfigDeleteMenuItem;
    private javax.swing.JMenuItem connConfigDisconnectMenuItem;
    private javax.swing.JMenuItem connConfigEditMenuItem;
    private javax.swing.JPopupMenu connConfigPopupMenu;
    private javax.swing.JMenuItem connInfoMenuItem;
    private javax.swing.JInternalFrame connInternalFrame;
    private javax.swing.JPopupMenu.Separator connSeparator1;
    private javax.swing.JPopupMenu.Separator connSeparator2;
    private javax.swing.JMenuItem connShutdownMenuItem;
    private javax.swing.JMenuItem connShutdownNowMenuItem;
    private javax.swing.JMenuItem connWindowMenuItem;
    private javax.swing.JMenu connectionMenu;
    private javax.swing.JLabel consStreamDiscardedAppLabel;
    private javax.swing.JLabel consStreamDiscardedLabel;
    private javax.swing.JLabel consStreamMsgInAppLabel;
    private javax.swing.JLabel consStreamMsgInLabel;
    private javax.swing.JLabel consStreamRejectedAppLabel;
    private javax.swing.JLabel consStreamRejectedLabel;
    private javax.swing.JPanel consStreamStatsPanel;
    private javax.swing.JMenuItem createConnMenuItem;
    private javax.swing.JMenuItem deleteAlertMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JInternalFrame eventsInternalFrame;
    private javax.swing.JTabbedPane eventsTabbedPane;
    private javax.swing.JToolBar eventsToolBar;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton filterEventsButton;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel prodStreamDiscardedAppLabel;
    private javax.swing.JLabel prodStreamDiscardedLabel;
    private javax.swing.JLabel prodStreamMsgOutAppLabel;
    private javax.swing.JLabel prodStreamMsgOutLabel;
    private javax.swing.JLabel prodStreamRejectedAppLabel;
    private javax.swing.JLabel prodStreamRejectedLabel;
    private javax.swing.JPanel prodStreamStatsPanel;
    private javax.swing.JLabel protoMsgInAppLabel;
    private javax.swing.JLabel protoMsgInLabel;
    private javax.swing.JLabel protoMsgOutAppLabel;
    private javax.swing.JLabel protoMsgOutLabel;
    private javax.swing.JLabel protoRejectedAppLabel;
    private javax.swing.JLabel protoRejectedLabel;
    private javax.swing.JPanel protoStatsPanel;
    private javax.swing.JLabel protoThroughputInAppLabel;
    private javax.swing.JLabel protoThroughputInLabel;
    private javax.swing.JLabel protoThroughputOutAppLabel;
    private javax.swing.JLabel protoThroughputOutLabel;
    private javax.swing.JMenuItem sessInfoConnMenuItem;
    private javax.swing.JMenuItem sessInfoDisconnMenuItem;
    private javax.swing.JMenuItem sessInfoFreezeMenuItem;
    private javax.swing.JInternalFrame sessInfoInternalFrame;
    private javax.swing.JMenuItem sessInfoMenuItem;
    private javax.swing.JPopupMenu sessInfoPopupMenu;
    private javax.swing.JMenuItem sessInfoRefreshMenuItem;
    private javax.swing.JMenuItem sessInfoResetSeqMenuItem;
    private javax.swing.JMenuItem sessInfoResetSessMenuItem;
    private javax.swing.JScrollPane sessInfoScrollPane;
    private javax.swing.JMenuItem sessInfoStartMenuItem;
    private javax.swing.JMenuItem sessInfoStatsMenuItem;
    private javax.swing.JMenuItem sessInfoStopMenuItem;
    private javax.swing.JMenuItem sessInfoThawMenuItem;
    private javax.swing.JPopupMenu.Separator sessSeparator;
    private javax.swing.JInternalFrame sessStatsInternalFrame;
    private javax.swing.JMenuItem sessStatsMenuItem;
    private javax.swing.JPanel sessStatsPanel;
    private javax.swing.JScrollPane sessStatsScrollPane;
    private javax.swing.JTable sessionInfoTable;
    private javax.swing.JLabel startSessionTimeAppLabel;
    private javax.swing.JLabel startSessionTimeLabel;
    private javax.swing.JPanel startTimeStatsPanel;
    private javax.swing.JLabel statsCptyLabel;
    private javax.swing.JPanel statsFillPanel;
    private javax.swing.JLabel statsSepLabel;
    private javax.swing.JLabel statsSessionLabel;
    private javax.swing.JPanel transpStatsPanel;
    private javax.swing.JLabel transportBytesInAppLabel;
    private javax.swing.JLabel transportBytesInLabel;
    private javax.swing.JLabel transportBytesOutAppLabel;
    private javax.swing.JLabel transportBytesOutLabel;
    private javax.swing.JLabel transportThroughputInAppLabel;
    private javax.swing.JLabel transportThroughputInLabel;
    private javax.swing.JLabel transportThroughputOutAppLabel;
    private javax.swing.JLabel transportThroughputOutLabel;
    private javax.swing.JMenuItem viewErrorMenuItem;
    private javax.swing.JMenuItem viewStackMenuItem;
    private javax.swing.JMenu windowsMenu;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void loadConfiguration() {
        try {
            configuration = Configurator.readConfiguration();
        } catch (ConfigurationException ex) {
            String errMsg = String.format("Fatal error starting the HadesAdminConsole application. Error was: %1$s Details: %2$s", 
                    new Object[] {ex.getMessage(), ExceptionUtil.getStackTrace(ex)});
            LOGGER.log(Level.SEVERE, errMsg);
            
            throw new RuntimeException(errMsg);
        }
        if (configuration.getInternalInfo() == null) {
            configuration.setInternalInfo(new InternalInfo());
            try {
                Configurator.saveConfiguration(configuration);
            } catch (Exception ex) {
                String errMsg = String.format("Fatal error starting the HadesAdminConsole application. Error was: %1$s Details: %2$s",
                        new Object[]{ex.getMessage(), ExceptionUtil.getStackTrace(ex)});
                LOGGER.log(Level.SEVERE, errMsg);

                throw new RuntimeException(errMsg);
            }
        }
    }
    
    private void initConfiguration() {
        activeConnections = connectStartupConnections();
        connectionTableModel = new EngineConnectionTableModel(configuration.getEngineConnections());
        sessionTableModel = new EngineSessionTableModel();
        sessionStatistics = new EngineSessionStatistics();
        notificationProcessor = new EngineNotificationProcessor();
        notificationProcessor.getAlertsTableModel().setFilter(configuration.getAlertFilter());
        Thread t = new Thread(notificationProcessor, "NOTIFICATION_PROCESSOR");
        t.start();
        ThreadUtil.blockUntilAlive(t);
    }

    private ConcurrentMap<Integer, EngineSessionWorker> connectStartupConnections() {
        ConcurrentMap<Integer, EngineSessionWorker> result = new ConcurrentHashMap<Integer, EngineSessionWorker>();
        StringBuilder errors = new StringBuilder();
        if (configuration.getEngineConnections() != null && configuration.getEngineConnections().length > 0) {
            for (EngineConnectionInfo conn : configuration.getEngineConnections()) {
                if (conn.getConnectAtStartup().booleanValue()) {
                    try {
                        EngineSessionWorker worker = new EngineSessionWorker(conn, this);
                        Thread t = new Thread(worker, String.format("SESSION_%s_%s:%s", new Object[] {conn.getId(), conn.getHost(), conn.getPort()}));
                        t.start();
                        ThreadUtil.blockUntilAlive(t);
                        worker.connect();
                        result.putIfAbsent(new Integer(conn.getId()), worker);
                        conn.setConnected(true);
                        conn.setEngineName(worker.getInstanceName());
                        conn.setEngineDescr(worker.getInstanceDescr());
                        Configurator.saveConfiguration(configuration);
                    } catch (Exception ex) {
                        errors.append(ex.getMessage());
                        if (ex.getCause() != null) {
                            errors.append(" ").append( ex.getCause().getMessage());
                        }
                        errors.append(System.getProperty("line.separator"));
                    }
                }
            }
            if (errors.length() > 0) {
                JOptionPane.showMessageDialog(this,
                    errors.toString(),
                    Messages.getString("HadesAdminConsole..ErrMsg.connect.text"),
                    JOptionPane.ERROR_MESSAGE);
            }
        }
        
        return result;
    }
}
