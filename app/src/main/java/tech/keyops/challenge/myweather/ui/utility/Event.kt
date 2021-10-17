package tech.keyops.challenge.myweather.ui.utility

import androidx.lifecycle.Observer

/**
 * This class is used as a data wrapper and a means of communication
 * between the ViewModel and the View.
 */
open class Event<out T>(private val content: T) {

    private var hasBeenDispatched = false

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotDispatchedOrReturnNull(): T? {
        return if (hasBeenDispatched) {
            null
        } else {
            hasBeenDispatched = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotDispatchedOrReturnNull()?.let {
            onEventUnhandledContent(it)
        }
    }
}