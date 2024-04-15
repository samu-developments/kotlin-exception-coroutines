package com.capra.four

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

/**
 * What is the result ?
 */
suspend fun main() {
    withTimeout(500) {
        println("Starting potentially long work..")
        launch {
            val result = runCatching {
                delay(1_000)
                println("Done")
                1
            }.getOrNull() ?: -1

            println("Result is... $result")
        }
    }
}