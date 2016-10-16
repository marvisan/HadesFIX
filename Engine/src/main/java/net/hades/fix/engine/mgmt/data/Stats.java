/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Stats.java
 *
 * $Id: Stats.java,v 1.1 2010-05-07 08:51:21 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Super class of all the statistics.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 */
public abstract class Stats implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Date startTimestamp;

    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }
}
