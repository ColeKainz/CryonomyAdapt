package adapters.bittrex

import io.reactivex.Observable
import models.conditionalorders.NewCancelConditionalOrder
import models.conditionalorders.NewOrder
import models.deposits.DepositStatus
import models.markets.Candle
import models.orders.Execution
import models.orders.Order
import java.math.BigDecimal

interface IOrdersBittrexAdapter : IBittrexAdapterBase {
    override fun getOpenOrders(symbol: String?): Observable<List<Order>> {
        return client.orders.getOpenOrders(symbol).map { list ->
            list.map {
                Order(
                    it.id,
                    it.marketSymbol,
                    it.direction,
                    it.type,
                    it.quantity,
                    it.limit,
                    it.ceiling,
                    it.timeInForce,
                    it.clientOrderId,
                    it.fillQuantity,
                    it.commission,
                    it.proceeds,
                    it.status,
                    it.createdAt,
                    it.updatedAt,
                    it.closedAt,
                    it.orderToCancel.convert()
                )
            }
        }
    }

    override fun checkOpenOrders() {
        return client.orders.checkOpenOrders()
    }

    override fun getOrder(orderId: String): Observable<Order> {
        return client.orders.getOrder(orderId).map {
            Order(
                it.id,
                it.marketSymbol,
                it.direction,
                it.type,
                it.quantity,
                it.limit,
                it.ceiling,
                it.timeInForce,
                it.clientOrderId,
                it.fillQuantity,
                it.commission,
                it.proceeds,
                it.status,
                it.createdAt,
                it.updatedAt,
                it.closedAt,
                it.orderToCancel.convert()
            )
        }
    }

    override fun deleteOrder(orderId: String): Observable<Order> {
        return client.orders.deleteOrder(orderId).map {
            Order(
                it.id,
                it.marketSymbol,
                it.direction,
                it.type,
                it.quantity,
                it.limit,
                it.ceiling,
                it.timeInForce,
                it.clientOrderId,
                it.fillQuantity,
                it.commission,
                it.proceeds,
                it.status,
                it.createdAt,
                it.updatedAt,
                it.closedAt,
                it.orderToCancel.convert()
            )
        }
    }

    override fun getClosedOrders(
        status: DepositStatus?,
        symbol: String?,
        nextPageToken: String?,
        previousPageToken: String?,
        pageSize: String?,
        startDate: String?,
        endDate: String?
    ): Observable<List<Order>> {
        return client.orders.getClosedOrders(
            status?.convert(),
            symbol,
            nextPageToken,
            previousPageToken,
            pageSize,
            startDate,
            endDate
        ).map { list ->
            list.map {
                Order(
                    it.id,
                    it.marketSymbol,
                    it.direction,
                    it.type,
                    it.quantity,
                    it.limit,
                    it.ceiling,
                    it.timeInForce,
                    it.clientOrderId,
                    it.fillQuantity,
                    it.commission,
                    it.proceeds,
                    it.status,
                    it.createdAt,
                    it.updatedAt,
                    it.closedAt,
                    it.orderToCancel.convert()
                )
            }
        }
    }

    override fun getExecutions(orderId: String): Observable<List<Execution>> {
        return client.orders.getExecutions(orderId).map { list ->
            list.map {
                Execution(
                    it.id,
                    it.marketSymbol,
                    it.executedAt,
                    it.quantity,
                    it.rate,
                    it.orderId,
                    it.commission,
                    it.isTaker
                )
            }
        }
    }

    override fun postOrder(order: NewOrder): Observable<Order> {
        return client.orders.postOrder(order.convert()).map {
            Order(
                it.id,
                it.marketSymbol,
                it.direction,
                it.type,
                it.quantity,
                it.limit,
                it.ceiling,
                it.timeInForce,
                it.clientOrderId,
                it.fillQuantity,
                it.commission,
                it.proceeds,
                it.status,
                it.createdAt,
                it.updatedAt,
                it.closedAt,
                it.orderToCancel.convert(),
            )
        }
    }
}