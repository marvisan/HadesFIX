/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SequenceResetMsgBuilder.java
 *
 * $Id: SequenceResetMsgBuilder.java,v 1.5 2010-11-17 10:55:05 vrotaru Exp $
 */
package net.hades.fix.message.builder.impl;

import net.hades.fix.message.impl.v44.SequenceResetMsg44;

import java.nio.ByteBuffer;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.SequenceResetMsg;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v40.SequenceResetMsg40;
import net.hades.fix.message.impl.v41.SequenceResetMsg41;
import net.hades.fix.message.impl.v42.SequenceResetMsg42;
import net.hades.fix.message.impl.v43.SequenceResetMsg43;
import net.hades.fix.message.impl.v50.SequenceResetMsg50;
import net.hades.fix.message.impl.v50sp1.SequenceResetMsg50SP1;
import net.hades.fix.message.impl.v50sp2.SequenceResetMsg50SP2;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;

/**
 * Builder for SequenceResetMsg FIX message.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.5 $
 * @created 22/09/2008, 20:37:45
 */
public class SequenceResetMsgBuilder extends MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public SequenceResetMsgBuilder() {
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {

        SequenceResetMsg fixMsg = null;
        switch (header.getBeginString()) {
            case FIX_4_0:
                fixMsg = new SequenceResetMsg40(header, message);
                break;

            case FIX_4_1:
                fixMsg = new SequenceResetMsg41(header, message);
                break;

            case FIX_4_2:
                fixMsg = new SequenceResetMsg42(header, message);
                break;

            case FIX_4_3:
                fixMsg = new SequenceResetMsg43(header, message);
                break;

            case FIX_4_4:
                fixMsg = new SequenceResetMsg44(header, message);
                break;

           case FIX_5_0:
                fixMsg = new  SequenceResetMsg50(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                fixMsg = new  SequenceResetMsg50SP1(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                fixMsg = new  SequenceResetMsg50SP2(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP2);
                break;

            case FIXT_1_1:
                fixMsg = createFIXTMessage(header, message);
                break;

            default:
                String error = "Message [SequenceResetMsg] not supported in FIX version [" + header.getBeginString() + "].";
                LOGGER.severe(error);
                throw new InvalidMsgException(error);
        }

        return fixMsg;
    }

    @Override
    public FIXMsg build(BeginString version, ApplVerID applVerID) throws InvalidMsgException {

        SequenceResetMsg message = null;
        switch (version) {
            case FIX_4_0:
                message = new SequenceResetMsg40(version);
                break;

            case FIX_4_1:
                message = new SequenceResetMsg41(version);
                break;

            case FIX_4_2:
                message = new SequenceResetMsg42(version);
                break;

            case FIX_4_3:
                message = new SequenceResetMsg43(version);
                break;

            case FIX_4_4:
                message = new SequenceResetMsg44(version);
                break;

            case FIX_5_0:
                message = new SequenceResetMsg50(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                message = new SequenceResetMsg50SP1(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                message = new SequenceResetMsg50SP2(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP2);
                break;

            case FIXT_1_1:
                message = createFIXTMessage(version, applVerID);
                break;

            default:
                String error = "Message [SequenceResetMsg] not supported in FIX version [" + version.getValue() + "].";
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

    private SequenceResetMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {

        SequenceResetMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
                message = new SequenceResetMsg40(version, applVerID);
                break;

            case FIX41:
                message = new SequenceResetMsg41(version, applVerID);
                break;

            case FIX42:
                message = new SequenceResetMsg42(version, applVerID);
                break;

            case FIX43:
                message = new SequenceResetMsg43(version, applVerID);
                break;

            case FIX44:
                message = new SequenceResetMsg44(version, applVerID);
                break;

            case FIX50:
                message = new SequenceResetMsg50(version, applVerID);
                break;

            case FIX50SP1:
                message = new SequenceResetMsg50SP1(version, applVerID);
                break;

            case FIX50SP2:
                message = new SequenceResetMsg50SP2(version, applVerID);
                break;
        }

        return message;
    }

    private SequenceResetMsg createFIXTMessage(Header header, ByteBuffer byteMessage)
            throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        SequenceResetMsg message = null;
        ApplVerID applVerID = header.getApplVerID();
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
                message = new SequenceResetMsg40(header, byteMessage);
                break;

            case FIX41:
                message = new SequenceResetMsg41(header, byteMessage);
                break;

            case FIX42:
                message = new SequenceResetMsg42(header, byteMessage);
                break;

            case FIX43:
                message = new SequenceResetMsg43(header, byteMessage);
                break;

            case FIX44:
                message = new SequenceResetMsg44(header, byteMessage);
                break;

            case FIX50:
                message = new SequenceResetMsg50(header, byteMessage);
                break;

            case FIX50SP1:
                message = new SequenceResetMsg50SP1(header, byteMessage);
                break;

            case FIX50SP2:
                message = new SequenceResetMsg50SP2(header, byteMessage);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
