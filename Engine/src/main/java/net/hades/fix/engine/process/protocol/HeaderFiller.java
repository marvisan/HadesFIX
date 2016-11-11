/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol;

import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.BeginString;
import net.hades.fix.message.util.MsgUtil;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Fills the header with configured data.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class HeaderFiller {

    private static final Logger LOGGER = Logger.getLogger(HeaderFiller.class.getName());

    private HeaderFiller() {
    }

    public static Header fillHeader(Protocol protocol, Header header) {
        header.setSenderCompID(protocol.getConfiguration().getCompID());
        header.setSenderSubID(protocol.getConfiguration().getSubID());

        header.setTargetCompID(protocol.getTargetCompID());
        header.setTargetSubID(protocol.getTargetSubID());
        header.setDeliverToCompID(protocol.getConfiguration().getDeliverToCompID());
        header.setDeliverToSubID(protocol.getConfiguration().getDeliverToSubID());

        ApplVerID ver = null;
        if (MsgUtil.compare(header.getBeginString(), BeginString.FIXT_1_1) >= 0) {
            ver = header.getApplVerID();
            header.setCstmApplVerID(protocol.getConfiguration().getCustomApplVerID());
            header.setApplVerID(ver);
        } else {
            try {
                ver = MsgUtil.getApplVerFromBeginString(header.getBeginString());
            } catch (InvalidMsgException ex) {
               // this will not happen here
            }
        }
        if (MsgUtil.compare(ver, ApplVerID.FIX41) >= 0) {
            header.setSenderLocationID(protocol.getConfiguration().getLocationID());
            header.setTargetLocationID(protocol.getTargetLocationID());
            header.setDeliverToLocationID(protocol.getConfiguration().getDeliverToLocationID());
        }
        if (MsgUtil.compare(ver, ApplVerID.FIX42) >= 0) {
            if (protocol.getConfiguration().getMessageEncoding() != null) {
                header.setMessageEncoding(Charset.forName(protocol.getConfiguration().getMessageEncoding()));
            }
        }
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Filled header : {0}", header.toString());
        }
        return header;
    }

    public static Header fillHeaderUnfilled(Protocol protocol, Header header) {
        header.setMsgSeqNum(protocol.getNextTxSeqNo());
        if (header.getSenderCompID() == null || header.getSenderCompID().trim().isEmpty()) {
            header.setSenderCompID(protocol.getConfiguration().getCompID());
        }
        if (header.getSenderSubID() == null || header.getSenderSubID().trim().isEmpty()) {
            header.setSenderSubID(protocol.getConfiguration().getSubID());
        }
        if (header.getTargetCompID() == null || header.getTargetCompID().trim().isEmpty()) {
            header.setTargetCompID(protocol.getTargetCompID());
        }
        if (header.getTargetSubID() == null || header.getTargetSubID().trim().isEmpty()) {
            header.setTargetSubID(protocol.getTargetSubID());
        }
        if (header.getDeliverToCompID() == null || header.getDeliverToCompID().trim().isEmpty()) {
            header.setDeliverToCompID(protocol.getConfiguration().getDeliverToCompID());
        }
        if (header.getDeliverToSubID() == null || header.getDeliverToSubID().trim().isEmpty()) {
            header.setDeliverToSubID(protocol.getConfiguration().getDeliverToSubID());
        }
        header.setSendingTime(new Date());
        ApplVerID ver = null;
        if (MsgUtil.compare(header.getBeginString(), BeginString.FIXT_1_1) >= 0) {
            ver = header.getApplVerID();
            header.setCstmApplVerID(protocol.getConfiguration().getCustomApplVerID());
            header.setApplVerID(ver);
        } else {
            try {
                ver = MsgUtil.getApplVerFromBeginString(header.getBeginString());
            } catch (InvalidMsgException ex) {
               // this will not happen here
            }
        }
        if (MsgUtil.compare(ver, ApplVerID.FIX41) >= 0) {
            if (header.getSenderLocationID() == null || header.getSenderLocationID().trim().isEmpty()) {
                header.setSenderLocationID(protocol.getConfiguration().getLocationID());
            }
            if (header.getTargetLocationID() == null || header.getTargetLocationID().trim().isEmpty()) {
                header.setTargetLocationID(protocol.getTargetLocationID());
            }
            if (header.getDeliverToLocationID() == null || header.getDeliverToLocationID().trim().isEmpty()) {
                header.setDeliverToLocationID(protocol.getConfiguration().getDeliverToLocationID());
            }
        }
        if (MsgUtil.compare(ver, ApplVerID.FIX42) >= 0) {
            if (protocol.getConfiguration().getMessageEncoding() != null) {
                header.setMessageEncoding(Charset.forName(protocol.getConfiguration().getMessageEncoding()));
            }
        }

        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Filled header : {0}", header.toString());
        }

        return header;
    }
}
