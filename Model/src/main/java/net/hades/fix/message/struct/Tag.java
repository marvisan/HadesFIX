/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Tag.java
 *
 * $Id: Tag.java,v 1.2 2010-01-27 07:54:03 vrotaru Exp $
 */
package net.hades.fix.message.struct;

/**
 * Strcuture that holds the tag number and string value pair.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 17/08/2008, 18:13:24
 */
public class Tag {

    public int tagNum;
    public byte[] value;
    public String unknownTag;
    
    public Tag(int tagNum, byte[] value) {
        this.tagNum = tagNum;
        this.value = value;
    }
    
    public Tag(String unknownTag, byte[] value) {
        this.unknownTag = unknownTag;
        this.value = value;
    }
}
