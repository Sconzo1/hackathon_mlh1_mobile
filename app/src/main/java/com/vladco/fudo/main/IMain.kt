package com.vladco.fudo.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IMain {

    interface View : MvpView {
        fun startScan()
    }

    interface Presenter {

    }
}