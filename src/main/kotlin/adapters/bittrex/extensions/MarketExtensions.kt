package adapters.bittrex

import com.bushka.bittrex.model.markets.OrderBookDepth as BOrderBookDepth
import com.bushka.bittrex.model.markets.OrderBookEntry as BOrderBookEntry
import com.bushka.bittrex.model.markets.TradeTakerSide as BTradeTakerSide
import com.bushka.bittrex.model.markets.CandleInterval as BCandleInterval
import com.bushka.bittrex.model.markets.OrderBookDepth.*
import com.bushka.bittrex.model.markets.TradeTakerSide.*
import models.markets.CandleInterval
import models.markets.CandleInterval.*
import models.markets.OrderBookDepth
import models.markets.OrderBookEntry
import models.markets.TradeTakerSide

fun OrderBookDepth.convert(): BOrderBookDepth {
    return when(this) {
        OrderBookDepth.SHALLOW -> SHALLOW
        OrderBookDepth.MID -> MID
        OrderBookDepth.DEEP -> DEEP
    }
}

fun BOrderBookEntry.convert(): OrderBookEntry {
    return OrderBookEntry(
        this.quantity,
        this.rate
    )
}

fun BTradeTakerSide.convert(): TradeTakerSide {
    return when(this) {
        BUY -> TradeTakerSide.BUY
        SELL -> TradeTakerSide.SELL
    }
}

fun CandleInterval.convert(): BCandleInterval {
    return when(this) {
        MINUTE_1 -> BCandleInterval.MINUTE_1
        MINUTE_5 -> BCandleInterval.MINUTE_5
        HOUR_1 -> BCandleInterval.HOUR_1
        DAY_1 -> BCandleInterval.DAY_1
    }
}