package adapters.bittrex

import com.bushka.bittrex.model.deposits.DepositSource.*
import models.deposits.DepositStatus
import com.bushka.bittrex.model.deposits.DepositStatus as BDepositStatus
import com.bushka.bittrex.model.deposits.DepositSource as BDepositSource
import com.bushka.bittrex.model.deposits.DepositStatus.*
import models.deposits.DepositSource

fun DepositStatus.convert(): BDepositStatus {
    return when(this) {
        DepositStatus.PENDING -> PENDING
        DepositStatus.COMPLETED -> COMPLETED
        DepositStatus.ORPHANED -> ORPHANED
        DepositStatus.INVALIDATED -> INVALIDATED
    }
}

fun BDepositStatus.convert(): DepositStatus {
    return when(this) {
        PENDING -> DepositStatus.PENDING
        COMPLETED -> DepositStatus.COMPLETED
        ORPHANED -> DepositStatus.ORPHANED
        INVALIDATED -> DepositStatus.INVALIDATED
    }
}

fun BDepositSource.convert(): DepositSource {
    return when(this) {
        BLOCKCHAIN -> DepositSource.BLOCKCHAIN
        WIRE_TRANSFER -> DepositSource.WIRE_TRANSFER
        CREDIT_CARD -> DepositSource.CREDIT_CARD
    }
}