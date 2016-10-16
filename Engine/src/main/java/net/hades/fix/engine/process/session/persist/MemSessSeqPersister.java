/*
 *   Copyright (c) 2006-2014 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MemSessSeqPersister.java
 *
 * $Id:$
 */
package net.hades.fix.engine.process.session.persist;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import net.hades.fix.engine.exception.ConfigurationException;
import net.hades.fix.engine.exception.SeqNoPersistenceException;

/**
 * Persister in memory class for the session sequence numbers.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision:$
 */
public class MemSessSeqPersister extends SessSeqPersister {

    public MemSessSeqPersister(String configDir, boolean resetAtStartup) throws ConfigurationException {
        super(configDir, resetAtStartup);
        initialise();
        if (LOGGER.isLoggable(Level.FINEST)) {
            LOGGER.log(Level.FINEST, "Session seq number persister initialised : rxSeqNo [{0}] txSeqNo [{1}].",
                    new Object[]{rxSeqNo.intValue(), txSeqNo.intValue()});
        }
    }

    public int getNextRxSeqNo() throws SeqNoPersistenceException {
        return rxSeqNo.incrementAndGet();
    }

    public void setRxSeqNo(int rxSeqNo) throws SeqNoPersistenceException {
        this.rxSeqNo.set(rxSeqNo);
    }

    public int getNextTxSeqNo() throws SeqNoPersistenceException {
        return txSeqNo.incrementAndGet();
    }

    public void setTxSeqNo(int txSeqNo) throws SeqNoPersistenceException {
        this.txSeqNo.set(txSeqNo);
    }

    private void initialise() throws ConfigurationException {
        rxSeqNo = new AtomicInteger(0);
        txSeqNo = new AtomicInteger(0);
    }

}
