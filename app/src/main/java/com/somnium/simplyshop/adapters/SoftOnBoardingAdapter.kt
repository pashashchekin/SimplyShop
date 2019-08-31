package com.somnium.simplyshop.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.somnium.simplyshop.R
import com.somnium.simplyshop.fragments.OnBoardingPageFragment

class SoftOnBoardingAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private  val fragments: MutableList<Fragment> = ArrayList()

    init {
        fragments.add(OnBoardingPageFragment.create(R.string.soft_of_title1,R.string.soft_of_body1,R.color.red,0))
        fragments.add(OnBoardingPageFragment.create(R.string.soft_of_title2,R.string.soft_of_body2,R.color.red,1))
        fragments.add(OnBoardingPageFragment.create(R.string.soft_of_title3,R.string.soft_of_body3,R.color.red,2))
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

}