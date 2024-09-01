package com.anabell.words.di

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
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val koinModule = module {
    single<AuthRepository> { AuthRepositoryImpl( authLocalDataSource = get(), authRemoteDataSource = get()) }

    single<GadgetRepository> { GadgetRepositoryImpl( remoteDataSource = get(), localDataSource = get()) }

    single<GadgetLocalDataSource> { GadgetLocalDataSourceImpl(gadgetDao = get()) }

    single<GadgetDao> { (get() as RoomDatabase).gadgetDao() }

    single<RoomDatabase> { Room.databaseBuilder( context = get(), name = RoomDatabase.NAME, klass = RoomDatabase::class.java ).build() }

    single<GadgetRemoteDataSource> { GadgetRemoteDataSourceImpl(gadgetService = get()) }

    single<GadgetService> { provideGadgetService(context = get()) }

    single<AuthLocalDataSource> { AuthLocalDataSourceImpl(dataStore = get()) }

    single<DataStore<Preferences>> { androidContext().dataStore }

    single<AuthRemoteDataSource> { AuthRemoteDataSourceImpl() }
}