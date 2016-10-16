/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Engine.java
 *
 * $Id: Engine.java,v 1.3 2010-10-08 08:43:13 vrotaru Exp $
 */
package net.hades.fix.engine;

import net.hades.fix.engine.process.session.Coordinable;

import java.util.List;

/**
 * Restricted interface available to Handler developers.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 */
public interface Engine {
    /**
     * Return a list with the configured session coordinators.
     * @return list of session coordinators
     */
    List<Coordinable> getSessionCoordinators();
}
