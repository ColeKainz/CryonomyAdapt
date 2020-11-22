package adapters.bittrex

import com.bushka.bittrex.BittrexClient

class BittrexAdapter(key: String,
                     password: String):
    IAccountBittrexAdapter,
    IAddressesBittrexAdapter,
    IBalancesBittrexAdapter,
    IConditionalOrdersBittrexAdapter,
    ICurrenciesBittrexAdapter,
    IDepositsBittrexAdapter,
    IMarketBittrexAdapter,
    IOrdersBittrexAdapter,
    IPingBittrexAdapter,
    IWithdrawalBittrexAdapter {

    private val _client: BittrexClient by lazy { BittrexClient(key, password) }
    override val client: BittrexClient
        get() = _client
}