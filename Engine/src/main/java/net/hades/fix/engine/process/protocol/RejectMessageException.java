/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol;

import net.hades.fix.message.Message;
import net.hades.fix.message.type.SessionRejectReason;

/**
 * Exception thrown when a message needs rejected.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class RejectMessageException extends Exception {

    private static final long serialVersionUID = 1L;

    private final Message rejected;
    private SessionRejectReason reason;

    public RejectMessageException(Message rejected, SessionRejectReason reason) {
	super(reason.name());
	this.rejected = rejected;
    }

    public Message getRejected() {
	return rejected;
    }

    public SessionRejectReason getReason() {
	return reason;
    }

}
