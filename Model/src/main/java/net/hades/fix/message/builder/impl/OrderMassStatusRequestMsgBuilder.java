/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderMassStatusRequestMsgBuilder.java
 *
 * $Id: OrderMassStatusRequestMsgBuilder.java,v 1.1 2011-05-09 08:21:13 vrotaru Exp $
 */
package net.hades.fix.message.builder.impl;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.OrderMassStatusRequestMsg;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v43.OrderMassStatusRequestMsg43;
import net.hades.fix.message.impl.v44.OrderMassStatusRequestMsg44;
import net.hades.fix.message.impl.v50.OrderMassStatusRequestMsg50;
import net.hades.fix.message.impl.v50sp1.OrderMassStatusRequestMsg50SP1;
import net.hades.fix.message.impl.v50sp2.OrderMassStatusRequestMsg50SP2;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

import java.nio.ByteBuffer;

/**
 * Builds a OrderMassStatusRequestMsg message for a specific version.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 09/05/2011, 3:15:57 PM
 */
public class OrderMassStatusRequestMsgBuilder extends MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public OrderMassStatusRequestMsgBuilder() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {
        OrderMassStatusRequestMsg fixMsg = null;
        checkTransportVersion(header.getBeginString());
        switch (header.getBeginString()) {
            case FIX_4_0:
            case FIX_4_1:
            case FIX_4_2:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.OrderMassStatusRequest.getValue(),
                        header.getBeginString(), header.getApplVerID()));

            case FIX_4_3:
                fixMsg = new OrderMassStatusRequestMsg43(header, message);
                break;

            case FIX_4_4:
                fixMsg = new OrderMassStatusRequestMsg44(header, message);
                break;

            case FIX_5_0:
                fixMsg = new OrderMassStatusRequestMsg50(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                fixMsg = new OrderMassStatusRequestMsg50SP1(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                fixMsg = new OrderMassStatusRequestMsg50SP2(header, message);
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
        OrderMassStatusRequestMsg message = null;
        checkTransportVersion(version);
        switch (version) {
            case FIX_4_0:
            case FIX_4_1:
            case FIX_4_2:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.OrderMassStatusRequest.getValue(),
                        version, applVerID));

            case FIX_4_3:
                message = new OrderMassStatusRequestMsg43(version);
                break;

            case FIX_4_4:
                message = new OrderMassStatusRequestMsg44(version);
                break;

            case FIX_5_0:
                message = new OrderMassStatusRequestMsg50(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                message = new OrderMassStatusRequestMsg50SP1(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                message = new OrderMassStatusRequestMsg50SP2(version);
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

    private OrderMassStatusRequestMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {
        OrderMassStatusRequestMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
            case FIX41:
            case FIX42:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.OrderMassStatusRequest.getValue(),
                        version, applVerID));

            case FIX43:
                message = new OrderMassStatusRequestMsg43(version, applVerID);
                break;

            case FIX44:
                message = new OrderMassStatusRequestMsg44(version, applVerID);
                break;

            case FIX50:
                message = new OrderMassStatusRequestMsg50(version, applVerID);
                break;

            case FIX50SP1:
                message = new OrderMassStatusRequestMsg50SP1(version, applVerID);
                break;

            case FIX50SP2:
                message = new OrderMassStatusRequestMsg50SP2(version, applVerID);
                break;
        }

        return message;
    }

    private OrderMassStatusRequestMsg createFIXTMessage(Header header, ByteBuffer fixMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        OrderMassStatusRequestMsg message = null;
        checkApplVersion(header.getApplVerID());
        switch (header.getApplVerID()) {
            case FIX40:
            case FIX41:
            case FIX42:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.OrderMassStatusRequest.getValue(),
                        header.getBeginString(), header.getApplVerID()));

            case FIX43:
                message = new OrderMassStatusRequestMsg43(header, fixMsg);
                break;

            case FIX44:
                message = new OrderMassStatusRequestMsg44(header, fixMsg);
                break;

            case FIX50:
                message = new OrderMassStatusRequestMsg50(header, fixMsg);
                break;

            case FIX50SP1:
                message = new OrderMassStatusRequestMsg50SP1(header, fixMsg);
                break;

            case FIX50SP2:
                message = new OrderMassStatusRequestMsg50SP2(header, fixMsg);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
