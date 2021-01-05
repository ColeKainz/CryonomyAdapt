package adapters.bittrex

import models.AdapterObservable
import com.bushka.bittrex.model.addresses.AddressStatus as BAddressStatus
import models.addresses.Address
import models.addresses.AddressStatus
import models.addresses.NewAddress
import com.bushka.bittrex.model.addresses.NewAddress as BNewAddress

interface IAddressesBittrexAdapter : IBittrexAdapterBase {
    override fun getAddresses(): AdapterObservable<List<Address>> {
        return client.addresses.getAddresses().map { list ->
            list.map {
                val status = when (it.status) {
                    BAddressStatus.REQUESTED -> AddressStatus.REQUESTED
                    BAddressStatus.PROVISIONED -> AddressStatus.PROVISIONED
                }

                Address(status, it.currencySymbol, it.cryptoAddress, it.cryptoAddressTag)
            }
        }
    }

    override fun putAddresses(address: NewAddress): AdapterObservable<List<Address>> {
        val bNewAddress = BNewAddress(address.currencySymbol)

        return client.addresses.putAddresses(bNewAddress).map { list ->
            list.map {
                val status = when (it.status) {
                    BAddressStatus.REQUESTED -> AddressStatus.REQUESTED
                    BAddressStatus.PROVISIONED -> AddressStatus.PROVISIONED
                }

                Address(status, it.currencySymbol, it.cryptoAddress, it.cryptoAddressTag)
            }
        }
    }

    override fun getAddresses(symbol: String): AdapterObservable<Address> {
        return client.addresses.getAddresses(symbol).map {
            val status = when (it.status) {
                BAddressStatus.REQUESTED -> AddressStatus.REQUESTED
                BAddressStatus.PROVISIONED -> AddressStatus.PROVISIONED
            }

            Address(status, it.currencySymbol, it.cryptoAddress, it.cryptoAddressTag)
        }
    }
}