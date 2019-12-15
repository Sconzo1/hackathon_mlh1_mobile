package com.vladco.fudo.adapters

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max

class ZoomOutPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val pageWidth = view.width
        val pageHeight = view.height

        when {
            position < -1 ->
                view.alpha = 0f
            position <= 1 -> {
                val scaleFactor = max(MIN_SCALE, 1 - abs(position))
                val vertMargin = pageHeight * (1 - scaleFactor) / 2
                val horzMargin = pageWidth * (1 - scaleFactor) / 2
                if (position < 0) {
                    view.translationX = horzMargin - vertMargin / 2
                } else {
                    view.translationX = -horzMargin + vertMargin / 2
                }

                view.scaleX = scaleFactor
                view.scaleY = scaleFactor

                view.alpha =
                    MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA)
            }
            else ->
                view.alpha = 0f
        }
    }

    companion object {
        private const val MIN_SCALE = 0.90f
        private const val MIN_ALPHA = 0.5f
    }
}