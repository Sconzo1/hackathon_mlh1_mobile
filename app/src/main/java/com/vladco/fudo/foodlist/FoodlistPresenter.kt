package com.vladco.fudo.foodlist

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladco.fudo.model.Model
import com.vladco.fudo.model.dao.FoodDao
import com.vladco.fudo.model.data.Food
import io.reactivex.Flowable

@InjectViewState
class FoodlistPresenter : MvpPresenter<FoodlistView>() {

    private val model = Model()

    fun getAllFood(foodDao: FoodDao): Flowable<List<Food>> {
        return model.getAllFood(foodDao)
    }


}