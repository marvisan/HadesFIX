/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.scheduler;

import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.config.model.ScheduleTaskInfo;
import net.hades.fix.engine.config.model.SchedulerInfo;
import net.hades.fix.engine.process.session.SessionCoordinator;

/**
 * Class used to manage time related sessions events.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 */
public class Scheduler extends Thread {

    private static final Logger LOGGER = Logger.getLogger(Scheduler.class.getName());

    private static final String COMPONENT_NAME = "SCHEDULER";

    private HadesInstance instance;

    private SchedulerInfo configuration;

    private ScheduledThreadPoolExecutor timer;

    private CountDownLatch shutdownSignal;

    private ConcurrentSkipListMap<String, ScheduledFuture<?>> tasks;

    public Scheduler(HadesInstance instance, SchedulerInfo configuration) {
        super(configuration.getId() == null ? COMPONENT_NAME : configuration.getId());
        this.instance = instance;
        this.configuration = configuration;
        tasks = new ConcurrentSkipListMap<String, ScheduledFuture<?>>();
        timer = new ScheduledThreadPoolExecutor(10);
        shutdownSignal = new CountDownLatch(1);
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO, "Starting scheduler thread [{0}].", getName());

        try {
            shutdownSignal.await();
        } catch (InterruptedException ex) {
            LOGGER.log(Level.WARNING, "Scheduler thread interrupted. Error was : {0}", ex.toString());
        }

        LOGGER.log(Level.INFO, "Scheduler thread [{0}] stopped.", getName());
    }

    public void scheduleTask(ScheduleTaskInfo configuration) {
        scheduleTask(null, configuration);
    }

    public void scheduleTask(SessionCoordinator coordinator, ScheduleTaskInfo configuration) {
        ScheduledTask task = new ScheduledTask(instance, coordinator, configuration);
        ScheduledFuture<?> taskResult = timer.scheduleAtFixedRate(task, getInitialDelay(configuration), 24 * 60 * 60, TimeUnit.SECONDS);
        tasks.put(configuration.getName(), taskResult);

        LOGGER.log(Level.INFO, "Task [{0}] scheduled to run in [{1}] minutes",
                new Object[]{configuration.getName(), taskResult.getDelay(TimeUnit.MINUTES)});
    }

    public void cancelTask(String taskName) {
        BlockingQueue<Runnable> queuedTasks = timer.getQueue();
        for (String name : tasks.keySet()) {
            if (name.equals(taskName)) {
                ScheduledFuture<?> result = tasks.get(name);
                for (Iterator<Runnable> it = queuedTasks.iterator(); it.hasNext();) {
                    ScheduledFuture<?> runningResult = (ScheduledFuture<?>) it.next();
                    if (runningResult.compareTo(result) == 0) {
                        if (!runningResult.isCancelled() && runningResult.isDone()) {
                            runningResult.cancel(true);
                        }
                    }
                }
            }
        }
    }

    public SchedulerInfo getConfiguration() {
        return configuration;
    }

    public void shutdown() {
        timer.shutdownNow();
        shutdownSignal.countDown();
    }

    private long getInitialDelay(ScheduleTaskInfo configuration) {
        long result;

        Calendar now = Calendar.getInstance();
        now.set(Calendar.MILLISECOND, 0);
        long nowSec = now.getTimeInMillis() / 1000;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(configuration.getDate().getHour()));
        cal.set(Calendar.MINUTE, Integer.valueOf(configuration.getDate().getMinute()));
        cal.set(Calendar.MILLISECOND, 0);
        long schedSec = cal.getTimeInMillis() / 1000;
        if (schedSec < nowSec) {
            result = 24 * 60 * 60 - (nowSec - schedSec);
        } else {
            result = schedSec - nowSec;
        }

        return result;
    }
}
