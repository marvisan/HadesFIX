/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * MsgSecureType.java
 *
 * $Id: MsgSecureType.java,v 1.3 2010-08-25 05:30:30 vrotaru Exp $
 */
package net.hades.fix.message;

/**
 * Type of secured field list.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 14/11/2008, 8:48:05 PM
 */
public enum MsgSecureType {
    SECURED_FIELDS, UNSECURED_FIELDS, ALL_FIELDS;

    private static final long serialVersionUID = 1L;
}
