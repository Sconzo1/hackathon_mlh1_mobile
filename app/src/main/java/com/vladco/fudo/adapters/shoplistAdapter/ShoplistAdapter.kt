package com.vladco.fudo.adapters.shoplistAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vladco.fudo.R


class ShoplistAdapter(private val presenter: ShoplistAdapterPresenter) :
    RecyclerView.Adapter<ShoplistVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoplistVH {
        return ShoplistVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.shoplist_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ShoplistVH, position: Int) {
        presenter.bind(holder, position)
    }
}