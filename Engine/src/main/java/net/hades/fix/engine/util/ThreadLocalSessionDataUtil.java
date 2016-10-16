/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ThreadLocalSessionDataUtil.java
 *
 * $Id:$
 */
package net.hades.fix.engine.util;

import net.hades.fix.engine.config.model.SessionInfo;
import net.hades.fix.engine.process.protocol.ProtocolVersion;
import net.hades.fix.message.config.SessionContext;
import net.hades.fix.message.config.SessionContextKey;
import net.hades.fix.message.config.ThreadData;
import net.hades.fix.message.type.BeginString;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility that sets thread context data on threads that do processing of FIX messages.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.51 $
 */
public class ThreadLocalSessionDataUtil {

    private static final Logger LOGGER = Logger.getLogger(ThreadLocalSessionDataUtil.class.getName());

    private ThreadLocalSessionDataUtil() {
    }

    public static void setThreadLocalSessionData(SessionInfo configuration, ProtocolVersion protocolVersion) {
        SessionContext context = ThreadData.getSessionContext();
        if (context.getValue(SessionContextKey.VALIDATE_REQUIRED) == null) {
            context.setValue(SessionContextKey.PRINTABLE_FIXML, configuration.getPrintableFIXML() != null
                    ? configuration.getPrintableFIXML().booleanValue()
                    : Boolean.FALSE);
            context.setValue(SessionContextKey.VALIDATE_INCOMING_FIXML, configuration.getValidateIncomingFIXML() != null
                    ? configuration.getValidateIncomingFIXML().booleanValue()
                    : Boolean.FALSE);
            context.setValue(SessionContextKey.VALIDATE_OUTGOING_FIXML, configuration.getValidateOutgoingFIXML() != null
                    ? configuration.getValidateOutgoingFIXML().booleanValue()
                    : Boolean.FALSE);
            context.setValue(SessionContextKey.FIXML_VALIDATE_ABORT_ON_ERROR, configuration.getAbortFIXMLValidationOnError()
                    ? configuration.getAbortFIXMLValidationOnError().booleanValue()
                    : Boolean.FALSE);
            context.setValue(SessionContextKey.VALIDATE_REQUIRED, configuration.getEnableMsgValidation() != null
                    ? configuration.getEnableMsgValidation()
                    : Boolean.FALSE);
        }
        if (BeginString.FIX_5_0.equals(protocolVersion.getBeginString())
                || BeginString.FIX_5_0SP1.equals(protocolVersion.getBeginString())
                || BeginString.FIX_5_0SP2.equals(protocolVersion.getBeginString())) {
            context.setValue(SessionContextKey.USE_NON_STD_BEGIN_STRING, Boolean.TRUE);
        } else {
            context.setValue(SessionContextKey.USE_NON_STD_BEGIN_STRING, Boolean.FALSE);
        }
        context.setValue(SessionContextKey.DEFAULT_APPL_VER_ID, configuration.getDefaultApplVerID());
        context.setValue(SessionContextKey.CRYPTER, configuration.getEncryption() != null
                ? configuration.getEncryption().getEncryptionType()
                : null);
        context.setValue(SessionContextKey.ENCRYPTION_DATA, configuration.getEncryption() != null
                ? configuration.getEncryption().getEncryptionSymKey()
                : null);
        context.setValue(SessionContextKey.ENCRYPTION_IMPL_CLASS, configuration.getEncryption() != null
                ? configuration.getEncryption().getEncryptImplClass()
                : null);

        LOGGER.log(Level.INFO, "Session context set for thread [{0}].", Thread.currentThread().getName());
    }

}
