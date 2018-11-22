package com.daten.myandroidplus.ui.fragment;

import android.support.v4.view.PagerAdapter;

import com.daten.myandroidplus.di.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class MeFragment extends BaseMainFragment {

    @Inject
    public MeFragment() {}
    @Override
    PagerAdapter getPagerAdapter() {
        return null;
    }
}
