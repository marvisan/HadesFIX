/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.handler.dump;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.handler.AbstractHandler;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Message;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;

/**
 * Just a print message service handler. It will just print the data passed in both directions.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class DumpHandler extends AbstractHandler {

    private static final Logger LOGGER = Logger.getLogger(DumpHandler.class.getName());

    public DumpHandler(String id) {
	super(id);
    }

    @Override
    protected void process(Message message) {
	try {
	    if (!disabled) {
		((FIXMsg) message).decode();
		LOGGER.log(Level.INFO, "Handler [{0}] Message consumed : {1}", new Object[]{id, message.toString()});
	    }
        } catch (InvalidMsgException ex) {
            LOGGER.log(Level.SEVERE, "Invalid message error.", ex);
        } catch (BadFormatMsgException ex) {
            LOGGER.log(Level.SEVERE, "Message format error.", ex);
        }
    }

    @Override
    public Map<String, String> getStatistics() {
	return null;
    }
}
