package com.anabell.words.data

import com.anabell.words.data.datasource.local.room.GadgetEntity

interface GadgetLocalDataSource {

    suspend fun insertGadget(gadgetEntity: GadgetEntity)

    suspend fun deleteGadget(gadgetEntity: GadgetEntity)

    suspend fun selectGadgetById(id: Int): GadgetEntity?

    suspend fun selectAllGadgets(): List<GadgetEntity>

    suspend fun isFavorite(id: Int): Boolean
}