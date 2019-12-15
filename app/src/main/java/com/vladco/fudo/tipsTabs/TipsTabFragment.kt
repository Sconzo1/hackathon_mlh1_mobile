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
import com.vladco.fudo.data.Tips
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
            Tips("1", "asdaaaaaaaaaaaaasdadasdasd"),
            Tips("2", "asdaaaaaaaaa456123877321323121231237786aaaasdadasdasd"),
            Tips("3", "456123877321323121231237786"),
            Tips("4", "asdaaaaaa238213231212aasdadas238773213231212dasd"),
            Tips("5", "asdaa312123aaaaaaa312123aaaasdad312123asdasd"),
            Tips("6", "asdaaaaaa88888888asdadasdasd"),
            Tips("7", "asdaaaaaa777777777sd")
        )

        val adapter = TipsTabAdapter(TipsTabAdapterPresenter(list))

        tipsTab_vp.adapter = adapter

        tipsTab_dots.setViewPager2(tipsTab_vp)
        tipsTab_vp.setPageTransformer(ZoomOutPageTransformer())
    }

}