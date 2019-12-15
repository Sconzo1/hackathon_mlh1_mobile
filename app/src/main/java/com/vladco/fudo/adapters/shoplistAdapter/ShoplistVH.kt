package com.vladco.fudo.adapters.shoplistAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vladco.fudo.model.data.ShopFood
import kotlinx.android.synthetic.main.shoplist_row.view.*

class ShoplistVH(private val v: View) : RecyclerView.ViewHolder(v), ShoplistRowView {

    override fun setData(food: ShopFood) {
        v.shoplist_row_cchb.text = food.name
        v.shoplist_row_cchb.setChecked(food.isBought)
    }
}