package models.deposits

enum class DepositStatus(val value: String) {
    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    ORPHANED("ORPHANED"),
    INVALIDATED("INVALIDATED")
}