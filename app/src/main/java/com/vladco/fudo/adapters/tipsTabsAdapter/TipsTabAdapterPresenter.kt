package com.vladco.fudo.adapters.tipsTabsAdapter

import com.vladco.fudo.model.data.Tips

class TipsTabAdapterPresenter(private var tips: ArrayList<Tips>) {


    fun getCount(): Int = tips.size

    fun bind(view: TipsTabRowView, position: Int) {
        view.setData(tips[position])
    }


}