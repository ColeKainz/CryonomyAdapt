package adapters.bittrex

import com.bushka.bittrex.model.currencies.CurrencyStatus.*
import models.currencies.CurrencyStatus
import com.bushka.bittrex.model.currencies.CurrencyStatus as BCurrencyStatus

fun BCurrencyStatus.convert(): CurrencyStatus {
    return when(this) {
        ONLINE -> CurrencyStatus.ONLINE
        OFFLINE -> CurrencyStatus.OFFLINE
    }
}