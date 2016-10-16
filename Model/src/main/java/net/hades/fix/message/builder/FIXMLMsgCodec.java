/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXMLMsgCodec.java
 */
package net.hades.fix.message.builder;

import net.hades.fix.message.config.SessionContext;
import net.hades.fix.message.config.SessionContextKey;
import net.hades.fix.message.config.ThreadData;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.exception.FixmlDecodingException;
import net.hades.fix.message.exception.FixmlEncodingException;
import net.hades.fix.message.xml.codec.FixmlCodec;
import net.hades.fix.message.xml.codec.util.FixmlCodecUtil;

/**
 * Utility class to encode and decode a FIX message in FIXML.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class FIXMLMsgCodec {

    private FixmlCodec codec;

    public FIXMLMsgCodec() {
        codec = FixmlCodecUtil.getInstance().getCodec();
    }

    public FIXMLMsgCodec(FIXMLMsgCodecContext context) {
        createSessionConfig(context);
        codec = FixmlCodecUtil.getInstance().getCodec();
    }

    /**
     * Creates a new instance of the codec without setting the  parameters that configures the codec behavior.
     * @return new instance of this class
     */
    public static FIXMLMsgCodec newInstance()  {
        return new FIXMLMsgCodec();
    }
    
    /**
     * Creates a new instance of the codec setting the  parameters that configures the codec behavior.
     * @param context parameters that configure the marshal/un-marshal behavior
     * @return new instance of this class
     */
    public static FIXMLMsgCodec newInstance(FIXMLMsgCodecContext context)  {
        return new FIXMLMsgCodec(context);
    }

    /**
     * Marshall the given FIX message to a FIXML message.
     * @param message
     * @return
     * @throws net.hades.fix.message.exception.FixmlEncodingException error in encoding the FIX message.
     */
    public String marshall(FIXMsg message) throws FixmlEncodingException {
        return codec.marshall(message);
    }

    /**
     * Un-marshal the given FIXML message document and creates a FIX message object
     * populated with data.
     * @param fixml XML FIX message data
     * @return FIX message object
     * @throws net.hades.fix.message.exception.FixmlDecodingException error in decoding the FIXML message
     */
    public FIXMsg unmarshall(String fixml) throws FixmlDecodingException {
        return codec.unmarshall(fixml, null);
    }

    private void createSessionConfig(FIXMLMsgCodecContext context) {
        SessionContext sessCtx = ThreadData.getSessionContext();
        if (sessCtx == null) {
            sessCtx = new SessionContext();
        }
        sessCtx.setValue(SessionContextKey.PRINTABLE_FIXML, context.isPrintableXML());
        sessCtx.setValue(SessionContextKey.VALIDATE_INCOMING_FIXML, context.isValidateDecodedXML());
        sessCtx.setValue(SessionContextKey.VALIDATE_OUTGOING_FIXML, context.isValidateEncodedXML());
        sessCtx.setValue(SessionContextKey.FIXML_VALIDATE_ABORT_ON_ERROR, context.isAbortOnFailedValidation());
    }
}
