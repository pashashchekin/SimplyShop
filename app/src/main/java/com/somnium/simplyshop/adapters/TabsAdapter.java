package com.somnium.simplyshop.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.somnium.simplyshop.fragments.CategoriesFragment;


public class TabsAdapter extends FragmentPagerAdapter {
    public enum PageType {
        CATEGORIES
    }

    String[] tabs;
    private PageType pageType;

    public TabsAdapter(FragmentManager fm, String[] tabs, PageType pageType) {
        super(fm);
        this.tabs = tabs;
        this.pageType = pageType;
    }

    @Override
    public Fragment getItem(int position) {
        if (pageType == PageType.CATEGORIES) {
            return CategoriesFragment.Companion.getInstance();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
