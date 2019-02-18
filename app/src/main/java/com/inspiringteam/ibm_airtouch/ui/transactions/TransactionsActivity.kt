package com.inspiringteam.ibm_airtouch.ui.transactions


import android.os.Bundle
import com.inspiringteam.ibm_airtouch.R
import com.inspiringteam.ibm_airtouch.utils.ActivityUtils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class TransactionsActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var injectedFragment: TransactionsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up fragment
        var fragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as? TransactionsFragment
        if (fragment == null) {
            fragment = injectedFragment
            ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment, R.id.contentFrame)
        }
    }
}
