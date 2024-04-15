package com.capra.four

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Is "Done" printed ?
 */
suspend fun main() {
    coroutineScope {
        launch {
            delay(1_000)
            println("Done")
        }

        launch {
            delay(100)
            throw MyCoolException
        }
    }
}


object MyCoolException : CancellationException()