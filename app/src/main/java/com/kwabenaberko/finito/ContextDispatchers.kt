package com.kwabenaberko.finito

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class ContextDispatchers(
        open val IO: CoroutineContext = Dispatchers.IO,
        open val Main: CoroutineContext = Dispatchers.Main
)