/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SeqSet.java
 *
 * $Id: SeqSet.java,v 1.1 2010-06-06 07:59:15 vrotaru Exp $
 */
package net.hades.fix.engine.process.protocol;

/**
 * Set of RX and TX sequences.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public class SeqSet {

    private int rxSeq;

    private int txSeq;

    public SeqSet() {
    }

    public SeqSet(int rxSeq, int txSeq) {
        this.rxSeq = rxSeq;
        this.txSeq = txSeq;
    }

    public int getRxSeq() {
        return rxSeq;
    }

    public void setRxSeq(int rxSeq) {
        this.rxSeq = rxSeq;
    }

    public int getTxSeq() {
        return txSeq;
    }

    public void setTxSeq(int txSeq) {
        this.txSeq = txSeq;
    }
}
