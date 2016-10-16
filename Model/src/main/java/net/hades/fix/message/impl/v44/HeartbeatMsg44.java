/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * HeartbeatMsg44.java
 *
 * $Id: HeartbeatMsg44.java,v 1.5 2011-04-04 09:37:11 vrotaru Exp $
 */
package net.hades.fix.message.impl.v44;

import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.type.ApplVerID;

import java.nio.ByteBuffer;

import net.hades.fix.message.HeartbeatMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.BeginString;

/**
 * FIX 4.4 implementation of Heartbeat message.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 01/04/2009, 12:18:30 PM
 */
public class HeartbeatMsg44 extends HeartbeatMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public HeartbeatMsg44(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }

    public HeartbeatMsg44(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public HeartbeatMsg44(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [HeartbeatMsg] message version [4.4].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
