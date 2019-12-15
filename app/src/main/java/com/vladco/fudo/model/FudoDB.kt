package com.vladco.fudo.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vladco.fudo.model.dao.FoodDao
import com.vladco.fudo.model.dao.ShopFoodDao
import com.vladco.fudo.model.data.Food
import com.vladco.fudo.model.data.ShopFood

@Database(entities = [Food::class, ShopFood::class], version = 1, exportSchema = false)
abstract class FudoDB : RoomDatabase() {

    abstract fun foodDao(): FoodDao

    abstract fun shopFoodDao(): ShopFoodDao

    companion object {

        @Volatile
        private var INSTANCE: FudoDB? = null

        fun getInstance(context: Context): FudoDB {
            return INSTANCE
                ?: synchronized(this) {
                    buildDatabase(
                        context
                    ).also { INSTANCE = it }
                }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FudoDB::class.java, "fudo.db"
        ).build()


    }

}