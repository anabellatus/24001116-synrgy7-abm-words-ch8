package com.anabell.words.data.repository

import com.anabell.words.data.datasource.local.AuthLocalDataSourceImpl
import com.anabell.words.data.datasource.remote.AuthRemoteDataSourceImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class AuthRepositoryImplTest {

    //given
    private val remoteDataSource = mock<AuthRemoteDataSourceImpl>()
    private val localDataSource = mock<AuthLocalDataSourceImpl>()

    private val repository = AuthRepositoryImpl(
        authLocalDataSource = localDataSource,
        authRemoteDataSource = remoteDataSource
    )

    @Test
    fun login() = runTest {
        //given
        val email = "anabellatus@gmail.com"
        val password = "hello123"
        val token = "fsduadgbdoahsaknsaknso739y7dgshdjsbcw9"

        //when
        whenever(remoteDataSource.login(email, password)).thenReturn(token)
        val expected = "fsduadgbdoahsaknsaknso739y7dgshdjsbcw9"
        val actual = repository.login(email, password)

        //then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun saveToken() = runTest {
        //given
        val token = "fsduadgbdoahsaknsaknso739y7dgshdjsbcw9"

        //when
        whenever(localDataSource.saveToken(token)).thenReturn(Unit)
        val actual = repository.saveToken(token)

        //then
        Assert.assertEquals(Unit, actual)
    }

    @Test
    fun loadToken() = runTest {
        //given
        val token = "fsduadgbdoahsaknsaknso739y7dgshdjsbcw9"

        //when
        whenever(localDataSource.loadToken()).thenReturn(token)
        val expected = "fsduadgbdoahsaknsaknso739y7dgshdjsbcw9"
        val actual = repository.loadToken()

        //then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun clearToken() = runTest {
        //when
        whenever(localDataSource.clearToken()).thenReturn(Unit)
        val actual = repository.clearToken()

        //then
        Assert.assertEquals(Unit, actual)

    }

    @Test
    fun saveUserEmail() = runTest {
        //given
        val email = "anabellatus@gmail.com"

        //when
        whenever(localDataSource.saveUserEmail(email)).thenReturn(Unit)
        val actual = repository.saveUserEmail(email)

        //then
        Assert.assertEquals(Unit, actual)
    }

    @Test
    fun loadUserEmail() = runTest {
        //given
        val email = "anabellatus@gmail.com"

        //when
        whenever(localDataSource.loadUserEmail()).thenReturn(email)
        val expected = "anabellatus@gmail.com"
        val actual = repository.loadUserEmail()

        //then
        Assert.assertEquals(expected, actual)

    }
}