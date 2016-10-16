/*
 *   Copyright (c) 2006-2008 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * FIXDecoderOutput.java
 */
package net.hades.fix.quickfix;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.session.IoSession;

import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * QuickFIX decoder output implementation.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
public class FIXDecoderOutput implements ProtocolDecoderOutput {
    
    private List<String> messages;
    
    public FIXDecoderOutput() {
        messages = new ArrayList<String>();
    }

    @Override
    public void write(Object message) {
        if (message != null) {
            messages.add((String) message);
        }
    }

    public String getMessage(int i) {
        String result = null;
        if (i < messages.size()) {
            result = messages.get(i);
        }
        return result;
    }

    @Override
    public void flush(IoFilter.NextFilter nf, IoSession is) {
	System.out.println("flushing...");
    }
}
