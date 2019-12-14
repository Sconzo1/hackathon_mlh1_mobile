package com.vladco.fudo

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity

class ScannerActivity : MvpAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        startScan()
    }

//    private fun startScan() {
//        val materialBarcodeScanner = MaterialBarcodeScannerBuilder()
//            .withActivity(this)
//            .withEnableAutoFocus(true)
//            .withBackfacingCamera()
//            .withBarcodeFormats(Barcode.EAN_13)
//            .withCenterTracker()
//            .withText("Сканирование...")
//            .withResultListener {
//                it?.let {
//                    resultScan(it)
//                }
//            }
//            .build()
//        materialBarcodeScanner.startScan()
//    }
//
//    private fun resultScan(barcode: Barcode) {
//
//        val barcodeResult = barcode.rawValue
//        intent.putExtra("barcode", barcodeResult)
//        setResult(Activity.RESULT_OK, intent)
//        finish()
//
//    }

}