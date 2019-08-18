package com.somnium.simplyshop.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.somnium.simplyshop.BaseActivity
import com.somnium.simplyshop.R

class ProfileActivity : BaseActivity(4) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        setupBottomNavigation()
    }
}
