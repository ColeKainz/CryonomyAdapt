package models.deposits

enum class DepositSource(val value: String) {
    BLOCKCHAIN("BLOCKCHAIN"),
    WIRE_TRANSFER("WIRE_TRANSFER"),
    CREDIT_CARD("CREDIT_CARD")
}