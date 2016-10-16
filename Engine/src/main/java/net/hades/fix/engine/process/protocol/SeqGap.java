/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SeqGap.java
 *
 * $Id: SeqGap.java,v 1.3 2011-03-22 11:53:52 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol;

/**
 * Holds the first and last sequenece numbers in a gap.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 */
public class SeqGap {

    private int first;
    private int last;

    public SeqGap(int first, int last) {
        this.first = first;
        this.last = last;
    }

    public synchronized int getFirst() {
        return first;
    }

    public synchronized int getLast() {
        return last;
    }

    public synchronized void setFirst(int first) {
        this.first = first;
    }

    public synchronized void setLast(int last) {
        this.last = last;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("first=");
        sb.append(first).append(" last=").append(last);

        return sb.toString();
    }
}
