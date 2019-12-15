package com.vladco.fudo.model.dao

import androidx.room.*
import com.vladco.fudo.model.data.Food
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FoodDao {

    @Insert
    fun insertFood(food: Food): Completable

    @Query("SELECT * FROM food")
    fun getAllFood(): Flowable<List<Food>>

    @Transaction
    @Delete
    fun deleteFood(food: Food): Completable

}