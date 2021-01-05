package adapters.bittrex


import models.AdapterObservable
import models.conditionalorders.*

import com.bushka.bittrex.model.conditionalorders.NewConditionalOrder as BNewConditionalOrder


interface IConditionalOrdersBittrexAdapter : IBittrexAdapterBase {
    override fun getConditionalOrder(id: String): AdapterObservable<ConditionalOrder> {
        return client.conditionalOrders.getConditionalOrder(id).mapToAdapter {
            ConditionalOrder(
                id = it.id,
                marketSymbol = it.marketSymbol,
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
                marketSymbol = it.marketSymbol,
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
        symbol: String?,
        nextPageToken: String?,
        previousPageToken: String?,
        pageSize: String?,
        startDate: String?,
        endDate: String
    ): AdapterObservable<ConditionalOrder> {
        return client.conditionalOrders.closeConditionalOrder(
            symbol,
            nextPageToken,
            previousPageToken,
            pageSize,
            startDate,
            endDate
        ).mapToAdapter {
            ConditionalOrder(
                id = it.id,
                marketSymbol = it.marketSymbol,
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

    override fun openConditionalOrder(symbol: String?): AdapterObservable<ConditionalOrder> {
        return client.conditionalOrders.openConditionalOrder(symbol).mapToAdapter {
            ConditionalOrder(
                id = it.id,
                marketSymbol = it.marketSymbol,
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

    override fun postConditionalOrder(newConditionalOrder: NewConditionalOrder): AdapterObservable<ConditionalOrder> {
        val newOrder = BNewConditionalOrder(
            id = newConditionalOrder.id,
            marketSymbol = newConditionalOrder.marketSymbol,
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
                marketSymbol = it.marketSymbol,
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