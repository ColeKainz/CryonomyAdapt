package adapters.bittrex

import io.reactivex.Observable
import models.markets.*

interface IMarketBittrexAdapter : IBittrexAdapterBase {
    override fun getMarkets(): Observable<List<Market>> {
        return client.markets.getMarkets().map { list ->
            list.map {
                Market(
                    it.symbol,
                    it.baseCurrencySymbol,
                    it.quoteCurrencySymbol,
                    it.minTradeSize,
                    it.precision,
                    it.status,
                    it.createdAt,
                    it.notice,
                    it.prohibitedIn
                )
            }
        }
    }

    override fun getMarket(symbol: String): Observable<Market> {
        return client.markets.getMarket(symbol).map {
            Market(
                it.symbol,
                it.baseCurrencySymbol,
                it.quoteCurrencySymbol,
                it.minTradeSize,
                it.precision,
                it.status,
                it.createdAt,
                it.notice,
                it.prohibitedIn
            )
        }

    }

    override fun getMarketSummaries(): Observable<List<MarketSummary>> {
        return client.markets.getMarketSummaries().map { list ->
            list.map {
                MarketSummary(
                    it.high,
                    it.low,
                    it.volume,
                    it.quoteVolume,
                    it.percentChange,
                    it.updatedAt
                )
            }
        }
    }

    override fun getMarketSummary(symbol: String): Observable<MarketSummary> {
        return client.markets.getMarketSummary(symbol).map {
            MarketSummary(
                it.high,
                it.low,
                it.volume,
                it.quoteVolume,
                it.percentChange,
                it.updatedAt
            )
        }
    }

    override fun checkMarketSummaries() {
        return client.markets.checkMarketSummaries()
    }

    override fun getTickers(): Observable<List<Ticker>> {
        return client.markets.getTickers().map { list ->
            list.map {
                Ticker(
                    it.symbol,
                    it.lastTradeRate,
                    it.bidRate,
                    it.askRate
                )
            }
        }
    }

    override fun getTicker(symbol: String): Observable<Ticker> {
        return client.markets.getTicker(symbol).map {
            Ticker(
                it.symbol,
                it.lastTradeRate,
                it.bidRate,
                it.askRate
            )
        }
    }

    override fun checkTickers() {
        return client.markets.checkTickers()
    }

    override fun getOrderBook(symbol: String, depth: OrderBookDepth): Observable<OrderBook> {
        return client.markets.getOrderBook(symbol, depth.convert()).map {
            OrderBook(
                it.bid.map { it.convert() },
                it.ask.map { it.convert() }
            )
        }

    }

    override fun checkOrderBook(symbol: String, depth: OrderBookDepth) {
        client.markets.checkOrderBook(symbol, depth.convert())
    }

    override fun getTrade(symbol: String): Observable<Trade> {
        return client.markets.getTrade(symbol).map {
            Trade(
                it.id,
                it.executedAt,
                it.quantity,
                it.rate,
                it.takerSide.convert()
            )
        }
    }

    override fun checkTrade(symbol: String): Observable<Trade> {
        return client.markets.checkTrade(symbol).map {
            Trade(
                it.id,
                it.executedAt,
                it.quantity,
                it.rate,
                it.takerSide.convert()
            )
        }
    }

    override fun getRecentCandles(symbol: String, candleInterval: CandleInterval): Observable<List<Candle>> {
        return client.markets.getRecentCandles(symbol, candleInterval.convert()).map { list ->
            list.map {
                Candle(
                    it.startsAt,
                    it.open,
                    it.high,
                    it.low,
                    it.close,
                    it.volume,
                    it.quoteVolume
                )
            }
        }
    }

    override fun checkRecentCandles(symbol: String, candleInterval: CandleInterval) {
        client.markets.checkRecentCandles(symbol, candleInterval.convert())
    }

    override fun getCandles(
        symbol: String,
        candleInterval: CandleInterval,
        year: Int,
        month: Int,
        day: Int
    ): Observable<List<Candle>> {
        return client.markets.getCandles(symbol, candleInterval.convert(), year, month, day).map { list ->
            list.map {
                Candle(
                    it.startsAt,
                    it.open,
                    it.high,
                    it.low,
                    it.close,
                    it.volume,
                    it.quoteVolume
                )
            }
        }
    }
}