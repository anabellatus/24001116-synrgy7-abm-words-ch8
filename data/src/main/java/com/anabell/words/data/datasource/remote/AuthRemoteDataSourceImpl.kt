package com.anabell.words.data.datasource.remote

import com.anabell.words.data.AuthRemoteDataSource

class AuthRemoteDataSourceImpl : AuthRemoteDataSource {

    override fun login(email: String, password: String): String {

        if (email == "anabellatus@gmail.com" && password == "hello123") {
            return "fsduadgbdoahsaknsaknso739y7dgshdjsbcw9"
        } else {
            throw UnsupportedOperationException("User tidak ada")
        }
    }

    override fun register(name: String, email: String, password: String): String {
        return "jpojjdjbdkjanxao739y7dgsh9wb9y29ce2n"
    }

}