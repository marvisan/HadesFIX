/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ProtocolVersion.java
 *
 * $Id: ProtocolVersion.java,v 1.2 2010-06-27 02:46:08 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;

/**
 * Holds current session FIX version.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 */
public class ProtocolVersion {

    private static final Set<BeginString> TRANSPORT_VERSIONS = EnumSet.copyOf((Arrays.asList(new BeginString[] {
        BeginString.FIXT_1_1 })));

    private BeginString beginString;

    private ApplVerID applVerID;

    private Integer applExtID;

    private String cstmApplVerID;

    public ProtocolVersion(BeginString beginString) {
        this.beginString = beginString;
    }

    public ProtocolVersion(BeginString beginString, ApplVerID applVerID) {
        this.beginString = beginString;
        this.applVerID = applVerID;
    }

    public boolean hasApplVersion() {
        return TRANSPORT_VERSIONS.contains(beginString);
    }

    public ApplVerID getApplVerID() {
        return applVerID;
    }

    public void setApplVerID(ApplVerID applVerID) {
        this.applVerID = applVerID;
    }

    public BeginString getBeginString() {
        return beginString;
    }

    public void setBeginString(BeginString beginString) {
        this.beginString = beginString;
    }

    public Integer getApplExtID() {
        return applExtID;
    }

    public void setApplExtID(Integer applExtID) {
        this.applExtID = applExtID;
    }

    public String getCstmApplVerID() {
        return cstmApplVerID;
    }

    public void setCstmApplVerID(String cstmApplVerID) {
        this.cstmApplVerID = cstmApplVerID;
    }

}
