package com.vladco.fudo.tipsTabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.vladco.fudo.R
import com.vladco.fudo.adapters.ZoomOutPageTransformer
import com.vladco.fudo.adapters.tipsTabsAdapter.TipsTabAdapter
import com.vladco.fudo.adapters.tipsTabsAdapter.TipsTabAdapterPresenter
import com.vladco.fudo.model.data.Tips
import kotlinx.android.synthetic.main.tipstab_fragment.*


class TipsTabFragment : MvpAppCompatFragment(), TipsTabView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.tipstab_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {

        val list = arrayListOf(
            Tips("kofe", "Откажитесь от пластиковых бутылок и одноразовых стаканчиков для кофе"),
            Tips("britva", "Купите многоразовую бритву"),
            Tips("vatnye_palochki", "Замените «расходники» eco-friendly аналогами"),
            Tips("bag", "Пользуйтесь холщовой сумкой вместо пластиковых пакетов")
        )

        val adapter = TipsTabAdapter(TipsTabAdapterPresenter(list))

        tipsTab_vp.adapter = adapter

        tipsTab_dots.setViewPager2(tipsTab_vp)
        tipsTab_vp.setPageTransformer(ZoomOutPageTransformer())
    }

}