package com.anabell.words

import android.app.Application
import com.anabell.words.di.Module
import com.anabell.words.di.factory.ViewModelFactory


class MyApplication : Application() {

    private lateinit var module: Module
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()

        module = Module(this)
        viewModelFactory = ViewModelFactory(module)

    }
}