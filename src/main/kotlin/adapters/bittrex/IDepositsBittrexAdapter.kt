package adapters.bittrex

import models.AdapterObservable
import models.deposits.Deposit
import models.deposits.DepositStatus

interface IDepositsBittrexAdapter : IBittrexAdapterBase {
    override fun getOpenDeposits(status: DepositStatus?, symbol: String?): AdapterObservable<List<Deposit>> {
        return client.deposits.getOpenDeposits(status?.convert(), symbol).map { list ->
            list.map {
                Deposit(
                    it.id,
                    it.currencySymbol,
                    it.quantity,
                    it.cryptoAddress,
                    it.cryptoAddressTag,
                    it.txId,
                    it.confirmations,
                    it.updatedAt,
                    it.completedAt,
                    it.status.convert(),
                    it.source.convert()
                )
            }
        }
    }

    override fun checkOpenDeposits() {
        client.deposits.checkOpenDeposits()
    }

    override fun getClosedDeposits(
        status: DepositStatus?,
        symbol: String?,
        nextPageToken: String?,
        previousPageToken: String?,
        pageSize: String?,
        startDate: String?,
        endDate: String?
    ): AdapterObservable<List<Deposit>> {
        return client.deposits.getClosedDeposits(
            status?.convert(),
            symbol,
            nextPageToken,
            previousPageToken,
            pageSize,
            startDate,
            endDate
        ).map { list ->
            list.map {
                Deposit(
                    it.id,
                    it.currencySymbol,
                    it.quantity,
                    it.cryptoAddress,
                    it.cryptoAddressTag,
                    it.txId,
                    it.confirmations,
                    it.updatedAt,
                    it.completedAt,
                    it.status.convert(),
                    it.source.convert()
                )
            }
        }
    }

    override fun getOpenDeposits(txId: String): AdapterObservable<List<Deposit>> {
        return client.deposits.getOpenDeposits(txId).map { list ->
            list.map {
                Deposit(
                    it.id,
                    it.currencySymbol,
                    it.quantity,
                    it.cryptoAddress,
                    it.cryptoAddressTag,
                    it.txId,
                    it.confirmations,
                    it.updatedAt,
                    it.completedAt,
                    it.status.convert(),
                    it.source.convert()
                )
            }
        }
    }

    override fun getDeposit(depositId: String): AdapterObservable<Deposit> {
        return client.deposits.getDeposit(depositId).map {
            Deposit(
                it.id,
                it.currencySymbol,
                it.quantity,
                it.cryptoAddress,
                it.cryptoAddressTag,
                it.txId,
                it.confirmations,
                it.updatedAt,
                it.completedAt,
                it.status.convert(),
                it.source.convert()
            )

        }
    }
}