/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * UIDGen.java
 *
 * $Id: UIDGen.java,v 1.2 2010-06-27 02:46:08 vrotaru Exp $
 */
package net.hades.fix.engine.util;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generator of unique IDs.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 */
public class UIDGen {

    private static final UIDGen INSTANCE;

    static {
        INSTANCE = new UIDGen();
    }

    private static String root;
    private AtomicInteger suffix = new AtomicInteger(0);

    private UIDGen() {
        root = new DecimalFormat("0000").format(new Random(System.currentTimeMillis()).nextInt(9999));
        suffix = new AtomicInteger(0);
    }

    public static UIDGen getInstance() {
        return INSTANCE;
    }

    public String getNextUID(String prefix) {
        StringBuilder uid = new StringBuilder(prefix);
        uid.append(root);
        uid.append(new DecimalFormat("0000").format(suffix.incrementAndGet()));

        return uid.toString();
    }
}
