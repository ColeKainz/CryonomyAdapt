package adapters.bittrex

import ICryonomyAdapter
import com.bushka.bittrex.BittrexClient

interface IBittrexAdapterBase: ICryonomyAdapter {
    val client: BittrexClient

}