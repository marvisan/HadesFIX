/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.hades.fix.engine.process.protocol.Protocol;

/**
 * Container for all protocol timers.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class TimersHolder {

    private static final Logger Log = Logger.getLogger(TimersHolder.class.getName());

    private final Timeouts timeouts;
    private final Protocol protocol;

    private ScheduledExecutorService timerExecutor;

    private HeartbeatTimeoutTimerTask heartbeatTimeoutTask;
    private ScheduledFuture<?> heartbeatTimeoutResult;

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

    public TimersHolder(Protocol protocol, Timeouts timeouts) {
	this.protocol = protocol;
	this.timeouts = timeouts;
	createtTimerTasks();
    }

    public LogoutTimeoutTimerTask getLogoutTimeoutTask() {
	return logoutTimeoutTask;
    }

    public synchronized void shutdown() {
	Log.log(Level.INFO, "Shutting down timers.");

	timerExecutor.shutdown();
	stopAllTasks();
	timerExecutor.shutdownNow();

	Log.log(Level.INFO, "Timers shut down.");
    }

    /**
     * Starts the heartbeat timer task.
     */
    public synchronized void startHeartbeatTimeoutTask() {
	if (heartbeatTimeoutResult != null) {
	    if (!heartbeatTimeoutResult.isCancelled() && !heartbeatTimeoutResult.isDone()) {
		heartbeatTimeoutResult.cancel(true);
	    }
	}
	if (timerExecutor != null && !timerExecutor.isShutdown()) {
	    heartbeatTimeoutResult = timerExecutor.schedule(heartbeatTimeoutTask, timeouts.getHtbtTimeout(), TimeUnit.SECONDS);
	    if (Log.isLoggable(Level.FINEST)) {
		Log.log(Level.FINEST, "Started heartbeat timer for [{0}] seconds.", timeouts.getHtbtTimeout());
	    }
	}
    }

    /**
     * Stop the heartbeat timer task.
     */
    public synchronized void stopHeartbeatTimeoutTask() {
	if (heartbeatTimeoutResult != null) {
	    boolean result = true;
	    if (!heartbeatTimeoutResult.isCancelled() && !heartbeatTimeoutResult.isDone()) {
		result = heartbeatTimeoutResult.cancel(true);
	    }
	    if (Log.isLoggable(Level.FINEST)) {
		Log.log(Level.FINEST, "Stopped heartbeat timeout timer for [{0}].", result);
	    }
	}
    }

    /**
     * Starts the timer task if the option is enabled.
     */
    public synchronized void startResendTimerTask() {
	if (resendTimeoutResult != null) {
	    if (!resendTimeoutResult.isCancelled() && !resendTimeoutResult.isDone()) {
		resendTimeoutResult.cancel(true);
	    }
	}
	if (timerExecutor != null && !timerExecutor.isShutdown()) {
	    resendTimeoutResult = timerExecutor.schedule(resendTimeoutTask, timeouts.getResendTimeout(), TimeUnit.MICROSECONDS);
	    if (Log.isLoggable(Level.FINEST)) {
		Log.log(Level.FINEST, "Started resend timer for [{0}] milliseconds.", timeouts.getResendTimeout());
	    }
	}
    }

    /**
     * Cancel the resend timeout task.
     */
    public synchronized void stopResendTimeoutTask() {
	if (resendTimeoutResult != null) {
	    boolean result = true;
	    if (!resendTimeoutResult.isCancelled() && !resendTimeoutResult.isDone()) {
		result = resendTimeoutResult.cancel(true);
	    }
	    resendTimeoutResult = null;
	    if (Log.isLoggable(Level.FINEST)) {
		Log.log(Level.FINEST, "Cancelled ResendTimeout task [{0}].", result);
	    }
	}
    }

    /**
     * Starts the logout timer task if the option is enabled.
     */
    public synchronized void startLogoutTimerTask() {
	if (logoutTimeoutResult != null) {
	    if (!logoutTimeoutResult.isCancelled() && !logoutTimeoutResult.isDone()) {
		logoutTimeoutResult.cancel(true);
	    }
	}
	if (timerExecutor != null && !timerExecutor.isShutdown()) {
	    logoutTimeoutResult = timerExecutor.schedule(logoutTimeoutTask, timeouts.getLogoutTimeout(), TimeUnit.SECONDS);
	    if (Log.isLoggable(Level.FINEST)) {
		Log.log(Level.FINEST, "Started logout timer for [{0}] seconds.", timeouts.getLogoutTimeout());
	    }
	}
    }

    /**
     * Resets the logout timer task.
     */
    public synchronized void stopLogoutTimeoutTask() {
	if (logoutTimeoutResult != null) {
	    boolean result = true;
	    if (!logoutTimeoutResult.isCancelled() && !logoutTimeoutResult.isDone()) {
		result = logoutTimeoutResult.cancel(true);
	    }
	    logoutTimeoutResult = null;
	    if (Log.isLoggable(Level.FINEST)) {
		Log.log(Level.FINEST, "Cancelled LogoutTimeout task [{0}].", result);
	    }
	}
    }

    /**
     * Starts the logon timer task if the option is enabled.
     */
    public synchronized void startLogonTimerTask() {
	if (logonTimeoutResult != null) {
	    if (!logonTimeoutResult.isCancelled() && !logonTimeoutResult.isDone()) {
		logonTimeoutResult.cancel(true);
	    }
	}
	if (timerExecutor != null && !timerExecutor.isShutdown()) {
	    logonTimeoutResult = timerExecutor.schedule(logonTimeoutTask, timeouts.getLogonTimeout(), TimeUnit.SECONDS);
	    if (Log.isLoggable(Level.FINEST)) {
		Log.log(Level.FINEST, "Started logon timer for [{0}] seconds.", timeouts.getLogonTimeout());
	    }
	}
    }

    /**
     * Resets the logon timer task.
     */
    public synchronized void stopLogonTimeoutTask() {
	if (logonTimeoutResult != null) {
	    boolean result = true;
	    if (!logonTimeoutResult.isCancelled() && !logonTimeoutResult.isDone()) {
		result = logonTimeoutResult.cancel(true);
	    }
	    logonTimeoutResult = null;
	    if (Log.isLoggable(Level.FINEST)) {
		Log.log(Level.FINEST, "Cancelled LogonTimeout task [{0}].", result);
	    }
	}
    }

    /**
     * Starts the test request timer task if the option is enabled.
     */
    public synchronized void startTestRequestTimeoutTask() {
	if (testRequestTimeoutResult != null) {
	    if (!testRequestTimeoutResult.isCancelled() && !testRequestTimeoutResult.isDone()) {
		testRequestTimeoutResult.cancel(true);
	    }
	}
	if (timerExecutor != null && !timerExecutor.isShutdown()) {
	    testRequestTimeoutResult = timerExecutor.schedule(testRequestTimeoutTask, timeouts.getTestRequestTimeout(), TimeUnit.SECONDS);
	    if (Log.isLoggable(Level.FINEST)) {
		Log.log(Level.FINEST, "Started test request timer for [{0}] seconds.", timeouts.getTestRequestTimeout());
	    }
	}
    }

    /**
     * Resets the test request timer task.
     */
    public synchronized void stopTestRequestTimeoutTask() {
	if (testRequestTimeoutResult != null) {
	    boolean result = true;
	    if (!testRequestTimeoutResult.isCancelled() && !testRequestTimeoutResult.isDone()) {
		result = testRequestTimeoutResult.cancel(true);
	    }
	    testRequestTimeoutResult = null;
	    if (Log.isLoggable(Level.FINEST)) {
		Log.log(Level.FINEST, "Cancelled TestRequestTimeout task [{0}].", result);
	    }
	}
    }

    /**
     * Starts the input timer task if the option is enabled.
     */
    public synchronized void startInputTimeoutTask() {
	if (inputTimeoutResult != null) {
	    if (!inputTimeoutResult.isCancelled() && !inputTimeoutResult.isDone()) {
		inputTimeoutResult.cancel(true);
	    }
	}
	if (timerExecutor != null && !timerExecutor.isShutdown()) {
	    inputTimeoutResult = timerExecutor.schedule(inputTimeoutTimerTask, timeouts.getHtbtOffset(), TimeUnit.SECONDS);
	    if (Log.isLoggable(Level.FINEST)) {
		Log.log(Level.FINEST, "Started input timer for [{0}] seconds.", timeouts.getHtbtOffset());
	    }
	}
    }

    /**
     * Resets the input timeout timer task.
     */
    public synchronized void stopInputTimeoutTask() {
	if (inputTimeoutResult != null) {
	    boolean result = true;
	    if (!inputTimeoutResult.isCancelled() && !inputTimeoutResult.isDone()) {
		result = inputTimeoutResult.cancel(true);
	    }
	    inputTimeoutResult = null;
	    if (Log.isLoggable(Level.FINEST)) {
		Log.log(Level.FINEST, "Cancelled InputTimeout task [{0}].", result);
	    }
	}
    }

    public void stopAllTasks() {
	stopResendTimeoutTask();
	stopLogoutTimeoutTask();
	stopLogonTimeoutTask();
	stopHeartbeatTimeoutTask();
	stopTestRequestTimeoutTask();
	stopInputTimeoutTask();
    }

    private void createtTimerTasks() {
	timerExecutor = Executors.newScheduledThreadPool(100);

	heartbeatTimeoutTask = new HeartbeatTimeoutTimerTask(protocol);
	if (timeouts.isEnableResendTimeout()) {
	    resendTimeoutTask = new ResendTimeoutTimerTask(protocol);
	}
	logoutTimeoutTask = new LogoutTimeoutTimerTask(protocol);
	logonTimeoutTask = new LogonTimeoutTimerTask(protocol);
	testRequestTimeoutTask = new TestRequestTimeoutTask(protocol);
	inputTimeoutTimerTask = new InputTimeoutTimerTask(protocol);

	Log.log(Level.INFO, "FIX timer tasks [{0}] started.", timerExecutor.toString());
    }
}
