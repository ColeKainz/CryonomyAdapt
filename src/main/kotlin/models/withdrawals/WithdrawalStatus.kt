package models.withdrawals

enum class WithdrawalStatus(val value: String) {
    REQUESTED("REQUESTED"),
    AUTHORIZED("AUTHORIZED"),
    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    ERROR_INVALID_ADDRESS("ERROR_INVALID_ADDRESS"),
    CANCELLED("CANCELLED")
}