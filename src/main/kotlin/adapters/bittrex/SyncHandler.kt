package adapters.bittrex

import com.bushka.bittrex.model.sockets.Sequenced
import com.bushka.bittrex.network.BittrexObservable
import com.bushka.bittrex.network.onFailure
import io.reactivex.Observable
import retrofit2.Response

/**
 * This follows the synchronizing guidelines provided by the Bittrex Api. The steps laid out in the guidelines are explained
 * throughout the [handle] function. Step 6 is removed as it is deemed useless to return the result of the rest call.
 */
class SyncHandler<T: Any, R: Sequenced>(private val getBlockingRestSequence: () -> BittrexObservable<Response<T>>, private val getStream: () -> BittrexObservable<R>) {
    fun handle(): Observable<R> {
        // 1. Subscribe to the relevant socket streams.
        // 2. Begin to queue up messages without processing them.
        val streamResponse = getStream()
        var firstSteamSequence: Int? = null
        var previousSequence = 0

        // 3. Call the equivalent v3 REST API and record both the
        // results and the value of the returned sequence header.
        // Refer to the descriptions of individual streams to find
        // the corresponding REST API.
        val sequenceObservable = getBlockingRestSequence()
        var sequence = sequenceObservable.sequence

        return streamResponse.skipWhile {
            sequenceObservable.onFailure { error -> throw error }

            // 7. Continue to apply messages as they are received
            // from the socket as long as sequence number on the
            // stream is always increasing by 1 each message
            // (Note: for private streams, the sequence number is
            // scoped to a single account or subaccount).
            //
            // 8. If a message is received that is not the next in
            // order, return to step 2 in this process
            if (firstSteamSequence == null || previousSequence != it.sequence - 1) {
                firstSteamSequence = it.sequence
            }

            // 4. If the Sequence header is less than the sequence
            // number of the first queued socket message received
            // (unlikely), discard the results of step 3 and then
            // repeat step 3 until this check passes.
            while (sequence < firstSteamSequence!!) {
                sequence = getBlockingRestSequence().sequence
            }

            previousSequence = it.sequence

            // 5. Discard all socket messages where the sequence number
            // is less than or equal to the Sequence header retrieved
            // from the REST call.
            return@skipWhile sequence < it.sequence
        }
    }
}