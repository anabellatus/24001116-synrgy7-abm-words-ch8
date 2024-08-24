package com.anabell.words.data.repository

import com.anabell.words.data.GadgetLocalDataSource
import com.anabell.words.data.GadgetRemoteDataSource
import com.anabell.words.data.model.CategoryGadget
import com.anabell.words.data.model.Gadget
import com.anabell.words.data.model.mapper.toGadget
import com.anabell.words.data.model.mapper.toGadgetEntity
import com.anabell.words.domain.GadgetRepository

class GadgetRepositoryImpl(
    private val remoteDataSource: GadgetRemoteDataSource,
    private val localDataSource: GadgetLocalDataSource
) : GadgetRepository {

    override suspend fun fetchGadgetData(): List<Gadget> {
        return remoteDataSource.fetchGadgetData()
    }

    override suspend fun fetchGadgetCategoryData(): List<CategoryGadget> {
        return remoteDataSource.fetchGadgetCategoryData()
    }

    override suspend fun addFavorite(gadget: Gadget) {
        localDataSource.insertGadget(gadget.toGadgetEntity())
    }

    override suspend fun loadGadget(): List<Gadget> {
        return localDataSource.selectAllGadgets().toGadget()
    }

    override suspend fun deleteGadget(gadget: Gadget) {
        localDataSource.deleteGadget(gadget.toGadgetEntity())
    }

    override suspend fun loadGadgetById(id: Int): Gadget? {
        return localDataSource.selectGadgetById(id)?.toGadget()
    }

    override suspend fun isFavorite(id: Int): Boolean {
        return localDataSource.isFavorite(id)
    }
}