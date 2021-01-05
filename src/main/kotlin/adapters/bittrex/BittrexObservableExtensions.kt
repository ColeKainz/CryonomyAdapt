package adapters.bittrex

import com.bushka.bittrex.network.BittrexObservable
import io.reactivex.Observable
import models.ResponseException
import retrofit2.HttpException

fun <O, R> BittrexObservable<O>.mapToAdapter(mapper: (O) -> R): Observable<R> {
    return this.map {
        try {
            mapper(it)
        } catch (e: HttpException) {
            throw ResponseException(
                e.code(),
                e.message()
            )
        }
    }
}