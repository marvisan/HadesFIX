/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * GenericEventProcessor.java
 *
 * $Id: GenericEventProcessor.java,v 1.2 2010-10-24 09:42:06 vrotaru Exp $
 */
package net.hades.fix.engine.process.event;

import net.hades.fix.engine.process.listener.AlertListener;
import net.hades.fix.engine.process.listener.LifeCycleListener;
import net.hades.fix.engine.process.listener.MessageListener;

/**
 * Event processor that only relay the events to its listeners.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 */
public class GenericEventProcessor extends EventProcessor {

    public GenericEventProcessor(String name) {
        super(name);
    }

    @Override
    protected void processAlertEvent(AlertEvent event) {
        if (event != null) {
            for (AlertListener alertListener : alertListeners) {
                alertListener.onAlertEvent(event);
            }
        }
    }

    @Override
    protected void processLifeCycleEvent(LifeCycleEvent event) {
        if (event != null) {
            for (LifeCycleListener lifeCycleListener : lifeCycleListeners) {
                lifeCycleListener.onLifeCycleEvent(event);
            }
        }
    }

    @Override
    protected void processMessageEvent(MessageEvent event) {
        if (event != null) {
            for (MessageListener messageListener : messageListeners) {
                messageListener.onMessageEvent(event);
            }
        }
    }
}
