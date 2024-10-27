package com.org.photoplay.utils

import com.org.photoplay.data.repository.NetworkState

fun isLoading(vararg states: NetworkState<*>): Boolean {
    return states.any { it is NetworkState.Loading }
}