/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HadesSaslServer.java
 *
 * $Id: HadesSaslServer.java,v 1.2 2010-09-27 11:44:22 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.sasl.Sasl;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;

import net.hades.fix.commons.exception.ExceptionUtil;
import net.hades.fix.commons.security.PasswordBank;

/**
 * SASL HadesFIX server implementation.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 19/09/2010
 */
public class HadesSaslServer implements SaslServer {

    private final Logger LOGGER = Logger.getLogger(HadesSaslServer.class.getName());

    public static final String MECHANISM = "HADESFIX";

    private String username;
    private boolean completed;
    private boolean userIdRcvd;

    public HadesSaslServer() {
    }

    @Override
    public String getMechanismName() {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Mechanism={0}", MECHANISM);
        }

        return MECHANISM;
    }

    @Override
    public byte[] evaluateResponse(byte[] responseData) throws SaslException {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "responseData={0}", (responseData != null ? new String(responseData) : "null"));
        }

        if (completed) {
            throw new IllegalStateException("Already completed");
        }

        if (!userIdRcvd) {
            username = new String(responseData);
            userIdRcvd = true;
        } else {
            if (!isDigestValid(responseData)) {
                throw new SaslException("Bad user name or credential.");
            }
            completed = true;
        }
        
        return new byte[0];
    }

    @Override
    public String getAuthorizationID() {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "username={0}", username);
        }

        return username;
    }

    @Override
    public boolean isComplete() {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "completed={0}", completed);
        }

        return completed;
    }

    @Override
    public byte[] unwrap(byte[] incoming, int offset, int len) throws SaslException {
        throw new SaslException("No negotiated security layer");
    }

    @Override
    public byte[] wrap(byte[] outgoing, int offset, int len) throws SaslException {
        throw new SaslException("No negotiated security layer");
    }

    @Override
    public Object getNegotiatedProperty(String propName) {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "propName={0}", propName);
        }
        if (propName.equals(Sasl.QOP)) {
            return "auth";
        }
        return null;
    }

    @Override
    public void dispose() throws SaslException {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "dispose");
        }
        username = null;
        completed = false;
        userIdRcvd = false;
    }

    private boolean isDigestValid(byte[] digest) throws SaslException {
        boolean result = false;
        char[] secret = PasswordBank.getInstance().getEntryValue(username);
        if (secret != null) {
            if (doesSecretsMatch(digest, getDigest(String.valueOf(secret)))) {
                result = true;
            }
        }

        return result;
    }

    private byte[] getDigest(String secret) throws SaslException {
        byte[] digest = null;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] msg = String.valueOf(secret).getBytes();
            md.update(msg);
            digest = md.digest();
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.log(Level.SEVERE, "Could not create a digest. Error was : {0}", ExceptionUtil.getStackTrace(ex));

            throw new SaslException("Could not create a digest for secret", ex);
        }

        return digest;
    }

    private boolean doesSecretsMatch(byte[] clientDigest, byte[] serverDigest) {
        boolean result = true;
        if (clientDigest == null || serverDigest == null) {
            result = false;
        } else {
            if (clientDigest.length != serverDigest.length) {
                result = false;
            } else {
                for (int i = 0; i < serverDigest.length; i++) {
                    if (clientDigest[i] != serverDigest[i]) {
                        result = false;
                        break;
                    }
                }
            }
        }

        return result;
    }
}
