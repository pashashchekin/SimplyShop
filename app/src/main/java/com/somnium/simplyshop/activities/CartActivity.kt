package com.somnium.simplyshop.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.somnium.simplyshop.BaseActivity
import com.somnium.simplyshop.R

class CartActivity : BaseActivity(2) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setupBottomNavigation()
    }
}
