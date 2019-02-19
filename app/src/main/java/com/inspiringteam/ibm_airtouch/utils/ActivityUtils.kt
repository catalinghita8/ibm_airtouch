package com.inspiringteam.ibm_airtouch.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


object ActivityUtils {
    /**
     * The fragment is added to the container view with id frameId. The operation is
     * performed by the fragmentManager.
     */
    fun addFragmentToActivity(
        fragmentManager: FragmentManager,
        fragment: Fragment, frameId: Int
    ) {
        checkNotNull(fragmentManager)
        checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }
}