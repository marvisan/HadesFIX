/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CounterpartyAddress.java
 *
 * $Id: CounterpartyAddress.java,v 1.2 2011-03-22 11:53:53 vrotaru Exp $
 */
package net.hades.fix.engine.model;

import net.hades.fix.engine.util.PartyUtil;

import java.io.Serializable;

/**
 * Counterparty FIX address.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 */
public class CounterpartyAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    private String compID;

    private String subID;

    private String locationID;

    public CounterpartyAddress() {
    }

    public CounterpartyAddress(String compID, String subID, String locationID) {
        this.compID = compID;
        this.subID = subID;
        this.locationID = locationID;
    }

    public String getCompID() {
        return compID;
    }

    public void setCompID(String compID) {
        this.compID = compID;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getSubID() {
        return subID;
    }

    public void setSubID(String subID) {
        this.subID = subID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CounterpartyAddress other = (CounterpartyAddress) obj;
        if ((this.compID == null) ? (other.compID != null) : !this.compID.equals(other.compID)) {
            return false;
        }
        if ((this.subID == null) ? (other.subID != null) : !this.subID.equals(other.subID)) {
            return false;
        }
        return !((this.locationID == null) ? (other.locationID != null) : !this.locationID.equals(other.locationID));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.compID != null ? this.compID.hashCode() : 0);
        hash = 83 * hash + (this.subID != null ? this.subID.hashCode() : 0);
        hash = 83 * hash + (this.locationID != null ? this.locationID.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(compID);
        if (subID != null) {
            sb.append(".").append(subID);
        }
        if (locationID != null) {
            sb.append(".").append(locationID);
        }

        return sb.toString();
    }

    public String getID() {
        return PartyUtil.getID(compID, subID, locationID);
    }
}
