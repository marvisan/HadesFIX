/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UsernamePasswordCallbackHandler.java
 *
 * $Id: UsernamePasswordCallbackHandler.java,v 1.1 2010-09-22 11:54:47 vrotaru Exp $
 */
package net.hades.fix.admin.session.security;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import net.hades.fix.commons.exception.ExceptionUtil;

/**
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 19/09/2010
 */
public class UsernamePasswordCallbackHandler implements CallbackHandler {

    private static final Logger LOGGER = Logger.getLogger(UsernamePasswordCallbackHandler.class.getName());

    private String userName;
    private char[] password;
    private byte[] digest;
    
    public UsernamePasswordCallbackHandler(String userName, char[] password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] msg = String.valueOf(password).getBytes();
            md.update(msg);
            digest = md.digest();
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.log(Level.SEVERE, "Could not create a digest. Error was : {0}", ExceptionUtil.getStackTrace(ex));
            
            throw new IOException(ex);
        }
    }

    public String getUserName() {
        return userName;
    }

    public byte[] getDigest() {
        return digest;
    }

}
