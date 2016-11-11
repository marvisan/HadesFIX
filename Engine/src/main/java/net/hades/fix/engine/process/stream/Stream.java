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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
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
import net.hades.fix.engine.process.EngineTask;
import net.hades.fix.engine.process.ExecutionResult;
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
    protected LinkedHashMap<String, Handler> handlers;
    protected List<HandlerDefInfo> handlerDefs;
    protected Map<String, ExecutionResult> results;
   
    protected Stream(SessionCoordinator sessionCoordinator, StreamInfo configuration, HandlerDefInfo[] handlerDefs) throws ConfigurationException {
        this.sessionCoordinator = sessionCoordinator;
	this.configuration = configuration;
	this.handlerDefs = Arrays.asList(handlerDefs);
	createHandlers();
	wireupHandlers();
	results = new HashMap<>();
    }

    public Handler findHandlerById(String id) {
	return handlers.get(id);
    }
    
    public void start(ExecutorService executor) {
	for (Map.Entry<String, Handler> handlerEntry : handlers.entrySet()) {
	    EngineTask<ExecutionResult> task = new EngineTask<>(Thread.NORM_PRIORITY, handlerEntry.getValue());
	    results.put(task.getName(), (ExecutionResult) executor.submit(task));
	}
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

    private void createHandlers() throws ConfigurationException {
	List<Handler> handlersList = new ArrayList<>();
	for (HandlerInfo handlerInfo : configuration.getHandlers()) {
	    Handler handler = getConfiguredHandler(handlerInfo.getId(), handlerInfo.getName());
	    if (handler != null) {
		handlersList.add(handler);
	    }
	}
	orderHandlers(handlersList);
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

    private void orderHandlers(List<Handler> handlersList) throws ConfigurationException {
	Map<String, VisitedStatus> visited = initialiseVisited(handlersList);
        for (Handler handler : handlersList) {
            if (VisitedStatus.No.equals(visited.get(handler.getId()))) {
                dfs(handlersList, visited, handler);
            }
        }
        Set<String> keys = handlers.keySet();
	if (configuration.getFirstHandlerId().equals(getLast(keys))) {
	    LinkedHashMap reversed = new LinkedHashMap();
	    List<String> l = new ArrayList<>(handlers.keySet());
	    for (int i = l.size() - 1; i >=0; i--) {
		String id = l.get(i);
                reversed.put(id, handlers.get(id));
            }
	    handlers = reversed;
	} else if (!configuration.getFirstHandlerId().equals(getFirst(keys))) {
	    throw new ConfigurationException("First handler does not match the ordered list");
	}
    }
    
    private String getFirst(Set<String> values) {
	return values.iterator().next();
    }
    
    private String getLast(Set<String> values) {
	String last = null;
	for (String s : values) {
	    last = s;
	}
	return last;
    }
    
    private Map<String, VisitedStatus> initialiseVisited(List<Handler> handlers) {
        Map<String, VisitedStatus> visited = new HashMap<>(handlers.size());
        handlers.forEach((handler) -> {
            visited.put(handler.getId(), VisitedStatus.No);
        });
        return visited;
    }

    
    private void dfs(List<Handler> handlersList, Map<String, VisitedStatus> visited, Handler handler) throws ConfigurationException {
        visited.put(handler.getId(), VisitedStatus.Started);
	for (Iterator<Handler> it = handler.getNextHandlers().iterator(); it.hasNext();) {
	    Handler h = it.next();
            if (visited.get(h.getId()).equals(VisitedStatus.No)) {
                dfs(handlersList, visited, h);
            } else if (visited.get(h.getId()).equals(VisitedStatus.Started)) {
                throw new ConfigurationException(String.format("Graph circular reference found for handler vertex [%s] --> [%s] ", handler.getId(), h.getId()));
            }
        }
        handlers.put(handler.getId(), handler);
        visited.put(handler.getId(), VisitedStatus.Done);
    }


}
