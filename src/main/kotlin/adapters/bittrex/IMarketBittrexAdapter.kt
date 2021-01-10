package adapters.bittrex

import models.AdapterObservable
import models.markets.*

internal interface IMarketBittrexAdapter : IBittrexAdapterBase {
    override fun getMarkets(): AdapterObservable<List<Market>> {
        return client.markets.getMarkets().mapToAdapter { list ->
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

    override fun getMarket(symbol: String): AdapterObservable<Market> {
        return client.markets.getMarket(symbol).mapToAdapter {
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

    override fun getMarketSummaries(): AdapterObservable<List<MarketSummary>> {
        return client.markets.getMarketSummaries().mapToAdapter { list ->
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

    override fun getMarketSummary(symbol: String): AdapterObservable<MarketSummary> {
        return client.markets.getMarketSummary(symbol).mapToAdapter {
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

    override fun getTickers(): AdapterObservable<List<Ticker>> {
        return client.markets.getTickers().mapToAdapter { list ->
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

    override fun getTicker(symbol: String): AdapterObservable<Ticker> {
        return socketClient.subscribeTicker(symbol).mapToAdapter {
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

    override fun getOrderBook(symbol: String, depth: OrderBookDepth): AdapterObservable<OrderBook> {
        return client.markets.getOrderBook(symbol, depth.convert()).mapToAdapter {
            OrderBook(
                it.bid.map { it.convert() },
                it.ask.map { it.convert() }
            )
        }

    }


    override fun checkOrderBook(symbol: String, depth: OrderBookDepth) {
        client.markets.checkOrderBook(symbol, depth.convert())
    }

    override fun getTrade(symbol: String): AdapterObservable<Trade> {
        return client.markets.getTrade(symbol).mapToAdapter {
            Trade(
                it.id,
                it.executedAt,
                it.quantity,
                it.rate,
                it.takerSide.convert()
            )
        }
    }

    override fun checkTrade(symbol: String): AdapterObservable<Trade> {
        return client.markets.checkTrade(symbol).mapToAdapter {
            Trade(
                it.id,
                it.executedAt,
                it.quantity,
                it.rate,
                it.takerSide.convert()
            )
        }
    }

    override fun getRecentCandles(symbol: String, candleInterval: CandleInterval): AdapterObservable<List<Candle>> {
        return client.markets.getRecentCandles(symbol, candleInterval.convert()).mapToAdapter { list ->
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
    ): AdapterObservable<List<Candle>> {
        return client.markets.getCandles(symbol, candleInterval.convert(), year, month, day).mapToAdapter { list ->
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