package com.vladco.fudo.calendar

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder
import com.google.android.gms.vision.barcode.Barcode

@InjectViewState
class CalendarPresenter : MvpPresenter<CalendarView>() {

    fun startScan() {
        val builder = MaterialBarcodeScannerBuilder()
            .withEnableAutoFocus(true)
            .withBackfacingCamera()
            .withBarcodeFormats(Barcode.EAN_13)
            .withCenterTracker()
            .withText("Scanning...")
            .withResultListener {
                resultScan(it.displayValue)
            }
        viewState.startScanView(builder)
    }


    private fun resultScan(barcode: String) {
        Log.d("Fudo_appTag", barcode)
    }

}