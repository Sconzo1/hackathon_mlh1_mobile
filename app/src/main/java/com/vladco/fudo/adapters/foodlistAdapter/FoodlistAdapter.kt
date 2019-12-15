package com.vladco.fudo.adapters.foodlistAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vladco.fudo.R
import com.vladco.fudo.model.FudoDB
import com.vladco.fudo.model.Model
import kotlinx.android.synthetic.main.foodlist_row.view.*


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

        holder.itemView.foodlist_row_color.setOnClickListener {


            val model = Model()

            model.deleteFood(
                FudoDB.getInstance(holder.itemView.context).foodDao(),
                presenter.get(position)
            )


        }
    }

    fun filter(sequence: CharSequence) {

        presenter.filter(sequence)

        notifyDataSetChanged()

    }
}