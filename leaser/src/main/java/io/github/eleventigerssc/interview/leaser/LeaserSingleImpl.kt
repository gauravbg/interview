package io.github.eleventigerssc.interview.leaser

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.withTimeoutOrNull
import java.util.concurrent.TimeUnit


class LeaserSingleImpl<T>(provider: () -> T): Leaser<T> {

    private val lock = Semaphore(1)
    private val lease = LeaseImpl<T>(provider) {
        lock.release()
    }

    override fun tryAcquireWithTimeout(timeout: Long, timeUnit: TimeUnit): Leaser.Lease<T>? {
        return runBlocking {
            withTimeoutOrNull(TimeUnit.MILLISECONDS.convert(timeout, timeUnit)) {
                lock.acquire()
                lease.acquire()
                lease
            }
        }
    }
}