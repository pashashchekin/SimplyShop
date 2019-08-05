package com.somnium.simplyshop.mainpage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.somnium.simplyshop.R

class MainPageActivity : AppCompatActivity(), MainPageApiListener {
    private val api = MainPageApi(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        api.initAPI(this)

    }

    override fun onProductsUpdate() {

    }

    override fun onStart() {
        super.onStart()
        api.loadProducts()
    }
}
