package models.conditionalorders

enum class ConditionalOrderTimeInForce(val value: String) {
    GOOD_TIL_CANCELED("GOOD_TIL_CANCELED"),
    IMMEDIATE_OR_CANCEL("IMMEDIATE_OR_CANCEL"),
    FILL_OR_KILL("FILL_OR_KILL"),
    POST_ONLY_GOOD_TIL_CANCELLED("POST_ONLY_GOOD_TIL_CANCELLED"),
    BUY_NOW("BUY_NOW")
}