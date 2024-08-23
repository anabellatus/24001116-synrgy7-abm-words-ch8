package com.anabell.words.domain

import com.anabell.words.data.model.Gadget

interface GadgetRepository {

    fun fetchData(): List<Gadget>

    suspend fun addFavorite(gadget: Gadget)

    suspend fun loadGadget(): List<Gadget>

    suspend fun deleteGadget(gadget: Gadget)

    suspend fun loadGadgetById(id: Int): Gadget
}