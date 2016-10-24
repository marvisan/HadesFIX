/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol.timer;

/**
 * Holder of timeout data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class Timeouts {

    private int htbtTimeout;
    private int htbtOffset;
    private int resendTimeout;
    private int testRequestTimeout;
    private int logonTimeout;
    private int logoutTimeout;

    private boolean enableResendTimeout;

    public Timeouts() {
    }

    public boolean isEnableResendTimeout() {
        return enableResendTimeout;
    }

    public void setEnableResendTimeout(boolean enableResendTimeout) {
        this.enableResendTimeout = enableResendTimeout;
    }

    public int getHtbtOffset() {
        return htbtOffset;
    }

    public void setHtbtOffset(int htbtOffset) {
        this.htbtOffset = htbtOffset;
    }

    public int getHtbtTimeout() {
        return htbtTimeout;
    }

    public void setHtbtTimeout(int htbtTimeout) {
        this.htbtTimeout = htbtTimeout;
    }

    public int getLogonTimeout() {
        return logonTimeout;
    }

    public void setLogonTimeout(int logonTimeout) {
        this.logonTimeout = logonTimeout;
    }

    public int getLogoutTimeout() {
        return logoutTimeout;
    }

    public void setLogoutTimeout(int logoutTimeout) {
        this.logoutTimeout = logoutTimeout;
    }

    public int getResendTimeout() {
        return resendTimeout;
    }

    public void setResendTimeout(int resendTimeout) {
        this.resendTimeout = resendTimeout;
    }

    public int getTestRequestTimeout() {
        return testRequestTimeout;
    }

    public void setTestRequestTimeout(int testRequestTimeout) {
        this.testRequestTimeout = testRequestTimeout;
    }
}
