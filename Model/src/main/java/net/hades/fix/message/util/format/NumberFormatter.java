/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NumberFormatter.java
 *
 * $Id: NumberFormatter.java,v 1.3 2011-02-04 09:58:24 vrotaru Exp $
 */
package net.hades.fix.message.util.format;

import java.text.DecimalFormat;

/**
 * Number formatter.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 22/05/2008, 12:21:46
 */
public class NumberFormatter {

    public static DecimalFormat getDecimalPlacesFormat() {
        return new DecimalFormat("#0.00#");
    }
    
    public static DecimalFormat getIntegerFormat() {
        return new DecimalFormat("#0");
    }
    
    public static final DecimalFormat TWO_DECIMAL_POS               = new DecimalFormat("#0.00#");
    public static final DecimalFormat FIX_CHECKSUM                  = new DecimalFormat("000");
}
