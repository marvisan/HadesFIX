/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Flow.java
 *
 * $Id: Flow.java,v 1.17 2011-03-21 04:54:25 vrotaru Exp $
 */
package net.hades.fix.engine.process.stream;

import net.hades.fix.engine.config.model.HandlerDefInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.handler.Handler;
import net.hades.fix.engine.handler.MessageTypeFilter;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.config.model.FlowInfo;
import net.hades.fix.engine.config.model.HandlerInfo;
import net.hades.fix.engine.config.model.HandlerParamInfo;
import net.hades.fix.engine.handler.HandlerException;
import net.hades.fix.engine.handler.ConsumerProducerHandler;
import net.hades.fix.engine.util.UIDGen;
import net.hades.fix.message.Message;

/**
 * This class creates a flow in a stream and manages the enclosed handlers.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.17 $
 */
public abstract class Flow implements Flowable {

    private static final Logger LOGGER = Logger.getLogger(Flow.class.getName());

    protected Stream stream;

    protected FlowInfo configuration;

    protected Filter flowFilter;

    protected Deque<Handler> handlers;

    public Flow(Stream stream, FlowInfo configuration) throws ConfigurationException {
        this.stream = stream;
        this.configuration = configuration;
    }

    @Override
    public Streamable getStream() {
        return stream;
    }

    @Override
    public String getId() {
        return configuration.getId();
    }

    public void initialise() throws ConfigurationException {
        LOGGER.log(Level.INFO, "Flow [{0}] initialising.", configuration.getId());

        createFilter();
        createHandlers();
        validateHandlers();

        LOGGER.log(Level.INFO, "Flow [{0}] initialised.", configuration.getId());
    }

    public boolean canProcess(Message message) {
        boolean result = true;
        if (flowFilter != null) {
            result = flowFilter.allow(message);
        }

        return result;
    }

    public FlowInfo getFlowConfig() {
        return configuration;
    }

    public Deque<Handler> getHandlers() {
        return handlers;
    }

    // ABSTRACT INTERFACE
    //////////////////////////////////////////////

    public abstract void start();

    public abstract void stop();

    public abstract void forward(String handlerId, Message message) throws HandlerException;

    protected abstract void checkHandlerClass(Handler handler) throws ConfigurationException;

    protected Handler getNextEnabledHandler(String id, FlowType flowType) {
        Handler result = null;
        Iterator<Handler> iterator;
        if (FlowType.Consumer.equals(flowType)) {
            iterator = handlers.iterator();
        } else {
            iterator = handlers.descendingIterator();
        }
        boolean found = false;
        while (iterator.hasNext()) {
            Handler h = iterator.next();
            if (found) {
                if (!h.isDisabled()) {
                    result = h;
                    break;
                }
            }
            if (h.getId().equals(id)) {
                found = true;
            }
        }

        return result;
    }

    private void createFilter() throws ConfigurationException {
        String msgFilterStr = configuration.getMsgFilter();
        if (msgFilterStr != null && !msgFilterStr.trim().isEmpty()) {
            flowFilter = new MessageTypeFilter(msgFilterStr);

            LOGGER.log(Level.INFO, "Msg type filter initialised for value [{0}].", msgFilterStr);
        } else {
            String msgFilterClass = configuration.getMsgFilterClass();
            if (msgFilterClass != null && !msgFilterClass.trim().isEmpty()) {
                try {
                    Class<? extends Filter> filterClass = Class.forName(msgFilterClass.trim()).asSubclass(Filter.class);
                    flowFilter = filterClass.newInstance();
                    
                    LOGGER.log(Level.INFO, "Filter class [{0}] initialised.", msgFilterClass);
                } catch (ClassNotFoundException ex) {
                    String errMsg = "Filter class [" + msgFilterClass + "] for flow [" + configuration.getId() +
                            "] was not found.";
                    LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ex.toString()});
                    throw new ConfigurationException(errMsg, ex);
                } catch (InstantiationException ex) {
                    String errMsg = "Could not create an instance of filter class [" + msgFilterClass +
                            "] for flow [" + configuration.getId() + "].";
                    LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ex.toString()});
                    throw new ConfigurationException(errMsg, ex);
                } catch (IllegalAccessException ex) {
                    String errMsg = "Class accessor error for [" + msgFilterClass + "] for flow [" + configuration.getId() +
                            "].";
                    LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ex.toString()});
                    throw new ConfigurationException(errMsg, ex);
                }
            }
        }
    }

    private void createHandlers() throws ConfigurationException {
        handlers = new LinkedList<Handler>();
        HandlerInfo handlerInfo = configuration.getHandler();
        List<Handler> singletonHandlers = stream.getSessionCoordinator().getSingletonHandlers();
        HandlerDefInfo[] handlerDefs = stream.getSessionCoordinator().getHandlerDefs();
        while (handlerInfo != null) {
            String name = handlerInfo.getName();
            Handler handler = getSingletonHandler(name, singletonHandlers);
            if (handler != null) {
                if (!ConsumerProducerHandler.class.isAssignableFrom(handler.getClass())) {
                    String errMsg = "Handler [" + name + "] is a singleton handler and must implement [ConsumerProducerHandler] interface.";
                    LOGGER.severe(errMsg);
                    throw new ConfigurationException(errMsg);
                }
                handler.setFlow(this);
                if (handler.getId() == null) {
                    if (handlerInfo.getId() != null) {
                        handler.setId(handlerInfo.getId());
                    } else {
                        handler.setId(UIDGen.getInstance().getNextUID(handler.getHandlerPrefix()));
                    }
                }
                handler.setName(handlerInfo.getName());
                handler.setDisabled(handlerInfo.getDisabled() != null ? handlerInfo.getDisabled() : false);
                handlers.add(handler);

                LOGGER.log(Level.INFO, "Found singleton handler [{0}] for flow [{1}].", new Object[] {handler.getName(), configuration.getId()});
            } else {
                handler = getConfiguredHandler(name, handlerDefs);
                if (handler == null) {
                    String errMsg = "Could not find a handler with name [" + name + "] for flow [" + configuration.getId() + "].";
                    LOGGER.severe(errMsg);
                    throw new ConfigurationException(errMsg);
                }
                if (handlerInfo.getId() == null) {
                    handlerInfo.setId(UIDGen.getInstance().getNextUID(handler.getHandlerPrefix()));
                }
                handler.setId(handlerInfo.getId());
                handler.setName(handlerInfo.getName());
                checkHandlerClass(handler);
		        handler.setDisabled(handlerInfo.getDisabled() != null ? handlerInfo.getDisabled() : false);
                handler.setFlow(this);
                handlers.add(handler);

                LOGGER.log(Level.INFO, "Created new handler [{0}] for flow [{1}].",  new Object[]{handler.getName(), configuration.getId()});
            }
            handlerInfo = handlerInfo.getNextHandler();
        }
    }

    /**
     * Checks for duplicate handler IDs. Processing assume that the IDs are unique in a flow.
     * @throws ConfigurationException found duplicate ID
     */
    private void validateHandlers() throws ConfigurationException {
        LinkedList<Handler> handlersList = (LinkedList<Handler>) handlers;
        String id;
        for (int i = 0; i < handlersList.size(); i++) {
            id = handlersList.get(i).getId();
            for (int j = i + 1; j < handlersList.size(); j++) {
                if (id.equals(handlersList.get(j).getId())) {
                    throw new ConfigurationException("Flows are not allowed to contain handlers with the same id [" + id + "].");
                }
            }
        }
    }

    private Handler getSingletonHandler(String name, List<Handler> singletonHandlers) {
        Handler result = null;
        if (singletonHandlers != null && singletonHandlers.size() > 0) {
            for (Handler handler : singletonHandlers) {
                if (handler.getName().equals(name)) {
                    result  = handler;
                    break;
                }
            }
        }

        return result;
    }

    private Handler getConfiguredHandler (String name, HandlerDefInfo[] handlerDefs) throws ConfigurationException {
        Handler result = null;
        for (HandlerDefInfo handlerDef : handlerDefs) {
            if (name.equals(handlerDef.getName())) {
                result = createHandlerInstance(handlerDef.getImplClass());
                result.setParameters(buildParameterMap(handlerDef.getParameters()));
            }
        }

        return result;
    }

    private Handler createHandlerInstance(String implClass) throws ConfigurationException {
        Handler handler;
        try {
            Class<? extends Handler> filterClass = Class.forName(implClass.trim()).asSubclass(Handler.class);
            handler = filterClass.newInstance();
        } catch (ClassNotFoundException ex) {
            String errMsg = "Handler class [" + implClass + "] for flow [" + configuration.getId()
                    + "] was not found.";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ex.toString()});
            throw new ConfigurationException(errMsg, ex);
        } catch (InstantiationException ex) {
            String errMsg = "Could not create an instance of handler class [" + implClass
                    + "] for flow [" + configuration.getId() + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ex.toString()});
            throw new ConfigurationException(errMsg, ex);
        } catch (IllegalAccessException ex) {
            String errMsg = "Class accessor error for [" + implClass + "] for flow [" + configuration.getId()
                    + "].";
            LOGGER.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ex.toString()});
            throw new ConfigurationException(errMsg, ex);
        }

        return handler;
    }

    private Map<String, String> buildParameterMap(HandlerParamInfo[] handlerParams) {
        Map<String, String> result = new HashMap<String, String>();
        if (handlerParams != null && handlerParams.length > 0) {
            result = new HashMap<String, String>(handlerParams.length);
            for (HandlerParamInfo handlerParam : handlerParams) {
                result.put(handlerParam.getName(), handlerParam.getValue());
            }
        }
        
        return result;
    }
}
