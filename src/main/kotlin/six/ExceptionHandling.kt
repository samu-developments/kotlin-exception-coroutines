package com.capra.six

import kotlinx.coroutines.delay
import kotlinx.coroutines.supervisorScope
import kotlin.random.Random

suspend fun main() = supervisorScope {
    val client = getClient()

    val response = client.getData()

    val data = when (response) {
        is ApiResponse.Success -> response.body
        is ApiResponse.Error -> {
            // Handle error: throw new exception / retry / return empty data..
            throw IllegalStateException("Could not get data")
        }
    }

    println("Got data: $data")
}

fun getClient() = object : Api {
    override suspend fun getData(): ApiResponse<Data> {
        return safeRequest {
            delay(100)
            if (Random.nextInt() % 2 == 0) {
                Data(data = listOf("Some", "data", "for", "you"))
            } else {
                if (Random.nextInt() % 2 == 0) throw IllegalStateException()
                else throw IllegalArgumentException()
            }
        }
    }

    private suspend inline fun <reified T> safeRequest(block: suspend () -> T): ApiResponse<T> =
        try {
            val response = block()
            ApiResponse.Success(response)
        } catch (e: IllegalStateException) {
            ApiResponse.Error.HttpError(code = 200, errorBody = "HttpError", exception = e)
        } catch (e: IllegalArgumentException) {
            ApiResponse.Error.SerializationError(exception = e)
        }
}

interface Api {
    suspend fun getData(): ApiResponse<Data>
}

data class Data(val data: List<String>)

sealed class ApiResponse<out T> {

    data class Success<T>(val body: T) : ApiResponse<T>()

    sealed class Error(val exception: Exception) : ApiResponse<Nothing>() {

        class HttpError(val code: Int, val errorBody: String?, exception: Exception) : Error(exception)
        class SerializationError(exception: Exception) : Error(exception)
    }
}