/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListStatusRequestMsgBuilder.java
 *
 * $Id: ListStatusRequestMsgBuilder.java,v 1.1 2011-02-06 01:06:14 vrotaru Exp $
 */
package net.hades.fix.message.builder.impl;

import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.impl.v40.ListStatusRequestMsg40;
import net.hades.fix.message.impl.v42.ListStatusRequestMsg42;
import net.hades.fix.message.impl.v50sp1.ListStatusRequestMsg50SP1;
import net.hades.fix.message.impl.v50sp2.ListStatusRequestMsg50SP2;
import net.hades.fix.message.type.ApplVerID;

import java.nio.ByteBuffer;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.ListStatusRequestMsg;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v44.ListStatusRequestMsg44;
import net.hades.fix.message.impl.v50.ListStatusRequestMsg50;
import net.hades.fix.message.type.BeginString;

/**
 * Builds a ListStatusRequestMsg message for a specific version.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 09/04/2009, 3:15:57 PM
 */
public class ListStatusRequestMsgBuilder extends MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ListStatusRequestMsgBuilder() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {
        ListStatusRequestMsg fixMsg = null;
        checkTransportVersion(header.getBeginString());
        switch (header.getBeginString()) {
            case FIX_4_0:
                fixMsg = new ListStatusRequestMsg40(header, message);
                break;

            case FIX_4_1:
                fixMsg = new ListStatusRequestMsg40(header, message);
                break;

            case FIX_4_2:
                fixMsg = new ListStatusRequestMsg42(header, message);
                break;

            case FIX_4_3:
                fixMsg = new ListStatusRequestMsg42(header, message);
                break;

            case FIX_4_4:
                fixMsg = new ListStatusRequestMsg44(header, message);
                break;

            case FIX_5_0:
                fixMsg = new ListStatusRequestMsg50(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                fixMsg = new ListStatusRequestMsg50SP1(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                fixMsg = new ListStatusRequestMsg50SP2(header, message);
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
        ListStatusRequestMsg message = null;
        checkTransportVersion(version);
        switch (version) {
            case FIX_4_0:
                message = new ListStatusRequestMsg40(version);
                break;

            case FIX_4_1:
                message = new ListStatusRequestMsg40(version);
                break;

            case FIX_4_2:
                message = new ListStatusRequestMsg42(version);
                break;

            case FIX_4_3:
                message = new ListStatusRequestMsg42(version);
                break;

            case FIX_4_4:
                message = new ListStatusRequestMsg44(version);
                break;

            case FIX_5_0:
                message = new ListStatusRequestMsg50(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                message = new ListStatusRequestMsg50SP1(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                message = new ListStatusRequestMsg50SP2(version);
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

    private ListStatusRequestMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {
        ListStatusRequestMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
                message = new ListStatusRequestMsg40(version, applVerID);
                break;

            case FIX41:
                message = new ListStatusRequestMsg40(version, applVerID);
                break;

            case FIX42:
                message = new ListStatusRequestMsg42(version, applVerID);
                break;

            case FIX43:
                message = new ListStatusRequestMsg42(version, applVerID);
                break;

            case FIX44:
                message = new ListStatusRequestMsg44(version, applVerID);
                break;

            case FIX50:
                message = new ListStatusRequestMsg50(version, applVerID);
                break;

            case FIX50SP1:
                message = new ListStatusRequestMsg50SP1(version, applVerID);
                break;

            case FIX50SP2:
                message = new ListStatusRequestMsg50SP2(version, applVerID);
                break;
        }

        return message;
    }

    private ListStatusRequestMsg createFIXTMessage(Header header, ByteBuffer fixMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        ListStatusRequestMsg message = null;
        checkApplVersion(header.getApplVerID());
        switch (header.getApplVerID()) {
            case FIX40:
                message = new ListStatusRequestMsg40(header, fixMsg);
                break;

            case FIX41:
                message = new ListStatusRequestMsg40(header, fixMsg);
                break;

            case FIX42:
                message = new ListStatusRequestMsg42(header, fixMsg);
                break;

            case FIX43:
                message = new ListStatusRequestMsg40(header, fixMsg);
                break;

            case FIX44:
                message = new ListStatusRequestMsg44(header, fixMsg);
                break;

            case FIX50:
                message = new ListStatusRequestMsg50(header, fixMsg);
                break;

            case FIX50SP1:
                message = new ListStatusRequestMsg50SP1(header, fixMsg);
                break;

            case FIX50SP2:
                message = new ListStatusRequestMsg50SP2(header, fixMsg);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
