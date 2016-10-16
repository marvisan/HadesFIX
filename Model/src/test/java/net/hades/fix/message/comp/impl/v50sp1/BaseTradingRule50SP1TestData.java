/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * BaseTradingRule50SP1TestData.java
 *
 * $Id: BaseTradingRule50SP1TestData.java,v 1.3 2011-10-29 09:42:11 vrotaru Exp $
 */
package net.hades.fix.message.comp.impl.v50sp1;

import static org.junit.Assert.*;

import net.hades.fix.message.MsgTest;
import net.hades.fix.message.comp.BaseTradingRules;
import net.hades.fix.message.type.Currency;
import net.hades.fix.message.type.ExpirationCycle;
import net.hades.fix.message.type.ImpliedMarketIndicator;
import net.hades.fix.message.type.LotType;
import net.hades.fix.message.type.MultilegModel;
import net.hades.fix.message.type.MultilegPriceMethod;
import net.hades.fix.message.type.PriceLimitType;
import net.hades.fix.message.type.PriceType;
import net.hades.fix.message.type.TickRuleType;

/**
 * Test utility for BaseTradingRules component class.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 * @version $Revision: 1.3 $
 * @created 14/04/2009, 9:53:49 AM
 */
public class BaseTradingRule50SP1TestData extends MsgTest {

    private static final BaseTradingRule50SP1TestData INSTANCE;

    static {
        INSTANCE = new BaseTradingRule50SP1TestData();
    }

    public static BaseTradingRule50SP1TestData getInstance() {
        return INSTANCE;
    }

    public void populate(BaseTradingRules comp) {
        comp.setNoTickRules(2);
        comp.getTickRuleGroups()[0].setStartTickPriceRange(23.5);
        comp.getTickRuleGroups()[0].setEndTickPriceRange(28.5);
        comp.getTickRuleGroups()[0].setTickIncrement(0.5);
        comp.getTickRuleGroups()[0].setTickRuleType(TickRuleType.Regular);
        comp.getTickRuleGroups()[1].setStartTickPriceRange(33.5);
        comp.getTickRuleGroups()[1].setEndTickPriceRange(38.5);
        comp.getTickRuleGroups()[1].setTickIncrement(0.8);
        comp.getTickRuleGroups()[1].setTickRuleType(TickRuleType.Fixed);

        comp.setNoLotTypeRules(2);
        comp.getLotTypeRuleGroups()[0].setLotType(LotType.BlockLot);
        comp.getLotTypeRuleGroups()[0].setMinLotSize(35.0);
        comp.getLotTypeRuleGroups()[1].setLotType(LotType.OddLot);
        comp.getLotTypeRuleGroups()[1].setMinLotSize(39.0);
        
        comp.setPriceLimits();
        comp.getPriceLimits().setPriceLimitType(PriceLimitType.Price);
        comp.getPriceLimits().setLowLimitPrice(56.7);
        comp.getPriceLimits().setHighLimitPrice(76.8);
        comp.getPriceLimits().setTradingReferencePrice(67.7);

        comp.setExpirationCycle(ExpirationCycle.ExpireOnTradingSessionClose);
        comp.setMinTradeVol(56000.0);
        comp.setMaxTradeVol(230000.0);
        comp.setMaxPriceVariation(25.5);
        comp.setImpliedMarketIndicator(ImpliedMarketIndicator.ImpliedIn);
        comp.setTradingCurrency(Currency.CanadianDollar);
        comp.setRoundLot(50.0);
        comp.setMultilegModel(MultilegModel.PredefinedMultilegSecurity);
        comp.setMultilegPriceMethod(MultilegPriceMethod.MultipliedPrice);
        comp.setPriceType(PriceType.Percentage);
    }

    public void check(BaseTradingRules expected, BaseTradingRules actual) {
        assertEquals(expected.getNoTickRules(), actual.getNoTickRules());
        for (int i = 0; i < 2; i++) {
            assertEquals(expected.getTickRuleGroups()[i].getStartTickPriceRange(), actual.getTickRuleGroups()[i].getStartTickPriceRange());
            assertEquals(expected.getTickRuleGroups()[i].getEndTickPriceRange(), actual.getTickRuleGroups()[i].getEndTickPriceRange());
            assertEquals(expected.getTickRuleGroups()[i].getTickIncrement(), actual.getTickRuleGroups()[i].getTickIncrement());
            assertEquals(expected.getTickRuleGroups()[i].getTickRuleType(), actual.getTickRuleGroups()[i].getTickRuleType());
        }

        assertEquals(expected.getNoLotTypeRules(), actual.getNoLotTypeRules());
        for (int i = 0; i < 2; i++) {
            assertEquals(expected.getLotTypeRuleGroups()[i].getLotType(), actual.getLotTypeRuleGroups()[i].getLotType());
            assertEquals(expected.getLotTypeRuleGroups()[i].getMinLotSize(), actual.getLotTypeRuleGroups()[i].getMinLotSize());
        }
        
        assertNotNull(actual.getPriceLimits());
        assertEquals(expected.getPriceLimits().getPriceLimitType(), actual.getPriceLimits().getPriceLimitType());
        assertEquals(expected.getPriceLimits().getLowLimitPrice(), actual.getPriceLimits().getLowLimitPrice());
        assertEquals(expected.getPriceLimits().getHighLimitPrice(), actual.getPriceLimits().getHighLimitPrice());
        assertEquals(expected.getPriceLimits().getTradingReferencePrice(), actual.getPriceLimits().getTradingReferencePrice());

        assertEquals(expected.getExpirationCycle(), actual.getExpirationCycle());
        assertEquals(expected.getMinTradeVol(), actual.getMinTradeVol());
        assertEquals(expected.getMaxTradeVol(), actual.getMaxTradeVol());
        assertEquals(expected.getMaxPriceVariation(), actual.getMaxPriceVariation());
        assertEquals(expected.getImpliedMarketIndicator(), actual.getImpliedMarketIndicator());
        assertEquals(expected.getTradingCurrency(), actual.getTradingCurrency());
        assertEquals(expected.getRoundLot(), actual.getRoundLot());
        assertEquals(expected.getMultilegModel(), actual.getMultilegModel());
        assertEquals(expected.getMultilegPriceMethod(), actual.getMultilegPriceMethod());
        assertEquals(expected.getPriceType(), actual.getPriceType());
    }
}
