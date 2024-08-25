package com.anabell.words.data.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        GadgetEntity::class
    ],
    version = 1
)
abstract class RoomDatabase : RoomDatabase() {

    companion object {
        const val NAME = "room_database"
    }

    abstract fun gadgetDao(): GadgetDao
}