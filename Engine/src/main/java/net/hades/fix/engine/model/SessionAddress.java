/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SessionAddress.java
 *
 * $Id: SessionAddress.java,v 1.4 2010-10-08 08:43:14 vrotaru Exp $
 */
package net.hades.fix.engine.model;

import java.io.Serializable;

/**
 * Defines the session address.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 */
public class SessionAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    private CounterpartyAddress remoteAddress;

    private CounterpartyAddress localAddress;

    public SessionAddress() {
    }

    public SessionAddress(CounterpartyAddress remoteAddress, CounterpartyAddress localAddress) {
        this.remoteAddress = remoteAddress;
        this.localAddress = localAddress;
    }

    public CounterpartyAddress getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(CounterpartyAddress localAddress) {
        this.localAddress = localAddress;
    }

    public CounterpartyAddress getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(CounterpartyAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

//    public void reverse() {
//        CounterpartyAddress remoteCopy = remoteAddress;
//        remoteAddress = localAddress;
//        localAddress = remoteCopy;
//    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SessionAddress other = (SessionAddress) obj;
        return !(this.remoteAddress != other.remoteAddress && (this.remoteAddress == null || !this.remoteAddress.equals(other.remoteAddress))) && !(this.localAddress != other.localAddress && (this.localAddress == null || !this.localAddress.equals(other.localAddress)));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.remoteAddress != null ? this.remoteAddress.hashCode() : 0);
        hash = 89 * hash + (this.localAddress != null ? this.localAddress.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (remoteAddress != null) {
            sb.append(remoteAddress.getID());
        }
        if (localAddress != null) {
            sb.append(":").append(localAddress.getID());
        }

        return sb.toString();
    }
}
