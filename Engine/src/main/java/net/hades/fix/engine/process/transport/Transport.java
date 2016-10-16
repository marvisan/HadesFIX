/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Transport.java
 *
 * $Id: Transport.java,v 1.8 2011-04-07 09:57:51 vrotaru Exp $
 */
package net.hades.fix.engine.process.transport;

import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.process.Reportable;
import net.hades.fix.engine.process.Commandable;
import net.hades.fix.engine.process.Manageable;

/**
 * Contract to be fulfilled by a transport client or server.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 */
public interface Transport extends Reportable, Manageable, Commandable {

    /**
     * Initialise transport
     */
    void initialise() throws ConfigurationException;

    /**
     * Reads a FIX message from the buffer. This method blocks until
     * a new message is available.
     *
     * @return FIX message as a byte array
     * @throws InterruptedException
     */
    byte[] readMessage() throws InterruptedException;

    /**
     * Reads a FIX message from the buffer. This method blocks until
     * a new message is available or timeout is reached.
     *
     * @param timeout timeout interval in seconds
     * @return FIX message as a byte array
     * @throws InterruptedException
     */
    byte[] readMessage(int timeout) throws InterruptedException;

    /**
     * Write a message to the transport TX queue.
     * 
     * @param message FIX message as a byte array
     * @throws InterruptedException
     */
    void writeMessage(byte[] message) throws InterruptedException;

    /**
     * Returns true if the client session is connected for the client side/or a client is connected to the server
     * for server side.
     * @return flag
     */
    boolean isConnected();
}
