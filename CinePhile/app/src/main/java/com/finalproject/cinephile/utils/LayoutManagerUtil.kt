package com.finalproject.cinephile.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

object LayoutManagerUtil {

    fun getHorizontalLayoutManager(context: Context): LinearLayoutManager = LinearLayoutManager(
        context,
        LinearLayoutManager.HORIZONTAL,
        false
    )

    fun getVerticalLayoutManager(context: Context): LinearLayoutManager = LinearLayoutManager(
        context,
        LinearLayoutManager.VERTICAL,
        false
    )

}