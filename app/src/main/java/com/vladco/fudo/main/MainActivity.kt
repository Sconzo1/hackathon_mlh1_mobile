package com.vladco.fudo.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.vladco.fudo.ScannerActivity


class MainActivity : MvpAppCompatActivity(), IMain.View {

    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, ScannerActivity::class.java)

        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK)
            data?.getStringExtra("barcode")?.let {
                // TODO()
            }
    }


}