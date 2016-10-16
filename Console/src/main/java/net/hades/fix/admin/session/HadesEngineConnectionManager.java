/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HadesEngineConnectionManager.java
 *
 * $Id: HadesEngineConnectionManager.java,v 1.2 2010-05-16 09:22:50 vrotaru Exp $
 */
package net.hades.fix.admin.session;

/**
 * Manager class for a single server session.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 09/05/2010
 */
public class HadesEngineConnectionManager {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final HadesEngineConnectionManager INSTANCE;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">

    static {
        INSTANCE = new HadesEngineConnectionManager();
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private HadesEngineSession session;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public static HadesEngineSession getEngineSession() throws SessionException {
        if (INSTANCE.session == null) {
            INSTANCE.session = new HadesEngineSession();
        }

        return INSTANCE.session;
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
