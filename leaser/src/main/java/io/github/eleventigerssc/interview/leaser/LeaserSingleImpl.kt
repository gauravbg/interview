package io.github.eleventigerssc.interview.leaser

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock


class LeaserSingleImpl<T>(provider: () -> T): Leaser<T> {

    private val lock = ReentrantLock()
    private val lease = LeaseImpl<T>(provider) {
        lock.unlock()
    }

    override fun tryAcquireWithTimeout(timeout: Long, timeUnit: TimeUnit): Leaser.Lease<T>? {
        return lock.withLock {
            lease
        }
    }
}