package adapters.bittrex

import models.AdapterObservable
import models.withdrawals.NewWithdrawal
import models.withdrawals.WhiteListAddress
import models.withdrawals.Withdrawal
import models.withdrawals.WithdrawalStatus

interface IWithdrawalBittrexAdapter : IBittrexAdapterBase {
    override fun getOpenWithdrawals(
        status: WithdrawalStatus?,
        symbol: String?
    ): AdapterObservable<List<Withdrawal>> {
        return client.withdrawal.getOpenWithdrawals(status?.convert(), symbol).mapToAdapter { list ->
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

    override fun getWithdrawal(withdrawalId: String): AdapterObservable<Withdrawal> {
        return client.withdrawal.getWithdrawal(withdrawalId).mapToAdapter {
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

    override fun getWithdrawals(txId: String): AdapterObservable<List<Withdrawal>> {
        return client.withdrawal.getWithdrawals(txId).mapToAdapter { list ->
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

    override fun deleteWithdrawal(withdrawalId: String): AdapterObservable<Withdrawal> {
        return client.withdrawal.getWithdrawal(withdrawalId).mapToAdapter {
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

    override fun postWithdrawal(withdrawal: NewWithdrawal): AdapterObservable<Withdrawal> {
        return client.withdrawal.postWithdrawal(withdrawal.convert()).mapToAdapter {
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
    ): AdapterObservable<List<Withdrawal>> {
        return client.withdrawal.getClosedWithdrawals(
            status?.convert(),
            symbol,
            nextPageToken,
            previousPageToken,
            pageSize,
            startDate,
            endDate
        ).mapToAdapter { list ->
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

    override fun getWhiteListedAddresses(): AdapterObservable<WhiteListAddress> {
        return client.withdrawal.getWhiteListedAddresses().mapToAdapter {
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