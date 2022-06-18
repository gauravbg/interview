package io.github.eleventigerssc.interview.leaser

class LeaseImpl<T>(private val provider: () -> T, private val closed: () -> Unit): Leaser.Lease<T> {
    override val value: T
        get() = provider.invoke()

    override fun close() {
        closed()
    }
}
