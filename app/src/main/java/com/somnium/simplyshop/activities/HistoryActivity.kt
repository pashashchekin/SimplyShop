package com.somnium.simplyshop.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.somnium.simplyshop.BaseActivity
import com.somnium.simplyshop.R

class HistoryActivity : BaseActivity(3) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setupBottomNavigation()
    }
}
