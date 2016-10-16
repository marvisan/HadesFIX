/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FixmlCodecUtil.java
 */
package net.hades.fix.message.xml.codec.util;

import java.util.logging.Logger;

import net.hades.fix.message.config.SessionContext;
import net.hades.fix.message.config.SessionContextKey;
import net.hades.fix.message.config.ThreadData;
import net.hades.fix.message.xml.codec.FixmlCodec;
import net.hades.fix.message.xml.codec.jaxb.JAXBCodec;

/**
 * Utility class used to load a codec implementation and configure it.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class FixmlCodecUtil {

    private static final FixmlCodecUtil INSTANCE;

    protected static final Logger LOGGER = Logger.getLogger(FixmlCodecUtil.class.getName());

    static {
        INSTANCE = new FixmlCodecUtil();
    }

    /**
     * Singleton getter.
     * @return same instance of the utility class
     */
    public static FixmlCodecUtil getInstance() {
        return INSTANCE;
    }

    /**
     * Loads a FIXML codec implementation.
     * @return codec implementation
     */
    public FixmlCodec getCodec() {
        SessionContext context = ThreadData.getSessionContext();
        FixmlCodec codecService = (FixmlCodec) context.getValue(SessionContextKey.FIXML_CODEC);
        if (codecService == null) {
            codecService = new JAXBCodec();
	    setFixmlCodecConfiguration(codecService);
            context.setValue(SessionContextKey.FIXML_CODEC, codecService);
        }

        return codecService;
    }

    private void setFixmlCodecConfiguration(FixmlCodec fixmlCodec) {
        SessionContext context = ThreadData.getSessionContext();
        if (context == null) {
            LOGGER.warning("Session context data has not been set by the current FIX session.");
        } else {
            if (context.getValue(SessionContextKey.PRINTABLE_FIXML) == null) {
                LOGGER.finest("Session FIXML printable format flag has not been set for the current session.");
            } else {
                fixmlCodec.setPrintableFormatting(((Boolean) context.getValue(SessionContextKey.PRINTABLE_FIXML)));
            }
            if (context.getValue(SessionContextKey.VALIDATE_INCOMING_FIXML) == null) {
                LOGGER.finest("Session FIXML incoming message validation flag has not been set for the current session.");
            } else {
                fixmlCodec.setValidateIncoming(((Boolean) context.getValue(SessionContextKey.VALIDATE_INCOMING_FIXML)));
            }
            if (context.getValue(SessionContextKey.VALIDATE_OUTGOING_FIXML) == null) {
                LOGGER.finest("Session FIXML outgoing message validation flag has not been set for the current session.");
            } else {
                fixmlCodec.setValidateOutgoing(((Boolean) context.getValue(SessionContextKey.VALIDATE_OUTGOING_FIXML)));
            }
            if (context.getValue(SessionContextKey.FIXML_VALIDATE_ABORT_ON_ERROR) == null) {
                LOGGER.finest("Session FIXML abort on error validation flag has not been set for the current session.");
            } else {
                fixmlCodec.setValidationThrowingError(((Boolean) context.getValue(SessionContextKey.FIXML_VALIDATE_ABORT_ON_ERROR)));
            }
        }
    }
}
