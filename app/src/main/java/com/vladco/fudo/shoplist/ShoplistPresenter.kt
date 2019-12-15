package com.vladco.fudo.shoplist

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladco.fudo.model.Model
import com.vladco.fudo.model.dao.ShopFoodDao
import com.vladco.fudo.model.data.ShopFood
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

@InjectViewState
class ShoplistPresenter : MvpPresenter<ShoplistView>() {

    private val model = Model()
    private val composite = CompositeDisposable()

    fun saveShopFood(shopFoodDao: ShopFoodDao, shopFood: ShopFood) {
        val d = model.insertShopFood(shopFoodDao, shopFood)
        composite.add(d)
    }

    fun getAllShopFood(shopFoodDao: ShopFoodDao): Flowable<List<ShopFood>> {
        return model.getAllShopFood(shopFoodDao)
    }

}