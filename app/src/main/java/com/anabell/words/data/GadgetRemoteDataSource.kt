package com.anabell.words.data

import com.anabell.words.data.model.CategoryGadget
import com.anabell.words.data.model.Gadget

interface GadgetRemoteDataSource {

    suspend fun fetchGadgetData(): List<Gadget>

    suspend fun fetchGadgetCategoryData(): List<CategoryGadget>
}