package com.vladco.fudo.calendar

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder
import com.google.android.gms.vision.barcode.Barcode
import com.vladco.fudo.model.Model
import com.vladco.fudo.model.dao.FoodDao
import com.vladco.fudo.model.data.Food
import io.reactivex.Flowable
import org.threeten.bp.LocalDate

@InjectViewState
class CalendarPresenter : MvpPresenter<CalendarView>() {

    private val model = Model()

    fun clickFoodList() {
        viewState.toFoodListFragment()
    }

    fun clickShopList() {
        viewState.toShopListFragment()
    }

    fun clickTips() {
        viewState.toTipsTabFragment()
    }

    fun clickAdd(selectedDate: LocalDate?) {

        if (selectedDate != null) {
            model.saveDate(selectedDate)
        }

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

    fun getAllFood(foodDao: FoodDao): Flowable<List<Food>> {
        return model.getAllFood(foodDao)
    }

    private fun resultScan(barcode: String) {
        model.saveBarcode(barcode)
        viewState.toAddProductFragment()
    }

}