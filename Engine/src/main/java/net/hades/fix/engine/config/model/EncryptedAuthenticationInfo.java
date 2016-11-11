/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 * Holds the FIX session encrypted authentication data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name = "encryptAuthInfo")
@XmlType(name = "EncryptedAuthenticationInfo", propOrder = {"encryptedPassword", "encryptedNewPassword"})
@XmlAccessorType(XmlAccessType.NONE)
public class EncryptedAuthenticationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer encryptedPasswordMethod;
    private byte[] encryptedPassword;
    private byte[] encryptedNewPassword;

    public EncryptedAuthenticationInfo() {
    }

    @XmlElement(name = "encryptNewPasswd")
    public byte[] getEncryptedNewPassword() {
        return encryptedNewPassword;
    }

    public void setEncryptedNewPassword(byte[] encryptedNewPassword) {
        this.encryptedNewPassword = encryptedNewPassword;
    }

    @XmlElement(name = "encryptPasswd")
    public byte[] getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(byte[] encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @XmlAttribute(name = "encryptPasswdMethod")
    public Integer getEncryptedPasswordMethod() {
        return encryptedPasswordMethod;
    }

    public void setEncryptedPasswordMethod(Integer encryptedPasswordMethod) {
        this.encryptedPasswordMethod = encryptedPasswordMethod;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{EncryptedAuthenticationInfo[");
        if (encryptedPasswordMethod != null) {
            sb.append("encryptedPasswordMethod=").append(encryptedPasswordMethod);
        }
        sb.append("]}");
        return sb.toString();
    }
}
