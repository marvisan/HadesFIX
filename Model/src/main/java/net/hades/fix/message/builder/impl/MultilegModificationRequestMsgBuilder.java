/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MultilegModificationRequestMsgBuilder.java
 *
 * $Id: MultilegModificationRequestMsgBuilder.java,v 1.1 2011-09-10 05:39:45 vrotaru Exp $
 */
package net.hades.fix.message.builder.impl;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v44.MultilegModificationRequestMsg44;
import net.hades.fix.message.impl.v50sp1.MultilegModificationRequestMsg50SP1;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

import java.nio.ByteBuffer;

import net.hades.fix.message.MultilegModificationRequestMsg;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.impl.v43.MultilegModificationRequestMsg43;
import net.hades.fix.message.impl.v50.MultilegModificationRequestMsg50;
import net.hades.fix.message.impl.v50sp2.MultilegModificationRequestMsg50SP2;

/**
 * Builds a MultilegModificationRequestMsg message for a specific version.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 12/05/2011, 3:15:57 PM
 */
public class MultilegModificationRequestMsgBuilder extends MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public MultilegModificationRequestMsgBuilder() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {
        MultilegModificationRequestMsg fixMsg = null;
        checkTransportVersion(header.getBeginString());
        switch (header.getBeginString()) {
            case FIX_4_0:
            case FIX_4_1:
            case FIX_4_2:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.MultilegModificationRequest.getValue(),
                        header.getBeginString(), header.getApplVerID()));

            case FIX_4_3:
                fixMsg = new MultilegModificationRequestMsg43(header, message);
                break;

            case FIX_4_4:
                fixMsg = new MultilegModificationRequestMsg44(header, message);
                break;

            case FIX_5_0:
                fixMsg = new MultilegModificationRequestMsg50(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                fixMsg = new MultilegModificationRequestMsg50SP1(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                fixMsg = new MultilegModificationRequestMsg50SP2(header, message);
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
        MultilegModificationRequestMsg message = null;
        checkTransportVersion(version);
        switch (version) {
            case FIX_4_0:
            case FIX_4_1:
            case FIX_4_2:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.MultilegModificationRequest.getValue(),
                        version, applVerID));

            case FIX_4_3:
                message = new MultilegModificationRequestMsg43(version);
                break;

            case FIX_4_4:
                message = new MultilegModificationRequestMsg44(version);
                break;

            case FIX_5_0:
                message = new MultilegModificationRequestMsg50(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                message = new MultilegModificationRequestMsg50SP1(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                message = new MultilegModificationRequestMsg50SP2(version);
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

    private MultilegModificationRequestMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {
        MultilegModificationRequestMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
            case FIX41:
            case FIX42:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.MultilegModificationRequest.getValue(),
                        version, applVerID));

            case FIX43:
                message = new MultilegModificationRequestMsg43(version, applVerID);
                break;

            case FIX44:
                message = new MultilegModificationRequestMsg44(version, applVerID);
                break;

            case FIX50:
                message = new MultilegModificationRequestMsg50(version, applVerID);
                break;

            case FIX50SP1:
                message = new MultilegModificationRequestMsg50SP1(version, applVerID);
                break;

            case FIX50SP2:
                message = new MultilegModificationRequestMsg50SP2(version, applVerID);
                break;
        }

        return message;
    }

    private MultilegModificationRequestMsg createFIXTMessage(Header header, ByteBuffer fixMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        MultilegModificationRequestMsg message = null;
        checkApplVersion(header.getApplVerID());
        switch (header.getApplVerID()) {
            case FIX40:
            case FIX41:
            case FIX42:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.MultilegModificationRequest.getValue(),
                        header.getBeginString(), header.getApplVerID()));

            case FIX43:
                message = new MultilegModificationRequestMsg43(header, fixMsg);
                break;

            case FIX44:
                message = new MultilegModificationRequestMsg44(header, fixMsg);
                break;

            case FIX50:
                message = new MultilegModificationRequestMsg50(header, fixMsg);
                break;

            case FIX50SP1:
                message = new MultilegModificationRequestMsg50SP1(header, fixMsg);
                break;

            case FIX50SP2:
                message = new MultilegModificationRequestMsg50SP2(header, fixMsg);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
