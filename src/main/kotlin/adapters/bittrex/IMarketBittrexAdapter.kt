package adapters.bittrex

import models.AdapterObservable
import models.coin.CoinPair
import models.markets.*

internal interface IMarketBittrexAdapter : IBittrexAdapterBase {
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

    override fun checkMarketSummaries() {
        return client.markets.checkMarketSummaries()
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

    override fun getTicker(pair: CoinPair): AdapterObservable<Ticker> {
        return socketClient.subscribeTicker(pair.asString()).mapToAdapter {
            Ticker(
                it.symbol.asPair(),
                it.lastTradeRate,
                it.bidRate,
                it.askRate
            )
        }
    }

    override fun checkTickers() {
        return client.markets.checkTickers()
    }

    override fun getOrderBook(pair: CoinPair, depth: OrderBookDepth): AdapterObservable<OrderBook> {
        return client.markets.getOrderBook(pair.asString(), depth.convert()).mapToAdapter {
            OrderBook(
                it.bid.map { it.convert() },
                it.ask.map { it.convert() }
            )
        }

    }


    override fun checkOrderBook(pair: CoinPair, depth: OrderBookDepth) {
        client.markets.checkOrderBook(pair.asString(), depth.convert())
    }

    override fun getTrade(pair: CoinPair): AdapterObservable<Trade> {
        return client.markets.getTrade(pair.asString()).mapToAdapter {
            Trade(
                it.id,
                it.executedAt,
                it.quantity,
                it.rate,
                it.takerSide.convert()
            )
        }
    }

    override fun checkTrade(pair: CoinPair): AdapterObservable<Trade> {
        return client.markets.checkTrade(pair.asString()).mapToAdapter {
            Trade(
                it.id,
                it.executedAt,
                it.quantity,
                it.rate,
                it.takerSide.convert()
            )
        }
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

    override fun checkRecentCandles(pair: CoinPair, candleInterval: CandleInterval) {
        client.markets.checkRecentCandles(pair.asString(), candleInterval.convert())
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