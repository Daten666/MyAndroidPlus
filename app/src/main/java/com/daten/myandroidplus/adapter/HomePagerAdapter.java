package com.daten.myandroidplus.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.daten.myandroidplus.ui.fragment.DynamicFragment;
import com.daten.myandroidplus.ui.fragment.HotQuestionFragment;
import com.daten.myandroidplus.ui.fragment.QuestionFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {

    private final String[] mTitles;

    public HomePagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 : return new QuestionFragment();
            case 1 : return new HotQuestionFragment();
            case 2 : return new DynamicFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    /**
     * TabLayout里会调用到该方法获取页面标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
