package models

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

//Make the Observable look more like a promise.
typealias AdapterObservable<T> = Observable<T>
fun <T> AdapterObservable<T>.onFailure(callback: (ResponseException) -> Unit): AdapterObservable<T> {
    return this.doOnError {
        callback(it as ResponseException)
    }
}

fun <T> AdapterObservable<T>.onSuccess(callback: (T) -> Unit): Disposable {
    return this.subscribe {
        callback(it)
    }
}

