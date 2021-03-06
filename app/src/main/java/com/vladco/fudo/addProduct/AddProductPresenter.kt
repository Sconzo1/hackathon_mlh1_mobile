package com.vladco.fudo.addProduct

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vladco.fudo.model.Model
import com.vladco.fudo.model.dao.FoodDao
import com.vladco.fudo.model.data.Food
import java.util.*

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

            val rnd = Random()
            val colors = listOf<String>("#DD4B4B", "#EAFB2B", "#56DD4B")
            val f = Food(name = it, date = shDate, color = colors[rnd.nextInt(3)])

            model.insertFood(foodDao, f)


        }


    }
}