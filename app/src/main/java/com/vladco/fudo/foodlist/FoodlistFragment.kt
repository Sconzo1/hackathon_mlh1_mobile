package com.vladco.fudo.foodlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.vladco.fudo.R
import com.vladco.fudo.adapters.foodlistAdapter.FoodlistAdapter
import com.vladco.fudo.adapters.foodlistAdapter.FoodlistAdapterPresenter
import com.vladco.fudo.data.Food
import kotlinx.android.synthetic.main.foodlist_fragment.*


@StateStrategyType(AddToEndSingleStrategy::class)
class FoodlistFragment : MvpAppCompatFragment(), FoodlistView {

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: FoodlistPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.foodlist_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {

        val list = arrayListOf(
            Food("Moloko", "12.11.18", "#536DFE"),
            Food("Хлеб", "22.09.19", "#FCEC7F"),
            Food("Пиво", "12.12.17", "#808080"),
            Food("Ягуар", "10.04.17", "#575757"),
            Food("Слойка", "19.10.18", "#808080"),
            Food("Компот", "30.12.20", "#FCEC7F"),
            Food("Пюре", "02.01.21", "#575757"),
            Food("Картошка", "12.08.22", "#536DFE")
        )

        val adapter = FoodlistAdapter(FoodlistAdapterPresenter(list))

        foodlist_rv.layoutManager = LinearLayoutManager(requireContext())
        foodlist_rv.adapter = adapter

        foodlist_et_search.doOnTextChanged { text, start, count, after ->
            text?.let {
                adapter.filter(it)

            }
        }

//        foodlist_et_search.doAfterTextChanged {  }

    }

}