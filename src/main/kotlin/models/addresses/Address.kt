package models.addresses

import models.coin.Coin

data class Address (
        val status: AddressStatus,
        val coin: Coin,
        val cryptoAddress: String,
        val cryptoAddressTag: String
)