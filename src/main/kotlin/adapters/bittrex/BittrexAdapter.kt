package adapters.bittrex

import com.bushka.bittrex.BittrexClient
import com.bushka.bittrex.services.SocketSubscriptionService

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

    companion object { val TAG = "BITTREX" }
    override val client: BittrexClient by lazy { BittrexClient(key, password) }
    override val socketClient: SocketSubscriptionService by lazy { SocketSubscriptionService(key, password) }
}