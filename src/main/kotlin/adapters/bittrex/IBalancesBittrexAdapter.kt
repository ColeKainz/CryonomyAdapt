package adapters.bittrex

import io.reactivex.Observable
import models.balances.Balance

interface IBalancesBittrexAdapter: IBittrexAdapterBase {
    override fun getBalances(): Observable<List<Balance>> {
        return client.balance.getBalances().map { list ->
            list.map {
                Balance(it.currencySymbol, it.total, it.available, it.updatedAt)
            }
        }
    }

    override fun checkBalances() {
        client.balance.checkBalances()
    }

    override fun getBalances(symbol: String): Observable<List<Balance>> {
        return client.balance.getBalances(symbol).map { list ->
            list.map {
                Balance(it.currencySymbol, it.total, it.available, it.updatedAt)
            }
        }
    }
}