/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import net.hades.fix.engine.config.model.HandlerDefInfo;

import net.hades.fix.engine.config.ConfigurationException;
import net.hades.fix.engine.process.session.SessionCoordinator;
import net.hades.fix.engine.config.model.StreamInfo;
import net.hades.fix.engine.handler.Handler;
import net.hades.fix.engine.process.protocol.Protocol;

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

    public void addLastHandler(Protocol protocol) {
	List<Handler> leaves = findLeavesHandlers();
	if (!leaves.isEmpty()) {
	    for (Handler h : leaves) {
		h.addNextHandler(protocol.getId(), protocol);
	    }
	}
    }

    private List<Handler> findLeavesHandlers() {
	List<Handler> leaves = new ArrayList<>();
	for (Handler h1 : handlers.values()) {
	    String h1Id = h1.getId();
	    boolean found = false;
	    for (Handler h2 : handlers.values()) {
		for (Iterator it = h2.getNextHandlers().iterator(); it.hasNext();) {
		    Handler h3 = (Handler) it.next();
		    if (h2.getId().equals(h1Id)) {
			found = true;
			break;
		    }
		}
		if (found) {
		    break;
		}
	    }
	    if (!found) {
		leaves.add(h1);
	    }
	}
	return leaves;
    }
}
