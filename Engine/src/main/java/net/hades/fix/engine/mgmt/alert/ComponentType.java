/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ComponentType.java
 *
 * $Id: ComponentType.java,v 1.3 2011-03-27 08:04:48 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt.alert;

/**
 * Component type.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 09/03/2010
 */
public enum ComponentType {

    HadesEngine,
    MBeanServer,
    TCPClient,
    TCPServer,
    TCPServerWorker,
    FIXClient,
    FIXServer,
    Handler,
    ConsumerHandler,
    ProducerHandler,
    ConsumerProducerHandler,
    Flow,
    ConsumerFlow,
    ProducerFlow,
    Stream,
    ConsumerStream,
    ProducerStream,
    SessionCoordinator,
    ClientSessionCoordinator,
    ServerSessionCoordinator,
    Scheduler,
    SchedulerTask;

    private static final long serialVersionUID = 1L;
}
