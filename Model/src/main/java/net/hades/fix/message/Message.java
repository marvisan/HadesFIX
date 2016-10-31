/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.message;

import java.io.Serializable;

/**
 * Generic message type.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 */
public interface Message extends Serializable {
    static final int PRIORITY_NORMAL = 0;
    static final int PRIORITY_HIGH = 10;
    
    byte[] getRawMessage();
    int getPriority();
    void setPriority(int priority);
    long getOrderSequence();
    void setOrderSequence(long order);
}
