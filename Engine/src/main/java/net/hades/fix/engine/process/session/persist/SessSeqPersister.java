/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SessSeqPersister.java
 *
 * $Id:$
 */
package net.hades.fix.engine.process.session.persist;

import net.hades.fix.engine.config.ConfigurationException;
import net.hades.fix.engine.process.protocol.SeqNoPersistenceException;

import java.util.logging.Logger;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Persister abstract class for the session sequence numbers.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision:$
 */
public abstract class SessSeqPersister {


    protected final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    protected AtomicInteger rxSeqNo;

    protected AtomicInteger txSeqNo;

    protected String configDir;

    protected boolean resetAtStartup;

    protected SessSeqPersister(String configDir, boolean resetAtStartup) throws ConfigurationException {
        this.configDir = configDir;
        this.resetAtStartup = resetAtStartup;
    }

    /**
     * Returns the current RX sequence number.
     * @return RX sequence number
     */
    public int getRxSeqNo() {
        return rxSeqNo.get();
    }

    /**
     * Returns the current TX sequence number.
     * @return TX sequence number
     */
    public int getTxSeqNo() {
        return txSeqNo.get();
    }

    /**
     * Getter for the next RX sequence number. The engine RX sequence number is incremented and
     * persisted locally.
     * @return next seq number
     * @throws net.hades.fix.engine.process.protocol.SeqNoPersistenceException
     */
    public abstract int getNextRxSeqNo() throws SeqNoPersistenceException;

    /**
     * Sets the current RX sequence number and writes it to the sequence number file.
     * @param rxSeqNo new seq number
     * @throws SeqNoPersistenceException
     */
    public abstract void setRxSeqNo(int rxSeqNo) throws SeqNoPersistenceException;

    /**
     * Getter for the next TX sequence number. The engine TX sequence number is incremented and
     * persisted locally.
     * @return next seq num
     * @throws SeqNoPersistenceException
     */
    public abstract int getNextTxSeqNo() throws SeqNoPersistenceException;

    /**
     * Sets the TX sequence number and writes it to the sequence number file.
     * @param txSeqNo new tx sequence number
     * @throws SeqNoPersistenceException
     */
    public abstract void setTxSeqNo(int txSeqNo) throws SeqNoPersistenceException;

}
