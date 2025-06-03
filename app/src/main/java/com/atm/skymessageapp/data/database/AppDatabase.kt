package com.atm.skymessageapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.atm.skymessageapp.data.database.dao.MessageDao
import com.atm.skymessageapp.data.database.entities.MessageEntity

@Database(
    entities = [MessageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun messageDao(): MessageDao
}