package com.anabell.words.data

import com.anabell.words.domain.model.CategoryGadget
import com.anabell.words.domain.model.Gadget

interface GadgetRemoteDataSource {

    suspend fun fetchGadgetData(): List<Gadget>

    suspend fun fetchGadgetCategoryData(): List<CategoryGadget>
}