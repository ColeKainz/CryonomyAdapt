package models.conditionalorders

enum class ConditionalOrderNewOrderType(val value: String) {
    LIMIT("LIMIT"),
    MARKET("MARKET"),
    CEILING_LIMIT("CEILING_LIMIT"),
    CEILING_MARKET("CEILING_MARKET")
}