package adapters.bittrex

import models.AdapterObservable
import models.coin.Coin
import models.withdrawals.NewWithdrawal
import models.withdrawals.WhiteListAddress
import models.withdrawals.Withdrawal
import models.withdrawals.WithdrawalStatus

internal interface IWithdrawalBittrexAdapter : IBittrexAdapterBase {
    override fun getOpenWithdrawals(
        status: WithdrawalStatus?,
        coin: Coin?
    ): AdapterObservable<List<Withdrawal>> {
        return client.withdrawal.getOpenWithdrawals(status?.convert(), coin?.symbol).mapToAdapter { list ->
            list.map {
                Withdrawal(
                    it.id,
                    it.currencySymbol.asCoin(),
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
                it.currencySymbol.asCoin(),
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
                    it.currencySymbol.asCoin(),
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
                it.currencySymbol.asCoin(),
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
                it.currencySymbol.asCoin(),
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
        coin: Coin?,
        nextPageToken: String?,
        previousPageToken: String?,
        pageSize: String?,
        startDate: String?,
        endDate: String?
    ): AdapterObservable<List<Withdrawal>> {
        return client.withdrawal.getClosedWithdrawals(
            status?.convert(),
            coin?.symbol,
            nextPageToken,
            previousPageToken,
            pageSize,
            startDate,
            endDate
        ).mapToAdapter { list ->
            list.map {
                Withdrawal(
                    it.id,
                    it.currencySymbol.asCoin(),
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
                it.currencySymbol.asCoin(),
                it.createdAt,
                it.status.convert(),
                it.activeAt,
                it.cryptoAddress,
                it.cryptoAddressTag
            )
        }
    }
}