package adapters.bittrex

import ICryonomyAdapter
import com.bushka.bittrex.BittrexClient
import com.bushka.bittrex.services.SocketSubscriptionService

internal interface IBittrexAdapterBase: ICryonomyAdapter {
    val client: BittrexClient
    val socketClient: SocketSubscriptionService
}