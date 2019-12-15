package com.vladco.fudo.model

import org.threeten.bp.LocalDate


object BarcodeStorage {

    private var barcode: String? = null
    private var date: LocalDate? = null

    fun setBarcode(barcode: String) {
        this.barcode = barcode
    }

    fun getBarcode(): String? = barcode

    fun clear() {
        barcode = null
    }

    fun setDate(date: LocalDate) {
        this.date = date
    }

    fun getDate(): LocalDate? = date

    fun clearDate() {
        date = null
    }


}