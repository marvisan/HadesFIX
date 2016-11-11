/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ListSessionParam.java
 *
 * $Id: ListSessionParam.java,v 1.2 2011-03-21 04:53:04 vrotaru Exp $
 */
package net.hades.fix.admin.command.param;

import java.util.HashMap;
import java.util.Map;

/**
 * Parameters for the list session result.
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.2 $
 * @created 17/03/2011
 */
public enum ListSessionParam {

    Id                          ("id"),
    Counterparty                ("counterparty"),
    Name                        ("name"),
    Status                      ("status"),
    Protocol                    ("protocol"),
    Type                        ("type"),
    RxSeqNo                     ("rxSeqNo"),
    TxSeqNo                     ("txSeqNo");

    private static final long serialVersionUID = 1L;

    private String value;

    private static final Map<String, ListSessionParam> stringToEnum = new HashMap<String, ListSessionParam>();

    static {
        for (ListSessionParam tag : values()) {
            stringToEnum.put(tag.getValue(), tag);
        }
    }

    /** Creates a new instance of ListSessionParam */
    ListSessionParam(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ListSessionParam valueFor(String value) {
        return stringToEnum.get(value);
    }
}
