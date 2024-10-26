package com.org.photoplay.data.repository

sealed class NetworkState<out T> {
    data object Loading : NetworkState<Nothing>()
    data class Success<out T>(val data: T) : NetworkState<T>()
    data class Error(val exception: Exception) : NetworkState<Nothing>()
}