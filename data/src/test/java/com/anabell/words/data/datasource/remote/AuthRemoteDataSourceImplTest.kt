package com.anabell.words.data.datasource.remote

import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class AuthRemoteDataSourceImplTest {

    //given
    private val dataSource = AuthRemoteDataSourceImpl()

    @Test
    fun login() = runTest {
        //given
        val email = "anabellatus@gmail.com"
        val password = "hello123"

        //when
        val expected = "fsduadgbdoahsaknsaknso739y7dgshdjsbcw9"
        val actual = dataSource.login(email, password)

        //then
        Assert.assertEquals(expected, actual)
    }
}