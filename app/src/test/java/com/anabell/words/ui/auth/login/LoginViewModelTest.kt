package com.anabell.words.ui.auth.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.anabell.words.MainDispatcherRule
import com.anabell.words.domain.repository.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    //given
    private val authRepository = mock<AuthRepository>()
    private val viewModel = LoginViewModel(
        authRepository = authRepository
    )

    private val loadingObserver = mock<Observer<Boolean>>()
    private val successObserver = mock<Observer<Boolean>>()
    private val errorObserver = mock<Observer<Throwable>>()

    private val loadingCaptor = argumentCaptor<Boolean>()
    private val successCaptor = argumentCaptor<Boolean>()
    private val errorCaptor = argumentCaptor<Throwable>()

    @Test
    fun loginSuccess() = runTest {
        // given
        val email = "email"
        val password = "password"

        val loadingLiveData = viewModel.loading
        val successLiveData = viewModel.success
        val errorLiveData = viewModel.error

        loadingLiveData.observeForever(loadingObserver)
        successLiveData.observeForever(successObserver)
        errorLiveData.observeForever(errorObserver)

        // when
        whenever(authRepository.login(email, password)).thenReturn(String())
        viewModel.login(email, password)

        //then
        verify(loadingObserver, times(2)).onChanged(loadingCaptor.capture())
        verify(successObserver).onChanged(successCaptor.capture())

        Assert.assertEquals(loadingCaptor.allValues, listOf(true, false))
        Assert.assertEquals(successCaptor.firstValue, true)

    }

    @Test
    fun loginError() = runTest {
        // given
        val email = "email"
        val password = "password"

        val loadingLiveData = viewModel.loading
        val successLiveData = viewModel.success
        val errorLiveData = viewModel.error

        loadingLiveData.observeForever(loadingObserver)
        successLiveData.observeForever(successObserver)
        errorLiveData.observeForever(errorObserver)

        //when
        val throwable = RuntimeException("error")
        whenever(authRepository.login(email, password)).thenThrow(throwable)
        viewModel.login(email, password)

        //then
        verify(loadingObserver, times(2)).onChanged(loadingCaptor.capture())
        verify(errorObserver).onChanged(errorCaptor.capture())

        Assert.assertEquals(loadingCaptor.allValues, listOf(true, false))
        Assert.assertEquals(errorCaptor.firstValue.message, "error")
    }

}