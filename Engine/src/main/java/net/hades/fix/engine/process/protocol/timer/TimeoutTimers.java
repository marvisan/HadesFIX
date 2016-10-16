/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TimeoutTimers.java
 *
 * $Id: TimeoutTimers.java,v 1.1 2011-04-03 08:00:06 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol.timer;

import net.hades.fix.engine.process.protocol.state.StateProcessor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Timers thread.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public class TimeoutTimers {

    private static final Logger LOGGER = Logger.getLogger(TimeoutTimers.class.getName());

    private StateProcessor stateProcessor;

    private Timeouts timeouts;

    private ScheduledExecutorService timer;

    private HeartbeatTimeoutTimerTask heartbeatTask;

    private ScheduledFuture<?> heartbeatResult;

    private ResendTimeoutTimerTask resendTimeoutTask;

    private ScheduledFuture<?> resendTimeoutResult;

    private LogoutTimeoutTimerTask logoutTimeoutTask;

    private ScheduledFuture<?> logoutTimeoutResult;

    private LogonTimeoutTimerTask logonTimeoutTask;

    private ScheduledFuture<?> logonTimeoutResult;

    private TestRequestTimeoutTask testRequestTimeoutTask;

    private ScheduledFuture<?> testRequestTimeoutResult;

    private InputTimeoutTimerTask inputTimeoutTimerTask;

    private ScheduledFuture<?> inputTimeoutResult;

    public TimeoutTimers(StateProcessor stateProcessor, Timeouts timeouts) {
        this.stateProcessor = stateProcessor;
        this.timeouts = timeouts;
        startTimerTasks();
    }

    public LogoutTimeoutTimerTask getLogoutTimeoutTask() {
        return logoutTimeoutTask;
    }

    public synchronized void shutdown() {
        LOGGER.log(Level.INFO, "Shutting down timers.");

        resetAllTasks();
        timer.shutdownNow();

        LOGGER.log(Level.INFO, "Timers shut down.");
    }

    /**
     * Starts the heartbeat timer task.
     */
    public synchronized void startHeartbeatTask() {
        resetHeartbeatTask();
        if (timer != null && !timer.isShutdown()) {
            heartbeatResult = timer.schedule(heartbeatTask, timeouts.getHtbtTimeout(), TimeUnit.SECONDS);

            if (LOGGER.isLoggable(Level.FINEST)) {
                LOGGER.log(Level.FINEST, "Started heartbeat timer for [{0}] seconds.", timeouts.getHtbtTimeout());
            }
        }
    }

    /**
     * Starts the timer task if the option is enabled.
     */
    public synchronized void startResendTimerTask() {
        resetResendTimeoutTask();
        if (timer != null && !timer.isShutdown()) {
            resendTimeoutResult = timer.schedule(resendTimeoutTask, timeouts.getResendTimeout(), TimeUnit.MICROSECONDS);

            if (LOGGER.isLoggable(Level.FINEST)) {
                LOGGER.log(Level.FINEST, "Started resend timer for [{0}] milliseconds.", timeouts.getResendTimeout());
            }
        }
    }

    /**
     * Starts the logout timer task if the option is enabled.
     */
    public synchronized void startLogoutTimerTask() {
        resetLogoutTimeoutTask();
        if (timer != null && !timer.isShutdown()) {
            logoutTimeoutResult = timer.schedule(logoutTimeoutTask, timeouts.getLogoutTimeout(), TimeUnit.SECONDS);

            if (LOGGER.isLoggable(Level.FINEST)) {
                LOGGER.log(Level.FINEST, "Started logout timer for [{0}] seconds.", timeouts.getLogoutTimeout());
            }
        }
    }

    /**
     * Starts the logon timer task if the option is enabled.
     */
    public synchronized void startLogonTimerTask() {
        resetLogonTimeoutTask();
        if (timer != null && !timer.isShutdown()) {
            logonTimeoutResult = timer.schedule(logonTimeoutTask, timeouts.getLogonTimeout(), TimeUnit.SECONDS);

            if (LOGGER.isLoggable(Level.FINEST)) {
                LOGGER.log(Level.FINEST, "Started logon timer for [{0}] seconds.", timeouts.getLogonTimeout());
            }
        }
    }

    /**
     * Starts the test request timer task if the option is enabled.
     */
    public synchronized void startTestRequestTimeoutTask() {
        resetTestRequestTimeoutTask();
        if (timer != null && !timer.isShutdown()) {
            testRequestTimeoutResult = timer.schedule(testRequestTimeoutTask, timeouts.getTestRequestTimeout(), TimeUnit.SECONDS);

            if (LOGGER.isLoggable(Level.FINEST)) {
                LOGGER.log(Level.FINEST, "Started test request timer for [{0}] seconds.", timeouts.getTestRequestTimeout());
            }
        }
    }

    /**
     * Starts the input timer task if the option is enabled.
     */
    public synchronized void startInputTimeoutTask() {
        resetInputTimeoutTask();
        if (timer != null && !timer.isShutdown()) {
            inputTimeoutResult = timer.schedule(inputTimeoutTimerTask, timeouts.getHtbtOffset(), TimeUnit.SECONDS);

            if (LOGGER.isLoggable(Level.FINEST)) {
                LOGGER.log(Level.FINEST, "Started input timer for [{0}] seconds.", timeouts.getHtbtOffset());
            }
        }
    }

    /**
     * Cancel the resend timeout task.
     */
    public synchronized void resetResendTimeoutTask() {
        if (resendTimeoutResult != null) {
            boolean result = resendTimeoutResult.cancel(true);
            resendTimeoutResult = null;

            if (LOGGER.isLoggable(Level.FINEST)) {
                LOGGER.log(Level.FINEST, "Cancelled ResendTimeout task [{0}].", result);
            }
        }
    }

    /**
     * Resets the logout timer task.
     */
    public synchronized void resetLogoutTimeoutTask() {
        if (logoutTimeoutResult != null) {
            boolean result = logoutTimeoutResult.cancel(true);
            logoutTimeoutResult = null;

            if (LOGGER.isLoggable(Level.FINEST)) {
                LOGGER.log(Level.FINEST, "Cancelled LogoutTimeout task [{0}].", result);
            }
        }
    }

    /**
     * Resets the logon timer task.
     */
    public synchronized void resetLogonTimeoutTask() {
        if (logonTimeoutResult != null) {
            boolean result = logonTimeoutResult.cancel(true);
            logonTimeoutResult = null;

            if (LOGGER.isLoggable(Level.FINEST)) {
                LOGGER.log(Level.FINEST, "Cancelled LogonTimeout task [{0}].", result);
            }
        }
    }

    /**
     * Resets the heartbeat timer task.
     */
    public synchronized void resetHeartbeatTask() {
        if (heartbeatResult != null) {
            boolean result = heartbeatResult.cancel(true);
            heartbeatResult = null;

            if (LOGGER.isLoggable(Level.FINEST)) {
                LOGGER.log(Level.FINEST, "Cancelled Heartbeat task [{0}].", result);
            }
        }
    }

    /**
     * Resets the test request timer task.
     */
    public synchronized void resetTestRequestTimeoutTask() {
        if (testRequestTimeoutResult != null) {
            boolean result = testRequestTimeoutResult.cancel(true);
            testRequestTimeoutResult = null;

            if (LOGGER.isLoggable(Level.FINEST)) {
                LOGGER.log(Level.FINEST, "Cancelled TestRequestTimeout task [{0}].", result);
            }
        }
    }

    /**
     * Resets the input timeout timer task.
     */
    public synchronized void resetInputTimeoutTask() {
        if (inputTimeoutResult != null) {
            boolean result = inputTimeoutResult.cancel(true);
            inputTimeoutResult = null;

            if (LOGGER.isLoggable(Level.FINEST)) {
                LOGGER.log(Level.FINEST, "Cancelled InputTimeout task [{0}].", result);
            }
        }
    }

    /**
     * Resets all the task weather or not they where started.
     */
    public synchronized void resetAllTasks() {
        resetResendTimeoutTask();
        resetLogoutTimeoutTask();
        resetLogonTimeoutTask();
        resetHeartbeatTask();
        resetTestRequestTimeoutTask();
        resetInputTimeoutTask();
    }

    public void startTimerTasks() {
        timer = Executors.newScheduledThreadPool(10);
        heartbeatTask = new HeartbeatTimeoutTimerTask(stateProcessor);
        if (timeouts.isEnableResendTimeout()) {
            resendTimeoutTask = new ResendTimeoutTimerTask(stateProcessor);
        }
        logoutTimeoutTask = new LogoutTimeoutTimerTask(stateProcessor);
        logonTimeoutTask = new LogonTimeoutTimerTask(stateProcessor);
        testRequestTimeoutTask = new TestRequestTimeoutTask(stateProcessor);
        inputTimeoutTimerTask = new InputTimeoutTimerTask(stateProcessor);

        LOGGER.log(Level.INFO, "FIX timer tasks [{0}] started.", timer.toString());
    }
}
