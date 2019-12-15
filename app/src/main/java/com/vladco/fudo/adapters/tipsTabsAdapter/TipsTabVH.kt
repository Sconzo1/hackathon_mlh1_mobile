package com.vladco.fudo.adapters.tipsTabsAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vladco.fudo.data.Tips
import kotlinx.android.synthetic.main.tips_row.view.*

class TipsTabVH(private val v: View) : RecyclerView.ViewHolder(v), TipsTabRowView {

    override fun setData(tips: Tips) {
        v.tips_tv_text.text = tips.text
    }

}