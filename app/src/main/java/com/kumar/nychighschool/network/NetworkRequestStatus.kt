package com.kumar.nychighschool.network

sealed class NetworkRequestStatus {
    class Successed<T>(val data: T) : NetworkRequestStatus()
    class Failed<T>(val message: String, val data: T) : NetworkRequestStatus()
    object Loading : NetworkRequestStatus()
}