package adapters.bittrex

import models.AdapterObservable
import models.currencies.Currency

internal interface ICurrenciesBittrexAdapter : IBittrexAdapterBase {
    override fun getCurrencies(): AdapterObservable<List<Currency>> {
        return client.currencies.getCurrencies().mapToAdapter { list ->
            list.map {
                Currency(
                    it.symbol,
                    it.name,
                    it.coinType,
                    it.status.convert(),
                    it.minConfirmations,
                    it.notice,
                    it.txFee,
                    it.logoUrl,
                    it.prohibitedIn
                )
            }

        }
    }

    override fun getCurrency(symbol: String): AdapterObservable<Currency> {
        return client.currencies.getCurrency(symbol).mapToAdapter {
            Currency(
                it.symbol,
                it.name,
                it.coinType,
                it.status.convert(),
                it.minConfirmations,
                it.notice,
                it.txFee,
                it.logoUrl,
                it.prohibitedIn
            )

        }
    }
}