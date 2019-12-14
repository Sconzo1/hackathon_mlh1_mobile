package com.vladco.fudo.common

import androidx.fragment.app.Fragment
import com.vladco.fudo.navigation.FragmentNavigation

class BaseFragment : Fragment(), FragmentNavigation.View {

    private lateinit var navigationPresenter: FragmentNavigation.Presenter

    override fun attachPresenter(presenter: FragmentNavigation.Presenter) {
        navigationPresenter = presenter
    }
}