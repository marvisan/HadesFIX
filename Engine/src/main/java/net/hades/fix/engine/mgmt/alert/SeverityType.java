/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * SeverityType.java
 *
 * $Id: SeverityType.java,v 1.1 2009-12-27 04:36:50 vrotaru Exp $
 */
package net.hades.fix.engine.mgmt.alert;

/**
 * Base interface to be implemented by the SeverityType enums.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 27/12/2009, 10:11:01 AM
 */
public interface SeverityType {
    /**
     * Gets the <code>String</code> value of the enumeration. Make your enum class
     * implements this method if you want to extend the base severity types.
     * @return enumeration value
     */
    String getValue();
}
