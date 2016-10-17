/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HadesFIXEngineMBean.java
 *
 * $Id: HadesFIXEngineMBean.java,v 1.25 2011-04-03 08:00:08 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.config.model.ClientSessionInfo;
import net.hades.fix.engine.config.model.ServerSessionInfo;
import net.hades.fix.engine.config.model.SessionInfo;
import net.hades.fix.engine.exception.ProtocolException;
import net.hades.fix.engine.exception.ProtocolStatusException;
import net.hades.fix.engine.mgmt.data.*;
import net.hades.fix.engine.process.event.AlertEvent;
import net.hades.fix.engine.process.event.LifeCycleEvent;
import net.hades.fix.engine.process.event.MessageEvent;
import net.hades.fix.engine.process.session.ServerSessionCoordinator;
import net.hades.fix.engine.process.session.SessionCoordinator;
import net.hades.fix.engine.process.session.SessionType;

import javax.management.*;
import javax.management.openmbean.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Management bean for the Hades FIX engine.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.25 $
 */
public class HadesFIXEngineMBean extends NotificationBroadcasterSupport implements DynamicMBean {

    private static final Logger LOGGER = Logger.getLogger(HadesFIXEngineMBean.class.getName());

    private OpenMBeanInfoSupport engineMBeanInfo;

    private final HadesInstance fixEngine;

    public HadesFIXEngineMBean(HadesInstance fixEngine) throws OpenDataException {
        this.fixEngine = fixEngine;
        buildMBeanInfo();
    }

    @Override
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        if (attribute == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute name cannot be null"),
                    "Cannot call getAttribute with null attribute name");
        }
        if (attribute.equals("name")) {
            return fixEngine.getConfiguration().getName();
        }
        if (attribute.equals("description")) {
            return fixEngine.getConfiguration().getDescription();
        }
        throw new AttributeNotFoundException("Cannot find " + attribute + " attribute ");
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
//        if (attribute == null) {
//            throw new RuntimeOperationsException(new IllegalArgumentException("Attribute cannot be null"),
//                    "Cannot invoke a setter of FIXEngineMBean with null attribute");
//        }
//        String attrName = attribute.getName();
//        Object value = attribute.getValue();
//        if (attrName.equals("name")) {
//            // if null value, try and see if the setter returns any exception
//            if (value == null) {
//                try {
//                    // set attribute here
//                } catch (Exception e) {
//                    throw new InvalidAttributeValueException("Cannot set attribute " + attrName + " to null");
//                }
//            } else {
//                try {
//                    if ((Class.forName("java.lang.String")).isAssignableFrom(value.getClass())) {
//                        setName((String) value);
//                    } else {
//                        throw new InvalidAttributeValueException("Cannot set attribute " + attrName + " to a "
//                                + value.getClass().getName() + " object, Long expected");
//                    }
//                } catch (ClassNotFoundException ex) {
//                    LOGGER.log(Level.SEVERE, ExceptionUtil.getStackTrace(ex));
//                }
//            }
//        } else if (attrName.equals("others")) {
//            throw new AttributeNotFoundException("Cannot set attribute " + attrName + " because it is read-only");
//        } else {
//            throw new AttributeNotFoundException("Attribute " + attrName + " not found in " + this.getClass().getName());
//        }
    }

    @Override
    public AttributeList getAttributes(String[] attributes) {
        if (attributes == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("attributes[] cannot be null"),
                    "Cannot call getAttributes with null attribute names");
        }
        AttributeList resultList = new AttributeList();
        if (attributes.length == 0) {
            return resultList;
        }
        for (String attribute : attributes) {
            try {
                Object value = getAttribute(attribute);
                resultList.add(new Attribute(attribute, value));
            } catch (AttributeNotFoundException | MBeanException | ReflectionException ex) {
                LOGGER.log(Level.SEVERE, ExceptionUtil.getStackTrace(ex));
            }
        }

        return resultList;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        if (attributes == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("AttributeList attributes cannot be null"),
                    "Cannot invoke a setter of FIXEngineMBean");
        }
        AttributeList resultList = new AttributeList();
        // if attributeNames is empty, nothing more to do
        if (attributes.isEmpty()) {
            return resultList;
        }
        // for each attribute, try to set it and add to the result list if
        // successful
        for (Object attribute : attributes) {
            Attribute attr = (Attribute) attribute;
            try {
                setAttribute(attr);
                String attrName = attr.getName();
                Object value = getAttribute(attrName);
                resultList.add(new Attribute(attrName, value));
            } catch (AttributeNotFoundException | InvalidAttributeValueException | MBeanException | ReflectionException ex) {
                LOGGER.log(Level.SEVERE, ExceptionUtil.getStackTrace(ex));
            }
        }
        return resultList;
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "actionName={0}", actionName);
        }
        if (actionName == null) {
            throw new RuntimeOperationsException(new IllegalArgumentException("Operation name cannot be null"),
                    "Cannot call invoke with null operation name");
        }
        if (actionName.equals("getConfigSessions")) {
            CompositeData[] result;
            try {
                result = getConfiguredSessions();
            } catch (Exception e) {
                throw new MBeanException(e, "invoking getConfigSessions: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else if (actionName.equals("setRxSeq")) {
            CompositeData result;
            if ((params.length != 3) || !(params[0] instanceof String || params[1] instanceof String || params[2] instanceof Integer)) {
                throw new RuntimeOperationsException(
                        new IllegalArgumentException("Cannot invoke [setRxSeq] : expecting params[i] : "
                                + "instanceof SimpleType.STRING for i = 0, instanceof SimpleType.STRING for i = 1, "
                                + "instanceof SimpleType.INTEGER for i = 2"),
                        "Wrong content for array Object[] params to invoke setRxSeq() method");
            }
            try {
                result = setRxSeq((String) params[0], (String) params[1], (Integer) params[2]);
            } catch (Exception e) {
                throw new MBeanException(e, "invoking setRxSeq: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else if (actionName.equals("setTxSeq")) {
            CompositeData result;
            if ((params.length != 3) || !(params[0] instanceof String || params[1] instanceof String || params[2] instanceof Integer)) {
                throw new RuntimeOperationsException(
                        new IllegalArgumentException("Cannot invoke [setTxSeq] : expecting params[i] : "
                                + "instanceof SimpleType.STRING for i = 0, instanceof SimpleType.STRING for i = 1, "
                                + "instanceof SimpleType.INTEGER for i = 2"),
                        "Wrong content for array Object[] params to invoke setTxSeq() method");
            }
            try {
                result = setTxSeq((String) params[0], (String) params[1], (Integer) params[2]);
            } catch (Exception e) {
                throw new MBeanException(e, "invoking setTxSeq: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else if (actionName.equals("freezeSess")) {
            CompositeData result;
            if ((params.length != 2) || !(params[0] instanceof String || params[1] instanceof String)) {
                throw new RuntimeOperationsException(
                        new IllegalArgumentException("Cannot invoke [freezeSess] : expecting params[i] : "
                                + "instanceof SimpleType.STRING for i = 0, instanceof SimpleType.STRING for i = 1 "),
                        "Wrong content for array Object[] params to invoke freezeSess() method");
            }
            try {
                result = freezeSess((String) params[0], (String) params[1]);
            } catch (Exception e) {
                throw new MBeanException(e, "invoking freezeSess: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else if (actionName.equals("thawSess")) {
            CompositeData result;
            if ((params.length != 2) || !(params[0] instanceof String || params[1] instanceof String)) {
                throw new RuntimeOperationsException(
                        new IllegalArgumentException("Cannot invoke [thawSess] : expecting params[i] : "
                                + "instanceof SimpleType.STRING for i = 0, instanceof SimpleType.STRING for i = 1 "),
                        "Wrong content for array Object[] params to invoke thawSess() method");
            }
            try {
                result = thawSess((String) params[0], (String) params[1]);
            } catch (Exception e) {
                throw new MBeanException(e, "invoking thawSess: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else if (actionName.equals("resetSequences")) {
            CompositeData result;
            if ((params.length != 3) || !(params[0] instanceof String || params[1] instanceof String) || params[1] instanceof Integer) {
                throw new RuntimeOperationsException(
                        new IllegalArgumentException("Cannot invoke [resetSequences] : expecting params[i] : "
                                + "instanceof SimpleType.STRING for i = 0, instanceof SimpleType.STRING for i = 1, instanceof SimpleType.INTEGER for i = 2 "),
                        "Wrong content for array Object[] params to invoke resetSequences() method");
            }
            try {
                result = resetSequences((String) params[0], (String) params[1], (Integer) params[2]);
            } catch (Exception e) {
                throw new MBeanException(e, "invoking resetSequences: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else if (actionName.equals("getSessionStats")) {
            CompositeData result;
            if ((params.length != 2) || !(params[0] instanceof String || params[1] instanceof String)) {
                throw new RuntimeOperationsException(
                        new IllegalArgumentException("Cannot invoke [getSessionStats] : expecting params[i] : "
                                + "instanceof SimpleType.STRING for i = 0, instanceof SimpleType.STRING for i = 1 "),
                        "Wrong content for array Object[] params to invoke getSessionStats() method");
            }
            try {
                result = getSessionStats((String) params[0], (String) params[1]);
            } catch (Exception e) {
                throw new MBeanException(e, "invoking getSessionStats: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else if (actionName.equals("killSession")) {
            CompositeData result;
            if ((params.length != 2) || !(params[0] instanceof String || params[1] instanceof String)) {
                throw new RuntimeOperationsException(
                        new IllegalArgumentException("Cannot invoke [killSession] : expecting params[i] : "
                                + "instanceof SimpleType.STRING for i = 0, instanceof SimpleType.STRING for i = 1 "),
                        "Wrong content for array Object[] params to invoke killSession() method");
            }
            try {
                result = killSession((String) params[0], (String) params[1]);
            } catch (Exception e) {
                throw new MBeanException(e, "invoking killSession: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else if (actionName.equals("startSession")) {
            CompositeData result;
            if ((params.length != 2) || !(params[0] instanceof String || params[1] instanceof String)) {
                throw new RuntimeOperationsException(
                        new IllegalArgumentException("Cannot invoke [startSession] : expecting params[i] : "
                                + "instanceof SimpleType.STRING for i = 0, instanceof SimpleType.STRING for i = 1 "),
                        "Wrong content for array Object[] params to invoke startSession() method");
            }
            try {
                result = startSession((String) params[0], (String) params[1]);
            } catch (Exception e) {
                throw new MBeanException(e, "invoking startSession: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else if (actionName.equals("disconnectSession")) {
            CompositeData result;
            if ((params.length != 2) || !(params[0] instanceof String || params[1] instanceof String)) {
                throw new RuntimeOperationsException(
                        new IllegalArgumentException("Cannot invoke [disconnectSession] : expecting params[i] : "
                                + "instanceof SimpleType.STRING for i = 0, instanceof SimpleType.STRING for i = 1 "),
                        "Wrong content for array Object[] params to invoke disconnectSession() method");
            }
            try {
                result = disconnectSession((String) params[0], (String) params[1]);
            } catch (Exception e) {
                throw new MBeanException(e, "invoking disconnectSession: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else if (actionName.equals("connectSession")) {
            CompositeData result;
            if ((params.length != 2) || !(params[0] instanceof String || params[1] instanceof String)) {
                throw new RuntimeOperationsException(
                        new IllegalArgumentException("Cannot invoke [connectSession] : expecting params[i] : "
                                + "instanceof SimpleType.STRING for i = 0, instanceof SimpleType.STRING for i = 1 "),
                        "Wrong content for array Object[] params to invoke connectSession() method");
            }
            try {
                result = connectSession((String) params[0], (String) params[1]);
            } catch (Exception e) {
                throw new MBeanException(e, "invoking connectSession: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else if (actionName.equals("sessionReset")) {
            CompositeData result;
            if ((params.length != 2) || !(params[0] instanceof String || params[1] instanceof String)) {
                throw new RuntimeOperationsException(
                        new IllegalArgumentException("Cannot invoke [sessionReset] : expecting params[i] : "
                                + "instanceof SimpleType.STRING for i = 0, instanceof SimpleType.STRING for i = 1 "),
                        "Wrong content for array Object[] params to invoke sessionReset() method");
            }
            try {
                result = sessionReset((String) params[0], (String) params[1]);
            } catch (Exception e) {
                throw new MBeanException(e, "invoking sessionReset: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else if (actionName.equals("getClientSessionConfig")) {
            CompositeData result;
            if ((params.length != 2) || !(params[0] instanceof String || params[1] instanceof String)) {
                throw new RuntimeOperationsException(
                        new IllegalArgumentException("Cannot invoke [getClientSessionConfig] : expecting params[i] : "
                                + "instanceof SimpleType.STRING for i = 0, instanceof SimpleType.STRING for i = 1 "),
                        "Wrong content for array Object[] params to invoke getClientSessionConfig() method");
            }
            try {
                result = getClientSessionConfig((String) params[0], (String) params[1]);
            } catch (Exception e) {
                throw new MBeanException(e, "invoking getClientSessionConfig: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else if (actionName.equals("getServerSessionConfig")) {
            CompositeData result;
            if ((params.length != 2) || !(params[0] instanceof String || params[1] instanceof String)) {
                throw new RuntimeOperationsException(
                        new IllegalArgumentException("Cannot invoke [getServerSessionConfig] : expecting params[i] : "
                                + "instanceof SimpleType.STRING for i = 0, instanceof SimpleType.STRING for i = 1 "),
                        "Wrong content for array Object[] params to invoke getServerSessionConfig() method");
            }
            try {
                result = getServerSessionConfig((String) params[0], (String) params[1]);
            } catch (Exception e) {
                throw new MBeanException(e, "invoking getServerSessionConfig: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else if (actionName.equals("shutdown")) {
            CompositeData result;
            try {
                result = shutdownEngine();
            } catch (Exception e) {
                throw new MBeanException(e, "invoking shutdown: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else if (actionName.equals("shutdownImmediate")) {
            CompositeData result;
            try {
                result = shutdownImmediateEngine();
            } catch (Exception e) {
                throw new MBeanException(e, "invoking shutdownImmediate: " + e.getClass().getName() + " caught ["
                        + e.getMessage() + "]");
            }

            return result;
        } else {
            throw new ReflectionException(new NoSuchMethodException(actionName), "Cannot find the operation " + actionName);
        }
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        return engineMBeanInfo;
    }

    private void buildMBeanInfo() throws OpenDataException {
        OpenMBeanAttributeInfoSupport[] attributes = new OpenMBeanAttributeInfoSupport[2];
        OpenMBeanConstructorInfoSupport[] constructors = new OpenMBeanConstructorInfoSupport[0];
        OpenMBeanOperationInfoSupport[] operations = new OpenMBeanOperationInfoSupport[16];
        MBeanNotificationInfo[] notifications = new MBeanNotificationInfo[3];

        // ATTRIBUTES
        attributes[0] = new OpenMBeanAttributeInfoSupport("name",
                "Name of the engine instance.",
                SimpleType.STRING,
                true,
                false,
                false);

        attributes[1] = new OpenMBeanAttributeInfoSupport("description",
                "Description of the engine instance.",
                SimpleType.STRING,
                true,
                false,
                false);

        // OPERATIONS
        // get all configured session method
        OpenMBeanParameterInfo[] getConfigSessionsParameters = new OpenMBeanParameterInfoSupport[0];
        operations[0] = new OpenMBeanOperationInfoSupport("getConfigSessions",
                "Gets all the configured sessions.",
                getConfigSessionsParameters,
                ArrayType.getArrayType(SessionProcessData.DataType),
                MBeanOperationInfo.ACTION);

        OpenMBeanParameterInfo[] setRxSeqParameters = new OpenMBeanParameterInfoSupport[3];
        setRxSeqParameters[0] = new OpenMBeanParameterInfoSupport("cptyAddress", "Counterparty Address", SimpleType.STRING);
        setRxSeqParameters[1] = new OpenMBeanParameterInfoSupport("sessAddress", "Session Address", SimpleType.STRING);
        setRxSeqParameters[2] = new OpenMBeanParameterInfoSupport("newSeqNum", "New sequence number", SimpleType.STRING);
        operations[1] = new OpenMBeanOperationInfoSupport("setRxSeq",
                "Sets the expected RX (incoming) sequence.",
                setRxSeqParameters,
                OutcomeData.DataType,
                MBeanOperationInfo.ACTION);

        OpenMBeanParameterInfo[] setTxSeqParameters = new OpenMBeanParameterInfoSupport[3];
        setTxSeqParameters[0] = new OpenMBeanParameterInfoSupport("cptyAddress", "Counterparty Address", SimpleType.STRING);
        setTxSeqParameters[1] = new OpenMBeanParameterInfoSupport("sessAddress", "Session Address", SimpleType.STRING);
        setTxSeqParameters[2] = new OpenMBeanParameterInfoSupport("newSeqNum", "New sequence number", SimpleType.STRING);
        operations[2] = new OpenMBeanOperationInfoSupport("setTxSeq",
                "Sets the expected TX (outgoing) sequence.",
                setTxSeqParameters,
                OutcomeData.DataType,
                MBeanOperationInfo.ACTION);

        OpenMBeanParameterInfo[] freezeSessParameters = new OpenMBeanParameterInfoSupport[2];
        freezeSessParameters[0] = new OpenMBeanParameterInfoSupport("cptyAddress", "Counterparty Address", SimpleType.STRING);
        freezeSessParameters[1] = new OpenMBeanParameterInfoSupport("sessAddress", "Session Address", SimpleType.STRING);
        operations[3] = new OpenMBeanOperationInfoSupport("freezeSess",
                "Freezes the specified session.",
                freezeSessParameters,
                OutcomeData.DataType,
                MBeanOperationInfo.ACTION);

        OpenMBeanParameterInfo[] thawSessParameters = new OpenMBeanParameterInfoSupport[2];
        thawSessParameters[0] = new OpenMBeanParameterInfoSupport("cptyAddress", "Counterparty Address", SimpleType.STRING);
        thawSessParameters[1] = new OpenMBeanParameterInfoSupport("sessAddress", "Session Address", SimpleType.STRING);
        operations[4] = new OpenMBeanOperationInfoSupport("thawSess",
                "Unfreezes the specified session.",
                thawSessParameters,
                OutcomeData.DataType,
                MBeanOperationInfo.ACTION);

        OpenMBeanParameterInfo[] resetSequencesParameters = new OpenMBeanParameterInfoSupport[3];
        resetSequencesParameters[0] = new OpenMBeanParameterInfoSupport("cptyAddress", "Counterparty Address", SimpleType.STRING);
        resetSequencesParameters[1] = new OpenMBeanParameterInfoSupport("sessAddress", "Session Address", SimpleType.STRING);
        resetSequencesParameters[2] = new OpenMBeanParameterInfoSupport("newSeqNum", "New sequence number", SimpleType.INTEGER);
        operations[5] = new OpenMBeanOperationInfoSupport("resetSequences",
                "Forcefully reset RX sequences.",
                resetSequencesParameters,
                OutcomeData.DataType,
                MBeanOperationInfo.ACTION);

        OpenMBeanParameterInfo[] statsParameters = new OpenMBeanParameterInfoSupport[2];
        statsParameters[0] = new OpenMBeanParameterInfoSupport("cptyAddress", "Counterparty Address", SimpleType.STRING);
        statsParameters[1] = new OpenMBeanParameterInfoSupport("sessAddress", "Session Address", SimpleType.STRING);
        operations[6] = new OpenMBeanOperationInfoSupport("getSessionStats",
                "Retrieve session stats per component.",
                statsParameters,
                OutcomeData.DataType,
                MBeanOperationInfo.ACTION);

        OpenMBeanParameterInfo[] killSessionParameters = new OpenMBeanParameterInfoSupport[2];
        killSessionParameters[0] = new OpenMBeanParameterInfoSupport("cptyAddress", "Counterparty Address", SimpleType.STRING);
        killSessionParameters[1] = new OpenMBeanParameterInfoSupport("sessAddress", "Session Address", SimpleType.STRING);
        operations[7] = new OpenMBeanOperationInfoSupport("killSession",
                "Kills the session and release all the resources.",
                killSessionParameters,
                OutcomeData.DataType,
                MBeanOperationInfo.ACTION);

        OpenMBeanParameterInfo[] startSessionParameters = new OpenMBeanParameterInfoSupport[2];
        startSessionParameters[0] = new OpenMBeanParameterInfoSupport("cptyAddress", "Counterparty Address", SimpleType.STRING);
        startSessionParameters[1] = new OpenMBeanParameterInfoSupport("sessAddress", "Session Address", SimpleType.STRING);
        operations[8] = new OpenMBeanOperationInfoSupport("startSession",
                "Starts a new session.",
                startSessionParameters,
                OutcomeData.DataType,
                MBeanOperationInfo.ACTION);

        OpenMBeanParameterInfo[] disconnectSessionParameters = new OpenMBeanParameterInfoSupport[2];
        disconnectSessionParameters[0] = new OpenMBeanParameterInfoSupport("cptyAddress", "Counterparty Address", SimpleType.STRING);
        disconnectSessionParameters[1] = new OpenMBeanParameterInfoSupport("sessAddress", "Session Address", SimpleType.STRING);
        operations[9] = new OpenMBeanOperationInfoSupport("disconnectSession",
                "Disconnect transport of a running session.",
                disconnectSessionParameters,
                OutcomeData.DataType,
                MBeanOperationInfo.ACTION);

        OpenMBeanParameterInfo[] connectSessionParameters = new OpenMBeanParameterInfoSupport[2];
        connectSessionParameters[0] = new OpenMBeanParameterInfoSupport("cptyAddress", "Counterparty Address", SimpleType.STRING);
        connectSessionParameters[1] = new OpenMBeanParameterInfoSupport("sessAddress", "Session Address", SimpleType.STRING);
        operations[10] = new OpenMBeanOperationInfoSupport("connectSession",
                "Connect transport of a blocked session.",
                connectSessionParameters,
                OutcomeData.DataType,
                MBeanOperationInfo.ACTION);

        OpenMBeanParameterInfo[] sessionResetParameters = new OpenMBeanParameterInfoSupport[2];
        sessionResetParameters[0] = new OpenMBeanParameterInfoSupport("cptyAddress", "Counterparty Address", SimpleType.STRING);
        sessionResetParameters[1] = new OpenMBeanParameterInfoSupport("sessAddress", "Session Address", SimpleType.STRING);
        operations[11] = new OpenMBeanOperationInfoSupport("sessionReset",
                "Reset the sequences to 1.",
                sessionResetParameters,
                OutcomeData.DataType,
                MBeanOperationInfo.ACTION);

        OpenMBeanParameterInfo[] cliSessConfigParameters = new OpenMBeanParameterInfoSupport[2];
        cliSessConfigParameters[0] = new OpenMBeanParameterInfoSupport("cptyAddress", "Counterparty Address", SimpleType.STRING);
        cliSessConfigParameters[1] = new OpenMBeanParameterInfoSupport("sessAddress", "Session Address", SimpleType.STRING);
        operations[12] = new OpenMBeanOperationInfoSupport("getClientSessionConfig",
                "Retrieve configuration data for a client session.",
                cliSessConfigParameters,
                ClientSessionInfo.DataType,
                MBeanOperationInfo.ACTION);

        OpenMBeanParameterInfo[] srvSessConfigParameters = new OpenMBeanParameterInfoSupport[2];
        srvSessConfigParameters[0] = new OpenMBeanParameterInfoSupport("cptyAddress", "Counterparty Address", SimpleType.STRING);
        srvSessConfigParameters[1] = new OpenMBeanParameterInfoSupport("sessAddress", "Session Address", SimpleType.STRING);
        operations[13] = new OpenMBeanOperationInfoSupport("getServerSessionConfig",
                "Retrieve configuration data for a server session.",
                srvSessConfigParameters,
                ServerSessionInfo.DataType,
                MBeanOperationInfo.ACTION);

        OpenMBeanParameterInfo[] shutdownParameters = new OpenMBeanParameterInfoSupport[0];
        operations[14] = new OpenMBeanOperationInfoSupport("shutdown",
                "Shutdown gracefully the instance.",
                shutdownParameters,
                OutcomeData.DataType,
                MBeanOperationInfo.ACTION);

        OpenMBeanParameterInfo[] shutdownImmediateParameters = new OpenMBeanParameterInfoSupport[0];
        operations[15] = new OpenMBeanOperationInfoSupport("shutdownImmediate",
                "Shutdown immediate the instance.",
                shutdownImmediateParameters,
                OutcomeData.DataType,
                MBeanOperationInfo.ACTION);

        notifications[0] = new MBeanNotificationInfo(new String[]{AlertEvent.ALERT_RAISED},
                Notification.class.getName(),
                "Alert Event",
                null);

        notifications[1] = new MBeanNotificationInfo(new String[]{LifeCycleEvent.LIFECYCLE_RAISED},
                Notification.class.getName(),
                "Lifecycle Event",
                null);

        notifications[2] = new MBeanNotificationInfo(new String[]{MessageEvent.MESSAGE_RAISED},
                Notification.class.getName(),
                "Message Event",
                null);

        engineMBeanInfo = new OpenMBeanInfoSupport(this.getClass().getName(),
                "Hades Fix Server Engine Mgmt Information",
                attributes,
                constructors,
                operations,
                notifications);
    }

    private CompositeData[] getConfiguredSessions() {
        List<CompositeData> resultSessions = new ArrayList<>();
        Map<SessionInfo, SessionCoordinator> configuredSessions = fixEngine.getConfiguredSessions();
        int fakeId = -1;
        for (Map.Entry<SessionInfo, SessionCoordinator> configSession : configuredSessions.entrySet()) {
            SessionCoordinator sessionCoordinator = configSession.getValue();
            if (sessionCoordinator != null) {
                // we found an running session
                SessionProcessData sessMgmtData = (SessionProcessData) sessionCoordinator.getMgmtData();
                if (sessMgmtData != null) {
                    sessMgmtData.setCounterparty(sessionCoordinator.getCptyID());
                    CompositeData cd;
                    cd = sessMgmtData.toCompositeData(SessionProcessData.DataType);
                    resultSessions.add(cd);
                }
            } else {
                // no in memory session, create a manual entry
                SessionProcessData sessMgmtData = new SessionProcessData();
                sessMgmtData.setId(String.valueOf(fakeId--));
                sessMgmtData.setName(configSession.getKey().getID());
                sessMgmtData.setCounterparty(configSession.getKey().getRemoteID());
                sessMgmtData.setStatus(ProcessStatus.SHUTDOWN);
                sessMgmtData.setConfig(configSession.toString());
                ProtocolProcessData protocolMgmtData = new ProtocolProcessData();
                if (configSession.getKey() instanceof ServerSessionInfo) {
                    protocolMgmtData.setSessionType(SessionType.SELL);
                } else {
                    protocolMgmtData.setSessionType(SessionType.BUY);
                }
                protocolMgmtData.setStatus(ProcessStatus.SHUTDOWN);
                protocolMgmtData.setRxSeqNo(0);
                protocolMgmtData.setTxSeqNo(0);
                sessMgmtData.setProtocolProcessData(protocolMgmtData);
                CompositeData cd = sessMgmtData.toCompositeData(SessionProcessData.DataType);
                resultSessions.add(cd);
            }
        }

        return resultSessions.toArray(new CompositeData[resultSessions.size()]);
    }

    private CompositeData setRxSeq(String cptyAddr, String sessAddr, Integer newSeqNum) {
        OutcomeData result;
        SessionCoordinator sessionCoordinator = fixEngine.getSessionCoordinator(cptyAddr, sessAddr);
        if (sessionCoordinator != null) {
            if (ProcessStatus.ACTIVE.equals(sessionCoordinator.getProcessStatus())) {
                result = new OutcomeData(false);
                result.setErrMsg("Could not change incoming messages expected sequence while the session is active.");
            } else {
                result = new OutcomeData(true);
                int seqNum = newSeqNum;
                if (seqNum > 0) {
                    --seqNum;
                }
                sessionCoordinator.getProtocol().setRxSeqNo(seqNum);
            }
        } else {
            result = new OutcomeData(false);
            result.setErrMsg("Could not found a session with address [" + cptyAddr + ":" + sessAddr + "].");
        }

        return result.toCompositeData(OutcomeData.DataType);
    }

    private CompositeData setTxSeq(String cptyAddr, String sessAddr, Integer newSeqNum) {
        OutcomeData result;
        SessionCoordinator sessionCoordinator = fixEngine.getSessionCoordinator(cptyAddr, sessAddr);
        if (sessionCoordinator != null) {
            if (ProcessStatus.ACTIVE.equals(sessionCoordinator.getProcessStatus())) {
                result = new OutcomeData(false);
                result.setErrMsg("Could not change outgoing messages expected sequence while the session is active.");
            } else {
                result = new OutcomeData(true);
                int seqNum = newSeqNum;
                if (seqNum > 0) {
                    --seqNum;
                }
                sessionCoordinator.getProtocol().setTxSeqNo(seqNum);
            }
        } else {
            result = new OutcomeData(false);
            result.setErrMsg("Could not found a session with address [" + cptyAddr + ":" + sessAddr + "].");
        }

        return result.toCompositeData(OutcomeData.DataType);
    }

    private CompositeData freezeSess(String cptyAddr, String sessAddr) {
        OutcomeData result;
        SessionCoordinator sessionCoordinator = fixEngine.getSessionCoordinator(cptyAddr, sessAddr);
        if (sessionCoordinator != null) {
            try {
                result = new OutcomeData(true);
                sessionCoordinator.freezeProtocol();
            } catch (ProtocolStatusException ex) {
                result = new OutcomeData(false);
                result.setErrMsg(ex.getMessage());
            }
        } else {
            result = new OutcomeData(false);
            result.setErrMsg("Could not found a session with address [" + cptyAddr + ":" + sessAddr + "].");
        }

        return result.toCompositeData(OutcomeData.DataType);
    }

    private CompositeData thawSess(String cptyAddr, String sessAddr) {
        OutcomeData result;
        SessionCoordinator sessionCoordinator = fixEngine.getSessionCoordinator(cptyAddr, sessAddr);
        if (sessionCoordinator != null) {
            try {
                result = new OutcomeData(true);
                sessionCoordinator.thawProtocol();
            } catch (ProtocolStatusException ex) {
                result = new OutcomeData(false);
                result.setErrMsg(ex.getMessage());
            }
        } else {
            result = new OutcomeData(false);
            result.setErrMsg("Could not found a session with address [" + cptyAddr + ":" + sessAddr + "].");
        }

        return result.toCompositeData(OutcomeData.DataType);
    }

    private CompositeData resetSequences(String cptyAddr, String sessAddr, Integer newSeqNum) {
        OutcomeData result;
        SessionCoordinator sessionCoordinator = fixEngine.getSessionCoordinator(cptyAddr, sessAddr);
        if (sessionCoordinator != null) {
            int expSeqNo = sessionCoordinator.getProtocol().getRxSeqNo() + 1;
            if (newSeqNum < expSeqNo) {
                result = new OutcomeData(false);
//                result.setErrMsg("Could not reset session [" + sessionCoordinator.getName() + "] sequence number to [" + newSeqNum
//                        + "] that is smaller than current expected sequence [" + expSeqNo + "]");
            } else {
                try {
                    sessionCoordinator.sendResetSequenceMessage(newSeqNum);
                    result = new OutcomeData(true);
                } catch (InterruptedException | ProtocolException ex) {
                    result = new OutcomeData(false);
                    result.setErrMsg(ex.getMessage());
                }
            }
        } else {
            result = new OutcomeData(false);
            result.setErrMsg("Could not found a session with address [" + cptyAddr + ":" + sessAddr + "].");
        }

        return result.toCompositeData(OutcomeData.DataType);
    }

    /**
     * Mught return null if session does not exist anymore
     *
     * @param cptyAddr remote counterparty address
     * @param sessAddr local counterparty address
     * @return session stats
     */
    private CompositeData getSessionStats(String cptyAddr, String sessAddr) {
        CompositeData result = null;
        SessionCoordinator sessionCoordinator = fixEngine.getSessionCoordinator(cptyAddr, sessAddr);
        if (sessionCoordinator != null) {
            SessionStats sessStats = (SessionStats) sessionCoordinator.getStats();
            if (sessStats != null) {
                result = sessStats.toCompositeData(SessionStats.DataType);
            }
        }

        return result;
    }

    private CompositeData killSession(String cptyAddr, String sessAddr) {
        OutcomeData result;
        try {
            result = new OutcomeData(true);
            fixEngine.stopSession(cptyAddr, sessAddr);
        } catch (ProtocolException ex) {
            result = new OutcomeData(false);
            result.setErrMsg(ex.getMessage());
        }

        return result.toCompositeData(OutcomeData.DataType);
    }

    private CompositeData startSession(String cptyAddr, String sessAddr) {
        OutcomeData result;
        try {
            fixEngine.startSession(cptyAddr, sessAddr);
            result = new OutcomeData(true);
        } catch (Exception ex) {
            String errMsg = String.format("Could not start the session with address [%s:%s]. Error was: %s", cptyAddr, sessAddr, ex.getMessage());
            LOGGER.severe(errMsg);
            result = new OutcomeData(false);
            result.setErrMsg(errMsg);
        }

        return result.toCompositeData(OutcomeData.DataType);
    }

    private CompositeData disconnectSession(String cptyAddr, String sessAddr) {
        OutcomeData result;
        SessionCoordinator sessionCoordinator = fixEngine.getSessionCoordinator(cptyAddr, sessAddr);
        if (sessionCoordinator != null) {
            try {
                result = new OutcomeData(true);
                sessionCoordinator.disconnectTransport();
            } catch (ProtocolStatusException ex) {
                result = new OutcomeData(false);
                result.setErrMsg(ex.getMessage());
            }
        } else {
            result = new OutcomeData(false);
            result.setErrMsg("Could not found a session with address [" + cptyAddr + ":" + sessAddr + "].");
        }

        return result.toCompositeData(OutcomeData.DataType);
    }

    private CompositeData connectSession(String cptyAddr, String sessAddr) {
        OutcomeData result;
        SessionCoordinator sessionCoordinator = fixEngine.getSessionCoordinator(cptyAddr, sessAddr);
        if (sessionCoordinator != null) {
            if (sessionCoordinator instanceof ServerSessionCoordinator) {
                result = new OutcomeData(false);
                result.setErrMsg("Only client (buy side) sessions can be connected.");
            } else {

                try {
                    result = new OutcomeData(true);
                    sessionCoordinator.connectTransport();
                } catch (ProtocolStatusException ex) {
                    result = new OutcomeData(false);
                    result.setErrMsg(ex.getMessage());
                }
            }
        } else {
            result = new OutcomeData(false);
            result.setErrMsg("Could not found a session with address [" + cptyAddr + ":" + sessAddr + "].");
        }

        return result.toCompositeData(OutcomeData.DataType);
    }

    private CompositeData sessionReset(String cptyAddr, String sessAddr) {
        OutcomeData result;
        SessionCoordinator sessionCoordinator = fixEngine.getSessionCoordinator(cptyAddr, sessAddr);
        if (sessionCoordinator != null) {
            try {
                result = new OutcomeData(true);
                sessionCoordinator.sessionReset();
            } catch (ProtocolException ex) {
                result = new OutcomeData(false);
                result.setErrMsg(ex.getMessage());
            }
        } else {
            result = new OutcomeData(false);
            result.setErrMsg("Could not found a session with address [" + cptyAddr + ":" + sessAddr + "].");
        }

        return result.toCompositeData(OutcomeData.DataType);
    }

    /**
     * Might return null if session does not exist anymore
     *
     * @param cptyAddr remote counterparty address
     * @param sessAddr local counterparty address
     * @return session config
     */
    private CompositeData getClientSessionConfig(String cptyAddr, String sessAddr) {
        CompositeData result = null;
        SessionCoordinator sessionCoordinator = fixEngine.getSessionCoordinator(cptyAddr, sessAddr);
        if (sessionCoordinator != null) {
            result = ((ClientSessionInfo) sessionCoordinator.getConfiguration()).toCompositeData(ClientSessionInfo.DataType);
        }

        return result;
    }

    /**
     * Might return null if session does not exist anymore
     *
     * @param cptyAddr remote counterparty address
     * @param sessAddr local counterparty address
     * @return session config
     */
    private CompositeData getServerSessionConfig(String cptyAddr, String sessAddr) {
        CompositeData result = null;
        SessionCoordinator sessionCoordinator = fixEngine.getSessionCoordinator(cptyAddr, sessAddr);
        if (sessionCoordinator != null) {
            result = ((ServerSessionInfo) sessionCoordinator.getConfiguration()).toCompositeData(ServerSessionInfo.DataType);
        }

        return result;
    }

    private CompositeData shutdownEngine() {
        OutcomeData result;
        try {
            fixEngine.shutdownEngine();
            result = new OutcomeData(true);
        } catch (Exception ex) {
            result = new OutcomeData(false);
            result.setErrMsg("Could not shutdown gracefully the instance. Error was : " + ex.getMessage());
        }

        return result.toCompositeData(OutcomeData.DataType);
    }

    private CompositeData shutdownImmediateEngine() {
        OutcomeData result;
        try {
            fixEngine.shutdownImmediateEngine();
            result = new OutcomeData(true);
        } catch (Exception ex) {
            result = new OutcomeData(false);
            result.setErrMsg("Could not shutdown immediate the instance. Error was : " + ex.getMessage());
        }

        return result.toCompositeData(OutcomeData.DataType);
    }

}
