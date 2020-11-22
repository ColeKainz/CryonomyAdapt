package models.conditionalorders

enum class ConditionalOrderOperand(val value: String) {
    LessThanOrEqual("LTE"),
    GreaterThanOrEqual("GTE")
}