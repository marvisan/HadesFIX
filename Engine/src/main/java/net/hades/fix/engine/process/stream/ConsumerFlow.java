    /*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Flow.java
 *
 * $Id: ConsumerFlow.java,v 1.8 2010-09-11 09:31:26 vrotaru Exp $
 */
package net.hades.fix.engine.process.stream;

    import net.hades.fix.commons.exception.ExceptionUtil;
    import net.hades.fix.engine.config.model.FlowInfo;
    import net.hades.fix.engine.exception.ConfigurationException;
    import net.hades.fix.engine.handler.HandlerException;
    import net.hades.fix.engine.handler.ConsumerHandler;
    import net.hades.fix.engine.handler.ConsumerProducerHandler;
    import net.hades.fix.engine.handler.Handler;
    import net.hades.fix.engine.mgmt.alert.Alert;
    import net.hades.fix.engine.mgmt.alert.AlertCode;
    import net.hades.fix.engine.mgmt.alert.BaseSeverityType;
    import net.hades.fix.engine.mgmt.alert.ComponentType;
    import net.hades.fix.engine.process.event.AlertEvent;
    import net.hades.fix.message.Message;

    import java.util.logging.Level;
    import java.util.logging.Logger;

/**
 * This class creates a flow in a stream and manages the enclosed handlers.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.8 $
 */
public class ConsumerFlow extends Flow {

    private static final Logger LOGGER = Logger.getLogger(ConsumerFlow.class.getName());

    public ConsumerFlow(Stream stream, FlowInfo config) throws ConfigurationException {
        super(stream, config);
    }

    @Override
    public void forward(String handlerId, Message message) throws HandlerException {
        try {
            ConsumerHandler handler = (ConsumerHandler) getNextEnabledHandler(handlerId, FlowType.Consumer);
            if (handler != null) {
                handler.consume(message);
            }
        } catch (Exception ex) {
            if (ex instanceof HandlerException) {
                throw (HandlerException) ex;
            } else {
                String logMsg = "Unexpected error occurred in the handler with id [" + handlerId + "] when processing message. "
                        + "The consumer flow id [" + getFlowConfig().getId() + "] will discard the message : "
                        + message.toString();
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] { logMsg, ExceptionUtil.getStackTrace(ex) });
                stream.getEventProcessor().onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(stream.getName(), ComponentType.ConsumerHandler.toString(),
                        BaseSeverityType.RECOVERABLE, AlertCode.HANDLER_PROCESSING_ERROR, logMsg, ex)));
            }
        }
    }

    public void process(Message message) throws HandlerException {
        ConsumerHandler chandler = null;
        try {
            // get first configured handler and start the flow
            chandler = (ConsumerHandler) handlers.getFirst();
            chandler.consume(message);
        } catch (Exception ex) {
            if (ex instanceof HandlerException) {
                throw (HandlerException) ex;
            } else {
                String logMsg = "Unexpected error occurred in the handler with id ["
                        + (chandler != null ? chandler.getId() : "null") + "] when processing message. "
                        + "The message flow id [" + getFlowConfig().getId() + "] will discard the message : " + message.toString();
                LOGGER.log(Level.SEVERE, "{0}. Error was : {1}", new Object[] { logMsg, ExceptionUtil.getStackTrace(ex) });
                stream.getEventProcessor().onAlertEvent(new AlertEvent(this,
                        Alert.createAlert(stream.getName(), ComponentType.ConsumerHandler.toString(),
                        BaseSeverityType.RECOVERABLE, AlertCode.HANDLER_PROCESSING_ERROR, logMsg, ex)));
            }
        }
    }

    @Override
    public void start() {
        LOGGER.log(Level.INFO, "Starting consumer flow [{0}].", configuration.getId());

        for (Handler handler : handlers) {
            handler.init();
        }

        LOGGER.log(Level.INFO, "Consumer flow [{0}] started.", configuration.getId());
    }

    @Override
    public void stop() {
        LOGGER.log(Level.INFO, "Stopping consumer flow [{0}].", configuration.getId());

        for (Handler handler : handlers) {
            handler.close();
        }

        LOGGER.log(Level.INFO, "Consumer flow [{0}] stopped.", configuration.getId());
    }

    @Override
    protected void checkHandlerClass(Handler handler) throws ConfigurationException {
        if (!(ConsumerHandler.class.isAssignableFrom(handler.getClass()) ||
            ConsumerProducerHandler.class.isAssignableFrom(handler.getClass()))) {
            String errMsg = "Handler [" + handler.getName() + "] in a Consumer flow must implement [ConsumerHandler] or "
                    + "[ConsumerProducerHandler] interfaces.";
            LOGGER.severe(errMsg);
            throw new ConfigurationException(errMsg); 
        }
    }
}
