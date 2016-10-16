/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HadesServerProvider.java
 *
 * $Id: HadesServerProvider.java,v 1.2 2010-09-27 11:44:22 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt.security;

import java.security.Provider;

/**
 * Server SASL provider.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 */
public class HadesServerProvider extends Provider {

    public static final String REMOTE_PROFILE_SSL = "TLS";
    public static final String REMOTE_PROFILE_SASL = "SASL/HADESFIX";
    public static final String REMOTE_PROFILE_SASL_WITH_SSL = "TLS SASL/HADESFIX";

    private static final long serialVersionUID = 1L;

    public HadesServerProvider() {
	super("HadesSasl", 1.0, "HadesFIX SASL mechanism provider");
	put("SaslServerFactory.HADESFIX", "net.hades.fix.engine.mgmt.security.HadesSaslServerFactory");
    }

}
