import io.reactivex.Observable
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
    fun getAccount(): Observable<Account>
    fun getAccountVolume(): Observable<AccountVolume>

    fun getAddresses(): Observable<List<Address>>
    fun putAddresses(address: NewAddress): Observable<List<Address>>
    fun getAddresses(symbol: String): Observable<Address>

    fun getBalances(): Observable<List<Balance>>
    fun checkBalances()
    fun getBalances(symbol: String): Observable<List<Balance>>

    fun getConditionalOrder(id: String): Observable<ConditionalOrder>
    fun deleteConditionalOrder(id: String): Observable<ConditionalOrder>
    fun closeConditionalOrder(symbol: String? = null,
                              nextPageToken: String? = null,
                              previousPageToken: String? = null,
                              pageSize: String? = null,
                              startDate: String? = null,
                              endDate: String): Observable<ConditionalOrder>
    fun openConditionalOrder(symbol: String? = null): Observable<ConditionalOrder>
    fun postConditionalOrder(newConditionalOrder: NewConditionalOrder): Observable<ConditionalOrder>

    fun getCurrencies(): Observable<List<Currency>>
    fun getCurrency(symbol: String): Observable<Currency>

    fun getOpenDeposits(status: DepositStatus? = null,
                        symbol: String? = null): Observable<List<Deposit>>
    fun checkOpenDeposits()
    fun getClosedDeposits(status: DepositStatus? = null,
                          symbol: String? = null,
                          nextPageToken: String? = null,
                          previousPageToken: String? = null,
                          pageSize: String? = null,
                          startDate: String? = null,
                          endDate: String? = null): Observable<List<Deposit>>
    fun getOpenDeposits(txId: String): Observable<List<Deposit>>
    fun getDeposit(depositId: String): Observable<Deposit>

    fun getMarkets(): Observable<List<Market>>
    fun getMarket(symbol: String): Observable<Market>
    fun getMarketSummaries(): Observable<List<MarketSummary>>
    fun getMarketSummary(symbol: String): Observable<MarketSummary>
    fun checkMarketSummaries()

    fun getTickers(): Observable<List<Ticker>>
    fun getTicker(symbol: String): Observable<Ticker>
    fun checkTickers()

    fun getOrderBook(symbol: String, depth: OrderBookDepth = OrderBookDepth.MID): Observable<OrderBook>
    fun checkOrderBook(symbol: String, depth: OrderBookDepth = OrderBookDepth.MID)

    fun getTrade(symbol: String): Observable<Trade>
    fun checkTrade(symbol: String): Observable<Trade>
    fun getRecentCandles(symbol: String, candleInterval: CandleInterval): Observable<List<Candle>>
    fun checkRecentCandles(symbol: String, candleInterval: CandleInterval)
    fun getCandles(symbol: String,
                   candleInterval: CandleInterval,
                   year: Int,
                   month: Int,
                   day: Int): Observable<List<Candle>>
    fun getOpenOrders(symbol: String? = null): Observable<List<Order>>
    fun checkOpenOrders()
    fun getOrder(orderId: String): Observable<Order>
    fun deleteOrder(orderId: String): Observable<Order>
    fun getClosedOrders(status: DepositStatus? = null,
                        symbol: String? = null,
                        nextPageToken: String? = null,
                        previousPageToken: String? = null,
                        pageSize: String? = null,
                        startDate: String? = null,
                        endDate: String? = null): Observable<List<Order>>
    fun getExecutions(orderId: String): Observable<List<Execution>>
    fun postOrder(order: NewOrder): Observable<Order>

    fun getPing(): Observable<Ping>

    fun getOpenWithdrawals(status: WithdrawalStatus? = null,
                           symbol: String? = null): Observable<List<Withdrawal>>
    fun getWithdrawal(withdrawalId: String): Observable<Withdrawal>
    fun getWithdrawals(txId: String): Observable<List<Withdrawal>>
    fun deleteWithdrawal(withdrawalId: String): Observable<Withdrawal>
    fun postWithdrawal(withdrawal: NewWithdrawal): Observable<Withdrawal>
    fun getClosedWithdrawals(status: WithdrawalStatus? = null,
                             symbol: String? = null,
                             nextPageToken: String? = null,
                             previousPageToken: String? = null,
                             pageSize: String? = null,
                             startDate: String? = null,
                             endDate: String? = null): Observable<List<Withdrawal>>
    fun getWhiteListedAddresses(): Observable<WhiteListAddress>
}