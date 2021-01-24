package models.conditionalorders

import models.coin.CoinPair
import java.math.BigDecimal

data class NewOrder (
        val pair: CoinPair,
        val direction: ConditionalOrderDirection,
        val type: ConditionalOrderNewOrderType,
        val quantity: BigDecimal,
        val ceiling: BigDecimal,
        val limit: BigDecimal,
        val timeInForce: ConditionalOrderTimeInForce,
        val clientOrderId: String,
        val useAwards: Boolean
)
