package com.anabell.words.data.datasource.remote.retrofit

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://my-json-server.typicode.com/anabellatus/db/")
        .client(provideOkhttpClient())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
}

private fun provideOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        })
        .build()
}

fun provideGadgetService(): GadgetService {
    return provideRetrofit().create(GadgetService::class.java)
}