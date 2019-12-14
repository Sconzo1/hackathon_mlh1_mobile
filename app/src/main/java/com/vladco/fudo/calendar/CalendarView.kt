package com.vladco.fudo.calendar

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder

@StateStrategyType(AddToEndSingleStrategy::class)
interface CalendarView : MvpView {

    fun startScanView(builder: MaterialBarcodeScannerBuilder)


}