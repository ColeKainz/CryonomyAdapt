import io.reactivex.Observable
import models.AdapterObservable
import models.account.*
import models.addresses.*
import models.balances.*
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
    fun getAddresses(symbol: String): AdapterObservable<Address>

    fun getBalances(): AdapterObservable<List<Balance>>
    fun checkBalances()
    fun getBalances(symbol: String): AdapterObservable<List<Balance>>

    fun getConditionalOrder(id: String): AdapterObservable<ConditionalOrder>
    fun deleteConditionalOrder(id: String): AdapterObservable<ConditionalOrder>
    fun closeConditionalOrder(symbol: String? = null,
                              nextPageToken: String? = null,
                              previousPageToken: String? = null,
                              pageSize: String? = null,
                              startDate: String? = null,
                              endDate: String): AdapterObservable<ConditionalOrder>
    fun openConditionalOrder(symbol: String? = null): AdapterObservable<ConditionalOrder>
    fun postConditionalOrder(newConditionalOrder: NewConditionalOrder): AdapterObservable<ConditionalOrder>

    fun getCurrencies(): AdapterObservable<List<Currency>>
    fun getCurrency(symbol: String): AdapterObservable<Currency>

    fun getOpenDeposits(status: DepositStatus? = null,
                        symbol: String? = null): AdapterObservable<List<Deposit>>
    fun checkOpenDeposits()
    fun getClosedDeposits(status: DepositStatus? = null,
                          symbol: String? = null,
                          nextPageToken: String? = null,
                          previousPageToken: String? = null,
                          pageSize: String? = null,
                          startDate: String? = null,
                          endDate: String? = null): AdapterObservable<List<Deposit>>
    fun getOpenDeposits(txId: String): AdapterObservable<List<Deposit>>
    fun getDeposit(depositId: String): AdapterObservable<Deposit>

    fun getMarkets(): AdapterObservable<List<Market>>
    fun getMarket(symbol: String): AdapterObservable<Market>
    fun getMarketSummaries(): AdapterObservable<List<MarketSummary>>
    fun getMarketSummary(symbol: String): AdapterObservable<MarketSummary>
    fun checkMarketSummaries()

    fun getTickers(): AdapterObservable<List<Ticker>>
    fun getTicker(symbol: String): AdapterObservable<Ticker>
    fun checkTickers()

    fun getOrderBook(symbol: String, depth: OrderBookDepth = OrderBookDepth.MID): AdapterObservable<OrderBook>
    fun checkOrderBook(symbol: String, depth: OrderBookDepth = OrderBookDepth.MID)

    fun getTrade(symbol: String): AdapterObservable<Trade>
    fun checkTrade(symbol: String): AdapterObservable<Trade>
    fun getRecentCandles(symbol: String, candleInterval: CandleInterval): AdapterObservable<List<Candle>>
    fun checkRecentCandles(symbol: String, candleInterval: CandleInterval)
    fun getCandles(symbol: String,
                   candleInterval: CandleInterval,
                   year: Int,
                   month: Int,
                   day: Int): AdapterObservable<List<Candle>>
    fun getOpenOrders(symbol: String? = null): AdapterObservable<List<Order>>
    fun checkOpenOrders()
    fun getOrder(orderId: String): AdapterObservable<Order>
    fun deleteOrder(orderId: String): AdapterObservable<Order>
    fun getClosedOrders(status: DepositStatus? = null,
                        symbol: String? = null,
                        nextPageToken: String? = null,
                        previousPageToken: String? = null,
                        pageSize: String? = null,
                        startDate: String? = null,
                        endDate: String? = null): AdapterObservable<List<Order>>
    fun getExecutions(orderId: String): AdapterObservable<List<Execution>>
    fun postOrder(order: NewOrder): AdapterObservable<Order>

    fun getPing(): AdapterObservable<Ping>

    fun getOpenWithdrawals(status: WithdrawalStatus? = null,
                           symbol: String? = null): AdapterObservable<List<Withdrawal>>
    fun getWithdrawal(withdrawalId: String): AdapterObservable<Withdrawal>
    fun getWithdrawals(txId: String): AdapterObservable<List<Withdrawal>>
    fun deleteWithdrawal(withdrawalId: String): AdapterObservable<Withdrawal>
    fun postWithdrawal(withdrawal: NewWithdrawal): AdapterObservable<Withdrawal>
    fun getClosedWithdrawals(status: WithdrawalStatus? = null,
                             symbol: String? = null,
                             nextPageToken: String? = null,
                             previousPageToken: String? = null,
                             pageSize: String? = null,
                             startDate: String? = null,
                             endDate: String? = null): AdapterObservable<List<Withdrawal>>
    fun getWhiteListedAddresses(): AdapterObservable<WhiteListAddress>
}