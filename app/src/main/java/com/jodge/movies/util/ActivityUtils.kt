package com.jodge.movies.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class ActivityUtils {

    companion object {
        fun addFragmentToActivity(fragmentManager: FragmentManager, fragment: Fragment,
                                  frameId: Int, tag: String? = null) {
            val transaction = fragmentManager.beginTransaction()
            transaction.add(frameId, fragment, tag)
            transaction.commit()
        }
    }
}
