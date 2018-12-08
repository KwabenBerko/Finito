package com.kwabenaberko.finito.viewmodel.util

import com.kwabenaberko.finito.ContextDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.CoroutineContext

class TestContextDispatchers : ContextDispatchers() {
    @ExperimentalCoroutinesApi
    override val IO: CoroutineContext
        get() = Dispatchers.Unconfined

    @ExperimentalCoroutinesApi
    override val Main: CoroutineContext
        get() = Dispatchers.Unconfined
}