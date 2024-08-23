package com.anabell.words.data

import com.anabell.words.data.model.CategoryGadget
import com.anabell.words.data.model.Gadget

interface GadgetRemoteDataSource {

    fun fetchGadgetData(): List<Gadget>

    fun fetchGadgetCategoryData(): List<CategoryGadget>
}