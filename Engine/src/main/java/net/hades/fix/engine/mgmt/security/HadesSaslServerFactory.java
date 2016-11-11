/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HadesSaslServerFactory.java
 *
 * $Id: HadesSaslServerFactory.java,v 1.2 2010-09-27 11:44:22 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt.security;

import java.util.Map;
import java.util.logging.Logger;

import javax.security.auth.callback.CallbackHandler;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import javax.security.sasl.SaslServerFactory;

/**
 * HadesFIX SASL server factory.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 */
public class HadesSaslServerFactory implements SaslServerFactory {

    private final Logger LOGGER = Logger.getLogger(HadesSaslServerFactory.class.getName());

    @Override
    public SaslServer createSaslServer(String mechanism, String protocol, String serverName, Map<String, ?> props, CallbackHandler cbh)
            throws SaslException {
        if (HadesSaslServer.MECHANISM.equals(mechanism)) {
            return new HadesSaslServer();
        }

        return null;
    }

    @Override
    public String[] getMechanismNames(Map<String, ?> props) {
        return new String[] {HadesSaslServer.MECHANISM};
    }
}
