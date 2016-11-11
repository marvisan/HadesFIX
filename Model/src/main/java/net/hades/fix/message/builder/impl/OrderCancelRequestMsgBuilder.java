/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OrderCancelRequestMsgBuilder.java
 *
 * $Id: OrderCancelRequestMsgBuilder.java,v 1.1 2011-01-22 09:52:24 vrotaru Exp $
 */
package net.hades.fix.message.builder.impl;

import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.impl.v41.OrderCancelRequestMsg41;
import net.hades.fix.message.impl.v42.OrderCancelRequestMsg42;
import net.hades.fix.message.impl.v50.OrderCancelRequestMsg50;
import net.hades.fix.message.type.ApplVerID;

import java.nio.ByteBuffer;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.OrderCancelRequestMsg;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v40.OrderCancelRequestMsg40;
import net.hades.fix.message.impl.v43.OrderCancelRequestMsg43;
import net.hades.fix.message.impl.v44.OrderCancelRequestMsg44;
import net.hades.fix.message.impl.v50sp1.OrderCancelRequestMsg50SP1;
import net.hades.fix.message.impl.v50sp2.OrderCancelRequestMsg50SP2;
import net.hades.fix.message.type.BeginString;

/**
 * Builds a OrderCancelRequestMsg message for a specific version.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 09/04/2009, 3:15:57 PM
 */
public class OrderCancelRequestMsgBuilder extends MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public OrderCancelRequestMsgBuilder() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {
        OrderCancelRequestMsg fixMsg = null;
        checkTransportVersion(header.getBeginString());
        switch (header.getBeginString()) {
            case FIX_4_0:
                fixMsg = new OrderCancelRequestMsg40(header, message);
                break;

            case FIX_4_1:
                fixMsg = new OrderCancelRequestMsg41(header, message);
                break;

            case FIX_4_2:
                fixMsg = new OrderCancelRequestMsg42(header, message);
                break;

            case FIX_4_3:
                fixMsg = new OrderCancelRequestMsg43(header, message);
                break;

            case FIX_4_4:
                fixMsg = new OrderCancelRequestMsg44(header, message);
                break;

            case FIX_5_0:
                fixMsg = new OrderCancelRequestMsg50(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                fixMsg = new OrderCancelRequestMsg50SP1(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                fixMsg = new OrderCancelRequestMsg50SP2(header, message);
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
        OrderCancelRequestMsg message = null;
        checkTransportVersion(version);
        switch (version) {
            case FIX_4_0:
                message = new OrderCancelRequestMsg40(version);
                break;

            case FIX_4_1:
                message = new OrderCancelRequestMsg41(version);
                break;

            case FIX_4_2:
                message = new OrderCancelRequestMsg42(version);
                break;

            case FIX_4_3:
                message = new OrderCancelRequestMsg43(version);
                break;

            case FIX_4_4:
                message = new OrderCancelRequestMsg44(version);
                break;

            case FIX_5_0:
                message = new OrderCancelRequestMsg50(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                message = new OrderCancelRequestMsg50SP1(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                message = new OrderCancelRequestMsg50SP2(version);
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

    private OrderCancelRequestMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {
        OrderCancelRequestMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
                message = new OrderCancelRequestMsg40(version, applVerID);
                break;

            case FIX41:
                message = new OrderCancelRequestMsg41(version, applVerID);
                break;

            case FIX42:
                message = new OrderCancelRequestMsg42(version, applVerID);
                break;

            case FIX43:
                message = new OrderCancelRequestMsg43(version, applVerID);
                break;

            case FIX44:
                message = new OrderCancelRequestMsg44(version, applVerID);
                break;

            case FIX50:
                message = new OrderCancelRequestMsg50(version, applVerID);
                break;

            case FIX50SP1:
                message = new OrderCancelRequestMsg50SP1(version, applVerID);
                break;

            case FIX50SP2:
                message = new OrderCancelRequestMsg50SP2(version, applVerID);
                break;
        }

        return message;
    }

    private OrderCancelRequestMsg createFIXTMessage(Header header, ByteBuffer fixMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        OrderCancelRequestMsg message = null;
        checkApplVersion(header.getApplVerID());
        switch (header.getApplVerID()) {
            case FIX40:
                message = new OrderCancelRequestMsg40(header, fixMsg);
                break;

            case FIX41:
                message = new OrderCancelRequestMsg41(header, fixMsg);
                break;

            case FIX42:
                message = new OrderCancelRequestMsg42(header, fixMsg);
                break;

            case FIX43:
                message = new OrderCancelRequestMsg43(header, fixMsg);
                break;

            case FIX44:
                message = new OrderCancelRequestMsg44(header, fixMsg);
                break;

            case FIX50:
                message = new OrderCancelRequestMsg50(header, fixMsg);
                break;

            case FIX50SP1:
                message = new OrderCancelRequestMsg50SP1(header, fixMsg);
                break;

            case FIX50SP2:
                message = new OrderCancelRequestMsg50SP2(header, fixMsg);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
