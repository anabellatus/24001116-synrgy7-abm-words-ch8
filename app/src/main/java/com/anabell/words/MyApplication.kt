package com.anabell.words

import android.app.Application
import com.anabell.words.di.factory.viewModelModule
import com.anabell.words.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(koinModule, viewModelModule)
        }

    }
}