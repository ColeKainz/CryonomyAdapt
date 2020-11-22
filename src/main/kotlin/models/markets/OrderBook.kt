package models.markets

data class OrderBook (
    val bid: List<OrderBookEntry>,
    val ask: List<OrderBookEntry>
)