/*
 *  Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *              Use is subject to license terms.
 */

package net.hades.fix.message;

/**
 * Binary message only.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class BinaryMessage implements Message {

    private static final long serialVersionUID = 1L;
    
    private final byte[] payload;
    private int priority;
    private long orderSequence;

    public BinaryMessage(byte[] payload) {
	this.payload = payload;
    }
    
    @Override
    public byte[] getRawMessage() {
	return payload;

    }

    @Override
    public int getPriority() {
	return priority;
    }

    @Override
    public long getOrderSequence() {
	return orderSequence;
    }

    @Override
    public void setPriority(int priority) {
	this.priority = priority;
    }

    @Override
    public void setOrderSequence(long orderSequence) {
	this.orderSequence = orderSequence;
    }

}
