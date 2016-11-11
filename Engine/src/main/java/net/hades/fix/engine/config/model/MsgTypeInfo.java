/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.engine.config.xml.adapter.BooleanAdapter;

/**
 * Message type supported for a certain version. Used in Logon messages.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name = "msgType")
@XmlType(name = "MsgTypeInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class MsgTypeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String refMsgType;
    private String msgDirection;
    private String refApplVerID;
    private Integer refApplExtID;
    private String refCstmApplVerID;
    private Boolean defaultVerIndicator;

    public MsgTypeInfo() {
    }

    @XmlAttribute(name = "msgDirection")
    public String getMsgDirection() {
        return msgDirection;
    }

    public void setMsgDirection(String msgDirection) {
        this.msgDirection = msgDirection;
    }

    @XmlAttribute(name = "refApplVerID")
    public String getRefApplVerID() {
        return refApplVerID;
    }

    public void setRefApplVerID(String refApplVerID) {
        this.refApplVerID = refApplVerID;
    }

    @XmlAttribute(name = "refCstmApplVerID")
    public String getRefCstmApplVerID() {
        return refCstmApplVerID;
    }

    public void setRefCstmApplVerID(String refCstmApplVerID) {
        this.refCstmApplVerID = refCstmApplVerID;
    }

    @XmlAttribute(name = "refMsgType", required = true)
    public String getRefMsgType() {
        return refMsgType;
    }

    public void setRefMsgType(String refMsgType) {
        this.refMsgType = refMsgType;
    }

    @XmlAttribute(name = "defaultVerIndicator")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    public Boolean getDefaultVerIndicator() {
        return defaultVerIndicator;
    }

    public void setDefaultVerIndicator(Boolean defaultVerIndicator) {
        this.defaultVerIndicator = defaultVerIndicator;
    }

    @XmlAttribute(name = "refApplExtID")
    public Integer getRefApplExtID() {
        return refApplExtID;
    }

    public void setRefApplExtID(Integer refApplExtID) {
        this.refApplExtID = refApplExtID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{MsgTypeInfo[");
        if (refMsgType != null) {
            sb.append("refMsgType=").append(refMsgType);
        }
        if (msgDirection != null) {
            sb.append("msgDirection=").append(msgDirection);
        }
        if (refApplVerID != null) {
            sb.append("refApplVerID=").append(refApplVerID);
        }
        if (refApplExtID != null) {
            sb.append(",").append("refApplExtID=").append(refApplExtID);
        }
        if (refCstmApplVerID != null) {
            sb.append(",").append("refCstmApplVerID=").append(refCstmApplVerID);
        }
        if (defaultVerIndicator != null) {
            sb.append(",").append("defaultVerIndicator=").append(defaultVerIndicator);
        }
        sb.append("]}");
        return sb.toString();
    }
}
