package com.vladco.fudo.addProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.vladco.fudo.R
import com.vladco.fudo.model.FudoDB
import kotlinx.android.synthetic.main.addproduct_fragment.*
import org.threeten.bp.LocalDate


class AddProductFragment : MvpAppCompatFragment(), AddProductView {

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: AddProductPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.addproduct_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        addProduct_sp_shelfLife.setItems("Day", "Month", "Year")

        presenter.findProduct()


        addProduct_btn_add.setOnClickListener {
            presenter.clickAddToCalendar()
        }

    }

    override fun setTextProduct(name: String) {
        addProduct_tv_name.text = name
    }


    override fun getShelfDate() {
        val dateProd = addProduct_et_date.text.toString()

        val dateProdLD = LocalDate.parse(dateProd) // Format (yyyy-MM-dd)

        val dTime = addProduct_et_count.text.toString().toLong()

        var sd = dateProdLD

        when (addProduct_sp_shelfLife.selectedIndex) {
            0 -> {
                sd = dateProdLD.plusDays(dTime)
            }
            1 -> {
                sd = dateProdLD.plusMonths(dTime)
            }
            2 -> {
                sd = dateProdLD.plusYears(dTime)
            }
            else -> {

            }
        }

        presenter.continued(sd.toString(), FudoDB.getInstance(requireContext()).foodDao())

    }
}