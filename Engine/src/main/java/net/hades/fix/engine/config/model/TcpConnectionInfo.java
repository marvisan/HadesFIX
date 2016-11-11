/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.engine.config.xml.adapter.BooleanAdapter;

/**
 * Connection information.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlType(name = "TcpConnectionInfo")
@XmlAccessorType(XmlAccessType.NONE)
public abstract class TcpConnectionInfo extends ConnectionInfo {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name="host")
    protected String host;

    @XmlAttribute(name="port", required = true)
    protected int port;

    @XmlAttribute(name = "rxBufferSize")
    protected Integer rxBufferSize;

    @XmlAttribute(name = "txBufferSize")
    protected Integer txBufferSize;
    
    @XmlAttribute(name = "soLinger")
    protected Integer socketLingerTimeout;

    @XmlAttribute(name = "tcpNodelay")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean tcpNodelay;

    @XmlAttribute(name = "soTimeout")
    protected Integer socketTimeout;

    @XmlAttribute(name = "soRcvbuf")
    protected Integer socketRcvbuf;

    @XmlAttribute(name = "soSndbuf")
    protected Integer socketSndbuf;

    @XmlAttribute(name = "sendKeepAlive")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    protected Boolean sendKeepAlive;

    @XmlElement(name = "sslData")
    protected SSLInfo sslData;

    public TcpConnectionInfo() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Boolean getTcpNodelay() {
        return tcpNodelay;
    }

    public void setTcpNodelay(Boolean tcpNodelay) {
        this.tcpNodelay = tcpNodelay;
    }

    public Integer getSocketLingerTimeout() {
        return socketLingerTimeout;
    }

    public void setSocketLingerTimeout(Integer socketLingerTimeout) {
        this.socketLingerTimeout = socketLingerTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public Integer getSocketRcvbuf() {
        return socketRcvbuf;
    }

    public void setSocketRcvbuf(Integer socketRcvbuf) {
        this.socketRcvbuf = socketRcvbuf;
    }

    public Integer getSocketSndbuf() {
        return socketSndbuf;
    }

    public void setSocketSndbuf(Integer socketSndbuf) {
        this.socketSndbuf = socketSndbuf;
    }

    public Boolean getSendKeepAlive() {
        return sendKeepAlive;
    }

    public void setSendKeepAlive(Boolean sendKeepAlive) {
        this.sendKeepAlive = sendKeepAlive;
    }

    public SSLInfo getSslData() {
        return sslData;
    }

    public void setSslData(SSLInfo sslData) {
        this.sslData = sslData;
    }

    public Integer getTxBufferSize() {
        return txBufferSize;
    }

    public void setTxBufferSize(Integer txBufferSize) {
        this.txBufferSize = txBufferSize;
    }

    public Integer getRxBufferSize() {
        return rxBufferSize;
    }

    public void setRxBufferSize(Integer rxBufferSize) {
        this.rxBufferSize = rxBufferSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{TcpConnectionInfo[");
	sb.append("id=").append(id);
        if (host != null) {
            sb.append("host=").append(host);
        }
        sb.append(",").append("port=").append(port);
        if (tcpNodelay != null) {
            sb.append(",").append("tcpNodelay=").append(tcpNodelay);
        }
        if (socketLingerTimeout != null) {
            sb.append(",").append("socketLingerTimeout=").append(socketLingerTimeout);
        }
        if (rxBufferSize != null) {
            sb.append(",").append("rxBufferSize=").append(rxBufferSize);
        }
        if (txBufferSize != null) {
            sb.append(",").append("txBufferSize=").append(txBufferSize);
        }
        if (tcpNodelay != null) {
            sb.append(",").append("tcpNodelay=").append(tcpNodelay);
        }
        if (socketTimeout != null) {
            sb.append(",").append("socketTimeout=").append(socketTimeout);
        }
        if (socketRcvbuf != null) {
            sb.append(",").append("socketRcvbuf=").append(socketRcvbuf);
        }
        if (socketSndbuf != null) {
            sb.append(",").append("socketSndbuf=").append(socketSndbuf);
        }
        if (sendKeepAlive != null) {
            sb.append(",").append("sendKeepAlive=").append(sendKeepAlive);
        }
        if (sslData != null) {
            sb.append(",").append("sslData=").append(sslData.toString());
        }
        sb.append("]}");
        return sb.toString();
    }
}
