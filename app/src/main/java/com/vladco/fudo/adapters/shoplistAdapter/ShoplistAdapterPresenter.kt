package com.vladco.fudo.adapters.shoplistAdapter

import com.vladco.fudo.model.data.ShopFood

class ShoplistAdapterPresenter(private var food: List<ShopFood>) {


    fun getCount(): Int = food.size

    fun bind(view: ShoplistRowView, position: Int) {
        view.setData(food[position])
    }

}