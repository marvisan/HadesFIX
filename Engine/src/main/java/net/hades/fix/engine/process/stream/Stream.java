/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.stream;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.engine.config.model.HandlerDefInfo;
import net.hades.fix.engine.config.model.HandlerInfo;
import net.hades.fix.engine.config.model.HandlerParamInfo;
import net.hades.fix.engine.config.model.HandlerRefInfo;
import net.hades.fix.engine.config.model.StreamInfo;
import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.handler.Handler;
import net.hades.fix.engine.process.session.SessionCoordinator;

/**
 * Generic class for a message stream container.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public abstract class Stream {

    private static final Logger Log = Logger.getLogger(Stream.class .getName());

    protected SessionCoordinator sessionCoordinator;
    protected StreamInfo configuration;
    protected List<Handler> handlers;
    protected List<HandlerDefInfo> handlerDefs;

    protected Stream(SessionCoordinator sessionCoordinator, StreamInfo configuration, HandlerDefInfo[] handlerDefs) throws ConfigurationException {
        this.sessionCoordinator = sessionCoordinator;
	this.configuration = configuration;
	this.handlerDefs = Arrays.asList(handlerDefs);
	createHandlers();
	wireupHandlers();
    }
    
    public Handler getFirstHandler() {
	Handler result = null;
	for (Handler handler : handlers) {
	    result = handler;
	    String id = handler.getId();
	    boolean found = false;
	    for (Handler other : handlers) {
//		for (Handler h : other.getNextHandlers()) {
//		    if (h.getId().equals(id)) {
//			found = true;
//			break;
//		    }
//		}
		if (found) {
		    break;
		}
	    }
	}
	return result;
    }
    
    public Handler getLastHandler() {
	for (Handler handler : handlers) {
	    if (handler.getNextHandlers() == null || handler.getNextHandlers().isEmpty()) {
		return handler;
	    }
	}
	return null;
    }

    //---------------------------------------------------------------------------------------------
    
    private void wireupHandlers() throws ConfigurationException {
	for (HandlerInfo handlerInfo : configuration.getHandlers()) {
	    Handler th = findHandlerById(handlerInfo.getId());
	    if (th == null) {
		throw new ConfigurationException(String.format("Handler with id [%s] is not properly configured", handlerInfo.getId()));
	    }
	    if (handlerInfo.getNextHandlers() != null && handlerInfo.getNextHandlers().length > 0) {
		for (HandlerRefInfo ref : handlerInfo.getNextHandlers()) {
		    Handler tr = findHandlerById(ref.getId());
		    if (tr == null) {
			throw new ConfigurationException(String.format("Handler with id [%s] is not properly configured", ref.getId()));
		    }
		    th.addNextHandler(ref.getId(), tr);
		}
	    }
	}
    }
    
    private Handler findHandlerById(String id) {
	for (Handler handler : handlers) {
	    if (id.equals(handler.getId())) {
		return handler;
	    }
	}
	return null;
    }

    private void createHandlers() throws ConfigurationException {
	handlers = new ArrayList<>();
	for (HandlerInfo handlerInfo : configuration.getHandlers()) {
	    Handler handler = getConfiguredHandler(handlerInfo.getId(), handlerInfo.getName());
	    if (handler != null) {
		handlers.add(handler);
	    }
	}
    }
   
    private Handler getConfiguredHandler(String id, String name) throws ConfigurationException {
        for (HandlerDefInfo handlerDef : handlerDefs) {
            if (name.equals(handlerDef.getName())) {
		Map<String, String> parameters = buildParameterMap(handlerDef.getParameters());
                return createHandlerInstance(handlerDef.getImplClass(), id, parameters);
            }
        }
        return null;
    }

    private Handler createHandlerInstance(String implClass, String id, Map<String, String> parameters) throws ConfigurationException {
        try {
	    Class[] types = new Class[] { String.class, Map.class, ConcurrentMap.class };
            Class<? extends Handler> handlerClass = Class.forName(implClass.trim()).asSubclass(Handler.class);
	    Constructor<? extends Handler> ctor = handlerClass.getConstructor(types);
	    Object[] params = new Object[] { id, parameters, sessionCoordinator.getSessionContext() };
            return ctor.newInstance(params);
        } catch (ClassNotFoundException ex) {
            String errMsg = "Singleton handler class [" + implClass + "] handler [" + id + "] was not found.";
            Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[] {errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        } catch (InstantiationException ex) {
            String errMsg = "Could not create an instance of handler class [" + implClass + "] handler [" + id + "].";
            Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        } catch (IllegalAccessException ex) {
            String errMsg = "Class accessor error for [" + implClass + "] handler [" + id + "].";
            Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
        } catch (NoSuchMethodException ex) {
	    String errMsg = "Class constructor for [" + implClass + "] handler [" + id + "] does not exists.";
            Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
	} catch (SecurityException ex) {
	    String errMsg = "Security access error for [" + implClass + "] handler [" + id + "].";
            Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
	} catch (IllegalArgumentException ex) {
	    String errMsg = "Inavlid arguments of constructor for [" + implClass + "] handler [" + id + "].";
            Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
	} catch (InvocationTargetException ex) {
	    String errMsg = "Could not call constructor for [" + implClass + "] handler [" + id + "].";
            Log.log(Level.SEVERE, "{0} Error was : {1}", new Object[]{errMsg, ExceptionUtil.getStackTrace(ex)});
            throw new ConfigurationException(errMsg, ex);
	}
    }

    private Map<String, String> buildParameterMap(HandlerParamInfo[] handlerParams) {
        Map<String, String> result = null;
        if (handlerParams != null && handlerParams.length > 0) {
            result = new HashMap<>(handlerParams.length);
            for (HandlerParamInfo handlerParam : handlerParams) {
                result.put(handlerParam.getName(), handlerParam.getValue());
            }
        }
        return result;
    }

}
