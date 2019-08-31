package com.somnium.simplyshop.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.TextView
import com.somnium.simplyshop.R
import com.somnium.simplyshop.adapters.SoftOnBoardingAdapter
import me.relex.circleindicator.CircleIndicator

const val PREFS_SOFT_ON_BOARDING = "SoftOnBoarding"
const val KEY_SOFT_ON_BOARDING_VIEWED = "soft_on_boarding_viewed"

class SoftOnBoardingActivity : AppCompatActivity() {

    companion object Boarding{
        fun isViewed(context: Context): Boolean{
            val prefs = context.getSharedPreferences(PREFS_SOFT_ON_BOARDING, Context.MODE_PRIVATE)
            return prefs.getBoolean(KEY_SOFT_ON_BOARDING_VIEWED, false)
        }

        fun setViewed(context: Context){
            val prefs = context.getSharedPreferences(PREFS_SOFT_ON_BOARDING, Context.MODE_PRIVATE)
            prefs.edit().putBoolean(KEY_SOFT_ON_BOARDING_VIEWED, true).apply()
        }
    }

    private var viewPager: ViewPager? = null
    private var passBtn: TextView? = null
    private var nextBtn: TextView? = null
    private var adapter: SoftOnBoardingAdapter = SoftOnBoardingAdapter(supportFragmentManager)
    private var indicator: CircleIndicator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soft_on_boarding)
        initUI()
    }

    private fun initUI() {
        viewPager = findViewById(R.id.activity_soft_on_boarding_view_pager)
        viewPager?.adapter = adapter
        viewPager?.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageSelected(position: Int) {
                onPageChanged(position)
            }
        })

        indicator = findViewById(R.id.activity_soft_on_boarding_indicator)
        indicator?.setViewPager(viewPager)
        passBtn = findViewById(R.id.activity_soft_on_boarding_pass)
        passBtn?.setOnClickListener(this::onPassClick)
        nextBtn = findViewById(R.id.activity_soft_on_boarding_next)
        nextBtn?.setOnClickListener(this::onNextClick)
    }

    private fun onPageChanged(position: Int) {
        nextBtn?.setText(if (adapter.count == position + 1) R.string.ready else R.string.next)
    }

    private fun onPassClick(v: View) {
        finish()
    }

    private fun onNextClick(v: View) {
        if(adapter.count == viewPager!!.currentItem + 1){
            finish()
        }else {
            viewPager?.setCurrentItem(viewPager!!.currentItem + 1, true)
        }
    }

    override fun finish() {
        super.finish()
        setViewed(this)
    }

}
