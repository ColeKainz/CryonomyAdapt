package adapters.bittrex

import ICryonomyAdapter
import com.bushka.bittrex.BittrexClient

internal interface IBittrexAdapterBase: ICryonomyAdapter {
    val client: BittrexClient
}