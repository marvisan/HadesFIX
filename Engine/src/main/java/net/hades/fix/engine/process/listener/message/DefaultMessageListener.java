/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * DefaultMessageListener.java
 *
 * $Id: DefaultMessageListener.java,v 1.1 2011-03-21 04:54:25 vrotaru Exp $
 */
package net.hades.fix.engine.process.listener.message;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.HadesInstance;
import net.hades.fix.engine.process.event.MessageEvent;
import net.hades.fix.engine.process.listener.MessageListener;

/**
 * Default implementation of the message listener that logs only in debug mode
 * the events.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class DefaultMessageListener implements MessageListener {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    
    private static final Logger LOGGER = Logger.getLogger(DefaultMessageListener.class.getName());

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public void onMessageEvent(MessageEvent event) {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "MSG EVNT : {0}", event.toString());
        }
        HadesInstance.getOpenMBean().sendNotification(event.buildNotification());
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
