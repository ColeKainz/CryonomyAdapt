package adapters.bittrex

import models.AdapterObservable
import models.balances.Balance
import models.coin.Coin

internal interface BalancesBittrexAdapter: BittrexAdapterBase {
    override fun getBalance(): AdapterObservable<List<Balance>> {
        return client.balance.getBalances().mapToAdapter { list ->
            list.map {
                Balance(it.currencySymbol.asCoin(), it.total, it.available, it.updatedAt)
            }
        }
    }

    override fun checkBalances() {
        client.balance.checkBalances()
    }

    override fun getBalance(coin: Coin): AdapterObservable<Balance> {
        return client.balance.getBalance(coin.symbol).mapToAdapter {
            Balance(
                it.currencySymbol.asCoin(),
                it.total,
                it.available,
                it.updatedAt
            )
        }
    }

    override fun subscribeBalance(coin: Coin): AdapterObservable<Balance> {
        val handler = SyncHandler(
            { client.balance.checkBalances() },
            { socketClient.subscribeBalance() }
        )

        return handler.handle().mapStreamToAdapter { balanceDelta ->
            val balance = balanceDelta.delta
            Balance(
                balance.currencySymbol.asCoin(),
                balance.total,
                balance.available,
                balance.updatedAt
            )
        }
    }
}