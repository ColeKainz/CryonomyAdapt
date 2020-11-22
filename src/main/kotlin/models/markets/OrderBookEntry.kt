package models.markets

import java.math.BigDecimal

data class OrderBookEntry (
        val quantity: BigDecimal,
        val rate: BigDecimal
)