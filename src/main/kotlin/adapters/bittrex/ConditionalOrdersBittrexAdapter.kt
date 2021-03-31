package adapters.bittrex


import models.AdapterObservable
import models.coin.CoinPair
import models.conditionalorders.*

import com.bushka.bittrex.model.conditionalorders.NewConditionalOrder as BNewConditionalOrder


internal interface ConditionalOrdersBittrexAdapter : BittrexAdapterBase {
    override fun getConditionalOrder(id: String): AdapterObservable<ConditionalOrder> {
        return client.conditionalOrders.getConditionalOrder(id).mapToAdapter {
            ConditionalOrder(
                id = it.id,
                pair = it.marketSymbol.asPair(),
                operand = it.operand.convert(),
                triggerPrice = it.triggerPrice,
                trailingStopPercent = it.trailingStopPercent,
                createdOrderId = it.createdOrderId,
                orderToCreate = it.orderToCreate.convert(),
                orderToCancel = it.orderToCancel.convert(),
                clientConditionalOrderId = it.clientConditionalOrderId,
                status = it.status.convert(),
                orderCreationErrorCode = it.orderCreationErrorCode,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
                closedAt = it.closedAt,
            )
        }
    }

    override fun deleteConditionalOrder(id: String): AdapterObservable<ConditionalOrder> {
        return client.conditionalOrders.deleteConditionalOrder(id).mapToAdapter {
            ConditionalOrder(
                id = it.id,
                pair = it.marketSymbol.asPair(),
                operand = it.operand.convert(),
                triggerPrice = it.triggerPrice,
                trailingStopPercent = it.trailingStopPercent,
                createdOrderId = it.createdOrderId,
                orderToCreate = it.orderToCreate.convert(),
                orderToCancel = it.orderToCancel.convert(),
                clientConditionalOrderId = it.clientConditionalOrderId,
                status = it.status.convert(),
                orderCreationErrorCode = it.orderCreationErrorCode,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
                closedAt = it.closedAt,
            )
        }
    }

    override fun closeConditionalOrder(
        pair: CoinPair?,
        nextPageToken: String?,
        previousPageToken: String?,
        pageSize: String?,
        startDate: String?,
        endDate: String
    ): AdapterObservable<ConditionalOrder> {
        return client.conditionalOrders.closeConditionalOrder(
            pair?.asString(),
            nextPageToken,
            previousPageToken,
            pageSize,
            startDate,
            endDate
        ).mapToAdapter {
            ConditionalOrder(
                id = it.id,
                pair = it.marketSymbol.asPair(),
                operand = it.operand.convert(),
                triggerPrice = it.triggerPrice,
                trailingStopPercent = it.trailingStopPercent,
                createdOrderId = it.createdOrderId,
                orderToCreate = it.orderToCreate.convert(),
                orderToCancel = it.orderToCancel.convert(),
                clientConditionalOrderId = it.clientConditionalOrderId,
                status = it.status.convert(),
                orderCreationErrorCode = it.orderCreationErrorCode,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
                closedAt = it.closedAt,
            )
        }
    }

    override fun checkConditionalOrder(): AdapterObservable<Unit> {
        return client.conditionalOrders.checkConditionalOrder().map { }
    }

    override fun openConditionalOrder(pair: CoinPair?): AdapterObservable<ConditionalOrder> {
        return client.conditionalOrders.openConditionalOrder(pair?.asString()).mapToAdapter {
            ConditionalOrder(
                id = it.id,
                pair = it.marketSymbol.asPair(),
                operand = it.operand.convert(),
                triggerPrice = it.triggerPrice,
                trailingStopPercent = it.trailingStopPercent,
                createdOrderId = it.createdOrderId,
                orderToCreate = it.orderToCreate.convert(),
                orderToCancel = it.orderToCancel.convert(),
                clientConditionalOrderId = it.clientConditionalOrderId,
                status = it.status.convert(),
                orderCreationErrorCode = it.orderCreationErrorCode,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
                closedAt = it.closedAt,
            )
        }
    }

    override fun subscribeOpenConditionalOrder(pair: CoinPair?): AdapterObservable<ConditionalOrder> {
        val handler = SyncHandler(
            { client.conditionalOrders.openConditionalOrder(pair?.asString()) },
            { socketClient.subscribeConditionalOrder() }
        )

        return handler.handle().mapStreamToAdapter { conditionalOrderDelta ->
            val order = conditionalOrderDelta.delta
            ConditionalOrder(
                id = order.id,
                pair = order.marketSymbol.asPair(),
                operand = order.operand.convert(),
                triggerPrice = order.triggerPrice,
                trailingStopPercent = order.trailingStopPercent,
                createdOrderId = order.createdOrderId,
                orderToCreate = order.orderToCreate.convert(),
                orderToCancel = order.orderToCancel.convert(),
                clientConditionalOrderId = order.clientConditionalOrderId,
                status = order.status.convert(),
                orderCreationErrorCode = order.orderCreationErrorCode,
                createdAt = order.createdAt,
                updatedAt = order.updatedAt,
                closedAt = order.closedAt,
            )
        }
    }

    override fun postConditionalOrder(newConditionalOrder: NewConditionalOrder): AdapterObservable<ConditionalOrder> {
        val newOrder = BNewConditionalOrder(
            id = newConditionalOrder.id,
            marketSymbol = newConditionalOrder.pair.asString(),
            operand = newConditionalOrder.operand.convert(),
            triggerPrice = newConditionalOrder.triggerPrice,
            trailingStopPercent = newConditionalOrder.trailingStopPercent,
            createdOrderId = newConditionalOrder.createdOrderId,
            orderToCreate = newConditionalOrder.orderToCreate.convert(),
            orderToCancel = newConditionalOrder.orderToCancel.convert(),
            clientConditionalOrderId = newConditionalOrder.clientConditionalOrderId,
        )

        return client.conditionalOrders.postConditionalOrder(newOrder).mapToAdapter {
            ConditionalOrder(
                id = it.id,
                pair = it.marketSymbol.asPair(),
                operand = it.operand.convert(),
                triggerPrice = it.triggerPrice,
                trailingStopPercent = it.trailingStopPercent,
                createdOrderId = it.createdOrderId,
                orderToCreate = it.orderToCreate.convert(),
                orderToCancel = it.orderToCancel.convert(),
                clientConditionalOrderId = it.clientConditionalOrderId,
                status = it.status.convert(),
                orderCreationErrorCode = it.orderCreationErrorCode,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
                closedAt = it.closedAt,
            )
        }
    }
}