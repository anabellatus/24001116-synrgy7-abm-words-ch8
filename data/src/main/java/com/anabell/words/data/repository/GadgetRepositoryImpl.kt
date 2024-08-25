package com.anabell.words.data.repository

import com.anabell.words.data.GadgetLocalDataSource
import com.anabell.words.data.GadgetRemoteDataSource
import com.anabell.words.domain.model.CategoryGadget
import com.anabell.words.domain.model.Gadget
import com.anabell.words.data.mapper.toGadget
import com.anabell.words.data.mapper.toGadgetEntity
import com.anabell.words.domain.repository.GadgetRepository

class GadgetRepositoryImpl(
    private val remoteDataSource: GadgetRemoteDataSource,
    private val localDataSource: com.anabell.words.data.GadgetLocalDataSource
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