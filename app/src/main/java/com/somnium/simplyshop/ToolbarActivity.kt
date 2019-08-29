package com.somnium.simplyshop

import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.TextView
import com.somnium.simplyshop.activities.DisposableActivity

abstract class ToolbarActivity : DisposableActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var toolbarTitle: TextView

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)
        try {
            initToolbar()
        } catch (e: Exception) {
            System.err.println("Toolbar cannot be initialized! Please add toolbar in your layout file!")
            e.printStackTrace()
        }
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = ""
        toolbarTitle = findViewById<TextView>(R.id.titleToolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setTitle(title: String) {
        try {
            toolbarTitle.text = title
        }catch (e:Exception){
            super.setTitle(title)
        }
    }

    override fun setTitle(@StringRes title: Int) {
        try {
            toolbarTitle.setText(title)
        }catch (e:Exception){
            super.setTitle(title)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return false
    }
}