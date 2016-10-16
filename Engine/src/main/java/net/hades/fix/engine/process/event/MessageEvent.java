/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DataEvent.java
 *
 * $Id: MessageEvent.java,v 1.2 2010-04-18 12:04:24 vrotaru Exp $
 */
package net.hades.fix.engine.process.event;

import net.hades.fix.engine.util.PartyUtil;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import javax.management.Notification;

import net.hades.fix.message.Message;

/**
 * Data message circulated between the processes. Can be an encoded binary array message or a decoded Hades
 * FIX message. The usage of this class is restricted in the sense that only one of the fields can be populated
 * and at least one of the fields must be not null;
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 */
public class MessageEvent extends EventObject {

    private static final long serialVersionUID = 1L;
    
    public static final String MESSAGE_RAISED = "hadesfix.message";
    
    private Message protocolMessage;
     
    private String session;

    /**
     * Creates a binary data event.
     * @param source source of the data event
     */
    public MessageEvent(Object source) {
        super(source);
        // we need the session decoded here just in case the thread logging the alert dies
        session = PartyUtil.getSessionId(getSource());
    }

    /**
     * Creates a Hades FIX data event.
     * @param source source of the data event
     * @param message FIX decoded message.
     */
    public MessageEvent(Object source, Message message) {
        this(source);
        this.protocolMessage = message;
    }

    public Message getProtocolMessage() {
        return protocolMessage;
    }
    
    public Notification buildNotification() {
        Notification result = new Notification(MESSAGE_RAISED, source, NotificationSequenceGenerator.getInstance().getNextSequence(), System.currentTimeMillis());
        result.setSource(null);
        result.setUserData(populateUserData());
        
        return result;
    }

    @Override
    public String toString() {
        String result = super.toString();
        if (protocolMessage != null) {
            result = protocolMessage.toString();
        }

        return result;
    }
         
    private Object populateUserData() {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("session", session);
        if (protocolMessage != null) {
            userData.put("protocolMessage", protocolMessage.toString());
        }
        
        return userData;
    }

}
