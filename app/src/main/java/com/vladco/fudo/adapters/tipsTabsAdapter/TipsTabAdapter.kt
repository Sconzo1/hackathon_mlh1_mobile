package com.vladco.fudo.adapters.tipsTabsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vladco.fudo.R

class TipsTabAdapter(private val presenter: TipsTabAdapterPresenter) :
    RecyclerView.Adapter<TipsTabVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipsTabVH {
        return TipsTabVH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.tips_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: TipsTabVH, position: Int) {
        presenter.bind(holder, position)
    }
}