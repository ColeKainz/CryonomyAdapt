package adapters.bittrex

import com.bushka.bittrex.network.BittrexObservable
import io.reactivex.Observable
import models.ResponseException
import retrofit2.HttpException
import retrofit2.Response

/**
 * Map the Bittrex response to the adapter. Throw an error if the response isn't successful.
 * This will force the error to [onFailure], rather than leaving it to be handled in [onSuccess].
 */
fun <O, R> BittrexObservable<Response<O>>.mapToAdapter(mapper: (O) -> R): Observable<R> {
    return this.map {
        if (!it.isSuccessful) {
            throw ResponseException(
                it.code(),
                it.message()
            )
        }

        try {
            mapper(it.body()!!)
        } catch (e: HttpException) {
            throw ResponseException(
                e.code(),
                e.message()
            )
        }
    }
}

fun <O, R> BittrexObservable<O>.mapStreamToAdapter(mapper: (O) -> R): Observable<R> {
    return this.map {
        try {
            return@map mapper(it)
        } catch (e: HttpException) {
            throw ResponseException(
                e.code(),
                e.message()
            )
        }
    }
}

val <T> BittrexObservable<Response<T>>.sequence: Int
    get() = this.blockingFirst().headers().get("Sequence")!!.toInt()