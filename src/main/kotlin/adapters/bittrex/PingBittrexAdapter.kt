package adapters.bittrex

import models.AdapterObservable
import models.ping.Ping

internal interface PingBittrexAdapter: BittrexAdapterBase {
    override fun getPing(): AdapterObservable<Ping> {
        return client.ping.getPing().mapToAdapter {
            Ping(
               it.serverTime
            )
        }
    }

    override fun subscribeHeatbeat(): AdapterObservable<Unit> {
        return socketClient.subscribeHeartbeat().map { }
    }
}