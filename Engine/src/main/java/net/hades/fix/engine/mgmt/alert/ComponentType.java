/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.mgmt.alert;

/**
 * Component type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public enum ComponentType {

    HadesFIXEngine,
    MBeanServer,
    TCPClient,
    TCPServer,
    TCPServerWorker,
    FIXClient,
    FIXServer,
    Handler,
    Stream,
    SessionCoordinator,
    ClientSessionCoordinator,
    ServerSessionCoordinator,
    Scheduler,
    SchedulerTask;

    private static final long serialVersionUID = 1L;
}
