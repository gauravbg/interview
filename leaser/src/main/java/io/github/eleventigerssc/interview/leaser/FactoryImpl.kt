package io.github.eleventigerssc.interview.leaser

import com.google.auto.service.AutoService

@AutoService(Leaser.Factory::class)
class FactoryImpl: Leaser.Factory {

    override fun <T> exclusiveFor(provider: () -> T): Leaser<T> {
        return LeaserSingleImpl<T>(provider)
    }

    override fun <T> refCountedFor(provider: () -> T): Leaser<T> {
        return LeaserMultipleImpl<T>(provider)
    }
}