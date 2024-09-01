@file:Suppress("SpellCheckingInspection")

package com.anabell.words.data.datasource.remote

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.anabell.words.data.datasource.local.AuthLocalDataSourceImpl
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@Suppress("SpellCheckingInspection")
class AuthLocalDataSourceImplTest {

    //given
    private val dataStore = mock<DataStore<Preferences>>()
    private val dataSource = AuthLocalDataSourceImpl(
        dataStore = dataStore
    )

    @Test
    fun saveToken() = runTest {
        //given
        val token = "fsduadgbdoahsaknsaknso739y7dgshdjsbcw9"

        //when
        whenever(dataSource.saveToken(token)).thenReturn(Unit)
        val actual = dataSource.saveToken(token)

        //then
        Assert.assertEquals(Unit, actual)
    }

    @Test
    fun loadToken() = runTest {
        //given
        val token = "fsduadgbdoahsaknsaknso739y7dgshdjsbcw9"
        val preferences = mock<Preferences> {
            on { get(stringPreferencesKey("token")) }.thenReturn(token)
        }

        //when
        whenever(dataStore.data).thenReturn(flowOf(preferences))
        whenever(dataSource.loadToken()).thenReturn(token)
        val expected = "fsduadgbdoahsaknsaknso739y7dgshdjsbcw9"
        val actual = dataSource.loadToken()

        //then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun clearToken() = runTest {

        //when
        whenever(dataSource.clearToken()).thenReturn(Unit)
        val actual = dataSource.clearToken()

        //then
        Assert.assertEquals(Unit, actual)

    }

    @Test
    fun saveUserEmail() = runTest {
        //given
        val email = "anabellatus@gmail.com"

        //when
        whenever(dataSource.saveUserEmail(email)).thenReturn(Unit)
        val actual = dataSource.saveUserEmail(email)

        //then
        Assert.assertEquals(Unit, actual)
    }

    @Test
    fun loadUserEmail() = runTest {
        //given
        val email = "anabellatus@gmail.com"
        val preferences = mock<Preferences> {
            on { get(stringPreferencesKey("email")) }.thenReturn(email)
        }

        //when
        whenever(dataStore.data).thenReturn(flowOf(preferences))
        whenever(dataSource.loadUserEmail()).thenReturn(email)
        val expected = "anabellatus@gmail.com"
        val actual = dataSource.loadUserEmail()

        //then
        Assert.assertEquals(expected, actual)

    }
}