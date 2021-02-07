package adapters.bittrex

import models.AdapterObservable
import com.bushka.bittrex.model.addresses.AddressStatus as BAddressStatus
import models.addresses.Address
import models.addresses.AddressStatus
import models.addresses.NewAddress
import models.coin.Coin
import com.bushka.bittrex.model.addresses.NewAddress as BNewAddress

internal interface AddressesBittrexAdapter : BittrexAdapterBase {
    override fun getAddresses(): AdapterObservable<List<Address>> {
        return client.addresses.getAddresses().mapToAdapter { list ->
            list.map {
                val status = when (it.status) {
                    BAddressStatus.REQUESTED -> AddressStatus.REQUESTED
                    BAddressStatus.PROVISIONED -> AddressStatus.PROVISIONED
                }

                Address(status, it.currencySymbol.asCoin(), it.cryptoAddress, it.cryptoAddressTag)
            }
        }
    }

    override fun putAddresses(address: NewAddress): AdapterObservable<List<Address>> {
        val bNewAddress = BNewAddress(address.coin.symbol)

        return client.addresses.putAddresses(bNewAddress).mapToAdapter { list ->
            list.map {
                val status = when (it.status) {
                    BAddressStatus.REQUESTED -> AddressStatus.REQUESTED
                    BAddressStatus.PROVISIONED -> AddressStatus.PROVISIONED
                }

                Address(status, it.currencySymbol.asCoin(), it.cryptoAddress, it.cryptoAddressTag)
            }
        }
    }

    override fun getAddresses(coin: Coin): AdapterObservable<Address> {
        return client.addresses.getAddresses(coin.symbol).mapToAdapter {
            val status = when (it.status) {
                BAddressStatus.REQUESTED -> AddressStatus.REQUESTED
                BAddressStatus.PROVISIONED -> AddressStatus.PROVISIONED
            }

            Address(status, it.currencySymbol.asCoin(), it.cryptoAddress, it.cryptoAddressTag)
        }
    }
}