package io.github.eleventigerssc.interview.leaser

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class LeaserMultipleImpl<T>(provider: ()-> T): Leaser<T> {
    private var leaseCounter = 0
    private val lock = ReentrantLock()
    private val lease = LeaseImpl(provider) {
        lock.withLock {
            leaseCounter--
        }
    }

    override fun tryAcquireWithTimeout(timeout: Long, timeUnit: TimeUnit): Leaser.Lease<T>? {
        return lock.withLock {
            if (leaseCounter == 0) {
                leaseCounter++
                lease.acquire()
                lease
            } else {
                leaseCounter++
                lease
            }
        }
    }
}