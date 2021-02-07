package adapters.bittrex

import models.AdapterObservable
import models.coin.CoinPair
import models.exchange.Execution

internal interface ExecutionsBittrexAdapter : BittrexAdapterBase {
    override fun getExecution(): AdapterObservable<Execution> {
        return client.exchanges.getExecution().mapToAdapter {
            Execution(
                it.id,
                it.marketSymbol.asPair(),
                it.executedAt,
                it.quantity,
                it.rate,
                it.orderId,
                it.commission,
                it.isTaker
            )
        }
    }

    override fun checkLastExecution(): AdapterObservable<Unit> {
        return client.exchanges.checkLastExecution().map { }
    }

    override fun getLastExecution(pair: CoinPair): AdapterObservable<String> {
        return client.exchanges.getLastExecution(pair.asString()).mapToAdapter { it.lastId }
    }
}