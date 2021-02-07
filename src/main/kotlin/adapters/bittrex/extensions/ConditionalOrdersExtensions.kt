package adapters.bittrex

import com.bushka.bittrex.model.conditionalorders.NewOrder as BNewOrder
import com.bushka.bittrex.model.conditionalorders.NewCancelConditionalOrder as BNewCancelConditionalOrder
import com.bushka.bittrex.model.conditionalorders.ConditionalOrderStatus as BConditionalOrderStatus
import com.bushka.bittrex.model.conditionalorders.ConditionalOrderOperand as BConditionalOrderOperand
import com.bushka.bittrex.model.conditionalorders.ConditionalOrderNewCancelType as BConditionalOrderNewCancelType
import com.bushka.bittrex.model.conditionalorders.ConditionalOrderDirection as BConditionalOrderDirection
import com.bushka.bittrex.model.conditionalorders.ConditionalOrderTimeInForce as BConditionalOrderTimeInForce
import com.bushka.bittrex.model.conditionalorders.ConditionalOrderNewOrderType as BConditionalOrderNewOrderType
import com.bushka.bittrex.model.conditionalorders.ConditionalOrderDirection.*
import com.bushka.bittrex.model.conditionalorders.ConditionalOrderNewCancelType.*
import com.bushka.bittrex.model.conditionalorders.ConditionalOrderNewOrderType.*
import com.bushka.bittrex.model.conditionalorders.ConditionalOrderOperand.*
import com.bushka.bittrex.model.conditionalorders.ConditionalOrderStatus.*
import com.bushka.bittrex.model.conditionalorders.ConditionalOrderTimeInForce.*
import models.conditionalorders.*

fun BNewOrder.convert(): NewOrder {
    return NewOrder(
        pair = this.marketSymbol.asPair(),
        direction = this.direction.convert(),
        type = this.type.convert(),
        quantity = this.quantity,
        ceiling = this.ceiling,
        limit = this.limit,
        timeInForce = this.timeInForce.convert(),
        clientOrderId = this.clientOrderId,
        useAwards = this.useAwards,
    )
}

fun NewOrder.convert(): BNewOrder {
    return com.bushka.bittrex.model.conditionalorders.NewOrder(
        marketSymbol = this.pair.asString(),
        direction = this.direction.convert(),
        type = this.type.convert(),
        quantity = this.quantity,
        ceiling = this.ceiling,
        limit = this.limit,
        timeInForce = this.timeInForce.convert(),
        clientOrderId = this.clientOrderId,
        useAwards = this.useAwards,
    )
}

fun BNewCancelConditionalOrder.convert(): NewCancelConditionalOrder {
    return NewCancelConditionalOrder(
        type = this.type.convert(),
        id = this.id,
    )
}

fun NewCancelConditionalOrder.convert(): BNewCancelConditionalOrder {
    return com.bushka.bittrex.model.conditionalorders.NewCancelConditionalOrder(
        this.type.convert(),
        this.id
    )
}

fun BConditionalOrderDirection.convert(): ConditionalOrderDirection {
    return when(this) {
        BUY -> ConditionalOrderDirection.BUY
        SELL -> ConditionalOrderDirection.SELL
    }
}

fun BConditionalOrderNewCancelType.convert(): ConditionalOrderNewCancelType {
    return when(this) {
        ORDER -> ConditionalOrderNewCancelType.ORDER
        CONDITIONAL_ORDER -> ConditionalOrderNewCancelType.CONDITIONAL_ORDER
    }
}

fun BConditionalOrderTimeInForce.convert(): ConditionalOrderTimeInForce {
    return when(this) {
        GOOD_TIL_CANCELED -> ConditionalOrderTimeInForce.GOOD_TIL_CANCELED
        IMMEDIATE_OR_CANCEL -> ConditionalOrderTimeInForce.IMMEDIATE_OR_CANCEL
        FILL_OR_KILL -> ConditionalOrderTimeInForce.FILL_OR_KILL
        POST_ONLY_GOOD_TIL_CANCELLED -> ConditionalOrderTimeInForce.POST_ONLY_GOOD_TIL_CANCELLED
        BUY_NOW -> ConditionalOrderTimeInForce.BUY_NOW
    }
}

fun BConditionalOrderOperand.convert(): ConditionalOrderOperand {
    return when(this) {
        LessThanOrEqual -> ConditionalOrderOperand.LessThanOrEqual
        GreaterThanOrEqual -> ConditionalOrderOperand.GreaterThanOrEqual
    }
}

fun BConditionalOrderNewOrderType.convert(): ConditionalOrderNewOrderType {
    return when(this) {
        LIMIT -> ConditionalOrderNewOrderType.LIMIT
        MARKET -> ConditionalOrderNewOrderType.MARKET
        CEILING_LIMIT -> ConditionalOrderNewOrderType.CEILING_LIMIT
        CEILING_MARKET -> ConditionalOrderNewOrderType.CEILING_MARKET
    }
}

fun BConditionalOrderStatus.convert(): ConditionalOrderStatus {
    return when(this) {
        OPEN -> ConditionalOrderStatus.OPEN
        COMPLETED -> ConditionalOrderStatus.COMPLETED
        CANCELLED -> ConditionalOrderStatus.CANCELLED
        FAILED -> ConditionalOrderStatus.FAILED
    }
}

fun ConditionalOrderDirection.convert(): BConditionalOrderDirection {
    return when(this) {
        ConditionalOrderDirection.BUY -> BUY
        ConditionalOrderDirection.SELL -> SELL
    }
}

fun ConditionalOrderNewCancelType.convert(): BConditionalOrderNewCancelType {
    return when(this) {
        ConditionalOrderNewCancelType.ORDER -> ORDER
        ConditionalOrderNewCancelType.CONDITIONAL_ORDER -> CONDITIONAL_ORDER
    }
}

fun ConditionalOrderTimeInForce.convert(): BConditionalOrderTimeInForce {
    return when(this) {
        ConditionalOrderTimeInForce.GOOD_TIL_CANCELED -> GOOD_TIL_CANCELED
        ConditionalOrderTimeInForce.IMMEDIATE_OR_CANCEL -> IMMEDIATE_OR_CANCEL
        ConditionalOrderTimeInForce.FILL_OR_KILL -> FILL_OR_KILL
        ConditionalOrderTimeInForce.POST_ONLY_GOOD_TIL_CANCELLED -> POST_ONLY_GOOD_TIL_CANCELLED
        ConditionalOrderTimeInForce.BUY_NOW -> BUY_NOW
    }
}

fun ConditionalOrderOperand.convert(): BConditionalOrderOperand {
    return when(this) {
        ConditionalOrderOperand.LessThanOrEqual -> LessThanOrEqual
        ConditionalOrderOperand.GreaterThanOrEqual -> GreaterThanOrEqual
    }
}

fun ConditionalOrderNewOrderType.convert(): BConditionalOrderNewOrderType {
    return when(this) {
        ConditionalOrderNewOrderType.LIMIT -> LIMIT
        ConditionalOrderNewOrderType.MARKET -> MARKET
        ConditionalOrderNewOrderType.CEILING_LIMIT -> CEILING_LIMIT
        ConditionalOrderNewOrderType.CEILING_MARKET -> CEILING_MARKET
    }
}

fun ConditionalOrderStatus.convert(): BConditionalOrderStatus {
    return when(this) {
        ConditionalOrderStatus.OPEN -> OPEN
        ConditionalOrderStatus.COMPLETED -> COMPLETED
        ConditionalOrderStatus.CANCELLED -> CANCELLED
        ConditionalOrderStatus.FAILED -> FAILED
    }
}