/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * CastorCodec.java
 *
 * $Id: CastorCodec.java,v 1.1 2009-07-06 03:19:19 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec.castor;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.xml.codec.FixmlCodec;

/**
 * Castor implementation of the FIXML codec.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public class CastorCodec implements FixmlCodec {

    @Override
    public String marshall(FIXMsg message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FIXMsg unmarshall(String fixml, FIXMsg message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPrintableFormatting(boolean value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isPrintableFormatting() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isValidateIncoming() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setValidateIncoming(boolean validateIncoming) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isValidateOutgoing() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setValidateOutgoing(boolean validateOutgoing) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isValidationThrowingError() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setValidationThrowingError(boolean validationThrowingError) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
