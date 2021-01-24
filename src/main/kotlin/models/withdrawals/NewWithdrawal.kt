package models.withdrawals

import models.coin.Coin
import java.math.BigDecimal

data class NewWithdrawal (
        val coin: Coin,
        val quantity: BigDecimal,
        val cryptoAddress: String,
        val cryptoAddressTag: String
)