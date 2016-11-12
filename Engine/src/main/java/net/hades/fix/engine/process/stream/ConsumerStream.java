/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.stream;

import net.hades.fix.engine.config.ConfigurationException;
import net.hades.fix.engine.process.session.SessionCoordinator;
import java.util.logging.Logger;
import net.hades.fix.engine.config.model.HandlerDefInfo;
import net.hades.fix.engine.config.model.StreamInfo;
import net.hades.fix.engine.handler.Handler;

/**
 * Stream that consumes (receives) messages from a counterparty.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class ConsumerStream extends Stream {

    private static final Logger LOGGER = Logger.getLogger(ConsumerStream.class.getName());

    public ConsumerStream(SessionCoordinator sessionCoordinator, StreamInfo configuration, HandlerDefInfo[] handlerDefs) throws ConfigurationException {
        super(sessionCoordinator, configuration, handlerDefs);
    }
 
}
