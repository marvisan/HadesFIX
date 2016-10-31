/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewOrderSingleMsgBuilder.java
 *
 * $Id: ExecutionReportMsgBuilder.java,v 1.4 2011-01-15 10:18:15 vrotaru Exp $
 */
package net.hades.fix.message.builder.impl;

import net.hades.fix.message.ExecutionReportMsg;
import net.hades.fix.message.impl.v44.ExecutionReportMsg44;

import java.nio.ByteBuffer;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v40.ExecutionReportMsg40;
import net.hades.fix.message.impl.v41.ExecutionReportMsg41;
import net.hades.fix.message.impl.v42.ExecutionReportMsg42;
import net.hades.fix.message.impl.v43.ExecutionReportMsg43;
import net.hades.fix.message.impl.v50.ExecutionReportMsg50;
import net.hades.fix.message.impl.v50sp1.ExecutionReportMsg50SP1;
import net.hades.fix.message.impl.v50sp2.ExecutionReportMsg50SP2;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;

/**
 * Builds a NewOrderSingleMsg message for a specific version.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 09/04/2009, 3:15:57 PM
 */
public class ExecutionReportMsgBuilder extends MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public ExecutionReportMsgBuilder() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {
        ExecutionReportMsg fixMsg = null;
        checkTransportVersion(header.getBeginString());
        switch (header.getBeginString()) {
            case FIX_4_0:
                fixMsg = new ExecutionReportMsg40(header, message);
                break;

            case FIX_4_1:
                fixMsg = new ExecutionReportMsg41(header, message);
                break;

            case FIX_4_2:
                fixMsg = new ExecutionReportMsg42(header, message);
                break;

            case FIX_4_3:
                fixMsg = new ExecutionReportMsg43(header, message);
                break;

            case FIX_4_4:
                fixMsg = new ExecutionReportMsg44(header, message);
                break;

            case FIX_5_0:
                fixMsg = new ExecutionReportMsg50(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                fixMsg = new ExecutionReportMsg50SP1(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                fixMsg = new ExecutionReportMsg50SP2(header, message);
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
        ExecutionReportMsg message = null;
        checkTransportVersion(version);
        switch (version) {
            case FIX_4_0:
                message = new ExecutionReportMsg40(version);
                break;

            case FIX_4_1:
                message = new ExecutionReportMsg41(version);
                break;

            case FIX_4_2:
                message = new ExecutionReportMsg42(version);
                break;

            case FIX_4_3:
                message = new ExecutionReportMsg43(version);
                break;

            case FIX_4_4:
                message = new ExecutionReportMsg44(version);
                break;

            case FIX_5_0:
                message = new ExecutionReportMsg50(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                message = new ExecutionReportMsg50SP1(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                message = new ExecutionReportMsg50SP2(version);
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

    private ExecutionReportMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {
        ExecutionReportMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
                message = new ExecutionReportMsg40(version, applVerID);
                break;

            case FIX41:
                message = new ExecutionReportMsg41(version, applVerID);
                break;

            case FIX42:
                message = new ExecutionReportMsg42(version, applVerID);
                break;

            case FIX43:
                message = new ExecutionReportMsg43(version, applVerID);
                break;

            case FIX44:
                message = new ExecutionReportMsg44(version, applVerID);
                break;

            case FIX50:
                message = new ExecutionReportMsg50(version, applVerID);
                break;

            case FIX50SP1:
                message = new ExecutionReportMsg50SP1(version, applVerID);
                break;

            case FIX50SP2:
                message = new ExecutionReportMsg50SP2(version, applVerID);
                break;
        }

        return message;
    }

    private ExecutionReportMsg createFIXTMessage(Header header, ByteBuffer fixMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        ExecutionReportMsg message = null;
        checkApplVersion(header.getApplVerID());
        switch (header.getApplVerID()) {
            case FIX40:
                message = new ExecutionReportMsg40(header, fixMsg);
                break;

            case FIX41:
                message = new ExecutionReportMsg41(header, fixMsg);
                break;

            case FIX42:
                message = new ExecutionReportMsg42(header, fixMsg);
                break;

            case FIX43:
                message = new ExecutionReportMsg43(header, fixMsg);
                break;

            case FIX44:
                message = new ExecutionReportMsg44(header, fixMsg);
                break;

            case FIX50:
                message = new ExecutionReportMsg50(header, fixMsg);
                break;

            case FIX50SP1:
                message = new ExecutionReportMsg50SP1(header, fixMsg);
                break;

            case FIX50SP2:
                message = new ExecutionReportMsg50SP2(header, fixMsg);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
