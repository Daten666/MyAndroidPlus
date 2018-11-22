package com.daten.myandroidplus.di;

import com.daten.myandroidplus.ui.fragment.ArticleFragment;
import com.daten.myandroidplus.ui.fragment.HomeFragment;
import com.daten.myandroidplus.ui.fragment.MeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeFragment homeFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ArticleFragment articleFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MeFragment meFragment();
}
