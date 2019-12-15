package com.vladco.fudo.shoplist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.vladco.fudo.R
import com.vladco.fudo.adapters.shoplistAdapter.ShoplistAdapter
import com.vladco.fudo.adapters.shoplistAdapter.ShoplistAdapterPresenter
import com.vladco.fudo.helps.dpToPx
import com.vladco.fudo.model.FudoDB
import com.vladco.fudo.model.data.ShopFood
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.shoplist_fragment.*


class ShoplistFragment : MvpAppCompatFragment(), ShoplistView {

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: ShoplistPresenter

    lateinit var adapterPresenter: ShoplistAdapterPresenter
    lateinit var adapter: ShoplistAdapter

    private val disposables = CompositeDisposable()

    private val Context.inputMethodManager
        get() = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    private val inputDialog by lazy {
        val editText = AppCompatEditText(requireContext())
        val layout = FrameLayout(requireContext()).apply {
            val padding = dpToPx(20, requireContext())
            setPadding(padding, padding, padding, padding)
            addView(
                editText, FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            )
        }
        AlertDialog.Builder(requireContext())
            .setTitle("Type product")
            .setView(layout)
            .setPositiveButton("Add") { _, _ ->
                addProduct(editText.text.toString())
                editText.setText("")
            }
            .setNegativeButton("Close", null)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.shoplist_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }


    private fun init() {
        adapterPresenter = ShoplistAdapterPresenter(arrayListOf())
        adapter = ShoplistAdapter(adapterPresenter)

        disposables.add(presenter.getAllShopFood(FudoDB.getInstance(requireContext()).shopFoodDao())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    shoplist_rv.layoutManager = LinearLayoutManager(context)
                    shoplist_rv.adapter = ShoplistAdapter(ShoplistAdapterPresenter(it))

                },
                {
                }
            ))

        shoplist_fab_add.setOnClickListener {
            inputDialog.show()
        }
    }

    private fun addProduct(text: String) {
        presenter.saveShopFood(
            FudoDB.getInstance(requireContext()).shopFoodDao(),
            ShopFood(name = text)
        )


    }


}