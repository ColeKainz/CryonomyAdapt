package adapters.bittrex

import io.reactivex.Observable
import models.AdapterObservable
import models.account.Account
import models.account.AccountVolume

interface IAccountBittrexAdapter: IBittrexAdapterBase {
    override fun getAccount(): AdapterObservable<Account> {
        return client.account.getAccount().map {
            Account(it.subAccountId, it.accountId)
        }
    }

    override fun getAccountVolume(): AdapterObservable<AccountVolume> {
        return client.account.getAccountVolume().map {
            AccountVolume(it.updated, it.volume30days)
        }
    }
}