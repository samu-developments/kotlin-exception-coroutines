package com.capra.three

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

suspend fun main() {
    // Imagined server scope
    val scope = CoroutineScope(coroutineContext + SupervisorJob())

    scope.launch {
        println("In first launch (1)")

        launch {
            println("In second launch (2)")
            val updatedRows = doSomeDatabaseWork()
            println("Updated $updatedRows rows! (3)") // 2
        }
        println("End of scope (4)") // 3
    }

    // Server shutdown
    scope.cancel()
    println("After scope (5)") // 4

    // We need to make sure all code has run
    delay(1_000)
}

suspend fun doSomeDatabaseWork(): Int {
    val result = runCatching {
        println("Doing db work.. (6)")
        delay(500)
        println("Db work done! (7)")
        1
    }.getOrNull()
    println("Returning result.. (8)")
    return result ?: -1
}