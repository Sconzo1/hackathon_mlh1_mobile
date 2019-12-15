package com.vladco.fudo.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vladco.fudo.model.data.ShopFood
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ShopFoodDao {

    @Insert
    fun insertShopFood(shopFood: ShopFood): Completable

    @Query("SELECT * FROM shopFood")
    fun getAllShopFood(): Flowable<List<ShopFood>>


}