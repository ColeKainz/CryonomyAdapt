package adapters.bittrex

import io.reactivex.Observable
import models.AdapterObservable
import models.balances.Balance

internal interface IBalancesBittrexAdapter: IBittrexAdapterBase {
    override fun getBalances(): AdapterObservable<List<Balance>> {
        return client.balance.getBalances().mapToAdapter { list ->
            list.map {
                Balance(it.currencySymbol, it.total, it.available, it.updatedAt)
            }
        }
    }

    override fun checkBalances() {
        client.balance.checkBalances()
    }

    override fun getBalances(symbol: String): AdapterObservable<List<Balance>> {
        return client.balance.getBalances(symbol).mapToAdapter { list ->
            list.map {
                Balance(it.currencySymbol, it.total, it.available, it.updatedAt)
            }
        }
    }
}