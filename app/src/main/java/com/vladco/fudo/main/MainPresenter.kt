package com.vladco.fudo.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class MainPresenter : MvpPresenter<IMain.View>() {

    fun onStartScan() {
        viewState.startScan()
    }

}