package adapters.bittrex

import models.withdrawals.NewWithdrawal
import models.withdrawals.WhiteListAddressStatus
import models.withdrawals.WithdrawalStatus
import models.withdrawals.WithdrawalStatus.*
import models.withdrawals.WithdrawalStatus.PENDING
import com.bushka.bittrex.model.withdrawals.WithdrawalStatus as BWithdrawalStatus
import com.bushka.bittrex.model.withdrawals.WhiteListAddressStatus as BWhiteListAddressStatus
import com.bushka.bittrex.model.withdrawals.NewWithdrawal as BNewWithdrawal

fun WithdrawalStatus.convert(): BWithdrawalStatus {
    return when (this) {
        REQUESTED -> BWithdrawalStatus.REQUESTED
        AUTHORIZED -> BWithdrawalStatus.AUTHORIZED
        PENDING -> BWithdrawalStatus.PENDING
        COMPLETED -> BWithdrawalStatus.COMPLETED
        ERROR_INVALID_ADDRESS -> BWithdrawalStatus.ERROR_INVALID_ADDRESS
        CANCELLED -> BWithdrawalStatus.CANCELLED
    }
}

fun BWithdrawalStatus.convert(): WithdrawalStatus {
    return when (this) {
        BWithdrawalStatus.REQUESTED -> REQUESTED
        BWithdrawalStatus.AUTHORIZED -> AUTHORIZED
        BWithdrawalStatus.PENDING -> PENDING
        BWithdrawalStatus.COMPLETED -> COMPLETED
        BWithdrawalStatus.ERROR_INVALID_ADDRESS -> ERROR_INVALID_ADDRESS
        BWithdrawalStatus.CANCELLED -> CANCELLED
    }
}

fun NewWithdrawal.convert(): BNewWithdrawal {
    return BNewWithdrawal(
        this.currencySymbol,
        this.quantity,
        this.cryptoAddress,
        this.cryptoAddressTag
    )
}

fun BWhiteListAddressStatus.convert(): WhiteListAddressStatus {
    return when(this) {
        BWhiteListAddressStatus.ACTIVE -> WhiteListAddressStatus.ACTIVE
        BWhiteListAddressStatus.PENDING -> WhiteListAddressStatus.PENDING
    }
}