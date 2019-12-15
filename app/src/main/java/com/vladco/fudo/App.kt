package com.vladco.fudo

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.vladco.fudo.model.FudoDB

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
        FudoDB.getInstance(applicationContext)
    }

}