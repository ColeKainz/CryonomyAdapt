package models.orders

enum class OrderType(val value: String) {
    LIMIT("LIMIT"),
    MARKET("MARKET"),
    CEILING_LIMIT("CEILING_LIMIT"),
    CEILING_MARKET("CEILING_MARKET")
}