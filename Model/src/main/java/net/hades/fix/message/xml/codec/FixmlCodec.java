/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FixmlCodec.java
 *
 * $Id: FixmlCodec.java,v 1.1 2009-07-06 03:19:15 vrotaru Exp $
 */
package net.hades.fix.message.xml.codec;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.exception.FixmlDecodingException;
import net.hades.fix.message.exception.FixmlEncodingException;

/**
 * Interface defining the contract to be fulfield by a FIXML encoder/decoder.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 28/03/2009, 7:25:43 PM
 */
public interface FixmlCodec {

    /**
     * Marshall a FIX message in FIXML format and returns it as a string.
     * @param message
     * @return FIX message as a FIXML string
     * @throws net.hades.fix.message.exception.FixmlEncodingException XML marshalling runtime error
     */
    String marshall(FIXMsg message) throws FixmlEncodingException;

    /**
     * Decodes a FIXML message and populate the given message with date. If the client
     * does not know the fix version and as a consequence cannot build a proper FIX message
     * then it should pass a null message as parameter and this method will create the message
     * from the FIXML data.
     * @param fixml FIXML message
     * @param message FIX message of correct version with the one of the FIXML or null
     * @return unmarshalled FIX message data object
     * @throws net.hades.fix.message.exception.FixmlDecodingException XML unmarshallin runtime error
     */
    FIXMsg unmarshall(String fixml, FIXMsg message) throws FixmlDecodingException;

    /**
     * Returns the current state of the printable formating flag.
     * @return true if the XML marshalled will have a printable format
     */
    boolean isPrintableFormatting();

    /**
     * Sets the printable formating flag to the XML marshaller.
     * @param value true in order for the marshaller to generate XML in pritable format
     */
    void setPrintableFormatting(boolean value);

    /**
     * Return the flag that indicates that the FIXML processor will validate incoming
     * data.
     * @return true if the processor is set to validate incoming FIXML meesage against schema.
     */
    boolean isValidateIncoming();

    /**
     * Sets the flag that indicates that the FIXML processor will validate incoming
     * data.
     * @param validateIncoming true if the processor is set to validate incoming FIXML
     * meesage against schema.
     */
    void setValidateIncoming(boolean validateIncoming);

    /**
     * Return the flag that indicates that the FIXML processor will validate outgoing
     * data.
     * @return true if the processor is set to validate incoming FIXML meesage against schema.
     */
    boolean isValidateOutgoing();

    /**
     * Sets the flag that indicates that the FIXML processor will validate outgoing
     * data.
     * @param validateOutgoing true if the processor is set to validate outgoing FIXML
     * meesage against schema.
     */
    void setValidateOutgoing(boolean validateOutgoing);

    /**
     * Getter for the flag that indicates that in the case the FIXML message validation fails
     * the processor will throw an exception.
     * @return true if the processor throws an exception.
     */
    boolean isValidationThrowingError();

    /**
     * Setter for the flag that indicates that in the case the FIXML message validation fails
     * the processor will throw an exception.
     * @param validationThrowingError true if the processor throws an exception. Otherwise the processor
     * will log warnings in the log.
     */
    void setValidationThrowingError(boolean validationThrowingError);
}
