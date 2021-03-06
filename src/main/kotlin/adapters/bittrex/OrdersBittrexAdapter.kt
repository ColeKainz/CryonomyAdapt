package adapters.bittrex

import models.AdapterObservable
import models.coin.CoinPair
import models.conditionalorders.NewOrder
import models.deposits.DepositStatus
import models.exchange.Execution
import models.orders.Order

internal interface OrdersBittrexAdapter : BittrexAdapterBase {
    override fun getOpenOrders(pair: CoinPair?): AdapterObservable<List<Order>> {
        return client.orders.getOpenOrders(pair?.asString()).mapToAdapter { list ->
            list.map {
                Order(
                    it.id,
                    it.marketSymbol.asPair(),
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

    override fun subscribeOpenOrders(): AdapterObservable<Order> {
        val handler = SyncHandler(
            { client.orders.checkOpenOrders() },
            { socketClient.subscribeOrder() }
        )

        return handler.handle().mapStreamToAdapter { orderDelta ->
            val order = orderDelta.delta
            Order(
                order.id,
                order.marketSymbol.asPair(),
                order.direction,
                order.type,
                order.quantity,
                order.limit,
                order.ceiling,
                order.timeInForce,
                order.clientOrderId,
                order.fillQuantity,
                order.commission,
                order.proceeds,
                order.status,
                order.createdAt,
                order.updatedAt,
                order.closedAt,
                order.orderToCancel.convert()
            )
        }
    }

    override fun checkOpenOrders(): AdapterObservable<Unit> {
        return client.orders.checkOpenOrders().map { }
    }

    override fun getOrder(orderId: String): AdapterObservable<Order> {
        return client.orders.getOrder(orderId).mapToAdapter {
            Order(
                it.id,
                it.marketSymbol.asPair(),
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

    override fun deleteOrder(orderId: String): AdapterObservable<Order> {
        return client.orders.deleteOrder(orderId).mapToAdapter {
            Order(
                it.id,
                it.marketSymbol.asPair(),
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
        pair: CoinPair?,
        nextPageToken: String?,
        previousPageToken: String?,
        pageSize: String?,
        startDate: String?,
        endDate: String?
    ): AdapterObservable<List<Order>> {
        return client.orders.getClosedOrders(
            status?.convert(),
            pair?.asString(),
            nextPageToken,
            previousPageToken,
            pageSize,
            startDate,
            endDate
        ).mapToAdapter { list ->
            list.map {
                Order(
                    it.id,
                    it.marketSymbol.asPair(),
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

    override fun getExecutions(orderId: String): AdapterObservable<List<Execution>> {
        return client.orders.getExecutions(orderId).mapToAdapter { list ->
            list.map {
                Execution(
                    it.id,
                    it.marketSymbol.asPair(),
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

    override fun postOrder(order: NewOrder): AdapterObservable<Order> {
        return client.orders.postOrder(order.convert()).mapToAdapter {
            Order(
                it.id,
                it.marketSymbol.asPair(),
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