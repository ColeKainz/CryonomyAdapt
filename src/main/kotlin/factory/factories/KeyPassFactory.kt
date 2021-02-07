package factory.factories

import CryonomyAdapter
import adapters.bittrex.BittrexAdapter
import factory.AdapterNotFoundException
import factory.IAdapterFactory

class KeyPassFactory(private val key: String, private val password: String, private val tag: String): IAdapterFactory {

    override fun create(): CryonomyAdapter {
        return when(tag) {
            BittrexAdapter.TAG -> BittrexAdapter(key, password)
            else -> throw AdapterNotFoundException()
        }
    }
}