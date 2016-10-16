/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.handler.pass;

import java.util.Map;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.Message;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.util.MsgUtil;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.hades.fix.engine.handler.AbstractHandler;
import net.hades.fix.message.exception.InvalidMsgException;

/**
 * Just a pass-through service process. It will just print the data passed in both directions and forward the message.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class PassThroughHandler extends AbstractHandler {

    private static final Logger LOGGER = Logger.getLogger(PassThroughHandler.class.getName());

    public PassThroughHandler(String id) {
	super(id);
    }

    @Override
    protected void process(Message message) {
	try {
	    ((FIXMsg) message).decode();
	    if (!nextHandlers.values().isEmpty()) {
		nextHandlers.values().iterator().next().write(message);
		LOGGER.log(Level.INFO, "Handler [{0}] Message relayed : {1}", new Object[]{id, MsgUtil.getPrintableRawFixMessage(((FIXMsg) message).encode())});
	    }
        } catch (InvalidMsgException ex) {
            LOGGER.log(Level.SEVERE, "Invalid message error.", ex);
        } catch (BadFormatMsgException ex) {
            LOGGER.log(Level.SEVERE, "Message format error.", ex);
        } catch (TagNotPresentException ex) {
	    LOGGER.log(Level.SEVERE, "TAg does not exists error.", ex);
	}
    }
    
    @Override
    public Map<String, String> getStatistics() {
	return null;
    }
}
