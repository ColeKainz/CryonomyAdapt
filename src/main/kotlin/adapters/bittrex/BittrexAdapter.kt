package adapters.bittrex

import com.bushka.bittrex.BittrexClient
import com.bushka.bittrex.services.SocketSubscriptionService

class BittrexAdapter(key: String,
                     password: String):
    AccountBittrexAdapter,
    AddressesBittrexAdapter,
    BalancesBittrexAdapter,
    ConditionalOrdersBittrexAdapter,
    CurrenciesBittrexAdapter,
    DepositsBittrexAdapter,
    MarketBittrexAdapter,
    OrdersBittrexAdapter,
    PingBittrexAdapter,
    WithdrawalBittrexAdapter,
    ExecutionsBittrexAdapter {

    companion object { const val TAG = "BITTREX" }
    override val client: BittrexClient by lazy { BittrexClient(key, password) }
    override val socketClient: SocketSubscriptionService by lazy { SocketSubscriptionService(key, password) }
}