package com.jodge.movies.ui.listing.sorting

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SortingOptionStore @Inject constructor(context: Context) {

    private var pref: SharedPreferences

    init {
        pref = context.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setSelectOption(sortType: SortType) {
        val editor = pref.edit()
        editor.putInt(SELECTED_OPTION, sortType.value)
        editor.apply()
    }

    fun getSelectedOption(): Int  = pref.getInt(SELECTED_OPTION, 0)

    companion object {
        const val SELECTED_OPTION = "selectedOption"
        const val PREF_NAME = "SortingOptionStore"
    }
}
