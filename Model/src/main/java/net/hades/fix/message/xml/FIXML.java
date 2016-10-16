/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXML.java
 *
 * $Id: FIXML.java,v 1.2 2011-04-27 23:28:25 vrotaru Exp $
 */
package net.hades.fix.message.xml;

import net.hades.fix.message.FIXMsg;
import net.hades.fix.message.comp.Batch;

/**
 * Super interface for the wrapper FIXML classes.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 10/06/2009, 8:41:38 PM
 */
public interface FIXML {
    /**
     * Returns the FIX message contained in the XML wrapped message.
     * @return FIX message object
     */
    FIXMsg getMessage();

    /**
     * Returns the list of batches contained in the FIXML message.
     * @return array of batch messages
     */
    Batch[] getBatches();
}
