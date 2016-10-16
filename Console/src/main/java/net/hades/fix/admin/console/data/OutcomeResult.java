/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * OutcomeResult.java
 *
 * $Id: OutcomeResult.java,v 1.1 2011-03-17 07:36:17 vrotaru Exp $
 */
package net.hades.fix.admin.console.data;

/**
 * The result of an executed command that return success/failed and a supplementary
 * error message.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 08/05/2010
 */
public class OutcomeResult extends Result {

    public OutcomeResult() {
        super();
    }

    public OutcomeResult(boolean outcome, String errorMsg) {
        super(outcome, errorMsg);
    }
}
