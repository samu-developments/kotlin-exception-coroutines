package com.capra.one

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Hvilken rekkefølge printes?
 * (n) // n indikerer println #n
 */
suspend fun main() {
    println("Hello (1)") // 1
    coroutineScope {
        launch {
            println("World (2)") // 2
        }
        println("End of scope (3)") // 3
    }
    println("After scope (4)") // 4
}