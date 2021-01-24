package models.orders

import models.coin.CoinPair
import models.conditionalorders.NewCancelConditionalOrder
import java.math.BigDecimal

data class Order (
        val id: String,
        val pair: CoinPair,
        val direction: String,
        val type: String,
        val quantity: BigDecimal,
        val limit: BigDecimal,
        val ceiling: BigDecimal,
        val timeInForce: String,
        val clientOrderId: String,
        val fillQuantity: BigDecimal,
        val commission: BigDecimal,
        val proceeds: BigDecimal,
        val status: String,
        val createdAt: String,
        val updatedAt: String,
        val closedAt: String,
        val orderToCancel: NewCancelConditionalOrder
)