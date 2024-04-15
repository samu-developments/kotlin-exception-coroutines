package com.capra.one

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Hvilken rekkefølge printes?
 */
suspend fun main() {
    coroutineScope {
        launch {
            delay(2_000)
            println("World (1)") // 1
        }

        launch {
            delay(1_000)
            println("Hello (2)") // 2
        }
        println("End of scope (3)") // 3
    }
    println("After scope (4)") // 4
}