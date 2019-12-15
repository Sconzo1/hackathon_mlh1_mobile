package com.vladco.fudo.adapters.foodlistAdapter

import android.util.Log
import com.vladco.fudo.model.data.Food

class FoodlistAdapterPresenter(private var food: ArrayList<Food>) {

    private var foodCopy: ArrayList<Food> = ArrayList(food)


    fun getCount(): Int = food.size

    fun bind(view: FoodlistRowView, position: Int) {
        view.setData(food[position])
    }

    fun filter(sequence: CharSequence) {

        val temp = ArrayList<Food>()

        Log.d("Fudo_appTag", sequence.isNotEmpty().toString())
        if (sequence.isNotEmpty()) {
            for (f in foodCopy) {
                if (f.name.toLowerCase().contains(sequence.toString().toLowerCase()))
                    temp.add(f)
            }
        } else {
            temp.addAll(foodCopy)
        }
        Log.d("Fudo_appTag", foodCopy.toString())

        food.clear()
        food.addAll(temp)

        temp.clear()
    }

}