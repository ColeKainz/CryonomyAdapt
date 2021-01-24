package models.conditionalorders

import models.coin.CoinPair
import java.math.BigDecimal

data class NewConditionalOrder (
        val id: String,
        val pair: CoinPair,
        val operand: ConditionalOrderOperand,
        val triggerPrice: BigDecimal,
        val trailingStopPercent: BigDecimal,
        val createdOrderId: String,
        val orderToCreate: NewOrder,
        val orderToCancel: NewCancelConditionalOrder,
        val clientConditionalOrderId: String
)