package adapters.bittrex

import io.reactivex.Observable
import models.deposits.Deposit
import models.deposits.DepositStatus
import java.math.BigDecimal

interface IDepositsBittrexAdapter : IBittrexAdapterBase {
    override fun getOpenDeposits(status: DepositStatus?, symbol: String?): Observable<List<Deposit>> {
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
    ): Observable<List<Deposit>> {
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

    override fun getOpenDeposits(txId: String): Observable<List<Deposit>> {
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

    override fun getDeposit(depositId: String): Observable<Deposit> {
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