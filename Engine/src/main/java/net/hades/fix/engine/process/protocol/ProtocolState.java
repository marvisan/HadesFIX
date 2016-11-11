/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol;

/**
 * State of the protocol client/server.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public enum ProtocolState {

    INITIALISED,
    LOGGEDON,
    LOGGEDOUT;

    private static final long serialVersionUID = 1L;

}
