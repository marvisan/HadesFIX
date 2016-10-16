/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LifeCycleType.java
 *
 * $Id: LifeCycleType.java,v 1.4 2010-06-15 11:58:18 vrotaru Exp $
 */
package net.hades.fix.engine.process.event.type;

/**
 * Life cycle event code category.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 10/07/2009, 6:12:28 PM
 */
public enum LifeCycleType {

    CLIENT_TCP_CONN, 
    SERVER_TCP_CONN,
    PROTOCOL_CLIENT,
    PROTOCOL_SERVER;

    private static final long serialVersionUID = 1L;
}
