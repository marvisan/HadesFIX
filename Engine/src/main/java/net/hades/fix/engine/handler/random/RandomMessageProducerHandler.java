/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * RandomMessageProducerHandler.java
 *
 * $Id: RandomMessageProducerHandler.java,v 1.6 2011-10-29 09:49:27 vrotaru Exp $
 */
package net.hades.fix.engine.handler.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.hades.fix.engine.handler.AbstractHandler;
import net.hades.fix.engine.process.TaskStatus;
import net.hades.fix.message.*;
import net.hades.fix.message.builder.FIXMsgBuilder;
import net.hades.fix.message.config.SessionContext;
import net.hades.fix.message.config.SessionContextKey;
import net.hades.fix.message.config.ThreadData;
import net.hades.fix.message.exception.BadFormatMsgException;
import net.hades.fix.message.exception.InvalidMsgException;
import net.hades.fix.message.exception.TagNotPresentException;
import net.hades.fix.message.group.LinesOfTextGroup;
import net.hades.fix.message.group.QuoteEntryGroup;
import net.hades.fix.message.group.QuoteRequestGroup;
import net.hades.fix.message.group.QuoteSetGroup;
import net.hades.fix.message.type.*;
import net.hades.fix.message.util.MsgUtil;

/**
 * It will generate a fixed set of random messages at a configured interval of time.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class RandomMessageProducerHandler extends AbstractHandler {

    private static final Logger LOGGER = Logger.getLogger(RandomMessageProducerHandler.class.getName());

    private static final String PRODUCER_INTERVAL = "interval";
    private static final int NO_OF_MESSAGES = 5;


    private List<String> messages;
    private long interval;
    private final Random random;
    private final MessageSender messageSender;

    public RandomMessageProducerHandler(String id) {
	super(id);
	random = new Random(872327463473L);
        messageSender = new MessageSender();
        messageSender.start();
    }

    @Override
    protected void process(Message message) {
	try {
	    if (!disabled) {
		((FIXMsg) message).decode();
		if (!nextHandlers.values().isEmpty()) {
		    nextHandlers.values().iterator().next().write(message);
		    LOGGER.log(Level.INFO, "Handler [{0}] Message relayed : {1}", new Object[]{id, MsgUtil.getPrintableRawFixMessage(((FIXMsg) message).encode())});
		}
		
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

    private class MessageSender extends Thread {

        private volatile boolean shutdown;

        @Override
        public void run() {
            SessionContext context = ThreadData.getSessionContext();
            context.setValue(SessionContextKey.VALIDATE_REQUIRED, Boolean.FALSE);

            createMessages();
            while (!shutdown) {
                try {
                    if (interval == -1) {
                        setProducerInterval();
                    }
                    Thread.sleep(interval * 1000);
                    String msgType = messages.get(Math.abs(random.nextInt()) % NO_OF_MESSAGES);
                    FIXMsg msg = null;
                    if (MsgType.Email.getValue().equals(msgType)) {
                        msg = createEmailMsg();
                    } else if (MsgType.IndicationOfInterest.getValue().equals(msgType)) {
                        msg = createIOIMsg();
                    } else if (MsgType.News.getValue().equals(msgType)) {
                        msg = createNewsMsg();
                    } else if (MsgType.QuoteRequest.getValue().equals(msgType)) {
                        msg = createQuoteRequestMsg();
                    } else if (MsgType.MassQuote.getValue().equals(msgType)) {
                        msg = createMassQuoteMsg();
                    }
                    if (msg != null) {
                        inQueue.put(msg);
                    }
                } catch (InterruptedException ex) {
		    status = TaskStatus.Exiting;
                    LOGGER.info("Producer handler interrupted.");
                    break;
                }
            }

            LOGGER.info("Message sender stopped.");
        }

        private EmailMsg createEmailMsg() {
            EmailMsg msg = null;
            try {
                msg = (EmailMsg) FIXMsgBuilder.build(MsgType.Email.getValue(), BeginString.FIX_4_4);
                msg.setEmailThreadID("2333765");
                msg.setEmailType(EmailType.New);
                msg.setSubject("Test random email");
                LinesOfTextGroup text = msg.addLinesOfTextGroup();
                text.setText("Random email content");
            } catch (InvalidMsgException ex) {
                LOGGER.log(Level.INFO, "Could not produce EmailMsg. Error was [{0}].", ex.getMessage());
            }

            return msg;
        }

        private IOIMsg createIOIMsg() {
            IOIMsg msg = null;
            try {
                msg = (IOIMsg) FIXMsgBuilder.build(MsgType.IndicationOfInterest.getValue(), BeginString.FIX_4_4);
                msg.setIoiID("3339876");
                msg.setIoiTransType(IOITransType.New);
                msg.setInstrument();
                msg.getInstrument().setSymbol("MSFT");
                msg.setSide(Side.Buy);
                msg.setIoiQty(IOIQty.Small);
                msg.setCurrency(Currency.UnitedStatesDollar);
            } catch (InvalidMsgException ex) {
                LOGGER.log(Level.INFO, "Could not produce IOIMsg. Error was [{0}].", ex.getMessage());
            }

            return msg;
        }

        private NewsMsg createNewsMsg() {
            NewsMsg msg = null;
            try {
                msg = (NewsMsg) FIXMsgBuilder.build(MsgType.News.getValue(), BeginString.FIX_4_4);
                msg.setHeadline("News headline for sample news");
                LinesOfTextGroup text = msg.addLinesOfTextGroup();
                text.setText("Random email content");
            } catch (InvalidMsgException ex) {
                LOGGER.log(Level.INFO, "Could not produce NewsMsg. Error was [{0}].", ex.getMessage());
            }

            return msg;
        }

        private QuoteRequestMsg createQuoteRequestMsg() {
            QuoteRequestMsg msg = null;
            try {
                msg = (QuoteRequestMsg) FIXMsgBuilder.build(MsgType.QuoteRequest.getValue(), BeginString.FIX_4_4);
                msg.setQuoteReqID("66742533");
                QuoteRequestGroup qrg = msg.addQuoteRelatedSymbolGroup();
                qrg.getInstrument().setSymbol("ORCL");
            } catch (InvalidMsgException ex) {
                LOGGER.log(Level.INFO, "Could not produce NewsMsg. Error was [{0}].", ex.getMessage());
            }

            return msg;
        }

        private MassQuoteMsg createMassQuoteMsg() {
            MassQuoteMsg msg = null;
            try {
                msg = (MassQuoteMsg) FIXMsgBuilder.build(MsgType.MassQuote.getValue(), BeginString.FIX_4_4);
                msg.setQuoteID("AS5478299");
                QuoteSetGroup qsg = msg.addQuoteSetGroup();
                qsg.setQuoteSetID("QSI8888888");
                qsg.setTotNoQuoteEntries(3);
                QuoteEntryGroup qeg = qsg.addQuoteEntryGroup();
                qeg.setQuoteEntryID("QEI999999");
            } catch (InvalidMsgException ex) {
                LOGGER.log(Level.INFO, "Could not produce NewsMsg. Error was [{0}].", ex.getMessage());
            }

            return msg;
        }

        private void createMessages() {
            messages = new ArrayList<String>();
            FIXMsg msg = createEmailMsg();
            if (msg != null) {
                messages.add(msg.getHeader().getMsgType());
            }
            msg = createIOIMsg();
            if (msg != null) {
                messages.add(msg.getHeader().getMsgType());
            }
            msg = createNewsMsg();
            if (msg != null) {
                messages.add(msg.getHeader().getMsgType());
            }
            msg = createQuoteRequestMsg();
            if (msg != null) {
                messages.add(msg.getHeader().getMsgType());
            }
            msg = createMassQuoteMsg();
            if (msg != null) {
                messages.add(msg.getHeader().getMsgType());
            }
        }

        private void setProducerInterval() {
            String producerInterval = parameters.get(PRODUCER_INTERVAL);
            if (producerInterval != null) {
                interval = Long.valueOf(producerInterval);
            } else {
                // defaults to 5 seconds
                interval = 5L;
            }
        }

        public void shutdown() {
            shutdown = true;
        }

    }

}
