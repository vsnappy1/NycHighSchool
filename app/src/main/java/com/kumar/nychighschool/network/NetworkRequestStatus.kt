package com.kumar.nychighschool.network

sealed class NetworkRequestStatus<T>(val data: T? = null) {
    class Succeed<T>(val value: T) : NetworkRequestStatus<T>(value)
    class Failed<T>(val message: String, val value: T) : NetworkRequestStatus<T>(value)
    class Loading<T> : NetworkRequestStatus<T>()
}