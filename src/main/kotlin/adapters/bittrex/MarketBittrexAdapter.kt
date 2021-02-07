package adapters.bittrex

import models.AdapterObservable
import models.coin.CoinPair
import models.markets.*

internal interface MarketBittrexAdapter : BittrexAdapterBase {
    override fun getMarkets(): AdapterObservable<List<Market>> {
        return client.markets.getMarkets().mapToAdapter { list ->
            list.map {
                Market(
                    it.symbol.asPair(),
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

    override fun getMarket(pair: CoinPair): AdapterObservable<Market> {
        return client.markets.getMarket(pair.asString()).mapToAdapter {
            Market(
                it.symbol.asPair(),
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

    override fun checkMarketSummaries(): AdapterObservable<Unit> {
        return client.markets.checkMarketSummaries().map { }
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

    override fun subscribeMarketSummaries(): AdapterObservable<List<MarketSummary>> {
        val handler = SyncHandler(
            { client.markets.checkMarketSummaries().sequence },
            { socketClient.subscribeMarketSummaries() }
        )

        return handler.handle().mapStreamToAdapter { summariesDelta ->
            val list = summariesDelta.deltas
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

    override fun getMarketSummary(pair: CoinPair): AdapterObservable<MarketSummary> {
        return client.markets.getMarketSummary(pair.asString()).mapToAdapter {
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

    override fun subscribeMarketSummary(pair: CoinPair): AdapterObservable<MarketSummary> {
        return socketClient.subscribeMarketSummary(pair.asString()).mapStreamToAdapter {
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

    override fun checkTickers(): AdapterObservable<Unit> {
        return client.markets.checkTickers().map { }
    }

    override fun getTickers(): AdapterObservable<List<Ticker>> {
        return client.markets.getTickers().mapToAdapter { list ->
            list.map {
                Ticker(
                    it.symbol.asPair(),
                    it.lastTradeRate,
                    it.bidRate,
                    it.askRate
                )
            }
        }
    }

    override fun subscribeTickers(): AdapterObservable<List<Ticker>> {
        val handler = SyncHandler(
            { client.markets.checkTickers().sequence },
            { socketClient.subscribeTickers() }
        )

        return handler.handle().mapStreamToAdapter { tickerDelta ->
            tickerDelta.deltas.map {
                Ticker(
                    it.symbol.asPair(),
                    it.lastTradeRate,
                    it.bidRate,
                    it.askRate
                )
            }
        }
    }

    override fun getTicker(pair: CoinPair): AdapterObservable<Ticker> {
        return client.markets.getTicker(pair.asString()).mapToAdapter {
            Ticker(
                it.symbol.asPair(),
                it.lastTradeRate,
                it.bidRate,
                it.askRate
            )
        }
    }

    override fun subscribeTicker(pair: CoinPair): AdapterObservable<Ticker> {
        return socketClient.subscribeTicker(pair.asString()).mapStreamToAdapter {
            Ticker(
                it.symbol.asPair(),
                it.lastTradeRate,
                it.bidRate,
                it.askRate
            )
        }
    }

    override fun getOrderBook(pair: CoinPair, depth: OrderBookDepth): AdapterObservable<OrderBook> {
        return client.markets.getOrderBook(pair.asString(), depth.convert()).mapToAdapter {
            OrderBook(
                it.bid.map { it.convert() },
                it.ask.map { it.convert() }
            )
        }
    }

    override fun subscribeOrderBook(pair: CoinPair, depth: OrderBookDepth): AdapterObservable<OrderBookDelta> {
        val handler = SyncHandler(
            { client.markets.checkOrderBook(pair.asString(), depth.convert()).sequence },
            { socketClient.subscribeOrderBook(pair.asString(), depth.value.toString()) }
        )

        return handler.handle().mapStreamToAdapter { orderbookDelta ->
            OrderBookDelta(
                orderbookDelta.bidDeltas.map { it.convert() },
                orderbookDelta.askDeltas.map { it.convert() }
            )
        }
    }

    override fun checkOrderBook(pair: CoinPair, depth: OrderBookDepth): AdapterObservable<Unit> {
        return client.markets.checkOrderBook(pair.asString(), depth.convert()).map { }
    }

    override fun getTrades(pair: CoinPair): AdapterObservable<List<Trade>> {
        return client.markets.getTrades(pair.asString()).mapToAdapter { list ->
            list.map {
                Trade(
                    it.id,
                    it.executedAt,
                    it.quantity,
                    it.rate,
                    it.takerSide.convert()
                )
            }
        }
    }

    override fun subscribeTrades(pair: CoinPair): AdapterObservable<List<Trade>> {
        val handler = SyncHandler(
            { client.markets.checkTrade(pair.asString()).sequence },
            { socketClient.subscribeTrade(pair.asString()) }
        )

        return handler.handle().mapStreamToAdapter { tradeDelta ->
            val list = tradeDelta.deltas
            list.map {
                Trade(
                    it.id,
                    it.executedAt,
                    it.quantity,
                    it.rate,
                    it.takerSide.convert()
                )
            }
        }
    }

    override fun checkTrade(pair: CoinPair): AdapterObservable<Unit> {
        return client.markets.checkTrade(pair.asString()).map { }
    }

    override fun getRecentCandles(pair: CoinPair, candleInterval: CandleInterval): AdapterObservable<List<Candle>> {
        return client.markets.getRecentCandles(pair.asString(), candleInterval.convert()).mapToAdapter { list ->
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

    override fun subscribeRecentCandle(pair: CoinPair, candleInterval: CandleInterval): AdapterObservable<Candle> {
        val handler = SyncHandler(
            { client.markets.checkRecentCandles(pair.asString(), candleInterval.convert()).sequence },
            { socketClient.subscribeCandle(pair.asString(), candleInterval.value) }
        )

        return handler.handle().mapStreamToAdapter { candleDelta ->
            val candle = candleDelta.delta
            Candle(
                candle.startsAt,
                candle.open,
                candle.high,
                candle.low,
                candle.close,
                candle.volume,
                candle.quoteVolume
            )
        }
    }

    override fun checkRecentCandles(pair: CoinPair, candleInterval: CandleInterval): AdapterObservable<Unit> {
        return client.markets.checkRecentCandles(pair.asString(), candleInterval.convert()).map { }
    }

    override fun getCandles(
        pair: CoinPair,
        candleInterval: CandleInterval,
        year: Int,
        month: Int,
        day: Int
    ): AdapterObservable<List<Candle>> {
        return client.markets.getCandles(pair.asString(), candleInterval.convert(), year, month, day).mapToAdapter { list ->
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