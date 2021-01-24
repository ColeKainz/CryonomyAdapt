package models.markets

import models.coin.CoinPair
import java.math.BigDecimal

data class Ticker (
        val pair: CoinPair,
        val lastTradeRate: BigDecimal,
        val bidRate: BigDecimal,
        val askRate: BigDecimal
)