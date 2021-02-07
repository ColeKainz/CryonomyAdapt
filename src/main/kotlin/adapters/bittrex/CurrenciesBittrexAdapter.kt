package adapters.bittrex

import models.AdapterObservable
import models.coin.Coin
import models.currencies.Currency

internal interface CurrenciesBittrexAdapter : BittrexAdapterBase {
    override fun getCurrencies(): AdapterObservable<List<Currency>> {
        return client.currencies.getCurrencies().mapToAdapter { list ->
            list.map {
                Currency(
                    it.symbol.asCoin(),
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

    override fun getCurrency(coin: Coin): AdapterObservable<Currency> {
        return client.currencies.getCurrency(coin.symbol).mapToAdapter {
            Currency(
                it.symbol.asCoin(),
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