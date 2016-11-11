/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * EmailMsgBuilder.java
 *
 * $Id: EmailMsgBuilder.java,v 1.4 2010-11-17 10:55:04 vrotaru Exp $
 */
package net.hades.fix.message.builder.impl;

import net.hades.fix.message.EmailMsg;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.impl.v41.EmailMsg41;
import net.hades.fix.message.impl.v42.EmailMsg42;
import net.hades.fix.message.impl.v50.EmailMsg50;
import net.hades.fix.message.impl.v50sp1.EmailMsg50SP1;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;

import java.nio.ByteBuffer;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.impl.v40.EmailMsg40;
import net.hades.fix.message.impl.v43.EmailMsg43;
import net.hades.fix.message.impl.v44.EmailMsg44;
import net.hades.fix.message.impl.v50sp2.EmailMsg50SP2;

/**
 * Builds a Email message for a specific version.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 02/04/2009, 9:25:12 AM
 */
public class EmailMsgBuilder extends MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    public EmailMsgBuilder() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {

        EmailMsg fixMsg = null;
        checkTransportVersion(header.getBeginString());
        switch (header.getBeginString()) {
            case FIX_4_0:
                fixMsg = new EmailMsg40(header, message);
                break;

            case FIX_4_1:
                fixMsg = new EmailMsg41(header, message);
                break;

            case FIX_4_2:
                fixMsg = new EmailMsg42(header, message);
                break;

            case FIX_4_3:
                fixMsg = new EmailMsg43(header, message);
                break;

            case FIX_4_4:
                fixMsg = new EmailMsg44(header, message);
                break;

            case FIX_5_0:
                fixMsg = new EmailMsg50(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                fixMsg = new EmailMsg50SP1(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                fixMsg = new EmailMsg50SP2(header, message);
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

        EmailMsg message = null;
        checkTransportVersion(version);
        switch (version) {
            case FIX_4_0:
                message = new EmailMsg40(version);
                break;

            case FIX_4_1:
                message = new EmailMsg41(version);
                break;

            case FIX_4_2:
                message = new EmailMsg42(version);
                break;

            case FIX_4_3:
                message = new EmailMsg43(version);
                break;

            case FIX_4_4:
                message = new EmailMsg44(version);
                break;

            case FIX_5_0:
                message = new EmailMsg50(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                message = new EmailMsg50SP1(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                message = new EmailMsg50SP2(version);
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

    private EmailMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {

        EmailMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
                message = new EmailMsg40(version, applVerID);
                break;

            case FIX41:
                message = new EmailMsg41(version, applVerID);
                break;

            case FIX42:
                message = new EmailMsg42(version, applVerID);
                break;

            case FIX43:
                message = new EmailMsg43(version, applVerID);
                break;

            case FIX44:
                message = new EmailMsg44(version, applVerID);
                break;

            case FIX50:
                message = new EmailMsg50(version, applVerID);
                break;

            case FIX50SP1:
                message = new EmailMsg50SP1(version, applVerID);
                break;

            case FIX50SP2:
                message = new EmailMsg50SP2(version, applVerID);
                break;
        }

        return message;
    }

    private EmailMsg createFIXTMessage(Header header, ByteBuffer fixMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {

        EmailMsg message = null;
        checkApplVersion(header.getApplVerID());
        switch (header.getApplVerID()) {
            case FIX40:
                message = new EmailMsg40(header, fixMsg);
                break;

            case FIX41:
                message = new EmailMsg41(header, fixMsg);
                break;

            case FIX42:
                message = new EmailMsg42(header, fixMsg);
                break;

            case FIX43:
                message = new EmailMsg43(header, fixMsg);
                break;

            case FIX44:
                message = new EmailMsg44(header, fixMsg);
                break;

            case FIX50:
                message = new EmailMsg50(header, fixMsg);
                break;

            case FIX50SP1:
                message = new EmailMsg50SP1(header, fixMsg);
                break;

            case FIX50SP2:
                message = new EmailMsg50SP2(header, fixMsg);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
