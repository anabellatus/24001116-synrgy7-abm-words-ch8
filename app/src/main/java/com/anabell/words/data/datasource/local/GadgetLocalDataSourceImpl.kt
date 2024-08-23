package com.anabell.words.data.datasource.local

import com.anabell.words.data.GadgetLocalDataSource
import com.anabell.words.data.datasource.local.room.GadgetDao
import com.anabell.words.data.datasource.local.room.GadgetEntity

class GadgetLocalDataSourceImpl(
    private val gadgetDao: GadgetDao,
): GadgetLocalDataSource {
    override suspend fun insertGadget(gadgetEntity: GadgetEntity) {
        gadgetDao.insertGadget(gadgetEntity)
    }

    override suspend fun deleteGadget(gadgetEntity: GadgetEntity) {
        gadgetDao.deleteGadget(gadgetEntity)
    }

    override suspend fun selectGadgetById(id: Int): GadgetEntity? {
        return gadgetDao.selectGadgetById(id)
    }

    override suspend fun selectAllGadgets(): List<GadgetEntity> {
        return gadgetDao.selectAllGadgets()
    }
}