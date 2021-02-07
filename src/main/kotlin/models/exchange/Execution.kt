package models.exchange

import models.coin.CoinPair
import java.math.BigDecimal

data class Execution (
        val id: String,
        val pair: CoinPair,
        val executedAt: String,
        val quantity: BigDecimal,
        val rate: BigDecimal,
        val orderId: String,
        val commission: BigDecimal,
        val isTaker: Boolean
)