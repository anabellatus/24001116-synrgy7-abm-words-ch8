package com.anabell.words.data.datasource.remote.retrofit

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://my-json-server.typicode.com/anabellatus/db/")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
}

fun provideGadgetService(): GadgetService {
    return provideRetrofit().create(GadgetService::class.java)
}