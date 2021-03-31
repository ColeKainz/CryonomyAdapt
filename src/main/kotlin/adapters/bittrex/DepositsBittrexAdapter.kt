package adapters.bittrex

import models.AdapterObservable
import models.coin.Coin
import models.deposits.Deposit
import models.deposits.DepositStatus

internal interface DepositsBittrexAdapter : BittrexAdapterBase {
    override fun getOpenDeposits(coin: Coin?, status: DepositStatus?): AdapterObservable<List<Deposit>> {
        return client.deposits.getOpenDeposits(status?.convert(), coin?.symbol).mapToAdapter { list ->
            list.map {
                Deposit(
                    it.id,
                    it.currencySymbol.asCoin(),
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

    override fun subscribeOpenDeposits(coin: Coin?, status: DepositStatus?): AdapterObservable<Deposit> {
        val handler = SyncHandler(
            { client.deposits.checkOpenDeposits() },
            { socketClient.subscribeDeposit() }
        )

        return handler.handle().mapStreamToAdapter { depositDelta ->
            val deposit = depositDelta.delta
            Deposit(
                deposit.id,
                deposit.currencySymbol.asCoin(),
                deposit.quantity,
                deposit.cryptoAddress,
                deposit.cryptoAddressTag,
                deposit.txId,
                deposit.confirmations,
                deposit.updatedAt,
                deposit.completedAt,
                deposit.status.convert(),
                deposit.source.convert()
            )
        }
    }

    override fun checkOpenDeposits(): AdapterObservable<Unit> {
        return client.deposits.checkOpenDeposits().map { }
    }

    override fun getClosedDeposits(
        status: DepositStatus?,
        coin: Coin?,
        nextPageToken: String?,
        previousPageToken: String?,
        pageSize: String?,
        startDate: String?,
        endDate: String?
    ): AdapterObservable<List<Deposit>> {
        return client.deposits.getClosedDeposits(
            status?.convert(),
            coin?.symbol,
            nextPageToken,
            previousPageToken,
            pageSize,
            startDate,
            endDate
        ).mapToAdapter { list ->
            list.map {
                Deposit(
                    it.id,
                    it.currencySymbol.asCoin(),
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
        return client.deposits.getOpenDeposits(txId).mapToAdapter { list ->
            list.map {
                Deposit(
                    it.id,
                    it.currencySymbol.asCoin(),
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
        return client.deposits.getDeposit(depositId).mapToAdapter {
            Deposit(
                it.id,
                it.currencySymbol.asCoin(),
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