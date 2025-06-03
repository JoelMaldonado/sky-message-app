package com.atm.skymessageapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_messages")
data class MessageEntity(
    @PrimaryKey val id: Int,
    val name: String
)
