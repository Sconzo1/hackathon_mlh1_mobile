package com.vladco.fudo.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.vladco.fudo.*
import com.vladco.fudo.addProduct.AddProductFragment
import com.vladco.fudo.foodlist.FoodlistFragment
import com.vladco.fudo.main.MainActivity
import com.vladco.fudo.shoplist.ShoplistFragment
import com.vladco.fudo.tipsTabs.TipsTabFragment
import kotlinx.android.synthetic.main.calendar_day_layout.view.*
import kotlinx.android.synthetic.main.calendar_fragment.*
import kotlinx.android.synthetic.main.calendar_legend.view.*
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.TextStyle
import java.util.*


// TODO (Перенести бизнес-логику календаря в P)


class CalendarFragment : MvpAppCompatFragment(), CalendarView {

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: CalendarPresenter

    private var selectedDate: LocalDate? = null
    private val today = LocalDate.now()
    private val titleSameYearFormatter =
        DateTimeFormatter.ofPattern("MMMM").withLocale(Locale.ENGLISH)
    private val titleFormatter = DateTimeFormatter.ofPattern("MMMM yyyy").withLocale(Locale.ENGLISH)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.calendar_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        presenter.startScan()
        init()
    }

    private fun init() {

        initCalendar()

        calendar_btn_tipsStatistics.setOnClickListener {
            presenter.clickTips()
        }
        calendar_im_shoplist.setOnClickListener {
            presenter.clickShopList()
        }
        calendar_im_add.setOnClickListener {
            presenter.clickAdd()
        }
        calendar_btn_myFood.setOnClickListener {
            presenter.clickFoodList()
        }
    }


    private fun initCalendar() {

        calendar_calv.post {
            selectDate(today)
        }

        val daysOfWeek = daysOfWeekFromLocale()
        val currentMonth = YearMonth.now()
        calendar_calv.setup(
            currentMonth.minusMonths(2),
            currentMonth.plusMonths(10),
            daysOfWeek.first()
        )
        calendar_calv.scrollToMonth(currentMonth)

        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay // Will be set when this container is bound.
            val textView = view.calendarDay_tv_day
            /*val dotView = view.exThreeDotView*/

            init {
                view.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH) {
                        selectDate(day.date)
                    }
                }
            }
        }

        calendar_calv.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)

            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                val dayView = container.textView
                /*val dotDayView = container.dotView*/

                dayView.text = day.date.dayOfMonth.toString()

                if (day.owner == DayOwner.THIS_MONTH) {
                    dayView.makeVisible()

                    if (day.date == today && day.date == selectedDate) {
                        dayView.setTextColorRes(R.color.example_3_white)
                        dayView.setBackgroundResource(R.drawable.calendar_day_today_selected_bg)
//                        dotDayView.makeInVisible()

                    } else if (day.date == today) {
                        dayView.setTextColorRes(R.color.example_3_white)
                        dayView.setBackgroundResource(R.drawable.calendar_day_today_bg)
//                        dotDayView.makeInVisible()

                    } else if (day.date == selectedDate) {
                        dayView.setBackgroundResource(R.drawable.calendar_day_selected_bg)
//                        dotDayView.makeInVisible()

                    } else {
                        dayView.setTextColorRes(R.color.calendar_day)
                        dayView.background = null

                        if (day.date.dayOfWeek == DayOfWeek.SATURDAY.plus(0) ||
                            day.date.dayOfWeek == DayOfWeek.SUNDAY.plus(0)
                        )
                            dayView.setTextColorRes(R.color.calendar_weekendDay)

                    }
                } else {
                    dayView.makeInVisible()
//                    dotDayView.makeInVisible()
                }
            }
        }

        calendar_calv.monthScrollListener = {
            selectDate(it.yearMonth.atDay(1))

            calendar_tv_month.text = if (it.year == today.year) {
                titleSameYearFormatter.format(it.yearMonth)
            } else {
                titleFormatter.format(it.yearMonth)
            }
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val legendLayout = view.legendLayout
        }

        calendar_calv.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthViewContainer> {
            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                if (container.legendLayout.tag == null) {
                    container.legendLayout.tag = month.yearMonth
                    container.legendLayout.children.map { it as TextView }
                        .forEachIndexed { index, tv ->
                            tv.text =
                                daysOfWeek[index].getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                                    .toUpperCase(Locale.ENGLISH)
                            if (daysOfWeek[index] == DayOfWeek.SATURDAY ||
                                daysOfWeek[index] == DayOfWeek.SUNDAY
                            ) {
                                tv.setTextColorRes(R.color.calendar_weekendDay)
                            } else
                                tv.setTextColorRes(R.color.calendar_weekdayDayOfWeek)
                        }
                    month.yearMonth
                }
            }
        }


    }

    private fun selectDate(date: LocalDate) {
        if (selectedDate != date) {
            val oldDate = selectedDate
            selectedDate = date
            oldDate?.let { calendar_calv.notifyDateChanged(it) }
            calendar_calv.notifyDateChanged(date)
        }
    }

    override fun startScanView(builder: MaterialBarcodeScannerBuilder) {
        builder
            .withActivity(activity as MainActivity)
            .build()
            .startScan()
    }

    override fun toFoodListFragment() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.main_container, FoodlistFragment(), null)
            ?.addToBackStack(FoodlistFragment::class.java.toString())
            ?.commit()
    }

    override fun toAddProductFragment() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.main_container, AddProductFragment(), null)
            ?.addToBackStack(FoodlistFragment::class.java.toString())
            ?.commit()
    }

    override fun toShopListFragment() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.main_container, ShoplistFragment(), null)
            ?.addToBackStack(ShoplistFragment::class.java.toString())
            ?.commit()
    }

    override fun toTipsTabFragment() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.main_container, TipsTabFragment(), null)
            ?.addToBackStack(TipsTabFragment::class.java.toString())
            ?.commit()
    }


}
