package com.vladco.fudo.shoplist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.vladco.fudo.R


class ShoplistFragment : MvpAppCompatFragment(), ShoplistView {

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: ShoplistPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.shoplist_fragment, container, false)


}