package io.github.eleventigerssc.interview.leaser

class LeaseImpl<T>(private val provider: () -> T, private val closed: () -> Unit): Leaser.Lease<T> {
    private var _value: T? = null
    override val value: T
        get() {
            return _value ?: throw IllegalStateException("WTH?")
        }

    override fun close() {
        closed()
    }

    fun acquire() {
        _value = provider.invoke()
    }
}
