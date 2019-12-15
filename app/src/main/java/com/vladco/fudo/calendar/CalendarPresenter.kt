package com.vladco.fudo.calendar

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class CalendarPresenter : MvpPresenter<CalendarView>() {

    fun clickFoodList() {
        viewState.toFoodListFragment()
    }

    fun clickShopList() {
        viewState.toShopListFragment()
    }

    fun clickTips() {
        viewState.toTipsTabFragment()
    }

    fun clickAdd() {
        viewState.toAddProductFragment() // TODO(SKIP!!!)


//        val builder = MaterialBarcodeScannerBuilder()
//            .withEnableAutoFocus(true)
//            .withBackfacingCamera()
//            .withBarcodeFormats(Barcode.EAN_13)
//            .withCenterTracker()
//            .withText("Scanning...")
//            .withResultListener {
//                resultScan(it.displayValue)
//            }
//        viewState.startScanView(builder)


    }


    private fun resultScan(barcode: String) {
        Log.d("Fudo_appTag", barcode)
    }

}