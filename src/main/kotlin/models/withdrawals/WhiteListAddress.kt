package models.withdrawals

data class WhiteListAddress (
        val currencySymbol: String,
        val createdAt: String,
        val status: WhiteListAddressStatus,
        val activeAt: String,
        val cryptoAddress: String,
        val cryptoAddressTag: String
)