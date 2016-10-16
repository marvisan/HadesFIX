/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LogoutMsg41.java
 *
 * $Id: LogoutMsg41.java,v 1.4 2010-03-31 11:05:17 vrotaru Exp $
 */
package net.hades.fix.message.impl.v41;

import net.hades.fix.message.struct.Tag;

import java.nio.ByteBuffer;

import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v40.LogoutMsg40;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;

/**
 * FIX 4.1 implementation of LogoutMsg.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 24/09/2008, 20:36:45
 */
public class LogoutMsg41 extends LogoutMsg40 {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -9119680944055294226L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public LogoutMsg41(Header header, ByteBuffer rawMsg) 
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public LogoutMsg41(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public LogoutMsg41(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [LogoutMsg] message version [4.1].";
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
