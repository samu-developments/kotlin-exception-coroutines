package com.capra.three

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() {
    coroutineScope {
        launch {
            delay(2_000)
            println("World (1)") // 1
        }

        val job2 = launch {
            try {
                delay(1_000)
            } catch (e: Exception) {
                println("Oops $e")
            }
            println("Hello (2)") // 2
        }

        job2.cancel()
        println("End of scope (3)") // 3
    }
    println("After scope (4)") // 4
}
