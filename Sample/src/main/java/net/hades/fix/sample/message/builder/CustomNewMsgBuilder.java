/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.6
 *               Use is subject to license terms.
 */
package net.hades.fix.sample.message.builder;

import net.hades.fix.sample.message.CustomNewMsg;

import java.nio.ByteBuffer;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;

public class CustomNewMsgBuilder extends MsgBuilder {

    public CustomNewMsgBuilder() {
    }
    
    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {
        CustomNewMsg fixMsg = null;
        checkTransportVersion(header.getBeginString());
        switch (header.getBeginString()) {
            case FIX_4_0:
            case FIX_4_1:
            case FIX_4_2:
            case FIX_4_3:
            case FIX_4_4:
                fixMsg = new CustomNewMsg(header, message);
                break;
            case FIXT_1_1:
                fixMsg = createFIXTMessage(header, message);
                break;
        }
        fixMsg.decode();

        return fixMsg;
    }

    @Override
    public FIXMsg build(BeginString version, ApplVerID applVerID) throws InvalidMsgException {
        CustomNewMsg message = null;
        checkTransportVersion(version);
        switch (version) {
            case FIX_4_0:
            case FIX_4_1:
            case FIX_4_2:
            case FIX_4_3:
            case FIX_4_4:
                message = new CustomNewMsg(version);
                break;
            case FIXT_1_1:
                message = createFIXTMessage(version, applVerID);
                break;
        }

        return message;
    }

    private CustomNewMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {
        CustomNewMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
            case FIX41:
            case FIX42:
            case FIX43:
            case FIX44:
            case FIX50:
            case FIX50SP1:
            case FIX50SP2:
                message = new CustomNewMsg(version, applVerID);
        }

        return message;
    }

    private CustomNewMsg createFIXTMessage(Header header, ByteBuffer fixMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {
        CustomNewMsg message = null;
        checkApplVersion(header.getApplVerID());
        switch (header.getApplVerID()) {
            case FIX40:
            case FIX41:
            case FIX42:
            case FIX43:
            case FIX44:
            case FIX50:
            case FIX50SP1:
            case FIX50SP2:
                message = new CustomNewMsg(header, fixMsg);
        }

        return message;
    }
}
