/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Stream.java
 *
 * $Id: Stream.java,v 1.33 2011-04-30 04:39:44 vrotaru Exp $
 */
package net.hades.fix.engine.process.stream;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.mgmt.data.Stats;
import net.hades.fix.engine.mgmt.data.StreamProcessData;
import net.hades.fix.engine.mgmt.data.StreamStats;
import net.hades.fix.engine.process.ManagedProcess;
import net.hades.fix.engine.process.command.Command;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.EventProcessor;
import net.hades.fix.engine.process.listener.AlertListener;
import net.hades.fix.engine.process.listener.LifeCycleListener;
import net.hades.fix.engine.process.protocol.Protocol;
import net.hades.fix.engine.process.session.Coordinable;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.data.ProcessData;
import net.hades.fix.engine.mgmt.data.ProcessStatus;
import net.hades.fix.engine.process.listener.MessageListener;
import net.hades.fix.engine.process.session.SessionCoordinator;

/**
 * Generic class for a message stream container.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.33 $
 */
public abstract class Stream {

    private static final Logger LOGGER = Logger.getLogger(Stream.class .getName());

    protected static final int MAX_NUM_COMMANDS                     = 5;

    protected Protocol protocol;
    protected SessionCoordinator sessionCoordinator;

    protected volatile boolean shutdown;

    protected EventProcessor eventProcessor;

    protected AtomicReference<ProcessStatus> processStatus;
    protected AtomicReference<StreamProcessData> mgmtData;
    protected AtomicReference<StreamStats> stats;

    protected BlockingDeque<Command> commandQueue;

    protected Stream(String name) {
        super(name);
        mgmtData = new AtomicReference<StreamProcessData>(new StreamProcessData());
        stats = new AtomicReference<StreamStats>(new StreamStats());
        processStatus = new AtomicReference<ProcessStatus>(ProcessStatus.INITIALISING);
        commandQueue = new LinkedBlockingDeque<Command>(MAX_NUM_COMMANDS);
    }

    protected abstract void initialise() throws ConfigurationException;
    
    protected abstract void startup();
    
    protected abstract void block();
    
    protected abstract void unblock();
    
    protected abstract void shutdown();
    
    protected abstract void shutdownNow();

    @Override
    public void execute(Command command) {
        try {
            commandQueue.put(command);
        } catch (InterruptedException ex) {
            String errMsg = "Stream thread [" + getName() + "] has been unexpectedly interrupted.";
            LOGGER.log(Level.WARNING, "{0} Error was : {1}", new Object[] { errMsg, ExceptionUtil.getStackTrace(ex) });
            eventProcessor.onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(getName(), ComponentType.Stream.toString(),
                        BaseSeverityType.WARNING, AlertCode.THREAD_INTERRUPTED, errMsg, ex)));
        }
    }

    @Override
    public void addLifeCycleListener(LifeCycleListener listener) {
        eventProcessor.addLifeCycleListener(listener);
    }

    @Override
    public void addAlertListener(AlertListener listener) {
        eventProcessor.addAlertListener(listener);
    }

    @Override
    public void addMessageListener(MessageListener listener) {
        eventProcessor.addMessageListener(listener);
    }

    @Override
    public Coordinable getSessionCoordinator() {
        return sessionCoordinator;
    }

    @Override
    public Stats getStats() {
        return stats.get();
    }

    @Override
    public ProcessData getMgmtData() {
        return mgmtData.get();
    }

    @Override
    public ProcessStatus getProcessStatus() {
        return processStatus.get();
    }

    @Override
    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus.set(processStatus);
    }

    @Override
    public String retrieveSessionAddress() {
        return sessionCoordinator.getSessionAddress().toString();
    }

    public EventProcessor getEventProcessor() {
        return eventProcessor;
    }

    protected abstract String getID();

    protected void processCommand(Command command) {
        if (command == null) {
            return;
        }
        switch (command.getCommandType()) {
            case Startup:
                startup();
                break;

            case Block:
                block();
                break;

            case Unblock:
                unblock();
                break;

            case Shutdown:
                shutdown();
                break;

            case ShutdownNow:
                shutdownNow();
                break;

            default:
                LOGGER.log(Level.SEVERE, "Command [{0}] not implemented for a Stream.", command.getCommandType().name());

        }
    }
}
