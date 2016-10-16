/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import java.io.Serializable;
import javax.xml.bind.annotation.*;

/**
 * Session encryption information.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name = "encryption")
@XmlType(name = "EncryptionInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class EncryptionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String encryptionType;
    private String encryptImplClass;
    private String encryptionKeyRing;
    private String encryptionSymKey;
    private String encryptionPubKey;
    private String encryptionPrvtKey;

    public EncryptionInfo() {
    }

    @XmlAttribute(name = "encryptionType")
    public String getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(String encryptionType) {
        this.encryptionType = encryptionType;
    }

    @XmlAttribute(name = "encryptImplClass")
    public String getEncryptImplClass() {
        return encryptImplClass;
    }

    public void setEncryptImplClass(String encryptImplClass) {
        this.encryptImplClass = encryptImplClass;
    }

    @XmlAttribute(name = "encryptionKeyRing")
    public String getEncryptionKeyRing() {
        return encryptionKeyRing;
    }

    public void setEncryptionKeyRing(String encryptionKeyRing) {
        this.encryptionKeyRing = encryptionKeyRing;
    }

    @XmlAttribute(name = "encryptionSymKey")
    public String getEncryptionSymKey() {
        return encryptionSymKey;
    }

    public void setEncryptionSymKey(String encryptionSymKey) {
        this.encryptionSymKey = encryptionSymKey;
    }

    @XmlAttribute(name = "encryptionPrvtKey")
    public String getEncryptionPrvtKey() {
        return encryptionPrvtKey;
    }

    public void setEncryptionPrvtKey(String encryptionPrvtKey) {
        this.encryptionPrvtKey = encryptionPrvtKey;
    }

    @XmlAttribute(name = "encryptionPubKey")
    public String getEncryptionPubKey() {
        return encryptionPubKey;
    }

    public void setEncryptionPubKey(String encryptionPubKey) {
        this.encryptionPubKey = encryptionPubKey;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{EncryptionInfo[");
        if (encryptionType != null) {
            sb.append("encryptionType=").append(encryptionType);
        }
        if (encryptImplClass != null) {
            sb.append(",").append("encryptImplClass=").append(encryptImplClass);
        }
        sb.append("]}");
        return sb.toString();
    }

}
