package models.conditionalorders

enum class ConditionalOrderStatus(val value: String) {
    OPEN("OPEN"),
    COMPLETED("COMPLETED"),
    CANCELLED("COMPLETED"),
    FAILED("COMPLETED")
}