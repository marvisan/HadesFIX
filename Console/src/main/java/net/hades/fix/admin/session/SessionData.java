/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */

/*
 * SessionData.java
 *
 * $Id: SessionData.java,v 1.2 2010-09-18 10:47:38 vrotaru Exp $
 */
package net.hades.fix.admin.session;

/**
 * JMX remote connection session data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 08/05/2010
 */
public class SessionData {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">

    private boolean useSSL;
    private String sslKeystore;
    private char[] sslKeystorePasswd;
    private boolean useSSLCliAuth;
    private String sslTruststore;
    private char[] sslTruststorePasswd;
    private boolean useAuth;
    private String username;
    private char[] password;
    private String remoteHost;
    private String remotePort;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SessionData() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public String getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(String remotePort) {
        this.remotePort = remotePort;
    }

    public boolean isUseAuth() {
        return useAuth;
    }

    public void setUseAuth(boolean useAuth) {
        this.useAuth = useAuth;
    }

    public boolean isUseSSL() {
        return useSSL;
    }

    public void setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isUseSSLCliAuth() {
        return useSSLCliAuth;
    }

    public void setUseSSLCliAuth(boolean useSSLCliAuth) {
        this.useSSLCliAuth = useSSLCliAuth;
    }

    public String getSslKeystore() {
        return sslKeystore;
    }

    public void setSslKeystore(String sslKeystore) {
        this.sslKeystore = sslKeystore;
    }

    public char[] getSslKeystorePasswd() {
        return sslKeystorePasswd;
    }

    public void setSslKeystorePasswd(char[] sslKeystorePasswd) {
        this.sslKeystorePasswd = sslKeystorePasswd;
    }

    public String getSslTruststore() {
        return sslTruststore;
    }

    public void setSslTruststore(String sslTruststore) {
        this.sslTruststore = sslTruststore;
    }

    public char[] getSslTruststorePasswd() {
        return sslTruststorePasswd;
    }

    public void setSslTruststorePasswd(char[] sslTruststorePasswd) {
        this.sslTruststorePasswd = sslTruststorePasswd;
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
