package com.capra.five

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.supervisorScope

/**
 * At which line is the exception thrown ?
 */
suspend fun main() {
    supervisorScope {
        val result1Job = async {
            delay(500)
            1
        }

        val result2Job = async<Int> {
            delay(100)
            throw IllegalStateException("Failed")
        }

        println("Awaiting results..")
        delay(1_000)

        println("Results should be ready now!")
        val result1 = result1Job.await()
        println("result1 is $result1")

        val result2 = result2Job.await()
        println("result2 is $result2")

        println("Sum is ${result1 + result2}!")
    }
}