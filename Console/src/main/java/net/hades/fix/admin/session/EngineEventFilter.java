/*
 *   Copyright (c) 2006-2012 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EngineEventFilter.java
 *
 * $Id$
 */
package net.hades.fix.admin.session;

import javax.management.NotificationFilterSupport;

/**
 * Notification filter.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class EngineEventFilter extends NotificationFilterSupport {
    private static final long serialVersionUID = 1L;

    public EngineEventFilter() {
        enableType(EngineEventListener.ALERT_NOTIFICATION);
    }

}
