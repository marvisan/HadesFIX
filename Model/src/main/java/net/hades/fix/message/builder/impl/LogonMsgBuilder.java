/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * LogonMsgBuilder.java
 *
 * $Id: LogonMsgBuilder.java,v 1.5 2010-11-17 10:55:05 vrotaru Exp $
 */
package net.hades.fix.message.builder.impl;

import net.hades.fix.message.impl.v40.LogonMsg40;
import net.hades.fix.message.impl.v41.LogonMsg41;
import net.hades.fix.message.impl.v44.LogonMsg44;
import net.hades.fix.message.type.ApplVerID;

import java.nio.ByteBuffer;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.LogonMsg;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v42.LogonMsg42;
import net.hades.fix.message.impl.v43.LogonMsg43;
import net.hades.fix.message.impl.v50.LogonMsg50;
import net.hades.fix.message.impl.v50sp1.LogonMsg50SP1;
import net.hades.fix.message.impl.v50sp2.LogonMsg50SP2;
import net.hades.fix.message.type.BeginString;

/**
 * Builds a logon message for a specific version.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 20/09/2008, 15:02:51
 */
public class LogonMsgBuilder extends MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public LogonMsgBuilder() {
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {
        
        LogonMsg fixMsg = null;
        switch (header.getBeginString()) {
            case FIX_4_0:
                fixMsg = new LogonMsg40(header, message);
                break;

            case FIX_4_1:
                fixMsg = new LogonMsg41(header, message);
                break;
                
            case FIX_4_2:
                fixMsg = new LogonMsg42(header, message);
                break;
                
            case FIX_4_3:
                fixMsg = new LogonMsg43(header, message);
                break;
                
            case FIX_4_4:
                fixMsg = new LogonMsg44(header, message);
                break;

           case FIX_5_0:
                fixMsg = new LogonMsg50(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                fixMsg = new LogonMsg50SP1(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                fixMsg = new LogonMsg50SP2(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
                break;

            case FIXT_1_1:
                fixMsg = createFIXTMessage(header, message);
                break;
                
            default:
                String error = "Message [LogonMsg] not supported in FIX version [" + header.getBeginString() + "].";
                LOGGER.severe(error);
                throw new InvalidMsgException(error);
        }
        
        return fixMsg;
    }

    @Override
    public FIXMsg build(BeginString version, ApplVerID applVerID) throws InvalidMsgException {
        
        LogonMsg message = null;
        switch (version) {
            case FIX_4_0:
                message = new LogonMsg40(version);
                break;
                
            case FIX_4_1:
                message = new LogonMsg41(version);
                break;
                
            case FIX_4_2:
                message = new LogonMsg42(version);
                break;
                
            case FIX_4_3:
                message = new LogonMsg43(version);
                break;
                
            case FIX_4_4:
                message = new LogonMsg44(version);
                break;

            case FIX_5_0:
                message = new LogonMsg50(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                message = new LogonMsg50SP1(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                message = new LogonMsg50SP2(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP2);
                break;

            case FIXT_1_1:
                message = createFIXTMessage(version, applVerID);
                break;
                
            default:
                String error = "Message [LogonMsg] not supported in FIX version [" + version.getValue() + "].";
                LOGGER.severe(error);
                throw new InvalidMsgException(error);
        }
        
        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">

    private LogonMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {

        LogonMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
                message = new LogonMsg40(version, applVerID);
                break;

            case FIX41:
                message = new LogonMsg41(version, applVerID);
                break;

            case FIX42:
                message = new LogonMsg42(version, applVerID);
                break;

            case FIX43:
                message = new LogonMsg43(version, applVerID);
                break;

            case FIX44:
                message = new LogonMsg44(version, applVerID);
                break;

            case FIX50:
                message = new LogonMsg50(version, applVerID);
                break;

            case FIX50SP1:
                message = new LogonMsg50SP1(version, applVerID);
                break;

            case FIX50SP2:
                message = new LogonMsg50SP2(version, applVerID);
                break;
        }

        return message;
    }

    private LogonMsg createFIXTMessage(Header header, ByteBuffer byteMessage)
            throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        LogonMsg message = null;
        ApplVerID applVerID = header.getApplVerID();
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
                message = new LogonMsg40(header, byteMessage);
                break;

            case FIX41:
                message = new LogonMsg41(header, byteMessage);
                break;

            case FIX42:
                message = new LogonMsg42(header, byteMessage);
                break;

            case FIX43:
                message = new LogonMsg43(header, byteMessage);
                break;

            case FIX44:
                message = new LogonMsg44(header, byteMessage);
                break;

            case FIX50:
                message = new LogonMsg50(header, byteMessage);
                break;

            case FIX50SP1:
                message = new LogonMsg50SP1(header, byteMessage);
                break;

            case FIX50SP2:
                message = new LogonMsg50SP2(header, byteMessage);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
