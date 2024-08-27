package com.anabell.words.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.anabell.words.data.AuthLocalDataSource
import com.anabell.words.data.AuthRemoteDataSource
import com.anabell.words.data.GadgetLocalDataSource
import com.anabell.words.data.GadgetRemoteDataSource
import com.anabell.words.data.datasource.local.AuthLocalDataSourceImpl
import com.anabell.words.data.datasource.local.GadgetLocalDataSourceImpl
import com.anabell.words.data.datasource.local.dataStore
import com.anabell.words.data.datasource.local.room.GadgetDao
import com.anabell.words.data.datasource.local.room.RoomDatabase
import com.anabell.words.data.datasource.remote.AuthRemoteDataSourceImpl
import com.anabell.words.data.datasource.remote.GadgetRemoteDataSourceImpl
import com.anabell.words.data.datasource.remote.retrofit.GadgetService
import com.anabell.words.data.datasource.remote.retrofit.provideGadgetService
import com.anabell.words.data.repository.AuthRepositoryImpl
import com.anabell.words.data.repository.GadgetRepositoryImpl
import com.anabell.words.domain.repository.AuthRepository
import com.anabell.words.domain.repository.GadgetRepository

class Module(context: Context) {

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(
            authLocalDataSource = authLocalDataSource,
            authRemoteDataSource = authRemoteDataSource,
        )
    }

    val gadgetRepository: GadgetRepository by lazy {
        GadgetRepositoryImpl(
            remoteDataSource = gadgetRemoteDataSource,
            localDataSource = gadgetLocalDataSource,
        )
    }

    private val gadgetLocalDataSource: GadgetLocalDataSource by lazy {
        GadgetLocalDataSourceImpl(
            gadgetDao = gadgetDao,
        )
    }

    private val gadgetDao: GadgetDao by lazy {
        roomDatabase.gadgetDao()
    }

    private val roomDatabase: RoomDatabase by lazy {
        Room.databaseBuilder(
            context = context,
            name = RoomDatabase.NAME,
            klass = RoomDatabase::class.java
        ).build()
    }

    private val gadgetRemoteDataSource: GadgetRemoteDataSource by lazy {
        GadgetRemoteDataSourceImpl(
            gadgetService = gadgetService,
        )
    }

    private val gadgetService: GadgetService by lazy {
        provideGadgetService()
    }

    private val authLocalDataSource: AuthLocalDataSource by lazy {
        AuthLocalDataSourceImpl(
            dataStore = dataStore
        )
    }

    private val dataStore: DataStore<Preferences> by lazy {
        context.dataStore
    }

    private val authRemoteDataSource: AuthRemoteDataSource by lazy {
        AuthRemoteDataSourceImpl()
    }

}