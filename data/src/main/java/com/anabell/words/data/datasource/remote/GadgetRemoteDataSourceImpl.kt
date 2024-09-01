package com.anabell.words.data.datasource.remote

import com.anabell.words.data.GadgetRemoteDataSource
import com.anabell.words.data.datasource.remote.retrofit.GadgetService
import com.anabell.words.domain.model.CategoryGadget
import com.anabell.words.domain.model.Gadget

class GadgetRemoteDataSourceImpl(
    private val gadgetService: GadgetService,
) : GadgetRemoteDataSource {
    override suspend fun fetchGadgetData(): List<Gadget> {
        return gadgetService.getGadgets()
    }

    override suspend fun fetchGadgetCategoryData(): List<CategoryGadget> {
        return gadgetService.getCategories()
    }
}