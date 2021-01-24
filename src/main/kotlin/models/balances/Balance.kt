package models.balances

import models.coin.Coin
import java.math.BigDecimal

data class Balance (
        val coin: Coin,
        val total: BigDecimal,
        val available: BigDecimal,
        val updatedAt: String
)