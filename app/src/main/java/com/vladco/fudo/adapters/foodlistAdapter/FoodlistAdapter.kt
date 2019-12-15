package com.vladco.fudo.adapters.foodlistAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vladco.fudo.R


class FoodlistAdapter(private val presenter: FoodlistAdapterPresenter) :
    RecyclerView.Adapter<FoodlistVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodlistVH {
        return FoodlistVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.foodlist_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: FoodlistVH, position: Int) {
        presenter.bind(holder, position)
    }

    fun filter(sequence: CharSequence) {

        presenter.filter(sequence)

        notifyDataSetChanged()

    }
}