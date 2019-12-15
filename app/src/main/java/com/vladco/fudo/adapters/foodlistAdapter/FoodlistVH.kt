package com.vladco.fudo.adapters.foodlistAdapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vladco.fudo.model.data.Food
import kotlinx.android.synthetic.main.foodlist_row.view.*

class FoodlistVH(private val v: View) : RecyclerView.ViewHolder(v), FoodlistRowView {

    override fun setData(food: Food) {
        v.apply {
            foodlist_row_name.text = food.name
            foodlist_row_date.text = food.date
            foodlist_row_color.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor(food.color))
        }
    }
}