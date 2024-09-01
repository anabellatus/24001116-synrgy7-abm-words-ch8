package com.anabell.words.data.datasource.remote.retrofit

import android.content.Context
import com.google.gson.Gson
import com.anabell.words.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.chuckerteam.chucker.api.ChuckerInterceptor

private fun provideRetrofit(context: Context): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL_GADGET)
        .client(provideOkhttpClient(context))
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
}

private fun provideOkhttpClient(context: Context): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        })
        .addInterceptor(ChuckerInterceptor.Builder(context).build())
        .build()
}

fun provideGadgetService(context: Context): GadgetService {
    return provideRetrofit(context).create(GadgetService::class.java)
}