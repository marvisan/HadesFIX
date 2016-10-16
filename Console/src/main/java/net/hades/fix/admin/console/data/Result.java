/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Result.java
 *
 * $Id: Result.java,v 1.2 2011-03-21 04:53:04 vrotaru Exp $
 */
package net.hades.fix.admin.console.data;

/**
 * Super class used for a command result data container.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 17/03/2011
 */
public abstract class Result {

    protected Boolean outcome;
    protected String errMsg;

    protected Result() {
        outcome = Boolean.TRUE;
    }

    protected Result(Boolean outcome) {
        this.outcome = outcome;
    }

    protected Result(Boolean outcome, String errMsg) {
        this.outcome = outcome;
        this.errMsg = errMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Boolean getOutcome() {
        return outcome;
    }

    public void setOutcome(Boolean outcome) {
        this.outcome = outcome;
    }

}
