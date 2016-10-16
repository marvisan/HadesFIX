
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

import net.hades.fix.engine.util.PartyUtil;

/**
 * Counterparty information for a FIX session connection. For a server session
 * the Counterparty is the source of the FIX messages. For a client session a counterparty
 * is the target to which the messages are sent.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name = "counterparty")
@XmlType(name = "CounterpartyInfo", propOrder = {"sessions", "handlerDefs", "securedMessages"})
@XmlAccessorType(XmlAccessType.NONE)
public class CounterpartyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String compID;
    private String subID;
    private String locationID;
    private String side;
    private SessionInfo[] sessions;
    private HandlerDefInfo[] handlerDefs;
    private SecuredMessageInfo[] securedMessages;

    public CounterpartyInfo() {
    }

    public CounterpartyInfo(String compID) {
        this.compID = compID;
    }

    /**
     * Only handler parameters can be reconfigured at runtime for now.
     *
     * @param newConfiguration
     */
    public void reconfigure(CounterpartyInfo newConfiguration) {
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
        for (SessionInfo session : sessions) {
            for (SessionInfo newSession : newConfiguration.getSessions()) {
                if (PartyUtil.getID(newSession).equals(PartyUtil.getID(session))) {
                    session.reconfigure(newSession);
                }
            }
        }
    }

    @XmlAttribute(name = "compID", required = true)
    public String getCompID() {
        return compID;
    }

    public void setCompID(String compID) {
        this.compID = compID;
    }

    @XmlAttribute(name = "locationID")
    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    @XmlAttribute(name = "subID")
    public String getSubID() {
        return subID;
    }

    public void setSubID(String subID) {
        this.subID = subID;
    }

    @XmlAttribute(name = "side")
    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @XmlElementWrapper(name = "sessions")
    @XmlElementRef()
    public SessionInfo[] getSessions() {
        return sessions;
    }

    public void setSessions(SessionInfo[] session) {
        this.sessions = session;
    }

    @XmlElementWrapper(name = "securedMessages")
    @XmlElementRef()
    public SecuredMessageInfo[] getSecuredMessages() {
        return securedMessages;
    }

    public void setSecuredMessages(SecuredMessageInfo[] securedMessages) {
        this.securedMessages = securedMessages;
    }

    @XmlElementWrapper(name = "handlerDefs")
    @XmlElementRef()
    public HandlerDefInfo[] getHandlerDefs() {
        return handlerDefs;
    }

    public void setHandlerDefs(HandlerDefInfo[] handlerDefs) {
        this.handlerDefs = handlerDefs;
    }

    public String getID() {
        return PartyUtil.getID(compID, subID, locationID);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{CounterpartyInfo[");
        sb.append("compID=").append(compID);
        if (subID != null) {
            sb.append(",").append("subID=").append(subID);
        }
        if (locationID != null) {
            sb.append(",").append("locationID=").append(locationID);
        }
        if (side != null) {
            sb.append(",").append("side=").append(side);
        }
        if (sessions != null && sessions.length > 0) {
            for (SessionInfo session : sessions) {
                sb.append("session=").append(session.toString()).append("\n");
            }
        }
        sb.append("]}");

        return sb.toString();
    }
}
