package com.atm.skymessageapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.atm.skymessageapp.data.database.entities.MessageEntity

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: MessageEntity)

    @Query("select * from tb_messages")
    suspend fun getList(): List<MessageEntity>

    @Update
    suspend fun update(user: MessageEntity)

    @Delete
    suspend fun delete(user: MessageEntity)
}