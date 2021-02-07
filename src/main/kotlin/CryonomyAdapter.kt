import models.AdapterObservable
import models.account.Account
import models.account.AccountVolume
import models.addresses.Address
import models.addresses.NewAddress
import models.balances.Balance
import models.coin.Coin
import models.coin.CoinPair
import models.conditionalorders.ConditionalOrder
import models.conditionalorders.NewConditionalOrder
import models.conditionalorders.NewOrder
import models.currencies.Currency
import models.deposits.Deposit
import models.deposits.DepositStatus
import models.exchange.Execution
import models.markets.*
import models.orders.Order
import models.ping.Ping
import models.withdrawals.NewWithdrawal
import models.withdrawals.WhiteListAddress
import models.withdrawals.Withdrawal
import models.withdrawals.WithdrawalStatus


interface CryonomyAdapter {
    fun getAccount(): AdapterObservable<Account>
    fun getAccountVolume(): AdapterObservable<AccountVolume>

    fun getAddresses(): AdapterObservable<List<Address>>
    fun getAddresses(coin: Coin): AdapterObservable<Address>
    fun putAddresses(address: NewAddress): AdapterObservable<List<Address>>

    fun checkBalances()
    fun getBalance(): AdapterObservable<List<Balance>>
    fun getBalance(coin: Coin): AdapterObservable<Balance>
    fun subscribeBalance(coin: Coin): AdapterObservable<Balance>


    fun postConditionalOrder(newConditionalOrder: NewConditionalOrder): AdapterObservable<ConditionalOrder>
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

    fun checkConditionalOrder(): AdapterObservable<Unit>
    fun openConditionalOrder(pair: CoinPair? = null): AdapterObservable<ConditionalOrder>
    fun subscribeOpenConditionalOrder(pair: CoinPair?): AdapterObservable<ConditionalOrder>

    fun getCurrency(coin: Coin): AdapterObservable<Currency>
    fun getCurrencies(): AdapterObservable<List<Currency>>

    fun checkOpenDeposits(): AdapterObservable<Unit>
    fun getOpenDeposits(txId: String): AdapterObservable<List<Deposit>>
    fun getOpenDeposits(
        coin: Coin? = null,
        status: DepositStatus? = null
    ): AdapterObservable<List<Deposit>>
    fun subscribeOpenDeposits(
        coin: Coin?,
        status: DepositStatus?
    ): AdapterObservable<Deposit>
    fun getDeposit(depositId: String): AdapterObservable<Deposit>
    fun getClosedDeposits(
        status: DepositStatus? = null,
        coin: Coin? = null,
        nextPageToken: String? = null,
        previousPageToken: String? = null,
        pageSize: String? = null,
        startDate: String? = null,
        endDate: String? = null
    ): AdapterObservable<List<Deposit>>

    fun getMarket(pair: CoinPair): AdapterObservable<Market>
    fun getMarkets(): AdapterObservable<List<Market>>
    fun getMarketSummaries(): AdapterObservable<List<MarketSummary>>
    fun subscribeMarketSummaries(): AdapterObservable<List<MarketSummary>>
    fun getMarketSummary(pair: CoinPair): AdapterObservable<MarketSummary>
    fun subscribeMarketSummary(pair: CoinPair): AdapterObservable<MarketSummary>
    fun checkMarketSummaries(): AdapterObservable<Unit>

    fun checkTickers(): AdapterObservable<Unit>
    fun getTickers(): AdapterObservable<List<Ticker>>
    fun subscribeTickers(): AdapterObservable<List<Ticker>>
    fun getTicker(pair: CoinPair): AdapterObservable<Ticker>
    fun subscribeTicker(pair: CoinPair): AdapterObservable<Ticker>

    fun checkOrderBook(pair: CoinPair, depth: OrderBookDepth = OrderBookDepth.MID): AdapterObservable<Unit>
    fun getOrderBook(pair: CoinPair, depth: OrderBookDepth = OrderBookDepth.MID): AdapterObservable<OrderBook>
    fun subscribeOrderBook(pair: CoinPair, depth: OrderBookDepth = OrderBookDepth.MID): AdapterObservable<OrderBookDelta>

    fun checkTrade(pair: CoinPair): AdapterObservable<Unit>
    fun getTrades(pair: CoinPair): AdapterObservable<List<Trade>>
    fun subscribeTrades(pair: CoinPair): AdapterObservable<List<Trade>>

    fun getRecentCandles(pair: CoinPair, candleInterval: CandleInterval): AdapterObservable<List<Candle>>
    fun subscribeRecentCandle(pair: CoinPair, candleInterval: CandleInterval): AdapterObservable<Candle>
    fun checkRecentCandles(pair: CoinPair, candleInterval: CandleInterval): AdapterObservable<Unit>
    fun getCandles(
        pair: CoinPair,
        candleInterval: CandleInterval,
        year: Int,
        month: Int,
        day: Int
    ): AdapterObservable<List<Candle>>

    fun postOrder(order: NewOrder): AdapterObservable<Order>
    fun getOpenOrders(pair: CoinPair? = null): AdapterObservable<List<Order>>
    fun subscribeOpenOrders(): AdapterObservable<Order>
    fun checkOpenOrders(): AdapterObservable<Unit>
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

    fun getPing(): AdapterObservable<Ping>
    fun subscribeHeatbeat(): AdapterObservable<Unit>

    fun postWithdrawal(withdrawal: NewWithdrawal): AdapterObservable<Withdrawal>
    fun getOpenWithdrawals(
        status: WithdrawalStatus? = null,
        coin: Coin? = null
    ): AdapterObservable<List<Withdrawal>>
    fun getWithdrawal(withdrawalId: String): AdapterObservable<Withdrawal>
    fun getWithdrawals(txId: String): AdapterObservable<List<Withdrawal>>
    fun deleteWithdrawal(withdrawalId: String): AdapterObservable<Withdrawal>
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

    fun checkLastExecution(): AdapterObservable<Unit>
    fun getExecution(): AdapterObservable<Execution>
    fun getLastExecution(pair: CoinPair): AdapterObservable<String>
}