package com.anabell.words.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GadgetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGadget(gadgetEntity: GadgetEntity)

    @Delete
    suspend fun deleteGadget(gadgetEntity: GadgetEntity)

    @Query("SELECT * FROM gadget WHERE id = :id")
    suspend fun selectGadgetById(id: Int): GadgetEntity?

    @Query("SELECT * FROM gadget")
    suspend fun selectAllGadgets(): List<GadgetEntity>
}