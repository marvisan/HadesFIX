/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HadesSaslClient.java
 *
 * $Id: HadesSaslClient.java,v 1.2 2010-09-27 11:44:09 vrotaru Exp $
 */
package net.hades.fix.admin.session.security;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.security.sasl.Sasl;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;

/**
 * Hades SASL client implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 19/09/2010
 */
public class HadesSaslClient implements SaslClient {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final Logger LOGGER = Logger.getLogger(HadesSaslClient.class.getName());

    public static final String MECHANISM = "HADESFIX";

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private byte[] username;
    private boolean completed;
    private byte[] digest;
    private boolean userSent;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public HadesSaslClient(String authorizationID, byte[] digest) throws SaslException {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "authorizationID={0}", authorizationID);
        }
        if (authorizationID != null) {
            try {
                username = authorizationID.getBytes("UTF8");
            } catch (UnsupportedEncodingException e) {
                throw new SaslException("Cannot convert " + authorizationID + " into UTF-8", e);
            }
        } else {
            username = new byte[0];
        }
        this.digest = digest;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public String getMechanismName() {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "MECHANISM={0}", MECHANISM);
        }
        return MECHANISM;
    }

    @Override
    public boolean hasInitialResponse() {
        // this forces the server yo call evaluateChallenge
        return true;
    }

    @Override
    public byte[] evaluateChallenge(byte[] challengeData) throws SaslException {
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "challengeData={0} length={1}", new String[] {String.valueOf(challengeData), String.valueOf(challengeData.length)});
        }

        if (completed) {
            throw new IllegalStateException("Already completed.");
        }
        byte[] result = null;
        if (!userSent) {
            // first we send the user ID
            result = username;
            userSent = true;
        } else {
            // second we send the password digest
            result = digest;
            completed = true;
        }

        return result;
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
            LOGGER.log(Level.FINEST, "dispose called");
        }
        username = null;
        digest = null;
        completed = false;
        userSent = false;
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
