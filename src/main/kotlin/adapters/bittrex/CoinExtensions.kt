package adapters.bittrex

import models.coin.Coin
import models.coin.CoinPair

internal fun String.asCoin(): Coin {
    return Coin(this)
}

internal fun String.asPair(): CoinPair {
    val coins = this.split('-', limit = 2)
    return CoinPair(
        coins.first().asCoin(),
        coins.last().asCoin()
    )
}

internal fun CoinPair.asString(): String {
    return "$base-$counter"
}