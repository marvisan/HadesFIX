/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process.session;

/**
 * Checks the health status of the session threads and stops the session if threads are in error or completed. Collects Handler statistics too.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class SessionSuperviserTimerTask implements Runnable {

    private final SessionCoordinator coordinator;

    public SessionSuperviserTimerTask(SessionCoordinator coordinator) {
	this.coordinator = coordinator;

    }

    @Override
    public void run() {
	//TODO check
    }

}
