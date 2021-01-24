package adapters.bittrex

import models.AdapterObservable
import models.balances.Balance
import models.coin.Coin

internal interface IBalancesBittrexAdapter: IBittrexAdapterBase {
    override fun getBalances(): AdapterObservable<List<Balance>> {
        return client.balance.getBalances().mapToAdapter { list ->
            list.map {
                Balance(it.currencySymbol.asCoin(), it.total, it.available, it.updatedAt)
            }
        }
    }

    override fun checkBalances() {
        client.balance.checkBalances()
    }

    override fun getBalances(coin: Coin): AdapterObservable<List<Balance>> {
        return client.balance.getBalances(coin.symbol).mapToAdapter { list ->
            list.map {
                Balance(it.currencySymbol.asCoin(), it.total, it.available, it.updatedAt)
            }
        }
    }
}