package models.markets

import models.coin.CoinPair
import java.math.BigDecimal

data class Market (
        val pair: CoinPair,
        val baseCurrencySymbol: String,
        val quoteCurrencySymbol: String,
        val minTradeSize: BigDecimal,
        val precision: Int,
        val status: String,
        val createdAt: String,
        val notice: String,
        val prohibitedIn: List<String>
)