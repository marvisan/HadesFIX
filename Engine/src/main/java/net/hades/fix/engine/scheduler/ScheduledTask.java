/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ScheduledTask.java
 *
 * $Id: ScheduledTask.java,v 1.5 2011-03-31 10:32:03 vrotaru Exp $
 */
package net.hades.fix.engine.scheduler;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.config.model.*;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.ProtocolException;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.mgmt.data.ProcessStatus;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.command.CommandType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.session.SessionCoordinator;

/**
 * Scheduler task set to be executed at a configured time.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 */
public class ScheduledTask implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(ScheduledTask.class.getName());

    private HadesInstance instance;
    private SessionCoordinator coordinator;
    private ScheduleTaskInfo configuration;

    public ScheduledTask(HadesInstance instance, SessionCoordinator coordinator, ScheduleTaskInfo configuration) {
        this.instance = instance;
        this.coordinator = coordinator;
        this.configuration = configuration;
    }

    @Override
    public void run() {
        if (!isAllowedToRun()) {
            return;
        }
        if (configuration != null && configuration.getTaskType() != null) {
            try {
                switch (configuration.getTaskType()) {
                    case FreezeSession:
                        freezeSession();
                        break;

                    case ThawSession:
                        thawSession();
                        break;

                    case StartSession:
                        startSession();
                        break;

                    case StopSession:
                        stopSession();
                        break;

                    case ConnectSession:
                        connectSession();
                        break;

                    case DisconnectSession:
                        disconnectSession();
                        break;

                    case SessionReset:
                        sessionReset();
                        break;

                    case ShutdownInstance:
                        shutdownInstance();
                        break;

                }
            } catch (Exception ex) {
                String logErr = "Scheduled task [" + configuration.getName() + "] not able to execute task ["
                        + configuration.getTaskType().name() + "]. Error eas : " + ex.getMessage();
                LOGGER.severe(logErr);
                if (instance != null) {
                    instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                            BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, ex)));
                }
            }
        }
    }

    private void freezeSession() {
        if (instance != null) {
            if (coordinator == null) {
                if (configuration.getTaskParams() != null && configuration.getTaskParams().length > 0) {
                    String cpty = configuration.getParameter(ScheduleFieldName.CptyAddress);
                    String sess = configuration.getParameter(ScheduleFieldName.SessAddress);
                    if (cpty != null && sess != null) {
                        SessionCoordinator sessionCoordinator = instance.getSessionCoordinator(cpty, sess);
                        if (sessionCoordinator != null) {
                            if (ProcessStatus.ACTIVE.equals(sessionCoordinator.getProcessStatus())) {
                                sessionCoordinator.execute(new Command(CommandType.Freeze));
                            } else {
                                String logErr = "Scheduled task [" + configuration.getName() + "] not able to freeze session ["
                                        + sessionCoordinator.getSessionAddress() + "] in wrong state.";
                                LOGGER.severe(logErr);
                                if (instance != null) {
                                    instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                            BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                                }
                            }
                        } else {
                            String logErr = "Scheduled task [" + configuration.getName() + "] not able to freeze session because no "
                                    + "session exists for address [" + cpty + ":" + sess + "].";
                            LOGGER.severe(logErr);
                            if (instance != null) {
                                instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                        BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                            }
                        }
                    } else {
                        String logErr = "Scheduled task [" + configuration.getName() + "] not able to freeze session because "
                                + "no session address has been configured for this task.";
                        LOGGER.severe(logErr);
                        if (instance != null) {
                            instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                    BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                        }
                    }
                } else {
                    String logErr = "Scheduled task [" + configuration.getName() + "] not able to freeze session because "
                            + "no session address has been configured for this task.";
                    LOGGER.severe(logErr);
                    if (instance != null) {
                        instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                    }
                }
            } else {
                if (ProcessStatus.ACTIVE.equals(coordinator.getProcessStatus())) {
                    coordinator.execute(new Command(CommandType.Freeze));
                } else {
                    String logErr = "Scheduled task [" + configuration.getName() + "] not able to freeze session ["
                            + coordinator.getSessionAddress() + "].";
                    LOGGER.severe(logErr);
                    if (instance != null) {
                        instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                    }
                }
            }
        } else {
            String logErr = "Instance not set for task [" + configuration.getName() + "].";
            LOGGER.severe(logErr);
        }
    }

    private void thawSession() {
        if (instance != null) {
            if (coordinator == null) {
                if (configuration.getTaskParams() != null && configuration.getTaskParams().length > 0) {
                    String cpty = configuration.getParameter(ScheduleFieldName.CptyAddress);
                    String sess = configuration.getParameter(ScheduleFieldName.SessAddress);
                    if (cpty != null && sess != null) {
                        SessionCoordinator sessionCoordinator = instance.getSessionCoordinator(cpty, sess);
                        if (sessionCoordinator != null) {
                            if (ProcessStatus.FROZEN.equals(sessionCoordinator.getProcessStatus())) {
                                sessionCoordinator.execute(new Command(CommandType.Thaw));
                            } else {
                                String logErr = "Scheduled task [" + configuration.getName() + "] not able to thaw session ["
                                        + sessionCoordinator.getSessionAddress() + "] in wrong state.";
                                LOGGER.severe(logErr);
                                if (instance != null) {
                                    instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                            BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                                }
                            }
                        } else {
                            String logErr = "Scheduled task [" + configuration.getName() + "] not able to thaw session because no "
                                    + "session exists for address [" + cpty + ":" + sess + "].";
                            LOGGER.severe(logErr);
                            if (instance != null) {
                                instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                        BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                            }
                        }
                    } else {
                        String logErr = "Scheduled task [" + configuration.getName() + "] not able to thaw session because "
                                + "no session address has been configured for this task.";
                        LOGGER.severe(logErr);
                        if (instance != null) {
                            instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                    BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                        }
                    }
                } else {
                    String logErr = "Scheduled task [" + configuration.getName() + "] not able to thaw session because "
                            + "no session address has been configured for this task.";
                    LOGGER.severe(logErr);
                    if (instance != null) {
                        instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                    }
                }
            } else {
                if (ProcessStatus.FROZEN.equals(coordinator.getProcessStatus())) {
                    coordinator.execute(new Command(CommandType.Thaw));
                } else {
                    String logErr = "Scheduled task [" + configuration.getName() + "] not able to thaw session ["
                            + coordinator.getSessionAddress() + "].";
                    LOGGER.severe(logErr);
                    if (instance != null) {
                        instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                    }
                }
            }
        } else {
            String logErr = "Instance not set for task [" + configuration.getName() + "].";
            LOGGER.severe(logErr);
        }
    }

    private void startSession() {
        if (instance != null) {
            if (configuration.getTaskParams() != null && configuration.getTaskParams().length > 0) {
                String cpty = configuration.getParameter(ScheduleFieldName.CptyAddress);
                String sess = configuration.getParameter(ScheduleFieldName.SessAddress);
                if (cpty != null && sess != null) {
                    try {
                        instance.startSession(cpty, sess);
                    } catch (ConfigurationException ex) {
                        String logErr = "Scheduled task [" + configuration.getName() + "] not able to start session ["
                                + cpty + ":" + sess + "].";
                        LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{logErr, ex.getMessage()});
                        if (instance != null) {
                            instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                    BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, ex)));
                        }
                    }
                } else {
                    String logErr = "Scheduled task [" + configuration.getName() + "] not able to start session because "
                            + "no session address has been configured for this task.";
                    LOGGER.severe(logErr);
                    if (instance != null) {
                        instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                    }
                }
            } else {
                String logErr = "Scheduled task [" + configuration.getName() + "] not able to start session because "
                        + "no session address has been configured for this task.";
                LOGGER.severe(logErr);
                if (instance != null) {
                    instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                            BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                }
            }
        } else {
            String logErr = "Instance not set for task [" + configuration.getName() + "].";
            LOGGER.severe(logErr);
        }
    }

    private void stopSession() {
        if (instance != null) {
            if (coordinator == null) {
                if (configuration.getTaskParams() != null && configuration.getTaskParams().length > 0) {
                    String cpty = configuration.getParameter(ScheduleFieldName.CptyAddress);
                    String sess = configuration.getParameter(ScheduleFieldName.SessAddress);
                    if (cpty != null && sess != null) {
                        try {
                            instance.stopSession(cpty, sess);
                        } catch (ProtocolException ex) {
                            String logErr = "Scheduled task [" + configuration.getName() + "] failed to start. Error was: " + ex.getMessage();
                            LOGGER.severe(logErr);
                            if (instance != null) {
                                instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                        BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                            }
                        }
                    } else {
                        String logErr = "Scheduled task [" + configuration.getName() + "] not able to start session because "
                                + "no session address has been configured for this task.";
                        LOGGER.severe(logErr);
                        if (instance != null) {
                            instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                    BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                        }
                    }
                } else {
                    String logErr = "Scheduled task [" + configuration.getName() + "] not able to start session because "
                            + "no session address has been configured for this task.";
                    LOGGER.severe(logErr);
                    if (instance != null) {
                        instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                    }
                }
            } else {
                if (!ProcessStatus.SHUTDOWN.equals(coordinator.getProcessStatus()) && !ProcessStatus.STOPPED.equals(coordinator.getProcessStatus())) {
                    coordinator.execute(new Command(CommandType.Shutdown));
                } else {
                    String logErr = "Scheduled task [" + configuration.getName() + "] not able to freeze session ["
                            + coordinator.getSessionAddress() + "].";
                    LOGGER.severe(logErr);
                    if (instance != null) {
                        instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                    }
                }
            }
        } else {
            String logErr = "Instance not set for task [" + configuration.getName() + "].";
            LOGGER.severe(logErr);
        }
    }

    private void connectSession() {
        if (instance != null) {
            if (coordinator == null) {
                if (configuration.getTaskParams() != null && configuration.getTaskParams().length > 0) {
                    String cpty = configuration.getParameter(ScheduleFieldName.CptyAddress);
                    String sess = configuration.getParameter(ScheduleFieldName.SessAddress);
                    if (cpty != null && sess != null) {
                        SessionCoordinator sessionCoordinator = instance.getSessionCoordinator(cpty, sess);
                        if (sessionCoordinator != null) {
                            sessionCoordinator.execute(new Command(CommandType.ConnectTransport));
                        } else {
                            String logErr = "Scheduled task [" + configuration.getName() + "] not able to connect session because no "
                                    + "session exists for address [" + cpty + ":" + sess + "].";
                            LOGGER.severe(logErr);
                            if (instance != null) {
                                instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                        BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                            }
                        }
                    } else {
                        String logErr = "Scheduled task [" + configuration.getName() + "] not able to connect session because "
                                + "no session address has been configured for this task.";
                        LOGGER.severe(logErr);
                        if (instance != null) {
                            instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                    BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                        }
                    }
                } else {
                    String logErr = "Scheduled task [" + configuration.getName() + "] not able to connect session because "
                            + "no configuration parameters are configured for this task.";
                    LOGGER.severe(logErr);
                    if (instance != null) {
                        instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                    }
                }
            } else {
                coordinator.execute(new Command(CommandType.ConnectTransport));
            }
        } else {
            String logErr = "Instance not set for task [" + configuration.getName() + "].";
            LOGGER.severe(logErr);
        }
    }

    private void disconnectSession() {
        if (instance != null) {
            if (coordinator == null) {
                if (configuration.getTaskParams() != null && configuration.getTaskParams().length > 0) {
                    String cpty = configuration.getParameter(ScheduleFieldName.CptyAddress);
                    String sess = configuration.getParameter(ScheduleFieldName.SessAddress);
                    if (cpty != null && sess != null) {
                        SessionCoordinator sessionCoordinator = instance.getSessionCoordinator(cpty, sess);
                        if (sessionCoordinator != null) {
                            sessionCoordinator.execute(new Command(CommandType.DisconnectTransport));
                        } else {
                            String logErr = "Scheduled task [" + configuration.getName() + "] not able to disconnect session because no "
                                    + "session exists for address [" + cpty + ":" + sess + "].";
                            LOGGER.severe(logErr);
                            if (instance != null) {
                                instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                        BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                            }
                        }
                    } else {
                        String logErr = "Scheduled task [" + configuration.getName() + "] not able to disconnect session because "
                                + "no session address has been configured for this task.";
                        LOGGER.severe(logErr);
                        if (instance != null) {
                            instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                    BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                        }
                    }
                } else {
                    String logErr = "Scheduled task [" + configuration.getName() + "] not able to disconnect session because "
                            + "no configuration parameters are configured for this task.";
                    LOGGER.severe(logErr);
                    if (instance != null) {
                        instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                    }
                }
            } else {
                coordinator.execute(new Command(CommandType.DisconnectTransport));
            }
        } else {
            String logErr = "Instance not set for task [" + configuration.getName() + "].";
            LOGGER.severe(logErr);
        }
    }

    private void shutdownInstance() {
        if (instance != null) {
            instance.shutdownEngine();
        } else {
            String logErr = "Instance not set for task [" + configuration.getName() + "].";
            LOGGER.severe(logErr);
        }
    }

    private void sessionReset() {
        if (instance != null) {
            try {
                if (coordinator == null) {
                    if (configuration.getTaskParams() != null && configuration.getTaskParams().length > 0) {
                        String cpty = configuration.getParameter(ScheduleFieldName.CptyAddress);
                        String sess = configuration.getParameter(ScheduleFieldName.SessAddress);
                        if (cpty != null && sess != null) {
                            SessionCoordinator sessionCoordinator = instance.getSessionCoordinator(cpty, sess);
                            if (sessionCoordinator != null) {
                                sessionCoordinator.sessionReset();
                            } else {
                                String logErr = "Scheduled task [" + configuration.getName() + "] not able to reset session because no "
                                        + "session exists for address [" + cpty + ":" + sess + "].";
                                LOGGER.severe(logErr);
                                if (instance != null) {
                                    instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                            BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                                }
                            }
                        } else {
                            String logErr = "Scheduled task [" + configuration.getName() + "] not able to reset session because "
                                    + "no session address has been configured for this task.";
                            LOGGER.severe(logErr);
                            if (instance != null) {
                                instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                        BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                            }
                        }
                    } else {
                        String logErr = "Scheduled task [" + configuration.getName() + "] not able to reset session because "
                                + "no configuration parameters are configured for this task.";
                        LOGGER.severe(logErr);
                        if (instance != null) {
                            instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                                    BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                        }
                    }
                } else {
                    coordinator.sessionReset();
                }
            } catch (ProtocolException ex) {
                String logErr = "Error resetting session for task [" + configuration.getName() + "].";
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[]{logErr, ex.getMessage()});
                if (instance != null) {
                    instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                            BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, ex)));
                }
            }
        } else {
            String logErr = "Instance not set for task [" + configuration.getName() + "].";
            LOGGER.severe(logErr);
        }
    }

    private boolean isAllowedToRun() {
        if (configuration.getDate().getYear() != null && !configuration.getDate().getYear().trim().isEmpty()) {
            if (!checkYear(configuration.getDate().getYear())) {
                return false;
            }
        }
        if (configuration.getDate().getMonth() != null && !configuration.getDate().getMonth().trim().isEmpty()) {
            if (!checkMonth(configuration.getDate().getMonth())) {
                return false;
            }
        }
        if (configuration.getDate().getDayOfMonth() != null && !configuration.getDate().getDayOfMonth().trim().isEmpty()) {
            if (!checkDayOfMonth(configuration.getDate().getDayOfMonth())) {
                return false;
            }
        } else if (configuration.getDate().getDayOfWeek() != null && !configuration.getDate().getDayOfWeek().trim().isEmpty()) {
            if (!checkDayOfWeek(configuration.getDate().getDayOfWeek())) {
                return false;
            }
        }

        return checkPublicHolidays(instance.getConfiguration().getScheduler().getCalendars());
    }

    private boolean checkYear(String year) {
        Calendar now = Calendar.getInstance();
        int y = now.get(Calendar.YEAR);
        if (year.indexOf("-") > 0) {
            String[] years = year.split("-");
            int y1 = Integer.valueOf(years[0]);
            int y2 = Integer.valueOf(years[1]);
            if (!(y >= y1 && y <= y2)) {
                return false;
            }
        } else {
            int y1 = Integer.valueOf(year);
            if (y != y1) {
                return false;
            }
        }

        return true;
    }

    private boolean checkMonth(String month) {
        Calendar now = Calendar.getInstance();
        int m = now.get(Calendar.MONTH);
        if (month.indexOf("-") > 0) {
            String[] months = month.split("-");
            int m1 = Integer.valueOf(months[0]);
            int m2 = Integer.valueOf(months[1]);
            if (!(m >= m1 - 1 && m <= m2 - 1)) {
                return false;
            }
        } else {
            int m1 = Integer.valueOf(month);
            if (m != m1 - 1) {
                return false;
            }
        }

        return true;
    }

    private boolean checkDayOfMonth(String dayOfMonth) {
        Calendar now = Calendar.getInstance();
        int d = now.get(Calendar.DAY_OF_MONTH);
        if (dayOfMonth.indexOf("-") > 0) {
            String[] days = dayOfMonth.split("-");
            int d1 = Integer.valueOf(days[0]);
            int d2 = Integer.valueOf(days[1]);
            if (!(d >= d1 && d <= d2)) {
                return false;
            }
        } else {
            int d1 = Integer.valueOf(dayOfMonth);
            if (d != d1) {
                return false;
            }
        }

        return true;
    }

    private boolean checkDayOfWeek(String dayOfWeek) {
        Calendar now = Calendar.getInstance();
        int d = now.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek.indexOf("-") > 0) {
            String[] days = dayOfWeek.split("-");
            int d1 = Integer.valueOf(days[0]);
            int d2 = Integer.valueOf(days[1]);
            if (!(d >= d1 && d <= d2)) {
                return false;
            }
        } else {
            int d1 = Integer.valueOf(dayOfWeek);
            if (d != d1) {
                return false;
            }
        }

        return true;
    }

    private boolean checkPublicHolidays(ScheduleCalendarInfo[] calendars) {
        CalendarRefInfo[] calRefs = configuration.getCalendars();
        if (calRefs != null && calRefs.length > 0) {
            if (calendars != null && calendars.length > 0) {
                for (CalendarRefInfo calRef : calRefs) {
                    for (ScheduleCalendarInfo calendar : calendars) {
                        if (calRef.getName().equals(calendar.getName())) {
                            if (checkCalendarExcludesMatch(calendar.getExcludes())) {
                                return false;
                            }
                        }
                    }
                }
            } else {
                String logErr = "Calendars are referenced in the schedule task but none is configired in instance configuration.";
                LOGGER.severe(logErr);
                if (instance != null) {
                    instance.getEventProcessor().onAlertEvent(new AlertEvent(this, Alert.createAlert(configuration.getName(), ComponentType.SchedulerTask.name(),
                            BaseSeverityType.RECOVERABLE, AlertCode.SCHED_TASK_EXEC_ERROR, logErr, null)));
                }
                return false;
            }
        }

        return true;
    }

    private boolean checkCalendarExcludesMatch(ScheduleDateInfo[] excludes) {
        if (excludes != null && excludes.length > 0) {
            for (ScheduleDateInfo date : excludes) {
                if (checkDayOfMonth(date.getDayOfMonth()) && checkMonth(date.getMonth()) && checkYear(date.getYear())) {
                    return true;
                }
            }
        }

        return false;
    }
}
