package adapters.bittrex

import io.reactivex.Observable
import models.ping.Ping

interface IPingBittrexAdapter: IBittrexAdapterBase {
    override fun getPing(): Observable<Ping> {
        return client.ping.getPing().map {
            Ping(
               it.serverTime
            )
        }
    }
}