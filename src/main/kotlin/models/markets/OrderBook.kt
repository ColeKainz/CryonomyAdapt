package models.markets

data class OrderBook (
    val bid: List<OrderBookEntry>,
    val ask: List<OrderBookEntry>
)

data class OrderBookDelta (
    val bidDelta: List<OrderBookEntry>,
    val askDelta: List<OrderBookEntry>
)