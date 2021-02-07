package adapters.bittrex

import CryonomyAdapter
import com.bushka.bittrex.BittrexClient
import com.bushka.bittrex.services.SocketSubscriptionService

internal interface BittrexAdapterBase: CryonomyAdapter {
    val client: BittrexClient
    val socketClient: SocketSubscriptionService
}