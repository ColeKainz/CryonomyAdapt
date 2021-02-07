import adapters.bittrex.BittrexAdapter
import com.bushka.bittrex.network.BittrexObservable
import factory.AdapterNotFoundException
import factory.factories.KeyPassFactory
import io.mockk.every
import io.mockk.verify
import io.reactivex.Observer
import models.coin.Coin
import models.coin.CoinPair
import org.junit.Before
import org.junit.Test

class BittrexTest {

    @Before
    fun before(){}

    /**
     * Show not throw [AdapterNotFoundException]
     */
    @Test
    fun `When adapter tag is BittrexAdapter tag, create Bittrex Adapter`() {
        KeyPassFactory("My", "Stuff", BittrexAdapter.TAG).create()
    }

    @Test
    fun `When adapter ticker is requested, get Bittrex ticker`() {
        val adapter = KeyPassFactory("My", "Stuff", BittrexAdapter.TAG).create()
        val bittrexAdapter = adapter as BittrexAdapter
        val mockClient = TestTools.mockClient(bittrexAdapter, bittrexAdapter::client)
        every { mockClient.markets.getTicker(any()) } answers {
            getSimpleBittrexObservable()
        }

        adapter.subscribeTicker(CoinPair(Coin("BTC"), Coin("XMR")))
        verify { bittrexAdapter.client.markets.getTicker("BTC-XMR") }
    }

    private fun <T> getSimpleBittrexObservable(): BittrexObservable<T> {
        return object : BittrexObservable<T>() {
            override fun subscribeActual(observer: Observer<in T>) {}
        }
    }
}