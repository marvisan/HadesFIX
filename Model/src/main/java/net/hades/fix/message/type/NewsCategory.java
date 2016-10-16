/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * NewsCategory.java
 *
 * $Id: NewsCategory.java,v 1.3 2010-01-14 09:06:47 vrotaru Exp $
 */
package net.hades.fix.message.type;

import java.util.HashMap;
import java.util.Map;

/**
 * Category of news mesage standard values.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 18/06/2009, 5:40:07 PM
 */
public enum NewsCategory {

    CompanyNews                 (0),
    MarketplaceNews             (1),
    FinancialMarketNews         (2),
    TechnicalNews               (3),
    OtherNews                   (1);

    private static final long serialVersionUID = -1959819823358654176L;

    private int value;

    private static final Map<String, NewsCategory> stringToEnum = new HashMap<String, NewsCategory>();

    static {
        for (NewsCategory tag : values()) {
            stringToEnum.put(String.valueOf(tag.getValue()), tag);
        }
    }

    /** Creates a new instance of NewsCategory */
    NewsCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static NewsCategory valueFor(int value) {
        return stringToEnum.get(String.valueOf(value));
    }
}
