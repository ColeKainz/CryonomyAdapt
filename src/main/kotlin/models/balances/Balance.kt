package models.balances

import java.math.BigDecimal

data class Balance (
        val currencySymbol: String,
        val total: BigDecimal,
        val available: BigDecimal,
        val updatedAt: String
)