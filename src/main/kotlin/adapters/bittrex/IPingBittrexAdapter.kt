package adapters.bittrex

import models.AdapterObservable
import models.ping.Ping

internal interface IPingBittrexAdapter: IBittrexAdapterBase {
    override fun getPing(): AdapterObservable<Ping> {
        return client.ping.getPing().mapToAdapter {
            Ping(
               it.serverTime
            )
        }
    }
}