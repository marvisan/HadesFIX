/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * HadesSaslServerFactory.java
 *
 * $Id: HadesSaslClientFactory.java,v 1.1 2010-09-22 11:54:47 vrotaru Exp $
 */
package net.hades.fix.admin.session.security;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.callback.Callback;

import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslClientFactory;
import javax.security.sasl.SaslException;

/**
 * HadesFIX SASL server factory.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 19/09/2010
 */
public class HadesSaslClientFactory implements SaslClientFactory {

    private static final Logger LOGGER = Logger.getLogger(HadesSaslClientFactory.class.getName());

    @Override
    public SaslClient createSaslClient(String[] mechanisms, String authorizationId, String protocol, String serverName,
            Map<String, ?> props, CallbackHandler cbh) throws SaslException {
        UsernamePasswordCallbackHandler uncbh = (UsernamePasswordCallbackHandler) cbh;
        try {
            cbh.handle(new Callback[0]);
        } catch (IOException ex) {
            throw new SaslException("Could not execute the callback handler", ex);
        } catch (UnsupportedCallbackException ex) {
            throw new SaslException("Callback not supported", ex);
        }
        for (String mechanism : mechanisms) {
            if (mechanism.equals(HadesSaslClient.MECHANISM)) {
                if (LOGGER.isLoggable(Level.FINEST)) {
                    LOGGER.finest("Mechanism found : " + HadesSaslClient.MECHANISM);
                }
                return new HadesSaslClient(uncbh.getUserName(), uncbh.getDigest());
            }
        }

        return null;
    }

    @Override
    public String[] getMechanismNames(Map<String, ?> props) {
        return new String[] {HadesSaslClient.MECHANISM};
    }
}
