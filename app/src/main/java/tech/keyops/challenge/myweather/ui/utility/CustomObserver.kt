package tech.keyops.challenge.myweather.ui.utility

import android.util.Log
import io.reactivex.observers.DisposableObserver
import tech.keyops.challenge.myweather.BuildConfig
import tech.keyops.challenge.myweather.ui.utility.CustomObserver

abstract class CustomObserver<T> : DisposableObserver<T>() {
    protected abstract fun onSuccess(data: T)
    protected abstract fun onFailure(e: Throwable)

    companion object {
        private val LOG_TAG = CustomObserver::class.java.simpleName
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        onFailure(e)

        if (BuildConfig.DEBUG) {
            Log.e(LOG_TAG, e.message, e)
        }
    }

    override fun onComplete() {}
}