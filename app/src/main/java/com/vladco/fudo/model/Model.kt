package com.vladco.fudo.model

import android.util.Log
import com.vladco.fudo.model.dao.FoodDao
import com.vladco.fudo.model.dao.ShopFoodDao
import com.vladco.fudo.model.data.Food
import com.vladco.fudo.model.data.ShopFood
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.LocalDate

class Model {

    private val barcodeStorage = BarcodeStorage

    fun insertShopFood(shopFoodDao: ShopFoodDao, shopFood: ShopFood): Disposable {
        return shopFoodDao.insertShopFood(shopFood)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                },
                {
                }
            )
    }


    fun insertFood(foodDao: FoodDao, food: Food): Disposable {
        return foodDao.insertFood(food)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("Fudo_appTag", "good ${food.toString()}")
                },
                {
                    Log.d("Fudo_appTag", "f: ${it.localizedMessage}")
                }
            )
    }

    fun getAllFood(foodDao: FoodDao): Flowable<List<Food>> {
        return foodDao.getAllFood()
    }


    fun getAllShopFood(shopFoodDao: ShopFoodDao): Flowable<List<ShopFood>> {
        return shopFoodDao.getAllShopFood()
    }

    fun deleteFood(foodDao: FoodDao, food: Food): Disposable {
        return foodDao.deleteFood(food)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("Fudo_appTag", "ready Delete: ${food.toString()}")
            }, {
                Log.d("Fudo_appTag", it.localizedMessage)
            })
    }


    fun saveBarcode(barcode: String) {
        barcodeStorage.setBarcode(barcode)
    }

    fun getBarcode(): String? = barcodeStorage.getBarcode()

    fun clearBarcode() {
        barcodeStorage.clear()
    }

    fun saveDate(date: LocalDate) {
        barcodeStorage.setDate(date)
    }

    fun getDate(): LocalDate? = barcodeStorage.getDate()

    fun clearDate() {
        barcodeStorage.clear()
    }

}