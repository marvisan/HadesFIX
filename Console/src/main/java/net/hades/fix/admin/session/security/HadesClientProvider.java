/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * HadesClientProvider.java
 *
 * $Id: HadesClientProvider.java,v 1.2 2010-09-27 11:44:09 vrotaru Exp $
 */
package net.hades.fix.admin.session.security;

import java.security.Provider;

/**
 * Server SASL provider.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 19/09/2010
 */
public class HadesClientProvider extends Provider {

    public static final String REMOTE_PROFILE_SSL = "TLS";
    public static final String REMOTE_PROFILE_SASL = "SASL/HADESFIX";
    public static final String REMOTE_PROFILE_SASL_WITH_SSL = "TLS SASL/HADESFIX";

    private static final long serialVersionUID = 1L;

    public HadesClientProvider() {
	super("HadesSasl", 1.0, "HadesFIX SASL mechanism provider");
	put("SaslClientFactory.HADESFIX", "net.hades.fix.admin.session.security.HadesSaslClientFactory");
    }

}
