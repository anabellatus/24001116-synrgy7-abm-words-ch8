@file:Suppress("SameReturnValue", "SameReturnValue")

package com.anabell.words.data

interface AuthRemoteDataSource {

    fun login(email: String, password: String): String

    fun register(name: String, email: String, password: String): String

}