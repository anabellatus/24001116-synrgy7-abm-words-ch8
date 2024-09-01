package com.anabell.words.ui.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.anabell.words.MainDispatcherRule
import com.anabell.words.domain.model.CategoryGadget
import com.anabell.words.domain.repository.GadgetRepository
import com.anabell.words.ui.fragment.list.ListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    //given
    private val gadgetRepository = mock<GadgetRepository>()
    private val viewModel = ListViewModel(
        gadgetRepository = gadgetRepository
    )

    private val categoryObserver = mock<Observer<List<CategoryGadget>>>()

    private val categoryCaptor = argumentCaptor<List<CategoryGadget>>()

    @Test
    fun retrieveCategoryData() = runTest {
        //given
        val expected = listOf(
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/3437/3437364.png",
                name = "Smartphone",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/2888/2888704.png",
                name = "Laptop",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/564/564579.png",
                name = "Tablet",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/5906/5906114.png",
                name = "Earphone",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/2668/2668914.png",
                name = "Camera",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/8462/8462356.png",
                name = "Speaker",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/617/617694.png",
                name = "Smartwatch",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/771/771261.png",
                name = "Game Console",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/5606/5606227.png",
                name = "TV",
            ),
            CategoryGadget(
                icon = "https://cdn-icons-png.flaticon.com/512/6212/6212141.png",
                name = "Tools",
            ),
        )
        viewModel.categories.observeForever(categoryObserver)

        //when
        whenever(gadgetRepository.fetchGadgetCategoryData()).thenReturn(expected)
        viewModel.retrieveCategoryData()

        //then
        verify(categoryObserver).onChanged(categoryCaptor.capture())
        Assert.assertEquals(expected, categoryCaptor.firstValue)

    }
}