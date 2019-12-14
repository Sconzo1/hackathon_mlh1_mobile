package com.vladco.fudo.navigation

import com.vladco.fudo.common.BaseFragment

interface FragmentNavigation {

    interface View {
        fun attachPresenter(presenter: Presenter)
    }

    interface Presenter {
        fun addFragment(fragment: BaseFragment)
    }
}