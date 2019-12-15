package com.vladco.fudo.main

import android.os.Bundle
import android.view.WindowManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.vladco.fudo.R
import com.vladco.fudo.calendar.CalendarFragment


class MainActivity : MvpAppCompatActivity(), MainView {


    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
//        setSupportActionBar(main_toolbar)

        init()
    }

    private fun init() {

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, CalendarFragment(), null)
            .commit()

    }

}