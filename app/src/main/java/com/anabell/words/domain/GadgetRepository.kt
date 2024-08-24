package com.anabell.words.domain

import com.anabell.words.data.model.CategoryGadget
import com.anabell.words.data.model.Gadget

interface GadgetRepository {

    suspend fun fetchGadgetData(): List<Gadget>

    suspend fun fetchGadgetCategoryData(): List<CategoryGadget>

    suspend fun addFavorite(gadget: Gadget)

    suspend fun loadGadget(): List<Gadget>

    suspend fun deleteGadget(gadget: Gadget)

    suspend fun loadGadgetById(id: Int): Gadget?

    suspend fun isFavorite(id: Int): Boolean
}