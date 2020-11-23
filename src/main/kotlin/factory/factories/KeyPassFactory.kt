package factory.factories

import ICryonomyAdapter
import adapters.bittrex.BittrexAdapter
import factory.AdapterNotFoundException
import factory.IAdapterFactory

class KeyPassFactory(private val key: String, private val password: String, private val name: String): IAdapterFactory {

    override fun create(): ICryonomyAdapter {
        return when(name) {
            BittrexAdapter.NAME -> BittrexAdapter(key, password)
            else -> throw AdapterNotFoundException()
        }
    }
}