package com.pegasus.rickverse.util

import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
    return try {
        Resource.Success(apiCall())
    } catch (e: IOException) {
        Resource.Error("Network error", e)
    } catch (e: HttpException) {
        Resource.Error("Server error", e)
    } catch (e: Exception) {
        Resource.Error("Unexpected error", e)
    }
}
