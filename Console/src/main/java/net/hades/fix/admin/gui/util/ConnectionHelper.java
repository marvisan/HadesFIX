/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ConnectionHelper.java
 *
 * $Id$
 */
package net.hades.fix.admin.gui.util;

import net.hades.fix.admin.console.exception.InputValidationException;
import net.hades.fix.admin.session.HadesEngineSession;
import net.hades.fix.admin.session.SessionException;
import net.hades.fix.admin.gui.config.model.EngineConnectionInfo;
import net.hades.fix.admin.gui.resources.Messages;
import net.hades.fix.admin.session.SessionData;

import net.hades.fix.commons.security.PasswordBank;

/**
 * Creates a HadesFIX engine connection given connection configuration parameters.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision$
 */
public class ConnectionHelper {

    private ConnectionHelper() {
    }
    
    public static HadesEngineSession connectToEngine(EngineConnectionInfo connectionInfo) throws InputValidationException, SessionException {
        validateConnectionInfo(connectionInfo);
        SessionData sessionData = new SessionData();
        sessionData.setRemoteHost(connectionInfo.getHost());
        sessionData.setRemotePort(connectionInfo.getPort() != null ? connectionInfo.getPort().toString() : "0");
        if (connectionInfo.getAuthEnabled() != null && connectionInfo.getAuthEnabled().booleanValue()) {
            sessionData.setUseAuth(true);
            sessionData.setUsername(connectionInfo.getUser());
            sessionData.setPassword(PasswordBank.getInstance().getEntryValue(connectionInfo.getUser()));
        }
        if (connectionInfo.getSslEnabled() != null && connectionInfo.getSslEnabled().booleanValue()) {
            sessionData.setUseSSL(true);
            char[] ksPassword = PasswordBank.getInstance().getEntryValue(connectionInfo.getSslUser());
            sessionData.setSslKeystorePasswd(ksPassword);
            sessionData.setSslTruststorePasswd(ksPassword);
            if (connectionInfo.getSslCliAuthEnabled() != null && connectionInfo.getSslCliAuthEnabled().booleanValue()) {
                sessionData.setUseSSLCliAuth(true);
                if (connectionInfo.getKeystorePath() != null && !connectionInfo.getKeystorePath().isEmpty()) {
                    sessionData.setSslKeystore(connectionInfo.getKeystorePath());
                }
            }
            if (connectionInfo.getTruststorePath() != null && !connectionInfo.getTruststorePath().isEmpty()) {
                sessionData.setSslTruststore(connectionInfo.getTruststorePath());
            }
        }
        HadesEngineSession session = new HadesEngineSession(sessionData);
        session.connect();
        
        return session;
    }

    public static void validateConnectionInfo(EngineConnectionInfo connectionInfo) throws InputValidationException {
        if (connectionInfo.getHost() == null || connectionInfo.getHost().isEmpty()) {
            connectionInfo.setHost("localhost");
        }
        if (connectionInfo.getPort() == null) {
            connectionInfo.setPort(33333);
        }
        if (connectionInfo.getAuthEnabled() && (connectionInfo.getUser() == null || connectionInfo.getUser().isEmpty())) {
            throw new InputValidationException(Messages.getString("MaintainConnectionView.ErrMsg.validation.user.text"));
        }
        if (connectionInfo.getSslEnabled() && (connectionInfo.getSslUser() == null || connectionInfo.getSslUser().isEmpty())) {
            throw new InputValidationException(Messages.getString("MaintainConnectionView.ErrMsg.validation.sslUser.text"));
        }
        if (connectionInfo.getSslEnabled() && connectionInfo.getSslCliAuthEnabled() && (connectionInfo.getKeystorePath() == null || connectionInfo.getKeystorePath().isEmpty())) {
            throw new InputValidationException(Messages.getString("MaintainConnectionView.ErrMsg.validation.keystore.text"));
        }
        if (connectionInfo.getSslEnabled() && (connectionInfo.getTruststorePath() == null || connectionInfo.getTruststorePath().isEmpty())) {
            throw new InputValidationException(Messages.getString("MaintainConnectionView.ErrMsg.validation.truststore.text"));
        }
    }

}
