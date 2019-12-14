package com.vladco.fudo.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder
import com.vladco.fudo.R
import com.vladco.fudo.main.MainActivity

class CalendarFragment : MvpAppCompatFragment(), CalendarView {

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: CalendarPresenter

    var i = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.main_activity, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.startScan()
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {

    }

    override fun startScanView(builder: MaterialBarcodeScannerBuilder) {
        builder
            .withActivity(activity as MainActivity)
            .build()
            .startScan()
    }
}
