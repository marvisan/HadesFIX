/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CommandType.java
 *
 * $Id: CommandType.java,v 1.1 2011-04-07 09:57:51 vrotaru Exp $
 */
package net.hades.fix.engine.process.command;

/**
 * Command type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public enum CommandType {

    Startup,
    Block,
    Unblock,
    Freeze,
    Thaw,
    Reset,
    ResetSequences,
    StartProtocol,
    StopTCPReader,
    DisconnectTransport,
    StopIncoming,
    TransportConnected,
    TransportDisconnected,
    ReconnectTransport,
    UnsetSessionCoordinator,
    SessionOpened,
    SessionRestarted,
    SetServerSessionTransport,
    Shutdown,
    ShutdownNow,
    ConnectTransport,
    StopSession;

    private static final long serialVersionUID = 1L;
}
