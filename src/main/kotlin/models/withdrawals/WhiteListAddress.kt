package models.withdrawals

import models.coin.Coin

data class WhiteListAddress (
        val coin: Coin,
        val createdAt: String,
        val status: WhiteListAddressStatus,
        val activeAt: String,
        val cryptoAddress: String,
        val cryptoAddressTag: String
)