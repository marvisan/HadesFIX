/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewsMsgBuilder.java
 *
 * $Id: NewsMsgBuilder.java,v 1.4 2010-11-17 10:55:05 vrotaru Exp $
 */
package net.hades.fix.message.builder.impl;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.NewsMsg;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.impl.v40.NewsMsg40;
import net.hades.fix.message.impl.v41.NewsMsg41;
import net.hades.fix.message.impl.v43.NewsMsg43;
import net.hades.fix.message.impl.v44.NewsMsg44;
import net.hades.fix.message.impl.v50.NewsMsg50;
import net.hades.fix.message.impl.v50sp1.NewsMsg50SP1;
import net.hades.fix.message.impl.v50sp2.NewsMsg50SP2;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;

import java.nio.ByteBuffer;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v42.NewsMsg42;

/**
 * Builds a News message for a specific version.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/03/2009, 15:02:51
 */
public class NewsMsgBuilder extends MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public NewsMsgBuilder() {
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {
        
        NewsMsg fixMsg = null;
        checkTransportVersion(header.getBeginString());
        switch (header.getBeginString()) {
            case FIX_4_0:
                fixMsg = new NewsMsg40(header, message);
                break;

            case FIX_4_1:
                fixMsg = new NewsMsg41(header, message);
                break;
                
            case FIX_4_2:
                fixMsg = new NewsMsg42(header, message);
                break;
                
            case FIX_4_3:
                fixMsg = new NewsMsg43(header, message);
                break;
                
            case FIX_4_4:
                fixMsg = new NewsMsg44(header, message);
                break;

            case FIX_5_0:
                fixMsg = new NewsMsg50(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                fixMsg = new NewsMsg50SP1(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                fixMsg = new NewsMsg50SP2(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
                break;

            case FIXT_1_1:
                fixMsg = createFIXTMessage(header, message);
                break;
        }

        return fixMsg;
    }

    @Override
    public FIXMsg build(BeginString version, ApplVerID applVerID) throws InvalidMsgException {
        
        NewsMsg message = null;
        checkTransportVersion(version);
        switch (version) {
            case FIX_4_0:
                message = new NewsMsg40(version);
                break;
                
            case FIX_4_1:
                message = new NewsMsg41(version);
                break;
                
            case FIX_4_2:
                message = new NewsMsg42(version);
                break;
                
            case FIX_4_3:
                message = new NewsMsg43(version);
                break;
                
            case FIX_4_4:
                message = new NewsMsg44(version);
                break;

            case FIX_5_0:
                message = new NewsMsg50(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                message = new NewsMsg50SP1(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                message = new NewsMsg50SP2(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP2);
                break;

            case FIXT_1_1:
                message = createFIXTMessage(version, applVerID);
                break;
        }
        
        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">

    private NewsMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {

        NewsMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
                message = new NewsMsg40(version, applVerID);
                break;

            case FIX41:
                message = new NewsMsg41(version, applVerID);
                break;

            case FIX42:
                message = new NewsMsg42(version, applVerID);
                break;

            case FIX43:
                message = new NewsMsg43(version, applVerID);
                break;

            case FIX44:
                message = new NewsMsg44(version, applVerID);
                break;

            case FIX50:
                message = new NewsMsg50(version, applVerID);
                break;

            case FIX50SP1:
                message = new NewsMsg50SP1(version, applVerID);
                break;

            case FIX50SP2:
                message = new NewsMsg50SP2(version, applVerID);
                break;
        }

        return message;
    }

    private NewsMsg createFIXTMessage(Header header, ByteBuffer fixMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {

        NewsMsg message = null;
        checkApplVersion(header.getApplVerID());
        switch (header.getApplVerID()) {
            case FIX40:
                message = new NewsMsg40(header, fixMsg);
                break;

            case FIX41:
                message = new NewsMsg41(header, fixMsg);
                break;

            case FIX42:
                message = new NewsMsg42(header, fixMsg);
                break;

            case FIX43:
                message = new NewsMsg43(header, fixMsg);
                break;

            case FIX44:
                message = new NewsMsg44(header, fixMsg);
                break;

            case FIX50:
                message = new NewsMsg50(header, fixMsg);
                break;

            case FIX50SP1:
                message = new NewsMsg50SP1(header, fixMsg);
                break;

            case FIX50SP2:
                message = new NewsMsg50SP2(header, fixMsg);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
