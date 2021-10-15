package tech.keyops.challenge.myweather.ui.utility

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