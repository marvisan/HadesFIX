/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.process.protocol;

/**
 * Holds the first and last sequenece numbers in a gap.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class SeqGap {

    private int resend;
    private int first;
    private int last;

    public SeqGap(int first, int last) {
	resend = 1;
        this.first = first;
        this.last = last;
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public void setLast(int last) {
        this.last = last;
    }
    
    public void resend() {
	resend++;
    }
    
    public int getResend() {
	return resend;
    }
 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("resend=");
	sb.append(resend);
	sb.append(" first=").append(first);
        sb.append(" last=").append(last);
        return sb.toString();
    }
}
