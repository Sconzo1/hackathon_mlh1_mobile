package com.vladco.fudo.addProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.vladco.fudo.R
import kotlinx.android.synthetic.main.addproduct_fragment.*


class AddProductFragment : MvpAppCompatFragment(), AddProductView {

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: AddProductPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.addproduct_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        addProduct_sp_shelfLife.setItems("Day", "Month", "Year")

    }
}