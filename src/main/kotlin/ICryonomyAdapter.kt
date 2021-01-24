import models.AdapterObservable
import models.account.*
import models.addresses.*
import models.balances.*
import models.coin.Coin
import models.coin.CoinPair
import models.conditionalorders.*
import models.currencies.*
import models.deposits.*
import models.markets.*
import models.orders.*
import models.ping.*
import models.withdrawals.*


interface ICryonomyAdapter {
    fun getAccount(): AdapterObservable<Account>
    fun getAccountVolume(): AdapterObservable<AccountVolume>

    fun getAddresses(): AdapterObservable<List<Address>>
    fun putAddresses(address: NewAddress): AdapterObservable<List<Address>>
    fun getAddresses(coin: Coin): AdapterObservable<Address>

    fun getBalances(): AdapterObservable<List<Balance>>
    fun checkBalances()
    fun getBalances(coin: Coin): AdapterObservable<List<Balance>>

    fun getConditionalOrder(id: String): AdapterObservable<ConditionalOrder>
    fun deleteConditionalOrder(id: String): AdapterObservable<ConditionalOrder>
    fun closeConditionalOrder(
        pair: CoinPair? = null,
        nextPageToken: String? = null,
        previousPageToken: String? = null,
        pageSize: String? = null,
        startDate: String? = null,
        endDate: String
    ): AdapterObservable<ConditionalOrder>
    fun openConditionalOrder(pair: CoinPair? = null): AdapterObservable<ConditionalOrder>
    fun postConditionalOrder(newConditionalOrder: NewConditionalOrder): AdapterObservable<ConditionalOrder>

    fun getCurrencies(): AdapterObservable<List<Currency>>
    fun getCurrency(coin: Coin): AdapterObservable<Currency>

    fun getOpenDeposits(
        coin: Coin? = null,
        status: DepositStatus? = null
    ): AdapterObservable<List<Deposit>>
    fun checkOpenDeposits()
    fun getClosedDeposits(
        status: DepositStatus? = null,
        coin: Coin? = null,
        nextPageToken: String? = null,
        previousPageToken: String? = null,
        pageSize: String? = null,
        startDate: String? = null,
        endDate: String? = null
    ): AdapterObservable<List<Deposit>>
    fun getOpenDeposits(txId: String): AdapterObservable<List<Deposit>>
    fun getDeposit(depositId: String): AdapterObservable<Deposit>

    fun getMarkets(): AdapterObservable<List<Market>>
    fun getMarket(pair: CoinPair): AdapterObservable<Market>
    fun getMarketSummaries(): AdapterObservable<List<MarketSummary>>
    fun getMarketSummary(pair: CoinPair): AdapterObservable<MarketSummary>
    fun checkMarketSummaries()

    fun getTickers(): AdapterObservable<List<Ticker>>
    fun getTicker(pair: CoinPair): AdapterObservable<Ticker>
    fun checkTickers()

    fun getOrderBook(pair: CoinPair, depth: OrderBookDepth = OrderBookDepth.MID): AdapterObservable<OrderBook>
    fun checkOrderBook(pair: CoinPair, depth: OrderBookDepth = OrderBookDepth.MID)

    fun getTrade(pair: CoinPair): AdapterObservable<Trade>
    fun checkTrade(pair: CoinPair): AdapterObservable<Trade>
    fun getRecentCandles(pair: CoinPair, candleInterval: CandleInterval): AdapterObservable<List<Candle>>
    fun checkRecentCandles(pair: CoinPair, candleInterval: CandleInterval)
    fun getCandles(
        pair: CoinPair,
        candleInterval: CandleInterval,
        year: Int,
        month: Int,
        day: Int
    ): AdapterObservable<List<Candle>>
    fun getOpenOrders(pair: CoinPair? = null): AdapterObservable<List<Order>>
    fun checkOpenOrders()
    fun getOrder(orderId: String): AdapterObservable<Order>
    fun deleteOrder(orderId: String): AdapterObservable<Order>
    fun getClosedOrders(
        status: DepositStatus? = null,
        pair: CoinPair? = null,
        nextPageToken: String? = null,
        previousPageToken: String? = null,
        pageSize: String? = null,
        startDate: String? = null,
        endDate: String? = null
    ): AdapterObservable<List<Order>>
    fun getExecutions(orderId: String): AdapterObservable<List<Execution>>
    fun postOrder(order: NewOrder): AdapterObservable<Order>

    fun getPing(): AdapterObservable<Ping>

    fun getOpenWithdrawals(
        status: WithdrawalStatus? = null,
        coin: Coin? = null
    ): AdapterObservable<List<Withdrawal>>
    fun getWithdrawal(withdrawalId: String): AdapterObservable<Withdrawal>
    fun getWithdrawals(txId: String): AdapterObservable<List<Withdrawal>>
    fun deleteWithdrawal(withdrawalId: String): AdapterObservable<Withdrawal>
    fun postWithdrawal(withdrawal: NewWithdrawal): AdapterObservable<Withdrawal>
    fun getClosedWithdrawals(
        status: WithdrawalStatus? = null,
        coin: Coin? = null,
        nextPageToken: String? = null,
        previousPageToken: String? = null,
        pageSize: String? = null,
        startDate: String? = null,
        endDate: String? = null
    ): AdapterObservable<List<Withdrawal>>
    fun getWhiteListedAddresses(): AdapterObservable<WhiteListAddress>
}