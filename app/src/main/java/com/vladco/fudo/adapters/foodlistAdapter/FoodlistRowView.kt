package com.vladco.fudo.adapters.foodlistAdapter

import com.vladco.fudo.model.data.Food

interface FoodlistRowView {

    fun setData(food: Food)
}