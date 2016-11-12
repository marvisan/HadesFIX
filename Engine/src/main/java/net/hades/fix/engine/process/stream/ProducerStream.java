/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.stream;

import java.util.logging.Logger;
import net.hades.fix.engine.config.model.HandlerDefInfo;
import net.hades.fix.engine.config.model.HandlerInfo;
import net.hades.fix.engine.config.model.HandlerRefInfo;

import net.hades.fix.engine.config.ConfigurationException;
import net.hades.fix.engine.process.session.SessionCoordinator;
import net.hades.fix.engine.config.model.StreamInfo;
import net.hades.fix.engine.handler.Handler;
import net.hades.fix.engine.process.transport.TcpWorker;

/**
 * Stream that produces (transmits) messages to a counterparty.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class ProducerStream extends Stream {

    private static final Logger Log = Logger.getLogger(ProducerStream.class.getName());

    public ProducerStream(SessionCoordinator sessionCoordinator, StreamInfo configuration, HandlerDefInfo[] handlerDefs) throws ConfigurationException {
       super(sessionCoordinator, configuration, handlerDefs);
    }

    public void setTransportHandler(TcpWorker tcpWorker) {
	String transportId = tcpWorker.getId();
	for (HandlerInfo config : configuration.getHandlers()) {
	    if (config.getNextHandlers() == null || config.getNextHandlers().length == 0) continue;
	    for (HandlerRefInfo ref : config.getNextHandlers()) {
		if (ref.getId().equals(transportId)) {
		    Handler handler = handlers.get(config.getId());
		    if (handler != null) {
			handler.addNextHandler(transportId, tcpWorker);
		    }
		}
	    }
	}
    }
}
