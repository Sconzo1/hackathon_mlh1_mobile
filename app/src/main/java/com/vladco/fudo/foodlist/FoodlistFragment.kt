package com.vladco.fudo.foodlist

import android.os.Bundle
import android.util.Log
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
import com.vladco.fudo.model.FudoDB
import com.vladco.fudo.model.data.Food
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.foodlist_fragment.*


@StateStrategyType(AddToEndSingleStrategy::class)
class FoodlistFragment : MvpAppCompatFragment(), FoodlistView {

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: FoodlistPresenter

    private val listFood = ArrayList<Food>()

    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.foodlist_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposables.add(presenter.getAllFood(FudoDB.getInstance(requireContext()).foodDao())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    listFood.clear()
                    listFood.addAll(it)
//                    Log.d("Fudo_appTag", it.toString())
                    init()
                },
                {
                    Log.d("Fudo_appTag", it.localizedMessage)
                }
            ))
    }

    private fun init() {


        val list = arrayListOf(
            Food(name = "Moloko", date = "12.11.18", color = "#536DFE"),
            Food(name = "Хлеб", date = "22.09.19", color = "#FCEC7F"),
            Food(name = "Пиво", date = "12.12.17", color = "#808080"),
            Food(name = "Ягуар", date = "10.04.17", color = "#575757"),
            Food(name = "Слойка", date = "19.10.18", color = "#808080"),
            Food(name = "Компот", date = "30.12.20", color = "#FCEC7F"),
            Food(name = "Пюре", date = "02.01.21", color = "#575757"),
            Food(name = "Картошка", date = "12.08.22", color = "#536DFE")
        )

        val adapter = FoodlistAdapter(FoodlistAdapterPresenter(listFood))

        foodlist_rv.layoutManager = LinearLayoutManager(requireContext())
        foodlist_rv.adapter = adapter

        foodlist_et_search.doOnTextChanged { text, _, _, _ ->
            text?.let {
                adapter.filter(it)
            }
        }

//        foodlist_et_search.doAfterTextChanged {  }

    }

}