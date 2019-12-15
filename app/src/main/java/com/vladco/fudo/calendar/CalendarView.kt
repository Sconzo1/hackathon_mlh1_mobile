package com.vladco.fudo.calendar

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder

@StateStrategyType(AddToEndStrategy::class)
interface CalendarView : MvpView {

    fun startScanView(builder: MaterialBarcodeScannerBuilder)

    @StateStrategyType(SkipStrategy::class)
    fun toFoodListFragment()


}