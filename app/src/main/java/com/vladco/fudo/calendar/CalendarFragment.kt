package com.vladco.fudo.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder
import com.vladco.fudo.R

class CalendarFragment : MvpFragment(), CalendarView {

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: CalendarPresenter

//    @ProvidePresenterTag(presenterClass = CalendarPresenter::class, type = PresenterType.LOCAL)
//    fun provideDialogPresenterTag(): String = "Calendar"
//
//    @ProvidePresenter(type = PresenterType.LOCAL)
//    fun provideDialogPresenter() = CalendarPresenter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.main_activity, container, false)

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        presenter.startScan()
    }

    override fun startScanView(builder: MaterialBarcodeScannerBuilder) {
        builder
            .withActivity(activity)
            .build()
            .startScan()
    }


}