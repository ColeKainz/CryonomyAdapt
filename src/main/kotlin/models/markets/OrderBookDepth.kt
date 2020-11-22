package models.markets

enum class OrderBookDepth(val value: Int) {
    SHALLOW(1),
    MID(25),
    DEEP(500)
}