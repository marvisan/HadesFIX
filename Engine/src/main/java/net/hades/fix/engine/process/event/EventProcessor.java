/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EventProcessor.java
 *
 * $Id: EventProcessor.java,v 1.5 2011-03-20 07:40:38 vrotaru Exp $
 */
package net.hades.fix.engine.process.event;

import java.util.Collection;
import java.util.EventObject;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.commons.thread.ThreadUtil;
import net.hades.fix.engine.process.Advisable;
import net.hades.fix.engine.process.Reportable;
import net.hades.fix.engine.process.listener.AlertListener;
import net.hades.fix.engine.process.listener.LifeCycleListener;
import net.hades.fix.engine.process.listener.MessageListener;
import net.hades.fix.engine.process.session.SessionCoordinator;

/**
 * Generic generic processor.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 */
public abstract class EventProcessor extends Thread implements Advisable, Reportable {

    private static final Logger LOGGER = Logger.getLogger(EventProcessor.class.getName());

    protected static final int EVENT_QUEUE_SIZE  = 3000;

    protected Collection<AlertListener> alertListeners;
    protected Collection<LifeCycleListener> lifeCycleListeners;
    protected Collection<MessageListener> messageListeners;
    
    protected BlockingQueue<EventObject> eventsQueue;

    protected SessionCoordinator sessionCoordinator;

    private volatile boolean shutdown;
    private volatile boolean active;

    public EventProcessor(String name) {
        super(name + "_EVENT_PROCESSOR");
        eventsQueue = new ArrayBlockingQueue<EventObject>(EVENT_QUEUE_SIZE);

        alertListeners = new ConcurrentLinkedQueue<AlertListener>();
        lifeCycleListeners = new ConcurrentLinkedQueue<LifeCycleListener>();
        messageListeners = new ConcurrentLinkedQueue<MessageListener>();
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Event processor thread [{0}] started.", getName());

        while (!shutdown) {
            active = true;
            try {
                EventObject event = eventsQueue.take();
                if (event instanceof AlertEvent) {
                    processAlertEvent((AlertEvent) event);
                } else if (event instanceof LifeCycleEvent) {
                    processLifeCycleEvent((LifeCycleEvent) event);
                } else if (event instanceof MessageEvent) {
                    processMessageEvent((MessageEvent) event);
                }
            } catch (InterruptedException ex) {
                LOGGER.log(Level.WARNING, "Event processor thread {0} interrupted.", getName());
            }
        }

        active = false;

        LOGGER.log(Level.INFO, "Event processor thread [{0}] shutdown.", getName());
    }

    public void shutdown() {
        // wait for events to be evacuated
        while (!eventsQueue.isEmpty()) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
        shutdown = true;
    }

    @Override
    public void addLifeCycleListener(LifeCycleListener listener) {
        if (listener != null) {
            lifeCycleListeners.add(listener);
        }
    }

    @Override
    public void addAlertListener(AlertListener listener) {
        if (listener != null) {
            alertListeners.add(listener);
        }
    }

    @Override
    public void addMessageListener(MessageListener listener) {
        if (listener != null) {
            messageListeners.add(listener);
        }
    }

    @Override
    public void onAlertEvent(AlertEvent event) {
        if (event == null) {
            return;
        }
        while (!eventsQueue.offer(event)) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }

    @Override
    public void onLifeCycleEvent(LifeCycleEvent event) {
        if (event == null) {
            return;
        }
        while (!eventsQueue.offer(event)) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }

    @Override
    public void onMessageEvent(MessageEvent event) {
        if (event == null) {
            return;
        }
        while (!eventsQueue.offer(event)) {
            if (!ThreadUtil.sleep(1)) {
                break;
            }
        }
    }

    public boolean isActive() {
        return active;
    }

    protected abstract void processAlertEvent(AlertEvent event);

    protected abstract void processLifeCycleEvent(LifeCycleEvent event);

    protected abstract void processMessageEvent(MessageEvent event);
}
