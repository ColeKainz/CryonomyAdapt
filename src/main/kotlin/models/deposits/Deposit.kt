package models.deposits

import models.coin.Coin
import java.math.BigDecimal

data class Deposit (
        val id: String,
        val coin: Coin,
        val quantity: BigDecimal,
        val cryptoAddress: String,
        val cryptoAddressTag: String,
        val txId: String,
        val confirmations: Int,
        val updatedAt: String,
        val completedAt: String,
        val status: DepositStatus,
        val source: DepositSource
)