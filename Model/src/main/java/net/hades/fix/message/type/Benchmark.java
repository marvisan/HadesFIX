/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * Benchmark.java
 *
 * $Id: Benchmark.java,v 1.4 2010-02-25 08:37:26 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Identifies the benchmark.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.4 $
 * @created 10/02/2009, 7:44:46 PM
 */
public enum Benchmark {

    CURVE               ('1'),
    INT5YR              ('2'),
    OLD5                ('3'),
    INT10YR             ('4'),
    OLD10               ('5'),
    INT30YR             ('6'),
    OLD30               ('7'),
    INT3MOLIBOR         ('8'),
    INT6MOLIBOR         ('9');

    private static final long serialVersionUID = 1L;

    private char value;

    private static final Map<String, Benchmark> stringToEnum = new HashMap<String, Benchmark>();

    static {
        for (Benchmark tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of Benchmark */
    Benchmark(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static Benchmark valueFor(char value) {
        return stringToEnum.get(String.valueOf(value));
    }

}
