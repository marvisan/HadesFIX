/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import net.hades.fix.engine.config.xml.adapter.BooleanAdapter;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * SSL configuration data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name = "sslData")
@XmlType(name = "SSLInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class SSLInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name="keyStoreLoc", required=true)
    private String keyStoreLoc;

    @XmlAttribute(name="keyStorePasswd", required=true)
    private String keyStorePasswd;

    @XmlAttribute(name="keyPasswd")
    private String keyPasswd;

    @XmlAttribute(name="trustStoreLoc")
    private String trustStoreLoc;

    @XmlAttribute(name="trustStorePasswd")
    private String trustStorePasswd;

    @XmlAttribute(name="useCliAuth")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean useCliAuth;

    public SSLInfo() {
        useCliAuth = Boolean.FALSE;
    }

    public String getKeyPasswd() {
        return keyPasswd;
    }

    public void setKeyPasswd(String keyPasswd) {
        this.keyPasswd = keyPasswd;
    }

    public String getKeyStoreLoc() {
        return keyStoreLoc;
    }

    public void setKeyStoreLoc(String keyStoreLoc) {
        this.keyStoreLoc = keyStoreLoc;
    }

    public String getKeyStorePasswd() {
        return keyStorePasswd;
    }

    public void setKeyStorePasswd(String keyStorePasswd) {
        this.keyStorePasswd = keyStorePasswd;
    }

    public String getTrustStoreLoc() {
        return trustStoreLoc;
    }

    public void setTrustStoreLoc(String trustStoreLoc) {
        this.trustStoreLoc = trustStoreLoc;
    }

    public String getTrustStorePasswd() {
        return trustStorePasswd;
    }

    public void setTrustStorePasswd(String trustStorePasswd) {
        this.trustStorePasswd = trustStorePasswd;
    }

    public Boolean isUseCliAuth() {
        return useCliAuth;
    }

    public void setUseCliAuth(Boolean useCliAuth) {
        this.useCliAuth = useCliAuth;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{SSLInfo[");
        sb.append("keyStoreLoc=").append(keyStoreLoc);
        if (trustStoreLoc != null) {
            sb.append(",trustStoreLoc=").append(trustStoreLoc);
        }
        if (useCliAuth != null) {
            sb.append(",useCliAuth=").append(useCliAuth);
        }
        sb.append("]}");
        return sb.toString();
    }
}
