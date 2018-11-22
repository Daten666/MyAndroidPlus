package com.daten.myandroidplus.ui.fragment;

import android.support.v4.view.PagerAdapter;

import com.daten.myandroidplus.di.ActivityScoped;

import javax.inject.Inject;

@ActivityScoped
public class ArticleFragment extends BaseMainFragment {

    @Inject
    public ArticleFragment() {}
    @Override
    PagerAdapter getPagerAdapter() {
        return null;
    }
}
