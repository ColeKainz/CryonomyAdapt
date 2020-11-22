package adapters.bittrex

import io.reactivex.Observable
import models.withdrawals.NewWithdrawal
import models.withdrawals.WhiteListAddress
import models.withdrawals.Withdrawal
import models.withdrawals.WithdrawalStatus

interface IWithdrawalBittrexAdapter : IBittrexAdapterBase {
    override fun getOpenWithdrawals(
        status: WithdrawalStatus?,
        symbol: String?
    ): Observable<List<Withdrawal>> {
        return client.withdrawal.getOpenWithdrawals(status?.convert(), symbol).map { list ->
            list.map {
                Withdrawal(
                    it.id,
                    it.currencySymbol,
                    it.quantity,
                    it.cryptoAddress,
                    it.cryptoAddressTag,
                    it.txCost,
                    it.txId,
                    it.status.convert(),
                    it.createdAt,
                    it.completedAt,
                )
            }
        }
    }

    override fun getWithdrawal(withdrawalId: String): Observable<Withdrawal> {
        return client.withdrawal.getWithdrawal(withdrawalId).map {
            Withdrawal(
                it.id,
                it.currencySymbol,
                it.quantity,
                it.cryptoAddress,
                it.cryptoAddressTag,
                it.txCost,
                it.txId,
                it.status.convert(),
                it.createdAt,
                it.completedAt,
            )
        }
    }

    override fun getWithdrawals(txId: String): Observable<List<Withdrawal>> {
        return client.withdrawal.getWithdrawals(txId).map { list ->
            list.map {
                Withdrawal(
                    it.id,
                    it.currencySymbol,
                    it.quantity,
                    it.cryptoAddress,
                    it.cryptoAddressTag,
                    it.txCost,
                    it.txId,
                    it.status.convert(),
                    it.createdAt,
                    it.completedAt,
                )
            }
        }
    }

    override fun deleteWithdrawal(withdrawalId: String): Observable<Withdrawal> {
        return client.withdrawal.getWithdrawal(withdrawalId).map {
            Withdrawal(
                it.id,
                it.currencySymbol,
                it.quantity,
                it.cryptoAddress,
                it.cryptoAddressTag,
                it.txCost,
                it.txId,
                it.status.convert(),
                it.createdAt,
                it.completedAt,
            )
        }
    }

    override fun postWithdrawal(withdrawal: NewWithdrawal): Observable<Withdrawal> {
        return client.withdrawal.postWithdrawal(withdrawal.convert()).map {
            Withdrawal(
                it.id,
                it.currencySymbol,
                it.quantity,
                it.cryptoAddress,
                it.cryptoAddressTag,
                it.txCost,
                it.txId,
                it.status.convert(),
                it.createdAt,
                it.completedAt,
            )
        }
    }

    override fun getClosedWithdrawals(
        status: WithdrawalStatus?,
        symbol: String?,
        nextPageToken: String?,
        previousPageToken: String?,
        pageSize: String?,
        startDate: String?,
        endDate: String?
    ): Observable<List<Withdrawal>> {
        return client.withdrawal.getClosedWithdrawals(
            status?.convert(),
            symbol,
            nextPageToken,
            previousPageToken,
            pageSize,
            startDate,
            endDate
        ).map { list ->
            list.map {
                Withdrawal(
                    it.id,
                    it.currencySymbol,
                    it.quantity,
                    it.cryptoAddress,
                    it.cryptoAddressTag,
                    it.txCost,
                    it.txId,
                    it.status.convert(),
                    it.createdAt,
                    it.completedAt,
                )
            }
        }
    }

    override fun getWhiteListedAddresses(): Observable<WhiteListAddress> {
        return client.withdrawal.getWhiteListedAddresses().map {
            WhiteListAddress(
                it.currencySymbol,
                it.createdAt,
                it.status.convert(),
                it.activeAt,
                it.cryptoAddress,
                it.cryptoAddressTag
            )
        }
    }
}