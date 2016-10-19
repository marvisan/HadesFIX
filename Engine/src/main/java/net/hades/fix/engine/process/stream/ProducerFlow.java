/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Flow.java
 *
 * $Id: ProducerFlow.java,v 1.7 2010-09-11 09:31:26 vrotaru Exp $
 */
package net.hades.fix.engine.process.stream;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.engine.config.model.FlowInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.handler.HandlerException;
import net.hades.fix.engine.handler.ConsumerProducerHandler;
import net.hades.fix.engine.handler.Handler;
import net.hades.fix.engine.handler.ProducerHandler;
import net.hades.fix.engine.mgmt.alert.Alert;
import net.hades.fix.engine.mgmt.alert.AlertCode;
import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
import net.hades.fix.engine.mgmt.alert.ComponentType;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.message.Message;

/**
 * This class creates a flow in a stream and manages the enclosed handlers.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 */
public class ProducerFlow extends Flow {
 
    private static final Logger LOGGER = Logger.getLogger(ProducerFlow.class.getName());

    public ProducerFlow(Stream stream, FlowInfo config) throws ConfigurationException {
        super(stream,config);

        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "ctor [{0}] called.", configuration.getId());
        }
    }

    @Override
    public void forward(String handlerId, Message message) throws HandlerException {
        try {
            ProducerHandler handler = (ProducerHandler) getNextEnabledHandler(handlerId, FlowType.Producer);
            if (handler != null) {
                handler.produce(message);
            } else {
                ((ProducerStream) stream).write(message);
            }
        } catch (Exception ex) {
            if (ex instanceof HandlerException) {
                throw (HandlerException) ex;
            } else {
                String logMsg = "Unexpected error occurred in the handler with id [" + handlerId + "] when processing message. "
                        + "The message flow id [" + getFlowConfig().getId() + "] will discard the message : "
                        + message.toString();
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] { logMsg, ExceptionUtil.getStackTrace(ex) });
                stream.getEventProcessor().onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(stream.getName(), ComponentType.ProducerHandler.toString(),
                        BaseSeverityType.RECOVERABLE, AlertCode.HANDLER_PROCESSING_ERROR, logMsg, ex)));
            }
        }
    }

    @Override
    public void start() {
        LOGGER.log(Level.INFO, "Starting producer flow [{0}].", configuration.getId());

        for (Iterator<Handler> iterator = handlers.descendingIterator(); iterator.hasNext();) {
            iterator.next().init();
        }

        LOGGER.log(Level.INFO, "Producer flow [{0}] started.", configuration.getId());
    }

    @Override
    public void stop() {
        LOGGER.log(Level.INFO, "Stopping producer flow [{0}].", configuration.getId());

        for (Iterator<Handler> iterator = handlers.descendingIterator(); iterator.hasNext();) {
            iterator.next().close();
        }

        LOGGER.log(Level.INFO, "Producer flow [{0}] stopped.", configuration.getId());
    }

    @Override
    protected void checkHandlerClass(Handler handler) throws ConfigurationException {
        if (!(ProducerHandler.class.isAssignableFrom(handler.getClass()) ||
            ConsumerProducerHandler.class.isAssignableFrom(handler.getClass()))) {
            String errMsg = "Handler [" + handler.getName() + "] in a Producer flow must implement [ProducerHandler] or "
                    + "[ConsumerProducerHandler] interfaces.";
            LOGGER.severe(errMsg);
            throw new ConfigurationException(errMsg);
        }
    }
}
