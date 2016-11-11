/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BaseSeverityType.java
 *
 * $Id: BaseSeverityType.java,v 1.1 2009-12-27 04:36:50 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt.alert;

/**
 * BaseSeverityType of the alert.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 10/07/2009, 6:12:28 PM
 */
public enum BaseSeverityType implements SeverityType {

    TEST            ("TEST"),
    INFO            ("INFO"),
    WARNING         ("WARNING"),
    RECOVERABLE     ("RECOVERABLE"),
    FATAL           ("FATAL");
    
    private static final long serialVersionUID = 5749993987802364890L;

    private String value;

    BaseSeverityType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
