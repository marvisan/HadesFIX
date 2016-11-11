/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AllocationReportMsgBuilder.java
 *
 * $Id: AllocationReportMsgBuilder.java,v 1.1 2011-02-13 04:40:46 vrotaru Exp $
 */
package net.hades.fix.message.builder.impl;

import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;

import java.nio.ByteBuffer;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.AllocationReportMsg;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v44.AllocationReportMsg44;
import net.hades.fix.message.impl.v50.AllocationReportMsg50;
import net.hades.fix.message.impl.v50sp1.AllocationReportMsg50SP1;
import net.hades.fix.message.impl.v50sp2.AllocationReportMsg50SP2;
import net.hades.fix.message.type.BeginString;

/**
 * Builds a AllocationReportMsg message for a specific version.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 09/04/2009, 3:15:57 PM
 */
public class AllocationReportMsgBuilder extends MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public AllocationReportMsgBuilder() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {
        AllocationReportMsg fixMsg = null;
        checkTransportVersion(header.getBeginString());
        switch (header.getBeginString()) {
            case FIX_4_0:
            case FIX_4_1:
            case FIX_4_2:
            case FIX_4_3:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.AllocationReport.getValue(),
                        header.getBeginString(), header.getApplVerID()));

            case FIX_4_4:
                fixMsg = new AllocationReportMsg44(header, message);
                break;

            case FIX_5_0:
                fixMsg = new AllocationReportMsg50(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                fixMsg = new AllocationReportMsg50SP1(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                fixMsg = new AllocationReportMsg50SP2(header, message);
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
        AllocationReportMsg message = null;
        checkTransportVersion(version);
        switch (version) {
            case FIX_4_0:
            case FIX_4_1:
            case FIX_4_2:
            case FIX_4_3:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.AllocationReport.getValue(),
                        version, applVerID));

            case FIX_4_4:
                message = new AllocationReportMsg44(version);
                break;

            case FIX_5_0:
                message = new AllocationReportMsg50(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                message = new AllocationReportMsg50SP1(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                message = new AllocationReportMsg50SP2(version);
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

    private AllocationReportMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {
        AllocationReportMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
            case FIX41:
            case FIX42:
            case FIX43:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.AllocationReport.getValue(),
                        version, applVerID));

            case FIX44:
                message = new AllocationReportMsg44(version, applVerID);
                break;

            case FIX50:
                message = new AllocationReportMsg50(version, applVerID);
                break;

            case FIX50SP1:
                message = new AllocationReportMsg50SP1(version, applVerID);
                break;

            case FIX50SP2:
                message = new AllocationReportMsg50SP2(version, applVerID);
                break;
        }

        return message;
    }

    private AllocationReportMsg createFIXTMessage(Header header, ByteBuffer fixMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        AllocationReportMsg message = null;
        checkApplVersion(header.getApplVerID());
        switch (header.getApplVerID()) {
            case FIX40:
            case FIX41:
            case FIX42:
            case FIX43:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.AllocationReport.getValue(),
                        header.getBeginString(), header.getApplVerID()));

            case FIX44:
                message = new AllocationReportMsg44(header, fixMsg);
                break;

            case FIX50:
                message = new AllocationReportMsg50(header, fixMsg);
                break;

            case FIX50SP1:
                message = new AllocationReportMsg50SP1(header, fixMsg);
                break;

            case FIX50SP2:
                message = new AllocationReportMsg50SP2(header, fixMsg);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
