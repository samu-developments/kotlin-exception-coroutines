package com.capra.four

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

/**
 * Is "Done" printed ?
 */
suspend fun main() {
    supervisorScope {
        launch {
            delay(1_000)
            println("Done")
        }

        launch {
            delay(100)
            throw IllegalStateException("Oops some error")
        }
    }
}