/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RejectMsg43.java
 *
 * $Id: RejectMsg43.java,v 1.4 2010-03-31 11:05:18 vrotaru Exp $
 */
package net.hades.fix.message.impl.v43;

import net.hades.fix.message.Header;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v42.RejectMsg42;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;

import java.nio.ByteBuffer;

/**
 * FIX 4.3 implementation of RejectMsg.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 24/09/2008, 20:36:45
 */
public class RejectMsg43 extends RejectMsg42 {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = -2224324744651516520L;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public RejectMsg43(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
    }
    
    public RejectMsg43(BeginString beginString) throws InvalidMsgException {
        super(beginString);
    }

    public RejectMsg43(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [RejectMsg] message version [4.3].";
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
