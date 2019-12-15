package com.vladco.fudo.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopFood")
data class ShopFood(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_shopFood") val id: Int? = null,
    val name: String,
    val isBought: Boolean = false
)