/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MsgBuilder.java
 *
 * $Id: MsgBuilder.java,v 1.6 2011-04-14 11:44:45 vrotaru Exp $
 */
package net.hades.fix.message.builder;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Header;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.type.ApplVerID;
import net.hades.fix.message.type.MsgType;

import java.nio.ByteBuffer;
import java.util.EnumSet;
import java.util.logging.Logger;

import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.type.BeginString;

/**
 * Contract for a FIX message builder. The message builders extending this interface must be
 * thread safe.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.6 $
 * @created 20/09/2008, 14:32:41
 */
public abstract class MsgBuilder {

    // <editor-fold defaultstate="collapsed" desc="Constants">

    protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    /**
     * FIXT application version supported by the HadesFIX engine.
     */
    public static final EnumSet<ApplVerID> SUPPORTED_APP_VERSIONS = EnumSet.of(ApplVerID.FIX40,
        ApplVerID.FIX41,
        ApplVerID.FIX42,
        ApplVerID.FIX43,
        ApplVerID.FIX44,
        ApplVerID.FIX50,
        ApplVerID.FIX50SP1,
        ApplVerID.FIX50SP2);

    /**
     * Transport versions supported by the HadesFIX engine.
     */
    public static final EnumSet<BeginString> SUPPORTED_TRANSPORT_VERSIONS = EnumSet.of(BeginString.FIX_4_0,
        BeginString.FIX_4_1,
        BeginString.FIX_4_2,
        BeginString.FIX_4_3,
        BeginString.FIX_4_4,
        BeginString.FIX_5_0,
        BeginString.FIX_5_0SP1,
        BeginString.FIX_5_0SP2,
        BeginString.FIXT_1_1);

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Static Block">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Attributes">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">

    protected MsgBuilder() {
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">

    // ABSTRACT INTERFACE
    //////////////////////////////////////////
    
    /**
     * Decoded a given message and creates the proper message object.
     * @param header header first populated when message is initialised.
     * @param message raw message received from transport to be decoded
     * @return FIX message
     * @throws TagNotPresentException required tag is missing
     * @throws net.hades.fix.message.exception.InvalidMsgException message cannot be decoded (fatal errors)
     * @throws BadFormatMsgException message is incorrect (retransmit required)
     */
    public abstract FIXMsg build(Header header, ByteBuffer message) throws TagNotPresentException, InvalidMsgException, BadFormatMsgException;
    
    /**
     * Creates a new message for the given parameters. If <code>applVerID</code> is null
     * the pre 5.0 transport level is assumed.
     * @param version begin string version
     * @param applVerID post 5.0 application version
     * @return FIX message
     * @throws TagNotPresentException required tag is missing
     * @throws InvalidMsgException message cannot be encoded
     */
    public abstract FIXMsg build(BeginString version, ApplVerID applVerID) throws TagNotPresentException, InvalidMsgException;
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">

    /**
     * This method checks that the FIXT application version is supported by HadesFIX.
     * @param applVerID version to be checked
     * @throws InvalidMsgException
     */
    protected void checkApplVersion(ApplVerID applVerID) throws InvalidMsgException {
        if (applVerID == null || !MsgBuilder.SUPPORTED_APP_VERSIONS.contains(applVerID)) {
            String error = "Could not build a FIXT message for application version [" + applVerID + "].";
            LOGGER.severe(error);
            throw new InvalidMsgException(error);
        }
    }

    /**
     * Checks the transport version is supported by HadesFIX.
     * @param version
     * @throws InvalidMsgException
     */
    protected void checkTransportVersion(BeginString version) throws InvalidMsgException {
        if (version == null || !MsgBuilder.SUPPORTED_TRANSPORT_VERSIONS.contains(version)) {
            String error = "Message not supported in FIX version [" + version.getValue() + "].";
            LOGGER.severe(error);
            throw new InvalidMsgException(error);
        }
    }

    /**
     * Returns a convenience error message for a message type not supported on a specific version.
     * @param msgType message type
     * @param version begin string version
     * @param applVerID FIXT app version
     * @return error message
     */
    protected String getMessageNotInFixVersionErrorMsg(String msgType, BeginString version, ApplVerID applVerID) {
        StringBuilder b = new StringBuilder("Message [");
        b.append(getMessageTypeName(msgType))
            .append("] is not supported in version [")
            .append(version.getValue());
        if (applVerID != null) {
            b.append("/").append(applVerID.toString());
        }
        b.append("].");

        return b.toString();
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Package Methods">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">

    private String getMessageTypeName(String msgType) {
        String result = msgType;
        MsgType msg = MsgType.valueFor(msgType);
        if (msg != null) {
            result = msg.toString();
        }

        return result;
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Inner Classes">
    // </editor-fold>
}
