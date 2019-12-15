package com.vladco.fudo.addProduct

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface AddProductView : MvpView {

    fun setTextProduct(name: String)

    fun getShelfDate()

}