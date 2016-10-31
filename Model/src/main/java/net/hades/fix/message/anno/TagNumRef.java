/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * TagNumRef.java
 *
 * $Id: TagNumRef.java,v 1.1 2009-07-06 04:20:07 vrotaru Exp $
 */
package net.hades.fix.message.anno;

import net.hades.fix.message.type.TagNum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for the tag number modified in the message by the give getter/setter method.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.1 $
 * @created 7/11/2008, 16:51:13
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface TagNumRef {
    
    /**
     * Tag number changed in the message by the accessor method annotated by this annotation.
     * @return TagNum enumeration value
     */
    TagNum tagNum();
    
    /**
     * Flag indicating that the tag is required in the message. If absent the tag
     * is not required.
     * @return true if tag is required, false otherwise
     */
    boolean required() default false;
    
    /**
     * Flag indicating that the tag is required in the message based on a logical
     * condition. If absent the tag is not required.
     * @return true if tag is required, false otherwise
     */
    boolean condRequired() default false;
}
