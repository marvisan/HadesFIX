/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXVersion.java
 *
 * $Id: FIXVersion.java,v 1.1 2009-07-06 04:20:07 vrotaru Exp $
 */
package net.hades.fix.message.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for FIX version data to be placed on FIX messages accessors in order
 * to give a hint to the message user in what version has been introduced and if it 
 * has been retired.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 7/11/2008, 16:51:13
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface FIXVersion {
    
    /**
     * Fix version implemented by the HadesFIX in which this message tag has been introduced.
     * Accessor methods on the message fields that are not part of the message version will
     * throw a <code>UnsupportedOperationException</code> exception.
     * @return string version (e.g. 4.0, 4.1, 5.0sp1)
     */
    String introduced();
    
    /**
     * Fix version implemented by the HadesFIX in which this message tag has been retired.
     * Accessor methods on the message fields that are not part of the message version will
     * throw a <code>UnsupportedOperationException</code> exception.
     * If not present then the tag is active in the latest FIX version implemented
     * @return string version (e.g. 4.0, 4.1, 5.0sp1)
     */
    String retired() default "";
}
