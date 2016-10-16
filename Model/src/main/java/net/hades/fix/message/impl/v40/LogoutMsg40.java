/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LogoutMsg40.java
 *
 * $Id: LogoutMsg40.java,v 1.7 2010-03-31 11:05:16 vrotaru Exp $
 */
package net.hades.fix.message.impl.v40;

import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.struct.Tag;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.TagNum;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.hades.fix.message.LogoutMsg;
import net.hades.fix.message.exception.BadFormatMsgException;

/**
 * The logout message is to used to initiate or confirm the termination of a FIX session. Disconnection
 * without the exchange of logout messages should be interpreted as an abnormal condition.<p>
 * Before actually closing the session, the logout initiator should wait for the opposite side to respond
 * with a confirming logout message. This gives the remote end a chance to perform any Gap Fill
 * operations that may be necessary. The session may be terminated if the remote side does not respond
 * in an appropriate timeframe.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.7 $
 * @created 24/09/2008, 20:36:45
 */
public class LogoutMsg40 extends LogoutMsg {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    private static final long serialVersionUID = 1L;

    protected static final Set<Integer> TAGS = new HashSet<Integer>(Arrays.asList(new Integer[] {
        TagNum.Text.getValue()
    }));

    protected static final Set<Integer> START_DATA_TAGS = null;

    protected static final Set<Integer> START_COMP_TAGS = null;

    protected static final Set<Integer> REQUIRED_TAGS = null;

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    
    protected Set<Integer> STANDARD_SECURED_TAGS = TAGS;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public LogoutMsg40(Header header, ByteBuffer rawMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        super(header, rawMsg);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }
    
    public LogoutMsg40(BeginString beginString) throws InvalidMsgException {
        super(beginString);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    public LogoutMsg40(BeginString beginString, ApplVerID applVerID) throws InvalidMsgException {
        super(beginString, applVerID);
        SECURED_TAGS = STANDARD_SECURED_TAGS;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public Set<Integer> getFragmentTags() {
        return TAGS;
    }

    @Override
    public Set<Integer> getFragmentAllTags() {
        return TAGS;
    }

    // ACCESSOR METHODS
    //////////////////////////////////////////

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    
    @Override
    protected ByteBuffer setFragmentDataTagValue(Tag tag, ByteBuffer message) throws BadFormatMsgException {
        return message;
    }

    @Override
    protected String getUnsupportedTagMessage() {
        return "This tag is not supported in [LogoutMsg] message version [4.0].";
    }

    @Override
    protected Set<Integer> getFragmentDataTags() {
        return START_DATA_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentCompTags() {
        return START_COMP_TAGS;
    }

    @Override
    protected Set<Integer> getFragmentSecuredTags() {
        return SECURED_TAGS;
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
