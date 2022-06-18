package io.github.eleventigerssc.interview.leaser

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class LeaserMultipleImpl<T>(private val provider: ()-> T): Leaser<T> {
    override fun tryAcquireWithTimeout(timeout: Long, timeUnit: TimeUnit): Leaser.Lease<T>? {
        TODO("Not yet implemented")
    }
}