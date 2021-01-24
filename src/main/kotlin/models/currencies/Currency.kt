package models.currencies

import models.coin.Coin
import java.math.BigDecimal

data class Currency (
        val coin: Coin,
        val name: String,
        val coinType: String,
        val status: CurrencyStatus,
        val minConfirmations: Int,
        val notice: String,
        val txFee: BigDecimal,
        val logoUrl: String,
        val prohibitedIn: List<String>
)