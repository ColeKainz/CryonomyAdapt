package adapters.bittrex

import models.currencies.Currency
import com.bushka.bittrex.model.currencies.CurrencyStatus
import io.reactivex.Observable
import java.math.BigDecimal

interface ICurrenciesBittrexAdapter : IBittrexAdapterBase {
    override fun getCurrencies(): Observable<List<Currency>> {
        return client.currencies.getCurrencies().map { list ->
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

    override fun getCurrency(symbol: String): Observable<Currency> {
        return client.currencies.getCurrency(symbol).map {
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