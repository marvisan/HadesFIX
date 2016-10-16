/*
 *   Copyright (c) 2006-2010 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.sample.message.type;

import java.util.HashMap;
import java.util.Map;

public enum CustTagNum {

    CustNewGroupTagLen          (15000),
    CustNewGroupTagType         (15001),
    CustNewGroupTagName         (15002),
    CustNewMsgIntTag            (15003),
    CustNewMsgStringTag         (15004),
    CustNewMsgDataLenTag        (15005),
    CustNewMsgDataTag           (15006),
    CustNewMsgDateTag           (15007),
    CustNewMsgBoolTag           (15008);

    private static final Map<Integer, CustTagNum> intToEnum = new HashMap<Integer, CustTagNum>();

    static {
        for (CustTagNum tag : values()) {
            intToEnum.put(tag.getValue(), tag);
        }
    }
    private static final long serialVersionUID = 1L;
    
    int value;

    CustTagNum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CustTagNum fromString(Integer value) {
        return intToEnum.get(value);
    }
}
