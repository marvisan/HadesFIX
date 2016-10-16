/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.engine.config.xml.adapter.BooleanAdapter;
import net.hades.fix.engine.util.PartyUtil;

/**
 * General FIX engine configuration data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name = "hadesInstance")
@XmlType(name = "HadesInstanceInfo", propOrder = {"counterparties", "handlerDefs", "securedMessages", "alertListeners",
        "lifeCycleListeners", "messageListeners", "scheduler"})
@XmlAccessorType(XmlAccessType.NONE)
public class HadesInstanceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "name", required = true)
    private String name;

    @XmlAttribute(name = "description")
    private String description;

    @XmlAttribute(name = "mgmtHost")
    private String mgmtHost;

    @XmlAttribute(name = "mgmtPort")
    private Integer mgmtPort;

    @XmlAttribute(name = "mgmtUseSSL")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean mgmtUseSSL;

    @XmlAttribute(name = "mgmtUseSSLCliAuth")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean mgmtUseSSLCliAuth;

    @XmlAttribute(name = "mgmtUseAuth")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean mgmtUseAuth;

    @XmlAttribute(name = "mgmtKeystoreFile")
    private String mgmtKeystoreFile;

    @XmlAttribute(name = "mgmtKeystorePasswd")
    private String mgmtKeystorePasswd;

    @XmlAttribute(name = "mgmtTruststoreFile")
    private String mgmtTruststoreFile;

    @XmlAttribute(name = "mgmtTruststorePasswd")
    private String mgmtTruststorePasswd;

    @XmlAttribute(name = "clustered")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean clustered;

    @XmlElementWrapper(name = "counterparties")
    @XmlElementRef()
    private CounterpartyInfo[] counterparties;

    @XmlElementWrapper(name = "handlerDefs")
    @XmlElementRef()
    private HandlerDefInfo[] handlerDefs;

    @XmlElementWrapper(name = "securedMessages")
    @XmlElementRef()
    private SecuredMessageInfo[] securedMessages;

    @XmlElementWrapper(name = "alertListeners")
    @XmlElementRef()
    private ListenerInfo[] alertListeners;

    @XmlElementWrapper(name = "lifeCycleListeners")
    @XmlElementRef()
    private ListenerInfo[] lifeCycleListeners;

    @XmlElementWrapper(name = "messageListeners")
    @XmlElementRef()
    private ListenerInfo[] messageListeners;

    @XmlElementRef(required = false)
    private SchedulerInfo scheduler;

    /**
     * Only handler parameters can be reconfigured at runtime for now.
     *
     * @param newConfiguration reconfiguration values
     */
    public void reconfigure(HadesInstanceInfo newConfiguration) {
        HandlerDefInfo[] newHandlerDefs = newConfiguration.getHandlerDefs();
        for (HandlerDefInfo handlerDef : handlerDefs) {
            for (HandlerDefInfo newHandlerDef : newHandlerDefs) {
                if (handlerDef.getName().equals(newHandlerDef.getName())) {
                    List<HandlerParamInfo> newParams = new ArrayList<>(Arrays.asList(handlerDef.getParameters()));
                    handlerDef.setParameters(newParams.toArray(new HandlerParamInfo[newParams.size()]));
                    break;
                }
            }
        }
        for (CounterpartyInfo counterparty : counterparties) {
            for (CounterpartyInfo newCounterparty : newConfiguration.getCounterparties()) {
                if (PartyUtil.getID(newCounterparty).equals(PartyUtil.getID(counterparty))) {
                    counterparty.reconfigure(newCounterparty);
                    break;
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMgmtPort() {
        return mgmtPort;
    }

    public void setMgmtPort(Integer mgmtPort) {
        this.mgmtPort = mgmtPort;
    }

    public Boolean getClustered() {
        return clustered;
    }

    public void setClustered(Boolean clustered) {
        this.clustered = clustered;
    }

    public CounterpartyInfo[] getCounterparties() {
        return counterparties;
    }

    public void setCounterparties(CounterpartyInfo[] counterparties) {
        this.counterparties = counterparties;
    }

    public SecuredMessageInfo[] getSecuredMessages() {
        return securedMessages;
    }

    public void setSecuredMessages(SecuredMessageInfo[] securedMessages) {
        this.securedMessages = securedMessages;
    }

    public HandlerDefInfo[] getHandlerDefs() {
        return handlerDefs;
    }

    public void setHandlerDefs(HandlerDefInfo[] handlerDefs) {
        this.handlerDefs = handlerDefs;
    }

    public Boolean getMgmtUseAuth() {
        return mgmtUseAuth;
    }

    public void setMgmtUseAuth(Boolean mgmtUseAuth) {
        this.mgmtUseAuth = mgmtUseAuth;
    }

    public Boolean getMgmtUseSSL() {
        return mgmtUseSSL;
    }

    public void setMgmtUseSSL(Boolean mgmtUseSSL) {
        this.mgmtUseSSL = mgmtUseSSL;
    }

    public String getMgmtHost() {
        return mgmtHost;
    }

    public void setMgmtHost(String mgmtHost) {
        this.mgmtHost = mgmtHost;
    }

    public Boolean getMgmtUseSSLCliAuth() {
        return mgmtUseSSLCliAuth;
    }

    public void setMgmtUseSSLCliAuth(Boolean mgmtUseSSLCliAuth) {
        this.mgmtUseSSLCliAuth = mgmtUseSSLCliAuth;
    }

    public String getMgmtKeystoreFile() {
        return mgmtKeystoreFile;
    }

    public void setMgmtKeystoreFile(String mgmtKeystoreFile) {
        this.mgmtKeystoreFile = mgmtKeystoreFile;
    }

    public String getMgmtKeystorePasswd() {
        return mgmtKeystorePasswd;
    }

    public void setMgmtKeystorePasswd(String mgmtKeystorePasswd) {
        this.mgmtKeystorePasswd = mgmtKeystorePasswd;
    }

    public String getMgmtTruststoreFile() {
        return mgmtTruststoreFile;
    }

    public void setMgmtTruststoreFile(String mgmtTruststoreFile) {
        this.mgmtTruststoreFile = mgmtTruststoreFile;
    }

    public String getMgmtTruststorePasswd() {
        return mgmtTruststorePasswd;
    }

    public void setMgmtTruststorePasswd(String mgmtTruststorePasswd) {
        this.mgmtTruststorePasswd = mgmtTruststorePasswd;
    }

    public SchedulerInfo getScheduler() {
        return scheduler;
    }

    public void setScheduler(SchedulerInfo scheduler) {
        this.scheduler = scheduler;
    }

    public ListenerInfo[] getAlertListeners() {
        return alertListeners;
    }

    public void setAlertListeners(ListenerInfo[] alertListeners) {
        this.alertListeners = alertListeners;
    }

    public ListenerInfo[] getLifeCycleListeners() {
        return lifeCycleListeners;
    }

    public void setLifeCycleListeners(ListenerInfo[] lifeCycleListeners) {
        this.lifeCycleListeners = lifeCycleListeners;
    }

    public ListenerInfo[] getMessageListeners() {
        return messageListeners;
    }

    public void setMessageListeners(ListenerInfo[] messageListeners) {
        this.messageListeners = messageListeners;
    }

}
