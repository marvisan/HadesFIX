/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SettlementInstructionsMsgBuilder.java
 *
 * $Id: SettlementInstructionsMsgBuilder.java,v 1.2 2011-03-26 03:24:28 vrotaru Exp $
 */
package net.hades.fix.message.builder.impl;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.SettlementInstructionsMsg;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v41.SettlementInstructionsMsg41;
import net.hades.fix.message.impl.v42.SettlementInstructionsMsg42;
import net.hades.fix.message.impl.v43.SettlementInstructionsMsg43;
import net.hades.fix.message.impl.v44.SettlementInstructionsMsg44;
import net.hades.fix.message.impl.v50.SettlementInstructionsMsg50;
import net.hades.fix.message.impl.v50sp1.SettlementInstructionsMsg50SP1;
import net.hades.fix.message.impl.v50sp2.SettlementInstructionsMsg50SP2;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.type.MsgType;

import java.nio.ByteBuffer;

/**
 * Builds a SettlementInstructionsMsg message for a specific version.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 09/04/2009, 3:15:57 PM
 */
public class SettlementInstructionsMsgBuilder extends MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public SettlementInstructionsMsgBuilder() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {
        SettlementInstructionsMsg fixMsg = null;
        checkTransportVersion(header.getBeginString());
        switch (header.getBeginString()) {
            case FIX_4_0:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.SettlInstructions.getValue(),
                        header.getBeginString(), header.getApplVerID()));

            case FIX_4_1:
                fixMsg = new SettlementInstructionsMsg41(header, message);
                break;

            case FIX_4_2:
                fixMsg = new SettlementInstructionsMsg42(header, message);
                break;

            case FIX_4_3:
                fixMsg = new SettlementInstructionsMsg43(header, message);
                break;

            case FIX_4_4:
                fixMsg = new SettlementInstructionsMsg44(header, message);
                break;

            case FIX_5_0:
                fixMsg = new SettlementInstructionsMsg50(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                fixMsg = new SettlementInstructionsMsg50SP1(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                fixMsg = new SettlementInstructionsMsg50SP2(header, message);
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
        SettlementInstructionsMsg message = null;
        checkTransportVersion(version);
        switch (version) {
            case FIX_4_0:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.SettlInstructions.getValue(),
                        version, applVerID));

            case FIX_4_1:
                message = new SettlementInstructionsMsg41(version);
                break;

            case FIX_4_2:
                message = new SettlementInstructionsMsg42(version);
                break;

            case FIX_4_3:
                message = new SettlementInstructionsMsg43(version);
                break;

            case FIX_4_4:
                message = new SettlementInstructionsMsg44(version);
                break;

            case FIX_5_0:
                message = new SettlementInstructionsMsg50(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                message = new SettlementInstructionsMsg50SP1(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                message = new SettlementInstructionsMsg50SP2(version);
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

    private SettlementInstructionsMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {
        SettlementInstructionsMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.SettlInstructions.getValue(),
                        version, applVerID));

            case FIX41:
                message = new SettlementInstructionsMsg41(version, applVerID);
                break;

            case FIX42:
                message = new SettlementInstructionsMsg42(version, applVerID);
                break;

            case FIX43:
                message = new SettlementInstructionsMsg43(version, applVerID);
                break;

            case FIX44:
                message = new SettlementInstructionsMsg44(version, applVerID);
                break;

            case FIX50:
                message = new SettlementInstructionsMsg50(version, applVerID);
                break;

            case FIX50SP1:
                message = new SettlementInstructionsMsg50SP1(version, applVerID);
                break;

            case FIX50SP2:
                message = new SettlementInstructionsMsg50SP2(version, applVerID);
                break;
        }

        return message;
    }

    private SettlementInstructionsMsg createFIXTMessage(Header header, ByteBuffer fixMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        SettlementInstructionsMsg message = null;
        checkApplVersion(header.getApplVerID());
        switch (header.getApplVerID()) {
            case FIX40:
                throw new InvalidMsgException(getMessageNotInFixVersionErrorMsg(MsgType.SettlInstructions.getValue(),
                        header.getBeginString(), header.getApplVerID()));

            case FIX41:
                message = new SettlementInstructionsMsg41(header, fixMsg);
                break;

            case FIX42:
                message = new SettlementInstructionsMsg42(header, fixMsg);
                break;

            case FIX43:
                message = new SettlementInstructionsMsg43(header, fixMsg);
                break;

            case FIX44:
                message = new SettlementInstructionsMsg44(header, fixMsg);
                break;

            case FIX50:
                message = new SettlementInstructionsMsg50(header, fixMsg);
                break;

            case FIX50SP1:
                message = new SettlementInstructionsMsg50SP1(header, fixMsg);
                break;

            case FIX50SP2:
                message = new SettlementInstructionsMsg50SP2(header, fixMsg);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
