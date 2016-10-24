/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol.client;

import net.hades.fix.engine.process.protocol.MessageFiller;
import net.hades.fix.engine.process.protocol.MessageProcessor;
import net.hades.fix.engine.process.protocol.SeqSet;
import net.hades.fix.message.BinaryMessage;
import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.exception.InvalidMsgException;

/**
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class ClientSessionMessageProcessor extends MessageProcessor {

    private final FixClient protocol;

    public ClientSessionMessageProcessor(FixClient protocol) {
	this.protocol = protocol;
    }

    //------------------------------- REQUESTS -------------------------------------------
    
    public FIXMsg processLogonRequest() throws InvalidMsgException {
	protocol.setSessStartSeqSet(new SeqSet(protocol.getRxSeqNo(), protocol.getTxSeqNo()));
	return MessageFiller.buildLogonMsg(protocol);
    }

    //------------------------------- RESPONSES -------------------------------------------
    
    public FIXMsg processLogonResponse(FIXMsg request, BinaryMessage response) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //-------------------------------------------------------------------------------------
}
