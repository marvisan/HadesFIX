/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * AdvertismentMsgBuilder.java
 *
 * $Id: AdvertismentMsgBuilder.java,v 1.4 2010-11-17 10:55:04 vrotaru Exp $
 */
package net.hades.fix.message.builder.impl;

import net.hades.fix.message.AdvertisementMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.impl.v43.AdvertisementMsg43;
import net.hades.fix.message.impl.v44.AdvertisementMsg44;
import net.hades.fix.message.impl.v50.AdvertisementMsg50;
import net.hades.fix.message.impl.v50sp2.AdvertisementMsg50SP2;
import net.hades.fix.message.type.ApplVerID;

import java.nio.ByteBuffer;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.builder.MsgBuilder;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.impl.v40.AdvertisementMsg40;
import net.hades.fix.message.impl.v41.AdvertisementMsg41;
import net.hades.fix.message.impl.v42.AdvertisementMsg42;
import net.hades.fix.message.impl.v50sp1.AdvertisementMsg50SP1;

/**
 * Builds a advertisment message for a specific version.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 20/10/2008, 15:02:51
 */
public class AdvertismentMsgBuilder extends MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public AdvertismentMsgBuilder() {
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    @Override
    public FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException {
        
        AdvertisementMsg fixMsg = null;
        checkTransportVersion(header.getBeginString());
        switch (header.getBeginString()) {
            case FIX_4_0:
                fixMsg = new AdvertisementMsg40(header, message);
                break;

            case FIX_4_1:
                fixMsg = new AdvertisementMsg41(header, message);
                break;
                
            case FIX_4_2:
                fixMsg = new AdvertisementMsg42(header, message);
                break;
                
            case FIX_4_3:
                fixMsg = new AdvertisementMsg43(header, message);
                break;
                
            case FIX_4_4:
                fixMsg = new AdvertisementMsg44(header, message);
                break;

            case FIX_5_0:
                fixMsg = new AdvertisementMsg50(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                fixMsg = new AdvertisementMsg50SP1(header, message);
                fixMsg.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                fixMsg = new AdvertisementMsg50SP2(header, message);
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
        
        AdvertisementMsg message = null;
        checkTransportVersion(version);
        switch (version) {
            case FIX_4_0:
                message = new AdvertisementMsg40(version);
                break;
                
            case FIX_4_1:
                message = new AdvertisementMsg41(version);
                break;
                
            case FIX_4_2:
                message = new AdvertisementMsg42(version);
                break;
                
            case FIX_4_3:
                message = new AdvertisementMsg43(version);
                break;
                
            case FIX_4_4:
                message = new AdvertisementMsg44(version);
                break;

            case FIX_5_0:
                message = new AdvertisementMsg50(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50);
                break;

            case FIX_5_0SP1:
                message = new AdvertisementMsg50SP1(version);
                message.getHeader().setApplVerID(ApplVerID.FIX50SP1);
                break;

            case FIX_5_0SP2:
                message = new AdvertisementMsg50SP2(version);
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

    private AdvertisementMsg createFIXTMessage(BeginString version, ApplVerID applVerID) throws InvalidMsgException {

        AdvertisementMsg message = null;
        checkApplVersion(applVerID);
        switch (applVerID) {
            case FIX40:
                message = new AdvertisementMsg40(version, applVerID);
                break;

            case FIX41:
                message = new AdvertisementMsg41(version, applVerID);
                break;

            case FIX42:
                message = new AdvertisementMsg42(version, applVerID);
                break;

            case FIX43:
                message = new AdvertisementMsg43(version, applVerID);
                break;

            case FIX44:
                message = new AdvertisementMsg44(version, applVerID);
                break;

            case FIX50:
                message = new AdvertisementMsg50(version, applVerID);
                break;

            case FIX50SP1:
                message = new AdvertisementMsg50SP1(version, applVerID);
                break;

            case FIX50SP2:
                message = new AdvertisementMsg50SP2(version, applVerID);
                break;
        }

        return message;
    }

    private AdvertisementMsg createFIXTMessage(Header header, ByteBuffer fixMsg)
    throws InvalidMsgException, TagNotPresentException, BadFormatMsgException {

        AdvertisementMsg message = null;
        checkApplVersion(header.getApplVerID());
        switch (header.getApplVerID()) {
            case FIX40:
                message = new AdvertisementMsg40(header, fixMsg);
                break;

            case FIX41:
                message = new AdvertisementMsg41(header, fixMsg);
                break;

            case FIX42:
                message = new AdvertisementMsg42(header, fixMsg);
                break;

            case FIX43:
                message = new AdvertisementMsg43(header, fixMsg);
                break;

            case FIX44:
                message = new AdvertisementMsg44(header, fixMsg);
                break;

            case FIX50:
                message = new AdvertisementMsg50(header, fixMsg);
                break;

            case FIX50SP1:
                message = new AdvertisementMsg50SP1(header, fixMsg);
                break;

            case FIX50SP2:
                message = new AdvertisementMsg50SP2(header, fixMsg);
                break;
        }

        return message;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
