/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ProcessingState.java
 *
 * $Id: ProcessingState.java,v 1.2 2011-10-25 08:29:22 vrotaru Exp $
 */
package net.hades.fix.message.struct;

/**
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 21/08/2008, 20:37:53
 */
public class ProcessingState {

    public boolean HeaderProcessed;
    
    public boolean BodyProcessed;

    public ProcessingState() {
        HeaderProcessed = false;
        BodyProcessed = false;
    }
}
