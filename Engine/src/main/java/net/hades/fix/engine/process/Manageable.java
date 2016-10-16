/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Manageable.java
 *
 * $Id: Manageable.java,v 1.9 2011-04-07 09:57:51 vrotaru Exp $
 */
package net.hades.fix.engine.process;

import net.hades.fix.engine.mgmt.data.ProcessData;
import net.hades.fix.engine.mgmt.data.Stats;
import net.hades.fix.engine.mgmt.data.ProcessStatus;

/**
 * Interface implemented by a process in order to be managed external.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.9 $
 */
public interface Manageable {

    /**
     * Returns the status of the process.
     * @return
     */
    ProcessStatus getProcessStatus();

    /**
     * Sets the process status.
     * @param processStatus process status
     */
    void setProcessStatus(ProcessStatus processStatus);

    /**
     * Gets the collected management data since the component was active.
     * @return statistics data
     */
    ProcessData getMgmtData();

    /**
     * Get the collected statistic data
     * @return
     */
    Stats getStats();
    
    /**
     * Session address on which this component runs in format: counterparty:party
     * @return 
     */
    String retrieveSessionAddress();
}
