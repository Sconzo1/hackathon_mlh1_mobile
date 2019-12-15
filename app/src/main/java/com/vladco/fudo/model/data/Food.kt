package com.vladco.fudo.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_food") val id: Int? = null,
    val name: String,
    val date: String,
    val color: String
)