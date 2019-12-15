package com.vladco.fudo.addProduct

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladco.fudo.model.Model
import com.vladco.fudo.model.dao.FoodDao
import com.vladco.fudo.model.data.Food

@InjectViewState
class AddProductPresenter : MvpPresenter<AddProductView>() {

    private val model = Model()

    fun findProduct() {
        model.getBarcode()?.let { viewState.setTextProduct(it) }
    }

    fun clickAddToCalendar() {
        viewState.getShelfDate()
    }

    fun continued(shDate: String, foodDao: FoodDao) {

        val name = model.getBarcode()


        name?.let {
            val f = Food(name = it, date = shDate, color = "#000008")

            model.insertFood(foodDao, f)


        }


    }
}